package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ResidentePaqueteFrame extends JFrame {
    private int idSesion;
    private Residente residente;

    public ResidentePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        this.residente = BaseDeDatos.construirResidente(idSesion);
        setTitle("Gestión de Paquetes - Residente");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea paquetesArea = new JTextArea();
        paquetesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(paquetesArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton consultarTodosButton = new JButton("Consultar Todos los Paquetes");
        JButton consultarPorDiaButton = new JButton("Consultar Paquetes por Día");
        JButton irAVisitantesButton = new JButton("Ir a Visitantes");
        JButton volverButton = new JButton("Cerrar Sesión");

        buttonPanel.add(consultarTodosButton);
        buttonPanel.add(consultarPorDiaButton);
        buttonPanel.add(irAVisitantesButton);
        buttonPanel.add(volverButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        consultarTodosButton.addActionListener(e -> {
            mostrarTablaPaquetes("SELECT idPaquete, remitente, fechaDeLlegada, isEntregado FROM paquete WHERE idDestino = " + residente.getIdApto());
        });

        consultarPorDiaButton.addActionListener(e -> {
            String dia = JOptionPane.showInputDialog("Ingresa el día (YYYY-MM-DD):");
            if (dia != null) {
                mostrarTablaPaquetes("SELECT idPaquete, remitente, fechaDeLlegada, isEntregado FROM paquete WHERE idDestino = " + residente.getIdApto() + " AND DATE(fechaDeLlegada) = '" + dia + "'");
            }
        });

        irAVisitantesButton.addActionListener(e -> {
            dispose();
            new ResidenteVisitanteFrame(idSesion).setVisible(true);
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
            Object[][] data = new Object[100][4];  // Ajusta tamaño si tienes más filas
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
            