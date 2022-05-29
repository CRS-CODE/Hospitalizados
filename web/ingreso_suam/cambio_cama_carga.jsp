<%-- 
    Document   : cambio_cama_carga
    Created on : 28-may-2012, 16:36:15
    Author     : EseGamboa
--%>

<%@page import="CapaDato.cDas"%>
<%@page import="CapaDato.cCama"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaNegocio.Negocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../css/boton_html.jsp" />

<%
            String titulo = " style=' background-color: #f7903b ; color: white '  ";
            String datos = " style=' background-color: #fcd5b6 ; color: black '  ";

            int recibe_das = Integer.parseInt(request.getParameter("das"));
            int recibe_modo = Integer.parseInt(request.getParameter("modo"));
            NegocioQ neg = new NegocioQ();




            ArrayList lista_cama = neg.lista_camillas_actuales();
            Iterator it_sala = lista_cama.iterator();
            Iterator it_sala_busca_paciente = lista_cama.iterator();

            cDas paciente = new cDas(); // esta clase la ocupo para mostrar los datos de camillas actualez

            while (it_sala_busca_paciente.hasNext()) {
                cDas aux = (cDas) it_sala_busca_paciente.next();
                if (aux.getId_das() == recibe_das) {
                    paciente.setApellidom_paciente(aux.getApellidom_paciente());
                    paciente.setApellidop_paciente(aux.getApellidop_paciente());
                    paciente.setNombres_paciente(aux.getNombres_paciente());
                    paciente.setCamilla(aux.getCamilla());
                    paciente.setCamilla_descri(aux.getCamilla_descri());
                    paciente.setSala(aux.getSala());
                    paciente.setSala_descri(aux.getSala_descri());
                    paciente.setId_das(aux.getId_das());
                }
            }




%>

<html>
    <script type="text/javascript">
        function valida_cambio_cama(){
            if(document.getElementById('cbo_camilla_seleccionada').value==-1)  {
                alert('Debe seleccionar una nueva cama para el paciente');
                return false;
            }
        }
    </script>
    <body>
        <div style=" vertical-align: top  " align="right" >
            <a href="../uh_visita_suam.jsp">
                <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
            </a>
        </div>
        <fieldset>
            <legend>CAMBIO DE CAMILLA</legend>
            <center>
                <form name="form_1" action="<%=neg.getLocal()%>modifico_suam" method="POST" onsubmit="valida_cambio_cama()" >
                    <input type="hidden" name="txt_modo" value="2" >
                    <input type="hidden" name="txt_camilla_actual" value="<% out.write("" + paciente.getCamilla());%>" >
                    <input type="hidden" name="txt_das_actual" value="<% out.write("" + paciente.getId_das());%>" >
                    <input type="hidden" name="txt_modo_camilla" value="<%=""+recibe_modo%>" >
                    <table border="0" cellpadding="3">
                        <thead>
                            <tr>
                                <th <%=titulo%> >CAMILLA ACTUAL</th>
                                <th ></th>
                                <th <%=titulo%> >CAMILLA DISPONIBLE</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><b><% out.write("" + paciente.getCamilla_descri() + "<br>" + paciente.getNombres_paciente() + "<br>" + paciente.getApellidop_paciente() + " " + paciente.getApellidom_paciente());%></b></td>
                                <td></td>
                                <td>
                                    <% if (recibe_modo == 1) {%>
                                    <select name="cbo_camilla_seleccionada" id="cbo_camilla_seleccionada" >
                                        <option value="-1" >Seleccione camilla...</option>
                                        <%
                                             while (it_sala.hasNext()) {
                                                 cDas aux = (cDas) it_sala.next();
                                                 if (aux.getId_das() == 0) {
                                                     out.write("<option value='" + aux.getCamilla() + "' >" + aux.getCamilla_descri() + "::[Sala " + aux.getSala_descri()+ "]</option>");
                                                 }
                                             }
                                        %>
                                    </select>
                                    <% } else {%>

                                    <select name="cbo_camilla_seleccionada" id="cbo_camilla_seleccionada" >
                                        <option value="-1" >Seleccione camilla...</option>
                                        <%
                                             while (it_sala.hasNext()) {
                                                 cDas aux = (cDas) it_sala.next();
                                                 if (aux.getId_das() != 0 && aux.getId_das() != recibe_das) {
                                                     out.write("<option value='" + aux.getId_das() + "' >" + aux.getCamilla_descri() + "::" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + "</option>");
                                                 }
                                             }
                                        %>
                                    </select>
                                    <% }%>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center" >
                                    <br><br><br><br>
                                    <input class="btn btn-primary" type="submit" value="REALIZAR CAMBIOS" style="font-size: medium; font-family:verdana;" name="btn_cambio_cama" />
                                </td>
                            </tr>


                        </tbody>
                    </table>
                </form>
            </center>
            <br><br>
            <div align="left" style=" vertical-align: bottom "  >
                <form name="form_back" id="form_back" action="../datos_suam/datos_paciente.jsp" method="POST">
                    <input type="hidden" name="txt_manda_das" value="<%=recibe_das%>" />
                    &nbsp;&nbsp;
                    <a href="#" onclick="document.getElementById('form_back').submit()" >
                        <img src="../Imagenes/back.png" width="45" height="30" alt="Volver"/>
                    </a>
                    <br>
                </form>
            </div>
        </fieldset>
    </body>
</html>
