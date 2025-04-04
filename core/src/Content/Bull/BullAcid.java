package Content.Bull;

import com.mygdx.game.bull.Bullet;
import com.mygdx.game.method.rand;
import com.mygdx.game.main.Main;

import static Data.DataColor.*;

public class BullAcid extends Bullet {
    public BullAcid(float x, float y, float rotation, float damage, float penetration, byte type_team, byte height){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.damage = damage;
        this.penetration = penetration;
        this.type_team = type_team;
        this.height = height;
        r = AcidR;g =  AcidG; b = AcidB;
        this.size = 8+rand.rand(8);
        this.size_render = (int)(size*Main.Zoom);
        this.speed = -5;
        this.time = 65+rand.rand(15);
        type = 4;
        speed_save();

    }
    public void all_action(int i){
        super.bull_move_xy();
        super.corpus_bull();
        super.BullBuildAcid();
        super.bull_clear_time_acid();
        this.update();
        super.clear();
    }

    public void update(){
        center_render();
        Main.Render.setColor(r,g,b,1);
        Main.Render.circle(this.x_rend,this.y_rend,this.size_render,this.size);
    }
}
