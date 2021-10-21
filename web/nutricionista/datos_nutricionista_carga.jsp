<%-- 
    Document   : datos_kinesiologia_carga
    Created on : 27-ago-2014, 16:13:55
    Author     : Informatica
--%>

<%@page import="CapaDato.cEvaRespiratoria"%>
<%@page import="CapaDato.cEvaNeurologia"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cEvaTraumatologia"%>
<%@page import="CapaDato.cSesionKine"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<%

    NegocioQ neg = new NegocioQ();

    int id_duo = Integer.parseInt(request.getParameter("duo"));
    ArrayList lista_sesion_nutri = neg.lista_sesion_nutricionista(id_duo);

    String titulo = " style=' background-color: #4169E1 ; color: white '  ";
    String datos = " style=' background-color: #87CEFA ; color: black '  ";

    int obtiene_perfil = 0;
    String tipo_perfil = "" + session.getAttribute("usuario_perfil_descripcion");
    try {
        obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
    } catch (NumberFormatException ex) {
        obtiene_perfil = -1;
    }

    ArrayList see_pdf = new ArrayList();
    see_pdf.add(1);
    see_pdf.add(2);
    see_pdf.add(4);
    see_pdf.add(8);
    see_pdf.add(9);
    see_pdf.add(10);
    see_pdf.add(12);
    see_pdf.add(13);
    see_pdf.add(14);
    see_pdf.add(15);
    see_pdf.add(16);
    see_pdf.add(17);
    see_pdf.add(18);

    ArrayList see_registrar = new ArrayList();

   
    see_registrar.add(14);


%>
<jsp:include page="../css/boton_html.jsp" />
<div style=" vertical-align: top  " align="right" >
    <form name="form_back" id="form_back" action="../datos/datos_paciente.jsp" method="POST">
        <input type="hidden" name="txt_manda_duo" value="<%=id_duo%>" />
        &nbsp;&nbsp;
        <a href="#" onclick="document.getElementById('form_back').submit()" >
            <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
        </a>
        <br>
    </form>
</div>

<table>
    <tr>
        <td rowspan="2" ><img src="../Imagenes/kinesiologia.png" width="48" height="48" alt="Nutricionista"/></td>
        <td <%=titulo%> >Datos de Nutricionista</td>
    </tr>
    <tr>
        <td <%=datos%>>Documentos</td>
    </tr>
</table>

<br><br>

<table>
    <tr valign="top" >
        <td>
            <fieldset>
                <legend>Sesión Nutricionista</legend>
                <table>
                    <tr>
                        <%    //
                            Iterator it_lista_sesion_kine = lista_sesion_nutri.iterator();
                            int contador = 0;
                            if (lista_sesion_nutri.size() == 0) {
                                out.write("<h3>Sin registro</h3>");
                            } else if (it_lista_sesion_kine.hasNext()) {
                                cSesionKine ses = (cSesionKine) it_lista_sesion_kine.next();
                                contador++;

                                out.write("<tr><td>");
                                out.write("<form  action='../PDF_sesion_nutricionista' method='POST' target='_blank' > "
                                        + "   <input type='hidden' value='" + id_duo + "' name='txt_duo'> "
                                        + "   <button name='boton' type='submit'><img src='../Imagenes/pdf.png'> " + ses.getFecha() + "</button>  "
                                        + " </form>  ");
                                out.write("</td>");
                            }


                        %>
                </table>  
            </fieldset>

        </td>

        </fieldset>

        </td>

    </tr>
</table>






<br>
<br>
<%    if (see_registrar.contains(obtiene_perfil)) {
%>
<fieldset>
    <legend>Registros</legend>
    <center>
        <TABLE>
            <TR>
                <TD>
                    <form name="form_fon"  action="sesion_nutricionista.jsp" method="POST"  >
                        <input type="hidden" name="txt_duo" value="<%=id_duo%>" > 
                        <input class="btn btn-primary" type="submit" value="Registrar Sesión Nutricionista" name="btn_ses" />
                    </form> 
                </TD>
            </tr>
        </table>
    </center>
</fieldset>
  <%}%>                      


