/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Nave;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author USUARIO
 */
public class DrawingSpace extends Canvas {
    private final Game game;
    private final Nave nave;
    private Image dibujoAux;
    private Graphics gAux;
    private Dimension dimAux;
    private final Dimension dimPanel;
    
    public DrawingSpace (Game game, Nave nave, Dimension d){
        this.initComponents();
        this.game = game;
        this.nave = nave;
        this.setSize(d);
        dimPanel = d;
    }
    private void initComponents(){
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
    }
    
    private void formMousePressed(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
        if (!WindowGame.getSpacePressed() && !WindowGame.getSpecialShoot())
            WindowGame.setMousePressed(true);
    }                                 
    private void formMouseReleased(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
        WindowGame.setMousePressed(false);
    }  
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    @Override
    public void paint(Graphics g){
        if (gAux == null || dimAux == null || dimPanel.width != dimAux.width ||
                dimPanel.height != dimAux.height){
            dimAux = dimPanel;
            dibujoAux = createImage(dimAux.width,dimAux.height);
            gAux = dibujoAux.getGraphics();
        }

        //FONDO
        gAux.setColor(Color.MAGENTA);
        gAux.fillRect(0, 0, WindowGame.WINDOW_WIDTH, WindowGame.WINDOW_HEIGHT);
        
        if (game.getNaves() != null){
            //DIBUJAR PUNTAJE
            gAux.setColor(Color.WHITE);
            gAux.drawString("SCORE: " + this.nave.getScore(), 10, 20);
            
            //DIBUJAR VIDA
            gAux.drawString("LIFE: " + this.nave.getHP(), WindowGame.WINDOW_WIDTH - 50,20);
            
            //DIBUJAR NAVE
            game.getNaves().dibujar(gAux);
        }
            
        //DIBUJAR ENEMIGOS
        if (game != null && game.movimientoEnemigos != null){
            game.movimientoEnemigos.dibujar(gAux);
        }

        g.drawImage(dibujoAux, 0, 0, this);
        g.dispose();
    }
}
