<%-- 
    Document   : alta_medica_carga
    Created on : 17-may-2012, 12:31:45
    Author     : EseGamboa
--%>
<%@page import="java.util.Vector"%>
<%@page import="CapaDato.cDiagnostico"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="CapaDato.cEnfermedad"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%

    int id_duo = Integer.parseInt(request.getParameter("duo"));
    NegocioQ neg = new NegocioQ();
    ArrayList lista_enfermedad = neg.obtiene_duo_enfermedad(id_duo);
    Iterator it_enf = lista_enfermedad.iterator();

    cDuo duo = neg.obtiene_duo(id_duo);
    String obtiene_fecha_hora_servidor = neg.obtiene_fecha_hora();
    cEnfermedad enf = new cEnfermedad();
    String Enfermedades_cronicas = " ";
    while (it_enf.hasNext()) {
        enf = (cEnfermedad) it_enf.next();
        Enfermedades_cronicas += " " + enf.getDescripcion() + " -";
    }
    Enfermedades_cronicas = Enfermedades_cronicas.substring(0, Enfermedades_cronicas.length() - 1);
    ArrayList diagnosticos = neg.lista_diagnostico(id_duo, " 1,2 ");
    Iterator it_dia = diagnosticos.iterator();
    String diagnostico = "";
    while (it_dia.hasNext()) {
        cDiagnostico dia = (cDiagnostico) it_dia.next();
        diagnostico += dia.getDescripcion_diagnostico() + " \n";
    }
%>


<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>


<body style="BACKGROUND-COLOR: #fff;">
    <form id="Form1" method="post"  action="<%=neg.getLocal()%>ingresa_alta">
        <input type="hidden" name="id_duo" value="<%=id_duo%>">
        <input type="hidden" name="txt_modo" value="1"  >
        <input type="hidden" id="txt_rut_usuario" name="txt_rut_usuario" value="<% out.write("" + session.getAttribute("usuario_rut"));%>" >
        <center><fieldset>
                <legend>Dar al Paciente de Alta</legend>
                <table>
                    <tr><th class="Datos" colspan="4" >EPICRISIS DE <% out.write("" + duo.getCama_descripcion());%></th></tr>
                    <tr><th class="Datos">Nombre:</th><td class="destacado" colspan="3"><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td></tr>
                    <tr><th class="Datos">Rut:</th><td class="destacado"><% out.write("" + duo.getRut_paciente());%></td><th class="Datos">Edad</th><td class="destacado"><% out.write("" + duo.getEdad() + " [" + duo.getFecha_nac() + "]");%></td></tr>
                    <tr><th class="Datos">Dias Hospitalizados</th> <td class="destacado"><% out.write("" + duo.getDias_cama());%> </td></tr>
                    <tr>
                        <th class="Datos">Fecha Ingreso:</th>
                        <td class="destacado"><% out.write("" + duo.getFecha_hora_ing_duo());%></td>
                        <th class="Datos">Fecha Egreso</th>
                        <td class="destacado">
                            <input style=" width: 150px;  " name="fecha_epi" id="fecha_epi" type="text" size="22" value="<% out.write("" + obtiene_fecha_hora_servidor);%>" readonly="readonly" >[Hora del Servidor]

                        </td>
                    </tr>
                    <tr>
                        <th class="Datos"> Diagnostico de Ingreso

                        </th>
                        <td class="destacado" colspan="3"><% out.write("" + diagnostico);%></td>
                    </tr>
                </table>
                <div id="Resultado" style=" overflow:auto;
                     padding-right: 15px; padding-top: 2px; padding-left: 15px; padding-bottom: 15px;
                     border-right: #6699CC 1px solid; border-top: #999999 1px solid;
                     border-left: #6699CC 1px solid; border-bottom: #6699CC 1px solid;
                     scrollbar-arrow-color : #999999; scrollbar-face-color : #666666;
                     scrollbar-track-color :#3333333 ;
                     height:auto; left: 100; top: 10; width: 95%">
                    <!-- Este DIV contendra la respuesta enviada por el Servlet -->
                    <table>
                        <tr>
                            <th class="Datos" colspan="2">Resumen Breve de Hospitalización</th>
                        </tr>
                        <tr>
                            <td class="Datos" colspan="2"><textarea id="resumen" name="resumen" cols="77" rows="4"></textarea></td>
                        </tr>
                         <tr>
                            <th class="Datos" colspan="2"> Exámenes realizados</th>
                        </tr>
                        <tr>
                            <td class="Datos" colspan="2"><textarea id="examenes" name="examenes" cols="77" rows="4"></textarea></td>
                        </tr>
                         <tr>
                            <th class="Datos" colspan="2"> Listado de medicamentos prescritos durante la hospitalización

                            </th>
                        </tr>
                        <tr>
                            <td class="Datos" colspan="2"><textarea id="medicamentos_prescritos" name="medicamentos_prescritos" cols="77" rows="4"></textarea></td>
                        </tr>
                       
                        <tr>
                            <th class="Datos" colspan="2">Diagnósticos Egreso</th>
                        </tr>
                        <tr>
                            <td class="Datos" colspan="2"><textarea id="diagnosticos"  name="diagnosticos" cols="77" rows="4"></textarea></td>
                        </tr>
                       
                        <tr>
                            <th class="Datos" colspan="2">Indicaciones al Alta</th>
                        </tr>
                        <tr>
                            <td class="Datos" colspan="2"><textarea id="indicaciones" name="indicaciones" cols="77" rows="4"></textarea></td>
                        </tr>
                       
                    </table>
                </div>
            </fieldset>
        </center>
    </form>
    <fieldset class="BUTTONS">
        <input type="button" value="Dar de Alta" onclick="Alta()">
    </fieldset>
    <script>
        function Alta()
        {
            if (document.getElementById('fecha_epi').value == '')
            {
                alert('Debe Ingresar la Fecha y Hora \n(Si no aparece vuelva a cargar esta pagina)');
                document.getElementById('fecha_epi').focus();
                return false;
            }
            if (document.getElementById('resumen').value == 0)
            {
                alert('Debe Ingresar el Resumen de la Hospitalización');
                document.getElementById('resumen').focus();
                return false;
            }
            if (document.getElementById('examenes').value == 0)
            {
                alert('Debe Ingresar Los Exámenes realizados');
                document.getElementById('examenes').focus();
                return false;
            }
            if (document.getElementById('diagnosticos').value == 0)
            {
                alert('Debe Ingresar Los Diagnósticos');
                document.getElementById('diagnosticos').focus();
                return false;
            }
            if (document.getElementById('indicaciones').value == 0)
            {
                alert('Debe Ingresar Las Indicaciones');
                document.getElementById('indicaciones').focus();
                return false;
            }
            if (document.getElementById('medicamentos_prescritos').value == 0)
            {
                alert('Debe Ingresar Listado de medicamentos prescritos durante la hospitalización');
                document.getElementById('medicamentos_prescritos').focus();
                return false;
            }

            if (confirm('Esta Seguro de dar de ALTA a este Paciente?')) {
                document.getElementById('Form1').submit();
            } else {
                return false;
            }

        }
    </script>
