<%-- 
    Document   : ficha_ingreso_carga
    Created on : 31-oct-2011, 21:19:19
    Author     : Dis
--%>

<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cDocumento"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cEnfermedad"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>

<%
    NegocioQ neg = new NegocioQ();
    ArrayList lista_documentos = neg.lista_documentos();
    Iterator it_doc = lista_documentos.iterator();

    cEnfermedad aux = new cEnfermedad();
    cDocumento doc = new cDocumento();

    int obtiene_duo = Integer.parseInt(request.getParameter("duo"));
    cDuo duo = neg.obtiene_duo(obtiene_duo);
%>


<script type="text/javascript">
    function onBlurAnamnesis() {
        if (document.getElementById('anamnesis').value == '')
        {
            document.getElementById('anamnesis').style.color = 'gray';
            document.getElementById('anamnesis').value = 'Anamnesis...'
        }
    }
    function onFocusAnamnesis() {
        if (document.getElementById('anamnesis').value == 'Anamnesis...')
        {
            document.getElementById('anamnesis').value = '';
            document.getElementById('anamnesis').style.color = 'black';
        }
    }
    function onBlurDiag() {
        if (document.getElementById('diagnostico').value == '')
        {
            document.getElementById('diagnostico').style.color = 'gray';
            document.getElementById('diagnostico').value = 'Ingrese Diagnostico'
        }
    }
    function onFocusDiag() {
        if (document.getElementById('diagnostico').value == 'Ingrese Diagnostico')
        {
            document.getElementById('diagnostico').value = '';
            document.getElementById('diagnostico').style.color = 'black';

        }
    }

    function dignostico()
    {
        if (document.getElementById('FielDiagnostico').style.display == 'none')
        {
            //alert('Estoy oculto');
            //document.getElementById('diagnostico').focus();
            return false;
        } else {
            if (document.getElementById('diagnostico').value == 'Ingrese Diagnostico' || document.getElementById('diagnostico').value == '')
            {
                alert('Debe Ingresar la Descripcion del Diagnostico');
                document.getElementById('diagnostico').focus();
                return false;
            }
        }
        return true;
    }
</script>
<script>
    function GuardaDiag()
    {
        var id_duo = document.getElementById('id_duo').value;
        var diagnostico = document.getElementById('diagnostico').value;
        var tipo_diagnostico_duo = document.getElementById('tipo_diagnostico_duo').value;
        document.getElementById('diagnostico').value == '';

        //alert('id_duo='+id_duo+'--diagnostico='+diagnostico+'--tipo='+tipo_diagnostico_duo);
        var ajax1 = nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico?id_duo=' + id_duo + '&diagnostico=' + diagnostico + '&tipo_diagnostico_duo=' + tipo_diagnostico_duo + '&modo=1', true);
        ajax1.send(null);
        if (ajax1.readyState == 1)
        {
            document.getElementById('cargandoD').innerHTML = 'Cargando...';
        }
        ajax1.onreadystatechange = function ()
        {
            if (ajax1.readyState == 4)
            {
                document.getElementById('DivMuestraDiag').innerHTML = ajax1.responseText;
                document.getElementById('cargandoD').innerHTML = '';
                document.getElementById('diagnostico').value = '';
            }
        }
    }
    function EliminaDiag(id)
    {
        var id_duo = document.getElementById('id_duo').value;
        var diagnostico = document.getElementById('diagnostico').value;
        var tipo_diagnostico_duo = document.getElementById('tipo_diagnostico_duo').value;


        var ajax1 = nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico?id_duo=' + id_duo + '&diagnostico=' + diagnostico + '&tipo_diagnostico_duo=' + tipo_diagnostico_duo + '&id_diagnostico_duo=' + id + '&modo=2', true);
        ajax1.send(null);
        if (ajax1.readyState == 1)
        {
            document.getElementById('cargandoD').innerHTML = 'Cargando...';

        }
        ajax1.onreadystatechange = function ()
        {
            if (ajax1.readyState == 4)
            {
                document.getElementById('DivMuestraDiag').innerHTML = ajax1.responseText;
                document.getElementById('cargandoD').innerHTML = '';
                document.getElementById('diagnostico').value = '';
            }
        }
    }

    function Enviar()
    {
        if (document.getElementById('morbilidades').value == '')
        {
            alert('Debe Ingresar Las Comorbilidades!!, de no tener Ingrese N/A');
            document.getElementById('morbilidades').focus();
            return false;
        }
        if (document.getElementById('farmacos').value == '')
        {
            alert('Debe Ingresar Los Fármacos que consume el Paciente al Ingreso!!De no tener Ingrese N/A');
            document.getElementById('farmacos').focus();
            return false;
        }
        if (document.getElementById('observacion').value == '')
        {
            alert('Debe Ingresar Observación o Historia Actual!!De no tener Ingrese N/A');
            document.getElementById('observacion').focus();
            return false;
        }


        if (confirm('Esta a Punto de Ingresar Al Paciente:  a la Unidad en la \nEsta Seguro?'))
        {
            document.getElementById('Form1').onsubmit = '';
            document.getElementById('Form1').action = '<%=neg.getLocal()%>ingreso_dato';
            document.getElementById('Form1').submit();
            window.open('<%=neg.getLocal()%>PDF_ingreso_enfermeria?txt_duo=' + document.getElementById('id_duo').value, 'pop-up', 'width=500, height=500, scrollbars=yes, menubar=no, location=yes, status=no, resizable=yes,left = 800,top = 0');

        }
    }
</script>

<script type="text/javascript" src="../js/jquery/jquery.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.form.js"></script>

<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<form id="Form1" onsubmit="return false">
    <fieldset>
        <legend>Recepción del Paciente por Parte de Enfermera</legend>
        <table bgcolor="#cccccc"  style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" BORDER="0" width="720"><input type="hidden" id="rut" value="<%=String.valueOf(request.getParameter("rut"))%>">

            <input type="hidden" id="rut" value="<%=String.valueOf(request.getParameter("rut"))%>">
            <input type="hidden" id="id_duo" name="id_duo" value="<% out.write(obtiene_duo + "");%>">
            <input type="hidden" id="txt_rut_usuario" name="txt_rut_usuario" value="<% out.write("" + session.getAttribute("usuario_rut")); %>" >
            <%if (duo.getEstado_duo() == 1) {
            %>
            <input type="hidden" id="estado" name="estado" value="2">
            <%            } else {
            %>
            <input type="hidden" id="estado" name="estado" value="21">
            <%                        }%>

            <input type="hidden" id="txt_modo" name="txt_modo" value="1">
            <tr>
                <td><b>Nombre:</b></td>
                <td COLSPAN="6"><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>
            <tr>
                <td><b>Rut:</b></td><td><% out.write("" + duo.getRut_paciente());%></td>
                <td><b>Fecha Nac:</b></td><td><% out.write("" + duo.getFecha_nac());%></td>
                <td><b>Edad:</b></td><td><% out.write("" + duo.getEdad());%></td>
                <td><b>Sexo:</b></td>
                <td><% out.write("" + duo.getSexo_descri());%></td>
            </tr>
            <tr>
                <td><b>Dirección:</b></td>
                <td colspan="3"><% out.write("" + duo.getDireccion());%></td>
                <td><b>Comuna:</b></td>
                <td><% out.write("" + duo.getComuna_descri());%></td>
            </tr>
            <tr>
                <td><b>Telefono:</b></td><td><% out.write("" + duo.getTelefono1());%></td>
                <td><b>Celular: 09-</b></td><td><% out.write("" + duo.getTelefono2());%></td>
            </tr>
            <tr>
                <td><b>Previsión:</b></td>
                <td colspan="2"><% out.write("" + duo.getCodigo_fonasa_descripcion());%></td>
                <td colspan="4">
                    <img src="../Iconos/dialog_information_small.png" width="15" height="20">Esta previsión esta sujeta a verifición a la salida del paciente
                </td>
            </tr>
            <tr>
                <td><b>Fecha y Hora:</b></td>
                <td><% out.write("" + duo.getFecha_hora_ing_duo());%></td>
                <td><b>Derivado desde:</b></td>
                <td><% out.write("" + duo.getDerivador_descripcion());%></td>
                <td><b>N° Cama:</b></td>
                <td><% out.write("" + duo.getCama_descripcion());%></td>
            </tr>
            <tr>
                <td colspan="6" >
                    <div id="cargandoD" ></div>
                </td>
            </tr>
        </table>
        <hr>
        <div id="Resultado" style=" overflow:auto;
             padding-right: 15px; padding-top: 5px; padding-left: 15px; padding-bottom: 15px;
             border-right: #6699CC 1px solid; border-top: #999999 1px solid;
             border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
             scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
             scrollbar-track-color :#3333333 ;
             height:415px; left: 100; top: 20; width: 95%">
            <!-- Este DIV contendra la respuesta enviada por el Servlet -->

            <fieldset id="FielDiagnostico" style="">
                <legend>Diagnósticos General</legend>
                <table>
                    <input type="hidden" id="tipo_diagnostico_duo" value="2">
                    <tr>
                        <td>
                            <input type="text" size="80" id="diagnostico" onkeypress="if (event.which == 13) {
                                        if (dignostico()) {
                                            GuardaDiag();
                                        }
                                    }" style="color:gray;text-transform:capitalize" onblur="onBlurDiag();" onfocus="onFocusDiag();" value="Ingrese Diagnostico">
                            <input type="button" onclick="if (dignostico()) {
                                        GuardaDiag();
                                    }" value="Guardar">
                        </td>
                    </tr>
                </table><div id="DivMuestraDiag" style="border:1px">
                </div>
            </fieldset>
            <table>
                <tr>
                    <th class="destacado">Antecedentes Mórbidos</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="morbilidades" name="morbilidades"></textarea></td>
                </tr>
                <tr>
                    <th class="destacado">Alergias</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="alergias" name="alergias"></textarea></td>
                </tr>
                <tr>
                    <th class="destacado">Tipo de Aislamiento</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="aislamiento" name="aislamiento"></textarea></td>
                </tr>
                <tr>
                    <th class="destacado">Plan de enfermeria</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="plan" name="plan"></textarea></td>
                </tr>

            </table>
            <table border="1" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;">
                <tr>
                    <th class="destacado"> Control de Signos Vitales </th>
                </tr>
                <tr>
                    <td colspan="6">

                        <table>
                            <table style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;">
                                <tr><td>PA</td><td><input tabindex="1" type="text" size="30" id="conciencia" name="pa"></td><td>Sat</td><td><input tabindex="11" type="text" size="30" id="sat" name="sat"></td></tr>
                                <tr><td>FC</td><td><input tabindex="2" type="text" size="30" id="cabeza" name="fa"></td><td>FiO2</td><td><input tabindex="12" type="text" size="30" id="fio2" name="fio2"></td></tr>
                                <tr><td>Tº</td><td><input tabindex="3" type="text" size="30" id="mucoza" name="t"></td><td>HGT</td><td><input tabindex="13" type="text" size="30" id="hgt" name="hgt"></td></tr>
                            </table>

                    </td>
                </tr>

                <tr>
                    <th class="destacado">Evolucion de Enfermeria</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="evolucion" name="evolucion"></textarea></td>
                </tr>

            </table>
            <fieldset>
                <legend>Elemento Invasivos</legend>
                <table style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;">
                    <tr><td>VVP</td><td><input tabindex="1" type="text" size="30" id="vvp" name="vvp"></td><td>GTT</td><td><input tabindex="11" type="text" size="30" id="gtt" name="gtt"></td></tr>
                    <tr><td>CUP</td><td><input tabindex="2" type="text" size="30" id="cup" name="cup"></td><td>PICC</td><td><input tabindex="12" type="text" size="30" id="picc" name="picc"></td></tr>
                    <tr><td>SNG</td><td><input tabindex="3" type="text" size="30" id="sng" name="sng"></td><td>CVC</td><td><input tabindex="13" type="text" size="30" id="cvc" name="cvc"></td></tr>
                    <tr><td>SNY</td><td><input tabindex="4" type="text" size="30" id="sny" name="sny"></td><td>TQT</td><td><input tabindex="14" type="text" size="30" id="tqt" name="tqt"></td></tr>
                </table>
            </fieldset>
            <table>
                <tr>
                    <th class="destacado">LPP</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="evolucion" name="evolucion"></textarea></td>
                </tr>
                <tr>
                    <th class="destacado">Prestaciones Realizadas</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="prestaciones" name="prestaciones"></textarea></td>
                </tr>
            </table>    
            <fieldset>
                <legend>Toma de Examenes</legend>
                <table style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;">
                    <tr><td>Venoso</td><td><input tabindex="1" type="text" size="30" id="venoso" name="venoso"></td><td>PCR</td><td><input tabindex="11" type="text" size="30" id="pcr" name="pcr"></td></tr>
                    <tr><td>Arterial</td><td><input tabindex="2" type="text" size="30" id="arterial" name="arterial"></td><td>ECG</td><td><input tabindex="12" type="text" size="30" id="ecg" name="ecg"></td></tr>
                    <tr> <td>otro</td><td><input tabindex="13" type="text" size="30" id="otro" name="otro"></td></tr>
                </table>
            </fieldset>
            <table>
                <tr>
                    <th class="destacado">Entrega de Farmacos</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="farmacos" name="farmacos"></textarea></td>
                </tr>
            </table>
            <table>
                <tr>
                    <th class="destacado">Educacion</th>
                </tr>
                <tr>
                    <td><textarea cols="100" rows="4" id="educacion" name="educacion"></textarea></td>
                </tr>
            </table>
            <fieldset>
                <legend>Entrega de Documentos</legend>
                <table border="0">
                    <tr>
                        <td>
                            <table border="0" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;">


                                <tr>
                                    <td><textarea cols="100" rows="4" id="documento" name="documento"></textarea></td>
                                </tr>
                            </table>
                        </td>

                    </tr>
                </table>

            </fieldset>

        </div>
    </fieldset>
    <h3 class="destacadorut">Responsable:<%=session.getAttribute("usuario_nombre_completo")%></h3>
    <fieldset class="buttons">
        <input type="button" id="BtnIngresar" style="" onclick="Enviar()" class="DR" value="Ingresar Paciente a Cama" >

    </fieldset>

</form>


