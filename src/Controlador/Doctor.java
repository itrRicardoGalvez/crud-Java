package Controlador;

public class Doctor {
    
    String nombre;
    String colorPiel;
    int edad;
    double salario;
    String tipoSangre;
    
    //Constructor
    public Doctor(String nombreQuePido, int edadQuePido){
        this.nombre = nombreQuePido;
        this.edad = edadQuePido;
    }
    
      public Doctor(String color){
        this.colorPiel = color;
    }
    
    
    
}
