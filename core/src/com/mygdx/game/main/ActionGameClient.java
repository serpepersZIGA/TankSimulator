package com.mygdx.game.main;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import Content.Particle.Acid;
import Content.Particle.FlameSpawn;
import com.mygdx.game.method.Keyboard;
import com.mygdx.game.unit.Unit;

import static com.mygdx.game.main.Main.*;
import static com.mygdx.game.main.ClientMain.Client;


public class ActionGameClient extends com.mygdx.game.main.ActionGame {
    public ActionGameClient(){
        Client.sendTCP(Main.SpawnPlayer);

    }
    int i;
    private static int timer = 0;
    public void action() {
        Main.RC.method();
        if(Main.UnitList.size()==0){
            if(Keyboard.PressW){
                Main.RC.y += 10;
            }
            if(Keyboard.PressS){
                Main.RC.y -= 10;
            }
            if(Keyboard.PressA){
                Main.RC.x -= 10;
            }
            if(Keyboard.PressD){
                Main.RC.x += 10;
            }
            try {
                if(timer <= 0) {

                    if (Keyboard.LeftMouse) {
                        Main.FlameSpawnList.add(new FlameSpawn(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2));
                        timer = 60;


                    }
                    if (Keyboard.RightMouse) {
                        //main.Main.bang_obj.add(new particle.bang(mouse_x,mouse_y,new Color(236,124,38),12));
                        Main.LiquidList.add(new Acid(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2));
                        //main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));
                        //main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));
                        //main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));

                    }
                }
                else{timer-= 1;}
            }
            catch(Exception ignored){

            }

        }



        Batch.begin();
        Render.begin(ShapeRenderer.ShapeType.Filled);
        Main.RC.render_block();
        if(flame_spawn_time > 0){flame_spawn_time-=1;}
        Batch.end();
        for (i= 0; i< Main.LiquidList.size(); i++){
            Main.LiquidList.get(i).all_action(i);}
        for (i = 0; i< Main.FlameStaticList.size(); i++){
            Main.FlameStaticList.get(i).all_action(i);}
        for (i = 0; i< Main.FlameList.size(); i++){
            Main.FlameList.get(i).all_action(i);}
        for (i = 0; i< Main.FlameParticleList.size(); i++){
            Main.FlameParticleList.get(i).all_action(i);}
        for (i = 0; i< Main.BulletList.size(); i++){
            if(Main.BulletList.get(i).height == 1) {
                Main.BulletList.get(i).all_action(i);
            }
        }
        Render.end();
        Batch.begin();
        Render.begin(ShapeRenderer.ShapeType.Filled);
        for (i = 0; i< Main.SoldatList.size(); i++){
            Main.SoldatList.get(i).all_action_client(i);}
        for (i= 0; i< Main.FlameSpawnList.size(); i++){
            Main.FlameSpawnList.get(i).all_action(i);
        }
        for(i = 0;i< UnitList.size();i++) {
            Unit unit = UnitList.get(i);
            unit.UpdateUnit();
            if(unit.host || unit.nConnect != IDClient) {
                unit.all_action_client_2();
            }
            else {
                unit.all_action_client_1();
            }
        }

        for (Unit debris : DebrisList){
            debris.all_action_client();
        }
        RC.BuildingIteration();
        Batch.draw(textureBuffer,-20,1,1,1);
        Render.end();

        Render.begin(ShapeRenderer.ShapeType.Filled);

        for (i = 0; i< Main.BulletList.size(); i++){
            if(Main.BulletList.get(i).height == 2) {
                Main.BulletList.get(i).all_action(i);
            }
        }
        for (i= 0; i< Main.UnitList.size(); i++){
            Main.UnitList.get(i).update();
        }
        for (i= 0; i< UnitList.size(); i++){
            UnitList.get(i).update();
        }
        for (i= 0; i< AirList.size(); i++){
            for(int i2= 0; i2< AirList.get(i).size(); i2++) {
                AirList.get(i).get(i2).all_action();
            }
        }
        for (i= 0; i< Main.BangList.size(); i++){
            Main.BangList.get(i).all_action(i);
        }
        if(flame_spawn_time < 0){flame_spawn_time=flame_spawn_time_max;}
        Render.end();
        Batch.end();
    }
}
