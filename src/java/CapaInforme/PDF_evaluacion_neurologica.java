/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cEvaNeurologia;

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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class PDF_evaluacion_neurologica extends HttpServlet {

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
        response.setHeader("charset=UTF-8", " attachment; filename=\"Informe.pdf\"");
//      Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();
        /**
         * ******************DATOS**************************
         */

        NegocioQ neg = new NegocioQ();
        int id_sesion = Integer.parseInt(request.getParameter("txt_id"));
        //   ArrayList lista_diagnostico = neg.lista_diagnostico_suam(id_das, "1");

        cEvaNeurologia eva = neg.obtiene_evaluacion_neurologia(id_sesion);
        // cEvaNeurologia eva = new cEvaNeurologia();
        cDuo duo = neg.obtiene_duo(eva.getId_duo());

        /**
         * * ***************DATOS*****************************
         */
        Locale currentLocale2 = new Locale("es", "Ch");
        java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);
        NumberFormat formateadorNumero = NumberFormat.getNumberInstance(currentLocale2);
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
        java.text.DateFormat formateadorFechaLarga = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);

        Date hoy = new Date();
        String actual = formateadorFechaLarga.format(hoy).toString() + ", " + formateadorHora.format(hoy.getTime());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hoy);
        calendar.add(Calendar.DATE, 1);
        Date fecha_final = calendar.getTime();
        String actual_mas_uno = formateadorFecha.format(fecha_final).toString();
        // out.write("RUT "+das.getRut_paciente());
        // out.write("<br>"+das.getId_das());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            String tipo_informe = "EVALUACION NEUROLOGICA - DUO " + duo.getId_duo();

//IMAGEN
            ServletContext context = this.getServletConfig().getServletContext();
            String path1 = context.getRealPath("/Imagenes");
            Image img = Image.getInstance(path1 + "/Logo_Gob.jpg");

//        img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth()) / 2,
//        (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
            img.scalePercent(25);
            img.setAbsolutePosition(60, PageSize.A4.getHeight() - img.getScaledHeight() - 40);
            document.add(img);
//FIN IMAGEN
            // pie de pagina

            PDF_evaluacion_neurologica.FooterPiePaginaiText_neuro footer = new PDF_evaluacion_neurologica.FooterPiePaginaiText_neuro();
            //Asignamos el manejador de eventos al escritor.
            writer.setPageEvent(footer);
            // fin pie de pagina
            document.add(new Paragraph("\n\n\n\n"));
            //creamos una tabla con 3 columnas

            //creamos una tabla con 3 columnas
            float[] colsWidth2 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬

//  CREO UN ARREGLO QUE CONTIENE LAS MEDIDAS DE CADA UNA DE LAS COLUMNAS
//  EN MI CASO SON 4, (TB PUEDES PASAR EL ARREGLO DIRECTAMENTE)
//  float[] medidaCeldas = {0.55f, 0.55f, 0.55f, 0.55f};
//  ASIGNAS LAS MEDIDAS A LA TABLA (ANCHO)
//  tabla1.setWidths(medidaCeldas);
            //añadimos texto con formato a la primera celda
            Font ft1 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft1v2 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft11 = FontFactory.getFont("Verdana", 11, Font.BOLDITALIC, BaseColor.BLUE);
            Font ft4 = FontFactory.getFont("Verdana", 8, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn
            Font ft2 = FontFactory.getFont("Verdana", 13, Font.ITALIC, BaseColor.BLACK);
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
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);

            document.add(tabla1);

            BaseColor myColor = WebColors.getRGBColor("#6699FF");

            float[] colsWidth = {1.15f, 1f, 0.8f, 0.9f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(90);

            //fila 1
            celda = new PdfPCell(new Paragraph("Paciente", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Rut", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Sexo", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            //OTRA FILA
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
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Derivado", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Hora", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph(duo.getEdad(), ft1));

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duo.getDerivador_descripcion(), ft1));

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getFecha_ingreso_neuro(), ft1));

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getHora_ingreso_neuro(), ft1));

            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /*FIN TABLA 2*/
            /* TABLA 3*/
            float[] colsWidth3 = {1f, 1f, 1f, 1f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("                                                                   Datos de la evaluación ", ft3));
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            //fila 1
            celda = new PdfPCell(new Paragraph("Lesión Evaluada ", ft3));
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getLesion_evaluada_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            if (eva.getLesion_evaluada() == 1) {

                //fila 1
                celda = new PdfPCell(new Paragraph("Lesión ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getLesion_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("ASIA", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getAsia(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Tipo de Lesión ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getLesion_tipo_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Ashworth Modificado", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getAshworth(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Reflejo Osteotendineo ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getReflejo_osteorendineo_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Evaluación Sensitiva ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getEvaluacion_sensitiva(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("Sensibilidad Anal Profunda ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getSensibilidad(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("Motor index Score ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getMotor_index(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("Contracción Esfinteránea y Anal ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getContraccion(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //OTRA FILA
                celda = new PdfPCell(new Paragraph(""));
                celda.setBorder(0);
                celda.setColspan(4);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("                                                                   Transferencias ", ft3));
                celda.setColspan(4);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                /**
                 * *****************************
                 */
                float[] colsWidth4 = {1f, 0.15f, 0.15f, 2.7f};
                PdfPTable tabla4 = new PdfPTable(colsWidth4);
                tabla4.setWidthPercentage(90);
// FILA

                celda = new PdfPCell(new Paragraph("Descripción ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("SI", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("NO", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("Detalle", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);
                //FILA  
                String si = "";
                String no = "";
                if (eva.getOp1() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Giros", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp1_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);
                //FILA  

                if (eva.getOp2() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Supino Decúbito Lateral", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp2_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //FILA  
                if (eva.getOp3() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Supino Prono", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp3_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //FILA  
                if (eva.getOp4() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Decúbito Lateral Sedente", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp4_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //FILA  
                if (eva.getOp5() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }
                celda = new PdfPCell(new Paragraph("Supino Sedente", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp5_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //FILA  
                if (eva.getOp6() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Sedente Bipedo", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp6_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(tabla4);
                celda.setColspan(4);
                tabla3.addCell(celda);
                /**
                 * *****************************
                 */
                // fila tabla transferencias

                //OTRA FILA
                celda = new PdfPCell(new Paragraph(""));
                celda.setBorder(0);
                celda.setColspan(4);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Silla de ruedas ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getSilla_ruedas(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Marcha ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getMarcha(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Nivel Sentivo Lesión ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getNivel_sentivo(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("Nivel Motor Lesión ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getNivel_motor(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("Nivel Neurológico Lesión ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getNivel_neurologico(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                document.add(tabla3);

            } else {
/// CASO SI ES OTRO TIPO DE LESION ghj
                //fila 1

                celda = new PdfPCell(new Paragraph("Lesión ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);
//02032015
                if (eva.getLesion_evaluada() == 2) {
                    celda = new PdfPCell(new Paragraph("" + eva.getLesion_desc(), ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                } else {
                    celda = new PdfPCell(new Paragraph("--", ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                }
//02032015
                celda = new PdfPCell(new Paragraph("Ashworth Modificado ", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getAshworth(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);
                //fila 1
                celda = new PdfPCell(new Paragraph("2° Motoneurona ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getMotoneurona(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Extrapiramidal ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getExtrapiramiral(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                // separacion
                celda = new PdfPCell(new Paragraph("", ft1));
                celda.setColspan(4);
                celda.setBorder(0);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("                                                                   Antecedentes Mórbidos ", ft3));
                celda.setColspan(4);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                /**
                 * *****************************
                 */
                float[] colsWidth4 = {1f, 0.2f, 0.2f, 2.6f};
                PdfPTable tabla4 = new PdfPTable(colsWidth4);
                tabla4.setWidthPercentage(90);
// FILA
                celda = new PdfPCell(new Paragraph("Descripción ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("SI", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("NO", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("Detalle", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);
                //FILA  
                String si = "";
                String no = "";
                if (eva.getOp1() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("HTA", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp1_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);
                //FILA  
                if (eva.getOp2() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("DM", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp2_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //FILA  
                if (eva.getOp3() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Dislipidemia", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp3_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //FILA  
                if (eva.getOp4() == 1) {
                    si = "X";
                    no = "";
                } else {
                    si = "";
                    no = "X";
                }

                celda = new PdfPCell(new Paragraph("Tabaquismo", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + si, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + no, ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getOp4_desc(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);

                //agrego la tabla
                celda = new PdfPCell(tabla4);
                celda.setColspan(4);
                tabla3.addCell(celda);
                /**
                 * *****************************
                 */
                // fila tabla transferencias

                // separacion
                celda = new PdfPCell(new Paragraph("", ft1));
                celda.setColspan(4);
                celda.setBorder(0);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Postura ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getPostura(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Fuerza ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getFuerza(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Tono Muscular ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getTono_muscular(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Trofismo ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                if (eva.getTrofismo() == 1) {
                    celda = new PdfPCell(new Paragraph("" + eva.getTrofismo_desc(), ft1));
                    celda.setColspan(3);
                    tabla3.addCell(celda);
                } else {
                    celda = new PdfPCell(new Paragraph("" + eva.getTrofismo_desc(), ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    celda = new PdfPCell(new Paragraph("Detalle", ft3));
                    celda.setBackgroundColor(myColor);
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    celda = new PdfPCell(new Paragraph("" + eva.getTrofismo_adicional(), ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                }

                // separacion
                celda = new PdfPCell(new Paragraph("", ft1));
                celda.setColspan(4);
                celda.setBorder(0);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("                                                                   Movilidad Activa", ft3));
                celda.setColspan(4);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                // fila 1
                celda = new PdfPCell(new Paragraph("EESS", ft3));
                celda.setColspan(2);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("EEII", ft3));
                celda.setColspan(2);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                // fila 1
                celda = new PdfPCell(new Paragraph(eva.getEess(), ft1));
                celda.setColspan(2);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph(eva.getEeii(), ft1));
                celda.setColspan(2);
                tabla3.addCell(celda);

                // separacion
                celda = new PdfPCell(new Paragraph("", ft1));
                celda.setColspan(4);
                celda.setBorder(0);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Reflejo Osteotendineos ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getReflejo_osteorendineo_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Propiocepción ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                if (eva.getTrofismo() == 1) {
                    celda = new PdfPCell(new Paragraph("" + eva.getPropiocepcion_desc(), ft1));
                    celda.setColspan(3);
                    tabla3.addCell(celda);
                } else {
                    celda = new PdfPCell(new Paragraph("" + eva.getPropiocepcion_desc(), ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    celda = new PdfPCell(new Paragraph("Detalle", ft3));
                    celda.setBackgroundColor(myColor);
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    celda = new PdfPCell(new Paragraph("" + eva.getPropiocepcion_adicional(), ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                }

                //fila 1
                celda = new PdfPCell(new Paragraph("Transiciones ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getTransicion(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Reacciones de Equilibrio ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getReaccion_equilibrio_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Reacciones de Enderezamiento ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getReaccion_enderezamiento_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Reacciones de Apoyo ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getReaccion_apoyo_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //fila 1
                celda = new PdfPCell(new Paragraph("Marcha ", ft3));
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + eva.getMarcha(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                document.add(tabla3);

            }

            /*FIN TABLA 3*/
            /* TABLA 10*/
            float[] colsWidth10 = {4f};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph(" \n \n \n \n                                        Responsable " + eva.getNombre_usuario() + " " + eva.getApellidop_usuario()
                    + " \n                                       UNIDAD DE OBSERVACIÓN", ft2));
            //celda.setBorder(0);
            tabla10.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha de Impresión :" + actual, ft1));
            tabla10.addCell(celda);

            document.add(tabla10);

            /* FIN TABLA 10 */
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PDF_evaluacion_traumatologica.class.getName()).log(Level.SEVERE, null, "(1)Ocurrio una excepcion " + ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_evaluacion_traumatologica.class.getName()).log(Level.SEVERE, null, "(2)Ocurrio una excepcion" + e);
        }

    }

    public class FooterPiePaginaiText_neuro extends PdfPageEventHelper {

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Centro de Referencia de Salud de Maipú"), 300, 20, 0);
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
