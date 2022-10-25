/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Temporal.Clases1;

import Herramientas.GeneradorHilos;
import Herramientas.GeneradorIds;

/**
 *
 * @author KevinDL
 */
public class Main {

    public static void main(String[] args) {
        
        int cantAutos = 21;//MODIFICABLE
        
        GeneradorIds genIdAutos = new GeneradorIds("AUTO");
        Buque buque1 = new Buque();
        Capitan cap1 = new Capitan(buque1);
        
        
        Thread t1 = new Thread(cap1);
        t1.start();
        for (int i=0; i < cantAutos; i++){
            Auto auto = new Auto(genIdAutos.generarUnId(), buque1);
            Thread t2 = new Thread(auto);
            t2.start();
        }
        
    }
}
