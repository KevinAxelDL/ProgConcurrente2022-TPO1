/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporal.Clases1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agust
 */
public class Capitan implements Runnable {
    // OBJETO ACTIVO

    private Buque buque;

    public Capitan(Buque buque) {
        this.buque = buque;
    }

    public void run() {
        while (true) {
            buque.ir();
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Capitan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            buque.volver();
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Capitan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
