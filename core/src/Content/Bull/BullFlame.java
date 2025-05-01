package Content.Bull;
import com.mygdx.game.FunctionalComponent.FunctionalBullet.FunctionalComponentBulletRegister;
import com.mygdx.game.FunctionalComponent.FunctionalList;
import com.mygdx.game.bull.Bullet;
import com.mygdx.game.bull.EffectBullet;
import com.mygdx.game.method.rand;

import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Controller.RegisterController;

import static Data.DataColor.*;
import static com.mygdx.game.main.Main.RegisterControl;
import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class BullFlame extends Bullet {
    public BullFlame(float x, float y, float rotation, float damage, float t_damage, float penetration, byte type_team, byte height){
        super(x,y,rotation,damage,t_damage,penetration,type_team,height);
        functionalList = new FunctionalList();
        functionalList.Add(FunctionalComponentBulletRegister.ColorFire);


    }
    public void all_action(){
        super.all_action();
    }

//    public void update(){
//        Main.Render.setColor(r,g,b,1);
//        center_render();
//        Main.Render.circle(this.x_rend,this.y_rend,this.size_render,this.size);
//    }
    public void update(){
        super.update();
    }
}
