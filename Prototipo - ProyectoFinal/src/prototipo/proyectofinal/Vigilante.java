package prototipo.proyectofinal;

//Encargado de registrar la salida y entrada de visitas, ademas de los paquetes
public class Vigilante extends Usuario{
    //Métodos Personalizados
    public void notificarEntrante(Entrante entrante){
        //Notificar al destino de la visita/paquete
        System.out.println("Entrante");
    }
    public void registrarEntrante(Entrante entrante){
        //Registrar el entrante en la base de datos, si es visita debe ser aprobada por el destino primero
        System.out.println("Entrante Registrado");
    }
    public void registrarSalida(Entrante entrante){
        //Registrar hora de salida de la visita
        System.out.println("Saliente");
    }

    //Acceso al Sistema - Herencia
    @Override
    public void iniciarSesion() {
        throw new UnsupportedOperationException("Inicio de Sesion"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Cerrado de Sesion"); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Constructores
    public Vigilante() {
    }
    public Vigilante(int id, String Contraseña) {
        super(id, Contraseña);
    }
}