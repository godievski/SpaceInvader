/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bullet;
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
                this.procesarDisparo();
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BulletMoving.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void procesarDisparo(){
        if (Game.getMousePressed()){
            if (time_shoting_mouse % DELAY_SHOTING == 0){
                this.nave.disparar(Bullet.CLICK);
            }
            this.time_shoting_mouse += SLEEP_TIME;
        }
        else if (Game.getSpacePressed()){
            if (time_shoting_space % DELAY_SHOTING == 0){
                this.nave.disparar(Bullet.SPACE);
            }
            this.time_shoting_space += SLEEP_TIME;
        }else{
            this.time_shoting_space = 0;
        }
    }
    
}
