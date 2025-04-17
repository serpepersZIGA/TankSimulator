package Content.UnitPack.Transport.Transport;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.UnitType;

import java.util.ArrayList;

import static com.mygdx.game.main.Main.ContentImage;
import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class DebrisTransport extends Unit {

    public DebrisTransport(float x, float y, float rotation, float speed, float inert_rotation,
                           float inert_speed, Sprite corpus, float width, float height, UnitType type){

        this.type_unit = type;
        this.x = x;
        this.y = y;
        this.corpus_width = width;
        this.corpus_height = height;
        this.rotation_corpus = rotation;
        this.speed = speed;
        this.SpeedInert = inert_speed;
        this.RotationInert = inert_rotation;
        this.corpus_img = corpus;
        this.max_hp = 10000;
        this.armor = 5;
        this.tower_obj = new ArrayList<>();
        functional.Add(RegisterFunctionalComponent.MoveDebris);
        functional.Add(RegisterFunctionalComponent.BuildCollision);
        if(this.corpus_img == null){
            this.corpus_img = ContentImage.corpus_enemy;
            this.corpus_width = 50;
            this.corpus_height = 129;
        }
        data();
        this.height = 1;
        this.team = -1;
        this.acceleration = 0.1f;
    }
    public void all_action(){
        super.all_action();
        super.corpus_corpus(Main.UnitList);
        super.corpus_corpus(Main.DebrisList);
        functional.FunctionalIterationAnHost(this);
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
        debrisDelete();
    }
    public void all_action_client(){
        center_render();
        RenderMethod.transorm_img(this.x_rend,this.y_rend,this.corpus_width_zoom,this.corpus_height_zoom,this.rotation_corpus,this.corpus_img,const_x_corpus,const_y_corpus);
    }

}
