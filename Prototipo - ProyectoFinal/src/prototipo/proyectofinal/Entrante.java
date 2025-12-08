package prototipo.proyectofinal;

public interface Entrante {
    public void notificarLlegada();
    public void registrar();
//interfaz ampliada
   public void registrarSalida(); //cuando sale del conjunto y solo aplica para los visitantes
   public void registrarEntrega();// cuando recibe el paquete y solo aplica a paquetes
   
}
