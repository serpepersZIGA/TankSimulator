package com.mygdx.game.unit.FunctionalComponent;

import com.mygdx.game.unit.Unit;

public class ComponentSoldatCorrect extends FunctionalComponent{
    @Override
    public void FunctionalIterationAnHost(Unit unit) {
        unit.tower_x = unit.x;
        unit.tower_y = unit.y;
        unit.rotation_tower = unit.rotation_corpus+180;
    }
}
