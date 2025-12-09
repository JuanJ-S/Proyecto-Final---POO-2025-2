package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResidentePaqueteFrame extends JFrame {
    private int idSesion;

    public ResidentePaqueteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Paquetes - Residente");
        setSize(400, 200);
        setLayout(new GridLayout(2, 1));

        JButton verPaquetesButton = new JButton("Ver Paquetes Disponibles");
        JButton volverButton = new JButton("Volver al Menú Principal");

        add(verPaquetesButton);
        add(volverButton);

        verPaquetesButton.addActionListener(e -> {
            String dia = JOptionPane.showInputDialog("Ingresa el día (YYYY-MM-DD):");
            JOptionPane.showMessageDialog(null, "Paquetes del día " + dia + " (implementar JTable aquí).");
        });

        volverButton.addActionListener(e -> dispose());
    }
}