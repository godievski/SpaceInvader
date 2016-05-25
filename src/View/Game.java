/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Controller.MovimientoEnemigos;
import Controller.ControlarColisiones;
import Controller.GestorDisparos;
import Model.Nave;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

/**
 *
 * @author Godievski
 */
public class Game extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    private final PanelDibujo panelDibujo;
    
    
    //OBJETOS
    private final Nave nave;
    protected MovimientoEnemigos movimientoEnemigos;
    private final ControlarColisiones controladorColisiones;
    private final GestorDisparos gestorDisparos;
    private static int score;
    private static Point position;
    
    
    //FLAGS
    private int keyPressed;
    private boolean disparando;
    private static boolean mousePressed;
    //CONSTANTS
    private final static int SLEEP_TIME = 10;
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 600;
    /**
     * 
     *
     */
    public Game() {
        initComponents();
        this.setIgnoreRepaint(true);
        this.setBounds(500, 100, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        this.setResizable(false);
        this.createBufferStrategy(2);
        this.setVisible(true);
        this.setTitle("SpaceInvader by Godievski");
        

        //PANEL
        this.panelDibujo = new PanelDibujo(this, new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.panelDibujo.setFocusable(false);
        this.setContentPane(this.panelDibujo);
        this.panelDibujo.setLayout(null);
        this.panelDibujo.setDoubleBuffered(true);

        //OBJETOS DEL JUEGO
        nave = new Nave();
        this.movimientoEnemigos = new MovimientoEnemigos();
        this.controladorColisiones = new ControlarColisiones(this.nave,this.movimientoEnemigos);
        this.gestorDisparos = new GestorDisparos(this);
        this.keyPressed = 0;
        this.disparando = false;
        Game.mousePressed = false;
        Game.score = 0;
        Game.position = this.getLocationOnScreen();
    }

    public void play(){
        this.movimientoEnemigos.start();
        this.controladorColisiones.start();
        this.gestorDisparos.start();
        while (true){
            try {
                nave.mover(this.keyPressed);
                repaint();
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean getDisparando(){
        return this.disparando;
    }
    public Nave getNave(){
        return this.nave;
    }
    public static void modifyScore(int points){
        Game.score += points;
    }
    public static int getScore(){
        return Game.score;
    }
    
    public static boolean getMousePressed(){
        return Game.mousePressed;
    }
    public static Point getMyPosition(){
        return Game.position;
    }

    @Override
    public void paint (Graphics g){
        this.panelDibujo.paint(g);
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
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
        //MOVIMIENTO
        if (code == KeyEvent.VK_A){
            this.keyPressed = KeyEvent.VK_LEFT;
        }else if (code == KeyEvent.VK_D){
            this.keyPressed = KeyEvent.VK_RIGHT;
        }else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT)
            this.keyPressed = code;
        
        //DISPARO
        if (code == KeyEvent.VK_SPACE){
            this.disparando = true;           
        }
             
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        int code = evt.getKeyCode();
     
        if (code == KeyEvent.VK_SPACE){
            this.disparando = false;
        }
    }//GEN-LAST:event_formKeyReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        Game.mousePressed = true;
        System.out.printf("Click!\n");
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        System.out.printf("Anit-Click!\n");
        Game.mousePressed = false;
    }//GEN-LAST:event_formMouseReleased

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
