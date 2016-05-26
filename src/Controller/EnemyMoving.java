/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Enemy;
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
    private static final int NEW_ENEMY_TIME = 1500;
    private static int contador = 0;
    
    public EnemyMoving(GestorEnemigos listEnemy){
        this.listaEnemy = listEnemy;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if (contador % NEW_ENEMY_TIME == 0)
                    this.listaEnemy.add(new Enemy());
                
                for (int i = 0; i < listaEnemy.size(); i++){
                    Enemy enemigo = listaEnemy.get(i);
                    if (enemigo != null){
                        enemigo.mover();
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
                g.fillOval(enemy.getPosX(), enemy.getPosY(),
                           enemy.getWidth(), enemy.getHeight());
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
}
