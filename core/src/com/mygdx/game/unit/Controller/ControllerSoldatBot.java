package com.mygdx.game.unit.Controller;

import com.mygdx.game.unit.Unit;

public class ControllerSoldatBot extends Controller{
    public void ControllerIteration(Unit unit){
        unit.AiSoldat();
        unit.bot_fire();
        //unit.TowerControlBot();
        //unit.SoldatMoveBot();
        //unit.SoldatRotateBot();
    }

}
