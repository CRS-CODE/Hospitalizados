<%@ page contentType="application/vnd.ms-excel; charset=iso-8859-1"
         pageEncoding="iso-8859-1"
         language="java"
         import="java.sql.*"
         import="java.util.*"
         import="java.text.SimpleDateFormat"
         import="java.text.DateFormat"
         import="java.text.ParseException"
%>
<%@ include file="../conexion.jsp"%>
<%!
    // Escapa texto para que caracteres como < > & no rompan el HTML/Excel
    private String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    // Convierte "dd-mm-yyyy" o "dd/mm/yyyy" a java.sql.Date; devuelve null si es inválida
    private java.sql.Date parseFecha(String txt) {
        if (txt == null || txt.trim().length() < 10) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            return new java.sql.Date(sdf.parse(txt.trim().substring(0, 10).replace('/', '-')).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    private void cerrar(AutoCloseable c) {
        if (c != null) {
            try { c.close(); } catch (Exception ignore) { }
        }
    }
%>
<%
    response.setHeader("Content-Disposition", "attachment; filename=InfDuoxOrigen.xls");

    String fecha1_dma = request.getParameter("fecha_inicio");
    String fecha2_dma = request.getParameter("fecha_fin");

    java.sql.Date fechaIni = parseFecha(fecha1_dma);
    java.sql.Date fechaFin = parseFecha(fecha2_dma);

    // Fecha fin exclusiva (fin + 1 día) para incluir todo el último día aunque fecha_duo sea timestamp
    java.sql.Date fechaFinExcl = null;
    if (fechaFin != null) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaFin);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        fechaFinExcl = new java.sql.Date(cal.getTimeInMillis());
    }

    DateFormat formateadorFecha = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("es", "CL"));

    String errorGeneral = null;
    if (fechaIni == null || fechaFin == null) {
        errorGeneral = "Fechas inválidas. Formato esperado: dd-mm-aaaa";
    } else if (st == null) {
        errorGeneral = "No se pudo conectar a la base de datos (revisar conexion.jsp y log de Tomcat)";
        log("InfDuoxOrigenxls: 'st' es null, falló la conexión en conexion.jsp");
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <style>
        table { mso-displayed-decimal-separator:"\."; mso-displayed-thousand-separator:"\,"; }
        @page { margin:1.0in .75in 1.0in .75in; mso-header-margin:.5in; mso-footer-margin:.5in; }
        tr { mso-height-source:auto; }
        col { mso-width-source:auto; }
        .dateFormat { mso-number-format:"dd-mm-yyyy"; border:.5pt solid windowtext; }
        .headerItem, .headerItem2 {
            padding:1px; mso-ignore:padding; color:windowtext; font-size:10.0pt; font-weight:700;
            font-family:Arial, sans-serif; mso-number-format:General; text-align:center;
            vertical-align:bottom; border:.5pt solid windowtext; mso-pattern:auto none; white-space:nowrap;
        }
        .headerItem  { background:silver; }
        .headerItem2 { background:#178AEB; }
        .bodyItem {
            padding:1px; mso-ignore:padding; color:windowtext; font-size:10.0pt; font-weight:500;
            font-family:Arial, sans-serif; mso-number-format:General; vertical-align:bottom;
            border:.5pt solid windowtext; white-space:nowrap;
        }
        .textFormat { mso-number-format:"\@"; }
        .error { color:red; font-weight:700; }
    </style>
</head>
<body>
<table>
    <tr><th class="headerItem2" colspan="4">INFORME DE DUO's POR ORIGEN</th></tr>
    <tr>
        <th class="headerItem2" colspan="2">Fecha Emision</th>
        <th class="dateFormat" colspan="2"><%= formateadorFecha.format(new java.util.Date()) %></th>
    </tr>
    <tr>
        <th class="headerItem2" colspan="4">Desde: <%= esc(fecha1_dma) %> Hasta: <%= esc(fecha2_dma) %></th>
    </tr>
<%
    if (errorGeneral != null) {
%>
    <tr><td class="error" colspan="4"><%= esc(errorGeneral) %></td></tr>
<%
    } else {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = st.getConnection();

            // ---------- Detalle ----------
%>
    <tr>
        <td class="headerItem">ID DUO</td>
        <td class="headerItem">RUT PACIENTE</td>
        <td class="headerItem">DERIVADOR</td>
        <td class="headerItem">FECHA DUO</td>
    </tr>
<%
            String consulta =
                  "SELECT D.id_duo, D.rut_paciente, DE.descripcion_derivador, D.fecha_duo "
                + "FROM schema_uo.duo D "
                + "JOIN schema_uo.derivador DE ON D.id_derivador = DE.id_derivador "
                + "WHERE D.estado_duo <> 99 "
                + "AND D.fecha_duo >= ? AND D.fecha_duo < ? "
                + "ORDER BY D.fecha_duo";

            ps = con.prepareStatement(consulta);
            ps.setDate(1, fechaIni);
            ps.setDate(2, fechaFinExcl);
            rs = ps.executeQuery();

            int filas = 0;
            while (rs.next()) {
                filas++;
                java.sql.Date f = rs.getDate("fecha_duo");
%>
    <tr>
        <td class="bodyItem"><%= rs.getInt("id_duo") %></td>
        <td class="bodyItem textFormat"><%= esc(rs.getString("rut_paciente")) %></td>
        <td class="bodyItem"><%= esc(rs.getString("descripcion_derivador")) %></td>
        <td class="dateFormat"><%= f != null ? formateadorFecha.format(f) : "" %></td>
    </tr>
<%
            }
            if (filas == 0) {
%>
    <tr><td class="bodyItem" colspan="4">Sin registros para el período</td></tr>
<%
            }
            cerrar(rs); rs = null;
            cerrar(ps); ps = null;

            // ---------- Resumen ----------
%>
    <tr><td></td></tr>
    <tr><td></td></tr>
    <tr><th class="headerItem2" colspan="2">Resumen Cuantitativo</th></tr>
    <tr>
        <td class="headerItem">DERIVADOR</td>
        <td class="headerItem">TOTAL</td>
    </tr>
<%
            String consultaResumen =
                  "SELECT DE.descripcion_derivador, COUNT(*) AS total "
                + "FROM schema_uo.duo D "
                + "JOIN schema_uo.derivador DE ON D.id_derivador = DE.id_derivador "
                + "WHERE D.estado_duo <> 99 "
                + "AND D.fecha_duo >= ? AND D.fecha_duo < ? "
                + "GROUP BY DE.descripcion_derivador "
                + "ORDER BY total DESC";

            ps = con.prepareStatement(consultaResumen);
            ps.setDate(1, fechaIni);
            ps.setDate(2, fechaFinExcl);
            rs = ps.executeQuery();

            int totalGeneral = 0;
            while (rs.next()) {
                int total = rs.getInt("total");
                totalGeneral += total;
%>
    <tr>
        <td class="bodyItem"><%= esc(rs.getString("descripcion_derivador")) %></td>
        <td class="bodyItem"><%= total %></td>
    </tr>
<%
            }
%>
    <tr>
        <td class="headerItem">TOTAL</td>
        <td class="headerItem"><%= totalGeneral %></td>
    </tr>
<%
        } catch (SQLException ex) {
            log("Error en InfDuoxOrigenxls.jsp", ex);
%>
    <tr><td class="error" colspan="4">Error al generar el informe: <%= esc(ex.getMessage()) %></td></tr>
<%
        } finally {
            cerrar(rs);
            cerrar(ps);
            cerrar(st);
            cerrar(con);  // libera la conexión (evita agotar max_connections)
        }
    }
%>
</table>
</body>
</html>
