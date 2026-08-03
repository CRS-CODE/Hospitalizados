<%@page import="CapaDato.cDato"%>
<%@page import="CapaDato.cPrescripcion"%>
<%@page import="CapaDato.cInfusion"%>
<%@page import="CapaDato.cInfusionDetalle"%>
<%@page import="CapaDato.cInsumo"%>
<%@page import="CapaDato.cUnidadMedida"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession session1 = request.getSession();
    NegocioQ negQ = new NegocioQ();

    if (session1.getAttribute("usuario_rut") == null) {
        out.write("<script>alert('El tiempo de su sesión ha caducado. Ingrese nuevamente.');"
                + "window.location='" + negQ.getLocal() + "index.jsp?timeout=1'</script>");
        return;
    }

    int id_duo = 0;
    if (request.getParameter("id_duo") != null) {
        try { id_duo = Integer.parseInt(request.getParameter("id_duo")); } catch (NumberFormatException e) {}
    }

    String tab = "prescripciones";
    if ("infusiones".equals(request.getParameter("tab"))) tab = "infusiones";

    ArrayList lista_meds    = negQ.lista_medicamento_completa();
    ArrayList lista_unidades = negQ.lista_unidad_medida();
    Vector<cDato> lista_vias = negQ.searchViaAdmision();
    ArrayList lista_pm      = negQ.lista_prescripciones_por_duo(id_duo);
    ArrayList lista_inf     = negQ.lista_infusiones_por_duo(id_duo);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Prescripción de Medicamentos</title>
    <style>
        * { box-sizing: border-box; }
        body { font-family: Arial, sans-serif; font-size: 12px; margin: 0; padding: 8px; background: #e8e8e8; }
        .container { background: white; border: 2px solid #4169E1; max-width: 1180px; margin: 0 auto; }
        .title-bar { background: #4169E1; color: white; padding: 7px 12px; font-weight: bold; font-size: 13px; }
        .tab-strip { display: flex; border-bottom: 2px solid #4169E1; background: #d8d8d8; }
        .tab-btn { padding: 5px 18px; cursor: pointer; border: 1px solid #aaa; border-bottom: none;
                   background: #d0d0d0; font-size: 12px; font-weight: bold; margin: 4px 2px 0 2px; }
        .tab-btn.active { background: white; border-color: #4169E1; border-bottom: 2px solid white;
                          margin-bottom: -2px; color: #4169E1; }
        .tab-panel { display: none; padding: 8px; }
        .tab-panel.active { display: block; }
        .section { border: 1px solid #4169E1; margin: 6px 0; }
        .section-hdr { background: #4169E1; color: white; padding: 4px 8px; font-weight: bold; font-size: 11px; }
        .section-body { padding: 8px; }
        th.col-hdr { font-weight: bold; font-size: 11px; padding: 3px 4px; text-align: left; color: #333; }
        table.grid { width: 100%; border-collapse: collapse; font-size: 11px; }
        table.grid th { background: #4169E1; color: white; padding: 4px 6px; text-align: left; }
        table.grid td { padding: 3px 6px; border: 1px solid #ddd; }
        table.grid tr.selectable:hover { background: #d0daff; cursor: pointer; }
        table.grid tr.selected { background: #4169E1 !important; color: white; }
        input[type=text], input[type=number] { font-size: 11px; padding: 2px 3px; width: 100%; }
        select { font-size: 11px; padding: 2px; width: 100%; }
        .btn { padding: 4px 14px; font-size: 12px; font-weight: bold; cursor: pointer;
               border: 1px solid #2050bb; border-radius: 2px; }
        .btn-blue { background: #4169E1; color: white; }
        .btn-blue:hover { background: #2050cc; }
        .btn-gray { background: #e0e0e0; color: #333; border-color: #aaa; }
        .btn-gray:hover { background: #cccccc; }
        .footer-bar { padding: 8px 12px; border-top: 1px solid #ccc; text-align: right; background: #f5f5f5; }
        .status-bar { font-size: 11px; color: #555; padding: 4px 10px; border-top: 1px solid #ccc; background: #fafafa; }
        .layout-row { display: flex; gap: 10px; }
        .layout-left { flex: 1; min-width: 0; }
        .layout-right { width: 320px; flex-shrink: 0; }
        .side-btns { display: flex; flex-direction: column; gap: 6px; padding-top: 22px; }
        .empty-row { color: #999; text-align: center; font-style: italic; }
    </style>
</head>
<body>
<div class="container">

    <div class="title-bar">PRESCRIPCIÓN DE MEDICAMENTOS</div>

    <div class="tab-strip">
        <div class="tab-btn <%= "prescripciones".equals(tab) ? "active" : "" %>"
             onclick="showTab('prescripciones')">PRESCRIPCIONES</div>
        <div class="tab-btn <%= "infusiones".equals(tab) ? "active" : "" %>"
             onclick="showTab('infusiones')">INFUSIONES</div>
    </div>

    <!-- ==================== TAB PRESCRIPCIONES ==================== -->
    <div id="panel-prescripciones" class="tab-panel <%= "prescripciones".equals(tab) ? "active" : "" %>">

        <div class="section">
            <div class="section-hdr">NUEVA PRESCRIPCIÓN</div>
            <div class="section-body">
                <form action="gestion_prescripcion" method="post" onsubmit="return validarPm();">
                    <input type="hidden" name="id_duo"          value="<%= id_duo %>" />
                    <input type="hidden" name="action"          id="pm-action" value="agregar_pm" />
                    <input type="hidden" name="id_pm"           id="pm-id" value="0" />
                    <input type="hidden" name="medicamento_desc" id="pm-med-desc" />
                    <input type="hidden" name="via_desc"        id="pm-via-desc" />
                    <input type="hidden" name="tab"             value="prescripciones" />

                    <table style="width:100%; border-collapse:collapse;">
                        <tr>
                            <th class="col-hdr" style="width:32%;">Medicamento</th>
                            <th class="col-hdr" style="width:8%;">Dosis</th>
                            <th class="col-hdr" style="width:8%;">Unidad</th>
                            <th class="col-hdr" style="width:14%;">Vía</th>
                            <th class="col-hdr" style="width:16%;">Frecuencia</th>
                            <th class="col-hdr" style="width:14%;">Observación</th>
                            <th style="width:8%;"></th>
                        </tr>
                        <tr>
                            <td>
                                <select name="id_insumo" id="pm-insumo" onchange="cargarUnidadPm(this)">
                                    <option value="">Seleccione...</option>
                                    <%
                                    for (Object obj : lista_meds) {
                                        cInsumo med = (cInsumo) obj;
                                        String unidad = med.getUnidad_medida_desc() != null ? med.getUnidad_medida_desc() : "";
                                        String desc   = med.getInsumo_desc() != null ? med.getInsumo_desc() : "";
                                    %>
                                    <option value="<%= med.getId_insumo() %>"
                                            data-desc="<%= desc.replace("\"","") %>"
                                            data-unidad="<%= unidad %>">
                                        <%= desc %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                            <td><input type="text" name="dosis" id="pm-dosis" /></td>
                            <td>
                                <select name="unidad_desc" id="pm-unidad">
                                    <option value="">--</option>
                                    <% for (Object objU : lista_unidades) {
                                        cUnidadMedida uMed = (cUnidadMedida) objU; %>
                                    <option value="<%= uMed.getDescripcion()%>"><%= uMed.getDescripcion() %></option>
                                    <% } %>
                                </select>
                            </td>
                            <td>
                                <select name="id_via" id="pm-via" onchange="capturarVia(this)">
                                    <option value="">Seleccione...</option>
                                    <% for (cDato v : lista_vias) { %>
                                    <option value="<%= v.getId() %>"><%= v.getDescription() %></option>
                                    <% } %>
                                </select>
                            </td>
                            <td>
                                <div style="display:flex;align-items:center;gap:3px;">
                                    <span>cada</span>
                                    <input type="text" name="frecuencia" id="pm-frecuencia"
                                           placeholder="ej: 8 hrs" style="width:80px;" />
                                </div>
                            </td>
                            <td><input type="text" name="observacion" id="pm-obs" /></td>
                            <td style="text-align:center; padding:3px;">
                                <button type="submit" class="btn btn-blue" id="pm-btn">Agregar</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <div class="section">
            <div class="section-hdr">PRESCRIPCIONES REGISTRADAS</div>
            <div class="section-body">
                <div class="layout-row">
                    <div class="layout-left" style="overflow-x:auto;">
                        <table class="grid" id="tbl-pm">
                            <tr>
                                <th>Medicamento</th>
                                <th style="width:65px;">Dosis</th>
                                <th style="width:55px;">Unidad</th>
                                <th style="width:55px;">Vía Adm.</th>
                                <th style="width:80px;">Frecuencia</th>
                                <th style="width:120px;">Observación</th>
                            </tr>
                            <%
                            int cntPm = 0;
                            for (Object obj : lista_pm) {
                                cPrescripcion pm = (cPrescripcion) obj;
                                cntPm++;
                                String dEsc  = pm.getMedicamento_desc().replace("'", "\\'").replace("\"", "");
                                String dDosis= pm.getDosis().replace("'", "\\'");
                                String dUni  = pm.getUnidad_desc().replace("'", "\\'");
                                String dVia  = pm.getVia_desc().replace("'", "\\'");
                                String dFrec = pm.getFrecuencia().replace("'", "\\'");
                                String dObs  = pm.getObservacion().replace("'", "\\'");
                            %>
                            <tr class="selectable"
                                onclick="selectPm(this,<%= pm.getId_pm() %>,<%= pm.getId_insumo() %>,'<%= dEsc %>','<%= dDosis %>','<%= dUni %>',<%= pm.getId_via() %>,'<%= dVia %>','<%= dFrec %>','<%= dObs %>')">
                                <td><%= pm.getMedicamento_desc() %></td>
                                <td><%= pm.getDosis() %></td>
                                <td><%= pm.getUnidad_desc() %></td>
                                <td><%= pm.getVia_desc() %></td>
                                <td><%= pm.getFrecuencia() %></td>
                                <td><%= pm.getObservacion() %></td>
                            </tr>
                            <% } %>
                            <% if (cntPm == 0) { %>
                            <tr><td colspan="6" class="empty-row">Sin prescripciones registradas</td></tr>
                            <% } %>
                        </table>
                    </div>
                    <div class="side-btns">
                        <button class="btn btn-blue" onclick="editarPm()">Modificar</button>
                        <button class="btn btn-blue" onclick="eliminarPm()">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- ==================== TAB INFUSIONES ==================== -->
    <div id="panel-infusiones" class="tab-panel <%= "infusiones".equals(tab) ? "active" : "" %>">

        <div class="section">
            <div class="section-hdr">NUEVA INFUSIÓN</div>
            <div class="section-body">
                <form action="gestion_prescripcion" method="post" id="form-inf">
                    <input type="hidden" name="id_duo"          value="<%= id_duo %>" />
                    <input type="hidden" name="action"          id="inf-action" value="agregar_inf" />
                    <input type="hidden" name="id_pi"           id="inf-id-pi" value="0" />
                    <input type="hidden" name="tab"             value="infusiones" />

                    <div class="layout-row">
                        <!-- Columna izquierda: filas de medicamentos -->
                        <div class="layout-left">
                            <table style="width:100%; border-collapse:collapse;">
                                <thead>
                                    <tr>
                                        <th class="col-hdr" style="width:22px;"></th>
                                        <th class="col-hdr">Medicamento</th>
                                        <th class="col-hdr" style="width:75px;">Dosis</th>
                                        <th class="col-hdr" style="width:60px;">Unidad</th>
                                    </tr>
                                </thead>
                                <tbody id="inf-rows"></tbody>
                            </table>
                            <div style="margin-top:5px; display:flex; gap:6px;">
                                <button type="button" class="btn btn-gray" onclick="addInfRow()">+ Medicamento</button>
                                <button type="button" class="btn btn-gray" onclick="removeInfRow()">- Quitar</button>
                            </div>
                        </div>

                        <!-- Columna derecha: suero + administración + botón -->
                        <div class="layout-right">
                            <div class="section" style="margin-bottom:6px;">
                                <div class="section-hdr">VEHÍCULO / SUERO</div>
                                <div class="section-body" style="padding:5px;">
                                    <select name="suero_desc" id="inf-suero">
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

                            <div class="section" style="margin-bottom:6px;">
                                <div class="section-hdr">ADMINISTRACIÓN</div>
                                <div class="section-body" style="padding:5px;">
                                    <table style="width:100%;">
                                        <tr>
                                            <th class="col-hdr">Velocidad infusión</th>
                                            <th class="col-hdr">Observaciones</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="display:flex;align-items:center;gap:3px;">
                                                    <input type="text" name="velocidad_inf" id="inf-vel"
                                                           style="width:55px;" />
                                                    <select name="unidad_velocidad" id="inf-unidad-vel"
                                                            style="width:90px;">
                                                        <option value="mL/hr">mL/hr</option>
                                                        <option value="gts/min">gts/min</option>
                                                        <option value="mcg/kg/min">mcg/kg/min</option>
                                                        <option value="UI/hr">UI/hr</option>
                                                    </select>
                                                </div>
                                            </td>
                                            <td><input type="text" name="obs_inf" id="inf-obs" /></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-blue" id="inf-btn"
                                    style="width:100%;">Agregar Infusión</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="section">
            <div class="section-hdr">INFUSIONES INGRESADAS</div>
            <div class="section-body">
                <div class="layout-row">
                    <div class="layout-left" style="overflow-x:auto;">
                        <table class="grid" id="tbl-inf">
                            <tr>
                                <th>Medicamentos (diluidos)</th>
                                <th style="width:190px;">Vehículo/Suero</th>
                                <th style="width:90px;">Velocidad</th>
                                <th style="width:120px;">Observación</th>
                            </tr>
                            <%
                            int cntInf = 0;
                            for (Object obj2 : lista_inf) {
                                cInfusion inf = (cInfusion) obj2;
                                cntInf++;
                            %>
                            <tr class="selectable" onclick="selectInf(this,<%= inf.getId_pi() %>)">
                                <td><%= inf.getMeds_display() %></td>
                                <td><%= inf.getSuero_desc() %></td>
                                <td><%= inf.getVelocidad_inf() %> <%= inf.getUnidad_velocidad() %></td>
                                <td><%= inf.getObservacion() %></td>
                            </tr>
                            <% } %>
                            <% if (cntInf == 0) { %>
                            <tr><td colspan="4" class="empty-row">Sin infusiones ingresadas</td></tr>
                            <% } %>
                        </table>
                    </div>
                    <div class="side-btns">
                        <button class="btn btn-blue" onclick="editarInf()">Modificar</button>
                        <button class="btn btn-blue" onclick="eliminarInf()">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer-bar">
        <button class="btn btn-gray" onclick="history.back()">Cancelar</button>
        &nbsp;
        <a href="PDF_prescripcion?id_duo=<%= id_duo %>" target="_blank">
            <button type="button" class="btn btn-gray">&#128438; Imprimir PDF</button>
        </a>
        &nbsp;
        <button class="btn btn-blue" onclick="history.back()">Guardar y Cerrar</button>
    </div>
    <div class="status-bar">Seleccione un medicamento de la lista y se cargará automáticamente la unidad de medida</div>
</div>

<!-- Formularios ocultos para eliminar -->
<form id="frm-del-pm" action="gestion_prescripcion" method="post">
    <input type="hidden" name="action"  value="eliminar_pm" />
    <input type="hidden" name="id_pm"   id="del-pm-id" value="0" />
    <input type="hidden" name="id_duo"  value="<%= id_duo %>" />
    <input type="hidden" name="tab"     value="prescripciones" />
</form>
<form id="frm-del-inf" action="gestion_prescripcion" method="post">
    <input type="hidden" name="action"  value="eliminar_inf" />
    <input type="hidden" name="id_pi"   id="del-inf-id" value="0" />
    <input type="hidden" name="id_duo"  value="<%= id_duo %>" />
    <input type="hidden" name="tab"     value="infusiones" />
</form>

<script>
// ---- _infData: datos de infusiones cargados desde servidor ----
var _infData = {};
<%
for (Object oInf2 : lista_inf) {
    cInfusion inf2 = (cInfusion) oInf2;
    String sSuero2 = inf2.getSuero_desc() != null ? inf2.getSuero_desc().replace("\\","\\\\").replace("\"","\\\"") : "";
    String sObs2   = inf2.getObservacion() != null ? inf2.getObservacion().replace("\\","\\\\").replace("\"","\\\"") : "";
    String sUVel2  = inf2.getUnidad_velocidad() != null ? inf2.getUnidad_velocidad() : "mL/hr";
    StringBuilder detSb = new StringBuilder("[");
    boolean fd = true;
    for (Object dObj2 : inf2.getDetalles()) {
        cInfusionDetalle det2 = (cInfusionDetalle) dObj2;
        if (!fd) detSb.append(",");
        String dDesc2 = det2.getMedicamento_desc() != null ? det2.getMedicamento_desc().replace("\\","\\\\").replace("\"","\\\"") : "";
        String dDos2  = det2.getDosis() != null ? det2.getDosis() : "";
        String dUni2  = det2.getUnidad_desc() != null ? det2.getUnidad_desc() : "";
        detSb.append("{\"id\":").append(det2.getId_insumo())
             .append(",\"desc\":\"").append(dDesc2).append("\"")
             .append(",\"dosis\":\"").append(dDos2).append("\"")
             .append(",\"unidad\":\"").append(dUni2).append("\"}");
        fd = false;
    }
    detSb.append("]");
%>
_infData[<%= inf2.getId_pi() %>]={suero:"<%= sSuero2 %>",vel:<%= inf2.getVelocidad_inf() %>,uVel:"<%= sUVel2 %>",obs:"<%= sObs2 %>",detalles:<%= detSb.toString() %>};
<% } %>

// ---- Medicamentos disponibles para filas de infusión ----
var _meds = [
<%
boolean fm = true;
for (Object obj : lista_meds) {
    cInsumo med = (cInsumo) obj;
    if (!fm) out.print(",");
    String d = med.getInsumo_desc() != null ? med.getInsumo_desc().replace("\"","\\\"").replace("'","\\'") : "";
    String u = med.getUnidad_medida_desc() != null ? med.getUnidad_medida_desc() : "";
    out.print("{\"id\":" + med.getId_insumo() + ",\"desc\":\"" + d + "\",\"unidad\":\"" + u + "\"}");
    fm = false;
}
%>
];

// ---- Unidades de medida disponibles ----
var _units = [
<%
boolean fu = true;
for (Object objU : lista_unidades) {
    cUnidadMedida uMed2 = (cUnidadMedida) objU;
    if (!fu) out.print(",");
    String uAbr  = uMed2.getAbreviacion()  != null ? uMed2.getAbreviacion().replace("\"","\\\"")  : "";
    String uDesc = uMed2.getDescripcion()  != null ? uMed2.getDescripcion().replace("\"","\\\"")  : "";
    out.print("{\"abr\":\"" + uAbr + "\",\"desc\":\"" + uDesc + "\"}");
    fu = false;
}
%>
];

// ---- Tabs ----
function showTab(name) {
    ['prescripciones','infusiones'].forEach(function(t) {
        document.getElementById('panel-' + t).classList.remove('active');
    });
    document.querySelectorAll('.tab-btn').forEach(function(b) { b.classList.remove('active'); });
    document.getElementById('panel-' + name).classList.add('active');
    document.querySelector('.tab-btn[onclick="showTab(\'' + name + '\')"]').classList.add('active');
}

// ---- Prescripcion simple ----
function cargarUnidadPm(sel) {
    var opt = sel.options[sel.selectedIndex];
    document.getElementById('pm-med-desc').value = opt.getAttribute('data-desc') || '';
    // Auto-seleccionar unidad si el medicamento tiene una definida
    var abr = opt.getAttribute('data-unidad') || '';
    if (abr) {
        var uSel = document.getElementById('pm-unidad');
        for (var i = 0; i < uSel.options.length; i++) {
            if (uSel.options[i].value === abr) { uSel.selectedIndex = i; break; }
        }
    }
}

function capturarVia(sel) {
    document.getElementById('pm-via-desc').value = sel.options[sel.selectedIndex].text || '';
}

function validarPm() {
    var sel = document.getElementById('pm-insumo');
    if (!sel.value) { alert('Seleccione un medicamento'); return false; }
    document.getElementById('pm-med-desc').value = sel.options[sel.selectedIndex].getAttribute('data-desc') || sel.options[sel.selectedIndex].text;
    var vSel = document.getElementById('pm-via');
    document.getElementById('pm-via-desc').value = vSel.options[vSel.selectedIndex].text || '';
    return true;
}

var _selPm = null, _selPmData = null;

function selectPm(row, id, idIns, desc, dosis, unidad, idVia, viaDesc, frecuencia, obs) {
    if (_selPm) _selPm.classList.remove('selected');
    _selPm = row; row.classList.add('selected');
    _selPmData = {id:id, idIns:idIns, desc:desc, dosis:dosis, unidad:unidad,
                  idVia:idVia, viaDesc:viaDesc, frecuencia:frecuencia, obs:obs};
}

function editarPm() {
    if (!_selPmData) { alert('Seleccione una prescripción de la lista'); return; }
    var d = _selPmData;
    document.getElementById('pm-action').value    = 'modificar_pm';
    document.getElementById('pm-id').value         = d.id;
    document.getElementById('pm-insumo').value     = d.idIns;
    document.getElementById('pm-med-desc').value   = d.desc;
    document.getElementById('pm-dosis').value      = d.dosis;
    document.getElementById('pm-unidad').value     = d.unidad;
    document.getElementById('pm-via').value        = d.idVia;
    document.getElementById('pm-via-desc').value   = d.viaDesc;
    document.getElementById('pm-frecuencia').value = d.frecuencia;
    document.getElementById('pm-obs').value        = d.obs;
    document.getElementById('pm-btn').textContent  = 'Guardar';
    window.scrollTo(0, 0);
}

function eliminarPm() {
    if (!_selPmData) { alert('Seleccione una prescripción de la lista'); return; }
    if (confirm('¿Desea eliminar la prescripción de ' + _selPmData.desc + '?')) {
        document.getElementById('del-pm-id').value = _selPmData.id;
        document.getElementById('frm-del-pm').submit();
    }
}

// ---- Infusión: filas de medicamentos ----
var _infRowCount = 0;

function hEsc(s) {
    return String(s || '').replace(/&/g,'&amp;').replace(/"/g,'&quot;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
}

function buildMedOptions(selectedId) {
    var html = '<option value="">Seleccione...</option>';
    for (var i = 0; i < _meds.length; i++) {
        var m = _meds[i];
        var s = (selectedId && m.id == selectedId) ? ' selected' : '';
        html += '<option value="' + m.id +
                '" data-desc="'   + hEsc(m.desc)   + '"' +
                ' data-unidad="'  + hEsc(m.unidad) + '"' + s + '>' +
                hEsc(m.desc) + '</option>';
    }
    return html;
}

function buildUnidadOptions(selectedAbr) {
    var html = '<option value="">--</option>';
    for (var i = 0; i < _units.length; i++) {
        var u = _units[i];
        var s = (selectedAbr && u.abr === selectedAbr) ? ' selected' : '';
        html += '<option value="' + hEsc(u.abr) + '"' + s + '>' + hEsc(u.abr) + '</option>';
    }
    return html;
}

function addInfRow(selId, dosisVal, unidadVal) {
    _infRowCount++;
    var n = _infRowCount;
    var tr = document.createElement('tr');
    tr.id = 'inf-rw-' + n;
    tr.innerHTML =
        '<td style="text-align:center;font-weight:bold;font-size:11px;padding:2px;">' + n + '</td>' +
        '<td style="padding:2px;">' +
            '<select name="inf_med_id[]" id="inf-sel-' + n + '"' +
                    ' onchange="onInfMedChange(this,' + n + ')"' +
                    ' style="width:100%;font-size:11px;padding:2px;">' +
                buildMedOptions(selId) +
            '</select>' +
            '<input type="hidden" name="inf_med_desc[]" id="inf-desc-' + n + '" />' +
        '</td>' +
        '<td style="padding:2px;">' +
            '<input type="text" name="inf_med_dosis[]" placeholder="Dosis"' +
                   ' value="' + hEsc(dosisVal || '') + '"' +
                   ' style="width:100%;font-size:11px;padding:2px;" />' +
        '</td>' +
        '<td style="padding:2px;">' +
            '<select name="inf_med_unidad[]" id="inf-uni-' + n + '"' +
                    ' style="width:100%;font-size:11px;padding:2px;">' +
                buildUnidadOptions(unidadVal) +
            '</select>' +
        '</td>';
    document.getElementById('inf-rows').appendChild(tr);
    if (selId) {
        for (var i = 0; i < _meds.length; i++) {
            if (_meds[i].id == selId) {
                document.getElementById('inf-desc-' + n).value = _meds[i].desc;
                break;
            }
        }
    }
}

function onInfMedChange(sel, n) {
    var opt = sel.options[sel.selectedIndex];
    document.getElementById('inf-desc-' + n).value = opt.getAttribute('data-desc') || '';
    // Auto-seleccionar unidad si el medicamento tiene una definida en BD
    var abr = opt.getAttribute('data-unidad') || '';
    if (abr) {
        var uSel = document.getElementById('inf-uni-' + n);
        for (var i = 0; i < uSel.options.length; i++) {
            if (uSel.options[i].value === abr) { uSel.selectedIndex = i; break; }
        }
    }
}

function removeInfRow() {
    if (_infRowCount <= 1) return;
    var row = document.getElementById('inf-rw-' + _infRowCount);
    if (row) { row.parentNode.removeChild(row); _infRowCount--; }
}

window.addEventListener('load', function() {
    addInfRow(); addInfRow(); addInfRow();
});

// ---- Infusión: selección y CRUD ----
var _selInf = null, _selInfData = null;

function selectInf(row, idPi) {
    if (_selInf) _selInf.classList.remove('selected');
    _selInf = row; row.classList.add('selected');
    var base = _infData[idPi];
    _selInfData = base
        ? {id:idPi, suero:base.suero, vel:base.vel, uVel:base.uVel,
           obs:base.obs, detalles:base.detalles}
        : {id:idPi, suero:'', vel:0, uVel:'mL/hr', obs:'', detalles:[]};
}

function editarInf() {
    if (!_selInfData) { alert('Seleccione una infusión de la lista'); return; }
    var d = _selInfData;
    document.getElementById('inf-action').value     = 'modificar_inf';
    document.getElementById('inf-id-pi').value      = d.id;
    document.getElementById('inf-suero').value      = d.suero;
    document.getElementById('inf-vel').value        = d.vel;
    document.getElementById('inf-unidad-vel').value = d.uVel;
    document.getElementById('inf-obs').value        = d.obs;
    var tbody = document.getElementById('inf-rows');
    tbody.innerHTML = '';
    _infRowCount = 0;
    if (d.detalles && d.detalles.length > 0) {
        for (var i = 0; i < d.detalles.length; i++) {
            addInfRow(d.detalles[i].id, d.detalles[i].dosis, d.detalles[i].unidad);
        }
    } else {
        addInfRow(); addInfRow(); addInfRow();
    }
    document.getElementById('inf-btn').textContent = 'Guardar Infusión';
    window.scrollTo(0, 0);
}

function eliminarInf() {
    if (!_selInfData) { alert('Seleccione una infusión de la lista'); return; }
    if (confirm('¿Desea eliminar esta infusión?')) {
        document.getElementById('del-inf-id').value = _selInfData.id;
        document.getElementById('frm-del-inf').submit();
    }
}
</script>
</body>
</html>
