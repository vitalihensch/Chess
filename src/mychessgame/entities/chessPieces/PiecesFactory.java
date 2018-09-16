/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities.chessPieces;

import mychessgame.entities.chessPieces.ChessPiece.PieceColor;
import mychessgame.textureManager.Assets;

/**
 * 
  
  
  
 * @author home
 */
public class PiecesFactory {
    public PiecesFactory(){
        
    }
    
    public ChessPiece createPiece(int i,int x,int y,int width,int height){
        switch(i){
            case 0: return new Pawn(PieceColor.WHITE,Assets.wPawn,0,x,y,width,height);
            case 1: return new Rook(PieceColor.WHITE,Assets.wRook,1,x,y,width,height);
            case 2: return new Knight(PieceColor.WHITE,Assets.wKnight,2,x,y,width,height);
            case 3: return new Bishop(PieceColor.WHITE,Assets.wBishop,3,x,y,width,height);
            case 4: return new King(PieceColor.WHITE,Assets.wKing,4,x,y,width,height);
            case 5: return new Queen(PieceColor.WHITE,Assets.wQueen,5,x,y,width,height);
            case 6: return new Pawn(PieceColor.BLACK,Assets.bPawn,6,x,y,width,height);
            case 7: return new Rook(PieceColor.BLACK,Assets.bRook,7,x,y,width,height);
            case 8: return new Knight(PieceColor.BLACK,Assets.bKnight,8,x,y,width,height);
            case 9: return new Bishop(PieceColor.BLACK,Assets.bBishop,9,x,y,width,height);
            case 10: return new King(PieceColor.BLACK,Assets.bKing,10,x,y,width,height);
            case 11: return new Queen(PieceColor.BLACK,Assets.bQueen,11,x,y,width,height);
            
            default: return null;
        }
        
    }

    public ChessPiece promotePiece(ChessPiece piece)
    {
        if(piece.getColor()==PieceColor.WHITE)
        {
            if(piece instanceof Pawn)return new Rook(PieceColor.WHITE,Assets.wRook,1,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Rook)return new Knight(PieceColor.WHITE,Assets.wKnight,2,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Knight)return new Bishop(PieceColor.WHITE,Assets.wBishop,3,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Bishop)return new Queen(PieceColor.WHITE,Assets.wQueen,5,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Queen)return new Rook(PieceColor.WHITE,Assets.wRook,1,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
        }
        else if(piece.getColor()==PieceColor.BLACK)
        {
            if(piece instanceof Pawn)return new Rook(PieceColor.BLACK,Assets.bRook,7,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Rook)return new Knight(PieceColor.BLACK,Assets.bKnight,8,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Knight)return new Bishop(PieceColor.BLACK,Assets.bBishop,9,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Bishop)return new Queen(PieceColor.BLACK,Assets.bQueen,11,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            else if(piece instanceof Queen)return new Rook(PieceColor.BLACK,Assets.bRook,7,piece.getX(),piece.getY(),piece.getWidth(),piece.getHeight());
            
        }
        return null;
    }

    
}
