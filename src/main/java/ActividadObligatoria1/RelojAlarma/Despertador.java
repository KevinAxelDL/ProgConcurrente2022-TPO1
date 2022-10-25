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
public class Despertador {
    //OBJETO PASIVO

    private int horaAct = -1;

    //Metodos para Reloj
    public synchronized void actualizaHora() {

        horaAct++;
        horaAct = (int)(horaAct % 24);
        System.out.println("--- SON LAS "+ horaAct +" hs ---");
        notify();//Notifica a alguno de los trabajadores durmiendo
        //notifyAll();//Notifica a todos los trabajadores//DEBUG
    }

    // Metodos para Trabajador
    public synchronized void descansar(Trabajador t) {
        while (t.getHoraTrabajo() != horaAct) {
            //Sigue descansando
            //Puede ser que desperto, no era su hora y volvio a dormir
            System.out.println("(ZZZ) "+ t.getId() +" descansa");//DEBUG
            
            try {
                this.wait();
                //System.out.println("(!!!) "+ t.getId() +" desperto!");//DEBUG
            } catch (InterruptedException ex) {
                Logger.getLogger(Despertador.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*
            this.notify();//Avisa a un compañero que hora es
            System.out.println("(...) "+ t.getId() +" notifica a un comañero de la hora");//DEBUG
            */
            
        }
        System.out.println("(<--) "+ t.getId() +" salio a trabajar");//DEBUG
    }
}
