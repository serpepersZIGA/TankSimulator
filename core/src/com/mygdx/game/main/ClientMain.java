package com.mygdx.game.main;
import Content.Build.BigBuildingWood1;
import Content.Build.Home1;
import Content.Bull.*;
import Content.Particle.*;
import Content.UnitPack.Soldat.SoldatFlame;
import Content.UnitPack.Transport.Transport.*;
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
import Content.UnitPack.Soldat.SoldatBullet;
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


public class ClientMain extends Listener {
    public static Client Client;
    static int udpPort = 27950, tcpPort = 27950;
    public static String IP = "127.0.0.1";
    private int i;

    public void create() {
        System.out.println("Подключаемся к серверу");
        Client = new Client(10000000, 10000000);

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
        Client.getKryo().register(SoldatBullet.class);
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

        Client.getKryo().register(PacketUnitUpdate.class);

        //Запускаем клиент
        Client.start();
        //Клиент начинает подключатся к серверу

        //Клиент подключается к серверу
        try {
            Client.connect(5000, IP, tcpPort, udpPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        packetUnitUpdate.ConfUnitList = true;
        packetUnitUpdate.ConfDebrisList = true;

        Client.addListener(Main.Main_client);
    }

    @Override
    public void received(Connection c, Object p) {
        Main.IDClient = c.getID();
        if (p instanceof PackerServer) {
            PacketBull = ((PackerServer) p).bull;
            for (BullPacket pack : PacketBull) {
                switch (pack.type) {
                    case 1:
                        BulletList.add(new BullFlame(pack.x, pack.y,
                                pack.rotation, 0.0F, 0.0F, 0, pack.team, pack.height));
                        break;
                    case 2:
                        BulletList.add(new BullFragment(pack.x, pack.y,
                                0.0F, 0.0f, pack.height));
                        break;

                    case 3:
                        BulletList.add(new BullMortar(pack.x, pack.y,
                                pack.rotation, 0.0f, 0f, 0f, 0f, pack.team
                                , pack.height));
                        break;
                    case 4:
                        BulletList.add(new BullAcid(pack.x, pack.y,
                                pack.rotation, 0.0f, 0.0f, pack.team
                                , pack.height));
                        break;
                    case 5:
                        BulletList.add(new BullTank(pack.x, pack.y,
                                pack.rotation, 0.0f, 0.0f, pack.team
                                , pack.height));
                        break;
                }
            }

            CycleTimeDay.lightTotal = ((PackerServer) p).TotalLight;
            PacketUnit = ((PackerServer) p).player;
            i = 0;
            //UnitCreate();
            //System.out.println(PacketUnit.size()+" "+UnitList.size());
            if(PacketUnit.size()== UnitList.size()) {
                for (Unit unit : UnitList) {
                    player_data(unit);
                    i++;
                }
            }
            else {
                UnitCreate();
            }
            PacketDebris = ((PackerServer) p).debris;
            i = 0;
            //DebrisList.clear();
//            for(DebrisPacket debris :PacketDebris){
//                debris_create(debris);
//                Main_client.debris_data_add(debris);
//            }
            if(PacketDebris.size()== DebrisList.size()) {
                for (Unit debris : DebrisList) {
                    debris_data(debris);
                    i++;
                }
            }
            else {
                DebrisList.clear();
                for (DebrisPacket packetDebris : PacketDebris) {
                    Main_client.debris_create(packetDebris);
                    Main_client.debris_data_add(packetDebris);
                }
                KeyboardObj.ZoomConstTransport();
                packetUnitUpdate.ConfDebrisList = false;
            }
            ActionGameClient.PackUpdateUnit();

            PacketMapObjects = ((PackerServer) p).mapObject;
            for (PacketMapObject packetMapObject : PacketMapObjects) {
                BlockList2D.get(packetMapObject.iy).get(packetMapObject.ix).objMap
                        = new VoidObject();
                //KeyboardObj.zoom_const();
            }


//            PacketSoldat = ((PackerServer) p).soldat;
//            if (PacketSoldat.size() == SoldatList.size()) {
//                for (int i = 0; i < PacketSoldat.size(); i++) {
//                    soldat_data(i);
//                }
//            } else {
//                SoldatList.clear();
//                for (SoldatPacket soldatPacket : PacketSoldat) {
//                    if (soldatPacket.name.equals("flame")) {
//                        SoldatList.add(new SoldatFlamse(0, 0, UnitList));
//                    } else if (soldatPacket.name.equals("bull")) {
//                        SoldatList.add(new SoldatBull(0, 0, UnitList));
//                    }
//                }
//                KeyboardObj.zoom_const();
//            }

            PacketMapObjects.clear();
            PacketBull.clear();
            PacketUnit.clear();
            PacketDebris.clear();
        } else if (p instanceof PacketBuildingServer) {
            PacketBuilding = ((PacketBuildingServer) p).BuildPack;
            BuildingList.clear();
            for (int i = 0; i < PacketBuilding.size(); i++) {
                Building_create(i, PacketBuilding.get(i).x - width_block, PacketBuilding.get(i).y - height_block);
            }
            ArrayList<ArrayList<PacketMapObject>> objMapList;
            objMapList = ((PacketBuildingServer) p).ObjectMapPack;
            for (int iy = 0; iy < objMapList.size(); iy++) {
                for (int ix = 0; ix < objMapList.get(iy).size(); ix++) {
                    //System.out.println(ix+" "+iy);
                    object_create(ix, iy, objMapList.get(iy).get(ix).objectAssets, objMapList.get(iy).get(ix).x,
                            objMapList.get(iy).get(ix).y, objMapList.get(iy).get(ix).width, objMapList.get(iy).get(ix).height,
                            objMapList.get(iy).get(ix).lighting, objMapList.get(iy).get(ix).distance_lighting);
                }
            }
            Block.passability_detected();

        }
        else if (p instanceof PacketUnitUpdate) {
            packetUnitUpdate = (PacketUnitUpdate)p;
        }
    }

    public void object_create(int ix, int iy, ObjectMapAssets assets, int x, int y, int width, int height,
                              boolean light, float distance_lighting) {
        try {
            if (width != 0) {
                Sprite asset = ObjectLoad.ImageLoad(assets);
                BlockList2D.get(iy).get(ix).objMap = new MapObject(x - ix * width_block, y - iy * height_block, asset, width, height, 0, ix, iy, new CollisionVoid()
                        , light, distance_lighting, assets);
            } else {
                BlockList2D.get(iy).get(ix).objMap = new VoidObject();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void bull_data(BullPacket pack) {
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

    public void player_data(Unit unit) {
            TransportPacket packet = PacketUnit.get(i);
            unit.type_unit = packet.name;
            unit.x = packet.x;
            unit.y = packet.y;
            unit.rotation_corpus = packet.rotation_corpus;
            unit.reload = packet.reload;
            unit.hp = packet.hp;
            unit.team = packet.team;
            unit.speed = packet.speed;
            unit.host = packet.host;
            unit.nConnect = packet.IDClient;
            unit.rotation_tower = packet.rotation_tower;
            for (int i2 = 0; i2 < unit.tower_obj.size(); i2++) {
                unit.tower_obj.get(i2).rotation_tower = packet.rotation_tower_2.get(i2);
            }

    }

    public void player_data_add(TransportPacket pack) {
        Unit transport = Main.UnitList.get(Main.UnitList.size() - 1);
        transport.type_unit = pack.name;
        transport.x = pack.x;
        transport.y = pack.y;
        transport.rotation_corpus = pack.rotation_corpus;
        transport.reload = pack.reload;
        transport.hp = pack.hp;
        transport.team = pack.team;
        transport.speed = pack.speed;
        transport.host = pack.host;
        transport.nConnect = pack.IDClient;
        transport.rotation_tower = pack.rotation_tower;
        for (int i2 = 0; i2 < transport.tower_obj.size(); i2++) {
            transport.tower_obj.get(i2).rotation_tower =
                    pack.rotation_tower_2.get(i2);
        }

    }

    public void enemy_data(int i) {
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
        for (int i2 = 0; i2 < PacketUnit.get(i).rotation_tower_2.size(); i2++) {
            UnitList.get(i).tower_obj.get(i2).rotation_tower = PacketUnit.get(i).rotation_tower_2.get(i2);
        }
    }

    public void debris_data(Unit debris) {
        DebrisPacket pack = PacketDebris.get(i);
        debris.type_unit = pack.name;
        debris.x = pack.x;
        debris.y = pack.y;
        debris.rotation_corpus = pack.rotation;

    }

    public void debris_data_add(DebrisPacket packet) {
        Unit debris = DebrisList.get(DebrisList.size() - 1);
        debris.type_unit = packet.name;
        debris.x = packet.x;
        debris.y = packet.y;
        debris.rotation_corpus = packet.rotation;
    }

    private void soldat_data(int i) {
        SoldatList.get(i).name = PacketSoldat.get(i).name;
        SoldatList.get(i).x = PacketSoldat.get(i).x;
        SoldatList.get(i).y = PacketSoldat.get(i).y;
        SoldatList.get(i).rotation = PacketSoldat.get(i).rotation;
        SoldatList.get(i).team = PacketSoldat.get(i).team;
    }

    public void Building_create(int i, int x, int y) {
        if (PacketBuilding.get(i).name != null) {
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

    public void debris_create(DebrisPacket debris) {
        switch (debris.name) {
            case PanzerFlameT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PanzerFlameT1.corpus_img,
                        PanzerFlameT1.corpus_width, PanzerFlameT1.corpus_height, UnitType.PanzerFlameT1));
                break;
            case PanzerMortarT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PanzerMortarT1.corpus_img,
                        PanzerMortarT1.corpus_width, PanzerMortarT1.corpus_height, UnitType.PanzerMortarT1));
                break;
            case PanzerT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PanzerT1.corpus_img,
                        PanzerT1.corpus_width, PanzerT1.corpus_height, UnitType.PanzerT1));
                break;
            case PanzerAcidT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PanzerAcidT1.corpus_img,
                        PanzerAcidT1.corpus_width, PanzerAcidT1.corpus_height, UnitType.PanzerAcidT1));
                break;
            case TrackRemountT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, TrackRemountT1.corpus_img,
                        TrackRemountT1.corpus_width, TrackRemountT1.corpus_height, UnitType.TrackRemountT1));
                break;
            case TrackSoldatT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, TrackSoldatT1.corpus_img,
                        TrackSoldatT1.corpus_width, TrackSoldatT1.corpus_height, UnitType.TrackSoldatT1));
                break;

            case PlayerFlameT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PlayerCannonFlame.corpus_img,
                        PlayerCannonFlame.corpus_width, PlayerCannonFlame.corpus_height, UnitType.PlayerFlameT1));
                break;
            case PlayerT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PlayerCannonBullTank.corpus_img,
                        PlayerCannonBullTank.corpus_width, PlayerCannonBullTank.corpus_height, UnitType.PlayerT1));
                break;
            case PlayerAcidT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PlayerCannonAcid.corpus_img,
                        PlayerCannonAcid.corpus_width, PlayerCannonAcid.corpus_height, UnitType.PlayerAcidT1));
                break;
            case PlayerMortarT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, PlayerCannonMortar.corpus_img,
                        PlayerCannonMortar.corpus_width, PlayerCannonMortar.corpus_height, UnitType.PlayerMortarT1));
                break;
            case HelicopterT1:
                DebrisList.add(new DebrisTransport(debris.x, debris.y, debris.rotation, 0, 0, 0, Helicopter_t1.corpus_img,
                        Helicopter_t1.corpus_width, Helicopter_t1.corpus_height, UnitType.HelicopterT1));
                break;
        }
    }

    public void UnitCreate() {
        Main.UnitList.clear();
        for (TransportPacket pack : PacketUnit) {

            switch (pack.name) {
                case PlayerFlameT1:
                    Main.UnitList.add(new PlayerCannonFlame(0, 0, Main.UnitList, pack.host, (byte) 1));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PlayerMortarT1:
                    Main.UnitList.add(new PlayerCannonMortar(0, 0, Main.UnitList, pack.host, (byte) 1));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PlayerT1:
                    Main.UnitList.add(new PlayerCannonBullTank(0, 0, Main.UnitList, pack.host, (byte) 1));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PlayerAcidT1:
                    Main.UnitList.add(new PlayerCannonAcid(0, 0, Main.UnitList, pack.host, (byte) 1));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PanzerFlameT1:
                    UnitList.add(new PanzerFlameT1(0, 0, UnitList, pack.host, (byte) 2));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PanzerMortarT1:
                    UnitList.add(new PanzerMortarT1(0, 0, UnitList, pack.host, (byte) 2));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PanzerT1:
                    UnitList.add(new PanzerT1(0, 0, UnitList, pack.host, (byte) 2));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case PanzerAcidT1:
                    UnitList.add(new PanzerAcidT1(0, 0, UnitList, pack.host, (byte) 2));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBot;
                    break;
                case TrackRemountT1:
                    UnitList.add(new TrackRemountT1(0, 0, UnitList, pack.host, (byte) 2));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerBotSupport;
                    break;
                case TrackSoldatT1:
                    UnitList.add(new TrackSoldatT1(0, 0, UnitList, pack.host, (byte) 2));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerSoldatTransport;
                    break;
                case SoldatFlame:
                    UnitList.add(new SoldatFlame(0, 0, (byte) 2, pack.host));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerSoldatBot;
                    break;
                case HelicopterT1:
                    UnitList.add(new HelicopterT1(0, 0, (byte) 2, pack.host));
                    Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerHelicopter;
                    break;
            }
            if (pack.PlayerConf) {
                Main.UnitList.get(Main.UnitList.size() - 1).control = RegisterControl.controllerPlayer;
                Main.UnitList.get(Main.UnitList.size() - 1).nConnect = pack.IDClient;
            }
            player_data_add(pack);
        }
        KeyboardObj.ZoomConstTransport();
    }

}

