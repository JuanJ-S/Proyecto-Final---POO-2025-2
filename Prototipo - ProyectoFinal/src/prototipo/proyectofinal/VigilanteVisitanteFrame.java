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
            // Formulario para registrar visitante
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField idDestinoField = new JTextField();
            Object[] message = {
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "ID Destino:", idDestinoField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Visitante", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Lógica para registrar (insertar en BD)
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/conjunto", "root", "Jj10302526");
                     PreparedStatement ps = con.prepareStatement("INSERT INTO visitante (nombres, apellidos, idDestino, fechaDeEntrada) VALUES (?, ?, ?, ?)")) {
                    ps.setString(1, nombreField.getText());
                    ps.setString(2, apellidoField.getText());
                    ps.setInt(3, Integer.parseInt(idDestinoField.getText()));
                    ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Visitante registrado. Notificación enviada al apartamento.");
                    // Aquí podrías agregar lógica para notificar (e.g., simular llamada)
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        consultarButton.addActionListener(e -> {
            // Opción 1: Visitantes activos
            String[] options = {"Ver Visitantes Activos", "Consultar por Día"};
            int choice = JOptionPane.showOptionDialog(null, "Elige una opción", "Consultar Visitantes", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                // Mostrar tabla de visitantes activos
                // (Lógica simplificada: consulta BD y muestra en JTable)
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
