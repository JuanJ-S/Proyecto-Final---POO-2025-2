package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VigilantePaqueteFrame extends JFrame {
    private int idSesion;

    public VigilantePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Paquetes - Vigilante");
        
        // Tamaño aumentado (500x350) y ajustado a 5 filas.
        setSize(1000, 700); 
        setLayout(new GridLayout(5, 1)); 

        JButton registrarButton = new JButton("Registrar Paquete");
        JButton confirmarEntregadoButton = new JButton("Confirmar Entregado");
        JButton registrarEntregaButton = new JButton("Registrar Entrega");
        // [ELIMINADO: JButton consultarButton = new JButton("Consultar Paquete");]
        JButton irAVisitantesButton = new JButton("Ir a Visitantes");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(confirmarEntregadoButton);
        add(registrarEntregaButton);
        // [ELIMINADO: add(consultarButton);]
        add(irAVisitantesButton);
        add(volverButton);

        // --- ACTIONLISTENER CORREGIDO: Registrar Paquete ---
        registrarButton.addActionListener(e -> {
            JTextField remitenteField = new JTextField();
            JTextField destinatarioField = new JTextField();
            JTextField torreField = new JTextField(); 
            JTextField numeroField = new JTextField(); 

            Object[] message = {
                "Remitente:", remitenteField,
                "Destinatario:", destinatarioField,
                "Torre Destino:", torreField, 
                "Número Destino:", numeroField 
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Paquete", JOptionPane.OK_CANCEL_OPTION);
            
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String remitente = remitenteField.getText();
                    String destinatario = destinatarioField.getText();
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());

                    // Obtener el ID Destino y Registrar
                    int idDestino = BaseDeDatos.obtenerIdApto(torre, numero); 

                    if (idDestino == 0) {
                        JOptionPane.showMessageDialog(null, "Apartamento de destino no encontrado.");
                        return;
                    }

                    BaseDeDatos.registrarPaquete(remitente, destinatario, idDestino);
                    
                    JOptionPane.showMessageDialog(null, "Paquete registrado. Notificación enviada al apartamento.");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de Torre o Número. Intenta de nuevo.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el paquete: " + ex.getMessage());
                }
            }
        });

        // --- El resto de los ActionListeners se mantiene ---
        confirmarEntregadoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Paquete confirmado como entregado. Notificación enviada.");
        });

        registrarEntregaButton.addActionListener(e -> {
            // ... (lógica de registrar entrega) ...
        });

        irAVisitantesButton.addActionListener(e -> {
            dispose();
            new VigilanteVisitanteFrame(idSesion).setVisible(true);
        });

        volverButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}