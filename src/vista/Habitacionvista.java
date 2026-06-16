package vista;

import controlador.Reservacioncontrolador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modelo.conexionBD;
import modelo.Habitacionmodelo;

public class Habitacionvista extends JFrame {

    private final Color FONDO_NAV = new Color(12, 12, 12);
    private final Color FONDO_CONT = new Color(238, 238, 243);
    private final Color DORADO = new Color(183, 148, 85);
    private final Color DORADO_CLARO = new Color(210, 185, 120);
    private final Color TEXTO_NAV = new Color(200, 200, 200);
    private final Color TARJETA_FONDO = Color.WHITE;
    private final Color TEXTO_TARJETA = new Color(50, 50, 50);
    private final Color VERDE = new Color(46, 184, 92);
    private final Color ROJO = new Color(210, 70, 70);
    private final Color SOMBRA = new Color(0, 0, 0, 20);

    private final Font TITULO = new Font("Serif", Font.PLAIN, 34);
    private final Font SUBTITULO = new Font("SansSerif", Font.ITALIC, 13);
    private final Font LABEL = new Font("SansSerif", Font.BOLD, 10);
    private final Font NOMBRE = new Font("Serif", Font.BOLD, 18);
    private final Font DESCRIPCION = new Font("SansSerif", Font.PLAIN, 11);
    private final Font PRECIO = new Font("SansSerif", Font.BOLD, 16);

    private JSpinner spinDesde, spinHasta;
    private JPanel panelGaleria;
    private JScrollPane scrollGaleria;
    private final JPanel contenedorGaleria = new JPanel(new BorderLayout());
    private JButton btnSalir;

    private final Habitacionmodelo[] rooms = {
        new Habitacionmodelo("Habitaci\u00f3n Simple", "Cama individual, ba\u00f1o privado, vista al jard\u00edn, WiFi y aire acondicionado.", "$45 / noche", "Simple", "imagenes/default_simple.png"),
        new Habitacionmodelo("Habitaci\u00f3n Doble", "Dos camas, ba\u00f1o completo, balc\u00f3n, minibar, TV y WiFi gratuito.", "$75 / noche", "Doble", "imagenes/doble1.jpg"),
        new Habitacionmodelo("Suite Premium", "Cama king size, sala de estar, jacuzzi, terraza privada y servicio a la habitaci\u00f3n.", "$140 / noche", "Suite", "imagenes/suite1.jpg"),
        new Habitacionmodelo("Habitaci\u00f3n Deluxe", "Cama queen size, sala peque\u00f1a, TV 50\", escritorio y WiFi premium.", "$100 / noche", "Deluxe", "imagenes/doble2.jpg"),
        new Habitacionmodelo("Habitaci\u00f3n Familiar", "Dos camas dobles, espacio infantil, refrigerador, microondas y TV.", "$110 / noche", "Familiar", "imagenes/default_doble.png"),
        new Habitacionmodelo("Penthouse Suite", "Dos niveles, terraza panor\u00e1mica, cocina equipada y bar privado.", "$250 / noche", "Penthouse", "imagenes/default_suite.png")
    };

    public Habitacionvista() {
        setTitle("Hotel La Orqu\u00eddea - Gesti\u00f3n de Habitaciones");
        setSize(1050, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        buildNavbar();
        buildContenido();
    }

    private void buildNavbar() {
        JPanel nav = new JPanel(new BorderLayout());
        nav.setBackground(FONDO_NAV);
        nav.setPreferredSize(new Dimension(1050, 65));
        nav.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, DORADO),
            new EmptyBorder(0, 25, 0, 25)));

        btnSalir = new JButton("\u2190 SALIR");
        btnSalir.setBackground(FONDO_NAV);
        btnSalir.setForeground(TEXTO_NAV);
        btnSalir.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalir.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSalir.addActionListener(e -> dispose());
        nav.add(btnSalir, BorderLayout.WEST);

        JLabel logo = new JLabel("LA ORQU\u00cdDEA", SwingConstants.CENTER);
        logo.setForeground(DORADO_CLARO);
        logo.setFont(new Font("Serif", Font.BOLD, 22));
        nav.add(logo, BorderLayout.CENTER);

        JPanel derecha = new JPanel();
        derecha.setOpaque(false);
        derecha.setPreferredSize(new Dimension(80, 65));
        nav.add(derecha, BorderLayout.EAST);

        add(nav, BorderLayout.NORTH);
    }

    private void buildContenido() {
        JPanel cont = new JPanel(new BorderLayout());
        cont.setBackground(FONDO_CONT);
        cont.add(buildFormulario(), BorderLayout.NORTH);
        buildGaleria(new HashSet<String>());
        cont.add(contenedorGaleria, BorderLayout.CENTER);
        add(cont, BorderLayout.CENTER);
    }

    private JPanel buildFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO_CONT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Habitaciones & Suites", SwingConstants.CENTER);
        titulo.setFont(TITULO);
        titulo.setForeground(TEXTO_TARJETA);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.insets = new Insets(35, 10, 3, 10);
        panel.add(titulo, gbc);

        JLabel sub = new JLabel("Experiencias de alojamiento dise\u00f1adas para el viajero contempor\u00e1neo", SwingConstants.CENTER);
        sub.setFont(SUBTITULO);
        sub.setForeground(new Color(160, 160, 160));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        gbc.insets = new Insets(3, 10, 28, 10);
        panel.add(sub, gbc);

        gbc.gridwidth = 1; gbc.weightx = 0.0;

        JLabel lblDesde = new JLabel("DESDE");
        lblDesde.setFont(LABEL);
        lblDesde.setForeground(new Color(150, 150, 150));
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 3, 10);
        panel.add(lblDesde, gbc);

        JLabel lblHasta = new JLabel("HASTA");
        lblHasta.setFont(LABEL);
        lblHasta.setForeground(new Color(150, 150, 150));
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(lblHasta, gbc);

        spinDesde = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor edDesde = new JSpinner.DateEditor(spinDesde, "dd/MM/yyyy");
        spinDesde.setEditor(edDesde);
        spinDesde.setPreferredSize(new Dimension(155, 36));
        spinDesde.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210)), new EmptyBorder(2, 5, 2, 5)));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 22, 10);
        panel.add(spinDesde, gbc);

        spinHasta = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor edHasta = new JSpinner.DateEditor(spinHasta, "dd/MM/yyyy");
        spinHasta.setEditor(edHasta);
        spinHasta.setPreferredSize(new Dimension(155, 36));
        spinHasta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210)), new EmptyBorder(2, 5, 2, 5)));
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(spinHasta, gbc);

        JButton btnDisp = new JButton("VER DISPONIBILIDAD");
        btnDisp.setBackground(DORADO);
        btnDisp.setForeground(new Color(20, 20, 20));
        btnDisp.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnDisp.setFocusPainted(false);
        btnDisp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDisp.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btnDisp.setPreferredSize(new Dimension(185, 36));
        btnDisp.addActionListener(this::buscarDisponibilidad);
        gbc.gridx = 2; gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 22, 10);
        panel.add(btnDisp, gbc);

        return panel;
    }

    private void buildGaleria(Set<String> ocupadas) {
        contenedorGaleria.removeAll();
        panelGaleria = new JPanel(new GridLayout(0, 3, 28, 25));
        panelGaleria.setBackground(FONDO_CONT);
        for (Habitacionmodelo room : rooms) {
            panelGaleria.add(crearTarjeta(room, ocupadas.contains(room.getNombre())));
        }
        scrollGaleria = new JScrollPane(panelGaleria);
        scrollGaleria.setBorder(null);
        scrollGaleria.setBackground(FONDO_CONT);
        scrollGaleria.getVerticalScrollBar().setUnitIncrement(16);
        scrollGaleria.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        contenedorGaleria.add(scrollGaleria, BorderLayout.CENTER);
        contenedorGaleria.revalidate();
        contenedorGaleria.repaint();
    }

    private JPanel crearTarjeta(Habitacionmodelo room, boolean ocupada) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(TARJETA_FONDO);
        tarjeta.setPreferredSize(new Dimension(290, 370));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ocupada ? new Color(240, 200, 200) : new Color(230, 230, 235), 1),
            new EmptyBorder(0, 0, 0, 0)));

        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setPreferredSize(new Dimension(290, 190));
        ImageIcon icono = cargarImagen(room.getImagen(), 290, 190);
        if (icono != null) {
            JLabel imgLabel = new JLabel(icono);
            imgPanel.add(imgLabel, BorderLayout.CENTER);
        }
        if (ocupada) {
            JPanel overlay = new JPanel(new GridBagLayout());
            overlay.setBackground(new Color(0, 0, 0, 140));
            JLabel lblOcupada = new JLabel("OCUPADA");
            lblOcupada.setFont(new Font("SansSerif", Font.BOLD, 20));
            lblOcupada.setForeground(Color.WHITE);
            overlay.add(lblOcupada);
            imgPanel.add(overlay, BorderLayout.CENTER);
        }
        tarjeta.add(imgPanel, BorderLayout.CENTER);

        JPanel info = new JPanel(new GridBagLayout());
        info.setBackground(TARJETA_FONDO);
        info.setBorder(new EmptyBorder(12, 14, 14, 14));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 3, 0);
        gbc.gridx = 0;

        gbc.gridy = 0;
        JLabel lblNombre = new JLabel(room.getNombre());
        lblNombre.setFont(NOMBRE);
        lblNombre.setForeground(TEXTO_TARJETA);
        info.add(lblNombre, gbc);

        gbc.gridy = 1;
        JLabel lblEstado = new JLabel(ocupada ? "NO DISPONIBLE" : "DISPONIBLE");
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblEstado.setForeground(ocupada ? ROJO : VERDE);
        info.add(lblEstado, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(6, 0, 0, 0);
        JLabel lblPrecio = new JLabel(room.getPrecio());
        lblPrecio.setFont(PRECIO);
        lblPrecio.setForeground(DORADO);
        info.add(lblPrecio, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 0);
        JButton btnRes = new JButton(ocupada ? "NO DISPONIBLE" : "RESERVAR AHORA");
        btnRes.setBackground(ocupada ? new Color(80, 80, 80) : DORADO);
        btnRes.setForeground(ocupada ? new Color(160, 160, 160) : new Color(20, 20, 20));
        btnRes.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnRes.setFocusPainted(false);
        btnRes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRes.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        btnRes.setPreferredSize(new Dimension(260, 32));
        btnRes.setEnabled(!ocupada);
        if (!ocupada) {
            btnRes.addActionListener(e -> {
                Reservacionvista dialog = new Reservacionvista(Habitacionvista.this, room.getNombre());
                new Reservacioncontrolador(dialog);
                dialog.setVisible(true);
            });
        }
        info.add(btnRes, gbc);

        tarjeta.add(info, BorderLayout.SOUTH);
        return tarjeta;
    }

    private ImageIcon cargarImagen(String ruta, int ancho, int alto) {
        java.net.URL url = getClass().getResource(ruta);
        if (url == null) return null;
        ImageIcon icono = new ImageIcon(url);
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void buscarDisponibilidad(ActionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String desde = sdf.format((Date) spinDesde.getValue());
        String hasta = sdf.format((Date) spinHasta.getValue());
        if (((Date) spinHasta.getValue()).before((Date) spinDesde.getValue())) {
            JOptionPane.showMessageDialog(this,
                "La fecha de salida debe ser posterior a la fecha de entrada.",
                "Fechas inv\u00e1lidas", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Set<String> ocupadas = new HashSet<>();
        String sql = "SELECT DISTINCT habitacion FROM reservaciones "
                + "WHERE TO_DATE(llegada, 'DD/MM/YYYY') <= TO_DATE(?, 'DD/MM/YYYY') "
                + "AND TO_DATE(salida, 'DD/MM/YYYY') >= TO_DATE(?, 'DD/MM/YYYY')";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hasta);
            ps.setString(2, desde);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) ocupadas.add(rs.getString("habitacion"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error al consultar disponibilidad: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        buildGaleria(ocupadas);
    }

    public JButton getBtnSalir() { return btnSalir; }
}
