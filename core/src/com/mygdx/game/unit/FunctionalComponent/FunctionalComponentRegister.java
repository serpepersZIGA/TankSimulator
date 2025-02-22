package com.mygdx.game.unit.FunctionalComponent;

public class FunctionalComponentRegister {
    public FunctionalComponent TowerXY,MotorControl,BuildCollision,Hill,SoldatSpawn;
    public FunctionalComponentRegister(){
        TowerXY = new ComponentTowerXY();
        MotorControl = new ComponentMotorControl();
        BuildCollision = new ComponentBuildingCollision();
        Hill = new ComponentHill();
        SoldatSpawn = new ComponentSpawnSoldat();
    }
}
