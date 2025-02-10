package com.mygdx.game.object_map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.block.Block;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.RenderMethod;
import com.mygdx.game.object_map.component_collision_system.CollisionBreak;
import com.mygdx.game.object_map.component_collision_system.CollisionVoid;
import com.mygdx.game.object_map.component_collision_system.ComponentCollisionSystem;
import com.mygdx.game.transport.DebrisPacket;
import com.mygdx.game.transport.Transport;

import java.util.ArrayList;

import static Data.DataColor.RGBFlame;
import static com.mygdx.game.main.Main.ContentImage;

public class MapObject {
    public static ArrayList<PacketMapObject> PacketMapObjects = new ArrayList<>();
    public int width,height,hp,y,x;
    public int width_render,height_render;
    public Sprite img;
    public float distance_lighting,distance_lighting_2;
    public boolean lighting;
    public ComponentCollisionSystem Collision;
    public ObjectMapAssets assets;
    public MapObject(){
    }
    public MapObject(int x, int y, Sprite img, int width, int height, int hp, int ix, int iy,
        ComponentCollisionSystem collision,boolean lighting,float distance_lighting,ObjectMapAssets assets){
        this.x = x+ix*Main.width_block;
        this.y = y+iy*Main.height_block;
        this.width = width;
        this.lighting = lighting;
        this.height = height;
        this.width_render = (int) (width*Main.Zoom);
        this.height_render = (int) (height*Main.Zoom);
        this.distance_lighting = distance_lighting;
        this.hp = hp;
        this.img = img;
        this.assets = assets;
        //center_render();

        Collision = collision;
    }
    public void render(){
        int[]xy = Main.RC.render_objZoom(this.x,this.y);
        if(lighting)Block.LightingAirObject(xy[0],xy[1],RGBFlame,distance_lighting*Main.Zoom);
        RenderMethod.transorm_img(xy[0],xy[1],width_render,height_render,img);
    }
    public static Sprite ImageLoad(ObjectMapAssets assets){
        Sprite img = null;
        switch (assets){
            case pepper:{img = ContentImage.pepper_object_map;}
            break;
            case building:{img = ContentImage.big_build_wood_1;}
            break;
        }
        return img;

    }
}
