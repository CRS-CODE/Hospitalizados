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
        if (!validaRut12(document.getElementById('rutpaciente').value, 1))
        {
            document.getElementById('rutpaciente').focus();
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
        var valor = document.getElementById('rutpaciente').value;
        var sRut = new String(valor);
        sRut = quitaFormato(sRut);
        var sDV = sRut.charAt(sRut.length - 1);
        sRut = sRut.substring(0, sRut.length - 1);
        var r = sRut;
        var dv = sDV;
        obj.open("GET", "admision_ugu_carga.jsp?user=" + valor + "&rut=" + r + "&dv=" + dv, true);
        obj.send(null);
        return (true);
    }
</script>


<script>
    function formateaRut(Rut)
    {
        var sRut = new String(Rut);
        var sRutFormateado = '';
        sRut = quitaFormato(sRut);
        var sDV = sRut.charAt(sRut.length - 1);
        sRut = sRut.substring(0, sRut.length - 1);
        document.getElementById('txtRutSinDV').value = sRut;
        document.getElementById('txtDV').value = sDV;
        //document.forms[0].txtRutSinDV.value = sRut;
        //document.forms[0].txtDV.value = sDV;

        while (sRut.length > 3)
        {
            sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;
            sRut = sRut.substring(0, sRut.length - 3);
        }
        sRutFormateado = sRut + sRutFormateado;
        if (sRutFormateado !== "")
            sRutFormateado += "-";
        sRutFormateado += sDV;
        if (document.getElementById('rutpaciente').value !== sRutFormateado)
            document.getElementById('rutpaciente').value = sRutFormateado;
    }
    function quitaFormato(Nro)
    {
        var strNro = new String(Nro);
        while (strNro.indexOf(".") !== - 1)
            strNro = strNro.replace(".", "");
        strNro = strNro.replace("-", "");
        return strNro;
    }

    function DigitoVerificadorRut(strRut) {
        var rut = 0;
        var s = 0;
        var l_dv = "";

        rut = strRut;
        for (i = 2; i < 8; i++) {
            s = s + (rut % 10) * i;
            rut = (rut - (rut % 10)) / 10;
        }
        s = s + (rut % 10) * 2;
        rut = (rut - (rut % 10)) / 10;
        s = s + (rut % 10) * 3;
        rut = (rut - (rut % 10)) / 10;
        s = 11 - (s % 11);
        if (s === 10)
            l_dv = "K";
        else
        if (s === 11)
            l_dv = "0"
        else
            l_dv = s + "";
        return(l_dv);
    }

    function validaRut() {
        var sRut = new String(document.getElementById('rutpaciente').value);
        sRut = quitaFormato(sRut);
        var sDV = new String(sRut.charAt(sRut.length - 1));
        sRut = sRut.substring(0, sRut.length - 1);
        if (sDV.toUpperCase() === DigitoVerificadorRut(sRut))
        {
            return true;
        }
        if (sDV.toUpperCase() !== DigitoVerificadorRut(sRut))
        {
            return false;
        }
    }


</script>

<script>

    function validarFechaMenorActual(date) {
        var dateInsert = new Date();
        var fecha = date.split("/");
        dateInsert.setFullYear(fecha[2], fecha[1] - 1, fecha[0]);
        var today = new Date();
        if (dateInsert >= today)
            return false;
        else
            return true;
    }
    function valida_form() {
        for (i = 0; i < 100; i++) {
            if (document.getElementById('direccion').value[i] == '#')
            {
                alert('No use Caracteres como # u Otros!!!');
                document.getElementById('direccion').value = document.getElementById('direccion').value.replace('#', 'N° ');
            }
        }

        if (!validaRut12(document.getElementById('rutpaciente').value, 1))
        {
            document.getElementById('rutpaciente').focus();
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
        } else if (document.getElementById('fecha_nac').value.length != 10) {
            alert('Debe ingresar Fecha Nacimiento (dd/mm/aaaa)');
            document.getElementById('fecha_nac').focus();
            return false;
        } else if (!validarFechaMenorActual(document.getElementById('fecha_nac').value)) {
            alert('Debe ingresar Una fecha valida menor.');
            document.getElementById('fecha_nac').focus();
            return false;
        } else if (document.getElementById('direccion').value.length == 0) {
            alert('Debe ingresar Dirección');
            document.getElementById('direccion').focus();
            return false;
        } else if (document.getElementById('id_comuna').value == -1) {
            alert('Debe seleccionar comuna');
            return false;
        } else if (document.getElementById('id_consultorio_pertenencia').value == 0) {
            alert('Debe seleccionar Consultorio Pertenencia');
            return false;
        } else if (document.getElementById('id_pueblo').value == -2) {
            alert('Debe seleccionar pueblo');
            return false;
        } else if (document.getElementById('prevision').value == -1) {
            alert('Debe seleccionar previsión');
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
<body onload="document.getElementById('rutpaciente').focus()" >
<legend>ADMISION UGU</legend>

<form name="index" method="GET" onsubmit="Enviar();
        return false;"    >
    <table border="0">
        <tbody>
        <thead> <tr><th colspan="3" >DATO UNIDAD OBSERVACIÓN DUO</th></tr></thead>
        <tr>
            <td><b>Ingrese Rut&nbsp;&nbsp;&nbsp;</b></td>
            <td>
                <div class="col-md-4">

                    <input type="text" id="rutpaciente" name="rutpaciente" style="text-transform:uppercase; cursor:pointer;" onmouseover="showToolTip(event, '¡Escriba su RUT sin puntos ni guiones!');
                            return false" onkeyup="formateaRut(this.value);" autocomplete=off onclick="javascript:document.getElementById('rutpaciente').select();" maxlength="12" class="form-control" placeholder="Rut Paciente">
                    <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                    <input value="d" name="txtDV" id="txtDV" type="hidden">

                </div>
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

