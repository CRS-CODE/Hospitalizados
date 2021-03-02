<%-- 
    Document   : alta_medica_suam_carga
    Created on : 29-ago-2012, 10:07:44
    Author     : EseGamboa
--%>


<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaDato.cDau"%>
<%@page import="CapaDato.cDas"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>

<%

            HttpSession session1 = request.getSession();
            NegocioQ neg = new NegocioQ();

            String titulo = " style=' background-color: #4169E1 ; color: white '  ";
            String datos = " style=' background-color: #87CEFA ; color: black '  ";
            // String datos = "  style=' color: #000080 '  ";
            int id_perfil = 0;
            String tipo_perfil = "" + session.getAttribute("usuario_perfil_descripcion");
            try {
                id_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
            } catch (NumberFormatException ex) {
                id_perfil = -1;
            }

            int numero_das = Integer.parseInt(request.getParameter("das").toString().trim());

            cDas das = neg.obtiene_das(numero_das);

              /*si el paciente es NN */
            if (das.getDau_id() == 0) {
                cDau busca_nn = neg.obtiene_paciente_segun_dauNN(das.getDau_nn_id());
                das.setRut_paciente("NN");
                das.setNombres_paciente(busca_nn.getNombres_paciente());
                das.setApellidop_paciente(busca_nn.getApellidop_paciente());
                das.setApellidom_paciente(busca_nn.getApellidom_paciente());
                das.setSexo(busca_nn.getSexo());
                das.setSexo_descri(busca_nn.getSexo_descri());
                das.setTelefono1(busca_nn.getTelefono1());

                das.setTelefono2(busca_nn.getTelefono2());
                das.setDireccion(busca_nn.getDireccion());
                das.setComuna_descri(busca_nn.getComuna_descri());
                das.setFecha_nac(busca_nn.getFecha_nac());
                das.setEdad(busca_nn.getEdad());
            }
            /*si el paciente es NN */

            // out.write(request.getParameter("das"));
            ArrayList lista_destino = neg.lista_destino_suam();
            Iterator it_des = lista_destino.iterator();

            ArrayList medicos = neg.lista_medicos_suam();
            Iterator it_med = medicos.iterator();


%>
<script>


    function valida_egreso(){

          if (document.getElementById('cbo_medico').value==-1){
            alert('Debe seleccionar Medico que Otorga Alta')
            document.getElementById('cbo_medico').focus();
            return false;
        }

        if (document.getElementById('cbo_destino').value==-1){
            alert('Debe seleccionar destino')
            document.getElementById('cbo_destino').focus();
            return false;
        }

        if (document.getElementById('txa_observacion').value.trim()==''){
            if(confirm("CONFIRMACION ! No ingreso Ninguna Observación, Desea Continuar? \n \n ")) {
            }else {
                return false;
            }
        }

        if(confirm("CONFIRMACION ! Esta Seguro que desea ingresar este Egreso? \n \n ")) {
        }else {
            return false;
        }
    }

       
</script>

<jsp:include page="../css/boton_html.jsp" />
<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita_suam.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<fieldset><legend>Datos del Paciente</legend>
    <table border="1" >
        <tbody>
            <tr>
                <td <%=titulo%> >Rut Paciente</td>
                <td colspan="2" <%=datos%> ><% out.write("" + das.getRut_paciente());%></td>

                <td <%=titulo%> >Fecha Nacimiento</td>
                <td colspan="2" <%=datos%> >   <% out.write("" + das.getFecha_nac());%></td>

            </tr>
            <tr>
                <td <%=titulo%> >Nombre Completo</td>
                <td colspan="3" <%=datos%> ><% out.write("" + das.getNombres_paciente() + " " + das.getApellidop_medico() + " " + das.getApellidom_usuario());%></td>

                <td <%=titulo%> >Edad</td>
                <td <%=datos%> ><% out.write("" + das.getEdad());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Dirección</td>
                <td colspan="5" <%=datos%> ><% out.write("" + das.getDireccion() + " , " + das.getComuna_descri());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Teléfono</td>
                <td colspan="5" <%=datos%> ><% out.write("" + das.getTelefono1() + "/" + das.getTelefono2());%></td>
            </tr>
        </tbody>
    </table>
</fieldset>
<fieldset>
    <legend>EGRESO DE PACIENTE</legend>
    <form name="form1" action="<% out.write(neg.getLocal() + "modifico_suam");%>" method="POST" onsubmit="return valida_egreso()" >
        <input type="hidden" name="txt_das" value="<% out.write("" + numero_das);%>">
        <input type="hidden" name="txt_modo" value="<% out.write("1");%>">
        <table border="1" cellpadding="8" >
            
             <tr>
                <td <%=titulo%> >MEDICO QUE OTORGA ALTA</td>
                <td colspan="4"  >
                     <select name="cbo_medico" id="cbo_medico" style=" font-size:  medium   " >
                        <option value="-1" >Seleccione Médico...</option>
                        <%
                                        while (it_med.hasNext()) {
                                            cUsuario aux = (cUsuario) it_med.next();
                                            out.write(" <option value=" + aux.getRut_usuario() + " >" + aux.getApellidop_usuario() + " " + aux.getApellidom_usuario() + " " +aux.getNombre_usuario() + "</option>");
                                        }
                        %>

                    </select>
                </td>
            </tr>


            <tr>
                <td <%=titulo%> >DESTINO</td>
                <td colspan="4"  >
                    <select name="cbo_destino" id="cbo_destino" style=" font-size:  medium   "  >
                        <option value="-1" >Seleccione...</option>
                        <%
                                    while (it_des.hasNext()) {
                                        cConsultorio con = (cConsultorio) it_des.next();
                                        if(con.getAlta()==1)
                                        out.write(" <option value='" + con.getId() + "' >" + con.getDescripcion() + "</option>");
                                    }


                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td <%=titulo%> >DIAGNOSTICO(S) DE EGRESO</td>
                <td colspan="4" >
                    <textarea name="txa_observacion"  id="txa_observacion" rows="7" cols="80"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="5"  align="center" >
                    <br>
                    <input type="hidden" name="txt_manda_das" value="<%=das.getId_das()%>"  >
                    <input type="hidden"name="txt_modo" value="3"  >
                    <input class="btn btn-primary" type="submit" value="EGRESAR" name="btn_1" />
                </td>

            </tr>

        </table>
    </form>
</fieldset>



