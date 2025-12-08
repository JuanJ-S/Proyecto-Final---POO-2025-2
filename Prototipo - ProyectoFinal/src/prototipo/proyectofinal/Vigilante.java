package prototipo.proyectofinal;
import java.util.Scanner;

public class Vigilante extends Usuario{
    Scanner scanner = new Scanner(System.in);
    
    //Métodos personalizados
    
    public void notificarEntrante(){
        Entrante entrante = null;
        int opcion = 0;
        do{
            System.out.print("¿Que se va a notificar?\n\t1.Visitante\n\t2.Paquete");
            opcion = scanner.nextInt();
        }while(opcion < 1 | opcion > 2);
        if(opcion == 1){
            entrante = new Visitante();
        } else{
            entrante = new Paquete();
        }
        entrante.notificarLlegada();
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
                case 4:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(true);
    }
    
    //Constructor
    public Vigilante() {
    }

    public Vigilante(String nombres, String Apellidos, int idSistema) {
        super(nombres, Apellidos, idSistema);
    }
    
}