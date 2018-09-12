/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.states;

import java.awt.Color;
import java.awt.Graphics;
import mychessgame.Handler;
import mychessgame.gfx.display.menu.MenuGUI;


public class MenuState extends State{
    private int width;
    private int height;
    private MenuGUI menuGUI;
    public MenuState(Handler handler) {
        super(handler);
        width=handler.getWidth();
        height=handler.getHeight();
        menuGUI=new MenuGUI(handler);
    }

    @Override
    public void tick() {
        menuGUI.tick();
    }

    @Override
    public void render(Graphics g) {
        menuGUI.render(g);
    }

    
}
