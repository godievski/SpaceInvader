/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bullet;
import Model.Nave;
import Model.Enemy;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.Game;

/**
 *
 * @author Godievski
 */
public class Collision extends Thread{
    private final Nave nave;
    private final GestorEnemigos listEnemy;
    private static final int SLEEP_TIME = 5;
    public Collision(Nave nave, GestorEnemigos listEnemy){
        this.nave = nave;
        this.listEnemy = listEnemy;
    }
    @Override
    public void run(){
        while(true){
            //CHECAR COLISION NAVE - ENEMIGO
            for(int i = 0; i < listEnemy.size();i++){
                try{
                    Enemy enemy = listEnemy.get(i);
                    if (enemy.getPosYInt() > (Game.WINDOW_HEIGHT)){
                        listEnemy.remove(i);
                        i--;
                        continue;
                    }
                    if ( ( (nave.getPosXInt() + nave.getWidth()) > enemy.getPosXInt()) &&
                         (nave.getPosXInt() <= (enemy.getPosXInt() + enemy.getWidth() ) ) &&
                         (nave.getPosYInt() >= enemy.getPosYInt() ) && 
                         (nave.getPosYInt() <= (enemy.getPosYInt() + enemy.getHeight() ) ) ){
                        Game.modifyScore(-enemy.getScore());
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
                        if ((bala.getPosXInt() + bala.getWidth()) > enemy.getPosXInt() && 
                            (bala.getPosXInt() <= (enemy.getPosXInt() + enemy.getWidth())) &&
                            (bala.getPosYInt()) >= enemy.getPosYInt() && 
                            (bala.getPosYInt() <= (enemy.getPosYInt() + enemy.getHeight()))){
                            Game.modifyScore(enemy.getScore());
                            listEnemy.remove(j);
                            listaBalas.remove(i);
                            i--;
                            j--;
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
