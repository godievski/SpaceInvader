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
    private double vectorX;
    private double vectorY;
    
    public static final int SPACE = 0;
    public static final int CLICK = 1;
    public Bala(int x, int y){
        super(x, y,VELOCIDAD, WIDTH, HEIGHT);
        tipo = SPACE;
    }
    public Bala(int x, int y, int tipo, double mouseX, double mouseY){
        super(x, y,VELOCIDAD, WIDTH, HEIGHT);
        this.posXDouble = x;
        this.posYDouble = y;
        this.tipo = tipo;
        calcularVector(mouseX,mouseY);
    }
    
    public void mover(){
        if (tipo == SPACE)
            this.posY -= this.vel;
        else if (tipo == CLICK){
            this.posYDouble += this.vectorY;
            this.posXDouble += this.vectorX;
            this.posX = (int) this.posXDouble;
            this.posY = (int) this.posYDouble;
        }
    }
    private void calcularVector(double xFinal, double yFinal){
        double y = yFinal - this.posY;
        double x = xFinal - this.posX;
        double r = Math.sqrt(y*y + x*x);
        y = y/r;
        x = x/r;
        this.vectorX = x;
        this.vectorY = y;
    }
    public void dibujar(Graphics g){
        g.fillRect(this.posX, this.posY, this.width, this.height);
    }
}
