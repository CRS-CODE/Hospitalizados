<%-- 
    Document   : Password
    Created on : 20-nov-2011, 21:02:11
    Author     : Dis
--%>

<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaNegocio.Negocio"%>
<%
            HttpSession session1 = request.getSession();
            if (session1.getAttribute("usuario_rut") == null) {
                out.write("<b>Su sesion ha caducado, cierre esta ventana y vuelva a entrar</b>");
            } else {
                Negocio neg = new Negocio();
                String rut = session1.getAttribute("usuario_rut").toString();
                cUsuario aux = neg.valida_Usuario_sinPass(rut);
                String pass = aux.getPass_usuario();

                String cuatro = rut.replace(".", "");
                cuatro = cuatro.substring(0, 4);
%>

<html><head><title>Modificar Contraseña.</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" >
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css">
    </head>

    <script>
        function verifica()
        {
            if (document.form1.pass_anterior.value!=document.form1.pass.value){
                alert('Su contraseña actual no corresponde a la digitada');
                document.form1.pass.value="";
                document.form1.pass.focus();
                return false;
            }else if (document.form1.cuatro.value==document.form1.txt_pass_nueva.value){
                alert("Su contraseña no puede coincidir con los primeros cuatro dígitos de su Rut");
                document.form1.txt_pass_nueva.value="";
                document.form1.txt_pass_nueva.focus();
                return false;
            }
            else if(document.form1.txt_pass_nueva.value.length<4)
            {alert("Su contraseña debe ser igual o superior a cuatro caracteres")
                document.form1.txt_pass_nueva.select();
                document.form1.txt_pass_nueva.focus();
                document.form1.repit_pass_new.value="";
                return false;
            }else if(document.form1.txt_pass_nueva.value!=document.form1.repit_pass_new.value)
            {alert('Error en la confirmación de su nueva contraseña');
                document.form1.repit_pass_new.value="";
                document.form1.repit_pass_new.focus();
                return false;
            }
        }
    </script>
    <body onload="javascript:document.form1.pass.focus();">
        <jsp:include page="css/boton_html.jsp" />
        <center>
            <table width="70%"><tr><td>
                        <div align="center" id="formArea">
                            <form action="<%out.write(neg.getLocal());%>modifico_uh" method="post" name="form1">
                                <input type="hidden" name="modo"  id="modo" value="98" />
                                <input type="hidden" name="misma_ventana"  id="misma_ventana" value="1" />
                                <input type="hidden" name="txt_usuario"  id="txt_usuario" value="<%=aux.getRut_usuario()%>" />
                                <input type="hidden" name="pass_anterior" value="<%=pass%>" />
                                <input type="hidden" name="cuatro" value="<%=cuatro%>" />
                                <table width="50%">
                                    <tr><td>
                                            <fieldset>
                                                <legend>Cambio de Contraseña</legend>
                                                <div align="center">
                                                    <table border="0" CELLSPACING="5" CELLPADDING="0">
                                                        <tr><td colspan="2">&nbsp;</td></tr>
                                                        <tr><td>Contraseña Actual:</td><td><input name="pass"  type="password" class="key" size="20"></td></tr>
                                                        <tr><td>Nueva Contraseña:</td><td><input name="txt_pass_nueva" type="password" class="PASS" size="25"></td></tr>
                                                        <tr><td>Confirmar Contraseña:</td><td><input name="repit_pass_new" type="password" class="PASS" size="25"></td></tr>
                                                        <tr>
                                                            <td align="center" colspan="2">
                                                                <fieldset class="buttons">
                                                                    <input  class="btn btn-primary" type="button" class="CERRAR" value="Cerrar" onclick=" window.close();">
                                                                    <input  class="btn btn-primary" type="reset" value="Borrar">
                                                                    <input  class="btn btn-primary" type="submit" value="Aceptar" onclick="return verifica();">
                                                                </fieldset>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </fieldset>
                                        </td></tr>
                                </table>
                            </form>
                        </div>
                        <div id="footer">Centro de Referencia de Salud Maipú - 2012</div>
                    </td></tr></table>
        </center>
    </body></html>

<% }%>

