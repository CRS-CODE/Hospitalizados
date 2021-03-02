<%--
    Document   : uh_visita
    Created on : 10-may-2012, 11:48:38
    Author     : EseGamboa
--%>

<%@page import="CapaDato.cDau"%>
<%@page import="CapaDato.cDas"%>
<%@page import="java.text.DateFormat"%>
<%
            HttpSession session1 = request.getSession();
            if (session1.getAttribute("usuario_rut") == null) {
                out.write("SIN SESION");
                response.sendRedirect("index.jsp?timeout=1");
            } else {
%>

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

<script type="text/javascript" src="js/calendario/calendar.js"></script>
<script type="text/javascript" src="js/calendario/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendario/calendar-es.js"></script>
<style type="text/css"> @import url("js/calendario/calendar-win2k-cold-2.css"); </style>

<%
                int perfil_usuario = Integer.parseInt(session1.getAttribute("usuario_perfil").toString());
                int camas_ocupadas = 0;
                int camas_desocupadas = 0;
                int contador = 0;
                NegocioQ neg = new NegocioQ();
                ArrayList lista_camas = neg.lista_camillas_actuales();

                ArrayList salas = new ArrayList();
                ArrayList salas_descripcion = new ArrayList();


                String fecha_hora = neg.obtiene_fecha_hora();
                String dia = fecha_hora.substring(0, 2);
                String mes = fecha_hora.substring(3, 5);
                String año = fecha_hora.substring(6, 10);
                String fecha_mda = mes + "-" + dia + "-" + año;
                String hora_duo = fecha_hora.substring(fecha_hora.length() - 8, fecha_hora.length());
                /***********************CALCULA PENDIENTES***********************/
                /***********************CALCULA PENDIENTES***********************/
                Iterator it_res = lista_camas.iterator();
                while (it_res.hasNext()) {
                    cDas aux = (cDas) it_res.next();
                    if (!salas.contains(aux.getSala()) && aux.getSala() != 0) {
                        salas.add(aux.getSala());
                        salas_descripcion.add(aux.getSala_descri());
                    }

                }

%>
<body onload="" >
    <script type="text/javascript" src="js/jquery/tooltip.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<legend>LISTADO DE CAMILLAS <% out.write(" " + fecha_hora);%></legend>

<center>
    <table>
    <tr>
        <td><h1 style="font-size: 20px;"><b>LISTADO DE CAMILLAS <% out.write(" " + fecha_hora);%></b></h1></td>
        <td><input class="btn btn-primary" type="submit" value="Ver Listado" name="btn_listado" onclick="location.href='uh_visita_tabla_suam.jsp'" /></td>
    </tr>
</table>
</center>
        
<center>




    <%
                    int indice_sala = 0;
                    String clas = "";
                    int cantidad_camas_x_sala = 0;
                    while (indice_sala < salas.size()) {
                        cantidad_camas_x_sala = 0;
                        //salas.get(indice_sala)
    %>

    <font face="verdana" style=" font-size: medium;" >
        <fieldset class="FIELDSET_SALA" >
            <legend><% out.write("" + salas_descripcion.get(indice_sala));%> </legend>
            <table border="0" cellpadding="10" align="left">

                <tr>
                    <th rowspan="2" id="" class="ESTADISTICA" align="left" >
                        <%
                                                int disponibles = 0;
                                                int ocupadas = 0;
                                                Iterator it_contador = lista_camas.iterator();
                                                while (it_contador.hasNext()) {
                                                    cDas ddd = (cDas) it_contador.next();
                                                    String sala_actual = ddd.getSala() + "";
                                                    if (sala_actual.equalsIgnoreCase(salas.get(indice_sala).toString())) {
                                                        if (ddd.getId_das() == 0) {
                                                            disponibles++;
                                                        } else {
                                                            ocupadas++;
                                                        }
                                                    }
                                                }
                                                out.write("Disponibles:" + disponibles + "&nbsp;<br>");
                                                out.write("Ocupadas&nbsp;&nbsp;  :" + ocupadas + "&nbsp;");
                        %>


                    </th>


                    <%
                                            Iterator it = lista_camas.iterator();
                                            while (it.hasNext()) {
                                                cDas aux = (cDas) it.next();
                                                /*si el paciente es NN */
                                                if (aux.getDau_id() == 0) {
                                                    cDau busca_nn = neg.obtiene_paciente_segun_dauNN(aux.getDau_nn_id());
                                                    aux.setRut_paciente("NN");
                                                    aux.setNombres_paciente(busca_nn.getNombres_paciente());
                                                    aux.setApellidop_paciente(busca_nn.getApellidop_paciente());
                                                    aux.setApellidom_paciente(busca_nn.getApellidom_paciente());
                                                    aux.setSexo(busca_nn.getSexo());
                                                    aux.setSexo_descri(busca_nn.getSexo_descri());
                                                }
                                                 /*si el paciente es NN */

                                                String sala_actual = aux.getSala() + "";
                                                if (sala_actual.equalsIgnoreCase(salas.get(indice_sala).toString())) {
                                                    cantidad_camas_x_sala++;

                                                    if (cantidad_camas_x_sala == 7) {
                                                        out.write("<tr>");
                                                    }
                                                    if (aux.getId_das() == 0) { // si la cama esta vacia

                    %>
                    <td  align="right" valign="top">
                        <table width="95px" dragableBox="false" border="0" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" >
                            <tr>
                                <td bgcolor="#33CC33" colspan="2" >
                                    <b style="color:#fff"><% out.write(" " + aux.getCamilla_descri());%>
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top" class="CAMA_DESACTIVA" colspan="2" >
                                    <b style="font-family: Verdana;font-size:10px">Disponible</b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="" >---</td>
                            </tr>
                            <tr>
                                <td colspan="2" style="" >
                                    <%

                                                                                            if (perfil_usuario == 1 || perfil_usuario == 2 || perfil_usuario == 4 || perfil_usuario == 10) {
                                                                                                out.write("<img src=\"Imagenes/Medic_add_disabled.png\" onclick=\"\" title=\"Ingreso Medico\" style=\"cursor:pointer\">&nbsp;");
                                                                                                out.write("  <img title=\"Visita Enfermera\" src=\"Imagenes/Nurse_add_disabled.png\" onclick=\"\" style=\"cursor:pointer\">");


                                                                                            }

                                    %>

                                </td>
                            </tr>
                        </table>
                    </td>


                    <%                         } else {
                                                                            // si la cama esta ocupada
                                                                            clas = "CRD_C";

                    %>
                    <td align="right" valign="top" dragableBox="false" >
                        <table border="0" width="95px" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" >
                            <tr>
                                <td bgcolor="#0088ff" width="85px" ><b style="color:#fff">
                                        <%

                                                                                                out.write(aux.getCamilla_descri());

                                        %>
                                    </b></td>
                                <td class="<%=clas%>" title="Ultima Categorización"><b style="color:#000"><% out.write("--");%></b></td>
                            </tr>
                            <tr>
                                <td valign="top" colspan="2" class="CAMA_ACTIVA" onclick="document.forms['form_pac<%=aux.getId_das()%>'].submit();" >
                                    <form name='form_pac<%=aux.getId_das()%>' id='form_pac<%=aux.getId_das()%>' action='datos_suam/datos_paciente.jsp' method='POST' >
                                        <input type='hidden' name='txt_manda_das' value='<%=aux.getId_das()%>' >
                                    </form>
                                    <b style="font-family: Verdana;font-size:7px"  >
                                        <% out.write("" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + " " + aux.getApellidom_paciente());%>
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="" >
                                    <b  style="color: blue;cursor:pointer; font-size: smaller ">
                                        <%
                                                                                                if (aux.getDif_dd() > 0) {
                                                                                                    out.write("" + aux.getDif_dd() + " dia, ");
                                                                                                }
                                                                                                out.write("" + neg.dig_cero(aux.getDif_hh()) + ":" + neg.dig_cero(aux.getDif_mm()) + ":" + neg.dig_cero(aux.getDif_ss()));
                                        %>
                                    </b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" >
                                    <table>
                                        <tr>
                                            <%
                                                                                                    if (aux.getEstado() == 2) {
                                                                                                        out.write("Egresando");
                                                                                                    } else {
                                                                                                        out.write("--");
                                                                                                    }

                                            %>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <%
                                                    }
                                                }
                                            }
                    %>



                </tr>


            </table>
        </fieldset>
        <%
                            indice_sala++;
                        }
        %>


    </font>
</center>
</body>

<jsp:include page="Footer.jsp" />

<%
            }
%>
