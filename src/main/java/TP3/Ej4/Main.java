/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP3.Ej4;

/**
 *
 * @author KevinDL
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("---INICIO MAIN---");
        Plato p1 = new Plato();
        Rueda r1 = new Rueda();
        Hamaca h1 = new Hamaca();
        //
        Hamster ham1 = new Hamster("Roberto", p1, r1, h1);
        Hamster ham2 = new Hamster("Trapo", p1, r1, h1);
        Hamster ham3 = new Hamster("Pepe", p1, r1, h1);
        //
        Thread t1 = new Thread(ham1);
        Thread t2 = new Thread(ham2);
        Thread t3 = new Thread(ham3);
        //
        t1.start();
        t2.start();
        t3.start();
        //
        try{
            t1.join();
            t2.join();
            t3.join();
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("---FIN MAIN---");
    }
}
//NOTAS:

