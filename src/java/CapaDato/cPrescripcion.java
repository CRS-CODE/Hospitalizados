package CapaDato;

public class cPrescripcion {

    private int id_pm;
    private int id_duo;
    private int id_insumo;
    private String medicamento_desc;
    private String dosis;
    private String unidad_desc;
    private int id_via;
    private String via_desc;
    private String frecuencia;
    private String observacion;
    private int estado;
    private String fecha_registro;
    private String usuario_registro;

    public cPrescripcion() {
        id_pm = 0;
        id_duo = 0;
        id_insumo = 0;
        medicamento_desc = "";
        dosis = "";
        unidad_desc = "";
        id_via = 0;
        via_desc = "";
        frecuencia = "";
        observacion = "";
        estado = 1;
        fecha_registro = "";
        usuario_registro = "";
    }

    public int getId_pm() { return id_pm; }
    public void setId_pm(int id_pm) { this.id_pm = id_pm; }

    public int getId_duo() { return id_duo; }
    public void setId_duo(int id_duo) { this.id_duo = id_duo; }

    public int getId_insumo() { return id_insumo; }
    public void setId_insumo(int id_insumo) { this.id_insumo = id_insumo; }

    public String getMedicamento_desc() { return medicamento_desc; }
    public void setMedicamento_desc(String medicamento_desc) { this.medicamento_desc = medicamento_desc; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getUnidad_desc() { return unidad_desc; }
    public void setUnidad_desc(String unidad_desc) { this.unidad_desc = unidad_desc; }

    public int getId_via() { return id_via; }
    public void setId_via(int id_via) { this.id_via = id_via; }

    public String getVia_desc() { return via_desc; }
    public void setVia_desc(String via_desc) { this.via_desc = via_desc; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro) { this.fecha_registro = fecha_registro; }

    public String getUsuario_registro() { return usuario_registro; }
    public void setUsuario_registro(String usuario_registro) { this.usuario_registro = usuario_registro; }
}
