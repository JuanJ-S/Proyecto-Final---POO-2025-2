package prototipo.proyectofinal;
import java.util.Scanner;

public class Residente extends Usuario{
    private Apto apartamento;
    private String nombre;
    private Scanner scanner;
    private Paquete[] paquetesPendientes;
    private Visitante[] visitasPendientes;
    
    //Métodos Personalizados
    public boolean aprobarVisitante(Visitante visitante){ //Aprobar o no la entrada de un vivistante
        boolean isAprobado = false;
        System.out.println("Nueva visita: "+"Datos de la Visita");
        System.out.println("Desea aprobar la visita? (1. si, 2. no)");
        int desicion = scanner.nextInt();
        switch(desicion){
            case 1:
                isAprobado = true;
            case 2:
                isAprobado = false;
            default:
                System.out.print("Opción Invalida");
        }
        return isAprobado;
        //Cambiar isAprobado en Visitante
    }
    
    public void consultarVisitas(){
        //Consultar la lista de visitas hechas hoy al apto
        System.out.print("Visitas Vistas :D");
    }

    //Ingreso al Sistema - Herencia
    @Override
    public void iniciarSesion() {
        throw new UnsupportedOperationException("Inicio de Sesion"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Cerrado de Sesion"); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Constructores
    public Residente() {
    }
    public Residente(Apto apartamento, String nombre, int id, String Contraseña) {
        super(id, Contraseña);
        this.apartamento = apartamento;
        this.nombre = nombre;
    }
    
    //Getters y Setters
    public Apto getApartamento() {
        return apartamento;
    }
    public void setApartamento(Apto apartamento) {
        this.apartamento = apartamento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Paquete[] getPaquetes() {
        return paquetesPendientes;
    }

    public void setPaquetes(Paquete[] paquetesPendientes) {
        this.paquetesPendientes = paquetesPendientes;
    }

    public Visitante[] getVisitas() {
        return visitasPendientes;
    }

    public void setVisitas(Visitante[] visitas) {
        this.visitasPendientes = visitas;
    }
    
}
