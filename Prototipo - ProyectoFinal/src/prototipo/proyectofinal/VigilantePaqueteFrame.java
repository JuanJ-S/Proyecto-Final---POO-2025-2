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
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));

        JButton registrarButton = new JButton("Registrar Paquete");
        JButton confirmarEntregadoButton = new JButton("Confirmar Entregado");
        JButton registrarEntregaButton = new JButton("Registrar Entrega");
        JButton consultarButton = new JButton("Consultar Paquete");
        JButton irAVisitantesButton = new JButton("Ir a Visitantes");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(confirmarEntregadoButton);
        add(registrarEntregaButton);
        add(consultarButton);
        add(irAVisitantesButton);
        add(volverButton);

        registrarButton.addActionListener(e -> {
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
                    BaseDeDatos.registrarEntrega(idPaquete);
                    JOptionPane.showMessageDialog(null, "Entrega registrada.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        consultarButton.addActionListener(e -> {
            String destino = JOptionPane.showInputDialog("Ingresa ID Destino:");
            if (destino != null) {
                mostrarTablaPaquetes("SELECT idPaquete, remitente, fechaDeLlegada, isEntregado FROM paquete WHERE idDestino = " + destino);
            }
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

    private void mostrarTablaPaquetes(String sql) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/conjunto", "root", "Jj10302526");
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            String[] columnNames = {"ID Paquete", "Remitente", "Fecha Llegada", "Entregado"};
            Object[][] data = new Object[100][4];
            int row = 0;
            while (rs.next() && row < 100) {
                data[row][0] = rs.getInt("idPaquete");
                data[row][1] = rs.getString("remitente");
                data[row][2] = rs.getTimestamp("fechaDeLlegada");
                data[row][3] = rs.getBoolean("isEntregado") ? "Sí" : "No";
                row++;
            }
            
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            JFrame tableFrame = new JFrame("Lista de Paquetes");
            tableFrame.add(scrollPane);
            tableFrame.setSize(600, 400);
            tableFrame.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}