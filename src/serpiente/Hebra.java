/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import static serpiente.Pantalla.*;

/**
 *
 * @author davidherreragarcia
 */
public class Hebra extends Thread implements Runnable {
    
    @Override
    public void run() {
        while (!fin) {
            checkChoque();
            mover();
            setCabeza();
             checkFood();
             pintar();
             try {
               Thread.sleep(velocidad);
             } catch (InterruptedException ex) {
            
             }
        }
        if ((fin == true) || (reiniciar == true)) {
            
            JOptionPane.showMessageDialog(null, "Has perdido. Se reiniciará el juego. Tu puntos: " + puntos);
            reiniciar();
            fin = false;
            run();
        }
        
        
    }
    
}
