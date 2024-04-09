package entity;

public class ContratacionEntity{

    int id;

    int id_coder;

    int id_vancante;

    String fecha;
    String estado;

    double salario;

    VacanteEntity objVacante;
    CoderEntity objCoder;

    public ContratacionEntity(int id, int id_coder, int id_vancante, String fecha, String estado, double salario) {
        this.id = id;
        this.id_coder = id_coder;
        this.id_vancante = id_vancante;
        this.fecha = fecha;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public int getId_vancante() {
        return id_vancante;
    }

    public void setId_vancante(int id_vancante) {
        this.id_vancante = id_vancante;
    }

    public ContratacionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public VacanteEntity getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(VacanteEntity objVacante) {
        this.objVacante = objVacante;
    }

    public CoderEntity getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(CoderEntity objCoder) {
        this.objCoder = objCoder;
    }

    @Override
    public String toString() {
        return "ContratacionEntity{" +
                "id=" + id +
                ", id_coder=" + id_coder +
                ", id_vancante=" + id_vancante +
                ", fecha='" + fecha + '\'' +
                ", estado='" + estado + '\'' +
                ", salario=" + salario +
                ", objVacante=" + objVacante +
                ", objCoder=" + objCoder +
                '}';
    }
}
