package com.mygdx.game.unit.Inventory;

import Data.DataImage;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.mygdx.game.main.Main.ContentImage;

public class Slot {
    public int x,y,width,height;
    public Item item;

    public Slot(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void SlotUpdate(){

    }
}
