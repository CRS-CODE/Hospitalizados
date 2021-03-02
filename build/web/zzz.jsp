<%-- 
    Document   : uh_visita
    Created on : 10-may-2012, 11:48:38
    Author     : EseGamboa
--%>

<%@page import="CapaNegocio.NegocioQ"%>
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



<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script>

    function chequear(rbt) {
        $(rbt).prop("checked", true);
        calcular_upp();
        calcular_prevencion();
    }

    function calcular_upp() {
        var estado_fisico;
        var estado_mental;
        var actividad;
        var movilidad;
        var incontinencia;

        estado_fisico = $('input[name=rbt_estado_fisico]').filter(':checked').val();
        estado_mental = $('input[name=rbt_estado_mental]').filter(':checked').val();
        actividad = $('input[name=rbt_actividad]').filter(':checked').val();
        movilidad = $('input[name=rbt_movilidad]').filter(':checked').val();
        incontinencia = $('input[name=rbt_incontinencia]').filter(':checked').val();

        if (isNaN(estado_fisico)) {
            estado_fisico = 0;
        }
        if (isNaN(estado_mental)) {
            estado_mental = 0;
        }
        if (isNaN(actividad)) {
            actividad = 0;
        }
        if (isNaN(movilidad)) {
            movilidad = 0;
        }
        if (isNaN(incontinencia)) {
            incontinencia = 0;
        }

        var total = parseFloat(estado_fisico) + parseFloat(estado_mental) + parseFloat(actividad) + parseFloat(movilidad) + parseFloat(incontinencia);
        $('#txt_upp_puntaje').val(total);

        if (total > 14) {
            $('#txt_upp_riesgo').val('RIESGO MINIMO/NO RIESGO');
        } else if (total >= 13) {
            $('#txt_upp_riesgo').val('RIESGO MEDIO');
        } else if (total >= 10) {
            $('#txt_upp_riesgo').val('RIESGO ALTO');
        } else if (total >= 5) {
            $('#txt_upp_riesgo').val('RIESGO MUY ALTO');
        } else {
            $('#txt_upp_riesgo').val('');
        }


    }


    function calcular_prevencion() {
        var uno;
        var dos;
        var tres;
        var cuatro;
        var cinco;

        uno = $('input[name=rbt_1]').filter(':checked').val();
        dos = $('input[name=rbt_2]').filter(':checked').val();
        tres = $('input[name=rbt_3]').filter(':checked').val();
        cuatro = $('input[name=rbt_4]').filter(':checked').val();
        cinco = $('input[name=rbt_5]').filter(':checked').val();

        if (isNaN(uno)) {
            uno = 0;
        }
        if (isNaN(dos)) {
            dos = 0;
        }
        if (isNaN(tres)) {
            tres = 0;
        }
        if (isNaN(cuatro)) {
            cuatro = 0;
        }
        if (isNaN(cinco)) {
            cinco = 0;
        }

        var total = parseFloat(uno) + parseFloat(dos) + parseFloat(tres) + parseFloat(cuatro) + parseFloat(cinco);
        $('#txt_prevencion_total').val(total);



    }


</script>

<script src="<%=neg.getLocal()%>js/jquery/jquery.js"></script>


<jsp:include page="Header.jsp" />



<body>
   

    <table>
        <thead>
            <tr>
                <th colspan="6"  style="background-color: #3366CC">ESCALA DE NORTON </th>
            </tr>
        <th>PTJE. </th>
        <th>ESTADO FISICO</th>
        <th>ESTADO MENTAL</th>
        <th>ACTIVIDAD </th>
        <th>MOVILIDAD</th>
        <th>INCONTINENCIA</th>
    </tr>
</thead>
<tr>
    <td>4</td>
    <td onclick="chequear('#rbt_estado_fisico_4')"><input type="radio" name="rbt_estado_fisico" id="rbt_estado_fisico_4" value="4" />BUENO</td>
    <td onclick="chequear('#rbt_estado_mental_4')"><input type="radio" name="rbt_estado_mental" id="rbt_estado_mental_4" value="4" />ALERTA</td>
    <td onclick="chequear('#rbt_actividad_4')"><input type="radio" name="rbt_actividad" id="rbt_actividad_4" value="4" />TOTAL</td>
    <td onclick="chequear('#rbt_movilidad_4')"><input type="radio" name="rbt_movilidad" id="rbt_movilidad_4" value="4" />AMBULANTE</td>
    <td onclick="chequear('#rbt_incontinencia_4')"><input type="radio" name="rbt_incontinencia" id="rbt_incontinencia_4" value="4" />NINGUNA</td>
</tr>
<tr>
    <td>3</td>
    <td onclick="chequear('#rbt_estado_fisico_3')"><input type="radio" name="rbt_estado_fisico" id="rbt_estado_fisico_3" value="3" />MEDIANO</td>
    <td onclick="chequear('#rbt_estado_mental_3')"><input type="radio" name="rbt_estado_mental" id="rbt_estado_mental_3" value="3" />APATICO</td>
    <td onclick="chequear('#rbt_actividad_3')"><input type="radio" name="rbt_actividad" id="rbt_actividad_3" value="3" />DISMINUIDA</td>
    <td onclick="chequear('#rbt_movilidad_3')"><input type="radio" name="rbt_movilidad" id="rbt_movilidad_3" value="3" />CAMINO CON AYUDA</td>
    <td onclick="chequear('#rbt_incontinencia_3')"><input type="radio" name="rbt_incontinencia" id="rbt_incontinencia_3" value="3" />OCASIONAL</td>
</tr>
<tr>
    <td>2</td>
    <td onclick="chequear('#rbt_estado_fisico_2')"><input type="radio" name="rbt_estado_fisico" id="rbt_estado_fisico_2" value="2" />REGULAR</td>
    <td onclick="chequear('#rbt_estado_mental_2')"><input type="radio" name="rbt_estado_mental" id="rbt_estado_mental_2" value="2" />CONFUSO</td>
    <td onclick="chequear('#rbt_actividad_2')"><input type="radio" name="rbt_actividad" id="rbt_actividad_2" value="2" />MUY LIMITADA</td>
    <td onclick="chequear('#rbt_movilidad_2')"><input type="radio" name="rbt_movilidad" id="rbt_movilidad_2" value="2" />SENTADO</td>
    <td onclick="chequear('#rbt_incontinencia_2')"><input type="radio" name="rbt_incontinencia" id="rbt_incontinencia_2" value="2" />URINARIA O FECAL</td>
</tr>
<tr>
    <td>1</td>
    <td onclick="chequear('#rbt_estado_fisico_1')"><input type="radio" name="rbt_estado_fisico" id="rbt_estado_fisico_1" value="1" />MUY  ALTO</td>
    <td onclick="chequear('#rbt_estado_mental_1')"><input type="radio" name="rbt_estado_mental" id="rbt_estado_mental_1" value="1" />COMATOSO</td>
    <td onclick="chequear('#rbt_actividad_1')"><input type="radio" name="rbt_actividad" id="rbt_actividad_1" value="1" />INMOVIL</td>
    <td onclick="chequear('#rbt_movilidad_1')" ><input type="radio" name="rbt_movilidad" id="rbt_movilidad_1" value="1" />POSTRADO</td>
    <td onclick="chequear('#rbt_incontinencia_1')" ><input type="radio" name="rbt_incontinencia" id="rbt_incontinencia_1" value="1" />URINARIA Y FECAL</td>
</tr>


</table>
<br>
<table border="1" >
    <thead> 
        <tr>
            <th colspan="4" style="background-color: #3366CC" >CLASIFICACION DE RIESGO UPP</th>
        </tr>
    </thead>
    <tr>
        <td>DE 05 A 09</td>
        <td>RIESGO MUY ALTO</td>
        <td>PUNTAJE</td>

    </tr>
    <tr>
        <td>DE 10 A 12</td>
        <td>RIESGO ALTO</td>
        <td> <input type="text" name="txt_upp_puntaje" id="txt_upp_puntaje" value="0" style=" width: 70% "   /></td>
    </tr>
    <tr>
        <td>DE 13 A 14</td> 
        <td>RIESGO MEDIO</td>
        <td>RIESGO</td>
    </tr>
    <tr>
        <td>MAYOR A 14</td>
        <td>RIESGO MINIMO/NO RIESGO</td>
        <td><input type="text" name="txt_upp_riesgo" id="txt_upp_riesgo" value=""  style=" width: 70% "  /></td>
    </tr>
    <thead>
        <tr>
            <th>RIESGO MINIMO</th>
            <th colspan="2" >RIESGO MEDIO Aplique las medidas anteriores además de:</th>
            <th>RIESGO MUY ALTO Todo lo anterior más:</th>
        </tr>
    </thead>
    <tr>
        <td>Evalúe estado de piel (seca, húmeda, c/s fiebre)</td>
        <td>Lubricación piel c/crema hidrat. s/perfume, mpinimo 2 veces al día</td>
        <td>No permita el contacto de prominencias óseas entre sí</td>
        <td>Uso colchón antiescaras</td>

    </tr>
    <tr>
        <td>Mantener higiene de piel (ducha, baño, etc.)</td>
        <td>Cambio pañales mín. 4 veces al día, previo aseo genital</td>
        <td>Talones, sacro, codos, trocánter, escrápula, columna, pabellón, auricular, revisión diaria protección</td>
        <td>Vigilar funcionamiento del colchón</td>

    </tr>
    <tr>
        <td>Mantener ropa limpia, seca, libre de arrugas</td>
        <td>Apósito transparente, prominencias óseas</td>
        <td>Control estricto de ingesta alimentaria</td>

    </tr>
    <tr>
        <td>Levante al paciente si esta autorizado</td>
        <td>Cambios de posición c/3 o 2 horas</td>
        <td>Revaloración de riesgo UPP</td>

    </tr>
    <tr>

        <td>Incentive el movimiento en cama</td>
        <td>Uso de sabanilla al movilizar NO ARRASTRE AL PACIENTE</td>

    </tr>
</table>
<br>

<table border="1" >
    <thead>
        <tr>
            <th  style="background-color: #3366CC" colspan="6" >PREVENCION DE CAIDAS</th>
        </tr>
        <tr>
            <th>ESCALA DE VALORACIÓN DEL RIESGO DE CAÍDAS</th>
            <th>SI</th>
            <th>NO</th>
            <th colspan="3" >MEDIDAS DE PREVENCIÓN DE CAÍDAS</th>

        </tr>
    </thead>
    <tr>
        <td>¿Tiene historia de caídas previa ha ingresado por caída?</td>
        <td onclick="chequear('#rbt_1_1')"><input type="radio" name="rbt_1" id="rbt_1_1" value="1" />1</td>
        <td onclick="chequear('#rbt_1_0')"><input type="radio" name="rbt_1" id="rbt_1_0" value="0" checked="checked" />0</td>
        <td>BAJO RIESGO 0 PTo.</td>
        <td>MEDIANO RIESGO 1 PTo.</td>
        <td>ALTO RIESGO 2 o más Ptos.</td>

    </tr>
    <tr>
        <td>¿Esta agitado, confuso, desorientado?</td>
        <td onclick="chequear('#rbt_2_1')"><input type="radio" name="rbt_2" id="rbt_2_1" value="1" />1</td>
        <td onclick="chequear('#rbt_2_0')"><input type="radio" name="rbt_2" id="rbt_2_0" value="0" checked="checked" />0</td>
        <td>Instalar VERDE</td>
        <td>Instalar AMARILLO</td>
        <td>Instalar ROJO</td>
    </tr>
    <tr>
        <td>¿Oye o e mal?</td>
        <td onclick="chequear('#rbt_3_1')"><input type="radio" name="rbt_3" id="rbt_3_1" value="1" />1</td>
        <td onclick="chequear('#rbt_3_0')"><input type="radio" name="rbt_3" id="rbt_3_0" value="0" checked="checked" />0</td>
        <td>Normas generles de prevención de caídas<br><br><br>Verificar entrega de tríptico</td>
        <td>Medidas anteriores más:<br> Vigilancia y/o asistencia al leventarse, deambular ida al baño, higiene.<br><br>Barandas en alto 24 horas</td>
        <td>Medidas anteriores más:<br> Revise indicación médica de contención farmacológica.<br>Evalué contención mecánica (como último recurso) <br>Relice cuidados de enfermería durante la contención</td>
    </tr>
    <tr>
        <td>¿Nececita acudir al baño con frecuencia o es incontinente?</td>
        <td onclick="chequear('#rbt_4_1')"><input type="radio" name="rbt_4" id="rbt_4_1" value="1" />1</td>
        <td onclick="chequear('#rbt_4_0')"><input type="radio" name="rbt_4" id="rbt_4_0" value="0" checked="checked" />0</td>
    </tr>
    <tr>
        <td>¿Tiene dificultades para caminar y/o incorporarse?</td>
        <td onclick="chequear('#rbt_5_1')"><input type="radio" name="rbt_5" id="rbt_5_1" value="1" />1</td>
        <td onclick="chequear('#rbt_5_0')"><input type="radio" name="rbt_5" id="rbt_5_0" value="0" checked="checked" />0</td>
    </tr>
    <tr>
        <td>TOTAL</td>
        <td colspan="2" >
            <input type="text" name="txt_prevencion_total" id="txt_prevencion_total" value="0" style=" width: 80px  " />
        </td>
    </tr>
    <tr>
        <td colspan="5" >EN CASO DE CONTENCIÓN MECÁNICA VERIFICAR INDICACIÓN MÉDICA EN FICHA</td>
        <td>
            <input type="radio" name="rbt_verifica_ficha" value="1" /> SI   

            <input type="radio" name="rbt_verifica_ficha" value="0" /> NO</td>
    </tr>
</table>


</body>

<jsp:include page="Footer.jsp" />
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
    table { /* background:#D3E4E5;*/
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
    tbody td:hover { background:#99BCBF;
                     border:1px solid #03476F;
                     color:#000000;
    }
</style>

<%
    }
%>
