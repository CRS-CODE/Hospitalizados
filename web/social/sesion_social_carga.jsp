<%-- 
    Document   : sesion_kinesica_carga
    Created on : 10-sep-2014, 12:42:03
    Author     : Informatica
--%>


<%@page import="CapaDato.cSesionKine"%>

<%@page import="java.util.Iterator"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%

    int opcion = Integer.parseInt(request.getParameter("ingresa")); // si ingresa ==1 ingreso
    NegocioQ neg = new NegocioQ();
    int id_duo = Integer.parseInt(request.getParameter("id_duo"));

    if (opcion == 1) {

        cSesionKine ses = new cSesionKine();

        ses.setFecha(neg.MMDDYYY(request.getParameter("fecha"), 1));
        ses.setHora(request.getParameter("hora"));
        ses.setFecha_hora(ses.getFecha() + " " + ses.getHora());
        ses.setDetalle(request.getParameter("detalle"));
        ses.setRut_usuario(request.getParameter("usuario"));
        ses.setId_duo(id_duo);

        if (neg.ingresa_sesion_social(ses)) {
       
        } else {
            out.write("<h3>No se pudo grabar el registro</h3>");
        }
    } else if (opcion == 2) {
        //
        //neg.anula_registro_seguimiento(Integer.parseInt(request.getParameter("id_seguimiento")));
        //

    }

    ArrayList lista_sesion = neg.lista_sesion_social(id_duo);

    String titulo = " style=' background-color: #4169E1 ; color: white '  ";
    String datos = " style=' background-color: #87CEFA ; color: black '  ";
%>


<%
    Iterator it_ses = lista_sesion.iterator();
    int n = 0;
    if (lista_sesion.isEmpty()) {
        out.write("<h3>Sin registros</h3>");
    } else {
        out.write(" <table style='width: 100% '  >");
        out.write(" <tr " + titulo + ">");
        out.write(" <td>#</td>");
        out.write(" <td>Fecha</td>");
        out.write(" <td>Detalle</td>");
        out.write(" <td>#</td>");
        out.write("  </tr>");

        while (it_ses.hasNext()) {
            n++;
            cSesionKine ses = (cSesionKine) it_ses.next();
            out.write("<tr " + datos + " >");
            out.write("<td style='width : 4%'>" + n + "</td>");
            out.write("<td style='width : 10%' >" + ses.getFecha_hora() + "</td>");
            out.write("<td>" + ses.getDetalle() + "</td>");
            out.write("<td style='width : 5%' >  " + ses.getNombre_usuario() + " " + ses.getApellidop_usuario() + "    </td>");
            out.write("</tr>");
        }
        out.write("</table>");
    }


%>

