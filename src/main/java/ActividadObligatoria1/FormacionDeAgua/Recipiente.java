/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActividadObligatoria1.FormacionDeAgua;

/**
 *
 * @author agust
 */
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipiente {
    //OBJETO PASIVO 

    //semaforos de espera
    private Semaphore semEsperaO = new Semaphore(1);
    private Semaphore semEsperaH = new Semaphore(2);
    private Semaphore semAguaLista = new Semaphore(0);//Comunicacion con hidrogeno

    private Semaphore semCantO = new Semaphore(0);//Comunicacion con oxigeno
    private Semaphore semCantH = new Semaphore(0);//Comunicacion con oxigeno

    private Semaphore semMutex = new Semaphore(1);

    private int cantActAgua = 0;
    private int cantMaxAgua;

    public Recipiente(int cantMaxAgua) {
        this.cantMaxAgua = cantMaxAgua;
    }

    //Metodos para Oxigeno
    
    public void hacerO(String id) {

        try {
            semEsperaO.acquire();//Espera su turno
        } catch (InterruptedException ex) {
            Logger.getLogger(Recipiente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("(OXL!) " + id + " creo oxigeno");//DEBUG
        semCantO.release();//Oxigeno listo
    }

    public void hacerAgua() {
        try {
            //Permisos necesarios para poder hacer agua
            semCantO.acquire(1);
            semCantH.acquire(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Recipiente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            semMutex.acquire();//EXM
            cantActAgua++;
            System.out.println("("+ this.cantActAgua +"/"+ this.cantMaxAgua +") recipiente llenado");//DEBUG
            if (cantActAgua == cantMaxAgua) {
                System.out.println("("+ this.cantActAgua +"/"+ this.cantMaxAgua +") recipiente LLENO!");//DEBUG
                cantActAgua = 0;//Se reinicia el contador
                System.out.println("("+ this.cantActAgua +"/"+ this.cantMaxAgua +") recipiente VACIADO!");//DEBUG
            }
            semMutex.release();//EXM
        } catch (InterruptedException ex) {
            Logger.getLogger(Recipiente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        semAguaLista.release(2);//Libera la ejecucion de los hidrogenos comprometidos
        semEsperaO.release();//Permite avanzar al siguiente oxigeno

    }

    // Metodos para Hidrogeno
    
    public void hacerH(String id) {
        try {
            this.semEsperaH.acquire();//Espera su turno
        } catch (InterruptedException ex) {
            Logger.getLogger(Hidrogeno.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("(HXL!) " + id + " creo hidrogeno");//DEBUG
        this.semCantH.release();//Hidrogeno listo

        try {
            semAguaLista.acquire();//Espera a que se termine de hacer agua
        } catch (InterruptedException ex) {
            Logger.getLogger(Hidrogeno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        semEsperaH.release();//Permite avanzar al siguiente hidrogeno
    }

}
