<%-- 
    Document   : ingreso_medico_carga
    Created on : 17-may-2012, 11:10:53
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="CapaDato.cDuo"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cCategorizacion"%>
<%@page import="CapaDato.cEnfermedad"%>
<%@page import="java.util.Iterator"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.ArrayList"%>

<%
            NegocioQ neg = new NegocioQ();
            ArrayList lista_trazadoras = neg.lista_prestacion_trazadora();
            Iterator it_tra = lista_trazadoras.iterator();
            ArrayList lista_cronicas = neg.lista_enfermedad_cronica();
            Iterator it_cro = lista_cronicas.iterator();
            //  ArrayList lista_categorizacion = neg.lista_categorizacion();
            //  Iterator it_cat = lista_categorizacion.iterator();

            ArrayList consultorio = neg.lista_consultorio_pertenecia();
            Iterator it_cons = consultorio.iterator();



            cEnfermedad aux = new cEnfermedad();
            cCategorizacion cat = new cCategorizacion();

            int obtiene_duo = Integer.parseInt(request.getParameter("duo"));
            cDuo duo = neg.obtiene_duo(obtiene_duo);

%>
<script type="text/javascript">
    function onBlurAnamnesis(){
        if(document.getElementById('anamnesis').value=='')
        {
            document.getElementById('anamnesis').style.color='gray';
            document.getElementById('anamnesis').value='Anamnesis...'}
    }
    function onFocusAnamnesis(){
        if(document.getElementById('anamnesis').value=='Anamnesis...')
        {
            document.getElementById('anamnesis').value='';
            document.getElementById('anamnesis').style.color='black';
        }
    }
    function onBlurDiag(){
        if(document.getElementById('diagnostico').value=='')
        {
            document.getElementById('diagnostico').style.color='gray';
            document.getElementById('diagnostico').value='Ingrese Diagnostico'}
    }
    function onFocusDiag(){
        if(document.getElementById('diagnostico').value=='Ingrese Diagnostico')
        {
            document.getElementById('diagnostico').value='';
            document.getElementById('diagnostico').style.color='black';
        }
    }

    function dignostico()
    {
        if(document.getElementById('FielDiagnostico').style.display=='none')
        {
            return false;
        }
        else{
            if(document.getElementById('diagnostico').value=='Ingrese Diagnostico' || document.getElementById('diagnostico').value=='')
            {
                alert('Debe Ingresar la Descripción del Diagnóstico');
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
        var id_duo=document.getElementById('id_duo').value;
        var diagnostico=document.getElementById('diagnostico').value;
        var tipo_diagnostico_duo=document.getElementById('tipo_diagnostico_duo').value;
        

        //alert('id_duo='+id_duo+'--diagnostico='+diagnostico+'--tipo='+tipo_diagnostico_duo);
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico?id_duo='+id_duo+'&diagnostico='+diagnostico+'&tipo_diagnostico_duo='+tipo_diagnostico_duo+'&modo=1', true);
        ajax1.send(null);
        if (ajax1.readyState==1)
        {
            document.getElementById('cargandoD').innerHTML='Cargando...';
        }
        ajax1.onreadystatechange=function()
        {
            if (ajax1.readyState==4)
            {
                document.getElementById('DivMuestraDiag').innerHTML=ajax1.responseText;
                document.getElementById('cargandoD').innerHTML='';
                document.getElementById('diagnostico').value='';
            }
        }
    }
    function EliminaDiag(id)
    {
        var id_duo=document.getElementById('id_duo').value;
        var diagnostico=document.getElementById('diagnostico').value;
        var tipo_diagnostico_duo=document.getElementById('tipo_diagnostico_duo').value;
      
        
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico?id_duo='+id_duo+'&diagnostico='+diagnostico+'&tipo_diagnostico_duo='+tipo_diagnostico_duo+'&id_diagnostico_duo='+id+'&modo=2', true);
        ajax1.send(null);
        if (ajax1.readyState==1)
        {
            document.getElementById('cargandoD').innerHTML='Cargando...';

        }
        ajax1.onreadystatechange=function()
        {
            if (ajax1.readyState==4)
            {
                document.getElementById('DivMuestraDiag').innerHTML=ajax1.responseText;
                document.getElementById('cargandoD').innerHTML='';
                document.getElementById('diagnostico').value=='';
            }
        }
    }
    function Enviar()
    {
        if(document.getElementById('anamnesis').value=='Anamnesis...' || document.getElementById('anamnesis').value=='')
        {alert('Debe Ingresar La Anamnesis de Ingreso!!');
            document.getElementById('anamnesis').focus();
            return false;
        }
        if(document.getElementById('cbo_consultorio_pertenencia').value==-1)
        {alert('Debe Seleccionar una Categorización!!\nSi no tiene seleccione N/A');
            document.getElementById('cbo_consultorio_pertenencia').focus();
            return false;
        }
        if(confirm('Esta apunto de generar el DUO N°: '+document.getElementById('id_duo').value+' \nRecuerde que despues de Generado no podrá modificarlo\nEsta Seguro de Continuar?'))
        {
            document.getElementById('Form1').action='<%=neg.getLocal()%>ingreso_dato';
            document.getElementById('Form1').submit();
            window.open('<% out.write(neg.getLocal()+"PDF_DUO"); %>?id_duo='+document.getElementById('id_duo').value,'pop-up','width=500, height=500, scrollbars=yes, menubar=no, location=yes, status=no, resizable=yes,left = 800,top = 0');
            // document.location.href='http://10.8.4.29:8084/modulo_uhce/uh_visita.jsp';
        }
    }
</script>
<script>
    function valida_enter_txt_diagnostico(evt){
        //asignamos el valor de la tecla a keynum
        if(window.event){// IE
            keynum = evt.keyCode;
        }else{
            keynum = evt.which;
        }
        //
        if(keynum == 13 ){
            // enter
            if(dignostico()){GuardaDiag();}
            return false;
        }
    }
</script>

<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<form id="Form1" >
    <fieldset>
        <legend>Ingreso del Paciente: </legend>
        <table bgcolor="#cccccc"  style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" BORDER="0" width="720">
            <input type="hidden" id="rut" value="<%=String.valueOf(request.getParameter("rut"))%>">
            <input type="hidden" name="txt_rut" id="txt_rut" value="<% out.write(duo.getRut_paciente());  %>">
            <input type="hidden" id="id_duo" name="id_duo" value="<% out.write(obtiene_duo + "");%>">
            <input type="hidden" id="estado" name="estado" value="21">
            <input type="hidden" id="txt_modo" name="txt_modo" value="2">
            <input type="hidden" id="txt_rut_usuario" name="txt_rut_usuario" value="<% out.write("" + session.getAttribute("usuario_rut"));%>" >
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
        <div id="Resultado" style=" overflow:auto;
             padding-right: 15px; padding-top: 5px; padding-left: 15px; padding-bottom: 15px;
             border-right: #6699CC 1px solid; border-top: #999999 1px solid;
             border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
             scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
             scrollbar-track-color :#3333333 ;
             height:370px; left: 100; top: 10; width: 95%">
            <!-- Este DIV contendra la respuesta enviada por el Servlet -->
            <fieldset>
                <legend>INGRESO MEDICO</legend>
                <table border="0" style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;">
                    <tr>
                        <td colspan="14" ></td>
                    </tr>
                    <tr>
                        <td colspan="4" width="150px">
                            <textarea rows="15" cols="90" name="anamnesis" id="anamnesis" title="Anamnesis" onblur="onBlurAnamnesis();" onfocus="onFocusAnamnesis()" style=" color: gray  "   >Anamnesis...</textarea>
                        </td>
                        <td colspan="5">
                            <center>
                                <b>CONSULTORIO PERTENECIENTE</b>
                                <br><br>

                                <select  name="cbo_consultorio_pertenencia" id="cbo_consultorio_pertenencia">
                                    <option value="-2" >Seleccione...</option>
                                    <%
                                                cConsultorio cons = new cConsultorio();
                                                String cbo_opcion_seleccionada = "";
                                                int a_consultorio_pertenencia = duo.getConsultorio();

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
                            </center>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <fieldset>
                <legend>Enfermedades Crónicas</legend>
                <table>
                    <tr>
                        <td colspan="1">
                            <table>
                                <%
                                            int i = 0;
                                            while (it_cro.hasNext()) {
                                                aux = (cEnfermedad) it_cro.next();
                                %>
                                <%
                                                                                int resto = i % 4; // numero de elementos antes de saltarse un afila
                                %>

                                <%  if (resto == 0 || i == 0) {%><tr><%}%>
                                    <td> <input id="EnfCronH" name="EnfCronH" type="checkbox" value="<%=aux.getId()%>"><% out.write("" + aux.getDescripcion());%>&nbsp;&nbsp;&nbsp;</td>
                                        <% i++;
                                                    }
                                        %>
                            </table>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <fieldset id="FielDiagnostico" style="">
                <legend>Diagnóstico:</legend>
                <table>
                    <input type="hidden" id="tipo_diagnostico_duo" value="1">
                    <tr>
                        <td>
                            <input type="text" size="100" id="diagnostico" style="text-transform:capitalize; color: gray " onblur="onBlurDiag();" onfocus="onFocusDiag();" onkeypress="return valida_enter_txt_diagnostico(event)"  value="Ingrese Diagnostico">
                            <input type="button" onclick="if(dignostico()){GuardaDiag();}" value="Guardar">
                        </td>
                    </tr>
                </table>
                <div id="DivMuestraDiag" style="border:1px">
                </div>
            </fieldset>

            <fieldset id="FielDiagnostico" style="">
                <legend>Prestaciones Trazadoras</legend>
                <table>
                    <%
                                i = 0;
                                while (it_tra.hasNext()) {
                                    aux = (cEnfermedad) it_tra.next();
                    %>
                    <%
                                                        int resto = i % 3;
                    %>

                    <%  if (resto == 0 || i == 0) {%><tr><%}%>
                        <td> <input id="PreDuo" name="PreDuo" type="checkbox" value="<%=aux.getId()%>"><% out.write(aux.getDescripcion());%> &nbsp;&nbsp;&nbsp;</td>
                            <% i++;
                                        }
                            %>

                </table>
            </fieldset>
        </div>
        <h3 class="destacadorut">Responsable:<%=session.getAttribute("usuario_nombre_completo")%></h3>
        <fieldset class="buttons">
            <input type="button" id="BtnIngresar" style="" onclick="Enviar()" class="DR" value="Ingresar Paciente a Cama" >
      
        </fieldset>

    </fieldset>
</form>