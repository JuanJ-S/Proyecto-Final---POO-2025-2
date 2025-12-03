package prototipo.proyectofinal;

public class Vigilante extends Usuario{
    //Métodos Personalizados
    public void notificarEntrante(Entrante entrante){ //Notificar Visita/Paquete al Residente
        entrante.notificarLlegada(); //Llama a notificarLlegada del entrante
    }
    public void registrarEntrante(Entrante entrante){ //Registra Visita/Paquete en el Sistema
        entrante.registrarEntrada(); //Llama a registrarEntrada del entrante
    }
    public void registrarSalida(Entrante entrante){ //Registra Salida o Entrega del Entrante
        System.out.println("Saliente"); //Llamar a la funcion registrarSalida
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