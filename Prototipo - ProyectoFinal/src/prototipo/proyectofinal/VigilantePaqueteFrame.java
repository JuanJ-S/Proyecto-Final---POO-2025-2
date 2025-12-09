package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class VigilantePaqueteFrame extends JFrame {
    private int idSesion;

    public VigilantePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Paquetes - Vigilante");
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        JButton registrarButton = new JButton("Registrar Paquete");
        JButton confirmarEntregadoButton = new JButton("Confirmar Entregado");
        JButton consultarButton = new JButton("Consultar Paquete");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(confirmarEntregadoButton);
        add(consultarButton);
        add(volverButton);

        registrarButton.addActionListener(e -> {
            // Formulario para registrar paquete
            JTextField remitenteField = new JTextField();
            JTextField destinatarioField = new JTextField();
            JTextField idDestinoField = new JTextField();
            Object[] message = {
                "Remitente:", remitenteField,
                "Destinatario:", destinatarioField,
                "ID Destino:", idDestinoField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Paquete", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Lógica para registrar (insertar en BD)
                JOptionPane.showMessageDialog(null, "Paquete registrado. Notificación enviada al apartamento.");
            }
        });

        confirmarEntregadoButton.addActionListener(e -> {
            // Seleccionar paquete y confirmar
            JOptionPane.showMessageDialog(null, "Paquete confirmado como entregado. Notificación enviada.");
        });

        consultarButton.addActionListener(e -> {
            String destino = JOptionPane.showInputDialog("Ingresa ID Destino:");
            JOptionPane.showMessageDialog(null, "Lista de paquetes para destino " + destino + " (implementar JTable aquí).");
        });

        volverButton.addActionListener(e -> dispose());
    }
}