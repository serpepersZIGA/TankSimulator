package com.mygdx.game.unit.Fire;

import Content.Bull.BullPacket;
import Content.Bull.BullTank;
import com.mygdx.game.bull.BulletRegister;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.SoundPlay;
import com.mygdx.game.method.rand;
import com.mygdx.game.unit.Unit;

import static Content.Bull.BullRegister.PacketBull;
import static com.mygdx.game.main.Main.BulletList;
import static com.mygdx.game.method.Option.SoundConst;
import static com.mygdx.game.method.pow2.pow2;
import static java.lang.StrictMath.*;

public class FireBull extends Fire {
    public void FireIteration(Unit unit){
        rotationTower = -unit.rotation_tower-90;
        SoundPlay.sound( unit.sound_fire,1-((float) sqrt(pow2(unit.x_rend) + pow2(unit.y_rend))/SoundConst));
        unit.fire_x = (float) (unit.tower_x+unit.tower_width_2+((unit.tower_height_2+unit.y_tower) *sin(rotationTower*3.1415926535/180)));
        unit.fire_y = (float) (unit.tower_y+unit.tower_height_2+((unit.tower_height_2+unit.y_tower) *cos(rotationTower*3.1415926535/180)));
        BulletRegister.BulletTank.BulletAdd(unit.fire_x, unit.fire_y,rotationTower+ -2+ rand.rand(4),unit.damage,unit.penetration,
                unit.damage_fragment,unit.penetration_fragment,unit.team,unit.height,unit.t_damage,unit.SpeedBullet
                ,unit.AmountFragment,unit.TimeBullet+rand.rand(unit.TimeBulletRand));
        PacketBull.add(new BullPacket());
        int i1 = PacketBull.size()-1;
        int i2 = BulletList.size()-1;
        unit.bull_packets(i1,i2);


    }
}
