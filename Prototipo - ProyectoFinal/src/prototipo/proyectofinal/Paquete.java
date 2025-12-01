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
public class Paquete extends Entrante{
    private String remitente;
    private boolean isEntregado;
    
    //Métodos Personalizados
    public void notificar(){
        System.out.println("LLego un paquete");
    }
    public void registrar(){
        System.out.println("Se registro el paquete");
        isEntregado = false;
    }
    public void entregar(){
        System.out.print("Se ha entregado el paquete");
        isEntregado = true;
    }
    //Constructores
    public Paquete() {
    }
    public Paquete(String remitente, boolean isEntregado, Apto destino, Double id) {
        super(destino, id);
        this.remitente = remitente;
        this.isEntregado = isEntregado;
    }
    
    //Getters y Setters
    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public boolean isIsEntregado() {
        return isEntregado;
    }

    public void setIsEntregado(boolean isEntregado) {
        this.isEntregado = isEntregado;
    }
    
}
