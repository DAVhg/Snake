/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import java.awt.*;
import static java.awt.BorderLayout.CENTER;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static serpiente.Controlador.*;

/**
 *
 * @author davidherreragarcia
 */
public class Pantalla extends JFrame implements KeyListener {

    
    public Pantalla() {
        setPantalla();
        iniciarComponentes();
        colocarSerpiente();
        //iniciarPuntos();
        this.addKeyListener(this);
    }
    

   
        public static Casilla[][] casilla;
        static final int x = 50;
        static final int y = 50;
        static int direccion;
        static int velocidad;
        static int longSerpiente;
        static int contSerpiente;
        static int randX;
        static int randY;
        static boolean pausa;
        static boolean reiniciar;
        static final int ARRIBA = 1;
        static final int DER = 2;
        static final int ABAJO = 3;
        static final int IZQ = 4;
        static final int INICIAL = 0;
        static int cabezaX;
        static int cabezaY;
        static int contFood;
        static int puntos;
        static boolean fin;
        GridLayout a;
        JLabel labelPuntos;
        GridLayout g;
        
        Runnable h = new Hebra();
        Thread t = new Thread(h);
        
        Runnable hComida = new HebraComida();
        Thread tComida = new Thread(hComida);
        
        private void setPantalla() {
        this.setSize(600, 600);
        this.setTitle("SERPIENTE");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);       
    }
        
    private void iniciarComponentes() {
        g = new GridLayout(x,y,1,1);
        this.setLayout(g);
        casilla = new Casilla[x][y];
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                casilla[i][j] = new Casilla();
                getContentPane().add(casilla[i][j]);
            }
        }
        setIndice();
        direccion = INICIAL;
        velocidad = 160;
        fin = false;
        pausa = false;
        reiniciar = false;
        contFood = 0;
        puntos = 0;
        cabezaX = 25;
        cabezaY = 25;
        longSerpiente = 4;
        t.start();
        tComida.start();
    }
     
    private void iniciarPuntos() {
        labelPuntos = new JLabel();
        labelPuntos.setText(String.valueOf(Pantalla.puntos));
        getContentPane().add(labelPuntos);
    }
    public static void reiniciar(){
        direccion = 2;
        velocidad = 160;
        fin = false;
        pausa = false;
        reiniciar = false;
        contFood = 0;
        puntos = 0;
        Puntos.actualizarPuntos();
        cabezaX = 25;
        cabezaY = 25;
        longSerpiente = 4;
        setIndice();
        colocarSerpiente();
    }
    
    public static void colocarSerpiente() {
        
        for(int i = 0; i < longSerpiente; i++){
            casilla[cabezaX][cabezaY-i].indice = longSerpiente - i;
        }
        
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                if (casilla[i][j].indice > 0) {
                    casilla[i][j].setBackground(Color.BLUE);
                } 
            }
        }
    }
    
    public static void pintar() {
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                if ((casilla[i][j].indice > 0) && (!casilla[i][j].food)){
                    casilla[i][j].setBackground(Color.BLUE);
                } else if ((casilla[i][j].indice == 0) && (!casilla[i][j].food)){
                    casilla[i][j].setBackground(Color.LIGHT_GRAY);
                } else if (casilla[i][j].food) {
                    casilla[i][j].setBackground(Color.YELLOW);
                } 
            }
        }
    }
    
    public static void setIndice(){
    for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                casilla[i][j].setBackground(Color.LIGHT_GRAY);
                casilla[i][j].indice = 0;
            }
        }
        
        for (int i = 1; i < x; i++) {
                casilla[i][49].setBackground(Color.GRAY);
                casilla[i][49].indice = -1;
            }
        for (int j = 1; j < y; j++) {
                casilla[49][j].setBackground(Color.GRAY);
                casilla[49][j].indice = -1;
            }
        for (int j = 1; j < y; j++) {
                casilla[1][j].setBackground(Color.GRAY);
                casilla[1][j].indice = -1;
            }
        for (int i = 1; i < y; i++) {
                casilla[i][1].setBackground(Color.GRAY);
                casilla[i][1].indice = -1;
            }
    }
    
    
    public static void setCabeza() {
    if (Pantalla.direccion == ARRIBA) {
                    --cabezaX;
                } else if (Pantalla.direccion == DER) {
                    cabezaY++;
                } else if (Pantalla.direccion == IZQ) {
                    --cabezaY;
                } else if (Pantalla.direccion == ABAJO) {
                    cabezaX++;
                }
    }
    
    public static void crecer() {
        longSerpiente++;
        switch (direccion) {
               case ARRIBA:
                   casilla[cabezaX-1][cabezaY].indice = longSerpiente ;
                   setCabeza();
                   pintar();
                   break;
               case ABAJO:
                   casilla[cabezaX+1][cabezaY].indice = longSerpiente ;
                   setCabeza();
                   pintar();
                   break;
               case DER:
                   casilla[cabezaX][cabezaY+1].indice = longSerpiente ;
                   setCabeza();
                   pintar();
                   break;
               case IZQ:
                   casilla[cabezaX][cabezaY-1].indice = longSerpiente ;
                   setCabeza();
                   pintar();
                   break;
               case INICIAL: 
                   break;
           }
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                if (casilla[i][j].indice > 0) {
                    casilla[i][j].indice--;
                }
            }
        }
    }
    
    
    
    public static void checkFood() {
        if (casilla[cabezaX][cabezaY].food) {
                    casilla[cabezaX][cabezaY].food = false;
                    casilla[cabezaX][cabezaY].setBackground(Color.BLUE);
                    puntos++;
                    Puntos.actualizarPuntos();
                    crecer();
                    generarFood();
                }
    }
    
    public static void mover(){
        switch (direccion) {
            case ARRIBA:
                casilla[cabezaX-1][cabezaY].indice = longSerpiente + 1;
                break;
            case DER:
               casilla[cabezaX][cabezaY+1].indice = longSerpiente + 1;
               break;
            case ABAJO:
                casilla[cabezaX+1][cabezaY].indice = longSerpiente + 1;
                break;
            case IZQ:
                casilla[cabezaX][cabezaY-1].indice = longSerpiente + 1;
                break;
            case INICIAL:
                break;
        }
        if (direccion != INICIAL) {
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                if (casilla[i][j].indice > 0) {
                    casilla[i][j].indice--;
                }
            }
        }
        }
    }
    
   
    public static void checkChoque(){
        switch (direccion) {
            case ARRIBA:
                if (casilla[cabezaX-1][cabezaY].indice != 0) {
                    fin = true;
                }
                break;
            case DER:
               if (casilla[cabezaX][cabezaY+1].indice != 0) {
                   fin = true;
                }
                break;
            case ABAJO:
                if (casilla[cabezaX+1][cabezaY].indice != 0) {
                    fin = true;
                }
                break;
            case IZQ:
                if (casilla[cabezaX][cabezaY-1].indice != 0) {
                    fin = true;
                }
                break;
        }
    }
    
    public static void generarFood() {
        randX = new Random().nextInt(x-1);
        randY = new Random().nextInt(y-1);
        while (casilla[randX][randY].indice != 0){
            randX = new Random().nextInt(x-1);
            randY = new Random().nextInt(y-1);
        }
        casilla[randX][randY].setBackground(Color.YELLOW);
        casilla[randX][randY].food = true;
    }
     
    public static void main(String[] args) {
        
        Puntos a = new Puntos();
        a.setVisible(true);
        Pantalla p = new Pantalla();
        p.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*int code = e.getKeyCode();
        if((code == KeyEvent.VK_UP || code == KeyEvent.VK_W)){
            this.direccion = ARRIBA;
            mover();
        } else if((code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)){
            this.direccion = DER;
            mover();
        } else if((code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)){
            this.direccion = ABAJO;
            mover();
        } else if((code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)){
            this.direccion = IZQ;
            mover();
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if((code == KeyEvent.VK_UP || code == KeyEvent.VK_W) && direccion != ABAJO){
            this.direccion = ARRIBA;
        } else if((code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) && direccion != IZQ){
            this.direccion = DER;
            
        } else if((code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) && direccion != ARRIBA){
            this.direccion = ABAJO;
        } else if((code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) && direccion != DER){
            this.direccion = IZQ;
        } else if(code == KeyEvent.VK_5){
            reiniciar = true;
            
        } else if(code == KeyEvent.VK_2){
            velocidad = velocidad/2;
            
        } else if(code == KeyEvent.VK_1){
            velocidad = velocidad*2;
            
        } else if(code == KeyEvent.VK_0){
            crecer();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*int code = e.getKeyCode();
        if((code == KeyEvent.VK_UP || code == KeyEvent.VK_W)){
            this.direccion = ARRIBA;
            mover();
        } else if((code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)){
            this.direccion = DER;
            mover();
        } else if((code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)){
            this.direccion = ABAJO;
            mover();
        } else if((code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)){
            this.direccion = IZQ;
            mover();
        }*/
    }
}
