package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Loginvista extends JFrame {

    private final Color COLOR_FONDO = new Color(18, 18, 18);
    private final Color COLOR_DORADO = new Color(183, 148, 85);
    private final Color COLOR_DORADO_CLARO = new Color(210, 185, 120);
    private final Color COLOR_FORM = new Color(30, 30, 30);
    private final Color COLOR_BORDE_FORM = new Color(60, 60, 60);

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;
    private JButton btnIniciarSesion;

    public Loginvista() {
        setTitle("Hotel La Orqu\u00eddea - Inicio de Sesi\u00f3n");
        setSize(440, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(COLOR_FONDO);
        contenedor.setBorder(new EmptyBorder(50, 55, 40, 55));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.gridx = 0;

        JPanel decorLine = new JPanel();
        decorLine.setBackground(COLOR_DORADO);
        decorLine.setPreferredSize(new Dimension(60, 3));
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 18, 0);
        contenedor.add(decorLine, gbc);

        JLabel lblLogo = new JLabel("LA ORQU\u00cdDEA", SwingConstants.CENTER);
        lblLogo.setForeground(COLOR_DORADO_CLARO);
        lblLogo.setFont(new Font("Serif", Font.BOLD, 30));
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 5, 0);
        contenedor.add(lblLogo, gbc);

        JLabel lblSubtitulo = new JLabel("Iniciar Sesi\u00f3n", SwingConstants.CENTER);
        lblSubtitulo.setForeground(new Color(140, 140, 140));
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 28, 0);
        contenedor.add(lblSubtitulo, gbc);

        gbc.insets = new Insets(4, 0, 4, 0);
        JLabel lblUsuario = new JLabel("USUARIO");
        lblUsuario.setForeground(new Color(160, 160, 160));
        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblUsuario.setBorder(new EmptyBorder(0, 1, 0, 0));
        gbc.gridy = 3;
        contenedor.add(lblUsuario, gbc);

        txtUsuario = new JTextField();
        txtUsuario.setBackground(COLOR_FORM);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(COLOR_DORADO);
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDE_FORM),
            new EmptyBorder(6, 8, 6, 8)));
        txtUsuario.setPreferredSize(new Dimension(310, 36));
        gbc.gridy = 4;
        contenedor.add(txtUsuario, gbc);

        JLabel lblContrasena = new JLabel("CONTRASE\u00d1A");
        lblContrasena.setForeground(new Color(160, 160, 160));
        lblContrasena.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblContrasena.setBorder(new EmptyBorder(0, 1, 0, 0));
        gbc.gridy = 5;
        contenedor.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField();
        txtContrasena.setBackground(COLOR_FORM);
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setCaretColor(COLOR_DORADO);
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDE_FORM),
            new EmptyBorder(6, 8, 6, 8)));
        txtContrasena.setPreferredSize(new Dimension(310, 36));
        gbc.gridy = 6;
        contenedor.add(txtContrasena, gbc);

        JLabel lblRol = new JLabel("ROL");
        lblRol.setForeground(new Color(160, 160, 160));
        lblRol.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblRol.setBorder(new EmptyBorder(0, 1, 0, 0));
        gbc.gridy = 7;
        contenedor.add(lblRol, gbc);

        cmbRol = new JComboBox<>(new String[]{"Empleado", "Administrador"});
        cmbRol.setBackground(COLOR_FORM);
        cmbRol.setForeground(Color.WHITE);
        cmbRol.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, COLOR_BORDE_FORM),
            new EmptyBorder(4, 6, 4, 6)));
        cmbRol.setPreferredSize(new Dimension(310, 36));
        ((JLabel) cmbRol.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 8;
        contenedor.add(cmbRol, gbc);

        gbc.insets = new Insets(22, 0, 0, 0);
        btnIniciarSesion = new JButton("INICIAR SESI\u00d3N");
        btnIniciarSesion.setBackground(COLOR_DORADO);
        btnIniciarSesion.setForeground(new Color(20, 20, 20));
        btnIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIniciarSesion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnIniciarSesion.setPreferredSize(new Dimension(310, 42));
        gbc.gridy = 9;
        contenedor.add(btnIniciarSesion, gbc);

        add(contenedor, BorderLayout.CENTER);
    }

    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JComboBox<String> getCmbRol() { return cmbRol; }
    public JButton getBtnIniciarSesion() { return btnIniciarSesion; }

    public void limpiar() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
    }
}
