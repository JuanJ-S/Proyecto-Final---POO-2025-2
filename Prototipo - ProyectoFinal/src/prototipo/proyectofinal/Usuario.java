package prototipo.proyectofinal;

//Clase Abstracta para el tipo de usuario del sistema
public abstract class Usuario {
    int id;
    String Contraseña;
    
    public abstract void iniciarSesion();
    public abstract void cerrarSesion();
}
