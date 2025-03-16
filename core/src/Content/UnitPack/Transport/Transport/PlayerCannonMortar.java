package Content.UnitPack.Transport.Transport;

import Content.UnitPack.Transport.Tower.TowerBullTankPlayer;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import java.util.ArrayList;

import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class PlayerCannonMortar extends Unit {
    public PlayerCannonMortar(float x, float y, ArrayList<Unit> tr, boolean host, byte team){
        this.type_unit = UnitType.PlayerMortarT1;
        this.x = x;this.y = y;
        this.host = host;
        this.speed_inert = 0;this.speed = 0;
        this.max_speed = 4;this.min_speed = -2;
        this.max_hp = 1200;
        this.allyList = tr;
        this.damage = 250;
        this.armor = 50;
        this.penetration = 7;
        this.acceleration = 0.2f;
        this.rotation_tower = 0;
        this.rotation_corpus = 0;
        this.team = team;
        this.tower_x = 0;
        this.tower_y = 0;
        this.reload_max = 120;
        this.height = 1;
        this.tower_img = Main.ContentImage.tower_player;
        this.corpus_img = Main.ContentImage.corpus_player;
        this.t = 0;
        this.damage_fragment = 12;
        this.penetration_fragment = 4;
        this.x_tower = 15;
        this.y_tower = 20;
        this.sound_fire = Main.ContentSound.cannon;

        this.corpus_width = 50;
        this.corpus_height = 129;
        this.width_tower = 35;
        this.height_tower = 55;
        functional.Add(RegisterFunctionalComponent.MotorControl);
        functional.Add(RegisterFunctionalComponent.TowerIteration);
        functional.Add(Main.RegisterFunctionalComponent.TowerXY);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        functional.Add(RegisterFunctionalComponent.FireControl);


        this.speed_tower = 1;this.speed_rotation = 0.5f;
        this.tower_obj.add(new TowerBullTankPlayer(18,55,52,-12,5,2,20,12, this.id_unit,
                (byte)1,this.team,Main.ContentImage.tower_player_auxiliary_1,this.allyList,Main.ContentSound.machinegun));
        this.tower_obj.add(new TowerBullTankPlayer(18,55,52,12,5,2,20,12, this.id_unit,
                (byte)1,this.team,Main.ContentImage.tower_player_auxiliary_1,this.allyList,Main.ContentSound.machinegun));
        this.difference = 18;
        const_tower_x = (int)(width_tower/2);
        const_tower_y = 21;
        this.tower_x_const = (int) (corpus_width/2)-(width_tower/2);
        this.tower_y_const = (int) (corpus_height/2)-(height_tower/2)+7;
        this.fire = FireRegister.FireMortar;
        data();
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
        functional.FunctionalIterationClientAnHost(this);
        control.ControllerIterationClientAnHost(this);
        super.transportDelete();
    }
    @Override
    public void all_action_client_1() {
        super.all_action_client_1();
        functional.FunctionalIterationAnClient(this);
        control.ControllerIterationClientAnClient(this);

    }
    public void all_action_client_2() {
        super.all_action_client_2();
        functional.FunctionalIterationOtherAnClient(this);

    }
    public void update(){
        indicator_reload();
        indicator_hp_2();
    }
    public void UpdateUnit(){
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
        RenderMethod.transorm_img(this.x_tower_rend,this.y_tower_rend,this.width_tower_zoom,this.height_tower_zoom,this.rotation_tower,this.tower_img,const_x_tower,const_y_tower);
    }

}
