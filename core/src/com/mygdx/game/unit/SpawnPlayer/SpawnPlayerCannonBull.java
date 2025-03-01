package com.mygdx.game.unit.SpawnPlayer;

import Content.Transport.Transport.PlayerCannonBullTank;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.UnitList;

public class SpawnPlayerCannonBull extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        Main.UnitList.add(new PlayerCannonBullTank(200,200,Main.UnitList,host,(byte)1));
        UnitList.get(UnitList.size()-1).control = Main.RegisterControl.controllerPlayer;
    }
}
