package prototipo.proyectofinal;

public class Apto {
    private int idApto;
    private int torre;
    private int numero;
    private String propietario;
    private int idPropietario;
    private boolean isArrendado;
    
    //Constructor

    public Apto() {
    }

    public Apto(int idApto, int torre, int numero, String propietario, int idPropietario, boolean isArrendado) {
        this.idApto = idApto;
        this.torre = torre;
        this.numero = numero;
        this.propietario = propietario;
        this.idPropietario = idPropietario;
        this.isArrendado = isArrendado;
    }
    
    //Getters y Setters

    public int getIdApto() {
        return idApto;
    }

    public int getTorre() {
        return torre;
    }

    public void setTorre(int torre) {
        this.torre = torre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public boolean isIsArrendado() {
        return isArrendado;
    }

    public void setIsArrendado(boolean isArrendado) {
        this.isArrendado = isArrendado;
    }
    
}
