package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/hotel";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    private conexionBD() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void crearTabla() {
        String sqlReservaciones = "CREATE TABLE IF NOT EXISTS reservaciones ("
                + "id SERIAL PRIMARY KEY, "
                + "habitacion TEXT NOT NULL, "
                + "llegada TEXT NOT NULL, "
                + "salida TEXT NOT NULL, "
                + "nombre TEXT NOT NULL, "
                + "correo TEXT NOT NULL, "
                + "telefono TEXT NOT NULL, "
                + "estado TEXT DEFAULT 'Pendiente'"
                + ")";
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id SERIAL PRIMARY KEY, "
                + "usuario VARCHAR(100) UNIQUE NOT NULL, "
                + "contrasena VARCHAR(100) NOT NULL, "
                + "rol VARCHAR(50) NOT NULL"
                + ")";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlReservaciones);
            try {
                stmt.execute("ALTER TABLE reservaciones ADD COLUMN IF NOT EXISTS estado TEXT DEFAULT 'Pendiente'");
            } catch (SQLException e) {
            }
            stmt.execute(sqlUsuarios);
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM usuarios");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO usuarios (usuario, contrasena, rol) VALUES "
                        + "('admin', 'admin', 'Administrador'), "
                        + "('empleado', 'empleado', 'Empleado')");
            }
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }
}
