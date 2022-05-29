/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDiagnostico;
import CapaDato.cDocumento;
import CapaDato.cDuo;
import CapaDato.cIngresoEnfermeria;
import CapaDato.cPaciente;
import CapaDato.cRegistroSeguimiento;
import CapaDato.cRegistroSocial;
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
public class PDF_registro_social extends HttpServlet {

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
        cDuo duo = neg.obtiene_duo(id_duo);

        ArrayList lista_contacto = neg.lista_contacto(duo.getRut_paciente());
        ArrayList lista_seguimiento = neg.lista_seguimiento(id_duo);

        cRegistroSocial reg = neg.obtiene_registro_social(id_duo);

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

            String tipo_informe = "REGISTRO SOCIAL";

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

            PDF_registro_social.FooterPiePaginaiText_registro_social footer = new PDF_registro_social.FooterPiePaginaiText_registro_social();
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
            BaseColor myColor2 = WebColors.getRGBColor("#f7903b");

            /**
             * *************************FIN TABLA 2*************************
             */
            /*TABLA 3*/
            float[] colsWidth3 = {0.3f, 0.3f, 0.12f, 0.68f, 0.32f, 0.28f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("I.- IDENTIFICACION PERSONAL", ft3));
            celda.setBackgroundColor(myColor2);
            celda.setColspan(6);
            tabla3.addCell(celda);
            //otra celda
            celda = new PdfPCell(new Paragraph("Nombre", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Cama", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getCama_descripcion(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            // OTRA CELDA
            celda = new PdfPCell(new Paragraph("Rut", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getRut_paciente(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Edad", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("F. Nacimiento", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getFecha_nac(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
            // OTRA CELDA

            celda = new PdfPCell(new Paragraph("Dirección", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getDireccion(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Comuna", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getComuna_descri(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
            /*OTRA CELDA*/

            celda = new PdfPCell(new Paragraph("Estado Civil", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getEstado_civil_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Situación Laboral", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getSituacion_laboral_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
            /*OTRA CELDA*/

            celda = new PdfPCell(new Paragraph("Consultorio", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getConsultorio_descri(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Institucionalizado", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getInstitucionalizado_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
            /*OTRA CELDA*/

            if (reg.getInstitucionalizado() == 1) {
                celda = new PdfPCell(new Paragraph("Institución", ft3));
                celda.setBackgroundColor(myColor);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + reg.getInstitucion_nombre(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);

            }

            //otra fila
            celda = new PdfPCell(new Paragraph("Hospital Origen", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getHospital_origen_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Telefono", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getTelefono1(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //otra fila
            celda = new PdfPCell(new Paragraph("A.S. Encargada", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getNombre_asistente() , ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Telefono", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getTelefono2(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //otra fila
            celda = new PdfPCell(new Paragraph("Diagnostico", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getDiagnostico(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Previsión", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            if (duo.getPrais() == 1) {
                celda = new PdfPCell(new Paragraph("PRAIS", ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);
            } else {
                celda = new PdfPCell(new Paragraph("" + duo.getTramo_prevision(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);
            }

            //otra fila
            celda = new PdfPCell(new Paragraph("Fecha Ingreso", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getFecha_duo(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha Egreso", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getFecha_egreso(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //otra fila
            celda = new PdfPCell(new Paragraph("Destino", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getDestino_desc(), ft1));
            celda.setColspan(5);
            tabla3.addCell(celda);

            // OTRA FILA
            celda = new PdfPCell(new Paragraph("II.- IDENTIFICACION FAMILIAR", ft3));
            celda.setBackgroundColor(myColor2);
            celda.setColspan(6);
            tabla3.addCell(celda);

            /**/
            celda = new PdfPCell(new Paragraph("Vive", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getVive_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Tiene Hijos", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getHijos_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Cantidad", ft3));
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + reg.getHijos_cantidad(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            document.add(tabla3);

            //OTRA TABLE  /////////////////
            float[] colsWidth4 = {0.46f, 0.40f, 0.27f, 0.27f, 0.32f, 0.28f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);

            // OTRA FILA
            celda = new PdfPCell(new Paragraph("Contacto", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(6);
            tabla4.addCell(celda);

            Iterator it_con = lista_contacto.iterator();

            if (lista_contacto.isEmpty()) {

                celda = new PdfPCell(new Paragraph("Sin datos de contacto adicionales", ft1));
                celda.setColspan(6);
                tabla4.addCell(celda);

            } else {
                ///encabezado tabla contacto
                celda = new PdfPCell(new Paragraph("Nombre", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla4.addCell(celda);
                celda = new PdfPCell(new Paragraph("Parentesco", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla4.addCell(celda);
                celda = new PdfPCell(new Paragraph("Telefonos", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla4.addCell(celda);
                celda = new PdfPCell(new Paragraph("Telefonos", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla4.addCell(celda);
                celda = new PdfPCell(new Paragraph("Dirección", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(2);
                tabla4.addCell(celda);

                ///
                while (it_con.hasNext()) {
                    cPaciente pac = (cPaciente) it_con.next();

                    celda = new PdfPCell(new Paragraph("" + pac.getNombres_paciente(), ft4));
                    celda.setColspan(1);
                    tabla4.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + pac.getParentesco_desc(), ft4));
                    celda.setColspan(1);
                    tabla4.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + pac.getTelefono1(), ft4));
                    celda.setColspan(1);
                    tabla4.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + pac.getTelefono2(), ft4));
                    celda.setColspan(1);
                    tabla4.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + pac.getDireccion(), ft4));
                    celda.setColspan(2);
                    tabla4.addCell(celda);

                }

                ///
            }
            document.add(tabla4);

////
            float[] colsWidth5 = {0.3f, 0.3f, 0.12f, 0.68f, 0.32f, 0.28f};
            PdfPTable tabla5 = new PdfPTable(colsWidth5);
            tabla5.setWidthPercentage(90);
            //
            // OTRA FILA
            celda = new PdfPCell(new Paragraph("III.- BREVE DESCRIPCION DE SITUACION ACTUAL", ft3));
            celda.setBackgroundColor(myColor2);
            celda.setColspan(6);
            tabla5.addCell(celda);

            // OTRA FILA
            celda = new PdfPCell(new Paragraph("" + reg.getSituacion(), ft1));
            celda.setColspan(6);
            tabla5.addCell(celda);

            // OTRA FILA
            celda = new PdfPCell(new Paragraph("IV.- PLAN DE TRABAJO", ft3));
            celda.setBackgroundColor(myColor2);
            celda.setColspan(6);
            tabla5.addCell(celda);

            // OTRA FILA
            celda = new PdfPCell(new Paragraph("" + reg.getPlan(), ft1));
            celda.setColspan(6);
            tabla5.addCell(celda);

            // OTRA FILA
            celda = new PdfPCell(new Paragraph("V.- SEGUIMIENTO Y EVOLUCION", ft3));
            celda.setBackgroundColor(myColor2);
            celda.setColspan(6);
            tabla5.addCell(celda);

            document.add(tabla5);

            /*FIN TABLA 4*/
            float[] colsWidth6 = {0.12f, 0.3f, 0.30f, 0.68f, 0.32f, 0.28f};
            PdfPTable tabla6 = new PdfPTable(colsWidth6);
            tabla6.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("#", ft3));
            celda.setBackgroundColor(myColor);
            tabla6.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha", ft3));
            celda.setBackgroundColor(myColor);
            tabla6.addCell(celda);

            celda = new PdfPCell(new Paragraph("Descripción", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(4);
            tabla6.addCell(celda);

            document.add(tabla6);

            Iterator it_seg = lista_seguimiento.iterator();
            int k = 0;
            while (it_seg.hasNext()) {
                cRegistroSeguimiento seg = (cRegistroSeguimiento) it_seg.next();
                k++;
                float[] colsWidth7 = {0.12f, 0.3f, 0.3f, 0.68f, 0.32f, 0.28f};
                PdfPTable tabla7 = new PdfPTable(colsWidth7);
                tabla7.setWidthPercentage(90);

                celda = new PdfPCell(new Paragraph("" + k, ft1));
                tabla7.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + seg.getFecha_seguimiento(), ft1));
                tabla7.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + seg.getDescripcion(), ft1));
                celda.setColspan(4);
                tabla7.addCell(celda);

                document.add(tabla7);
            }

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PDF_registro_social.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_registro_social.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }

    }

    public class FooterPiePaginaiText_registro_social extends PdfPageEventHelper {

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
