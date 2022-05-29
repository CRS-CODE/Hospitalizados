<%@page import="CapaNegocio.Negocio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    Negocio neg = new Negocio();
%>
<html lang="en">
    <head>

        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Inicio Sesion</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="public/css/bootstrap.min.css" rel="stylesheet">

        <link href="public/css/styles.css" rel="stylesheet">
        <link rel="shortcut icon" href="imagenes/icon.png">
        <script type="text/javascript" src="js/valida_rut.js"></script>
    </head>
    <body onload="javascript:document.index.user.focus();">


        <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="text-center"><img src="public/imagenes/logo.png" WIDTH=58 HEIGHT=52></h1>
                    </div>
                    <div class="modal-body">

                        <form class="form col-md-12 center-block" action="<% out.print(neg.getLocal());%>valida_usuario" method="post"  name="index" onsubmit="return validaRut(document.index.user.value);">
                            <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                            <input value="d" name="txtDV" id="txtDV" type="hidden">

                            <div class="form-group">
                                <input style="text-transform: uppercase" id="user" name="user" class="form-control input-lg" placeholder="Usuario"  type="text" size="30"  onkeyup="formateaRut(this.value)" autocomplete="off" maxlength="12" >
                            </div>


                            <div class="form-group">
                                <input class="form-control input-lg" placeholder="Contrase�a"  name="pass" id="pass" type="password" class="PASS" size="30" >
                            </div>
                            <%
                                try {
                                    if (request.getParameter("timeout").equals("1")) {
                                        out.print("<h1 style=\"color:#7f7f7f;\" >Su sesi�n ha caducado <h1>");
                                        out.print("<h1 style=\"color:#7f7f7f;\">Ingrese Nuevamente <h1>");
                                    }
                                } catch (Exception ex) {
                                }

                                try {
                                    if (request.getParameter("cambio_pass").equals("1")) {
                                        out.print("<h1 style=\"color:#7f7f7f;\" >Su Clave ha sido Modificada<h1>");
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
                            <div class="form-group">
                                <button class="btn btn-primary btn-lg btn-block" type="submit">Entrar al Sistema</button>
                                <button class="btn btn-primary btn-lg btn-block" data-dismiss="modal" aria-hidden="true" type="reset">Borrar</button>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="col-md-12">

                        </div>	
                    </div>
                </div>
            </div>
        </div>
        <script>
            function formateaRut(Rut)

            {

                var sRut = new String(Rut.toUpperCase());

                var sRutFormateado = '';

                sRut = quitaFormato(sRut);

                var sDV = sRut.charAt(sRut.length - 1);

                sRut = sRut.substring(0, sRut.length - 1);

                document.forms[0].txtRutSinDV.value = sRut;

                document.forms[0].txtDV.value = sDV;



                while (sRut.length > 3)

                {

                    sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;

                    sRut = sRut.substring(0, sRut.length - 3);

                }

                sRutFormateado = sRut + sRutFormateado;

                if (sRutFormateado != "")
                    sRutFormateado += "-";

                sRutFormateado += sDV;

                if (document.forms[0].user.value != sRutFormateado)
                    document.forms[0].user.value = sRutFormateado;

            }

            function quitaFormato(Nro)

            {
                var strNro = new String(Nro);

                while (strNro.indexOf(".") != - 1)
                    strNro = strNro.replace(".", "");

                strNro = strNro.replace("-", "");

                return strNro;

            }

            function validaRut(sRut) {

                sDV = document.forms[0].user.value.substr(document.forms[0].user.value.length - 1, 1);
                RUTCORTADO = quitaFormato(sRut);
                RUTCORTADO = RUTCORTADO.substr(0, RUTCORTADO.length - 1);
                /*
                 alert (RUTCORTADO);
                 alert (dv (16623070));
                 
                 \u00e1 -> �
                 \u00e9 -> �
                 \u00ed -> �
                 \u00f3 -> �
                 \u00fa -> �
                 \u00c1 -> �
                 \u00c9 -> �
                 \u00cd -> �
                 \u00d3 -> �
                 \u00da -> �
                 \u00f1 -> �
                 \u00d1 -> �
                 � -> \u00FC
                 
                 � -> \u00DC
                 
                 � -> \u00E7
                 
                 � -> \u00C7
                 
                 � -> \u00BF
                 
                 � -> \u00A1
                 */

                if (document.forms[0].user.value == '')

                {
                    alert("             DEBE INGRESAR RUT!\n             .");
                    document.forms[0].user.select();
                    return false;
                }

                if (sDV.toUpperCase() != dv(RUTCORTADO))

                {
                    alert("             RUT INVALIDO!\n             Vuelva a Intentar.");
                    document.forms[0].user.select();
                    return false;
                }

                if (document.getElementById('pass').value.length == "")
                {
                    alert('Debe ingresar su contrase\u00f1a');
                    document.getElementById('pass').focus();
                    return false;
                }

                var sRut = new String(document.forms[0].user.value);

                sRut = quitaFormato(sRut);

                var sDV = new String(sRut.charAt(sRut.length - 1));

                sRut = sRut.substring(0, sRut.length - 1);

                if (sDV.toUpperCase() == dv(RUTCORTADO))

                {
                    return true;
                }

                return false;


            }




            function dv(T) {
                var M = 0, S = 1;
                for (; T; T = Math.floor(T / 10))
                    S = (S + T % 10 * (9 - M++ % 6)) % 11;
                return S ? S - 1 : 'K';
            }



            function validasoloRut(sRut) {

                sDV = document.forms[0].user.value.substr(document.forms[0].user.value.length - 1, 1);
                RUTCORTADO = quitaFormato(sRut);
                RUTCORTADO = RUTCORTADO.substr(0, RUTCORTADO.length - 1);
                /*
                 alert (RUTCORTADO);
                 alert (dv (16623070));
                 */
                if (document.forms[0].user.value == '')

                {
                    alert("             DEBE INGRESAR RUT!\n             .");
                    document.forms[0].user.select();
                    return false;
                }



                if (sDV.toUpperCase() != dv(RUTCORTADO))

                {
                    alert("             RUT INVALIDO!\n             Vuelva a Intentar.");
                    document.forms[0].user.select();
                    return false;
                }
                return true
            }


            function mensaj() {
                alert("toma valida rut");

            }











        </script>
        <script src="public/js/jquery.min.js"></script>
        <script src="public/js/bootstrap.min.js"></script>
    </body>
</html>