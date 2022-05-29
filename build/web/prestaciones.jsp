<%-- 
    Document   : receta
    Created on : 01-sep-2015, 17:42:05
    Author     : Informatica
--%>

<%@page import="CapaDato.cPrestacion"%>
<%@page import="CapaDato.cDiagnostico"%>
<%@page import="CapaDato.cDato"%>
<%@page import="java.util.Vector"%>
<%@page import="CapaDato.cReceta"%>
<%@page import="java.text.ParseException"%>
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
<meta http-equiv=?Content-Type? content=?text/html; charset=UTF-8? />
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

        String titulo = " style=' background-color: #f7903b ; color: white '  '  ";
        String datos = " style=' background: #FFFF; color: black; '  ";

        int obtiene_perfil = 0;

        try {
            obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
        } catch (NumberFormatException ex) {
            obtiene_perfil = -1;
        }

        ArrayList lista_prestacion = neg.lista_prestaciones(13);
        Iterator it_prestacion = lista_prestacion.iterator();

%>

<script src="<%=neg.getLocal()%>js/jquery/jquery.js"></script>
<jsp:include page="Header.jsp" />


<style>
    #format { margin-top: 2em; }
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

    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even){background-color: #f2f2f2}

    th {
        background-color: #f7903b;
        color: white;
    }

</style>

<form name="form_pdf_receta" id="form_pdf_receta" action="PDF_receta" method="POST" target="a_blank" >
    <input type="hidden" name="txt_duo" id="txt_duo_pdf" value="0" />
    <input type="hidden" name="txt_receta" id="txt_receta_pdf" value="0" />
</form>
<legend>Registro Prestaciones o Actividades</legend>
<fieldset><legend>Datos del Paciente</legend>

    <div id="nav">
        <form name="form_paciente_receta" id="form_paciente_receta" action="prestaciones.jsp" method="POST">
            <h2>Seleccione paciente</h2>
            <select name="txt_duo" id="txt_duo" onchange="selecciona_cama()" style=" width: 180px ">
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
                Vector<cDato> via_medica = neg.searchViaAdmision();
                cReceta lastRecipe = neg.searchLastRecipe(obtiene_duo);
        %>


        <input type="hidden" name="txt_receta" id="txt_receta" value="0" />

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

                    <td <%=titulo%> >Id Registro</td>
                    <td <%=datos%> ><% out.write("" + duo.getId_duo());%></td>
                </tr>
                <tr>

                    <td <%=titulo%>>Días Hospitalizado</td>
                    <td <%=datos%>>  <%out.write("" + duo.getDias_cama());%>
                    </td>

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
<form id="indica" name="indica" method="get" action="ingresa_receta" onsubmit="return validar()"  >
    <input  type="hidden" name="txt_duo_seleccionado" id="txt_duo_seleccionado" value="<%=duo.getId_duo()%>" />
    <input type="hidden" name="txt_paciente" id="txt_paciente" value="<%=duo.getRut_paciente()%>" />
    <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=obtiene_usuario%>" />
    <fieldset id="fieldset_receta" >
        <legend>Prestaciones</legend>
        <fieldset>
            <legend>Prestacion realizada</legend>

            &nbsp;
            <select name="cbo_examenes" id="cbo_examenes" style=" width: 800px" >
                <option value="-1"></option>
                <%
                    while (it_prestacion.hasNext()) {
                        cPrestacion prestacion = (cPrestacion) it_prestacion.next();
                        out.write("<option value='" + prestacion.getId()+ "'  >" + prestacion.getCodigo()+" "+ prestacion.getDescripcion() +"</option>");
                    }
                %>
            </select>
            <input class="btn btn-primary" type="button" value="Agregar Prestacion " name="btn_grabar_receta" onclick="javascript: addRow2('myTablexa');"  />

        </fieldset>
        <table id="myTablexa" name="myTablexa" style=" width: 100% " class=" table table-striped" cellspacing="0" border="1">

            <tbody>

            </tbody>

        </table>



        <fieldset>

            <input class="btn btn-primary" type="submit" value="Registrar">

        </fieldset>


</form>

<fieldset style=" height:800" ><legend>Prestaciones</legend>     

    <%
        ArrayList lista_indicaciones = neg.bucarindicacionesporduo(obtiene_duo);
        String guarda_fecha = "";
        Iterator it_ses = lista_indicaciones.iterator();

        if (lista_indicaciones.isEmpty()) {

    %>
    <table>
        <tr>
            <td>
                No se encontraron registros  
            </td>
        </tr>
    </table>



    <% } else {
        /**/
    %>

    <% while (it_ses.hasNext()) {
            cReceta vis = (cReceta) it_ses.next();
    %>
    <table style='width: 100%;  border:solid 0.05px #f7903b ' >



    </table>

</fieldset>


<% }
        }
    }%>


<script>

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

    function selecciona_cama() {
        if ($("#cbo_cama").val() != 0) {
            document.getElementById('form_paciente_receta').submit();
        }

    }



    function ver_pdf(duo, receta) {
        //alert('generando');
        $("#txt_duo_pdf").val(duo);
        $("#txt_receta_pdf").val(receta);
        document.getElementById('form_pdf_receta').submit();
    }


    function borrarFila(t) {
        var td = t.parentNode;
        var tr = td.parentNode;
        var table = tr.parentNode;
        table.removeChild(tr);
    }


    function addRow2(id) {

        var combo3 = document.getElementById("cbo_examenes");
        var selected3 = combo3.options[combo3.selectedIndex].text;

        var idmedicamento = $("#cbo_examenes").val();
        if (idmedicamento == -1) {
            alert('Debe seleccionar una prestacion');
            return false;

        } else {
            var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
            var row = document.createElement("TR");
            row.setAttribute('style', ' text-align: center');
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode(selected3));
            row.appendChild(td1);
            var td2 = document.createElement("TD");
            td2.style = "width: 10px";
            var boton = document.createElement('button');
            boton.type = "button";
            boton.setAttribute('class', 'button2');
            boton.innerHTML = "x";


            boton.onclick = function () {
                var td = this.parentNode;
                var tr = td.parentNode;
                var table = tr.parentNode;
                table.removeChild(tr);
                return false;
            };
            td2.appendChild(boton);
            row.appendChild(td2);
            /*los id*/

            var td = document.createElement("TD");
            td.style = "width: 1px";
            var div = document.createElement("input");
            div.id = "examenen" + idmedicamento;
            div.name = "examenen" + idmedicamento;
            div.value = idmedicamento;
            div.type = "hidden";
            td.appendChild(div);
            row.appendChild(td);
            tbody.appendChild(row);
            document.getElementById("cbo_examenes").value = -1;

        }

    }

    function limpiar() {
        document.getElementById("txt_cantidad").value = "";
        document.getElementById("txt_frecuencia").value = "";
        document.getElementById("txt_duracion").value = "";
        document.getElementById("txt_indicacion").value = "";
        document.getElementById("cbo_medida").value = 1;
        document.getElementById("combobox").value = -1;

    }


    (function ($) {
        $.widget("custom.combobox", {
            _create: function () {
                this.wrapper = $("<span>")
                        .addClass("custom-combobox")
                        .insertAfter(this.element);
                this.element.hide();
                this._createAutocomplete();
            },
            _createAutocomplete: function () {
                var selected = this.element.children(":selected"),
                        value = selected.val() ? selected.text() : "";
                this.input = $("<input  style='width: 600px' onfocus='this.select();'  >")
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
                    autocompleteselect: function (event, ui) {
                        ui.item.option.selected = true;
                        this._trigger("select", event, {
                            item: ui.item.option
                        });
                    },
                    autocompletechange: "_removeIfInvalid"
                });
            },
            _source: function (request, response) {
                var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
                response(this.element.children("option").map(function () {
                    var text = $(this).text();
                    if (this.value && (!request.term || matcher.test(text)))
                        return {
                            label: text,
                            value: text,
                            option: this
                        };
                }));
            },
            _removeIfInvalid: function (event, ui) {
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
                this.element.children("option").each(function () {
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
            _destroy: function () {
                this.wrapper.remove();
                this.element.show();
            }
        });
    })(jQuery);
    $(function () {
        $("#combobox").combobox();
        $("#toggle").click(function () {
            $("#combobox").toggle();
        });
    });


</script>

<jsp:include page="Footer.jsp" />

<%
    }
%>
