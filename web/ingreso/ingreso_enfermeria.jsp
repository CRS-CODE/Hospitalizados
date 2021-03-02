<%-- 
    Document   : ficha_ingreso
    Created on : 31-oct-2011, 21:19:06
    Author     : Dis
--%>
<%@page import="CapaNegocio.Negocio"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
         
            String guarda_duo = request.getParameter("txt_manda_duo").toString().toUpperCase();
            /* 13-06-2013 */
            HttpSession session1 = request.getSession();
            String obtiene_usuario = "" + session1.getAttribute("usuario_rut");
            Negocio neg = new Negocio();
            if (obtiene_usuario.equalsIgnoreCase("null")) {
                out.write("<script> alert('Su sesion ha expirado'); ");
                out.write(" window.location = '" + neg.getLocal() + "cierra_sesion' ");
                out.write("</script>");
            }
            /* 13-06-2013 */
%>

<head>
    <title>CRS Maipu- UHCE</title>
    <link REL="SHORTCUT ICON" HREF="../Iconos/logo_url.ico"/>
    <link href="../css/style.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="../Alert/css/demos.css" media="screen" type="text/css"/>
    <link rel="stylesheet" href="../Alert/css/modal-message.css" type="text/css"/>
    <script type="text/javascript" src="../Alert/js/ajax.js"></script>
    <script type="text/javascript" src="../Alert/js/modal-message.js"></script>
    <script type="text/javascript" src="../Alert/js/ajax-dynamic-content.js"></script>
    <script type="text/javascript" src="../js/nuevo_ajax.js"></script>
</head>
<body onload="history.forward();displayMessage('../ingreso/ingreso_enfermeria_carga.jsp?duo=<% out.write("" + guarda_duo);%>')">
</body>
</html>
<script type="text/javascript">
    messageObj = new DHTML_modalMessage();	// We only create one object of this class
    messageObj.setShadowOffset(15);	// Large shadow
    function displayMessage(url)
    {
        messageObj.setSource(url);
        messageObj.setCssClassMessageBox(false);
        messageObj.setSize(780,740);
        messageObj.setShadowDivVisible(true);	// Enable shadow for these boxes
        messageObj.display();
    }
    function displayStaticMessage(messageContent,cssClass)
    {
        messageObj.setHtmlContent(messageContent);
        messageObj.setSize(300,150);
        messageObj.setCssClassMessageBox(cssClass);
        messageObj.setSource(false);	// no html source since we want to use a static message here.
        messageObj.setShadowDivVisible(false);	// Disable shadow for these boxes
        messageObj.display();
    }
    function closeMessage()
    {
        messageObj.close();
    }
</script>
<%
%>
