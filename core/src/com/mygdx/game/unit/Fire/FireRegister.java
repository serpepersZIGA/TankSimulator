package com.mygdx.game.unit.Fire;

public class FireRegister {
    public static Fire FireMortar,FireFlame, FireBullet,FireAcid;
    public FireRegister(){
        FireMortar = new FireMortar();
        FireFlame = new FireFlame();
        FireBullet = new FireBull();
        FireAcid = new FireAcid();
    }
}
