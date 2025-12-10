package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;

public class VigilantePaqueteFrame extends JFrame {
    private int idSesion;

    public VigilantePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Paquetes - Vigilante");
        
        // Tamaño ajustado
        setSize(1000, 700); 
        // Layout ajustado a 4 filas
        setLayout(new GridLayout(4, 1)); 

        JButton registrarButton = new JButton("Registrar Paquete");
        JButton confirmarEntregadoButton = new JButton("Confirmar Entregado"); 
        
        JButton irAVisitantesButton = new JButton("Ir a Visitantes");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(registrarButton);
        add(confirmarEntregadoButton);
        add(irAVisitantesButton);
        add(volverButton);

        // --- ACTION LISTENER: Registrar Paquete (Usa Torre/Número) ---
        registrarButton.addActionListener(e -> {
            JTextField remitenteField = new JTextField();
            JTextField destinatarioField = new JTextField();
            JTextField torreField = new JTextField(); 
            JTextField numeroField = new JTextField(); 

            Object[] message = {
                "Remitente:", remitenteField,
                "Destinatario:", destinatarioField,
                "Torre Destino:", torreField, 
                "Número Destino:", numeroField 
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Paquete", JOptionPane.OK_CANCEL_OPTION);
            
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String remitente = remitenteField.getText();
                    String destinatario = destinatarioField.getText();
                    int torre = Integer.parseInt(torreField.getText());
                    int numero = Integer.parseInt(numeroField.getText());

                    int idDestino = BaseDeDatos.obtenerIdApto(torre, numero); 

                    if (idDestino == 0) {
                        JOptionPane.showMessageDialog(null, "Apartamento de destino no encontrado.", "Error de Destino", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    BaseDeDatos.registrarPaquete(remitente, destinatario, idDestino);
                    
                    JOptionPane.showMessageDialog(null, "Paquete registrado. Notificación enviada al apartamento.");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Torre o Número deben ser valores numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el paquete: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        
        confirmarEntregadoButton.addActionListener(e -> {
            String idPaqueteStr = JOptionPane.showInputDialog(
                null, 
                "Ingresa el ID del Paquete a registrar como entregado:", 
                "Registrar Entrega", 
                JOptionPane.QUESTION_MESSAGE
            );

            // Manejo de la entrada nula o vacía (Cancelado o campo vacío)
            if (idPaqueteStr == null || idPaqueteStr.trim().isEmpty()) {
                return; 
            }

            try {
                int idPaquete = Integer.parseInt(idPaqueteStr);
                
                // Llamada al método VOID. Asumimos éxito si no lanza excepción.
                BaseDeDatos.registrarEntrega(idPaquete); 
                
                // Si la ejecución llega aquí, el registro fue exitoso.
                JOptionPane.showMessageDialog(null, "Entrega del Paquete ID " + idPaquete + " registrada exitosamente.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: El ID del paquete debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Captura cualquier EXCEPTION (como SQLException o una excepción personalizada si el paquete no existe)
                JOptionPane.showMessageDialog(null, "Error al registrar la entrega: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            }
        });


        // --- NAVEGACIÓN ---
        irAVisitantesButton.addActionListener(e -> {
            dispose();
            new VigilanteVisitanteFrame(idSesion).setVisible(true);
        });

        volverButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}