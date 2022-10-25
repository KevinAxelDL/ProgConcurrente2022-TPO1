/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActividadObligatoria1.RelojAlarma;

import Herramientas.GeneradorIds;

/**
 *
 * @author KevinDL
 */
public class Main {

    public static void main(String[] args) {
        int cantTrabajadores = 5;
        GeneradorIds genIdTrabajadores = new GeneradorIds("TRABAJADOR");
        Despertador despertador = new Despertador();//Obj.Pasivo
        Reloj reloj = new Reloj(despertador);//Obj.Activo
        
        //
        Thread t1 = new Thread(reloj);
        t1.start();
        
        for(int i = 0; i < cantTrabajadores; i++){
            Trabajador obj = new Trabajador(genIdTrabajadores.generarUnId(), despertador, generadorHorario());//Obj.Activo
            System.out.println("(?) "+ obj.getId() +" trabaja a las "+ obj.getHoraTrabajo() +" hs");//DEBUG
            Thread t2 = new Thread(obj);
            t2.start();
        }
    }
    
    public static int generadorHorario(){
        return ((int)(Math.random()*23));//24 hs disponibles
    }

}
