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
public class Cama {
    //OBJETO PASIVO
    //MONITOR

    private int horaAct = -1;
    private Cama camaVecina;
    
    public Cama(Cama camaVecina){
        this.camaVecina = camaVecina;
    }

    //Metodos para Reloj 
    public synchronized void actualizaHora() {

        horaAct++;
        horaAct = (int)(horaAct % 24);
        System.out.println("--- SON LAS "+ horaAct +" hs ---");
        this.notify();//Notifica al trabajador mas cercano
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
                Logger.getLogger(Cama.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(this.camaVecina != null){
                //Si no es la ultima cama
                camaVecina.notify();//Avisa a un compañero que hora es
                System.out.println("(...) "+ t.getId() +" notifica a un comañero de la hora");//DEBUG
            }
        }
        System.out.println("(<--) "+ t.getId() +" salio a trabajar");//DEBUG
    }
}
