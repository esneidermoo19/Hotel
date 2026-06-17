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
        abrirAdminPanel();
    }

    private void abrirAdminPanel() {
        AdminPanelvista adminVista = new AdminPanelvista();
        adminVista.setVisible(true);
        adminVista.getBtnCerrarSesion().addActionListener(e -> {
            adminVista.dispose();
            reabrirLogin();
        });
    }

    private void reabrirLogin() {
        Loginvista nueva = new Loginvista();
        new Logincontrolador(nueva);
        nueva.setVisible(true);
    }
}
