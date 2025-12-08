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
    //Adiccion de nueva funcion de los metodos- vigilante registra la salida del visitante
    public void registrarSalida(){
        system.out.print("ID del visitante para registrar salida: ");
        int id= scanner.nextInt();
        scanner.nextLine();

        Visitante v = new Visitante();
        V.setIdVisitante(id);
        V.registrarSalida();
    }
    public void registrarEntrega(){
        throw new UnsupportedOperationException("RegistrarEntrega Not supported yet.");
    }
    //Métodos Heredados
    @Override
    public void menu(Scanner scanner){
        int opcion = 0;
        do{
    //orden en el system.out
            System.out.println("\n=== MENU VIGILANTE ===");
            System.out.println("1. Notificar Entrante");
            System.out.println("2. Registrar Salida");
            System.out.println("3. Registrar Entrega");
            System.out.println("4. Cerrar Sesión");
            System.out.print("Opcion: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
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