// lighting.frag
#ifdef GL_ES
    precision mediump float;
#endif

#define MAX_LIGHTS 160

uniform sampler2D u_texture;
uniform vec4 u_ambientColor; // RGBA
uniform float u_minLightness;

struct Light {
    vec2 position;
    vec4 color;         // RGBA
    float intensity;
    float radius;
    float transparency; // 0.0 - непрозрачный, 1.0 - полностью прозрачный
};

uniform Light u_lights[MAX_LIGHTS];
uniform int u_activeLights;

varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_worldPos;

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoords) * v_color;

    // Пропускаем полностью прозрачные пиксели
    if (texColor.a <= 0.0) discard;

    vec4 accumulatedLight = u_ambientColor;

    for (int i = 0; i < MAX_LIGHTS; i++) {
        if (i >= u_activeLights) break;

        Light light = u_lights[i];
        float dist = distance(v_worldPos, light.position);

        // Пропускаем свет вне радиуса
        if (dist > light.radius) continue;

        // Рассчитываем затухание с учётом прозрачности
        float attenuation = 1.0 - smoothstep(light.radius * 0.7, light.radius, dist);
        attenuation *= (1.0 - light.transparency); // Учитываем прозрачность
        attenuation = pow(attenuation, 1.5);

        // Рассчитываем вклад света с учётом его альфа-канала
        vec4 lightEffect = light.color * light.intensity * attenuation;

        // Правильное смешивание с накопленным светом
        accumulatedLight.rgb += lightEffect.rgb * lightEffect.a;
        accumulatedLight.a *= (1.0 - lightEffect.a * attenuation);
    }

    // Применяем освещение к текстуре
    vec4 finalColor = texColor;
    finalColor.rgb *= max(accumulatedLight.rgb, vec3(u_minLightness));
    finalColor.a *= accumulatedLight.a;

    // Финализируем цвет
    finalColor.rgb = clamp(finalColor.rgb, 0.0, 1.0);
    finalColor.a = clamp(1-finalColor.a, 0.0, 1.0);

    gl_FragColor = finalColor;
}