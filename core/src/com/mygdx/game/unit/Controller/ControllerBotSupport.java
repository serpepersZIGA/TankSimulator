package com.mygdx.game.unit.Controller;

import com.mygdx.game.unit.Unit;

public class ControllerBotSupport {
    public void ControllerIteration(Unit unit, int i){
        unit.behavior_bot(unit.enemyList,i);
        unit.bot_fire(unit,unit.enemyList);
        unit.FireBotControl();
        unit.tower_ii();
        for (Unit Tower : unit.tower_obj){
            Tower.TargetX = unit.TargetX;
            Tower.TargetY = unit.TargetY;
            Tower.left_mouse = unit.left_mouse;
            Tower.TowerControlBot();
            Tower.bot_fire(Tower,unit.enemyList);
            Tower.FireBotControl();
        }
    }
}
