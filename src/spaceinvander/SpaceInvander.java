/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvander;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Godievski
 */
public class SpaceInvander {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GraphicsEnvironment graphEnv =
        GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphDevice = graphEnv.getDefaultScreenDevice
        ();
        GraphicsConfiguration graphicConf = graphDevice.getDefaultConfiguration(); 
        Game game = new Game(graphicConf);
        game.play();
    }
    
}
