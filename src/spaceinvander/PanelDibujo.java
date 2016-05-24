/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class PanelDibujo extends JPanel{
    private Game ventana;
    private Image dibujoAux;
    private Graphics gAux;
    private Dimension dimAux;
    private Dimension dimPanel;
    
    public PanelDibujo (Game ventana, Dimension d){
        this.ventana = ventana;
        this.setSize(d);
        dimPanel = d;
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
        gAux.drawString("Score: " + Game.score, 10, 45);
        
        //DIBUJAR NAVE Y ENEMIGOS
        
        if (ventana.nave != null)
            ventana.nave.dibujar(gAux);

        //DIBUJAR ENEMIGOS
        ventana.enemigos.dibujar(gAux);

        g.drawImage(dibujoAux, 0, 0, this);
        g.dispose();
    }
}
