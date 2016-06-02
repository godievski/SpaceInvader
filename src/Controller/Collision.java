/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bullet;
import Model.Nave;
import Model.Enemy;
import Model.Sprite;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.WindowGame;

/**
 *
 * @author Godievski
 */
public class Collision extends Thread{
    private final Nave nave;
    private final GestorEnemigos listEnemy;
    private static final int SLEEP_TIME = 5;
    private boolean playing;
    
    public Collision(Nave nave, GestorEnemigos listEnemy){
        this.nave = nave;
        this.listEnemy = listEnemy;
        this.playing = true;
    }
    
    public void stopIt(){
        this.playing = false;
    }
    
    public boolean isPlaying(){
        return this.playing;
    }
    
    private boolean checkCollision(Sprite obj1, Sprite obj2){
        return ( obj1.getPosXInt() + obj1.getWidth() >= obj2.getPosXInt() ) &&
               ( obj1.getPosXInt() <= obj2.getPosXInt() + obj2.getWidth() ) &&
               ( obj1.getPosYInt() + obj1.getHeight() >= obj2.getPosYInt() ) &&
               ( obj1.getPosYInt() <= obj2.getPosYInt() + obj2.getHeight());
    }
    
    @Override
    public void run(){
        playing = true;
        while(playing){
            //CHECAR COLISION NAVE - ENEMIGO
            for(int i = 0; i < listEnemy.size();i++){
                try{
                    Enemy enemy = listEnemy.get(i);
                    if (enemy.getPosYInt() > (WindowGame.WINDOW_HEIGHT)){
                        listEnemy.remove(i);
                        i--;
                        nave.removeHP();
                        continue;
                    }
                    if (checkCollision(nave,enemy)){
                        nave.removeHP();
                        listEnemy.remove(i);
                        i--;    
                        if (i < 0) break;
                    }
                } catch (Exception e){
                }
            }
            
            //CHECAR COLISION BALA - ENEMIGO
            GestorBalas listaBalas = nave.getBalas();
            for(int i = 0; i < listaBalas.size(); i++){
                Bullet bala = listaBalas.get(i);
                if (bala == null) break;
                if (bala.getPosY() < 0){
                        listaBalas.remove(i);
                        i--;
                        continue;
                    }
                for (int j = 0; j < listEnemy.size(); j++){
                    try{
                        Enemy enemy = listEnemy.get(j);
                        if (enemy == null) break;
                        if (checkCollision(bala,enemy)){
                            enemy.removeHP();
                            if (enemy.getHP() <= 0){
                                nave.incrementScore(enemy.getScore());
                                listEnemy.remove(j);
                                j--;
                            }
                            listaBalas.remove(i);
                            i--;
                            if (i < 0) break;
                        }
                    } catch (Exception e){
                        
                    }
                }
            }
            try {
                sleep(SLEEP_TIME);
                //Ventana.puntaje += 1;
            } catch (InterruptedException ex) {
                Logger.getLogger(Collision.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
