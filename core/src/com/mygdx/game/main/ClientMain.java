package com.mygdx.game.main;
import Content.Build.BigBuildingWood1;
import Content.Build.Home1;
import Content.Bull.*;
import Content.Particle.*;
import Content.Transport.Transport.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.block.Block;
import com.mygdx.game.build.BuildPacket;
import com.mygdx.game.build.BuildType;
import com.mygdx.game.build.PacketBuildingServer;
import com.mygdx.game.bull.Bullet;
import com.mygdx.game.method.CycleTimeDay;
import com.mygdx.game.object_map.ObjectLoad;
import com.mygdx.game.method.SoundPlay;
import Content.Soldat.SoldatBull;
import Content.Soldat.SoldatFlame;
import com.mygdx.game.soldat.SoldatPacket;
import com.mygdx.game.object_map.MapObject;
import com.mygdx.game.object_map.ObjectMapAssets;
import com.mygdx.game.object_map.PacketMapObject;
import com.mygdx.game.object_map.VoidObject;
import com.mygdx.game.object_map.component_collision_system.CollisionVoid;
import com.mygdx.game.unit.*;
import com.mygdx.game.unit.SpawnPlayer.*;

import static Content.Bull.BullRegister.PacketBull;
import static com.mygdx.game.build.BuildRegister.PacketBuilding;
import static com.mygdx.game.main.Main.*;
import static com.mygdx.game.soldat.SoldatRegister.PacketSoldat;
import static com.mygdx.game.object_map.MapObject.PacketMapObjects;
import static com.mygdx.game.unit.TransportRegister.*;


public class ClientMain extends Listener{
    public static Client Client;
    static int udpPort = 27950, tcpPort = 27950;
    public static String IP = "127.0.0.1";
    private int i;
    public void create() {
        System.out.println("Подключаемся к серверу");
        Client = new Client(10000000,10000000);

        //Регистрируем пакет
        Client.getKryo().register(PackerServer.class);
        Client.getKryo().register(Packet_client.class);
        Client.getKryo().register(TransportPacket.class);
        Client.getKryo().register(BullPacket.class);
        Client.getKryo().register(ArrayList.class);
        Client.getKryo().register(DebrisTransport.class);
        Client.getKryo().register(SoundPlay.class);
        Client.getKryo().register(SoldatPacket.class);
        Client.getKryo().register(DebrisPacket.class);
        Client.getKryo().register(SoldatFlame.class);
        Client.getKryo().register(SoldatBull.class);
        Client.getKryo().register(UnitType.class);
        Client.getKryo().register(Bang.class);
        Client.getKryo().register(FlameSpawn.class);
        Client.getKryo().register(Flame.class);
        Client.getKryo().register(FlameParticle.class);
        Client.getKryo().register(Acid.class);
        Client.getKryo().register(Blood.class);
        Client.getKryo().register(FlameStatic.class);
        Client.getKryo().register(BuildPacket.class);
        Client.getKryo().register(BuildType.class);
        Client.getKryo().register(BullFlame.class);
        Client.getKryo().register(BullFragment.class);
        Client.getKryo().register(BullAcid.class);
        Client.getKryo().register(BullTank.class);
        Client.getKryo().register(BullMortar.class);
        Client.getKryo().register(PacketBuildingServer.class);
        Client.getKryo().register(PlayerSpawnData.class);
        Client.getKryo().register(SpawnPlayerCannonFlame.class);
        Client.getKryo().register(SpawnPlayerCannonAcid.class);
        Client.getKryo().register(SpawnPlayerCannonMortar.class);
        Client.getKryo().register(SpawnPlayerCannonBull.class);
        Client.getKryo().register(SpawnPlayerVoid.class);

        Client.getKryo().register(PacketMapObject.class);
        Client.getKryo().register(ObjectMapAssets.class);

        //Запускаем клиент
        Client.start();
        //Клиент начинает подключатся к серверу

        //Клиент подключается к серверу
        try {
            Client.connect(5000, IP, tcpPort, udpPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Client.addListener(Main.Main_client);
    }

    @Override
    public void received(Connection c, Object p) {
        Main.IDClient = c.getID();
        if(p instanceof PackerServer) {
            PacketBull = ((PackerServer) p).bull;
            i = BulletList.size();
            for (BullPacket pack : PacketBull) {
                switch (pack.type) {
                    case 1:
                        BulletList.add(new BullFlame(pack.x,pack.y,
                                pack.rotation, 0.0F, 0.0F, 0,pack.team,pack.height));
                        break;
                    case 2:
                        BulletList.add(new BullFragment(pack.x, pack.y,
                                0.0F, 0.0f, pack.height));break;

                    case 3:
                        BulletList.add(new BullMortar(pack.x, pack.y,
                                pack.rotation, 0.0f, 0f, 0f, 0f,pack.team
                                ,pack.height));break;
                    case 4:
                        BulletList.add(new BullAcid(pack.x,pack.y,
                                pack.rotation, 0.0f, 0.0f,pack.team
                                , pack.height));break;
                    case 5:
                        BulletList.add(new BullTank(pack.x, pack.y,
                                pack.rotation, 0.0f, 0.0f, pack.team
                                , pack.height));break;
                }
            }
            CycleTimeDay.lightTotal = ((PackerServer) p).TotalLight;
            PacketUnit = ((PackerServer) p).player;
            if (PacketUnit.size() == Main.UnitList.size()) {
                for (i = 0; i < PacketUnit.size(); i++) {
                    player_data(i);
                }
            } else {
                Main.UnitList.clear();
                for (i = 0; i < PacketUnit.size(); i++) {
                    switch (PacketUnit.get(i).name) {
                        case PlayerFlameT1:
                            Main.UnitList.add(new PlayerCannonFlame(0, 0, Main.UnitList, PacketUnit.get(i).host,(byte)1));
                            break;
                        case PlayerMortarT1:
                            Main.UnitList.add(new PlayerCannonMortar(0, 0, Main.UnitList, PacketUnit.get(i).host,(byte)1));
                            break;
                        case PlayerT1:
                            Main.UnitList.add(new PlayerCannonBullTank(0, 0, Main.UnitList, PacketUnit.get(i).host,(byte)1));
                            break;
                        case PlayerAcidT1:
                            Main.UnitList.add(new PlayerCannonAcid(0, 0, Main.UnitList, PacketUnit.get(i).host,(byte)1));
                            break;
                        case PanzerFlameT1:
                            UnitList.add(new PanzerFlameT1(0, 0, UnitList,(byte)2));
                            break;
                        case PanzerMortarT1:
                            UnitList.add(new PanzerMortarT1(0, 0, UnitList,(byte)2));
                            break;
                        case PanzerT1:
                            UnitList.add(new PanzerT1(0, 0, UnitList,(byte)2));
                            break;
                        case PanzerAcidT1:
                            UnitList.add(new PanzerAcidT1(0, 0, UnitList,(byte)2));
                            break;
                        case TrackRemountT1:
                            UnitList.add(new TrackRemountT1(0, 0, UnitList,(byte)2));
                            break;
                        case TrackSoldatT1:
                            UnitList.add(new TrackSoldatT1(0, 0, UnitList,(byte)2));
                            break;
                    }
                    Main.UnitList.get(Main.UnitList.size()-1).control = RegisterControl.controllerPlayer;
                    player_data(i);
                }
                KeyboardObj.zoom_const();
            }
            PacketDebris = ((PackerServer) p).debris;
            if (PacketDebris.size() == DebrisList.size()) {
                for (int i = 0; i < DebrisList.size(); i++) {
                    debris_data(i);
                }
            } else {
                DebrisList.clear();
                for (int i = 0; i < PacketDebris.size(); i++) {
                    debris_create(i, PacketDebris.get(i).x, PacketDebris.get(i).y, PacketDebris.get(i).rotation);
                    debris_data(i);
                }
                KeyboardObj.zoom_const();
            }


            PacketMapObjects = ((PackerServer) p).mapObject;
            for (PacketMapObject packetMapObject : PacketMapObjects) {
                BlockList2D.get(packetMapObject.iy).get(packetMapObject.ix).objMap
                        = new VoidObject();
                KeyboardObj.zoom_const();
            }




            PacketSoldat = ((PackerServer) p).soldat;
            if (PacketSoldat.size() == SoldatList.size()) {
                for (int i = 0; i < PacketSoldat.size(); i++) {
                    soldat_data(i);
                }
            } else {
                SoldatList.clear();
                for (SoldatPacket soldatPacket : PacketSoldat) {
                    if (soldatPacket.name.equals("flame")) {
                        SoldatList.add(new SoldatFlame(0, 0, UnitList));
                    } else if (soldatPacket.name.equals("bull")) {
                        SoldatList.add(new SoldatBull(0, 0, UnitList));
                    }
                }
                KeyboardObj.zoom_const();
            }
            PacketMapObjects.clear();
            PacketBull.clear();
            PacketUnit.clear();
            PacketDebris.clear();
            PacketSoldat.clear();
        }
        else if(p instanceof PacketBuildingServer){
            PacketBuilding = ((PacketBuildingServer) p).BuildPack;
            BuildingList.clear();
            for (int i = 0; i < PacketBuilding.size(); i++) {
                Building_create(i,PacketBuilding.get(i).x-width_block,PacketBuilding.get(i).y-height_block);
            }
            ArrayList<ArrayList<PacketMapObject>>objMapList;
            objMapList = ((PacketBuildingServer) p).ObjectMapPack;
            for (int iy = 0;iy< objMapList.size();iy++) {
                for (int ix = 0; ix < objMapList.get(iy).size(); ix++) {
                    //System.out.println(ix+" "+iy);
                    object_create(ix,iy, objMapList.get(iy).get(ix).objectAssets, objMapList.get(iy).get(ix).x,
                    objMapList.get(iy).get(ix).y,objMapList.get(iy).get(ix).width, objMapList.get(iy).get(ix).height,
                    objMapList.get(iy).get(ix).lighting,objMapList.get(iy).get(ix).distance_lighting);
                }
            }
            Block.passability_detected();

        }
    }
    public void object_create(int ix, int iy, ObjectMapAssets assets,int x,int y,int width,int height,
        boolean light,float distance_lighting) {
        try{
            if (width != 0) {
                Sprite asset = ObjectLoad.ImageLoad(assets);
                BlockList2D.get(iy).get(ix).objMap = new MapObject(x - ix * width_block, y - iy * height_block, asset, width, height, 0, ix, iy, new CollisionVoid()
                        , light, distance_lighting, assets);
            } else {
                BlockList2D.get(iy).get(ix).objMap = new VoidObject();
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    private void bull_data(BullPacket pack){
        Bullet bull = BulletList.get(this.i);
        bull.x = pack.x;
        bull.y = pack.y;
        bull.rotation = pack.rotation;
        bull.time = pack.time;
        bull.height = pack.height;
        bull.type = pack.type;
        bull.type_team = pack.team;
        this.i += 1;
    }

    private void player_data(int i) {
        Main.UnitList.get(i).type_unit = PacketUnit.get(i).name;
        Main.UnitList.get(i).x = PacketUnit.get(i).x;
        Main.UnitList.get(i).y = PacketUnit.get(i).y;
        Main.UnitList.get(i).rotation_corpus = PacketUnit.get(i).rotation_corpus;
        Main.UnitList.get(i).reload = PacketUnit.get(i).reload;
        Main.UnitList.get(i).hp = PacketUnit.get(i).hp;
        Main.UnitList.get(i).team = PacketUnit.get(i).team;
        Main.UnitList.get(i).speed = PacketUnit.get(i).speed;
        Main.UnitList.get(i).host = PacketUnit.get(i).host;
        Main.UnitList.get(i).nConnect = PacketUnit.get(i).IDClient;
        Main.UnitList.get(i).rotation_tower = PacketUnit.get(i).rotation_tower;
        for (int i2 = 0; i2< PacketUnit.get(i).rotation_tower_2.size(); i2++) {
            Main.UnitList.get(i).tower_obj.get(i2).rotation_tower = PacketUnit.get(i).rotation_tower_2.get(i2);
        }

    }
    private void enemy_data(int i){
        UnitList.get(i).type_unit = PacketUnit.get(i).name;
        UnitList.get(i).x = PacketUnit.get(i).x;
        UnitList.get(i).y = PacketUnit.get(i).y;
        UnitList.get(i).rotation_corpus = PacketUnit.get(i).rotation_corpus;
        UnitList.get(i).crite_life = PacketUnit.get(i).crite_life;
        UnitList.get(i).rotation_tower = PacketUnit.get(i).rotation_tower;
        UnitList.get(i).reload = PacketUnit.get(i).reload;
        UnitList.get(i).hp = PacketUnit.get(i).hp;
        UnitList.get(i).team = PacketUnit.get(i).team;
        UnitList.get(i).speed = PacketUnit.get(i).speed;
        for (int i2 = 0; i2< PacketUnit.get(i).rotation_tower_2.size(); i2++) {
            UnitList.get(i).tower_obj.get(i2).rotation_tower = PacketUnit.get(i).rotation_tower_2.get(i2);
        }
    }
    private void debris_data(int i){
        DebrisList.get(i).type_unit = PacketDebris.get(i).name;
        DebrisList.get(i).x = PacketDebris.get(i).x;
        DebrisList.get(i).y = PacketDebris.get(i).y;
        DebrisList.get(i).rotation_corpus = PacketDebris.get(i).rotation;
    }
    private void soldat_data(int i) {
        SoldatList.get(i).name = PacketSoldat.get(i).name;
        SoldatList.get(i).x = PacketSoldat.get(i).x;
        SoldatList.get(i).y = PacketSoldat.get(i).y;
        SoldatList.get(i).rotation = PacketSoldat.get(i).rotation;
        SoldatList.get(i).team = PacketSoldat.get(i).team;
    }
    private void enemy_create(int i){
        switch (PacketUnit.get(i).name) {
            case PanzerFlameT1:
                UnitList.add(new PanzerFlameT1(0, 0, UnitList,(byte)2));
                break;
            case PanzerMortarT1:
                UnitList.add(new PanzerMortarT1(0, 0, UnitList,(byte)2));
                break;
            case PanzerT1:
                UnitList.add(new PanzerT1(0, 0, UnitList,(byte)2));
                break;
            case PanzerAcidT1:
                UnitList.add(new PanzerAcidT1(0, 0, UnitList,(byte)2));
                break;
            case TrackRemountT1:
                UnitList.add(new TrackRemountT1(0, 0, UnitList,(byte)2));
                break;
            case TrackSoldatT1:
                UnitList.add(new TrackSoldatT1(0, 0, UnitList,(byte)2));
                break;
        }
    }
    public void Building_create(int i,int x,int y){
        if(PacketBuilding.get(i).name != null) {
            switch (PacketBuilding.get(i).name) {
                case BigBuildingWood1:
                    BuildingList.add(new BigBuildingWood1(x, y));
                    break;
                case Home1:
                    BuildingList.add(new Home1(x, y));
                    break;
            }
        }
    }
    public void debris_create(int i, float x, float y, float rotation){
        switch (PacketDebris.get(i).name) {
            case PanzerFlameT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PanzerFlameT1.corpus_img,
                        PanzerFlameT1.corpus_width, PanzerFlameT1.corpus_height, UnitType.PanzerFlameT1));
                break;
            case PanzerMortarT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PanzerMortarT1.corpus_img,
                        PanzerMortarT1.corpus_width, PanzerMortarT1.corpus_height, UnitType.PanzerMortarT1));
                break;
            case PanzerT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PanzerT1.corpus_img,
                        PanzerT1.corpus_width, PanzerT1.corpus_height, UnitType.PanzerT1));
                break;
            case PanzerAcidT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PanzerAcidT1.corpus_img,
                        PanzerAcidT1.corpus_width, PanzerAcidT1.corpus_height, UnitType.PanzerAcidT1));
                break;
            case TrackRemountT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, TrackRemountT1.corpus_img,
                        TrackRemountT1.corpus_width, TrackRemountT1.corpus_height, UnitType.TrackRemountT1));
                break;
            case TrackSoldatT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, TrackSoldatT1.corpus_img,
                        TrackSoldatT1.corpus_width, TrackSoldatT1.corpus_height, UnitType.TrackSoldatT1));
                break;


            case PlayerFlameT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PlayerCannonFlame.corpus_img,
                        PlayerCannonFlame.corpus_width, PlayerCannonFlame.corpus_height, UnitType.PlayerFlameT1));
                break;
            case PlayerT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PlayerCannonBullTank.corpus_img,
                        PlayerCannonBullTank.corpus_width, PlayerCannonBullTank.corpus_height, UnitType.PlayerT1));
                break;
            case PlayerAcidT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PlayerCannonAcid.corpus_img,
                        PlayerCannonAcid.corpus_width, PlayerCannonAcid.corpus_height, UnitType.PlayerAcidT1));
                break;
            case PlayerMortarT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, PlayerCannonMortar.corpus_img,
                        PlayerCannonMortar.corpus_width, PlayerCannonMortar.corpus_height, UnitType.PlayerMortarT1));
                break;
            case HelicopterT1:
                DebrisList.add(new DebrisTransport(x, y,rotation,0, 0,0, Helicopter_t1.corpus_img,
                        Helicopter_t1.corpus_width, Helicopter_t1.corpus_height, UnitType.HelicopterT1));
                break;
        }
    }

}

