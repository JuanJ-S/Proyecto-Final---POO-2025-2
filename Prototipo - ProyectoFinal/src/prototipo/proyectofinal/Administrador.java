package prototipo.proyectofinal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Administrador extends Usuario implements ConsultarBD{
    private Scanner scanner = new Scanner(System.in);
    //Métodos Personalizados
    
    public void consultarApto(){
        int torre = 0;
        int numero = 0;
        System.out.print("Digita la torre del apto: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apto: ");
        numero = scanner.nextInt();
        System.out.print(BaseDeDatos.consultarApto(torre, numero));
    }
    public void consultarResidente(){
        int torre = 0;
        int numero = 0;
        int idApto = 0;
        System.out.print("Digita la torre del apto: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apto: ");
        numero = scanner.nextInt();
        idApto = BaseDeDatos.obtenerIdApto(torre, numero);
        System.out.print(BaseDeDatos.consultarResidente(idApto));
    }
    public void registrarApto(){
        int torre = 0;
        int numero = 0;
        String propietario = null;
        int idPropietario = 0;
        boolean isArrendado = false;
        System.out.print("Ingrese la torre del apartamento: ");
        torre = scanner.nextInt();
        System.out.print("Ingrese el número del Apartamento: ");
        numero = scanner.nextInt();
        System.out.print("Ingrese el nombre del propietario: ");
        scanner.next(); //Limpiar\n de nextInt
        propietario = scanner.next();
        System.out.print("Ingrese ID del propietario: ");
        idPropietario = scanner.nextInt();
        System.out.print("¿El apartamento esta arrendado? (1. si  2.no): ");
        isArrendado = scanner.nextBoolean();
        BaseDeDatos.registrarApto(torre, numero, propietario, idPropietario, isArrendado);
    }
    public void registrarResidente(){
        int torre = 0;
        int numero = 0;
        int idApto = 0;
        String nombres = null;
        String apellidos = null;
        int idResidente = 0;
        int idSesion = 0;
        System.out.print("Digita la torre del apto: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apto: ");
        numero = scanner.nextInt();
        idApto = BaseDeDatos.obtenerIdApto(torre, numero);
        System.out.print("Ingrese los nombres del residente: ");
        nombres = scanner.next();
        System.out.print("Ingrese los apellidos del residente: ");
        apellidos = scanner.next();
        System.out.print("Ingrese el ID del residente: ");
        idResidente = scanner.nextInt();
        System.out.print("Ingrese el ID de Sesion del residente: ");
        idSesion = scanner.nextInt();
        BaseDeDatos.registrarResidente(nombres, apellidos, idResidente, idApto, idSesion);  // Corregido: Apellidos -> apellidos
    }
    
    //Métodos Heredados
    
    @Override
    public void menu(){
        int opcion = 0;
        do{
            System.out.println("¿Que quieres hacer?: \n\t1.Consultar Visitantes\n\t2.Consultar Paquetes\n\t3.Consultar Apartamento\n\t4.Consultar Residente\n\t5.Registrar Apartamento\n\t6.Registrar Residente\n\t7.Cerrar Sesion");
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                    consultarVisitantes();
                    break;
                case 2:
                    consultarPaquetes();
                    break;
                case 3:
                    consultarApto();
                    break;
                case 4:
                    consultarResidente();
                    break;
                case 5:
                    registrarApto();
                    break;
                case 6:
                    registrarResidente();
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(opcion != 7);
    }
    
    @Override
    public void consultarVisitantes() {
        int torre = 0;
        int numero = 0;
        int idApto = 0;
        System.out.print("Digita la torre del apto: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apto: ");
        numero = scanner.nextInt();
        idApto = BaseDeDatos.obtenerIdApto(torre, numero);
        BaseDeDatos.consultarVisitantes(idApto);
    }

    @Override
    public void consultarPaquetes() {
        int torre = 0;
        int numero = 0;
        int idApto = 0;
        int opcion = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.print("Digita la torre del apto: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apto: ");
        numero = scanner.nextInt();
        idApto = BaseDeDatos.obtenerIdApto(torre, numero);
        do{
            System.out.println("1. Ver todos los paquetes\n2.Ver paquetes por día");
            opcion = scanner.nextInt();
            if(opcion != 1 & opcion != 2){
                System.out.println("Opcion Invalida vuelve a digitar");
            }
        } while(opcion != 1 & opcion != 2);
        if(opcion == 1){
            BaseDeDatos.consultarPaquetes(idApto);
        } else if(opcion == 2){
            System.out.print("Ingresa la fecha que quieres consultar (Formato: AAAA-MM-DD): ");  // Corregido: Simplificado a YYYY-MM-DD
            String dia = scanner.next();  // Corregido: Cambiado a String, sin parseo
            BaseDeDatos.consultarPaquetesPorDia(idApto, dia);  // Pasa String
        }
    }
    
    //Contructores
    public Administrador() {
    }

    public Administrador(String nombres, String Apellidos, int idSistema) {
        super(nombres, Apellidos, idSistema);
    }
    
}