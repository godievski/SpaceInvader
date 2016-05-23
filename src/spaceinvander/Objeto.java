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
public class Objeto {
    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected int vel;

    public Objeto(int x, int y, int vel, int width, int height){
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
    public void setPOsY(int value){
        this.posY = value;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
}
