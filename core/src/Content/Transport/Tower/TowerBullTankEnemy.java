package Content.Transport.Tower;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.Unit;

import java.util.ArrayList;

public class TowerBullTankEnemy extends Unit {
    public TowerBullTankEnemy(float x_const, float y_const, int difference, int difference_2, float reload_max, float speed_rotation, float damage, float penetration,
                              int ind_unit, byte height, byte team, Sprite tower_image, ArrayList<Unit> List, Sound sound){
        this.tower_x_const = x_const;
        this.tower_y_const = y_const;
        this.allyList = List;
        this.sound_fire = sound;
        this.speed_tower = speed_rotation;
        this.reload_max = reload_max;
        this.reload = this.reload_max;
        this.damage = damage;
        this.penetration = penetration;
        this.tower_img = tower_image;
        this.id_unit = ind_unit;
        this.teg_unit = "tower";
        this.height = height;
        this.team = team;
        this.difference = difference;
        this.difference_2 = difference_2;
        this.width_tower = 15;
        this.height_tower = 20;
        fire = FireRegister.FireBull;
        data_tower();
        x_tower =7;
        y_tower =10;



    }
    public void tower_action() {
        TowerXY2();
        UpdateTower();

    }
    public void UpdateTower(){
        center_render_tower();
        RenderMethod.transorm_img(this.x_tower_rend,this.y_tower_rend,this.width_tower_zoom,this.height_tower_zoom,this.rotation_tower,this.tower_img,const_x_tower,const_y_tower);
    }
}

