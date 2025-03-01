package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.main.Main;
import com.mygdx.game.unit.Unit;

import java.util.ArrayList;

public class FunctionalList {
    public ArrayList<FunctionalComponent> functional= new ArrayList<>();
    public void Add(FunctionalComponent component){
        functional.add(component);
    }
    public void FunctionalIterationAnHost(Unit unit){
        for(FunctionalComponent func : functional){
            func.FunctionalIterationAnHost(unit);
        }
    }
    public void FunctionalIterationClientAnHost(Unit unit){
        for(FunctionalComponent func : functional){
            func.FunctionalIterationClientAnHost(unit);
        }
    }
    public void FunctionalIterationAnClient(Unit unit){
        for(FunctionalComponent func : functional){
            func.FunctionalIterationAnClient(unit);
        }
    }
    public void FunctionalIterationOtherAnClient(Unit unit){
        for(FunctionalComponent func : functional){
            func.FunctionalIterationOtherAnClient(unit);
        }
    }
}
