package prototipo.proyectofinal;

//Clase Abstracta para toda entidad ajena al conjunto (Visita/Paquete)
public abstract class Entrante {
    private Apto destino;
    private Double id;
    
    //Métodos Personalizados
    public void notificarLlegada(){}
    public void registrarEntrada(){}
    
    //Constructores
    public Entrante() {
    }
    public Entrante(Apto destino, Double id) {
        this.destino = destino;
        this.id = id;
    }
    //Getters y Setters
    public Apto getDestino() {
        return destino;
    }
    public void setDestino(Apto destino) {
        this.destino = destino;
    }
    public Double getId() {
        return id;
    }
    public void setId(Double id) {
        this.id = id;
    }
}
