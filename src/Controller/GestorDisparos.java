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
import View.WindowGame;
import static java.lang.Thread.sleep;

/**
 *
 * @author USUARIO
 */
public class GestorDisparos extends Thread{
    private final static int SLEEP_TIME = 10;
    private final static int DELAY_SHOTING = 150;
    private int time_shoting;
    private int time_special;
    private final WindowGame game;
    private final Nave nave;
    private boolean playing;
  
    public GestorDisparos(WindowGame game){
        this.time_shoting = 0;
        this.game = game;
        this.nave = game.getNave();
        this.playing = true;
    }
    
    public void stopIt(){
        this.playing = false;
    }
    
    public boolean isPlaying(){
        return this.playing;
    }
    
    @Override
    public void run() {
        playing = true;
        while(playing){
            try {
                this.procesarDisparo();
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BulletMoving.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void procesarDisparo(){
        if (WindowGame.getSpecialShoot()){
            if (time_special % DELAY_SHOTING == 0){
                this.nave.special();
            }
            this.time_special += SLEEP_TIME;
        }
        else if (WindowGame.getMousePressed()){
            if (time_special % DELAY_SHOTING == 0){
                this.nave.disparar(Bullet.CLICK);
            }
            this.time_special += SLEEP_TIME;
        }
        else if (WindowGame.getSpacePressed()){
            if (time_special % DELAY_SHOTING == 0){
                this.nave.disparar(Bullet.SPACE);
            }
            this.time_special += SLEEP_TIME;
        }else{
            this.time_special = 0;
        }
    }
}
