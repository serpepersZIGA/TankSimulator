package com.mygdx.game.unit.SpawnPlayer;

import Content.Transport.Transport.PlayerCannonAcid;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.PlayerList;

public class SpawnPlayerCannonAcid extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        PlayerList.add(new PlayerCannonAcid(200,200, PlayerList,host,(byte)1));
        PlayerList.get(PlayerList.size()-1).control = Main.RegisterControl.controllerPlayer;
    }
}
