package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionBD {

    private static final String URL = "jdbc:postgresql://localhost:12165/hotel";
    private static final String USER = "admin";
    private static final String PASSWORD = "WINNY5331";

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
        String sqlHabitaciones = "CREATE TABLE IF NOT EXISTS habitaciones ("
                + "nombre VARCHAR(100) PRIMARY KEY, "
                + "descripcion TEXT, "
                + "precio VARCHAR(50), "
                + "tipo VARCHAR(50), "
                + "imagen VARCHAR(255), "
                + "estado VARCHAR(20) DEFAULT 'Disponible')";
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "usuario VARCHAR(50) PRIMARY KEY, "
                + "contrasena VARCHAR(100), "
                + "rol VARCHAR(20))";
        String sqlReservaciones = "CREATE TABLE IF NOT EXISTS reservaciones ("
                + "id SERIAL PRIMARY KEY, "
                + "habitacion VARCHAR(100), "
                + "llegada VARCHAR(10), "
                + "salida VARCHAR(10), "
                + "nombre VARCHAR(100), "
                + "correo VARCHAR(100), "
                + "telefono VARCHAR(20), "
                + "estado VARCHAR(20) DEFAULT 'Pendiente')";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlHabitaciones);
            stmt.executeUpdate(sqlUsuarios);
            stmt.executeUpdate(sqlReservaciones);

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM habitaciones");
            rs.next();
            if (rs.getInt(1) == 0) {
                String insert = "INSERT INTO habitaciones (nombre, descripcion, precio, tipo, imagen, estado) VALUES "
                        + "('Habitaci\u00f3n Simple','Cama individual, ba\u00f1o privado, vista al jard\u00edn, WiFi y aire acondicionado.','$45 / noche','Simple','imagenes/default_simple.png','Disponible'),"
                        + "('Habitaci\u00f3n Doble','Dos camas, ba\u00f1o completo, balc\u00f3n, minibar, TV y WiFi gratuito.','$75 / noche','Doble','imagenes/doble1.jpg','Disponible'),"
                        + "('Suite Premium','Cama king size, sala de estar, jacuzzi, terraza privada y servicio a la habitaci\u00f3n.','$140 / noche','Suite','imagenes/suite1.jpg','Disponible'),"
                        + "('Habitaci\u00f3n Deluxe','Cama queen size, sala peque\u00f1a, TV 50\", escritorio y WiFi premium.','$100 / noche','Deluxe','imagenes/doble2.jpg','Disponible'),"
                        + "('Habitaci\u00f3n Familiar','Dos camas dobles, espacio infantil, refrigerador, microondas y TV.','$110 / noche','Familiar','imagenes/default_doble.png','Disponible'),"
                        + "('Penthouse Suite','Dos niveles, terraza panor\u00e1mica, cocina equipada y bar privado.','$250 / noche','Penthouse','imagenes/default_suite.png','Disponible'),"
                        + "('Habitaci\u00f3n Econ\u00f3mica','Habitaci\u00f3n sencilla con cama individual, ventilador y ba\u00f1o compartido.','$25 / noche','Simple','imagenes/default_simple.png','Disponible'),"
                        + "('Habitaci\u00f3n Ejecutiva','Espacio de trabajo amplio, cama queen, escritorio, silla ergon\u00f3mica y WiFi de alta velocidad.','$95 / noche','Doble','imagenes/doble2.jpg','Disponible'),"
                        + "('Suite Junior','Habitaci\u00f3n con cama king, sala peque\u00f1a, TV de pantalla plana y minibar.','$130 / noche','Suite','imagenes/suite1.jpg','Disponible'),"
                        + "('Habitaci\u00f3n Matrimonial','Cama matrimonial, ba\u00f1o privado, TV cable, armario amplio y vista interior.','$60 / noche','Doble','imagenes/default_doble.png','Disponible'),"
                        + "('Suite Presidencial','Lujosa suite con sala de estar, comedor privado, jacuzzi, terraza y mayordomo.','$350 / noche','Suite','imagenes/suite1.jpg','Disponible'),"
                        + "('Habitaci\u00f3n Twin','Dos camas individuales, ba\u00f1o completo, TV, escritorio y WiFi. Ideal para viajeros.','$65 / noche','Doble','imagenes/doble1.jpg','Disponible'),"
                        + "('Habitaci\u00f3n Superior','Cama queen, ba\u00f1o renovado, TV 43\", cafetera, vistas al jard\u00edn y balc\u00f3n peque\u00f1o.','$85 / noche','Deluxe','imagenes/doble2.jpg','Disponible'),"
                        + "('Suite Nupcial','Suite rom\u00e1ntica con cama king, jacuzzi para dos, velas, cortinas de seda y champ\u00e1n de bienvenida.','$200 / noche','Suite','imagenes/suite1.jpg','Disponible'),"
                        + "('Habitaci\u00f3n con Vista al Mar','Amplia habitaci\u00f3n con ventanales panor\u00e1micos al mar, cama king, aire acondicionado y TV.','$160 / noche','Deluxe','imagenes/default_deluxe.png','Disponible'),"
                        + "('\u00c1tico Premium','Lujoso \u00e1tico con terraza privada, piscina infinita, sala de estar, cocina totalmente equipada y bar.','$400 / noche','Penthouse','imagenes/default_suite.png','Disponible')";
                stmt.executeUpdate(insert);
            }
        } catch (SQLException e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
        }
    }

}
