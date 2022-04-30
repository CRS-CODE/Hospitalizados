<%-- 
    Document   : visita_medica
    Created on : 14-ago-2015, 11:23:31
    Author     : Informatica
--%>
<%@page import="CapaDato.cVisita"%>
<%@page import="java.util.Iterator"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="CapaNegocio.Negocio"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("index.jsp?timeout=1");
    } else {

        //
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

        //
        int obtiene_duo = 0;
        if (request.getParameter("txt_duo") != null) {
            obtiene_duo = Integer.parseInt(request.getParameter("txt_duo"));
        }

        String titulo = " style=' background-color: #4169E1 ; color: white '  ";
        String datos = " style=' background-color: #87CEFA ; color: black '  ";

%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="Header.jsp" />

<script>
    function selecciona_cama() {
        if ($("#cbo_cama").val() != 0) {
            document.getElementById('form_paciente').submit();
        }

    }

    function busca_visita_medica(visita) {

        var obj = false;
        if (window.XMLHttpRequest) {
            //Cuidado aqui, el objeto XMLHttpRequest no esta disponible en versiones previas a IE7
            obj = new XMLHttpRequest();
        } else {
            return false;
        }
        obj.onreadystatechange = function () {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando_visita_medica').innerHTML = '';
                document.getElementById("div_visita_medica").innerHTML = obj.responseText;

                $('#dialog').dialog('option', 'title', $('#txt_fecha2').val());
                $('#dialog').dialog('open');
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando_visita_medica').innerHTML = '-Si el problema persisten consulte a Informática-';
                document.getElementById("div_visita_medica").innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('div_cargando_visita_medica').innerHTML = '&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>  Cargando...';
            }
        };


        obj.open("GET", "sesion_carga.jsp?visita_medica=" + visita, true);
        obj.send(null);
        return (true);

    }

    function valida_visita_medica() {
        if (document.getElementById(txa_evolucion).value.length == 0) {
            alert('Complete los datos ')
            return false;
        }
        if (confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta visita ? \n \n ")) {
        } else {
            return false;
        }

    }


</script>


<script type="text/javascript" src="js/script2.js"></script>
<script>
    $(function () {
        $("#txt_fecha").datepicker();
    });
</script>
<fieldset><legend>Datos del Paciente</legend>

    <div id="nav">
        <form name="form_paciente" id="form_paciente" action="sesion.jsp" method="POST"  >
            <h2>-Seleccione paciente-</h2>
            <select name="txt_duo" id="txt_duo" onchange="selecciona_cama()" style=" width: 180px "   >
                <option value="0" >Seleccione...</option>
                <%    //
                    ArrayList lista_cama = null;
                    Iterator it_sala = null;

                    lista_cama = neg.lista_grilla_camas();
                    it_sala = lista_cama.iterator();

                    while (it_sala.hasNext()) {
                        cDuo aux = (cDuo) it_sala.next();
                        if (aux.getId_duo() != 0) {
                            out.write("<option value='" + aux.getId_duo() + "' >" + aux.getCama_descripcion() + "::" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + "</option>");
                        }
                    }
                %>
            </select>
            <br> <br>
        </form>

    </div>
    <div id="section">

        <%    if (obtiene_duo == 0) {
                out.write("<h3>No ha seleccionado paciente --> </h3>");
            } else {

                cDuo duo = neg.obtiene_duo(obtiene_duo);
        %>

        <table border="1" style=" width:800px "  >
            <tbody>
                <tr>
                    <td <%=titulo%> >Rut Paciente</td>
                    <td colspan="3" <%=datos%> ><% out.write("" + duo.getRut_paciente());%></td>

                    <td <%=titulo%> >Fecha Nacimiento</td>
                    <td colspan="2" <%=datos%> >   <% out.write("" + duo.getFecha_nac());%></td>
                </tr>
                <tr>
                    <td <%=titulo%> >Nombre Completo</td>
                    <td colspan="3" <%=datos%> ><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>

                    <td <%=titulo%> >Edad</td>
                    <td <%=datos%> ><% out.write("" + duo.getEdad());%></td>
                </tr>
                <tr>
                    <td <%=titulo%> >Cama</td>
                    <td colspan="3" <%=datos%> ><% out.write("" + duo.getCama_descripcion());%></td>

                    <td <%=titulo%> >DUO</td>
                    <td <%=datos%> ><% out.write("" + duo.getId_duo());%></td>
                </tr>
            </tbody>
        </table><br>
        <table>
            <tr>
                <td>
                    <h3>Responsable: <%=session.getAttribute("usuario_nombre_completo")%></h3>
                </td>
            </tr>
        </table>
    </div>
</fieldset>


<fieldset>
    <legend>Evolución</legend>
    <div id="nav">
        <table>
            <tr>
                <td>
                    <form name="form_formulario" id="form_formulario" action="PDF_sesion" method="POST"  target="a_blank" >
                        <input type="hidden" name="txt_duo" value="<%=duo.getId_duo()%>"   >
                        <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_formulario').submit()" />
                    </form>
                </td>
                <td>
                    << Ver Evolución
                </td>
            </tr>
            <tr>
                <td> <br> </td>
            </tr>

            <tr>

                <%
                    ArrayList lista = neg.lista_historial_sesion(obtiene_duo);
                    Iterator it_lista = lista.iterator();

                    while (it_lista.hasNext()) {
                        cVisita vis = (cVisita) it_lista.next();
                        out.write("   <tr>");
                        out.write("  <td colspan='2' >");
                %>
            <a href='#' onclick=" busca_visita_medica(<%=vis.getId_visita_categorizacion()%>)"
               title='Dr. <% out.write(vis.getNombre_usuario() + " " + vis.getApellidop_usuario());%> '  >
                <%=vis.getFecha_visita()%> 
            </a> 
            <%
                    out.write(" </td>");
                    out.write(" </tr>");

                }

            %>

        </table>  
    </div>
    <div id="section">
        <form name="form_evolucion" method="POST" action="ingresa_sesion" onsubmit=" return valida_visita_medica();
              "  >
            <input type="hidden" name="txt_modo" id="txt_modo"  value="1"  >  
            <input type="hidden" name="txt_duo" id="txt_duo"  value="<%=duo.getId_duo()%>"  >  
            <table border="0" >
                <tr>
                    <td style="  text-decoration:  underline  " >   Evolución:</td>
                    <td style="  text-decoration:  underline  " > Fecha/Hora:</td>
                </tr>
                <tr>
                    <td rowspan="2" >
                        <textarea name="txa_evolucion" id="txa_evolucion" rows="15" cols="90"></textarea>  
                    </td>
                    <td valign="top" >
                        <br>
                        <input type="text" name="txt_fecha" id="txt_fecha" 
                               value="<%=formateaDMY.format(fecha_del_dia)%>" readonly="readonly"  >  

                        <input type="text" name="txt_hora" id="txt_hora" value="<%=hora%>" 
                               style=" width: 30px " maxlength="2" onkeypress="return soloNumeros(event);" >
                        :
                        <input type="text" name="txt_minuto" id="txt_minuto" value="<%=minuto%>" 
                               style=" width: 30px " maxlength="2" onkeypress="return soloNumeros(event);"   >  
                    </td>
                </tr>
                <tr>
                    <td colspan="1" align="right"  >
                        <input class="btn btn-primary"  type="submit" value="Grabar evolución" name="btn_grabar" />
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <script>
        $(function () {
            $("#dialog").dialog({
                autoOpen: false,
                width: 600,
            });


        });
    </script>
</head>
<body>
    <div id="div_cargando_visita_medica" ></div>
    <div id="dialog" title="Visita" style="   " >
        <p>
        <div id="div_visita_medica" ></div>
        </p>
    </div>



</fieldset>


<%

        }

    }
%>

<style>

    #nav {
        line-height:30px;
        background-color:#eeeeee;
        height:95%;
        width:215px;
        float:right;
        padding:5px;
    }
    #section {
        width:350px;
        float:left;
        padding:10px;
    }

</style> 
<jsp:include page="Footer.jsp" />

