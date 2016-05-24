/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Godievski
 */
public class Bala extends Objeto{
    
    public static final int WIDTH = 5;
    public static final int HEIGHT = 7;
    private static final int VELOCIDAD = 2;
    private int tipo;
    
    private static final int SPACE = 0;
    private static final int CLICK = 1;
    public Bala(int x, int y){
        super(x, y,VELOCIDAD, WIDTH, HEIGHT);
        tipo = SPACE;
    }
    
    public void mover(){
        if (tipo == SPACE)
            this.posY -= this.vel;
        else if (tipo == CLICK){
            //
        }
    }
    
    public void dibujar(Graphics g){
        g.fillRect(this.posX, this.posY, this.width, this.height);
    }
}
