package com.mygdx.game.unit.FunctionalComponent;

public class FunctionalComponentRegister {
    public FunctionalComponent TowerXY,MotorControl,BuildCollision,Hill,SoldatSpawn,TowerIteration,FireControl;
    public FunctionalComponent SoldatControl,SoldatCorrect,WorkBlade,MoveDebris,TowerControl;
    public FunctionalComponentRegister(){
        TowerControl = new ComponentTowerControl();
        MoveDebris = new ComponentMoveDebris();
        WorkBlade = new ComponentWorkBlade();
        FireControl = new ComponentFireControl();
        SoldatCorrect = new ComponentSoldatCorrect();
        TowerXY = new ComponentTowerXY();
        MotorControl = new ComponentMotorControl();
        BuildCollision = new ComponentBuildingCollision();
        Hill = new ComponentHill();
        SoldatSpawn = new ComponentSpawnSoldat();
        TowerIteration = new ComponentTowerAuxiliaryIteration();
        SoldatControl = new ComponentSoldatControl();
    }
}
