<%-- 
    Document   : receta
    Created on : 01-sep-2015, 17:42:05
    Author     : Informatica
--%>

<%@page import="CapaDato.cDiagnostico"%>
<%@page import="CapaDato.cDato"%>
<%@page import="java.util.Vector"%>
<%@page import="CapaDato.cReceta"%>
<%@page import="java.text.ParseException"%>
<%@page import="CapaDato.cUnidadMedida"%>
<%@page import="CapaDato.cVisita"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="CapaDato.cPaciente"%>
<%@page import="CapaDato.cInsumo"%>
<%@page import="CapaDato.cPrescripcion"%>
<%@page import="CapaDato.cInfusion"%>
<%@page import="CapaDato.cInfusionDetalle"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.text.DateFormat"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesi�n ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("index.jsp?timeout=1");
    } else {

        String obtiene_usuario = session1.getAttribute("usuario_rut").toString();

        Date fecha_del_dia = new Date();
        Locale hora_local = new Locale("es", "CHL");
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
        java.text.DateFormat formateadorFechaCorta = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, currentLocale);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE-dd-MMM", currentLocale);
        SimpleDateFormat formateaDMY = new SimpleDateFormat("dd/MM/yyyy", currentLocale);
        int hora_del_dia = fecha_del_dia.getHours();
        int minuto_del_dia = fecha_del_dia.getMinutes();
        String hora = "";
        String minuto = "";
        if (hora_del_dia < 10) {
            hora = "0" + hora_del_dia;
        } else {
            hora = "" + hora_del_dia;
        }
        if (minuto_del_dia < 10) {
            minuto = "0" + minuto_del_dia;
        } else {
            minuto = "" + minuto_del_dia;
        }

        //
        int obtiene_duo = 0;
        if (request.getParameter("txt_duo") != null) {
            obtiene_duo = Integer.parseInt(request.getParameter("txt_duo"));
        }

        String titulo = "class='td-header'";
        String datos  = "class='td-data'";
        ArrayList diagnosticos = neg.lista_diagnostico(obtiene_duo, " 1,2 ");
        Iterator it_dia = diagnosticos.iterator();
         String diagnostico = "";
            while (it_dia.hasNext()) {
                cDiagnostico dia = (cDiagnostico) it_dia.next();

                diagnostico += dia.getDescripcion_diagnostico() + " \n";

            }
            if (diagnostico.length() > 0) {
                diagnostico = diagnostico.substring(0, diagnostico.length() - 2);
            }
        ArrayList lista_unidad_medida = neg.lista_unidad_medida();
        Iterator it_unidad_medida = lista_unidad_medida.iterator();

        ArrayList lista_examenes = neg.lista_examenes();
        Iterator it_examenes = lista_examenes.iterator();

        ArrayList lista_meds = neg.lista_medicamento_completa();
        ArrayList lista_pm   = neg.lista_prescripciones_por_duo(obtiene_duo);
        ArrayList lista_inf  = neg.lista_infusiones_por_duo(obtiene_duo);

        int obtiene_perfil = 0;

        try {
            obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
        } catch (NumberFormatException ex) {
            obtiene_perfil = -1;
        }

%>

<script src="<%=neg.getLocal()%>js/jquery/jquery.js"></script>
<jsp:include page="Header.jsp" />


<style>
    /* ======================================
       RECETA – ESTILOS UNIFICADOS
       Color principal: #4169E1
    ====================================== */

    *, *::before, *::after { box-sizing: border-box; }

    body, input, select, textarea, button, th, td, legend {
        font-family: Arial, sans-serif;
        font-size: 13px;
    }

    /* === FIELDSET Y LEGEND === */
    fieldset {
        border: 1px solid #4169E1;
        border-radius: 4px;
        margin: 8px 0;
        padding: 8px 14px 12px;
    }
    legend {
        color: #4169E1;
        font-weight: bold;
        font-size: 13px;
        padding: 0 6px;
    }

    /* === TABLAS GLOBALES === */
    table {
        border-collapse: collapse;
        width: 100%;
    }
    th {
        background-color: #4169E1;
        color: white;
        text-align: left;
        padding: 7px 8px;
        font-size: 12px;
        font-weight: bold;
    }
    td {
        text-align: left;
        padding: 6px 8px;
    }
    tr:nth-child(even) { background-color: #f4f6fb; }

    /* === CELDAS TABLA PACIENTE === */
    .td-header {
        background-color: #4169E1;
        color: white;
        font-weight: bold;
    }
    .td-data {
        background-color: #f9fafb;
        color: #222;
    }

    /* === ELEMENTOS DE FORMULARIO === */
    input[type=text], input[type=number], input[type=email], select, textarea {
        font-family: Arial, sans-serif;
        font-size: 12px;
        padding: 4px 6px;
        border: 1px solid #bbb;
        border-radius: 3px;
        width: 100%;
    }
    textarea { resize: vertical; }

    /* === BOTONES BOOTSTRAP (override) === */
    .btn-primary, input.btn.btn-primary {
        background-color: #4169E1 !important;
        border-color: #2050bb !important;
        font-family: Arial, sans-serif;
        font-size: 13px;
        font-weight: bold;
    }
    .btn-primary:hover { background-color: #2050cc !important; }

    /* === PANEL LATERAL (selector de cama) === */
    #nav {
        line-height: 28px;
        background-color: #f0f2f8;
        border: 1px solid #4169E1;
        border-radius: 4px;
        height: auto;
        width: 225px;
        float: right;
        padding: 10px;
    }
    #nav h2 {
        font-size: 13px;
        color: #4169E1;
        margin: 0 0 8px;
        font-weight: bold;
    }
    #section {
        width: calc(100% - 240px);
        float: left;
        padding: 10px;
    }

    /* === ETIQUETAS DE CAMPO (b dentro de fieldset) === */
    fieldset b, fieldset strong { color: #333; font-size: 12px; }

    /* === TÍTULO PÁGINA === */
    legend.page-legend {
        font-size: 16px;
        font-weight: bold;
        color: #fff;
        background: #4169E1;
        padding: 6px 18px;
        border-radius: 3px;
        display: block;
        margin-bottom: 8px;
    }

    /* === TABLA MEDICAMENTOS LEGADO (dentro de fieldset) === */
    fieldset table { border: none; }
    fieldset table td { border: none; vertical-align: top; padding: 4px 6px; }

    /* === COMBOBOX AUTOCOMPLETE === */
    .custom-combobox { position: relative; display: inline-block; }
    .custom-combobox-toggle { position: absolute; top: 0; bottom: 0; margin-left: -1px; padding: 0; }

    /* ======================================
       MÓDULO RX (PRESCRIPCIONES)
    ====================================== */
    .rx-tabs { margin: 6px 0 0 0; }
    .rx-tab {
        background: #d8dff8;
        border: 1px solid #4169E1;
        border-bottom: none;
        padding: 5px 18px;
        cursor: pointer;
        margin: 4px 2px 0;
        font-weight: bold;
        font-size: 12px;
        color: #4169E1;
        border-radius: 4px 4px 0 0;
    }
    .rx-tab.active { background: #4169E1; color: #fff; }
    .rx-panel { border: 1px solid #4169E1; padding: 8px; background: #fff; }
    .rx-section { border: 1px solid #c9d4f5; margin: 6px 0; border-radius: 3px; }
    .rx-section-hdr { background: #4169E1; color: #fff; padding: 5px 8px; font-weight: bold; font-size: 12px; border-radius: 3px 3px 0 0; }
    .rx-section-body { padding: 8px; }
    .rx-col-hdr { font-weight: bold; font-size: 12px; padding: 4px 6px; text-align: left; color: #333; background: #e8edf8 !important; }
    .rx-layout-row { display: flex; gap: 10px; }
    .rx-layout-left { flex: 1; min-width: 0; }
    .rx-layout-right { width: 310px; flex-shrink: 0; }
    .rx-side-btns { display: flex; flex-direction: column; gap: 6px; padding-top: 22px; width: 90px; }
    .rx-empty-row { color: #999; text-align: center; font-style: italic; padding: 10px !important; }
    table.rx-grid { width: 100%; border-collapse: collapse; font-size: 12px; }
    table.rx-grid th { background: #4169E1 !important; color: #fff !important; padding: 5px 6px; font-size: 12px; font-weight: bold; }
    table.rx-grid td { padding: 4px 6px; border-bottom: 1px solid #e0e6f5; background: inherit; }
    table.rx-grid tr.rx-selectable:hover { background: #d8e4ff; cursor: pointer; }
    table.rx-grid tr.rx-selected td { background: #4169E1 !important; color: #fff !important; }
    .rx-btn-blue {
        background: #4169E1; color: #fff; border: 1px solid #2050bb;
        padding: 4px 12px; font-size: 12px; font-weight: bold;
        cursor: pointer; border-radius: 3px; font-family: Arial, sans-serif;
    }
    .rx-btn-blue:hover { background: #2050cc; }
    .rx-btn-gray {
        background: #e8edf8; color: #4169E1; border: 1px solid #4169E1;
        padding: 4px 12px; font-size: 12px; font-weight: bold;
        cursor: pointer; border-radius: 3px; font-family: Arial, sans-serif;
    }
    .rx-btn-gray:hover { background: #d0daf5; }

</style>

<form name="form_pdf_receta" id="form_pdf_receta" action="PDF_receta" method="POST" target="a_blank" >
    <input type="hidden" name="txt_duo" id="txt_duo_pdf" value="0" />
    <input type="hidden" name="txt_receta" id="txt_receta_pdf" value="0" />
</form>
<legend class="page-legend">Registro de Indicaciones</legend>
<fieldset><legend>Datos del Paciente</legend>

    <div id="nav">
        <form name="form_paciente_receta" id="form_paciente_receta" action="receta.jsp" method="POST">
            <h2>-Seleccione paciente-</h2>
            <select name="txt_duo" id="txt_duo" onchange="selecciona_cama()" style=" width: 180px ">
                <option value="0" >Seleccione...</option>
                <%    //
                    ArrayList lista_cama = null;
                    Iterator it_sala = null;

                    lista_cama = neg.lista_grilla_camas();
                    it_sala = lista_cama.iterator();

                    while (it_sala.hasNext()) {
                        cDuo aux = (cDuo) it_sala.next();
                        if (aux.getId_duo() != 0) {
                            out.write("<option value='" + aux.getId_duo() + "' >" + aux.getCama_descripcion() + "::" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + "</option>");
                        }
                    }
                %>
            </select>
            <br> <br>
        </form>

    </div>
    <div id="section">

   <%    if (obtiene_duo == 0) {
                out.write("<h3>No ha seleccionado paciente --> </h3>");
            } else {

                cDuo duo = neg.obtiene_duo(obtiene_duo);
                ArrayList lista_medicamento = neg.lista_medicamento();
                Iterator it_medicamento = lista_medicamento.iterator();
                Vector<cDato> via_medica = neg.searchViaAdmision();
                cReceta lastRecipe = neg.searchLastRecipe(obtiene_duo);
       %>


     <input type="hidden" name="txt_receta" id="txt_receta" value="0" />

        <table style="width:100%; border:1px solid #4169E1;"  >
            <tbody>
                <tr>
                    <td <%=titulo%> >Rut Paciente</td>
                    <td colspan="3" <%=datos%> ><% out.write("" + duo.getRut_paciente());%></td>

                    <td <%=titulo%> >Fecha Nacimiento</td>
                    <td colspan="2" <%=datos%> >   <% out.write("" + duo.getFecha_nac());%></td>
                </tr>
                <tr>
                    <td <%=titulo%> >Nombre Completo</td>
                    <td colspan="3" <%=datos%> ><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>

                    <td <%=titulo%> >Edad</td>
                    <td <%=datos%> ><% out.write("" + duo.getEdad());%></td>
                </tr>
                <tr>
                    <td <%=titulo%> >Cama</td>
                    <td colspan="3" <%=datos%> ><% out.write("" + duo.getCama_descripcion());%></td>

                    <td <%=titulo%> >Id Registro</td>
                    <td <%=datos%> ><% out.write("" + duo.getId_duo());%></td>
                </tr>
                <tr>

                    <td <%=titulo%>>D�as Hospitalizado</td>
                    <td <%=datos%>>  <%out.write("" + duo.getDias_cama());%>
                    </td>

                </tr>
            </tbody>
        </table>
        <table>
            <tr>
                <td>
                    <h3>Responsable: <%=session.getAttribute("usuario_nombre_completo")%></h3>
                </td>
            </tr>
        </table>
    </div>
</fieldset>
<form id="indica" name="indica" method="get" action="ingresa_receta" onsubmit="return validar()"  >
    <input  type="hidden" name="txt_duo_seleccionado" id="txt_duo_seleccionado" value="<%=duo.getId_duo()%>" />
    <input type="hidden" name="txt_paciente" id="txt_paciente" value="<%=duo.getRut_paciente()%>" />
    <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=obtiene_usuario%>" />
    <fieldset id="fieldset_receta" >
        <legend>Indicaciones</legend>
        <fieldset>
            <legend>Diagnostico:</legend>

            <textarea rows="5" name="txt_diagnostico" id="txt_diagnostico"   style=" width: 100% " ><%=diagnostico%></textarea>
        </fieldset>
        
        <fieldset>
            <legend>R�gimen:</legend>
            <textarea rows="5" name="txt_regimen" id="txt_regimen"   style=" width: 100% " ><%=lastRecipe.getRegimen() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Reposo:</legend>

            <textarea rows="5" name="txt_reposo" id="txt_reposo"   style=" width: 100% " ><%=lastRecipe.getReposo() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Control de signos vitales:</legend>

            <textarea rows="2" name="txt_control_signos" id="txt_control_signos"   style=" width: 100% " ><%=lastRecipe.getControl_signos() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Aislamiento:</legend>

            <textarea rows="2" name="txt_aislamiento" id="txt_aislamiento"   style=" width: 100% " ><%=lastRecipe.getAislamiento() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Alergias:</legend>

            <textarea rows="2" name="txt_alergias" id="txt_alergias"   style=" width: 100% " ><%=lastRecipe.getAlergias() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Contenci�n:</legend>

            <textarea rows="2" name="txt_contencion" id="txt_contencion"   style=" width: 100% " ><%=lastRecipe.getContencion() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Im�genes:</legend>

            <textarea rows="2" name="txt_imagenes" id="txt_imagenes"   style=" width: 100% " ><%=lastRecipe.getImagenes() %></textarea>
        </fieldset>
         <fieldset>
            <legend>Otros:</legend>

            <textarea rows="2" name="txt_otros" id="txt_otros"   style=" width: 100% " ><%=lastRecipe.getOtros() %></textarea>
        </fieldset>
        <fieldset>
            <table>
                <tr>
                    <td>
                           <legend>Indicaciones Enfermeria:</legend>
                         <textarea rows="2" name="txt_indicaciones_enfermeria" id="txt_indicaciones_enfermeria"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_enfermeria() %></textarea>
                    </td>
                    <td>
                           <legend>Indicaciones nutricionista:</legend>
                         <textarea rows="2" name="txt_indicaciones_nutricionista" id="txt_indicaciones_nutricionista"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_nutricionista() %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                           <legend>Indicaciones kinesi�logo:</legend>
                         <textarea rows="2" name="txt_indicaciones_kinesiologo" id="txt_indicaciones_kinesiologo"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_kinesiologo()%></textarea>
                    </td>
                    <td>
                           <legend>Indicaciones otros:</legend>
                         <textarea rows="2" name="txt_indicaciones_otros" id="txt_indicaciones_otros"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_otros() %></textarea>
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>Medicamentos </legend>
            <table border='0' cellpadding="5px"   >
                <tr>
                    <td>
                        <b>Lista de Medicamentos</b><br>
                        <select name="combobox" id="combobox"   >
                            <option value="-1"></option>
                            <% //
                                while (it_medicamento.hasNext()) {
                                    cInsumo ins = (cInsumo) it_medicamento.next();

                                    out.write("<option value='" + ins.getId_insumo() + "' >" + ins.getInsumo_desc() + "</option>");
                                }
                            %>
                        </select>
                        <input type="hidden" name="txt_medicamento" id="txt_medicamento" value="" />  
                    <td><b>Cantidad</b><br>
                        <input type="text" name="txt_cantidad" id="txt_cantidad" value="" onkeypress="return soloNumerosDecimales(event)" />
                        &nbsp;
                        <select name="cbo_medida" id="cbo_medida" >
                            <%
                                while (it_unidad_medida.hasNext()) {
                                    cUnidadMedida uni = (cUnidadMedida) it_unidad_medida.next();
                                    out.write("<option value='" + uni.getId_unidad_medida() + "'  >" + uni.getDescripcion() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td rowspan="4" >
                        <input class="btn btn-primary" type="button" value="Agregar medicamento" name="btn_grabar_receta" onclick="javascript: addRow1('myTable');"  />
                    </td>

                </tr>
                <tr>
                    <td><b>Frecuencia</b><br>
                        <input type="text" name="txt_frecuencia" id="txt_frecuencia" value="" />
                        Horas
                    </td>

                    <td id="td_duracion" >
                        <b>Duraci�n</b><br>
                        <input type="text" name="txt_duracion" id="txt_duracion" value="" />
                        D�as
                    </td>
                </tr>
                <tr>
                    <td><b>Via de Administracion</b><br>
                        &nbsp;
                        <select name="cbo_via_administracion" id="cbo_via_administracion" >
                            <%
                                for( cDato via : via_medica) {
                                    out.write("<option value='" + via.getId()+ "'  >" + via.getDescription() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td colspan="2" ><b>Indicaci�n Especial </b> <br>
                        <textarea rows="3" name="txt_indicacion" id="txt_indicacion" style=" width: 90% "  ></textarea>

                    </td> 
                </tr>
               

            </table>
        </fieldset>

        <fieldset>

            <table id="myTable" name="myTable" style=" width: 100% " class=" table table-striped" cellspacing="0" border="1">

                <tbody>

                </tbody>

            </table>

        </fieldset>

        <fieldset>
            <legend>Examenes a realizar</legend>

            &nbsp;
            <select name="cbo_examenes" id="cbo_examenes" >
                <option value="-1"></option>
                <%
                    while (it_examenes.hasNext()) {
                        cUnidadMedida uni = (cUnidadMedida) it_examenes.next();
                        out.write("<option value='" + uni.getId_unidad_medida() + "'  >" + uni.getDescripcion() + "</option>");
                    }
                %>
            </select>
            <input class="btn btn-primary" type="button" value="Agregar Examen " name="btn_grabar_receta" onclick="javascript: addRow2('myTablexa');"  />

        </fieldset>
        <table id="myTablexa" name="myTablexa" style=" width: 100% " class=" table table-striped" cellspacing="0" border="1">

            <tbody>

            </tbody>

        </table>

        <fieldset>
            <legend>Otras Indicaciones</legend>
            <textarea rows="5" name="txt_otrasindicaciones" id="txt_otrasindicaciones"   style=" width: 100% " ></textarea>
        </fieldset>


    </fieldset>  

    <fieldset>

        <input class="btn btn-primary" type="submit" value="Ingresar Indicaciones">

    </fieldset>


</form>

<%-- ===== MÓDULO DE PRESCRIPCIONES ===== --%>
<fieldset id="fs-presc">
    <legend>Prescripciones de Medicamentos</legend>

    <div style="text-align:right; margin-bottom:6px;">
        <a href="PDF_prescripcion?id_duo=<%=obtiene_duo%>" target="_blank" class="btn btn-default btn-sm">
            &#128438; PDF Prescripciones
        </a>
    </div>

    <div class="rx-tabs">
        <button class="rx-tab active" id="rx-tab-pm" onclick="rxShowTab('pm',this)">PRESCRIPCIONES</button>
        <button class="rx-tab" id="rx-tab-inf" onclick="rxShowTab('inf',this)">INFUSIONES</button>
        <button class="rx-tab" id="rx-tab-otra" onclick="rxShowTab('otra',this)">OTRAS PRESCRIPCIONES</button>
    </div>

    <%-- TAB: Prescripciones simples --%>
    <div id="rx-panel-pm" class="rx-panel">
        <div class="rx-section">
            <div class="rx-section-hdr">NUEVA PRESCRIPCI&Oacute;N</div>
            <div class="rx-section-body">
                <form action="gestion_prescripcion" method="post" onsubmit="return rxValidarPm();">
                    <input type="hidden" name="id_duo"            value="<%=obtiene_duo%>" />
                    <input type="hidden" name="action"            id="rx-pm-action"   value="agregar_pm" />
                    <input type="hidden" name="id_pm"             id="rx-pm-id"        value="0" />
                    <input type="hidden" name="medicamento_desc"  id="rx-pm-med-desc" />
                    <input type="hidden" name="origen"            value="receta" />
                    <table style="width:100%; border-collapse:collapse;">
                        <tr>
                            <th class="rx-col-hdr" style="width:32%;">Medicamento</th>
                            <th class="rx-col-hdr" style="width:8%;">Dosis</th>
                            <th class="rx-col-hdr" style="width:10%;">Unidad</th>
                            <th class="rx-col-hdr" style="width:14%;">V&iacute;a</th>
                            <th class="rx-col-hdr" style="width:16%;">Frecuencia</th>
                            <th class="rx-col-hdr" style="width:12%;">Observaci&oacute;n</th>
                            <th style="width:8%;"></th>
                        </tr>
                        <tr>
                            <td>
                                <select name="id_insumo" id="rx-pm-insumo" onchange="rxOnMedChangePm(this)" style="width:100%;font-size:11px;">
                                    <option value="">Seleccione...</option>
                                    <% for (Object objRxM : lista_meds) {
                                        cInsumo insRxM = (cInsumo) objRxM;
                                        String rxMdesc = insRxM.getInsumo_desc() != null ? insRxM.getInsumo_desc() : "";
                                        String rxMuni  = insRxM.getUnidad_medida_desc() != null ? insRxM.getUnidad_medida_desc() : "";
                                    %>
                                    <option value="<%=insRxM.getId_insumo()%>" data-desc="<%=rxMdesc.replace("\"","")%>" data-unidad="<%=rxMuni%>"><%=rxMdesc%></option>
                                    <% } %>
                                </select>
                            </td>
                            <td><input type="text" name="dosis" id="rx-pm-dosis" style="width:100%;font-size:11px;" /></td>
                            <td>
                                <select name="unidad_desc" id="rx-pm-unidad" style="width:100%;font-size:11px;">
                                    <option value="">--</option>
                                </select>
                            </td>
                            <td>
                                <select name="id_via" id="rx-pm-via" onchange="rxCapturarVia(this)" style="width:100%;font-size:11px;">
                                    <option value="0">--</option>
                                </select>
                                <input type="hidden" name="via_desc" id="rx-pm-via-desc" />
                            </td>
                            <td><input type="text" name="frecuencia" id="rx-pm-frecuencia" placeholder="ej: 8 hrs" style="width:100%;font-size:11px;" /></td>
                            <td><input type="text" name="observacion" id="rx-pm-obs" style="width:100%;font-size:11px;" /></td>
                            <td style="text-align:center;">
                                <button type="submit" class="rx-btn-blue" id="rx-pm-btn">Agregar</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <div class="rx-section" style="margin-top:8px;">
            <div class="rx-section-hdr">PRESCRIPCIONES REGISTRADAS</div>
            <div class="rx-section-body">
                <div class="rx-layout-row">
                    <div class="rx-layout-left">
                        <table class="rx-grid" id="rx-tbl-pm">
                            <tr>
                                <th>Medicamento</th>
                                <th style="width:65px;">Dosis</th>
                                <th style="width:70px;">Unidad</th>
                                <th style="width:60px;">V&iacute;a</th>
                                <th style="width:90px;">Frecuencia</th>
                                <th style="width:120px;">Observaci&oacute;n</th>
                            </tr>
                            <% int rxCntPm = 0;
                            for (Object objPmRx : lista_pm) {
                                cPrescripcion pmRx = (cPrescripcion) objPmRx;
                                if (pmRx.getId_insumo() == 0) continue;
                                rxCntPm++;
                                String rxdEsc  = pmRx.getMedicamento_desc().replace("'", "\\'").replace("\"", "");
                                String rxdDosis= pmRx.getDosis().replace("'", "\\'");
                                String rxdUni  = pmRx.getUnidad_desc().replace("'", "\\'");
                                String rxdVia  = pmRx.getVia_desc().replace("'", "\\'");
                                String rxdFrec = pmRx.getFrecuencia().replace("'", "\\'");
                                String rxdObs  = pmRx.getObservacion().replace("'", "\\'");
                            %>
                            <tr class="rx-selectable" onclick="rxSelectPm(this,<%=pmRx.getId_pm()%>,<%=pmRx.getId_insumo()%>,'<%=rxdEsc%>','<%=rxdDosis%>','<%=rxdUni%>',<%=pmRx.getId_via()%>,'<%=rxdVia%>','<%=rxdFrec%>','<%=rxdObs%>')">
                                <td><%=pmRx.getMedicamento_desc()%></td>
                                <td><%=pmRx.getDosis()%></td>
                                <td><%=pmRx.getUnidad_desc()%></td>
                                <td><%=pmRx.getVia_desc()%></td>
                                <td><%=pmRx.getFrecuencia()%></td>
                                <td><%=pmRx.getObservacion()%></td>
                            </tr>
                            <% } %>
                            <% if (rxCntPm == 0) { %>
                            <tr><td colspan="6" class="rx-empty-row">Sin prescripciones registradas</td></tr>
                            <% } %>
                        </table>
                    </div>
                    <div class="rx-side-btns">
                        <button class="rx-btn-blue" onclick="rxEditarPm()">Modificar</button>
                        <button class="rx-btn-blue" onclick="rxEliminarPm()">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    </div><%-- /rx-panel-pm --%>

    <%-- TAB: Infusiones --%>
    <div id="rx-panel-inf" class="rx-panel" style="display:none;">
        <div class="rx-section">
            <div class="rx-section-hdr">NUEVA INFUSI&Oacute;N</div>
            <div class="rx-section-body">
                <form action="gestion_prescripcion" method="post" id="rx-form-inf">
                    <input type="hidden" name="id_duo"   value="<%=obtiene_duo%>" />
                    <input type="hidden" name="action"   id="rx-inf-action"  value="agregar_inf" />
                    <input type="hidden" name="id_pi"    id="rx-inf-id-pi"   value="0" />
                    <input type="hidden" name="origen"   value="receta" />
                    <div class="rx-layout-row">
                        <div class="rx-layout-left">
                            <table style="width:100%; border-collapse:collapse;">
                                <thead>
                                    <tr>
                                        <th class="rx-col-hdr" style="width:22px;"></th>
                                        <th class="rx-col-hdr">Medicamento</th>
                                        <th class="rx-col-hdr" style="width:75px;">Dosis</th>
                                        <th class="rx-col-hdr" style="width:60px;">Unidad</th>
                                    </tr>
                                </thead>
                                <tbody id="rx-inf-rows"></tbody>
                            </table>
                            <div style="margin-top:5px; display:flex; gap:6px;">
                                <button type="button" class="rx-btn-gray" onclick="rxAddInfRow()">+ Medicamento</button>
                                <button type="button" class="rx-btn-gray" onclick="rxRemoveInfRow()">- Quitar</button>
                            </div>
                        </div>
                        <div class="rx-layout-right">
                            <div class="rx-section" style="margin-bottom:6px;">
                                <div class="rx-section-hdr">VEH&Iacute;CULO / SUERO</div>
                                <div class="rx-section-body" style="padding:5px;">
                                    <select name="suero_desc" id="rx-inf-suero" style="width:100%;font-size:11px;">
                                        <option>Cloruro de sodio 0,9% matraz 100 mL EV</option>
                                        <option>Cloruro de sodio 0,9% matraz 250 mL EV</option>
                                        <option>Cloruro de sodio 0,9% matraz 500 mL EV</option>
                                        <option>Suero glucosado 5% matraz 100 mL EV</option>
                                        <option>Suero glucosado 5% matraz 250 mL EV</option>
                                        <option>Suero glucosado 5% matraz 500 mL EV</option>
                                        <option>Suero glucosalino matraz 500 mL EV</option>
                                        <option>Ringer lactato matraz 500 mL EV</option>
                                        <option>Agua bidestilada 10 mL EV</option>
                                    </select>
                                </div>
                            </div>
                            <div class="rx-section" style="margin-bottom:6px;">
                                <div class="rx-section-hdr">ADMINISTRACI&Oacute;N</div>
                                <div class="rx-section-body" style="padding:5px;">
                                    <table style="width:100%;">
                                        <tr>
                                            <th class="rx-col-hdr">Velocidad infusi&oacute;n</th>
                                            <th class="rx-col-hdr">Observaciones</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="display:flex;align-items:center;gap:3px;">
                                                    <input type="text" name="velocidad_inf" id="rx-inf-vel" style="width:55px;font-size:11px;" />
                                                    <select name="unidad_velocidad" id="rx-inf-uvel" style="width:90px;font-size:11px;">
                                                        <option value="mL/hr">mL/hr</option>
                                                        <option value="gts/min">gts/min</option>
                                                        <option value="mcg/kg/min">mcg/kg/min</option>
                                                        <option value="UI/hr">UI/hr</option>
                                                    </select>
                                                </div>
                                            </td>
                                            <td><input type="text" name="obs_inf" id="rx-inf-obs" style="width:100%;font-size:11px;" /></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <button type="submit" class="rx-btn-blue" id="rx-inf-btn" style="width:100%;">Agregar Infusi&oacute;n</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="rx-section" style="margin-top:8px;">
            <div class="rx-section-hdr">INFUSIONES INGRESADAS</div>
            <div class="rx-section-body">
                <div class="rx-layout-row">
                    <div class="rx-layout-left">
                        <table class="rx-grid" id="rx-tbl-inf">
                            <tr>
                                <th>Medicamentos (diluidos)</th>
                                <th style="width:190px;">Veh&iacute;culo/Suero</th>
                                <th style="width:90px;">Velocidad</th>
                                <th style="width:120px;">Observaci&oacute;n</th>
                            </tr>
                            <% int rxCntInf = 0;
                            for (Object objInfRx : lista_inf) {
                                cInfusion infRx = (cInfusion) objInfRx;
                                rxCntInf++;
                            %>
                            <tr class="rx-selectable" onclick="rxSelectInf(this,<%=infRx.getId_pi()%>)">
                                <td><%=infRx.getMeds_display()%></td>
                                <td><%=infRx.getSuero_desc()%></td>
                                <td><%=infRx.getVelocidad_inf()%> <%=infRx.getUnidad_velocidad()%></td>
                                <td><%=infRx.getObservacion()%></td>
                            </tr>
                            <% } %>
                            <% if (rxCntInf == 0) { %>
                            <tr><td colspan="4" class="rx-empty-row">Sin infusiones ingresadas</td></tr>
                            <% } %>
                        </table>
                    </div>
                    <div class="rx-side-btns">
                        <button class="rx-btn-blue" onclick="rxEditarInf()">Modificar</button>
                        <button class="rx-btn-blue" onclick="rxEliminarInf()">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    </div><%-- /rx-panel-inf --%>

    <%-- TAB: Otras Prescripciones --%>
    <div id="rx-panel-otra" class="rx-panel" style="display:none;">
        <div class="rx-section">
            <div class="rx-section-hdr">NUEVA PRESCRIPCI&Oacute;N (NO ARSENAL)</div>
            <div class="rx-section-body">
                <form action="gestion_prescripcion" method="post" onsubmit="return rxValidarOtra();">
                    <input type="hidden" name="id_duo"          value="<%=obtiene_duo%>" />
                    <input type="hidden" name="action"          id="rx-otra-action"  value="agregar_pm" />
                    <input type="hidden" name="id_pm"           id="rx-otra-id"       value="0" />
                    <input type="hidden" name="id_insumo"       value="0" />
                    <input type="hidden" name="id_via"          value="0" />
                    <input type="hidden" name="origen"          value="receta" />
                    <table style="width:100%; border-collapse:collapse;">
                        <tr>
                            <th class="rx-col-hdr" style="width:28%;">Medicamento</th>
                            <th class="rx-col-hdr" style="width:8%;">Dosis</th>
                            <th class="rx-col-hdr" style="width:9%;">Unidad</th>
                            <th class="rx-col-hdr" style="width:10%;">V&iacute;a</th>
                            <th class="rx-col-hdr" style="width:16%;">Frecuencia</th>
                            <th class="rx-col-hdr" style="width:21%;">Observaci&oacute;n</th>
                            <th style="width:8%;"></th>
                        </tr>
                        <tr>
                            <td><input type="text" name="medicamento_desc" id="rx-otra-med" placeholder="Nombre del medicamento" style="width:100%;font-size:11px;" /></td>
                            <td><input type="text" name="dosis"           id="rx-otra-dosis" style="width:100%;font-size:11px;" /></td>
                            <td><input type="text" name="unidad_desc"     id="rx-otra-unidad" style="width:100%;font-size:11px;" /></td>
                            <td><input type="text" name="via_desc"        id="rx-otra-via"    style="width:100%;font-size:11px;" /></td>
                            <td><input type="text" name="frecuencia"      id="rx-otra-frecuencia" placeholder="ej: cada 8 hrs" style="width:100%;font-size:11px;" /></td>
                            <td><input type="text" name="observacion"     id="rx-otra-obs"    style="width:100%;font-size:11px;" /></td>
                            <td style="text-align:center;">
                                <button type="submit" class="rx-btn-blue" id="rx-otra-btn">Agregar</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <div class="rx-section" style="margin-top:8px;">
            <div class="rx-section-hdr">OTRAS PRESCRIPCIONES REGISTRADAS</div>
            <div class="rx-section-body">
                <div class="rx-layout-row">
                    <div class="rx-layout-left">
                        <table class="rx-grid" id="rx-tbl-otra">
                            <tr>
                                <th>Medicamento</th>
                                <th style="width:65px;">Dosis</th>
                                <th style="width:70px;">Unidad</th>
                                <th style="width:70px;">V&iacute;a</th>
                                <th style="width:90px;">Frecuencia</th>
                                <th style="width:120px;">Observaci&oacute;n</th>
                            </tr>
                            <% int rxCntOtra = 0;
                            for (Object objPmOtra : lista_pm) {
                                cPrescripcion pmOtra = (cPrescripcion) objPmOtra;
                                if (pmOtra.getId_insumo() != 0) continue;
                                rxCntOtra++;
                                String rxoEsc  = pmOtra.getMedicamento_desc().replace("'", "\\'").replace("\"","");
                                String rxoDosis= pmOtra.getDosis().replace("'", "\\'");
                                String rxoUni  = pmOtra.getUnidad_desc().replace("'", "\\'");
                                String rxoVia  = pmOtra.getVia_desc().replace("'", "\\'");
                                String rxoFrec = pmOtra.getFrecuencia().replace("'", "\\'");
                                String rxoObs  = pmOtra.getObservacion().replace("'", "\\'");
                            %>
                            <tr class="rx-selectable" onclick="rxSelectOtra(this,<%=pmOtra.getId_pm()%>,'<%=rxoEsc%>','<%=rxoDosis%>','<%=rxoUni%>','<%=rxoVia%>','<%=rxoFrec%>','<%=rxoObs%>')">
                                <td><%=pmOtra.getMedicamento_desc()%></td>
                                <td><%=pmOtra.getDosis()%></td>
                                <td><%=pmOtra.getUnidad_desc()%></td>
                                <td><%=pmOtra.getVia_desc()%></td>
                                <td><%=pmOtra.getFrecuencia()%></td>
                                <td><%=pmOtra.getObservacion()%></td>
                            </tr>
                            <% } %>
                            <% if (rxCntOtra == 0) { %>
                            <tr><td colspan="6" class="rx-empty-row">Sin otras prescripciones registradas</td></tr>
                            <% } %>
                        </table>
                    </div>
                    <div class="rx-side-btns">
                        <button class="rx-btn-blue" onclick="rxEditarOtra()">Modificar</button>
                        <button class="rx-btn-blue" onclick="rxEliminarOtra()">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    </div><%-- /rx-panel-otra --%>
</fieldset>

<%-- Formularios ocultos para eliminar prescripciones e infusiones --%>
<form id="rx-frm-del-pm" action="gestion_prescripcion" method="post">
    <input type="hidden" name="action"  value="eliminar_pm" />
    <input type="hidden" name="id_pm"   id="rx-del-pm-id" value="0" />
    <input type="hidden" name="id_duo"  value="<%=obtiene_duo%>" />
    <input type="hidden" name="origen"  value="receta" />
</form>
<form id="rx-frm-del-inf" action="gestion_prescripcion" method="post">
    <input type="hidden" name="action"  value="eliminar_inf" />
    <input type="hidden" name="id_pi"   id="rx-del-inf-id" value="0" />
    <input type="hidden" name="id_duo"  value="<%=obtiene_duo%>" />
    <input type="hidden" name="origen"  value="receta" />
</form>
<form id="rx-frm-del-otra" action="gestion_prescripcion" method="post">
    <input type="hidden" name="action"  value="eliminar_pm" />
    <input type="hidden" name="id_pm"   id="rx-del-otra-id" value="0" />
    <input type="hidden" name="id_duo"  value="<%=obtiene_duo%>" />
    <input type="hidden" name="origen"  value="receta" />
</form>

<fieldset style=" height:800" ><legend>Indicaciones</legend>

    <%
        ArrayList lista_indicaciones = neg.bucarindicacionesporduo(obtiene_duo);
        String guarda_fecha = "";
        Iterator it_ses = lista_indicaciones.iterator();

        if (lista_indicaciones.isEmpty()) {

    %>
    <table>
        <tr>
            <td>
                No se encontraron registros  
            </td>
        </tr>
    </table>



    <% } else {
        /**/
    %>

    <% while (it_ses.hasNext()) {
            cReceta vis = (cReceta) it_ses.next();
    %>
    <table style='width: 100%;  border:solid 0.05px #337ab7 ' >

        <%
            if (!vis.getFecha().equals(guarda_fecha)) {

                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yyyy");

        %>
        <tr>
            <th colspan="4">
                Dia : <%=vis.getFecha()%>  
            </th>
        </tr>
        <%

            }
        %>
        <tr>
            <td style="width: 10%"><%=vis.getHgt()%></td>
            <td style="width: 100%">
                 <fieldset><legend>Diagnostico:</legend>
                    <%=vis.getDiagnostico() %>
                </fieldset> 
                <fieldset><legend>Reposo:</legend>
                    <%=vis.getReposo()%>
                </fieldset> 
                <fieldset><legend>Regimen: </legend>
                    <%=vis.getRegimen()%>
                </fieldset> 
                <fieldset> <legend>Control de signos vitales:</legend>
                <%=vis.getControl_signos() %>
                </fieldset>
                <fieldset> <legend>Aislamiento:</legend>
                <%=vis.getAislamiento() %>
                </fieldset>
                <fieldset><legend>Alergias:</legend>
                 <%=vis.getAlergias() %>
                </fieldset>
                <fieldset><legend>Contenci�n:</legend>
                <%=vis.getContencion() %>
                </fieldset>
                <fieldset><legend>Im�genes:</legend>
                <%=vis.getImagenes() %>    
                </fieldset>
                <fieldset><legend>Otros:</legend>
                </fieldset>
                <fieldset>
                    <table>
                        <tr>
                            <td>
                                   <legend>Indicaciones Enfermeria:</legend> 
                                   <%=vis.getIndicaciones_enfermeria() %>
                            </td>
                            <td>
                                   <legend>Indicaciones nutricionista:</legend>
                                   <%=vis.getIndicaciones_nutricionista() %>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                   <legend>Indicaciones kinesi�logo:</legend>
                                   <%=vis.getIndicaciones_kinesiologo() %>
                            </td>
                            <td>
                                   <legend>Indicaciones otros:</legend>
                                   <%=vis.getIndicaciones_otros() %>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset>
                    <legend> Medicamentos:</legend>
                    <% Vector<String> recetas = neg.buscarRecetadeIndicaciones(vis.getId_duo());
                        String menas = "";
                        boolean tengoreceta = false;
                        for (int i = 0; i < recetas.size(); i++) {
                            menas = menas + recetas.get(i) + ".  \n";
                            tengoreceta = true;
                        }

                    %>
                    <%=menas.toLowerCase()%>
                </fieldset> 
                <fieldset>
                    <legend> Solicitud de Examenes:</legend>
                    <% Vector<String> examenes = neg.buscarSolicitudExamenesdeIndicaciones(vis.getId_duo());
                        String exam = "";
                        boolean tengoexamenes = false;
                        for (int i = 0; i < examenes.size(); i++) {
                            exam = exam + examenes.get(i) + " , \n";
                            tengoexamenes = true;
                        }

                    %>
                    <%=exam.toLowerCase()%>
                </fieldset> 

                <fieldset><legend>Otras Indicaciones: </legend>
                    <%=vis.getIndicacion()%>
                </fieldset> 





            </td>
            <td style="width: 30%"><%=vis.getNombre_usuario()%></td>

            <td>Indicaciones
                <form name="form_formulario<%=vis.getId_duo()%>" id="form_formulario<%=vis.getId_duo()%>" action="PDF_Indicaciones" method="POST"  target="a_blank" >
                    <input type="hidden" name="id_ind" value="<%=vis.getId_duo()%>"   >
                    <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_formulario<%=vis.getId_duo()%>').submit()" />
                </form>
            </td>

            <% if (tengoreceta == true) {%>
            <td>Receta
                <form name="form_receta<%=vis.getId_duo()%>" id="form_receta<%=vis.getId_duo()%>" action="RecetaMedica" method="POST"  target="a_blank" >
                    <input type="hidden" name="id_ind" value="<%=vis.getId_duo()%>"   >
                    <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_receta<%=vis.getId_duo()%>').submit()" />
                </form>
            </td>
            <%}%>

            <% if (tengoexamenes == true) {%>
            <td>Solicitud Examenes
                <form name="form_examen<%=vis.getId_duo()%>" id="form_examen<%=vis.getId_duo()%>" action="SolicitudExamenes" method="POST"  target="a_blank" >
                    <input type="hidden" name="id_ind" value="<%=vis.getId_duo()%>"   >
                    <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_examen<%=vis.getId_duo()%>').submit()" />
                </form>
            </td>
            <%}%>

        </tr>
        </tr>

        <%
                    guarda_fecha = vis.getFecha();
                }
                
            } %>




    </table>

</fieldset>


<% }%>


<script>

    function soloNumeros(evt) {
        //asignamos el valor de la tecla a keynum
        if (window.event) {// IE
            keynum = evt.keyCode;
        } else {
            keynum = evt.which;
        }
        //
        if ((keynum > 45 && keynum < 58) || keynum == 8 || keynum == 13 || keynum == 9) {
            //numeros || delete || enter || tab
            return true;
        } else {
            return false;
        }
    }

    function soloNumerosDecimales(evt) {
        //asignamos el valor de la tecla a keynum
        if (window.event) {// IE
            keynum = evt.keyCode;
        } else {
            keynum = evt.which;
        }
        //
        if ((keynum > 45 && keynum < 58) || keynum == 8 || keynum == 13 || keynum == 9 || keynum == 0) {
            //numeros || delete || enter || tab

            return true;
        } else {
            return false;
        }
    }

    function selecciona_cama() {
        if ($("#cbo_cama").val() != 0) {
            document.getElementById('form_paciente_receta').submit();
        }

    }

    function validar() {
        /*validar usuario*/
        var perfil = <%=obtiene_perfil%>;
        if (perfil == 1 || perfil == 6 || perfil == 8) {

            var txt_reposo = $("#txt_reposo").val();
            var txt_regimen = $("#txt_regimen").val();
            var txt_otrasindicaciones = $("#txt_otrasindicaciones").val();
            if (txt_reposo == "" || txt_regimen == "" ) {
                alert('Debe ingresar el reposo y el regimen');
                return false;
            } else {
                document.getElementById("indica").submit();
            }
        } else {
            alert('No contiene los privilegios necesarios!..');
            return false;
        }

    }

    function ver_pdf(duo, receta) {
        //alert('generando');
        $("#txt_duo_pdf").val(duo);
        $("#txt_receta_pdf").val(receta);
        document.getElementById('form_pdf_receta').submit();
    }


    function borrarFila(t) {
        var td = t.parentNode;
        var tr = td.parentNode;
        var table = tr.parentNode;
        table.removeChild(tr);
    }


    function addRow1(id) {

        if ($("#combobox").val() != -1) {
            $("#txt_medicamento").val($("#combobox").val());
        }

        var medicamento = $("#txt_medicamento").val().replace("'", "").toUpperCase();

        var combo3 = document.getElementById("cbo_medida");
        var selected3 = combo3.options[combo3.selectedIndex].text;
        var combo4 = document.getElementById("combobox");
        var selected4 = combo4.options[combo4.selectedIndex].text;
        var cantidad = $("#txt_cantidad").val();
        var frecuencia = $("#txt_frecuencia").val();
        var duracion = $("#txt_duracion").val();
        var indicacion = $("#txt_indicacion").val();
        var via =document.getElementById("cbo_via_administracion");
        var via_medicina= via.options[via.selectedIndex].text;
        var medida = $("#cbo_medida").val();
        var id_via = $("#cbo_via_administracion").val();
        var idmedicamento = $("#combobox").val();
        if (medicamento.length == 0 || idmedicamento == -1) {
            alert('Debe ingresar el medicamento');
            return false;
        }
        if (cantidad.length == 0) {
            $("#txt_cantidad").focus();
            alert('Debe ingresar la cantidad');
            return false;
        }

        if (isNaN(cantidad)) {
            $("#txt_cantidad").focus();
            alert('Debe ingresar la cantidad v�lida');
            return false;
        }

        if (frecuencia.length == 0) {
            $("#txt_frecuencia").focus();
            alert('Debe ingresar la frecuencia');
            return false;
        }
        if (duracion.length == 0) {
            $("#txt_duracion").focus();
            alert('Debe ingresar la duraci�n');
            return false;
        } else {
            var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
            var row = document.createElement("TR");
            row.setAttribute('style', ' text-align: center');
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode( selected4 + "  "+cantidad + "  " + selected3 + " cada  " + frecuencia + " horas durante " + duracion + " dias , via "+via_medicina+" \n" + indicacion));
            row.appendChild(td1);
            var td2 = document.createElement("TD");
            td2.style = "width: 10px";
            var boton = document.createElement('button');
            boton.type = "button";
            boton.setAttribute('class', 'button2');
            boton.innerHTML = "x";
            boton.onclick = function () {
                var td = this.parentNode;
                var tr = td.parentNode;
                var table = tr.parentNode;
                table.removeChild(tr);
                return false;
            };
            td2.appendChild(boton);
            row.appendChild(td2);
            /*los id*/

            var td = document.createElement("TD");
            td.style = "width: 1px";
            var div = document.createElement("input");
            div.id = "medicamento" + idmedicamento;
            div.name = "medicamento" + idmedicamento;
            div.value = idmedicamento;
            div.type = "hidden";
            td.appendChild(div);
            var div2 = document.createElement("input");
            div2.id = "cantidad" + idmedicamento;
            div2.name = "cantidad" + idmedicamento;
            div2.value = cantidad;
            div2.type = "hidden";
            td.appendChild(div2);
            var div3 = document.createElement("input");
            div3.id = "medida" + idmedicamento;
            div3.name = "medida" + idmedicamento;
            div3.value = medida;
            div3.type = "hidden";
            td.appendChild(div3);
            var div4 = document.createElement("input");
            div4.id = "frecuencia" + idmedicamento;
            div4.name = "frecuencia" + idmedicamento;
            div4.value = frecuencia;
            div4.type = "hidden";
            td.appendChild(div4);
            var div5 = document.createElement("input");
            div5.id = "duracion" + idmedicamento;
            div5.name = "duracion" + idmedicamento;
            div5.value = duracion;
            div5.type = "hidden";
            td.appendChild(div5);
            var div6 = document.createElement("input");
            div6.id = "indicaciones" + idmedicamento;
            div6.name = "indicaciones" + idmedicamento;
            div6.value = indicacion;
            div6.type = "hidden";
            td.appendChild(div6);
             var div7 = document.createElement("input");
            div7.id = "via" + idmedicamento;
            div7.name = "via" + idmedicamento;
            div7.value = id_via;
            div7.type = "hidden";
            td.appendChild(div7);
            row.appendChild(td);
            tbody.appendChild(row);
            limpiar();
        }

    }


    function addRow2(id) {

        var combo3 = document.getElementById("cbo_examenes");
        var selected3 = combo3.options[combo3.selectedIndex].text;

        var idmedicamento = $("#cbo_examenes").val();
        if (idmedicamento == -1) {
            alert('Debe ingresar el examen');
            return false;

        } else {
            var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
            var row = document.createElement("TR");
            row.setAttribute('style', ' text-align: center');
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode(selected3));
            row.appendChild(td1);
            var td2 = document.createElement("TD");
            td2.style = "width: 10px";
            var boton = document.createElement('button');
            boton.type = "button";
            boton.setAttribute('class', 'button2');
            boton.innerHTML = "x";


            boton.onclick = function () {
                var td = this.parentNode;
                var tr = td.parentNode;
                var table = tr.parentNode;
                table.removeChild(tr);
                return false;
            };
            td2.appendChild(boton);
            row.appendChild(td2);
            /*los id*/

            var td = document.createElement("TD");
            td.style = "width: 1px";
            var div = document.createElement("input");
            div.id = "examenen" + idmedicamento;
            div.name = "examenen" + idmedicamento;
            div.value = idmedicamento;
            div.type = "hidden";
            td.appendChild(div);
            row.appendChild(td);
            tbody.appendChild(row);
            document.getElementById("cbo_examenes").value = -1;

        }

    }


    function limpiar() {
        document.getElementById("txt_cantidad").value = "";
        document.getElementById("txt_frecuencia").value = "";
        document.getElementById("txt_duracion").value = "";
        document.getElementById("txt_indicacion").value = "";
        document.getElementById("cbo_medida").value = 1;
        document.getElementById("combobox").value = -1;

    }


    (function ($) {
        $.widget("custom.combobox", {
            _create: function () {
                this.wrapper = $("<span>")
                        .addClass("custom-combobox")
                        .insertAfter(this.element);
                this.element.hide();
                this._createAutocomplete();
            },
            _createAutocomplete: function () {
                var selected = this.element.children(":selected"),
                        value = selected.val() ? selected.text() : "";
                this.input = $("<input  style='width: 600px' onfocus='this.select();'  >")
                        .appendTo(this.wrapper)
                        .val(value)
                        .attr("title", "")
                        .addClass("custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
                        .autocomplete({
                            delay: 0,
                            minLength: 3,
                            source: $.proxy(this, "_source")
                        })
                        .tooltip({
                            tooltipClass: "ui-state-highlight"
                        });
                this._on(this.input, {
                    autocompleteselect: function (event, ui) {
                        ui.item.option.selected = true;
                        this._trigger("select", event, {
                            item: ui.item.option
                        });
                    },
                    autocompletechange: "_removeIfInvalid"
                });
            },
            _source: function (request, response) {
                var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
                response(this.element.children("option").map(function () {
                    var text = $(this).text();
                    if (this.value && (!request.term || matcher.test(text)))
                        return {
                            label: text,
                            value: text,
                            option: this
                        };
                }));
            },
            _removeIfInvalid: function (event, ui) {
// Selected an item, nothing to do
                if (ui.item) {
                    return;
                }
// Search for a match (case-insensitive)
                var value = this.input.val(),
                        valueLowerCase = value.toLowerCase(),
                        valid = false;
                $("#txt_medicamento").val(value);
                $("#combobox").val(-1);
                this.element.children("option").each(function () {
                    if ($(this).text().toLowerCase() === valueLowerCase) {
                        this.selected = valid = true;
                        return false;
                    }
                });
// Found a match, nothing to do
                if (valid) {

                    return;
                }
// Remove invalid value

            },
            _destroy: function () {
                this.wrapper.remove();
                this.element.show();
            }
        });
    })(jQuery);
    $(function () {
        $("#combobox").combobox();
        $("#toggle").click(function () {
            $("#combobox").toggle();
        });
    });


</script>

<script>
// ===== RX MODULE DATA =====
var _rxInfData = {};
<%
for (Object oRxInf : lista_inf) {
    cInfusion rxInf2 = (cInfusion) oRxInf;
    String rxSuero2 = rxInf2.getSuero_desc() != null ? rxInf2.getSuero_desc().replace("\\","\\\\").replace("\"","\\\"") : "";
    String rxObs2   = rxInf2.getObservacion() != null ? rxInf2.getObservacion().replace("\\","\\\\").replace("\"","\\\"") : "";
    String rxUVel2  = rxInf2.getUnidad_velocidad() != null ? rxInf2.getUnidad_velocidad() : "mL/hr";
    StringBuilder rxDetSb = new StringBuilder("[");
    boolean rxFd = true;
    for (Object rxDObj : rxInf2.getDetalles()) {
        cInfusionDetalle rxDet = (cInfusionDetalle) rxDObj;
        if (!rxFd) rxDetSb.append(",");
        String rxDDesc = rxDet.getMedicamento_desc() != null ? rxDet.getMedicamento_desc().replace("\\","\\\\").replace("\"","\\\"") : "";
        String rxDDos  = rxDet.getDosis() != null ? rxDet.getDosis() : "";
        String rxDUni  = rxDet.getUnidad_desc() != null ? rxDet.getUnidad_desc() : "";
        rxDetSb.append("{\"id\":").append(rxDet.getId_insumo())
               .append(",\"desc\":\"").append(rxDDesc).append("\"")
               .append(",\"dosis\":\"").append(rxDDos).append("\"")
               .append(",\"unidad\":\"").append(rxDUni).append("\"}");
        rxFd = false;
    }
    rxDetSb.append("]");
%>
_rxInfData[<%=rxInf2.getId_pi()%>]={suero:"<%=rxSuero2%>",vel:<%=rxInf2.getVelocidad_inf()%>,uVel:"<%=rxUVel2%>",obs:"<%=rxObs2%>",detalles:<%=rxDetSb.toString()%>};
<% } %>

var _rxMeds = [
<%
boolean rxFm = true;
for (Object objRxM3 : lista_meds) {
    cInsumo rxMed3 = (cInsumo) objRxM3;
    if (!rxFm) out.print(",");
    String rxD3 = rxMed3.getInsumo_desc() != null ? rxMed3.getInsumo_desc().replace("\"","\\\"").replace("'","\\'") : "";
    String rxU3 = rxMed3.getUnidad_medida_desc() != null ? rxMed3.getUnidad_medida_desc() : "";
    out.print("{\"id\":" + rxMed3.getId_insumo() + ",\"desc\":\"" + rxD3 + "\",\"unidad\":\"" + rxU3 + "\"}");
    rxFm = false;
}
%>
];

var _rxUnits = [
<%
boolean rxFu = true;
for (Object objRxU3 : lista_unidad_medida) {
    cUnidadMedida rxUMed3 = (cUnidadMedida) objRxU3;
    if (!rxFu) out.print(",");
    String rxUAbr3  = rxUMed3.getAbreviacion()  != null ? rxUMed3.getAbreviacion().replace("\"","\\\"")  : "";
    String rxUDesc3 = rxUMed3.getDescripcion()  != null ? rxUMed3.getDescripcion().replace("\"","\\\"")  : "";
    out.print("{\"abr\":\"" + rxUAbr3 + "\",\"desc\":\"" + rxUDesc3 + "\"}");
    rxFu = false;
}
%>
];

// ===== ARSENAL LOOKUP =====
function rxNorm(s) {
    return (s||'').toLowerCase()
        .replace(/[áàäâ]/g,'a').replace(/[éèëê]/g,'e').replace(/[íìïî]/g,'i')
        .replace(/[óòöô]/g,'o').replace(/[úùüû]/g,'u').replace(/[ñ]/g,'n')
        .replace(/[çć]/g,'c').replace(/\s+/g,' ').trim();
}
var _rxArsenal = {
  "acenocumarol 4 mg":{via:["VO","SNG"],u:["mg"]},
  "acetazolamida 250 mg":{via:["VO","SNG"],u:["mg"]},
  "aciclovir 400 mg":{via:["VO","SNG"],u:["mg"]},
  "aciclovir 3% unguento oftalmico":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["aplicación"]},
  "acido acetilsalicilico 100 mg":{via:["VO","SNG"],u:["mg"]},
  "acido folico 5 mg":{via:["VO","SNG"],u:["mg"]},
  "acido tranexamico 1000 mg / 10 ml":{via:["EV"],u:["mg"]},
  "adenosina 6 mg / 2 ml":{via:["EV"],u:["mg"]},
  "agua bidestilada 10 ml":{via:["EV"],u:["mL"]},
  "agua bidestilada matraz 500 ml":{via:["EV"],u:["mL"]},
  "amikacina 500 mg / 2 ml":{via:["EV"],u:["mg"]},
  "amlodipino 10 mg":{via:["VO","SNG"],u:["mg"]},
  "amoxicilina + ac. clavulanico 875 mg + 125 mg":{via:["VO","SNG"],u:["comprimido"]},
  "amoxicilina + ac. clavulanico 500 mg + 125 mg":{via:["VO","SNG"],u:["comprimido"]},
  "amoxicilina 500 mg":{via:["VO","SNG"],u:["mg"]},
  "amoxicilina 500 mg / 5 ml jarabe":{via:["VO","SNG"],u:["mL"]},
  "ampicilina + sulbactam 1,5 gr":{via:["EV"],u:["gr"]},
  "atenolol 50 mg":{via:["VO","SNG"],u:["mg"]},
  "atorvastatina 20 mg":{via:["VO","SNG"],u:["mg"]},
  "atropina 1 mg / 1 ml":{via:["EV"],u:["mg"]},
  "atropina 1% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "azitromicina 500 mg":{via:["VO","SNG"],u:["mg"]},
  "betametasona 4 mg / 1 ml":{via:["EV"],u:["mg"]},
  "betaxolol 0,5% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "bisoprolol 5 mg":{via:["VO","SNG"],u:["mg"]},
  "brimonidina 0,2% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "budesonida 200 mcg / dosis inhalador":{via:["inhalatorio"],u:["puff"]},
  "bupivacaina 50 mg / 10 ml":{via:["EV"],u:["mg"]},
  "calcio + vitamina d 500 mg + 400 ui":{via:["VO","SNG"],u:["comprimido"]},
  "calcio gluconato 10%":{via:["EV"],u:["ampolla"]},
  "captopril 25 mg":{via:["VO","SNG"],u:["mg"]},
  "carvedilol 12,5 mg":{via:["VO","SNG"],u:["mg"]},
  "carvedilol 6,25 mg":{via:["VO","SNG"],u:["mg"]},
  "cefadroxilo 500 mg":{via:["VO","SNG"],u:["mg"]},
  "cefazolina 1 gr":{via:["EV"],u:["gr"]},
  "ceftazidima 1 gr":{via:["EV"],u:["gr"]},
  "ceftriaxona 1 gr":{via:["EV"],u:["gr"]},
  "cefuroxima 50 mg":{via:["EV"],u:["mg"]},
  "celecoxib 200 mg":{via:["VO","SNG"],u:["mg"]},
  "ciclobenzaprina 10 mg":{via:["VO","SNG"],u:["mg"]},
  "ciclopentolato 1% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "ciprofloxacino 500 mg":{via:["VO","SNG"],u:["mg"]},
  "ciprofloxacino 200 mg/100 ml":{via:["EV"],u:["mg"]},
  "clindamicina 600 mg / 4 ml":{via:["EV"],u:["mg"]},
  "clonazepam 0,5 mg":{via:["VO","SNG"],u:["mg"]},
  "clonixinato de lisina 125 mg":{via:["VO","SNG"],u:["mg"]},
  "clonixinato de lisina 100 mg / 2 ml":{via:["EV"],u:["mg"]},
  "clopidogrel 75 mg":{via:["VO","SNG"],u:["mg"]},
  "cloranfenicol 0,5% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "cloranfenicol 1% unguento oftalmico":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["aplicación"]},
  "clorfenamina 4 mg":{via:["VO","SNG"],u:["mg"]},
  "clorfenamina 10 mg / ml":{via:["EV"],u:["mg"]},
  "clorhexidina gluconato 0,12% colutorio":{via:["VO","SNG"],u:["mL"]},
  "clorpromazina 25 mg / 2 ml":{via:["EV"],u:["mg"]},
  "clotrimazol 1% crema":{via:["tópica"],u:["aplicación"]},
  "complejo vitaminico b":{via:["EV"],u:["ampolla"]},
  "dexametasona 4 mg / 1 ml":{via:["EV"],u:["mg"]},
  "diclofenaco 50 mg":{via:["VO","SNG"],u:["mg"]},
  "domperidona 10 mg":{via:["VO","SNG"],u:["mg"]},
  "domperidona 10 mg / 2 ml":{via:["EV"],u:["mg"]},
  "dorzolamida 2% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "droperidol 5 mg / 2 ml":{via:["EV"],u:["mg"]},
  "efedrina 60 mg / 1 ml":{via:["EV"],u:["mg"]},
  "enalapril 10 mg":{via:["VO","SNG"],u:["mg"]},
  "enoxaparina 40 mg":{via:["SC"],u:["mg"]},
  "enoxaparina 60 mg":{via:["SC"],u:["mg"]},
  "enoxaparina 80 mg":{via:["SC"],u:["mg"]},
  "epinefrina 1 mg / 1 ml":{via:["EV"],u:["mg"]},
  "ertapenem 1 gr":{via:["EV"],u:["gr","mg"]},
  "espironolactona 25 mg":{via:["VO","SNG"],u:["mg"]},
  "fenilefrina 10 mg / 1 ml":{via:["EV"],u:["mg"]},
  "fenilefrina 2,5% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "fenoterol":{via:["inhalatorio","nebulización"],u:["puff","mL"]},
  "fentanilo 0,1 mg / 2 ml":{via:["EV"],u:["mg"]},
  "flumazenil 0,5 mg / 5 ml":{via:["EV"],u:["mg"]},
  "fluticasona + salmeterol":{via:["inhalatorio"],u:["puff"]},
  "fosfato de sodio enema":{via:["rectal"],u:["aplicación"]},
  "furosemida 40 mg":{via:["VO","SNG"],u:["mg"]},
  "furosemida 20 mg / 1 ml":{via:["EV"],u:["mg"]},
  "gentamicina 80 mg / 2 ml":{via:["EV"],u:["mg"]},
  "glucosa 10% matraz 500 ml":{via:["EV"],u:["mL"]},
  "glucosa 2,5%":{via:["EV"],u:["mL"]},
  "glucosa 5% matraz 250 ml":{via:["EV"],u:["mL"]},
  "glucosa hipertonica 30%":{via:["EV"],u:["ampolla"]},
  "haloperidol 5 mg / 1 ml":{via:["EV"],u:["mg"]},
  "heparina sodica":{via:["SC"],u:["UI"]},
  "hidralazina 50 mg":{via:["VO","SNG"],u:["mg"]},
  "hidroclorotiazida 50 mg":{via:["VO","SNG"],u:["mg"]},
  "hidrocortisona":{via:["EV"],u:["mg"]},
  "sulfato ferroso 200 mg":{via:["VO","SNG"],u:["mg"]},
  "ibuprofeno 400 mg":{via:["VO","SNG"],u:["mg"]},
  "ibuprofeno 200 mg / 5 ml jarabe":{via:["VO","SNG"],u:["mL"]},
  "imipenem + cilastatina":{via:["EV"],u:["mg"]},
  "insulina cristalina":{via:["SC"],u:["UI"]},
  "insulina nph":{via:["SC"],u:["UI"]},
  "bromuro de ipratropio 20 mcg":{via:["inhalatorio"],u:["puff"]},
  "bromuro de ipratropio 0,25 mg":{via:["nebulización"],u:["mL"]},
  "isosorbida 10 mg":{via:["VO","SNG"],u:["mg"]},
  "ketoprofeno 100 mg":{via:["VO","SNG"],u:["mg"]},
  "ketoprofeno 100 mg / 2 ml":{via:["EV"],u:["mg"]},
  "ketorolaco 30 mg / 1 ml":{via:["EV"],u:["mg"]},
  "labetalol 100 mg / 20 ml":{via:["EV"],u:["mg"]},
  "lactulosa 65% jarabe":{via:["VO","SNG"],u:["mL"]},
  "lagrimas artificiales colirio":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "lagrimas artificiales unguento":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["aplicación"]},
  "lanatosido c 0,4 mg / 2 ml":{via:["EV"],u:["mg"]},
  "lansoprazol 30 mg":{via:["VO","SNG"],u:["mg"]},
  "latanoprost":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "levofloxacino 500 mg":{via:["VO","SNG"],u:["mg"]},
  "levotiroxina 100 mcg":{via:["VO","SNG"],u:["mcg"]},
  "levotiroxina 50 mcg":{via:["VO","SNG"],u:["mcg"]},
  "lidocaina 2%":{via:["EV"],u:["mg"]},
  "loratadina 10 mg":{via:["VO","SNG"],u:["mg"]},
  "lorazepam 4 mg / 2 ml":{via:["EV"],u:["mg"]},
  "losartan 50 mg":{via:["VO","SNG"],u:["mg"]},
  "magnesio sulfato 25%":{via:["EV"],u:["ampolla"]},
  "manitol 15%":{via:["EV"],u:["mL"]},
  "meropenem 500 mg":{via:["EV"],u:["mg"]},
  "metamizol sodico 1 gr / 2 ml":{via:["EV"],u:["gr"]},
  "metformina 850 mg":{via:["VO","SNG"],u:["mg"]},
  "metronidazol 500 mg":{via:["VO","SNG"],u:["mg"]},
  "metronidazol 500 mg / 100 ml":{via:["EV"],u:["mg"]},
  "miconazol gel bucal":{via:["VO"],u:["aplicación"]},
  "midazolam 5 mg / 1 ml":{via:["EV"],u:["mg"]},
  "morfina 10 mg / 1 ml":{via:["EV","SC"],u:["mg"]},
  "moxifloxacino 400 mg":{via:["VO","SNG"],u:["mg"]},
  "moxifloxacino 0,5% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "naloxona 0,4 mg / ml":{via:["EV"],u:["mg"]},
  "neostigmina 0,5 mg / ml":{via:["EV"],u:["mg"]},
  "nitroglicerina 50 mg / 10 ml":{via:["EV"],u:["mg"]},
  "omeprazol 20 mg":{via:["VO","SNG"],u:["mg"]},
  "omeprazol 40 mg":{via:["EV"],u:["mg"]},
  "ondansetron 4 mg / 2 ml":{via:["EV"],u:["mg"]},
  "oximetazolina 0,05%":{via:["nasal"],u:["aplicación"]},
  "paracetamol 500 mg":{via:["VO","SNG"],u:["gr","mg"]},
  "paracetamol 120 mg / 5 ml jarabe":{via:["VO","SNG"],u:["mL"]},
  "paracetamol 1 gr / 100 ml":{via:["EV"],u:["gr"]},
  "pargeverina 5 mg / 1 ml":{via:["EV"],u:["mg"]},
  "pilocarpina 2% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "piperacilina + tazobactam 4,5 gr":{via:["EV"],u:["gr"]},
  "polietilenglicol":{via:["VO","SNG"],u:["sobre"]},
  "potasio comp":{via:["VO","SNG"],u:["mg"]},
  "potasio cloruro 10%":{via:["EV"],u:["ampolla"]},
  "potasio gluconato 31,2%":{via:["VO","SNG"],u:["mL"]},
  "povidona yodada 5% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "prednisolona acetato 1% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "prednisona 20 mg":{via:["VO","SNG"],u:["mg"]},
  "pregabalina 75 mg":{via:["VO","SNG"],u:["mg"]},
  "propanolol 1 mg / 1 ml":{via:["VO","SNG"],u:["mg"]},
  "proparacaina":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "quetiapina 25 mg":{via:["VO","SNG"],u:["mg"]},
  "ranitidina 50 mg / 2 ml":{via:["EV"],u:["mg"]},
  "ringer lactato":{via:["EV"],u:["mL"]},
  "risperidona gotas 1 mg / ml":{via:["VO","SNG"],u:["gota"]},
  "rivaroxaban 10 mg":{via:["VO","SNG"],u:["mg"]},
  "rivaroxaban 15 mg":{via:["VO","SNG"],u:["mg"]},
  "rocuronio 50 mg / 5 ml":{via:["EV"],u:["mg"]},
  "saccharomyces boulardii 250 mg":{via:["VO","SNG"],u:["mg","sobre"]},
  "salbutamol 100 mcg":{via:["inhalatorio"],u:["puff"]},
  "salbutamol 0,5%":{via:["nebulización"],u:["mL"]},
  "sertralina 50 mg":{via:["VO","SNG"],u:["mg"]},
  "bicarbonato de sodio 8,4%":{via:["EV"],u:["ampolla"]},
  "cloruro de sodio 0,9% matraz 3000":{via:["intravesical"],u:["mL"]},
  "cloruro de sodio 0,9% matraz 1":{via:["EV"],u:["mL"]},
  "cloruro de sodio 0,9% matraz 100":{via:["EV"],u:["mL"]},
  "cloruro de sodio 0,9% matraz 250":{via:["EV"],u:["mL"]},
  "cloruro de sodio 0,9% matraz 500":{via:["EV"],u:["mL"]},
  "cloruro de sodio 10% ampolla 10 ml":{via:["EV"],u:["mL"]},
  "cloruro de sodio 10% ampolla 20 ml":{via:["EV"],u:["mL"]},
  "cloruro de sodio 0,9% ampolla 20 ml":{via:["EV"],u:["mL"]},
  "suxametonio":{via:["EV"],u:["mg"]},
  "tamsulosina":{via:["VO","SNG"],u:["cápsula"]},
  "timolol 0,5% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "tobramicina + dexametasona 0,3% + 0,1% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "tobramicina + dexametasona 3% + 1%":{via:["tópica"],u:["aplicación"]},
  "tobramicina 0,3% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "tobramicina 0,3% unguento":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["aplicación"]},
  "tramadol 100 mg / 2 ml":{via:["EV"],u:["mg"]},
  "tramadol gotas 100 mg / ml":{via:["VO","SNG"],u:["gota"]},
  "tropicamida 1% colirios":{via:["ojo derecho","ojo izquierdo","ambos ojos"],u:["gota"]},
  "vancomicina 1000 mg":{via:["EV"],u:["gr","mg"]},
  "vaselina liquida":{via:["tópica"],u:["ampolla"]},
  "zopiclona 7,5 mg":{via:["VO","SNG"],u:["mg"]}
};

function rxArsenalLookup(medDesc) {
    var norm = rxNorm(medDesc);
    // strip trailing route suffix
    norm = norm.replace(/\s+(vo|ev|sc|sng|im)$/,'');
    if (_rxArsenal[norm]) return _rxArsenal[norm];
    // partial match: find longest key contained in norm
    var best = null, bestLen = 0;
    for (var k in _rxArsenal) {
        if (norm.indexOf(k) === 0 && k.length > bestLen) { best = _rxArsenal[k]; bestLen = k.length; }
    }
    return best;
}

// ===== RX TABS =====
function rxShowTab(name, btn) {
    document.getElementById('rx-panel-pm').style.display   = (name === 'pm')   ? '' : 'none';
    document.getElementById('rx-panel-inf').style.display  = (name === 'inf')  ? '' : 'none';
    document.getElementById('rx-panel-otra').style.display = (name === 'otra') ? '' : 'none';
    var tabs = document.querySelectorAll('.rx-tab');
    for (var i = 0; i < tabs.length; i++) tabs[i].classList.remove('active');
    btn.classList.add('active');
}

// ===== PRESCRIPCIONES =====
function rxRebuildVia(selId, vias) {
    var sel = document.getElementById(selId);
    if (!sel) return;
    sel.innerHTML = '';
    var list = (vias && vias.length) ? vias : ['VO','EV','SC','SNG','inhalatorio','nebulización','tópica','nasal','rectal','intravesical','ojo derecho','ambos ojos','ojo izquierdo'];
    for (var i = 0; i < list.length; i++) {
        var o = document.createElement('option');
        o.value = 0; o.text = list[i]; sel.appendChild(o);
    }
}

function rxRebuildUnidad(selId, unidades) {
    var sel = document.getElementById(selId);
    if (!sel) return;
    sel.innerHTML = '';
    var list = (unidades && unidades.length) ? unidades : ['mg','gr','mL','mcg','UI','gota','puff','comprimido','ampolla','sobre','cápsula','aplicación'];
    for (var i = 0; i < list.length; i++) {
        var o = document.createElement('option');
        o.value = list[i]; o.text = list[i]; sel.appendChild(o);
    }
}

function rxOnMedChangePm(sel) {
    var opt = sel.options[sel.selectedIndex];
    var desc = opt.getAttribute('data-desc') || opt.text || '';
    document.getElementById('rx-pm-med-desc').value = desc;
    var entry = rxArsenalLookup(desc);
    rxRebuildVia('rx-pm-via', entry ? entry.via : null);
    rxRebuildUnidad('rx-pm-unidad', entry ? entry.u : null);
    // Update via-desc hidden field
    var vSel = document.getElementById('rx-pm-via');
    if (vSel && vSel.options.length > 0)
        document.getElementById('rx-pm-via-desc').value = vSel.options[0].text;
}

function rxCapturarVia(sel) {
    var hid = document.getElementById('rx-pm-via-desc');
    if (hid) hid.value = sel.options[sel.selectedIndex].text || '';
}

function rxValidarPm() {
    var sel = document.getElementById('rx-pm-insumo');
    if (!sel.value) { alert('Seleccione un medicamento'); return false; }
    document.getElementById('rx-pm-med-desc').value = sel.options[sel.selectedIndex].getAttribute('data-desc') || sel.options[sel.selectedIndex].text;
    var vSel = document.getElementById('rx-pm-via');
    if (vSel && vSel.selectedIndex >= 0)
        document.getElementById('rx-pm-via-desc').value = vSel.options[vSel.selectedIndex].text || '';
    return true;
}

var _rxSelPm = null, _rxSelPmData = null;

function rxSelectPm(row, id, idIns, desc, dosis, unidad, idVia, viaDesc, frecuencia, obs) {
    if (_rxSelPm) _rxSelPm.classList.remove('rx-selected');
    _rxSelPm = row; row.classList.add('rx-selected');
    _rxSelPmData = {id:id, idIns:idIns, desc:desc, dosis:dosis, unidad:unidad,
                    idVia:idVia, viaDesc:viaDesc, frecuencia:frecuencia, obs:obs};
}

function rxEditarPm() {
    if (!_rxSelPmData) { alert('Seleccione una prescripción de la lista'); return; }
    var d = _rxSelPmData;
    // Set medication and rebuild via/unidad from arsenal
    var medSel = document.getElementById('rx-pm-insumo');
    medSel.value = d.idIns;
    rxOnMedChangePm(medSel);
    // Now override with saved values
    document.getElementById('rx-pm-action').value    = 'modificar_pm';
    document.getElementById('rx-pm-id').value         = d.id;
    document.getElementById('rx-pm-med-desc').value   = d.desc;
    document.getElementById('rx-pm-dosis').value      = d.dosis;
    // Set saved unidad (add if not in list)
    var uSel = document.getElementById('rx-pm-unidad');
    if (d.unidad) {
        var found = false;
        for (var i=0;i<uSel.options.length;i++) if(uSel.options[i].value===d.unidad){uSel.selectedIndex=i;found=true;break;}
        if (!found) { var o=document.createElement('option');o.value=d.unidad;o.text=d.unidad;uSel.insertBefore(o,uSel.firstChild);uSel.selectedIndex=0; }
    }
    // Set saved via (match by text)
    var vSel = document.getElementById('rx-pm-via');
    if (d.viaDesc) {
        for (var j=0;j<vSel.options.length;j++) {
            if (vSel.options[j].text.toLowerCase()===d.viaDesc.toLowerCase()){vSel.selectedIndex=j;break;}
        }
    }
    document.getElementById('rx-pm-via-desc').value   = d.viaDesc;
    document.getElementById('rx-pm-frecuencia').value = d.frecuencia;
    document.getElementById('rx-pm-obs').value        = d.obs;
    document.getElementById('rx-pm-btn').textContent  = 'Guardar';
    document.getElementById('fs-presc').scrollIntoView({behavior:'smooth', block:'start'});
}

function rxEliminarPm() {
    if (!_rxSelPmData) { alert('Seleccione una prescripción de la lista'); return; }
    if (confirm('¿Desea eliminar la prescripción de ' + _rxSelPmData.desc + '?')) {
        document.getElementById('rx-del-pm-id').value = _rxSelPmData.id;
        document.getElementById('rx-frm-del-pm').submit();
    }
}

// ===== OTRAS PRESCRIPCIONES =====
var _rxSelOtra = null, _rxSelOtraData = null;

function rxSelectOtra(row, id, desc, dosis, unidad, viaDesc, frecuencia, obs) {
    if (_rxSelOtra) _rxSelOtra.classList.remove('rx-selected');
    _rxSelOtra = row; row.classList.add('rx-selected');
    _rxSelOtraData = {id:id, desc:desc, dosis:dosis, unidad:unidad, viaDesc:viaDesc, frecuencia:frecuencia, obs:obs};
}

function rxEditarOtra() {
    if (!_rxSelOtraData) { alert('Seleccione una prescripción de la lista'); return; }
    var d = _rxSelOtraData;
    document.getElementById('rx-otra-action').value     = 'modificar_pm';
    document.getElementById('rx-otra-id').value          = d.id;
    document.getElementById('rx-otra-med').value         = d.desc;
    document.getElementById('rx-otra-dosis').value       = d.dosis;
    document.getElementById('rx-otra-unidad').value      = d.unidad;
    document.getElementById('rx-otra-via').value         = d.viaDesc;
    document.getElementById('rx-otra-frecuencia').value  = d.frecuencia;
    document.getElementById('rx-otra-obs').value         = d.obs;
    document.getElementById('rx-otra-btn').textContent   = 'Guardar';
    rxShowTab('otra', document.getElementById('rx-tab-otra'));
    document.getElementById('fs-presc').scrollIntoView({behavior:'smooth', block:'start'});
}

function rxEliminarOtra() {
    if (!_rxSelOtraData) { alert('Seleccione una prescripción de la lista'); return; }
    if (confirm('¿Desea eliminar la prescripción de ' + _rxSelOtraData.desc + '?')) {
        document.getElementById('rx-del-otra-id').value = _rxSelOtraData.id;
        document.getElementById('rx-frm-del-otra').submit();
    }
}

function rxValidarOtra() {
    var desc = document.getElementById('rx-otra-med').value.trim();
    if (!desc) { alert('Ingrese el nombre del medicamento'); return false; }
    return true;
}

// ===== INFUSIONES =====
var _rxInfRowCount = 0;

function rxHEsc(s) {
    return String(s || '').replace(/&/g,'&amp;').replace(/"/g,'&quot;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
}

function rxBuildMedOptions(selectedId) {
    var html = '<option value="">Seleccione...</option>';
    for (var i = 0; i < _rxMeds.length; i++) {
        var m = _rxMeds[i];
        var s = (selectedId && m.id == selectedId) ? ' selected' : '';
        html += '<option value="' + m.id +
                '" data-desc="'   + rxHEsc(m.desc)   + '"' +
                ' data-unidad="'  + rxHEsc(m.unidad) + '"' + s + '>' +
                rxHEsc(m.desc) + '</option>';
    }
    return html;
}

function rxBuildUnidadOptions(selectedAbr) {
    var html = '<option value="">--</option>';
    for (var i = 0; i < _rxUnits.length; i++) {
        var u = _rxUnits[i];
        var s = (selectedAbr && u.abr === selectedAbr) ? ' selected' : '';
        html += '<option value="' + rxHEsc(u.abr) + '"' + s + '>' + rxHEsc(u.abr) + '</option>';
    }
    return html;
}

function rxAddInfRow(selId, dosisVal, unidadVal) {
    _rxInfRowCount++;
    var n = _rxInfRowCount;
    var tr = document.createElement('tr');
    tr.id = 'rx-inf-rw-' + n;
    tr.innerHTML =
        '<td style="text-align:center;font-weight:bold;font-size:11px;padding:2px;">' + n + '</td>' +
        '<td style="padding:2px;">' +
            '<select name="inf_med_id[]" id="rx-inf-sel-' + n + '"' +
                    ' onchange="rxOnInfMedChange(this,' + n + ')"' +
                    ' style="width:100%;font-size:11px;padding:2px;">' +
                rxBuildMedOptions(selId) +
            '</select>' +
            '<input type="hidden" name="inf_med_desc[]" id="rx-inf-desc-' + n + '" />' +
        '</td>' +
        '<td style="padding:2px;">' +
            '<input type="text" name="inf_med_dosis[]" placeholder="Dosis"' +
                   ' value="' + rxHEsc(dosisVal || '') + '"' +
                   ' style="width:100%;font-size:11px;padding:2px;" />' +
        '</td>' +
        '<td style="padding:2px;">' +
            '<select name="inf_med_unidad[]" id="rx-inf-uni-' + n + '"' +
                    ' style="width:100%;font-size:11px;padding:2px;">' +
                rxBuildUnidadOptions(unidadVal) +
            '</select>' +
        '</td>';
    document.getElementById('rx-inf-rows').appendChild(tr);
    if (selId) {
        for (var i = 0; i < _rxMeds.length; i++) {
            if (_rxMeds[i].id == selId) {
                document.getElementById('rx-inf-desc-' + n).value = _rxMeds[i].desc;
                break;
            }
        }
    }
}

function rxOnInfMedChange(sel, n) {
    var opt = sel.options[sel.selectedIndex];
    document.getElementById('rx-inf-desc-' + n).value = opt.getAttribute('data-desc') || '';
    var abr = opt.getAttribute('data-unidad') || '';
    if (abr) {
        var uSel = document.getElementById('rx-inf-uni-' + n);
        for (var i = 0; i < uSel.options.length; i++) {
            if (uSel.options[i].value === abr) { uSel.selectedIndex = i; break; }
        }
    }
}

function rxRemoveInfRow() {
    if (_rxInfRowCount <= 1) return;
    var row = document.getElementById('rx-inf-rw-' + _rxInfRowCount);
    if (row) { row.parentNode.removeChild(row); _rxInfRowCount--; }
}

var _rxSelInf = null, _rxSelInfData = null;

function rxSelectInf(row, idPi) {
    if (_rxSelInf) _rxSelInf.classList.remove('rx-selected');
    _rxSelInf = row; row.classList.add('rx-selected');
    var base = _rxInfData[idPi];
    _rxSelInfData = base
        ? {id:idPi, suero:base.suero, vel:base.vel, uVel:base.uVel,
           obs:base.obs, detalles:base.detalles}
        : {id:idPi, suero:'', vel:0, uVel:'mL/hr', obs:'', detalles:[]};
}

function rxEditarInf() {
    if (!_rxSelInfData) { alert('Seleccione una infusión de la lista'); return; }
    var d = _rxSelInfData;
    document.getElementById('rx-inf-action').value    = 'modificar_inf';
    document.getElementById('rx-inf-id-pi').value     = d.id;
    document.getElementById('rx-inf-suero').value     = d.suero;
    document.getElementById('rx-inf-vel').value       = d.vel;
    document.getElementById('rx-inf-uvel').value      = d.uVel;
    document.getElementById('rx-inf-obs').value       = d.obs;
    var tbody = document.getElementById('rx-inf-rows');
    tbody.innerHTML = '';
    _rxInfRowCount = 0;
    if (d.detalles && d.detalles.length > 0) {
        for (var i = 0; i < d.detalles.length; i++) {
            rxAddInfRow(d.detalles[i].id, d.detalles[i].dosis, d.detalles[i].unidad);
        }
    } else {
        rxAddInfRow(); rxAddInfRow(); rxAddInfRow();
    }
    document.getElementById('rx-inf-btn').textContent = 'Guardar Infusión';
    rxShowTab('inf', document.getElementById('rx-tab-inf'));
    document.getElementById('fs-presc').scrollIntoView({behavior:'smooth', block:'start'});
}

function rxEliminarInf() {
    if (!_rxSelInfData) { alert('Seleccione una infusión de la lista'); return; }
    if (confirm('¿Desea eliminar esta infusión?')) {
        document.getElementById('rx-del-inf-id').value = _rxSelInfData.id;
        document.getElementById('rx-frm-del-inf').submit();
    }
}

window.addEventListener('load', function() {
    // Initialize infusion rows
    if (document.getElementById('rx-inf-rows')) {
        rxAddInfRow(); rxAddInfRow(); rxAddInfRow();
    }
    // Initialize PM via/unidad with defaults
    rxRebuildVia('rx-pm-via', null);
    rxRebuildUnidad('rx-pm-unidad', null);
    var vSel = document.getElementById('rx-pm-via');
    var hid  = document.getElementById('rx-pm-via-desc');
    if (vSel && hid && vSel.options.length > 0)
        hid.value = vSel.options[0].text;
});
</script>

<jsp:include page="Footer.jsp" />

<%
    }
%>
