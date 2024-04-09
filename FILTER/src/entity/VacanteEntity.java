package entity;

public class VacanteEntity {

    int id;

    int id_empresa;
    String titulo;
    String descripcion;
    String duracion;

    String tegnologia;

    String estado;

    EmpresaEntity objEmpresa;

    public VacanteEntity(int id, int id_empresa, String titulo, String descripcion, String duracion, String tegnologia, String estado) {
        this.id = id;
        this.id_empresa = id_empresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.tegnologia = tegnologia;
        this.estado = estado;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getTegnologia() {
        return tegnologia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTegnologia(String tegnologia) {
        this.tegnologia = tegnologia;
    }

    public VacanteEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public EmpresaEntity getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(EmpresaEntity objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    @Override
    public String toString() {
        return "VacanteEntity{" +
                "id=" + id +
                ", id_empresa=" + id_empresa +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", tegnologia='" + tegnologia + '\'' +
                ", estado='" + estado + '\'' +
                ", Nombre Empresa= " + objEmpresa.getNombre() +", Sector empresa= '" + objEmpresa.getSector() + '\'' ;
    }
}
