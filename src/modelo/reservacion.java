package modelo;

public class Reservacion {

    private int id;
    private String habitacion;
    private String llegada;
    private String salida;
    private String nombre;
    private String correo;
    private String telefono;
    private String estado;

    public Reservacion() {
    }

    public Reservacion(int id, String habitacion, String llegada, String salida,
                       String nombre, String correo, String telefono, String estado) {
        this.id = id;
        this.habitacion = habitacion;
        this.llegada = llegada;
        this.salida = salida;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getHabitacion() { return habitacion; }
    public void setHabitacion(String habitacion) { this.habitacion = habitacion; }

    public String getLlegada() { return llegada; }
    public void setLlegada(String llegada) { this.llegada = llegada; }

    public String getSalida() { return salida; }
    public void setSalida(String salida) { this.salida = salida; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
