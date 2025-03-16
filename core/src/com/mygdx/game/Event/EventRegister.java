package com.mygdx.game.Event;

public class EventRegister {
    public EventGame eventDeadSoldat,eventDeadTransport;
    public EventRegister(){
        eventDeadSoldat = new EventDeadSoldat();
        eventDeadTransport = new EventDeadTransport();
    }
}
