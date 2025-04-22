package com.mygdx.game.unit;

import Content.UnitPack.Transport.Transport.*;
import com.mygdx.game.main.Main;
import com.mygdx.game.main.PacketUnitUpdate;
import com.mygdx.game.unit.moduleUnit.Cannon;
import com.mygdx.game.unit.moduleUnit.RegisterModuleCannon;
import com.mygdx.game.unit.moduleUnit.RegisterModuleCorpus;
import com.mygdx.game.unit.moduleUnit.RegisterModuleEngine;

import java.util.ArrayList;

public class TransportRegister {
    public static ArrayList<TransportPacket> PacketUnit = new ArrayList<>();
    public static PacketUnitUpdate packetUnitUpdate = new PacketUnitUpdate();
    public static ArrayList<DebrisPacket> PacketDebris = new ArrayList<>();
    public static Unit PlayerCannonFlame, PlayerCannonMortar, PlayerCannonBullTank, PlayerCannonAcid,
            PanzerMortarT1, PanzerFlameT1, PanzerAcidT1, PanzerT1, TrackRemountT1, TrackSoldatT1, Helicopter_t1
            ,PlayerCannonFlameA1,PlayerCannonFlameA2;
    public TransportRegister() {
        PlayerCannonFlame = new PlayerCannonFlame(0,0,false,(byte)1);
        PlayerCannonMortar = new PlayerCannonMortar(0,0,false,(byte)1);
        PlayerCannonBullTank = new PlayerCannonBullTank(0,0,false,(byte)1);
        PlayerCannonAcid = new PlayerCannonAcid(0,0,false,(byte)1);

        PanzerMortarT1 = new PanzerMortarT1(0,0,true,(byte)2);
        PanzerFlameT1 = new PanzerFlameT1(0,0,true,(byte)2);
        PanzerAcidT1 = new PanzerAcidT1(0,0,true,(byte)2);
        PanzerT1 = new PanzerT1(0,0,true,(byte)2);

        TrackRemountT1 = new TrackRemountT1(0,0,true,(byte)2);
        TrackSoldatT1 = new TrackSoldatT1(0,0,true,(byte)2);
        Helicopter_t1 = new HelicopterT1(0,0,true,(byte)2);
        ArrayList<Cannon>list = new ArrayList<>();
        list.add(RegisterModuleCannon.CannonFlameAuxiliary);
        list.add(RegisterModuleCannon.CannonFlameAuxiliary);
        list.add(RegisterModuleCannon.CannonFlame);
        PlayerCannonFlameA1 = new UnitPattern("PanzFlA1",
                RegisterModuleCorpus.CorpusT1, RegisterModuleEngine.Engine1E,list,new int[][]{{-12,52},{12,52},{1,18}});
        PlayerCannonFlameA2 = new UnitPattern("PanzFlA2",
                RegisterModuleCorpus.CorpusT1, RegisterModuleEngine.Engine2E,list,new int[][]{{-12,52},{12,52},{1,18}});

    }
}
