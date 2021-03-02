<%-- 
    Document   : xxx
    Created on : 21-dic-2015, 17:12:15
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


<table border="1" >
    <tr>
        <th>Id</th>
        <th>Tarea programada</th>
        <th>Horario</th>
        <th>Realizada</th>
        <th>No realizada</th>
        <th>Observacion</th>
    </tr>
    <tr>
        <td>1</td>
        <td>Aseo matinal</td>
        <td>08:00</td>
        <td><input type="radio" name="rbt_aseo_matinal" value="1" /></td>
        <td><input type="radio" name="rbt_aseo_matinal" value="0" /></td>
        <td><input type="text" name="txt_aseo_matinal" value="" checked="checked"  /></td>
    </tr>
    <tr>
        <td>2</td>
        <td>Aseo bucal</td>
        <td>09:00</td>
        <td><input type="radio" name="rbt_aseo_bucal" value="1" /></td>
        <td><input type="radio" name="rbt_aseo_bucal" value="0" checked="checked" /></td>
        <td><input type="text" name="txt_aseo_matinal" value="" /></td>
    </tr>

</table>


<jsp:include page="Footer.jsp" />

<%
    }
%>
