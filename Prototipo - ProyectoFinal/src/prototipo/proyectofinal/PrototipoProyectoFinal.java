package prototipo.proyectofinal;
import java.util.Scanner;

public class PrototipoProyectoFinal{
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Usuario usuario = new Residente();
        int rol = elegirRol(); //Guarda el rol elegido
        int idSesion = 0;
        boolean acceso = false; //Confirma si la sesion es valida
        do{ //Inicio de Sesion - Implementar GUI
            switch(rol){ //Identificar el tipo de Usuario
                case 1:
                    usuario = new Administrador();
                    break;
                case 2:
                    usuario = new Residente();
                    break;
                case 3:
                    usuario = new Vigilante();
                    break;
                default:
                    System.out.println("Error con el rol escogido, no hay clase de usuario asignada");
                    break;
            }
            System.out.print("Digita tu ID de inicio de Sesion: ");
            idSesion = scanner.nextInt();
            System.out.print("\nDigita tu clave de usuario: ");
            String contraseña = scanner.next();
            acceso = usuario.iniciarSesion(idSesion, contraseña, rol);
            if(acceso==false){
                System.out.println("Datos dados incorrectos, vuelve a intentarlo");
                rol = elegirRol();
            }
        }while(acceso==false);
        //Crear objeto y acceder a menu de funciones
        if(usuario instanceof Residente){
            Residente residente = BaseDeDatos.construirResidente(idSesion);
            residente.menu(scanner);
        } else if(usuario instanceof Vigilante){
            Vigilante vigilante = BaseDeDatos.construirVigilante(idSesion);
            vigilante.menu(scanner);
        } else if(usuario instanceof Administrador){
            Administrador admin = BaseDeDatos.construirAdmin(idSesion);
            admin.menu(scanner);
        }
    }
    //Implementar en GUI
    private static int elegirRol(){
        int rol = 0; //Variable de Retorno
        do{
            System.out.println("Selecciona tu cargo:\n\t1.Administrador\n\t2.Residente\n\t3.Vigilante");
            System.out.print("Digita tu eleccion: ");
            rol = scanner.nextInt();
            if(rol<1|rol>3){
                System.out.println("\nEleccion invalida, Vuelve a digitar\n");
            } else{
                System.out.println("Se ha elegido un rol");
            }
        }while(rol<1|rol>3);
        return rol;
    }
}
