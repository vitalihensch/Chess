
package mychessgame.gamelogics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import mychessgame.Handler;
import mychessgame.entities.chessPieces.ChessPiece;
import mychessgame.entities.chessPieces.ChessPiece.PieceColor;
import mychessgame.entities.chessPieces.King;
import mychessgame.entities.chessPieces.Pawn;
import mychessgame.entities.chessPieces.PiecesFactory;
import mychessgame.entities.chessPieces.Rook;
import mychessgame.playboard.Field;


public class GameLogic {
private Handler handler;
private final int TURN_WHITE_PLAYER=0, TURN_BLACK_PLAYER=1;
private int currentTurn;
private int mouseOnFieldX, mouseOnFieldY;
private int lockedX,lockedY;
private boolean locked;
private Point promotablePiece,positionBlackKing,positionWhiteKing;
private boolean whiteKingInCheck=false,blackKingInCheck=false;


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
     renderPromotable(g);
    
  }
  private void renderPromotable(Graphics g)
  {
      if(promotablePiece!=null)
      {
           g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
          g.setColor(Color.BLACK);
          g.drawString("A Pawn reached promotoble Position", 0 ,300);
      }
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
    else if(handler.getGame().getMouseManager().isRightPressed()&&locked){//on right click the locks gets open and also promotable piece gets zeroed out, after this no promotion for same piece anymore
        promotablePiece=null;  
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
      if(promotablePiece!=null&&leftClick&&//guard against nullpointer exception
              (currentTurn==TURN_WHITE_PLAYER&&handler.getPlayboard().getFields()[promotablePiece.x][promotablePiece.y].getPieceOnField().getColor()==PieceColor.BLACK
              ||currentTurn==TURN_BLACK_PLAYER&&handler.getPlayboard().getFields()[promotablePiece.x][promotablePiece.y].getPieceOnField().getColor()==PieceColor.WHITE)//promotion of pieces schould be between the turns 
              &&mouseOnFieldX==promotablePiece.x&&mouseOnFieldY==promotablePiece.y)//making shure mouse klicking on the piece to promote 
                {
                promote();
                return;
                }
    
    if(locked&&leftClick&&!handler.getPlayboard().getFields()[lockedX][lockedY].equals(handler.getPlayboard().getFields()[mouseOnFieldX][mouseOnFieldY]))
        if(handler.getPlayboard().getFields()[lockedX][lockedY].isPieceOnField())//second step has a Piece on the square
           if(piecesRules(handler.getPlayboard().getFields()[lockedX][lockedY].getPieceOnField()))//after the checks the Piece instance is given to the rules Function
               movePiece(handler.getPlayboard().getFields()[lockedX][lockedY]);//last step if all the conditions are ok the move  

            
      
    
}
  private boolean piecesRules(ChessPiece piece){//the function for all the rules of the game pieces
        
       return  piece.pieceMoveAndCaptureRule(mouseOnFieldX, mouseOnFieldY,handler.getPlayboard().getFields() );   
        
  }
  private void movePiece(Field theSquare){//moving piece from one square to another old piece gets deleted
     if(positionBlackKing==null||positionWhiteKing==null)
         this.setKingPositions();
      this.setAnyKingInCheck();
      
     ChessPiece tmp= theSquare.getPieceOnField();
     if(currentTurn==TURN_WHITE_PLAYER&&tmp.getColor()==PieceColor.WHITE)
     currentTurn=TURN_BLACK_PLAYER;
     else if(currentTurn==TURN_BLACK_PLAYER&&tmp.getColor()==PieceColor.BLACK)
     currentTurn=TURN_WHITE_PLAYER;
     else return;
     //special case casteling of a king and a rook
     if(tmp instanceof Rook)
         if(((Rook) tmp).isCasteling(mouseOnFieldX,mouseOnFieldY,handler.getPlayboard().getFields()))
         {
             locked=false;
            
            try{Thread.sleep(200);} //short sleep so the left click is not geting recognized as another click again /human speed/
            catch(InterruptedException ex){Thread.currentThread().interrupt();}     
        
             return;
         }
    
     
     
    //normal move 
     theSquare.setPiece(null);
     tmp.moveTo(mouseOnFieldX*handler.getFieldWidth(),mouseOnFieldY*handler.getFieldHeight());
     
     handler.getPlayboard().getFields()[mouseOnFieldX][mouseOnFieldY].setPiece(tmp);
     tmp=handler.getPlayboard().getFields()[mouseOnFieldX][mouseOnFieldY].getPieceOnField();
     
     //specieal case for pawn romotion
     if(tmp instanceof Pawn)
     {
         if(((Pawn) tmp).isPromoteable())
         {
           System.out.println("Promotable Piece");
           promotablePiece=new Point(mouseOnFieldX,mouseOnFieldY);
             
         }
     }
     if(tmp instanceof King)
     {
         this.setKingPositions();
     }
     
      locked=false;      
        try{Thread.sleep(200);} //short sleep so the left click is not geting recognized as another click again /human speed/
        catch(InterruptedException ex){Thread.currentThread().interrupt();}     
        }
     


    private void setAnyKingInCheck()
   {//gives the cordinates of whiteKing to every black piece as moving Option and checks if it is a legit move for the piece if it is then the white king is in check
    //same for black king    
       for(int y=0;y<handler.getFieldCount();y++)
       {
           for(int x=0;x<handler.getFieldCount();x++)
           {
             if(handler.getPlayboard().getFields()[y][x].getPieceOnField()!=null&&positionBlackKing!=null
                       &&handler.getPlayboard().getFields()[y][x].getPieceOnField().getColor()==PieceColor.WHITE)
                       blackKingInCheck=!handler.getPlayboard().getFields()[y][x].getPieceOnField().pieceMoveAndCaptureRule(
                        (int)positionBlackKing.getX(),(int) positionBlackKing.getY(),handler.getPlayboard().getFields() );
             if(handler.getPlayboard().getFields()[y][x].getPieceOnField()!=null&&positionWhiteKing!=null
                       &&handler.getPlayboard().getFields()[y][x].getPieceOnField().getColor()==PieceColor.BLACK)
                       whiteKingInCheck=!handler.getPlayboard().getFields()[y][x].getPieceOnField().pieceMoveAndCaptureRule(
                        (int) positionWhiteKing.getX(),(int) positionWhiteKing.getY(),handler.getPlayboard().getFields() );
             
             
           }
       }
       if(blackKingInCheck||whiteKingInCheck)
       System.out.println("check");
    }
    private void setKingPositions(){
         for(int y=0;y<handler.getFieldCount();y++)
       {
           for(int x=0;x<handler.getFieldCount();x++)
           {
               
               if(handler.getPlayboard().getFields()[y][x].getPieceOnField()!=null
                       &&handler.getPlayboard().getFields()[y][x].getPieceOnField() instanceof King)
                       {
                           if(handler.getPlayboard().getFields()[y][x].getPieceOnField().getColor()==PieceColor.BLACK)
                           {   positionBlackKing=new Point(x,y);
                           System.out.println("Black king set to "+x+"=x "+y+"=y");}
                           if(handler.getPlayboard().getFields()[y][x].getPieceOnField().getColor()==PieceColor.WHITE)
                           {  positionWhiteKing=new Point(x,y);
                           System.out.println("White king set to "+x+"=x "+y+"=y");}
                       }
           }
       }
         
    }
    private void promote()
    {
        PiecesFactory factory=new PiecesFactory();
        ChessPiece pieceToPromote=handler.getPlayboard().getFields()[promotablePiece.x][promotablePiece.y].getPieceOnField();
        ChessPiece promotedPiece=factory.promotePiece(pieceToPromote);
        handler.getPlayboard().getFields()[promotablePiece.x][promotablePiece.y].setPiece(promotedPiece);
    System.out.println("Piece have been promoted!");
    try{Thread.sleep(200);} //short sleep so the left click is not geting recognized as another click again /human speed/
        catch(InterruptedException ex){Thread.currentThread().interrupt();}
    }
  
}
