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

    //IMPLEMENTACION CON MONITORES
    public static void main(String[] args) {
        int cantTrabajadores = 5;
        GeneradorIds genIdTrabajadores = new GeneradorIds("TRABAJADOR");

        //1ra cama
        Cama cama1 = new Cama(null);//Obj.Pasivo
        Trabajador obj = new Trabajador(genIdTrabajadores.generarUnId(), cama1, generadorHorario());//Obj.Activo
        System.out.println("(?) " + obj.getId() + " trabaja a las " + obj.getHoraTrabajo() + " hs");//DEBUG
        Thread t1 = new Thread(obj);
        t1.start();
        
        //cantTrabajadores - 1 camas
        for (int i = 0; i < cantTrabajadores - 1; i++) {
            Cama cama2 = new Cama(cama1);//Obj.Pasivo
            Trabajador obj2 = new Trabajador(genIdTrabajadores.generarUnId(), cama2, generadorHorario());//Obj.Activo
            System.out.println("(?) " + obj2.getId() + " trabaja a las " + obj2.getHoraTrabajo() + " hs");//DEBUG
            Thread t2 = new Thread(obj2);
            t2.start();
            cama1 = cama2;//Proxima referencia
        }
        
        //
        Reloj reloj = new Reloj(cama1);//Obj.Activo
        Thread t3 = new Thread(reloj);
        t3.start();
    }

    public static int generadorHorario() {
        return ((int) (Math.random() * 23));//24 hs disponibles
    }

}
