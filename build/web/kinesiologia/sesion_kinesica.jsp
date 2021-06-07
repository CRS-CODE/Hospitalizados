<%-- 
    Document   : sesion_kinesica
    Created on : 14-ago-2014, 14:59:25
    Author     : Informatica
--%>


<%@page import="java.text.DateFormat"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("../index.jsp?timeout=1");
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

<jsp:include page="../Header.jsp" />
<script>

    function lista_sesion(ingreso) {

        var obj = creaObjetoAjax();
        var id_duo = $("#txt_duo").val();
        if (ingreso === 1) {
            var detalle = $("#txa_detalle").val();
            var fecha = $("#txt_fecha").val();
            var hora = $("#cbo_hora").val() + ":" + $("#cbo_minuto").val() + ":00";
            var usuario = $("#txt_usuario").val();


            misdatos = "ingresa=" + ingreso + "&id_duo=" + id_duo + "&fecha=" + fecha + "&hora=" + hora + "&detalle=" + detalle + "&usuario=" + usuario;
            // alert(misdatos);



            if (fecha == "") {
                alert('Debe ingresar fecha de registro');
                return false;
            }

            if (detalle.indexOf("'") != -1) {
                alert('No puede ingresar comillas simples ni dobles');
                return false;
            }
            if (detalle.indexOf('"') != -1) {
                alert('No puede ingresar comillas simples ni dobles');
                return false;
            }

            if (detalle == "") {
                alert('Debe ingresar descripción al seguimiento');
                return false;
            }
            if (confirm("CONFIRMACION ! Desea ingresar este registro de sesión ? \n \n ")) {
            } else {
                return false;
            }
        } else {
            misdatos = "ingresa=0" + "&id_duo=" + id_duo;
        }
       
        //Preparar el envio  con Open
        obj.open("POST", "sesion_kinesica_carga.jsp", true);
        //Enviar cabeceras para que acepte POST:
        obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        obj.setRequestHeader("Content-length", misdatos.length);
        obj.setRequestHeader("Connection", "close");
        obj.send(misdatos); //pasar datos como parámetro


        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '';
                document.getElementById('div_seguimiento').innerHTML = obj.responseText;
                $("#txa_detalle").val("");

            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById('div_seguimiento').innerHTML = '' + obj.statusText; // estado 12-02-2013


            } else {
                //procesando...
                document.getElementById('div_seguimiento').innerHTML = '&nbsp;&nbsp; <img src="../Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
    }

    $(function() {
        $("#txt_fecha").datepicker();
    });
</script>

<%
    String titulo = " style=' background-color: #4169E1 ; color: white '  ";
    String datos = " style=' background-color: #87CEFA ; color: black '  ";
    int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
    cDuo duo = neg.obtiene_duo(id_duo);
%>
<body onload="lista_sesion(0)" >
<legend>Ingreso sesión kinésica</legend>
<table BORDER="0" style=" width: 95%" >
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
        <td <%=datos%>>DUO</td>
        <td> <% out.write("" + duo.getId_duo());%> </td>
    </tr>
</table>


<form  action='../PDF_sesion_kinesiologia' id="form_documento_registro" method='POST' target='_blank' >
    <input type='hidden' value='<%=id_duo%>' name='txt_duo' id="txt_duo" > 
    <a href="#" onclick="document.getElementById('form_documento_registro').submit();
            return false"  >
        <img src='../Imagenes/pdf.png' alt="Ver documento"    >
        <b>Ver Documento</b>
    </a>
</form>  
<div id="div_cargando" ></div>
<form name="form_sesion_kinesica" action="#" method="POST" onsubmit="return false"  >
    <input type="hidden" name="txt_modo" value="4" >
    <input type="hidden" name="txt_duo" value="<%=duo.getId_duo()%>" >
    <input type="hidden" name="txt_usuario"  id="txt_usuario"  value="<%=session1.getAttribute("usuario_rut")%>" >
    <table  style=" width: 95%" border="0" >
        <tr>
            <td colspan="5" <%=titulo%> >Datos de la sesión</td>
        </tr>
        <tr VALIGN="TOP" >
            <td><b>Fecha/Hora</b></td>
            <td><b>Detalle</b></td>
            <td>--</td>
        </tr>
        <tr VALIGN="TOP">
            <td> 
                <img src="../Imagenes/calendar.png" width="21" height="21" alt="calendario" onclick="$('#txt_fecha').focus()" />
                <input type="text" name="txt_fecha" id="txt_fecha" value="" size="12" />

            </td>

            <td rowspan="2" >
                <textarea name="txa_detalle" id="txa_detalle"  cols="110" rows="5"  ></textarea>
            </td>
            <td rowspan="2">
                <center> <input class="btn btn-primary" type="submit" value="GRABAR" name="btn4_grabar" 
                                onclick="lista_sesion(1);"  />
                </center>

            </td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <select name="cbo_hora" id="cbo_hora" >
                    <%
                        for (int k = 0; k < 24; k++) {
                            out.write("<option value='" + neg.dig_cero(k) + "' >" + neg.dig_cero(k) + "</option>");
                        }
                    %>
                </select>:
                <select name="cbo_minuto" id="cbo_minuto" >
                    <%
                        for (int k = 0; k < 60; k++) {
                            out.write("<option value='" + neg.dig_cero(k) + "' >" + neg.dig_cero(k) + "</option>");
                        }
                    %>
                </select>
            </td>
        </tr>




        <tr>
            <td colspan="4" > <br>
            </td>
        </tr>
    </table>
</form>
<div id="div_seguimiento" ></div>    
</body>

<jsp:include page="../Footer.jsp" />

<%
    }
%>
