<%-- 
    Document   : ActualizaPrevision
    Created on : 31-may-2012, 19:03:38
    Author     : EseGamboa
--%>

<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaDato.cPaciente"%>
<%@page import="CapaNegocio.Negocio_fonasa"%>

<%
            String rut = request.getParameter("rut_paciente");
            int duo = Integer.parseInt(request.getParameter("duo"));
            Negocio_fonasa neg = new Negocio_fonasa();
            NegocioQ ne2 = new NegocioQ();
            out.write("" + rut);
            neg.Actualiza_prevision(rut);
            cPaciente pac = ne2.obtiene_paciente_prevision(rut);
            int id_prevision = pac.getId_prevision();

            // modifico datos del duo
            ne2.modifica_duo_x_alta_adm(duo, 4, id_prevision);
            


%>