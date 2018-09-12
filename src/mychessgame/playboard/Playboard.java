/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.playboard;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import mychessgame.Handler;


public class Playboard {
    Handler handler;
    Field[][] fields;
   
    
    
    
    public Playboard(Handler handler){
        this.handler=handler;
        fields=new Field[handler.getFieldCount()][handler.getFieldCount()];
        init();
    }
//init method set field sizes and color also symbolizes the click box
private void init(){
    handler.setPlayboard(this);
    for(int x=0;x<handler.getFieldCount();x++){
           for(int y=0;y<handler.getFieldCount();y++){
               if((x+y)%2==1)
                   fields[x][y]=new Field(x*handler.getFieldWidth(),y*handler.getFieldHeight(),handler.getFieldWidth(),handler.getFieldHeight(),Color.BLUE);
               else
                   fields[x][y]=new Field(x*handler.getFieldWidth(),y*handler.getFieldHeight(),handler.getFieldWidth(),handler.getFieldHeight(),Color.GRAY);
               }
           
            } 
}   

private void renderBorders(Graphics g){
    g.setColor(Color.BLUE);
    
    char[]  rightNumbers={'8','7','6','5','4','3','2','1'};
    char[]  lowLetters={'A','B','C','D','E','F','G','H'};
    
//right border
    g.fillRect(handler.getFieldCount()*handler.getFieldWidth(), 0, (int)handler.getFieldWidth()/2, handler.getFieldCount()*handler.getFieldHeight());
    
    g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    g.setColor(Color.BLACK);
    for(int i=0;i<rightNumbers.length;i++)//numbers on the border
    g.drawChars(rightNumbers, i, 1, handler.getFieldWidth()*handler.getFieldCount()+handler.getFieldWidth()/8, ((int)handler.getFieldHeight()*3/4)+handler.getFieldHeight()*i);
     
    
//lower border
    g.setColor(Color.BLUE);
    g.fillRect(0, handler.getFieldCount()*handler.getFieldHeight(), handler.getFieldCount()*handler.getFieldHeight()+((int)handler.getFieldHeight()/2), ((int)handler.getFieldHeight()/2));
    g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    g.setColor(Color.BLACK);
    for(int i=0;i<lowLetters.length;i++)//numbers on the border
    g.drawChars(lowLetters, i, 1, handler.getFieldHeight()/4 + handler.getFieldWidth()*i, handler.getFieldHeight()*handler.getFieldCount()+handler.getFieldHeight()/2  );
    
}




//rendering gamefield
public void render(Graphics g){
       for(int x=0;x<handler.getFieldCount();x++){
           for(int y=0;y<handler.getFieldCount();y++){
             fields[x][y].render(g);   
        }
       }
       renderBorders(g);
      
}        
  
    public void tick(){
           
       for(int x=0;x<handler.getFieldCount();x++){
           for(int y=0;y<handler.getFieldCount();y++){
             fields[x][y].tick();   
        }
       }
                 
               
        }        

    public Field[][] getFields() {
        return fields;
    }
    
   

  
}
