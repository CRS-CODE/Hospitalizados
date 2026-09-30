<%-- 
    Document   : admision_ugu_carga
    Created on : 10-may-2012, 12:11:06
    Author     : EseGamboa
--%>
<%@page import="CapaDato.prevision"%>
<%@page import="CapaDato.cNacion"%>
<%@page import="CapaDato.cEpicrisis"%>
<%@page import="CapaDato.cPaciente"%>

<%@page import="CapaDato.cPueblo"%>
<%@page import="CapaDato.cCama"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="CapaDato.cComuna"%>
<%@page import="CapaDato.cCategoriaOcupacional"%>
<%@page import="CapaDato.cNivelInstruccion"%>
<%@page import="CapaDato.cLeyPrevisional"%>
<%@page import="CapaDato.cPuebloAfrodescendiente"%>
<%@page import="CapaDato.cIdentidadGenero"%>
<%@page import="CapaDato.cTipoVia"%>
<%@page import="CapaDato.cOcupacionDeclarada"%>
<%@page import="CapaDato.cPuebloOriginarioGate"%>
<%@page import="CapaDato.cProcedenciaPaciente"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
    NegocioQ neg = new NegocioQ();
    String obtiene_rut = request.getParameter("user");

    ArrayList lista_duo = neg.lista_documentos_paciente(obtiene_rut);
    Iterator itt = lista_duo.iterator();
    boolean sw_esta = false;
    while (itt.hasNext()) {
        cEpicrisis epi = (cEpicrisis) itt.next();
        if (epi.getEstado_duo() == 1 || epi.getEstado_duo() == 2 || epi.getEstado_duo() == 3 || epi.getEstado_duo() == 21) {
            sw_esta = true;
        }
    }

    if (sw_esta) {
        out.write("<h2>ESTE PACIENTE YA TIENE UN REGISTRO ACTIVO</h2>");
    } else {

        String hora_Registro = neg.obtiene_fecha_hora();
        ArrayList comuna = neg.buscarComuna();
        ArrayList consultorio = neg.lista_consultorio_pertenecia();
        ArrayList derivador = neg.lista_derivador();
        ArrayList cama = neg.lista_cama_desocupada("11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28"); // 1 es el area para camas disponibles normales , puede ser tambien -(1,2)-
        ArrayList pueblo = neg.lista_pueblo();
        ArrayList nacion = neg.lista_nacion();
        ArrayList categoriaOcupacionalLista = neg.lista_categoria_ocupacional();
        ArrayList nivelInstruccionLista = neg.lista_nivel_instruccion();
        ArrayList leyPrevisionalLista = neg.lista_leyes_previsionales();
        ArrayList puebloAfroLista = neg.lista_pueblo_afrodescendiente();
        ArrayList identidadGeneroLista = neg.lista_identidad_genero();
        ArrayList tipoViaLista = neg.lista_tipo_via();
        ArrayList ocupacionDeclaradaLista = neg.lista_ocupacion_declarada();
        ArrayList puebloOriginarioGateLista = neg.lista_pueblo_originario_gate();
        ArrayList procedenciaPacienteLista = neg.lista_procedencia_paciente();

        Iterator it_com = comuna.iterator();
        Iterator it_cons = consultorio.iterator();
        Iterator it_der = derivador.iterator();
        Iterator it_cam = cama.iterator();
        Iterator it_pue = pueblo.iterator();
        Iterator it_nac = nacion.iterator();
        Iterator it_cat_ocu = categoriaOcupacionalLista.iterator();
        Iterator it_niv_ins = nivelInstruccionLista.iterator();
        Iterator it_ley_prev = leyPrevisionalLista.iterator();
        Iterator it_pue_afro = puebloAfroLista.iterator();
        Iterator it_gen = identidadGeneroLista.iterator();
        Iterator it_via = tipoViaLista.iterator();
        Iterator it_ocu_dec = ocupacionDeclaradaLista.iterator();
        Iterator it_gate = puebloOriginarioGateLista.iterator();
        Iterator it_proc = procedenciaPacienteLista.iterator();

        cComuna com = new cComuna();
        cConsultorio cons = new cConsultorio();
        cConsultorio der = new cConsultorio();
        cCama cam = new cCama();
        cPueblo pue = new cPueblo();
        cNacion nac = new cNacion();
        cCategoriaOcupacional cat_ocu = new cCategoriaOcupacional();
        cNivelInstruccion niv_ins = new cNivelInstruccion();
        cLeyPrevisional ley_prev = new cLeyPrevisional();
        cPuebloAfrodescendiente pue_afro = new cPuebloAfrodescendiente();
        cIdentidadGenero gen = new cIdentidadGenero();
        cTipoVia via = new cTipoVia();
        cOcupacionDeclarada ocu_dec = new cOcupacionDeclarada();
        cPuebloOriginarioGate gate = new cPuebloOriginarioGate();
        cProcedenciaPaciente proc = new cProcedenciaPaciente();
        String rut = obtiene_rut;
        String rutsinpunto = request.getParameter("rut");
        String dv = request.getParameter("dv");
        rut = neg.FormatearRUT(rut);
        cPaciente pac = neg.buscarpacienteporrut(rut);
        String a_nombres = "";
        String a_apellidop = "";
        String a_apellidom = "";
        String a_fono1 = "";
        String a_fono2 = "";
        String a_rut = "";
        String a_direccion = "";
        String a_fecha_nacimiento = "";
        int a_sexo = -1;
        int a_comuna = 0;
        int a_pueblo = -1;
        int a_consultorio_pertenencia = -1;
        String a_mail = "";
        int codigoprevicion = -1;
        int t = -1;
        String a_codigo_fonasa = "";
        String a_codigo_fonasa_descripcion = "";
        String a_tramo = "";
        int a_prais = -1;
        int existe = 0;
        String a_nombresocial = "";
        int a_categoria_ocupacional = -1;
        int a_nivel_instruccion = -1;
        int a_ley_previsional = -1;
        int a_pueblo_afrodescendiente = -1;
        int a_identidad_genero = -1;
        int a_tipo_via = -1;
        int a_ocupacion_declarada = -1;
        int a_pueblo_originario_gate = -1;
        String a_pais_origen = "";
        int a_procedencia_servicio = -1;
        String a_establecimiento_procedencia = "";

        //  pac.setRut("");
        if (pac.getRut_paciente().equals("")) {
            existe = 0;
            cPaciente pac_fon = new cPaciente();
            a_nombres = pac_fon.getNombres_paciente() + "";
            a_apellidop = pac_fon.getApellidop_paciente();
            a_apellidom = pac_fon.getApellidom_paciente();
            a_fono1 = pac_fon.getTelefono1();
            a_fono2 = pac_fon.getTelefono2();
            a_rut = pac_fon.getRut_paciente();
            a_rut = obtiene_rut;

            a_fecha_nacimiento = pac_fon.getFecha_nac();
            a_sexo = pac_fon.getSexo();
            a_codigo_fonasa_descripcion = pac_fon.getCodigo_fonasa_descripcion();
            a_tramo = pac_fon.getTramo_prevision();
            a_prais = pac_fon.getPrais();

            codigoprevicion = pac.getId_prevision();
            t = pac.getTramo();

        } else {
            existe = 1;
            a_nombres = pac.getNombres_paciente();
            a_apellidop = pac.getApellidop_paciente();
            a_apellidom = pac.getApellidom_paciente();
            a_fono1 = pac.getTelefono1();
            a_fono2 = pac.getTelefono2();
            a_rut = pac.getRut_paciente();
            a_direccion = pac.getDireccion();
            a_fecha_nacimiento = pac.getFecha_nac();
            a_sexo = pac.getSexo();

            a_comuna = pac.getComuna_codigo(); // xq el tipo de id comuna es varchar en la BD ¬¬
            a_pueblo = pac.getPueblo();
            a_consultorio_pertenencia = pac.getProcedencia();
            a_mail = pac.getMail();
            a_prais = pac.getPrais();
            a_tramo = pac.getTramo_prevision();
            codigoprevicion = pac.getId_prevision();
            t = pac.getTramo();

            a_nombresocial = pac.getNombresocial();
            a_categoria_ocupacional = pac.getCategoria_ocupacional();
            a_nivel_instruccion = pac.getNivel_instruccion();
            a_ley_previsional = pac.getLey_previsional();
            a_pueblo_afrodescendiente = pac.getPueblo_afrodescendiente();
            a_identidad_genero = pac.getIdentidad_genero();
            a_tipo_via = pac.getTipo_via();
            a_ocupacion_declarada = pac.getId_ocupacion_declarada();
            a_pueblo_originario_gate = pac.getId_pueblo_originario_gate();
            a_pais_origen = pac.getPais_origen();
            a_procedencia_servicio = pac.getId_procedencia_servicio();
            a_establecimiento_procedencia = pac.getEstablecimiento_procedencia();

        }

        /**/
        String cbo_opcion_seleccionada = "";
        /**/

        //  out.write(""+pac.getCodigo_fonasa()+"<br>"+pac.getTramo_prevision()+"<br>"+pac.getPrais());
%>
<script>
     function validarFechaMenorActual(date) {
        var dateInsert = new Date();
        var fecha = date.split("/");
        dateInsert.setFullYear(fecha[2], fecha[1] - 1, fecha[0]);
        var today = new Date();
        if (dateInsert >= today)
            return false;
        else
            return true;
    }
    function valida_form() {
        for (i = 0; i < 100; i++) {
            if (document.getElementById('direccion').value[i] == '#')
            {
                alert('No use Caracteres como # u Otros!!!');
                document.getElementById('direccion').value = document.getElementById('direccion').value.replace('#', 'N° ');
            }
        }

        if (!validaRut12(document.getElementById('rutpaciente').value, 1))
        {
            document.getElementById('rutpaciente').focus();
            return false;
        }

        if (document.getElementById('nombres').value.length == 0) {
            alert('Debe ingresar Nombres');
            document.getElementById('nombres').focus();
            return false;
        } else if (document.getElementById('apellidop').value.length == 0) {
            alert('Debe ingresar apellido Paterno');
            document.getElementById('apellidop').focus();
            return false;
        } else if (document.getElementById('fecha_nac').value.length != 10) {
            alert('Debe ingresar Fecha Nacimiento (dd/mm/aaaa)');
            document.getElementById('fecha_nac').focus();
            return false;
        } else if (!validarFechaMenorActual(document.getElementById('fecha_nac').value)) {
            alert('Debe ingresar Una fecha valida menor.');
            document.getElementById('fecha_nac').focus();
            return false;
        }else if (document.getElementById('direccion').value.length == 0) {
            alert('Debe ingresar Dirección');
            document.getElementById('direccion').focus();
            return false;
        } else if (document.getElementById('id_comuna').value == -1) {
            alert('Debe seleccionar comuna');
            return false;
        } else if (document.getElementById('id_consultorio_pertenencia').value == 0) {
            alert('Debe seleccionar Consultorio Pertenencia');
            return false;
        } else if (document.getElementById('id_pueblo_originario_gate').value == -1) {
            alert('Debe indicar si se considera perteneciente a algun pueblo indigena u originario');
            return false;
        } else if (document.getElementById('id_pueblo_originario_gate').value == '1' && document.getElementById('id_pueblo').value == -2) {
            alert('Debe seleccionar el pueblo indigena u originario');
            return false;
        } else if (document.getElementById('pais_origen').value.length == 0) {
            alert('Debe ingresar Pais de Origen del paciente');
            return false;
        } else if (document.getElementById('prevision').value == -1) {
            alert('Debe seleccionar previsión');
            return false;
        } else if (document.getElementById('fecha_duo').value.length == 0) {
            alert('Debe seleccionar Fecha Duo');
            return false;
        } else if (document.getElementById('id_derivado').value == -2) {
            alert('Debe seleccionar Derivador');
            return false;
        } else if (document.getElementById('id_procedencia_servicio').value == -1) {
            alert('Debe seleccionar Procedencia del Paciente (Clasificacion Oficial IEEH)');
            return false;
        } else if ((document.getElementById('id_procedencia_servicio').value == '4' || document.getElementById('id_procedencia_servicio').value == '7') && document.getElementById('establecimiento_procedencia').value.length == 0) {
            alert('Debe ingresar el Establecimiento de Procedencia');
            return false;
        } else if (document.getElementById('id_cama').value == -2) {
            alert('Debe seleccionar Cama');
            return false;
        } else if (document.getElementById('id_tipo_via').value == -1) {
            alert('Debe seleccionar Tipo de Via');
            return false;
        } else if (document.getElementById('id_categoria_ocupacional').value == -1) {
            alert('Debe seleccionar Categoria Ocupacional');
            return false;
        } else if (document.getElementById('id_categoria_ocupacional').value == '2' && document.getElementById('id_ocupacion_declarada').value == -1) {
            alert('Debe seleccionar Ocupacion Declarada');
            return false;
        } else if (document.getElementById('id_nivel_instruccion').value == -1) {
            alert('Debe seleccionar Nivel de Instruccion');
            return false;
        } else if (document.getElementById('id_ley_previsional').value == -1) {
            alert('Debe seleccionar Leyes Previsionales');
            return false;
        } else if (document.getElementById('id_pueblo_afrodescendiente').value == -1) {
            alert('Debe seleccionar Pueblo Afrodescendiente Chileno');
            return false;
        } else if (document.getElementById('id_identidad_genero').value == -1) {
            alert('Debe seleccionar Identidad de Genero');
            return false;
        }
        /* Nombre Social se deja opcional a proposito: solo corresponde
           completarlo cuando el/la paciente usa un nombre distinto al
           legal (Ley 21.120). Avisar si se quiere hacer obligatorio
           tambien. */

        if (confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Información ? \n \n ")) {
        } else {
            return false;
        }
    }
</script>

<style>
    /* 27-08-2026: layout responsivo para que el formulario de ingreso
       (incluye los campos nuevos UME) no se corte ni se desborde de
       la pantalla en monitores angostos o notebooks. */
    #form1_wrapper {
        max-width: 100%;
        overflow-x: auto;
    }
    #form1 table {
        width: 100%;
        max-width: 100%;
        table-layout: fixed;
        border-collapse: collapse;
    }
    #form1 table td {
        padding: 4px 6px;
        word-wrap: break-word;
        overflow-wrap: break-word;
        vertical-align: top;
    }
    #form1 table input[type="text"],
    #form1 table input[type="tel"],
    #form1 table select {
        width: 100% !important;
        max-width: 100%;
        box-sizing: border-box;
    }
</style>
<div id="form1_wrapper">
<form  id="form1" name="form1" action="<% out.write(neg.getLocal());%>ingreso_uh" onsubmit="return valida_form()" method="POST"   >
    <input type="hidden" name="modo" id="modo" value="1">
    <input type="hidden" name="existe" id="existe" value="<%=existe%>">
    <input type="hidden" name="verificado_fonasa" id="verificado_fonasa" value="0">
    <div id="Datitos"><input type="hidden" id="id_duo" value="0"></div>
    <fieldset>
        <legend>Ingreso del Paciente:<%=obtiene_rut%></legend>
        <table  style="FONT-FAMILY: Calibri; FONT-SIZE: 13px;" BORDER="0">
            <tr><td>Nombre Paciente:</td>
                <td><input type="text" size="25" name="nombres" id="nombres" value="<%=a_nombres%>"  ></td>
                <td>Primer Apellido.</td><td><input name="apellidop" id="apellidop" type="text" size="25" value="<%=a_apellidop%>" ></td>
                <td>Segundo Apellido.</td><td><input name="apellidom" id="apellidom" type="text" size="25" value="<%=a_apellidom%>"  ></td>
            <tr>
                <td>Rut:</td>
                <td>
                    <input type="text" name="rut" id="rut" value="<%=a_rut%>" readonly="readonly" >
                </td>
                <td>F. Nacimiento:</td>
                <td>
                    <input name="fecha_nac" id="fecha_nac" type="text" size="20" value="<%=a_fecha_nacimiento%>"  max="2022-01-16" required >
                    <img src="Imagenes/calender.png" id="f_trigger_a" style="cursor:pointer" onclick="document.getElementById('fecha_nac').focus()">
                </td>
                <td>Sexo:</td>
                <td>

                    M<input type="radio" name="rbt_sexo" id="rbt_sexo0"  value="0"  checked='checked'     />
                    F<input type="radio" name="rbt_sexo" id="rbt_sexo1"   value="1"  />
                </td>
            </tr>
            <tr>
                <td>Tipo de Via:</td>
                <td>
                    <select id="id_tipo_via" name="id_tipo_via">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_via.hasNext()) {
                                via = (cTipoVia) it_via.next();
                                cbo_opcion_seleccionada = "  ";
                                if (via.getId_tipo_via() == a_tipo_via) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + via.getId_tipo_via() + "' " + cbo_opcion_seleccionada + " >" + via.getDescripcion_tipo_via() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>Dirección:</td>
                <td colspan="3">
                    <input type="text" size="60" onkeyup="for (i = 0; i < 100; i++) {
                                if (this.value[i] == '#') {
                                    alert('No use Caracteres como # u Otros!!!');
                                    this.value = this.value.replace('#', 'N° ');
                                }
                            }" id="direccion" name="direccion" value="<%=a_direccion%>">
                </td>
            </tr>
            <tr>
                <td>Comuna:</td>
                <td colspan="5">
                    <select id="id_comuna" name="id_comuna" >
                        <option value="-1" >Seleccione...</option>
                        <%

                            while (it_com.hasNext()) {
                                com = (cComuna) it_com.next();
                                cbo_opcion_seleccionada = "  ";
                                if (a_comuna == com.getId_comuna()) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + com.getId_comuna() + "' " + cbo_opcion_seleccionada + " >" + com.getComuna_descripcion() + "</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Teléfono:</td>
                <td>
                    <input type="text" id="telefono1" name="telefono1"  value="<%=a_fono1%>">
                </td>
                <td>Celular: 09-</td>
                <td><input type="text" id="telefono2" name="telefono2"  value="<%=a_fono2%>"></td>
            </tr>

            <tr>
                <td>Email</td>
                <td colspan="3" >
                    <input style=" width: 400px " type="text" id="txt_mail" name="txt_mail"  value="<%=a_mail%>">
                </td>
            </tr>

            <tr>
                <td>Consultorio de pertenencia:</td>
                <td>
                    <select  name="id_consultorio_pertenencia" id="id_consultorio_pertenencia">
                        <option value="0" >Seleccione...</option>
                        <%
                            while (it_cons.hasNext()) {
                                cons = (cConsultorio) it_cons.next();
                                cbo_opcion_seleccionada = "  ";
                                if (cons.getId() == a_consultorio_pertenencia) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + cons.getId() + "' " + cbo_opcion_seleccionada + " >" + cons.getDescripcion() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>Se considera perteneciente a algun pueblo indigena u originario?:</td>
                <td>
                    <select name="id_pueblo_originario_gate" id="id_pueblo_originario_gate" onchange="javascript:
                                    var gateVal = document.forms['form1']['id_pueblo_originario_gate'].value;
                            if (gateVal == '1') {
                                document.getElementById('pueblo_originario_wrapper').style.display = 'inline';
                            } else {
                                document.getElementById('pueblo_originario_wrapper').style.display = 'none';
                                document.getElementById('id_pueblo').value = '-2';
                            }
                            ">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_gate.hasNext()) {
                                gate = (cPuebloOriginarioGate) it_gate.next();
                                cbo_opcion_seleccionada = "  ";
                                if (gate.getId_gate() == a_pueblo_originario_gate) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + gate.getId_gate() + "' " + cbo_opcion_seleccionada + " >" + gate.getDescripcion_gate() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>Pueblo Originario:</td>
                <td>
                    <span id="pueblo_originario_wrapper" style="<%=(a_pueblo_originario_gate == 1 ? "display:inline" : "display:none")%>">
                    <select  name="id_pueblo" id="id_pueblo">
                        <option value="-2" >Seleccione...</option>
                        <%
                            while (it_pue.hasNext()) {
                                pue = (cPueblo) it_pue.next();
                                cbo_opcion_seleccionada = "  ";
                                if (pue.getId_pueblo() == a_pueblo) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + pue.getId_pueblo() + "' " + cbo_opcion_seleccionada + " >" + pue.getDescripcion_pueblo() + "</option>");
                            }
                        %>
                    </select>
                    </span>
                </td>
            </tr>
            <tr>
                <td>Nacionalidad:</td>
                <td>
                    <select name="paciente_nacion" id="paciente_nacion" style=" width:150px;  " >
                        <%
                            while (it_nac.hasNext()) {
                                nac = (cNacion) it_nac.next();
                                if (nac.getDescripcion_nac().equalsIgnoreCase("CHILE")) {
                                    out.write("<option value='" + nac.getId_nac() + "' selected='selected' >" + nac.getDescripcion_nac() + "</option>");
                                } else {
                                    out.write("<option value='" + nac.getId_nac() + "' >" + nac.getDescripcion_nac() + "</option>");
                                }

                            }

                        %>
                    </select>
                </td>
                <td>Pais de Origen del (de la) paciente:</td>
                <td>
                    <input type="text" size="25" id="pais_origen" name="pais_origen" value="<%=a_pais_origen%>">
                </td>
            </tr>

            <tr>
                <td>Previsión:</td>
                <td>
                    <select class="form-control" id="prevision" name="prevision"  onchange="javascript:
                                    var pre = document.forms['form1']['prevision'].value;
                            if (pre == 1) {

                                document.getElementById('t').style.display = 'block';
                            } else
                                document.getElementById('t').style.display = 'none';
                            ">


                        <option  value="-1">Prevision
                            <% for (prevision pre : neg.buscarPrevicion()) {

                                    if (codigoprevicion == pre.getId_prevision()) {
                            %>

                        <option selected  value="<%=pre.getId_prevision()%>"><%=pre.getNombre()%>
                            <% } else {%>
                        <option  value="<%=pre.getId_prevision()%>"><%=pre.getNombre()%>
                            <%}
                                }%>

                    </select>
                </td>

                <td id="t" name="t" >

                    <select class="form-control" id="tramo" name="tramo" >
                        <option value="0">Tramo
                            <% for (prevision pre : neg.buscarTramo()) {

                                    if (pre.getId_prevision() == t) {%>
                        <option selected value="<%=pre.getId_prevision()%>"><%=pre.getNombre()%>  
                            <%} else {%>

                        <option  value="<%=pre.getId_prevision()%>"><%=pre.getNombre()%>
                            <%}
                                }%>
                    </select>

                </td>


                <td>
                    <input type="hidden" name="paciente_programa" id="paciente_programa" value="0" >
                </td>
            </tr>


            <tr>
                <td>Fecha y Hora:</td>
                <td>
                    <input name="fecha_duo" id="fecha_duo" type="text" size="22" value="<% out.write(hora_Registro);%>" >
                </td>
                <td>Procedencia del (de la ) paciente:</td>
                <td>
                    <select name="id_derivado" id="id_derivado">
                        <option value="-2">Seleccione...</option>
                        <%
                            while (it_der.hasNext()) {
                                der = (cConsultorio) it_der.next();
                                out.write("<option value='" + der.getId() + "' >" + der.getDescripcion() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>N° Cama:</td>
                <td>
                    <select  name="id_cama" id="id_cama">
                        <option value="-2">Seleccione...</option>
                        <%
                            while (it_cam.hasNext()) {
                                cam = (cCama) it_cam.next();
                                out.write("<option value='" + cam.getId_cama() + "' >" + cam.getDescripcion_cama() + "</option>");

                            }
                        %>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Procedencia del Paciente (Clasificacion Oficial IEEH):</td>
                <td>
                    <select name="id_procedencia_servicio" id="id_procedencia_servicio" onchange="javascript:
                                    var procv = document.forms['form1']['id_procedencia_servicio'].value;
                            if (procv == '4' || procv == '7') {
                                document.getElementById('establecimiento_procedencia_wrapper').style.display = 'inline';
                            } else {
                                document.getElementById('establecimiento_procedencia_wrapper').style.display = 'none';
                                document.getElementById('establecimiento_procedencia').value = '';
                            }
                            ">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_proc.hasNext()) {
                                proc = (cProcedenciaPaciente) it_proc.next();
                                cbo_opcion_seleccionada = "  ";
                                if (proc.getId_procedencia() == a_procedencia_servicio) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + proc.getId_procedencia() + "' " + cbo_opcion_seleccionada + " >" + proc.getDescripcion_procedencia() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>Establecimiento de Procedencia:<br><small>(Solo si Procedencia = Otro Establecimiento u Hospital comunitario/baja complejidad)</small></td>
                <td>
                    <span id="establecimiento_procedencia_wrapper" style="<%=((a_procedencia_servicio == 4 || a_procedencia_servicio == 7) ? "display:inline" : "display:none")%>">
                    <input type="text" size="25" id="establecimiento_procedencia" name="establecimiento_procedencia" value="<%=a_establecimiento_procedencia%>">
                    </span>
                </td>
            </tr>

            <tr>
                <td>Nombre Social:</td>
                <td colspan="3">
                    <input type="text" size="40" name="nombresocial" id="nombresocial" value="<%=a_nombresocial%>" >
                </td>
            </tr>

            <tr>
                <td>Categoria Ocupacional:</td>
                <td>
                    <select name="id_categoria_ocupacional" id="id_categoria_ocupacional" onchange="javascript:
                                    var cato = document.forms['form1']['id_categoria_ocupacional'].value;
                            if (cato == '2') {
                                document.getElementById('ocupacion_declarada_wrapper').style.display = 'inline';
                            } else {
                                document.getElementById('ocupacion_declarada_wrapper').style.display = 'none';
                                document.getElementById('id_ocupacion_declarada').value = '-1';
                            }
                            ">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_cat_ocu.hasNext()) {
                                cat_ocu = (cCategoriaOcupacional) it_cat_ocu.next();
                                cbo_opcion_seleccionada = "  ";
                                if (cat_ocu.getId_categoria_ocupacional() == a_categoria_ocupacional) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + cat_ocu.getId_categoria_ocupacional() + "' " + cbo_opcion_seleccionada + " >" + cat_ocu.getDescripcion_categoria_ocupacional() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>Nivel de Instruccion:</td>
                <td>
                    <select name="id_nivel_instruccion" id="id_nivel_instruccion">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_niv_ins.hasNext()) {
                                niv_ins = (cNivelInstruccion) it_niv_ins.next();
                                cbo_opcion_seleccionada = "  ";
                                if (niv_ins.getId_nivel_instruccion() == a_nivel_instruccion) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + niv_ins.getId_nivel_instruccion() + "' " + cbo_opcion_seleccionada + " >" + niv_ins.getDescripcion_nivel_instruccion() + "</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Ocupacion Declarada:<br><small>(Solo si Categoria Ocupacional = Activos)</small></td>
                <td colspan="3">
                    <span id="ocupacion_declarada_wrapper" style="<%=(a_categoria_ocupacional == 2 ? "display:inline" : "display:none")%>">
                    <select name="id_ocupacion_declarada" id="id_ocupacion_declarada">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_ocu_dec.hasNext()) {
                                ocu_dec = (cOcupacionDeclarada) it_ocu_dec.next();
                                cbo_opcion_seleccionada = "  ";
                                if (ocu_dec.getId_ocupacion_declarada() == a_ocupacion_declarada) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + ocu_dec.getId_ocupacion_declarada() + "' " + cbo_opcion_seleccionada + " >" + ocu_dec.getDescripcion_ocupacion_declarada() + "</option>");
                            }
                        %>
                    </select>
                    </span>
                </td>
            </tr>

            <tr>
                <td>Leyes Previsionales:</td>
                <td>
                    <select name="id_ley_previsional" id="id_ley_previsional">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_ley_prev.hasNext()) {
                                ley_prev = (cLeyPrevisional) it_ley_prev.next();
                                cbo_opcion_seleccionada = "  ";
                                if (ley_prev.getId_ley_previsional() == a_ley_previsional) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + ley_prev.getId_ley_previsional() + "' " + cbo_opcion_seleccionada + " >" + ley_prev.getDescripcion_ley_previsional() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>Pueblo Afrodescendiente Chileno:</td>
                <td>
                    <select name="id_pueblo_afrodescendiente" id="id_pueblo_afrodescendiente">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_pue_afro.hasNext()) {
                                pue_afro = (cPuebloAfrodescendiente) it_pue_afro.next();
                                cbo_opcion_seleccionada = "  ";
                                if (pue_afro.getId_pueblo_afro() == a_pueblo_afrodescendiente) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + pue_afro.getId_pueblo_afro() + "' " + cbo_opcion_seleccionada + " >" + pue_afro.getDescripcion_pueblo_afro() + "</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Identidad de Genero:</td>
                <td colspan="3">
                    <select name="id_identidad_genero" id="id_identidad_genero">
                        <option value="-1" >Seleccione...</option>
                        <%
                            while (it_gen.hasNext()) {
                                gen = (cIdentidadGenero) it_gen.next();
                                cbo_opcion_seleccionada = "  ";
                                if (gen.getId_identidad_genero() == a_identidad_genero) {
                                    cbo_opcion_seleccionada = " selected='selected' ";
                                }
                                out.write("<option value='" + gen.getId_identidad_genero() + "' " + cbo_opcion_seleccionada + " >" + gen.getDescripcion_identidad_genero() + "</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>

        <fieldset class="buttons">
            <br><br>
            <input class="btn btn-primary" type="submit" value="GUARDAR DATOS" name="btn_guarda_datos" />

            <br><br>
        </fieldset>
    </fieldset>

</form>
</div>


<%
    }
%>