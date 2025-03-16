package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PlayerCannonAcid;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.UnitList;

public class SpawnPlayerCannonAcid extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        UnitList.add(new PlayerCannonAcid(200,200, UnitList,host,(byte)1));
        UnitList.get(UnitList.size()-1).control = Main.RegisterControl.controllerPlayer;
        UnitList.get(UnitList.size()-1).PlayerConf = true;
    }
}
