package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PlayerCannonMortar;
import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Inventory.Inventory;
import com.mygdx.game.unit.Inventory.Item;

import static com.mygdx.game.main.Main.UnitList;
import static com.mygdx.game.unit.TransportRegister.PlayerCannonFlameA1;
import static com.mygdx.game.unit.TransportRegister.PlayerCannonMortarA1;

public class SpawnPlayerCannonMortar extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        //Main.UnitList.add(PlayerCannonFlameA1.UnitAdd(200,200,host, (byte) 1,Main.RegisterControl.controllerPlayer));
        PlayerCannonMortarA1.UnitAdd(200,200,host, (byte) 1,Main.RegisterControl.controllerPlayer,new Inventory(new Item[3][4]));
        //UnitList.get(UnitList.size()-1).control = Main.RegisterControl.controllerPlayer;
        UnitList.get(UnitList.size()-1).PlayerConf = true;
    }

}
