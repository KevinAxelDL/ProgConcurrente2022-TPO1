/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP3.Ej4;

/**
 *
 * @author KevinDL
 */
public class Plato {
    //Rec. compartido
    
    public Plato() {
    }
    
    public synchronized void usar(Hamster ham){
        try{
            System.out.println("(...) PLATO EN USO POR "+ ham.getId());
            Thread.sleep(1500);
            System.out.println("(!) "+ ham.getId() +" LIBERO EL PLATO");
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
    
}
