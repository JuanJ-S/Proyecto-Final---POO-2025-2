package prototipo.proyectofinal;
import java.util.Scanner;

public class Administrador extends Usuario implements ConsultarBD{
    Scanner scanner = new Scanner(System.in);
    //Métodos Personalizados
    
    public void consultarApto(){
        int torre = 0;
        int numero = 0;
        int idApto = 0;
        System.out.print("Digita el numero de torre: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apartamento: ");
        numero = scanner.nextInt();
        idApto = BaseDeDatos.idApto(torre, numero);
        BaseDeDatos.consultarApto(idApto);
    }
    public void consultarResidente(){
        int torre = 0;
        int numero = 0;
        int idApto = 0;
        System.out.print("Digita el numero de torre: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apartamento: ");
        numero = scanner.nextInt();
        idApto = BaseDeDatos.idApto(torre, numero);
        BaseDeDatos.consultarResidente(idApto);
    }
    public void registrarApto(){
        int torre = 0;
        int numero = 0;
        String propietario = "";
        int idPropietario = 0;
        int opcion = 0;
        boolean isArrendado = false;
        System.out.print("Ingresa el numero de torre del Apto: ");
        torre = scanner.nextInt();
        System.out.print("\nIngresa el numero del Apto: ");
        numero = scanner.nextInt();
        System.out.print("\n¿El Apartamento esta arrendado?: \n\t1.Si\n\t2.No\n");
        opcion = scanner.nextInt();
        System.out.print("\nIngresa el nombre completo del propietario: ");
        scanner.nextLine();
        propietario = scanner.next();
        System.out.print("\nIngresa el ID del propietario: ");
        idPropietario = scanner.nextInt();
        if(opcion == 1){isArrendado = true;} else{isArrendado = false;}
        System.out.println(BaseDeDatos.registrarApto(torre, numero, propietario, idPropietario, isArrendado));
    }
    public void registrarResidente(){
        throw new UnsupportedOperationException("registrarResidente Not supported yet.");
    }
    
    //Métodos Heredados
    
    @Override
    public void menu(Scanner scanner){
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
                case 7:
                    cerrarSesion();
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(true);
    }
    
    @Override
    public void consultarVisitantes() {
        int torre = 0;
        int numero = 0;
        int idDestino = 0;
        System.out.print("Digita el numero de torre: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apartamento: ");
        numero = scanner.nextInt();
        idDestino = BaseDeDatos.idApto(torre, numero);
        BaseDeDatos.consultarVisitantes(idDestino);
    }

    @Override
    public void consultarPaquetes() {
        int torre = 0;
        int numero = 0;
        int idDestino = 0;
        System.out.print("Digita el numero de torre: ");
        torre = scanner.nextInt();
        System.out.print("\nDigita el numero del apartamento: ");
        numero = scanner.nextInt();
        idDestino = BaseDeDatos.idApto(torre, numero);
        BaseDeDatos.consultarPaquetes(idDestino);
    }
    
    //Contructores
    public Administrador() {
    }

    public Administrador(String nombres, String Apellidos, int idSistema) {
        super(nombres, Apellidos, idSistema);
    }
    
}
