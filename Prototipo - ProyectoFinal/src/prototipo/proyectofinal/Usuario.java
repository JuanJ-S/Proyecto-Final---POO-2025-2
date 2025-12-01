package prototipo.proyectofinal;

//Clase Abstracta para el tipo de usuario del sistema
public abstract class Usuario {
    private int id;
    private String Contraseña;
    
    public abstract void iniciarSesion();
    public abstract void cerrarSesion();
    
    //Constructores
    public Usuario() {
    }

    public Usuario(int id, String Contraseña) {
        this.id = id;
        this.Contraseña = Contraseña;
    }
    
    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
}
