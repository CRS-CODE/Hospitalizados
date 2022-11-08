<%-- 
    Document   : visita_enfermeria_ingresa
    Created on : 11-dic-2014, 12:17:59
    Author     : Informatica
--%>

<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cSesionKine"%>
<%@page import="CapaDato.cVisita"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="CapaNegocio.NegocioQ"%>
<jsp:include page="Header.jsp" />
<%
    //-MODIFICAR PDF EL NUMERO DE VIVISTA ENFERMERIA
    //-HABILITAR EL INGRESO DE EFNERMERIA Y DETALLE
    //

    NegocioQ neg = new NegocioQ();
    String observaciones = request.getParameter("txa_detalle").replaceAll("'", "''");

    int obtiene_duo = Integer.parseInt(request.getParameter("txt_duo"));
    cDuo duo = neg.obtiene_duo(obtiene_duo);

    int id_cama = duo.getCama();

    // out.write(obtiene_duo + "  -- " + id_cama);
    //   String categorizacion = request.getParameter("txt_categorizacion").toUpperCase();
    String fecha_hora_at_dma = request.getParameter("txt_fecha");
    int hora = Integer.parseInt(request.getParameter("txt_hora"));
    int minuto = Integer.parseInt(request.getParameter("txt_minuto"));

    Date fecha_del_dia = new Date();
    Locale hora_local = new Locale("es", "CHL");
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
    java.text.DateFormat formateadorFechaCorta = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, currentLocale);
    SimpleDateFormat formatter = new SimpleDateFormat("EEE-dd-MMM", currentLocale);
    SimpleDateFormat formateaDMY = new SimpleDateFormat("dd/MM/yyyy", currentLocale);

    //HttpSession session1 = request.getSession();
    String rut_usuario = request.getParameter("txt_usuario");

    //String observaciones = request.getParameter("observaciones").toUpperCase().replaceAll("'", "''");
    ArrayList historial_visita_enfermeria = neg.lista_historial_visita_enfermeria(obtiene_duo);
    boolean sw_mismo_dia = false;
    int visita_mismo_dia = 0;
    Iterator it_his = historial_visita_enfermeria.iterator();

    while (it_his.hasNext()) {
        cVisita vis = (cVisita) it_his.next();
        if (fecha_hora_at_dma.replace("-", "/").equals(vis.getFecha_visita().replace("-", "/"))) {
            sw_mismo_dia = true;
            visita_mismo_dia = vis.getId_visita_categorizacion();

        }
    }

    String fecha_ingreso = "01-01-2001";
    String hora11 = "02:00:00";
    String hora22 = "03:00:00";

    String hora33 = neg.obtiene_fecha_hora();
    hora33 = hora33.substring(hora33.length() - 8, hora33.length());

    //out.println(dau_id+"  "+fecha_ingreso+"  "+hora1+"  "+hora2);
    GregorianCalendar c = new GregorianCalendar();
    //out.println("<hr>"+Integer.parseInt(fecha_ingreso.substring(6,10))+" -- "+ Integer.parseInt(fecha_ingreso.substring(3,5))+" -- "+ Integer.parseInt(fecha_ingreso.substring(0,2)));
    c.setTime(new Date(Integer.parseInt(fecha_ingreso.substring(6, 10)) - 1900, Integer.parseInt(fecha_ingreso.substring(3, 5)) - 1, Integer.parseInt(fecha_ingreso.substring(0, 2))));
    DateFormat sdf = new SimpleDateFormat("HH:mm:SS");
    DateFormat amd = new SimpleDateFormat("MM-dd-yyyy");
    Date Timehora1 = null;
    Date Timehora2 = null;
    Date Timehora3 = null;
    try {
        Timehora1 = sdf.parse(hora11);
        Timehora2 = sdf.parse(hora22);
        Timehora3 = sdf.parse(hora33);
    } catch (ParseException ex) {

    }
    int grabo = 1;
    //out.println("<hr>"+amd.format(c.getTime()));
    if (sw_mismo_dia) {
        out.write("<h2>Este paciente ya tiene una visita asignada para este dia</h2>");

        out.write("<form name='form_enf" + obtiene_duo + "' id='form_enf" + obtiene_duo + "' action='" + neg.getLocal() + "visitas/CategorizacionPDF.jsp?id_visita=" + visita_mismo_dia + "' target='a_blank' method='POST' >");
        out.write("<input type='hidden' name='txt_manda_duo' value='" + obtiene_duo + "' >");
        out.write(" <img title='Visita Enfermera' src='Imagenes/Nurse_edit.png' onclick='document.forms[\"form_enf" + obtiene_duo + "\"].submit();' style='cursor:pointer'> "
                + "<--Ver PDF");
        out.write("</form>");

    } else {

        String dia1 = fecha_hora_at_dma.substring(0, 2);
        String mes1 = fecha_hora_at_dma.substring(3, 5);
        String ano1 = fecha_hora_at_dma.substring(6, 10);
        String fecha1 = dia1 + "-" + mes1 + "-" + ano1;
        String hora1 = hora + ":" + minuto + ":00";
        int id_categorizacion = 0;
        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        int d4 = 0;
        int d5 = 0;
        int d6 = 0;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
        int r13 = 0;
        if (request.getParameter("d1") != null) {
            if (request.getParameter("d1").equals("checkbox")) {
                d1 = 1;
            }
        }
        if (request.getParameter("d2") != null) {
            if (request.getParameter("d2").equals("checkbox")) {
                d2 = 1;
            }
        }
        if (request.getParameter("d3") != null) {
            if (request.getParameter("d3").equals("checkbox")) {
                d3 = 1;
            }
        }
        if (request.getParameter("d4") != null) {
            if (request.getParameter("d4").equals("checkbox")) {
                d4 = 1;
            }
        }
            if (request.getParameter("d5") != null) {
                if (request.getParameter("d5").equals("checkbox")) {
                    d5 =1;
                }
            }
            if (request.getParameter("d6") != null) {
                if (request.getParameter("d6").equals("checkbox")) {
                    d6 = 1;
                }
            }
            if (request.getParameter("r7") != null) {
                if (request.getParameter("r7").equals("checkbox")) {
                    r1 = 1;
                }
            }
            if (request.getParameter("r8") != null) {
                if (request.getParameter("r8").equals("checkbox")) {
                    r2 = 1;
                }
            }
            if (request.getParameter("r9") != null) {
                if (request.getParameter("r9").equals("checkbox")) {
                    r3 = 1;
                }
            }
            if (request.getParameter("r10") != null) {
                if (request.getParameter("r10").equals("checkbox")) {
                    r4 = 1;
                }
            }
            if (request.getParameter("r11") != null) {
                if (request.getParameter("r11").equals("checkbox")) {
                    r5 = 1;
                }
            }
            if (request.getParameter("r12") != null) {
                if (request.getParameter("r12").equals("checkbox")) {
                    r6 = 1;
                }
            }
            if (request.getParameter("r13") != null) {
                if (request.getParameter("r13").equals("checkbox")) {
                    r7 = 1;
                }
            }
            if (request.getParameter("r14") != null) {
                if (request.getParameter("r14").equals("checkbox")) {
                    r8 = 1;
                }
            }
            if (request.getParameter("r15") != null) {
                if (request.getParameter("r15").equals("checkbox")) {
                    r9 = 1;
                }
            }
            if (request.getParameter("r16") != null) {
                if (request.getParameter("r16").equals("checkbox")) {
                    r10 =1;
                }
            }
            if (request.getParameter("r17") != null) {
                if (request.getParameter("r17").equals("checkbox")) {
                    r11 = 1;
                }
            }
            if (request.getParameter("r18") != null) {
                if (request.getParameter("r18").equals("checkbox")) {
                    r12 = 1;
                }
            }
            if (request.getParameter("r19") != null) {
                if (request.getParameter("r19").equals("checkbox")) {
                    r13 = 1;
                }
            }

            String cat = "";

                // out.write("paso al ingersa " + hora + " " + minuto + "  || " + hora1);

                int obtiene_id_cat = neg.ingresa_categorizacion_enfermeria(d1, d2, d3, d4, d5, d6, r1, r2, r3, r4, r5, r6, r7, r8, cat.trim());
                grabo = neg.ingresa_visita_enfermeria(observaciones, fecha1, hora1, rut_usuario, id_cama, obtiene_id_cat, 2, obtiene_duo);

                if (grabo > 0) {
                    out.print("<h2>La Visita Correspondiente al dia de Hoy Se ha grabado Satisfactoriamente!!</h2><br>");

                    out.write("<form name='form_enf" + obtiene_duo + "' id='form_enf" + obtiene_duo + "' action='" + neg.getLocal() + "visitas/CategorizacionPDF.jsp?id_visita=" + grabo + "' target='a_blank' method='POST' >");
                    out.write("<input type='hidden' name='txt_manda_duo' value='" + obtiene_duo + "' >");
                    out.write(" <img title='Visita Enfermera' src='Imagenes/Nurse_edit.png' onclick='document.forms[\"form_enf" + obtiene_duo + "\"].submit();' style='cursor:pointer'> "
                            + "<--Ver PDF");
                    out.write("</form>");

                } else {
                    out.print("<h2>Ha ocurrido un Error al Guardar la Visita; Intentelo Nuevamente\n"
                            + "Si el Error persiste comuniqueselo al Depto. de Informatica</h2>");
                }
        }

        out.write("<form name='form_visita_enfermeria" + obtiene_duo + "' id='form_visita_enfermeria" + obtiene_duo + "' action='" + neg.getLocal() + "visita_enfermeria.jsp' method='POST' >");
        out.write("<input type='hidden' name='txt_manda_duo' value='" + obtiene_duo + "' >");
        out.write(" <img title='Visita' src='Imagenes/home.png' onclick='document.forms[\"form_visita_enfermeria" + obtiene_duo + "\"].submit();' style='cursor:pointer'>"
                + "<--Volver a la página de visita enfermería");
        out.write("</form>");

        out.write("<form name='form_visita" + obtiene_duo + "' id='form_visita" + obtiene_duo + "' action='" + neg.getLocal() + "uh_visita.jsp' method='POST' >");
        out.write("<input type='hidden' name='txt_manda_duo' value='" + obtiene_duo + "' >");
        out.write(" <img title='Visita' src='Imagenes/home.png' onclick='document.forms[\"form_visita" + obtiene_duo + "\"].submit();' style='cursor:pointer'>"
                + "<--Volver a la página de visita");
        out.write("</form>");


%>

<jsp:include page="Footer.jsp" />