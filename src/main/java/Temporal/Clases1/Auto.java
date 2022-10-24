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
public class Auto implements Runnable {
    //OBJETO ACTIVO

    private Buque buque;
    private String id;

    public Auto(String id, Buque buque) {
        this.id = id;
        this.buque = buque;
    }

    public void run() {
        buque.subir();
        this.subiendo();
        buque.estacionar();
        buque.bajar();
        this.bajando();
        buque.liberaRampa();

    }

    private void subiendo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void bajando() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
