//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cVisita;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingresa_visita extends HttpServlet {
    public ingresa_visita() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String encabezado = " <LINK REL=\"stylesheet\" TYPE=\"text/css\" HREF=\"css/style.css\">\n";
        encabezado = encabezado + "    <div><img src=\"Imagenes/Encabezado_1.gif\" width=\"765\" height=\"75\" alt=\"Archivo\"/></div>\r\n";
        encabezado = encabezado + "\r\n";
        encabezado = encabezado + "    <DIV id=wrapper>\r\n";
        encabezado = encabezado + "        <DIV id=header>\r\n";
        encabezado = encabezado + "            <DIV id=\"formArea\" style=\" height:545\" >\r\n";
        encabezado = encabezado + "                <br>\r\n";
        encabezado = encabezado + "                <!-- Beginning of compulsory code below -->\r\n";
        encabezado = encabezado + "                <div id=\"menu\">\r\n";
        encabezado = encabezado + "                <fieldset style=\" height:470\">\r\n";
        encabezado = encabezado + "                    <br><br>\r\n";
        String pie = "  \n";
        pie = pie + "                </fieldset>\n";
        pie = pie + "            </DIV>\n";
        pie = pie + "            <div id=\"footer\">Centro de Referencia de Salud Maip&uacute; - ";
        pie = pie + "</div>\n";
        pie = pie + "        </DIV>\n";
        pie = pie + "    </DIV>\n";
        new Date();
        new Locale("es", "CHL");
        Locale currentLocale = new Locale("es", "CL");
        DateFormat formateadorFecha = DateFormat.getDateInstance(0, currentLocale);
        DateFormat formateadorFechaCorta = DateFormat.getDateInstance(3, currentLocale);
        new SimpleDateFormat("EEE-dd-MMM", currentLocale);
        new SimpleDateFormat("dd/MM/yyyy", currentLocale);
        HttpSession session1 = request.getSession();
        String rut_usuario = session1.getAttribute("usuario_rut") + "";
        NegocioQ neg = new NegocioQ();
        String observaciones = request.getParameter("txa_detalle");
        int id_cama = Integer.parseInt(request.getParameter("id_cama"));
        int id_duo = Integer.parseInt(request.getParameter("id_duo"));
        String fecha_hora_at_dma = request.getParameter("txt_fecha");
        ArrayList historial_visita_enfermeria = neg.lista_historial_visita_enfermeria(id_duo);
        boolean sw_mismo_dia = false;
        Iterator it_his = historial_visita_enfermeria.iterator();

        while(it_his.hasNext()) {
            cVisita vis = (cVisita)it_his.next();
            if (fecha_hora_at_dma.replace("-", "/").equals(vis.getFecha_visita().replace("-", "/"))) {
                sw_mismo_dia = true;
            }
        }

        String fecha_ingreso = "01-01-2001";
        String hora11 = "02:00:00";
        String hora22 = "21:59:59";
        String hora33 = neg.obtiene_fecha_hora();
        hora33 = hora33.substring(hora33.length() - 8, hora33.length());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date(Integer.parseInt(fecha_ingreso.substring(6, 10)) - 1900, Integer.parseInt(fecha_ingreso.substring(3, 5)) - 1, Integer.parseInt(fecha_ingreso.substring(0, 2))));
        DateFormat sdf = new SimpleDateFormat("HH:mm:SS");
        new SimpleDateFormat("MM-dd-yyyy");
        Date Timehora1 = null;
        Date Timehora2 = null;
        Date Timehora3 = null;

        try {
            Timehora1 = sdf.parse(hora11);
            Timehora2 = sdf.parse(hora22);
            Timehora3 = sdf.parse(hora33);
        } catch (ParseException var56) {
            Logger.getLogger(ingresa_visita.class.getName()).log(Level.SEVERE, (String)null, var56);
        }

        out.write(encabezado);
        if (sw_mismo_dia) {
            out.write("<h2>Este paciente ya tiene una visita asignada para este dia</h2>");
        } else if (Timehora1.before(Timehora3) && Timehora2.after(Timehora3)) {
            out.write("<h2>-Siendo las " + hora33 + "(Hora del servidor sincronizado con el SHOA) esta restringido el ingreso de categorizaciones.</h2>");
            out.write("<h2>-El horario para categorizar es de 22:00:00 a 02:00:00. </h2>");
            out.write("<h2>-No se realizó ninguna acción. </h2>");
        } else {
            String dia1 = fecha_hora_at_dma.substring(0, 2);
            String mes1 = fecha_hora_at_dma.substring(3, 5);
            String ano1 = fecha_hora_at_dma.substring(6, 10);
            String fecha1 = mes1 + "-" + dia1 + "-" + ano1;
            String hora1 = request.getParameter("txt_hora") + ":" + request.getParameter("txt_minuto");
            int id_categorizacion = 0;
            int d1 = Integer.parseInt(request.getParameter("d0"));
            int d2 = Integer.parseInt(request.getParameter("d1"));
            int d3 = Integer.parseInt(request.getParameter("d2"));
            int d4 = Integer.parseInt(request.getParameter("d3"));
            int d5 = Integer.parseInt(request.getParameter("d4"));
            int d6 = Integer.parseInt(request.getParameter("d5"));
            int r1 = Integer.parseInt(request.getParameter("d7"));
            int r2 = Integer.parseInt(request.getParameter("r8"));
            int r3 = Integer.parseInt(request.getParameter("r9"));
            int r4 = Integer.parseInt(request.getParameter("r10"));
            int r5 = Integer.parseInt(request.getParameter("r11"));
            int r6 = Integer.parseInt(request.getParameter("r12"));
            int r7 = Integer.parseInt(request.getParameter("r13"));
            int r8 = Integer.parseInt(request.getParameter("r14"));
            String cat = request.getParameter("17");
            if (cat.trim().length() != 2) {
                out.print("<h1>El campo categoria presentaba un problema antes de guardar la visita (estaba vacio o contenia más de 2 caracteres);<br> Intentelo Nuevamente</h1><br>");
            } else {
                int obtiene_id_cat = neg.ingresa_categorizacion_enfermeria(d1, d2, d3, d4, d5, d6, r1, r2, r3, r4, r5, r6, r7, r8, cat.trim());
                int grabo = neg.ingresa_visita_enfermeria(observaciones, fecha1, hora1, rut_usuario, id_cama, obtiene_id_cat, 2, id_duo,-1,-1);
                if (grabo > 0) {
                    out.print("<h2>La Visita Correspondiente al dia de Hoy Se ha grabado Satisfactoriamente!!</h2><br>");
                } else {
                    out.print("<h2>Ha ocurrido un Error al Guardar la Visita; Intentelo Nuevamente\nSi el Error persiste comuniqueselo al Depto. de Informatica</h2>");
                }
            }
        }

        out.write("<form name='form_enf" + id_duo + "' id='form_enf" + id_duo + "' action='" + neg.getLocal() + "ingreso/visita_enfermeria.jsp' method='POST' >");
        out.write("<input type='hidden' name='txt_manda_duo' value='" + id_duo + "' >");
        out.write(" <img title='Visita Enfermera' src='Imagenes/Nurse_edit.png' onclick='document.forms[\"form_enf" + id_duo + "\"].submit();' style='cursor:pointer'> <--Volver a la Ventana de Ingreso Enfermeria");
        out.write("</form>");
        out.write("<form name='form_visita" + id_duo + "' id='form_visita" + id_duo + "' action='" + neg.getLocal() + "uh_visita.jsp' method='POST' >");
        out.write("<input type='hidden' name='txt_manda_duo' value='" + id_duo + "' >");
        out.write(" <img title='Visita' src='Imagenes/home.png' onclick='document.forms[\"form_visita" + id_duo + "\"].submit();' style='cursor:pointer'><--Volver a la Visita de camas");
        out.write("</form>");
        out.write(pie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
