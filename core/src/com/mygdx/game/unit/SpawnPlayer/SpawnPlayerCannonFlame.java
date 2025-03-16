package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PlayerCannonFlame;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.UnitList;

public class SpawnPlayerCannonFlame extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        Main.UnitList.add(new PlayerCannonFlame(200,200,Main.UnitList,host,(byte)1));
        UnitList.get(UnitList.size()-1).control = Main.RegisterControl.controllerPlayer;
        UnitList.get(UnitList.size()-1).PlayerConf = true;
    }
}
