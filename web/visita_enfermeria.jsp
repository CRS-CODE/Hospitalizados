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
                   <%
              
                if (obtiene_perfil == 12 || obtiene_perfil == 10) {
                  
                  %>
                
                     
           $( "#txt_fecha" ).datepicker();

               
                  <%
                    
                }
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
            <h2>-Seleccione paciente-</h2>
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
            <table>
                <thead>
                    <tr>
                        <th colspan="3" style="background-color: #3366CC">CATEGORIA DE CUIDADOS UNIVERSALES</th>
                    </tr>
                    <tr>
                        <th title="Dependencia">1.</th>
                        <th colspan="1">Cuidados en Confort y Bienestar&nbsp;<input id="d1" name="d1" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d1', 3)">Cuidados Básicos requeridos 3 veces al día o mas C/S Familia</td>
                        <td style="cursor: pointer" onclick="asigna('d1', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d1', 2)">Cuidados Básicos requeridos 2 veces al día o mas C/S Familia</td>
                        <td style="cursor: pointer" onclick="asigna('d1', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d1', 1)">Usuario y Flia, realizan cuidados con ayuda y supervision, cualquier frecuencia</td>
                        <td style="cursor: pointer" onclick="asigna('d1', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d1', 0)" >Autovalente</td>
                        <td style="cursor: pointer" onclick="asigna('d1', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                <thead>
                    <tr>
                        <th title="Dependencia">2.</th>
                        <th colspan="1">Movilización y Transporte&nbsp;<input id="d2" name="d2" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d2', 3)">Usuario no se levanta y requiere cambios de pocisión 10 o mas veces c/s flia</td>
                        <td style="cursor: pointer" onclick="asigna('d2', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 2)">Usuario es levantado a silla y cambios de posición 4 a 9 veces c/s flia</td>
                        <td style="cursor: pointer" onclick="asigna('d2', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d2', 1)">Usuario se levanta y deambula con ayuda, se cambia de posición solo o c/s flia</td>
                        <td style="cursor: pointer" onclick="asigna('d2', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d2', 0)">Usuario deambula sin ayuda y se moviliza solo en la cama</td>
                        <td style="cursor: pointer" onclick="asigna('d2', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                <thead>
                    <tr>
                        <th>3</th>
                        <th colspan="1">Cuidados de alimentación&nbsp;<input id="d3" name="d3" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d3', 3)">Alimentación y/o hidratación parental total/parcial o ayuno prolongado</td>
                        <td style="cursor: pointer" onclick="document.getElementById('d3').value = '3'">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d3', 3)">Alimentación por via enteral permanente o discontinua c/s familia</td>
                        <td style="cursor: pointer" onclick="asigna('d3', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d3', 2)">Alimentación por via oral, la que es administrada c/s la familia</td>
                        <td style="cursor: pointer" onclick="asigna('d3', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d3', 1)">Alimentación oral o enteral, con ayuda y/o supervisión</td>
                        <td style="cursor: pointer" onclick="asigna('d3', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d3', 0)">Autovalente</td>
                        <td style="cursor: pointer" onclick="asigna('d3', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>4</th>
                        <th colspan="1">Cuidados de eliminación&nbsp;<input id="d4" name="d4" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d4', 3)">Egresos por sonda, protesis, procedimientos dialiticos, colectores, pañales</td>
                        <td style="cursor: pointer" onclick="asigna('d4', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d4', 2)">Egresos x via natural y se le entregan o colocan los colectores (chata, pato)</td>
                        <td style="cursor: pointer" onclick="asigna('d4', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d4', 1)">Usuario y familia realizan recolección de egresos con ayuda y supervision</td>
                        <td  style="cursor: pointer" onclick="asigna('d4', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d4', 0)">Usuario usa colectores (chata, pato) sin ayuda y/o usa WC</td>
                        <td style="cursor: pointer" onclick="asigna('d4', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>5</th>
                        <th colspan="1">Apoyo Psicosocial y emocional&nbsp;<input id="d5" name="d5" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d5', 3)">Recibe mas de 30 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                        <td style="cursor: pointer" onclick="asigna('d5', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d5', 2)">Recibe entre 15 y 30 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                        <td style="cursor: pointer" onclick="asigna('d5', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d5', 1)">Recibe entre 5 y 14 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                        <td style="cursor: pointer" onclick="asigna('d5', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d5', 0)">Recibe menos de 5 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                        <td style="cursor: pointer" onclick="asigna('d5', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>6</th>
                        <th colspan="1">Vigilancia&nbsp;<input id="d6" name="d6" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('d6', 3)">Alteración de conciencia y/o conducta insegura (desorientado, confuso, exitado, agresivo)</td>
                        <td style="cursor: pointer" onclick="asigna('d6', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d6', 3)">Con riesgo de caida o de incidentes (limitacion fisica o cognoscituva y/o > de 70 años y < de 2 años) </td>
                        <td style="cursor: pointer" onclick="asigna('d6', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d6', 2)">Conciente pero intranquilo y c/riesgo de caida o incidente (bajo efectos de farmacos, con 1 o + elementos)</td>
                        <td style="cursor: pointer" onclick="asigna('d6', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('d6', 1)">Conciente pero c/inestabilidad de la marcha o no camina por reposo, edad o alteración fisica</td>
                        <td style="cursor: pointer" onclick="asigna('d6', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('d6', 0)">Conciente, orientado, autonomo</td>
                        <td style="cursor: pointer" onclick="asigna('d6', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="3" style="background-color: #3366CC">CATEGORIA DE CUIDADOS TERAPEUTICOS</th>
                    </tr>
                    <tr>
                        <th>7</th>
                        <th colspan="1">Medición de signos vitales&nbsp;<input id="r7" name="r7" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r7', 3)">Control por 8 veces y mas (cada 3 horas o mas)</td>
                        <td style="cursor: pointer" onclick="asigna('r7', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r7', 2)">Control por 4 a 7 veces (cada 4,5,6,7 horas)</td>
                        <td style="cursor: pointer" onclick="asigna('r7', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r7', 1)">Control por 2 a 3 veces (cada 8,9,10,11,12 horas)</td>
                        <td style="cursor: pointer" onclick="asigna('r7', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r7', 0)">Control por 1 vez (cada 13 a cada 24 horas)</td>
                        <td style="cursor: pointer" onclick="asigna('r7', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>8</th>
                        <th colspan="1">Balance hidrico&nbsp;<input id="r8" name="r8" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r8', 3)">Balance hidrico por 6 veces o mas  (cada 4 horas o mas frecuente)</td>
                        <td style="cursor: pointer" onclick="asigna('r8', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r8', 2)">Balance hidrico por 2 a 5 veces (cada 12,8,6 o 5 horas)</td>
                        <td style="cursor: pointer" onclick="asigna('r8', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r8', 1)">Balance hidrico por 1 vez (cada 24 horas o menor de cada 12 horas)</td>
                        <td style="cursor: pointer" onclick="asigna('r8', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r8', 0)">No requiere</td>
                        <td style="cursor: pointer" onclick="asigna('r8', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>9</th>
                        <th colspan="1">Cuidados oxigenoterapia&nbsp;<input id="r9" name="r9" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r9', 3)">Administración de oxigeno por tubo y canula endotraqueak y/o VMI y VMNI permanente</td>
                        <td style="cursor: pointer" onclick="asigna('r9', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r9', 2)">Administración de oxigeno por halo, mascara, incubadora y/o VMNI intermitente</td>
                        <td style="cursor: pointer" onclick="asigna('r9', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r9', 1)">Administración de oxigeno por bigotera</td>
                        <td style="cursor: pointer" onclick="asigna('r9', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r9', 0)">Sin oxigenoterapia</td>
                        <td style="cursor: pointer" onclick="asigna('r9', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>10</th>
                        <th colspan="1">Cuidados diarios de la via aerea&nbsp;<input id="r10" name="r10" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r10', 3)">Con via aerea artificial (tubo o canula endotraqueal)</td>
                        <td style="cursor: pointer" onclick="asigna('r10', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r10', 3)">Via aerea artificial y/o natural con 4 o mas aspiraciones secresiones tranqueales y/o apoyo kinesico</td>
                        <td style="cursor: pointer" onclick="asigna('r10', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r10', 2)">Respira x via natural y requiere de 1 a 3 aspiraciones de secresiones y/o apoyo kinesico 2 a 3 veces/día</td>
                        <td style="cursor: pointer" onclick="asigna('r10', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r10', 1)">Respira x via natural, sin aspiracion de secresiones y/o apoyo kinesico 1 vez al día</td>
                        <td style="cursor: pointer" onclick="asigna('r10', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r10', 0)">No requiere apoyo ventilatorio adicional</td>
                        <td style="cursor: pointer" onclick="asigna('r10', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>11</th>
                        <th colspan="1">Intervenciones profesionales&nbsp;<input id="r11" name="r11" readonly type="text" size="1" maxlength="1" style="font-size: 16px; " value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r11', 3)">1 o mas procedimientos invasivos realizados por medicos en la últimas 24 hrs.</td>
                        <td style="cursor: pointer" onclick="asigna('r11', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r11', 3)">3 o mas procedimientos invasivos realizados por enfermera, matrona en la últimas 24 hrs.</td>
                        <td style="cursor: pointer" onclick="asigna('r11', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r11', 2)">1 o 2 procedimientos invasivos realizados por enfermera, matrona en la últimas 24 hrs.</td>
                        <td style="cursor: pointer" onclick="asigna('r11', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r11', 1)">1 o mas procedimientos invasivos realizados por otros prefesionales en las últimas 24 hrs.</td>
                        <td style="cursor: pointer" onclick="asigna('r11', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r11', 0)">No se realizan procedimientos invasivos en 24 hrs.</td>
                        <td style="cursor: pointer" onclick="asigna('r11', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>12</th>
                        <th colspan="1">Cuidados de la piel y curaciones&nbsp;<input id="r12" name="r12" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r12', 3)">Curación o refuerzo 3 o mas veces al día, independiente de la complejidad de la tecnica</td>
                        <td style="cursor: pointer" onclick="asigna('r12', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r12', 2)">Curación o refuerzo 1 a 2 veces al día, independiente de la complejidad de la tecnica</td>
                        <td style="cursor: pointer" onclick="asigna('r12', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r12', 2)">Prevención compleja de lesiones de piel: uso de colchon antiescara, piel de cordero otros</td>
                        <td style="cursor: pointer" onclick="asigna('r12', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r12', 1)">Prevención corriente de lesiones: aseo, lubricación y protección de zonas propensas</td>
                        <td style="cursor: pointer" onclick="asigna('r12', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r12', 0)">No requiere</td>
                        <td style="cursor: pointer" onclick="asigna('r12', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>13</th>
                        <th colspan="1">Administración de TTO farmacologico&nbsp;<input id="r13" name="r13" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r13', 3)">Tratamiento intratecal e inyectable endovenoso, directo o por fleboclisis</td>
                        <td style="cursor: pointer" onclick="asigna('r13', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r13', 3)">Tratamiento diario con 5 o más fármacos distintos, administrados por diferentes vías no inyectable</td>
                        <td style="cursor: pointer" onclick="asigna('r13', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r13', 2)">Tratamiento inyectable no endovenoso (IM,SC,ID)</td>
                        <td style="cursor: pointer" onclick="asigna('r13', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r13', 2)">Tratamiento diario con 2 a 4 fármacos, administrados por diferentes vías no inyectable</td>
                        <td style="cursor: pointer" onclick="asigna('r13', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r13', 1)">Tratamiento con 1 fármaco, administrado por diferentes vías no inyectable</td>
                        <td style="cursor: pointer" onclick="asigna('r13', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r13', 0)">Sin tratamiento farmacológico.</td>
                        <td style="cursor: pointer" onclick="asigna('r13', 0)">&nbsp;&nbsp;0</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>14</th>
                        <th colspan="1">Presencia de elementos invasivos&nbsp;<input id="r14" name="r14" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                        <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td colspan="2" onclick="asigna('r14', 3)">Con 3 o mas elementos invasivos (sondas, drenajes, cateteres o vias vasculares)</td>
                        <td style="cursor: pointer" onclick="asigna('r14', 3)">&nbsp;&nbsp;3</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r14', 2)">Con 1 a 2 elementos invasivos (sondas, drenajes, via arterial,catetere o via venosa central)</td>
                        <td style="cursor: pointer" onclick="asigna('r14', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r14', 2)">Con 2 o mas vias venosas perifericas (mariposas, teflones, agujas)</td>
                        <td style="cursor: pointer" onclick="asigna('r14', 2)">&nbsp;&nbsp;2</td>
                    </tr>
                    <tr class="odd">
                        <td colspan="2" onclick="asigna('r14', 1)">Con 1 via venosa periferica (mariposas, teflones, agujas)</td>
                        <td style="cursor: pointer" onclick="asigna('r14', 1)">&nbsp;&nbsp;1</td>
                    </tr>
                    <tr>
                        <td colspan="2" onclick="asigna('r14', 0)">Sin elementos invasivos</td>
                        <td style="cursor: pointer" onclick="asigna('r14', 0)">&nbsp;&nbsp;0</td>
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

                <h1>Pauta de Categorización de pacientes Riesgo/Dependencia</h1>
                <br>
                <fieldset>
                    <table border="1">
                        <thead>
                            <tr>
                                <td colspan="2" rowspan="2">&nbsp;</td>
                                <th colspan="3">CUIDADOS UNIVERSALES</th>
                            </tr>

                            <tr>
                                <td style="text-align: center">1</td>
                                <td style="text-align: center">2</td>
                                <td style="text-align: center">3</td>
                            </tr>

                            <tr>
                                <th colspan="2">RIESGO TERAPEUTICO</th>
                                <td  class="odd">Dependencia Total (13 a 18 ptos.)</td>
                                <td  class="odd">Dependencia Parcial (7 a 12 ptos.)</td>
                                <td  class="odd">Autovalencia Parcial </td>
                            </tr>
                        </thead>
                        <tr>
                            <td style="background-color: red;">A</td>
                            <td class="odd">Máximo&nbsp;Riesgo&nbsp;Terapeutico&nbsp;(19&nbsp;a&nbsp;24&nbsp;ptos)</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('A1: Máximo Riesgo y Dependencia Total');">A1</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('A2: Máximo Riesgo y Dependencia Parcial');">A2</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('A3: Máximo Riesgo y Autovalencia Parcial');">A3</td>
                        </tr>
                        <tr >
                            <td style="background-color: blue;color: #ffffff">B</td>
                            <td class="odd">Alto Riesgo Terapeutico (12 a 18 ptos)</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('B1: Alto Riesgo y Dependencia Total');">B1</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('B2: Alto Riesgo y Dependencia Parcial');">B2</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('B3: Alto Riesgo y Autovalencia Parcial');">B3</td>
                        </tr>
                        <tr>
                            <td style="background-color: pink">C</td>
                            <td class="odd">Mediano Riesgo Terapeutico (6 a 11 ptos)</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('C1: Mediano Riesgo y Dependencia Total');">C1</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('C2: Mediano Riesgo y Dependencia Parcial');">C2</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('C3: Mediano Riesgo y Autovalencia Parcial');">C3</td>
                        </tr>
                        <tr>
                            <td style="background-color: yellow">D</td>
                            <td class="odd">Bajo Riesgo Terapeutico (0 a 5 ptos)</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('D1: Bajo Riesgo y Dependencia Total');">D1</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('D2: Bajo Riesgo y Dependencia Parcial');">D2</td>
                            <td style="cursor: pointer;text-align: center" onclick="alert('D3: Bajo Riesgo y Autovalencia Parcial');">D3</td>
                        </tr>
                    </table>
                    <br>
                    <center>
                        <%
                            // <input type="button" style="width: 90px; height: 40px;background-color: #000000;color: white" onclick="cat()" value="CALCULAR">
                        %>
                    </center>
                    <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br>
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
                            border-radius: 8px 8px 8px 8px;
                            float: left;
                            position: fixed;
                            min-height: 225px;
                            margin-bottom: 10px;
                            margin-right: 10px;
                            overflow: hidden;
                            text-align: center;
                            width: 300px;
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

                    <%
                        }
                    %>
