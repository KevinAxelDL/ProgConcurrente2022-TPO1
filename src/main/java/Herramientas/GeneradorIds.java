/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herramientas;

/**
 *
 * @author KevinDL
 */
public class GeneradorIds {
    //Clase para generar IDs 
    /*
    La idea de esta clase es facilitar la creacion de IDs para instancias de 
    objetos. Cada instancia de esta clase sirve como una plantilla para X tipo 
    de ID deseado. Cada instancia tiene un contador con el fin de usarlo para 
    diferenciar entre diferentes IDs que provienen de la misma instancia 
    generadora.
    Ejemplo:
    Si se necesitan muchos IDs para diferenciar entre alumnos, se necesitara 
    crear una instancia de GeneradorIds con nombreRef = ALUMNO, y por cada ID 
    que se desea generar se debe usar el mensaje generarUnId().
    Los IDs resultantes serian:
        ALUMNO-0, ALUMNO-1, ALUMNO-3, etc.
    */
    private int contadorRef;
    private String nombreRef;
    
    public GeneradorIds(String nombre){
        //Constructor
        this.nombreRef = nombre.toUpperCase();
        this.contadorRef = 0;
    }
    
    public String generarUnId(){
        //Retorna un String
        String id = (this.nombreRef+ "-" + Integer.toString(this.contadorRef));
        this.contadorRef++;
        return id;
    }
}
