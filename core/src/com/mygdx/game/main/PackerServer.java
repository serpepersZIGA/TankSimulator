package com.mygdx.game.main;

import com.mygdx.game.bull.BullPacket;
import com.mygdx.game.build.BuildPacket;
import com.mygdx.game.object_map.PacketMapObject;
import com.mygdx.game.unit.DebrisPacket;
import com.mygdx.game.unit.TransportPacket;

import java.util.ArrayList;

public class PackerServer {
    public ArrayList<TransportPacket>player;
    public ArrayList<BullPacket>bull;
    public ArrayList<BuildPacket>building;
    public ArrayList<DebrisPacket>debris;
    public ArrayList<PacketMapObject>mapObject = new ArrayList<>();
    public float TotalLight;
}
