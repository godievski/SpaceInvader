/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Enemy;
import Model.Nave;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static java.lang.Thread.sleep;

/**
 *
 * @author Godievski
 */
public class EnemyMoving extends Thread{
    public GestorEnemigos listaEnemy;
    private static final int SLEEP_TIME = 15;
    private static final int NEW_ENEMY_TIME = 900;
    private static int contador = 0;
    private boolean playing;
    private Nave nave;
    
    public EnemyMoving(GestorEnemigos listEnemy, Nave nave){
        this.listaEnemy = listEnemy;
        this.playing = true;
        this.nave = nave;
    }
    
    
    public void stopIt(){
        this.playing = false;
    }
    
    public boolean isPlaying(){
        return this.playing;
    }
    
    @Override
    public void run() {
        playing = true;
        while(playing){
            try {
                if (contador % NEW_ENEMY_TIME == 0){
                    Enemy enemigo = new Enemy();
                    this.listaEnemy.add(enemigo);
                }

                for (int i = 0; i < listaEnemy.size(); i++){
                    Enemy enemigo = listaEnemy.get(i);
                    if (enemigo != null){
                            enemigo.mover(nave);
                    }
                        
                }
                sleep(SLEEP_TIME);
                contador += SLEEP_TIME;
            } catch (InterruptedException ex) {
                Logger.getLogger(BulletMoving.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void dibujar(Graphics g){
        try {
            g.setColor(Color.BLACK);
            for (int i = 0; i < listaEnemy.size(); i++){
                Enemy enemy = listaEnemy.get(i);
                enemy.dibujar(g);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
}
