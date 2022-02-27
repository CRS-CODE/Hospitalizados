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
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%

    NegocioQ neg = new NegocioQ();

    String obtiene_rut = request.getParameter("user");
    String div = request.getParameter("div");
    String rutsin = request.getParameter("sindiv");

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
        //String obtiene_rut = "16.623.070-5";
        /* informacion para llenar el formulario*/
        ArrayList comuna = neg.buscarComuna();
        ArrayList consultorio = neg.lista_consultorio_pertenecia();
        ArrayList derivador = neg.lista_derivador();
        ArrayList cama = neg.lista_cama_desocupada("1"); // 1 es el area para camas disponibles normales , puede ser tambien -(1,2)-
        ArrayList pueblo = neg.lista_pueblo();
        ArrayList nacion = neg.lista_nacion();

        Iterator it_com = comuna.iterator();
        Iterator it_cons = consultorio.iterator();
        Iterator it_der = derivador.iterator();
        Iterator it_cam = cama.iterator();
        Iterator it_pue = pueblo.iterator();
        Iterator it_nac = nacion.iterator();

        cComuna com = new cComuna();
        cConsultorio cons = new cConsultorio();
        cConsultorio der = new cConsultorio();
        cCama cam = new cCama();
        cPueblo pue = new cPueblo();
        cNacion nac = new cNacion();
        cPaciente pac = neg.obtiene_paciente(obtiene_rut);
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

        //  pac.setRut("");
        if (pac.getRut_paciente().equals("")) {
            existe = 0;
            cPaciente pac_fon = new cPaciente();
            // 15122015 servicio deprecado 
            //   try {

            // pac_fon = n_fon.getConsultaPrevision(obtiene_rut);
            //  } catch (Exception ex) {
            //      pac_fon = new cPaciente();
            //  }
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

        }

        /**/
        String cbo_opcion_seleccionada = "";
        /**/

        //  out.write(""+pac.getCodigo_fonasa()+"<br>"+pac.getTramo_prevision()+"<br>"+pac.getPrais());
%>

<form  id="form1" name="form1" action="<% out.write(neg.getLocal());%>ingreso_uh" onsubmit="return valida_ingreso()" method="POST"   >
    <input type="hidden" name="modo" id="modo" value="1">
    <input type="hidden" name="existe" id="existe" value="<%=existe%>">
    <input type="hidden" name="verificado_fonasa" id="verificado_fonasa" value="0">
    <div id="Datitos"><input type="hidden" id="id_duo" value="0"></div>
    <fieldset>
        <legend>Ingreso del Paciente:<%=obtiene_rut%></legend>
        <table  style="FONT-FAMILY: Calibri; FONT-SIZE: 13px;" BORDER="0" width="720">
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
                    <input name="fecha_nac" id="fecha_nac" type="text" size="20" value="<%=a_fecha_nacimiento%>"  >
                    <img src="Imagenes/calender.png" id="f_trigger_a" style="cursor:pointer" onclick="document.getElementById('fecha_nac').focus()">
                </td>
                <td>Sexo:</td>
                <td>

                    M<input type="radio" name="rbt_sexo" id="rbt_sexo0"  value="0"  checked='checked'     />
                    F<input type="radio" name="rbt_sexo" id="rbt_sexo1"   value="1"  />
                </td>
            </tr>
            <tr>
                <td>Dirección:</td>
                <td colspan="3">
                    <input type="text" size="60" onkeyup="for (i = 0; i < 100; i++) {
                                if (this.value[i] == '#') {
                                    alert('No use Caracteres como # u Otros!!!');
                                    this.value = this.value.replace('#', 'N° ');
                                }
                            }" id="direccion" name="direccion" value="<%=a_direccion%>">
                </td>
                <td>Comuna:</td>
                <td>
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
                <td>Pueblo Originario:</td>
                <td>
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
                </td>
                <td>País de origen del (de la) paciente:
                </td>
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
        </table>
       
        <fieldset class="buttons">
            <br><br>
            <input class="btn btn-primary" type="submit" value="GUARDAR DATOS" name="btn_guarda_datos" />

            <br><br>
        </fieldset>
    </fieldset>

</form>
                   
<% 
        }
%>
