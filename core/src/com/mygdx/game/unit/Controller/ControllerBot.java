package com.mygdx.game.unit.Controller;

import com.mygdx.game.method.Keyboard;
import com.mygdx.game.unit.Unit;

public class ControllerBot extends Controller{
    public void ControllerIteration(Unit unit,int i){
        unit.behavior_bot(unit.enemyList,i);
        unit.FireBotControl();
        unit.tower_ii();
        for (Unit Tower : unit.tower_obj){
            Tower.TargetX = unit.TargetX;
            Tower.TargetY = unit.TargetY;
            Tower.left_mouse = unit.left_mouse;
            Tower.tower_ii_2();
            Tower.FireBotControl();
        }
    }
}
