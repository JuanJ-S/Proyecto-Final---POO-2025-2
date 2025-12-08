package prototipo.proyectofinal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Visitante implements Entrante{
Scanner scanner = new Scanner(System.in);

    private int idSistema;
    private int idVisitante;
    private String nombres;
    private String apellidos;
    private int idDestino;
    private boolean isAprobado;
    private LocalDateTime fechaDeEntrada;
    private LocalDateTime fechaDesalida;
    
    //Métodos personalizados
    //adiccion de prints en cada metodo para q no lance errores
    public void registrarSalida(){
        System.out.println("Registrando salida del visitante con ID: " + idVisitante);
        fechaDesalida = LocalDateTime.now();    }
    
    //Métodos Heredados

    @Override
    public void notificarLlegada() {
        System.out.println("Notificando llegada de visitante...");
        fechaDeEntrada = LocalDateTime.now();
    }   

    @Override
    public void registrar() {
        System.out.println("Registrando datos del visitante en el sistema...");
    }

//adiccion de registrar entrega (metodo heredado de entrante)
    @Override
    public void registrarEntrega() {
        System.out.println("El visitante no utiliza registrarEntrega().");
    }
    
    //Constructor

    public Visitante() {
    }

    public Visitante(int idSistema, int idVisitante, String nombres, String apellidos, int idDestino, boolean isAprobado, LocalDateTime fechaDeEntrada, LocalDateTime fechaDesalida) {
        this.idSistema = idSistema;
        this.idVisitante = idVisitante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.idDestino = idDestino;
        this.isAprobado = isAprobado;
        this.fechaDeEntrada = fechaDeEntrada;
        this.fechaDesalida = fechaDesalida;
    }
    
    //Getters y Setters

    public int getIdSistema() {
        return idSistema;
    }

    public int getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public boolean isIsAprobado() {
        return isAprobado;
    }

    public void setIsAprobado(boolean isAprobado) {
        this.isAprobado = isAprobado;
    }

    public LocalDateTime getFechaDeEntrada() {
        return fechaDeEntrada;
    }

    public void setFechaDeEntrada(LocalDateTime fechaDeEntrada) {
        this.fechaDeEntrada = fechaDeEntrada;
    }

    public LocalDateTime getFechaDesalida() {
        return fechaDesalida;
    }

    public void setFechaDesalida(LocalDateTime fechaDesalida) {
        this.fechaDesalida = fechaDesalida;
    }
    
}
