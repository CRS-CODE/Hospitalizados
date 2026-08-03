package CapaDato;

import java.util.ArrayList;

public class cInfusion {

    private int id_pi;
    private int id_duo;
    private String suero_desc;
    private double velocidad_inf;
    private String unidad_velocidad;
    private String observacion;
    private int estado;
    private String fecha_registro;
    private String usuario_registro;
    private ArrayList detalles;
    private String meds_display;

    public cInfusion() {
        id_pi = 0;
        id_duo = 0;
        suero_desc = "";
        velocidad_inf = 0;
        unidad_velocidad = "mL/hr";
        observacion = "";
        estado = 1;
        fecha_registro = "";
        usuario_registro = "";
        detalles = new ArrayList();
        meds_display = "";
    }

    public int getId_pi() { return id_pi; }
    public void setId_pi(int id_pi) { this.id_pi = id_pi; }

    public int getId_duo() { return id_duo; }
    public void setId_duo(int id_duo) { this.id_duo = id_duo; }

    public String getSuero_desc() { return suero_desc; }
    public void setSuero_desc(String suero_desc) { this.suero_desc = suero_desc; }

    public double getVelocidad_inf() { return velocidad_inf; }
    public void setVelocidad_inf(double velocidad_inf) { this.velocidad_inf = velocidad_inf; }

    public String getUnidad_velocidad() { return unidad_velocidad; }
    public void setUnidad_velocidad(String unidad_velocidad) { this.unidad_velocidad = unidad_velocidad; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro) { this.fecha_registro = fecha_registro; }

    public String getUsuario_registro() { return usuario_registro; }
    public void setUsuario_registro(String usuario_registro) { this.usuario_registro = usuario_registro; }

    public ArrayList getDetalles() { return detalles; }
    public void setDetalles(ArrayList detalles) { this.detalles = detalles; }

    public String getMeds_display() { return meds_display; }
    public void setMeds_display(String meds_display) { this.meds_display = meds_display; }
}
