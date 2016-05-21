/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Godievski
 */
public class Nave extends Objeto{
    
    private static final int POSX_INI = 240;
    private static final int POSY_INI = 450;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int VELOCIDAD = 10;
    
    public final MoverBalas movimientoBala;
    
    public Nave(JFrame ventana){
        super(POSX_INI, POSY_INI, VELOCIDAD, WIDTH, HEIGHT, ventana);
        movimientoBala = new MoverBalas();
        movimientoBala.start();
    }
    
    public void mover(int dir){
        if (dir == KeyEvent.VK_LEFT){
            if (this.posX >= WIDTH/2)
                this.posX -= this.vel;
        } else if (dir == KeyEvent.VK_RIGHT){
            if (this.posX <= (500 - 3*WIDTH/2 ))
                this.posX += this.vel;
            
        }
    }
    
    public void dibujar(){
        BufferStrategy buff = ventana.getBufferStrategy();
        Graphics g = null;
        try {
            g = buff.getDrawGraphics();
            g.setColor(Color.CYAN);
            g.fillRect(posX, posY, width, height);
        
            //DIBUJAR BALAS
            g.setColor(Color.WHITE);
            for (int i = 0; i < movimientoBala.listaBalas.size(); i++){
                Bala bala = this.movimientoBala.listaBalas.get(i);
                g.fillRect(bala.posX, bala.posY, bala.width, bala.height);
            }
        } finally{
            g.dispose();
        }
    }
    public void disparar(){
        this.movimientoBala.listaBalas.add(new Bala(this.posX + 5,this.posY - 8,null));
    }
}
