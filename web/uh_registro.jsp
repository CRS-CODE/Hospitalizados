<%-- 
    Document   : uh_registro
    Created on : 31-may-2012, 15:08:02
    Author     : EseGamboa
--%>



<jsp:include page="Header.jsp" />
<script type="text/javascript" src="js/valida_1.js"></script>
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>


<script>
    function Enviar() {
        if (!validaRut12(document.getElementById('id_txt_user').value,1))
        {
            document.getElementById('id_txt_user').focus();
            return false;
        }
        var obj = false;
        if (window.XMLHttpRequest) {
            //Cuidado aqui, el objeto XMLHttpRequest no esta disponible en versiones previas a IE7
            obj = new XMLHttpRequest();
        } else {
            return false;
        }
        obj.onreadystatechange = function () {
            if ( obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf ("http")==- 1)) {
                document.getElementById('cargando').innerHTML='';
                document.getElementById("Resultado").innerHTML = obj.responseText;
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML='&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
        valor = document.getElementById('id_txt_user').value;
        document.getElementById('id_txt_user').value="";
        obj.open("GET", "uh_registro_carga.jsp?user="+valor , true);
        obj.send(null);
        return (true);
    }


    function Enviar2() {
      
        var obj = false;
        if (window.XMLHttpRequest) {
            //Cuidado aqui, el objeto XMLHttpRequest no esta disponible en versiones previas a IE7
            obj = new XMLHttpRequest();
        } else {
            return false;
        }
        obj.onreadystatechange = function () {
            if ( obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf ("http")==- 1)) {
                document.getElementById('cargando').innerHTML='';
                document.getElementById("Resultado").innerHTML = obj.responseText;
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML='&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...';
            }
        };
        valor = document.getElementById('txt_valor').value;
        modo=0;
        if (document.getElementById('rbt_modo1').checked){
            modo=1;
        }else if (document.getElementById('rbt_modo2').checked){
            modo=2;
        }
        
        obj.open("GET", "uh_registro_busqueda.jsp?modo="+modo+"&valor="+valor , true);
        obj.send(null);
        return (true);
    }
</script>

<script type="text/javascript">
    function valida_form(){

        if (document.getElementById('txt_nombres').value.length==0){
            alert('Debe ingresar nombres');
            return false;
        }else if (document.getElementById('txt_apellidop').value.length==0){
            alert('Debe ingresar apellido paterno');
            return false;
        }else if (document.getElementById('txt_apellidom').value.length==0){
            alert('Debe ingresar apellido materno');
            return false;
        }

    }
</script>

<body onload="document.getElementById('id_txt_user').focus()" >
<legend>Registro de Usuarios</legend>

<table>
    <tr>
        <td>
            <form name="index" method="GET" onsubmit="Enviar();return false;"    >
                <table border="0">
                    <tbody>
                        <tr>
                            <td><b>Ingrese Rut&nbsp;&nbsp;&nbsp;</b></td>
                            <td>
                                <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                                <input value="d" name="txtDV" id="txtDV" type="hidden">
                                <input name="user" class="user" id="id_txt_user"  type="text" size="20" maxlength="12" autocomplete="off" onkeyup="formateaRut(this.value)" value=""   >
                                <br>
                            </td>
                            <td>
                                &nbsp;
                                <input class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar();" name="enviarAjax" >
                            </td>
                            <td>&nbsp; <div id="cargando"></div> </td>
                        </tr>
                        <tr>
                            
                            <td colspan="4" >
                                <%

                                            try {
                                                if (request.getParameter("ok") != null) {
                                                    out.print("<h1 style=\"color:#0000CD;\" >Se registro a " + request.getParameter("ok") + "<br>"
                                                            + "Recuerde que su clave corresponde a los <br>primeros cuatro digitos de su rut<h1>");

                                                } else if (request.getParameter("ok96") != null) {
                                                    out.print("<h1 style=\"color:#0000CD;\" >Se reestalecio la contraseña del usuario<br>"
                                                            + "Recuerde que su clave corresponde a los <br>primeros cuatro digitos de su rut<h1>");

                                                }else if (request.getParameter("ok97") != null) {
                                                    out.print("<h1 style=\"color:#0000CD;\" >Se modificaron los datos <h1>");

                                                }
                                            } catch (Exception ex) {
                                            }

                                %>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>

        </td>
        <td>

            <form name="index2" method="GET" onsubmit="Enviar2();return false;"    >
                <table border="0">
                    <tbody>
                    <thead> <tr><th colspan="3" >&nbsp;<input type="radio" name="rbt_modo" id="rbt_modo1" value="1" checked="checked" />Por Nombre&nbsp;&nbsp;&nbsp;<input type="radio" name="rbt_modo" id="rbt_modo2" value="2" />Por Apellido P.</th></tr></thead>
                    <tr>
                        <td><b>Ingrese Nombre</b></td>
                        <td>
                            <input value="" name="txt_valor" id="txt_valor" type="text">
                            <br>
                        </td>
                        <td>
                            &nbsp;
                            <input  class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar2();" name="enviarAjax" >
                        </td>
                        <td>&nbsp; <div id="cargando2"></div> </td>
                    </tr>
                    </tbody>
                </table>
            </form>

        </td>
    </tr>
</table>



<div id="Resultado" style=" overflow:auto;
     padding-right: 15px; padding-top: 15px; padding-left: 15px; padding-bottom: 15px;
     border-right: #6699CC 1px solid; border-top: #999999 1px solid;
     border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
     scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
     scrollbar-track-color :#3333333 ; left: 100; top: 20; width: 95%">
    <!-- Este DIV contendra la respuesta enviada por el Servlet -->
</div>



</body>
<jsp:include page="Footer.jsp" />

