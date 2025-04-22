package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.unit.Unit;

public class ComponentTowerControl extends FunctionalComponent{
    public void FunctionalIterationAnHost(Unit unit){
        unit.TowerControl();
    }
    public void FunctionalIterationClientAnHost(Unit unit){
        unit.TowerControl();

    }
    public void FunctionalIterationAnClient(Unit unit){
    }
    public void FunctionalIterationOtherAnClient(Unit unit){
    }
}
