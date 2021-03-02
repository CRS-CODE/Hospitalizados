/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.DetailIndexBarthel;
import CapaDato.DuoIndexBarthel;
import CapaDato.cDiagnostico;
import CapaDato.cDuo;
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
 * @author Kairin
 */
public class PDF_indexBarthel extends HttpServlet {

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
        response.setHeader("charset=UTF-8", " attachment; filename=\"Indice Barthel.pdf\"");

//        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();

        /**
         * ******************DATOS**************************
         */
        NegocioQ controller = new NegocioQ();
        int id = Integer.parseInt(request.getParameter("id"));
        DuoIndexBarthel duoIndexBarthel = controller.getDuoIndexBarthel(id);
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

            String tipo_informe = "Ìndice Barthel";

            ServletContext context = this.getServletConfig().getServletContext();
            String path1 = context.getRealPath("/Imagenes");
            Image img = Image.getInstance(path1 + "/Logo_Gob.jpg");

            img.scalePercent(25);
            img.setAbsolutePosition(60, PageSize.A4.getHeight() - img.getScaledHeight() - 40);
            document.add(img);

            PDF_indexBarthel.FooterPiePaginaiText_pdf_duo footer = new PDF_indexBarthel.FooterPiePaginaiText_pdf_duo();

            writer.setPageEvent(footer);

            document.add(new Paragraph("\n\n\n"));

            float[] colsWidth2 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬

            Font ft1 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft1v2 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft11 = FontFactory.getFont("Verdana", 11, Font.BOLDITALIC, BaseColor.BLUE);
            Font ft4 = FontFactory.getFont("Verdana", 8, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn
            Font ft2 = FontFactory.getFont("Verdana", 11, Font.BOLDITALIC, BaseColor.BLACK);
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
            float[] colsWidth = {1f, 2.5f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(90);

            //fila 1
            BaseColor myColor = WebColors.getRGBColor("#6699FF");

            celda = new PdfPCell(new Paragraph("Nombre:", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duoIndexBarthel.getNamePaciente(), ft1));
            //celda.setBorder(0);
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Rut:", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duoIndexBarthel.getRutPaciente(), ft1));
            //celda.setBorder(0);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha:", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duoIndexBarthel.getDateString(), ft1));
            //celda.setBorder(0);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Aplicado por:", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duoIndexBarthel.getUserRegisters(), ft1));
            //celda.setBorder(0);
            celda.setColspan(1);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duoIndexBarthel.getTypeString(), ft1));
            //celda.setBorder(0);
            celda.setColspan(2);
            tabla2.addCell(celda);

            document.add(tabla2);

            float[] colsWidthDetail = {1f, 2f, 1f};
            PdfPTable tablaDetail = new PdfPTable(colsWidthDetail);
            tablaDetail.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Actividad", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tablaDetail.addCell(celda);

            celda = new PdfPCell(new Paragraph("Descripcion", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tablaDetail.addCell(celda);

            celda = new PdfPCell(new Paragraph("Puntaje", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tablaDetail.addCell(celda);

            for (DetailIndexBarthel detailIndexBarthel : controller.listDetailIndexBarthel(id)) {
                celda = new PdfPCell(new Paragraph(detailIndexBarthel.getDescriptionBarthel(), ft1));
                celda.setColspan(1);
                tablaDetail.addCell(celda);

                celda = new PdfPCell(new Paragraph(detailIndexBarthel.getDescriptionDetail(), ft1));
                celda.setColspan(1);
                tablaDetail.addCell(celda);

                celda = new PdfPCell(new Paragraph(String.valueOf(detailIndexBarthel.getPunctuction()), ft1));
                celda.setColspan(1);
                tablaDetail.addCell(celda);
            }

            document.add(tablaDetail);
            document.add(new Paragraph("\n"));
            float[] colsWidthPie = {2f};
            PdfPTable tablaP = new PdfPTable(colsWidthPie);
            tablaP.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Puntaje:" + String.valueOf(duoIndexBarthel.getTotalPuntuction()), ft1));
            celda.setBorder(0);
            celda.setColspan(3);
            tablaP.addCell(celda);

            celda = new PdfPCell(new Paragraph("Grado de Dependencia:" + duoIndexBarthel.getDegreeOfDependency(), ft1));
            celda.setBorder(0);
            celda.setColspan(3);
            tablaP.addCell(celda);

            document.add(tablaP);

            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PDF_DUO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_DUO.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }

    }

    public class FooterPiePaginaiText_pdf_duo extends PdfPageEventHelper {

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("             "
                    + "                                            Centro de Referencia de Salud de Maipú"), 200, 20, 0);

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
