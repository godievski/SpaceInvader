/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godievski
 */
public class GestorBalas extends Thread{
    
    public ArrayList<Bala> listaBalas;
    private static final int SLEEP_TIME = 5;
    
    public GestorBalas(){
        listaBalas = new ArrayList<>();
    }
    @Override
    public void run() {
        while(true){
            try {
                for (int i = 0; i < listaBalas.size(); i++){
                    Bala bala = listaBalas.get(i);
                    if (bala != null)
                        bala.mover();
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
