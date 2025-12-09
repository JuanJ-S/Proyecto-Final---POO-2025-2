package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class VigilanteVisitanteFrame extends JFrame {
    private int idSesion;

    public VigilanteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Visitantes - Vigilante");
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        JButton registrarButton = new JButton("Registrar Visitante");
        JButton consultarButton = new JButton("Consultar Visitantes");
        JButton registrarSalidaButton = new JButton("Registrar Salida");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(consultarButton);
        add(registrarSalidaButton);
        add(volverButton);

        registrarButton.addActionListener(e -> {
            // Formulario para registrar visitante usando BaseDeDatos.registrarVisitante
            JTextField idField = new JTextField();
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField idDestinoField = new JTextField();
            Object[] message = {
                "ID Visitante:", idField,
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "ID Destino:", idDestinoField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Visitante", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int idVisitante = Integer.parseInt(idField.getText());
                    String nombres = nombreField.getText();
                    String apellidos = apellidoField.getText();
                    int idDestino = Integer.parseInt(idDestinoField.getText());
                    BaseDeDatos.registrarVisitante(idVisitante, nombres, apellidos, idDestino);  // Usar método de BaseDeDatos
                    JOptionPane.showMessageDialog(null, "Visitante registrado. Notificación enviada al apartamento.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos. Intenta de nuevo.");
                }
            }
        });

        consultarButton.addActionListener(e -> {
            // Opción 1: Visitantes activos
            String[] options = {"Ver Visitantes Activos", "Consultar por Día"};
            int choice = JOptionPane.showOptionDialog(null, "Elige una opción", "Consultar Visitantes", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                // Mostrar tabla de visitantes activos (puedes implementar JTable aquí)
                JOptionPane.showMessageDialog(null, "Tabla de visitantes activos (implementar JTable aquí).");
            } else {
                // Consultar por día
                String dia = JOptionPane.showInputDialog("Ingresa el día (YYYY-MM-DD):");
                JOptionPane.showMessageDialog(null, "Tabla de visitantes del día " + dia + " (implementar JTable aquí).");
            }
        });

        registrarSalidaButton.addActionListener(e -> {
            String documento = JOptionPane.showInputDialog("Ingresa el ID del visitante:");
            // Lógica para registrar salida (actualizar BD)
            JOptionPane.showMessageDialog(null, "Salida registrada para ID: " + documento);
        });

        volverButton.addActionListener(e -> dispose());
    }
}