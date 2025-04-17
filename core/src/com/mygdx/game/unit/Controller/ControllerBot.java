package com.mygdx.game.unit.Controller;

import com.mygdx.game.unit.Unit;

public class ControllerBot extends Controller{
    public void ControllerIteration(Unit unit){
        unit.behavior_bot();
        unit.bot_fire();
        //unit.FireControl();
        unit.tower_ii();
        unit.TowerControlBot();
        for (Unit Tower : unit.tower_obj){
            Tower.TargetX = unit.TargetX;
            Tower.TargetY = unit.TargetY;
            Tower.left_mouse = unit.left_mouse;
            Tower.TowerControlBot();
        }
    }
}
