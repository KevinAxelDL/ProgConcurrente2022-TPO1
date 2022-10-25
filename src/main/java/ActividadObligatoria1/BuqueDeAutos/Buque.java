/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadObligatoria1.BuqueDeAutos;

/**
 *
 * @author agust
 */

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buque {

    //Objeto pasivo
    private Semaphore semMutex = new Semaphore(1);
    //private Semaphore semEspacio = new Semaphore(10);//Si queremos tener un orden podemos usar el "true" como segundo parametro
    //
    private Semaphore semCruzar = new Semaphore(0);//Comunicacion con buque
    private Semaphore semEnDestino = new Semaphore(0);//Comunicacion con autos
    private Semaphore semEnPuerto = new Semaphore(0);//Comunicacion con autos
    //
    private int espacioOcupado = 0;//Valor condicional
    private int enOrilla = 0;//Valor condicional

    public Buque() {
        
    }

    //METODOS PARA AUTO
    public void subir(String auto) {
        //Sube un auto por vez
        try {
            this.semMutex.acquire();//EXM
            enOrilla++;
            System.out.println("(...) " + auto + " esperando en orilla");//DEBUG
            this.semMutex.release();//EXM

            semEnPuerto.acquire();//Espera que venga un buque

            //semEspacio.acquire();//Intenta ocupar un espacio

            this.semMutex.acquire();//EXM
            enOrilla--;//Salio de la orilla
            this.semMutex.release();//EXM

            System.out.println("(-->) " + auto + " subiendo al buque ");//DEBUG       
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void estacionar(String auto) {
        try {
            semMutex.acquire();//EXM
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
        espacioOcupado++;
        if ((espacioOcupado == 10) || (espacioOcupado < 10 && enOrilla == 0)) {
            //No pueden subir mas autos
            System.out.println("(!!!) BUQUE LISTO!!");
            semCruzar.release();
        } else {
            semEnPuerto.release();//Notifica a los demas autos que se puede subir
        }
        System.out.println("(---) " + auto + " a bordo");
        semMutex.release();//EXM
    }

    public void bajar(String auto) {
        try {
            semEnDestino.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("(-->) " + auto + " bajando del BUQUE");//DEBUG
    }

    public void liberaRampa(String auto) {
        try {
            semMutex.acquire();//EXM
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }

        espacioOcupado--;//Salio del barco

        System.out.println("(---) " + auto + " en tierra");

        if (espacioOcupado == 0) {
            //Buque bacio
            System.out.println("(!!!) Se descargaron los AUTOS");//DEBUG
            semCruzar.release();
        }else{
            semEnDestino.release();//Avisa a los demas autos que pueden bajar
        }

        semMutex.release();//EXM

        
    }

    //METODOS PARA CAPITAN
    public void ir() {
        try {

            semEnPuerto.release();//Notifica a los autos que llego al puerto

            System.out.println("(!!!) El BUQUE ESPERA en el PUERTO");

            semCruzar.acquire();

            System.out.println("BUQUE LLENDO -->");//DEBUG

        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void volver() {
        //semEspacio.release(espacioOcupado);//Se liberan los permisos del espacio para el siguiente viaje

        System.out.println("(!!!) BUQUE en DESTINO!");//DEBUG

        semEnDestino.release();//Notifica a los autos que ya llegaron

        try {
            semCruzar.acquire();//Regresa en cuanto se bajen todos los autos

            System.out.println("<-- BUQUE VOLVIENDO");//DEBUG
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
