/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BulletMoving;
import Controller.Collision;
import Controller.EnemyMoving;
import Controller.GestorEnemigos;
import Controller.GestorNave;
import Model.Nave;

/**
 *
 * @author Godievski
 */
public class Game {
    //GAME WINDOW
    WindowGame wg;
    
    //OBJETOS
    private final GestorNave naves;
    private final GestorEnemigos enemies;
    
    //THREADS
    protected EnemyMoving movimientoEnemigos;
    protected BulletMoving movimientoBalas;
    protected Collision controladorColisiones;
    
    public Game(WindowGame wg){
        //OBJETOS DEL JUEGO
        
        this.naves = new GestorNave();
        this.enemies = new GestorEnemigos();
        this.movimientoEnemigos = null;
        this.movimientoBalas = null;
        this.controladorColisiones = null;
        this.wg = wg;
    }

    public void joinGame(Nave nave){
        this.naves.add(nave);
    }
    
    public GestorNave getNaves(){
        return this.naves;
    }
    
    public GestorEnemigos getEnemies(){
        return this.enemies;
    }
}
