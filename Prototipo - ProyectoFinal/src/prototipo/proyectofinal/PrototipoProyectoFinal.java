package prototipo.proyectofinal;
import java.util.Scanner;

public class PrototipoProyectoFinal{
    static Scanner scanner = new Scanner(System.in);
    
    
    public static void main(String[] args) {
    new LoginFrame(); // Lanzar la GUI en lugar de la consola
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
