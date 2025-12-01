/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.proyectofinal;
import java.time.LocalDateTime;

/**
 *
 * @author juanj
 */
public class Visitante extends Entrante{
    private String nombre;
    private LocalDateTime horaDeEntrada;
    private LocalDateTime horaDeSalida;
    
    //Métodos Personalizados
    public void notificar(){
        //Notifica su llegada al destino
        System.out.println("Visitante");
        registrar();
    }
    private void registrar(){
        //Registra sus datos en el sistema del conjunto
        System.out.println("Visitante Registrado");
    }
    
    //Constructores
    public Visitante() {
    }
    public Visitante(String nombre, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida, Apto destino, Double id) {
        super(destino, id);
        this.nombre = nombre;
        this.horaDeEntrada = horaDeEntrada;
        this.horaDeSalida = horaDeSalida;
    }
    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LocalDateTime getHoraDeEntrada() {
        return horaDeEntrada;
    }
    public void setHoraDeEntrada(LocalDateTime horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }
    public LocalDateTime getHoraDeSalida() {
        return horaDeSalida;
    }
    public void setHoraDeSalida(LocalDateTime horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }
    
}
