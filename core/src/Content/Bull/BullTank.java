package Content.Bull;

import com.mygdx.game.bull.Bull;
import com.mygdx.game.main.Main;

public class BullTank extends Bull {
    public BullTank(double x, double y, double rotation, double damage, double penetration, byte type_time, byte height){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.type_team = type_time;
        this.height = height;

        this.size = 10;
        this.size_render = (int)(size*Main.Zoom);
        this.damage = damage;
        this.penetration = penetration;
        this.speed = -12;
        this.r = (float)1/256*165;
        this.g = (float)1/256*165;
        this.b = (float)1/256*10;
        this.time = 220;
        type = 5;
        speed_save();
    }
    public void all_action(int i){
        super.bull_move_xy();
        //super.bull_clear_display();
        super.bull_clear_time();
        super.corpus_bull(Main.EnemyList);
        super.corpus_bull(Main.PlayerList);
        super.corpus_bull(Main.HelicopterList);
        super.corpus_bull(Main.DebrisList);
        super.bull_build(Main.BuildingList);
        super.soldat_bull(Main.SoldatList);
        this.update();
        super.clear(i);
    }
    public void all_action_client(int i){
        //super.all_action_client(i);
        this.update();
        super.bull_move_xy();
        super.bull_clear_time();
        super.corpus_bull(Main.EnemyList);
        super.corpus_bull(Main.PlayerList);
        super.corpus_bull(Main.HelicopterList);
        super.corpus_bull(Main.DebrisList);
        super.bull_build(Main.BuildingList);
        super.soldat_bull(Main.SoldatList);
        super.clear(i);
    }
    public void update(){
        center_render();
        Main.Render.setColor(r,g,b,1);
        Main.Render.circle(this.x_rend,this.y_rend,this.size_render,this.size_render);
    }

}
