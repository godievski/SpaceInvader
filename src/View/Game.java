/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Controller.EnemyMoving;
import Controller.Collision;
import Controller.GestorDisparos;
import Controller.GestorEnemigos;
import Controller.BulletMoving;
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
    private final DrawingSpace panelDibujo;
    
    //OBJETOS
    private final Nave nave;
    private final GestorEnemigos enemies;
    private static int score;
    private static Point position;
    
    //THREADS
    protected EnemyMoving movimientoEnemigos;
    protected BulletMoving movimientoBalas;
    private Collision controladorColisiones;
    private GestorDisparos gestorDisparos;
    
    //FLAGS
    protected boolean keyUp;
    protected boolean keyDown;
    protected boolean keyLeft;
    protected boolean keyRight;
    public static boolean spacePressed;
    public static boolean mousePressed;
    public static boolean specialShoot;
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
        this.setBounds(500, 100, Game.WINDOW_WIDTH+6, Game.WINDOW_HEIGHT+29);
        this.setResizable(false);
        this.createBufferStrategy(2);
        this.setVisible(true);
        this.setTitle("SpaceInvader by Godievski");
        this.setFocusableWindowState(true);
        
        //PANEL
        this.panelDibujo = new DrawingSpace(this, new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.panelDibujo.setFocusable(false);
        this.panelDibujo.setIgnoreRepaint(false);
        this.add(this.panelDibujo);
        
        //OBJETOS DEL JUEGO
        this.nave = new Nave();
        this.enemies = new GestorEnemigos();
        this.movimientoEnemigos = new EnemyMoving(enemies);
        this.movimientoBalas = new BulletMoving(nave.getBalas());
        this.controladorColisiones = new Collision(this.nave,this.enemies);
        this.gestorDisparos = new GestorDisparos(this);
        this.keyDown = false;
        this.keyLeft = false;
        this.keyRight = false;
        this.keyUp = false;
        Game.spacePressed = false;
        Game.mousePressed = false;
        Game.specialShoot = false;
        Game.score = 0;
        Game.position = this.getLocationOnScreen();
    }

    public void play(){
        while(true){
            //MENU            
            
            //INICIALIZACION
            this.newGame();
            this.movimientoEnemigos.start();
            this.movimientoBalas.start();
            this.controladorColisiones.start();
            this.gestorDisparos.start();
            
            while (nave.getHP() > 0){
                try {
                    nave.mover(keyLeft, keyRight, keyUp, keyDown);
                    panelDibujo.repaint();
                    sleep(SLEEP_TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            restartGame();
        }
    }
    
    public void restartGame(){
        if (this.movimientoEnemigos.isPlaying()){
            this.movimientoEnemigos.stopIt();
            try{this.movimientoEnemigos.join();} catch(InterruptedException ex) {}
            movimientoEnemigos = null;
        }
        if (this.movimientoBalas.isPlaying()){
            this.movimientoBalas.stopIt();
            try{this.movimientoBalas.join();} catch(InterruptedException ex) {}
            movimientoBalas = null;
        }
        if (this.controladorColisiones.isPlaying()){
            this.controladorColisiones.stopIt();
            try{this.controladorColisiones.join();} catch(InterruptedException ex) {}
            controladorColisiones = null;
        }
        if (this.gestorDisparos.isPlaying()){
            this.gestorDisparos.stopIt();
            try{this.gestorDisparos.join();} catch(InterruptedException ex) {}
            gestorDisparos = null;
        }
    }
    
    public void newGame(){
        enemies.clear();
        nave.getBalas().clear();
        nave.setHP(4);
        nave.setPosX(Nave.POSX_INI);
        nave.setPosY(Nave.POSY_INI);
        Game.score = 0;
        if (movimientoEnemigos == null){
            this.movimientoEnemigos = new EnemyMoving(enemies);
        }
        if (movimientoBalas == null){
            this.movimientoBalas = new BulletMoving(nave.getBalas());
        }
        if (controladorColisiones == null){
            this.controladorColisiones = new Collision(this.nave,this.enemies);
        }
        if (gestorDisparos == null){
            this.gestorDisparos = new GestorDisparos(this);
        }
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
    public static boolean getSpacePressed(){
        return Game.spacePressed;
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
            .addGap(0, 529, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
        int code = evt.getKeyCode();
        //MOVIMIENTO
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            this.keyLeft = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            this.keyRight = true;
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            this.keyUp = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            this.keyDown = true;

        //DISPARO
        if (code == KeyEvent.VK_SPACE)
            Game.spacePressed = true;
        if (code == KeyEvent.VK_X)
            Game.specialShoot = true;
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        int code = evt.getKeyCode();
     //MOVIMIENTO
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            this.keyLeft = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            this.keyRight = false;
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            this.keyUp = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            this.keyDown = false;

        //DISPARO
        if (code == KeyEvent.VK_SPACE)
            Game.spacePressed = false;
        if (code == KeyEvent.VK_X)
            Game.specialShoot = false;
    }//GEN-LAST:event_formKeyReleased


    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
