package entity;

public class CoderEntity {

    int id;

    String nombre;

    String apellido;
    String dni;
    int cohote;
    String hoja_de_vida;
    String clan;

    public CoderEntity(int id, String nombre, String apellido, String dni, int cohote, String hoja_de_vida, String clan) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cohote = cohote;
        this.hoja_de_vida = hoja_de_vida;
        this.clan = clan;
    }

    public CoderEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getCohote() {
        return cohote;
    }

    public void setCohote(int cohote) {
        this.cohote = cohote;
    }

    public String getHoja_de_vida() {
        return hoja_de_vida;
    }

    public void setHoja_de_vida(String hoja_de_vida) {
        this.hoja_de_vida = hoja_de_vida;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "CoderEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", cohote=" + cohote +
                ", hoja_de_vida='" + hoja_de_vida + '\'' +
                ", clan='" + clan + '\'' +
                '}';
    }
}
