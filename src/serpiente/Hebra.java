/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import javax.swing.*;
import static serpiente.Pantalla.*;     // Importo Pantalla para poder usar sus métodos sin poner Pantalla.metodo

/**
 *
 * @author davidherreragarcia
 */
public class Hebra extends Thread implements Runnable {
    
    @Override
    public void run() { 
        while (!fin) {  //Mientras que no se haya acabado la partida 
            checkChoque();
            mover();
            setCabeza();
            checkFood();
            pintar();
             try {      // Se realizan todos los metodos anteriores y aquí la hebra espera (velocidad) milisegundos para continuar
               Thread.sleep(velocidad); // Velocidad se encuentra en el metodo iniciarComponentes de Pantalla
             } catch (InterruptedException ex) {
            
             }
        }
        if (fin == true) { // Una vez se sale del bucle, se muestra mensaje de final, se reinicia el juego y se vuelve a ejecutar run
            
            JOptionPane.showMessageDialog(null, "Has perdido. Se reiniciará el juego. Tu puntos: " + puntos);
            reiniciar();
            fin = false;
            run();
        }
        
        
    }
    
}
