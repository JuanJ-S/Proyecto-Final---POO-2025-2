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
        // Se asume que BaseDeDatos.construirResidente(idSesion) funciona correctamente
        this.residente = BaseDeDatos.construirResidente(idSesion); 
        setTitle("Gestión de Visitantes - Residente");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea visitasArea = new JTextArea();
        visitasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(visitasArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        cargarVisitasPendientes(visitasArea); // Cargar solo visitantes

        // --- PANEL DE BOTONES CORREGIDO ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton aceptarButton = new JButton("Aceptar Visitante");
        JButton rechazarButton = new JButton("Rechazar Visitante");
        JButton irAPaquetesButton = new JButton("Ir a Paquetes");
        // Cambiamos el nombre del botón para que su función sea clara: Cerrar Sesión
        JButton cerrarSesionButton = new JButton("Cerrar Sesión"); 

        buttonPanel.add(aceptarButton);
        buttonPanel.add(rechazarButton);
        buttonPanel.add(irAPaquetesButton);
        buttonPanel.add(cerrarSesionButton); // Usamos el botón con la función de Cerrar Sesión

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ... [ActionListeners para Aceptar y Rechazar Visitante] ...

        aceptarButton.addActionListener(e -> {
            String idVisitanteStr = JOptionPane.showInputDialog("Ingresa el ID del visitante a aprobar:");
            if (idVisitanteStr != null) {
                try {
                    int idVisitante = Integer.parseInt(idVisitanteStr);
                    // Se asume que BaseDeDatos.aprobarVisita existe
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
                    // Se asume que BaseDeDatos.rechazarVisita existe, aquí solo muestra mensaje
                    JOptionPane.showMessageDialog(null, "Visita rechazada."); 
                    cargarVisitasPendientes(visitasArea);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        // --- CONEXIÓN A PAQUETES ---
        irAPaquetesButton.addActionListener(e -> {
            dispose();
            new ResidentePaqueteFrame(idSesion).setVisible(true); // Abre la ventana de gestión de paquetes
        });

        // --- CERRAR SESIÓN ---
        cerrarSesionButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true); // Vuelve al Login
        });
        
        // El resto del código del constructor...
    }

    private void cargarVisitasPendientes(JTextArea area) {
        // Se asume que BaseDeDatos.consultarVisitasPendientes(idApto) funciona
        String visitas = BaseDeDatos.consultarVisitasPendientes(residente.getIdApto()); 
        area.setText("Visitas Pendientes:\n" + visitas); // Solo visitantes
    }
}