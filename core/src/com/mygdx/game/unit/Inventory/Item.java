package com.mygdx.game.unit.Inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.moduleUnit.Gun;

import java.util.ArrayList;

public class Item implements Cloneable{
    public String ID;
    public static ArrayList<Object[]>IDListItem = new ArrayList<>();
    public Sprite image;
    public TypeItem typeItem;
    public ArrayList<TegItem>teg;
    public Gun gun;
    public float Reload;
    public Item(Gun gun,ArrayList<TegItem>teg){
        this.teg = teg;
        this.gun = gun;
        this.typeItem = TypeItem.Gun;
    }
    public Item(Gun gun,String ID,ArrayList<TegItem>teg,Sprite image){
        this.teg = teg;
        this.ID = ID;
        this.gun = gun;
        this.image = image;
        this.typeItem = TypeItem.Gun;
        IDListItem.add(new Object[]{this,ID});
    }
    public void Use(Unit unit){
        switch (typeItem){
            case Gun:
                //unit.GunUse.Reload = unit.reload;
                //unit.reload = this.Reload;
                //unit.GunUse = this;
                gun.GunLoad(unit);
                break;
            case Medic:
                break;
        }
    }
    public void UseNull(Unit unit){
        switch (typeItem){
            case Gun:
                gun.GunLoad(unit);
                break;
            case Medic:
                break;
        }
    }
    public Item clone(){
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }

}
