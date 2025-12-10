package prototipo.proyectofinal;
import java.util.Scanner;

//Clase Abstracta para el tipo de usuario del sistema
public abstract class Usuario{
    String nombres;
    String Apellidos;
    int idSistema;
    
    //Métodos Personalizados
    boolean iniciarSesion(int idSesion, String contraseña, int rol) { //Convoca a inicioSesion para conceder acceso al programa
        boolean acceso = BaseDeDatos.inicioSesion(idSesion, contraseña, rol);
        return acceso;
    }
    
    public abstract void menu(); //Abstracto para el menu de cada clase
    
    //constructores
    public Usuario() {
    }
    
    public Usuario(String nombres, String Apellidos, int idSistema) {
        this.nombres = nombres;
        this.Apellidos = Apellidos;
        this.idSistema = idSistema;
    }
    
    
    //Getters y Setters

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }
  
}
