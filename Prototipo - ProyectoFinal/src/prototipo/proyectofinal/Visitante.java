package prototipo.proyectofinal;
import java.time.LocalDateTime;

public class Visitante implements Entrante{
    private int idSistema;
    private int idVisitante;
    private String nombres;
    private String apellidos;
    private int idDestino;
    private boolean isAprobado;
    private LocalDateTime fechaDeEntrada;
    private LocalDateTime fechaDesalida;
    
    //Métodos personalizados
    public void registrarSalida(){
        throw new UnsupportedOperationException("registrarSalida Not supported yet.");
    }
    
    //Métodos Heredados

    @Override
    public void notificarLlegada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
