package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;

public class ResidentePaqueteFrame extends JFrame {
    private int idSesion;
    private Residente residente;
    private JTextArea paquetesArea; 
    public ResidentePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        this.residente = BaseDeDatos.construirResidente(idSesion);
        setTitle("Gestión de Paquetes - Residente");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        paquetesArea = new JTextArea(); // Inicialización
        paquetesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(paquetesArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        
        cargarPaquetes(paquetesArea, residente.getIdApto(), null); 

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
            cargarPaquetes(paquetesArea, residente.getIdApto(), null);
        });

        consultarPorDiaButton.addActionListener(e -> {
            String dia = JOptionPane.showInputDialog("Ingresa el día (YYYY-MM-DD):");
            if (dia != null && !dia.trim().isEmpty()) {
                cargarPaquetes(paquetesArea, residente.getIdApto(), dia);
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

    /**
     * Llama a BaseDeDatos para obtener los paquetes y actualiza el JTextArea.
     * @param area El JTextArea a actualizar.
     * @param idApto El ID del apartamento del residente.
     * @param dia Si es null, consulta todos; si tiene fecha, consulta por día.
     */
    private void cargarPaquetes(JTextArea area, int idApto, String dia) {
        String resultado;
        if (dia == null) {
            resultado = BaseDeDatos.consultarPaquetes(idApto);
        } else {
            resultado = BaseDeDatos.consultarPaquetesPorDia(idApto, dia);
        }
        
        // Verifica si la consulta fue exitosa
        if (resultado.contains("!!! Error")) {
            JOptionPane.showMessageDialog(this, resultado, "Error de Consulta", JOptionPane.ERROR_MESSAGE);
            area.setText("Error al cargar paquetes. Verifique la conexión a la base de datos.");
        } else if (resultado.isEmpty() || resultado.equals("ID | Remitente | Destinatario | Llegada | Entregado\n------------------------------------------------------------------------\n")) {
            area.setText("No se encontraron paquetes para este criterio.");
        } else {
            area.setText("--- PAQUETES REGISTRADOS ---\n\n" + resultado);
        }
    }
}
            