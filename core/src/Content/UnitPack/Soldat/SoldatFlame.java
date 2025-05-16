package Content.UnitPack.Soldat;

import com.mygdx.game.main.Main;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.method.rand;
import com.mygdx.game.unit.ClassUnit;
import com.mygdx.game.unit.CollisionUnit.TypeCollision;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;
import com.mygdx.game.unit.Fire.FireRegister;

import static com.mygdx.game.main.Main.ContentSound;
import static com.mygdx.game.main.Main.EventData;
import static com.mygdx.game.main.Main.RegisterControl;
import static com.mygdx.game.main.Main.RegisterFunctionalComponent;


public class SoldatFlame extends Unit {
    public SoldatFlame(float x, float y,boolean host, byte team){
        classUnit = ClassUnit.Soldat;
        this.type_unit = UnitType.SoldatFlame;
        collision = TypeCollision.circle;
        this.x = x;this.y = y;
        this.SpeedInert = 0;
        this.max_speed = 4;this.min_speed = -4;
        this.max_hp = 150;
        this.damage = 10;
        this.host = host;
        this.armor = 5;
        this.penetration = 2;
        this.acceleration = 0.2f;
        this.behavior = (byte) (2+ rand.rand(1));
        this.reload_max = 2;
        this.height = 1;
        this.corpus_img = Main.ContentImage.soldat_1;
        this.t = 0;
        this.t_damage = 1;
        this.x_tower = 15;
        this.y_tower = 20;
        this.distance_target = 150;
        this.distance_target_2 = 30;
        this.team = team;
        EventClear = EventData.eventDeadSoldat;
        this.corpus_width = 20;
        this.corpus_height = 20;
        this.speed_rotation = 3;
        this.sound_fire = ContentSound.flame_attack;
        fire = FireRegister.FireFlame;
        control = RegisterControl.controllerSoldatBot;
        functional.Add(RegisterFunctionalComponent.SoldatCorrect);
        functional.Add(RegisterFunctionalComponent.SoldatControl);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        functional.Add(RegisterFunctionalComponent.FireControl);
        dataSoldat();
        center_render();
    }
    public void all_action() {
        super.all_action();
        control.ControllerIteration(this);
        functional.FunctionalIterationAnHost(this);
        EventClear.EventIteration(this);
    }
    @Override
    public void all_action_client() {
        super.all_action_client();
        control.ControllerIterationClientAnHost(this);
        functional.FunctionalIterationClientAnHost(this);
        EventClear.EventIteration(this);
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
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus+180,this.corpus_img,const_x_corpus,const_y_corpus);
    }
}
