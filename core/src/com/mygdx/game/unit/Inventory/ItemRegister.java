package com.mygdx.game.unit.Inventory;

import com.mygdx.game.unit.moduleUnit.Gun;
import com.mygdx.game.unit.moduleUnit.GunRegister;

import java.util.ArrayList;

import static com.mygdx.game.unit.Inventory.TegItem.afar;
import static com.mygdx.game.unit.Inventory.TegItem.intimately;

public class ItemRegister {
    public static Item AK74,VoidGun,flamethrower;
    public static void Create(){
        flamethrower = new Item(GunRegister.flamethrower,new ArrayList<>());
        flamethrower.teg.add(intimately);
        AK74 = new Item(GunRegister.AK74,new ArrayList<>());
        AK74.teg.add(afar);
        VoidGun = new Item(GunRegister.Void,new ArrayList<>());
    }
}
