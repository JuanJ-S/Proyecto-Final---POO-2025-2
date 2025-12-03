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
    @Override
    public void notificarLlegada(){ //Se notifica el paquete al Residente
        System.out.println("LLego un paquete de "+remitente+" para "+getDestino().getResidente().getNombre()+" del apartamento "+getDestino().getNum()+" de la torre "+getDestino().getTorre());
    }
    public void registrarEntrada(){ //Se guarda en paquete en la BD en paquetesPendientes de Residente
        System.out.println("Se registro el paquete en la base de datos, para el apartamento "+getDestino().getNum()+" de la torre "+getDestino().getTorre());
        isEntregado = false;
    }
    public void entregar(){ //Se registra el paquete como entregado en el sistema y se borra de paquetesPendientes
        System.out.print("Se ha entregado el paquete a: ");
        isEntregado = true;
    }
    //Constructores
    public Paquete() {
    }
    public Paquete(String remitente, Apto destino, Double id) {
        super(destino, id);
        this.remitente = remitente;
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
