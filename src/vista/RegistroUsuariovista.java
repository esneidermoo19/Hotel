package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistroUsuariovista extends JDialog {

    private final Color DORADO = new Color(183, 148, 85);
    private final Color FORM_BG = new Color(30, 30, 30);

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistroUsuariovista(JFrame padre) {
        super(padre, "Hotel La Orqu\u00eddea - Registrar Usuario", true);
        setSize(400, 320);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(Color.WHITE);

        JPanel encabezado = new JPanel(new GridBagLayout());
        encabezado.setBackground(Color.WHITE);
        encabezado.setBorder(new EmptyBorder(20, 20, 5, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("REGISTRAR USUARIO", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 40, 40));
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 3, 0);
        encabezado.add(titulo, gbc);

        JLabel sub = new JLabel("Cree un nuevo usuario para el sistema", SwingConstants.CENTER);
        sub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        sub.setForeground(new Color(150, 150, 150));
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 0, 0);
        encabezado.add(sub, gbc);
        contenedor.add(encabezado, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(new EmptyBorder(5, 20, 10, 20));
        GridBagConstraints fbc = new GridBagConstraints();
        fbc.fill = GridBagConstraints.HORIZONTAL;
        fbc.insets = new Insets(4, 0, 4, 0);
        fbc.gridx = 0;

        txtUsuario = crearCampo("USUARIO");
        txtContrasena = new JPasswordField();
        txtContrasena.setBackground(FORM_BG);
        txtContrasena.setForeground(new Color(160, 160, 160));
        txtContrasena.setCaretColor(DORADO);
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, DORADO),
            new EmptyBorder(6, 8, 6, 8)));
        txtContrasena.setPreferredSize(new Dimension(340, 32));
        txtContrasena.setText("CONTRASE\u00d1A");

        cmbRol = new JComboBox<>(new String[]{"Empleado", "Administrador"});
        cmbRol.setBackground(FORM_BG);
        cmbRol.setForeground(new Color(200, 200, 200));
        cmbRol.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cmbRol.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, DORADO),
            new EmptyBorder(4, 8, 4, 8)));
        cmbRol.setPreferredSize(new Dimension(340, 32));

        fbc.gridy = 0; formulario.add(txtUsuario, fbc);
        fbc.gridy = 1; formulario.add(txtContrasena, fbc);
        fbc.gridy = 2; formulario.add(cmbRol, fbc);
        contenedor.add(formulario, BorderLayout.CENTER);

        JPanel botonera = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botonera.setBackground(Color.WHITE);
        botonera.setBorder(new EmptyBorder(5, 20, 20, 20));

        btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setBackground(DORADO);
        btnRegistrar.setForeground(new Color(20, 20, 20));
        btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRegistrar.setPreferredSize(new Dimension(160, 38));

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBackground(new Color(80, 80, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCancelar.setPreferredSize(new Dimension(160, 38));
        btnCancelar.addActionListener(e -> dispose());

        botonera.add(btnRegistrar);
        botonera.add(btnCancelar);
        contenedor.add(botonera, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);
    }

    private JTextField crearCampo(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setBackground(FORM_BG);
        campo.setForeground(new Color(160, 160, 160));
        campo.setCaretColor(DORADO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, DORADO),
            new EmptyBorder(6, 8, 6, 8)));
        campo.setPreferredSize(new Dimension(340, 32));
        return campo;
    }

    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JComboBox<String> getCmbRol() { return cmbRol; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
