package com.mygdx.game.unit.SpawnPlayer;

import Content.Transport.Transport.PlayerCannonMortar;
import com.mygdx.game.main.Main;

public class SpawnPlayerCannonMortar extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        Main.PlayerList.add(new PlayerCannonMortar(200,200,Main.PlayerList,host,(byte)1));
    }

}
