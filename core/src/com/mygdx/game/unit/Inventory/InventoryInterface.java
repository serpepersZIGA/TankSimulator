package com.mygdx.game.unit.Inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Event.EventGame;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.unit.Unit;

import static com.mygdx.game.main.Main.*;
import static com.mygdx.game.method.Keyboard.MouseX;
import static com.mygdx.game.method.Keyboard.MouseY;

public class InventoryInterface {
    public static boolean InventoryConf = false;
    public static boolean InventoryConfMoving = false;
    public int XCol,YCol,XColUs,YColUs;
    public int XInterface,YInterface,XSlots,YSlots,WidthWindow,HeightWindow,x,y;
    public Sprite frame = ContentImage.frameInventory;
    public Sprite frameInventory = ContentImage.InventoryBackground;
    public Slot[][]SlotInventory;
    public InventoryInterface(Inventory inventory,int x,int y,int width,int height){
        XInterface = inventory.InventorySlots.length;
        YInterface = inventory.InventorySlots[0].length;
        SlotInventory = new Slot[XInterface][YInterface];
        this.x = x;this.y = y;
        WidthWindow = width;
        HeightWindow = height;
        System.out.println(XInterface);
        XSlots = width/XInterface;
        YSlots = height/YInterface;
        if(XSlots>YSlots){XSlots=YSlots;}
        else if(XSlots<YSlots){YSlots = XSlots;}
        SlotGeneration(inventory);
    }
    public InventoryInterface(){
        SlotInventory = new Slot[0][0];
        this.x = 200;this.y = 1000;
        WidthWindow = 600;
        HeightWindow = 350;
    }
    public void FrameImageSet(Sprite frame,Sprite frameInventory){
        this.frame = frame;
        this.frameInventory = frameInventory;

    }
    public void SlotGeneration(Inventory inventory){
        for(int ix = 0;ix<inventory.InventorySlots.length;ix++){
            for(int iy = 0;iy<inventory.InventorySlots[ix].length;iy++){
                SlotInventory[ix][iy] = new Slot(XSlots*ix,YSlots*iy,XSlots,YSlots);
                Slot slot = SlotInventory[ix][iy];
                slot.item = inventory.InventorySlots[ix][iy];
            }
        }
    }
    public void InventoryIteration(){
        if(InventoryConf) {
            RenderMethod.transorm_img(this.x, this.y, this.WidthWindow, this.HeightWindow, this.frameInventory);
            for (Slot[] slotX : SlotInventory) {
                for (Slot slot : slotX) {
                    RenderMethod.transorm_img(slot.x+x, slot.y+y, slot.width, slot.height, this.frame);
                    if(slot.item != null) {
                        RenderMethod.transorm_img(slot.x + x, slot.y + y, slot.width, slot.height, slot.item.image);
                    }
                }
            }
            if(InventoryConfMoving){
                x = MouseX-XCol;
                y = MouseY-YCol;
            }
        }
    }
    public boolean CollisionMouseInvert(){
        XCol = MouseX-this.x;
        YCol = MouseY-this.y;
        return YCol < HeightWindow & YCol > 0 & XCol<WidthWindow &XCol> 0;
        //return false;
    }
    public void InventoryUs(Unit unit){
        for (Slot[] slots : SlotInventory) {
            for (Slot slot : slots) {
                XColUs = MouseX - (this.x + slot.x);
                YColUs = MouseY - (this.y + slot.y);
                if (YColUs < slot.height & YColUs > 0 & XColUs < slot.width & XColUs > 0) {
                    if (slot.item != null) {
                        slot.item.Use(unit);
                    }
                    return;
                }
                //return false;
            }
        }
    }
    public void InventoryUsClient(Unit unit){
        for (Slot[] slots : SlotInventory) {
            for (Slot slot : slots) {
                XColUs = MouseX - (this.x + slot.x);
                YColUs = MouseY - (this.y + slot.y);
                if (YColUs < slot.height & YColUs > 0 & XColUs < slot.width & XColUs > 0) {
                    if (slot.item != null) {
                        slot.item.Use(unit);
                        for (int i = 0; i < UnitList.size(); i++) {
                            EventGame.EventGameClient(slot.item.ID, i);
                        }
                    }
                    return;
                }
                //return false;
            }
        }
    }
}
