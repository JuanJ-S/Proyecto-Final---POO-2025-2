package prototipo.proyectofinal;
import java.util.Scanner;

public class Residente extends Usuario implements ConsultarBD{
    private int idResidente;
    private int idApto;
    private Scanner scanner = new Scanner(System.in);
    
    //Métodos personalizados
    public void aprobarVisita(int idApto){ //Esto va en ventana
        int opcion = 0;
        System.out.println("Ingresa el ID del visitante que quieras aprobar: ");
        BaseDeDatos.consultarVisitasPendientes(idApto);
        opcion = scanner.nextInt();
        BaseDeDatos.aprobarVisita(idApto, opcion);
    }
    
    //Métodos Heredados

    //Esto va en ventana
    @Override
    public void menu(Scanner scanner){
        int opcion = 0;
        do{
            System.out.println("¿Que quieres hacer?: \n\t1.Aprobar Visita\n\t2.Consultar Visitas\n\t3.Consultar Paquetes\n\t4.Cerrar Sesion");
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                    aprobarVisita(idApto);
                    break;
                case 2:
                    consultarVisitantes();
                    break;
                case 3:
                    consultarPaquetes();
                    break;
                case 4:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(opcion != 4);
    }

    @Override
    public void consultarVisitantes() {
        BaseDeDatos.consultarVisitantes(idApto);
    }

    @Override
    public void consultarPaquetes() {
        BaseDeDatos.consultarPaquetes(idApto);
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
