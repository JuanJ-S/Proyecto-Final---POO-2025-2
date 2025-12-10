package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidenteVisitanteFrame extends JFrame {
    private int idSesion;
    private Residente residente;

    public ResidenteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        this.residente = BaseDeDatos.construirResidente(idSesion);
        setTitle("Gestión de Visitantes - Residente");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea visitasArea = new JTextArea();
        visitasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(visitasArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        cargarVisitasPendientes(visitasArea);  // Cargar solo visitantes

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton aceptarButton = new JButton("Aceptar Visitante");
        JButton rechazarButton = new JButton("Rechazar Visitante");
        JButton irAPaquetesButton = new JButton("Ir a Paquetes");
        JButton volverButton = new JButton("Cerrar Sesión");

        buttonPanel.add(aceptarButton);
        buttonPanel.add(rechazarButton);
        buttonPanel.add(irAPaquetesButton);
        buttonPanel.add(volverButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        aceptarButton.addActionListener(e -> {
            String idVisitanteStr = JOptionPane.showInputDialog("Ingresa el ID del visitante a aprobar:");
            if (idVisitanteStr != null) {
                try {
                    int idVisitante = Integer.parseInt(idVisitanteStr);
                    BaseDeDatos.aprobarVisita(residente.getIdApto(), idVisitante);
                    JOptionPane.showMessageDialog(null, "Visita aprobada.");
                    cargarVisitasPendientes(visitasArea);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        rechazarButton.addActionListener(e -> {
            String idVisitanteStr = JOptionPane.showInputDialog("Ingresa el ID del visitante a rechazar:");
            if (idVisitanteStr != null) {
                try {
                    int idVisitante = Integer.parseInt(idVisitanteStr);
                    JOptionPane.showMessageDialog(null, "Visita rechazada.");
                    cargarVisitasPendientes(visitasArea);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        irAPaquetesButton.addActionListener(e -> {
            dispose();
            new ResidentePaqueteFrame(idSesion).setVisible(true);
        });

        volverButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void cargarVisitasPendientes(JTextArea area) {
        String visitas = BaseDeDatos.consultarVisitasPendientes(residente.getIdApto());
        area.setText("Visitas Pendientes:\n" + visitas);  // Solo visitantes
    }
}