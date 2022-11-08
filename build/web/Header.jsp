<%-- 
    Document   : Header
    Created on : 10-may-2012, 9:51:41
    Author     : EseGamboa
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.Negocio"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
    HttpSession session1 = request.getSession();
    Negocio neg = new Negocio();

    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION<script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script>");

    } else {

        int obtiene_perfil = 0;
        String tipo_perfil = "" + session.getAttribute("usuario_perfil_descripcion");
        try {
            obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
        } catch (NumberFormatException ex) {
            obtiene_perfil = -1;
        }

        ArrayList see_admision_urgencia = new ArrayList();
        see_admision_urgencia.add(3);
        see_admision_urgencia.add(10);
        see_admision_urgencia.add(12);
        ArrayList see_admision_suam = new ArrayList();
        see_admision_suam.add(6);
        see_admision_suam.add(7);
        see_admision_suam.add(11);
        see_admision_suam.add(12);
        ArrayList see_duo_x_ingresar = new ArrayList();
        see_duo_x_ingresar.add(2);
        see_duo_x_ingresar.add(4);
        see_duo_x_ingresar.add(9);
        see_duo_x_ingresar.add(10);
        see_duo_x_ingresar.add(12);

        ArrayList see_visita = new ArrayList();
        see_visita.add(1);
        see_visita.add(2);
        see_visita.add(3);
        see_visita.add(4);
        see_visita.add(5);
        see_visita.add(8);
        see_visita.add(9);
        see_visita.add(10);
        see_visita.add(12);
        see_visita.add(13);
        see_visita.add(14);
        see_visita.add(15);
        see_visita.add(16);
        see_visita.add(17);
        see_visita.add(20);

        ArrayList see_informe = new ArrayList();
        see_informe.add(10);
        see_informe.add(11);
        see_informe.add(12);
        see_informe.add(1);
        ArrayList see_mantencion = new ArrayList();
        see_mantencion.add(10);
        see_mantencion.add(11);
        see_mantencion.add(12);

        ArrayList psicologo = new ArrayList();
        psicologo.add(18);

        ArrayList odontologo = new ArrayList();
        odontologo.add(20);


%>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ATENCION GERIATRICA INTEGRAL DOMICILIARIA</title>
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=neg.getLocal()%>css/style_tabla.css">
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=neg.getLocal()%>css/style.css">
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=neg.getLocal()%>css/style_diseno.css">
        <script type="text/javascript" src="<%=neg.getLocal()%>js/valida_rut.js"></script>
        <LINK REL="SHORTCUT ICON" HREF="<%=neg.getLocal()%>Iconos/flag_chile.ico">
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=neg.getLocal()%>js/menu/menu.css">


        <link rel="stylesheet" href="<%=neg.getLocal()%>js/jquery/jquery-ui-1.10.4.custom.css" />
        <script src="<%=neg.getLocal()%>js/jquery/jquery.js"></script>
        <script src="<%=neg.getLocal()%>js/jquery/jquery-ui.js"></script>
        <%            // <script src="<%=neg.getLocal() ajax.js"></script>
        %>

        <jsp:include page="css/boton_html.jsp" />


        <script>
            function creaObjetoAjax() { //Mayoría de navegadores
                var obj;
                if (window.XMLHttpRequest) {
                    obj = new XMLHttpRequest();
                } else { //para IE 5 y IE 6
                    obj = new ActiveXObject(Microsoft.XMLHTTP);
                }
                return obj;
            }
        </script>

    </head>

    <DIV id=wrapper>
        <DIV id=header>
            <DIV id="formArea" style=" height:400%" >

                <!-- Beginning of compulsory code below -->
                <div id="menu">
                    <ul class="menu">
                        <li><a href="<%=neg.getLocal()%>uh_visita.jsp"><span>HOSP. DOMICILIARIA</span></a>
                        </li>

                        <li><a href="#"><span>INGRESO</span></a>
                            <ul>

                                <li><a href="<%=neg.getLocal()%>admision_ugu.jsp" class="dir">Admision</a></li>

                            </ul>
                        </li>

                        </li>
                        <li><a href="<%=neg.getLocal()%>index_barthel/registers_index_barthel.jsp"><span>INDICE BARTHEL</span></a>
                        </li>

                        <%
                            if (see_visita.contains(obtiene_perfil)) {
                        %>

                        <li><a><span>EVOLUCIÓN </span></a>
                            <ul>
                                <li><a href="<%=neg.getLocal()%>visita_medica.jsp"><span>Medica</span></a>  
                                <li><a href="<%=neg.getLocal()%>visita_enfermeria.jsp"><span>Enfermeria</span></a>  
                                <li><a href="<%=neg.getLocal()%>uh_visita.jsp"><span>Nutrición</span></a> 
                                <li><a href="<%=neg.getLocal()%>uh_visita.jsp"><span>Kinesiologia</span></a> 
                                <li><a href="<%=neg.getLocal()%>uh_visita.jsp"><span>Terapeuta Ocupacional</span></a> 
                                    <% if (psicologo.contains(obtiene_perfil)) {%>
                                <li><a href="<%=neg.getLocal()%>uh_visita.jsp"><span>Psicólogo/a</span></a>
                                    <%}%>

                                    <% if (odontologo.contains(obtiene_perfil)) {%>
                                <li><a href="<%=neg.getLocal()%>sesion.jsp"><span>Odontologo/a</span></a>
                                    <%}%>
                                <li> <a href="<%=neg.getLocal()%>asistencia_social/social_ingreso.jsp"><span>Trabajadora Social</span></a>
                                <li><a href="<%=neg.getLocal()%>uh_visita.jsp"><span>Fonoaudiologa</span></a> 


                            </ul>
                        </li>
                        <%  }
                        %>


                        <li><a href="<%=neg.getLocal()%>prestaciones.jsp"><span>VISITAS</span></a>
                        </li>
                        <li><a href="<%=neg.getLocal()%>receta.jsp"><span>INDICACIONES</span></a>
                        </li>

                        <li><a href="<%=neg.getLocal()%>uh_paciente.jsp"><span>HISTORIAL</span></a>
                        </li>


                        <li><a href="<%=neg.getLocal()%>uh_informe.jsp"><span>INFORMES</span></a>
                        </li>

                        <%  if (see_mantencion.contains(obtiene_perfil)) {
                        %>
                        <li><a href="#"><span>MANTENCION</span></a>
                            <ul>
                                <li><a href="<%=neg.getLocal()%>uh_registro.jsp" class="dir">Registrar Usuario</a></li>
                                <li><a href="<%=neg.getLocal()%>mantencion_alta_medica.jsp" class="dir">Anular Alta-Méd</a></li>
                            </ul>

                        </li>
                        <%      }
                        %>

                        <li class="last">
                            <a href="#">
                                <span>
                                    <% out.write("" + session1.getAttribute("usuario_nombre"));%>
                                </span>
                            </a>
                            <ul>
                                <li><a href="#" onclick="javascript:window.open('<%=neg.getLocal()%>Password.jsp', '', 'width=400,height=300,status=no,scrolling=auto');" ><span>Modificar Clave</span></a></li>
                                <li><a href="<%=neg.getLocal()%>cierra_sesion"><span>Cerrar Sesion</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- / END -->
                <div id="copyright"><a href="http://apycom.com/"></a></div>

                <fieldset style=" height:50">
                    <br>

                    <%  }%>


