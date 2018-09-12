/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.entities.chessPieces;



import mychessgame.Handler;
import positions.PiecesLoader;




public class PiecesManager {
    private String DEFAULT_POS_PATH="src/positions/StartPosition.txt";
    PiecesLoader piecesFromFile;
    PiecesFactory piecesFactory;
    ChessPiece[][] pieces;
    ChessPiece pieceInFocus=null;
    //pieces saved
    Handler handler;
    
    
    //constructor
    public PiecesManager(Handler handler){
        this.handler=handler;
        piecesFactory=new PiecesFactory();
        piecesFromFile=new PiecesLoader(handler,DEFAULT_POS_PATH);
        }
    
    //create Pieces in Factory and add to Array List
    public void putPiecesOnPlayfield(){
        int[] arrOfPiecePositions=piecesFromFile.getPiecePositions();
        
        for(int y=0;y<handler.getFieldCount();y++){
            for(int x=0;x<handler.getFieldCount();x++){
               handler.getPlayboard().getFields()[x][y].setPiece(piecesFactory.createPiece(arrOfPiecePositions[x+y*handler.getFieldCount()],//<<Piece ID
                                                                    handler.getPlayboard().getFields()[x][y].getX(),//x cordinate
                                                                    handler.getPlayboard().getFields()[x][y].getY(),// y cordinate
                                                                    handler.getPlayboard().getFields()[x][y].getWidth(),//width
                                                                    handler.getPlayboard().getFields()[x][y].getHeight()));//height
                            
                
            }
        }
        
        
        
    }

    
   


    
}
   
  
