/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Godievski
 */
public class Bala extends Objeto{
    
    protected static final int WIDTH = 4;
    protected static final int HEIGHT = 8;
    private static final int VELOCIDAD = 2;
    public Bala(int x, int y){
        super(x, y,VELOCIDAD, WIDTH, HEIGHT);
    }
    
    public void dibujar(Graphics g){
        g.fillRect(this.posX, this.posY, this.width, this.height);
    }
}
