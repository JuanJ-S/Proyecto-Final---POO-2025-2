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
    String nombre;
    Double id;
    LocalDateTime horaDeEntrada;
    LocalDateTime horaDeSalida;
    
    void notificar(){
        //Notifica su llegada al destino
        System.out.println("Visitante");
    }
    void registrar(){
        //Registra sus datos en el sistema del conjunto
        System.out.println("Visitante Registrado");
    }
}
