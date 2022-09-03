/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import TPO1.TecladoIn;

/**
 *
 * @author agust
 */
public class main {

    public static void main(String arg[]) {
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

        if (k <= 0 || k >= n) {
            //Control: la cantidad de hilos debe ser k > 0 && k < n
            k = n - 1;
            System.out.println("ADVERTENCIA: Cantidad de hilos no valida, el programa determinara la cantidad");
            System.out.println("VAL: "+ k +", "+ n);//DEBUG
        }
        
        Thread[] hilosCreados = new Thread[k];//Arreglo con hilos, necesario en main
        //El arreglo debe poder almacenar k hilos, por lo tant el tam. debe ser k
        cantPosAsignadas =  (n - 1) / k;
        posIni = 0;
        posFin = cantPosAsignadas - 1;
        
        //Se crean los hilos
        for (int i = 0; i < k; i++) {
            Sumador obj = new Sumador(arrNum, posIni, posFin);//Se crea un objeto
            posIni = posFin + 1;
            posFin = posIni + cantPosAsignadas + 1;
            if (posFin >= n ) {
                //Caso final en el arreglo
                posFin = n - 1;
            }
            Thread t1 = new Thread(obj);//Se crea el hilo
            t1.start();//se activa el hilo

            hilosCreados[i] = t1;
            System.out.println("(+) " + obj.getId() + " CREADO E INICIADO");
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
