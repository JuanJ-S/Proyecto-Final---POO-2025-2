package prototipo.proyectofinal;
import java.util.Scanner;

//Residente del conjunto
public class Residente extends Usuario{
    private Apto apartamento;
    private String nombre;
    private Scanner scanner;
    
    //Métodos Personalizados
    public boolean aprobarVisitante(){
        //El residente puede dejar entrar o no dejar entrar a la visita notificada
        boolean isAprobado = false;
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
    }
    
    public void consultarVisitas(){
        //El residente puede consultar las visitas de la semana de su apto
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
}
