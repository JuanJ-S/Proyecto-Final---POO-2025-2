package prototipo.proyectofinal;
import java.util.Scanner;

public class Administrador extends Usuario implements ConsultarBD{
    
    //Métodos Personalizados
    
    public void consultarApto(){
        throw new UnsupportedOperationException("ConsultarApto Not supported yet.");
    }
    public void consultarResidente(){
        throw new UnsupportedOperationException("ConsultarResidente Not supported yet.");
    }
    public void registrarApto(){
        throw new UnsupportedOperationException("RegistrarApto Not supported yet.");
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
                default:
                    System.out.println("Opcion invalida, Elije una de las opciones disponibles");
                    break;
            }
        }while(opcion != 7);
    }
    
    @Override
    public void consultarVisitantes() {
        throw new UnsupportedOperationException("ConsultarVisitantes Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarPaquetes() {
        throw new UnsupportedOperationException("Consultar Paquetes Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Contructores
    public Administrador() {
    }

    public Administrador(String nombres, String Apellidos, int idSistema) {
        super(nombres, Apellidos, idSistema);
    }
    
}
