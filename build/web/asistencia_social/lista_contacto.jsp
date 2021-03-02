<%-- 
    Document   : lista_contacto
    Created on : 01-sep-2014, 16:49:03
    Author     : Informatica
--%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaDato.cPaciente"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
    String titulo = " style=' background-color: #4169E1 ; color: white '  ";
    String datos = " style=' background-color: #87CEFA ; color: black '  ";

    String rut_paciente = request.getParameter("paciente_rut");
    int opcion = Integer.parseInt(request.getParameter("ingresa")); // si ingresa ==1 ingreso
    HttpSession session1 = request.getSession();
    String obtiene_usuario = "" + request.getParameter("usuario");

    NegocioQ neg = new NegocioQ();

    if (opcion == 1) {

        cPaciente con = new cPaciente();
        con.setRut_paciente(rut_paciente);
        con.setNombre_usuario(obtiene_usuario);
        con.setNombres_paciente(request.getParameter("nombre"));

        try {
            Integer.parseInt(request.getParameter("fono1"));
            con.setTelefono1(request.getParameter("fono1"));
        } catch (NumberFormatException ex) {
            con.setTelefono1("0");
        }

        try {
            Integer.parseInt(request.getParameter("fono2"));
            con.setTelefono2(request.getParameter("fono2"));
        } catch (NumberFormatException ex) {
            con.setTelefono2("0");
        }

        con.setDireccion(request.getParameter("direccion"));
        con.setParentesco_desc(request.getParameter("parentesco"));
        con.setComuna_codigo(Integer.parseInt(request.getParameter("comuna")));
        neg.ingresa_contacto(con);
    }

    ArrayList lista_contacto = neg.lista_contacto(rut_paciente);

%>

<table style=" width: 100% " >
    <tr <%=datos%>>
        <td style="  " >#</td>
        <td>Nombre</td>
        <td>Parentesco</td>
        <td>Fono 1</td>
        <td>Fono 2</td>
        <td>Dirección</td>

    </tr>
    <%

        if (lista_contacto.isEmpty()) {
            out.write("<td colspan='6' ><b>Sin datos de contacto adicionales</b></td>");
        } else {
            Iterator it_contacto = lista_contacto.iterator();
            int n = 0;
            while (it_contacto.hasNext()) {
                out.write("<tr>");
                n++;
                cPaciente pac = (cPaciente) it_contacto.next();
                out.write(" <td  >" + n + "</td>");
                out.write(" <td  >" + pac.getNombres_paciente() + "</td>");
                out.write(" <td  >" + pac.getParentesco_desc() + "</td>");
                out.write(" <td  >" + pac.getTelefono1() + "</td>");
                out.write(" <td  >" + pac.getTelefono2() + "</td>");

                if (pac.getDireccion().equals("")) {
                    out.write(" <td  ></td>");
                } else {
                    out.write(" <td  >" + pac.getDireccion() + ", " + pac.getComuna_descri() + "</td>");
                }

                out.write("</tr>");
            }
        }


    %>
</table>