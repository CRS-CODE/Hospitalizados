<%-- 
    Document   : admision_uhce
    Created on : 10-may-2012, 11:54:49
    Author     : EseGamboa
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<%@page import="CapaNegocio.NegocioQ"%>
<jsp:include page="Header.jsp" />
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script>
    function valida_anula(){

        if (document.getElementById('txa_motivo').value.length==0){
            alert('Debe ingresar el motivo de Anulación');
            document.getElementById('txa_motivo').focus();
            return false;
        }

        if(confirm(" ¿ Esta seguro que desea Anular este Duo ?  \n"))
        {  return true;} else {
            return false;}
    }

</script>

<body>

    <%
                NegocioQ neg = new NegocioQ();
                int duo = Integer.parseInt(request.getParameter("txt_manda_duo"));
                String motivo = request.getParameter("txa_motivo");


                out.write("<center><form name='form_can" + duo + "' id='form_can" + duo + "' action='" + neg.getLocal() + "modifico_uh' method='POST' onsubmit='return valida_anula()' >");
                out.write("<input type='hidden' name='txt_manda_duo' value='" + duo + "' >");
                out.write("<input type='hidden' name='modo' value='99' >");
                out.write("Ingrese Motivo:<br><textarea cols=\"100\" rows=\"4\" id=\"txa_motivo\" name=\"txa_motivo\"></textarea>");

                out.write("<br><br> <input type=\"submit\" value=\"ANULAR DUO " + duo + "\" name=\"btn_solicitud\"  /> ");
                out.write("</form></center>");


    %>
</tbody>




</body>
<jsp:include page="Footer.jsp" />

