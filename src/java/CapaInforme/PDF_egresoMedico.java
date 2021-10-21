/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDiagnostico;
import CapaDato.cDocumento;
import CapaDato.cDuo;
import CapaDato.cEnfermedad;
import CapaDato.cEpicrisis;
import CapaDato.cIngresoEnfermeria;
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
 * @author Kairin
 */
public class PDF_egresoMedico extends HttpServlet {

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
        response.setHeader("charset=UTF-8", " attachment; filename=\"Egreso Medico.pdf\"");
//      Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();
        /**
         * ******************DATOS**************************
         */

        NegocioQ neg = new NegocioQ();
        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
        //   ArrayList lista_diagnostico = neg.lista_diagnostico_suam(id_das, "1");

        cDuo duo = neg.obtiene_duo(id_duo);

        ArrayList lista_diagnostico = neg.lista_diagnostico(id_duo, " 1,2 ");
        cEpicrisis epicrisis = neg.getEpicrisis(id_duo);

        /**
         * * ***************DATOS*****************************
         */
        Locale currentLocale2 = new Locale("es", "Ch");
        java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);
        NumberFormat formateadorNumero = NumberFormat.getNumberInstance(currentLocale2);
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
        java.text.DateFormat formateadorFechaLarga = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, currentLocale);

        ArrayList lista_enfermedad = neg.obtiene_duo_enfermedad(id_duo);
        Iterator it_enf = lista_enfermedad.iterator();
        String Enfermedades_cronicas = " ";
        cEnfermedad enf = new cEnfermedad();
        while (it_enf.hasNext()) {
            enf = (cEnfermedad) it_enf.next();
            Enfermedades_cronicas += " " + enf.getDescripcion() + " -";
        }
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

            String tipo_informe = "EPICRISIS";

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

            FooterPiePaginaiText footer = new FooterPiePaginaiText();
            //Asignamos el manejador de eventos al escritor.
            writer.setPageEvent(footer);
            // fin pie de pagina
            document.add(new Paragraph("\n\n\n\n"));
            //creamos una tabla con 3 columnas

            //creamos una tabla con 3 columnas
            float[] colsWidth2 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬

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

            /**
             * **********************************
             */
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

            celda = new PdfPCell(new Paragraph("Fecha", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Hora", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Dias Hospitalizados", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            // OTRA FILA
            try {
                celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
                tabla2.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_enf().substring(0, 10), ft1));
                tabla2.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_enf().substring(duo.getFecha_hora_ing_enf().length() - 8, duo.getFecha_hora_ing_enf().length()), ft1));
                tabla2.addCell(celda);
                int dayReales = duo.getDias_reales_cama() == 0 ?  1 : duo.getDias_reales_cama();
                int day = duo.isTengoEgreso() ? dayReales : duo.getDias_cama();
                
                celda = new PdfPCell(new Paragraph("" + day, ft1));
                //celda.setBorder(0);
                tabla2.addCell(celda);
            } catch (Exception ex) {
                celda = new PdfPCell(new Paragraph("--", ft1));
                tabla2.addCell(celda);
                celda = new PdfPCell(new Paragraph("--", ft1));
                tabla2.addCell(celda);

            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Fecha Ingreso", ft3));
            celda.setColspan(2);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha Egreso", ft3));
            celda.setColspan(2);
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            //OTRA FILA

            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med(), ft1));
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + epicrisis.getFecha_epicrisis(), ft1));
            celda.setColspan(2);
            //celda.setBorder(0);
            tabla2.addCell(celda);

            document.add(tabla2);

            /**
             * *************************FIN TABLA 2*************************
             */
            /**
             * *************************TABLA 3*****************************
             */
            float[] colsWidth3 = {1.55f, 2.45f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Diagnósticos Ingreso", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla3.addCell(celda);

            String cadena_diagnosticos = "";
            Iterator it_dia = lista_diagnostico.iterator();
            while (it_dia.hasNext()) {
                cDiagnostico dia = (cDiagnostico) it_dia.next();
                cadena_diagnosticos += dia.getDescripcion_diagnostico() + "-";
            }
            if (cadena_diagnosticos.length() > 0) {
                cadena_diagnosticos = cadena_diagnosticos.substring(0, cadena_diagnosticos.length() - 1);
            }
            celda = new PdfPCell(new Paragraph(cadena_diagnosticos, ft1));
            celda.setColspan(2);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setColspan(2);
            celda.setBorder(0);
            tabla3.addCell(celda);

            document.add(tabla3);

           
            float[] colsWidth4 = {4f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Diagnostico Egreso", ft3));
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + epicrisis.getDiagnostico_epicrisis(), ft1));
            //celda.setFixedHeight(140f);
            celda.setRowspan(5);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setBorder(0);
            tabla4.addCell(celda);

            document.add(tabla4);

            document.newPage();

            float[] colsWidth8 = {0.4f, 0.4f, 0.3f, 0.3f, 0.3f, 0.3f};
            PdfPTable tabla8 = new PdfPTable(colsWidth8);
            tabla8.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Resumen Breve de Hospitalización", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(6);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + epicrisis.getResumen_epicrisis(), ft1));
            celda.setColspan(6);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("Exámenes realizados", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + epicrisis.getExamen_epicrisis(), ft1));
            celda.setColspan(6);
            tabla8.addCell(celda);
            
            celda = new PdfPCell(new Paragraph("Listado de medicamentos prescritos durante la hospitalización", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + epicrisis.getMedicamentos_prescritos(), ft1));
            celda.setColspan(6);
            tabla8.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Enfermedades Crónica", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + Enfermedades_cronicas, ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA

            celda = new PdfPCell(new Paragraph("Indicaciones al Alta", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + epicrisis.getIndicacion_epicrisis(), ft1));
            celda.setColspan(6);
            tabla8.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setColspan(6);
            celda.setBorder(0);
            tabla8.addCell(celda);

            document.add(tabla8);

            /**
             * *************************FIN TABLA 8****************************
             */
            /* TABLA 10*/
            float[] colsWidth10 = {4f};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Duo " + duo.getId_duo() + " firmado en sistema por Rut " + epicrisis.getRut_usuario() + ", el " + epicrisis.getFecha_epicrisis() + " a las " + epicrisis.getHora_epicrisis() + "\n \n"
                    + "   Dr." + epicrisis.getNombre_usuario() + " " + epicrisis.getApellidop_usuario() + " " + epicrisis.getApellidom_usuario() + "\n", ft2));
            //celda.setBorder(0);
            tabla10.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha de Impresión :" + actual, ft1));
            tabla10.addCell(celda);

            document.add(tabla10);

            /* FIN TABLA 10 */
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PDF_ingreso_enfermeria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_ingreso_enfermeria.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
        }

    }

    public class FooterPiePaginaiText extends PdfPageEventHelper {

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
