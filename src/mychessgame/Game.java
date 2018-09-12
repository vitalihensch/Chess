/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import mychessgame.gfx.display.Display;
import mychessgame.input.MouseManager;
import mychessgame.states.*;
import mychessgame.textureManager.Assets;

/**
 *
 * @author home
 */
public class Game implements Runnable{
   //variables
    private final int FIELDHIGHT=80,FIELDWIDTH=80;
    private final int BOARDSIZE=8;//chessboard is 8 x 8 fields
    private int width,height;
    private boolean running;
    private Thread thread;//game starts on extra thread
    private Display display;//outer window
    private String title;
    private Graphics g;
    private BufferStrategy bufferStrategy;
    private Handler handler;
    //states
    GameState gameState;
    MenuState menuState;
    //input managers
    private MouseManager mouseManager;
    //optionaly GameCam
   
    //construct
    public Game(String title,int width,int height){
       this.width=width;
       this.height=height;
       this.title=title;
       running=false;
       mouseManager=new MouseManager();
       
   }
    
   //methods
    @Override
    public void run(){
        init();
        float framesPerSecond=100000;
        float accumulatedTime=0;
        long lastTime=System.nanoTime();
        long currentTime=0;
        while(running){
            if(accumulatedTime>=framesPerSecond){
            tick();
            render();
            
            accumulatedTime=0;
            
            }
            currentTime=System.nanoTime();
            accumulatedTime+=(currentTime-lastTime);
            lastTime=currentTime;
           
        }
        Stop();
    }
   public void Start(){//starts the game on separate Thread
       if(running==false){
           running=true;
           thread=new Thread(this);
           thread.start();
       }
       else 
           return ;
   }
   public synchronized void Stop(){
    try{
        thread.join();
    }
    catch(InterruptedException e){
        e.printStackTrace();
    }
   }
   public void init(){
     display=new Display(title,width,height);
     handler=new Handler(this);
     //addition of mousemanager to frame and canvas
    display.getFrame().addMouseListener(mouseManager);
    display.getFrame().addMouseMotionListener(mouseManager);
    display.getCanvas().addMouseListener(mouseManager);
    display.getCanvas().addMouseMotionListener(mouseManager);
     //
     Assets.init();
     
     
     
     gameState=new GameState(handler);
     menuState=new MenuState(handler);
     //loading textures into memory
     
     
     State.setState(menuState);//code needs change later
   }
   public void tick(){
      if(State.getState()!=null)
          State.getState().tick();
   }
   public void render(){
      bufferStrategy=display.getCanvas().getBufferStrategy();
      if(bufferStrategy==null){
          display.getCanvas().createBufferStrategy(3);
          return;
      }
      //geting graphics obj
      g=bufferStrategy.getDrawGraphics();
      //clearing screen
      g.clearRect(0,0,width,height);
      //rendering of all objects ot the set State or lvl of the game;
      
        State.getState().render(g);
     
      //end draw
      bufferStrategy.show();
      g.dispose();   
   }
   //geter and seter

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFIELDHIGHT() {
        return FIELDHIGHT;
    }

    public int getFIELDWIDTH() {
        return FIELDWIDTH;
    }

    public int getBOARDSIZE() {
        return BOARDSIZE;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public MenuState getMenuState() {
        return menuState;
    }
   
}
