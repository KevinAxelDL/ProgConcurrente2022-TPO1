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
        try{
            for(int i = 0; i < hilosCreados.length; i++){
                //El hilo Main debe esperar a todos los demas hilos antes de
                //terminar su ejecucion
                hilosCreados[i].join();
            }
        }catch(InterruptedException e){
           e.getMessage();
        }
        System.out.println("La suma total de las sumas parciales es: "+ 
                Sumador.SUMA_TOTAL);
    }

    public static Thread[] crearHilos() {
        int[] arrNum;
        int k, cantPosAsignadas, posIni, posFin, n = 50000;
        
        arrNum = llenarArreglo(n);
        
        System.out.println("Ingrese la cantidad de hilos que desea: ");
        k = TecladoIn.readLineInt();//Cant. hilos
        Thread[] hilosCreados = new Thread[k];//Arreglo con hilos
        
        try {
            if (k > 0 && k < n + 1) {
                //Si se crean mas de 0 y menos de n + 1 hilos
                cantPosAsignadas = n / k;
                System.out.println("CANTPOS: "+ n/k);//DEBUG
                posIni = 0;
                posFin = cantPosAsignadas;
                //Creamos hilos
                for (int i = 0; i < k; i++) {
                    Sumador obj = new Sumador(arrNum, posIni, posFin);//Se crea un objeto
                    posIni = posFin + 1;
                    posFin = cantPosAsignadas;
                    if (posFin > n - 1) {
                        //Caso final en el arreglo
                        posFin = n;
                    }
                    Thread t1 = new Thread(obj);//Se crea el hilo
                    t1.start();//se activa el hilo
                    
                    hilosCreados[i] = t1;
                    System.out.println("(+) " +obj.getId()+" CREADO E INICIADO");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hilosCreados;
    }

    public static int[] llenarArreglo(int n) {
        int pos;
        int[] arrInt = new int[n];
        int numRandom;
        
        for (pos = 0; pos < n; pos++) {
            //Se llena el arreglo con numeros de 1 a 10
            numRandom = (int)(Math.floor(Math.random() * (10 - 1 + 1)) + 1);
            arrInt[pos] = 1; //DEBUG
        }
        return arrInt;
    }
}
