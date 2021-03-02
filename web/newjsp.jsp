<%-- 
    Document   : receta
    Created on : 01-sep-2015, 17:42:05
    Author     : Informatica
--%>

<%@page import="CapaDato.cUnidadMedida"%>
<%@page import="CapaDato.cVisita"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="CapaDato.cPaciente"%>
<%@page import="CapaDato.cInsumo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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

        String obtiene_usuario = session1.getAttribute("usuario_rut").toString();

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

        ArrayList lista_insumo = new ArrayList();

        Iterator it_insumo = lista_insumo.iterator();

        ArrayList lista_unidad_medida = neg.lista_unidad_medida();
        Iterator it_unidad_medida = lista_unidad_medida.iterator();

%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    <table>
        <tr>
            <td>
                <b>Digite rut del paciente</b><br>
                <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                <input value="d" name="txtDV" id="txtDV" type="hidden">
                <input name="user" class="user" id="id_txt_user"  type="text" size="20" maxlength="12" autocomplete="off" onkeyup="formateaRut(this.value)" value=""   >
            </td>
            <td>
                <br> <input class="botonMonitor" type="button" value="Buscar"  name="enviarAjax" >
            </td>
        </tr>
    </table>
--%>
<jsp:include page="Header.jsp" />
<script>
    $(function() {
        $("#txt_fecha").datepicker();
    });
</script>
<script>


    function selecciona_cama() {
        if ($("#cbo_cama").val() != 0) {
            document.getElementById('form_paciente_receta').submit();
        }

    }

    function tipo_receta() {
        if ($("#cbo_tipo_receta").val() == 2) {
        } else {
        }


    }



    function lista_receta(ingreso) {

        var obj = creaObjetoAjax();
        var id_duo = $("#txt_duo_seleccionado").val();
        if (ingreso == 1) {

            var usuario = $("#txt_usuario").val();
            var tipo = $("#cbo_tipo").val();
            var fecha = $("#txt_fecha").val();
            var medicamento = $("#txt_medicamento").val().replace("'", "");
            ;
            var cantidad = $("#txt_cantidad").val();
            var paciente = $("#txt_paciente").val();
            var medida = $("#cbo_medida").val();

            var frecuencia = $("#txt_frecuencia").val();
            var duracion = $("#txt_duracion").val();
            var indicacion = $("#txt_indicacion").val();
            var indicacion_no = $("#txt_indicacion_no").val();
            var duo = $("#txt_duo_seleccionado").val();


            misdatos = "ingresa=" + ingreso + "&txt_usuario=" + usuario + "&cbo_tipo=" + tipo + "&txt_fecha=" + fecha + "&txt_medicamento=" + medicamento + "&txt_cantidad=" + cantidad + "&txt_paciente=" + paciente +
                    "&cbo_medida=" + medida + "&txt_frecuencia=" + frecuencia + "&txt_duracion=" + duracion + "&txt_indicacion=" + indicacion +
                    "&txt_indicacion_no=" + indicacion_no + "&txt_duo_seleccionado=" + id_duo;


        } else {
            misdatos = "ingresa=0" + "&txt_duo_seleccionado=" + id_duo;
        }
        alert(misdatos);

        //Preparar el envio  con Open
        obj.open("POST", "ingresa_receta", true);
        //Enviar cabeceras para que acepte POST:
        obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        obj.setRequestHeader("Content-length", misdatos.length);
        obj.setRequestHeader("Connection", "close");
        obj.send(misdatos); //pasar datos como parámetro

        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando_receta').innerHTML = '';
                document.getElementById('div_receta').innerHTML = obj.responseText;
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando_receta').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById('div_receta').innerHTML = '' + obj.statusText; // estado 12-02-2013

                /*  $("#txt_contacto_nombre").val("");
                 $("#cbo_contacto_parentesco").val("");
                 $("#txt_contacto_fono1").val("");
                 $("#txt_contacto_fono2").val("");
                 $("#txt_contacto_direccion").val("");
                 $("#cbo_contacto_comuna").val("");
                 */
            } else {
                //procesando...
                document.getElementById('div_receta').innerHTML = '&nbsp;&nbsp; <img src="../Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
    }

</script>

<legend>Registro de Receta</legend>

<fieldset><legend>Datos del Paciente</legend>

    <div id="nav">
        <form name="form_paciente_receta" id="form_paciente_receta" action="receta.jsp" method="POST"  >
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

        <input type="hidden" name="txt_duo_seleccionado" id="txt_duo_seleccionado" value="<%=duo.getId_duo()%>" />
        <input type="hidden" name="txt_paciente" id="txt_paciente" value="<%=duo.getRut_paciente()%>" />
        <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=obtiene_usuario%>" />

        <script> lista_receta(0);</script>


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
        </table>
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
    <legend>Datos de la receta</legend>
    <table border='0' cellpadding="5px"  >
        <tr>
            <td colspan="1" >
                <b>Tipo</b><br>
                <select name="cbo_tipo" id="cbo_tipo" >
                    <option value="1" >Receta Hospitalizado</option>
                    <option value="2" >Receta Alta</option>
                </select>
            </td>
            <td><b>Fecha</b><br>
                <input type="text" name="txt_fecha" id="txt_fecha" value="" />
            </td>
            <td rowspan="5" >
                <input class="btn btn-primary" type="submit" value="Grabar receta" name="btn_grabar_receta" onclick="lista_receta(1)"  />
            </td>
        </tr>

        <tr>
            <td>
                <b>Medicamento de Lista</b><br>
                <select name="combobox" id="combobox"   >
                    <option value="-1"></option>
                    <% //
                        while (it_insumo.hasNext()) {
                            cInsumo ins = (cInsumo) it_insumo.next();
                            out.write("<option value='" + ins.getId_insumo() + "' >" + ins.getInsumo_desc() + "</option>");
                        }
                    %>
                </select>
                <input type="hidden" name="txt_medicamento" id="txt_medicamento" value="" />  
            <td><b>Cantidad</b><br>
                <input type="text" name="txt_cantidad" id="txt_cantidad" value="" />
                &nbsp;
                <select name="cbo_medida" id="cbo_medida" >
                    <%
                        while (it_unidad_medida.hasNext()) {
                            cUnidadMedida uni = (cUnidadMedida) it_unidad_medida.next();
                            out.write("<option value='" + uni.getId_unidad_medida() + "'  >" + uni.getDescripcion() + "</option>");
                        }
                    %>
                </select>
            </td>

        </tr>
        <tr>
            <td><b>Frecuencia</b><br>
                <input type="text" name="txt_frecuencia" id="txt_frecuencia" value="" />
                Horas
            </td>

            <td id="td_duracion" >
                <b>Duración</b><br>
                <input type="text" name="txt_duracion" id="txt_duracion" value="" />
                Días
            </td>
        </tr>
        <tr>
            <td colspan="2" ><b>Indicación</b>&nbsp;&nbsp;(Especifique indicaciones, duración tratamiento u otras) <br>
                <input type="text" name="txt_indicacion" id="txt_indicacion" style=" width: 90% "  />

            </td> 
        </tr>


        <tr>
            <td colspan="2" >
                <b>Indicaciones no medicamentosas</b> <br>
                <input type="text" name="txt_indicacion_no" id="txt_indicacion_no" style=" width: 90% "  />
            </td>

        </tr>
    </table>

</fieldset>
<fieldset>
    <legend>Registros asociados</legend>
    <div id="div_cargando_receta" > </div>
    <div id="div_receta" > </div>
</fieldset>


<% } %>








<style>
    .custom-combobox {
        position: relative;
        display: inline-block;
    }
    .custom-combobox-toggle {
        position: absolute;
        top: 0;
        bottom: 0;
        margin-left: -1px;
        padding: 0;
    }
</style>




<style>
    .custom-combobox {
        position: relative;
        display: inline-block;

    }

    .custom-combobox-toggle {
        position: absolute;
        top: 0;
        bottom: 0;
        margin-left: -1px;
        padding: 0;
    }

</style>
<script>


    (function($) {
        $.widget("custom.combobox", {
            _create: function() {
                this.wrapper = $("<span>")
                        .addClass("custom-combobox")
                        .insertAfter(this.element);
                this.element.hide();
                this._createAutocomplete();

            },
            _createAutocomplete: function() {
                var selected = this.element.children(":selected"),
                        value = selected.val() ? selected.text() : "";
                this.input = $("<input  style='width: 700px' onfocus='this.select();'  >")
                        .appendTo(this.wrapper)
                        .val(value)
                        .attr("title", "")
                        .addClass("custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
                        .autocomplete({
                            delay: 0,
                            minLength: 3,
                            source: $.proxy(this, "_source")
                        })
                        .tooltip({
                            tooltipClass: "ui-state-highlight"
                        });

                this._on(this.input, {
                    autocompleteselect: function(event, ui) {
                        ui.item.option.selected = true;
                        this._trigger("select", event, {
                            item: ui.item.option
                        });
                    },
                    autocompletechange: "_removeIfInvalid"
                });
            },
            _source: function(request, response) {
                var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
                response(this.element.children("option").map(function() {
                    var text = $(this).text();
                    if (this.value && (!request.term || matcher.test(text)))
                        return {
                            label: text,
                            value: text,
                            option: this
                        };
                }));
            },
            _removeIfInvalid: function(event, ui) {
// Selected an item, nothing to do
                if (ui.item) {
                    return;
                }
// Search for a match (case-insensitive)
                var value = this.input.val(),
                        valueLowerCase = value.toLowerCase(),
                        valid = false;
                $("#txt_medicamento").val(value);
                $("#combobox").val(-1);
                this.element.children("option").each(function() {
                    if ($(this).text().toLowerCase() === valueLowerCase) {
                        this.selected = valid = true;

                        return false;
                    }
                });
// Found a match, nothing to do
                if (valid) {

                    return;
                }
// Remove invalid value

            },
            _destroy: function() {
                this.wrapper.remove();
                this.element.show();

            }
        });
    })(jQuery);
    $(function() {
        $("#combobox").combobox();

        $("#toggle").click(function() {
            $("#combobox").toggle();
        });

    });


</script>

<jsp:include page="Footer.jsp" />

<%
    }
%>