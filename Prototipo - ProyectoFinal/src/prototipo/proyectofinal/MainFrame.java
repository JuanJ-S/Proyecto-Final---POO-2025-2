package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private int rol;
    private int idSesion;

    public MainFrame(int rol, int idSesion) {
        this.rol = rol;
        this.idSesion = idSesion;

        setTitle("Menú Principal");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton visitanteButton = new JButton("Visitante");
        JButton paqueteButton = new JButton("Paquete");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");

        add(visitanteButton);
        add(paqueteButton);
        add(cerrarSesionButton);

        visitanteButton.addActionListener(e -> {
            if (rol == 2) { // Residente
                new ResidenteVisitanteFrame(idSesion).setVisible(true);
            } else if (rol == 3) { // Vigilante
                new VigilanteVisitanteFrame(idSesion).setVisible(true);
            }
        });

        paqueteButton.addActionListener(e -> {
            if (rol == 2) { // Residente
                new ResidentePaqueteFrame(idSesion).setVisible(true);
            } else if (rol == 3) { // Vigilante
                new VigilantePaqueteFrame(idSesion).setVisible(true);
            }
        });

        cerrarSesionButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
