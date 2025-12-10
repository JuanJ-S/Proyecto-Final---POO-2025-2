package prototipo.proyectofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JComboBox<String> rolComboBox;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Inicio de Sesión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Rol:"));
        rolComboBox = new JComboBox<>(new String[]{"Administrador", "Residente", "Vigilante"});
        add(rolComboBox);

        add(new JLabel("ID de Sesión:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Iniciar Sesión");
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rol = rolComboBox.getSelectedIndex() + 1; // 1: Admin, 2: Residente, 3: Vigilante
                int idSesion = Integer.parseInt(idField.getText());
                String contraseña = new String(passwordField.getPassword());

                Usuario usuario = null;
                switch (rol) {
                    case 1:
                        usuario = new Administrador();
                        break;
                    case 2:
                        usuario = new Residente();
                        break;
                    case 3:
                        usuario = new Vigilante();
                        break;
                }

                boolean acceso = usuario.iniciarSesion(idSesion, contraseña, rol);
                if (acceso) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    dispose(); // Cerrar login
                   
                    switch (rol) {
                        case 1:
                            new AdministradorFrame(idSesion).setVisible(true);
                            break;
                        case 2:
                            new ResidenteVisitanteFrame(idSesion).setVisible(true);  // Ir directo a Visitante
                            break;
                        case 3:
                            new VigilanteVisitanteFrame(idSesion).setVisible(true);  // Ir directo a Visitante
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos. Intenta de nuevo.");
                }
            }
        });

        setVisible(true);
    }
}