/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;

/**
 *
 * @author Godievski
 */
public class GestorEnemigos extends Thread{
    public ArrayList<Enemy> listaEnemy;
    private static final int SLEEP_TIME = 15;
    private static final int NEW_ENEMY_TIME = 1500;
    private static int contador = 0;
    
    public GestorEnemigos(){
        listaEnemy = new ArrayList<>();
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if (contador % NEW_ENEMY_TIME == 0)
                    this.listaEnemy.add(new Enemy());
                
                for (int i = 0; i < listaEnemy.size(); i++){
                    Enemy enemigo = listaEnemy.get(i);
                    if (enemigo != null)
                        enemigo.posY += enemigo.vel;
                }
                sleep(SLEEP_TIME);
                contador += SLEEP_TIME;
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void dibujar(Graphics g){
        try {
            g.setColor(Color.BLACK);
            for (int i = 0; i < listaEnemy.size(); i++){
                Enemy enemy = listaEnemy.get(i);
                g.fillOval(enemy.posX, enemy.posY, enemy.width, enemy.height);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
}
