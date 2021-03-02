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

        String titulo = " style=' background-color: #4169E1 ; color: white '  '  ";
        String datos = " style=' background: #FFFF; color: black; '  ";

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
    function soloNumeros(evt){
    //asignamos el valor de la tecla a keynum
    if(window.event){// IE
        keynum = evt.keyCode;
    }else{
        keynum = evt.which;
    }
    //
    if((keynum>45 && keynum<58) || keynum == 8 ||keynum == 13||keynum == 9 ){
        //numeros || delete || enter || tab
        return true;
    }else{
        return false;
    }
}

    function soloNumerosDecimales(evt) {
        //asignamos el valor de la tecla a keynum
        if (window.event) {// IE
            keynum = evt.keyCode;
        } else {
            keynum = evt.which;
        }
        //
        if ((keynum > 45 && keynum < 58) || keynum == 8 || keynum == 13 || keynum == 9 || keynum == 0) {
            //numeros || delete || enter || tab

            return true;
        } else {
            return false;
        }
    }
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
        var usuario = $("#txt_usuario").val();
        if (ingreso == 1) {


            var tipo = $("#cbo_tipo").val();
            var fecha = $("#txt_fecha").val();
            if ($("#combobox").val() != -1) {
                $("#txt_medicamento").val($("#combobox").val());
            }

            var medicamento = $("#txt_medicamento").val().replace("'", "").toUpperCase();

            var cantidad = $("#txt_cantidad").val();
            var paciente = $("#txt_paciente").val();
            var medida = $("#cbo_medida").val();

            var frecuencia = $("#txt_frecuencia").val();
            var duracion = $("#txt_duracion").val();
            var indicacion = $("#txt_indicacion").val();
            var indicacion_no = $("#txt_indicacion_no").val();
            var duo = $("#txt_duo_seleccionado").val();
            var receta = $("#txt_receta").val();

            misdatos = "ingresa=" + ingreso + "&txt_usuario=" + usuario + "&cbo_tipo=" + tipo + "&txt_fecha=" + fecha + "&txt_medicamento=" + medicamento + "&txt_cantidad=" + cantidad + "&txt_paciente=" + paciente +
                    "&cbo_medida=" + medida + "&txt_frecuencia=" + frecuencia + "&txt_duracion=" + duracion + "&txt_indicacion=" + indicacion +
                    "&txt_indicacion_no=" + indicacion_no + "&txt_duo_seleccionado=" + id_duo + "&txt_receta=" + receta;

            if (medicamento.length == 0) {
                alert('Debe ingresar el medicamento');
                return false;
            }
            if (cantidad.length == 0) {
                $("#txt_cantidad").focus();
                alert('Debe ingresar la cantidad');
                return false;
            }

            if (isNaN(cantidad)) {
                $("#txt_cantidad").focus();
                alert('Debe ingresar la cantidad válida');
                return false;
            }

            if (frecuencia.length == 0) {
                $("#txt_frecuencia").focus();
                alert('Debe ingresar la frecuencia');
                return false;
            }
            if (duracion.length == 0) {
                $("#txt_duracion").focus();
                alert('Debe ingresar la duración');
                return false;
            }
            if (receta == 0) {
                alert('No hay receta seleccionada, no se puede ingresar el registro');
                return false;
            }



        } else if (ingreso == 11) {

            var tipo = $("#cbo_tipo").val();
            var fecha = $("#txt_fecha").val();

            var dia = $("#txt_dia").val();
            var diagnostico = $("#txt_diagnostico").val();
            var reposo = $("#txt_reposo").val();
            var regimen = $("#txt_regimen").val();
            var kinesiologia = $("#txt_kinesiologia").val();
            var aislamiento = $("#txt_aislamiento").val();
            var contencion = $("#txt_contencion").val();
            var dispositivo = $("#txt_dispositivo").val();
            var hgt = $("#txt_hgt").val();


            if (fecha.length != 10) {
                $("#txt_fecha").focus();
                alert('Debe ingresar la fecha [dd/mm/yyyy]');
                return false;
            }
                 if (dia.length == 0) {
                 $("#txt_dia").focus();
                alert('Debe ingresar dias hospitalización');
                return false;
            }
              if (isNaN(dia)) {
                $("#txt_dia").focus();
                alert('Debe ingresar dias hospitalización como número entero');
                return false;
            }

            if (confirm("CONFIRMACION ! Desea ingresar este registro ? \n \n ")) {
                // document.getElementById('form_documento').submit();
            } else {
                //alert("Haz cancelado el ingreso");
                return false;
            }
            misdatos = "ingresa=11" + "&txt_duo_seleccionado=" + id_duo + "&cbo_tipo=" + tipo + "&txt_fecha=" + fecha + "&txt_usuario=" + usuario +
                    "&dia=" + dia + "&diagnostico=" + diagnostico + "&reposo=" + reposo + "&regimen=" + regimen + "&kinesiologia=" + kinesiologia +
                    "&aislamiento=" + aislamiento + "&contencion=" + contencion + "&dispositivo=" + dispositivo + "&hgt=" + hgt;
        } else {
            misdatos = "ingresa=0" + "&txt_duo_seleccionado=" + id_duo;
        }
        //  alert(misdatos);
        //return false;
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

                if (ingreso == 11) {

                    $("#txt_receta").val($("#txt_receta_obtenida").val());


                }
                if ($("#txt_receta").val() != 0) {
                    document.getElementById('div_ingresando_receta').innerHTML = ' <h3>Receta N° ' + $("#txt_receta").val() + ' con fecha ' + $("#txt_fecha").val() + '</h3>';
                    $("#fieldset_receta").hide();
                    $("#fieldset_receta_detalle").show();
                } else {
                    $("#fieldset_receta").show();
                    $("#fieldset_receta_detalle").hide();
                }
                // $("#txt_contacto_nombre").val("");
                $("#txt_cantidad").val("");
                $("#txt_frecuencia").val("");
                $("#txt_duracion").val("");
                $("#txt_indicacion").val("");
                $("#txt_indicacion_no").val("");

            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('div_cargando_receta').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById('div_receta').innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('div_receta').innerHTML = '&nbsp;&nbsp; <img src="../Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
    }

    function ver_pdf(duo, receta) {
        //alert('generando');
        $("#txt_duo_pdf").val(duo);
        $("#txt_receta_pdf").val(receta);
        document.getElementById('form_pdf_receta').submit();
    }

</script>
<form name="form_pdf_receta" id="form_pdf_receta" action="PDF_receta" method="POST" target="a_blank" >
    <input type="hidden" name="txt_duo" id="txt_duo_pdf" value="0" />
    <input type="hidden" name="txt_receta" id="txt_receta_pdf" value="0" />
</form>
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
                ArrayList lista_medicamento = neg.lista_medicamento();
                Iterator it_medicamento = lista_medicamento.iterator();
        %>

        <input  type="hidden" name="txt_duo_seleccionado" id="txt_duo_seleccionado" value="<%=duo.getId_duo()%>" />
        <input type="hidden" name="txt_paciente" id="txt_paciente" value="<%=duo.getRut_paciente()%>" />
        <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=obtiene_usuario%>" />
        <input type="hidden" name="txt_receta" id="txt_receta" value="0" />

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

<fieldset id="fieldset_receta" >
    <legend>Receta</legend>
    <table border='0' cellpadding="5px"   >
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
            <td><b>Días Hospitalizado</b><br>
                <input type="text" name="txt_dia" id="txt_dia" value="<%=duo.getDias_cama()%>" onkeypress="return soloNumeros(event)" />
            </td>
            <td>
                <input class="btn btn-primary" type="submit" value="Nueva Receta" name="btn_grabar_receta" onclick="lista_receta(11)"  />
            </td>
        </tr>

        <tr>
            <td><b>Diagnósticos</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_diagnostico" id="txt_diagnostico" value="" style=" width: 100% " />
            </td>
        </tr>
        <tr>
            <td><b>Régimen</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_regimen" id="txt_regimen" value="" style=" width: 100% " /></td>
        </tr>
        <tr>
            <td><b>Reposo</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_reposo" id="txt_reposo" value="" style=" width: 100% " />
            </td>
        </tr>
        <tr>
            <td><b>Kinesiología</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_kinesiologia" id="txt_kinesiologia" value="" style=" width: 100% " />
            </td>
        </tr>

        <tr>
            <td><b>Aislamiento</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_aislamiento" id="txt_aislamiento" value="" style=" width: 100% " />
            </td>
        </tr>
        <tr>
            <td><b>Contención</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_contencion" id="txt_contencion" value="" style=" width: 100% " />
            </td>
        </tr>
        <tr>
            <td><b>Dispositivos</b></td>
            <td colspan="2" >
                <input type="text" name="txt_dispositivo" id="txt_dispositivo" value="" style=" width: 100% " />
            </td>
        </tr>
        <tr>
            <td><b>HGT</b></td>
            <td colspan="2" > 
                <input type="text" name="txt_hgt" id="txt_hgt" value="" style=" width: 100% " /></td>
        </tr>



    </table>
</fieldset>             


<fieldset id="fieldset_receta_detalle" >
    <legend>Datos de la receta</legend>
    <div id="div_ingresando_receta" style=" border:  #000; border-style:  ridge " ></div>
    <table border='0' cellpadding="5px"   >
        <tr>
            <td>
                <b>Medicamento de Lista</b><br>
                <select name="combobox" id="combobox"   >
                    <option value="-1"></option>
                    <% //
                        while (it_medicamento.hasNext()) {
                            cInsumo ins = (cInsumo) it_medicamento.next();

                            out.write("<option value='" + ins.getInsumo_desc() + "' >" + ins.getInsumo_desc() + "</option>");
                        }
                    %>
                </select>
                <input type="hidden" name="txt_medicamento" id="txt_medicamento" value="" />  
            <td><b>Cantidad</b><br>
                <input type="text" name="txt_cantidad" id="txt_cantidad" value="" onkeypress="return soloNumerosDecimales(event)" />
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
            <td rowspan="4" >
                <input class="btn btn-primary" type="submit" value="Registrar medicamento" name="btn_grabar_receta" onclick="lista_receta(1)"  />
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
