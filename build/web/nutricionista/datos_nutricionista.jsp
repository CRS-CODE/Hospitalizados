<%-- 
    Document   : datos_kinesiologia
    Created on : 27-ago-2014, 16:13:32
    Author     : Informatica
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
    String guarda_duo = request.getParameter("txt_manda_duo").toString().toUpperCase();
%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>CRS Maipu- UHCE</title>
        <link REL="SHORTCUT ICON" HREF="../Iconos/logo_url.ico"/>
        <link href="../css/style.css" type="text/css" rel="stylesheet"/>
        <link href="../css/style_diseno.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" href="../Alert/css/demos.css" media="screen" type="text/css"/>
        <link rel="stylesheet" href="../Alert/css/modal-message.css" type="text/css"/>
        <script type="text/javascript" src="../Alert/js/ajax.js"></script>
        <script type="text/javascript" src="../Alert/js/modal-message.js"></script>
        <script type="text/javascript" src="../Alert/js/ajax-dynamic-content.js"></script>
        <script type="text/javascript" src="../js/nuevo_ajax.js"></script>
    </head>
    <body onload="history.forward();
            displayMessage('datos_nutricionista_carga.jsp?duo=<% out.write("" + guarda_duo);%>')">
    </body>
</html>
<script type="text/javascript">
    messageObj = new DHTML_modalMessage();	// We only create one object of this class
    messageObj.setShadowOffset(15);	// Large shadow
    function displayMessage(url)
    {
        messageObj.setSource(url);
        messageObj.setCssClassMessageBox(false);
        messageObj.setSize(820, 550);
        messageObj.setShadowDivVisible(true);	// Enable shadow for these boxes
        messageObj.display();
    }

    function closeMessage()
    {
        messageObj.close();
    }
</script>
