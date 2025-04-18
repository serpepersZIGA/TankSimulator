package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PanzerAcidT1;
import Content.UnitPack.Transport.Transport.PlayerCannonAcid;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.UnitList;

public class SpawnPlayerCannonAcid extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        UnitList.add(new PanzerAcidT1(200,200,host,(byte)1));
        UnitList.get(UnitList.size()-1).control = Main.RegisterControl.controllerPlayer;
        UnitList.get(UnitList.size()-1).PlayerConf = true;
    }
}
