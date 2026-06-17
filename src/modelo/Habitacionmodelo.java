package modelo;

public class Habitacionmodelo {

    private String nombre;
    private String descripcion;
    private String precio;
    private String tipo;
    private String imagen;
    private String estado;

    public Habitacionmodelo() {
        this.estado = "Disponible";
    }

    public Habitacionmodelo(String nombre, String descripcion, String precio, String tipo, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.estado = "Disponible";
    }

    public Habitacionmodelo(String nombre, String descripcion, String precio, String tipo, String imagen, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.estado = estado;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrecio() { return precio; }
    public void setPrecio(String precio) { this.precio = precio; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
