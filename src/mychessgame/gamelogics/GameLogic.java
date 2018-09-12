
package mychessgame.gamelogics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import mychessgame.Handler;
import mychessgame.entities.chessPieces.ChessPiece;
import mychessgame.entities.chessPieces.ChessPiece.PieceColor;
import mychessgame.playboard.Field;


public class GameLogic {
private Handler handler;
private final int TURN_WHITE_PLAYER=0, TURN_BLACK_PLAYER=1;
private int currentTurn;
private int mouseOnFieldX, mouseOnFieldY;
private int lockedX,lockedY;
private boolean locked;

   public GameLogic(Handler handler){
        this.handler=handler;
       currentTurn=TURN_WHITE_PLAYER;
    }
    
    
  public void tick(){
    getMouseAsCordinate();
    lockField();
    checksForPieceMove();
  }
  
  
  public void render(Graphics g){
     renderLockedFieldbox(g);
     renderWhosTurn(g);
    
  }
  
  private void renderWhosTurn(Graphics g)
  {
      g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
      if(currentTurn==TURN_WHITE_PLAYER)
      {
          g.setColor(Color.WHITE);
          g.drawString("White players turn!", 200 ,200);
      }
      else if(currentTurn==TURN_BLACK_PLAYER)//just so it s easier to read else alsone would be enough
      {
          g.setColor(Color.BLACK);
          g.drawString("Black players turn!", 200 ,200);
      }
  }
//all mouse operations on game field  
  
  private void getMouseAsCordinate(){//translates pixel cordinates into gamefield cordinates
         
    mouseOnFieldX=Math.min(handler.getGame().getMouseManager().getMouseX()/handler.getFieldWidth(),handler.getFieldCount()-1);
    mouseOnFieldY=Math.min(handler.getGame().getMouseManager().getMouseY()/handler.getFieldHeight(),handler.getFieldCount()-1);
    
    }  
   
  private void lockField(){//after one left click field gets locked
    if(mouseOnFieldX<handler.getFieldCount()&&mouseOnFieldY<handler.getFieldCount()&&handler.getGame().getMouseManager().isLeftPressed()&&!locked){
        locked=true;
        lockedX=mouseOnFieldX;
        lockedY=mouseOnFieldY;
       
    }
    else if(handler.getGame().getMouseManager().isRightPressed()&&locked){
          locked=false;   
    
    }
  }
 //border around the field which is locked
 private void renderLockedFieldbox(Graphics g){
     if(locked==true){
        
         g.setColor(Color.GREEN);
         g.drawRect(lockedX*handler.getFieldWidth(),lockedY*handler.getFieldHeight(),handler.getFieldWidth(),handler.getFieldHeight());
     }
         
 }

//pieces move and capture logic 
public void checksForPieceMove(){//first step check for left click on another location then the locked field location
    boolean leftClick=handler.getGame().getMouseManager().isLeftPressed();
    
    if(locked&&leftClick&&!handler.getPlayboard().getFields()[lockedX][lockedY].equals(handler.getPlayboard().getFields()[mouseOnFieldX][mouseOnFieldY]))
        if(handler.getPlayboard().getFields()[lockedX][lockedY].isPieceOnField())//second step has a Piece on the square
           if(piecesRules(handler.getPlayboard().getFields()[lockedX][lockedY].getPieceOnField()))//after the checks the Piece instance is given to the rules Function
               movePiece(handler.getPlayboard().getFields()[lockedX][lockedY]);//last step if all the conditions are ok the move  

            
      
    
}
  private boolean piecesRules(ChessPiece piece){//the function for all the rules of the game pieces
        
       return  piece.pieceMoveAndCaptureRule(mouseOnFieldX, mouseOnFieldY,handler.getPlayboard().getFields() );   
        
  }
  private void movePiece(Field theSquare){//figur von feld nehmen, figur auf neues feld stellen, die cordinaten der figur ändern
     
      
      
     ChessPiece tmp= theSquare.getPieceOnField();
     if(currentTurn==TURN_WHITE_PLAYER&&tmp.getColor()==PieceColor.WHITE)
     currentTurn=TURN_BLACK_PLAYER;
     else if(currentTurn==TURN_BLACK_PLAYER&&tmp.getColor()==PieceColor.BLACK)
     currentTurn=TURN_WHITE_PLAYER;
     else return;
     
    
     
     
     tmp.setFirstMove(false);
     theSquare.setPiece(null);
     tmp.moveTo(mouseOnFieldX*handler.getFieldWidth(),mouseOnFieldY*handler.getFieldHeight());
     
     handler.getPlayboard().getFields()[mouseOnFieldX][mouseOnFieldY].setPiece(tmp);
     tmp=handler.getPlayboard().getFields()[mouseOnFieldX][mouseOnFieldY].getPieceOnField();
     locked=false;
            
        try{Thread.sleep(200);} //short sleep so the left click is not geting recognized as another click again /human speed/
        catch(InterruptedException ex){Thread.currentThread().interrupt();}     
        }
     


private void check()
{
    
}
  
}
