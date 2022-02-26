/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cInterconsulta;
import CapaDato.cReceta;
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
public class sicPDF extends HttpServlet {

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
        {
            response.setContentType("application/pdf");
            response.setHeader("charset=UTF-8", " attachment; filename=\"Informe.pdf\"");
//      Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            Document document = new Document();
            /**
             * ******************DATOS**************************
             */

            NegocioQ neg = new NegocioQ();
            int id_sic = Integer.parseInt(request.getParameter("id_sic"));
            cInterconsulta sic = neg.getSIC(id_sic);
            cDuo duo = neg.obtiene_duo(sic.getIdDuo());
            
            String[] razon = {"Sin informacion", "Confirmacion Diagnostica", "Realiza Tratamiento", "Seguimiento", "Otro"};
            String[] sospecha = {"Sin informacion", "Si", "No"};
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

                String tipo_informe = "SOLICITUD DE INTERCONSULTA O DERIVACION";

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

                sicPDF.FooterPiePaginaiText_SIC footer = new sicPDF.FooterPiePaginaiText_SIC();
                //Asignamos el manejador de eventos al escritor.
                writer.setPageEvent(footer);
                // fin pie de pagina
                document.add(new Paragraph("\n\n\n\n"));
                //creamos una tabla con 3 columnas

                //creamos una tabla con 3 columnas
                float[] colsWidth2 = {0.75f, 1.05f, 0.8f, 1.4f};
                PdfPTable tabla1 = new PdfPTable(colsWidth2);
                tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬

                //añadimos texto con formato a la primera celda
                Font ft1 = FontFactory.getFont("Verdana", 9, Font.ITALIC, BaseColor.BLACK);
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
                celda = new PdfPCell(new Paragraph(""));
                celda.setBorder(0);
                celda.setColspan(4);
                tabla1.addCell(celda);

                document.add(tabla1);
                BaseColor myColor = WebColors.getRGBColor("#6699FF");
                BaseColor myColor2 = WebColors.getRGBColor("#4169E1");

                /**
                 * *************************FIN TABLA
                 * 2*************************
                 */
                /*TABLA 3*/
                float[] colsWidth3 = {1f, 1f, 1f, 1f};
                PdfPTable tabla3 = new PdfPTable(colsWidth3);
                tabla3.setWidthPercentage(90);

                celda = new PdfPCell(new Paragraph("I.- DATOS DEL PACIENTE", ft3));
                celda.setBackgroundColor(myColor2);
                celda.setColspan(4);
                tabla3.addCell(celda);
                //otra celda
                celda = new PdfPCell(new Paragraph("Nombre", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                // OTRA CELDA
                celda = new PdfPCell(new Paragraph("Rut", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getRut_paciente(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Fecha", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getFechaRegistro(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                //CELDA
                celda = new PdfPCell(new Paragraph("Sexo", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getSexo_descri(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Fecha de Nacimiento", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getFecha_nac(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Edad", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Domicilio", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getDireccion(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Comuna de Residencia", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getComuna_descri(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Telefono", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getTelefono1() + " " + duo.getTelefono2(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Correo", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getMail(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("II.- DATOS CLINICOS", ft3));
                celda.setBackgroundColor(myColor2);
                celda.setColspan(4);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Se deriva para atencion en:  Establecimiento", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getEstablecimiento(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Especialidad", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getEspecialidad(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                //  CELDA
                celda = new PdfPCell(new Paragraph("Se envia a consultar para: ", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + razon[sic.getIdRazon()], ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);
                if (sic.getIdRazon() == 5) {

                    celda = new PdfPCell(new Paragraph("Especificar", ft3));
                    celda.setBackgroundColor(myColor);
                    celda.setColspan(1);
                    tabla3.addCell(celda);

                    celda = new PdfPCell(new Paragraph("" + sic.getOtraRazon(), ft1));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                }

                tabla3.addCell(celda);

                document.add(tabla3);

                float[] colsWidth4 = {0.3f, 2.3f, 0.6f, 0.7f, 0.7f, 1.2f, 1.2f};
                PdfPTable tabla4 = new PdfPTable(colsWidth4);
                tabla4.setWidthPercentage(90);

                celda = new PdfPCell(new Paragraph("Hipotesis diagnostica o diagnostico:", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(7);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getDiagnostico(), ft1));
                celda.setColspan(7);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("¿Sospecha problema de salud AUGE ?", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(2);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sospecha[sic.getIdSospechaProblema()], ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);
                celda = new PdfPCell(new Paragraph(" Espicificar Problema", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getEspecificarProblema(), ft1));
                celda.setColspan(1);
                tabla4.addCell(celda);
                celda = new PdfPCell(new Paragraph("Subgrupo o subproblema de salud AUGE:", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getSubProblema(), ft1));
                celda.setColspan(1);
                
                celda = new PdfPCell(new Paragraph("Fundamentos del Diagnostico:", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(7);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getFundamentosDiagnostico(), ft1));
                celda.setColspan(7);
                tabla4.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Examenes Realizados:", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(7);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + sic.getExamenesRealizados(), ft1));
                celda.setColspan(7);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("Nombre médico: Dr. " + sic.getUser() + ".", ft1));
                celda.setColspan(5);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("Firma", ft1));
                celda.setColspan(2);
                tabla4.addCell(celda);

                document.add(tabla4);

                document.close();

            } catch (DocumentException ex) {
                Logger.getLogger(PDF_receta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                Logger.getLogger(PDF_receta.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
            }

        }
    }

    public class FooterPiePaginaiText_SIC extends PdfPageEventHelper {

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {

        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("             "
//                    + "                                       __________________________"), 200, 92, 0);
//
//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("             "
//                    + "                                       Firma Responsable"), 200, 80, 0);
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
