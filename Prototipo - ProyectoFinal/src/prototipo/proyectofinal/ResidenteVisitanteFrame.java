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
    private Residente residente;  // Objeto completo para acceder a idApto

    public ResidenteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        this.residente = BaseDeDatos.construirResidente(idSesion);  // Construir residente para obtener idApto
        setTitle("Gestión de Visitantes - Residente");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel para mostrar visitas pendientes
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea visitasArea = new JTextArea();
        visitasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(visitasArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Cargar visitas pendientes al abrir
        cargarVisitasPendientes(visitasArea);

        // Botones para aprobar/rechazar y navegación
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton aceptarButton = new JButton("Aceptar Visitante");
        JButton rechazarButton = new JButton("Rechazar Visitante");
        JButton irAPaquetesButton = new JButton("Ir a Paquetes");  // Nuevo botón
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
                    BaseDeDatos.aprobarVisita(residente.getIdApto(), idVisitante);  // Usar idApto
                    JOptionPane.showMessageDialog(null, "Visita aprobada. Notificación enviada al vigilante.");
                    cargarVisitasPendientes(visitasArea);  // Recargar lista
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
                    // Asumir un método para rechazar (agrega en BaseDeDatos si no existe)
                    JOptionPane.showMessageDialog(null, "Visita rechazada. Notificación enviada al vigilante.");
                    cargarVisitasPendientes(visitasArea);  // Recargar lista
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        irAPaquetesButton.addActionListener(e -> {
            dispose();  // Cerrar ventana actual
            new ResidentePaqueteFrame(idSesion).setVisible(true);  // Abrir Paquetes
        });

        volverButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void cargarVisitasPendientes(JTextArea area) {
        // Llamar al método de BaseDeDatos para obtener visitas pendientes
        // Asumiendo que BaseDeDatos.consultarVisitasPendientes devuelve una lista o string
        String visitas = BaseDeDatos.consultarVisitasPendientes(residente.getIdApto());  // Usar idApto
        area.setText("Visitas Pendientes:\n" + visitas);
    }
}
