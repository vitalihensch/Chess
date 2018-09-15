/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities.chessPieces;


import java.awt.image.BufferedImage;
import mychessgame.playboard.Field;


/**rules for movement of a pawn:
 *Apawn moves straight forward one square x+1, if that square is vacant.
 * If it has not yet moved if(firstMove), x+2, provided both squares are vacant.
 *  Pawns cannot move backwards.
 * 
 */
public class Pawn extends ChessPiece{
    
    public Pawn(PieceColor color,BufferedImage texture,int id,int x,int y,int width,int height) {
        super(color,texture,id,x,y,width,height);
         
    }

    @Override
    public boolean pieceMoveAndCaptureRule(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields){
     int pieceX=getX()/width;   
     int pieceY=getY()/height;
     int i=0;
     if(color.BLACK==this.getColor())//color check since black pieces go down on board and white pieces up
         i=-1;
     else
         i=1;
     
           if((pieceY-2*i)==mouseOnFieldY&&pieceX==mouseOnFieldX&&//first move of the piece allows double step normal move,only y cordinate can change and there should be no peace on the square 
                  !fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField()&&firstMove){
                     return true;}
           else if((pieceY-1*i)==mouseOnFieldY&&pieceX==mouseOnFieldX&&//normal move 1 step in y direction
                  !fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField())
                  return true;
          else if(pieceY==mouseOnFieldY+1*i&&(pieceX==(mouseOnFieldX-1*i)||pieceX==(mouseOnFieldX+1*i))&&//capturing  only move x-1||x+1 ans y-1 if enemy piece on this square !capturing!
                  fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField()&&getColor()!=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor())//maybe bad code checking if nullpointer and geting the pointer in same statement!?
                  return true; 
          else         
            return false; 
               
            
    }
public boolean isPromoteable()
{
    if((this.color==PieceColor.WHITE&&this.getY()/height==0)||(this.color==PieceColor.BLACK&&this.getY()/height==7))
        return true;
    return false;
}
   
    
}
