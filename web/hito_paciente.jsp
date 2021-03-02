<%-- 
    Document   : hito_paciente
    Created on : 10-ago-2012, 9:40:58
    Author     : EseGamboa
--%>

<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaDato.cHito"%>
<%@page import="CapaDato.cHito"%>
<%@page import="CapaDato.cHito"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%

            HttpSession session1 = request.getSession();
            if (session1.getAttribute("usuario_rut") == null) {
                out.write("SIN SESION");
                response.sendRedirect("index.jsp?timeout=1");
            } else {
                NegocioQ neg = new NegocioQ();

                String rut = request.getParameter("rut");
                String usuario=session1.getAttribute("usuario_rut").toString();
              
                ArrayList nyu = neg.lista_hitos(rut);
                Iterator it = nyu.iterator();

                ArrayList lista_tipos = neg.lista_tipo_hito();
                Iterator it_lis = lista_tipos.iterator();

%>
<LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css">
<jsp:include page="css/boton_html.jsp" />
<link rel="stylesheet" href="css/themes/blue/style.css" type="text/css" media="print, projection, screen" />
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/jquery/jquery.tablesorter.js"></script>


<script type="text/javascript">
    $(function() {
        $("#tablesorter-demo").tablesorter({sortList:[[0,0],[2,1]], widgets: ['zebra']});
        $("#options").tablesorter({sortList: [[0,0]], headers: { 3:{sorter: false}, 4:{sorter: false}}});
    });
</script>
<script>
    function valida_hito(){

        if (document.getElementById('cbo_tipo').value==-1){
            alert('Debe seleccionar tipo de Hito');
            return false;
        }

        if(confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Información ? \n \n ")) {
        }else {
            return false;
        }

       return true;
    }
   
</script>

<body>
    <fieldset>
        <legend>HITOS DE PACIENTE DE LA UNIDAD DE OBSERVACIÓN <%=rut%></legend>
        <div align="right" ><input type="button" class="CERRAR" value="Cerrar" onclick=" window.close();"></div>

        <form name="form_ingresa" action="<%=neg.getLocal()%>ingreso_uh" method="POST" onsubmit="return valida_hito()" >
            <input type="hidden" name="modo" value="98" />
            <input type="hidden" name="txt_paciente" value="<%=rut%>" />
            <input type="hidden" name="txt_usuario" value="<%=usuario%>" />
            <table border="0" cellspacing="15" >
                <tbody>
                    <tr>
                        <td>Hito</td>
                        <td>
                            <textarea name="txa_hito" id="txa_hito" rows="6" cols="55"></textarea>
                        </td>
                        <td>
                            <select name="cbo_tipo" id="cbo_tipo" >
                                <option value="-1" >Seleccione...</option>
                                <%
                                                while (it_lis.hasNext()) {
                                                    cHito hit = (cHito) it_lis.next();
                                                    out.write("<option value='" + hit.getTipo() + "' >" + hit.getTipo_descripcion()+"("+hit.getTipo()+") " + "</option>");
                                                }
                                %>

                            </select>

                        </td>
                        <td align="center" >
                            <input class="btn btn-primary"  type="submit" value="Ingresar Hito" name="btn_hito" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
        
        <div id="Resultado" style=" overflow:auto;
             padding-right: 15px; padding-top: 15px; padding-left: 15px; padding-bottom: 15px;
             border-right: #6699CC 1px solid; border-top: #999999 1px solid;
             border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
             scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
             scrollbar-track-color :#3333333 ;
             height:200px; left: 100; top: 20; width: 95%">
            <%
                            if (nyu.size() == 0) {
                                out.write("<b>Este Rut no tiene Hitos de Unidad Observación</b>");
                            } else {
            %>
            <table id="tablesorter-demo" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
                <thead>
                    <tr>
                        <th>ID HITO &nbsp;</th>
                        <th>FECHA</th>
                        <th>TIPO</th>
                        <th>DETALLE</th>
                        <th>USUARIO</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                                                    while (it.hasNext()) {
                                                        out.write("<tr>");
                                                        cHito hhh = (cHito) it.next();
                                                        out.write("<td>" + hhh.getId() + "</td>");
                                                        out.write("<td>" + hhh.getFecha() + "</td>");
                                                        out.write("<td>" + hhh.getTipo_descripcion() + "</td>");
                                                        out.write("<td>" + hhh.getDetalle() + "</td>");
                                                        out.write("<td>" + hhh.getUsuario_nombre() + " " + hhh.getUsuario_apellidop() + "</td>");
                                                        out.write("</tr>");
                                                    }

                    %>
                </tbody>
            </table>
            <% }%>


            <!-- Este DIV contendra la respuesta enviada por el Servlet -->
        </div>
    </fieldset>
</body>

<% }%>