package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.unit.Unit;

public class ComponentMotorControl extends FunctionalComponent{
    public void FunctionalIterationAnHost(Unit unit){
        unit.MotorControl();
    }
    public void FunctionalIterationClientAnHost(Unit unit){
        unit.MotorControl();
    }
    public void FunctionalIterationAnClient(Unit unit){
        unit.move_xy_transport();
    }
}
