package com.mygdx.game.unit.Controller;

import com.mygdx.game.method.Keyboard;
import com.mygdx.game.unit.Unit;

public class ControllerPlayer extends Controller {
    public void ControllerIteration(Unit unit){
        unit.left_mouse = Keyboard.LeftMouse;
        unit.right_mouse = Keyboard.RightMouse;
        unit.press_w = Keyboard.PressW;
        unit.press_a = Keyboard.PressA;
        unit.press_s = Keyboard.PressS;
        unit.press_d = Keyboard.PressD;
        unit.TargetX = Keyboard.MouseX;
        unit.TargetY = Keyboard.MouseY;
        for(Unit Tower : unit.tower_obj){
            Tower.TargetX = Keyboard.MouseX;
            Tower.TargetY = Keyboard.MouseY;
        }

    }
    public void ControllerIterationClientAnHost(Unit unit){

    }
    public void ControllerIterationClientAnClient(Unit unit){

    }
}
