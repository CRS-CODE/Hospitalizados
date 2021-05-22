<%-- 
    Document   : uh_paciente
    Created on : 11-may-2012, 12:39:14
    Author     : EseGamboa
--%>

<%
    String color = "black";
%>
<jsp:include page="Header.jsp" />
<script type="text/javascript" src="js/valida_1.js"></script>
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>

<script>
    function Enviar() {
        if (!validaRut12(document.getElementById('id_txt_user').value, 1))
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
        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('cargando').innerHTML = '';
                document.getElementById("Resultado").innerHTML = obj.responseText;
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('cargando').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById("Resultado").innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML = '&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>  Cargando...';
            }
        };
        valor = document.getElementById('id_txt_user').value;
        document.getElementById('id_txt_user').value = "";
        obj.open("GET", "uh_paciente_documento.jsp?user=" + valor, true);
        obj.send(null);
        return (true);
    }

    function Enviar_Modificar() {
        if (!validaRut12(document.getElementById('id_txt_user').value, 1))
        {
            document.getElementById('id_txt_user').focus();
            return false;
        }

        if (document.getElementById('id_txt_user').value == '') {
            alert('Debe ingresar el Rut');
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
        obj.onreadystatechange = function() {
            if (obj.readyState == 4 && (obj.status == 200 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('cargando').innerHTML = '';
                document.getElementById("Resultado").innerHTML = obj.responseText;
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('cargando').innerHTML = '-Si el problema persisten consulte a Informática-';
                document.getElementById("Resultado").innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML = '&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>  Cargando...';
            }
        };
        valor = document.getElementById('id_txt_user').value;
        document.getElementById('id_txt_user').value = "";
        obj.open("GET", "uh_paciente_datos.jsp?user=" + valor, true);
        obj.send(null);
        return (true);
    }


</script>

<body onload="document.getElementById('id_txt_user').focus()" >
<legend>BUSCAR DOCUMENTOS</legend>

<form name="index" method="POST" onsubmit="Enviar();
        return false;"    >
    <table border="0">
        <tbody>
        <thead> <tr><th colspan="3" ></th></tr></thead>
        <tr>
            <td><font color="<%=color%>" ><b>Ingrese Rut&nbsp;&nbsp;&nbsp;</b></font></td>
            <td>
                <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                <input value="d" name="txtDV" id="txtDV" type="hidden">
                <input name="user" class="user" id="id_txt_user"  type="text" size="20" maxlength="12" autocomplete="off" onkeyup="formateaRut(this.value)" value=""   >
            </td>
            <td style=" text-align:  center " >
                &nbsp;
                <input class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar();" name="enviarAjax" >
                <% for (int j = 0; j < 55; j++) {
                        out.write("&nbsp;");
                    }%>
            </td>
            <td>
                <table>
                    <tr>
                        <td>
                            <img src="Imagenes/consultorio.png" onclick="Enviar_Modificar()" width="44" height="44" 
                                 alt="Modificar Consultorio (Digite el Rut y luego haga click sobre la imagen)" 
                                 title="Modificar Consultorio (Digite el Rut y luego haga click sobre la imagen)"  />

                        </td>
                        <td>
                            <font color="<%=color%>" style=" font-size: small; font-family:  cursive ">Modificar<br>Consultorio<br>pertenencia</font>
                            <div id="cargando"></div>   
                        </td>
                    </tr>
                </table>


            </td>
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
     height:370px; left: 100; top: 20; width: 95%">
    <!-- Este DIV contendra la respuesta enviada por el Servlet -->

    <%

        try {
            if (request.getParameter("mod").equals("1")) {
                out.print("<h1 style=\"color:#0000CD;\" >Se modifico Consultorio<h1>");

            }
        } catch (Exception ex) {
        }
    %>
</div>

</body>
<jsp:include page="Footer.jsp" />