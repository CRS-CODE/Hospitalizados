<%-- 
    Document   : yyy
    Created on : 23-dic-2015, 9:44:19
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
<script src="<%=neg.getLocal()%>js/jquery/jquery.js"></script>
<jsp:include page="Header.jsp" />

<script>
    function setea(rbt) {
        var cadena = '';
        // alert(rbt);
        //var porId = document.getElementById(rbt).checked;
        // alert(porId);

        cadena += '00-03-06'
        // document.getElementById('opener_aseo_matinal').innerHTML='asda';
        $('#opener_aseo_matinal').text(cadena).button("refresh");
    }

</script>


<script>
    $(function () {
        $("#check").button();
        $("#format_aseo_matinal").buttonset();
    });

    $(function () {
        $("#format_bano_cama").buttonset();
        $("#format_aseo_bucal").buttonset()
    });
</script>
<script>
    $(function () {
        $("#dialog_aseo_matinal").dialog({
            width: 600,
            autoOpen: false,
            show: {
                effect: "blind"
            },
            hide: {
                effect: "clip"
            }
        });

        $("#opener_aseo_matinal").click(function () {
            $("#dialog_aseo_matinal").dialog("open");
        });
    });

    $(function () {
        $("#dialog_bano_cama").dialog({
            width: 1200,
            autoOpen: false,
            show: {
                effect: "blind"
            },
            hide: {
                effect: "clip"
            }
        });

        $("#opener_bano_cama").click(function () {
            $("#dialog_bano_cama").dialog("open");
        });
    });


    $(function () {
        $("#dialog_aseo_bucal").dialog({
            autoOpen: false,
            show: {
                effect: "blind"
            },
            hide: {
                effect: "clip"
            }
        });

        $("#opener_aseo_bucal").click(function () {
            $("#dialog_aseo_bucal").dialog("open");
        });
    });
</script>
<style>
    #format_aseo_matinal { margin-top: 2em; }
    #format_bano_cama { margin-top: 2em; }
    #format_aseo_bucal { margin-top: 2em; }
</style>
<style>

    [type="checkbox"] + label {

        color:  #fff;
        text-shadow: #2c3e50 2px 2px 1px;
        transition: color .3s;
    }
    [type="checkbox"] + label:after {

        transition: all .2s;
        top: 0; 
        left: 0;
    }
    /* OFF */
    [type="checkbox"]:not(:checked) + label:after {
        background: url('check_off.png') left center no-repeat; 
        background: #000;
    }
    /* ON */
    [type="checkbox"]:checked + label {
        /*color: #fff; */
        background:#fff; 
        color: #fff;

    }
    [type="checkbox"]:checked + label:after {
        background: url('check_on.png') left center no-repeat;
    }
</style>
<body>
<legend>PLAN DE ATENCIÓN DE ENFERMERÍA <% out.write(" ");%></legend>

  <script>
  $(function() {
    $( "#check" ).button();
    $( "#format" ).buttonset();
  });
  </script>
  <style>
  #format { margin-top: 2em; }
  </style>
</head>


 
<div id="format">
  <input type="checkbox" id="check1"><label for="check1">00</label>
  <input type="checkbox" id="check2"><label for="check2">01</label>
  <input type="checkbox" id="check3"><label for="check3">02</label>
   <input type="checkbox" id="check4"><label for="check4">03</label>
</div>
<table border="1" >
    <thead>
        <tr>
            <th colspan='4' >PLAN DE ATENCION DE ENFERMERIA<th>
        </tr>
        <tr>
            <th>CUIDADOS VÍA AEREA</th>
            <th>HORARIO</th>
            <th>ASEO Y CONFORT</th>
            <th>HORARIO</th>
        </tr>
    </thead>
    <tr>
        <td>ASPIRACION DE SECRECIONES BUCALES</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>ASEO MATINAL</td>
        <td>
            <input type="text" name="txt_aseo_matinal" id="txt_aseo_matinal" value="" />

            <div id="dialog_aseo_matinal" title="Horario Aseo matinal">
                <div id="format_aseo_matinal">
                    <input type="checkbox" id="check00" onclick="setea('00')" ><label for="check00">00</label>
                    <input type="checkbox" id="check01" onclick="setea('01')"><label for="check01">01</label>
                    <input type="checkbox" id="check02" onclick="setea('02')"><label for="check02">02</label>
                    <input type="checkbox" id="check03" onclick="setea('03')"><label for="check03">03</label>
                    <input type="checkbox" id="check04"><label for="check04">04</label>
                    <input type="checkbox" id="check05"><label for="check05">05</label>
                    <input type="checkbox" id="check06"><label for="check06">06</label>
                    <input type="checkbox" id="check07"><label for="check07">07</label>
                    <input type="checkbox" id="check08"><label for="check08">08</label>
                    <input type="checkbox" id="check09"><label for="check09">09</label>
                    <input type="checkbox" id="check10"><label for="check10">10</label>
                    <input type="checkbox" id="check11"><label for="check11">11</label>

                    <input type="checkbox" id="check12"><label for="check12">12</label>
                    <input type="checkbox" id="check13"><label for="check13">13</label>
                    <input type="checkbox" id="check14"><label for="check14">14</label>
                    <input type="checkbox" id="check15"><label for="check15">15</label>
                    <input type="checkbox" id="check16"><label for="check16">16</label>
                    <input type="checkbox" id="check17"><label for="check17">17</label>
                    <input type="checkbox" id="check18"><label for="check18">18</label>
                    <input type="checkbox" id="check19"><label for="check19">19</label>
                    <input type="checkbox" id="check20"><label for="check20">20</label>
                    <input type="checkbox" id="check21"><label for="check21">21</label>
                    <input type="checkbox" id="check22"><label for="check22">22</label>
                    <input type="checkbox" id="check23"><label for="check23">23</label>
                </div>  
            </div>

            <button id="opener_aseo_matinal">Fijar Horario</button>

        </td>
    </tr>
    <tr>
        <td>MANTENER CABECERA 30°</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>BAÑO EN CAMA/DUCHA</td>
        <td>


            <input type="text" name="txt_bano_cama" id="txt_bano_cama" value="" />


            <div id="dialog_bano_cama" title="Horario Baño cama">
                <div id="format_bano_cama">
                    <input type="checkbox" id="check00"><label for="check00">00</label>
                    <input type="checkbox" id="check01"><label for="check01">01</label>
                    <input type="checkbox" id="check02"><label for="check02">02</label>
                    <input type="checkbox" id="check03"><label for="check03">03</label>
                    <input type="checkbox" id="check04"><label for="check04">04</label>
                    <input type="checkbox" id="check05"><label for="check05">05</label>
                    <input type="checkbox" id="check06"><label for="check06">06</label>
                    <input type="checkbox" id="check07"><label for="check07">07</label>
                    <input type="checkbox" id="check08"><label for="check08">08</label>
                    <input type="checkbox" id="check09"><label for="check09">09</label>
                    <input type="checkbox" id="check10"><label for="check10">10</label>
                    <input type="checkbox" id="check11"><label for="check11">11</label>

                    <input type="checkbox" id="check12"><label for="check12">12</label>
                    <input type="checkbox" id="check13"><label for="check13">13</label>
                    <input type="checkbox" id="check14"><label for="check14">14</label>
                    <input type="checkbox" id="check15"><label for="check15">15</label>
                    <input type="checkbox" id="check16"><label for="check16">16</label>
                    <input type="checkbox" id="check17"><label for="check17">17</label>
                    <input type="checkbox" id="check18"><label for="check18">18</label>
                    <input type="checkbox" id="check19"><label for="check19">19</label>
                    <input type="checkbox" id="check20"><label for="check20">20</label>
                    <input type="checkbox" id="check21"><label for="check21">21</label>
                    <input type="checkbox" id="check22"><label for="check22">22</label>
                    <input type="checkbox" id="check23"><label for="check23">23</label>
                </div>  
            </div>
            <button id="opener_bano_cama">Fijar Horario</button>
        </td>
    </tr>

    <tr>
        <td>CAMBIO FRASCO HUMIDIFICADOR</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>ASEO BUCAL/PROTESIS DENTAL CON CLOHEXIDINA</td>
        <td>


            <input type="text" name="txt_aseo_bucal" id="txt_aseo_bucal" value="" />


            <div id="dialog_aseo_bucal" title="Horario Aseo Bucal">
                <div id="format_aseo_bucal">
                    <input type="checkbox" id="check00"><label for="check00">00</label>
                    <input type="checkbox" id="check01"><label for="check01">01</label>
                    <input type="checkbox" id="check02"><label for="check02">02</label>
                    <input type="checkbox" id="check03"><label for="check03">03</label>
                    <input type="checkbox" id="check04"><label for="check04">04</label>
                    <input type="checkbox" id="check05"><label for="check05">05</label>
                    <input type="checkbox" id="check06"><label for="check06">06</label>
                    <input type="checkbox" id="check07"><label for="check07">07</label>
                    <input type="checkbox" id="check08"><label for="check08">08</label>
                    <input type="checkbox" id="check09"><label for="check09">09</label>
                    <input type="checkbox" id="check10"><label for="check10">10</label>
                    <input type="checkbox" id="check11"><label for="check11">11</label>

                    <input type="checkbox" id="check12"><label for="check12">12</label>
                    <input type="checkbox" id="check13"><label for="check13">13</label>
                    <input type="checkbox" id="check14"><label for="check14">14</label>
                    <input type="checkbox" id="check15"><label for="check15">15</label>
                    <input type="checkbox" id="check16"><label for="check16">16</label>
                    <input type="checkbox" id="check17"><label for="check17">17</label>
                    <input type="checkbox" id="check18"><label for="check18">18</label>
                    <input type="checkbox" id="check19"><label for="check19">19</label>
                    <input type="checkbox" id="check20"><label for="check20">20</label>
                    <input type="checkbox" id="check21"><label for="check21">21</label>
                    <input type="checkbox" id="check22"><label for="check22">22</label>
                    <input type="checkbox" id="check23"><label for="check23">23</label>
                </div>  
            </div>
            <button id="opener_aseo_bucal">Fijar Horario</button>

        </td>
    </tr>

    <tr>
        <td>ASEO DE CAVIDADES NASAL Y BUCAL</td>
        <td>
            <input type="text" name="txt_aseo_cavidad" id="txt_aseo_cavidad" value="" />
            <button id="opener_aseo_cavidad">Fijar horarios</button>

        </td>
        <td>LAVADO CABELLO</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>
    <tr>
        <td>CAMBIO NEBULIZADOR Y BOLSA</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>LAVADO MANOS</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>

    <tr>
        <td></td>
        <td></td>
        <td>CAMBIO PAÑAL PREVIO ASEO GENITAL</td>
        <td>
            <input type="text" name="txt_cambio_panal" id="txt_cambio_panal" value="" />
            <button id="opener_cambio_panal">Fijar horarios</button>
        </td>
    </tr>


    <tr>
        <th>CUIDADOS SNG Y/O GASTROSTOMIA</th>
        <th>HORARIO</th>
        <td>CAMBIO ROPA CAMA</td>
        <td>
            <input type="text" name="txt_cambio_ropa_cama" id="txt_cambio_ropa_cama" value="" />
            <button id="opener_cambio_ropa_cama">Fijar horarios</button>
        </td>
    </tr>


    <tr>
        <td>LAVAR CON AGUA TIBIA SNG</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>LEVANTAR A SILLA VIGILADO</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>

    <tr>
        <td>ASPIRAR CADA __ HORAS</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>CAMBIOS DE POSICIÓN</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>


    <tr>
        <td>CAMBIAR SITIO DE FIJACIÓN</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>LUBRICAR PIEL</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>


    <tr>
        <td>OBSERVAR TOLERANCIA A PAPILLA</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>ASISTENCIA EN ALIMENTACIÓN</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>



    <tr>
        <th>CUIDADOS CUP</th>
        <th>HORARIO</th>
        <th>MEDIDAS GENERALES DE PREV. CAIDAS</th>
        <th>HORARIO</th>
    </tr>

    <tr>
        <td>ROTAR LUGAR DE FIJACIÓN CUP</td>
        <td>
            <input type="text" name="txt_lugar_fijacion" id="txt_lugar_fijacion" value="" />
            <button id="opener_lugar_fijacion">Fijar horarios</button>  
        </td>
        <td>CAMA NIVEL MAS BAJO</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>

    <tr>
        <td>OBSERVAR POSICIÓN RECOLECTOR</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>USO DE BARANDAS</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>
    <tr>
        <td>ASEO GENITAL CADA 12 HORAS</td>
        <td>

            <input type="text" name="txt_aseo_genital" id="txt_aseo_genital" value="" />
            <button id="opener_aseo_genital">Fijar horarios</button> 
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>CUIDADO PACIENTES INMOVILIZADOS</th>
        <th>HORARIO</th>
        <th>CUIDADO HERIDAS</th>
        <th>HORARIO</th>
    </tr>
    <tr>
        <td>PROTEGER ZONAS INMOVILIZADAS</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>OBSERVAR APOSITOS</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
    </tr>

    <tr>
        <td>MANTENER PAC. SEMI SENTADO</td>
        <td>
            <input type="text" name="txt_" id="txt_" value="" />
        </td>
        <td>AVISAR PRESENCIA DOLOR</td>
        <td>
            <input type="text" name="txt_presencia_dolor" id="txt_presencia_dolor" value="" />
        </td>
    </tr>

    <tr>
        <td>CONTROL DE PACIENTES CADA __ HRS</td>
        <td>
            <input type="text" name="txt_control_paciente" id="txt_control_paciente" value="" />
            <button id="opener_control_paciente">Fijar horarios</button> 
        </td>
        <td>AVISAR PRESENCIA SANGRAMIENTO</td>
        <td>
            <input type="text" name="txt_presencia_sangramiento" id="txt_presencia_sangramiento" value="" />
        </td>
    </tr>
</table>


</body>
<jsp:include page="Footer.jsp" />

<%
    }
%>
