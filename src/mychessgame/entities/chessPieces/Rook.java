/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities.chessPieces;


import java.awt.image.BufferedImage;
import mychessgame.playboard.Field;



public class Rook extends ChessPiece {

    public Rook(PieceColor color,BufferedImage texture,int id,int x,int y,int width,int height) {
        super(color, texture, id,x,y,width,height);
    }
    @Override
    public boolean pieceMoveAndCaptureRule(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields){
     int pieceX=getX()/width;   
     int pieceY=getY()/height;   
    
    if((pieceY==mouseOnFieldY||pieceX==mouseOnFieldX)&&!isPieceInTheWay(mouseOnFieldX,mouseOnFieldY,fields))return true;
    else  return false;     
         
}
    
public boolean isPieceInTheWay(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields){
      //1 step calculating distance between aimed field and location of the piece
      int tmpX=(getX()/width)-mouseOnFieldX;
      int tmpY=getY()/height-mouseOnFieldY;
      //2 step while loop until distance gets to zero check every field for own and oponent pieces
      while(!(tmpX==0&&tmpY==0)){
          
          if(tmpX<0)
              tmpX++;
          if(tmpX>0)
              tmpX--;
          if(tmpY<0)
              tmpY++;
          if(tmpY>0)
              tmpY--;
          if(tmpX==0&&tmpY==0&&fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField()&&//after the distance gets to 0 check if opponent pice is on field so capturing is still an option
                      getColor()!=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor())
              return false;
          if(fields[mouseOnFieldX+tmpX][mouseOnFieldY+tmpY].isPieceOnField())//checks for any piece in the proximity of the distance
              return true;
          
          
      }
       
          return false;
  }
private void casteling()
{/*if piece in lock is a king 
    case black king the target piece is black rook on position a8 or h8
        if a8 place rook on d8 and king on c8
        if h8 place rook on f8 and king on g8
    case white king the target piece is white rook on position a1 or h1
        if a1 place rook on f1 and king on g8
        if h1 place rook on d1 and king on c1
*/}
   
    
}
