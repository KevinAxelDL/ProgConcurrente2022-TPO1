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
        int num[] = null, arrNum[], pos,k,cantPos,posIni,posFin,n=50000;
        arrNum = new int[n];
     
       
        num=llenarArreglo(arrNum);
        System.out.println("Ingrese la cantidad de hilos que desea: ");
        k=TecladoIn.readLineInt();
        
        try{
        if(k>0 && k<n+1){
            cantPos=n/k;
            posIni=0;
            posFin =cantPos;
         //Creamos hilos
       for (pos = 1; pos < k; pos++) {
           //objeto define metodo run
            Sumador hilo= new Sumador(num,posIni, posFin);
            posIni = posFin +1;
            posFin = posFin + cantPos;
            if(posFin>n-1){
                posFin = n;
            }
            //Se crea el hilo
            Thread t1 = new Thread(hilo);
            
            //se activa el hilo
            t1.start();
            
        }
        } 
        }catch()
        
       
         
         
         
         
       
        
      

    }
    public static int[] llenarArreglo(int[] arrNum){
        int pos,num[] = null;
        double numRandom;
         for (pos = 0; pos < arrNum.length; pos++) {
            numRandom = Math.floor(Math.random() * (10 - 1 + 1)) + 1;
             num[pos] = (int) numRandom;
        }
         return num;
    }
}
