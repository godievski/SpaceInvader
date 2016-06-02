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
public class WindowGame extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    
    protected final DrawingSpace panelDibujo;
    
    //OBJETOS
    private static Point position;
    private int numPlayer = 0;
    private Nave nave;
    
    //FLAGS
    protected boolean keyUp;
    protected boolean keyDown;
    protected boolean keyLeft;
    protected boolean keyRight;
    private static boolean spacePressed;
    private static boolean mousePressed;
    private static boolean specialShoot;
    
    //CONSTANTS
    protected final static int SLEEP_TIME = 10;
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 600;
    /**
     * 
     *
     */
    public final Game game;
    
    public WindowGame() {
        initComponents();
        this.setIgnoreRepaint(true);
        this.setBounds(500, 100, WindowGame.WINDOW_WIDTH+6, WindowGame.WINDOW_HEIGHT+29);
        this.setResizable(false);
        this.createBufferStrategy(2);
        this.setVisible(true);
        this.setTitle("SpaceInvader by Godievski");
        this.setFocusableWindowState(true);
        
        //GAME - REMPLAZAR POR LLAMADO AL SERVIDOR
        this.game = new Game(this);
        this.nave = new Nave();
        
        //PANEL
        this.panelDibujo = new DrawingSpace(this.game, this.nave, new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.panelDibujo.setFocusable(false);
        this.panelDibujo.setIgnoreRepaint(false);
        this.add(this.panelDibujo);
        
        this.keyDown = false;
        this.keyLeft = false;
        this.keyRight = false;
        this.keyUp = false;
        WindowGame.spacePressed = false;
        WindowGame.mousePressed = false;
        WindowGame.specialShoot = false;
        WindowGame.position = this.getLocationOnScreen();
    }

    public void addPlayer(){
        this.numPlayer = this.game.getNaves().size();
        this.game.joinGame(this.nave);
    }
    
    public void startGame(){
        this.addPlayer();
        while(true){
            //INICIALIZACION
            initGame();    
            while (game.getNaves().isSomeOneAlive()){
                try {
                    nave.mover(keyLeft, keyRight, keyUp, keyDown);
                    panelDibujo.repaint();
                    sleep(SLEEP_TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WindowGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            endGame();
        }
    }
    
    public void joinGame(){
        this.addPlayer();
        while(true){
            //MENU
            while (game.getNaves().isSomeOneAlive()){
                try {
                    nave.mover(keyLeft, keyRight, keyUp, keyDown);
                    panelDibujo.repaint();
                    sleep(SLEEP_TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WindowGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void viewGame(){
        while(true){
            try {
                panelDibujo.repaint();
                sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(WindowGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    public void initGame(){
        game.getEnemies().clear();
        nave.getBalas().clear();
        nave.setHP(4);
        nave.setPosX(Nave.POSX_INI);
        nave.setPosY(Nave.POSY_INI);
        nave.setScore(0);
        if (game.movimientoEnemigos == null){
            game.movimientoEnemigos = new EnemyMoving(game.getEnemies(), nave);
        }
        if (game.movimientoBalas == null){
            game.movimientoBalas = new BulletMoving(nave.getBalas());
        }
        if (game.controladorColisiones == null){
            game.controladorColisiones = new Collision(this.nave,game.getEnemies());
        }
        if (game.gestorDisparos == null){
            game.gestorDisparos = new GestorDisparos(this);
        }
        game.movimientoEnemigos.start();
        game.movimientoBalas.start();
        game.controladorColisiones.start();
        game.gestorDisparos.start();
    }
    
    public void endGame(){
        if (game.movimientoEnemigos.isPlaying()){
            game.movimientoEnemigos.stopIt();
            try{game.movimientoEnemigos.join();} catch(InterruptedException ex) {}
            game.movimientoEnemigos = null;
        }
        if (game.movimientoBalas.isPlaying()){
            game.movimientoBalas.stopIt();
            try{game.movimientoBalas.join();} catch(InterruptedException ex) {}
            game.movimientoBalas = null;
        }
        if (game.controladorColisiones.isPlaying()){
            game.controladorColisiones.stopIt();
            try{game.controladorColisiones.join();} catch(InterruptedException ex) {}
            game.controladorColisiones = null;
        }
        if (game.gestorDisparos.isPlaying()){
            game.gestorDisparos.stopIt();
            try{game.gestorDisparos.join();} catch(InterruptedException ex) {}
            game.gestorDisparos = null;
        }
    }
    
    
    public Nave getNave(){
        return this.nave;
    }

    public static boolean getSpecialShoot(){
        return WindowGame.specialShoot;
    }
    public static void setSpecialShoot(boolean value){
        WindowGame.specialShoot = value;
    }
    public static boolean getMousePressed(){
        return WindowGame.mousePressed;
    }
    public static void setMousePressed(boolean value){
        WindowGame.mousePressed = value;
    }
    public static boolean getSpacePressed(){
        return WindowGame.spacePressed;
    }
    public static void setSpacePressed(boolean value){
        WindowGame.spacePressed = value;
    }
    public static Point getMyPosition(){
        return WindowGame.position;
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
        if (code == KeyEvent.VK_SPACE){
            if (!WindowGame.mousePressed && !WindowGame.specialShoot)
                WindowGame.spacePressed = true;
        }
        if (code == KeyEvent.VK_X){
            if (!WindowGame.spacePressed && !WindowGame.mousePressed)
                WindowGame.specialShoot = true;
        }
            
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
        if (code == KeyEvent.VK_SPACE){
            WindowGame.spacePressed = false;
        }
        if (code == KeyEvent.VK_X){
            WindowGame.specialShoot = false;
        }
            
    }//GEN-LAST:event_formKeyReleased


    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
