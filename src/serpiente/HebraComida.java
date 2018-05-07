/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author davidherreragarcia
 */
public class HebraComida extends Thread implements Runnable {
    int randomX;
    int randomY;
    

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        //Genera coordenadas x e y para la aparicion de los tesoros
        while (true){
            
            Pantalla.generarFood();
            
            try {
                Thread.sleep(70000);
            } catch (InterruptedException ex) {
               // Logger.getLogger(Tesoros.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Pantalla.casilla[Pantalla.randX][Pantalla.randY].setBackground(Color.LIGHT_GRAY);
            Pantalla.casilla[Pantalla.randX][Pantalla.randY].food = false;
        
       
    
        }
    }
}

   /** @Override
    public void run() {
        while (!Pantalla.fin) {
            
            Random rand = new Random();
        //Generate random x and y coords for the food
        int z = rand.nextInt(Pantalla.x);
        int a = rand.nextInt(Pantalla.y);
        if(Pantalla.contFood == 0){
            while ((!Pantalla.casilla[z][a].cabeza) || (!Pantalla.casilla[z][a].cuerpo) || (!Pantalla.casilla[z][a].cola)){
                //If there is something in the way, generate another location
                z = rand.nextInt(Pantalla.x);
                a = rand.nextInt(Pantalla.y);
            }
            //Place the food
            //Pantalla.casilla[z][a].food = true;
            Pantalla.casilla[z][a].setBackground(Color.YELLOW);
            //Increment the amount of food on the ground
            Pantalla.contFood++;
            }
            try {
               Thread.sleep(200);
             } catch (InterruptedException ex) {
            
             }
        }
    }
    
} */