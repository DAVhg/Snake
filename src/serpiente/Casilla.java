/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import javax.swing.*;

/**
 *
 * @author davidherreragarcia
 */
public class Casilla extends JPanel {
    boolean food = false;    // Se convierte en true cuando es comida
    int indice;              // Indice de la casilla: -1 si es borde, 0 si es tablero, >0 si es la serpiente
} 
