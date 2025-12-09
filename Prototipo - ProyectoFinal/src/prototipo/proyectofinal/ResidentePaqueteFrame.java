package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResidentePaqueteFrame extends JFrame {
    private int idSesion;
    private Residente residente;  // Objeto completo para acceder a idApto y nombres

    public ResidentePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        this.residente = BaseDeDatos.construirResidente(idSesion);  // Construir residente para obtener idApto y nombres
        setTitle("Gestión de Paquetes - Residente");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea paquetesArea = new JTextArea();
        paquetesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(paquetesArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones para submenú (similar a consultarPaquetes() en Residente)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton consultarTodosButton = new JButton("Consultar Todos los Paquetes");
        JButton consultarPorDiaButton = new JButton("Consultar Paquetes por Día");
        JButton volverButton = new JButton("Volver al Menú Principal");

        buttonPanel.add(consultarTodosButton);
        buttonPanel.add(consultarPorDiaButton);
        buttonPanel.add(volverButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        consultarTodosButton.addActionListener(e -> {
            // Opción 1: Consultar todos los paquetes
            String paquetes = BaseDeDatos.consultarPaquetes(residente.getIdApto());  // Usar idApto
            paquetesArea.setText("Todos los Paquetes:\n" + paquetes);
        });

        consultarPorDiaButton.addActionListener(e -> {
            // Opción 2: Consultar por día, usando nombres del residente
            String dia = JOptionPane.showInputDialog("Ingresa el día que quieres consultar (Año-Mes-Día, e.g., 2023-10-15):");
            if (dia != null && !dia.trim().isEmpty()) {
                String paquetes = BaseDeDatos.consultarPaquetesPorDia(residente.getIdApto(), residente.getNombres());  // Usar idApto y nombres
                paquetesArea.setText("Paquetes del día " + dia + " (filtrado por nombre: " + residente.getNombres() + "):\n" + paquetes);
            } else {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Intenta de nuevo.");
            }
        });

        volverButton.addActionListener(e -> dispose());
    }
}