package Content.UnitPack.Transport.Transport;

import com.mygdx.game.method.RenderMethod;

import java.util.ArrayList;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.ClassUnit;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import static com.mygdx.game.main.Main.RegisterControl;
import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class TrackRemountT1 extends Unit {
    public TrackRemountT1(float x, float y, ArrayList<Unit> tr,boolean host,byte team){
        this.type_unit = UnitType.TrackRemountT1;
        this.x = x;this.y = y;
        this.speed_inert = 0;this.speed = 0;
        this.max_speed = 4;this.min_speed = -4;
        this.damage = 5;
        classUnit = ClassUnit.SupportTransport;
        this.penetration = 20;
        this.allyList = tr;
        this.max_hp = 1500;
        this.acceleration = 0.2f;
        this.rotation_tower = 0;
        this.rotation_corpus = 70;
        this.tower_x_const = -10;
        this.tower_y_const = 3;
        this.tower_x = 0;
        this.tower_y = 0;
        this.medic_help = 0;
        this.height = 1;
        this.behavior = 3;
        this.reload_max = 180;
        this.team = team;
        this.t = 0;
        this.hill = 2;
        this.corpus_img = Main.ContentImage.track_enemy_1lvl;
        this.corpus_width = 50;
        this.corpus_height = 129;
        this.host = host;

        this.x_tower = 15;
        this.y_tower = 20;
        this.difference = 18;
        this.distance_target = 200;
        this.distance_target_2 = 80;
        control = RegisterControl.controllerBotSupport;
        functional.Add(RegisterFunctionalComponent.TowerXY);
        functional.Add(RegisterFunctionalComponent.MotorControl);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        functional.Add(RegisterFunctionalComponent.Hill);

        this.speed_tower = 1;this.speed_rotation = 1;
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
        indicator_hp_2();
    }
    public void UpdateUnit(){
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
    }

}
