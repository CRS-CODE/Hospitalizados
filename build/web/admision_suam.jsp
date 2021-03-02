<%-- 
    Document   : admision_suam
    Created on : 23-ago-2012, 15:24:35
    Author     : EseGamboa
--%>

<%@page import="CapaNegocio.Negocio"%>
<%
    HttpSession session1 = request.getSession();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION");
        response.sendRedirect("index.jsp?timeout=1");
    } else {
%>
<jsp:include page="Header.jsp" />

<script type="text/javascript" src="js/script2.js"></script>
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>

<%
    Negocio neg = new Negocio();
%>
<script>
    function Enviar() {

        if (document.getElementById('txt_dau').value.length === 0)
        {
            alert('Debe Ingresar Número DAU');
            document.getElementById('txt_dau').focus();
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
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML = '&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/>Cargando...espere un momento...';
            }
        };
        valor = document.getElementById('txt_dau').value;
        tipo = 1;
        if (document.getElementById('rbt_tipo1').checked) {
            tipo = 1;
        } else {
            tipo = 2;
        }

        document.getElementById('txt_dau').value = "";
        obj.open("GET", "admision_suam_carga.jsp?id_dau=" + valor + "&tipo=" + tipo, true);
        obj.send(null);
        return (true);
    }
</script>

<script type="text/javascript">
    function valida_form() {
        if (confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Información ? \n \n ")) {
        } else {
            return false;
        }
    }
</script>


<script>



    function Enviar_DAS()
    {
        var id_das = document.getElementById('txt_das_id').value;
        if (confirm('Esta apunto de generar un Registro: \nRecuerde que después de Generado no podrá modificarlo\nEsta Seguro de Continuar?'))
        {
            // document.getElementById('Form1').action='<=neg.getLocal()>ingreso_dato';
            document.getElementById('Form1').submit();


        }
    }
</script>


<script>
    function valida_form_das() {

        if (document.getElementById('cbo_derivador').value == -1) {
            alert('Debe seleccionar Derivador');
            return false;
        }
        else if (document.getElementById('cbo_camilla').value == -1) {
            alert('Debe seleccionar Camilla');
            return false;
        } else if (document.getElementById('cbo_medico').value == -1) {
            alert('Debe seleccionar Médico');
            return false;
        }

        if (confirm('Esta apunto de generar un Registro:\nEsta Seguro de Continuar?'))
        {

        } else {
            return false;
        }

        return true;
    }

</script>

<body onload="document.getElementById('txt_dau').focus()" >
<legend>ADMISION SUAM</legend>

<form name="index" method="GET" onsubmit="Enviar();
        return false;"    >
    <table border="0">
        <thead>
            <tr>

            </tr>
        </thead>
        <tbody>
            <tr>
                <th rowspan="4" >Ingrese DAU&nbsp;&nbsp;&nbsp;</th>
                <td>DAU </td>
                <td><input type="radio" name="rbt_tipo" id="rbt_tipo1" value="1" checked="checked" onclick="document.getElementById('txt_dau').focus()" /> </td>
                <td> DAU NN</td>
                <td><input type="radio" name="rbt_tipo" id="rbt_tipo2"  value="2" onclick="document.getElementById('txt_dau').focus()" /> </td>
                <td rowspan="3" align="center" >  &nbsp;&nbsp; &nbsp;&nbsp;<input class="btn btn-primary" type="submit" value="BUSCAR" name="btn_buscar" /> </td>
                <td rowspan="3" align="center" > &nbsp;&nbsp; <div id="cargando" ></div> </td>
            </tr>
            <tr>
                <td>N°</td>
                <td colspan="3" >
                    <input type="text" name="txt_dau" id="txt_dau" value="" size="15" maxlength="9"  onkeypress="return soloNumeros(event)" autocomplete="off"  />
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
</div>

</body>
<jsp:include page="Footer.jsp" />

<%            }
%>
