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
    private static final int sleepTime = 50;
    private static final int intervalo = 700;
    private static int contador = 0;
    
    public ProcesoEnemigo(){
        listaEnemy = new ArrayList<>();
    }
    public void run() {
        while(true){
            try {
                if (contador % intervalo == 0)
                    this.listaEnemy.add(new Enemy(null));
                
                for (int i = 0; i < listaEnemy.size(); i++){
                    Enemy enemigo = listaEnemy.get(i);
                    enemigo.posY += enemigo.vel;
                    if (enemigo.posY > 500){
                        listaEnemy.remove(i);
                        i--;
                    }
                }
                sleep(sleepTime);
                contador += sleepTime;
            } catch (InterruptedException ex) {
                Logger.getLogger(MoverBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
