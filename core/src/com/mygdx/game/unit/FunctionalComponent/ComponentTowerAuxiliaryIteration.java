package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.unit.Unit;

public class ComponentTowerAuxiliaryIteration extends FunctionalComponent{
    public void FunctionalIterationAnHost(Unit unit){
        unit.tower_iteration(unit);
    }
    public void FunctionalIterationClientAnHost(Unit unit){
        unit.tower_iteration(unit);

    }
    public void FunctionalIterationAnClient(Unit unit){
        unit.tower_iteration(unit);

    }
    public void FunctionalIterationOtherAnClient(Unit unit){
        unit.tower_iteration(unit);
    }
}
