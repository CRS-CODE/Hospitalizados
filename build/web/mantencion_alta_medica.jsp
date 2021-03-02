<%-- 
    Document   : mantencion_alta_medica
    Created on : 12-feb-2013, 16:10:04
    Author     : Silvio
--%>

<jsp:include page="Header.jsp" />

<script>
    function Enviar() {
        if (document.getElementById('txt_duo').value.length == 0)
        {
            alert('Debe ingresar número interno del DUO');
            document.getElementById('txt_duo').focus();
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
        valor = document.getElementById('txt_duo').value;
        document.getElementById('txt_duo').value = "";
        obj.open("GET", "mantencion_alta_medica_carga.jsp?txt_duo=" + valor + "&modo=1", true);
        obj.send(null);
        return (true);
    }


    function Enviar2(txt_duo) {
        //alert(txt_duo);
        if (confirm("CONFIRMACION ! Esta Seguro que desea anular esta alta medica? \n \n ")) {
        } else {
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

        obj.open("GET", "mantencion_alta_medica_carga.jsp?txt_duo=" + txt_duo + "&modo=2", true);
        obj.send(null);
        return (true);
    }

</script>
<body onload="document.getElementById('txt_duo').focus()" >
    <form name="form_1" action="#" method="POST" onsubmit="" ></form>
    <table>
        <tr>
            <td>
                <b>Ingrese N° DUO</b>
            </td>
            <td>
                <input type="text" value="" name="txt_duo" id="txt_duo" onkeypress="" >
            </td>

            <td style=" text-align:  center " >
                &nbsp;
                <input class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar();" name="enviarAjax" >
            </td>
            <td>
                <div id="cargando" ></div>
            </td>
        </tr>
    </table>

    <div id="Resultado" style=" overflow:auto;
         padding-right: 15px; padding-top: 15px; padding-left: 15px; padding-bottom: 15px;
         border-right: #6699CC 1px solid; border-top: #999999 1px solid;
         border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
         scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
         scrollbar-track-color :#3333333 ;
         height:370px; left: 100; top: 20; width: 95%">
        <!-- Este DIV contendra la respuesta enviada por el Servlet -->
    </div>

    <jsp:include page="Footer.jsp" />
