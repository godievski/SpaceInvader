/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.GestorBalas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import View.Game;
import java.awt.MouseInfo;
import java.awt.Point;

/**
 *
 * @author Godievski
 */
public class Nave extends Sprite{
    
    protected static final int WIDTH = 20;
    protected static final int HEIGHT = 20;
    private static final double VELOCIDAD = 2.2;
    private static final int HP = 4;
    public static final int POSX_INI = (int)((Game.WINDOW_WIDTH - WIDTH)/2);
    public static final int POSY_INI = Game.WINDOW_HEIGHT - HEIGHT - 20;
   
    private final GestorBalas balas;
    
    public Nave(){
        super(POSX_INI, POSY_INI, VELOCIDAD, Nave.WIDTH, Nave.HEIGHT, Nave.HP);
        this.balas = new GestorBalas();
    }
    
    public GestorBalas getBalas(){
        return this.balas;
    }
    
    public void mover(boolean left, boolean right, boolean up, boolean down){
        double x=0, y=0;
        if (left) x -=1 ;
        if (right) x +=1;
        if (up) y-=1;
        if (down) y+=1;
        double r = Math.sqrt(y*y + x*x);
        y = y*VELOCIDAD;
        x = x*VELOCIDAD;
        if (r != 0){
            y /= r;
            x /= r;
        }
        if (left)
            if (this.posX >= 3*VELOCIDAD)
                this.posX += x;
        if (right)
            if (this.posX <= ((Game.WINDOW_WIDTH) - (int)(Nave.WIDTH) - 3*VELOCIDAD))
                this.posX += x;   
        if (up)
            if (this.posY >= Game.WINDOW_HEIGHT/2)
                this.posY += y;
        if (down)
            if (this.posY <= POSY_INI)
                this.posY += y;
    }
    
    public void dibujar(Graphics g){
        try {
            //DIBUJAR NAVE
            g.setColor(Color.CYAN);
            g.fillRect((int)posX,(int) posY, WIDTH, HEIGHT);
            
            //DIBUJAR BALAS
            balas.dibujar(g);
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public void disparar(int tipo){
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        int mouseX = (int)( mousePoint.getX() - Game.getMyPosition().getX() );
        int mouseY = (int)( mousePoint.getY() - Game.getMyPosition().getY() );
        if (mouseY < this.posY){
            balas.add(new Bullet((int)(this.posX) + this.width/2 - Bullet.WIDTH/2,
                (int)(this.posY) - Bullet.HEIGHT,
                tipo,mouseX,mouseY));
        }
    }
}
