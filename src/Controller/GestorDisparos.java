/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bala;
import Model.Nave;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.Game;
import static java.lang.Thread.sleep;

/**
 *
 * @author USUARIO
 */
public class GestorDisparos extends Thread{
    private final static int SLEEP_TIME = 10;
    private final static int DELAY_SHOTING = 150;
    private int time_shoting_space;
    private int time_shoting_mouse;
    private final Game game;
    private final Nave nave;
  
    public GestorDisparos(Game game){
        this.time_shoting_mouse = time_shoting_space = 0;
        this.game = game;
        this.nave = game.getNave();
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if (game.getMousePressed()){
                    if (time_shoting_mouse % DELAY_SHOTING == 0){
                        this.nave.disparar(Bala.CLICK);
                    }
                    this.time_shoting_mouse += SLEEP_TIME;
                }else{
                    this.time_shoting_mouse = 0;
                } 
                /*ORIGINAL*/
                if (game.getDisparando()){
                    if (time_shoting_space % DELAY_SHOTING == 0){
                        this.nave.disparar(Bala.SPACE);
                    }
                    this.time_shoting_space += SLEEP_TIME;
                }else{
                    this.time_shoting_space = 0;
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovimientoBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
