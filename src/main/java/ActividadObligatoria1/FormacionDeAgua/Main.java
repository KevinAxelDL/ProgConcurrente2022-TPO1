/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActividadObligatoria1.FormacionDeAgua;
import Herramientas.GeneradorIds;
/**
 *
 * @author KevinDL
 */
public class Main {
    public static void main(String[] args) {
        /*
        NOTA: Algunos de los hilos de Oxigeno y/o Hidrogeno nunca terminaran su
        ejecucion si no hay la cantidad suficiente de los mismos para generar
        una molecula de agua, esto no es un problema si la cantidad de hilos
        generados de ambas clases es infinita.
        Otra solucion seria la terminacion temprana de la ejecucion de 
        estos hilos.
        */
        int cantMaxAgua = 4; //MODIFICABLE
        int cantOxigeno = 5; //MODIFICABLE
        int cantHidrogeno = 9; //MODIFICABLE
        //
        GeneradorIds genIdO = new GeneradorIds("OXIGENO");
        GeneradorIds genIdH = new GeneradorIds("HIDROGENO");
        Recipiente reci = new Recipiente(cantMaxAgua);
        
        for(int i = 0; i < cantOxigeno; i++){
            //Genera los hilos de oxigeno
            Oxigeno obj = new Oxigeno(genIdO.generarUnId(), reci);
            Thread t = new Thread(obj);
            t.start();
        }
        
        for(int i = 0; i < cantHidrogeno; i++){
            //Genera los hilos de hidrogeno
            Hidrogeno obj = new Hidrogeno(genIdH.generarUnId(), reci);
            Thread t = new Thread(obj);
            t.start();
        }
        
    }
}
