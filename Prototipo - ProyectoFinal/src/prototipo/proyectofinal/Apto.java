package prototipo.proyectofinal;

public class Apto {
    private String torre;
    private int num;
    private String propietario;
    private Double idPropietario;
    private Residente residente;
    private boolean isArrendado;
    private Visitante[] visitasDeHoy;
    
    //Constructores
    public Apto() {
    }

    public Apto(String torre, int num, String propietario, Double idPropietario, Residente residente, boolean isArrendado) {
        this.torre = torre;
        this.num = num;
        this.propietario = propietario;
        this.idPropietario = idPropietario;
        this.residente = residente;
        this.isArrendado = isArrendado;
    }
    
    //Getters y Setters

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Double getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Double idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public boolean isIsArrendado() {
        return isArrendado;
    }

    public void setIsArrendado(boolean isArrendado) {
        this.isArrendado = isArrendado;
    }

    public Visitante[] getVisitasDeHoy() {
        return visitasDeHoy;
    }

    public void setVisitasDeHoy(Visitante[] visitasDeHoy) {
        this.visitasDeHoy = visitasDeHoy;
    }
}
