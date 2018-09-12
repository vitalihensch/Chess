/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities.chessPieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mychessgame.playboard.Field;
import mychessgame.textureManager.Assets;

/**
 *
 * @author home
 */
public class Bishop extends ChessPiece {

    public Bishop(PieceColor color,BufferedImage texture,int id,int x,int y,int width,int height) {
        
        super(color,texture, id,x,y,width,height);
    }
public boolean pieceMoveAndCaptureRule(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields){
     int pieceX=getX()/width;   
     int pieceY=getY()/height;   
    
    if(Math.abs(pieceX-mouseOnFieldX)==Math.abs(pieceY-mouseOnFieldY)&&!isPieceInTheWay(mouseOnFieldX,mouseOnFieldY,fields))return true;
    else  return false;     
         
}
    
public boolean isPieceInTheWay(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields){
      int tmpX=(getX()/width)-mouseOnFieldX;
      int tmpY=getY()/height-mouseOnFieldY;
      
      while(!(tmpX==0&&tmpY==0)){
          
          if(tmpX<0)
              tmpX++;
          if(tmpX>0)
              tmpX--;
          if(tmpY<0)
              tmpY++;
          if(tmpY>0)
              tmpY--;
          if(tmpX==0&&tmpY==0&&fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField()&&
                      getColor()!=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor())
              return false;
          if(fields[mouseOnFieldX+tmpX][mouseOnFieldY+tmpY].isPieceOnField())
              return true;
          
          
      }
       
          return false;
  }
   
             
  
}
