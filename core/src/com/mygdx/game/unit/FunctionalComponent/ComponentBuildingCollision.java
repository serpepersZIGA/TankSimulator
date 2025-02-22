package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.unit.Unit;

public class ComponentBuildingCollision extends FunctionalComponent{
    public void FunctionalIterationAnHost(Unit unit){
        unit.build_corpus();
    }
    public void FunctionalIterationClientAnHost(Unit unit){
        unit.build_corpus();
    }
}
