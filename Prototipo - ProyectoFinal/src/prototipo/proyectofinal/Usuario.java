package prototipo.proyectofinal;

//Clase Abstracta para el tipo de usuario del sistema
public abstract class Usuario {
    private int id; //Identificación del Ususario en el Sistema
    private String Contraseña; //Contraseña del usuario para acceder al sistema
    
    public abstract void iniciarSesion(); ////Se recibe un id y se busca en la base de datos, si esta en la BD se compara la constraseña dada con la relacionada al id
        //Si ambos datos dados coinciden, se le da acceso al Ususario al sistema
    public abstract void cerrarSesion(); //Se cierra el acceso al sistema
    
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
