package com.mygdx.game.unit.Inventory;

import com.mygdx.game.main.Main;
import com.mygdx.game.method.RenderMethod;

import static com.mygdx.game.main.Main.inventoryMain;
import static com.mygdx.game.method.Keyboard.*;

public class SlotBuffer {
    public Slot slot;
    public Item item;
    public int x, y, width, height,ix,iy,XCol,YCol;

    public SlotBuffer(Slot slot, int x, int y, int width, int height,int ix,int iy) {
        this.item = slot.item;
        this.slot = slot;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ix = ix;this.iy = iy;
    }

    public void SlotXY() {
        this.x = MouseX;
        this.y = MouseY;
    }

    public void SlotPaste() {
        if(!MiddleMouse){
//            XCol = MouseX-(this.x+inventoryMain.x);
//            YCol = MouseY-(this.y+inventoryMain.y);
//            if(!(YCol < height & YCol > 0 & XCol<width &XCol> 0)){
//                slot.item = null;
//                inventoryMain.inventory.InventorySlots[ix][iy] = null;
//                inventoryMain.SlotBuffer = null;
//                return;
//            }
            int ix2 = 0;
            int iy2 = 0;
            for(Slot[] SlotLine : inventoryMain.SlotInventory){
                for(Slot Slot : SlotLine) {
                    XCol = MouseX-(Slot.x+inventoryMain.x);
                    YCol = MouseY-(Slot.y+inventoryMain.y);
                    if(YCol < Slot.height & YCol > 0 & XCol<Slot.width &XCol> 0){
                        Item item1 = item;
                        Item item2 = Slot.item;
//                        if(item2!= null){
//                            slot.item = item2.clone();
//                            inventoryMain.inventory.InventorySlots[ix][iy] = item2.clone();
//                        }
                        if(item1!= null){
                            Slot.item = item1.clone();
                            inventoryMain.inventory.InventorySlots[ix2][iy2] = item1.clone();
                            if(item2!= null){
                                slot.item = item2.clone();
                                inventoryMain.inventory.InventorySlots[ix][iy] = item2.clone();
                            }
                            else{
                                slot.item = null;
                                inventoryMain.inventory.InventorySlots[ix][iy] = null;
                            }
                        }
                        inventoryMain.SlotBuffer = null;
                        return;
                    }
                    iy2++;
                }
                ix2++;
                iy2 = 0;

            }

        }
    }

    public void SlotRender() {
        RenderMethod.transorm_img(this.x, this.y, this.width, this.height,item.image);
    }
}
