<%-- 
    Document   : uh_registro_carga
    Created on : 10-jun-2012, 12:06:42
    Author     : Dis
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaDato.cPerfil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%
            NegocioQ neg = new NegocioQ();


            String modo = request.getParameter("modo");
            String valor = request.getParameter("valor").toUpperCase();

            String a_nombres = "";
            String a_apellidop = "";
            String a_apellidom = "";
            String a_rut = "";
            String columna = "";

            if (modo.contains("1")) {
                columna = "nombre_usuario";
            } else if (modo.contains("2")) {
                columna = "apellidop_usuario";
            } else if (modo.contains("3")) {
                columna = "apellidom_usuario";
            }
        String titulo = " style=' background-color: #f7903b ; color: white '  ";
            ArrayList arra = neg.obtiene_usuario_sistema(columna, valor);
            boolean sw_tiene_usuario = false;
            Iterator it = arra.iterator();
            out.write("<table>");
            out.write("<tr "+titulo+"><td>Rut</td><td>Nombres</td><td>Apellido P.</td><td>Apellido M.</td><tr>");
            while (it.hasNext()) {
                sw_tiene_usuario = true;
                cUsuario usu = (cUsuario) it.next();
                out.write("<tr>");
                out.write("<td>" + usu.getRut_usuario() + "</td>");
                out.write("<td>" + usu.getNombre_usuario() + "</td>");
                out.write("<td>" + usu.getApellidop_usuario() + "</td>");
                out.write("<td>" + usu.getApellidom_usuario() + "</td>");
                out.write("</tr>");
            }
            out.write("</table>");


         



%>

