/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.gfx.display.menu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import mychessgame.states.State;


public class StartButton extends Button{
    
    public StartButton(BufferedImage texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }
    
 public void tick(){}
     
    @Override
 public void render(Graphics g){
     g.drawImage(texture, x, y, width,height,null);
 }

    public Rectangle getHitbox() {
        return hitbox;
    }
 
}
