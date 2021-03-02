<%-- 
    Document   : uh_paciente_suam
    Created on : 31-ene-2013, 12:47:23
    Author     : Silvio
--%>

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
<script type="text/javascript" src="js/script2.js"></script>
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>

<script>
    function Enviar(modo) {
        if (modo==1){
            if (!validaRut12(document.getElementById('id_txt_user').value,1))
            {
                document.getElementById('id_txt_user').focus();
                return false;
            }else if (document.getElementById('id_txt_user').value.length<2){
                alert('Debe ingresar Rut');
                return false;
            }
            valor = document.getElementById('id_txt_user').value;
            document.getElementById('id_txt_user').value="";
        }else if (modo==2){
            
            if (document.getElementById('txt_dau').value.length==0)
            {
                alert('Debe Ingresar Número DAU');
                document.getElementById('txt_dau').focus();
                return false;
            }else if (document.getElementById('txt_dau').value==0){
                alert('Debe Ingresar Número DAU distinto de 0');
                document.getElementById('txt_dau').focus();
                return false;

            }
            valor = document.getElementById('txt_dau').value;
            tipo =2;
            if(document.getElementById('rbt_tipo1').checked){
                txt_modo =2;
            }else{
                txt_modo =3;
            }
            
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
            } else if ( obj.readyState == 4 && (obj.status == 500 || window.location.href.indexOf ("http")==- 1)) {
                document.getElementById('cargando').innerHTML='-Si el problema persiste consulte a Informática-';
                document.getElementById("Resultado").innerHTML =''+ obj.statusText; // estado 12-02-2013
            } else {
                //procesando...
                document.getElementById('cargando').innerHTML='&nbsp;&nbsp; <img src="Imagenes/loading.gif" width="16" height="16" alt="loading"/> Cargando...';
            }
        };
     
        if (modo==1){
            obj.open("GET", "uh_paciente_documento_suam.jsp?user="+valor+"&txt_modo=1" , true);
        }else if (modo==2){
            obj.open("GET", "uh_paciente_documento_suam.jsp?user="+valor+"&tipo="+tipo+"&txt_modo="+txt_modo , true);
           
        }
        
        obj.send(null);
        return (true);
    }


    

  
</script>

<body onload="document.getElementById('id_txt_user').focus()" >
<legend>BUSCAR DOCUMENTOS SUAM</legend>

<form name="index" method="GET" onsubmit="Enviar(1);return false;"    >
    <table border="0">
        <tbody>
        <thead> <tr><th colspan="3" ></th>  <th colspan="3" >  DAU<input type="radio" name="rbt_tipo" id="rbt_tipo1" value="1" checked="checked" /> DAU NN<input type="radio" name="rbt_tipo" id="rbt_tipo2" value="2" />  </th></tr></thead>
        <tr>
            <td><font color="<%=color%>" ><b>Ingrese Rut&nbsp;&nbsp;&nbsp;</b></font></td>
            <td>
                <input value="dfaddsds" name="txtRutSinDV" id="txtRutSinDV" type="hidden">
                <input value="d" name="txtDV" id="txtDV" type="hidden">
                <input name="user" class="user" id="id_txt_user"  type="text" size="20" maxlength="12" autocomplete="off" onkeyup="formateaRut(this.value)" value=""   >
            </td>
            <td style=" text-align:  center " >
                &nbsp;
                <input class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar(1);" name="enviarAjax" >
                <% for (int j = 0; j < 5; j++) {
                                out.write("&nbsp;");
                            }
                %>
            </td>
            <td><font color="<%=color%>" ><b>Ingrese N° DAU&nbsp;&nbsp;&nbsp;</b></font></td>
            <td>
                <input value="" name="txt_dau" id="txt_dau" onkeypress="return soloNumeros(event)"  type="text">
            </td>
            <td style=" text-align:  center " >
                &nbsp;
                <input class="btn btn-primary" type="button" value="BUSCAR" onclick="Enviar(2);" name="enviarAjax" >
                <% for (int j = 0; j < 5; j++) {
                                out.write("&nbsp;");
                            }
                %>
            </td>
        </tr>
        </tbody>
    </table>
</form>


<div id="cargando"></div>
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