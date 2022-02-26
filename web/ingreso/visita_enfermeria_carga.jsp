<%-- 
    Document   : visita_enfermeria_carga
    Created on : 17-may-2012, 11:39:44
    Author     : EseGamboa
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="CapaDato.cVisita"%>
<%@page import="java.util.Iterator"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>

<%

    Date fecha_del_dia = new Date();
    Locale hora_local = new Locale("es", "CHL");
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
    java.text.DateFormat formateadorFechaCorta = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, currentLocale);
    SimpleDateFormat formatter = new SimpleDateFormat("EEE-dd-MMM", currentLocale);
    SimpleDateFormat formateaDMY = new SimpleDateFormat("dd/MM/yyyy", currentLocale);

    int obtiene_duo = Integer.parseInt(request.getParameter("duo"));

    NegocioQ neg = new NegocioQ();
    ArrayList historial_visita_enfermeria = neg.lista_historial_visita_enfermeria(obtiene_duo);
    cDuo duo = neg.obtiene_duo(obtiene_duo);

    Iterator it_his = historial_visita_enfermeria.iterator();

    int hora_del_dia = fecha_del_dia.getHours();
    int minuto_del_dia = fecha_del_dia.getMinutes();
    String hora = "";
    String minuto = "";
    if (hora_del_dia < 10) {
        hora = "0" + hora_del_dia;
    } else {
        hora = "" + hora_del_dia;
    }
    if (minuto_del_dia < 10) {
        minuto = "0" + minuto_del_dia;
    } else {
        minuto = "" + minuto_del_dia;
    }

%>


<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<form action="<%=neg.getLocal()%>ingresa_visita" name="form_1" method="POST" onsubmit="return confirm('¿Esta seguro que desea ingresar esta visita como ' + document.getElementById('17').value.trim() + '?');"    >
    <input type="hidden" name="id_cama" value="<%=duo.getCama()%>">
    <input type="hidden" name="id_duo" value="<%=duo.getId_duo()%>">

    <fieldset>
        <legend>Visita Enfermería Nueva en:<% out.write("" + duo.getCama_descripcion() + "  [" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente() + "]");%> </legend>
        <table border="0">
            <tr>
                <td class="destacado"><label class="rojo">*</label>Observaciones de Enfermeria</td>
                <td rowspan="3">
                    <fieldset>
                        <legend>Historial</legend>
                        <div id="Resultado" style=" overflow:auto;
                             padding-right: 5px; padding-top: 5px; padding-left: 5px; padding-bottom: 5px;
                             border-right: #6699CC 1px solid; border-top: #999999 1px solid;
                             border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
                             scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
                             scrollbar-track-color :#3333333 ;
                             height:160px; ; width: 400px" >
                            <table border="0" cellpadding="1" cellspacing="1">

                                <%
                                    String clas = "";
                                    int contador = 0;

                                    while (it_his.hasNext()) {
                                        cVisita vis = (cVisita) it_his.next();

                                        int resto = contador % 3;
                                        if (contador == 0 || resto == 0) {
                                            out.write("<tr>");
                                        }
                                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("D")) {
                                            clas = "CRD_D";
                                        }
                                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("C")) {
                                            clas = "CRD_C";
                                        }
                                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("B")) {
                                            clas = "CRD_B";
                                        }
                                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("A")) {
                                            clas = "CRD_A";
                                        }

                                %>
                                <td>
                                    <a href="/modulo_uhce/visitas/CategorizacionPDF.jsp?id_visita=<%=vis.getId_visita_categorizacion()%>" target="_blank"  >
                                        <img src="../Iconos/pdf-ico-small.gif" style="cursor: pointer">
                                    </a>
                                </td>
                                <td class="" style="text-transform: uppercase;cursor:pointer" onclick="" ><%=vis.getFecha_visita()%></td>
                                <td onclick="window.open('/modulo_uhce/visitas/CategorizacionPDF.jsp?id_visita=' +<%=vis.getId_visita_categorizacion()%>, 'Categorizacion', 'height=800,width=800,left=100, top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')" class="<%=clas%>"><%=vis.getCat_visita_categorizacion()%></td>

                                <%
                                        contador++;
                                    }
                                %>
                            </table>
                        </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td>
                    <textarea cols="45" rows="6" name="observaciones" id="observaciones"></textarea>
                </td>
            </tr>
           
            <tr>

            </tr>
        </table>

        <fieldset>
            <legend>Categorización Riesgo Dependencia </legend>
            <input type="button" value="Ver Pauta" onclick="window.open('PautaCRD.jsp', 'Pauta', 'height=800,width=800,left=100, top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')">
            <jsp:include page="TablaCategorizacion.jsp" flush="true"/>
            <hr>
            <b><label class="rojo">*</label>Fecha:<input name="fecha_at" id="fecha_at" type="text" size="12" value="<%=formateaDMY.format(fecha_del_dia)%>"  maxlength="10">&nbsp;
                <input maxlength="2" type="text" value="<%=hora%>" name="hora_at" id="hora_at" size="2" autocomplete="off" onkeyup="">&nbsp;:&nbsp;
                <input maxlength="2" type="text" name="min_at" id="min_at" autocomplete="off"  value="<%=minuto%>" size="2" onkeyup=""></b>
            <label class="rojo">  dd/mm/yyyy 24hrs.</label>
        </fieldset>

        <fieldset class="BUTTONS">
            <%
                String hora_MAR = neg.obtiene_fecha_hora();
                hora_MAR = hora_MAR.substring(hora_MAR.length() - 8, hora_MAR.length());
                // hora_MAR = "00:01:05"; // sacar
                String fecha_ingreso = "01-01-2001";
                String hora11 = "02:00:00";
                String hora22 = "21:59:59";
                //String hora22 = "14:53:59";
                String hora33 = "" + hora_MAR;

//out.println(dau_id+"  "+fecha_ingreso+"  "+hora1+"  "+hora2);
                GregorianCalendar c = new GregorianCalendar();
//out.println("<hr>"+Integer.parseInt(fecha_ingreso.substring(6,10))+" -- "+ Integer.parseInt(fecha_ingreso.substring(3,5))+" -- "+ Integer.parseInt(fecha_ingreso.substring(0,2)));
                c.setTime(new Date(Integer.parseInt(fecha_ingreso.substring(6, 10)) - 1900, Integer.parseInt(fecha_ingreso.substring(3, 5)) - 1, Integer.parseInt(fecha_ingreso.substring(0, 2))));
                DateFormat sdf = new SimpleDateFormat("HH:mm:SS");
                DateFormat amd = new SimpleDateFormat("MM-dd-yyyy");
                Date Timehora1 = sdf.parse(hora11);
                Date Timehora2 = sdf.parse(hora22);
                Date Timehora3 = sdf.parse(hora33);

                cDuo fecha_primera = neg.obtiene_fecha_enfermeria_medico_ingreso(duo.getId_duo());

//out.println("<hr>"+amd.format(c.getTime()));
                //  out.write(" "+fecha_primera.getDif_dd()+" "+fecha_primera.getDif_hh()+" "+fecha_primera.getDif_mm()+" "+fecha_primera.getDif_ss());
               
              if (fecha_primera.getDif_dd() == 0 && fecha_primera.getDif_hh() < 8) {
                    out.write("<h3>Este paciente aun no cumple 8 horas desde el ingreso médico o enfermería [" + neg.dig_cero(fecha_primera.getDif_hh()) + ":" + neg.dig_cero(fecha_primera.getDif_mm()) + ":" + neg.dig_cero(fecha_primera.getDif_ss()) + "]</h3>");
                } else if (Timehora1.before(Timehora3) && Timehora2.after(Timehora3)) {
                    out.write("<h3>En este Horario no se puede categorizar [22:00-02:00].-</h3>");
                } else {

            %>  <input type="submit" id="hora_egreso" value="Guardar Nueva Visita">

            <%                        }

            %>



            <% out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hora del Servidor:&nbsp; <b>" + hora_MAR + "</b>");%>
            <%%>
        </fieldset>
    </fieldset>
</form>
