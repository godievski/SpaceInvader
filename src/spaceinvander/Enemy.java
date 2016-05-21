/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import javax.swing.JFrame;

/**
 *
 * @author Godievski
 */
public class Enemy extends Objeto {
    
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;
    private static final int VELOCIDAD = 6;
    
    public Enemy(JFrame ventana){
        super(0, 0,VELOCIDAD, WIDTH, HEIGHT, ventana);
        this.posX = (int)(WIDTH/2) + (int)(Math.random() * (500 - (int)(3*WIDTH/2)) ); ; //RANDOM
    }
    
}
