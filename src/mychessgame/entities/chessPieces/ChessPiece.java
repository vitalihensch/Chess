/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities.chessPieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mychessgame.entities.Entity;
import mychessgame.playboard.Field;

/**
 *
 * @author home
 */
public class ChessPiece extends Entity{
    protected BufferedImage texture;
    protected int id;
    protected boolean firstMove;
    public enum PieceColor{BLACK, WHITE};
    protected PieceColor color;
    public ChessPiece(PieceColor color,BufferedImage texture, int id,int x, int y, int width, int height) {
        super(x, y, width, height);
        this.texture=texture;
        this.id=id;
        firstMove=true;
        this.color=color;
    }
   public void tick(){
       
   } 
   public void render(Graphics g){
      g.drawImage(texture, x, y,width,height,null); 
   }
   public boolean isEnemy(PieceColor color){
       if(this.color.equals(color))
           return false;
        return true;
   }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
   public void moveTo(int x,int y){
       setX(x);
       setY(y);
   }

    public PieceColor getColor() {
        return color;
    }
    public boolean pieceMoveAndCaptureRule(int x,int y,Field[][] z){
        return false;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
   public boolean isFirstMove(){
       return this.firstMove;
   } 
   
}
