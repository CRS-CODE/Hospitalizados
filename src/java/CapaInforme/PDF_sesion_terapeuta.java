/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cSesionKine;
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
 * @author a
 */
public class PDF_sesion_terapeuta extends HttpServlet {

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
        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
        //   ArrayList lista_diagnostico = neg.lista_diagnostico_suam(id_das, "1");

        ArrayList lista_sesion = neg.lista_sesion_terapeuta(id_duo);
        cDuo duo = neg.obtiene_duo(id_duo);

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

            String tipo_informe = "SESION TERAPEUTA ";

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

            PDF_sesion_terapeuta.FooterPiePaginaiText_sesion footer = new PDF_sesion_terapeuta.FooterPiePaginaiText_sesion();
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
            Font ft1 = FontFactory.getFont("Verdana", 9, Font.ITALIC, BaseColor.BLACK);
            Font ft1v2 = FontFactory.getFont("Verdana", 11, Font.ITALIC, BaseColor.BLACK);
            Font ft11 = FontFactory.getFont("Verdana", 11, Font.BOLDITALIC, BaseColor.BLUE);
            Font ft4 = FontFactory.getFont("Verdana", 8, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn
            Font ft2 = FontFactory.getFont("Verdana", 13, Font.ITALIC, BaseColor.BLACK);
            Font ft3 = FontFactory.getFont("Verdana", 9, Font.ITALIC, BaseColor.WHITE);
             Font ft5 = FontFactory.getFont("Verdana", 6, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn

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
            tabla2.setWidthPercentage(95);

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

            celda = new PdfPCell(new Paragraph("" + duo.getFecha_duo(), ft1));

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getHora_duo(), ft1));

            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /*FIN TABLA 2*/
            /* TABLA 3*/
            float[] colsWidth3 = {0.4f, 3.5f, 0.3f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(95);

            //fila 1
            String guarda_fecha = "";

            Iterator it_ses = lista_sesion.iterator();

            if (lista_sesion.isEmpty()) {

                celda = new PdfPCell(new Paragraph("No se encontraron registros", ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

            } else {
                /**/
                while (it_ses.hasNext()) {
                    cSesionKine ses = (cSesionKine) it_ses.next();

                    if (!ses.getFecha().equals(guarda_fecha)) {
                       // celda = new PdfPCell(new Paragraph("                                                                 Día " + ses.getFecha(), ft1));

                        celda = new PdfPCell(new Paragraph("Día " + ses.getFecha(), ft1));

                        celda.setColspan(3);
                        celda.setBackgroundColor(myColor);
                        tabla3.addCell(celda);
                    }

                    celda = new PdfPCell(new Paragraph("" + ses.getHora(), ft4));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + ses.getDetalle(), ft4));
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    celda = new PdfPCell(new Paragraph("" + ses.getNombre_usuario()+"\n"+ses.getApellidop_usuario(), ft5));
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    guarda_fecha = ses.getFecha();
                }
                /**/
            }

            document.add(tabla3);

            /*FIN TABLA 3*/
            /* TABLA 10*/
            float[] colsWidth10 = {4f};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(95);

            celda = new PdfPCell(new Paragraph(" \n \n \n \n                                        "
                    + " \n                                       HOSPITALIZACION DOMICILIARIA", ft2));
            //celda.setBorder(0);
            tabla10.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha de Impresión :" + actual, ft1));
            tabla10.addCell(celda);

            document.add(tabla10);

            /* FIN TABLA 10 */
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PDF_sesion_kinesiologia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_sesion_kinesiologia.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }
    }
    
    
     public class FooterPiePaginaiText_sesion extends PdfPageEventHelper {

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
