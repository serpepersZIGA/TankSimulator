package com.mygdx.game.unit.SpawnPlayer;

import Content.Transport.Transport.PlayerCannonBullTank;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.PlayerList;

public class SpawnPlayerCannonBull extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        Main.PlayerList.add(new PlayerCannonBullTank(200,200,Main.PlayerList,host,(byte)1));
        PlayerList.get(PlayerList.size()-1).control = Main.RegisterControl.controllerPlayer;
    }
}
