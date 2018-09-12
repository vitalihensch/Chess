/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.gfx.display.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import mychessgame.entities.Entity;


public class Button extends Entity{
    BufferedImage texture;
    Rectangle hitbox;
    public Button(BufferedImage texture,int x, int y, int width, int height) {
        super(x, y, width, height);
        this.texture=texture;
        hitbox=new Rectangle(x,y,width,height);
    }

    @Override
    public void render(Graphics g) {
       
        
    }

    @Override
    public void tick() {
        
    }
    
}
