package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.App;

public class UserCreate extends WindowListener implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaCrearUsuario;
    private JLabel etiquetaNombre;
    private JLabel etiquetaContraseña;
    private JLabel etiquetaEdad;
    private JLabel etiquetaCorreo;
    private JLabel etiquetaImagen;
    private JTextField textoNombre;
    private JPasswordField textoContraseña;
    private JTextField textoEdad;
    private JTextField textoCorreo;
    private JTextField textoImagen;
    private JButton btnImagen;
    private JButton btnCrear;
    private JButton btnCancelar;
    private App app;

    public UserCreate(App app) {
        super(app);
        this.app = app;
        setTitle("Aplicación usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 322, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        etiquetaCrearUsuario = new JLabel("CREAR USUARIO");
        etiquetaCrearUsuario.setBounds(80, 11, 154, 20);
        etiquetaCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(etiquetaCrearUsuario);

        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaNombre.setBounds(45, 56, 68, 14);
        contentPane.add(etiquetaNombre);

        textoNombre = new JTextField();
        textoNombre.setBounds(45, 81, 214, 20);
        contentPane.add(textoNombre);
        textoNombre.setColumns(10);

        etiquetaContraseña = new JLabel("Contraseña:");
        etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaContraseña.setBounds(45, 112, 68, 14);
        contentPane.add(etiquetaContraseña);

        textoContraseña = new JPasswordField();
        textoContraseña.setColumns(10);
        textoContraseña.setBounds(45, 137, 214, 20);
        contentPane.add(textoContraseña);

        etiquetaEdad = new JLabel("Edad:");
        etiquetaEdad.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaEdad.setBounds(45, 168, 68, 14);
        contentPane.add(etiquetaEdad);

        textoEdad = new JTextField();
        textoEdad.setColumns(10);
        textoEdad.setBounds(45, 193, 214, 20);
        contentPane.add(textoEdad);

        etiquetaCorreo = new JLabel("Correo electrónico:");
        etiquetaCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaCorreo.setBounds(45, 224, 214, 14);
        contentPane.add(etiquetaCorreo);

        textoCorreo = new JTextField();
        textoCorreo.setColumns(10);
        textoCorreo.setBounds(45, 249, 214, 20);
        contentPane.add(textoCorreo);

        etiquetaImagen = new JLabel("Imagen de perfil:");
        etiquetaImagen.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaImagen.setBounds(45, 280, 214, 14);
        contentPane.add(etiquetaImagen);

        btnImagen = new JButton("Subir imagen");
        btnImagen.setBounds(45, 300, 214, 23);
        btnImagen.addActionListener(this);
        contentPane.add(btnImagen);

        textoImagen = new JTextField();
        textoImagen.setColumns(10);
        textoImagen.setBounds(45, 330, 214, 20);
        textoImagen.setEnabled(false);
        contentPane.add(textoImagen);

        btnCrear = new JButton("Crear");
        btnCrear.setBounds(168, 380, 89, 23);
        btnCrear.addActionListener(this);
        contentPane.add(btnCrear);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(45, 380, 89, 23);
        btnCancelar.addActionListener(this);
        contentPane.add(btnCancelar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            clearFields();
            this.dispose();
            this.app.showUser(this.app.getCurrentUser());
            return;
        }

        if (e.getSource() == btnCrear) {
            // Load and filter data
            String name, mail, image;
            int age = 0;
            char[] pwd;

            name = this.textoNombre.getText();
            pwd = this.textoContraseña.getPassword();
            mail = this.textoCorreo.getText();
            image = this.textoImagen.getText();

            if (name.isEmpty() || pwd.length == 0 || mail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                try {
                    age = Integer.parseInt(this.textoEdad.getText());
                } catch (NumberFormatException ex) {
                    this.textoEdad.requestFocus();
                    this.textoEdad.selectAll();
                    JOptionPane.showMessageDialog(this, "Dato edad incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (age < 0 || age == 0) {
                this.textoEdad.requestFocus();
                this.textoEdad.selectAll();
                JOptionPane.showMessageDialog(this, "La edad debe ser mayor y diferente de 0", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add user to App
            model.User user = new model.User(name, String.valueOf(pwd), age, mail);
            File userImage = null;
            if (!textoImagen.getText().isEmpty()) {
                userImage = new File(textoImagen.getText());
            }
            if (!this.app.addUser(user, userImage)) {
                JOptionPane.showMessageDialog(this, "No se ha podido crear el usuario. El usuario ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.app.logOut();
            this.dispose();
        }

        if (e.getSource() == btnImagen) {
            System.out.println("Subir imagen de usuario.");
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                textoImagen.setText(selectedFile.getPath());
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección de archivo cancelada.");
            }
        }
    }


    private void clearFields() {
        this.textoNombre.setText("");
        this.textoContraseña.setText("");
        this.textoEdad.setText("");
        this.textoCorreo.setText("");
        this.textoNombre.requestFocus();
    }
}
