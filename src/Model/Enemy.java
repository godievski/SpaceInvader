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
    private static final double VELOCIDAD = 1;
    private static final int HP = 2;
    private final int score;
    private static final int MIN = (Enemy.WIDTH/2 + Nave.WIDTH/2);
    private static final int MAX = (Game.WINDOW_WIDTH - Enemy.WIDTH - Nave.WIDTH/2);
    
    public Enemy(){
        super(0, 0,VELOCIDAD, WIDTH, HEIGHT, HP);
        int hp_temp = (int)(Math.random()*HP) + HP;
        this.hp = hp_temp;
        this.posX = (int)(Math.random() * (Enemy.MIN - Enemy.MAX)  + Enemy.MAX);
        this.score = hp_temp*10;
    }
    public int getScore(){
        return this.score;
    }
    public void mover(){
        this.posY += VELOCIDAD;
    }
    public void dibujar(Graphics g){
        g.fillOval((int)this.posX,(int) this.posY, this.width, this.height);
    }
}
