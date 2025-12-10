package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VigilanteVisitanteFrame extends JFrame {
    private int idSesion;

    public VigilanteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Visitantes - Vigilante");
        // Ajusta el tamaño ya que ahora hay menos botones (6 -> 5)
        setSize(400, 300); 
        // Cambia la cantidad de filas en el layout (6 -> 5)
        setLayout(new GridLayout(5, 1)); 

        JButton registrarButton = new JButton("Notificar Entrante (Registrar Visitante)");
        JButton registrarSalidaButton = new JButton("Registrar Salida");
        // *** SE ELIMINA ESTE BOTÓN: JButton registrarEntregaButton = new JButton("Registrar Entrega"); ***
        JButton consultarButton = new JButton("Consultar Visitantes");
        JButton irAPaquetesButton = new JButton("Ir a Paquetes");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(registrarSalidaButton);
        // *** SE ELIMINA LA ADICIÓN DEL BOTÓN: add(registrarEntregaButton); ***
        add(consultarButton);
        add(irAPaquetesButton);
        add(volverButton);

        registrarButton.addActionListener(e -> {
            JTextField idField = new JTextField();
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            Object[] message = {
                "ID Visitante:", idField,
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "Torre:", torreField,
                "Número:", numeroField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Notificar Entrante", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int idVisitante = Integer.parseInt(idField.getText());
                    String nombres = nombreField.getText();
                    String apellidos = apellidoField.getText();
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idDestino = BaseDeDatos.obtenerIdApto(torre, numero);
                    if (idDestino == 0) {
                        JOptionPane.showMessageDialog(null, "Apartamento no encontrado.");
                        return;
                    }
                    BaseDeDatos.registrarVisitante(idVisitante, nombres, apellidos, idDestino);
                    JOptionPane.showMessageDialog(null, "Visitante registrado. Notificación enviada al apartamento.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos. Intenta de nuevo.");
                }
            }
        });

        registrarSalidaButton.addActionListener(e -> {
            String idVisitanteStr = JOptionPane.showInputDialog("Ingresa el ID del visitante para registrar salida:");
            if (idVisitanteStr != null) {
                try {
                    int idVisitante = Integer.parseInt(idVisitanteStr);
                    BaseDeDatos.registrarSalida(idVisitante);
                    JOptionPane.showMessageDialog(null, "Salida registrada.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        consultarButton.addActionListener(e -> {
            String[] options = {"Ver Visitantes Activos", "Consultar por Día"};
            int choice = JOptionPane.showOptionDialog(null, "Elige una opción", "Consultar Visitantes", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                mostrarTablaVisitantes("SELECT idVisitante, nombres, apellidos, fechaDeEntrada FROM visitante WHERE fechaDesalida IS NULL");
            } else {
                String dia = JOptionPane.showInputDialog("Ingresa el día (YYYY-MM-DD):");
                if (dia != null) {
                    mostrarTablaVisitantes("SELECT idVisitante, nombres, apellidos, fechaDeEntrada FROM visitante WHERE DATE(fechaDeEntrada) = '" + dia + "'");
                }
            }
        });

        irAPaquetesButton.addActionListener(e -> {
            dispose();
            new VigilantePaqueteFrame(idSesion).setVisible(true);
        });

        volverButton.addActionListener(e -> dispose());
    }

    private void mostrarTablaVisitantes(String sql) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/conjunto", "root", "Jj10302526");
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            String[] columnNames = {"ID Visitante", "Nombres", "Apellidos", "Fecha Entrada"};
            // El resto del método es el mismo...
            Object[][] data = new Object[100][4];
            int row = 0;
            while (rs.next() && row < 100) {
                data[row][0] = rs.getInt("idVisitante");
                data[row][1] = rs.getString("nombres");
                data[row][2] = rs.getString("apellidos");
                data[row][3] = rs.getTimestamp("fechaDeEntrada");
                row++;
            }
            
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            JFrame tableFrame = new JFrame("Lista de Visitantes");
            tableFrame.add(scrollPane);
            tableFrame.setSize(600, 400);
            tableFrame.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}