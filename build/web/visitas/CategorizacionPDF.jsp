<%-- 
    Document   : CategorizacionPDF
    Created on : 03-11-2010, 04:23:27 PM
    Author     : Victor
--%>

<%@page import="Datos.AmbitoCRD"%>
<%@page import="Datos.GradoCRD"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaDato.Duo"%>
<%@page import="CapaDato.HistorialVisita"%>
<%@page import="CapaNegocio.Negocio"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%//@ include file="conexion.jsp" %>
<%@ include file="../conexion.jsp" %>
<%@ page import="java.sql.*,java.net.URL,java.io.*,java.util.*,java.util.Vector,java.text.NumberFormat" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.*,com.lowagie.text.*,com.lowagie.text.pdf.*,java.awt.Color"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="com.lowagie.text.pdf.PdfEncryptor" %>
<%@ page import="com.lowagie.text.pdf.PdfReader" %>
<%@ page import="com.lowagie.text.pdf.PdfWriter" %>
<%@ page import="com.lowagie.text.Chunk" %>
<%@ page import="com.lowagie.text.Document"%>
<%@ page import="com.lowagie.text.Element" %>
<%@ page import="com.lowagie.text.ExceptionConverter" %>
<%@ page import="com.lowagie.text.Font"%>
<%@ page import="com.lowagie.text.Image" %>
<%@ page import="com.lowagie.text.PageSize " %>
<%@ page import="com.lowagie.text.Paragraph"%>
<%@ page import="com.lowagie.text.Phrase "%>
<%@ page import="com.lowagie.text.Rectangle"%>
<%@ page import="com.lowagie.text.pdf.BaseFont"%>
<%@ page import="com.lowagie.text.pdf.PdfContentByte" %>
<%@ page import="com.lowagie.text.pdf.PdfGState"%>
<%@ page import="com.lowagie.text.pdf.PdfPTable" %>
<%@ page import="com.lowagie.text.pdf.PdfPageEventHelper"%>
<%@ page import="com.lowagie.text.pdf.PdfTemplate"%>
<%@ page import="com.lowagie.text.HeaderFooter"%>
<%@ page import="com.lowagie.text.Header"%>
<%@ page import="com.lowagie.text.pdf.PdfWriter"%>
<%@ page import="com.lowagie.text.pdf.*"%>

<%    int id_visita = Integer.parseInt(request.getParameter("id_visita"));
    Negocio conroller = new Negocio();
    HistorialVisita hv = new HistorialVisita();
    hv = conroller.getVisita(id_visita);
    int id_duo = hv.getId_duo();
    cDuo duo = new cDuo();
    NegocioQ neg = new NegocioQ();
    duo = neg.obtiene_duo(id_duo);
    GradoCRD gradocrd = new GradoCRD();
    AmbitoCRD ambitocrd = new AmbitoCRD();
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale);
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
    java.text.DateFormat formateadorFechaLarga = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
    NumberFormat formateadorNumero = NumberFormat.getNumberInstance(currentLocale);

    Date hoy = new Date();

    int id_mes = 0;
    String palabra_mes = "", numero_ano = "", numero_mes = "";

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=\"VisitaEnfermera.pdf\"");
    Document document = new Document(PageSize.LETTER, 10, 10, 10, 10);
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PdfWriter writer = PdfWriter.getInstance(document, buffer);
    ServletContext context = config.getServletContext();
    String path = context.getRealPath("/");
    int SPACE_TITULO = 15;
    int SPACE_NORMAL = 13;

    Font TEXT_TITULO = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0));
    Font TEXT_NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, new Color(0, 0, 0));
    Font TEXT_NORMAL_NEGRITA = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, new Color(0, 0, 0));
    Font TEXT_NORMAL_NEGRITA_BLANCA = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, new Color(2, 2, 2));
    Phrase cabecera = new Phrase(SPACE_NORMAL, "Unidad de Hospitalizacion de Media Estadia", TEXT_NORMAL_NEGRITA);
    Phrase pie = new Phrase(SPACE_NORMAL, "Pagina N°", TEXT_NORMAL_NEGRITA);
    HeaderFooter header = new HeaderFooter(cabecera, false);
    document.setHeader(header);

    document.open();

    Phrase titulo = new Phrase(SPACE_TITULO, "CATEGORIA DE PACIENTES" + id_visita, TEXT_TITULO);
    int cuenta_liquidacion = 0, cuenta = 1;

    PdfPTable tabla_encabezado = new PdfPTable(5);

    int widths_tabla_encabezado[] = {10, 30, 30, 15, 15};
    tabla_encabezado.setWidthPercentage(80);
    tabla_encabezado.setWidths(widths_tabla_encabezado);
    tabla_encabezado.getDefaultCell().setBorder(0);

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.addCell(titulo);

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_TITULO, " ", TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_TITULO, "Generado el: " + formateadorFechaLarga.format(hoy) + " " + formateadorHora.format(hoy) + "", TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setBorder(10);
    tabla_encabezado.getDefaultCell().setBorderWidthLeft(0.3f);
    tabla_encabezado.getDefaultCell().setBorderWidthRight(0.5f);
    tabla_encabezado.getDefaultCell().setBorderWidthBottom(0.5f);
    tabla_encabezado.getDefaultCell().setBorderWidthTop(0.5f);

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(3);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Paciente", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Rut", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Sexo", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(3);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, duo.getRut_paciente(), TEXT_NORMAL));
    String sex = "";
    if (duo.getSexo() == 1) {
        sex = "Femenino";
    } else {
        sex = "Masculino";
    }
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, sex, TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(2);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Edad", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Cama", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Fecha", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Hora", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(2);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, duo.getEdad(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, String.valueOf(hv.getDescripcion_cama()), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, duo.getFecha_duo(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, duo.getHora_duo(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setBorder(0);
    tabla_encabezado.getDefaultCell().setMinimumHeight(70.99f);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setVerticalAlignment(10);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(50);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "OBS: " + hv.getObs_visita(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setMinimumHeight(10);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Responsable EU. " + hv.getNombre_usuario() + " " + hv.getApellidop_usuario() + " " + hv.getApellidom_usuario(), TEXT_NORMAL_NEGRITA));

    PdfPTable tabla_cat = new PdfPTable(18);

    tabla_cat.setWidthPercentage(90);
//tabla_cat.set
//tabla_encabezado.setWidths(widths_tabla_encabezado);
//tabla_cat.getDefaultCell().setBorder(1);
    tabla_cat.getDefaultCell().setBorder(10);
    tabla_cat.getDefaultCell().setBorderWidthLeft(0.3f);
    tabla_cat.getDefaultCell().setBorderWidthRight(0.5f);
    tabla_cat.getDefaultCell().setBorderWidthBottom(0.5f);
    tabla_cat.getDefaultCell().setBorderWidthTop(0.5f);

    tabla_cat.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_cat.getDefaultCell().setPadding(2);
    tabla_cat.getDefaultCell().setColspan(9);
    tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Básico", TEXT_NORMAL_NEGRITA));

    tabla_cat.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_cat.getDefaultCell().setPadding(2);
    tabla_cat.getDefaultCell().setColspan(9);
    if (hv.getD1_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Control de signos vitales", TEXT_NORMAL_NEGRITA));
    }

    if (hv.getD2_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Control de Hemoglucotest", TEXT_NORMAL_NEGRITA));
    }

    if (hv.getD3_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Educación", TEXT_NORMAL_NEGRITA));
    }

    if (hv.getD4_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Kinesiología Motora.", TEXT_NORMAL_NEGRITA));
    }

    if (hv.getD5_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Fonoaudiología.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getD6_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Terapia Ocupacional.", TEXT_NORMAL_NEGRITA));
    }

    tabla_cat.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_cat.getDefaultCell().setPadding(2);
    tabla_cat.getDefaultCell().setColspan(9);
    tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Intermedio", TEXT_NORMAL_NEGRITA));

    tabla_cat.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_cat.getDefaultCell().setPadding(2);
    tabla_cat.getDefaultCell().setColspan(9);
    if (hv.getR1_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Control de signos.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getR2_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Control de Hemoglucotest.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getR3_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Kinesiología motora.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getR4_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Kinesiología respiratoria.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getR5_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Fonoaudiología.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getR6_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Educación.", TEXT_NORMAL_NEGRITA));
    }
    if (hv.getR7_visita_categorizacion() == 1) {
        tabla_cat.addCell(new Phrase(SPACE_NORMAL, "Tratamiento oral.", TEXT_NORMAL_NEGRITA));
    }

//AGREGO LAS TABLAS AL DOCUMENTO
    document.add(tabla_encabezado);

    document.add(tabla_cat);

//*****************************************************
    document.newPage();
//cuenta++;

    /*}
     }
     catch(SQLException ex){}*/
    document.close();
//Ahora hay que escribir los datos en memoria (buffer)
//hacia la salida del JSP

//Aqui se obtiene el stream de salida del JSP
    DataOutputStream output = new DataOutputStream(response.getOutputStream());

//Se transforma el buffer en arreglo de bytes  !!! EL BUFFER FIRMADO !!!!
    byte[] bytes = buffer.toByteArray();

//Se indica el tamaño del arreglo de bytes
    response.setContentLength(bytes.length);

//Se escriben los bytes hacia la salida
    for (int i = 0; i < bytes.length; i++) {
        output.writeByte(bytes[i]);
    }
    output.flush();

%>