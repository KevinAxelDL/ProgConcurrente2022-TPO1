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
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buque {
    //Objeto pasivo
    private Semaphore semMutex = new Semaphore(1);
    private Semaphore semEspacio = new Semaphore(0);//Si queremos tener un orden podemos usar el "true" como segundo parametro
    //
    private Semaphore semCruzar = new Semaphore(0);//Comunicacion con buque
    private Semaphore semEnDestino = new Semaphore(0);//Comunicacion con autos
    private Semaphore semEnPuerto = new Semaphore(0);//Comunicacion con autos
    //
    private String id;
    private int espacioOcupado = 0;
    private int enOrilla = 0;
    
    public Buque(String id) {
        this.id = id;
    }

    public void subir(String auto) {
        try {
            this.semMutex.acquire();
            enOrilla++;//Esperando en orilla
            this.semMutex.release();
            
            semEspacio.acquire();//Intenta entrar al buque
            
            this.semMutex.acquire();
            enOrilla--;//Sale de la orilla
            this.semMutex.release();
            
            System.out.println("(-->) " + auto + " sube al buque ");//DEBUG
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void estacionar() {

        try {
            semMutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
        espacioOcupado++;
        if ((espacioOcupado == 10) || (espacioOcupado < 10 && enOrilla == 0)) {
            System.out.println("(!!!) Buque LLENO!!");
            semCruzar.release();
            semEnPuerto.release();
        }
        semMutex.release();
    }

    //PASO 2
    public void ir() {
        try {
            semCruzar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
        semEspacio.release(espacioOcupado);
    }
    //PASO 3

    public void bajar() {
        try {
            semEnDestino.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("(!!!) EN DESTINO!");

    }

    public void liberaRampa() {
        try {
            semMutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }
        espacioOcupado--;
        if (espacioOcupado == 0) {
            System.out.println("(!!!) Se descargaron los coches");//DEBUG
            semCruzar.release();
        }
        semMutex.release();
        semEnDestino.release();
    }

    public void volver() {
        semEnDestino.release();
        try {
            semCruzar.acquire();
            semEnPuerto.acquire();
            //System.out.println("El buque esta en el puerto");
        } catch (InterruptedException ex) {
            Logger.getLogger(Buque.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
