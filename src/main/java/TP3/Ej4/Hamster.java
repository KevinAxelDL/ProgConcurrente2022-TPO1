/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP3.Ej4;

/**
 *
 * @author KevinDL
 */
public class Hamster implements Runnable {

    //Hilo
    private String id;
    private Plato plato;
    private Rueda rueda;
    private Hamaca hamaca;

    public Hamster(String id, Plato plato, Rueda rueda, Hamaca hamaca) {
        this.id = id;
        this.plato = plato;
        this.rueda = rueda;
        this.hamaca = hamaca;
    }

    public String getId() {
        return id;
    }

    private void iniciarActividades() {
        //Un loop para seleccionar y hacer actividades
        int actividad;

        while (true) {
            actividad = 0 + (int)(Math.random() * ((2 - 0) + 1));

            switch (actividad) {
                case 0:
                    System.out.println("(*) "+ this.id +" ESPERANDO POR PLATO");
                    this.plato.usar(this);
                    break;
                case 2:
                    System.out.println("(*) "+ this.id +" ESPERANDO POR RUEDA");
                    this.rueda.usar(this);
                    break;
                case 3:
                    System.out.println("(*) "+ this.id +" ESPERANDO POR HAMACA");
                    this.hamaca.usar(this);
                    break;
            }
        }
    }
    
    public void run(){
        iniciarActividades();
    }
}
