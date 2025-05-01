package Content.Bull;

import com.mygdx.game.bull.Bullet;
import com.mygdx.game.method.rand;

import com.mygdx.game.main.Main;

import static Data.DataColor.*;

public class BullFragment extends Bullet {

    public BullFragment(float x, float y, float damage, float penetration, byte type_team){
        super(x,y,damage,penetration,type_team);
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
    }
    public void all_action(){
        super.bull_move_xy();
        super.CorpusBullet();
        super.bullClearTime();
        super.BuildBulletCollision();
        this.update();
        super.clear();
    }
    public void update(){
        center_render();
        Main.Render.setColor(r,g,b,1);
        Main.Render.rect(this.x_rend,this.y_rend,(int)(this.x2* Main.Zoom),(int)(this.y2* Main.Zoom));
    }
}
