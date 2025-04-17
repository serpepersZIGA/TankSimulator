package com.mygdx.game.unit.moduleUnit;

import Data.DataSound;
import com.mygdx.game.unit.Fire.FireRegister;
import com.mygdx.game.unit.FunctionalComponent.FunctionalComponentRegister;
import com.mygdx.game.unit.FunctionalComponent.FunctionalList;

import static com.mygdx.game.main.Main.*;

public class RegisterModuleCannon {
    public static Cannon CannonFlame,CannonMortar,CannonFlameAuxiliary;
    public RegisterModuleCannon(){
        FunctionalList func = new FunctionalList();
        func.Add(RegisterFunctionalComponent.TowerXY);
        func.Add(RegisterFunctionalComponent.FireControl);
        CannonFlame = new Cannon(55,35,34,17,1f,
                 20,6,0,0,1.5f,2
                ,2,400,40,15,20, FireRegister.FireFlame,ContentImage.tower_enemy
                ,func,ContentSound.flame_attack);
        CannonFlameAuxiliary = new Cannon(20,15,12,7,1f,
                20,6,0,0,1.5f,2
                ,2,400,40,15,20, FireRegister.FireFlame,ContentImage.tower_enemy_auxiliary_1
                ,func, ContentSound.flame_attack);
        CannonMortar = new Cannon(55,35,34,17,1f,
                20,6,20,25,1.5f,2
                ,2,400,40,15,20, FireRegister.FireFlame,ContentImage.tower_player
                ,func,ContentSound.flame_attack);

    }
}
