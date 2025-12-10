package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministradorFrame extends JFrame {
    private int idSesion;

    public AdministradorFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Menú Administrador");
        setSize(500, 400);
        setLayout(new GridLayout(8, 1));

        JButton consultarVisitantesButton = new JButton("Consultar Visitantes");
        JButton consultarPaquetesButton = new JButton("Consultar Paquetes");
        JButton consultarAptoButton = new JButton("Consultar Apartamento");
        JButton consultarResidenteButton = new JButton("Consultar Residente");
        JButton registrarAptoButton = new JButton("Registrar Apartamento");
        JButton registrarResidenteButton = new JButton("Registrar Residente");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");

        add(consultarVisitantesButton);
        add(consultarPaquetesButton);
        add(consultarAptoButton);
        add(consultarResidenteButton);
        add(registrarAptoButton);
        add(registrarResidenteButton);
        add(cerrarSesionButton);

        // ActionListener para Consultar Visitantes
        consultarVisitantesButton.addActionListener(e -> {
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            Object[] message = {"Torre:", torreField, "Número:", numeroField};
            int option = JOptionPane.showConfirmDialog(null, message, "Consultar Visitantes", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idApto = BaseDeDatos.obtenerIdApto(torre, numero);
                    String resultado = BaseDeDatos.consultarVisitantes(idApto);
                    JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "No hay visitantes." : resultado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ActionListener para Consultar Paquetes (con submenú)
        consultarPaquetesButton.addActionListener(e -> {
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            Object[] message = {"Torre:", torreField, "Número:", numeroField};
            int option = JOptionPane.showConfirmDialog(null, message, "Consultar Paquetes", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idApto = BaseDeDatos.obtenerIdApto(torre, numero);
                    String[] options = {"Ver Todos", "Por Día"};
                    int choice = JOptionPane.showOptionDialog(null, "Opción", "Consultar Paquetes", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (choice == 0) {
                        String resultado = BaseDeDatos.consultarPaquetes(idApto);
                        JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "No hay paquetes." : resultado);
                    } else {
                        String dia = JOptionPane.showInputDialog("Fecha (YYYY-MM-DD):");
                        if (dia != null) {
                            String resultado = BaseDeDatos.consultarPaquetesPorDia(idApto, dia);
                            JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "No hay paquetes en esa fecha." : resultado);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ActionListener para Consultar Apartamento
        consultarAptoButton.addActionListener(e -> {
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            Object[] message = {"Torre:", torreField, "Número:", numeroField};
            int option = JOptionPane.showConfirmDialog(null, message, "Consultar Apartamento", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    String resultado = BaseDeDatos.consultarApto(torre, numero);
                    JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "Apartamento no encontrado." : resultado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ActionListener para Consultar Residente
        consultarResidenteButton.addActionListener(e -> {
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            Object[] message = {"Torre:", torreField, "Número:", numeroField};
            int option = JOptionPane.showConfirmDialog(null, message, "Consultar Residente", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idApto = BaseDeDatos.obtenerIdApto(torre, numero);
                    String resultado = BaseDeDatos.consultarResidente(idApto);
                    JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "Residente no encontrado." : resultado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ActionListener para Registrar Apartamento
        registrarAptoButton.addActionListener(e -> {
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            JTextField propietarioField = new JTextField();
            JTextField idPropietarioField = new JTextField();
            JCheckBox arrendadoCheck = new JCheckBox("¿Arrendado?");
            Object[] message = {"Torre:", torreField, "Número:", numeroField, "Propietario:", propietarioField, "ID Propietario:", idPropietarioField, arrendadoCheck};
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Apartamento", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    String propietario = propietarioField.getText();
                    int idPropietario = Integer.parseInt(idPropietarioField.getText());
                    boolean isArrendado = arrendadoCheck.isSelected();
                    BaseDeDatos.registrarApto(torre, numero, propietario, idPropietario, isArrendado);
                    JOptionPane.showMessageDialog(null, "Apartamento registrado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ActionListener para Registrar Residente
        registrarResidenteButton.addActionListener(e -> {
            JTextField torreField = new JTextField();
            JTextField numeroField = new JTextField();
            JTextField nombresField = new JTextField();
            JTextField apellidosField = new JTextField();
            JTextField idResidenteField = new JTextField();
            JTextField idSesionField = new JTextField();
            Object[] message = {"Torre:", torreField, "Número:", numeroField, "Nombres:", nombresField, "Apellidos:", apellidosField, "ID Residente:", idResidenteField, "ID Sesión:", idSesionField};
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Residente", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idApto = BaseDeDatos.obtenerIdApto(torre, numero);
                    String nombres = nombresField.getText();
                    String apellidos = apellidosField.getText();
                    int idResidente = Integer.parseInt(idResidenteField.getText());
                    
                    BaseDeDatos.registrarResidente(nombres, apellidos, idResidente, idApto, idSesion);
                    JOptionPane.showMessageDialog(null, "Residente registrado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ActionListener para Cerrar Sesión
        cerrarSesionButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        setVisible(true);  // Agregado: Hace visible la ventana
    }
}