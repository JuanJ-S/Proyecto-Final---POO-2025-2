package prototipo.proyectofinal;
import java.time.*;

public class Paquete implements Entrante{
    private int idSistema;
    private String remitente;
    private String nombreDestinatario;
    private int idDestino;
    private boolean isEntregado;
    private LocalDateTime fechaDeLlegada;
    private LocalDateTime fechaDesalida;
    
    //Métodos Personalizados
    void registrarEntrega(){
        throw new UnsupportedOperationException("registrarEntrega Not supported yet.");
    }
    
    //Métodos Heredados

    @Override
    public void notificarLlegada() {
        throw new UnsupportedOperationException("notificarLlegada Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar() {
        throw new UnsupportedOperationException("notificarLlegadaNot supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Constructores

    public Paquete() {
    }

    public Paquete(int idSistema, String remitente, String nombreDestinatario, int idDestino, boolean isEntregado, LocalDateTime fechaDeLlegada, LocalDateTime fechaDesalida) {
        this.idSistema = idSistema;
        this.remitente = remitente;
        this.nombreDestinatario = nombreDestinatario;
        this.idDestino = idDestino;
        this.isEntregado = isEntregado;
        this.fechaDeLlegada = fechaDeLlegada;
        this.fechaDesalida = fechaDesalida;
    }
    
    //getters y Setters

    public int getIdSistema() {
        return idSistema;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public boolean isIsEntregado() {
        return isEntregado;
    }

    public void setIsEntregado(boolean isEntregado) {
        this.isEntregado = isEntregado;
    }

    public LocalDateTime getFechaDeLlegada() {
        return fechaDeLlegada;
    }

    public void setFechaDeLlegada(LocalDateTime fechaDeLlegada) {
        this.fechaDeLlegada = fechaDeLlegada;
    }

    public LocalDateTime getFechaDesalida() {
        return fechaDesalida;
    }

    public void setFechaDesalida(LocalDateTime fechaDesalida) {
        this.fechaDesalida = fechaDesalida;
    }
    
    
}
