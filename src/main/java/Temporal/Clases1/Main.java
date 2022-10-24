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
        int cantAutos = 11;//MODIFICABLE
        GeneradorIds genIdAutos = new GeneradorIds("AUTO");
        Buque buque1 = new Buque("BUQUE");
        Capitan cap1 = new Capitan("CAPITAN", buque1);

        for (int i=0; i < cantAutos; i++){
            Auto auto = new Auto(genIdAutos.generarUnId(), buque1);
            Thread t = new Thread(auto);
            t.start();
        }
    }
}
