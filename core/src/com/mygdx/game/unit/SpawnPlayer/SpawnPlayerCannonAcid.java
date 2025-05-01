package com.mygdx.game.unit.SpawnPlayer;

import Content.UnitPack.Transport.Transport.PanzerAcidT1;
import Content.UnitPack.Transport.Transport.PlayerCannonAcid;
import com.mygdx.game.main.Main;

import static com.mygdx.game.main.Main.UnitList;
import static com.mygdx.game.unit.TransportRegister.PlayerCannonAcidA1;
import static com.mygdx.game.unit.TransportRegister.PlayerCannonMachineGunA1;

public class SpawnPlayerCannonAcid extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        PlayerCannonAcidA1.UnitAdd(200,200,host, (byte) 1,Main.RegisterControl.controllerPlayer);
        UnitList.get(UnitList.size()-1).PlayerConf = true;
    }
}
