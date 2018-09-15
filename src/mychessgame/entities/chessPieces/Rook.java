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
      int tmpY=(getY()/height)-mouseOnFieldY;
      
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
          if(tmpX==0&&tmpY==0&&fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField()&&   //case of casteling the turn is not denied if king and its kings firt move and also rooks firs move
                  getColor()==fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor()
                  &&fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField() instanceof King 
                  &&fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().isFirstMove()
                  &&this.isFirstMove())
            return false;
          if(fields[mouseOnFieldX+tmpX][mouseOnFieldY+tmpY].isPieceOnField())//checks for any piece in the proximity of the distance
              return true;
          
          
      }
       
          return false;
  }
public boolean isCasteling(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields)
{
if(!isPieceInTheWay(mouseOnFieldX,mouseOnFieldY,fields))//if nothing in the way
{   //case black king the current piece is black rook on position a8 or h8
    if(fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField()!=null&&//gurad against null pointer exception
          fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor()==PieceColor.BLACK&&//check if piece is black
            fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField() instanceof King)//check if the target piece is king
            {
                if(this.getX()/this.width==0&&this.getY()/this.height==0)//case the rook is on a8
                {
                    ChessPiece tmp;
                    tmp=this;//first move rook to new location
                    fields[this.getX()/width][this.getY()/height].setPiece(null);
                    this.moveTo(3*width, 0);
                    fields[3][0].setPiece(tmp);
                    //moving the king to new location
                    tmp=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField();
                    fields[mouseOnFieldX][mouseOnFieldY].setPiece(null);
                    tmp.moveTo(2*width, 0);
                    fields[2][0].setPiece(tmp);
                    
                    //after placement is done we return true so no move action is going to be done
                     return true;
                }
                else if(this.getX()/this.width==7&&this.getY()/this.height==0)//case the rook is on h8
                {
                    ChessPiece tmp;
                    tmp=this;//first move rook to new location
                    fields[this.getX()/width][this.getY()/height].setPiece(null);
                    this.moveTo(5*width, 0);
                    fields[5][0].setPiece(tmp);
                    //moving the king to new location
                    tmp=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField();
                    fields[mouseOnFieldX][mouseOnFieldY].setPiece(null);
                    tmp.moveTo(6*width, 0);
                    fields[6][0].setPiece(tmp);
                    
                    //after placement is done we return true so no move action is going to be done
                     return true;
                }
                    
        
            }
    else if(fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField()!=null&&//gurad against null pointer exception
      fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor()==PieceColor.WHITE&&//check if piece is white
            fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField() instanceof King)//check if the target piece is king
            {
                if(this.getX()/this.width==0&&this.getY()/this.height==7)//case the rook is on a1
                {
                    ChessPiece tmp;
                    tmp=this;//first move rook to new location
                    fields[this.getX()/width][this.getY()/height].setPiece(null);
                    this.moveTo(3*width, 7*height);
                    fields[3][7].setPiece(tmp);
                    //moving the king to new location
                    tmp=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField();
                    fields[mouseOnFieldX][mouseOnFieldY].setPiece(null);
                    tmp.moveTo(2*width, 7*height);
                    fields[2][7].setPiece(tmp);
                    
                    //after placement is done we return true so no move action is going to be done
                     return true;
                }
                else if(this.getX()/this.width==7&&this.getY()/this.height==7)//case the rook is on h1
                {
                    ChessPiece tmp;
                    tmp=this;//first move rook to new location
                    fields[this.getX()/width][this.getY()/height].setPiece(null);
                    this.moveTo(5*width, 7*height);
                    fields[5][7].setPiece(tmp);
                    //moving the king to new location
                    tmp=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField();
                    fields[mouseOnFieldX][mouseOnFieldY].setPiece(null);
                    tmp.moveTo(6*width, 7*height);
                    fields[6][7].setPiece(tmp);
                    
                    //after placement is done we return true so no move action is going to be done
                     return true;
                }
                    
        
            }
        }



        return false;
    }
   
    
}
