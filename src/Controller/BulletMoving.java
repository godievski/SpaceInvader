/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bullet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godievski
 */
public class BulletMoving extends Thread{
    
    public GestorBalas listaBalas;
    private static final int SLEEP_TIME = 5;
    
    public BulletMoving(GestorBalas listaBalas){
        this.listaBalas = listaBalas;
    }
    @Override
    public void run() {
        while(true){
            try {
                for (int i = 0; i < listaBalas.size(); i++){
                    Bullet bala = listaBalas.get(i);
                    if (bala != null)
                        bala.mover();
                }   
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BulletMoving.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
