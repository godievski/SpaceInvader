/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Game;
import java.awt.Graphics;

/**
 *
 * @author Godievski
 */
public class Enemy extends Sprite {
    
    private static final int WIDTH = 18;
    private static final int HEIGHT = 18;
    private static final double VELOCIDAD = 1.5;
    private static final int HP = 3;
    private static final int TIPO_RANDOM = 1;
    private static final int TIPO_NORMAL = 0;
    
    private final int score;
    private static final int MIN = (Enemy.WIDTH/2 + Nave.WIDTH/2);
    private static final int MAX = (Game.WINDOW_WIDTH - Enemy.WIDTH - Nave.WIDTH/2);
    private int tipo;
    
    public Enemy(){
        super(0, 0,VELOCIDAD, WIDTH, HEIGHT, HP);
        int hp_temp = (int)(Math.random()*HP) + HP;
        this.hp = hp_temp;
        this.posX = (int)(Math.random() * (Enemy.MIN - Enemy.MAX)  + Enemy.MAX);
        this.score = hp_temp*10;
        this.tipo = (int)(Math.random()*10000 / 6001);
    }
    
    public int getScore(){
        return this.score;
    }
    public void mover(Nave nave){
        if (tipo == TIPO_NORMAL)
            this.posY += VELOCIDAD;
        else if (tipo == TIPO_RANDOM)
            this.specialMove(nave);
    }
    
    public void setTipo(int tipo){
        this.tipo = tipo;
    }
    
    public void specialMove(Nave nave){
        this.calcularVector(nave.getPosX(), nave.getPosY());
        this.posY += this.vectorY;
        this.posX += this.vectorX;
    }
    
    public void dibujar(Graphics g){
        g.fillOval((int)this.posX,(int) this.posY, this.width, this.height);
    }
}
