/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.textureManager;

import java.awt.image.BufferedImage;

/**
 *
 * @author home
 */
public class Assets {
    
    private static final int width=45,height=45,bWidth=150,bHeight=100;//Figuresize
    public static BufferedImage wPawn,wKnight,wBishop,wRook,wQueen,wKing
                                ,bPawn,bKnight,bBishop,bRook,bQueen,bKing,
            
                                bStart,bOptions,bGreen,bRed;
    
    
    
    public static void init(){
         SpriteSheet sheet=new SpriteSheet(ImageLoader.loadImage("/textureManager/textures/Chess_Pieces_Sprite.png"));
         SpriteSheet guiSheet= new SpriteSheet(ImageLoader.loadImage("/textureManager/textures/buttons.png"));
         wKing=sheet.crop(0, 0, width, height);
         wQueen=sheet.crop(width,0 , width, height);
         wBishop=sheet.crop(width*2, 0, width, height);
         wKnight=sheet.crop(width*3, 0, width, height);
         wRook=sheet.crop(width*4, 0, width, height);
         wPawn=sheet.crop(width*5, 0, width, height);
         
         bKing=sheet.crop(0, height, width, height);
         bQueen=sheet.crop(width,height , width, height);
         bBishop=sheet.crop(width*2, height, width, height);
         bKnight=sheet.crop(width*3, height, width, height);
         bRook=sheet.crop(width*4, height, width, height);
         bPawn=sheet.crop(width*5, height, width, height);
         
         bStart=guiSheet.crop(0, 0, bWidth, bHeight);
         bGreen=guiSheet.crop(bWidth, 0, bWidth, bHeight);
         bOptions=guiSheet.crop(0,bHeight , bWidth, bHeight);
         bRed=guiSheet.crop(bHeight, bWidth, bWidth, bHeight);
    }
   
}
