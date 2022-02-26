<%-- 
    Document   : ingreso_diagnostico
    Created on : 27-ago-2012, 13:40:02
    Author     : EseGamboa
--%>


<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%
            response.setHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.addHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "private");
            response.setDateHeader("Expires", -1);
            String guarda_das = request.getParameter("txt_manda_das").toString().toUpperCase();

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

    <link rel="stylesheet" href="../js/jquery/jquery_ui.css" media="screen" type="text/css"/>
    <script type="text/javascript" src="../js/jquery/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery/jquery_ui.js"></script>



    <script type="text/javascript" src="../js/nuevo_ajax.js"></script>
</head>
<body onload="history.forward();displayMessage('../ingreso_suam/ingreso_diagnostico_carga.jsp?das=<% out.write("" + guarda_das);%>');">
</body>
</html>
<script type="text/javascript">
    messageObj = new DHTML_modalMessage();	// We only create one object of this class
    messageObj.setShadowOffset(15);	// Large shadow
    function displayMessage(url)
    {
        messageObj.setSource(url);
        messageObj.setCssClassMessageBox(false);
       // messageObj.setSize(780,665);
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
