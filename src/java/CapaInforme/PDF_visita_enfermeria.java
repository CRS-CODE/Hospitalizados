/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cAlta_Das;
import CapaDato.cDas;
import CapaDato.cDiagnostico;
import CapaDato.cDuo;
import CapaDato.cExamen;
import CapaDato.cObservacion;
import CapaDato.cPaciente;
import CapaDato.cRegistroSeguimiento;
import CapaDato.cRegistroSocial;
import CapaDato.cVisita;
import CapaNegocio.NegocioQ;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Informatica
 */
public class PDF_visita_enfermeria extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("charset=UTF-8", " attachment; filename=\"VisitaEnfemeria.pdf\"");

//        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();

        /**
         * ******************DATOS**************************
         */
        NegocioQ neg = new NegocioQ();
        int id_visita = Integer.parseInt(request.getParameter("id_visita"));

        cVisita vis = neg.obtiene_visita_enfermeria(id_visita);
        cDuo duo = neg.obtiene_duo(vis.getId_duo());

        /**
         * ***************DATOS*****************************
         */
        Locale currentLocale2 = new Locale("es", "Ch");
        java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);
        NumberFormat formateadorNumero = NumberFormat.getNumberInstance(currentLocale2);
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
        java.text.DateFormat formateadorFechaLarga = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);

        Date hoy = new Date();
        String actual = formateadorFechaLarga.format(hoy) + ", " + formateadorHora.format(hoy.getTime());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hoy);
        calendar.add(Calendar.DATE, 1);
        Date fecha_final = calendar.getTime();
        String actual_mas_uno = formateadorFecha.format(fecha_final);

        // out.write("RUT "+das.getRut_paciente());
        // out.write("<br>"+das.getId_das());
        try {

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            String tipo_informe = "CATEGORIZACION RIESGO-DEPENDENCIA\n  N° " + id_visita;

//IMAGEN
            ServletContext context = this.getServletConfig().getServletContext();
            String path1 = context.getRealPath("/Imagenes");
            Image img = Image.getInstance(path1 + "/Logo_Gob.jpg");

//        img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth()) / 2,
//                (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
            img.scalePercent(25);
            img.setAbsolutePosition(60, PageSize.A4.getHeight() - img.getScaledHeight() - 40);
            document.add(img);
//FIN IMAGEN
            PDF_visita_enfermeria.FooterPiePaginaiText_visita_enfermeria footer = new PDF_visita_enfermeria.FooterPiePaginaiText_visita_enfermeria();
            //Asignamos el manejador de eventos al escritor.
            writer.setPageEvent(footer);

            document.add(new Paragraph("\n\n\n"));
            //creamos una tabla con 3 columnas

            //creamos una tabla con 3 columnas
            float[] colsWidth2 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬
            //
//CREO UN ARREGLO QUE CONTIENE LAS MEDIDAS DE CADA UNA DE LAS COLUMNAS
// EN MI CASO SON 4, (TB PUEDES PASAR EL ARREGLO DIRECTAMENTE)
            //float[] medidaCeldas = {0.55f, 0.55f, 0.55f, 0.55f};
//// ASIGNAS LAS MEDIDAS A LA TABLA (ANCHO)
//          tabla1.setWidths(medidaCeldas);

            //añadimos texto con formato a la primera celda
            Font ft1 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft1v2 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft11 = FontFactory.getFont("Verdana", 11, Font.BOLDITALIC, BaseColor.BLUE);
            Font ft4 = FontFactory.getFont("Verdana", 8, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn
            Font ft2 = FontFactory.getFont("Verdana", 13, Font.BOLDITALIC, BaseColor.BLACK);
            Font ft3 = FontFactory.getFont("Verdana", 9, Font.ITALIC, BaseColor.WHITE);

            PdfPCell celda = new PdfPCell(new Paragraph(tipo_informe, ft2));             // color
            //unimos esta celda con otras 2
            celda.setBorder(0);
            celda.setColspan(4);
            //alineamos el contenido al centro
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            // añadimos un espaciado
            celda.setPadding(12.0f);
            //colocamos un color de fondo
            //BaseColor myColor = WebColors.getRGBColor("#6699FF");
            celda.setBackgroundColor(BaseColor.WHITE);
            //se añade a la tabla
            tabla1.addCell(celda);

            //fila 1
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);

            //fila 2
            //fila 3
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);

            document.add(tabla1);

            /**
             * **TABLA
             * 2**********************************************************************
             */
            float[] colsWidth = {1.15f, 1f, 0.8f, 0.9f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(90);

            //fila 1
            BaseColor myColor = WebColors.getRGBColor("#6699FF");

            celda = new PdfPCell(new Paragraph("Paciente", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Rut", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Sexo", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            //fila 1
            celda = new PdfPCell(new Paragraph("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), ft1));
            //celda.setBorder(0);
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getRut_paciente(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getSexo_descri(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Edad", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Cama", ft3));
            celda.setBackgroundColor(myColor);
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Fecha", ft3));
            celda.setBackgroundColor(myColor);
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Hora", ft3));
            celda.setBackgroundColor(myColor);
            //celda.setBorder(0);
            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getCama_descripcion(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med().substring(0, 10), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med().substring(duo.getFecha_hora_ing_med().length() - 8, duo.getFecha_hora_ing_med().length()), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            //fila 3
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /**
             * ****************tabla 3****************
             */
            float[] colsWidth3 = {1.15f, 1f, 0.8f, 0.9f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            //FILA
            celda = new PdfPCell(new Paragraph("EU. Responsable", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("" + vis.getNombre_usuario() + " " + vis.getApellidop_usuario() + " " + vis.getApellidom_usuario(), ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla3.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Observación", ft3));
            //celda.setBorder(0);
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getObs_visita(), ft1));
            //celda.setBorder(0);
            celda.setColspan(4);
            tabla3.addCell(celda);

            //fila 3
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla3.addCell(celda);

            document.add(tabla3);

            /**
             * *************fin tabla 3**************
             */
            float[] colsWidth4 = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);

            // FILA
            celda = new PdfPCell(new Paragraph("Dependencia", ft3));
            //celda.setBorder(0);
            celda.setColspan(7);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Riesgo", ft3));
            //celda.setBorder(0);
            celda.setColspan(9);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("#", ft3));
            //celda.setBorder(0);
            celda.setColspan(2);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            // FILA
            celda = new PdfPCell(new Paragraph("D1", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("D2", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("D3", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("D4", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("D5", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("D6", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Ptje.", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R1", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R2", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R3", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R4", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R5", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R6", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R7", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("R8", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Ptje.", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Total", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("C", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            // FILA
            celda = new PdfPCell(new Paragraph("" + vis.getD1_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getD2_visita_categorizacion(), ft1));
            //celda.setBorder(0);

            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getD3_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getD4_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getD5_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getD6_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            int suma_d = vis.getD1_visita_categorizacion() + vis.getD2_visita_categorizacion() + vis.getD3_visita_categorizacion()
                    + vis.getD4_visita_categorizacion() + vis.getD5_visita_categorizacion() + vis.getD6_visita_categorizacion();
            celda = new PdfPCell(new Paragraph("" + suma_d, ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR1_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR2_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR3_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR4_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR5_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR6_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR7_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getR8_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);
            int suma_r = vis.getR1_visita_categorizacion() + vis.getR2_visita_categorizacion() + vis.getR3_visita_categorizacion() + vis.getR4_visita_categorizacion()
                    + vis.getR5_visita_categorizacion() + vis.getR6_visita_categorizacion() + vis.getR7_visita_categorizacion() + vis.getR8_visita_categorizacion();
            celda = new PdfPCell(new Paragraph("" + suma_r, ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            int suma_total = suma_d + suma_r;
            celda = new PdfPCell(new Paragraph("" + suma_total, ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + vis.getCat_visita_categorizacion(), ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);

            //blanco
            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setColspan(18);
            celda.setBorder(0);
            tabla4.addCell(celda);

            //
            document.add(tabla4);

            /**
             * ****************tabla 3****************
             */
            float[] colsWidth5 = {4f};
            PdfPTable tabla5 = new PdfPTable(colsWidth5);
            tabla5.setWidthPercentage(90);

            //FILA
            celda = new PdfPCell(new Paragraph("DEPENDENCIA", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Cuid. en Confort y Bienestar", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Movilización y Transporte", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Cuid. de Alimentación", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Cuid. de Eliminación", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Apoyo Psicosocial y Emocional", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Vigilancia", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("RIESGO", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Medición diaria de Signos Vitales", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Balance Hídrico", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Cuid. en Oxígenoterapia", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Cuidados diarios de la Vía Aérea", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Interv. Prof.", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Cuid. de Piel y Curaciones", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Administración de Tratamiento Farmacólogico", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);

            //FILA
            celda = new PdfPCell(new Paragraph("Presencia de Elementos Invasivos", ft3));
            //celda.setBorder(0);
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla5.addCell(celda);
           

            //
            document.add(tabla5);

            /**
             * ***********************************************************************
             */
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PDF_visita_enfermeria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_visita_enfermeria.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }

    }

    public class FooterPiePaginaiText_visita_enfermeria extends PdfPageEventHelper {

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("             "
                    + "                                       Centro de Referencia de Salud de Maipú"), 200, 20, 0);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
