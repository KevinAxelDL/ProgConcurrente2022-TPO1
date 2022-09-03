/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 *
 * @author KevinDL
 */
public class Sumador implements Runnable{
    //Atributos
    String id;
    static int SUMA_TOTAL = 0; //Deberia resetearse a 0 esta variable antes de terminar una ejecucion del programa 
    static int REFERENCIA_ID = 0;
    int[] arregloCompartido;
    int posIn, posFin;
    
    //
    public Sumador(int[] arr, int posIn, int posFin ){
        
        this.id = crearId();
        this.arregloCompartido = arr;
    }
    
    private String crearId(){
        //Crea el ID del hilo
        String id = ("HILO-"+ Sumador.REFERENCIA_ID + 1);
        Sumador.REFERENCIA_ID++;
        return id;
    }
    
    private int Sumar(){
        //Suma los elementos del arreglo de cierto intervalo
        int sumaLocal = 0;
        
        for(int i = this.posIn; i <= this.posFin; i++){
            System.out.println("posIn: "+ this.posIn + " posFin: "+ this.posFin);//DEBUG
            sumaLocal = sumaLocal + this.arregloCompartido[i];
        }
        System.out.println(sumaLocal);//DEBUG
        
        return sumaLocal;
    }
    
    private synchronized static  void sumarTotal(int sumaLocal){
        //Modifica la suma total (atributo de clase)
        //Sincronizado
        Sumador.SUMA_TOTAL = Sumador.SUMA_TOTAL + sumaLocal;  
    }
    
    public String getId(){
        return this.id;
    }
    
    public void run(){
        int sumaLocal;
        
        sumaLocal = Sumar();
        Sumador.sumarTotal(sumaLocal);
        
        System.out.println("(-) EJECUCION DE "+ this.id +" TERMINADA");
    }
}
