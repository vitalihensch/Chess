/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.states;

import java.awt.Graphics;
import mychessgame.Handler;

/**
 *
 * @author home
 */
public abstract class State {
    private static State currentState=null;
    protected Handler handler;
    
    //const
    public State(Handler handler){
        this.handler=handler;
    }
    //geters seters
    public static void setState(State state){
        currentState=state;
    }
    
    public static State getState(){
        return currentState;
    }
    //abst methods
    public abstract void tick();
    public abstract void render(Graphics g);
}
