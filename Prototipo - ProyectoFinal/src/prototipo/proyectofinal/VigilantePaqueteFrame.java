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
        setLayout(new GridLayout(6, 1));  // Cambiar a 6 filas para acomodar el nuevo botón

        JButton registrarButton = new JButton("Registrar Paquete");
        JButton confirmarEntregadoButton = new JButton("Confirmar Entregado");
        JButton registrarEntregaButton = new JButton("Registrar Entrega");
        JButton consultarButton = new JButton("Consultar Paquete");
        JButton irAVisitantesButton = new JButton("Ir a Visitantes");  // Nuevo botón
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(confirmarEntregadoButton);
        add(registrarEntregaButton);
        add(consultarButton);
        add(irAVisitantesButton);
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

        irAVisitantesButton.addActionListener(e -> {
            dispose();  // Cerrar ventana actual
            new VigilanteVisitanteFrame(idSesion).setVisible(true);  // Abrir Visitantes
        });

        volverButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);  // O ajustar para cerrar sesión
        });
    }
}
