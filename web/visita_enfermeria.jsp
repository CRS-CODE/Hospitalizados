<%-- 
    Document   : visita_enfermeria
    Created on : 10-dic-2014, 16:09:53
    Author     : Informatica
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

<script>

    function selecciona_cama() {

        var obj = false;
        if (window.XMLHttpRequest) {
            //Cuidado aqui, el objeto XMLHttpRequest no esta disponible en versiones previas a IE7
            obj = new XMLHttpRequest();
        } else {
            return false;
        }
        obj.onreadystatechange = function () {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cama_cargando').innerHTML = '';
                document.getElementById("div_cama_seleccionada").innerHTML = obj.responseText;
                //  $("#txt_fecha").datepicker();
                cat();
                lista_visita();
    <%                       if (obtiene_perfil == 12 || obtiene_perfil == 10) {

    %>


                $("#txt_fecha").datepicker();


    <%                      }
    %>
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cama_cargando').innerHTML = '-Si el problema persisten consulte a Informática-';
                document.getElementById("div_cama_seleccionada").innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('div_cama_cargando').innerHTML = '&nbsp;&nbsp; <img src="Imagen/loading.gif" width="16" height="16" alt="loading"/>  Cargando...';
            }
        };
        valor = document.getElementById('cbo_cama').value;

        obj.open("GET", "visita_enfermeria_carga.jsp?txt_duo=" + valor + "&modo=1", true);
        obj.send(null);
        return (true);

    }

    function lista_visita() {

        var obj = false;
        if (window.XMLHttpRequest) {
            //Cuidado aqui, el objeto XMLHttpRequest no esta disponible en versiones previas a IE7
            obj = new XMLHttpRequest();
        } else {
            return false;
        }
        obj.onreadystatechange = function () {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cama_cargando').innerHTML = '';
                document.getElementById("div_historia").innerHTML = obj.responseText;

            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cama_cargando').innerHTML = '-Si el problema persisten consulte a Informática-';
                document.getElementById("div_historia").innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('div_cama_cargando').innerHTML = '&nbsp;&nbsp; <img src="Imagen/loading.gif" width="16" height="16" alt="loading"/>  Cargando...';
            }
        };


        obj.open("GET", "visita_enfermeria_historial.jsp?txt_duo=" + valor + "&modo=1", true);
        obj.send(null);
        return (true);

    }


    function cat()
    {
        suma_dep();
        suma_rie();
        var letra = "";
        var numero = "";
        // alert(document.getElementById('LBLRie').innerHTML); 
        var riesgo = $('#LBLRie').val();
        var dependencia = $('#LBLDep').val();

//riesgo
        if (riesgo >= 0 && riesgo <= 5) {
            letra = "D";
        } else if (riesgo >= 6 && riesgo <= 11) {
            letra = "C";
        } else if (riesgo >= 12 && riesgo <= 18) {
            letra = "B";
        } else if (riesgo >= 19 && riesgo <= 24) {
            letra = "A";
        }

        //dependencia    
        if (dependencia >= 0 && dependencia <= 6) {
            numero = "3";
        } else if (dependencia >= 7 && dependencia <= 12) {
            numero = "2";
        } else if (dependencia >= 13 && dependencia <= 18) {
            numero = "1";
        }

        document.getElementById('txt_categorizacion').value = letra + numero;
    }

    function tab(id)
    {
        var algo = parseInt(id) + 1;
        if (document.getElementById(id).value > 3) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
        if (document.getElementById(id).value.length == 0) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        } else {
            if (document.getElementById(id).value.length == 1)
            {
                if (algo == 6) {
                    algo = algo + 1;
                }
                document.getElementById(algo).focus();
                //alert(algo);
            }
        }
        suma_dep();
        suma_rie();
        //  suma_total();
        //  cat();
    }
    function valido(id)
    {
        if (document.getElementById(id).value > 3) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
        if (document.getElementById(id).value.length == 0) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
    }


    function resetear() {
        for (i = 1; i <= 6; i++) {
            document.getElementById('d' + i).value = 0;
            //alert(document.getElementById(i).value);
        }
        for (i = 7; i <= 14; i++) {
            document.getElementById('r' + i).value = 0;
        }
        cat()
    }

    function suma_dep()
    {
        var suma_dep = 0;
        for (i = 1; i <= 6; i++) {
            suma_dep = suma_dep + parseInt(document.getElementById('d' + i).value);
            //alert(document.getElementById(i).value);
        }
        //alert(suma_dep);
        // document.getElementById('6').value = suma_dep;
        //alert(suma_dep);

        $('#LBLDep').val(suma_dep);

    }
    function suma_rie()
    {
        var suma_rie = 0;
        for (i = 7; i <= 14; i++) {
            suma_rie = suma_rie + parseInt(document.getElementById('r' + i).value);
        }
        // document.getElementById('15').value = suma_rie;
        // alert(suma_rie);
        $('#LBLRie').val(suma_rie)
    }


    function soloNumeros(evt) {
        //asignamos el valor de la tecla a keynum
        if (window.event) {// IE
            keynum = evt.keyCode;
        } else {
            keynum = evt.which;
        }
        //
        if ((keynum > 45 && keynum < 58) || keynum == 8 || keynum == 13 || keynum == 9) {
            //numeros || delete || enter || tab
            return true;
        } else {
            alert('Solo se aceptan Números');
            return false;
        }
    }

    /////
    function asigna(id_text, valor) {
        document.getElementById(id_text).value = valor;
        cat();
    }

    function valida_visita() {
        if (confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta visita ? \n \n ")) {
        } else {
            return false;
        }
    }

</script>

<body onload="" >
    <script type="text/javascript" src="js/jquery/tooltip.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<legend>VISITA DE ENFERMERIA</legend>
<form name="form_visita_enfermeria" method="POST" action="visita_enfermeria_ingresa.jsp" onsubmit="return valida_visita()"  >
    <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=session1.getAttribute("usuario_rut")%>" >

    <div id="div_historia" style=" overflow:auto;
         padding-right: 15px; padding-top: 15px; padding-left: 15px; padding-bottom: 15px;
         border-right: #6699CC 1px solid; border-top: #999999 1px solid;
         border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
         scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
         scrollbar-track-color :#3333333 ;
         height:80px; left: 100; top: 20; width: 95%"
         > </div>
    <div id="content">
        <div id="column-left">
            <h1>Seleccione paciente</h1>
            <br>
            <select name="cbo_cama" id="cbo_cama" onchange="selecciona_cama()"  >
                <option>Seleccione...</option>
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
            <div id="div_cama_seleccionada" ></div>
            <div id="div_cama_cargando" ></div>

        </div>
        <div id="central"  >
            <%  //mar %> 
            <table width="500pxs">
                <thead>
                    <tr>
                        <th colspan="3" style="background-color: #e46c0a">CATEGORIA DE PACIENTES</th>
                    </tr>
                    <tr>
                        <th title="Dependencia">1.</th>
                        <th colspan="1">BASICO</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d1', 3)">Control de signos vitales. </td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d1', 2)">Control de Hemoglucotest.</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d1', 1)">Educación.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d1', 0)" >Kinesiología Motora.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d1', 0)" >Fonoaudiología.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d1', 0)" >Terapia Ocupacional.</td>
                    </tr>
                <thead>
                    <tr>
                        <th title="Dependencia">2.</th>
                        <th colspan="1">Intermedio&nbsp;</th>

                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d2', 3)">Control de signos.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 2)">Control de Hemoglucotest.</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d2', 1)">Educación.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Kinesiología motora.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Kinesiología respiratoria.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Fonoaudiología.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Educación.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Tratamiento oral.</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Tratamiento subcutáneo.</td>
                    </tr>

                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Tratamiento endovenoso.</td>
                    </tr>

                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Oxígeno.</td>
                    </tr>

                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Curaciones.</td>
                    </tr>

                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Toma de exámenes.</td>
                    </tr>


                </tbody>


            </table>   
            <% // %>

            <div id="Resultado" style=" overflow:auto;
                 padding-right: 15px; padding-top: 15px; padding-left: 15px; padding-bottom: 15px;
                 border-right: #6699CC 1px solid; border-top: #999999 1px solid;
                 border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
                 scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
                 scrollbar-track-color :#3333333 ;
                 left: 100; top: 20; width: 95%">
                <!-- Este DIV contendra la respuesta enviada por el Servlet -->


            </div>  

        </div>
    </div>
</form>

<style>
    body {
        font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
        font-size: 12;
    }

    #content {

        margin: 0px auto;
    }

    #column-left {
        background-color: #EBE9EA;
        border: 1px solid #D2D2D2;
        border-radius: 4px 4px 4px 4px;
        float: left;
        position: fixed;
        min-height: 225px;
        margin-bottom: 10px;
        margin-right: 10px;
        overflow: hidden;
        text-align: center;
        width: 25%;

    }

    #central {
        background-color: #EBE9EA;
        border: 1px solid #D2D2D2;
        border-radius: 8px 8px 8px 8px;
        float: right;

        margin-bottom: 10px;
        width: 875px;
    }


</style>

<style>
    table { /*background:#D3E4E5;*/
        border:1px solid gray;
        border-collapse:collapse;
        color:#fff;
        font:normal 12px verdana, arial, helvetica, sans-serif;
    }
    caption { border:1px solid #5C443A;
              color:#5C443A;
              font-weight:bold;
              letter-spacing:20px;
              padding:6px 4px 8px 0px;
              text-align:center;
              text-transform:uppercase;
    }
    td, th { color:#363636;
             padding:.4em;
    }
    tr { border:1px dotted gray;
    }
    thead th, tfoot th { background:#5C443A;
                         color:#FFFFFF;
                         padding:3px 10px 3px 10px;
                         text-align:left;
                         text-transform:uppercase;
    }
    tbody td a { color:#363636;
                 text-decoration:none;
    }
    tbody td a:visited { color:gray;
                         text-decoration:line-through;
    }
    tbody td a:hover { text-decoration:underline;
    }
    tbody th a { color:#363636;
                 font-weight:normal;
                 text-decoration:none;
    }
    tbody th a:hover { color:#363636;
    }
    tbody td+td+td+td a { background-image:url('bullet_blue.png');
                          background-position:left center;
                          background-repeat:no-repeat;
                          color:#03476F;
                          padding-left:15px;
    }
    tbody td+td+td+td a:visited { background-image:url('bullet_white.png');
                                  background-position:left center;
                                  background-repeat:no-repeat;
    }
    tbody th, tbody td { text-align:left;
                         vertical-align:top;
    }
    tfoot td { background:#5C443A;
               color:#FFFFFF;
               padding-top:3px;
    }
    .odd { background:#fff;
    }
    tbody tr:hover { background:#99BCBF;
                     border:1px solid #03476F;
                     color:#000000;
    }
</style>

</body>

<jsp:include page="Footer.jsp" />

<%                        }
%>
