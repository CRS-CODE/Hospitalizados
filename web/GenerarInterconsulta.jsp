<%-- 
    Document   : GenerarInterconsulta
    Created on : 28-nov-2021, 15:59:26
    Author     : Kairin
--%>

<%@page import="CapaDato.cInterconsulta"%>
<%@page import="java.util.List"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cConsultorio"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="Header.jsp"/>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<%NegocioQ neg = new NegocioQ();
    if (request.getParameter("id") != null) {
        int id = Integer.parseInt(request.getParameter("id"));
%>
<html>
    <LINK href="../css/style.css" type=text/css rel=stylesheet>    
    <meta HTTP-EQUIV="Refresh" CONTENT="0; URL=uh_visita.jsp">

    <head>
        <style>
            select{
                max-width: 70%;
            }  
        </style>
        <script language="javascript">
            function imprimir()
            {
                window.open('<%=neg.getLocal()%>sicPDF?id_sic=<%=id%>', '', 'titlebar=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1400,height=1050');

                    }
        </script>
    </head>
    <body onload="imprimir();">

    </body>
</html> 

<%
} else {%>
<%
    ArrayList consultorio = neg.lista_consultorio_pertenecia();
    int obtiene_duo = Integer.parseInt(request.getParameter("txt_manda_duo"));
    cDuo duo = neg.obtiene_duo(obtiene_duo);
    Iterator it_con = consultorio.iterator();%>
<form id="generarInterconsulta" name="generarInterconsulta" action="<% out.write(neg.getLocal() + "ingresar_sic");%>" onsubmit="return valida_form_das()" method="POST" >

    <div class="container">
        <div class="tab-content">
            <div class="container">
                <input id="duo" name="duo" value="<%=duo.getId_duo()%>" hidden >
                <legend class="center">Solicitud de Interconsulta o Derivación</legend>
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">Datos del Paciente</div>
                        <div class="panel-body">
                            <table class="table-responsive table-bordered" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>RUT :<%=duo.getRut_paciente()%></th>
                                        <th>Paciente: <%=duo.getNombres_paciente() + " " + duo.getApellidop_paciente()%></th>
                                        <th>Sexo: <%=duo.getSexo_descri()%></th>
                                        <th>Fecha de Nacimiento: <%=duo.getFecha_nac()%></th>
                                        <th>Edad: <%=duo.getEdad()%></th>
                                    </tr>
                                    <tr>
                                        <th>Domicilio:<%=duo.getDireccion()%> </th>
                                        <th>Comuna :<%=duo.getComuna_descri()%> </th>
                                        <th>Telefonos</th>
                                        <th><%=duo.getTelefono1() + "/" + duo.getTelefono2()%></th>
                                        <th>Email:<%=duo.getMail()%></th>
                                    </tr>
                                </thead>
                            </table>

                        </div>
                    </div>
                </div>
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">Datos Clinicos</div>
                        <div class="panel-body">
                            <table class="table-responsive" style="width: 100%">
                                <tr>
                                    <td>
                                        <label> Se deriva para atencion en </label>
                                    </td>
                                    <td>
                                        <span class="col-md-3 col-md-offset-1 "><i class="fa fa-chevron-down bigicon" style=" font-size: 12px" >Establecimiento:</i></span>

                                        <div class="col-md-6">
                                            <select name="establecimiento" id="establecimiento" >
                                                <option value="-1" >Seleccione...</option>
                                                <%
                                                    while (it_con.hasNext()) {
                                                        cConsultorio con = (cConsultorio) it_con.next();
                                                        out.write("<option value='" + con.getId() + "' >" + con.getDescripcion() + "</option>");

                                                    }
                                                %>
                                            </select>  
                                        </div>
                                    </td>
                                    <td>

                                        <div class="col-md-12">
                                            <textarea type="text"  rows="2" id="especialidad" name="especialidad" required="Debe ingresar la Especializado"  class="form-control" placeholder="Especialidad"  ></textarea>

                                        </div>  
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label> Se envia a consultar para </label>
                                    </td>
                                    <td>
                                        <span class="col-md-3 col-md-offset-1 "><i class="fa fa-chevron-down bigicon" style=" font-size: 12px" ></i></span>

                                        <div class="col-md-8">
                                            <select id="razonConsulta" name="razonConsulta" onchange="javascript:
                                                            var razon = document.getElementById('razonConsulta').value;
                                                    if (razon == 4) {
                                                        document.getElementById('otraRazon').style.display = 'block';
                                                    } else
                                                        document.getElementById('otraRazon').style.display = 'none';
                                                    "> 
                                                <option selected value="0"> Seleccione </option>
                                                <option  value="1"> Confirmacion Diagnostica </option>
                                                <option  value="2"> Realizar Tratamiento </option>
                                                <option  value="3"> Seguimiento </option>
                                                <option  value="4"> Otro </option>

                                            </select>  
                                        </div>
                                    </td
                                <tr><td><br><br> &nbsp;</td></tr>
                                <td>

                                    <div class="col-md-12" id="otraRazon" name="otraRazon" style=" display: none">
                                        <textarea type="text"  rows="2" id="especificarOtro" name="especificarOtro"  class="form-control" placeholder="Especificar Otros"  ></textarea>

                                    </div>  
                                </td>

                                </tr>
                                <tr><td><br><br> &nbsp;</td></tr>
                                <tr>
                                    <td colspan="3">

                                        <div class="col-md-12" id="otrodiagnosticosicmuestro" name="otrodiagnosticosicmuestro">
                                            <textarea type="text"  rows="5" id="diagnostico" name="diagnostico" required="Debe ingresar el Dignostico"  class="form-control" placeholder="Hipotesis diagnostica o diagnostico:"  ></textarea>
                                        </div>  

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="col-md-3 col-md-offset-1 "><i class="fa fa-chevron-down bigicon" style=" font-size: 12px" >¿Sospechas problema de salud AUGE?</i></span>

                                        <div class="col-md-8">
                                            <select id="sospechaProblema" name="sospechaProblema" > 
                                                <option selected value="0"> Seleccione </option>
                                                <option  value="1"> Si </option>
                                                <option  value="2"> No </option>

                                            </select>  
                                        </div>
                                    </td> 
                                    <td>

                                        <div class="col-md-12">
                                            <textarea type="text"  rows="2" id="especificarProblema" name="especificarProblema"   class="form-control" placeholder="Especificar Problema"  ></textarea>

                                        </div>  
                                    </td>
                                    <td>

                                        <div class="col-md-12">
                                            <textarea type="text"  rows="2" id="subproblema" name="subproblema"   class="form-control" placeholder="Subgrupo o subproblema de salud AUGE"  ></textarea>

                                        </div>  
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <div class="col-md-12" id="fundaentosDiagnostico" name="otrodiagnosticosicmuestro">
                                            <textarea type="text"  rows="5" id="fundaentosDiagnostico" name="fundaentosDiagnostico"  required class="form-control" placeholder="Fundamentos del Diagnostico"  ></textarea>
                                        </div>  

                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <div class="col-md-12" id="examenRealizados">
                                            <textarea type="text"  rows="5" id="examenRealizados" name="examenRealizados"  class="form-control" required placeholder="Examenes Realizados"  ></textarea>
                                        </div>  

                                    </td>
                                </tr>
                            </table>


                        </div>
                    </div>
                </div>
                <div class="center">
                    <table class="center">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <div class="center col-md-12 text-center">
                                        <button type="submit" class="center btn btn-primary btn-lg">Guardar </button>

                                        <button type="reset" class="center btn btn-primary btn-lg">Cancelar</button>
                                    </div>
                                </div> 
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="center">
                    <fieldset style=" height:800" ><legend>Indicaciones</legend>     

                        <%
                            List<cInterconsulta> listSIC = neg.getLisSIC(obtiene_duo);

                            if (listSIC.size() == 0) {

                        %>
                        <table>
                            <tr>
                                <td>
                                    No se encontraron registros de SIC 
                                </td>
                            </tr>
                        </table>



                        <% } else {
                            /**/
                        %>
                        <table class="ta " style='width: 100%' >
                            <thead>
                                <tr>
                                    <th>Fecha Registro</th>
                                    <th>Profesional</th>
                                    <th>Imprimir SIC</th>
                                </tr>
                            </thead>
                            <% for (cInterconsulta sic : listSIC) {

                            %>

                            <tr>

                                <td>
                                    <%=sic.getFechaRegistro()%>
                                </td>
                                <td>
                                    <%=sic.getUser()%>
                                <td>

                                <td>
                                    <p>  <img src="Imagenes/pdf.png" alt="" onclick="javascript:   window.open('<%=neg.getLocal()%>sicPDF?id_sic=<%=sic.getIdInterconsulta()%>', '', 'titlebar=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1400,height=1050');"/></p> 

                                </td>


                            </tr>
                            <%}%>
                        </table>

                    </fieldset>


                </div>
            </div>
        </div>
    </div>

</form>
<%}%>
<script type="text/javascript">
    function valida_form() {
        if (confirm("CONFIRMACION ! Esta Seguro que desea ingresar esta Información ? \n \n ")) {
        } else {
            return false;
        }
    }
</script>
<%}%>

<jsp:include page="Footer.jsp"/>
