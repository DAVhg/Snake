/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import java.awt.Color;
import java.awt.*;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

/**
 *
 * @author davidherreragarcia
 */
public class Puntos extends JFrame {
    // Se crea otra Pantalla aparte para mostrar los puntos del usuario
    public Puntos(){
        setPantallaPuntos();
        iniciarPuntos();
    }
    
    public static JLabel labelPuntos;
    FlowLayout l = new FlowLayout(FlowLayout.CENTER);
  
    private void setPantallaPuntos() {
        this.setSize(200, 70);
        this.setTitle("PUNTOS");
        this.setLayout(l);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);       
    }
    
    private void iniciarPuntos() {
       labelPuntos = new JLabel();
        labelPuntos.setLayout(l);
        labelPuntos.setLocation(100, 100);
        labelPuntos.setText(String.valueOf("Puntos obtenidos: " + Pantalla.puntos));
        getContentPane().add(labelPuntos);
    }
    
    // Para que en la ventana se muestren los puntos actualizados, debe cambiarse el texto del label cada vez que cogemos una comida.
    // Es llamado por Pantalla cuando se coge una comida
    public static void actualizarPuntos() {
        labelPuntos.setText(String.valueOf("Puntos obtenidos: " + Pantalla.puntos));
    }
    
    public static void main(String[] args) {
        Puntos a = new Puntos();
        a.setVisible(true);
    }
}
