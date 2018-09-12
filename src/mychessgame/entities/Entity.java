/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities;

import java.awt.Graphics;



/**
 *
 * @author home
 */
public abstract class Entity {
    
    
    protected int x,y,width,height;
   
    
    public Entity(int x,int y,int width, int height){
        
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
     }  
        
       
public abstract void render(Graphics g);
public abstract void tick();
    
   

   
    
}
