package prototipo.proyectofinal;
import java.util.Scanner;

public class Residente extends Usuario implements ConsultarBD{
    private int idResidente;
    private int idApto;
    
    //Métodos personalizados
    public void aprobarVisita(){
        throw new UnsupportedOperationException("aprobarVisita Not supported yet.");
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
                    aprobarVisita();
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
        }while(true);
    }

    @Override
    public void consultarVisitantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarPaquetes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
