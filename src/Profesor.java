public class Profesor {
    private String nombre;
    private String correo;
    private String id;


    public Profesor(String nombre, String correo, String id) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
