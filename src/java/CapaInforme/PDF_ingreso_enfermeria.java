/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDiagnostico;
import CapaDato.cDocumento;
import CapaDato.cDuo;
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
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
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
 * @author EseGamboa
 */
public class PDF_ingreso_enfermeria extends HttpServlet {

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

        ArrayList lista_diagnostico = neg.lista_diagnostico(id_duo, "2");
        cIngresoEnfermeria eu = neg.obtiene_ingreso_enfermeria(id_duo);
        ArrayList lista_documento = neg.lista_documento_segun_duo(id_duo);

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

            String tipo_informe = "INGRESO DE ENFERMERIA";

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

            celda = new PdfPCell(new Paragraph("Derivado", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Hora", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            // OTRA FILA
            try {
                celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
                tabla2.addCell(celda);
                celda = new PdfPCell(new Paragraph("" + duo.getDerivador_descripcion(), ft1));
                tabla2.addCell(celda);
                celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_enf().substring(0, 10), ft1));
                tabla2.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_enf().substring(duo.getFecha_hora_ing_enf().length() - 8, duo.getFecha_hora_ing_enf().length()), ft1));
                tabla2.addCell(celda);
            } catch (Exception ex) {
                celda = new PdfPCell(new Paragraph("--", ft1));
                tabla2.addCell(celda);
                celda = new PdfPCell(new Paragraph("--", ft1));
                tabla2.addCell(celda);

            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Teléfonos", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Direccion", ft3));
            celda.setColspan(3);
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("" + duo.getTelefono1() + " // " + duo.getTelefono2(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getDireccion() + ", " + duo.getComuna_descri(), ft1));
            celda.setColspan(3);
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

            celda = new PdfPCell(new Paragraph("Diagnósticos", ft3));
            celda.setBackgroundColor(myColor);
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
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setColspan(2);
            celda.setBorder(0);
            tabla3.addCell(celda);

            document.add(tabla3);

            /**
             * *************************FIN TABLA 3*************************
             */
            /**
             * *************************TABLA 4*****************************
             */
            float[] colsWidth4 = {4f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("ANTECEDENTES MÓRBIDOS", ft3));
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getOtro_comorbilidad_ing_enfermeria(), ft1));
            //celda.setFixedHeight(140f);
            celda.setRowspan(5);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setBorder(0);
            tabla4.addCell(celda);

            document.add(tabla4);
            /**
             * *************************FIN TABLA 3*************************
             */
            /**
             * *************************TABLA 5*****************************
             */

            float[] colsWidth5 = {4f};
            PdfPTable tabla5 = new PdfPTable(colsWidth5);
            tabla5.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("TRATAMIENTOS FARMACOLÓGICOS PREVIOS", ft3));
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getFarmaco_ing_enfermeria(), ft1));
            //celda.setFixedHeight(140f);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setBorder(0);
            tabla5.addCell(celda);

            document.add(tabla5);
            /**
             * *************************FIN TABLA 5*************************
             */
            /**
             * *************************TABLA 6*****************************
             */
            float[] colsWidth6 = {4f};
            PdfPTable tabla6 = new PdfPTable(colsWidth6);
            tabla6.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("HISTORIAL ACTUAL", ft3));
            celda.setBackgroundColor(myColor);
            tabla6.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getObs_ing_enfermeria(), ft1));
            //celda.setFixedHeight(140f);
            
            tabla6.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setBorder(0);
            tabla6.addCell(celda);

            document.add(tabla6);

            /**
             * *************************FIN TABLA 6*************************
             */
            /**
             * *************************TABLA 8****************************
             */
            document.newPage();

            float[] colsWidth8 = {0.4f, 0.4f, 0.3f, 0.3f, 0.3f, 0.3f};
            PdfPTable tabla8 = new PdfPTable(colsWidth8);
            tabla8.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("EXAMEN FISICO", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(6);
            tabla8.addCell(celda);
            //otra celda
            celda = new PdfPCell(new Paragraph("Conciencia", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getConciencia_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Cabeza", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getCabeza_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Mucosas", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getMucosa_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Torax", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getTorax_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Dorso lumbar", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getDorso_lumbar_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Pie y tegumentos", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getPiel_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Abdomen", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getAbdomen_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("EESS", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getEess_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("EEII", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getEeii_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Zona sacra", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getZ_sacra_ex_fisico(), ft1));
            celda.setColspan(5);
            tabla8.addCell(celda);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Peso", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getPeso_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("Talla", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getTalla_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulso", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getPulso_ex_fisico(), ft1));
            tabla8.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("P/A", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getPresion_a_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("T°", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getTemp_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("SAT.DE O2", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getSatura_ex_fisico(), ft1));
            tabla8.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("VVP1", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getVvp1_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("VVP2", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getVvp2_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("VVC", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getVvc_ex_fisico(), ft1));
            tabla8.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("SNG", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getSng_ex_fisico(), ft1));
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("S. Foley", ft3));
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + eu.getS_foley_ex_fisico(), ft1));
            celda.setColspan(3);
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
            /**
             * ***********************TABLA 9*******************************
             */
            float[] colsWidth9 = {4f};
            PdfPTable tabla9 = new PdfPTable(colsWidth9);
            tabla9.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("DOCUMENTOS Y/O EXAMENES QUE EL PACIENTE TRAE", ft3));
            celda.setBackgroundColor(myColor);
            tabla9.addCell(celda);

            String cadena_documento = "";

            Iterator it_doc = lista_documento.iterator();
            while (it_doc.hasNext()) {
                cDocumento doc = (cDocumento) it_doc.next();
                cadena_documento += doc.getDescripcion() + " - ";
            }
            if (cadena_documento.length() > 0) {
                cadena_documento = cadena_documento.substring(0, cadena_documento.length() - 2);
            }

            if (eu.getOtro_ex_docto_ing_enfermeria().trim().length()>0){
            cadena_documento+="\n\nOTROS:\n"+eu.getOtro_ex_docto_ing_enfermeria();
            }
            
            celda = new PdfPCell(new Paragraph(""+cadena_documento, ft1));
           // celda.setFixedHeight(100f);
            tabla9.addCell(celda);

            document.add(tabla9);
            /**
             * *************************FIN TABLA 8****************************
             */
            /* TABLA 10*/

            float[] colsWidth10 = {4f};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph(" \n \n \n \n                              EU Responsable " + duo.getNombre_usuario_ing_enf()
                    + " \n                                       UNIDAD DE OBSERVACIÓN", ft2));
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
