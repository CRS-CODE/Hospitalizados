<%-- 
    Document   : vista_medica2
    Created on : 14-ago-2015, 11:23:46
    Author     : Informatica
--%>

<%@page import="java.text.DateFormat"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("index.jsp?timeout=1");
    } else {
%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="Header.jsp" />


<body onload="" >
<legend>LISTADO DE CAMILLAS </legend>


<fieldset>
    <legend>Historial Visita Medica</legend>

    <table border="0" cellpadding="1" cellspacing="1">

    </table>

</fieldset>

<input type="hidden" value="0" id="control_grabado">
<fieldset>
    <legend>Visita Médica Nueva en la CAMA: </legend>


    <div id="dhtmlgoodies_tabView2">
        <div class="dhtmlgoodies_aTab" >
            <fieldset>
                <legend>Datos Generales</legend>
                <table border="0" style="font-size: 12px" cellpadding="1" cellspacing="1">
                    <tr>
                        <td colspan="3">
                            <b><label class="rojo">*</label>Fecha:<input name="fecha_at" id="fecha_at" type="text" size="12" value="" onBlur="esFechaValida(this);" maxlength="10">&nbsp;<input maxlength="2" type="text" value="" name="hora_at" id="hora_at" size="2" autocomplete="off" onkeyup="solonumerosHora(this.value, this.id, 'min_at')">&nbsp;:&nbsp;<input maxlength="2" type="text" name="min_at" id="min_at" autocomplete="off"  value="" size="2" onkeyup="solonumerosMin(this.value, this.id, 'hora_egreso')"></b><label class="rojo">  dd/mm/yyyy 24hrs.</label>
                        </td>
                    </tr>
                    <tr>
                        <td>Reposo:</td><td><input type="text" id="reposo_visita_med"></td>
                        <td>Oxigenoterapia:</td><td><input type="text" id="oxigenoterapia_visita_med"></td>
                    </tr>
                    <tr>
                        <td>Regimen:</td><td><input type="text" id="regimen_visita_med"></td>
                        <td>KNT Resp:</td><td><input type="checkbox" id="knt_resp_visita_med" value="0" onclick="if (this.checked) {
                                    this.value = 1
                                } else {
                                    this.value = 0
                                }"></td>
                        <td>KNT Motora:</td><td><input type="checkbox" id="knt_motora_visita_med" value="0" onclick="if (this.checked) {
                                    this.value = 1
                                } else {
                                    this.value = 0
                                }"></td>
                    </tr>
                    <tr>
                        <td>CSV:</td><td><input type="text" id="csv_visita_med"></td>
                        <td>Nebulizaciones:</td><td><input type="text" id="neb_visita_med"></td>

                    </tr>
                </table>
            </fieldset>
        </div>

        <div class="dhtmlgoodies_aTab">
            <fieldset>
                <legend>Evolución Médica</legend>
                <table border="0" cellpadding="1" cellspacing="1">
                    <tr>
                        <td><textarea cols="80" rows="5" id="evolucion_visita_med"></textarea></td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="dhtmlgoodies_aTab">

            <fieldset id="FielDiagnostico" style="">
                <legend>Diagnóstico:</legend>
                <table>
                    <tr>
                        <td><input type="text" size="80" id="descripcion_diagnostico_visita_med">
                            <input type="button" onclick="GuardaDiag();" value="Guardar"></td>
                    </tr>
                </table>
                <label style="font-size: 12px;text-decoration:underline">Diagnosticos Nuevos detectados en esta Visita</label>
                <div id="DivMuestraDiag">




                    <table width="700">

                        <tr>
                            <th style="background-color:lime;font-size:11px">N°</th>
                            <th style="background-color:lime;font-size:11px">Descripción Diagnóstico</th>
                            <th style="background-color:lime;font-size:11px">Eliminar</th>
                        </tr>

                    </table>
                </div>
                <br>
                <table width="700" class="DATOS">
                    <tr>
                        <th class="DATOS" colspan="3">Diagnosticos Registrados al Ingreso y en anteriores Visitas</th>

                    </tr>
                    <tr>
                        <th class="DATOS">N°</th>
                        <th class="DATOS">Descripción Diagnóstico</th>
                        <th class="DATOS">-</th>
                    </tr>

                </table>
            </fieldset>
        </div>
        <div class="dhtmlgoodies_aTab">
            <fieldset>
                <legend>Medicamentos<input type="button" value="Ver Receta" onclick="if (document.getElementById('DivMuestraMedicamento').childNodes[1].nodeName == 'P') {
                            alert('No hay Medicamentos Ingresados');
                        } else {
                            window.open('RecetaPDF.jsp?id_visita=' + document.getElementById('control_grabado').value, 'Receta', 'height=800,width=800,left=100, top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')
                        }"></legend>
                <br>
                <input type="button" value="Agregar Medicamento" onclick="GuardaMedicamento();">
                <table border="0" width="500px" class="DATOS" cellpadding="1" cellspacing="1">
                    <tr>
                        <th  style="background-color:lime;font-size:11px">Medicamento</th>
                        <th  style="background-color:lime;font-size:11px">Dosis</th>
                        <th  style="background-color:lime;font-size:11px">Vía</th>
                        <th  style="background-color:lime;font-size:11px">Frecuencia</th>
                        <th  style="background-color:lime;font-size:11px">Horario</th>
                    </tr>
                    <tr>


                        <td class="DATOS">
                            <input type="text" size="50" id="detalle_medicamento_visita_med" onclick="this.select();//document.getElementById('medicamentoseleccionado').value=''">
                            <input id="id_medicamento" type="hidden">
                        </td>
                        <td class="DATOS"><input type="text" size="15" id="dosis_medicamento_visita_med"></td>
                        <td class="DATOS"><select id="via_medicamento_visita_med">
                                <%%>
                            </select>
                        </td>
                        <td class="DATOS"><input type="text" size="10" id="frecuencia_medicamento_visita_med"></td>
                        <td class="DATOS"><input type="text" size="10" id="horario_medicamento_visita_med"></td>

                    </tr>

                </table>
                <div id="DivMuestraMedicamento">
                    <p></p>
                </div>
            </fieldset>
        </div>
        <div class="dhtmlgoodies_aTab">
            <fieldset>
                <legend>Solicitud de Examenes<input type="button" value="Orden de Examen" onclick="/*if(document.getElementById('DivMuestraExamen').childNodes[1].nodeName=='P'){alert('No hay Examenes Ingresados');}else{*/window.open('OrdenExamenPDF.jsp?id_visita=' + document.getElementById('control_grabado').value, 'Orden Examen', 'height=800,width=800,left=100, top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')//}"></legend>
                <table border="0" cellpadding="1" cellspacing="1" style="border: 1px solid;font-size: 9px" >

                    <tr>

                    <tr>
                        <th style="border: 1px solid;background-color: #85BBEF" colspan="10">RX</th>
                    </tr>
                    <tr>
                        <td><input type="checkbox" id="67" onclick="if (!this.checked) {
                                    document.getElementById('text_rx').disabled = true;
                                    EliminaExamen(67);
                                } else {
                                    document.getElementById('text_rx').disabled = false;
                                    document.getElementById('text_rx').focus();
                                    document.getElementById('btnRX').disabled = false
                                }"></td>
                        <td style="cursor: pointer"  onclick="if (document.getElementById('67').checked) {
                                    document.getElementById('67').checked = false;
                                    document.getElementById('text_rx').disabled = true;
                                    EliminaExamen(67);
                                } else {
                                    document.getElementById('67').checked = true;
                                    document.getElementById('text_rx').disabled = false;
                                    document.getElementById('text_rx').focus();
                                    document.getElementById('btnRX').disabled = false
                                }">
                            RX</td><td colspan="6"><input type="text" id="text_rx" size="40" disabled></td>
                        <td><input type="button" disabled id="btnRX" value="Guardar" onclick="if (document.getElementById('67').checked) {
                                    GuardaExamen(67, document.getElementById('text_rx').value);
                                    this.disabled = true;
                                }"></td>
                    </tr>
                </table>
                <div id="DivMuestraExamen">
                    <p></p>
                </div>
            </fieldset>


            </body>

            <jsp:include page="Footer.jsp" />

            <%
                }
            %>

