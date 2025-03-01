package com.mygdx.game.unit;

import Content.Transport.Transport.*;
import com.mygdx.game.main.Main;

import java.util.ArrayList;

public class TransportRegister {
    public static ArrayList<TransportPacket> PacketUnit = new ArrayList<>();
    public static ArrayList<DebrisPacket> PacketDebris = new ArrayList<>();
    public static Unit PlayerCannonFlame, PlayerCannonMortar, PlayerCannonBullTank, PlayerCannonAcid,
            PanzerMortarT1, PanzerFlameT1, PanzerAcidT1, PanzerT1, TrackRemountT1, TrackSoldatT1, Helicopter_t1;
    public TransportRegister() {
        PlayerCannonFlame = new PlayerCannonFlame(0,0, Main.UnitList,false,(byte)1);
        PlayerCannonMortar = new PlayerCannonMortar(0,0, Main.UnitList,false,(byte)1);
        PlayerCannonBullTank = new PlayerCannonBullTank(0,0, Main.UnitList,false,(byte)1);
        PlayerCannonAcid = new PlayerCannonAcid(0,0, Main.UnitList,false,(byte)1);

        PanzerMortarT1 = new PanzerMortarT1(0,0, Main.UnitList,(byte)2);
        PanzerFlameT1 = new PanzerFlameT1(0,0, Main.UnitList,(byte)2);
        PanzerAcidT1 = new PanzerAcidT1(0,0, Main.UnitList,(byte)2);
        PanzerT1 = new PanzerT1(0,0, Main.UnitList,(byte)2);

        TrackRemountT1 = new TrackRemountT1(0,0, Main.UnitList,(byte)2);
        TrackSoldatT1 = new TrackSoldatT1(0,0, Main.UnitList,(byte)2);
        Helicopter_t1 = new HelicopterT1(0,0, Main.UnitList);


    }
}
