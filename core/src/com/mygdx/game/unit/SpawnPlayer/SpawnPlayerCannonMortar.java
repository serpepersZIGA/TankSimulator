package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PlayerCannonMortar;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.UnitList;

public class SpawnPlayerCannonMortar extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        Main.UnitList.add(new PlayerCannonMortar(200,200,Main.UnitList,host,(byte)1));
        UnitList.get(UnitList.size()-1).control = Main.RegisterControl.controllerPlayer;
        UnitList.get(UnitList.size()-1).PlayerConf = true;
    }

}
