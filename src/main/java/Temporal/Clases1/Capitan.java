/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporal.Clases1;

/**
 *
 * @author agust
 */
public class Capitan implements Runnable{
    // OBJETO ACTIVO
    
    private Buque buque;
    
    public Capitan(Buque buque){
        this.buque=buque;
    }
    
    public void run(){
        buque.ir();
        buque.volver();
    }
    
    
}
