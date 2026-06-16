package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservacionvista extends JDialog {

    private final Color DORADO = new Color(183, 148, 85);
    private final Color DORADO_CLARO = new Color(210, 185, 120);
    private final Color FORM_BG = new Color(30, 30, 30);
    private final Color FORM_BORDE = new Color(60, 60, 60);

    private JSpinner spinLlegada;
    private JSpinner spinSalida;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JButton btnReservar;
    private final String habitacion;

    public Reservacionvista(JFrame padre, String habitacion) {
        super(padre, "Reservar - " + habitacion, true);
        this.habitacion = habitacion;
        setSize(430, 400);
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

        JLabel titulo = new JLabel(habitacion, SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 40, 40));
        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 3, 0);
        encabezado.add(titulo, gbc);

        JLabel sub = new JLabel("Complete sus datos para confirmar la reserva", SwingConstants.CENTER);
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

        spinLlegada = crearSpinner();
        spinSalida = crearSpinner();
        txtNombre = crearCampo("NOMBRE COMPLETO");
        txtCorreo = crearCampo("CORREO DE CONFIRMACI\u00d3N");
        txtTelefono = crearCampo("TEL\u00c9FONO M\u00d3VIL");

        fbc.gridy = 0; formulario.add(spinLlegada, fbc);
        fbc.gridy = 1; formulario.add(spinSalida, fbc);
        fbc.gridy = 2; formulario.add(txtNombre, fbc);
        fbc.gridy = 3; formulario.add(txtCorreo, fbc);
        fbc.gridy = 4; formulario.add(txtTelefono, fbc);
        contenedor.add(formulario, BorderLayout.CENTER);

        JPanel botonera = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        botonera.setBackground(Color.WHITE);
        botonera.setBorder(new EmptyBorder(5, 20, 20, 20));

        btnReservar = new JButton("CONFIRMAR RESERVA");
        btnReservar.setBackground(DORADO);
        btnReservar.setForeground(new Color(20, 20, 20));
        btnReservar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnReservar.setFocusPainted(false);
        btnReservar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReservar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnReservar.setPreferredSize(new Dimension(370, 40));
        botonera.add(btnReservar);
        contenedor.add(botonera, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);
    }

    private JSpinner crearSpinner() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);
        spinner.setBackground(FORM_BG);
        spinner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, DORADO),
            new EmptyBorder(2, 5, 2, 5)));
        spinner.setPreferredSize(new Dimension(370, 32));
        for (Component c : spinner.getEditor().getComponents()) {
            if (c instanceof JTextField) {
                c.setBackground(FORM_BG);
                c.setForeground(new Color(200, 200, 200));
                c.setFont(new Font("SansSerif", Font.PLAIN, 12));
            }
        }
        return spinner;
    }

    private JTextField crearCampo(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setBackground(FORM_BG);
        campo.setForeground(new Color(160, 160, 160));
        campo.setCaretColor(DORADO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, DORADO),
            new EmptyBorder(6, 8, 6, 8)));
        campo.setPreferredSize(new Dimension(370, 32));
        return campo;
    }

    public String getHabitacion() { return habitacion; }
    public JSpinner getSpinLlegada() { return spinLlegada; }
    public JSpinner getSpinSalida() { return spinSalida; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtCorreo() { return txtCorreo; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JButton getBtnReservar() { return btnReservar; }
}
