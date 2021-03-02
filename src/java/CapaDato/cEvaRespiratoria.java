/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author Informatica
 */
public class cEvaRespiratoria extends cUsuario {

    private int id_respi;
    private int id_duo;

    
    private String fecha_ingreso;
    private String hora_ingreso;

    private int estado;
    private String estado_desc;

    private int oxigeno;
    private String oxigeno_desc;
    private String oxigeno_det;

    private int via_venosa;
    private String via_venosa_desc;

    private int via_urinaria;
    private String via_urinaria_desc;
    private String via_urinaria_det;

    private int vigil;
    private String vigil_desc;
    // no es vigil
    private int estado_conciencia;
    private String estado_conciencia_desc;
    // si es vigil
    private int alteracion_lenguaje;
    private String alteracion_lenguaje_desc;

    private int estimulo_verbal;
    private int estimulo_visual;

    private int ubicacion_temporal;
    private int ubicacion_espacial;

    private String estimulo_verbal_desc;
    private String estimulo_visual_desc;

    private String ubicacion_temporal_desc;
    private String ubicacion_espacial_desc;

    private int postura; // simetrico/asimetrico
    private String postura_desc;

    private String EESS;
    private String EEII;

    private String deformidad;

    //estado nutricional
    private int estado_nutricional;
    private String estado_nutricional_desc;

    /*private int en_caquético;
     private int en_normal;
     private int en_sobrepeso;
     private int en_obeso;*/
    private int coloracion_piel;
    private String coloracion_piel_desc;

    private int cianosis;
    private String cianosis_desc;

//ESTADO DE PIEL CHECKBOX
    private int ep_normal;
    private String ep_normal_desc;
    private int ep_escara;
    private String ep_escara_desc;
    private int ep_cicatriz;
    private String ep_cicatriz_desc;
    private int ep_hematoma;
    private String ep_hematoma_desc;
    private int ep_petequia;
    private String ep_petequia_desc;
    private int ep_edema;
    private String ep_edema_desc;

    private String ep_normal_det;
    private String ep_escara_det;
    private String ep_cicatriz_det;
    private String ep_hematoma_det;
    private String ep_petequia_det;
    private String ep_edema_det;
    //FIN ESTADO DE PIEL CHECKBOX

    private int patron_respiratorio;
    private String patron_respiratorio_desc;

    private int respirador;
    private String respirador_desc;

    private int dificultad_respiratoria;
    private String dificultad_respiratoria_desc;

    private int musculatura_accesoria;
    private String musculatura_accesoria_desc;

    private String musculatura_accesoria_det;

    private int aleteo_nasal;
    private String aleteo_nasal_desc;
    private int aleteo_costal;
    private String aleteo_costal_desc;
    private int temperatura;
    private String temperatura_desc;

    private int elasticidad;
    private String elasticidad_desc;
    private String elasticidad_det;

    private String restriccion_miofascial;

    private int llene_capilar;
    private String llene_capilar_desc;

    private int edema;
    private String edema_desc;

    private int dolor;
    private String dolor_desc;

    private int EVA;
    private String EVA_desc;

    private String desalineacion;
    private String frecuencia_cardiaca;
    private String frecuencia_respiratoria;
    private String saturacion;

    private int aus_pi_ls;
    private int aus_pi_li;
    private int aus_pd_ls;
    private int aus_pd_lm;
    private int aus_pd_li;

    private String aus_pi_ls_desc;
    private String aus_pi_li_desc;
    private String aus_pd_ls_desc;
    private String aus_pd_lm_desc;
    private String aus_pd_li_desc;

    // ruidos agregados checklist
    private int sibilancia;
    private int roncus;
    private int crepitacion;
    private int estertor_traqueal;
    private int crepitacion_fina;
    private int crepitacion_gruesa;

    private String sibilancia_desc;
    private String roncus_desc;
    private String crepitacion_desc;
    private String estertor_traqueal_desc;
    private String crepitacion_fina_desc;
    private String crepitacion_gruesa_desc;
    // FIN ruidos agregados checklist

    private String ra_pi_ls;
    private String ra_pi_li;
    private String ra_pd_ls;
    private String ra_pd_lm;
    private String ra_pd_li;

    private int tos_presencia;
    private int tos_produccion;
    private int secrecion_cantidad;
    private int secrecion_coloracion;
    private int secrecion_tipo;

    private String tos_presencia_desc;
    private String tos_produccion_desc;

    private String secrecion_cantidad_desc;
    private String secrecion_coloracion_desc;
    private String secrecion_tipo_desc;

    public cEvaRespiratoria() {
        this.id_respi = 0;
        this.id_duo = 0;

        fecha_ingreso = "";
        hora_ingreso = "";
        estado = 0;
        estado_desc = "";

        this.oxigeno = 0;
        this.oxigeno_desc = "";

        this.oxigeno_det = "";
        this.via_venosa = 0;
        this.via_venosa_desc = "";
        this.via_urinaria = 0;
        this.via_urinaria_desc = "";
        this.via_urinaria_det = "";
        this.vigil = 0;
        this.vigil_desc = "";
        this.estado_conciencia = 0;
        this.estado_conciencia_desc = "";
        this.alteracion_lenguaje = 0;
        this.alteracion_lenguaje_desc = "";
        this.estimulo_verbal = 0;
        this.estimulo_visual = 0;
        this.ubicacion_temporal = 0;
        this.ubicacion_espacial = 0;
        this.estimulo_verbal_desc = "";
        this.estimulo_visual_desc = "";
        this.ubicacion_temporal_desc = "";
        this.ubicacion_espacial_desc = "";
        this.postura = 0;
        this.postura_desc = "";
        this.EESS = "";
        this.EEII = "";
        this.deformidad = "";
        this.estado_nutricional = 0;
        this.estado_nutricional_desc = "";
        this.coloracion_piel = 0;
        this.coloracion_piel_desc = "";
        this.cianosis = 0;
        this.cianosis_desc = "";
        this.ep_normal = 0;
        this.ep_normal_desc = "";
        this.ep_escara = 0;
        this.ep_escara_desc = "";
        this.ep_cicatriz = 0;
        this.ep_cicatriz_desc = "";
        this.ep_hematoma = 0;
        this.ep_hematoma_desc = "";
        this.ep_petequia = 0;
        this.ep_petequia_desc = "";
        this.ep_edema = 0;
        this.ep_edema_desc = "";
        this.ep_normal_det = "";
        this.ep_escara_det = "";
        this.ep_cicatriz_det = "";
        this.ep_hematoma_det = "";
        this.ep_petequia_det = "";
        this.ep_edema_det = "";
        this.patron_respiratorio = 0;
        this.patron_respiratorio_desc = "";
        this.respirador = 0;
        this.respirador_desc = "";
        this.dificultad_respiratoria = 0;
        this.dificultad_respiratoria_desc = "";
        this.musculatura_accesoria = 0;
        this.musculatura_accesoria_desc = "";
        this.musculatura_accesoria_det = "";
        this.aleteo_nasal = 0;
        this.aleteo_nasal_desc = "";
        this.aleteo_costal = 0;
        this.aleteo_costal_desc = "";
        this.temperatura = 0;
        this.temperatura_desc = "";
        this.elasticidad = 0;
        this.elasticidad_desc = "";
        this.elasticidad_det = "";
        this.restriccion_miofascial = "";
        this.llene_capilar = 0;
        this.llene_capilar_desc = "";
        this.edema = 0;
        this.edema_desc = "";
        this.dolor = 0;
        this.dolor_desc = "";
        this.EVA = 0;
        this.EVA_desc = "";
        this.desalineacion = "";
        this.frecuencia_cardiaca = "";
        this.frecuencia_respiratoria = "";
        this.saturacion = "";
        this.aus_pi_ls = 0;
        this.aus_pi_li = 0;
        this.aus_pd_ls = 0;
        this.aus_pd_lm = 0;
        this.aus_pd_li = 0;
        this.sibilancia = 0;

        aus_pi_ls_desc = "";
        aus_pi_li_desc = "";
        aus_pd_ls_desc = "";
        aus_pd_lm_desc = "";
        aus_pd_li_desc = "";

        this.roncus = 0;
        this.crepitacion = 0;
        this.estertor_traqueal = 0;
        this.crepitacion_fina = 0;
        this.crepitacion_gruesa = 0;
        this.sibilancia_desc = "";
        this.roncus_desc = "";
        this.crepitacion_desc = "";
        this.estertor_traqueal_desc = "";
        this.crepitacion_fina_desc = "";
        this.crepitacion_gruesa_desc = "";
        this.ra_pi_ls = "";
        this.ra_pi_li = "";
        this.ra_pd_ls = "";
        this.ra_pd_lm = "";
        this.ra_pd_li = "";
        this.tos_presencia = 0;
        this.tos_produccion = 0;
        this.secrecion_cantidad = 0;
        this.secrecion_coloracion = 0;
        this.secrecion_tipo = 0;
        this.tos_presencia_desc = "";
        this.tos_produccion_desc = "";
        this.secrecion_cantidad_desc = "";
        this.secrecion_coloracion_desc = "";
        this.secrecion_tipo_desc = "";
    }

    /**
     * @return the id_respi
     */
    public int getId_respi() {
        return id_respi;
    }

    /**
     * @param id_respi the id_respi to set
     */
    public void setId_respi(int id_respi) {
        this.id_respi = id_respi;
    }

    /**
     * @return the id_duo
     */
    public int getId_duo() {
        return id_duo;
    }

    /**
     * @param id_duo the id_duo to set
     */
    public void setId_duo(int id_duo) {
        this.id_duo = id_duo;
    }

    /**
     * @return the oxigeno
     */
    public int getOxigeno() {
        return oxigeno;
    }

    /**
     * @param oxigeno the oxigeno to set
     */
    public void setOxigeno(int oxigeno) {
        this.oxigeno = oxigeno;
    }

    /**
     * @return the oxigeno_desc
     */
    public String getOxigeno_desc() {
        return oxigeno_desc;
    }

    /**
     * @param oxigeno_desc the oxigeno_desc to set
     */
    public void setOxigeno_desc(String oxigeno_desc) {
        this.oxigeno_desc = oxigeno_desc;
    }

    /**
     * @return the oxigeno_det
     */
    public String getOxigeno_det() {
        return oxigeno_det;
    }

    /**
     * @param oxigeno_det the oxigeno_det to set
     */
    public void setOxigeno_det(String oxigeno_det) {
        this.oxigeno_det = oxigeno_det;
    }

    /**
     * @return the via_venosa
     */
    public int getVia_venosa() {
        return via_venosa;
    }

    /**
     * @param via_venosa the via_venosa to set
     */
    public void setVia_venosa(int via_venosa) {
        this.via_venosa = via_venosa;
    }

    /**
     * @return the via_venosa_desc
     */
    public String getVia_venosa_desc() {
        return via_venosa_desc;
    }

    /**
     * @param via_venosa_desc the via_venosa_desc to set
     */
    public void setVia_venosa_desc(String via_venosa_desc) {
        this.via_venosa_desc = via_venosa_desc;
    }

    /**
     * @return the via_urinaria
     */
    public int getVia_urinaria() {
        return via_urinaria;
    }

    /**
     * @param via_urinaria the via_urinaria to set
     */
    public void setVia_urinaria(int via_urinaria) {
        this.via_urinaria = via_urinaria;
    }

    /**
     * @return the via_urinaria_desc
     */
    public String getVia_urinaria_desc() {
        return via_urinaria_desc;
    }

    /**
     * @param via_urinaria_desc the via_urinaria_desc to set
     */
    public void setVia_urinaria_desc(String via_urinaria_desc) {
        this.via_urinaria_desc = via_urinaria_desc;
    }

    /**
     * @return the via_urinaria_det
     */
    public String getVia_urinaria_det() {
        return via_urinaria_det;
    }

    /**
     * @param via_urinaria_det the via_urinaria_det to set
     */
    public void setVia_urinaria_det(String via_urinaria_det) {
        this.via_urinaria_det = via_urinaria_det;
    }

    /**
     * @return the vigil
     */
    public int getVigil() {
        return vigil;
    }

    /**
     * @param vigil the vigil to set
     */
    public void setVigil(int vigil) {
        this.vigil = vigil;
    }

    /**
     * @return the vigil_desc
     */
    public String getVigil_desc() {
        return vigil_desc;
    }

    /**
     * @param vigil_desc the vigil_desc to set
     */
    public void setVigil_desc(String vigil_desc) {
        this.vigil_desc = vigil_desc;
    }

    /**
     * @return the estado_conciencia
     */
    public int getEstado_conciencia() {
        return estado_conciencia;
    }

    /**
     * @param estado_conciencia the estado_conciencia to set
     */
    public void setEstado_conciencia(int estado_conciencia) {
        this.estado_conciencia = estado_conciencia;
    }

    /**
     * @return the estado_conciencia_desc
     */
    public String getEstado_conciencia_desc() {
        return estado_conciencia_desc;
    }

    /**
     * @param estado_conciencia_desc the estado_conciencia_desc to set
     */
    public void setEstado_conciencia_desc(String estado_conciencia_desc) {
        this.estado_conciencia_desc = estado_conciencia_desc;
    }

    /**
     * @return the alteracion_lenguaje
     */
    public int getAlteracion_lenguaje() {
        return alteracion_lenguaje;
    }

    /**
     * @param alteracion_lenguaje the alteracion_lenguaje to set
     */
    public void setAlteracion_lenguaje(int alteracion_lenguaje) {
        this.alteracion_lenguaje = alteracion_lenguaje;
    }

    /**
     * @return the alteracion_lenguaje_desc
     */
    public String getAlteracion_lenguaje_desc() {
        return alteracion_lenguaje_desc;
    }

    /**
     * @param alteracion_lenguaje_desc the alteracion_lenguaje_desc to set
     */
    public void setAlteracion_lenguaje_desc(String alteracion_lenguaje_desc) {
        this.alteracion_lenguaje_desc = alteracion_lenguaje_desc;
    }

    /**
     * @return the vigil_estimulo_verbal
     */
    public int getEstimulo_verbal() {
        return estimulo_verbal;
    }

    /**
     * @param vigil_estimulo_verbal the vigil_estimulo_verbal to set
     */
    public void setEstimulo_verbal(int estimulo_verbal) {
        this.estimulo_verbal = estimulo_verbal;
    }

    /**
     * @return the vigil_estimulo_visual
     */
    public int getEstimulo_visual() {
        return estimulo_visual;
    }

    /**
     * @param vigil_estimulo_visual the vigil_estimulo_visual to set
     */
    public void setEstimulo_visual(int estimulo_visual) {
        this.estimulo_visual = estimulo_visual;
    }

    /**
     * @return the ubicacion_temporal
     */
    public int getUbicacion_temporal() {
        return ubicacion_temporal;
    }

    /**
     * @param ubicacion_temporal the ubicacion_temporal to set
     */
    public void setUbicacion_temporal(int ubicacion_temporal) {
        this.ubicacion_temporal = ubicacion_temporal;
    }

    /**
     * @return the ubicacion_espacial
     */
    public int getUbicacion_espacial() {
        return ubicacion_espacial;
    }

    /**
     * @param ubicacion_espacial the ubicacion_espacial to set
     */
    public void setUbicacion_espacial(int ubicacion_espacial) {
        this.ubicacion_espacial = ubicacion_espacial;
    }

    /**
     * @return the vigil_estimulo_verbal_desc
     */
    public String getEstimulo_verbal_desc() {
        return estimulo_verbal_desc;
    }

    /**
     * @param vigil_estimulo_verbal_desc the vigil_estimulo_verbal_desc to set
     */
    public void setEstimulo_verbal_desc(String estimulo_verbal_desc) {
        this.estimulo_verbal_desc = estimulo_verbal_desc;
    }

    /**
     * @return the vigil_estimulo_visual_desc
     */
    public String getEstimulo_visual_desc() {
        return estimulo_visual_desc;
    }

    /**
     * @param vigil_estimulo_visual_desc the vigil_estimulo_visual_desc to set
     */
    public void setEstimulo_visual_desc(String estimulo_visual_desc) {
        this.estimulo_visual_desc = estimulo_visual_desc;
    }

    /**
     * @return the ubicacion_temporal_desc
     */
    public String getUbicacion_temporal_desc() {
        return ubicacion_temporal_desc;
    }

    /**
     * @param ubicacion_temporal_desc the ubicacion_temporal_desc to set
     */
    public void setUbicacion_temporal_desc(String ubicacion_temporal_desc) {
        this.ubicacion_temporal_desc = ubicacion_temporal_desc;
    }

    /**
     * @return the ubicacion_espacial_desc
     */
    public String getUbicacion_espacial_desc() {
        return ubicacion_espacial_desc;
    }

    /**
     * @param ubicacion_espacial_desc the ubicacion_espacial_desc to set
     */
    public void setUbicacion_espacial_desc(String ubicacion_espacial_desc) {
        this.ubicacion_espacial_desc = ubicacion_espacial_desc;
    }

    /**
     * @return the postura
     */
    public int getPostura() {
        return postura;
    }

    /**
     * @param postura the postura to set
     */
    public void setPostura(int postura) {
        this.postura = postura;
    }

    /**
     * @return the postura_desc
     */
    public String getPostura_desc() {
        return postura_desc;
    }

    /**
     * @param postura_desc the postura_desc to set
     */
    public void setPostura_desc(String postura_desc) {
        this.postura_desc = postura_desc;
    }

    /**
     * @return the EESS
     */
    public String getEESS() {
        return EESS;
    }

    /**
     * @param EESS the EESS to set
     */
    public void setEESS(String EESS) {
        this.EESS = EESS;
    }

    /**
     * @return the EEII
     */
    public String getEEII() {
        return EEII;
    }

    /**
     * @param EEII the EEII to set
     */
    public void setEEII(String EEII) {
        this.EEII = EEII;
    }

    /**
     * @return the deformidad
     */
    public String getDeformidad() {
        return deformidad;
    }

    /**
     * @param deformidad the deformidad to set
     */
    public void setDeformidad(String deformidad) {
        this.deformidad = deformidad;
    }

    /**
     * @return the estado_nutricional
     */
    public int getEstado_nutricional() {
        return estado_nutricional;
    }

    /**
     * @param estado_nutricional the estado_nutricional to set
     */
    public void setEstado_nutricional(int estado_nutricional) {
        this.estado_nutricional = estado_nutricional;
    }

    /**
     * @return the estado_nutricional_desc
     */
    public String getEstado_nutricional_desc() {
        return estado_nutricional_desc;
    }

    /**
     * @param estado_nutricional_desc the estado_nutricional_desc to set
     */
    public void setEstado_nutricional_desc(String estado_nutricional_desc) {
        this.estado_nutricional_desc = estado_nutricional_desc;
    }

    /**
     * @return the coloracion_piel
     */
    public int getColoracion_piel() {
        return coloracion_piel;
    }

    /**
     * @param coloracion_piel the coloracion_piel to set
     */
    public void setColoracion_piel(int coloracion_piel) {
        this.coloracion_piel = coloracion_piel;
    }

    /**
     * @return the coloracion_piel_desc
     */
    public String getColoracion_piel_desc() {
        return coloracion_piel_desc;
    }

    /**
     * @param coloracion_piel_desc the coloracion_piel_desc to set
     */
    public void setColoracion_piel_desc(String coloracion_piel_desc) {
        this.coloracion_piel_desc = coloracion_piel_desc;
    }

    /**
     * @return the cianosis
     */
    public int getCianosis() {
        return cianosis;
    }

    /**
     * @param cianosis the cianosis to set
     */
    public void setCianosis(int cianosis) {
        this.cianosis = cianosis;
    }

    /**
     * @return the cianosis_desc
     */
    public String getCianosis_desc() {
        return cianosis_desc;
    }

    /**
     * @param cianosis_desc the cianosis_desc to set
     */
    public void setCianosis_desc(String cianosis_desc) {
        this.cianosis_desc = cianosis_desc;
    }

    /**
     * @return the ep_normal
     */
    public int getEp_normal() {
        return ep_normal;
    }

    /**
     * @param ep_normal the ep_normal to set
     */
    public void setEp_normal(int ep_normal) {
        this.ep_normal = ep_normal;
    }

    /**
     * @return the ep_normal_desc
     */
    public String getEp_normal_desc() {
        return ep_normal_desc;
    }

    /**
     * @param ep_normal_desc the ep_normal_desc to set
     */
    public void setEp_normal_desc(String ep_normal_desc) {
        this.ep_normal_desc = ep_normal_desc;
    }

    /**
     * @return the ep_escara
     */
    public int getEp_escara() {
        return ep_escara;
    }

    /**
     * @param ep_escara the ep_escara to set
     */
    public void setEp_escara(int ep_escara) {
        this.ep_escara = ep_escara;
    }

    /**
     * @return the ep_escara_desc
     */
    public String getEp_escara_desc() {
        return ep_escara_desc;
    }

    /**
     * @param ep_escara_desc the ep_escara_desc to set
     */
    public void setEp_escara_desc(String ep_escara_desc) {
        this.ep_escara_desc = ep_escara_desc;
    }

    /**
     * @return the ep_cicatriz
     */
    public int getEp_cicatriz() {
        return ep_cicatriz;
    }

    /**
     * @param ep_cicatriz the ep_cicatriz to set
     */
    public void setEp_cicatriz(int ep_cicatriz) {
        this.ep_cicatriz = ep_cicatriz;
    }

    /**
     * @return the ep_cicatriz_desc
     */
    public String getEp_cicatriz_desc() {
        return ep_cicatriz_desc;
    }

    /**
     * @param ep_cicatriz_desc the ep_cicatriz_desc to set
     */
    public void setEp_cicatriz_desc(String ep_cicatriz_desc) {
        this.ep_cicatriz_desc = ep_cicatriz_desc;
    }

    /**
     * @return the ep_hematoma
     */
    public int getEp_hematoma() {
        return ep_hematoma;
    }

    /**
     * @param ep_hematoma the ep_hematoma to set
     */
    public void setEp_hematoma(int ep_hematoma) {
        this.ep_hematoma = ep_hematoma;
    }

    /**
     * @return the ep_hematoma_desc
     */
    public String getEp_hematoma_desc() {
        return ep_hematoma_desc;
    }

    /**
     * @param ep_hematoma_desc the ep_hematoma_desc to set
     */
    public void setEp_hematoma_desc(String ep_hematoma_desc) {
        this.ep_hematoma_desc = ep_hematoma_desc;
    }

    /**
     * @return the ep_petequia
     */
    public int getEp_petequia() {
        return ep_petequia;
    }

    /**
     * @param ep_petequia the ep_petequia to set
     */
    public void setEp_petequia(int ep_petequia) {
        this.ep_petequia = ep_petequia;
    }

    /**
     * @return the ep_petequia_desc
     */
    public String getEp_petequia_desc() {
        return ep_petequia_desc;
    }

    /**
     * @param ep_petequia_desc the ep_petequia_desc to set
     */
    public void setEp_petequia_desc(String ep_petequia_desc) {
        this.ep_petequia_desc = ep_petequia_desc;
    }

    /**
     * @return the ep_edema
     */
    public int getEp_edema() {
        return ep_edema;
    }

    /**
     * @param ep_edema the ep_edema to set
     */
    public void setEp_edema(int ep_edema) {
        this.ep_edema = ep_edema;
    }

    /**
     * @return the ep_edema_desc
     */
    public String getEp_edema_desc() {
        return ep_edema_desc;
    }

    /**
     * @param ep_edema_desc the ep_edema_desc to set
     */
    public void setEp_edema_desc(String ep_edema_desc) {
        this.ep_edema_desc = ep_edema_desc;
    }

    /**
     * @return the ep_normal_det
     */
    public String getEp_normal_det() {
        return ep_normal_det;
    }

    /**
     * @param ep_normal_det the ep_normal_det to set
     */
    public void setEp_normal_det(String ep_normal_det) {
        this.ep_normal_det = ep_normal_det;
    }

    /**
     * @return the ep_escara_det
     */
    public String getEp_escara_det() {
        return ep_escara_det;
    }

    /**
     * @param ep_escara_det the ep_escara_det to set
     */
    public void setEp_escara_det(String ep_escara_det) {
        this.ep_escara_det = ep_escara_det;
    }

    /**
     * @return the ep_cicatriz_det
     */
    public String getEp_cicatriz_det() {
        return ep_cicatriz_det;
    }

    /**
     * @param ep_cicatriz_det the ep_cicatriz_det to set
     */
    public void setEp_cicatriz_det(String ep_cicatriz_det) {
        this.ep_cicatriz_det = ep_cicatriz_det;
    }

    /**
     * @return the ep_hematoma_det
     */
    public String getEp_hematoma_det() {
        return ep_hematoma_det;
    }

    /**
     * @param ep_hematoma_det the ep_hematoma_det to set
     */
    public void setEp_hematoma_det(String ep_hematoma_det) {
        this.ep_hematoma_det = ep_hematoma_det;
    }

    /**
     * @return the ep_petequia_det
     */
    public String getEp_petequia_det() {
        return ep_petequia_det;
    }

    /**
     * @param ep_petequia_det the ep_petequia_det to set
     */
    public void setEp_petequia_det(String ep_petequia_det) {
        this.ep_petequia_det = ep_petequia_det;
    }

    /**
     * @return the ep_edema_det
     */
    public String getEp_edema_det() {
        return ep_edema_det;
    }

    /**
     * @param ep_edema_det the ep_edema_det to set
     */
    public void setEp_edema_det(String ep_edema_det) {
        this.ep_edema_det = ep_edema_det;
    }

    /**
     * @return the patron_respiratorio
     */
    public int getPatron_respiratorio() {
        return patron_respiratorio;
    }

    /**
     * @param patron_respiratorio the patron_respiratorio to set
     */
    public void setPatron_respiratorio(int patron_respiratorio) {
        this.patron_respiratorio = patron_respiratorio;
    }

    /**
     * @return the patron_respiratorio_desc
     */
    public String getPatron_respiratorio_desc() {
        return patron_respiratorio_desc;
    }

    /**
     * @param patron_respiratorio_desc the patron_respiratorio_desc to set
     */
    public void setPatron_respiratorio_desc(String patron_respiratorio_desc) {
        this.patron_respiratorio_desc = patron_respiratorio_desc;
    }

    /**
     * @return the respirador
     */
    public int getRespirador() {
        return respirador;
    }

    /**
     * @param respirador the respirador to set
     */
    public void setRespirador(int respirador) {
        this.respirador = respirador;
    }

    /**
     * @return the respirador_desc
     */
    public String getRespirador_desc() {
        return respirador_desc;
    }

    /**
     * @param respirador_desc the respirador_desc to set
     */
    public void setRespirador_desc(String respirador_desc) {
        this.respirador_desc = respirador_desc;
    }

    /**
     * @return the dificultad_respiratoria
     */
    public int getDificultad_respiratoria() {
        return dificultad_respiratoria;
    }

    /**
     * @param dificultad_respiratoria the dificultad_respiratoria to set
     */
    public void setDificultad_respiratoria(int dificultad_respiratoria) {
        this.dificultad_respiratoria = dificultad_respiratoria;
    }

    /**
     * @return the dificultad_respiratoria_desc
     */
    public String getDificultad_respiratoria_desc() {
        return dificultad_respiratoria_desc;
    }

    /**
     * @param dificultad_respiratoria_desc the dificultad_respiratoria_desc to
     * set
     */
    public void setDificultad_respiratoria_desc(String dificultad_respiratoria_desc) {
        this.dificultad_respiratoria_desc = dificultad_respiratoria_desc;
    }

    /**
     * @return the musculatura_accesoria
     */
    public int getMusculatura_accesoria() {
        return musculatura_accesoria;
    }

    /**
     * @param musculatura_accesoria the musculatura_accesoria to set
     */
    public void setMusculatura_accesoria(int musculatura_accesoria) {
        this.musculatura_accesoria = musculatura_accesoria;
    }

    /**
     * @return the musculatura_accesoria_desc
     */
    public String getMusculatura_accesoria_desc() {
        return musculatura_accesoria_desc;
    }

    /**
     * @param musculatura_accesoria_desc the musculatura_accesoria_desc to set
     */
    public void setMusculatura_accesoria_desc(String musculatura_accesoria_desc) {
        this.musculatura_accesoria_desc = musculatura_accesoria_desc;
    }

    /**
     * @return the musculatura_accesoria_det
     */
    public String getMusculatura_accesoria_det() {
        return musculatura_accesoria_det;
    }

    /**
     * @param musculatura_accesoria_det the musculatura_accesoria_det to set
     */
    public void setMusculatura_accesoria_det(String musculatura_accesoria_det) {
        this.musculatura_accesoria_det = musculatura_accesoria_det;
    }

    /**
     * @return the aleteo_nasal
     */
    public int getAleteo_nasal() {
        return aleteo_nasal;
    }

    /**
     * @param aleteo_nasal the aleteo_nasal to set
     */
    public void setAleteo_nasal(int aleteo_nasal) {
        this.aleteo_nasal = aleteo_nasal;
    }

    /**
     * @return the aleteo_nasal_desc
     */
    public String getAleteo_nasal_desc() {
        return aleteo_nasal_desc;
    }

    /**
     * @param aleteo_nasal_desc the aleteo_nasal_desc to set
     */
    public void setAleteo_nasal_desc(String aleteo_nasal_desc) {
        this.aleteo_nasal_desc = aleteo_nasal_desc;
    }

    /**
     * @return the aleteo_costal
     */
    public int getAleteo_costal() {
        return aleteo_costal;
    }

    /**
     * @param aleteo_costal the aleteo_costal to set
     */
    public void setAleteo_costal(int aleteo_costal) {
        this.aleteo_costal = aleteo_costal;
    }

    /**
     * @return the aleteo_costal_desc
     */
    public String getAleteo_costal_desc() {
        return aleteo_costal_desc;
    }

    /**
     * @param aleteo_costal_desc the aleteo_costal_desc to set
     */
    public void setAleteo_costal_desc(String aleteo_costal_desc) {
        this.aleteo_costal_desc = aleteo_costal_desc;
    }

    /**
     * @return the temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the temperatura_desc
     */
    public String getTemperatura_desc() {
        return temperatura_desc;
    }

    /**
     * @param temperatura_desc the temperatura_desc to set
     */
    public void setTemperatura_desc(String temperatura_desc) {
        this.temperatura_desc = temperatura_desc;
    }

    /**
     * @return the elasticidad
     */
    public int getElasticidad() {
        return elasticidad;
    }

    /**
     * @param elasticidad the elasticidad to set
     */
    public void setElasticidad(int elasticidad) {
        this.elasticidad = elasticidad;
    }

    /**
     * @return the elasticidad_desc
     */
    public String getElasticidad_desc() {
        return elasticidad_desc;
    }

    /**
     * @param elasticidad_desc the elasticidad_desc to set
     */
    public void setElasticidad_desc(String elasticidad_desc) {
        this.elasticidad_desc = elasticidad_desc;
    }

    /**
     * @return the elasticidad_det
     */
    public String getElasticidad_det() {
        return elasticidad_det;
    }

    /**
     * @param elasticidad_det the elasticidad_det to set
     */
    public void setElasticidad_det(String elasticidad_det) {
        this.elasticidad_det = elasticidad_det;
    }

    /**
     * @return the restriccion_miofascial
     */
    public String getRestriccion_miofascial() {
        return restriccion_miofascial;
    }

    /**
     * @param restriccion_miofascial the restriccion_miofascial to set
     */
    public void setRestriccion_miofascial(String restriccion_miofascial) {
        this.restriccion_miofascial = restriccion_miofascial;
    }

    /**
     * @return the llene_capilar
     */
    public int getLlene_capilar() {
        return llene_capilar;
    }

    /**
     * @param llene_capilar the llene_capilar to set
     */
    public void setLlene_capilar(int llene_capilar) {
        this.llene_capilar = llene_capilar;
    }

    /**
     * @return the llene_capilar_desc
     */
    public String getLlene_capilar_desc() {
        return llene_capilar_desc;
    }

    /**
     * @param llene_capilar_desc the llene_capilar_desc to set
     */
    public void setLlene_capilar_desc(String llene_capilar_desc) {
        this.llene_capilar_desc = llene_capilar_desc;
    }

    /**
     * @return the edema
     */
    public int getEdema() {
        return edema;
    }

    /**
     * @param edema the edema to set
     */
    public void setEdema(int edema) {
        this.edema = edema;
    }

    /**
     * @return the edema_desc
     */
    public String getEdema_desc() {
        return edema_desc;
    }

    /**
     * @param edema_desc the edema_desc to set
     */
    public void setEdema_desc(String edema_desc) {
        this.edema_desc = edema_desc;
    }

    /**
     * @return the dolor
     */
    public int getDolor() {
        return dolor;
    }

    /**
     * @param dolor the dolor to set
     */
    public void setDolor(int dolor) {
        this.dolor = dolor;
    }

    /**
     * @return the dolor_desc
     */
    public String getDolor_desc() {
        return dolor_desc;
    }

    /**
     * @param dolor_desc the dolor_desc to set
     */
    public void setDolor_desc(String dolor_desc) {
        this.dolor_desc = dolor_desc;
    }

    /**
     * @return the EVA
     */
    public int getEVA() {
        return EVA;
    }

    /**
     * @param EVA the EVA to set
     */
    public void setEVA(int EVA) {
        this.EVA = EVA;
    }

    /**
     * @return the EVA_desc
     */
    public String getEVA_desc() {
        return EVA_desc;
    }

    /**
     * @param EVA_desc the EVA_desc to set
     */
    public void setEVA_desc(String EVA_desc) {
        this.EVA_desc = EVA_desc;
    }

    /**
     * @return the desalineacion
     */
    public String getDesalineacion() {
        return desalineacion;
    }

    /**
     * @param desalineacion the desalineacion to set
     */
    public void setDesalineacion(String desalineacion) {
        this.desalineacion = desalineacion;
    }

    /**
     * @return the frecuencia_cardiaca
     */
    public String getFrecuencia_cardiaca() {
        return frecuencia_cardiaca;
    }

    /**
     * @param frecuencia_cardiaca the frecuencia_cardiaca to set
     */
    public void setFrecuencia_cardiaca(String frecuencia_cardiaca) {
        this.frecuencia_cardiaca = frecuencia_cardiaca;
    }

    /**
     * @return the frecuencia_respiratoria
     */
    public String getFrecuencia_respiratoria() {
        return frecuencia_respiratoria;
    }

    /**
     * @param frecuencia_respiratoria the frecuencia_respiratoria to set
     */
    public void setFrecuencia_respiratoria(String frecuencia_respiratoria) {
        this.frecuencia_respiratoria = frecuencia_respiratoria;
    }

    /**
     * @return the saturacion
     */
    public String getSaturacion() {
        return saturacion;
    }

    /**
     * @param saturacion the saturacion to set
     */
    public void setSaturacion(String saturacion) {
        this.saturacion = saturacion;
    }

    /**
     * @return the aus_pi_ls
     */
    public int getAus_pi_ls() {
        return aus_pi_ls;
    }

    /**
     * @param aus_pi_ls the aus_pi_ls to set
     */
    public void setAus_pi_ls(int aus_pi_ls) {
        this.aus_pi_ls = aus_pi_ls;
    }

    /**
     * @return the aus_pi_li
     */
    public int getAus_pi_li() {
        return aus_pi_li;
    }

    /**
     * @param aus_pi_li the aus_pi_li to set
     */
    public void setAus_pi_li(int aus_pi_li) {
        this.aus_pi_li = aus_pi_li;
    }

    /**
     * @return the aus_pd_ls
     */
    public int getAus_pd_ls() {
        return aus_pd_ls;
    }

    /**
     * @param aus_pd_ls the aus_pd_ls to set
     */
    public void setAus_pd_ls(int aus_pd_ls) {
        this.aus_pd_ls = aus_pd_ls;
    }

    /**
     * @return the aus_pd_lm
     */
    public int getAus_pd_lm() {
        return aus_pd_lm;
    }

    /**
     * @param aus_pd_lm the aus_pd_lm to set
     */
    public void setAus_pd_lm(int aus_pd_lm) {
        this.aus_pd_lm = aus_pd_lm;
    }

    /**
     * @return the aus_pd_li
     */
    public int getAus_pd_li() {
        return aus_pd_li;
    }

    /**
     * @param aus_pd_li the aus_pd_li to set
     */
    public void setAus_pd_li(int aus_pd_li) {
        this.aus_pd_li = aus_pd_li;
    }

    /**
     * @return the sibilancia
     */
    public int getSibilancia() {
        return sibilancia;
    }

    /**
     * @param sibilancia the sibilancia to set
     */
    public void setSibilancia(int sibilancia) {
        this.sibilancia = sibilancia;
    }

    /**
     * @return the roncus
     */
    public int getRoncus() {
        return roncus;
    }

    /**
     * @param roncus the roncus to set
     */
    public void setRoncus(int roncus) {
        this.roncus = roncus;
    }

    /**
     * @return the crepitacion
     */
    public int getCrepitacion() {
        return crepitacion;
    }

    /**
     * @param crepitacion the crepitacion to set
     */
    public void setCrepitacion(int crepitacion) {
        this.crepitacion = crepitacion;
    }

    /**
     * @return the estertor_traqueal
     */
    public int getEstertor_traqueal() {
        return estertor_traqueal;
    }

    /**
     * @param estertor_traqueal the estertor_traqueal to set
     */
    public void setEstertor_traqueal(int estertor_traqueal) {
        this.estertor_traqueal = estertor_traqueal;
    }

    /**
     * @return the crepitacion_fina
     */
    public int getCrepitacion_fina() {
        return crepitacion_fina;
    }

    /**
     * @param crepitacion_fina the crepitacion_fina to set
     */
    public void setCrepitacion_fina(int crepitacion_fina) {
        this.crepitacion_fina = crepitacion_fina;
    }

    /**
     * @return the crepitacion_gruesa
     */
    public int getCrepitacion_gruesa() {
        return crepitacion_gruesa;
    }

    /**
     * @param crepitacion_gruesa the crepitacion_gruesa to set
     */
    public void setCrepitacion_gruesa(int crepitacion_gruesa) {
        this.crepitacion_gruesa = crepitacion_gruesa;
    }

    /**
     * @return the sibilancia_desc
     */
    public String getSibilancia_desc() {
        return sibilancia_desc;
    }

    /**
     * @param sibilancia_desc the sibilancia_desc to set
     */
    public void setSibilancia_desc(String sibilancia_desc) {
        this.sibilancia_desc = sibilancia_desc;
    }

    /**
     * @return the roncus_desc
     */
    public String getRoncus_desc() {
        return roncus_desc;
    }

    /**
     * @param roncus_desc the roncus_desc to set
     */
    public void setRoncus_desc(String roncus_desc) {
        this.roncus_desc = roncus_desc;
    }

    /**
     * @return the crepitacion_desc
     */
    public String getCrepitacion_desc() {
        return crepitacion_desc;
    }

    /**
     * @param crepitacion_desc the crepitacion_desc to set
     */
    public void setCrepitacion_desc(String crepitacion_desc) {
        this.crepitacion_desc = crepitacion_desc;
    }

    /**
     * @return the estertor_traqueal_desc
     */
    public String getEstertor_traqueal_desc() {
        return estertor_traqueal_desc;
    }

    /**
     * @param estertor_traqueal_desc the estertor_traqueal_desc to set
     */
    public void setEstertor_traqueal_desc(String estertor_traqueal_desc) {
        this.estertor_traqueal_desc = estertor_traqueal_desc;
    }

    /**
     * @return the crepitacion_fina_desc
     */
    public String getCrepitacion_fina_desc() {
        return crepitacion_fina_desc;
    }

    /**
     * @param crepitacion_fina_desc the crepitacion_fina_desc to set
     */
    public void setCrepitacion_fina_desc(String crepitacion_fina_desc) {
        this.crepitacion_fina_desc = crepitacion_fina_desc;
    }

    /**
     * @return the crepitacion_gruesa_desc
     */
    public String getCrepitacion_gruesa_desc() {
        return crepitacion_gruesa_desc;
    }

    /**
     * @param crepitacion_gruesa_desc the crepitacion_gruesa_desc to set
     */
    public void setCrepitacion_gruesa_desc(String crepitacion_gruesa_desc) {
        this.crepitacion_gruesa_desc = crepitacion_gruesa_desc;
    }

    /**
     * @return the ra_pi_ls
     */
    public String getRa_pi_ls() {
        return ra_pi_ls;
    }

    /**
     * @param ra_pi_ls the ra_pi_ls to set
     */
    public void setRa_pi_ls(String ra_pi_ls) {
        this.ra_pi_ls = ra_pi_ls;
    }

    /**
     * @return the ra_pi_li
     */
    public String getRa_pi_li() {
        return ra_pi_li;
    }

    /**
     * @param ra_pi_li the ra_pi_li to set
     */
    public void setRa_pi_li(String ra_pi_li) {
        this.ra_pi_li = ra_pi_li;
    }

    /**
     * @return the ra_pd_ls
     */
    public String getRa_pd_ls() {
        return ra_pd_ls;
    }

    /**
     * @param ra_pd_ls the ra_pd_ls to set
     */
    public void setRa_pd_ls(String ra_pd_ls) {
        this.ra_pd_ls = ra_pd_ls;
    }

    /**
     * @return the ra_pd_lm
     */
    public String getRa_pd_lm() {
        return ra_pd_lm;
    }

    /**
     * @param ra_pd_lm the ra_pd_lm to set
     */
    public void setRa_pd_lm(String ra_pd_lm) {
        this.ra_pd_lm = ra_pd_lm;
    }

    /**
     * @return the ra_pd_li
     */
    public String getRa_pd_li() {
        return ra_pd_li;
    }

    /**
     * @param ra_pd_li the ra_pd_li to set
     */
    public void setRa_pd_li(String ra_pd_li) {
        this.ra_pd_li = ra_pd_li;
    }

    /**
     * @return the tos_presencia
     */
    public int getTos_presencia() {
        return tos_presencia;
    }

    /**
     * @param tos_presencia the tos_presencia to set
     */
    public void setTos_presencia(int tos_presencia) {
        this.tos_presencia = tos_presencia;
    }

    /**
     * @return the tos_produccion
     */
    public int getTos_produccion() {
        return tos_produccion;
    }

    /**
     * @param tos_produccion the tos_produccion to set
     */
    public void setTos_produccion(int tos_produccion) {
        this.tos_produccion = tos_produccion;
    }

    /**
     * @return the secrecion_cantidad
     */
    public int getSecrecion_cantidad() {
        return secrecion_cantidad;
    }

    /**
     * @param secrecion_cantidad the secrecion_cantidad to set
     */
    public void setSecrecion_cantidad(int secrecion_cantidad) {
        this.secrecion_cantidad = secrecion_cantidad;
    }

    /**
     * @return the secrecion_coloracion
     */
    public int getSecrecion_coloracion() {
        return secrecion_coloracion;
    }

    /**
     * @param secrecion_coloracion the secrecion_coloracion to set
     */
    public void setSecrecion_coloracion(int secrecion_coloracion) {
        this.secrecion_coloracion = secrecion_coloracion;
    }

    /**
     * @return the secrecion_tipo
     */
    public int getSecrecion_tipo() {
        return secrecion_tipo;
    }

    /**
     * @param secrecion_tipo the secrecion_tipo to set
     */
    public void setSecrecion_tipo(int secrecion_tipo) {
        this.secrecion_tipo = secrecion_tipo;
    }

    /**
     * @return the tos_presencia_desc
     */
    public String getTos_presencia_desc() {
        return tos_presencia_desc;
    }

    /**
     * @param tos_presencia_desc the tos_presencia_desc to set
     */
    public void setTos_presencia_desc(String tos_presencia_desc) {
        this.tos_presencia_desc = tos_presencia_desc;
    }

    /**
     * @return the tos_produccion_desc
     */
    public String getTos_produccion_desc() {
        return tos_produccion_desc;
    }

    /**
     * @param tos_produccion_desc the tos_produccion_desc to set
     */
    public void setTos_produccion_desc(String tos_produccion_desc) {
        this.tos_produccion_desc = tos_produccion_desc;
    }

    /**
     * @return the secrecion_cantidad_desc
     */
    public String getSecrecion_cantidad_desc() {
        return secrecion_cantidad_desc;
    }

    /**
     * @param secrecion_cantidad_desc the secrecion_cantidad_desc to set
     */
    public void setSecrecion_cantidad_desc(String secrecion_cantidad_desc) {
        this.secrecion_cantidad_desc = secrecion_cantidad_desc;
    }

    /**
     * @return the secrecion_coloracion_desc
     */
    public String getSecrecion_coloracion_desc() {
        return secrecion_coloracion_desc;
    }

    /**
     * @param secrecion_coloracion_desc the secrecion_coloracion_desc to set
     */
    public void setSecrecion_coloracion_desc(String secrecion_coloracion_desc) {
        this.secrecion_coloracion_desc = secrecion_coloracion_desc;
    }

    /**
     * @return the secrecion_tipo_desc
     */
    public String getSecrecion_tipo_desc() {
        return secrecion_tipo_desc;
    }

    /**
     * @param secrecion_tipo_desc the secrecion_tipo_desc to set
     */
    public void setSecrecion_tipo_desc(String secrecion_tipo_desc) {
        this.secrecion_tipo_desc = secrecion_tipo_desc;
    }

    /**
     * @return the fecha_ingreso
     */
    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the estado_desc
     */
    public String getEstado_desc() {
        return estado_desc;
    }

    /**
     * @param estado_desc the estado_desc to set
     */
    public void setEstado_desc(String estado_desc) {
        this.estado_desc = estado_desc;
    }

    /**
     * @return the hora_ingreso
     */
    public String getHora_ingreso() {
        return hora_ingreso;
    }

    /**
     * @param hora_ingreso the hora_ingreso to set
     */
    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    /**
     * @return the aus_pi_ls_desc
     */
    public String getAus_pi_ls_desc() {
        return aus_pi_ls_desc;
    }

    /**
     * @param aus_pi_ls_desc the aus_pi_ls_desc to set
     */
    public void setAus_pi_ls_desc(String aus_pi_ls_desc) {
        this.aus_pi_ls_desc = aus_pi_ls_desc;
    }

    /**
     * @return the aus_pi_li_desc
     */
    public String getAus_pi_li_desc() {
        return aus_pi_li_desc;
    }

    /**
     * @param aus_pi_li_desc the aus_pi_li_desc to set
     */
    public void setAus_pi_li_desc(String aus_pi_li_desc) {
        this.aus_pi_li_desc = aus_pi_li_desc;
    }

    /**
     * @return the aus_pd_ls_desc
     */
    public String getAus_pd_ls_desc() {
        return aus_pd_ls_desc;
    }

    /**
     * @param aus_pd_ls_desc the aus_pd_ls_desc to set
     */
    public void setAus_pd_ls_desc(String aus_pd_ls_desc) {
        this.aus_pd_ls_desc = aus_pd_ls_desc;
    }

    /**
     * @return the aus_pd_lm_desc
     */
    public String getAus_pd_lm_desc() {
        return aus_pd_lm_desc;
    }

    /**
     * @param aus_pd_lm_desc the aus_pd_lm_desc to set
     */
    public void setAus_pd_lm_desc(String aus_pd_lm_desc) {
        this.aus_pd_lm_desc = aus_pd_lm_desc;
    }

    /**
     * @return the aus_pd_li_desc
     */
    public String getAus_pd_li_desc() {
        return aus_pd_li_desc;
    }

    /**
     * @param aus_pd_li_desc the aus_pd_li_desc to set
     */
    public void setAus_pd_li_desc(String aus_pd_li_desc) {
        this.aus_pd_li_desc = aus_pd_li_desc;
    }

    
}
