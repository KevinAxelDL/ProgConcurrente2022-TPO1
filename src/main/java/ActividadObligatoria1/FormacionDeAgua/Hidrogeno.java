/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadObligatoria1.FormacionDeAgua;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agust
 */
public class Hidrogeno implements Runnable {
    
    private final Recipiente recipiente;
    private final String id;
    
    public Hidrogeno(String id, Recipiente recipiente){
        this.recipiente = recipiente;
        this.id = id;
    }
    
    public void run(){
        recipiente.hacerH(id);
        /*
        try {
             Thread.sleep(1000);
         } catch (InterruptedException ex) {
             Logger.getLogger(Oxigeno.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
    }
    
}
