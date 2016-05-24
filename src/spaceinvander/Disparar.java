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

/**
 *
 * @author USUARIO
 */
public class Disparar extends Thread{
    private final static int SLEEP_TIME = 10;
    private final static int DELAY_SHOTING = 150;
    private int time_shoting;
    private Game game;
    public Disparar(Game game){
        this.time_shoting = 0;
        this.game = game;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if (game.disparando){
                    if (time_shoting % DELAY_SHOTING == 0){
                        game.nave.disparar();
                    }
                    this.time_shoting += SLEEP_TIME;
                }else{
                    this.time_shoting = 0;
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
