package com.mygdx.game.unit.Controller;

public class RegisterController {
    public Controller controllerBot,controllerPlayer,controllerTowerBot,controllerTowerPlayer,controllerBotSupport,
    controllerSoldatTransport;
    public RegisterController(){
        controllerBot = new ControllerBot();
        controllerPlayer = new ControllerPlayer();
        controllerTowerBot = new ControllerTowerBot();
        controllerTowerPlayer = new ControllerTowerPlayer();
        controllerBotSupport = new ControllerBotSupport();
        controllerSoldatTransport = new ControllerSoldatTransport();
    }
}
