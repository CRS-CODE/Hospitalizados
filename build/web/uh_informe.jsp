<%-- 
    Document   : uh_informe
    Created on : 10-may-2012, 11:50:18
    Author     : EseGamboa
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="CapaNegocio.Negocio"%>


<% Negocio neg = new Negocio();
    String titulo = " style=' background-color: #f7903b ; color: white '  ";
    String datos = " style=' background-color: #fcd5b6 ; color: black '  ";

%>
<jsp:include page="Header.jsp" />
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/script1.js"></script>
<script type="text/javascript" src="js/calendario/calendar.js"></script>
<script type="text/javascript" src="js/calendario/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendario/calendar-es.js"></script>
<style type="text/css"> @import url("js/calendario/calendar-win2k-cold-2.css"); </style>
<body>
<legend>INFORME</legend>


<table>
    <TR>
        <TD id="111" >

            <fieldset>
                <legend>INFORMES</legend>

                <form name="form_excel_eco" action=""  method="POST" onsubmit="return valida_form('id_fecha_inicial', 'id_fecha_final', 'opcion');
                        " >

                    <table border="0" cellspacing="15" >
                        <thead>
                            <tr <%=titulo%> >
                                <th colspan="2" >TIPO INFORME</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr >
                                <td colspan="2" >
                                    <select name="cbo_informe" id="opcion" onchange="this.form.action = this.value;
                                            ">
                                        <option value="0">Seleccione...</option>
                                        <option value="XLS_AdmissionHospitalization">Ingresos a Hospitalizaciòn</option>
                                        <option value="XLS_DuosDadoAltaSimple">Duos Dado de Alta Simple</option>
                                        <option value="XLS_DuosDadoAltaDatosEpicrisis">Duos Dado de Alta con Datos Epicrisis</option>
                                        <option value="Informe/InfDuoxOrigenxls.jsp">Duos por Origen</option>
                                        <option value="XLS_DuosDadoAltaSimpleAsistenciaSocial">Duos Dado de Alta Simple + Registro Social</option>  
                                        <option value="XLS_InformeDas?modo=1">Nomina Das por Fecha Ingreso</option>
                                        <option value="XLS_InformeDas?modo=2">Nomina Das por Fecha Egreso</option>
                                        <option value="ClinicalData">Clinical Data</option>
                                    </select>
                                </td>
                            </tr>
                            <tr <%=titulo%> >
                                <th>FECHA INICIAL</th>
                                <th>FECHA FINAL</th>
                            </tr>
                            <tr>
                                <td>

                                    <input type="text" name="fecha1" id="id_fecha_inicial" onchange="document.getElementById('fecha1_dma').value = document.getElementById('id_fecha_inicial').value" readonly="1" />
                                    <img src="Imagenes/calendar.png" id="f_trigger_a" style="cursor: pointer; " title="Fecha Inicial"
                                         onmouseover="this.style.background = 'red';" onmouseout="this.style.background = ''" />

                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField: "id_fecha_inicial", // id of the input field
                                            ifFormat: "%d/%m/%Y", // format of the input field  "%d/%m/%Y %I:%M",
                                            showsTime: true, // will display a time selector
                                            button: "f_trigger_a", // trigger for the calendar (button ID)
                                            singleClick: true, // double-click mode
                                            step: 1                // show all years in drop-down boxes (instead of every other year as default)
                                        });
                                    </script>
                                </td>
                                <td>

                                    <input type="text" name="fecha2" id="id_fecha_final" onchange="document.getElementById('fecha2_dma').value = document.getElementById('id_fecha_final').value" readonly="1" />
                                    <img src="Imagenes/calendar.png" id="f_trigger_b" style="cursor: pointer; " title="Fecha Final"
                                         onmouseover="this.style.background = 'red';" onmouseout="this.style.background = ''" />

                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField: "id_fecha_final", // id of the input field
                                            ifFormat: "%d/%m/%Y", // format of the input field  "%d/%m/%Y %I:%M",
                                            showsTime: true, // will display a time selector
                                            button: "f_trigger_b", // trigger for the calendar (button ID)
                                            singleClick: true, // double-click mode
                                            step: 1                // show all years in drop-down boxes (instead of every other year as default)
                                        });
                                    </script>
                                    <input type="hidden" name="fecha_inicio" id="fecha1_dma" value="" />
                                    <input type="hidden" name="fecha_fin" id="fecha2_dma" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="CENTER"  >
                                    <br>
                                    <input class="btn btn-primary"  type="submit" value="GENERAR EXCEL" name="btn_solicitud" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </fieldset>

        </TD>
        <TD id="222" align="center"   >

            <fieldset style=" width: 300px; "  >
                <legend>INFORMES POR DIA</legend>

                <form name="form_excel2" action=""  method="POST" onsubmit="return valida_form('id_fecha_busqueda', 'id_fecha_busqueda', 'opcion2');
                        ">
                    <table border="0" cellspacing="15" >
                        <thead>
                            <tr <%=titulo%> >
                                <th colspan="2" >TIPO INFORME</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2" >
                                    <select name="cbo_informe2" id="opcion2" onchange="this.form.action = this.value;
                                            ">
                                        <option value="0">Seleccione...</option>
                                        <option value="<%=neg.getLocal()%>XLS_VisitasEnfermeria">Visitas de Enfermeria x Día</option>
                                    </select>
                                </td>
                            </tr>
                            <tr <%=titulo%> >
                                <th colspan="2" >FECHA BÚSQUEDA</th>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"  >
                                    <input type="text" name="fecha_busqueda" id="id_fecha_busqueda" onchange="document.getElementById('fecha_busqueda_dma').value = document.getElementById('id_fecha_busqueda').value" readonly="1" />
                                    <img src="Imagenes/calendar.png" id="f_trigger_busqueda" style="cursor: pointer; " title="Fecha Búsqueda"
                                         onmouseover="this.style.background = 'red';" onmouseout="this.style.background = ''" />

                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField: "id_fecha_busqueda", // id of the input field
                                            ifFormat: "%d/%m/%Y", // format of the input field  "%d/%m/%Y %I:%M",
                                            showsTime: true, // will display a time selector
                                            button: "f_trigger_busqueda", // trigger for the calendar (button ID)
                                            singleClick: true, // double-click mode
                                            step: 1                // show all years in drop-down boxes (instead of every other year as default)
                                        });
                                    </script>
                                    <input type="hidden" name="fecha_busqueda_dma" id="fecha_busqueda_dma" value="" />
                                </td> 
                            </tr>

                            <tr>
                                <td colspan="2" align="center"  >
                                    <br>
                                    <input class="btn btn-primary"  type="submit" value="GENERAR EXCEL" name="btn_informe2" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </fieldset>


        </TD>
    </TR>
</table>


<fieldset>
    <legend>Categorizaciones</legend>

    <FORM id="form" name="form" action="<%=neg.getLocal()%>InfCatxls" method="post" ONSUBMIT="return valida_vacio();">

        <FIELDSET>
            <legend> Informe Categorización de Pacientes en Sala de Hospitalizacion</legend>
            <DIV class="FormTablas">
                <div class="SUBTITULOS">Seleccione el mes y el año, luego presione el boton Generar Informe.</div>
                <table>
                    <tr>
                        <td><select name="mes" id="mes">
                                <option value="0">Enero
                                <option value="1">Febrero
                                <option value="2">Marzo
                                <option value="3">Abril
                                <option value="4">Mayo
                                <option value="5">Junio
                                <option value="6">Julio
                                <option value="7">Agosto
                                <option value="8">Septiembre
                                <option value="9">Octubre
                                <option value="10">Noviembre
                                <option value="11">Diciembre
                            </select></td>
                        <td>
                            <select name="ano" id="ano">
                                <%

                                    Date d = new Date();
                                    GregorianCalendar bb = new GregorianCalendar();
                                    bb.setTime(d);
                                    int ann = bb.get(Calendar.YEAR);
                                    for (int k = ann; k > 2008; k--) {
                                        out.write("<option value='" + k + "'>" + k);
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                </table>
                <div class="SUBTITULOS"> </div>
            </DIV>
        </FIELDSET>

        <FIELDSET class="buttons">
            <INPUT name="Grabar" class="btn btn-primary" type="submit" value="Generar Informe">


        </FIELDSET>
    </FORM>

</fieldset>

</body>
<jsp:include page="Footer.jsp" />


