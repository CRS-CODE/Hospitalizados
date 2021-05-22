<%-- 
    Document   : admision_ugu
    Created on : 10-may-2012, 11:52:03
    Author     : EseGamboa
--%>
<%
    HttpSession session1 = request.getSession();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION");
        response.sendRedirect("index.jsp?timeout=1");
    } else {
%>
<jsp:include page="Header.jsp" />
<script type="text/javascript" src="js/valida_1.js"></script>

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
                $("#fecha_nac").datepicker({
                    changeMonth: true,
                    changeYear: true
                });
            } else if (obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf("http") == -1)) {
                document.getElementById('cargando').innerHTML = '-Si el problema persiste consulte a Informática-';
                document.getElementById("Resultado").innerHTML = '' + obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML = '&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...espere mientras el Sistema consulta Informacion a Fonasa...';
            }
        };
        valor = document.getElementById('id_txt_user').value;
        document.getElementById('id_txt_user').value = "";
        obj.open("GET", "admision_ugu_carga.jsp?user=" + valor, true);
        obj.send(null);
        return (true);
    }
</script>

<script type="text/javascript">
    function valida_form() {
        for (i = 0; i < 100; i++) {
            if (document.getElementById('direccion').value[i] == '#')
            {
                alert('No use Caracteres como # u Otros!!!');
                document.getElementById('direccion').value = document.getElementById('direccion').value.replace('#', 'N° ');
            }
        }

        if (!validaRut12(document.getElementById('id_txt_user').value, 1))
        {
            document.getElementById('id_txt_user').focus();
            return false;
        }

        if (document.getElementById('nombres').value.length == 0) {
            alert('Debe ingresar Nombres');
            document.getElementById('nombres').focus();
            return false;
        } else if (document.getElementById('apellidop').value.length == 0) {
  alert('Debe ingresar apellido Paterno');
     document.getElementById('apellidop').focus();
            return false;
        }  else if (document.getElementById('fecha_nac').value.length != 10) {
            alert('Debe ingresar Fecha Nacimiento (dd/mm/aaaa)');
               document.getElementById('fecha_nac').focus();
            return false;
        } else if (document.getElementById('direccion').value.length == 0) {
            alert('Debe ingresar Dirección');
               document.getElementById('direccion').focus();
            return false;
        } else if (document.getElementById('id_comuna').value == -2) {
            alert('Debe seleccionar comuna');
            return false;
        } else if (document.getElementById('id_consultorio_pertenencia').value == -2) {
            alert('Debe seleccionar Consultorio Pertenencia');
            return false;
        } else if (document.getElementById('id_pueblo').value == -2) {
            alert('Debe seleccionar pueblo');
            return false;
        } else if (document.getElementById('paciente_prevision').value == -2) {
            alert('Debe seleccionar previsión');
            return false;
        } else if (document.getElementById('fecha_duo').value.length == 0) {
            alert('Debe seleccionar Fecha Duo');
            return false;
        } else if (document.getElementById('id_derivado').value == -2) {
            alert('Debe seleccionar Derivador');
            return false;
        } else if (document.getElementById('id_cama').value == -2) {
            alert('Debe seleccionar Cama');
            return false;
        }

        if (confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Información ? \n \n ")) {
        } else {
            return false;
        }
    }
</script>

<body onload="document.getElementById('id_txt_user').focus()" >
<legend>ADMISION UGU</legend>

<form name="index" method="GET" onsubmit="Enviar();
        return false;"    >
    <table border="0">
        <tbody>
        <thead> <tr><th colspan="3" >DATO UNIDAD OBSERVACIÓN DUO</th></tr></thead>
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
                <input  class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar();" name="enviarAjax" >
            </td>
            <td>&nbsp; <div id="cargando"></div></td>
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
     height: auto; left: 100; top: 20; width: 95%">
    <!-- Este DIV contendra la respuesta enviada por el Servlet -->
</div>

</body>
<jsp:include page="Footer.jsp" />

<%
    }
%>

