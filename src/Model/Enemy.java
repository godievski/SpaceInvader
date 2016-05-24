/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.JFrame;
import View.Game;

/**
 *
 * @author Godievski
 */
public class Enemy extends Objeto {
    
    private static final int WIDTH = 18;
    private static final int HEIGHT = 18;
    private static final int VELOCIDAD = 1;
    private final int score;
    private static final int MIN = (Enemy.WIDTH/2 + Nave.WIDTH/2);
    private static final int MAX = (Game.WINDOW_WIDTH - Enemy.WIDTH - Nave.WIDTH/2);
    
    public Enemy(){
        super(0, 0,VELOCIDAD, WIDTH, HEIGHT);
        this.posX = (int)(Math.random() * (Enemy.MIN - Enemy.MAX)  + Enemy.MAX);
        this.score = 10;
    }
    public int getScore(){
        return this.score;
    }
    public void mover(){
        this.posY += VELOCIDAD;
    }
}
