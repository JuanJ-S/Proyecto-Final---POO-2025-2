package prototipo.proyectofinal;

//Clase Abstracta para toda entidad ajena al conjunto (Visita/Paquete)
public abstract class Entrante {
    Apto destino;
    Double id;
    
    void notificarLlegada(){}
    void registrarEntrada(){}
}
