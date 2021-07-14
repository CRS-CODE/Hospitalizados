<%-- 
    Document   : cambio_cama_carga
    Created on : 28-may-2012, 16:36:15
    Author     : EseGamboa
--%>

<%@page import="CapaDato.cCama"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaNegocio.Negocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
            String titulo = " style=' background-color: #4169E1 ; color: white '  ";
            String datos = " style=' background-color: #87CEFA ; color: black '  ";

            int recibe_duo = Integer.parseInt(request.getParameter("duo"));
            int recibe_modo = Integer.parseInt(request.getParameter("modo"));
            NegocioQ neg = new NegocioQ();
            cDuo duo = neg.obtiene_duo(recibe_duo);
            ArrayList lista_cama = null;
            Iterator it_sala = null;

            if (recibe_modo == 1) {
                lista_cama = neg.lista_cama_desocupada("1");
                it_sala = lista_cama.iterator();
            } else if (recibe_modo == 2) {
                lista_cama = neg.lista_grilla_camas();
                it_sala = lista_cama.iterator();
            }
%>

<html>
    <script type="text/javascript">
        function valida_cambio_cama(){
            if(document.getElementById('cbo_cama_seleccionada').value==-1)  {
                alert('Debe seleccionar una nueva cama para el paciente');
                return false;
            }
        }
    </script>
    <body>
        <div style=" vertical-align: top  " align="right" >
            <a href="../uh_visita.jsp">
                <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
            </a>
        </div>
        <fieldset>
            <legend>CAMBIO DE CAMA*</legend>
            <center>
                <form name="form_1" action="<%=neg.getLocal()%>modifico_uh" method="POST" onsubmit="valida_cambio_cama()" >
                    <input type="hidden" name="modo" value="1" >
                    <input type="hidden" name="txt_cama_actual" value="<%=duo.getCama()%>" >
                    <input type="hidden" name="txt_duo_actual" value="<%=duo.getId_duo()%>" >
                    <input type="hidden" name="txt_modo_cama" value="<%=recibe_modo%>" >
                    <table border="0" cellpadding="3">
                        <thead>
                            <tr>
                                <th <%=titulo%> >CAMA ACTUAL</th>
                                <th ></th>
                                <th <%=titulo%> >CAMA DISPONIBLE</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><b><% out.write("" + duo.getCama_descripcion() + "<br>" + duo.getNombres_paciente() + "<br>" + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></b></td>
                                <td></td>
                                <td>
                                    <% if (recibe_modo == 1) {%>
                                    <select name="cbo_cama_seleccionada" id="cbo_cama_seleccionada" >
                                        <option value="-1" >Seleccione cama...</option>
                                        <%
                                             while (it_sala.hasNext()) {
                                                 cCama aux = (cCama) it_sala.next();
                                                 out.write("<option value='" + aux.getId_cama() + "' >" + aux.getDescripcion_cama() + "::[Sala " + aux.getDescripcion_sala() + "]</option>");
                                             }
                                        %>
                                    </select>
                                    <% } else {%>

                                    <select name="cbo_cama_seleccionada" id="cbo_cama_seleccionada" >
                                        <option value="-1" >Seleccione cama...</option>
                                        <%
                                             while (it_sala.hasNext()) {
                                                 cDuo aux = (cDuo) it_sala.next();
                                                 if (aux.getId_duo() != 0 && aux.getId_duo() != recibe_duo) {
                                                     out.write("<option value='" + aux.getId_duo() + "' >" + aux.getCama_descripcion() + "::" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + "</option>");
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
                                    <input type="submit" value="REALIZAR CAMBIOS" style="font-size: medium; font-family:verdana;" name="btn_cambio_cama" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </center>
            <br><br>
            <div align="left" style=" vertical-align: bottom "  >
                <form name="form_back" id="form_back" action="../datos/datos_paciente.jsp" method="POST">
                    <input type="hidden" name="txt_manda_duo" value="<%=recibe_duo%>" />
                    &nbsp;&nbsp;
                    <a href="#" onclick="document.getElementById('form_back').submit()" >
                        <img src="../Imagenes/back.png" width="45" height="30" title="Volver"/>Volver
                    </a>
                    <br>
                </form>
            </div>
        </fieldset>
    </body>
</html>
