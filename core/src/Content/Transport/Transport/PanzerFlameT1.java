package Content.Transport.Transport;

import Content.Transport.Tower.TowerBullTankEnemy;
import Content.Transport.Tower.TowerFlameEnemy;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.method.rand;

import java.util.ArrayList;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class PanzerFlameT1 extends Unit {
    public PanzerFlameT1(float x, float y, ArrayList<Unit> tr, byte team){
        this.type_unit = UnitType.PanzerFlameT1;
        this.x = x;this.y = y;
        this.speed_inert = 0;this.speed = 0;
        this.max_speed = 4;this.min_speed = -4;
        this.max_hp = 1200;
        this.damage = 10;
        this.allyList = tr;
        this.armor = 50;
        this.penetration = 2;
        this.acceleration = 0.2f;
        this.behavior = (byte) (2+rand.rand(1));
        this.reload_max = 2;
        this.height = 1;
        this.tower_img = Main.ContentImage.tower_enemy;
        this.corpus_img = Main.ContentImage.corpus_enemy;
        this.t = 0;
        this.t_damage = 1;
        this.x_tower = 15;
        this.y_tower = 20;
        this.distance_target = 150;
        this.distance_target_2 = 30;
        this.team = team;


        this.corpus_width = 50;
        this.corpus_height = 129;
        this.width_tower = 35;
        this.height_tower = 55;
        this.speed_tower = 1;this.speed_rotation = 1;
        this.sound_fire = Main.ContentSound.flame_attack;
        fire = FireRegister.FireFlame;
        functional.Add(RegisterFunctionalComponent.MotorControl);
        functional.Add(Main.RegisterFunctionalComponent.TowerXY);
        functional.Add(RegisterFunctionalComponent.TowerIteration);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        data();
        this.tower_obj.add(new TowerBullTankEnemy(18,55,52,-12,4,2,65,12, this.id_unit,
                (byte)1,this.team,Main.ContentImage.tower_enemy_auxiliary_1,this.allyList, Main.ContentSound.flame_attack));
        this.tower_obj.add(new TowerFlameEnemy(18,55,52,12,4,2,65,12,2, this.id_unit,
                (byte)1,this.team,Main.ContentImage.tower_enemy_auxiliary_1,this.allyList, Main.ContentSound.flame_attack));
        this.difference = 18;
        const_tower_x = (int)(width_tower/2);
        const_tower_y = 21;
        this.tower_x_const = (int) (corpus_width/2)-(width_tower/2);
        this.tower_y_const = (int) (corpus_height/2)-(height_tower/2)+7;
        center_render();


    }
    public void all_action(int i) {
        super.all_action(i);
        control.ControllerIteration(this,i);
        functional.FunctionalIterationAnHost(this);
        super.transportDelete();
    }
    @Override
    public void all_action_client(int i) {
        super.all_action_client(i);
        control.ControllerIterationClientAnHost(this);
        functional.FunctionalIterationClientAnHost(this);
        super.transportDelete();
    }
    @Override
    public void all_action_client_1(int i) {
        super.all_action_client_1(i);
        control.ControllerIterationClientAnClient(this);
        functional.FunctionalIterationAnClient(this);
    }
    public void all_action_client_2(int i) {
        super.all_action_client_2(i);
        functional.FunctionalIterationAnClient(this);
    }
    public void update(){
        indicator_reload();
        indicator_hp_2();
    }
    public void UpdateUnit(){
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
        RenderMethod.transorm_img(this.x_tower_rend,this.y_tower_rend,this.width_tower_zoom,this.height_tower_zoom,this.rotation_tower,this.tower_img,const_x_tower,const_y_tower
        );
    }


}
