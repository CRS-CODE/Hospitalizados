<%-- 
    Document   : uh_paciente_datos
    Created on : 07-jun-2012, 22:30:46
    Author     : Dis
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cPaciente"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%
            String rut_paciente = request.getParameter("user");
            NegocioQ neg = new NegocioQ();
            cPaciente pac = neg.obtiene_paciente(rut_paciente);
            ArrayList consultorio = neg.lista_consultorio_pertenecia();
            Iterator it_con = consultorio.iterator();

            ArrayList historial_consultorios=neg.lista_historial_consultorio(rut_paciente);

            String titulo = " style=' background-color: #4169E1 ; color: white '  ";
            String datos = " style=' background-color: #87CEFA ; color: black '  ";
            cConsultorio con = new cConsultorio();

%>


<form name="form" action="<%=neg.getLocal()%>modifico_uh" method="POST" onsubmit="return validador();"   >
    <% if (pac.getRut_paciente().equals("")) {
                    out.write("<h2>Paciente no encontrado</h2>");
                } else {%>
    <input type="hidden" name="txt_rut" value="<%=pac.getRut_paciente()%>" />
    <input type="hidden" name="modo"  id="modo" value="97" />
    <input type="hidden" name="txt_consultorio_anterior"  id="txt_consultorio_anterior" value="<%=pac.getConsultorio()%>" />
     
    <table border="0"  cellpadding="5px" >
        <thead>
            <tr <%=titulo%> >
                <th colspan="4" >Información del Paciente <% out.write("" + pac.getRut_paciente());%>:::: <% out.write(" "+pac.getNombres_paciente()+" "+pac.getApellidop_paciente()); %></th>
            </tr>
        </thead>
        <tbody>
          
            <tr <%=datos%> >
                <td>Consultorio Pertenencia</td>
                <td colspan="3" >
                    <select name="cbo_consultorio" id="cbo_consultorio" style=" font-size:  medium  "  >
                        <option value="-1" >Seleccione...</option>
                        <%
                             while (it_con.hasNext()) {
                                 con = (cConsultorio) it_con.next();
                                 if (con.getId() == pac.getConsultorio()) {
                                     out.write("<option value='" + con.getId() + "' selected='selected' >" + con.getDescripcion() + "</option>");
                                 } else {
                                     out.write("<option value='" + con.getId() + "' >" + con.getDescripcion() + "</option>");
                                 }
                             }
                        %>
                    </select>
                </td>
            </tr>

            <tr <%=datos%> >
                <td colspan="4" ><br></td>
            </tr>
            <tr <%=datos%> >
                <td colspan="4" ><center><input class="btn btn-primary" type="submit" value="MODIFICAR" name="btn_mod" />  </center></td>
            </tr>
            
            <tr <%=datos%> >
                <td colspan="4" ><br></td>
            </tr>

        </tbody>
    </table>
                
    <% }%>
</form>