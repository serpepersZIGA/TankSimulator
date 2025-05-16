package com.mygdx.game.unit.Inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.method.RenderMethod;

import static com.mygdx.game.main.Main.ContentImage;
import static com.mygdx.game.main.Main.Render;
import static com.mygdx.game.method.Keyboard.MouseX;
import static com.mygdx.game.method.Keyboard.MouseY;

public class InventoryInterface {
    public static boolean InventoryConf = false;
    public static boolean InventoryConfMoving = false;
    public int XCol,YCol;
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
        this.x = 200;this.y = 200;
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
                System.out.println(MouseX);
            }
        }
    }
    public boolean CollisionMouseInvert(){
        XCol = MouseX-this.x;
        YCol = MouseY-this.y;
        return YCol < HeightWindow & YCol > 0 & XCol<WidthWindow &XCol> 0;
        //return false;
    }

}
