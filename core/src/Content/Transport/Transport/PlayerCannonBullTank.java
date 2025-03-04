package Content.Transport.Transport;

import Content.Transport.Tower.TowerFlamePlayer;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import java.util.ArrayList;

import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class PlayerCannonBullTank extends Unit {
    public PlayerCannonBullTank(float x, float y, ArrayList<Unit> tr, boolean host, byte team){
        this.type_unit = UnitType.PlayerT1;
        this.x = x;this.y = y;
        this.host = host;
        this.speed_inert = 0;this.speed = 0;
        this.max_speed = 4;this.min_speed = -4;
        this.max_hp = 1200;
        this.damage = 1000;
        this.armor = 50;
        this.allyList = tr;
        this.penetration = 25;
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
        this.corpus_width = 50;
        this.corpus_height = 129;
        this.width_tower = 35;
        this.height_tower = 55;
        this.x_tower = 15;
        this.y_tower = 20;
        this.t = 0;
        this.sound_fire = Main.ContentSound.cannon;
        this.speed_tower = 1;this.speed_rotation = 1;
        fire = FireRegister.FireBull;
        functional.Add(RegisterFunctionalComponent.MotorControl);
        functional.Add(RegisterFunctionalComponent.TowerXY);
        functional.Add(RegisterFunctionalComponent.TowerIteration);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        data();
        this.difference = 18;
        const_tower_x = (int)(width_tower/2);
        const_tower_y = 21;
        this.tower_x_const = (int) (corpus_width/2)-(width_tower/2);
        this.tower_y_const = (int) (corpus_height/2)-(height_tower/2)+7;
        this.tower_obj.add(new TowerFlamePlayer(18,55,52,-12,2,2,12,5,2,this.id_unit,this.height,
                this.team,Main.ContentImage.tower_player_auxiliary_1,this.allyList,Main.ContentSound.flame_attack));

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
