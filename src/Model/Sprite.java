/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics;

/**
 *
 * @author Godievski
 */
public abstract class Sprite {
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;
    protected double vel;
    protected int hp;

    public Sprite(int x, int y, double vel, int width, int height, int hp){
        this.posX = x;
        this.posY = y;
        this.vel = vel;
        this.width = width;
        this.height = height;
        this.hp = hp;
    }
    
    public int getHP(){
        return this.hp;
    }
    public void setHP(int value){
        this.hp = value;
    }
    public void removeHP(){
        this.hp -= 1;
    }
    public double getPosX(){
        return this.posX;
    }
    public int getPosXInt(){
        return (int) this.posX;
    }
    public void setPosX(double value){
        this.posX = value;
    }
    public double getPosY(){
        return this.posX;
    }
    public int getPosYInt(){
        return (int)this.posY;
    }
    public void setPosY(double value){
        this.posY = value;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    abstract public void dibujar(Graphics g);
}
