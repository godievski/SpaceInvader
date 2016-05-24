/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bala;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static Model.Nave.WIDTH;

/**
 *
 * @author Godievski
 */
public class GestorBalas {
    private ArrayList<Bala> listaBalas;
    
    public GestorBalas(){
        this.listaBalas = new ArrayList<>();
    }
    public Bala get(int index){
        if (index < this.listaBalas.size()){
            return this.listaBalas.get(index);
        }
        return null;
    }
    public void add(Bala bala){
        this.listaBalas.add(bala);
    }
    public void remove(int index){
        this.listaBalas.remove(index);
    }
    public void dibujar(Graphics g){
        try {
            //DIBUJAR BALAS
            g.setColor(Color.WHITE);
            for (int i = 0; i < listaBalas.size(); i++){
                Bala bala = this.listaBalas.get(i);
                bala.dibujar(g);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
}
