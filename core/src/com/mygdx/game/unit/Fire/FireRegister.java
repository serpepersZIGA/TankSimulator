package com.mygdx.game.unit.Fire;

public class FireRegister {
    public static Fire FireMortar,FireFlame,FireBull,FireAcid;
    public FireRegister(){
        FireMortar = new FireMortar();
        FireFlame = new FireFlame();
        FireBull = new FireBull();
        FireAcid = new FireAcid();
    }
}
