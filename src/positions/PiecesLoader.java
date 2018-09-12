/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package positions;

import mychessgame.Handler;
import utility.Utility;

/**
 *file gets split into array of ints with the size of the playfield every int 
 * every int stands for a game piece, can be reused for LOAD GAME, SAVE GAME maybe
 * @author home
 */
public class PiecesLoader {
    Handler handler;
    private int[] piecePositions;
    public PiecesLoader(Handler handler, String path){
        this.handler=handler;
        piecePositions=new int[handler.getFieldCount()*handler.getFieldCount()];//the size is the number of the Playfields on the Gameboard
        loadPositions(path);
    }

    public PiecesLoader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void loadPositions(String path){
        String file=Utility.loadFileAsString(path);
        String[] tokens=file.split("\\s+");
        for(int y=0;y<handler.getFieldCount();y++){
            for(int x=0;x<handler.getFieldCount();x++){
                piecePositions[y*handler.getFieldCount()+x]=Utility.parseInt(tokens[(y*handler.getFieldCount())+x]);
            }
        }
        
    }

    public int[] getPiecePositions() {
        return piecePositions;
    }
    
}
