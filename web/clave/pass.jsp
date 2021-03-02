<%-- 
    Document   : calidad_pass
    Created on : 02-abr-2012, 14:10:39
    Author     : usuario
--%>


<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaNegocio.Negocio"%>
<%
response.setHeader( "Pragma", "no-cache" );
response.addHeader("Cache-Control","no-cache, must-revalidate");
response.addHeader("Cache-Control","post-check=0, pre-check=0");
response.addHeader( "Cache-Control", "no-store" );
response.addHeader("Cache-Control","private");
response.setDateHeader("Expires", -1);

String rep_rut=request.getParameter("rut");
Negocio neg=new Negocio();
cUsuario usu=neg.valida_Usuario_sinPass(rep_rut);
String rep_clave=usu.getPass_usuario();

%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>CRS Maipú- UHCE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <link REL="SHORTCUT ICON" HREF="../iconos/logo_url.ico"/>
        <link href="../css/style.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" href="../Alert/css/demos.css" media="screen" type="text/css"/>
        <link rel="stylesheet" href="../Alert/css/modal-message.css" type="text/css"/>
        <script type="text/javascript" src="../Alert/js/ajax.js"></script>
        <script type="text/javascript" src="../Alert/js/modal-message.js"></script>
        <script type="text/javascript" src="../Alert/js/ajax-dynamic-content.js"></script>
        <script type="text/javascript" src="../js/js_pass.js"></script>
    </head>
    <body onload="history.forward();displayMessage('../clave/pass_carga.jsp?rep_rut=<%=rep_rut%>&rep_pass=<%=rep_clave%>');">
    </body>
</html>

 <style type="text/css" media="all">
    .table { }
    .table .row { clear: left; }
    .table .row .cell{ float: left; }
</style>

<script type="text/javascript">
    messageObj = new DHTML_modalMessage();	// We only create one object of this class
    messageObj.setShadowOffset(15);	// Large shadow
    function displayMessage(url)
    {
        messageObj.setSource(url);
        messageObj.setCssClassMessageBox(false);
        messageObj.setSize(710,420);
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
<%%>
