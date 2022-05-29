<%-- 
    Document   : uh_visita
    Created on : 10-may-2012, 11:48:38
    Author     : EseGamboa
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="../Header.jsp" />

<%
    int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
    cDuo duo = neg.obtiene_duo(id_duo);

%>

<script>

    function valida_traumatologica() {

        if ($("#txa3_observacion_inicial").val() === "") {
            alert('Ingrese observación inicial');
            $("#txa3_observacion_inicial").focus();
            return false;
        } else if ($("#txa3_historial").val() === "") {
            alert('Ingrese historial del usuario');
            $("#txa3_historial").focus();
            return false;
        } else if ($("#txa3_dolor").val() === "") {
            alert('Ingrese dolor');
            $("#txa3_dolor").focus();
            return false;
        } else if ($("#txa3_prueba_especial").val() === "") {
            alert('Ingrese pruebas especiales');
            $("#txa3_prueba_especial").focus();
            return false;
        } else if ($("#txa3_palpacion").val() === "") {
            alert('Ingrese palpación');
            $("#txa3_palpacion").focus();
            return false;
        } else if ($("#txt3_marcha").val() === "") {
            alert('Ingrese marcha');
            $("#txt3_marcha").focus();
            return false;
        } else if ($("#txa3_plano_frontal").val() === "") {
            alert('Ingrese plano frontal');
            $("#txa3_plano_frontal").focus();
            return false;
        } else if ($("#txa3_plano_sagital").val() === "") {
            alert('Ingrese plano sagital');
            $("#txa3_plano_sagital").focus();
            return false;
        } else if ($("#txa3_plano_posterior").val() === "") {
            alert('Ingrese posterior');
            $("#txa3_plano_posterior").focus();
            return false;
        } else if ($("#txa3_movimiento_activo").val() === "") {
            alert('Ingrese movimiento activo');
            $("#txa3_movimiento_activo").focus();
            return false;
        } else if ($("#txa3_movimiento_pasivo").val() === "") {
            alert('Ingrese movimiento pasivo');
            $("#txa3_movimiento_pasivo").focus();
            return false;
        } else if ($("#txa3_observacion").val() === "") {
            alert('Ingrese observaciones');
            $("#txa3_observacion").focus();
            return false;
        } else if ($("#txa3_dermatoma").val() === "") {
            alert('Ingrese dermatoma');
            $("#txa3_dermatoma").focus();
            return false;
        } else if ($("#txa3_miotoma").val() === "") {
            alert('Ingrese miotoma');
            $("#txa3_miotoma").focus();
            return false;
        } else if ($("#txa3_reflejo_osteotendineo").val() === "") {
            alert('Ingrese reflejo osteotendineo');
            $("#txa3_reflejo_osteotendineo").focus();
            return false;
        } else if ($("#txa3_test_neurodinamico").val() === "") {
            alert('Ingrese test neurodinamico');
            $("#txa3_test_neurodinamico").focus();
            return false;
        } else if ($("#txa3_diagnostico_imagen").val() === "") {
            alert('Ingrese diagnostico por imagen');
            $("#txa3_diagnostico_imagen").focus();
            return false;
        } else if ($("#txa3_diagnostico_kinesico").val() === "") {
            alert('Ingrese diagnotico kinesico');
            $("#txa3_diagnostico_kinesico").focus();
            return false;
        }

        if (confirm("CONFIRMACION ! Desea ingresar este registro ? \n \n ")) {
        } else {
            return false;
        }

    }

</script>

<body onload="" >


    <%      //
        String titulo = " style=' background-color: #f7903b ; color: white '  ";
        String datos = " style=' background-color: #fcd5b6 ; color: black '  ";
    %>

<legend>INGRESO EVALUACION TRAUMATOLOGICA</legend>
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

<form name="form_evaluacion_traumatologica" action="<%=neg.getLocal() + "ingreso_caso"%>" method="POST" onsubmit="return valida_traumatologica()"  >
    <input type="hidden" name="txt_modo" value="3" >
    <input type="hidden" name="txt_duo" value="<%=id_duo%>" >
    <input type="hidden" name="txt_usuario" value="<%=session1.getAttribute("usuario_rut")%>" >
    <table border="0" style=" width:  95%"  >
        <tr>
            <td colspan="4" <%=titulo%> >Datos de la evaluación</td>
        </tr>
        <tr>
            <td>
                Observación <br>Inicial  
            </td>
            <td colspan="3"  >
                <textarea name="txa3_observacion_inicial" id="txa3_observacion_inicial"  rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Historial <br>del <br>Usuario 
            </td>
            <td colspan="3"  >
                <textarea name="txa3_historial" id="txa3_historial" rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Dolor
            </td>
            <td colspan="3"  >
                <textarea name="txa3_dolor" id="txa3_dolor" rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Pruebas<br>Especiales
            </td>
            <td colspan="3"  >
                <textarea name="txa3_prueba_especial" id="txa3_prueba_especial" rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Palpación
            </td>
            <td colspan="3"  >
                <textarea  name="txa3_palpacion" id="txa3_palpacion"  rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Marcha
            </td>
            <td colspan="3"  >
                <textarea  name="txt3_marcha" id="txt3_marcha"  rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="4" <%=titulo%> >Plano</td>
        </tr>
        <tr>
            <td>
                Plano <br>Frontal
            </td>
            <td colspan="3"  >
                <textarea name="txa3_plano_frontal" id="txa3_plano_frontal" rows="2" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Plano <br>Sagital
            </td>
            <td colspan="3"  >
                <textarea name="txa3_plano_sagital" id="txa3_plano_sagital" rows="2" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Plano <br>Posterior
            </td>
            <td colspan="3"  >
                <textarea name="txa3_plano_posterior" id="txa3_plano_posterior" rows="2" cols="110"  ></textarea>
            </td>

        </tr>
        <tr>
            <td colspan="4" <%=titulo%> >Movimiento</td>
        </tr>
        <tr>
            <td>
                Movimiento <br>Activo
            </td>
            <td>
                <textarea name="txa3_movimiento_activo" id="txa3_movimiento_pasivo" rows="3" cols="55"  ></textarea>
            </td>
            <td>
                Movimiento <br>Pasivo
            </td>
            <td>
                <textarea name="txa3_movimiento_pasivo" id="txa3_movimiento_pasivo" rows="3" cols="55"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Observaciones
            </td>
            <td colspan="3"  >
                <textarea name="txa3_observacion" id="txa3_observacion" rows="3" cols="110"  ></textarea>
            </td>

        </tr>
        <tr>
            <td colspan="4" <%=titulo%>  > Evaluación Metamérica</td>
        </tr>
        <tr>
            <td>
                Dermatoma
            </td>
            <td>
                <textarea name="txa3_dermatoma" id="txa3_dermatoma" rows="3" cols="55"  ></textarea>

            </td>
            <td>
                Miotoma
            </td>
            <td   >
                <textarea name="txa3_miotoma" id="txa3_miotoma" rows="3" cols="55"  ></textarea>
            </td>
        </tr>

        <tr>
            <td>
                Reflejo <br>Osteotendíneo
            </td>
            <td>
                <textarea name="txa3_reflejo_osteotendineo" id="txa3_reflejo_osteotendineo" rows="3" cols="55"  ></textarea>
            </td>
            <td>
                Test<br>Neurodinámico
            </td>
            <td>
                <textarea name="txa3_test_neurodinamico" id="txa3_test_neurodinamico" rows="3" cols="55"  ></textarea>
            </td>
        </tr>


        <tr>
            <td colspan="4" <%=titulo%> > Diagnósticos</td>
        </tr>
        <tr>
            <td>
                Diagnóstico <br>por<br>Imagen
            </td>
            <td colspan="3"  >
                <textarea name="txa3_diagnostico_imagen" id="txa3_diagnostico_imagen" rows="3" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Diagnóstico <br>Kinésico
            </td>
            <td colspan="3"  >
                <textarea rows="3" name="txa3_diagnostico_kinesico" id="txa3_diagnostico_kinesico" cols="110"  ></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="4" > <center> <input class="btn btn-primary" type="submit" value="GRABAR" name="btn3_grabar" /></center> </td>
        </tr>
    </table>
</form>


</body>

<jsp:include page="../Footer.jsp" />

<%
    }
%>
