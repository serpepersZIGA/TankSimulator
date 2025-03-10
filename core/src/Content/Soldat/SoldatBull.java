package Content.Soldat;
import com.mygdx.game.main.Main;
import com.mygdx.game.soldat.Soldat;
import com.mygdx.game.unit.Unit;

import java.util.ArrayList;

public class SoldatBull extends Soldat {
    public SoldatBull(float x, float y, ArrayList<Unit> List){
        this.name = "bull";
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.allyList = List;
        this.speed_rotation = 5;
        this.width = 22;
        this.height = 22;
        this.soldat_image = Main.ContentImage.soldat_1;
        this.rotation = 0;
        this.size = 10;
        this.time_max = 10;
        this.team = 2;
        this.damage = 18;
        this.penetration = 2;
        data();

    }
    public void all_action(int i) {
        super.all_action(i);
        super.move_soldat_ii_bull(i);
        super.collision_soldat(Main.SoldatList,i);
        super.collision_build();
        super.collision_transport(Main.UnitList);
        super.hustle(Main.UnitList);
        super.clear(Main.SoldatList,i);
    }
    public void all_action_client(int i){
        super.all_action(i);
    }
}
