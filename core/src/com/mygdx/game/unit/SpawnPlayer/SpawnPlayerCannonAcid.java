package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PanzerAcidT1;
import Content.UnitPack.Transport.Transport.PlayerCannonAcid;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Inventory.Inventory;
import com.mygdx.game.unit.Inventory.InventoryInterface;
import com.mygdx.game.unit.Inventory.Item;

import static com.mygdx.game.main.Main.UnitList;
import static com.mygdx.game.main.Main.inventoryMain;
import static com.mygdx.game.unit.TransportRegister.PlayerCannonAcidA1;
import static com.mygdx.game.unit.TransportRegister.PlayerCannonMachineGunA1;

public class SpawnPlayerCannonAcid extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        PlayerCannonAcidA1.UnitAdd(200,200,host, (byte) 1,Main.RegisterControl.controllerPlayer,new Inventory(new Item[3][4]));
        UnitList.get(UnitList.size()-1).PlayerConf = true;
        inventoryMain = new InventoryInterface(UnitList.get(UnitList.size()-1).inventory,200,200,600,350);

    }
}
