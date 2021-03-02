<%-- 
    Document   : admision_suam_carga
    Created on : 23-ago-2012, 15:24:58
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cDas"%>
<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaDato.cDiagnostico"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaDato.cDau"%>
<%@page import="CapaNegocio.NegocioQ"%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
            HttpSession session1 = request.getSession();
            NegocioQ neg = new NegocioQ();
            int tipo_dau = Integer.parseInt(request.getParameter("tipo"));
            int dau = Integer.parseInt(request.getParameter("id_dau"));

            ArrayList derivador = neg.lista_derivador_suam();
            Iterator it_der = derivador.iterator();

            ArrayList medicos = neg.lista_medicos_suam();
            Iterator it_med = medicos.iterator();

            cDau mar = new cDau();
            String titulo_tabla = "";
            int modo_tabla = 0;
            ArrayList motivos_dau = new ArrayList();
            String motivos_asociados = "";


            if (tipo_dau == 1) {
                mar = neg.obtiene_paciente_segun_dau(dau);
                titulo_tabla = "DAU N° " + dau;
                modo_tabla = 1;
                motivos_dau = neg.lista_motivos_consulta_segun_dau(dau, 1);
            } else if (tipo_dau == 2) {
                mar = neg.obtiene_paciente_segun_dauNN(dau);
                titulo_tabla = "DAU NN N° " + dau;
                modo_tabla = 2;
                motivos_dau = neg.lista_motivos_consulta_segun_dau(dau, 2);
            }
            Iterator it_mot = motivos_dau.iterator();
            while (it_mot.hasNext()) {
                cDiagnostico mot = (cDiagnostico) it_mot.next();
                motivos_asociados += " " + mot.getDescripcion_diagnostico() + " -";
            }
            if (motivos_asociados.contains("-")) {
                motivos_asociados = motivos_asociados.substring(0, motivos_asociados.length() - 1);
            }


            


            String titulo = " style=' background-color: #4169E1 ; color: white '  ";
            String datos = " style=' background-color: #87CEFA ; color: black '  ";

            ArrayList lista_camillas = neg.lista_camillas_actuales();
            Iterator it = lista_camillas.iterator();
            Iterator it_cam = lista_camillas.iterator();

            boolean sw_existe_actualmente = false;
            String paciente_camilla = "";

            while (it_cam.hasNext()) {
                cDas das = (cDas) it_cam.next();
                String EL_DAU_ID = das.getDau_nn_id() + "";

                if (tipo_dau == 1) {
                    EL_DAU_ID = das.getDau_id() + "";
                } else {
// SI ES nn
                    EL_DAU_ID = das.getDau_nn_id() + "";
                }

                if (EL_DAU_ID.equalsIgnoreCase(mar.getId_dau() + "")) {
                    paciente_camilla = " " + das.getCamilla_descri();
                    sw_existe_actualmente = true;
                }
            }


            if (sw_existe_actualmente) {
                out.write("<h2><font color='red' >Este paciente ya esta asociado a " + paciente_camilla + "</font></h2>");
            } else {

%>


<form name="form_das" action="<% out.write(neg.getLocal() + "ingreso_suam");%>" onsubmit="return valida_form_das()" method="POST" >
    <input id="txt_modo" name="txt_modo" type="hidden" value="1" >
    <input id="txt_dau_id" name="txt_dau_id" type="hidden" value="<% out.write("" + mar.getId_dau());%>" >
    <input id="txt_dau_id_tipo" name="txt_dau_id_tipo" type="hidden" value="<% out.write("" + modo_tabla);%>" >
    <input id="txt_paciente" name="txt_paciente" type="hidden" value="<% out.write("" + mar.getRut_paciente());%>" >
    <table border="1" cellspacing="1" cellpadding="3" >
        <thead>
            <tr>
                <th colspan="2" align="left" <%=titulo%> ><% out.write("" + titulo_tabla);%> </th>
                <th colspan="7" align="center" <%=titulo%> >DATOS DEL PACIENTE </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td <%=titulo%> ><b>  Rut:</b> </td>
                <td <%=datos%> ><% out.write("" + mar.getRut_paciente());%></td>
                <td <%=titulo%> ><b>Nombre:</b></td>
                <td colspan="2" <%=datos%>  ><% out.write("" + mar.getNombres_paciente() + " " + mar.getApellidop_paciente() + " " + mar.getApellidom_paciente());%></td>
                <td <%=titulo%> ><b>Sexo: </b></td>
                <td  <%=datos%> ><% out.write("" + mar.getSexo_descri());%></td>
                <td <%=titulo%> ><b>Edad:</b> </td>
                <td  <%=datos%>><% out.write("" + mar.getEdad());%></td>
            </tr>
            <% if (modo_tabla == 2) {
            %>
            <tr>
                <td colspan="2" <%=titulo%> ><b>Descripción:</b> </td>
                <td colspan="7" <%=datos%> ><% out.write("" + mar.getPaciente_descripcion());%></td>
            </tr>
            <%      }
            %>
            <tr>
                <td colspan="2" <%=titulo%> ><b>Fecha Admisión:</b> </td>
                <td colspan="7" <%=datos%> ><% out.write("" + mar.getDau_fecha_ingreso());%></td>
            </tr>
            <tr>
                <td colspan="3" <%=titulo%> ><b>Motivos de Consulta:</b> </td>
                <td colspan="6" <%=datos%> ><% out.write("" + motivos_asociados);%></td>
            </tr>

            <tr>
                <th colspan="9" align="center" <%=titulo%> >DATOS DE INGRESO SUAM </th>
            </tr>

            <tr>
                <td colspan="2" <%=titulo%> ><b>Derivador:</b> </td>
                <td colspan="3" >
                    <select name="cbo_derivador" id="cbo_derivador" style=" font-size:  medium   " >
                        <option value="-1" >Seleccione Derivador...</option>
                        <%
                                        while (it_der.hasNext()) {
                                            cConsultorio aux = (cConsultorio) it_der.next();
                                            out.write(" <option value=" + aux.getId() + " >" + aux.getDescripcion() + "</option>");
                                        }
                        %>
                    </select>
                </td>
                <td colspan="2" <%=titulo%> ><b>Camilla:</b> </td>
                <td colspan="2" >
                    <select name="cbo_camilla" id="cbo_camilla" style=" font-size:  medium   " >
                        <option value="-1" >Seleccione Camilla...</option>
                        <%
                                        while (it.hasNext()) {
                                            cDas aux = (cDas) it.next();
                                            if (aux.getId_das() == 0) {
                                                out.write(" <option value=" + aux.getCamilla() + " >" + aux.getCamilla_descri() + "</option>");
                                            }
                                        }
                        %>
                    </select>
                </td>
            </tr>


            <tr>
                <td colspan="2" <%=titulo%> ><b>Médico:</b> </td>
                <td colspan="5" >
                    <select name="cbo_medico" id="cbo_medico" style=" font-size:  medium   " >
                        <option value="-1" >Seleccione Médico...</option>
                        <%
                                        while (it_med.hasNext()) {
                                            cUsuario aux = (cUsuario) it_med.next();
                                            out.write(" <option value=" + aux.getRut_usuario() + " >" + aux.getApellidop_usuario() + " " + aux.getNombre_usuario() + " " + aux.getNombre_usuario() + "</option>");
                                        }
                        %>
                    </select>
                </td>
                <td colspan="2" ></td>
                <td colspan="2" >
                </td>
            </tr>

            <tr>
                <td colspan="9" align="CENTER" >
                    <br>
                    <input class="btn btn-primary" type="submit" value="REGISTRAR PACIENTE" name="btn_sumit" />
                    <br>&nbsp;
                </td>
            </tr>
        </tbody>
    </table>
</form>

<%
            }
%>










