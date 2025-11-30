package prototipo.proyectofinal;
import java.util.Scanner;

//Residente del conjunto
public class Residente extends Usuario{
    Apto apartamento;
    String nombre;
    Scanner scanner;
    
    boolean aprobarVisitante(){
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
    
    void consultarVisitas(){
        //El residente puede consultar las visitas de la semana de su apto
        System.out.print("Visitas Vistas :D");
    }

    //Ingreso al Sistema
    
    @Override
    public void iniciarSesion() {
        throw new UnsupportedOperationException("Inicio de Sesion"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Cerrado de Sesion"); //To change body of generated methods, choose Tools | Templates.
    }
}
