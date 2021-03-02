<%-- 
    Document   : lista_seguimiento
    Created on : 03-sep-2014, 10:16:11
    Author     : Informatica
--%>

<%@page import="CapaDato.cRegistroSeguimiento"%>
<%@page import="java.util.Iterator"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%

    int opcion = Integer.parseInt(request.getParameter("ingresa")); // si ingresa ==1 ingreso
    NegocioQ neg = new NegocioQ();
    int id_duo = Integer.parseInt(request.getParameter("id_duo"));

    if (opcion == 1) {
        cRegistroSeguimiento seg = new cRegistroSeguimiento();
        seg.setRut_usuario(request.getParameter("usuario"));
        seg.setFecha_seguimiento(neg.MMDDYYY(request.getParameter("fecha"), 1));
        seg.setDescripcion(request.getParameter("descripcion"));
        seg.setId_registro_id(id_duo);
        if (neg.ingresa_registro_seguimiento(seg)) {
            //  out.write("<h3>Se grabo registro</h3>");
        } else {
            // out.write("<h3>No se pudo grabar el registro</h3>");
        }

    } else if (opcion == 2) {
        neg.anula_registro_seguimiento(Integer.parseInt(request.getParameter("id_seguimiento")));
    }

    ArrayList lista_seguimiento = neg.lista_seguimiento(id_duo);

    String titulo = " style=' background-color: #4169E1 ; color: white '  ";
    String datos = " style=' background-color: #87CEFA ; color: black '  ";
%>


<%        Iterator it_seg = lista_seguimiento.iterator();
    int n = 0;
    if (lista_seguimiento.isEmpty()) {
        out.write("<h3>Sin datos de seguimiento y evolución</h3>");
    } else {
        out.write(" <table style='width: 100% '  >");
        out.write(" <tr " + titulo + ">");
        out.write(" <td>#</td>");
        out.write(" <td>Fecha</td>");
        out.write(" <td>Descripción</td>");
        out.write(" <td>#</td>");
        out.write("  </tr>");

        while (it_seg.hasNext()) {
            n++;
            cRegistroSeguimiento seg = (cRegistroSeguimiento) it_seg.next();
            out.write("<tr " + datos + " >");
            out.write("<td>" + n + "</td>");
            out.write("<td style='width : 10%' >" + seg.getFecha_seguimiento() + "</td>");
            out.write("<td>" + seg.getDescripcion() + "</td>");
            out.write("<td style='width : 5%' >      <img src='../Imagenes/cancel.png' width='15' height='15' alt='remover' onclick='eliminar_seguimiento(" + seg.getId_seguimiento() + ")' />  </td>");
            out.write("</tr>");
        }
        out.write("</table>");
    }


%>

