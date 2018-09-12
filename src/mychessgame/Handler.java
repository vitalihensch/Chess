/*not sure if rly needed
 * Handles the connection between the Game class and other dependent classes
 */
package mychessgame;

import mychessgame.playboard.Playboard;
import mychessgame.states.State;

/**
 *
 * @author home
 */
public class Handler {
   //the managed instances
    private Playboard playboard;
    private Game game;
    //private World world; not sure bout this yet
    public Handler(Game game){
        this.game=game;
        playboard=null;
        
    }
    
    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
        
    }
    public int getFieldHeight(){
        return game.getFIELDHIGHT();
    }
    public int getFieldWidth(){
        return game.getFIELDWIDTH();
    }
    public int getFieldCount(){
        return game.getBOARDSIZE();
    }

    public Game getGame() {
        return game;
    }

    public Playboard getPlayboard() {
        return playboard;
    }

    public void setPlayboard(Playboard playboard) {
        this.playboard = playboard;
    }
    
}
