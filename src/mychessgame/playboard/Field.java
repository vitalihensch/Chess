/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.playboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import mychessgame.entities.Entity;
import mychessgame.entities.chessPieces.ChessPiece;

/**
 *
 * @author home
 */
public class Field extends Entity{
    
    private Color color;
    ChessPiece pieceOnField;
    
    public Field(int x,int y,int width,int height, Color color){
        super(x,y,width,height);
        this.color=color;
        pieceOnField=null;
        
    }
public void tick(){
    if(pieceOnField!=null)
        pieceOnField.tick();
    }   
public void render(Graphics g){
    g.setColor(color);
    g.fillRect(x,y,width,height);
     if(pieceOnField!=null)
        pieceOnField.render(g);
       
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

public void setPiece(ChessPiece piece){
      pieceOnField=piece;
}
 
public boolean isPieceOnField(){
    if(pieceOnField!=null)
        return true;
    return false;
    }

    public ChessPiece getPieceOnField() {
        return pieceOnField;
    }

}



