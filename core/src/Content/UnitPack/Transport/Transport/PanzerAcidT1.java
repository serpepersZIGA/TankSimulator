package Content.UnitPack.Transport.Transport;

import Content.UnitPack.Transport.Tower.TowerBullTankEnemy;
import Content.UnitPack.Transport.Tower.TowerFlameEnemy;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.method.rand;

import java.util.ArrayList;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import static com.mygdx.game.main.Main.RegisterFunctionalComponent;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PanzerAcidT1 extends Unit {
    public PanzerAcidT1(float x, float y, ArrayList<Unit> tr,boolean host, byte team){
        this.type_unit = UnitType.PanzerAcidT1;
        this.x = x;this.y = y;
        this.host = host;
        this.speed_inert = 0;this.speed = 0;
        this.max_speed = 4;this.min_speed = -4;
        this.damage = 10;
        this.max_hp = 1500;
        this.armor = 50;
        this.allyList = tr;
        this.penetration = 10;
        this.acceleration = 0.2f;
        this.rotation_tower = 0;
        this.rotation_corpus = 70;
        this.tower_x = 0;
        this.tower_y = 0;
        this.medic_help = 0;
        this.behavior = (byte) (1+rand.rand(2));
        this.reload_max = 2;
        this.team = team;
        this.t = 0;
        this.x_tower = 15;
        this.y_tower = 20;
        this.distance_target = 150;
        this.distance_target_2 = 30;

        this.tower_img = Main.ContentImage.tower_enemy;
        this.corpus_img = Main.ContentImage.corpus_enemy;

        this.corpus_width = 50;
        this.corpus_height = 129;
        this.width_tower = 55;
        this.height_tower = 35;
        this.speed_tower = 1;this.speed_rotation = 1;
        this.sound_fire = Main.ContentSound.acid_attack;
        fire = FireRegister.FireAcid;
        functional.Add(RegisterFunctionalComponent.MotorControl);
        functional.Add(Main.RegisterFunctionalComponent.TowerXY);
        functional.Add(RegisterFunctionalComponent.TowerIteration);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        functional.Add(RegisterFunctionalComponent.FireControl);
        data();
//        this.tower_obj.add(new tower_flame_enemy(18,55,52,-12,4,2,65,12,2, this.id_unit,
//                (byte)1,(byte)2,Main.content_base.tower_player_auxiliart_1,this.spisok, Main.sa.get(0).flame_attack));
        this.tower_obj.add(new TowerBullTankEnemy(18,55,52,-12,4,2,65,12, this.id_unit,
                (byte)1,this.team,Main.ContentImage.tower_enemy_auxiliary_1,this.allyList, Main.ContentSound.flame_attack));
        this.tower_obj.add(new TowerFlameEnemy(18,55,52,12,4,2,65,12,2, this.id_unit,
                (byte)1,this.team,Main.ContentImage.tower_enemy_auxiliary_1,this.allyList, Main.ContentSound.flame_attack));
        this.difference = 18;
        const_tower_x = 34;
        const_tower_y = 17;
        this.tower_x_const = (int) (corpus_width/2)-(width_tower/2)-6;
        this.tower_y_const = (int) (corpus_height/2)-(height_tower/2);
        center_render();


    }
    public void all_action() {
        super.all_action();
        control.ControllerIteration(this);
        functional.FunctionalIterationAnHost(this);
        super.transportDelete();
    }
    @Override
    public void all_action_client() {
        super.all_action_client();
        control.ControllerIterationClientAnHost(this);
        functional.FunctionalIterationClientAnHost(this);
        super.transportDelete();
    }
    @Override
    public void all_action_client_1() {
        super.all_action_client_1();
        control.ControllerIterationClientAnClient(this);
        functional.FunctionalIterationAnClient(this);
    }
    public void all_action_client_2() {
        super.all_action_client_2();
        functional.FunctionalIterationAnClient(this);
    }
    public void update(){
        indicator_reload();
        indicator_hp_2();
    }
    public void UpdateUnit(){
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
        for(Unit Tower : tower_obj){Tower.UpdateTower();}
        RenderMethod.transorm_img(this.x_tower_rend,this.y_tower_rend,this.width_tower_zoom,this.height_tower_zoom,this.rotation_tower,this.tower_img,const_x_tower,const_y_tower
        );
    }
}
