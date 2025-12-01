/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.proyectofinal;

/**
 *
 * @author juanj
 */
public class Apto {
    private String torre;
    private int num;
    private String propietario;
    private Double idPropietario;
    private Residente residente;
    private boolean isArrendado;
    
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
    
}
