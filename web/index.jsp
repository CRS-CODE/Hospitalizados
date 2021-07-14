<%@page import="CapaNegocio.Negocio"%>
<%@ page
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%
                Negocio neg = new Negocio();
    %>

    <html><head>
            <LINK REL="SHORTCUT ICON" HREF="Iconos/flag_chile.ico">

            <title>Entrada al sistema - Modulo UHD.</title>
            <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css"></head>
        <script type="text/javascript" src="js/valida_rut.js"></script>
        <body onload="javascript:document.index.user.focus();">
            <div><img src="Imagenes/camas.png"></div>
            <center>
                <table width="70%"><tr><td>
                            <div align="center" id="formArea">
                                <form action="<% out.print(neg.getLocal());%>valida_usuario" method="post"  name="index" onsubmit="return validaRut(document.index.user.value);">
                                    <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                                    <input value="d" name="txtDV" id="txtDV" type="hidden">
                                    <table width="50%">
                                        <tr><td>
                                                <fieldset>
                                                    <legend onclick="">Entrada al sistema UHD</legend>
                                                    <div align="center">
                                                        <table border="0" CELLSPACING="5" CELLPADDING="0">
                                                            <tr><td colspan="2" align="center"  class="ari999bold11">Ingrese Rut de usuario y Contraseña.</td></tr>
                                                            <tr><td colspan="2">&nbsp;</td></tr>
                                                            <tr><td>Rut de usuario:</td><td align="center"><input style="text-transform: uppercase" id="user" name="user" class="USER" type="text" size="30"  onkeyup="formateaRut(this.value)" autocomplete="off" maxlength="12" ></td></tr>
                                                            <tr><td>Contraseña:</td><td align="center"><input name="pass" id="pass" type="password" class="PASS" size="30" ></td></tr>
                                                            <tr>
                                                                <td align="center" colspan="2">
                                                                    <fieldset class="buttons">
                                                                        <input type="reset" value="Borrar">
                                                                        <input type="submit" value="Aceptar">
                                                                    </fieldset>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" bgcolor="#666666">
                                                                    <center></center>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                        <%
                                                                    try {
                                                                        if (request.getParameter("timeout").equals("1")) {
                                                                            out.print("<h1 style=\"color:#FF0000;\" >Su sesión ha caducado <h1>");
                                                                            out.print("<h1 style=\"color:#FF0000;\">Ingrese Nuevamente <h1>");
                                                                        }
                                                                    } catch (Exception ex) {
                                                                    }

                                                                    try {
                                                                        if (request.getParameter("cambio_pass").equals("1")) {
                                                                            out.print("<h1 style=\"color:#0000CD;\" >Su Clave ha sido Modificada<h1>");
                                                                        }
                                                                    } catch (Exception ex) {
                                                                    }



                                                                    try {
                                                                        // NO VALIDO
                                                                        if (request.getParameter("nn").equals("1")) {
                                                                            out.print("<h1 style=\"color:#FF0000;\" > Datos Incorrectos <h1>");
                                                                        }
                                                                    } catch (Exception ex) {
                                                                    }

                                                        %>

                                                    </div>
                                                </fieldset>
                                            </td></tr>
                                    </table>
                                </form>

                            </div>
                            <div id="footer">Sistema de Gestión de Camas Domiciliaria Version 1.0 (25/06/2021)<img src="Iconos/Logo_uo.png">_____________________________________________________CRS Maipú 2021.</div>
                        </td></tr></table>
            </center>
        </body>


    </html>