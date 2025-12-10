package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VigilanteVisitanteFrame extends JFrame {
    private int idSesion;

    public VigilanteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Visitantes - Vigilante");
        
        // El layout ahora tiene 4 filas (antes 5): Registrar Entrada, Registrar Salida, Ir a Paquetes, Volver
        setSize(800, 500); 
        setLayout(new GridLayout(4, 1)); 

        JButton registrarButton = new JButton("Notificar Entrante (Registrar Visitante)");
        JButton registrarSalidaButton = new JButton("Registrar Salida");
        // *** SE ELIMINA ESTE BOTÓN: JButton consultarButton = new JButton("Consultar Visitantes"); ***
        JButton irAPaquetesButton = new JButton("Ir a Paquetes");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(registrarSalidaButton);
        // *** SE ELIMINA LA ADICIÓN DEL BOTÓN: add(consultarButton); ***
        add(irAPaquetesButton);
        add(volverButton);

        registrarButton.addActionListener(e -> {
            JTextField idField = new JTextField();
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            Object[] message = {
                "ID Visitante:", idField,
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "Torre:", torreField,
                "Número:", numeroField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Notificar Entrante", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int idVisitante = Integer.parseInt(idField.getText());
                    String nombres = nombreField.getText();
                    String apellidos = apellidoField.getText();
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    // Se asume que BaseDeDatos existe y tiene estos métodos
                    int idDestino = BaseDeDatos.obtenerIdApto(torre, numero); 
                    if (idDestino == 0) {
                        JOptionPane.showMessageDialog(null, "Apartamento no encontrado.");
                        return;
                    }
                    BaseDeDatos.registrarVisitante(idVisitante, nombres, apellidos, idDestino);
                    JOptionPane.showMessageDialog(null, "Visitante registrado. Notificación enviada al apartamento.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos. Intenta de nuevo.");
                }
            }
        });

        registrarSalidaButton.addActionListener(e -> {
            String idVisitanteStr = JOptionPane.showInputDialog("Ingresa el ID del visitante para registrar salida:");
            if (idVisitanteStr != null) {
                try {
                    int idVisitante = Integer.parseInt(idVisitanteStr);
                    BaseDeDatos.registrarSalida(idVisitante);
                    JOptionPane.showMessageDialog(null, "Salida registrada.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        // *** SE ELIMINA TODO EL ACTIONLISTENER ASOCIADO A consultarButton ***

        irAPaquetesButton.addActionListener(e -> {
            dispose();
            new VigilantePaqueteFrame(idSesion).setVisible(true);
        });

        // Este botón debería volver al MainFrame, pero si es el menú principal,
        // asumo que debe cerrar la ventana. Si debe volver al Login, cámbialo.
        volverButton.addActionListener(e -> dispose()); 
    }
    
    // *** SE ELIMINA EL MÉTODO private void mostrarTablaVisitantes(String sql) ***
}