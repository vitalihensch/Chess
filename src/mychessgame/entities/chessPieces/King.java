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
public class King extends ChessPiece{

    public King(PieceColor color,BufferedImage texture,int id,int x,int y,int width,int height ) {
        super(color,texture, id,x,y,width,height);
    }
    @Override
 public boolean pieceMoveAndCaptureRule(int mouseOnFieldX, int mouseOnFieldY,Field[][] fields){
     int pieceX=getX()/width;   
     int pieceY=getY()/height;   
      
        if((mouseOnFieldX>=pieceX-1 && mouseOnFieldX<=pieceX+1) && (mouseOnFieldY>=pieceY-1 && mouseOnFieldY<=pieceY+1))
                  if(!fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField())
                    return true;
                   else if(fields[mouseOnFieldX][mouseOnFieldY].isPieceOnField()&&
                     getColor()!=fields[mouseOnFieldX][mouseOnFieldY].getPieceOnField().getColor() )
                    return true;
                 
                       
            return false;
    }

    
    
}
