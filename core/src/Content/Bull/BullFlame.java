package Content.Bull;
import com.mygdx.game.bull.Bullet;
import com.mygdx.game.bull.EffectBullet;
import com.mygdx.game.method.rand;

import com.mygdx.game.main.Main;

import static Data.DataColor.*;

public class BullFlame extends Bullet {
    public BullFlame(float x, float y, float rotation, float damage, float t_damage, float penetration, byte type_team, byte height){
        this.x = x;
        this.y = y;
        this.r = FlameR;this.g = FlameG;this.b = FlameB;
        this.rotation = rotation;
        this.type_team = type_team;
        this.height = height;
        this.damage = damage;
        this.penetration = penetration;
        this.t_damage = t_damage;

        this.size = 8+rand.rand(8);
        this.size_render = (int)(size*Main.Zoom);
        this.speed = -5;
        this.time = 65+rand.rand(15);
        type = 1;
        speed_save();
        effectBullet = EffectBullet.Flame;

    }
    public void all_action(int i){
        super.bull_move_xy();
        super.color_fire();
        super.corpus_bull();
        super.BullBuildFlame();
        super.bull_clear_time_flame();
        this.update();
        super.clear();
    }

    public void update(){
        Main.Render.setColor(r,g,b,1);
        center_render();
        Main.Render.circle(this.x_rend,this.y_rend,this.size_render,this.size);
    }
}
