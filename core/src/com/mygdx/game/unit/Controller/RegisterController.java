package com.mygdx.game.unit.Controller;

public class RegisterController {
    public Controller controllerBot,controllerPlayer,controllerTowerBot,controllerTowerPlayer;
    public RegisterController(){
        controllerBot = new ControllerBot();
        controllerPlayer = new ControllerPlayer();
        controllerTowerBot = new ControllerTowerBot();
        controllerTowerPlayer = new ControllerTowerPlayer();
    }
}
