<%-- 
    Document   : calidad_pass_carga
    Created on : 02-abr-2012, 14:10:50
    Author     : usuario
--%>
<%@page import="CapaNegocio.Negocio"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, no-store, post-check=0, pre-check=0"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", -1);
%>

<%
            Negocio neg = new Negocio();
            String rut = request.getParameter("rep_rut");
            String clave = request.getParameter("rep_pass");
            String cuatro = rut.replace(".", "");
            cuatro = cuatro.substring(0, 4);
%>
<html>

<script>
    //alert('mensaje');
    document.getElementById('txt_pass_nueva').focus();

  </script>
    <body >
        <br>
        <br>
        <form name="form1" action="<%out.write(neg.getLocal());%>modifico_uh" onsubmit="return valida_clave();" method="post">
            <input type="hidden" name="modo"  id="modo" value="98" />
            <input type="hidden" name="txt_pass_actual"  id="txt_pass_actual" value="<%=clave%>" />
            <input type="hidden" name="txt_usuario"  id="txt_usuario" value="<%=rut%>" />
            <input type="hidden" name="txt_cuatro"  id="txt_cuatro" value="<%=cuatro%>" />
            <fieldset>
                <legend>MODIFICACIÓN DE CLAVE</legend>
                <% out.write("Bienvenido(a), si esta es su primera vez en este modulo UHCE deberá modificar su clave ");%>
                <% out.write("<br>Esta acción también es requerida si nunca ha modificado su clave ");%>
                <BR><BR>
                <CENTER>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td><b>Actual Clave</b></td>
                                <td><input type="password" name="txt_pass_vieja"  id="txt_pass_vieja" value="<%=clave%>" autocomplete="off" /></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><b>Nueva Clave</b></td>
                                <td><input type="password" name="txt_pass_nueva" id="txt_pass_nueva" value="" autocomplete="off" /></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><b>Confirmar Nueva Clave</b></td>
                                <td><input type="password" name="txt_pass_confirma" id="txt_pass_confirma"  value="" autocomplete="off" /></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><br></td>
                                <td></td>
                                <td>&nbsp;&nbsp;</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input type="submit" value="Continuar" name="btn_continuar" style=" width: 100px "  /></td>
                            </tr>

                             <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>  <input type="submit" value="Volver" name="btn_volver" onclick="history:back(1);return false;"  style=" width: 100px "   /></td>
                            </tr>

                        </tbody>
                    </table>
                </CENTER>

            </fieldset>
        </form>
    </body>
</html>