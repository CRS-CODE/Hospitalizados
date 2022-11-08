<%-- 
    Document   : visita_medica_carga
    Created on : 17-may-2012, 11:40:03
    Author     : EseGamboa
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<%@page import="CapaDato.cVisita"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%
    NegocioQ neg = new NegocioQ();

    int obtiene_duo = Integer.parseInt(request.getParameter("duo"));
    cDuo duo = neg.obtiene_duo(obtiene_duo);

    int old = neg.IngresoOld(obtiene_duo);

    ArrayList historial_visita_enfermeria = neg.lista_historial_visita_enfermeria(obtiene_duo);
    Iterator it_his = historial_visita_enfermeria.iterator();
%>


<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<form id="Form1" >
    <fieldset>
        <legend>Datos del Paciente: </legend>
        <table bgcolor="#cccccc"  style="FONT-FAMILY: Arial, Helvetica, sans-serif; FONT-SIZE: 12px;" BORDER="0" width="720">
            <input type="hidden" id="rut" value="<%=String.valueOf(request.getParameter("rut"))%>">
            <input type="hidden" id="id_duo" name="id_duo" value="<% out.write(obtiene_duo + "");%>">
            <input type="hidden" id="estado" name="estado" value="21">
            <input type="hidden" id="txt_modo" name="txt_modo" value="2">
            <tr>
                <td><b>Nombre:</b></td>
                <td COLSPAN="6"><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>
            <tr>
                <td><b>Rut:</b></td><td><% out.write("" + duo.getRut_paciente());%></td>
                <td><b>Fecha Nac:</b></td><td><% out.write("" + duo.getFecha_nac());%></td>
                <td><b>Edad:</b></td><td><% out.write("" + duo.getEdad());%></td>
                <td><b>Sexo:</b></td>
                <td><% out.write("" + duo.getSexo_descri());%></td>
            </tr>
            <tr>
                <td><b>Dirección:</b></td>
                <td colspan="3"><% out.write("" + duo.getDireccion());%></td>
                <td><b>Comuna:</b></td>
                <td><% out.write("" + duo.getComuna_descri());%></td>
            </tr>
            <tr>
                <td><b>Telefono:</b></td><td><% out.write("" + duo.getTelefono1());%></td>
                <td><b>Celular: 09-</b></td><td><% out.write("" + duo.getTelefono2());%></td>
            </tr>
            <tr>
                <td><b>Previsión:</b></td>
                <td colspan="2"><% out.write("" + duo.getCodigo_fonasa_descripcion());%></td>
                <td colspan="4">
                    <img src="../Iconos/dialog_information_small.png" width="15" height="20">Esta previsión esta sujeta a verifición a la salida del paciente
                </td>
            </tr>
            <tr>
                <td><b>Fecha y Hora:</b></td>
                <td><% out.write("" + duo.getFecha_hora_ing_duo());%></td>
                <td><b>Derivado desde:</b></td>
                <td><% out.write("" + duo.getDerivador_descripcion());%></td>
                <td><b>N° Cama:</b></td>
                <td><% out.write("" + duo.getCama_descripcion());%></td>
            </tr>
            <tr>
                <td colspan="6" >
                    <div id="cargandoD" ></div>
                </td>
            </tr>


        </table>
        <div id="Resultado" style=" overflow:auto;
             padding-right: 15px; padding-top: 5px; padding-left: 15px; padding-bottom: 15px;
             border-right: #6699CC 1px solid; border-top: #999999 1px solid;
             border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
             scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
             scrollbar-track-color :#3333333 ;
             height:210px; left: 100; top: 10; width: 95%">
            <!-- Este DIV contendra la respuesta enviada por el Servlet -->
            <fieldset>
                <legend>Historial Visita Enfermería</legend>

                <%
                    String clas = "";
                    int contador = 0;

                    while (it_his.hasNext()) {
                        cVisita vis = (cVisita) it_his.next();

                        int resto = contador % 3;
                        if (contador == 0 || resto == 0) {
                            out.write("<tr>");
                        }
                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("D")) {
                            clas = "CRD_D";
                        }
                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("C")) {
                            clas = "CRD_C";
                        }
                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("B")) {
                            clas = "CRD_B";
                        }
                        if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("A")) {
                            clas = "CRD_A";
                        }

                %>
                <td><img src="../Iconos/pdf-ico-small.gif" style="cursor: pointer" onclick="window.open('/modulo_uhce/visitas/CategorizacionPDF.jsp?id_visita=' +<%=vis.getId_visita_categorizacion()%>, 'Categorizacion', 'height=800,width=800,left=100, top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')"></td>
                <td class="" style="text-transform: uppercase;cursor:pointer" onclick="location.href = 'VerVisita.jsp?id_visita=<%=vis.getId_visita_categorizacion()%>'"><%=vis.getFecha_visita()%></td>
                <td onclick="location.href = 'http://10.8.4.9:9090/modulo_uo/Visita/VerVisita.jsp?id_visita=<%=vis.getId_visita_categorizacion()%>'" class="<%=clas%>"><%=vis.getCat_visita_categorizacion()%></td>

                <%
                        contador++;
                    }
                %>

            </fieldset>

            <fieldset>
                <legend>Historial Visita Médica</legend>

            </fieldset>
            <fieldset>
                <legend>Datos Paciente </legend>
                <% out.write("<a href='" + neg.getLocal() + "PDF_DUO?id_duo=" + obtiene_duo + "' target='_blank'><img src='../Imagenes/doctorImp.png' width='35' height='36' alt='Ingreso Medico'/></a>"); %>
                <% out.write(" &nbsp;&nbsp;"); %>
                <%
                    if (old > 0) {
                        out.write("<a href='" + neg.getLocal() + "PDF_ingreso_enfermeriaOLD?txt_duo=" + obtiene_duo + "' target='_blank'><img src='Imagenes/enfermeraImp.png' width='35' height='36' alt='Ingreso Enfermería' title='Ingreso Enfermería' /></a>");

                    } else {
                        out.write("<a href='" + neg.getLocal() + "PDF_ingreso_enfermeria?txt_duo=" + obtiene_duo + "' target='_blank'><img src='Imagenes/enfermeraImp.png' width='35' height='36' alt='Ingreso Enfermería' title='Ingreso Enfermería' /></a>");

                     }%>


            </fieldset>



        </div>
        <h3 class="destacadorut">Responsable:<%=session.getAttribute("usuario_nombre_completo")%></h3>
        <fieldset class="buttons">
            <input type="button" id="BtnIngresar" style="" onclick="Enviar()" class="DR" value="Ingresar Paciente a Cama" >
            <input type="button" id="BtnFicha" value="Ver Ficha" onclick="window.open('http://10.8.4.9:9090/modulo_agenda/pdf_portada_ficha.jsp?rut=' + document.getElementById('paciente_rut').value + '&rut2=' + document.getElementById('txtRutSinDV').value + '&dv=' + document.getElementById('txtDV').value, 'pop-up', 'width=500, height=500, scrollbars=yes, menubar=no, location=yes, status=no, resizable=yes,left = 800,top = 0')">
        </fieldset>

    </fieldset>
</form>