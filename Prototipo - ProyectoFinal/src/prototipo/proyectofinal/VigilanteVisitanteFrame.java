package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VigilanteVisitanteFrame extends JFrame {
    private int idSesion;

    public VigilanteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Visitantes - Vigilante");
        setSize(400, 350);  // Aumentar tamaño para más botones
        setLayout(new GridLayout(6, 1));  // Cambiar a 6 filas para acomodar nuevos botones

        JButton registrarButton = new JButton("Notificar Entrante (Registrar Visitante)");
        JButton registrarSalidaButton = new JButton("Registrar Salida");
        JButton registrarEntregaButton = new JButton("Registrar Entrega");  // Nuevo botón
        JButton consultarButton = new JButton("Consultar Visitantes");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(registrarSalidaButton);
        add(registrarEntregaButton);
        add(consultarButton);
        add(volverButton);

        registrarButton.addActionListener(e -> {
            // Formulario para registrar visitante, ahora con torre y numero
            JTextField idField = new JTextField();
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField torreField = new JTextField();  // Nuevo campo
            JTextField numeroField = new JTextField();  // Nuevo campo
            Object[] message = {
                "ID Visitante:", idField,
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "Torre:", torreField,  // Pedir torre
                "Número:", numeroField  // Pedir numero
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Notificar Entrante", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int idVisitante = Integer.parseInt(idField.getText());
                    String nombres = nombreField.getText();
                    String apellidos = apellidoField.getText();
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idDestino = BaseDeDatos.obtenerIdApto(torre, numero);  // Obtener idDestino
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
                    BaseDeDatos.registrarSalida(idVisitante);  // Nuevo método
                    JOptionPane.showMessageDialog(null, "Salida registrada.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
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
            // Opción 1: Visitantes activos
            String[] options = {"Ver Visitantes Activos", "Consultar por Día"};
            int choice = JOptionPane.showOptionDialog(null, "Elige una opción", "Consultar Visitantes", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                JOptionPane.showMessageDialog(null, "Tabla de visitantes activos (implementar JTable aquí).");
            } else {
                String dia = JOptionPane.showInputDialog("Ingresa el día (YYYY-MM-DD):");
                JOptionPane.showMessageDialog(null, "Tabla de visitantes del día " + dia + " (implementar JTable aquí).");
            }
        });

        volverButton.addActionListener(e -> dispose());
    }
}