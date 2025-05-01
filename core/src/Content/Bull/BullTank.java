package Content.Bull;

import com.mygdx.game.bull.Bullet;
import com.mygdx.game.main.Main;

public class BullTank extends Bullet {
    public BullTank(float x, float y, float rotation, float damage, float penetration, byte type_time, byte height){
        super(x,y,rotation,damage,penetration,type_time,height);
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.type_team = type_time;
        this.height = height;

        this.size = 8;
        this.size_render = (int)(size*Main.Zoom);
        this.damage = damage;
        this.penetration = penetration;
        this.speed = -12;
        this.r = (float)1/256*165;
        this.g = (float)1/256*165;
        this.b = (float)1/256*10;
        this.time = 220;
        speed_save();
    }
    public void all_action(){
        super.bull_move_xy();
        //super.bull_clear_display();
        super.bullClearTime();
        super.CorpusBullet();
        super.BuildBulletCollision();
        this.update();
        super.clear();
    }
    public void update(){
        center_render();
        Main.Render.setColor(r,g,b,1);
        Main.Render.circle(this.x_rend,this.y_rend,this.size_render,this.size);
    }

}
