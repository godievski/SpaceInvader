/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
    private Image dibujoAux;
    private Graphics gAux;
    private Dimension dimAux;
    private final Dimension dimPanel;
    
    public DrawingSpace (Game game, Dimension d){
        this.initComponents();
        this.game = game;
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
        if (!Game.getSpacePressed() && !Game.getSpecialShoot())
            Game.setMousePressed(true);
    }                                 
    private void formMouseReleased(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
        Game.setMousePressed(false);
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
        gAux.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        
        //DIBUJAR PUNTAJE
        gAux.setColor(Color.WHITE);
        gAux.drawString("SCORE: " + Game.getScore(), 10, 20);
        
        //DIBUJAR VIDA
        gAux.drawString("LIFE: " + game.getNave().getHP(), Game.WINDOW_WIDTH - 50,20);
        
        //DIBUJAR NAVE
        if (game.getNave() != null)
            game.getNave().dibujar(gAux);

        //DIBUJAR ENEMIGOS
        if (game != null)
            game.movimientoEnemigos.dibujar(gAux);

        g.drawImage(dibujoAux, 0, 0, this);
        g.dispose();
    }
}
