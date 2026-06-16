package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.conexionBD;
import vista.AdminPanelvista;
import vista.EmpleadoPanelvista;
import vista.Loginvista;

public class Logincontrolador {

    private final Loginvista loginVista;

    public Logincontrolador(Loginvista loginVista) {
        this.loginVista = loginVista;
        conexionBD.crearTabla();
        this.loginVista.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String usuario = loginVista.getTxtUsuario().getText().trim();
        String contrasena = new String(loginVista.getTxtContrasena().getPassword());
        String rolSeleccionado = (String) loginVista.getCmbRol().getSelectedItem();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(loginVista,
                "Ingrese usuario y contrase\u00f1a.",
                "Campos vac\u00edos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT rol FROM usuarios WHERE usuario = ? AND contrasena = ? AND rol = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            String rolBD = "Administrador".equals(rolSeleccionado) ? "Administrador" : "Empleado";
            ps.setString(3, rolBD);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(loginVista,
                    "Usuario, contrase\u00f1a o rol incorrectos.",
                    "Error de inicio de sesi\u00f3n", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(loginVista,
                "Error de conexi\u00f3n: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        loginVista.dispose();

        if ("Administrador".equals(rolSeleccionado)) {
            abrirAdminPanel(usuario);
        } else {
            abrirEmpleadoPanel(usuario);
        }
    }

    private void abrirEmpleadoPanel(String usuario) {
        EmpleadoPanelvista empVista = new EmpleadoPanelvista();
        empVista.setVisible(true);
        empVista.getBtnCerrarSesion().addActionListener(e -> {
            empVista.dispose();
            reabrirLogin();
        });
        empVista.getBtnAceptarReserva().addActionListener(e -> aceptarReserva(empVista));
        empVista.getBtnModificarEstancia().addActionListener(e ->
            JOptionPane.showMessageDialog(empVista,
                "Funcionalidad pr\u00f3ximamente.",
                "En desarrollo", JOptionPane.INFORMATION_MESSAGE));
    }

    private void abrirAdminPanel(String usuario) {
        AdminPanelvista adminVista = new AdminPanelvista();
        adminVista.setVisible(true);
        adminVista.getBtnCerrarSesion().addActionListener(e -> {
            adminVista.dispose();
            reabrirLogin();
        });
        adminVista.getBtnAceptarReserva().addActionListener(e -> aceptarReserva(adminVista));
        adminVista.getBtnModificarEstancia().addActionListener(e ->
            JOptionPane.showMessageDialog(adminVista,
                "Funcionalidad pr\u00f3ximamente.",
                "En desarrollo", JOptionPane.INFORMATION_MESSAGE));
        adminVista.getBtnGestionarHabitaciones().addActionListener(e ->
            JOptionPane.showMessageDialog(adminVista,
                "Funcionalidad pr\u00f3ximamente.",
                "En desarrollo", JOptionPane.INFORMATION_MESSAGE));
        adminVista.getBtnVerReportes().addActionListener(e ->
            JOptionPane.showMessageDialog(adminVista,
                "Funcionalidad pr\u00f3ximamente.",
                "En desarrollo", JOptionPane.INFORMATION_MESSAGE));
        adminVista.getBtnAdminEmpleados().addActionListener(e ->
            JOptionPane.showMessageDialog(adminVista,
                "Funcionalidad pr\u00f3ximamente.",
                "En desarrollo", JOptionPane.INFORMATION_MESSAGE));
    }

    private void aceptarReserva(EmpleadoPanelvista vista) {
        int fila = vista.getTblReservas().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista,
                "Seleccione una reserva de la tabla.",
                "Ninguna fila seleccionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Object idObj = vista.getModeloTabla().getValueAt(fila, 0);
        String sql = "UPDATE reservaciones SET estado = 'Aceptada' WHERE id = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idObj.toString()));
            ps.executeUpdate();
            vista.cargarReservas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista,
                "Error al aceptar reserva: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aceptarReserva(AdminPanelvista vista) {
        int fila = vista.getTblReservas().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista,
                "Seleccione una reserva de la tabla.",
                "Ninguna fila seleccionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Object idObj = vista.getModeloTabla().getValueAt(fila, 0);
        String sql = "UPDATE reservaciones SET estado = 'Aceptada' WHERE id = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idObj.toString()));
            ps.executeUpdate();
            vista.cargarReservas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista,
                "Error al aceptar reserva: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reabrirLogin() {
        Loginvista nueva = new Loginvista();
        new Logincontrolador(nueva);
        nueva.setVisible(true);
    }
}
