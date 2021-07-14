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
<%
    int obtiene_perfil = Integer.parseInt(session1.getAttribute("usuario_perfil").toString());
    ArrayList see_medico = new ArrayList();
    see_medico.add(1);
    see_medico.add(2);
    see_medico.add(4);
    see_medico.add(8);
    see_medico.add(9);
    see_medico.add(10);
    see_medico.add(12);
    int camas_ocupadas = 0;
    int camas_desocupadas = 0;
    int contador = 0;
    ArrayList lista_camas = neg.lista_grilla_camas();
    ArrayList salas = new ArrayList();
    salas.add("1");
    String nombre_sala[] = new String[1];
    String fecha_hora = neg.obtiene_fecha_hora();
    String dia = fecha_hora.substring(0, 2);
    String mes = fecha_hora.substring(3, 5);
    String año = fecha_hora.substring(6, 10);
    String fecha_mda = mes + "-" + dia + "-" + año;
    String hora_duo = fecha_hora.substring(fecha_hora.length() - 8, fecha_hora.length());
    boolean sw_cat = false;
    Date fecha_del_dia = new Date();
    Locale hora_local = new Locale("es", "CHL");
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
    java.text.DateFormat formateadorFechaCorta = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, currentLocale);
    SimpleDateFormat formatter = new SimpleDateFormat("EEE-dd-MMM", currentLocale);
    SimpleDateFormat formateaDMY = new SimpleDateFormat("dd/MM/yyyy", currentLocale);
    String fecha_ingreso = "01-01-2001";
    String hora11 = "00:00:00";
    String hora22 = "02:00:00";
    String hora33 = "" + hora_duo;
//out.println(dau_id+"  "+fecha_ingreso+"  "+hora1+"  "+hora2);
    GregorianCalendar c = new GregorianCalendar();
//out.println("<hr>"+Integer.parseInt(fecha_ingreso.substring(6,10))+" -- "+ Integer.parseInt(fecha_ingreso.substring(3,5))+" -- "+ Integer.parseInt(fecha_ingreso.substring(0,2)));
    c.setTime(new Date(Integer.parseInt(fecha_ingreso.substring(6, 10)) - 1900, Integer.parseInt(fecha_ingreso.substring(3, 5)) - 1, Integer.parseInt(fecha_ingreso.substring(0, 2))));
    DateFormat sdf = new SimpleDateFormat("HH:mm:SS");
    DateFormat amd = new SimpleDateFormat("MM-dd-yyyy");
    Date Timehora1 = sdf.parse(hora11);
    Date Timehora2 = sdf.parse(hora22);
    Date Timehora3 = sdf.parse(hora33);
    if (Timehora1.before(Timehora3) && Timehora2.after(Timehora3)) {
        sw_cat = true; // SI ES TRUE ES PORQUE SON ENTRE 0 Y 2 AM;
    }
    Iterator it_res = lista_camas.iterator();
    while (it_res.hasNext()) {
        cDuo aux = (cDuo) it_res.next();
        if (aux.getSala() == 1) {
            nombre_sala[0] = "" + aux.getSala_descripcion();
        } 
    }
    int CamasDisponibles = 0;
    int CamasReservada = 0;
    int EnesperadeIngMedico = 0;
    int PacientesIngresadosHoy = 0;
    int AltaMedica = 0;
    int PacientesEgresados = 0;
    Iterator it_con = lista_camas.iterator();
    while (it_con.hasNext()) {
        cDuo aux = (cDuo) it_con.next();
        if (aux.getId_duo() == 0) {
            CamasDisponibles++;
        } else {
            // CamasReservada++;
        }
        if (aux.getEstado_duo() == 1 && aux.getTiene_enfermeria() == 0) {
            CamasReservada++;
        }
        if (aux.getEstado_duo() == 1 || aux.getEstado_duo() == 2) {
            EnesperadeIngMedico++;
        }
        if (aux.getEstado_duo() == 3) {
            AltaMedica++;
        }
    }
%>
<body onload="" >
    <script type="text/javascript" src="js/jquery/tooltip.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<legend >LISTADO DE HOSP. DOMICILIARIA <% out.write(" " + fecha_hora);%></legend>
<center>
    <table width="30px">

        <%
            int col = 0;
            int indice_sala = 0;
            String clas = "";
            int cantidad_camas_x_sala = 0;
            while (indice_sala < salas.size()) {
                cantidad_camas_x_sala = 0;
                //salas.get(indice_sala)
                int resu = col % 1;
                if (col == 0 || resu == 0) {
                    out.write("<tr>");
                }
                col++;
        %>
        <td width="50">
            <font face="verdana" style=" font-size: small;" >
                <fieldset class="FIELDSET_SALA"   >
                    <legend><%      out.write("" + nombre_sala[indice_sala]);%> </legend>
                    <table border="0" cellpadding="10" align="left">
                        <tr>
                            <th rowspan="6" id="" class="ESTADISTICA" align="left"  >
                                <%//camas
                                    int disponibles = 0;
                                    int ocupadas = 0;
                                    Iterator it_contador = lista_camas.iterator();
                                    while (it_contador.hasNext()) {
                                        cDuo ddd = (cDuo) it_contador.next();
                                        String sala_actual = ddd.getSala() + "";
                                        if (sala_actual.equalsIgnoreCase(salas.get(indice_sala).toString())) {
                                            if (ddd.getId_duo() == 0) {
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
                                    cDuo aux = (cDuo) it.next();
                                    String sala_actual = aux.getSala() + "";
                                    if (sala_actual.equalsIgnoreCase(salas.get(indice_sala).toString())) {
                                        cantidad_camas_x_sala++;
                                        if (aux.getSala() == 1 && cantidad_camas_x_sala == 9 ) {
                                            out.write("<tr>");
                                        }
                                       
                                        if (aux.getId_duo() == 0) { // si la cama esta vacia
                            %>

                            <td align="right" valign="top">
                                <table width="85px" dragableBox="false" border="0" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" >
                                    <tr>
                                        <td bgcolor="#33CC33" colspan="2" >
                                            <b style="color:#fff"><% out.write(" " + aux.getCama_descripcion());%>
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
                                                if (see_medico.contains(obtiene_perfil)) {
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
                                clas = "CRD_" + aux.getUltima_clasificacion().substring(0, 1);
                            %>

                            <td align="right" valign="top" dragableBox="false" >
                                <table border="0" width="85px" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" >
                                    <tr>
                                        <td bgcolor="#0088ff" width="60px" ><b style="color:#fff">
                                                <%                                            if (aux.getEstado_duo() == 3) {
                                                        // hitos de la epiciris
                                                        out.write("<a style=\"color:white;text-decoration:none; \" href='" + neg.getLocal() + "hito_paciente.jsp?rut=" + aux.getRut_paciente() + "' target='_blank' onClick=\"window.open(this.href, this.target, 'width=900,height=450'); return false;\" "
                                                                + " onmouseover=\"Tip('Rut del Paciente:" + aux.getRut_paciente() + "<br><br>(Haga click para abrir ventana de Hitos)', SHADOW, true, TITLE, 'Hitos del Paciente', PADDING, 9)\" >"
                                                                + aux.getCama_descripcion() + "</a> \n");
                                                    } else {
                                                        out.write(aux.getCama_descripcion());
                                                    }
                                                %>
                                            </b></td>
                                        <td class="<%=clas%>" title="Ultima Categorización"><b style="color:#000"><% out.write("" + aux.getUltima_clasificacion());%></b></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" colspan="2" class="CAMA_ACTIVA" onclick="document.forms['form_pac<%=aux.getId_duo()%>'].submit();" >
                                            <form name='form_pac<%=aux.getId_duo()%>' id='form_pac<%=aux.getId_duo()%>' action='datos/datos_paciente.jsp' method='POST' >
                                                <input type='hidden' name='txt_manda_duo' value='<%=aux.getId_duo()%>' >
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
                                                    int dias_cama = aux.getDias_cama() + 1;
                                                    if (aux.getEstado_duo() == 1 && aux.getTiene_enfermeria() == 0) {
                                                        out.write("<b style=\"color:#696969  \">");
                                                        out.write("Reservada");
                                                        out.write("</b>");
                                                    } else {
                                                        out.write("DIA:" + dias_cama);
                                                    }
                                                %>
                                            </b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" >
                                            <table>
                                                <tr>
                                                    <%
                                                        if (aux.getEstado_duo() == 3) {
                                                            out.write("<td colspan='2' ><form name='form_med" + aux.getId_duo() + "' id='form_med" + aux.getId_duo() + "' action='" + neg.getLocal() + "ingreso/ingreso_medico.jsp' method='POST' >");
                                                            out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                                                            if (aux.getFecha_hora_alta_med_duo().contains("---")) {
                                                                out.write("<b title=\"Presione para Ver Epicrisis\" "
                                                                        + "style=\"color:green;TEXT-DECORATION:underline;cursor:pointer\" onclick=\"if("
                                                                        + obtiene_perfil + "!=5){window.open('PDF_egresoMedico?txt_duo="
                                                                        + aux.getId_duo() + "','pop-up','width=500, height=500, scrollbars=yes, menubar=no, location=yes, status=no, "
                                                                        + "resizable=yes,left = 800,top = 0');}else{alert('No esta autorizado, para ver esta información')}\">Pre Alta-Med<br></b>");
                                                                out.write("<b style=\"color:#696969  \">");
                                                                out.write("Confirmar Egreso<br>" + neg.tiempo_desde_alta_med(aux.getId_duo()));
                                                                out.write("</b>");
                                                            } else {
                                                                out.write("<b title=\"Presione para Ver Epicrisis\" "
                                                                        + "style=\"color:red;TEXT-DECORATION:underline;cursor:pointer\" onclick=\"if("
                                                                        + obtiene_perfil + "!=5&&" + obtiene_perfil + "!=3){window.open('PDF_egresoMedico?txt_duo="
                                                                        + aux.getId_duo() + "','pop-up','width=500, height=500, scrollbars=yes, menubar=no, location=yes, status=no, "
                                                                        + "resizable=yes,left = 800,top = 0');}else{alert('No esta autorizado, para ver esta información')}\">&nbsp;Alta-Medica&nbsp;<br></b>");
                                                            }
                                                            out.write("</form></td>");
                                                        } else if (aux.getEstado_duo() == 21) {
                                                            if (see_medico.contains(obtiene_perfil)) {
                                                                out.write("<td><form name='form_med" + aux.getId_duo() + "' id='form_med" + aux.getId_duo() + "' action='" + neg.getLocal() + "ingreso/visita_medico.jsp' method='POST' >");
                                                                out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                                                                out.write(" <img src='Imagenes/Medic_edit.png' onclick='document.forms[\"form_med" + aux.getId_duo() + "\"].submit();' title='Visita Medica' style='cursor:pointer'>");
                                                                out.write("</form></td>");
                                                            }
                                                        } else if (see_medico.contains(obtiene_perfil)) {
                                                            out.write("<td><form name='form_med" + aux.getId_duo() + "' id='form_med" + aux.getId_duo() + "' action='" + neg.getLocal() + "ingreso/ingreso_medico.jsp' method='POST' >");
                                                            out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                                                            out.write(" <img src='Imagenes/Medic_add.png' onclick='document.forms[\"form_med" + aux.getId_duo() + "\"].submit();' title='Ingreso Medico' style='cursor:pointer'>");
                                                            out.write("</form></td>");
                                                        }
                                                        if (aux.getEstado_duo() == 3) {
                                                        } else if (aux.getTiene_enfermeria() == 0) {
                                                            if (see_medico.contains(obtiene_perfil)) {
                                                                out.write("<td><form name='form_enf" + aux.getId_duo() + "' id='form_enf" + aux.getId_duo() + "' action='" + neg.getLocal() + "ingreso/ingreso_enfermeria.jsp' method='POST' >");
                                                                out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                                                                out.write(" <img title='Ingreso Enfermera' src='Imagenes/Nurse_add.png' onclick='document.forms[\"form_enf" + aux.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                                                out.write("</form></td>");
                                                            }
                                                        } else if (aux.getTiene_enfermeria() == 1) {
                                                            if (see_medico.contains(obtiene_perfil)) {
                                                                out.write("<td><form name='form_enf" + aux.getId_duo() + "' id='form_enf" + aux.getId_duo() + "' action='" + neg.getLocal() + "ingreso/visita_enfermeria.jsp' method='POST' >");
                                                                out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                                                                out.write(" <img title='Visita Enfermera' src='Imagenes/Nurse_edit.png' onclick='document.forms[\"form_enf" + aux.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                                                out.write("</form></td>");
                                                            }
                                                        }
                                                    %>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <%                                }
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
        </td>
    </table>
</center>
</body>

<jsp:include page="Footer.jsp" />

<%
    }
%>