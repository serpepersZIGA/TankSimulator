package com.mygdx.game.unit.moduleUnit;


import com.mygdx.game.unit.FunctionalComponent.FunctionalList;

import static com.mygdx.game.main.Main.RegisterFunctionalComponent;

public class RegisterModuleEngine {
    public static Engine Engine1E,Engine2E;
    public RegisterModuleEngine(){
        FunctionalList functional = new FunctionalList();
        functional.Add(RegisterFunctionalComponent.MotorControl);
        Engine1E = new Engine(4F,-4F,0.2F,1F,functional);
        Engine2E = new Engine(4F,-4F,0.2F,0.6F,functional);

    }
}
