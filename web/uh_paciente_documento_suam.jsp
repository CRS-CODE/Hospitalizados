<%-- 
    Document   : uh_pacientes_documento
    Created on : 11-may-2012, 12:40:41
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cDas"%>
<%@page import="CapaDato.cPaciente"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cEpicrisis"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaNegocio.Negocio"%>
<%
            NegocioQ neg = new NegocioQ();

            String rut_paciente = request.getParameter("user");
            int modo = Integer.parseInt(request.getParameter("txt_modo"));
            if (modo == 2 || modo == 3) {
                int tipo_dau = Integer.parseInt(request.getParameter("tipo"));

            }

            //  out.write("Documentos del Paciente " + rut_paciente + "<br>");
            ArrayList lista_das = neg.lista_documentos_paciente_suam(rut_paciente, modo);



            String titulo = " style=' background-color: #f7903b ; color: white '  ";
            String titulo_menos = " style=' background-color: #f7903b ; color: white;FONT-SIZE:9px; '  ";
            String datos = " style=' background-color: #fcd5b6 ; color: black '  ";

            if (lista_das.size() == 0) {
                out.write("<b>No se encontraron Resultados para " + rut_paciente + "</b>");
            } else {
                Iterator it = lista_das.iterator();
                out.write("<table border='4' >");
                out.write(" <tr>");
                out.write(" <th>N° Interno <br>Documento</th>");
                out.write(" <th>N° Dau</th>");
                out.write("  <th>Ver Documento</th>");
                while (it.hasNext()) {
                    cDas das = (cDas) it.next();

                    out.write(" </tr>");
                    out.write(" <tr>");
                    out.write("  <td>" + das.getId_das() + "</td>");
                    if (das.getDau_id() == 0) {
                        out.write(" <td>" + das.getDau_nn_id() + "</td>");
                    } else {
                        out.write(" <td>" + das.getDau_id() + "</td>");
                    }

                    out.write("<td align='center' >"
                            + " <a href='" + neg.getLocal() + "PDF_DAS?txt_das=" + das.getId_das() + "' TARGET='_blank' > <img src=\"Imagenes/pdf.png\" ></a>"
                            + "</td>");
                    out.write(" </tr>");

                }
                out.write("</table>");



            }



%>






