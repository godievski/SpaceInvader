/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Godievski
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    private Nave nave;
    private int keyPressed;
    private ProcesoEnemigo enemigos;
    private ControlarColisiones controladorColisiones;
    protected static int puntaje;
    
    
    public Ventana() {
        initComponents();
        this.setBounds(500, 100, 500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.createBufferStrategy(4);
        this.setTitle("SpaceInvader by Godievski");
        //CENTRADO NAVE
        nave = new Nave(this);
        this.enemigos = new ProcesoEnemigo();
        this.controladorColisiones = new ControlarColisiones(this.nave,this.enemigos);
        this.keyPressed = 0;
        this.puntaje = 0;
    }

    public void play(){
        this.enemigos.start();
        this.controladorColisiones.start();
        while (true){
            try {
                nave.mover(this.keyPressed);
                repaint();
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void dibujarEnemigos(){
        
        BufferStrategy buff = this.getBufferStrategy();
        Graphics g = null;
        if (enemigos == null || enemigos.listaEnemy == null)
            return;
        try {
            g = buff.getDrawGraphics();
            g.setColor(Color.BLACK);
            
            for (int i = 0; i < enemigos.listaEnemy.size(); i++){
                Enemy enemy = enemigos.listaEnemy.get(i);
                g.fillOval(enemy.posX, enemy.posY, enemy.width, enemy.height);
            }
        } finally{
            g.dispose();
        }
    }
    
    @Override
    public void paint (Graphics graph){
        super.paint(graph);
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = bf.getDrawGraphics();
        
        //FONDO
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, 500, 500);
        
        //DIBUJAR PUNTAJE
        g.setColor(Color.WHITE);
        g.drawString("Points: " + Ventana.puntaje, 10, 40);
        
        
        g.dispose();
        //DIBUJAR NAVE Y ENEMIGOS
        if (nave != null)
            nave.dibujar();

        //DIBUJAR ENEMIGOS
        dibujarEnemigos();
       
        
        
        bf.show();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int code = evt.getKeyCode();
        
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT)
            this.keyPressed = code;
        
            
        if (code == KeyEvent.VK_SPACE)
            nave.disparar();            
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
