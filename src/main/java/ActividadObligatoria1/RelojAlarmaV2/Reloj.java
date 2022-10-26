/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadObligatoria1.RelojAlarmaV2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agust
 */
public class Reloj implements Runnable {

    private Despertador despertador;

    public Reloj(Despertador despertador) {
        this.despertador = despertador;
    }

    public void run() {
        while (true) {
            this.pasaHora();
            despertador.actualizaHora();

        }
    }

    private void pasaHora() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
