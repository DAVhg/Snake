/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import java.awt.*;

/**
 *
 * @author davidherreragarcia
 */
public class HebraComida extends Thread implements Runnable {
    int randomX;
    int randomY;
    

    @Override
    public void run() {
        while (true){
            
            Pantalla.generarFood(); // Llama al método de la pantalla para generar la comida en una casilla random
            
            try {
                Thread.sleep(70000); // Cada cuanto tiempo se espera para eliminar la comida anterior si no ha sido comida aún
            } catch (InterruptedException ex) {
            }
            // Si no se ha cogido la comida, se cambia su color a gris y deja de ser comida. Se vuelve al principio del bucle.
            Pantalla.casilla[Pantalla.randX][Pantalla.randY].setBackground(Color.LIGHT_GRAY);
            Pantalla.casilla[Pantalla.randX][Pantalla.randY].food = false;
        }
    }
}