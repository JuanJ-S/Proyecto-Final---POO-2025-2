package prototipo.proyectofinal;
import java.util.Scanner;

public class Residente extends Usuario implements ConsultarBD{
    private Scanner scanner = new Scanner(System.in);
    private int idResidente;
    private int idApto;
    
    //Métodos personalizados
    private void aprobarVisita(){
        int idVisitante = 0;
        System.out.print("Digita el ID del Visitante que quieres aprobar: ");
        idVisitante = scanner.nextInt();
        BaseDeDatos.aprobarVisita(idApto, idVisitante);
    }
    
    //Métodos Heredados

    //Esto va en ventana
    @Override
    public void menu(){
        int opcion = 0;
        do{
            System.out.println("¿Que quieres hacer?: \n\t1.Aprobar Visita\n\t2.Consultar Visitas\n\t3.Consultar Paquetes\n\t4.Cerrar Sesion");
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                    aprobarVisita();
                    break;
                case 2:
                    consultarVisitantes();
                    break;
                case 3:
                    consultarPaquetes();
                    break;
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(opcion != 4);
    }

    @Override
    public void consultarVisitantes() {
        System.out.print(BaseDeDatos.consultarVisitantes(idApto));
    }

    @Override
    public void consultarPaquetes() {
        int opcion = 0;
        do{
            System.out.print("1. Consultar Todos los Paquetes\n\t2.Consultar Paquetes por día");
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                    System.out.print(BaseDeDatos.consultarPaquetes(idApto));
                case 2:
                    System.out.println("Ingresa el día que quieres consultar (Año-Mes-Dia)");
                    String dia = scanner.next();
                    System.out.print(BaseDeDatos.consultarPaquetesPorDia(idApto, nombres));
                default:
                    System.out.println("Opcion Invalida, vuelve a elegir");
            }
        }while(opcion < 1 | opcion > 2);
    }
    
    //Constructores

    public Residente() {
    }

    public Residente(int idResidente, int idApto, String nombres, String Apellidos, int idSistema) {
        super(nombres, Apellidos, idSistema);
        this.idResidente = idResidente;
        this.idApto = idApto;
    }
    
    //Getters y Setters
    
    
    public int getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(int idResidente) {
        this.idResidente = idResidente;
    }

    public int getIdApto() {
        return idApto;
    }

    public void setIdApto(int idApto) {
        this.idApto = idApto;
    }
}
