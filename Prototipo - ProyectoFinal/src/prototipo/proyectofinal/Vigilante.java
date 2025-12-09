package prototipo.proyectofinal;
import java.util.Scanner;

public class Vigilante extends Usuario{
    
    //Métodos personalizados
    
    public void notificarEntrante(){
        throw new UnsupportedOperationException("NotificarEntrante Not supported yet.");
    }
    public void registrarSalida(){
        throw new UnsupportedOperationException("RegistrarSalida Not supported yet.");
    }
    public void registrarEntrega(){
        throw new UnsupportedOperationException("RegistrarEntrega Not supported yet.");
    }
    //Métodos Heredados
    @Override
    public void menu(Scanner scanner){
        int opcion = 0;
        do{
            System.out.println("¿Que quieres hacer?: \n\t1.Notificar Entrante\n\t2.Registrar Salida\n\t3.Registrar Entrega\n\t4.Cerrar Sesion");
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                    notificarEntrante();
                    break;
                case 2:
                    registrarSalida();
                    break;
                case 3:
                    registrarEntrega();
                    break;
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(opcion != 4);
    }
    
    //Constructor
    public Vigilante() {
    }

    public Vigilante(String nombres, String Apellidos, int idSistema) {
        super(nombres, Apellidos, idSistema);
    }
    
}