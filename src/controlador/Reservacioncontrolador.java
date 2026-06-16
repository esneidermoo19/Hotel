package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.conexionBD;
import vista.Reservacionvista;

public class Reservacioncontrolador {

    private final Reservacionvista vista;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservacioncontrolador(Reservacionvista vista) {
        this.vista = vista;
        conexionBD.crearTabla();
        this.vista.getBtnReservar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservar();
            }
        });
    }

    private void reservar() {
        String habitacion = vista.getHabitacion();
        String llegada = sdf.format((Date) vista.getSpinLlegada().getValue());
        String salida = sdf.format((Date) vista.getSpinSalida().getValue());
        String nombre = vista.getTxtNombre().getText().trim();
        String correo = vista.getTxtCorreo().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();

        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Todos los campos son obligatorios.",
                "Campos vac\u00edos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (((Date) vista.getSpinSalida().getValue()).before((Date) vista.getSpinLlegada().getValue())) {
            JOptionPane.showMessageDialog(vista,
                "La fecha de salida debe ser posterior a la de llegada.",
                "Fechas inv\u00e1lidas", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO reservaciones (habitacion, llegada, salida, nombre, correo, telefono) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, habitacion);
            ps.setString(2, llegada);
            ps.setString(3, salida);
            ps.setString(4, nombre);
            ps.setString(5, correo);
            ps.setString(6, telefono);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(vista,
                "\u00a1Reserva de " + habitacion + " confirmada con \u00e9xito!",
                "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista,
                "Error al guardar la reserva: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
