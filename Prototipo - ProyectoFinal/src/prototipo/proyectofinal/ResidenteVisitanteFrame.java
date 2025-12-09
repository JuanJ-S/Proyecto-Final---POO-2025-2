package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResidenteVisitanteFrame extends JFrame {
    private int idSesion;

    public ResidenteVisitanteFrame(int idSesion) {
        this.idSesion = idSesion;
        setTitle("Gestión de Visitantes - Residente");
        setSize(400, 200);
        setLayout(new GridLayout(2, 1));

        JButton aceptarButton = new JButton("Aceptar Visitante");
        JButton rechazarButton = new JButton("Rechazar Visitante");

        add(aceptarButton);
        add(rechazarButton);

        aceptarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Visitante aceptado. Notificación enviada al vigilante.");
            dispose(); // Volver al menú principal
        });

        rechazarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Visitante rechazado. Notificación enviada al vigilante.");
            dispose();
        });
    }
}
