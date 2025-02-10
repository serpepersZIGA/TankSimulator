package com.mygdx.game.particle;

import Content.Particle.Flame;
import Content.Particle.FlameParticle;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.Method;
import com.mygdx.game.method.SoundPlay;
import com.mygdx.game.method.move;
import com.mygdx.game.method.rand;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.mygdx.game.method.Option.SoundConst;
import static com.mygdx.game.method.pow2.pow2;
import static java.lang.StrictMath.sqrt;
import static java.sql.Types.NULL;

public abstract class Particle {
    public float x,y,size,size_2,speed_x,speed_y,interval_rise_size,size_3;
    protected float r;
    protected float g;
    protected float b;
    public static float rad;
    protected int time_delete;
    public static int brightness = 200;
    private int sound_time;
    private static final int sound_time_max = 300;
    public static final float r_m =(float)(3.0/255);
    public static final float g_m =(float)(3.0/255);
    public static final float b_m =(float)(1.0/255);
    protected int time_spawn,time_spawn_max;
    public int x_rend,y_rend,size_render;
    public float[]rgb;

    protected void timer(int i, LinkedList<Particle> obj){
        this.time_delete -= 1;
        if(this.time_delete <= 0){
            obj.remove(i);
        }
    }
    protected void spawn_flame(){
        this.time_spawn -= 1;
        if(this.time_spawn <0){
            this.time_spawn = time_spawn_max;
            Main.FlameList.add(new Flame((int)((this.x+ -20+rand.rand(40))),(int)((this.y+-20+rand.rand(40)))));
        }
    }
    protected void sound_play(){
        sound_time +=1;
        if(sound_time_max == sound_time) {
            double[]xy = Main.RC.render_obj(this.x,this.y);
            rad = 1-((float) sqrt(pow2(xy[0]) + pow2(xy[1]))/SoundConst);
            sound_time = 0;
            if(rad>0) {
                SoundPlay.sound(Main.ContentSound.get(0).flame_sound,rad);
            }
        }
    }
    protected final void grass_delete(){
        int ix = (int) (x/Main.width_block)-1;
        int iy = (int) (y/Main.height_block)-1;
        if(ix >0 & iy >0){
            if(Main.BlockList2D.get(iy).get(ix).render_block == Main.UpdateBlockReg.GrassUpdate) {
                Main.BlockList2D.get(iy).get(ix).render_block = Main.UpdateBlockReg.Update2;
            }
        }
    }
    protected void size_update(){
        this.size_2 = this.size/2;
        this.size_3 = this.size_2-2;
    }
    private int radCollision;
    protected float RotationXY;
    public int xCollision,yCollision;
    protected void liquid_const(){
        if(Main.LiquidList.size()!= 0) {
            int[] rg = Method.detection_near_particle_xy_def(Main.LiquidList, Main.LiquidList.size()-1,Main.LiquidList);
            if (rg[0] != 0) {
                RotationXY = (int) ((-rg[1] - 90) * 3.1415926535 / 180);
                //liquid_obj.get(rg[2]).speed_x = move.move_sin2(3, RotationXY);
                //liquid_obj.get(rg[2]).speed_y = move.move_cos2(3, RotationXY);
                //this.speed_x = move.move_sin2(3, RotationXY);
                //this.speed_y = move.move_cos2(3, RotationXY);
                radCollision = rg[0];
                xCollision = rg[2];
                yCollision = rg[3];
                //Main.LiquidList.get(rg[2]).xCollision = (int) x;
                //Main.LiquidList.get(rg[2]).yCollision = (int) y;
                //Main.LiquidList.get(rg[2]).speed_x = -this.speed_x;
                //Main.LiquidList.get(rg[2]).speed_y = -this.speed_y;
            }
            else{
                xCollision = (int) x;
                yCollision = (int) y;
            }
        }

    }
    protected void liquid_physic(){
        radCollision = (int) sqrt(pow2(x-xCollision)+pow2(y-yCollision));
             if (radCollision < this.size_3) {
                 this.x -= speed_x;
                 this.y -= speed_y;
             } else if (radCollision < this.size && radCollision > this.size_2) {
                 this.x += speed_x;
                 this.y += speed_y;
             }
    }
    protected void center_render(){
        double[]xy = Main.RC.render_obj(this.x,this.y);
        this.x_rend = (int)(xy[0]* Main.Zoom);
        this.y_rend = (int)(xy[1]* Main.Zoom);
    }
    protected void size_rise(){
        this.size += this.interval_rise_size;
        this.size_2 = this.size/2;
        this.size_3 = this.size_2/2;
    }
    protected void size_rise_delete(int i){

        this.size -= this.interval_rise_size;
        if(this.size < 4){
            Main.LiquidList.remove(i);
        }
        this.size_2 = this.size/2;
        this.size_3 = this.size_2/2;
    }
    protected void color_fire(){
        this.r-= r_m;
        this.g -=g_m;
        this.b -= b_m;
        if(this.r< 0){this.r = 0;}
        if(this.g< 0){this.g = 0;}
        if(this.b< 0){this.b = 0;}
    }
    protected void move_particle(){
        this.x += this.speed_x;
        this.y += this.speed_y;
    }
    protected void create_flame_particle(LinkedList<Particle>obj){
        this.time_spawn -= 1;
        if(this.time_spawn <= 0){
            obj.add(new FlameParticle(this.x,this.y));
            this.time_spawn = this.time_spawn_max;
        }
    }
    public void all_action(int i){}
    public void update(){
    }





}
