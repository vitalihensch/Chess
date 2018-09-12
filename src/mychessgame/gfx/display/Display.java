/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychessgame.gfx.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author home
 */
public class Display {
    private JFrame frame;//outer window part
    private Canvas canvas;//the real Drawing place
    private Dimension canvSetings;
    
    
    private String gameFrameTitle;
    private int width, height;
    //contruct
    public Display(String gameFrameTitle,int width, int height){
        this.width=width;
        this.height=height;
        this.gameFrameTitle=gameFrameTitle;
        canvSetings=new Dimension(width,height);
        CreateDisplay();
        CreateCanvas();
        frame.add(canvas);
    }
    private void CreateDisplay(){
        frame=new JFrame(gameFrameTitle);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);//if rezizeble might give problems with game grafix
        frame.setLocationRelativeTo(null);//no parent might need a change in the future
        
    }
    private void CreateCanvas(){
       canvas=new Canvas();
       canvas.setPreferredSize(canvSetings);
       canvas.setMaximumSize(canvSetings);
       canvas.setMaximumSize(canvSetings);
       canvas.setFocusable(false);
       
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
}
