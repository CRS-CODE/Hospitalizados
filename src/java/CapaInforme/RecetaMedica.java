/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cReceta;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a
 */
public class RecetaMedica extends HttpServlet {

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
            throws ServletException, IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("charset=UTF-8", " attachment; filename=\"Informe.pdf\"");

        Document document = new Document();
        
        NegocioQ neg = new NegocioQ();

        int idindicacion = Integer.parseInt(request.getParameter("id_ind"));
        cReceta rec = neg.bucarindicacion(idindicacion);

        cDuo duo = neg.obtiene_duo(rec.getId_receta_detalle());
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
        
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            String tipo_informe = "RECETA MEDICA";


            //IMAGEN
            ServletContext context = this.getServletConfig().getServletContext();
            String path1 = context.getRealPath("/Imagenes");
            Image img = Image.getInstance(path1 + "/crsm.png");

//        img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth()) / 2,
//        (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
            img.scalePercent(25);
            img.setAbsolutePosition(60, PageSize.A4.getHeight() - img.getScaledHeight() - 40);
            document.add(img);
//FIN IMAGEN
            // pie de pagina

            RecetaMedica.FooterPiePaginaiText_visita_medica footer = new RecetaMedica.FooterPiePaginaiText_visita_medica();
            //Asignamos el manejador de eventos al escritor.
            writer.setPageEvent(footer);
            document.add(new Paragraph("\n\n\n"));
            //creamos una tabla con 3 columnas

            //creamos una tabla con 3 columnas
            float[] colsWidth2 = {0.30f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬

            Font ft1 = FontFactory.getFont("Calibri", 9, Font.ITALIC, BaseColor.BLACK);
            Font ft1v2 = FontFactory.getFont("Calibri", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft11 = FontFactory.getFont("Calibri", 11, Font.BOLDITALIC, BaseColor.BLACK);
            Font ft4 = FontFactory.getFont("Calibri", 10, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn
            Font ft2 = FontFactory.getFont("Calibri", 13, Font.ITALIC, BaseColor.BLACK);
            Font ft3 = FontFactory.getFont("Calibri", 9, Font.ITALIC, BaseColor.WHITE);
            Font ft5 = FontFactory.getFont("Calibri", 8, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn

            PdfPCell celda = new PdfPCell(new Paragraph("F-" + rec.getId_duo(), ft2));             // color
            //unimos esta celda con otras 2
            celda.setBorder(0);
            celda.setColspan(4);
            //alineamos el contenido al centro
            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            celda.setBackgroundColor(BaseColor.WHITE);
            //se añade a la tabla
            tabla1.addCell(celda);

            //fila 1
            celda = new PdfPCell(new Paragraph(tipo_informe));
            celda.setBorder(0);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setColspan(4);
            celda.setPadding(12.0f);
            tabla1.addCell(celda);

            //fila 2
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);

            document.add(tabla1);

            BaseColor myColor = WebColors.getRGBColor("#6699FF");

            float[] colsWidth = {0.8f, 1f, 0.8f, 0.9f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(95);

            //fila 1
            celda = new PdfPCell(new Paragraph("Nombre paciente:", ft11));
            celda.setColspan(1);
            celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), ft1));
            celda.setBorder(0);
            celda.setColspan(3);
            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla2.addCell(celda);
            
              celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("RUT:", ft11));
            celda.setBorder(0);
             celda.setColspan(1);
            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("" + duo.getRut_paciente(), ft1));
            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
             celda.setColspan(1);
            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Edad:", ft11));
             celda.setColspan(1);
            celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(duo.getEdad(), ft4));
            celda.setColspan(1);
            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
            tabla2.addCell(celda);
   
             celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);
            
            document.add(tabla2);

            float[] colsWidthtk = {0.4f, 0.4f, 0.8f, 0.4f, 0.4f};
            PdfPTable tablak = new PdfPTable(colsWidthtk);
            tablak.setWidthPercentage(95);

            celda = new PdfPCell(new Paragraph("Fecha:", ft11));
           celda.setBorder(0);
            celda.setColspan(1);
            tablak.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + rec.getFecha(), ft1));
             celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
            celda.setColspan(1);
            tablak.addCell(celda);

            celda = new PdfPCell(new Paragraph("Día(s) Hospitalizado(a):", ft11));
             celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
            celda.setColspan(1);
            tablak.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getDias_cama(), ft1));
            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
            celda.setColspan(1);
            tablak.addCell(celda);
            
            
            celda = new PdfPCell(new Paragraph(duo.getCama_descripcion(), ft11));
            celda.setBorder(0);
            celda.setColspan(1);
            tablak.addCell(celda);

            celda = new PdfPCell(new Paragraph("Diagnostico:", ft11));
            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
            celda.setColspan(1);
            tablak.addCell(celda);

            celda = new PdfPCell(new Paragraph(rec.getDiagnostico(), ft1));
            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda.setBorder(0);
            celda.setColspan(4);
            tablak.addCell(celda);
             
          

            document.add(tablak);
             document.add(new Paragraph("\n"));
           float[] colsWidth3 = {3.5f, 3.5f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(95);


            Vector<String> recetas = neg.buscarRecetadeIndicaciones(idindicacion);
            
            if (recetas.size() > 0) {
                celda = new PdfPCell(new Paragraph("Medicamentos", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);
                celda = new PdfPCell(new Paragraph("Horarios", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);
               for (int i = 0; i < recetas.size(); i++) {
                  celda = new PdfPCell(new Paragraph(i+1+". " + recetas.get(i).toLowerCase(), ft4));
                  celda.setColspan(1);
                  tabla3.addCell(celda);
                  celda = new PdfPCell(new Paragraph());
                  celda.setColspan(1);
                  tabla3.addCell(celda);
               }
            }

       
            celda = new PdfPCell(new Paragraph("Otras Indicaciones", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + rec.getIndicacion(), ft4));
            celda.setColspan(2);
            celda.setPadding(14.0f);
            tabla3.addCell(celda);

            /**/
            document.add(tabla3);

            document.add(new Paragraph("\n\n"));
            float[] colsWidth10 = {4f};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(95);

            celda = new PdfPCell(new Paragraph("Nombre Medico:  " + rec.getNombre_usuario(), ft4));
            celda.setBorder(0);
            tabla10.addCell(celda);

            document.add(tabla10);

            /* FIN TABLA 10 */
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PDF_visita_medica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_visita_medica.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }

    }

    public class FooterPiePaginaiText_visita_medica extends PdfPageEventHelper {

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(""), 300, 20, 0);

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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(PDF_Indicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(PDF_Indicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
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
