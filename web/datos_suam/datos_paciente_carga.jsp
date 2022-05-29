<%-- 
    Document   : datos_paciente_carga
    Created on : 17-may-2012, 12:32:48
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cDau"%>
<%@page import="CapaDato.cDas"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>

<%
            HttpSession session1 = request.getSession();
            NegocioQ neg = new NegocioQ();


            String titulo = " style=' background-color: #f7903b ; color: white '  ";
            String datos = " style=' background-color: #fcd5b6 ; color: black '  ";
            // String datos = "  style=' color: #000080 '  ";
            int obtiene_perfil = 0;
            String tipo_perfil = "" + session.getAttribute("usuario_perfil_descripcion");
            try {
                obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
            } catch (NumberFormatException ex) {
                obtiene_perfil = -1;
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



%>

<jsp:include page="../css/boton_html.jsp" />
<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita_suam2.jsp">
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
                <td colspan="3" <%=datos%> ><% out.write("" + das.getNombres_paciente() + " " + das.getApellidop_paciente() + " " + das.getApellidom_paciente());%></td>

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
    <legend>Datos Clínicos</legend>
    <table border="1">
        <tbody>
            <tr>
                <td <%=titulo%> >ID DATO SUAM</td>
                <td colspan="3" <%=datos%> ><% out.write("" + das.getId_das());%></td>
                <td <%=titulo%> >CAMILLA ACTUAL</td>
                <td <%=datos%> ><% out.write("" + das.getCamilla_descri());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >N° DAU</td>
                <td colspan="3" <%=datos%> ><% out.write("" + das.getDau_id());%></td>
                <td <%=titulo%> >N° DAU NN</td>
                <td <%=datos%> ><% out.write("" + das.getDau_nn_id());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >FECHA INGRESO</td>
                <td colspan="3" <%=datos%> ><% out.write("" + das.getFecha_ingreso());%></td>
                <td <%=titulo%> >---</td>
                <td <%=datos%> ><% out.write("");%></td>
            </tr>
        </tbody>
    </table>
</fieldset>


<fieldset>
    <legend>Ingresos</legend>
    <center>
        <table border="0" cellspacing="10" >
            <tbody>
                <tr>
                    <td>
                        <form name="form4" action="../ingreso_suam/ingreso_diagnostico.jsp" method="POST" >
                            <input type="hidden" name="txt_manda_das" value="<%=das.getId_das()%>"  >
                            <input type="hidden"name="txt_modo" value="1"  >
                            <input  class="btn btn-primary" type="submit" value="Registro Evolución Paciente" name="btn_4" />
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    </center>
</fieldset>

<fieldset>
    <legend>Movimientos</legend>
    <center>
        <table border="0" cellspacing="10" >
            <tbody>
                <tr>
                    <td>
                        <form name="form1" action="../ingreso_suam/cambio_cama.jsp" method="POST" >
                            <input type="hidden" name="txt_manda_das" value="<%=das.getId_das()%>"  >
                            <input type="hidden"name="txt_modo" value="1"  >
                            <input class="btn btn-primary" type="submit" value="Cambiar a Camilla Disponible" name="btn_1" />
                        </form>
                    </td>
                    <td>
                        <form name="form2" action="../ingreso_suam/cambio_cama.jsp" method="POST" >
                            <input type="hidden" name="txt_manda_das" value="<%=das.getId_das()%>"  >
                            <input type="hidden"name="txt_modo" value="2"  >
                            <input class="btn btn-primary" type="submit" value="Cambiar Entre Camillas" name="btn_2" />
                        </form>
                    </td>
                    <td>
                        <form name="form3" action="../egreso_suam/alta_medica_suam.jsp" method="POST" >
                            <input type="hidden" name="txt_manda_das" value="<%=das.getId_das()%>"  >
                            <input class="btn btn-primary" type="submit" value="Dar Paciente de Alta" name="btn_3" />
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    </center>
</fieldset>

