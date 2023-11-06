package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.App;

public class UserDetails extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaDatosUsuario;
    private JLabel etiquetaNombre;
    private JLabel etiquetaEdad;
    private JLabel etiquetaCorreo;
    private JTextPane datoNombre;
    private JTextPane datoEdad;
    private JTextPane datoCorreo;
    private JButton btnVolver;
    private JMenuItem xmlMenu;
    private JMenuItem jsonMenu;

    private JMenuItem csvMenu;
    private JMenuItem pdfMenu;
    private App app;

    public UserDetails(App app, String nombreUsuario, String edad, String correo) {
        this.app = app;

        setTitle("Aplicación usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 304, 390);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        etiquetaDatosUsuario = new JLabel("Datos usuario");
        etiquetaDatosUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaDatosUsuario.setBounds(64, 32, 169, 30);
        contentPane.add(etiquetaDatosUsuario);

        datoNombre = new JTextPane();
        datoNombre.setEditable(false);
        datoNombre.setBounds(64, 111, 169, 20);
        datoNombre.setText(nombreUsuario);
        contentPane.add(datoNombre);

        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaNombre.setBounds(64, 86, 57, 14);
        contentPane.add(etiquetaNombre);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(99, 278, 89, 23);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);

        etiquetaEdad = new JLabel("Edad:");
        etiquetaEdad.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaEdad.setBounds(64, 142, 57, 14);
        contentPane.add(etiquetaEdad);

        datoEdad = new JTextPane();
        datoEdad.setEditable(false);
        datoEdad.setBounds(64, 167, 169, 20);
        datoEdad.setText(edad);
        contentPane.add(datoEdad);

        etiquetaCorreo = new JLabel("Correo electrónico:");
        etiquetaCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaCorreo.setBounds(64, 209, 169, 14);
        contentPane.add(etiquetaCorreo);

        datoCorreo = new JTextPane();
        datoCorreo.setEditable(false);
        datoCorreo.setBounds(64, 234, 169, 20);
        datoCorreo.setText(correo);
        contentPane.add(datoCorreo);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu exportarMenu = new JMenu("Exportar");
        menuBar.add(exportarMenu);

        xmlMenu = new JMenuItem("XML");
        jsonMenu = new JMenuItem("JSON");
        csvMenu = new JMenuItem("CSV");
        pdfMenu = new JMenuItem("PDF");

        xmlMenu.addActionListener(this);
        jsonMenu.addActionListener(this);
        csvMenu.addActionListener(this);
        pdfMenu.addActionListener(this);

        exportarMenu.add(xmlMenu);
        exportarMenu.add(jsonMenu);
        exportarMenu.add(csvMenu);
        exportarMenu.add(pdfMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == xmlMenu) {
            System.out.println("Exportar usuario (XML)");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("usuario.xml"));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.app.exportUserToXML(selectedFile);
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección de archivo cancelada.");
            }
        }

        if (e.getSource() == jsonMenu) {
            System.out.println("Exportar usuario (JSON)");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("usuario.json"));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.app.exportUserToJSON(selectedFile);
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección de archivo cancelada.");
            }
        }

        if (e.getSource() == csvMenu) {
            System.out.println("Exportar usuario (CSV)");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("usuario.csv"));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.app.exportUserToCSV(selectedFile);
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección de archivo cancelada.");
            }
        }

        if (e.getSource() == pdfMenu) {
            System.out.println("Exportar usuario (PDF)");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("usuario.pdf"));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.app.exportUserToPDF(selectedFile);
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección de archivo cancelada.");
            }
        }

        if (e.getSource() == btnVolver) {
			this.dispose();
        }
    }
}
