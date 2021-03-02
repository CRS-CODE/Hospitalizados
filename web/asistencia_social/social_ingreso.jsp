<%-- 
    Document   : social_ingreso
    Created on : 26-ago-2014, 15:13:37
    Author     : Informatica
--%>

<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaDato.cEpicrisis"%>
<%@page import="CapaDato.cDocumento"%>
<%@page import="CapaDato.cRegistroSocial"%>
<%@page import="CapaDato.cPaciente"%>
<%@page import="CapaDato.cComuna"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="java.text.DateFormat"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("<script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("../index.jsp?timeout=1");
    } else {
        String obtiene_usuario = session1.getAttribute("usuario_rut").toString();

        String titulo = " style=' background-color: #4169E1 ; color: white '  ";
        String datos = " style=' background-color: #87CEFA ; color: black '  ";

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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="../Header.jsp" />
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<style>
    .left{
        float: left;
    }
    .right{
        float: right;
        text-align: left
    }
    .center{
    }
</style>


<body onload="inicio_social();" >

<legend>INGRESO REGISTRO SOCIAL</legend>
<div id="div_cargando" ></div>

<% //
    if (request.getParameter("txt_duo") != null) {
        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
        cRegistroSocial soc = neg.obtiene_registro_social(id_duo);

        cDuo duo = neg.obtiene_duo(id_duo);
        ArrayList lista_consultorio = neg.lista_consultorio_pertenecia();
        ArrayList lista_comuna = neg.lista_comuna();

%>


<div class="container">
    <div class="left"  >
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
    </div>
    <div class="right"  >
        <%            if (soc.getId_registro() != 0) {
        %>
        <form  action='../PDF_registro_social' id="form_documento_registro" method='POST' target='_blank' >
            <input type='hidden' value='<%=id_duo%>' name='txt_duo'> 
            <a href="#" onclick="document.getElementById('form_documento_registro').submit();
                    return false"  >
                <img src='../Imagenes/pdf.png' alt="Ver documento"    >
                <b>Ver Documento</b>
            </a>
        </form>  

        <%
            }

        %>
    </div>
    <div class="center">

        <form name="form_registro_social" method="GET" action="<% out.write(neg.getLocal() + "ingreso_caso");%>" onsubmit="return valida_registro_social()"  >
            <%
                if (soc.getId_registro() == 0) {
                    // ingresa
                    out.write("<input type='hidden' name='txt_modo' value='21' >");
                } else {
                    //modifica
                    out.write("<input type='hidden' name='txt_modo' value='22' >");
                }
            %>

            <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=obtiene_usuario%>" >
            <input type="hidden" name="txt_duo" id="txt_duo" value="<%=id_duo%>" >
            <input type="hidden" name="txt_rut" id="txt_rut" value="<%=duo.getRut_paciente()%>" >
            <input type="hidden" name="txt_id_registro" id="txt_id_registro" value="<%=soc.getId_registro()%>" >

            <table BORDER="1"  >
                <tr>
                    <td colspan="6" <%=titulo%> >I.- IDENTIFICACION PERSONAL</td>
                </tr>
                <tr>
                    <td <%=datos%>>Nombre</td>
                    <td colspan="3" > <% out.write(duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%> </td>
                    <td <%=datos%>>Cama</td>
                    <td><% out.write("" + duo.getCama_descripcion());%></td>
                </tr>
                <tr>
                    <td <%=datos%>>Rut</td>
                    <td><% out.write("" + duo.getRut_paciente());%>
                        <input type="hidden" name="txt_paciente_rut" id="txt_paciente_rut" value="<% out.write("" + duo.getRut_paciente());%>" >
                    </td>
                    <td <%=datos%>>Edad</td>
                    <td><% out.write("" + duo.getEdad());%></td>
                    <td <%=datos%>>Fech. Nacimiento</td>
                    <td><% out.write("" + duo.getFecha_nac());%></td>
                </tr>
                <tr>
                    <td <%=datos%>> Domicilio</td>
                    <td colspan="5" ><% out.write("" + duo.getDireccion());%></td>

                </tr>
                <tr>
                    <td <%=datos%>>Comuna</td>
                    <td colspan="3" > <% out.write("" + duo.getComuna_descri());%> </td>
                    <td <%=datos%>>--Previsión</td>
                    <td>
                        <select id='cbo_prevision' name='cbo_prevision'>
                            <%
                                //   cPaciente pac = neg.obtiene_paciente(obtiene_rut);
                                String a_tramo = "";
                                int a_prais = -1;
                                String seleccionador_A = "";
                                String seleccionador_B = "";
                                String seleccionador_C = "";
                                String seleccionador_D = "";
                                String seleccionador_0 = "";
                                String seleccionador_prais = "";
                                if (a_tramo.contains("A")) {
                                    seleccionador_A = " selected='selected' ";
                                } else if (a_tramo.contains("B")) {
                                    seleccionador_B = " selected='selected' ";
                                } else if (a_tramo.contains("C")) {
                                    seleccionador_C = " selected='selected' ";
                                } else if (a_tramo.contains("D")) {
                                    seleccionador_D = " selected='selected' ";
                                } else if (a_tramo.contains("0")) {
                                    seleccionador_0 = " selected='selected' ";
                                } else if (a_prais == 1) {
                                    seleccionador_prais = " selected='selected' ";
                                }

                                String sel_a = "", sel_b = "", sel_c = "", sel_d = "", sel_0 = "", sel_prais = "";

                                if (duo.getPrais() == 1) {
                                    sel_prais = " selected='selected' ";
                                } else if (duo.getTramo_prevision().equals("A")) {
                                    sel_a = " selected='selected' ";
                                } else if (duo.getTramo_prevision().equals("B")) {
                                    sel_b = " selected='selected' ";
                                } else if (duo.getTramo_prevision().equals("C")) {
                                    sel_c = " selected='selected' ";
                                } else if (duo.getTramo_prevision().equals("D")) {
                                    sel_d = " selected='selected' ";
                                } else if (duo.getTramo_prevision().equals("0")) {
                                    sel_0 = " selected='selected' ";
                                }

                                out.write("<option value=\"-1\">Seleccione...");
                                out.write("<OPTION VALUE=\"00110A0\" " + seleccionador_A + " " + sel_a + " >FONASA A");  // <!--los 4 primeros es el cdigo fonasa, el quinto el tramo y el sexto Prais o no-->
                                out.write("<OPTION VALUE=\"00110B0\" " + seleccionador_B + " " + sel_b + " >FONASA B");
                                out.write("<OPTION VALUE=\"00110C0\" " + seleccionador_C + " " + sel_c + " >FONASA C");
                                out.write("<OPTION VALUE=\"00110D0\" " + seleccionador_D + " " + sel_d + " >FONASA D");
                                out.write("<OPTION VALUE=\"0190100\" " + seleccionador_0 + " " + sel_0 + " >PARTICULAR/ISAPRE");
                                out.write("<OPTION VALUE=\"00110A1\" " + seleccionador_prais + " " + sel_prais + " >PRAIS");

                            %>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td <%=datos%>>Est. Civil</td>
                    <td  colspan="3" >
                        <select name="cbo_estado_civil" id="cbo_estado_civil" >
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Soltero(a)</option>
                            <option value="2" >Casado(a)</option>
                            <option value="3" >Divorciado(a)</option>
                            <option value="4" >Separado(a)</option>
                            <option value="5" >Viudo(a)</option>                
                        </select>
                        <script>
                            $("#cbo_estado_civil option[value=<%=soc.getEstado_civil()%>]").attr("selected", true);
                        </script>
                    </td>
                    <td <%=datos%>>Situación Laboral</td>
                    <td colspan="2" >
                        <select name="cbo_situacion_laboral" id="cbo_situacion_laboral" >
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Activo</option>
                            <option value="2" >Cesante</option>
                            <option value="3" >Jubilado</option>
                            <option value="0" >Otro</option>
                        </select>
                        <script>
                            $("#cbo_situacion_laboral option[value=<%=soc.getSituacion_laboral()%>]").attr("selected", true);
                        </script>
                    </td>
                </tr>
                <tr>
                    <td <%=datos%>>Consultorio</td>
                    <td colspan="3" >
                        <select name="cbo_consultorio" id="cbo_consultorio" >
                            <option value="-1">Seleccione...</option>
                            <%
                                Iterator it_con = lista_consultorio.iterator();
                                String sel = "";
                                while (it_con.hasNext()) {
                                    cConsultorio con = (cConsultorio) it_con.next();
                                    if (duo.getConsultorio() == con.getId()) {
                                        sel = " selected='selected' ";
                                    }
                                    out.write("<option value='" + con.getId() + "' " + sel + " >" + con.getDescripcion() + "</option>");
                                }
                            %>
                        </select>     
                        <script>
                            $("#cbo_consultorio option[value=<%=duo.getConsultorio()%>]").attr("selected", true);
                        </script>
                    </td>

                    <td <%=datos%> >Institucionalizado</td>
                    <td colspan="2" >
                        <select name="cbo_institucionalizado" id="cbo_institucionalizado" >
                            <OPTION value="-1" >Seleccione...</OPTION>
                            <OPTION value="1" >Si</OPTION>
                            <OPTION value="0" >No</OPTION>
                        </select>
                        <script>
                            $("#cbo_institucionalizado option[value=<%=soc.getInstitucionalizado()%>]").attr("selected", true);
                        </script>
                    </td>
                </tr>
                <tr id="tr_institucion" >
                    <td <%=datos%>>Nombre Institución</td>
                    <td colspan="5" >
                        <input type="text" name="txt_institucion" id="txt_institucion" value="<%=soc.getInstitucion_nombre()%>" style=" width: 300px"/>
                    </td>
                </tr>


                <%  //23012015 %>
                <tr>
                    <td <%=datos%>>Hospital de Origen</td>
                    <td  colspan="3" >

                        <input type="text" name="txt_hospital_origen" id="txt_hospital_origen" value="<%=soc.getHospital_origen_desc()%>" style=" width: 300px"  >
                    </td>
                    <td <%=datos%>>Teléfono</td>
                    <td colspan="2" >
                        <input type="text" name="txt_telefono1" id="txt_telefono1" value="<%=duo.getTelefono1()%>"  >
                    </td>
                </tr>

                <tr>
                    <td <%=datos%>>A.S. Encargada</td>
                    <td  colspan="3" >
                        <input type="text" name="txt_asistente_social" id="txt_asistente_social" value="<%=soc.getNombre_asistente()%>" style=" width: 300px"  >
                    </td>
                    <td <%=datos%>>Teléfono</td>
                    <td colspan="2" >
                        <input type="text" name="txt_telefono2" id="txt_telefono2" value="<%=duo.getTelefono2()%>" >
                    </td>
                </tr>
                <tr>
                    <td <%=datos%>>Mail</td>
                    <td  colspan="5" >
                        <input type="text" name="txt_mail" id="txt_mail" value="<%=duo.getMail()%>" style=" width: 300px" >
                    </td>
                </tr>
                <tr>
                    <td <%=datos%>>Diagnóstico</td>
                    <td  colspan="5" >
                        <input type="text" name="txt_diagnostico" id="txt_diagnostico" value="<%=soc.getDiagnostico()%>" style=" width: 700px" >
                    </td>
                </tr>

                <tr>
                    <td <%=datos%>>Fecha Egreso CRS</td>
                    <td  colspan="3" >
                        <input type="text" name="txt_fecha_egreso" id="txt_fecha_egreso" value="<%=soc.getFecha_egreso()%>"  >

                    </td>
                    <td <%=datos%>>Destino</td>
                    <td colspan="2" >
                        <select name="cbo_destino" id="cbo_destino">
                            <option value="0">Seleccione...</option>
                            <%
                                ArrayList lista_destino = neg.lista_destino();

                                Iterator it_des = lista_destino.iterator();
                                cConsultorio con = new cConsultorio(); // utilizado para setar valores del destino
                                while (it_des.hasNext()) {
                                    con = (cConsultorio) it_des.next();
                                    out.write(" <option value='" + con.getId() + "' >" + con.getDescripcion() + "</option>");
                                }
                            %>
                        </select>
                        <script>
                            $("#cbo_destino option[value=<%=soc.getDestino()%>]").attr("selected", true);
                        </script>
                    </td>
                </tr>



                <tr>
                    <td colspan="6" <%=titulo%> >II.- IDENTIFICACION FAMILIAR</td>
                </tr>

                <tr>
                    <td <%=datos%>>Vive</td>
                    <td>
                        <select name="cbo_vive" id="cbo_vive" >
                            <OPTION value="-1" >Seleccione...</OPTION>
                            <OPTION value="1" >Solo</OPTION>
                            <OPTION value="0" >Con otros</OPTION>
                        </select>
                        <script>
                            $("#cbo_vive option[value=<%=soc.getVive()%>]").attr("selected", true);
                        </script>
                    </td>
                    <td <%=datos%>>Tiene Hijos</td>
                    <td>
                        <select name="cbo_hijo" id="cbo_hijo" onchange="oculta_hijo()"  >
                            <OPTION value="-1" >Seleccione...</OPTION>
                            <OPTION value="1" >Si</OPTION>
                            <OPTION value="0" >No</OPTION>
                        </select>
                        <script>
                            $("#cbo_hijo option[value=<%=soc.getHijos()%>]").attr("selected", true);



                        </script>

                    </td>
                    <td <%=datos%>> <label id="lbl_cantidad" >Cantidad</label> </td>
                    <td><input type="text" name="txt_cantidad" id="txt_cantidad" value="<%=soc.getHijos_cantidad()%>" /> </td>

                <script>

                </script>
                </tr>
                <tr>
                    <td  colspan="6" >
                        <fieldset>
                            <legend>Datos</legend>
                            <table border="0" id="tbl_contacto"    >
                                <tr>
                                    <td rowspan="3" <%=datos%> >
                                        Datos<br>contacto<br>
                                        <img src="../Imagenes/contacto.png" width="34" height="34" alt="contacto"/>
                                    </td>
                                    <td <%=datos%>>Nombre</td>
                                    <td><input type="text" name="txt_contacto_nombre"  id="txt_contacto_nombre" value="" size="40" /></td>
                                    <td <%=datos%>>Parentesco</td>
                                    <td> 
                                        <select name="cbo_contacto_parentesco" id="cbo_contacto_parentesco" style=" width: 85% " >
                                            <option value="-1" >Seleccione...</option>
                                            <option value="Padre" >Padre</option>                
                                            <option value="Madre" >Madre</option>
                                            <option value="Esposo(a)" >Esposo(a)</option>
                                            <option value="Hijo(a)" >Hijo(a)</option>
                                            <option value="Hermano(a)" >Hermano(a)</option>
                                            <option value="Sobrino(a)" >Sobrino(a)</option>
                                            <option value="Tío(a)" >Tío(a))</option>
                                            <option value="Primo(a)" >Primo(a)</option>
                                            <option value="Abuelo(a)" >Abuelo(a)</option>
                                            <option value="Nieto(a)" >Nieto(a)</option>
                                            <option value="Cuñado(a)" >Cuñado(a)</option>
                                            <option value="Suegro(a)" >Suegro(a)</option>
                                            <option value="Nuera" >Nuera</option>
                                            <option value="Yerno" >Yerno</option>
                                            <option value="Otro" >Otro</option>
                                        </select>

                                    </td>
                                    <td rowspan='3' >
                                        <img src="../Imagenes/add.png" width="40" height="40" alt="add" onclick="lista_contacto(1)" />
                                    </td>
                                </tr>
                                <tr>
                                    <td <%=datos%>>Fono 1</td>
                                    <td> <input type="text" name="txt_contacto_fono1" id="txt_contacto_fono1" value="" /></td>
                                    <td <%=datos%>>Fono 2</td>
                                    <td> <input type="text" name="txt_contacto_fono2"  id="txt_contacto_fono2" value="" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                                <tr>
                                    <td <%=datos%>>Dirección</td>
                                    <td> <input type="text" name="txt_contacto_direccion" id="txt_contacto_direccion" value=""  size="40"/> </td>
                                    <td <%=datos%>>Comuna</td>
                                    <td> 
                                        <select name="cbo_contacto_comuna" id="cbo_contacto_comuna" style=" width: 85% ">
                                            <option value="-1" >Seleccione...</option>
                                            <%

                                                Iterator it_com = lista_comuna.iterator();
                                                while (it_com.hasNext()) {
                                                    cComuna com = (cComuna) it_com.next();
                                                    if (com.getComuna_codigo().equals("13119")) {
                                                        out.write("<option value='" + com.getComuna_codigo() + "' selected='selected' >" + com.getComuna_descripcion() + "</option>");

                                                    } else {
                                                        out.write("<option value='" + com.getComuna_codigo() + "' >" + com.getComuna_descripcion() + "</option>");

                                                    }
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" >
                        <div id="div_contacto" ></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" <%=titulo%> >III.- BREVE DESCRIPCION DE SITUACION ACTUAL </td>
                </tr>
                <tr>
                    <td colspan="6" >
                        <textarea name="txa_situacion" rows="5" cols="120"><% out.write("" + soc.getSituacion());%></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" <%=titulo%> >IV.- PLAN DE TRABAJO</td>
                </tr>
                <tr>
                    <td colspan="6" >
                        <textarea name="txa_plan" id="txa_plan"  rows="5" cols="120"><% out.write("" + soc.getPlan());%></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" <%=titulo%> >V.- SEGUIMIENTO Y EVOLUCION</td>
                </tr>

                <tr>
                    <td  <%=titulo%> >FECHA</td>
                    <td colspan="5" <%=titulo%> >DESCRIPCION</td>
                </tr>

                <tr>
                    <td>
                        <img src="../Imagenes/calendar.png" width="21" height="21" alt="calendario" onclick="$('#txt_seguimiento_fecha').focus()" />
                        <input type="text" name="txt_seguimiento_fecha" id="txt_seguimiento_fecha" value="" size="12" />
                    </td>
                    <td colspan="4" >
                        <textarea name="txa_seguimiento_descripcion" id="txa_seguimiento_descripcion" rows="2" cols="75"></textarea>
                    </td>
                    <td>
                        <img src="../Imagenes/add.png" width="40" height="40" alt="add" onclick="lista_seguimiento(1)" />
                    </td>
                </tr>

                <tr>
                    <td colspan="6" >
                        <div id="div_seguimiento"></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" >  <center> <input class="btn btn-primary"  type="submit" name="btn_grabar" value="Grabar" ></center> </td>
                </tr>
            </table>
        </form>

    </div>
</div>





<%

} else if (request.getParameter("user") != null) {
    ArrayList lista_doc = neg.lista_documentos_paciente(request.getParameter("user"));
    Iterator it_doc = lista_doc.iterator();

    if (lista_doc.isEmpty()) {
%>
<form name="form_social" action="" method="POST" >
    <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
    <input value="d" name="txtDV" id="txtDV" type="hidden">
    <input name="user" class="user" id="id_txt_user"  type="text" size="20" maxlength="12" autocomplete="off" onkeyup="formateaRut(this.value)" value=""   >
    <input class="btn btn-primary" type="submit" value="Buscar" name="btn_rut_paciente" />
</form> 
<%
    out.write("<h3>No se encontraron datos de observación para este paciente</h3>");
} else {

    out.write("<h3>Seleccione DUO</h3>");
    out.write("<table cellpadding='12px'  >");
    out.write("<tr>");
    while (it_doc.hasNext()) {
        cEpicrisis doc = (cEpicrisis) it_doc.next();
        out.write("<td>");
%>
<TABLE>
    <TR>
        <TD <%=titulo%> >
            <% //   //
                out.write("<b>DUO " + doc.getId_duo() + "</b>");
            %>
        </TD>
    </TR>
    <TR>
        <TD align="center" <%=datos%> >
            <form name="form_duo<%=doc.getId_duo()%>" id="form_duo<%=doc.getId_duo()%>" method="POST" action="" >
                <input type="hidden" name="txt_duo" id="txt_duo"  value="<%=doc.getId_duo()%>">
                <img src="../Imagenes/docu.png" width="32" height="32" alt="docu" onclick="document.getElementById('form_duo<%=doc.getId_duo()%>').submit();" />
            </form>
        </TD>
    </TR>
    <tr>
        <td>
            <% //   //
                out.write("<b>" + doc.getFecha_duo() + "</b>");
            %>
        </td>
    </tr>
</TABLE>
<%
            out.write("</td>");
        }
        out.write("</tr>");
        out.write("</table>");

    }

} else {
%> 
<h3>Ingrese Rut de paciente</h3>
<form name="form_social" action="" method="POST" >
    <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
    <input value="d" name="txtDV" id="txtDV" type="hidden">
    <input name="user" class="user" id="id_txt_user"  type="text" size="20" maxlength="12" autocomplete="off" onkeyup="formateaRut(this.value)" value=""   >
    <input class="btn btn-primary" type="submit" value="Buscar" name="btn_rut_paciente" />
</form> 

<%
    }

%>

<script>
    $(function() {
        $("#txt_seguimiento_fecha").datepicker();
    });
    $(function() {
        $("#txt_fecha_egreso").datepicker();
    });
    function inicio_social() // java script function is created
    {
        // document.getElementById('lbl_cantidad').style.visibility = 'hidden';
        //  document.getElementById('txt_cantidad').style.visibility = 'hidden';
        if ($("#txt_duo").val() != null && $("#txt_paciente_rut").val() != null) {
            lista_contacto(0);
            lista_seguimiento(0);
        }

        // document.getElementById('tr_contacto').style.visibility = 'hidden';
    }

    function oculta_hijo() // java script function is created
    {
        if ($("#cbo_hijo").val() == 1) {
            document.getElementById('lbl_cantidad').style.visibility = 'visible';
            document.getElementById('txt_cantidad').style.visibility = 'visible';
        } else {
            document.getElementById('lbl_cantidad').style.visibility = 'hidden';
            document.getElementById('txt_cantidad').style.visibility = 'hidden';
        }
    }


    function lista_contacto(ingreso) {

        var obj = creaObjetoAjax();
        var rut = $("#txt_paciente_rut").val();
        if (ingreso == 1) {
            var nombre = $("#txt_contacto_nombre").val();
            var parentesco = $("#cbo_contacto_parentesco").val();
            var fono1 = $("#txt_contacto_fono1").val();
            var fono2 = $("#txt_contacto_fono2").val();
            var direccion = $("#txt_contacto_direccion").val();
            var comuna = $("#cbo_contacto_comuna").val();
            misdatos = "ingresa=" + ingreso + "&paciente_rut=" + rut + "&nombre=" + nombre + "&parentesco=" + parentesco +
                    "&fono1=" + fono1 + "&fono2=" + fono2 + "&direccion=" + direccion + "&comuna=" + comuna;
            //alert(misdatos);
            if (parentesco == -1) {
                alert('Seleccione parentesco');
                return false;
            } else if (nombre == "" || fono1 == "" || fono2 == "" || direccion == "" || comuna == "") {

                if (confirm("CONFIRMACION ! Hay campos sin rellenar, desea ingresar el contacto de todos modos ? \n \n ")) {
                } else {
                    return false;
                }
            } else {
                if (confirm("CONFIRMACION ! Desea ingresar el contacto ? \n \n ")) {
                } else {
                    return false;
                }
            }

        } else {
            misdatos = "ingresa=0" + "&paciente_rut=" + rut;
        }
        //Preparar el envio  con Open
        obj.open("POST", "lista_contacto.jsp", true);
        //Enviar cabeceras para que acepte POST:
        obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        obj.setRequestHeader("Content-length", misdatos.length);
        obj.setRequestHeader("Connection", "close");
        obj.send(misdatos); //pasar datos como parámetro

        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '';
                document.getElementById('div_contacto').innerHTML = obj.responseText;
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById('div_contacto').innerHTML = '' + obj.statusText; // estado 12-02-2013

                $("#txt_contacto_nombre").val("");
                $("#cbo_contacto_parentesco").val("");
                $("#txt_contacto_fono1").val("");
                $("#txt_contacto_fono2").val("");
                $("#txt_contacto_direccion").val("");
                $("#cbo_contacto_comuna").val("");

            } else {
                //procesando...
                document.getElementById('div_contacto').innerHTML = '&nbsp;&nbsp; <img src="../Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
    }


    function lista_seguimiento(ingreso) {

        var obj = creaObjetoAjax();
        var id_duo = $("#txt_duo").val();
        if (ingreso == 1) {
            var fecha = $("#txt_seguimiento_fecha").val();
            var descripcion = $("#txa_seguimiento_descripcion").val();
            var usuario = $("#txt_usuario").val();

            misdatos = "ingresa=" + ingreso + "&id_duo=" + id_duo + "&fecha=" + fecha + "&descripcion=" + descripcion + "&usuario=" + usuario;
            // alert(misdatos);
            // return false;
            if (fecha == "") {
                alert('Debe ingresar fecha al seguimiento');
                return false;
            }
            if (descripcion == "") {
                alert('Debe ingresar descripción al seguimiento');
                return false;
            }
            if (confirm("CONFIRMACION ! Desea ingresar este registro de seguimiento ? \n \n ")) {
            } else {
                return false;
            }
        } else {
            misdatos = "ingresa=0" + "&id_duo=" + id_duo;
        }


        //Preparar el envio  con Open
        obj.open("POST", "lista_seguimiento.jsp", true);
        //Enviar cabeceras para que acepte POST:
        obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        obj.setRequestHeader("Content-length", misdatos.length);
        obj.setRequestHeader("Connection", "close");
        obj.send(misdatos); //pasar datos como parámetro


        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '';
                document.getElementById('div_seguimiento').innerHTML = obj.responseText;
                $("#txt_seguimiento_descripcion").val("");
                $("#txt_seguimiento_fecha").val();
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById('div_seguimiento').innerHTML = '' + obj.statusText; // estado 12-02-2013

            } else {
                //procesando...
                document.getElementById('div_seguimiento').innerHTML = '&nbsp;&nbsp; <img src="../Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
    }

    function eliminar_seguimiento(id_seguimiento) {

        var obj = creaObjetoAjax();
        var id_duo = $("#txt_duo").val();
        if (confirm("CONFIRMACION ! Desea eliminar este registro ? \n \n ")) {

        } else {
            return false;
        }
        misdatos = "ingresa=2" + "&id_duo=" + id_duo + "&id_seguimiento=" + id_seguimiento;
        // alert(misdatos);

        //Preparar el envio  con Open
        obj.open("POST", "lista_seguimiento.jsp", true);
        //Enviar cabeceras para que acepte POST:
        obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        obj.setRequestHeader("Content-length", misdatos.length);
        obj.setRequestHeader("Connection", "close");
        obj.send(misdatos); //pasar datos como parámetro


        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '';
                document.getElementById('div_seguimiento').innerHTML = obj.responseText;
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById('div_seguimiento').innerHTML = '' + obj.statusText; // estado 12-02-2013
                // $("#txt_seguimiento_descripcion").val("");
                //  $("#txt_seguimiento_fecha").val();
            } else {
                //procesando...
                document.getElementById('div_seguimiento').innerHTML = '&nbsp;&nbsp; <img src="../Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
    }




    function valida_registro_social() {
        var estado_civil = $("#cbo_estado_civil").val();
        var situacion = $("#cbo_situacion_laboral").val();
        var institucionalizado = $("#cbo_institucionalizado").val();
        var consultorio = $("#cbo_consultorio").val();

        if (estado_civil === -1) {
            alert('Debe seleccionar estado civil');
            return false;
        } else if (situacion === -1) {
            alert('Debe seleccionar situación laboral');
            return false;
        } else if (consultorio === -1) {
            alert('Debe seleccionar consultorio');
            return false;
        } else if (institucionalizado === -1) {
            alert('Debe seleccionar institucionalizado');
            return false;
        }

        if (confirm("CONFIRMACION ! Desea ingresar este registro ? \n \n ")) {
        } else {
            return false;
        }

    }


</script>


</body>

<jsp:include page="../Footer.jsp" />

<%    }
%>
