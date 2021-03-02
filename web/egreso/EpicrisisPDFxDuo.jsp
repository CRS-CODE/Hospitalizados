<%@page import="CapaDato.Epicrisis"%>
<%@page import="CapaNegocio.Select"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.*,java.net.URL,java.io.*,java.util.*,java.util.Vector,java.text.NumberFormat" %>
<%@ page import="java.util.Date"
         
         import="java.util.Iterator"%>
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



<%
    int id_duo = Integer.parseInt(request.getParameter("id_duo"));
//int id_epi=Integer.parseInt(request.getParameter("id_epi"));
    Select sele = new Select();
    int id_epi = sele.getIdEpicrisis(id_duo);
  
   
    Epicrisis epi = new Epicrisis();
    epi = sele.getEpicrisis(id_epi);
//int id_duo=epi.getId_duo();
    
   

   
    Locale currentLocale = new Locale("es", "CL");
    java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale);
    java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
    java.text.DateFormat formateadorFechaLarga = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);
    NumberFormat formateadorNumero = NumberFormat.getNumberInstance(currentLocale);

    Date hoy = new Date();

    int id_mes = 0;
    String palabra_mes = "", numero_ano = "", numero_mes = "";

    response.setContentType("application/pdf");
    Document document = new Document(PageSize.LETTER, 10, 10, 10, 10);
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PdfWriter writer = PdfWriter.getInstance(document, buffer);
    ServletContext context = config.getServletContext();
    String path = context.getRealPath("/");

    int SPACE_TITULO = 15;
    int SPACE_GRANDE = 55;
    int SPACE_NORMAL = 13;

    Font TEXT_TITULO = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0));
    Font TEXT_NORMAL = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, new Color(0, 0, 0));
    Font TEXT_NORMAL_DETALLE = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, new Color(5, 5, 5));
    Font TEXT_NORMAL_NEGRITA = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, new Color(0, 0, 0));
    Font TEXT_GRANDE_NEGRITA = FontFactory.getFont(FontFactory.TIMES_ROMAN, 83, Font.NORMAL, new Color(0, 0, 0));
//writer.setPageEvent(new PageNumbersWatermark());
    writer.setPageEvent(new LogoWatermark());
    document.open();

    int cuenta_liquidacion = 0, cuenta = 1;

    PdfPTable tabla_encabezado = new PdfPTable(5);

    int widths_tabla_encabezado[] = {10, 30, 30, 15, 15};
    tabla_encabezado.setWidthPercentage(80);
    tabla_encabezado.setWidths(widths_tabla_encabezado);
    tabla_encabezado.getDefaultCell().setBorder(0);

    /*tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
tabla_encabezado.getDefaultCell().setPadding(10);
tabla_encabezado.getDefaultCell().setColspan(5);
tabla_encabezado.addCell(new Phrase(SPACE_GRANDE,duo.getPaciente_rut(),TEXT_GRANDE_NEGRITA));*/
//tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.addCell(new Phrase(SPACE_TITULO, "EPICRISIS E INDICACIONES AL ALTA", TEXT_TITULO));

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_TITULO, " ", TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_TITULO, "Generado el: " + formateadorFechaLarga.format(hoy) + " " + formateadorHora.format(hoy) + "", TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setBorder(0);
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
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getPaciente_nombres() + " " + epi.getPaciente_apellidop() + " " + epi.getPaciente_apellidom(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getPaciente_rut(), TEXT_NORMAL));
    String sex = "";
    if (epi.getPaciente_sexo() == 1) {
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
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, " -- ", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Fecha", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Hora", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(2);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "", TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "--", TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, formateadorFecha.format(epi.getFecha_epicrisis()), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(1);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getHora_epicrisis(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setMinimumHeight(0.1f);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(2);
   

  

    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "RESUMEN DE LA HOSPITALIZACION:", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setMinimumHeight(100.99f);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getResumen_epicrisis(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setMinimumHeight(0.1f);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "EXAMENES-PROCEDIMIENTOS RELEVANTES PERTINENTES AL DIAGNÒSTICO:DIAGNÒSTICOS-TERAPEÙTICOS E INTERCONSULTAS", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setMinimumHeight(100.99f);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getExamen_epicrisis(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setMinimumHeight(0.1f);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "TRATAMIENTO, MEDICAMENTOS Y DOSIS ADMINISTRADOS DURANTE HOSPITALIZACIÒN", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setMinimumHeight(100.99f);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getDiagnostico_epicrisis(), TEXT_NORMAL));

    tabla_encabezado.getDefaultCell().setMinimumHeight(0.1f);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.lightGray);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "TRATAMIENTO NO FARMACOLOGICOS Y FRAMACOLOGICO Y OTRAS INDICACIONES AL ALTA", TEXT_NORMAL_NEGRITA));

    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.WHITE);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.getDefaultCell().setMinimumHeight(100.99f);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, epi.getIndicacion_epicrisis(), TEXT_NORMAL));

    
    
       
           

   
    
    tabla_encabezado.getDefaultCell().setMinimumHeight(80.99f);
    tabla_encabezado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla_encabezado.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.white);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL,
            " MEDICO RESPONSABLE " + epi.getNombre_usuario() + " " + epi.getApellidop_usuario() + " " + epi.getApellidom_usuario() + "\n", TEXT_NORMAL_NEGRITA));
//Paragraph p = new Paragraph(new Chunk("Click to say Hi").setAction(PdfAction.javaScript("app.alert('Mira tu');\r", writer)));
//      document.add(p);

    tabla_encabezado.getDefaultCell().setMinimumHeight(0.1f);
    tabla_encabezado.getDefaultCell().setBorder(0);
    tabla_encabezado.getDefaultCell().setBackgroundColor(Color.white);
    tabla_encabezado.getDefaultCell().setPadding(2);
    tabla_encabezado.getDefaultCell().setColspan(5);
    tabla_encabezado.addCell(new Phrase(SPACE_NORMAL, "Centro de Referencia de Salud Maipù <br> Av. Camino a Rinconada  Nº1001. Telefono:225746463     ", TEXT_NORMAL));

//AGREGO LAS TABLAS AL DOCUMENTO
    document.add(tabla_encabezado);

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