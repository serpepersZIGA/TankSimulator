package com.mygdx.game.unit.Inventory;
import com.mygdx.game.unit.moduleUnit.GunRegister;

import java.util.ArrayList;

import static com.mygdx.game.main.Main.ContentImage;
import static com.mygdx.game.unit.Inventory.TegItem.*;

public class ItemRegister {
    public static Item AK74,VoidGun,flamethrower,MedicineT1;
    public static void Create(){
        flamethrower = new Item(GunRegister.flamethrower,"FlGun",new ArrayList<>(),ContentImage.FlameGun);
        flamethrower.teg.add(intimately);
        MedicineT1 = new Item(220,"Med1",new ArrayList<>(),ContentImage.Medicine);
        MedicineT1.teg.add(medicine);
        AK74 = new Item(GunRegister.AK74,"AK74",new ArrayList<>(),ContentImage.AK74);
        AK74.teg.add(afar);
        VoidGun = new Item(GunRegister.Void,new ArrayList<>());
    }
}
