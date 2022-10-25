/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadObligatoria1.RelojAlarma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agust
 */
public class Trabajador implements Runnable {

    //OBJETO ACTIVO
    private int horaTrabajo;
    private Despertador despertador;
    private String id;

    public Trabajador(String id, Despertador despertador, int horaTrabajo) {
        this.despertador = despertador;
        this.horaTrabajo = horaTrabajo;
        this.id = id;
    }

    public void run() {
        while (true) {
            despertador.descansar(this);
            this.trabajar();
        }
    }

    private void trabajar() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("(-->) "+ id +" regreso del trabajo");
    }
    
    //Getters
    public String getId(){
        return id;
    }

    public int getHoraTrabajo() {
        return horaTrabajo;
    }
}
