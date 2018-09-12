
package mychessgame.gfx.display.menu;

import java.awt.Graphics;
import mychessgame.Handler;
import mychessgame.states.State;
import mychessgame.textureManager.Assets;


public class MenuGUI {
    private Handler handler;
    private final int buttonWidth=150,buttonHeight=100;
    private StartButton startButton;
    private Button optionButton;
    public MenuGUI(Handler handler){
        this.handler=handler;
       startButton=new StartButton(Assets.bStart,300,100,buttonWidth,buttonHeight);
       optionButton=new Button(Assets.bOptions,0,buttonHeight,buttonWidth,buttonHeight);
   } 
    public void render(Graphics g){
      startButton.render(g);
      optionButton.render(g);
    }
    public void tick(){
       if(startButton.getHitbox().contains(handler.getGame().getMouseManager().getMouseX(),handler.getGame().getMouseManager().getMouseY())&&handler.getGame().getMouseManager().isLeftPressed())
           State.setState(handler.getGame().getGameState());
      
    }
    
}
