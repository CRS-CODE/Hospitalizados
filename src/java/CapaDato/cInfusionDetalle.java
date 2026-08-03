package CapaDato;

public class cInfusionDetalle {

    private int id_pid;
    private int id_pi;
    private int orden;
    private int id_insumo;
    private String medicamento_desc;
    private String dosis;
    private String unidad_desc;

    public cInfusionDetalle() {
        id_pid = 0;
        id_pi = 0;
        orden = 1;
        id_insumo = 0;
        medicamento_desc = "";
        dosis = "";
        unidad_desc = "";
    }

    public int getId_pid() { return id_pid; }
    public void setId_pid(int id_pid) { this.id_pid = id_pid; }

    public int getId_pi() { return id_pi; }
    public void setId_pi(int id_pi) { this.id_pi = id_pi; }

    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }

    public int getId_insumo() { return id_insumo; }
    public void setId_insumo(int id_insumo) { this.id_insumo = id_insumo; }

    public String getMedicamento_desc() { return medicamento_desc; }
    public void setMedicamento_desc(String medicamento_desc) { this.medicamento_desc = medicamento_desc; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getUnidad_desc() { return unidad_desc; }
    public void setUnidad_desc(String unidad_desc) { this.unidad_desc = unidad_desc; }
}
