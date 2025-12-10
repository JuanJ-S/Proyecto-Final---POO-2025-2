package prototipo.proyectofinal;
import java.util.Scanner;

public class Vigilante extends Usuario{
    private Scanner scanner  = new Scanner(System.in);
    //Métodos personalizados
    
    public void notificarEntrante(){
        int idVisitante = 0;
        String nombres = null;
        String apellidos = null;
        int torre = 0;
        int numero = 0;
        int idDestino = 0;
        System.out.print("Ingresa el ID del visitante: ");
        idVisitante = scanner.nextInt();
        System.out.print("Ingresa los nombres del Visitante: ");
        scanner.next(); //Limpiar \n del nextInt
        nombres = scanner.next();
        System.out.print("Ingresa los apellidos del Visitante: ");
        apellidos = scanner.next();
        System.out.print("Ingresa la torre del apartamento de destino: ");
        torre = scanner.nextInt();
        System.out.println("Ingresa el número del apartamento de destino: ");
        numero = scanner.nextInt();
        idDestino = BaseDeDatos.obtenerIdApto(torre, numero);
        BaseDeDatos.registrarVisitante(idVisitante, nombres, apellidos, idDestino);
    }
    public void registrarSalida(){
        int idVisitante = 0;
        System.out.print("Ingresa el ID del visitante: ");
        idVisitante = scanner.nextInt();
        BaseDeDatos.registrarSalida(idVisitante);
    }
    public void registrarEntrega(){
        int idPaquete = 0;
        System.out.print("Ingresa el ID del Paquete: ");
        idPaquete = scanner.nextInt();
        BaseDeDatos.registrarEntrega(idPaquete);
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