package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import controlador.Reservacioncontrolador;
import modelo.conexionBD;

public class AdminPanelvista extends JFrame {

    private final Color FONDO_NAV = new Color(12, 12, 12);
    private final Color FONDO_CONT = new Color(238, 238, 243);
    private final Color DORADO = new Color(183, 148, 85);
    private final Color DORADO_CLARO = new Color(210, 185, 120);
    private final Color TEXTO_NAV = new Color(200, 200, 200);
    private final Color TARJETA_FONDO = Color.WHITE;
    private final Color TEXTO_TARJETA = new Color(50, 50, 50);
    private final Color VERDE = new Color(46, 184, 92);
    private final Color AMARILLO = new Color(200, 150, 50);
    private final Color AZUL = new Color(60, 130, 210);

    private final Font TITULO = new Font("Serif", Font.PLAIN, 30);
    private final Font SUBTITULO = new Font("SansSerif", Font.ITALIC, 12);

    private JButton btnSalir;
    private JButton btnAgregarHabitacion;
    private JButton btnRegistrarUsuario;
    private JPanel panelGaleria;

    private static final String[][] DEFAULT_ROOMS = {
        {"Habitaci\u00f3n Simple", "Cama individual, ba\u00f1o privado, vista al jard\u00edn, WiFi y aire acondicionado.", "$45 / noche", "Simple", "Disponible", "imagenes/default_simple.png"},
        {"Habitaci\u00f3n Doble", "Dos camas, ba\u00f1o completo, balc\u00f3n, minibar, TV y WiFi gratuito.", "$75 / noche", "Doble", "Disponible", "imagenes/doble1.jpg"},
        {"Suite Premium", "Cama king size, sala de estar, jacuzzi, terraza privada y servicio a la habitaci\u00f3n.", "$140 / noche", "Suite", "Disponible", "imagenes/suite1.jpg"},
        {"Habitaci\u00f3n Deluxe", "Cama queen size, sala peque\u00f1a, TV 50\", escritorio y WiFi premium.", "$100 / noche", "Deluxe", "Disponible", "imagenes/doble2.jpg"},
        {"Habitaci\u00f3n Familiar", "Dos camas dobles, espacio infantil, refrigerador, microondas y TV.", "$110 / noche", "Familiar", "Disponible", "imagenes/default_doble.png"},
        {"Penthouse Suite", "Dos niveles, terraza panor\u00e1mica, cocina equipada y bar privado.", "$250 / noche", "Penthouse", "Disponible", "imagenes/default_suite.png"},
        {"Habitaci\u00f3n Econ\u00f3mica", "Habitaci\u00f3n sencilla con cama individual, ventilador y ba\u00f1o compartido.", "$25 / noche", "Simple", "Disponible", "imagenes/default_simple.png"},
        {"Habitaci\u00f3n Ejecutiva", "Espacio de trabajo amplio, cama queen, escritorio, silla ergon\u00f3mica y WiFi de alta velocidad.", "$95 / noche", "Doble", "Disponible", "imagenes/doble2.jpg"},
        {"Suite Junior", "Habitaci\u00f3n con cama king, sala peque\u00f1a, TV de pantalla plana y minibar.", "$130 / noche", "Suite", "Disponible", "imagenes/suite1.jpg"},
        {"Habitaci\u00f3n Matrimonial", "Cama matrimonial, ba\u00f1o privado, TV cable, armario amplio y vista interior.", "$60 / noche", "Doble", "Disponible", "imagenes/default_doble.png"},
        {"Suite Presidencial", "Lujosa suite con sala de estar, comedor privado, jacuzzi, terraza y mayordomo.", "$350 / noche", "Suite", "Disponible", "imagenes/suite1.jpg"},
        {"Habitaci\u00f3n Twin", "Dos camas individuales, ba\u00f1o completo, TV, escritorio y WiFi. Ideal para viajeros.", "$65 / noche", "Doble", "Disponible", "imagenes/doble1.jpg"},
        {"Habitaci\u00f3n Superior", "Cama queen, ba\u00f1o renovado, TV 43\", cafetera, vistas al jard\u00edn y balc\u00f3n peque\u00f1o.", "$85 / noche", "Deluxe", "Disponible", "imagenes/doble2.jpg"},
        {"Suite Nupcial", "Suite rom\u00e1ntica con cama king, jacuzzi para dos, velas, cortinas de seda y champ\u00e1n de bienvenida.", "$200 / noche", "Suite", "Disponible", "imagenes/suite1.jpg"},
        {"Habitaci\u00f3n con Vista al Mar", "Amplia habitaci\u00f3n con ventanales panor\u00e1micos al mar, cama king, aire acondicionado y TV.", "$160 / noche", "Deluxe", "Disponible", "imagenes/default_deluxe.png"},
        {"\u00c1tico Premium", "Lujoso \u00e1tico con terraza privada, piscina infinita, sala de estar, cocina totalmente equipada y bar.", "$400 / noche", "Penthouse", "Disponible", "imagenes/default_suite.png"}
    };

    public AdminPanelvista() {
        setTitle("Hotel La Orqu\u00eddea - Panel Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        buildNavbar();
        buildContenido();
        cargarHabitaciones();
    }

    private void buildNavbar() {
        JPanel nav = new JPanel(new BorderLayout());
        nav.setBackground(FONDO_NAV);
        nav.setPreferredSize(new Dimension(0, 60));
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
        nav.add(btnSalir, BorderLayout.WEST);

        JLabel logo = new JLabel("LA ORQU\u00cdDEA", SwingConstants.CENTER);
        logo.setForeground(DORADO_CLARO);
        logo.setFont(new Font("Serif", Font.BOLD, 22));
        nav.add(logo, BorderLayout.CENTER);

        JPanel derecha = new JPanel();
        derecha.setOpaque(false);
        derecha.setPreferredSize(new Dimension(80, 60));
        nav.add(derecha, BorderLayout.EAST);

        add(nav, BorderLayout.NORTH);
    }

    private void buildContenido() {
        JPanel cont = new JPanel(new BorderLayout());
        cont.setBackground(FONDO_CONT);

        JPanel header = new JPanel();
        header.setBackground(FONDO_CONT);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(18, 0, 8, 0));

        JLabel titulo = new JLabel("Todas las Habitaciones", SwingConstants.CENTER);
        titulo.setFont(TITULO);
        titulo.setForeground(TEXTO_TARJETA);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(titulo);

        JLabel sub = new JLabel("Gestione, edite y reserve las habitaciones del hotel", SwingConstants.CENTER);
        sub.setFont(SUBTITULO);
        sub.setForeground(new Color(160, 160, 160));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(sub);

        cont.add(header, BorderLayout.NORTH);

        panelGaleria = new JPanel(new GridBagLayout());
        panelGaleria.setBackground(FONDO_CONT);

        JScrollPane scroll = new JScrollPane(panelGaleria);
        scroll.setBorder(null);
        scroll.setBackground(FONDO_CONT);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        cont.add(scroll, BorderLayout.CENTER);

        JPanel botonera = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        botonera.setBackground(FONDO_CONT);
        botonera.setBorder(new EmptyBorder(10, 0, 18, 0));

        btnAgregarHabitacion = estiloBoton("+ AGREGAR HABITACI\u00d3N", DORADO, new Color(20, 20, 20));
        btnAgregarHabitacion.addActionListener(e -> agregarHabitacion());

        btnRegistrarUsuario = estiloBoton("REGISTRAR USUARIO", new Color(100, 100, 100), Color.WHITE);
        btnRegistrarUsuario.addActionListener(e -> {
            RegistroUsuariovista d = new RegistroUsuariovista(AdminPanelvista.this);
            d.getBtnRegistrar().addActionListener(ev -> registrarUsuario(d));
            d.setVisible(true);
        });

        botonera.add(btnAgregarHabitacion);
        botonera.add(btnRegistrarUsuario);
        cont.add(botonera, BorderLayout.SOUTH);

        add(cont, BorderLayout.CENTER);
    }

    private void registrarUsuario(RegistroUsuariovista vista) {
        String usuario = vista.getTxtUsuario().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();
        String rol = (String) vista.getCmbRol().getSelectedItem();
        if (usuario.isEmpty() || contrasena.isEmpty() || contrasena.equals("CONTRASE\u00d1A")) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos.", "Campos vac\u00edos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (usuario, contrasena, rol) VALUES (?, ?, ?)")) {
            ps.setString(1, usuario); ps.setString(2, contrasena); ps.setString(3, rol);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(vista, "Usuario registrado exitosamente.", "Operaci\u00f3n exitosa", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton estiloBoton(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.BOLD, 11));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }

    private void cargarHabitaciones() {
        List<String[]> lista = new ArrayList<>();
        try (Connection conn = conexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre, descripcion, precio, tipo, estado, imagen FROM habitaciones ORDER BY nombre")) {
            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("nombre"), rs.getString("descripcion"),
                    rs.getString("precio"), rs.getString("tipo"),
                    rs.getString("estado"), rs.getString("imagen")
                });
            }
        } catch (SQLException e) { /* usar defaults */ }

        if (lista.isEmpty()) {
            for (String[] r : DEFAULT_ROOMS) lista.add(r);
        }

        panelGaleria.removeAll();
        panelGaleria.setLayout(new GridLayout(0, 3, 20, 20));
        panelGaleria.setBorder(new EmptyBorder(10, 25, 10, 25));

        for (String[] room : lista) {
            panelGaleria.add(crearTarjeta(room));
        }

        panelGaleria.revalidate();
        panelGaleria.repaint();
    }

    private JPanel crearTarjeta(String[] r) {
        String nombre = r[0], desc = r[1], precio = r[2], tipo = r[3], estado = r[4], img = r[5];
        boolean disponible = "Disponible".equals(estado);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(TARJETA_FONDO);
        card.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 230), 1));

        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setPreferredSize(new Dimension(280, 160));
        ImageIcon icono = loadImg(img, 280, 160);
        if (icono != null) {
            imgPanel.add(new JLabel(icono), BorderLayout.CENTER);
        } else {
            JLabel ph = new JLabel(tipo, SwingConstants.CENTER);
            ph.setFont(new Font("Serif", Font.ITALIC, 24));
            ph.setForeground(new Color(200, 200, 200));
            ph.setBackground(new Color(248, 248, 248));
            ph.setOpaque(true);
            imgPanel.add(ph, BorderLayout.CENTER);
        }
        card.add(imgPanel, BorderLayout.CENTER);

        JPanel info = new JPanel(new GridBagLayout());
        info.setBackground(TARJETA_FONDO);
        info.setBorder(new EmptyBorder(10, 14, 12, 14));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 2, 0);
        g.gridx = 0;

        g.gridy = 0;
        JLabel lblName = new JLabel(nombre);
        lblName.setFont(new Font("Serif", Font.BOLD, 16));
        lblName.setForeground(TEXTO_TARJETA);
        info.add(lblName, g);

        g.gridy = 1;
        JLabel lblTipo = new JLabel(tipo + "  |  " + estado);
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblTipo.setForeground(disponible ? VERDE : ("Mantenimiento".equals(estado) ? AMARILLO : new Color(210, 70, 70)));
        info.add(lblTipo, g);

        g.gridy = 2;
        g.insets = new Insets(4, 0, 0, 0);
        JLabel lblPr = new JLabel(precio);
        lblPr.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblPr.setForeground(DORADO);
        info.add(lblPr, g);

        g.gridy = 3;
        g.insets = new Insets(8, 0, 0, 0);
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        btns.setBackground(TARJETA_FONDO);

        JButton btnRes = new JButton("RESERVAR");
        btnRes.setBackground(disponible ? AZUL : new Color(180, 180, 180));
        btnRes.setForeground(disponible ? Color.WHITE : new Color(210, 210, 210));
        btnRes.setFont(new Font("SansSerif", Font.BOLD, 9));
        btnRes.setFocusPainted(false);
        btnRes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRes.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        btnRes.setEnabled(disponible);
        if (disponible) {
            btnRes.addActionListener(e -> {
                Reservacionvista d = new Reservacionvista(AdminPanelvista.this, nombre);
                new Reservacioncontrolador(d);
                d.setVisible(true);
            });
        }

        JButton btnEdt = new JButton("EDITAR");
        btnEdt.setBackground(DORADO);
        btnEdt.setForeground(new Color(20, 20, 20));
        btnEdt.setFont(new Font("SansSerif", Font.BOLD, 9));
        btnEdt.setFocusPainted(false);
        btnEdt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEdt.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        btnEdt.addActionListener(e -> editarHabitacion(nombre, desc, precio, tipo, img));

        boolean mante = "Mantenimiento".equals(estado);
        JButton btnEst = new JButton(mante ? "HABILITAR" : "MANTENIMIENTO");
        btnEst.setBackground(mante ? VERDE : AMARILLO);
        btnEst.setForeground(new Color(20, 20, 20));
        btnEst.setFont(new Font("SansSerif", Font.BOLD, 9));
        btnEst.setFocusPainted(false);
        btnEst.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEst.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        btnEst.addActionListener(e -> cambiarEstado(nombre, mante ? "Disponible" : "Mantenimiento"));

        btns.add(btnRes);
        btns.add(btnEdt);
        btns.add(btnEst);
        info.add(btns, g);

        card.add(info, BorderLayout.SOUTH);
        return card;
    }

    private ImageIcon loadImg(String ruta, int w, int h) {
        java.net.URL url = getClass().getResource(ruta);
        if (url == null) url = getClass().getResource("/" + ruta);
        if (url == null) url = getClass().getResource("/vista/" + ruta);
        if (url == null) {
            String nom = ruta.replaceAll(".*/", "");
            url = getClass().getResource("imagenes/" + nom);
        }
        if (url == null) return null;
        return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }

    private void cambiarEstado(String nombre, String nuevo) {
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE habitaciones SET estado = ? WHERE nombre = ?")) {
            ps.setString(1, nuevo);
            ps.setString(2, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarHabitaciones();
    }

    private void agregarHabitacion() {
        JTextField txtNombre = new JTextField();
        JTextField txtDesc = new JTextField();
        JTextField txtPrecio = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Simple", "Doble", "Suite", "Deluxe", "Familiar", "Penthouse"});
        JTextField txtImg = new JTextField("imagenes/default_simple.png");

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(3, 5, 3, 5);
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Nombre:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtNombre, g);
        g.gridx = 0; g.gridy = 1; g.weightx = 0; form.add(new JLabel("Descripci\u00f3n:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtDesc, g);
        g.gridx = 0; g.gridy = 2; g.weightx = 0; form.add(new JLabel("Precio:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtPrecio, g);
        g.gridx = 0; g.gridy = 3; g.weightx = 0; form.add(new JLabel("Tipo:"), g);
        g.gridx = 1; g.weightx = 1; form.add(comboTipo, g);
        g.gridx = 0; g.gridy = 4; g.weightx = 0; form.add(new JLabel("Imagen:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtImg, g);

        if (JOptionPane.showConfirmDialog(this, form, "Agregar Habitaci\u00f3n", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) != JOptionPane.OK_OPTION)
            return;

        String nom = txtNombre.getText().trim();
        String des = txtDesc.getText().trim();
        String pre = txtPrecio.getText().trim();
        String tip = (String) comboTipo.getSelectedItem();
        String ima = txtImg.getText().trim();
        if (nom.isEmpty() || des.isEmpty() || pre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, descripci\u00f3n y precio son obligatorios.", "Campos vac\u00edos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO habitaciones (nombre, descripcion, precio, tipo, imagen, estado) VALUES (?, ?, ?, ?, ?, 'Disponible')")) {
            ps.setString(1, nom); ps.setString(2, des); ps.setString(3, pre);
            ps.setString(4, tip); ps.setString(5, ima);
            ps.executeUpdate();
            cargarHabitaciones();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarHabitacion(String nomAnt, String desAnt, String preAnt, String tipAnt, String imgAnt) {
        JTextField txtNombre = new JTextField(nomAnt);
        JTextField txtDesc = new JTextField(desAnt);
        JTextField txtPrecio = new JTextField(preAnt);
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Simple", "Doble", "Suite", "Deluxe", "Familiar", "Penthouse"});
        comboTipo.setSelectedItem(tipAnt);
        JTextField txtImg = new JTextField(imgAnt);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(3, 5, 3, 5);
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Nombre:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtNombre, g);
        g.gridx = 0; g.gridy = 1; g.weightx = 0; form.add(new JLabel("Descripci\u00f3n:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtDesc, g);
        g.gridx = 0; g.gridy = 2; g.weightx = 0; form.add(new JLabel("Precio:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtPrecio, g);
        g.gridx = 0; g.gridy = 3; g.weightx = 0; form.add(new JLabel("Tipo:"), g);
        g.gridx = 1; g.weightx = 1; form.add(comboTipo, g);
        g.gridx = 0; g.gridy = 4; g.weightx = 0; form.add(new JLabel("Imagen:"), g);
        g.gridx = 1; g.weightx = 1; form.add(txtImg, g);

        if (JOptionPane.showConfirmDialog(this, form, "Editar Habitaci\u00f3n", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) != JOptionPane.OK_OPTION)
            return;

        String nom = txtNombre.getText().trim();
        String des = txtDesc.getText().trim();
        String pre = txtPrecio.getText().trim();
        String tip = (String) comboTipo.getSelectedItem();
        String ima = txtImg.getText().trim();
        if (nom.isEmpty() || des.isEmpty() || pre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Campos vac\u00edos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE habitaciones SET nombre=?, descripcion=?, precio=?, tipo=?, imagen=? WHERE nombre=?")) {
            ps.setString(1, nom); ps.setString(2, des); ps.setString(3, pre);
            ps.setString(4, tip); ps.setString(5, ima); ps.setString(6, nomAnt);
            ps.executeUpdate();
            cargarHabitaciones();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JButton getBtnCerrarSesion() { return btnSalir; }
    public JButton getBtnRegistrarUsuario() { return btnRegistrarUsuario; }
}
