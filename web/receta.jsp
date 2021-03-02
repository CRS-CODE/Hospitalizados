<%-- 
    Document   : receta
    Created on : 01-sep-2015, 17:42:05
    Author     : Informatica
--%>

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
        ArrayList diagnosticos = neg.lista_diagnostico(obtiene_duo, " 1,2 ");
        Iterator it_dia = diagnosticos.iterator();
         String diagnostico = "";
            while (it_dia.hasNext()) {
                cDiagnostico dia = (cDiagnostico) it_dia.next();

                diagnostico += dia.getDescripcion_diagnostico() + " \n";

            }
            if (diagnostico.length() > 0) {
                diagnostico = diagnostico.substring(0, diagnostico.length() - 2);
            }
        ArrayList lista_unidad_medida = neg.lista_unidad_medida();
        Iterator it_unidad_medida = lista_unidad_medida.iterator();

        ArrayList lista_examenes = neg.lista_examenes();
        Iterator it_examenes = lista_examenes.iterator();

        int obtiene_perfil = 0;

        try {
            obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
        } catch (NumberFormatException ex) {
            obtiene_perfil = -1;
        }

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
        background-color: #337ab7;
        color: white;
    }

</style>

<form name="form_pdf_receta" id="form_pdf_receta" action="PDF_receta" method="POST" target="a_blank" >
    <input type="hidden" name="txt_duo" id="txt_duo_pdf" value="0" />
    <input type="hidden" name="txt_receta" id="txt_receta_pdf" value="0" />
</form>
<legend>Registro de Indicaciones</legend>
<fieldset><legend>Datos del Paciente</legend>

    <div id="nav">
        <form name="form_paciente_receta" id="form_paciente_receta" action="receta.jsp" method="POST">
            <h2>-Seleccione paciente-</h2>
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
        <legend>Indicaciones</legend>
        <fieldset>
            <legend>Diagnostico:</legend>

            <textarea rows="5" name="txt_diagnostico" id="txt_diagnostico"   style=" width: 100% " ><%=diagnostico%></textarea>
        </fieldset>
        
        <fieldset>
            <legend>Régimen:</legend>
            <textarea rows="5" name="txt_regimen" id="txt_regimen"   style=" width: 100% " ><%=lastRecipe.getRegimen() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Reposo:</legend>

            <textarea rows="5" name="txt_reposo" id="txt_reposo"   style=" width: 100% " ><%=lastRecipe.getReposo() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Control de signos vitales:</legend>

            <textarea rows="2" name="txt_control_signos" id="txt_control_signos"   style=" width: 100% " ><%=lastRecipe.getControl_signos() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Aislamiento:</legend>

            <textarea rows="2" name="txt_aislamiento" id="txt_aislamiento"   style=" width: 100% " ><%=lastRecipe.getAislamiento() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Alergias:</legend>

            <textarea rows="2" name="txt_alergias" id="txt_alergias"   style=" width: 100% " ><%=lastRecipe.getAlergias() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Contención:</legend>

            <textarea rows="2" name="txt_contencion" id="txt_contencion"   style=" width: 100% " ><%=lastRecipe.getContencion() %></textarea>
        </fieldset>
        <fieldset>
            <legend>Imágenes:</legend>

            <textarea rows="2" name="txt_imagenes" id="txt_imagenes"   style=" width: 100% " ><%=lastRecipe.getImagenes() %></textarea>
        </fieldset>
         <fieldset>
            <legend>Otros:</legend>

            <textarea rows="2" name="txt_otros" id="txt_otros"   style=" width: 100% " ><%=lastRecipe.getOtros() %></textarea>
        </fieldset>
        <fieldset>
            <table>
                <tr>
                    <td>
                           <legend>Indicaciones Enfermeria:</legend>
                         <textarea rows="2" name="txt_indicaciones_enfermeria" id="txt_indicaciones_enfermeria"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_enfermeria() %></textarea>
                    </td>
                    <td>
                           <legend>Indicaciones nutricionista:</legend>
                         <textarea rows="2" name="txt_indicaciones_nutricionista" id="txt_indicaciones_nutricionista"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_nutricionista() %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                           <legend>Indicaciones kinesiólogo:</legend>
                         <textarea rows="2" name="txt_indicaciones_kinesiologo" id="txt_indicaciones_kinesiologo"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_kinesiologo()%></textarea>
                    </td>
                    <td>
                           <legend>Indicaciones otros:</legend>
                         <textarea rows="2" name="txt_indicaciones_otros" id="txt_indicaciones_otros"   style=" width: 100% " ><%=lastRecipe.getIndicaciones_otros() %></textarea>
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>Medicamentos </legend>
            <table border='0' cellpadding="5px"   >
                <tr>
                    <td>
                        <b>Lista de Medicamentos</b><br>
                        <select name="combobox" id="combobox"   >
                            <option value="-1"></option>
                            <% //
                                while (it_medicamento.hasNext()) {
                                    cInsumo ins = (cInsumo) it_medicamento.next();

                                    out.write("<option value='" + ins.getId_insumo() + "' >" + ins.getInsumo_desc() + "</option>");
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
                        <input class="btn btn-primary" type="button" value="Agregar medicamento" name="btn_grabar_receta" onclick="javascript: addRow1('myTable');"  />
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
                    <td><b>Via de Administracion</b><br>
                        &nbsp;
                        <select name="cbo_via_administracion" id="cbo_via_administracion" >
                            <%
                                for( cDato via : via_medica) {
                                    out.write("<option value='" + via.getId()+ "'  >" + via.getDescription() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td colspan="2" ><b>Indicación Especial </b> <br>
                        <textarea rows="3" name="txt_indicacion" id="txt_indicacion" style=" width: 90% "  ></textarea>

                    </td> 
                </tr>
               

            </table>
        </fieldset>

        <fieldset>

            <table id="myTable" name="myTable" style=" width: 100% " class=" table table-striped" cellspacing="0" border="1">

                <tbody>

                </tbody>

            </table>

        </fieldset>

        <fieldset>
            <legend>Examenes a realizar</legend>

            &nbsp;
            <select name="cbo_examenes" id="cbo_examenes" >
                <option value="-1"></option>
                <%
                    while (it_examenes.hasNext()) {
                        cUnidadMedida uni = (cUnidadMedida) it_examenes.next();
                        out.write("<option value='" + uni.getId_unidad_medida() + "'  >" + uni.getDescripcion() + "</option>");
                    }
                %>
            </select>
            <input class="btn btn-primary" type="button" value="Agregar Examen " name="btn_grabar_receta" onclick="javascript: addRow2('myTablexa');"  />

        </fieldset>
        <table id="myTablexa" name="myTablexa" style=" width: 100% " class=" table table-striped" cellspacing="0" border="1">

            <tbody>

            </tbody>

        </table>

        <fieldset>
            <legend>Otras Indicaciones</legend>
            <textarea rows="5" name="txt_otrasindicaciones" id="txt_otrasindicaciones"   style=" width: 100% " ></textarea>
        </fieldset>


    </fieldset>  

    <fieldset>

        <input class="btn btn-primary" type="submit" value="Ingresar Indicaciones">

    </fieldset>


</form>

<fieldset style=" height:800" ><legend>Indicaciones</legend>     

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
    <table style='width: 100%;  border:solid 0.05px #337ab7 ' >

        <%
            if (!vis.getFecha().equals(guarda_fecha)) {

                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yyyy");

        %>
        <tr>
            <th colspan="4">
                Dia : <%=vis.getFecha()%>  
            </th>
        </tr>
        <%

            }
        %>
        <tr>
            <td style="width: 10%"><%=vis.getHgt()%></td>
            <td style="width: 100%">
                 <fieldset><legend>Diagnostico:</legend>
                    <%=vis.getDiagnostico() %>
                </fieldset> 
                <fieldset><legend>Reposo:</legend>
                    <%=vis.getReposo()%>
                </fieldset> 
                <fieldset><legend>Regimen: </legend>
                    <%=vis.getRegimen()%>
                </fieldset> 
                <fieldset> <legend>Control de signos vitales:</legend>
                <%=vis.getControl_signos() %>
                </fieldset>
                <fieldset> <legend>Aislamiento:</legend>
                <%=vis.getAislamiento() %>
                </fieldset>
                <fieldset><legend>Alergias:</legend>
                 <%=vis.getAlergias() %>
                </fieldset>
                <fieldset><legend>Contención:</legend>
                <%=vis.getContencion() %>
                </fieldset>
                <fieldset><legend>Imágenes:</legend>
                <%=vis.getImagenes() %>    
                </fieldset>
                <fieldset><legend>Otros:</legend>
                </fieldset>
                <fieldset>
                    <table>
                        <tr>
                            <td>
                                   <legend>Indicaciones Enfermeria:</legend> 
                                   <%=vis.getIndicaciones_enfermeria() %>
                            </td>
                            <td>
                                   <legend>Indicaciones nutricionista:</legend>
                                   <%=vis.getIndicaciones_nutricionista() %>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                   <legend>Indicaciones kinesiólogo:</legend>
                                   <%=vis.getIndicaciones_kinesiologo() %>
                            </td>
                            <td>
                                   <legend>Indicaciones otros:</legend>
                                   <%=vis.getIndicaciones_otros() %>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset>
                    <legend> Medicamentos:</legend>
                    <% Vector<String> recetas = neg.buscarRecetadeIndicaciones(vis.getId_duo());
                        String menas = "";
                        boolean tengoreceta = false;
                        for (int i = 0; i < recetas.size(); i++) {
                            menas = menas + recetas.get(i) + ".  \n";
                            tengoreceta = true;
                        }

                    %>
                    <%=menas.toLowerCase()%>
                </fieldset> 
                <fieldset>
                    <legend> Solicitud de Examenes:</legend>
                    <% Vector<String> examenes = neg.buscarSolicitudExamenesdeIndicaciones(vis.getId_duo());
                        String exam = "";
                        boolean tengoexamenes = false;
                        for (int i = 0; i < examenes.size(); i++) {
                            exam = exam + examenes.get(i) + " , \n";
                            tengoexamenes = true;
                        }

                    %>
                    <%=exam.toLowerCase()%>
                </fieldset> 

                <fieldset><legend>Otras Indicaciones: </legend>
                    <%=vis.getIndicacion()%>
                </fieldset> 





            </td>
            <td style="width: 30%"><%=vis.getNombre_usuario()%></td>

            <td>Indicaciones
                <form name="form_formulario<%=vis.getId_duo()%>" id="form_formulario<%=vis.getId_duo()%>" action="PDF_Indicaciones" method="POST"  target="a_blank" >
                    <input type="hidden" name="id_ind" value="<%=vis.getId_duo()%>"   >
                    <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_formulario<%=vis.getId_duo()%>').submit()" />
                </form>
            </td>

            <% if (tengoreceta == true) {%>
            <td>Receta
                <form name="form_receta<%=vis.getId_duo()%>" id="form_receta<%=vis.getId_duo()%>" action="RecetaMedica" method="POST"  target="a_blank" >
                    <input type="hidden" name="id_ind" value="<%=vis.getId_duo()%>"   >
                    <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_receta<%=vis.getId_duo()%>').submit()" />
                </form>
            </td>
            <%}%>

            <% if (tengoexamenes == true) {%>
            <td>Solicitud Examenes
                <form name="form_examen<%=vis.getId_duo()%>" id="form_examen<%=vis.getId_duo()%>" action="SolicitudExamenes" method="POST"  target="a_blank" >
                    <input type="hidden" name="id_ind" value="<%=vis.getId_duo()%>"   >
                    <img src="Imagenes/pdf.png" width="32" height="32" alt="pdf" onclick="document.getElementById('form_examen<%=vis.getId_duo()%>').submit()" />
                </form>
            </td>
            <%}%>

        </tr>
        </tr>

        <%
                    guarda_fecha = vis.getFecha();
                }
                
            } %>




    </table>

</fieldset>


<% }%>


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

    function validar() {
        /*validar usuario*/
        var perfil = <%=obtiene_perfil%>;
        if (perfil == 1 || perfil == 6 || perfil == 8) {

            var txt_reposo = $("#txt_reposo").val();
            var txt_regimen = $("#txt_regimen").val();
            var txt_otrasindicaciones = $("#txt_otrasindicaciones").val();
            if (txt_reposo == "" || txt_regimen == "" ) {
                alert('Debe ingresar el reposo y el regimen');
                return false;
            } else {
                document.getElementById("indica").submit();
            }
        } else {
            alert('No contiene los privilegios necesarios!..');
            return false;
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


    function addRow1(id) {

        if ($("#combobox").val() != -1) {
            $("#txt_medicamento").val($("#combobox").val());
        }

        var medicamento = $("#txt_medicamento").val().replace("'", "").toUpperCase();

        var combo3 = document.getElementById("cbo_medida");
        var selected3 = combo3.options[combo3.selectedIndex].text;
        var combo4 = document.getElementById("combobox");
        var selected4 = combo4.options[combo4.selectedIndex].text;
        var cantidad = $("#txt_cantidad").val();
        var frecuencia = $("#txt_frecuencia").val();
        var duracion = $("#txt_duracion").val();
        var indicacion = $("#txt_indicacion").val();
        var via =document.getElementById("cbo_via_administracion");
        var via_medicina= via.options[via.selectedIndex].text;
        var medida = $("#cbo_medida").val();
        var id_via = $("#cbo_via_administracion").val();
        var idmedicamento = $("#combobox").val();
        if (medicamento.length == 0 || idmedicamento == -1) {
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
        } else {
            var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
            var row = document.createElement("TR");
            row.setAttribute('style', ' text-align: center');
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode( selected4 + "  "+cantidad + "  " + selected3 + " cada  " + frecuencia + " horas durante " + duracion + " dias , via "+via_medicina+" \n" + indicacion));
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
            div.id = "medicamento" + idmedicamento;
            div.name = "medicamento" + idmedicamento;
            div.value = idmedicamento;
            div.type = "hidden";
            td.appendChild(div);
            var div2 = document.createElement("input");
            div2.id = "cantidad" + idmedicamento;
            div2.name = "cantidad" + idmedicamento;
            div2.value = cantidad;
            div2.type = "hidden";
            td.appendChild(div2);
            var div3 = document.createElement("input");
            div3.id = "medida" + idmedicamento;
            div3.name = "medida" + idmedicamento;
            div3.value = medida;
            div3.type = "hidden";
            td.appendChild(div3);
            var div4 = document.createElement("input");
            div4.id = "frecuencia" + idmedicamento;
            div4.name = "frecuencia" + idmedicamento;
            div4.value = frecuencia;
            div4.type = "hidden";
            td.appendChild(div4);
            var div5 = document.createElement("input");
            div5.id = "duracion" + idmedicamento;
            div5.name = "duracion" + idmedicamento;
            div5.value = duracion;
            div5.type = "hidden";
            td.appendChild(div5);
            var div6 = document.createElement("input");
            div6.id = "indicaciones" + idmedicamento;
            div6.name = "indicaciones" + idmedicamento;
            div6.value = indicacion;
            div6.type = "hidden";
            td.appendChild(div6);
             var div7 = document.createElement("input");
            div7.id = "via" + idmedicamento;
            div7.name = "via" + idmedicamento;
            div7.value = id_via;
            div7.type = "hidden";
            td.appendChild(div7);
            row.appendChild(td);
            tbody.appendChild(row);
            limpiar();
        }

    }


    function addRow2(id) {

        var combo3 = document.getElementById("cbo_examenes");
        var selected3 = combo3.options[combo3.selectedIndex].text;

        var idmedicamento = $("#cbo_examenes").val();
        if (idmedicamento == -1) {
            alert('Debe ingresar el examen');
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
