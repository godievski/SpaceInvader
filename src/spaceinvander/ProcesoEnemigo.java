/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

/**
 *
 * @author Godievski
 */
public class ProcesoEnemigo extends Thread{
    public ArrayList<Enemy> listaEnemy;
    private static final int SLEEP_TIME = 15;
    private static final int NEW_ENEMY_TIME = 1500;
    private static int contador = 0;
    
    public ProcesoEnemigo(){
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
                    enemigo.posY += enemigo.vel;
                    if (enemigo.posY > (Game.WINDOW_HEIGHT)){
                        listaEnemy.remove(i);
                        i--;
                    }
                }
                sleep(SLEEP_TIME);
                contador += SLEEP_TIME;
            } catch (InterruptedException ex) {
                Logger.getLogger(MoverBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
