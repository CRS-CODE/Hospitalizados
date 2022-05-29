<%-- 
    Document   : ingreso_diagnostico_carga
    Created on : 27-ago-2012, 13:41:17
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cExamen"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="CapaDato.cDas"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>


<%
            NegocioQ neg = new NegocioQ();

            int numero_das = Integer.parseInt(request.getParameter("das"));

            ArrayList lista_destino = neg.lista_destino_suam();
            Iterator it_des = lista_destino.iterator();

            cDas das = neg.obtiene_das(numero_das);
            boolean sw_tiene_indicacion_egreso = false;
            String indicacion_egreso = "";
            if (das.getIndicacion_destino_id() != 0) {
                sw_tiene_indicacion_egreso = true;
                indicacion_egreso = das.getIndicacion_destino_descri();
            }

            String titulo = " style=' background-color: #f7903b ; color: white '  ";
            String datos = " style=' background-color: #fcd5b6 ; color: black '  ";

            String div_style = " style=' height: 230px; '";

            String fecha_hoy = neg.obtiene_fecha_hora();
            try {
                fecha_hoy = fecha_hoy.substring(0, 10) + "";
            } catch (Exception ex) {
            }

%>

<%  // lista de tipos de examenes a mostrar
            ArrayList lista = neg.lista_examen_radiografia("1,2");
            Iterator it_exa = lista.iterator();
            Iterator it_rad = lista.iterator();

            ArrayList lista_examen = neg.lista_examen_x_das(numero_das);

%>

<script type="text/javascript">

    $(function() {
        $( "#tabs" ).tabs();
    });


    function seleccionador(tab){
        $tabs.tabs('select', 2); // switch to third tab
        return false;
    }



    function onBlurDiag(){
        if(document.getElementById('txt_diagnostico').value=='')
        {
            document.getElementById('txt_diagnostico').style.color='gray';
            document.getElementById('txt_diagnostico').value='Ingrese Diagnostico'}
    }
    function onFocusDiag(){
        if(document.getElementById('txt_diagnostico').value=='Ingrese Diagnostico')
        {
            document.getElementById('txt_diagnostico').value='';
            document.getElementById('txt_diagnostico').style.color='black';
        }
    }

    function dignostico()
    {
        if(document.getElementById('FielDiagnostico').style.display=='none')
        {
            return false;
        }
        else{
            if(document.getElementById('txt_diagnostico').value=='Ingrese Diagnostico' || document.getElementById('txt_diagnostico').value=='')
            {
                alert('Debe Ingresar la Descripción del Diagnóstico');
                document.getElementById('txt_diagnostico').focus();
                return false;
            }
        }
        return true;
    }
</script>
<script  type="text/javascript">
    function GuardaDiag()
    {
        var id_das=document.getElementById('txt_das').value;
        var diagnostico=document.getElementById('txt_diagnostico').value;
        var tipo_diagnostico=document.getElementById('txt_tipo_diagnostico').value;


        //alert('id_duo='+id_duo+'--diagnostico='+diagnostico+'--tipo='+tipo_diagnostico_duo);
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&diagnostico='+diagnostico+'&tipo_diagnostico='+tipo_diagnostico+'&txt_modo=1', true);
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
                document.getElementById('txt_diagnostico').value='';
            }
        }
    }
    function EliminaDiag(id)
    {
        var id_das=document.getElementById('txt_das').value;
        var diagnostico=document.getElementById('txt_diagnostico').value;
        var tipo_diagnostico=document.getElementById('txt_tipo_diagnostico').value;


        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&diagnostico='+diagnostico+'&tipo_diagnostico='+tipo_diagnostico+'&id_diagnostico_das='+id+'&txt_modo=2', true);
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
                document.getElementById('txt_diagnostico').value=='';
            }
        }
    }

    function MuestraDiag(){
        var id_das=document.getElementById('txt_das').value;
        var tipo_diagnostico=document.getElementById('txt_tipo_diagnostico').value;
    
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&tipo_diagnostico='+tipo_diagnostico+'&txt_modo=3', true);
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
            }
        }   
    }


  


    function ingresaExamen(id_examen,nombre_examen){
        var id_das=document.getElementById('txt_das').value;
       /* 15-07-2013 */
       /*
        if(confirm("CONFIRMACION ! Esta Seguro que desea ingresar "+nombre_examen+" a este paciente? \n \n ")) {
        }else {
            return false;
        }
        */
        /* 15-07-2013 */
       
       
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&id_examen='+id_examen+'&txt_modo=31', true);
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
                document.getElementById('txa_observacion').value='';
            }
        }
  
    }

    function eliminaExamen(id_examen,nombre_examen){

        var id_das=document.getElementById('txt_das').value;

        if(confirm("CONFIRMACION ! Esta Seguro que desea eliminar "+nombre_examen+" a este paciente? \n \n ")) {
        }else {
            return false;
        }

        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&id_examen='+id_examen+'&txt_modo=32', true);
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
                document.getElementById('txa_observacion').value='';
            }
        }

    }



    function GuardaObs()
    {
        if(confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Observación? \n \n ")) {
        }else {
            return false;
        }
        
        var id_das=document.getElementById('txt_das').value;
        var diagnostico=document.getElementById('txa_observacion').value;

        if (document.getElementById('chk_radio').checked){
            var radio=1;
        }else{
            var radio=0;
        }

        if (document.getElementById('chk_laboratorio').checked){
            var laboratorio=1;
        }else{
            var laboratorio=0;
        }
   

        //alert('id_duo='+id_duo+'--diagnostico='+diagnostico+'--tipo='+tipo_diagnostico_duo);
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&diagnostico='+diagnostico+'&radio='+radio+'&laboratorio='+laboratorio+'&txt_modo=11', true);
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
                document.getElementById('txa_observacion').value='';

                document.getElementById('chk_radio').checked=false;
                document.getElementById('chk_laboratorio').checked=false;
          
            }
        }
    }
        
    function EliminaObs(id)
    {
        var id_das=document.getElementById('txt_das').value;
        var observacion=document.getElementById('txa_observacion').value;
    
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&diagnostico='+observacion+'&id_diagnostico_das='+id+'&txt_modo=12', true);
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
                document.getElementById('txa_observacion').value='';
            }
        }
    }

    function valida_fecha(fecha){

        //valida fecha en formato aaaa-mm-dd
        var fechaArr = fecha.split('-');
        var aho = fechaArr[0];
        var mes = fechaArr[1];
        var dia = fechaArr[2];

        var plantilla = new Date(aho, mes - 1, dia);//mes empieza de cero Enero = 0

        if(!plantilla || plantilla.getFullYear() == aho && plantilla.getMonth() == mes -1 && plantilla.getDate() == dia){
            // alert('valida');
            //  return true;
        }else{
            alert('El formato de Fecha no es correcto [dd-mm-aaaa]');
            return false;
        }
    }

    function GuardaContacto(){
        /**/
        if(confirm("CONFIRMACION ! Esta Seguro que desea ingresar este registro? \n \n ")) {
        }else {
            return false;
        }

        var id_das=document.getElementById('txt_das').value;
        var nombre=document.getElementById('txt_nombre').value;
        var observaciones =document.getElementById('txt_observaciones').value;
        var fecha=document.getElementById('txt_fecha').value;
        var hora=document.getElementById('cbo_hora').value;
        var minuto =document.getElementById('cbo_minuto').value;
        
        var dd=fecha.substring(0, 2);
        var mm=fecha.substring(3, 5);
        var aa=fecha.substring(6, 10);
        var fecha_amd=aa+"-"+mm+"-"+dd;
        var fecha_mda=mm+"-"+dd+"-"+aa;
        
        valida_fecha(fecha_amd);

        if (nombre.length==0){
            if(confirm("CONFIRMACION ! No ha ingresado nombre de Familiar. Desea continuar? \n \n ")) {
            }else {
                return false;
            }
        }
        if (observaciones.length==0){
            if(confirm("CONFIRMACION ! No ha ingresado Observaciones. Desea continuar? \n \n ")) {
            }else {
                return false;
            }
        }
    


 
        var ajax1=nuevoAjax();
        ajax1.open('POST', '<%=neg.getLocal()%>ingreso_diagnostico_suam?id_das='+id_das+'&nombre='+nombre+'&observaciones='+observaciones+'&fecha='+fecha_mda+'&hora='+hora+'&minuto='+minuto+'&txt_modo=21', true);
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
                document.getElementById('txa_observaciones').value='';

            }
        }
        /**/
    }



</script>
<script type="text/javascript">
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


    function valida_indicacion(){

        if (document.getElementById('cbo_destino').value==-1){
            alert('Debe seleccionar alguno de los destinos');
            document.getElementById('cbo_destino').focus();
            return false;
        }

        if(confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Indicación de Alta (Hasta el egreso final)? \n \n ")) {
             
        }else {
            return false;
        }
    }

        

    MuestraDiag();
</script>
<jsp:include page="../css/boton_html.jsp" />
<br>


<fieldset><legend>Datos del Paciente</legend>

    <table style=" width: 750px " >
        <tr>
            <td>
                <table border="1" >
                    <tbody>
                        <tr>
                            <td <%=titulo%> >Rut Paciente</td>
                            <td colspan="2" <%=datos%> ><% out.write("" + das.getRut_paciente());%></td>

                            <td <%=titulo%> >Fecha Nacimiento</td>
                            <td colspan="2" <%=datos%> >   <% out.write("" + das.getFecha_nac());%> </td>
                        </tr>
                        <tr>
                            <td <%=titulo%> >Nombre Completo</td>
                            <td colspan="3" <%=datos%> ><% out.write("" + das.getNombres_paciente() + " " + das.getApellidop_paciente() + " " + das.getApellidom_paciente());%></td>

                            <td <%=titulo%> >Camilla</td>
                            <td <%=datos%> ><% out.write("" + das.getCamilla_descri());%></td>
                        </tr>
                    </tbody>
                </table>
            </td>
            <td align="left" valign="top" >
                <div style=" vertical-align: top  " align="right" >
                    <a href="../uh_visita_suam.jsp">
                        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
                    </a>
                </div>
            </td>
        </tr>
    </table>


</fieldset>

<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Diagnosticos</a></li>
        <li ><a href="#tabs-2" >Examenes</a></li>
        <li><a href="#tabs-3">Evolución</a></li>
        <li><a href="#tabs-4">Indicacion Egreso</a></li>
        <li><a href="#tabs-5">Contacto</a></li>

    </ul>
    <div id="tabs-1" <%=div_style%> >
        <fieldset id="FielDiagnostico" style="">
            <legend>Diagnóstico(s) de Ingreso:</legend>
            <table>
                <input type="hidden" name="txt_das" id="txt_das" value="<% out.write("" + numero_das);%>">
                <input type="hidden" id="txt_tipo_diagnostico" value="1">
                <tr>
                    <td>
                        <input type="text" size="55" id="txt_diagnostico" style="text-transform:capitalize; color: gray " onblur="onBlurDiag();" onfocus="onFocusDiag();" onkeypress="return valida_enter_txt_diagnostico(event)"  value="Ingrese Diagnostico">
                    </td>
                    <td>
                        <input class="btn btn-primary" type="button" onclick="if(dignostico()){GuardaDiag();}" value="Guardar">
                    </td>
                </tr>
            </table>
            <div id="" style="border:1px">
            </div>
        </fieldset>

    </div>

    <div id="tabs-2" <%=div_style%> >
        <fieldset id="FielDiagnostico1" style="">
            <legend>Exámenes y Radiografías:</legend>
            <div id="MM" style=" overflow:auto;
                 padding-right: 1px; padding-top: 1px; padding-left: 1px; padding-bottom: 1px;
                 border-right: #6699CC 1px solid; border-top: #999999 1px solid;
                 border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
                 scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
                 scrollbar-track-color :#3333333 ;
                 height:215px; left: 90; top: 15; width: 99%">
                <table>
                    <tr>
                        <th>
                            <p> Exámenes</p>
                        </th>
                        <th>
                            <p> Radiografías</p>
                        </th>
                    </tr>
                    <tr >
                        <td id="exam" valign="TOP" >
                            <table border="3" >
                                <%
                                            int contador = 0;
                                            int resto = 0;
                                            while (it_exa.hasNext()) {
                                                cExamen exa = (cExamen) it_exa.next();
                                                if (exa.getTipo() == 1) {
                                                    resto = contador % 2;
                                                    if (contador == 0 || resto == 0) {
                                                        out.write("<tr>");
                                                    }
                                                    out.write("<td> <input type='checkbox' name='cbo_examen' value='" + exa.getId_examen() + "' onclick=\"ingresaExamen(" + exa.getId_examen() + ",'" + exa.getDescripcion() + "')\"  /> "
                                                            + "<font style=\"font-size:xx-small;\">" + exa.getDescripcion() + "</font> </td>");
                                                    contador++;
                                                }
                                            }
                                %>
                            </table>
                        </td>
                        <td id="rad" valign="TOP"   >
                            <table border="3" >
                                <%
                                            contador = 0;
                                            resto = 0;
                                            while (it_rad.hasNext()) {
                                                cExamen exa = (cExamen) it_rad.next();
                                                if (exa.getTipo() == 2) {
                                                    resto = contador % 2;
                                                    if (contador == 0 || resto == 0) {
                                                        out.write("<tr>");
                                                    }
                                                    out.write("<td> <input type='checkbox' name='cbo_examen' value='" + exa.getId_examen() + "' onclick=\"ingresaExamen(" + exa.getId_examen() + ",'" + exa.getDescripcion() + "')\"  /> "
                                                            + "<font style=\"font-size:xx-small;\">" + exa.getDescripcion() + "</font> </td>");
                                                    contador++;
                                                }
                                            }
                                %>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>


    <div id="tabs-3" <%=div_style%> >

        <fieldset id="FielDiagnostico1" style="">
            <legend>Evolución:</legend>
            <table>
                <input type="hidden" id="txt_tipo_diagnostico" value="1">

                <tr>
                    <td colspan="2" valign="top"  >
                        Indicación:
                        <br>
                        <textarea name="txa_observacion" id="txa_observacion" rows="5" cols="50"></textarea>
                    </td>
                    <td valign="" align="center" >
                        <input class="btn btn-primary" type="button" onclick="GuardaObs()" value="Guardar">
                    </td>
                </tr>
                <tr>

                </tr>
                <tr>
                    <td>
                        Espera Radiografia <input type="checkbox" name="chk_radio" id="chk_radio" value="ON" onclick="seleccionador(1)"   />
                    </td>
                    <td>
                        Espera Exámen <input type="checkbox" name="chk_laboratorio" id="chk_laboratorio" value="ON"  />
                    </td>
                </tr>
            </table>
            <br>
        </fieldset>

    </div>
    <div id="tabs-4" <%=div_style%> >
        <fieldset id="FielDiagnostico2" style="">
            <legend>Indicación Egreso:</legend>
            <% if (sw_tiene_indicacion_egreso == true) {
                            out.write("<h3>Este Paciente tiene indicación de egreso para <b>" + indicacion_egreso + "</b></h3>");
                        }
            %>
            <form name="form_indicacion" id="form_indicacion" action="<%=neg.getLocal() + "modifico_suam"%>" method="POST" onsubmit="return valida_indicacion()" >
                <input type="hidden" name="txt_das"  value="<% out.write("" + numero_das);%>">
                <input type="hidden" name="txt_modo" value="<% out.write("3");%>">
                <table>
                    <tr>
                        <td>

                            <select name="cbo_destino" id="cbo_destino" style=" font-size: medium;width:170px   "  >
                                <option value="-1" >Seleccione...</option>
                                <%
                                            while (it_des.hasNext()) {
                                                cConsultorio con = (cConsultorio) it_des.next();
                                                if (con.getIndicacionEgreso() == 1) {
                                                    out.write(" <option value='" + con.getId() + "' >" + con.getDescripcion() + "</option>");
                                                }
                                            }
                                %>
                            </select>
                        </td>
                        <td>
                            <input class="btn btn-primary" type="submit"  value="Guardar">
                        </td>

                    </tr>
                </table>
            </form>


        </fieldset>

    </div>
    <div id="tabs-5" <%=div_style%> >
        <fieldset id="FielDiagnostico1" style="">
            <legend>Contacto con Familiar:</legend>
            <table>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="txt_nombre" id="txt_nombre" size="40"></td>
                </tr>
                <tr>
                    <td>Fecha:  </td>
                    <td>
                        <input type="text" name="txt_fecha" id="txt_fecha" size="20" value="<% out.write("" + fecha_hoy);%>" >
                    </td>
                </tr>
                <tr>
                    <td>Hora:</td>
                    <td>
                        <select name="cbo_hora" id="cbo_hora" >
                            <%
                                        for (int j = 0; j < 24; j++) {
                                            String hora = "" + j;
                                            if (j < 10) {
                                                hora = "0" + j;
                                            }
                                            out.write("<option value='" + hora + "' >" + hora + "</option>");

                                        }

                            %>
                        </select>
                        :
                        <select name="cbo_minuto" id="cbo_minuto" >
                            <%
                                        for (int j = 0; j < 60; j++) {
                                            String minuto = "" + j;
                                            if (j < 10) {
                                                minuto = "0" + j;
                                            }
                                            out.write("<option value='" + minuto + "' >" + minuto + "</option>");
                                        }

                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Observación:
                    </td>
                    <td>
                        <input type="text" name="txt_observaciones" id="txt_observaciones" size="40">
                    </td>
                </tr>
                <tr>
                    <td colspan="2"  >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;

                        <input class="btn btn-primary" type="button" onclick="GuardaContacto()" value="Guardar">
                    </td>
                </tr>
            </table>

            <br>
        </fieldset>

    </div>


</div>



<div id="cargandoD" ></div>
<fieldset id="FielDiagnostico3" style="">
    <legend>Registros</legend>
    <div id="DivMuestraDiag" style=" overflow:auto;
         padding-right: 1px; padding-top: 1px; padding-left: 1px; padding-bottom: 1px;
         border-right: #6699CC 1px solid; border-top: #999999 1px solid;
         border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
         scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
         scrollbar-track-color :#3333333 ;
         height:180px; left: 90; top: 15; width: 99%">
        <!-- Este DIV contendra la respuesta enviada por el Servlet -->
    </div>
</fieldset>

