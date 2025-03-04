package com.mygdx.game.unit;

import java.util.ArrayList;

public class TransportPacket {
    public float x,y,rotation_corpus,rotation_tower,reload;
    public UnitType name;
    public byte team;
    public int hp;
    public float speed;
    public boolean host,crite_life,PlayerConf;
    public int IDClient;
    public ArrayList<Float>rotation_tower_2 = new ArrayList<>();
}
