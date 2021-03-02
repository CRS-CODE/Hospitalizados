<%-- 
    Document   : alta_administrativa_carga
    Created on : 17-may-2012, 12:31:30
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="CapaDato.cEnfermedad"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
            Locale currentLocale = new Locale("es", "CL");
            java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale);
            java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);

            int id_duo = Integer.parseInt(request.getParameter("duo"));

            NegocioQ neg = new NegocioQ();
            ArrayList lista_enfermedad = neg.obtiene_duo_enfermedad(id_duo);
            ArrayList lista_destino = neg.lista_destino();

            Iterator it_enf = lista_enfermedad.iterator();
            Iterator it_des = lista_destino.iterator();


            cEnfermedad enf = new cEnfermedad();
            cConsultorio con = new cConsultorio(); // utilizado para setar valores del destino

            String Enfermedades_cronicas = " ";
            while (it_enf.hasNext()) {
                enf = (cEnfermedad) it_enf.next();
                Enfermedades_cronicas += " " + enf.getDescripcion() + " -";
            }
            Enfermedades_cronicas = Enfermedades_cronicas.substring(0, Enfermedades_cronicas.length() - 1);

            cDuo duo = neg.obtiene_duo(id_duo);
            int id_cama = duo.getCama();
            String fecha__hora_servidor = neg.obtiene_fecha_hora();
%>

<script>
    function Alta()
    {

        if(document.getElementById('fecha_epi').value=='')
        {alert('Debe Ingresar la Fecha y Hora,\npresionando el calendario al lado de la fecha');
            document.getElementById('fecha_epi').focus();
            return false;}
        if(document.getElementById('id_destino').value==0)
        {alert('Debe Ingresar el Destino');
            document.getElementById('id_destino').focus();
            return false;}
        if(document.getElementById('obs').value==0)
        {alert('Debe Ingresar Observaciones');
            document.getElementById('obs').focus();
            return false;}

        if(confirm('Esta Seguro de dar de ALTA a este Paciente?\nSi lo da de Alta Desocupara la Cama')){
            document.getElementById('BtnAlta').disabled=true;
            document.getElementById('imagenCargando').innerHTML='<img src=../Imagenes/loading.gif><b>Favor Espere Que el Proceso Termine!!</b>'
            ActualizoPrevision();
        }
        else{return false;}

    }


    function ActualizoPrevision()
    {
        var rut_paciente1=document.getElementById('rut_paciente1').value;
        var duo1=document.getElementById('id_duo').value;
        var ajax3=nuevoAjax();
        ajax3.open('POST', '<%=neg.getLocal()%>egreso/ActualizaPrevision.jsp?rut_paciente='+rut_paciente1+'&duo='+duo1, true);
        ajax3.send(null);
        if (ajax3.readyState==1)
        {
            document.getElementById('mensaje').innerHTML='Actualizando Prevision...';
        }
        ajax3.onreadystatechange=function()
        {
            if (ajax3.readyState==4 )
            {
                document.getElementById('mensaje').innerHTML=ajax3.responseText;
                GraboPrestacion();
            }
        }
    }

    function GraboPrestacion()
    {
        alert('Se Actualizo previsión del Paciente y se procederá a registrar Alta Médica <%=id_duo%> ');
        document.getElementById('Form1').submit();

        //document.getElementById('Form1').submit();
    }
</script>
<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<form id="Form1" action="<%=neg.getLocal()%>ingresa_alta">
     <input type="hidden" id="txt_rut_usuario" name="txt_rut_usuario" value="<% out.write(""+session.getAttribute("usuario_rut")); %>" >
    <input type="hidden" name="id_duo"  id="id_duo" value="<%=id_duo%>">
    <input type="hidden" name="txt_modo"  id="txt_modo" value="3"  >

    <body style="BACKGROUND-COLOR: #fff;">
        <div class="cargando" style="left: 80%; "  id="mensaje"></div>
        <center><fieldset>
                <legend>Dar al Paciente de Alta</legend>
                <big>Egreso Administrativo</big>

                <table><input type="hidden" id="dau" name="dau" value="DUO"  >
                    <tr><th class="Datos">Nombre:</th>
                        <td class="destacado" colspan="3"><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>
                    </tr>
                    <tr><th class="Datos">Rut:</th>
                        <td class="destacado">
                            <input type="hidden" id="rut_paciente1" name="rut_paciente1" value="<% out.write("" + duo.getRut_paciente());%>" ><% out.write("" + duo.getRut_paciente());%>

                        </td>
                        <th class="Datos">Edad</th>
                        <td class="destacado">
                            <% out.write("" + duo.getEdad());%>
                        </td>
                    </tr>
                    <tr><th class="Datos">Fecha Ingreso:</th><td class="destacado"> <% out.write("" + duo.getFecha_hora_ing_duo());%> <input type="hidden" id="fecha_duo" value="<% out.write("" + duo.getFecha_hora_ing_duo());%>"> </td>
                        <th class="Datos">Fecha Egreso:</th>
                        <td class="destacado"><input name="fecha_epi" id="fecha_epi" type="text" size="23" value="<% out.write("" + fecha__hora_servidor);%>" readonly="readonly"  >
                           <% // <img src="../Iconos/calendario.gif" id="fechita" style="cursor:pointer" onclick="" alt="" > %>
                        </td>
                    </tr>
                    <!--<tr><th class="Datos">Mensaje:</th><td colspan="3" class="destacado" id="mensaje" style="color:blue"></td></tr>-->
                    <tr><th class="Datos">Destino del Paciente</th>
                        <td><select name="id_destino" id="id_destino">
                                <option value="0">Seleccione...</option>
                                <%
                                            while (it_des.hasNext()) {
                                                con = (cConsultorio) it_des.next();
                                                out.write(" <option value='" + con.getId() + "' >" + con.getDescripcion() + "</option>");
                                            }
                                %>
                            </select>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <th class="Datos" colspan="1">Prestación Día Cama</th><th class="Datos">Código</th>
                        <th class="Datos">Cantidad</th>
                    </tr>
                    <tr>
                        <td class="Datos" width="450px">Dia cama hospitalizacion integral medicina, cirugia, pediatria, obstetricia-ginecologia y especialidades (sala 3 camas o mas) Hospitales tipo 1</td>
                        <td class="Datos">0203001<input type="hidden" id="prestacion1" value=0203001 name="prestacion1"></td>
                        <td class="Datos" style="font-size:18px" id="TDdias"><%  out.write("" + duo.getDias_cama() + 1);%><input type="hidden" id="cantidad" name="cantidad" value=1></td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <th class="Datos" colspan="2">Observaciones</th>
                    </tr>
                    <tr>
                        <td class="Datos" colspan="2"><textarea id="obs" name="obs" cols="77" rows="4"></textarea></td>
                    </tr>

                </table>
            </fieldset>  </center>
        <div id="imagenCargando">
        </div>
</form>
<fieldset class="BUTTONS">
    <input type="button" id="BtnAlta"  value="Dar de Alta" onclick="Alta()">
</fieldset>
