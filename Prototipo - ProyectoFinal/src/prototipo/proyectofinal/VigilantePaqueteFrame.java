package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VigilantePaqueteFrame extends JFrame {
    private int idSesion;

    public VigilantePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Paquetes - Vigilante");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1));  // Cambiar a 5 filas para el nuevo botón

        JButton registrarButton = new JButton("Registrar Paquete");
        JButton confirmarEntregadoButton = new JButton("Confirmar Entregado");
        JButton registrarEntregaButton = new JButton("Registrar Entrega");  // Nuevo botón
        JButton consultarButton = new JButton("Consultar Paquete");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(confirmarEntregadoButton);
        add(registrarEntregaButton);
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
                JOptionPane.showMessageDialog(null, "Paquete registrado. Notificación enviada al apartamento.");
            }
        });

        confirmarEntregadoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Paquete confirmado como entregado. Notificación enviada.");
        });

        registrarEntregaButton.addActionListener(e -> {
            String idPaqueteStr = JOptionPane.showInputDialog("Ingresa el ID del paquete para registrar entrega:");
            if (idPaqueteStr != null) {
                try {
                    int idPaquete = Integer.parseInt(idPaqueteStr);
                    BaseDeDatos.registrarEntrega(idPaquete);  // Nuevo método
                    JOptionPane.showMessageDialog(null, "Entrega registrada.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        consultarButton.addActionListener(e -> {
            String destino = JOptionPane.showInputDialog("Ingresa ID Destino:");
            JOptionPane.showMessageDialog(null, "Lista de paquetes para destino " + destino + " (implementar JTable aquí).");
        });

        volverButton.addActionListener(e -> dispose());
    }
}