package com.mygdx.game.unit.Inventory;
import com.mygdx.game.unit.moduleUnit.GunRegister;

import java.util.ArrayList;

import static com.mygdx.game.main.Main.ContentImage;
import static com.mygdx.game.unit.Inventory.TegItem.afar;
import static com.mygdx.game.unit.Inventory.TegItem.intimately;

public class ItemRegister {
    public static Item AK74,VoidGun,flamethrower;
    public static void Create(){
        flamethrower = new Item(GunRegister.flamethrower,"flameGun",new ArrayList<>(),ContentImage.FlameGun);
        flamethrower.teg.add(intimately);
        AK74 = new Item(GunRegister.AK74,"AK74",new ArrayList<>(),ContentImage.AK74);
        AK74.teg.add(afar);
        VoidGun = new Item(GunRegister.Void,new ArrayList<>());
    }
}
