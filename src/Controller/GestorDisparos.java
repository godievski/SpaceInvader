/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Nave;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.Game;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;

/**
 *
 * @author USUARIO
 */
public class GestorDisparos extends Thread{
    private final static int SLEEP_TIME = 10;
    private final static int DELAY_SHOTING = 150;
    private int time_shoting;
    private final Game game;
    private final Nave nave;
    public GestorDisparos(Game game){
        this.time_shoting = 0;
        this.game = game;
        this.nave = game.getNave();
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if (game.getDisparando()){
                    if (time_shoting % DELAY_SHOTING == 0){
                        this.nave.disparar();
                    }
                    this.time_shoting += SLEEP_TIME;
                }else{
                    this.time_shoting = 0;
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovimientoBalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
