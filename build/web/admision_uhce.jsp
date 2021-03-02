<%-- 
    Document   : admision_uhce
    Created on : 10-may-2012, 11:54:49
    Author     : EseGamboa
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<jsp:include page="Header.jsp" />
<script type="text/javascript" src="js/jquery/tooltip.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<body>
<legend>ADMISION UHCE</legend>
<%
            String titulo = " style=' background-color: #4169E1 ; color: white '  ";
            String datos = " style=' background-color: #87CEFA ; color: black '  ";
%>
<table border="1">
    <thead>
        <tr <%=titulo%>>
            <th>N° Duo</th>
            <th>Rut Paciente</th>
            <th>Nombre Paciente</th>
            <th>Cama</th>
            <th>Fecha Ingreso</th>
            <th>Ing. Enf.</th>
            <th>Anular Ingreso</th>
        </tr>
    </thead>
    <tbody>
        <%


                    NegocioQ neg = new NegocioQ();
                    ArrayList lista_camas = neg.lista_grilla_camas();
                    Iterator it = lista_camas.iterator();

                    while (it.hasNext()) {
                        cDuo aux = (cDuo) it.next();
                        if (aux.getTiene_enfermeria() == 0 && aux.getId_duo() != 0) {
                            out.write("<tr " + datos + " >");
                            out.write("<td>" + aux.getId_duo() + "</td>");
                            out.write("<td>" + aux.getRut_paciente() + "</td>");
                            out.write("<td>" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + " " + aux.getApellidom_paciente() + "</td>");
                            out.write("<td>" + aux.getCama_descripcion() + "</td>");
                            out.write("<td>" + aux.getFecha_duo()+" "+aux.getHora_duo()+ "</td>");
                            out.write("<td><center><form name='form_enf" + aux.getId_duo() + "' id='form_enf" + aux.getId_duo() + "' action='" + neg.getLocal() + "ingreso/ingreso_enfermeria.jsp' method='POST' >");
                            out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                            out.write(" <img width='35' height='36' title='Ingreso Enfermera' src='Imagenes/Nurse_add.png' onclick='document.forms[\"form_enf" + aux.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                            out.write("</form></center></td>");


                            out.write("<td><center><form name='form_can" + aux.getId_duo() + "' id='form_can" + aux.getId_duo() + "' action='" + neg.getLocal() + "admision_uhce_anula.jsp' method='POST' >");
                            out.write("<input type='hidden' name='txt_manda_duo' value='" + aux.getId_duo() + "' >");
                            out.write("<input type='hidden' name='modo' value='99' >");
                            out.write(" <img width='33' height='34' title='Anular Ingreso' src='Imagenes/cancel.png' onclick='document.forms[\"form_can" + aux.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                            out.write("</form></center></td>");

                            out.write("</tr>");
                        }

                    }


        %>
    </tbody>
</table>




</body>
<jsp:include page="Footer.jsp" />

