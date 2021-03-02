/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cEvaTraumatologia;

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
public class PDF_evaluacion_traumatologica extends HttpServlet {

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

        cEvaTraumatologia eva = neg.obtiene_evaluacion_traumatologia(id_sesion);
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

            String tipo_informe = "EVALUACION TRAUMATOLOGICA - DUO "+duo.getId_duo();

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

            PDF_evaluacion_traumatologica.FooterPiePaginaiText_trauma footer = new PDF_evaluacion_traumatologica.FooterPiePaginaiText_trauma();
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

            celda = new PdfPCell(new Paragraph(eva.getFecha_ingreso_trauma(), ft1));

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(eva.getHora_ingreso_trauma(), ft1));

            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /*FIN TABLA 2*/
            /* TABLA 3*/
            float[] colsWidth3 = {0.70f, 3.2f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            //fila 1
            celda = new PdfPCell(new Paragraph("Observación Inicial ", ft3));
            celda.setColspan(1);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getObservacion_inicial(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Historial del usuario", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getHistorial_usuario(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Dolor", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getDolor(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pruebas especiales", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getPrueba_especial(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Palpación", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getPalpacion(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Marcha", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getMarcha(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft3));
            celda.setBorder(0);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("PLANO", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Plano Frontal", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getPlano_frontal(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Plano Sagital", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getPlano_sagital(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Plano Posterior", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getPlano_posterior(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft3));
            celda.setBorder(0);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("MOVIMIENTO", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Moviento Activo", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getMovimiento_activo(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Movimiento Pasivo", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getMovimiento_pasivo(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Observaciones", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getObservacion(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft3));
            celda.setBorder(0);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("EVALUACION METAMERICA", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Dermatoma", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getDermatoma(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Miotoma", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getMiotoma(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Reflejo Osteotendíneo", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getReflejo_osteotendineo(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Test Neurodinámico", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getTest_neurodinamico(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft3));
            celda.setBorder(0);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("DIAGNOSTICO", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Diagnóstico por Imagen", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getDiagnostico_imagen(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Diagnóstico Kinésico ", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eva.getDiagnostico_kinesico(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            document.add(tabla3);

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
            Logger.getLogger(PDF_evaluacion_traumatologica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_evaluacion_traumatologica.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }

    }

    public class FooterPiePaginaiText_trauma extends PdfPageEventHelper {

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
