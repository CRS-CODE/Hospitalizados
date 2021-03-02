<%-- 
    Document   : Footer
    Created on : 10-may-2012, 9:51:49
    Author     : EseGamboa
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%
    
            Date d = new Date();
            GregorianCalendar bb = new GregorianCalendar();
            bb.setTime(d);
            int ano = bb.get(Calendar.YEAR);

%>
</fieldset>
</DIV>
<div id="footer">
    <div align="center" style="bottom:0px;width: 100%">
        <FONT color="#ffffff" size="+1">
        </FONT>
    </div>
    Centro de Referencia de Salud Maip&uacute; - <%=ano%>
</div>

</DIV>
</DIV>
</html>
