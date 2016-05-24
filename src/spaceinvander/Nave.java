/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Godievski
 */
public class Nave extends Objeto{
    
    protected static final int WIDTH = 20;
    protected static final int HEIGHT = 20;
    private static final int VELOCIDAD = 2;
    private static final int POSX_INI = (int)((Game.WINDOW_WIDTH - WIDTH)/2);
    private static final int POSY_INI = Game.WINDOW_HEIGHT - HEIGHT - 20;
    
    public final GestorBalas movimientoBala;
    
    public Nave(){
        super(POSX_INI, POSY_INI, VELOCIDAD, Nave.WIDTH, Nave.HEIGHT);
        movimientoBala = new GestorBalas();
        movimientoBala.start();
    }
    
    public void mover(int dir){
        if (dir == KeyEvent.VK_LEFT){
            if (this.posX >= VELOCIDAD)
                this.posX -= this.vel;
        } else if (dir == KeyEvent.VK_RIGHT){
            if (this.posX <= ((Game.WINDOW_WIDTH) - (int)(Nave.WIDTH) - 6))
                this.posX += this.vel;   
        }
    }
    
    public void dibujar(Graphics g){
        try {
            //DIBUJAR NAVE
            
            g.setColor(Color.CYAN);
            g.fillRect(posX, posY, WIDTH, HEIGHT);
            
            //DIBUJAR BALAS
            g.setColor(Color.WHITE);
            for (int i = 0; i < movimientoBala.listaBalas.size(); i++){
                Bala bala = this.movimientoBala.listaBalas.get(i);
                bala.dibujar(g);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    public void disparar(){
        this.movimientoBala.listaBalas.add(new Bala(this.posX + this.width/2 - Bala.WIDTH/2,this.posY - Bala.HEIGHT));
    }
}
