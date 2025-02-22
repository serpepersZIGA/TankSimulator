package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.unit.Unit;

public class ComponentTowerXY extends FunctionalComponent{
    public void FunctionalIterationAnHost(Unit unit){
        unit.tower_xy();
    }
    public void FunctionalIterationClientAnHost(Unit unit){
        unit.tower_xy();
    }
    public void FunctionalIterationAnClient(Unit unit){
        unit.tower_xy();
    }
}
