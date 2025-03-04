package com.mygdx.game.main;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import Content.Bull.BullFragment;
import Content.Particle.Acid;
import Content.Particle.FlameSpawn;
import com.mygdx.game.soldat.SoldatPacket;
import com.mygdx.game.build.BuildPacket;
import com.mygdx.game.method.CycleTimeDay;
import com.mygdx.game.method.Keyboard;
import com.mygdx.game.object_map.MapObject;
import com.mygdx.game.unit.DebrisPacket;
import com.mygdx.game.unit.Unit;
import com.mygdx.game.unit.TransportPacket;

import static Content.Bull.BullRegister.PacketBull;
import static com.mygdx.game.build.BuildRegister.PacketBuilding;
import static com.mygdx.game.main.Main.*;
import static com.mygdx.game.main.ServerMain.Server;
import static com.mygdx.game.soldat.SoldatRegister.PacketSoldat;
import static com.mygdx.game.unit.TransportRegister.*;

public class ActionGameHost extends com.mygdx.game.main.ActionGame {
    private int i;
    private static int timer = 0;
    public void action() {
        RC.method();
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
                        timer = 30;
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));
                        Main.BulletList.add(new BullFragment(Keyboard.MouseX / Zoom + RC.x2,Keyboard.MouseY / Zoom + RC.y2,40,20,(byte)1));


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
        //boolean[]mouse_e = new metod.mouse_control().mouse_event();
        //Main.player_obj.get(1).all_action_client(Main.left_mouse_client, Main.right_mouse_client, Main.mouse_x_client,
                //Main.mouse_y_client, Main.press_w_client, Main.press_a_client, Main.press_s_client, Main.press_d_client);
        if(Unit.ai_sost != 0){
            Unit.ai_sost-=1;}
        if(flame_spawn_time > 0){flame_spawn_time-=1;}
        Batch.begin();
        Render.begin(ShapeRenderer.ShapeType.Filled);
        Main.RC.render_block();
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
            packet_enemy_soldat();
            Main.SoldatList.get(i).all_action(i);
        }
        for (i= 0; i< Main.FlameSpawnList.size(); i++){
            Main.FlameSpawnList.get(i).all_action(i);
        }
        for(i = 0;i< DebrisList.size();i++) {
            Unit debris = DebrisList.get(i);
            packet_debris_server(debris);
            debris.all_action();
        }
        for(i = 0;i< UnitList.size();i++) {
            Unit unit = UnitList.get(i);
            unit.UpdateUnit();
            packet_player_server(unit);
            if(unit.host || unit.control == RegisterControl.controllerBot
                    || unit.control == RegisterControl.controllerBotSupport
                    || unit.control == RegisterControl.controllerSoldatTransport) {
                unit.all_action();
            }
            else{
                unit.all_action_client();
            }
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
        for(Unit unit : UnitList) {
            unit.update();
        }
        for (i= 0; i< AirList.size(); i++){
            for(int i2= 0; i2< AirList.get(i).size(); i2++) {
                AirList.get(i).get(i2).all_action();
            }
        }
        for (i= 0; i< Main.BangList.size(); i++){
            Main.BangList.get(i).all_action(i);}
        Render.end();
        Batch.end();
        server_packet();
        if(Unit.ai_sost == 0){
            Unit.ai_sost=400;}
        if(flame_spawn_time <= 0){flame_spawn_time=flame_spawn_time_max;}
        CycleDayNight.WorkTime();
        Collision.CollisionIterationGlobal();
    }
    private void server_packet() {
        if(EnumerationList){
            for (i = 0; i < Main.UnitList.size(); i++) {
                packet_player_server(Main.UnitList.get(i));
            }
            PacketUnit.clear();
            EnumerationList = false;
        }
        PacketServer.debris = PacketDebris;
        PacketServer.soldat = PacketSoldat;
        PacketServer.player = PacketUnit;
        PacketServer.bull = PacketBull;
        PacketServer.building = PacketBuilding;
        PacketServer.mapObject = MapObject.PacketMapObjects;
        PacketServer.TotalLight = CycleTimeDay.lightTotal;
        Server.sendToAllUDP(PacketServer);
        MapObject.PacketMapObjects.clear();
        PacketUnit.clear();
        PacketBull.clear();
        PacketSoldat.clear();
        PacketDebris.clear();
        PacketBuilding.clear();
    }
    private void packet_player_server(Unit unit){
        PacketUnit.add(new TransportPacket());
        TransportPacket pack = PacketUnit.get(i);
        pack.name = unit.type_unit;
        pack.x = unit.x;
        pack.y = unit.y;
        pack.PlayerConf = unit.PlayerConf;
        pack.rotation_corpus = unit.rotation_corpus;
        pack.rotation_tower = unit.rotation_tower;
        pack.reload = unit.reload;
        pack.hp = unit.hp;
        pack.team = unit.team;
        pack.speed = unit.speed;
        pack.host = unit.host;
        pack.IDClient = unit.nConnect;
        for (Unit Tower : unit.tower_obj){
            pack.rotation_tower_2.add(Tower.rotation_tower);
        }
    }
    private void packet_enemy_server(Unit unit){
        com.mygdx.game.unit.TransportRegister.PacketUnit.add(new TransportPacket());
        TransportPacket pack = com.mygdx.game.unit.TransportRegister.PacketUnit.get(i);
        pack.name = unit.type_unit;
        pack.x = unit.x;
        pack.y = unit.y;
        pack.crite_life = unit.crite_life;
        pack.rotation_corpus = unit.rotation_corpus;
        pack.rotation_tower = unit.rotation_tower;
        pack.reload = unit.reload;
        pack.hp = unit.hp;
        pack.team = unit.team;
        pack.speed = unit.speed;
        for (int i2 = 0; i2< unit.tower_obj.size(); i2++) {
            pack.rotation_tower_2.add(unit.tower_obj.get(i2).rotation_tower);
        }
    }
    private void packet_debris_server(Unit unit){
        PacketDebris.add(new DebrisPacket());
        PacketDebris.get(i).name = unit.type_unit;
        PacketDebris.get(i).x = unit.x;
        PacketDebris.get(i).y = unit.y;
        PacketDebris.get(i).rotation = unit.rotation_corpus;
    }
    private void packet_enemy_soldat(){
        PacketSoldat.add(new SoldatPacket());
        PacketSoldat.get(i).name = SoldatList.get(i).name;
        PacketSoldat.get(i).x = SoldatList.get(i).x;
        PacketSoldat.get(i).y = SoldatList.get(i).y;
        PacketSoldat.get(i).rotation = SoldatList.get(i).rotation;
        PacketSoldat.get(i).team = SoldatList.get(i).team;
    }
    public void PacketBuildServer(int i){
        PacketBuilding.add(new BuildPacket());
        PacketBuilding.get(i).name = BuildingList.get(i).name;
        PacketBuilding.get(i).x = BuildingList.get(i).x;
        PacketBuilding.get(i).y = BuildingList.get(i).y;
    }
}
