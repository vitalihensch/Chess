/*
 * 
 */
package mychessgame.states;


import java.awt.Graphics;
import mychessgame.Handler;
import mychessgame.entities.chessPieces.PiecesManager;
import mychessgame.gamelogics.GameLogic;

import mychessgame.playboard.Playboard;

/**
 *
 * 
 */
public class GameState extends State {
    
    private Playboard playboard;
    private PiecesManager piecesManager;
    private GameLogic gameLogic;
    
    public GameState(Handler handler) {
        super(handler);
        playboard=new Playboard(handler);
        piecesManager=new PiecesManager(handler);
        gameLogic=new GameLogic(handler);
        piecesManager.putPiecesOnPlayfield();
        
    }
     @Override
    public void tick() {
        playboard.tick();
        gameLogic.tick();
        
    }
     
    
    @Override
    public void render(Graphics g) {
         playboard.render(g);
         gameLogic.render(g);
        }
   
       
   
   
}    
    

    
    

    
    

