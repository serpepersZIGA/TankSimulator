package Content.UnitPack.Transport.Transport;

import Content.UnitPack.Transport.Tower.TowerFlamePlayer;
import com.mygdx.game.method.RenderMethod;

import java.util.ArrayList;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class PlayerCannonAcid extends Unit {
    public PlayerCannonAcid(float x, float y, boolean host, byte team){
        tower_obj = new ArrayList<>();
        this.type_unit = UnitType.PlayerAcidT1;
        this.x = x;this.y = y;
        this.host = host;
        this.SpeedInert = 0;this.speed = 0;
        this.max_speed = 7;this.min_speed = -7;
        this.max_hp = 1200;
        this.damage = 12;
        this.armor = 50;
        this.penetration = 20;
        this.acceleration = 0.2f;
        this.rotation_tower = 0;
        this.rotation_corpus = 0;
        this.team = team;
        this.tower_x = 0;
        this.tower_y = 0;
        this.reload_max = 2;
        this.height = 1;
        this.tower_img = Main.ContentImage.tower_player;
        this.corpus_img = Main.ContentImage.corpus_player;
        this.corpus_width = 50;
        this.corpus_height = 129;
        this.width_tower = 35;
        this.height_tower = 55;
        this.t = 0;
        this.x_tower = 15;
        this.y_tower = 20;
        this.speed_tower = 1;this.speed_rotation = 3;
        this.sound_fire = Main.ContentSound.acid_attack;
        data();
        functional.Add(Main.RegisterFunctionalComponent.MotorControl);
        functional.Add(Main.RegisterFunctionalComponent.TowerXY);
        functional.Add(RegisterFunctionalComponent.TowerIteration);
        functional.Add(Main.RegisterFunctionalComponent.BuildCollision);
        functional.Add(RegisterFunctionalComponent.FireControl);
        fire = FireRegister.FireAcid;
        this.tower_obj.add(new TowerFlamePlayer(18,55,52,-12,2,2,12,5,2,this.id_unit,this.height,
                this.team,Main.ContentImage.tower_player_auxiliary_1,Main.ContentSound.flame_attack));
        this.tower_obj.add(new TowerFlamePlayer(18,55,52,-12,2,2,12,5,2,this.id_unit,this.height,
                this.team,Main.ContentImage.tower_player_auxiliary_1,Main.ContentSound.flame_attack));
        this.difference = 18;
        const_tower_x = 34;
        const_tower_y = 17;
        this.tower_x_const = (int) (corpus_width/2)-(width_tower/2)-6;
        this.tower_y_const = (int) (corpus_height/2)-(height_tower/2);
        center_render();
    }
    public void all_action() {
        super.all_action();
    }
    @Override
    public void all_action_client() {
        super.all_action_client();
    }
    @Override
    public void all_action_client_1() {
        super.all_action_client_1();

    }
    public void all_action_client_2() {
        super.all_action_client_2();
    }
    public void update(){
        indicator_reload();
        indicator_hp_2();
    }
    public void UpdateUnit(){
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
        for(Unit Tower : tower_obj){Tower.UpdateTower();}
        RenderMethod.transorm_img(this.x_tower_rend,this.y_tower_rend,this.width_tower_zoom,this.height_tower_zoom,this.rotation_tower,this.tower_img,const_x_tower,const_y_tower);
    }
}