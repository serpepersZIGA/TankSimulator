package com.mygdx.game.main;

import java.util.ArrayList;

public class Packet_client {
    public boolean press_w,press_a,press_s,press_d;
    public boolean right_mouse,left_mouse;
    public int mouse_x,mouse_y,IDClient;
    public float TargetX,TargetY;

    public ArrayList<Float>rot_tower = new ArrayList<>();
}
