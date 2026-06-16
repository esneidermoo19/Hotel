package vista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import modelo.conexionBD;

public class AdminPanelvista extends JFrame {

    private final Color FONDO_NAV = new Color(12, 12, 12);
    private final Color FONDO_CONT = new Color(238, 238, 243);
    private final Color DORADO = new Color(183, 148, 85);
    private final Color DORADO_CLARO = new Color(210, 185, 120);

    private JTable tblReservas;
    private DefaultTableModel modeloTabla;
    private JButton btnAceptarReserva;
    private JButton btnModificarEstancia;
    private JButton btnGestionarHabitaciones;
    private JButton btnVerReportes;
    private JButton btnAdminEmpleados;
    private JButton btnCerrarSesion;

    public AdminPanelvista() {
        setTitle("Hotel La Orqu\u00eddea - Panel Administrador");
        setSize(1050, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        buildNavbar();
        buildContenido();
        cargarReservas();
    }

    private void buildNavbar() {
        JPanel nav = new JPanel(new BorderLayout());
        nav.setBackground(FONDO_NAV);
        nav.setPreferredSize(new Dimension(1050, 65));
        nav.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, DORADO),
            new EmptyBorder(0, 30, 0, 30)));

        JLabel logo = new JLabel("LA ORQU\u00cdDEA  |  PANEL ADMINISTRADOR", SwingConstants.CENTER);
        logo.setForeground(DORADO_CLARO);
        logo.setFont(new Font("Serif", Font.BOLD, 20));
        nav.add(logo, BorderLayout.CENTER);
        add(nav, BorderLayout.NORTH);
    }

    private void buildContenido() {
        JPanel cont = new JPanel(new BorderLayout());
        cont.setBackground(FONDO_CONT);
        cont.setBorder(new EmptyBorder(25, 35, 25, 35));

        JLabel titulo = new JLabel("Panel de Control - Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 26));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setBorder(new EmptyBorder(0, 0, 18, 0));
        cont.add(titulo, BorderLayout.NORTH);

        String[] cols = {"ID", "Habitaci\u00f3n", "Llegada", "Salida", "Cliente", "Correo", "Tel\u00e9fono", "Estado"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblReservas = new JTable(modeloTabla);
        tblReservas.setRowHeight(32);
        tblReservas.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblReservas.setSelectionBackground(new Color(210, 185, 120));
        tblReservas.setSelectionForeground(Color.BLACK);
        tblReservas.setShowGrid(false);
        tblReservas.setIntercellSpacing(new Dimension(0, 0));
        tblReservas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblReservas.getColumnCount(); i++) {
            tblReservas.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JTableHeader header = tblReservas.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 11));
        header.setBackground(FONDO_NAV);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 36));

        JScrollPane scroll = new JScrollPane(tblReservas);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(215, 215, 220)));
        scroll.getViewport().setBackground(Color.WHITE);
        cont.add(scroll, BorderLayout.CENTER);

        JPanel botonera = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botonera.setBackground(FONDO_CONT);
        botonera.setBorder(new EmptyBorder(18, 0, 0, 0));

        btnAceptarReserva = crearBoton("ACEPTAR RESERVA");
        btnModificarEstancia = crearBoton("MODIFICAR ESTANCIA");
        btnGestionarHabitaciones = crearBoton("GESTIONAR HABITACIONES");
        btnVerReportes = crearBoton("VER REPORTES");
        btnAdminEmpleados = crearBoton("ADMIN. EMPLEADOS");
        btnCerrarSesion = crearBoton("CERRAR SESI\u00d3N");
        btnCerrarSesion.setBackground(new Color(80, 80, 80));

        botonera.add(btnAceptarReserva);
        botonera.add(btnModificarEstancia);
        botonera.add(btnGestionarHabitaciones);
        botonera.add(btnVerReportes);
        botonera.add(btnAdminEmpleados);
        botonera.add(btnCerrarSesion);
        cont.add(botonera, BorderLayout.SOUTH);
        add(cont, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(DORADO);
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("SansSerif", Font.BOLD, 10));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        btn.setPreferredSize(new Dimension(150, 38));
        return btn;
    }

    public void cargarReservas() {
        modeloTabla.setRowCount(0);
        String sql = "SELECT id, habitacion, llegada, salida, nombre, correo, telefono, estado "
                + "FROM reservaciones ORDER BY id DESC";
        try (Connection conn = conexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("habitacion"),
                    rs.getString("llegada"),
                    rs.getString("salida"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("estado")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar reservas: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JTable getTblReservas() { return tblReservas; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JButton getBtnAceptarReserva() { return btnAceptarReserva; }
    public JButton getBtnModificarEstancia() { return btnModificarEstancia; }
    public JButton getBtnGestionarHabitaciones() { return btnGestionarHabitaciones; }
    public JButton getBtnVerReportes() { return btnVerReportes; }
    public JButton getBtnAdminEmpleados() { return btnAdminEmpleados; }
    public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
}
