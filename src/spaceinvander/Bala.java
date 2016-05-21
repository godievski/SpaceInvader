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
public class Bala extends Objeto{
    
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final int VELOCIDAD = 1;
    public Bala(int x, int y, JFrame ventana){
        super(x, y,VELOCIDAD, WIDTH, HEIGHT, ventana);
    }
    
}
