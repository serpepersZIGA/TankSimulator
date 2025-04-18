package Content.Bull;

import com.mygdx.game.bull.Bullet;
import com.mygdx.game.method.rand;

import com.mygdx.game.main.Main;

import static Data.DataColor.*;

public class BullFragment extends Bullet {
    public int x2,y2;

    public BullFragment(float x, float y, float damage, float penetration, byte type_team){
        this.x = x;
        this.y = y;
        this.type_team = type_team;
        this.damage = damage;
        this.penetration = penetration;
        this.speed_x = (float) (-8+rand.rand(16.0));
        this.speed_y = (float) (-8+rand.rand(16.0));
        this.x2 = 3+rand.rand(5);
        this.y2 = 3+rand.rand(5);
        this.size = this.x2;
        this.height = 1;
        this.size_render = (int)(size*Main.Zoom);
        this.r = MortarR;
        this.g = MortarG;
        this.b = MortarB;
        this.time = 50+rand.rand(80);
        type = 2;
    }
    public void all_action(int i){
        super.bull_move_xy();
        super.corpus_bull();
        super.bull_clear_time();
        super.BullBuild();
        this.update();
        super.clear();
    }
    public void update(){
        center_render();
        Main.Render.setColor(r,g,b,1);
        Main.Render.rect(this.x_rend,this.y_rend,(int)(this.x2* Main.Zoom),(int)(this.y2* Main.Zoom));
    }
}
