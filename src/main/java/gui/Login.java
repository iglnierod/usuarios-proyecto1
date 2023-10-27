package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.App;

public class Login extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JTextField textoUsuario;
    private JTextField textoContraseña;
    private JButton btnIniciarSesion;
    private App app;

    public Login(App app) {
        this.app = app;

        setTitle("Aplicación usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 300, 260);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaUsuario.setBounds(40, 20, 46, 14);
        contentPane.add(etiquetaUsuario);

        textoUsuario = new JTextField();
        textoUsuario.setBounds(40, 45, 210, 20);
        contentPane.add(textoUsuario);
        textoUsuario.setColumns(10);

        JLabel etiquetaContraseña = new JLabel("Contraseña:");
        etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaContraseña.setBounds(40, 75, 210, 14);
        contentPane.add(etiquetaContraseña);

        textoContraseña = new JPasswordField();
        textoContraseña.setColumns(10);
        textoContraseña.setBounds(40, 100, 210, 20);
        contentPane.add(textoContraseña);
        textoContraseña.addActionListener(this);

        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setBounds(80, 145, 140, 23);
        btnIniciarSesion.addActionListener(this);
        contentPane.add(btnIniciarSesion);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = this.textoUsuario.getText();
        String pwd = this.textoContraseña.getText();
        if (userName.isEmpty() || pwd.isEmpty()) return;
        if (this.app.logIn(userName, pwd)) {
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "El usuario y/o contraseña no es correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
