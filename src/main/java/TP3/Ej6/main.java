/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Ej6;

import TP3.Ej6.TecladoIn;

/**
 *
 * @author agust
 */
public class main {

    public static void main(String arg[]) {
        System.out.println("---INICIO MAIN---");
        Thread[] hilosCreados;

        hilosCreados = crearHilos();

        try {
            for (int i = 0; i < hilosCreados.length; i++) {
                /*
                *El hilo Main debe esperar a todos los demas hilos antes de
                *terminar su ejecucion
                 */
                hilosCreados[i].join();
            }
        } catch (InterruptedException e) {
            e.getMessage();
        }
        System.out.println("---FIN DE OPERACIONES---");
        System.out.println("La suma total de las sumas parciales es: "
                + Sumador.getSumaTotal());
        Sumador.reSetSumaTotal();
        System.out.println("---FIN MAIN---");
    }
    
    public static Thread[] crearHilos() {
        //Crea hilos y se les asigna una parte del arreglo para sumar
        // k -> Cant. de hilos
        // n -> Cant. de elementos en el arreglo
        int[] arrNum;
        int k, cantPosAsignadas, posIni, posFin, n = 50000;

        arrNum = llenarArreglo(n); //Retorna un arreglo con n - 1 posiciones

        System.out.println("Ingrese la cantidad de hilos que desea: ");
        k = TecladoIn.readLineInt();//Cant. hilos
        
        if (k <= 0 || k > n) {
            //Control: la cantidad de hilos debe ser k > 0 && k <= n
            k = n;
            System.out.println("(!)ADVERTENCIA: Cantidad de hilos no valida, el programa determinara la cantidad");
        }
        
        System.out.println("HILOS: "+ k +", ELEMENTOS: "+ n);//DEBUG
        
        Thread[] hilosCreados = new Thread[k];
        //Arreglo con hilos, necesario en main
        //El arreglo debe poder almacenar k hilos
        
        cantPosAsignadas = (n / k) - 1;
        //n posiciones divididas en k hilos 
        //Se redondea hacia abajo
        
        if( n == k){
            //Caso especial
            //(Ver una mejor solucion)
            cantPosAsignadas = 0;
        }
        
        posIni = 0;
        posFin = cantPosAsignadas + posIni;
        
        //Se crean los hilos
        for (int i = 0; i < k; i++) {
            if (posFin < n && i == k-1 ) {
                //Caso final en el arreglo
                posFin = n - 1;
                System.out.println("DEBUG: "+ posIni +", "+ posFin);//DEBUG
            }
            Sumador obj = new Sumador(arrNum, posIni, posFin);//Se crea un objeto
            Thread t1 = new Thread(obj);//Se crea el hilo
            t1.start();//se activa el hilo
            hilosCreados[i] = t1;
            System.out.println("(+) " + obj.getId() + " CREADO E INICIADO");
            //
            posIni = posFin + 1;
            posFin = posIni + cantPosAsignadas;
        }
        return hilosCreados;
    }

    public static int[] llenarArreglo(int n) {
        int pos;
        int[] arrInt = new int[n];
        int numRandom;

        for (pos = 0; pos < n; pos++) {
            //Se llena el arreglo con numeros de 1 a 10
            numRandom = (int) (Math.floor(Math.random() * (10 - 1 + 1)) + 1);
            arrInt[pos] = 1; //DEBUG
        }
        return arrInt;
    }
}
