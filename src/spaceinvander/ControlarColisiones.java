/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godievski
 */
public class ControlarColisiones extends Thread{
    private Nave nave;
    private ProcesoEnemigo enemigos;
    public ControlarColisiones(Nave nave, ProcesoEnemigo enemigos){
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
                    if ((nave.posX + nave.width) > enemy.posX && (nave.posX <= (enemy.posX + enemy.width)) &&
                        (nave.posY) >= enemy.posY && (nave.posY <= (enemy.posY + enemy.height))){
                        Game.score -= enemy.getScore();
                        enemigos.listaEnemy.remove(i);
                        i--;
                        if (i < 0) break;
                    }
                } catch (Exception e){
                }
            }
            
            //CHECAR COLISION BALA - ENEMIGO
            for(int i = 0; i < nave.movimientoBala.listaBalas.size(); i++){
                for (int j = 0; j < enemigos.listaEnemy.size(); j++){
                    try{
                        Bala bala = nave.movimientoBala.listaBalas.get(i);
                        Enemy enemy = enemigos.listaEnemy.get(j);
                        if (bala == null || enemy == null) break;
                        if ((bala.posX + bala.width) > enemy.posX && (bala.posX <= (enemy.posX + enemy.width)) &&
                            (bala.posY) >= enemy.posY && (bala.posY <= (enemy.posY + enemy.height))){
                            Game.score += enemy.getScore();
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
                sleep(2);
                //Ventana.puntaje += 1;
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlarColisiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
