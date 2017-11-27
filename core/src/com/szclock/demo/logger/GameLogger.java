package com.szclock.demo.logger;


public class GameLogger {
    public GameLogger(){

    }

    public void log(Object ... message){
        StringBuffer result = new StringBuffer();
        for(Object messagePart : message){
            result.append(messagePart.toString());
        }
        System.out.println(result.toString());
    }
}
