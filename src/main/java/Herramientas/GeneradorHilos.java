/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herramientas;

/**
 *
 * @author KevinDL
 */
public abstract class GeneradorHilos {
    //Genera hilos funcionales con la interfaz Runnable
    public static Thread generarHilo(Runnable obj){
        //Genera un hilo con un objecto como parametro y lo retorna
        Thread t1 = new Thread(obj);
        return t1;
    }
}
