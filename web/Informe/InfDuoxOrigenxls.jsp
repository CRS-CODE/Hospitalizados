<head>
    <style>
        table
        {
            mso-displayed-decimal-separator:"\.";
            mso-displayed-thousand-separator:"\,";
        }
        @page
        {
            margin:1.0in .75in 1.0in .75in;
            mso-header-margin:.5in;
            mso-footer-margin:.5in;
        }

        tr
        {
            mso-height-source:auto;
        }

        col
        {
            mso-width-source:auto;
        }

        .numberFormat
        {
            mso-style-parent:style0;
            mso-number-format:"\@";
            border:.5pt solid windowtext;
        }

        .numberFormatCurrency
        {
            mso-style-parent:style0;
            mso-number-format:"\@";
            text-align:right;
            border:.5pt solid windowtext;
        }

        .dateFormat
        {	
            mso-number-format:"dd-mm-yyyy";
            border:.5pt solid windowtext;
        }

        .headerItem
        {
            padding-top:1px;
            padding-right:1px;
            padding-left:1px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:Arial, sans-serif;
            mso-font-charset:0;
            mso-number-format:General;
            text-align:center;
            vertical-align:bottom;
            border:.5pt solid windowtext;
            background:silver;
            mso-pattern:auto none;
            white-space:nowrap;}	
        .headerItem2
        {
            padding-top:1px;
            padding-right:1px;
            padding-left:1px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:Arial, sans-serif;
            mso-font-charset:0;
            mso-number-format:General;
            text-align:center;
            vertical-align:bottom;
            border:.5pt solid windowtext;
            background:#178AEB;
            mso-pattern:auto none;
            white-space:nowrap;}
        .bodyItem
        {
            padding-top:1px;
            padding-right:1px;
            padding-left:1px;
            mso-ignore:padding;
            color:windowtext;
            font-size:10.0pt;
            font-weight:500;
            font-style:normal;
            text-decoration:none;
            font-family:Arial, sans-serif;
            mso-font-charset:0;
            mso-number-format:General;
            text-align:general;
            vertical-align:bottom;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;	
        }
    </style>
</head>
<%@ page import="java.sql.*,java.net.URL" %>
<%@ include file="../conexion.jsp"%>


<%@ page contentType="text/html; charset=iso-8859-1" 
         language="java" 
         import="java.sql.*"
         import="java.util.Iterator" 
         import="java.util.Properties"
         import="java.util.GregorianCalendar"
         import="java.util.Calendar"
         import="java.util.Date"
         import="java.text.SimpleDateFormat"
         import="java.util.*"
         %>
<%    String fecha1_dma = request.getParameter("fecha_inicio");
    String fecha2_dma = request.getParameter("fecha_fin");
    String dia1 = fecha1_dma.substring(0, 2);
    String mes1 = fecha1_dma.substring(3, 5);
    String ano1 = fecha1_dma.substring(6, 10);
    String dia2 = fecha2_dma.substring(0, 2);
    String mes2 = fecha2_dma.substring(3, 5);
    String ano2 = fecha2_dma.substring(6, 10);
    String fecha1 = dia1+ "-"+mes1+"-" + ano1;
    String fecha2 = dia2+ "-"+ mes2+"-"+ ano2;

    response.setContentType("application/vnd.ms-excel");
    response.setHeader("content-disposition", "attachment; filename=InfDuoxOrigen.xls");
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
    Locale currentLocale2 = new Locale("es", "Ch");
    java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);

%>

<table>
    <tr>
        <th class="headerItem2" colspan="4">INFORME DE DUO's POR ORIGEN</th>
    </tr>
    <tr>
        <th class="headerItem2" colspan="2">Fecha Emision</th>
        <th class="dateFormat" colspan="2"><%=formateadorFecha.format(new Date())%></th>

    </tr>
    <tr>
        <th class="headerItem2" colspan="4">Desde: <%out.println(fecha1_dma);%> Hasta: <%out.println(fecha2_dma);%></th>
    </tr>
    <tr>
        <td class="headerItem">ID DUO</td>
        <td class="headerItem">RUT PACIENTE</td>
        <td class="headerItem">DERIVADOR</td>
        <td class="headerItem">FECHA DUO</td>

    </tr>
    <%

        try {
            String consulta = "select D.id_duo,"
                    + "D.rut_paciente,"
                    + "DE.descripcion_derivador,"
                    + "D.fecha_duo"
                    + " from schema_uo.duo D, schema_uo.derivador DE "
                    + "where D.id_derivador=DE.id_derivador and "
                    + "D.estado_duo!=99 and "
                    + "D.fecha_duo between '" + fecha1 + "' and '" + fecha2 + "' order by D.fecha_duo";

            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
    %>
    <tr>
        <td class="bodyItem"><%=rs.getInt("id_duo")%></td>
        <td class="bodyItem"><%=rs.getString("rut_paciente")%></td>
        <td class="bodyItem"><%=rs.getString("descripcion_derivador")%></td>
        <td class="dateFormat"><%=formateadorFecha.format(rs.getDate("fecha_duo"))%></td>


    </tr>

    <%}
    } catch (SQLException ex) {%><td><%=ex%></td><%}

    %>
    <tr>
        <td></td>
    </tr>

    <tr>
        <td></td>
    </tr>

    <tr>
        <th class="headerItem2" colspan="2">Resumen Cuantitativo</th>
    </tr>
    <%        try {
            String consultaResumen = "select "
                    + "DE.descripcion_derivador,"
                    + "count(*) as total"
                    + " from schema_uo.duo D, schema_uo.derivador DE "
                    + "where D.id_derivador=DE.id_derivador and "
                    + "D.estado_duo!=99 and "
                    + "D.fecha_duo between '" + fecha1 + "' and '" + fecha2 + "' "
                    + "group by DE.descripcion_derivador";

            ResultSet rs_resumen = st.executeQuery(consultaResumen);

            while (rs_resumen.next()) {
    %>
    <tr>
        <td class="bodyItem"><%=rs_resumen.getString("descripcion_derivador")%></td>
        <td class="bodyItem"><%=rs_resumen.getInt("total")%></td>
    </tr>
    <%}
    } catch (SQLException ex) {%><td><%=ex%></td><%}

    %>
</table>
