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
public abstract class Sprite {
    protected int posX;
    protected int posY;
    protected double posXDouble;
    protected double posYDouble;
    protected int width;
    protected int height;
    protected int vel;

    public Sprite(int x, int y, int vel, int width, int height){
        this.posX = x;
        this.posY = y;
        this.vel = vel;
        this.width = width;
        this.height = height;
    }
    public int getPosX(){
        return this.posX;
    }
    public void setPosX(int value){
        this.posX = value;
    }
    public int getPosY(){
        return this.posY;
    }
    public void setPosY(int value){
        this.posY = value;
    }
    public double getPosXDouble(){
        return this.posXDouble;
    }
    public void setPosXDouble(double value){
        this.posXDouble = value;
    }
    public double getPosYDouble(){
        return this.posXDouble;
    }
    public void setPosYDouble(double value){
        this.posYDouble = value;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    abstract public void dibujar(Graphics g);
}
