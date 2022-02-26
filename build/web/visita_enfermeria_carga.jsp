<%-- 
    Document   : visita_enfermeria_carga
    Created on : 06-oct-2014, 16:08:19
    Author     : Informatica
--%>

<%@page import="CapaDato.cDato"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%
    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("index.jsp?timeout=1");
    } else {
        int obtiene_perfil = 0;

        try {
            obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
        } catch (NumberFormatException ex) {
            obtiene_perfil = -1;
        }
        Date fecha_del_dia = new Date();
        Locale hora_local = new Locale("es", "CHL");
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
        java.text.DateFormat formateadorFechaCorta = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, currentLocale);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE-dd-MMM", currentLocale);
        SimpleDateFormat formateaDMY = new SimpleDateFormat("dd/MM/yyyy", currentLocale);
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

        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));


%>
<style>

    div.tabla
    {
        clear: none;
        overflow: auto;
    }

    div.fila
    {
        clear: both;
    }

    div.col_titulo
    {
        float: left;
        padding: 5px;
        border-color: #F0E0A0;
        border-style: solid;
        border-right-width: 0px;
        border-left-width: 0px;
        border-top-width: 0px;
        border-bottom-width: 1px;
    }

    div.col
    {
        float: left;
        padding: 5px;
        border-color: #F0E0A0;
        border-style: solid;
        border-right-width: 0px;
        border-left-width: 0px;
        border-top-width: 0px;
        border-bottom-width: 1px;
    }

</style>




<input type="hidden" name="txt_duo" id="txt_duo" value="<%=id_duo%>" > 

<input type="hidden" name="LBLDep" id="LBLDep" />
<input type="hidden" name="LBLRie" id="LBLRie" />
<input type="hidden" name="LBLTOTAL" id="LBLTOTAL" />
<input type="hidden" name="LBLCAT" id="LBLCAT" />


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div class="tabla">
    <div class="fila">
        <div class="col_titulo" style="" ><b>Categorización:</b></div>
        <div class="col_titulo" style="">     
            <input type="text" name="txt_categorizacion" id="txt_categorizacion"  
                   style=" width: 30px " readonly="readonly"   > 
        </div>
    </div>

    <div class="fila">
        <div class="col_titulo" style="" ><b>Ingrese Observación:</b></div> 
    </div>
    <div class="fila">
        <div class="col_titulo" style="">     
            <textarea name="txa_detalle" id="txa_detalle" cols="40" rows="5"  ></textarea>
        </div>
    </div>
    <div class="fila">
        <div class="col_titulo" style="" ><b>Riesgo UPP:</b></div> 
    </div>
      <div>
        <select name="riesgo_upp" id="riesgo_upp"   >
            <option value="-1">Seleccione...</option>
            <%    //
               for (cDato dato : neg.getRiesgoUpp()) {

                    out.write("<option value='" + dato.getId() + "' >" + dato.getDescription() + "</option>");

                }
            %>

        </select>
    </div>

    <div class="fila">
        <div class="col_titulo" style="" ><b>Riesgo de Caida:</b></div> 
    </div>
    <div>
        <select name="riesgo_caida" id="riesgo_caida"   >
            <option value="-1">Seleccione...</option>
            <%    //
               for (cDato dato : neg.getRiesgoCaida()) {

                    out.write("<option value='" + dato.getId() + "' >" + dato.getDescription() + "</option>");

                }
            %>

        </select>
    </div>

    <div class="fila">
        <div class="col_titulo" style="" ><b>Ingrese fecha:</b></div>
        <div class="col_titulo" style="">     

            <%
                String estado_fecha = "  readonly='readonly'  ";
                if (obtiene_perfil == 12 || obtiene_perfil == 10) {
                    estado_fecha = "";
            %>
            <script>
                $(function () {
                    $("#txt_fecha").datepicker();
                });
            </script>
            <%
                }
            %>
            <input type="text" name="txt_fecha" id="txt_fecha" value="<%=formateaDMY.format(fecha_del_dia)%>"  <%=estado_fecha%>   >  

        </div>
    </div>
    <div class="fila">
        <div class="col_titulo" style="" ><b>Ingrese hora:</b></div>
        <div class="col_titulo" style="">     
            <input type="text" name="txt_hora" id="txt_hora" value="<%=hora%>" style=" width: 30px " maxlength="2" > : <input type="text" name="txt_minuto" id="txt_minuto" value="<%=minuto%>" style=" width: 30px " maxlength="2"  >  
        </div>
    </div>
</div>



<fieldset class="BUTTONS">
    <%

        String hora_MAR = neg.obtiene_fecha_hora();
        hora_MAR = hora_MAR.substring(hora_MAR.length() - 8, hora_MAR.length());
        // hora_MAR = "00:01:05"; // sacar
        String fecha_ingreso = "01-01-2001";
        String hora11 = "02:00:00";
        String hora22 = "03:00:00";
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

        cDuo fecha_primera = neg.obtiene_fecha_enfermeria_medico_ingreso(id_duo);

        //  out.write(" "+fecha_primera.getDif_dd()+" "+fecha_primera.getDif_hh()+" "+fecha_primera.getDif_mm()+" "+fecha_primera.getDif_ss());
        if (fecha_primera.getDif_dd() == 0 && fecha_primera.getDif_hh() < 8) {
            out.write("<h3>Este paciente aun no cumple 8 horas desde el ingreso médico o enfermería [" + neg.dig_cero(fecha_primera.getDif_hh()) + ":" + neg.dig_cero(fecha_primera.getDif_mm()) + ":" + neg.dig_cero(fecha_primera.getDif_ss()) + "]</h3>");
        } else {
    %>  <input class="btn btn-primary" type="submit" id="hora_egreso" value="Guardar Nueva Visita">
    <%                        }

    %>

    <% out.write("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hora del Servidor:&nbsp; <b>" + hora_MAR + "</b>");%>
</fieldset>


<%

    }
%>