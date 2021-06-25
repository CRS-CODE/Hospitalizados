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
            text-align: center;
            border:.5pt solid windowtext;
        }

        .dateFormat
        {
            mso-number-format:"Short Date";
            border:.5pt solid windowtext;
            text-align: center;
        }

        .dateFormat_fechahora
        {
            mso-number-format:"dd-MM-yyyy hh:mm:ss";
            border:.5pt solid windowtext;
            text-align: center;
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
            color: white ;
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
            background:blue;
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
            text-align: center;
            vertical-align:bottom;
            border:.5pt solid windowtext;
            mso-background-source:auto;
            mso-pattern:auto;
            white-space:nowrap;
        }

        .bodyItem_2
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
            text-align: center;
            vertical-align:bottom;
            border:.5pt solid windowtext;
            mso-background-source: green;
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
         import="java.util.*"
         %>
<%    response.setContentType("application/vnd.ms-excel");
    response.setHeader("content-disposition", "attachment; filename=InfDuosEnUnidad.xls");

    Date hoy = new Date();
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
    Locale currentLocale2 = new Locale("es", "Ch");
    java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);


%>

<table>
    <tr>
      
    </tr>
    <tr>
        <th class="headerItem2" colspan="2">Fecha Emision</th>
        <th class="dateFormat_fechahora" colspan="2"><% out.write(formateadorFecha.format(hoy) + " " + formateadorHora.format(hoy) + " ");%></th>
        <th class="headerItem2" colspan="10">INFORME DE DUO's AÚN EN LA UNIDAD&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
    </tr>
    <tr>
        <td class="headerItem">CAMA ACTUAL</td>
        <td class="headerItem">N°DUO</td>
        <td class="headerItem">FECHA DUO</td>
        <td class="headerItem">CANTIDAD DIAS HASTA HOY</td>
        <td class="headerItem">RUT PACIENTE</td>
        <td class="headerItem">NOMBRE COMPLETO PACIENTE</td>
        <td class="headerItem">FECHA NACIMIENTO</td>
        <td class="headerItem">EDAD</td>
        <td class="headerItem">ORIGEN</td>
        <td class="headerItem">ESTADO</td>

        <td class="headerItem">FECHA EPICRISIS</td>
        <td class="headerItem">CANTIDAD DIAS DUO / EPI</td>
        <td class="headerItem">PREVISION</td>
        <td class="headerItem">TRAMO</td>    
    </tr>
    <% try {
            Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String consulta = "SELECT  "
                    + "  *  "
                    + " FROM schema_uhd.vista_uoce_actual VU ORDER BY VU.id_cama,VU.paciente_rut;";

            ResultSet rs = st1.executeQuery(consulta);

            String consulta_cama = "Select id_cama,descripcion_cama,MAR.id_sala,MAR.descripcion_sala from schema_uhd.cama DIS JOIN "
                    + "schema_uhd.sala MAR ON (DIS.id_sala=MAR.id_sala) WHERE "
                    + "MAR.estado_sala IN (1) and DIS.estado_cama IN (1)";
            ResultSet rs_cam = st.executeQuery(consulta_cama);
            boolean sw_ocupada = false;
            String cama_siguiente = "";
            while (rs_cam.next()) {
                cama_siguiente = rs_cam.getInt("id_cama") + "";
                sw_ocupada = false;
                rs.beforeFirst();
                while (rs.next()) {

                    if (cama_siguiente.equalsIgnoreCase(rs.getString("id_cama"))) {
                        sw_ocupada = true;
    %>
    <tr>
        <td class="bodyItem"><%=rs.getString("id_cama")%></td>
        <td class="bodyItem"><%=rs.getString("id_duo")%></td>
        <td class="dateFormat"><%=rs.getString("fecha_duo")%></td>
        <%
            String hasta_hoy = rs.getString("hasta_hoy");
            hasta_hoy = hasta_hoy.replace("days", "dias");
            hasta_hoy = hasta_hoy.replace("mons", "meses");
            hasta_hoy = hasta_hoy.replace("years", "años");
            hasta_hoy = hasta_hoy.replace("day", "dia");
            hasta_hoy = hasta_hoy.replace("mon", "mes");
            hasta_hoy = hasta_hoy.replace("year", "año");
        %>
        <td class="numberFormat"><%=hasta_hoy%></td>
        <td class="bodyItem"><%=rs.getString("paciente_rut")%></td>
        <td class="bodyItem"><%=rs.getString("paciente_nombres") + " " + rs.getString("paciente_apellidop") + " " + rs.getString("paciente_apellidom")%></td>
        <td class="dateFormat"><%=rs.getString("paciente_fecha_nac")%></td>
        <%
            String edad = rs.getString("paciente_edad");
            edad = edad.replace("days", "dias");
            edad = edad.replace("mons", "meses");
            edad = edad.replace("years", "años");
            edad = edad.replace("day", "dia");
            edad = edad.replace("mon", "mes");
            edad = edad.replace("year", "año");
        %>
        <td class="dateFormat"><%=edad%></td>
        <td class="bodyItem"><%=rs.getString("descripcion_derivador")%></td>
        <td class="bodyItem"><%=rs.getString("estado")%></td>


        <% if (rs.getString("fecha_epicrisis") == null) {
        %>
        <td class="bodyItem"><%=""%></td>
        <%
        } else {


        %>
        <td class="dateFormat"><%=rs.getString("fecha_epicrisis")%></td>
        <%   } %>


        <%  /**
             * ********************
             */%>
        <% if (rs.getString("qdias_epi_duo") == null) {
        %>
        <td class="bodyItem"><%=""%></td>
        <%
        } else {
            String qdias_epi_duo = rs.getString("qdias_epi_duo");
            qdias_epi_duo = qdias_epi_duo.replace("days", "dias");
            qdias_epi_duo = qdias_epi_duo.replace("mons", "meses");
            qdias_epi_duo = qdias_epi_duo.replace("years", "años");
            qdias_epi_duo = qdias_epi_duo.replace("day", "dia");
            qdias_epi_duo = qdias_epi_duo.replace("mon", "mes");
            qdias_epi_duo = qdias_epi_duo.replace("year", "año");
        %>
        <td class="numberFormat"><%=qdias_epi_duo%></td>
        <%
            }%>

        <%  /**
             * ********************
             */%>

        <td class="bodyItem"><%=rs.getString("codigo_fonasa_descripcion")%></td>
        <td class="bodyItem"><%=rs.getString("tramo_prevision_paciente")%></td>
    </tr>

    <% }
            }

            if (!sw_ocupada) {
                out.write("<tr class=\"bodyItem_2\" > "
                        + " <td class='bodyItem'>" + cama_siguiente + "</td>"
                        + " <td class='bodyItem'></td>"
                        + " <td class='dateFormat'></td>"
                        + " <td class='numberFormat'></td>"
                        + " <td class='bodyItem'>CAMA LIBRE</td>"
                        + " <td class='bodyItem'></td>"
                        + " <td class='dateFormat'></td>"
                        + " <td class='dateFormat'></td>"
                        + " <td class='bodyItem'></td>"
                        + " <td class='bodyItem'></td>"
                        + " <td class='dateFormat'></td>  "
                        + " <td class='numberFormat'></td>"
                        + " <td class='bodyItem'></td>"
                        + " <td class='bodyItem'> </td> </tr>");
            }

        }

    } catch (SQLException ex) {%><td><%=ex%></td><%}
    %>
</table>
