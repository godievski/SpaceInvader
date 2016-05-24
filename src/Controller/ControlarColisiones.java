/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bala;
import Model.Nave;
import Model.Enemy;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.Game;

/**
 *
 * @author Godievski
 */
public class ControlarColisiones extends Thread{
    private Nave nave;
    private MovimientoEnemigos enemigos;
    private static final int SLEEP_TIME = 5;
    public ControlarColisiones(Nave nave, MovimientoEnemigos enemigos){
        this.nave = nave;
        this.enemigos = enemigos;
    }
    @Override
    public void run(){
        while(true){
            //CHECAR COLISION NAVE - ENEMIGO
            for(int i = 0; i < enemigos.listaEnemy.size();i++){
                try{
                    Enemy enemy = enemigos.listaEnemy.get(i);
                    if (enemy.getPosY() > (Game.WINDOW_HEIGHT)){
                        enemigos.listaEnemy.remove(i);
                        i--;
                        continue;
                    }
                    if ( ( (nave.getPosX() + nave.getWidth()) > enemy.getPosX()) &&
                         (nave.getPosX() <= (enemy.getPosX() + enemy.getWidth() ) ) &&
                         (nave.getPosY() >= enemy.getPosY() ) && 
                         (nave.getPosY() <= (enemy.getPosY() + enemy.getHeight() ) ) ){
                        Game.modifyScore(-enemy.getScore());
                        enemigos.listaEnemy.remove(i);
                        i--;
                        if (i < 0) break;
                    }
                } catch (Exception e){
                }
            }
            
            //CHECAR COLISION BALA - ENEMIGO
            for(int i = 0; i < nave.movimientoBala.listaBalas.size(); i++){
                Bala bala = nave.movimientoBala.listaBalas.get(i);
                if (bala == null) break;
                if (bala.getPosY() < 0){
                        nave.movimientoBala.listaBalas.remove(i);
                        i--;
                        continue;
                    }
                for (int j = 0; j < enemigos.listaEnemy.size(); j++){
                    try{
                        Enemy enemy = enemigos.listaEnemy.get(j);
                        if (enemy == null) break;
                        if ((bala.getPosX() + bala.getWidth()) > enemy.getPosX() && 
                            (bala.getPosX() <= (enemy.getPosX() + enemy.getWidth())) &&
                            (bala.getPosY()) >= enemy.getPosY() && 
                            (bala.getPosY() <= (enemy.getPosY() + enemy.getHeight()))){
                            Game.modifyScore(enemy.getScore());
                            enemigos.listaEnemy.remove(j);
                            nave.movimientoBala.listaBalas.remove(i);
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
                Logger.getLogger(ControlarColisiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
