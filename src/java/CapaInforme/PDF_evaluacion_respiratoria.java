/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;

import CapaDato.cEvaRespiratoria;
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
public class PDF_evaluacion_respiratoria extends HttpServlet {

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
        int id_eva_respi = Integer.parseInt(request.getParameter("txt_id"));
        //   ArrayList lista_diagnostico = neg.lista_diagnostico_suam(id_das, "1");

        cEvaRespiratoria res = neg.obtiene_evaluacion_respiratoria(id_eva_respi);
        // cEvaNeurologia eva = new cEvaNeurologia();
        cDuo duo = neg.obtiene_duo(res.getId_duo());

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

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            String tipo_informe = "EVALUACION RESPIRATORIA - DUO " + duo.getId_duo();

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

            PDF_evaluacion_respiratoria.FooterPiePaginaiText_respi footer = new PDF_evaluacion_respiratoria.FooterPiePaginaiText_respi();
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
            Font ft1 = FontFactory.getFont("Verdana", 7, Font.ITALIC, BaseColor.BLACK);
            Font ft3 = FontFactory.getFont("Verdana", 7, Font.ITALIC, BaseColor.WHITE);

            Font ft11 = FontFactory.getFont("Verdana", 7, Font.BOLDITALIC, BaseColor.BLUE);
            Font ft4 = FontFactory.getFont("Verdana", 5, Font.ITALIC, BaseColor.BLACK); // PARA LA HORA DE IMPRESIOn
            Font ft2 = FontFactory.getFont("Verdana", 9, Font.ITALIC, BaseColor.BLACK);

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

            celda = new PdfPCell(new Paragraph("" + res.getFecha_ingreso(), ft1));

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getHora_ingreso(), ft1));

            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /*FIN TABLA 2*/
            /*TABLA 3*/
            float[] colsWidth3 = {0.95f, 1f, 1f, 1.25f, 0.5f, 1.3f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Oxígeno", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            if (res.getOxigeno() == 1) {

                celda = new PdfPCell(new Paragraph("" + res.getOxigeno_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Detalle", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph(res.getOxigeno_det() + " lt/min", ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

            } else {
                celda = new PdfPCell(new Paragraph(res.getOxigeno_desc() + "", ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Vía Venosa", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getVia_venosa_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Vía Urinaria o Sonda", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getVia_urinaria_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Detalle", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getVia_urinaria_det(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
            //OTRA FILA

            celda = new PdfPCell(new Paragraph("Vigil", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            if (res.getVigil() == 1) {
                // SI ES VIGIl
                celda = new PdfPCell(new Paragraph(res.getVigil_desc(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
                // OTRA FILA

                celda = new PdfPCell(new Paragraph("Alteración Lenguaje", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph(res.getAlteracion_lenguaje_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Estímulo Verbal", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph(res.getEstimulo_verbal_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Estí. Visual", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph(res.getEstimulo_visual_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

            } else {
                // SI NO ES VIGIl
                celda = new PdfPCell(new Paragraph(res.getVigil_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Estado", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph(res.getEstado_conciencia_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);

            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Ubicación Temporal", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getUbicacion_temporal_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Ubicación Espacial", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getUbicacion_espacial_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Postura", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getPostura_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("EESS", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getEESS(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("EEII", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getEEII(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Deformidades", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getDeformidad(), ft1));
            celda.setColspan(5);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Estado Nutricional", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getEstado_nutricional_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Coloración Piel", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            if (res.getColoracion_piel() == 1) {
                celda = new PdfPCell(new Paragraph("" + res.getColoracion_piel_desc(), ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
            } else {
                celda = new PdfPCell(new Paragraph("" + res.getColoracion_piel_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("Cianósis", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getCianosis_desc(), ft1));
                celda.setColspan(1);
                tabla3.addCell(celda);

            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("                                              "
                    + "                                                       Estado Piel", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(6);
            tabla3.addCell(celda);

            if (res.getEp_normal() == 1) {
                celda = new PdfPCell(new Paragraph("Normal", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getEp_normal_det(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            if (res.getEp_escara() == 1) {
                celda = new PdfPCell(new Paragraph("Escara", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getEp_escara_det(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            if (res.getEp_cicatriz() == 1) {
                celda = new PdfPCell(new Paragraph("Cicatriz", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getEp_cicatriz_det(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            if (res.getEp_hematoma() == 1) {
                celda = new PdfPCell(new Paragraph("Hematoma", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getEp_hematoma_det(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            if (res.getEp_petequia() == 1) {
                celda = new PdfPCell(new Paragraph("Petequia", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getEp_petequia_det(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            if (res.getEp_edema() == 1) {
                celda = new PdfPCell(new Paragraph("Edema", ft3));
                celda.setBackgroundColor(myColor);
                celda.setColspan(1);
                tabla3.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + res.getEp_edema_det(), ft1));
                celda.setColspan(5);
                tabla3.addCell(celda);
            }

            if (res.getEp_normal() == 0 && res.getEp_escara() == 0 && res.getEp_cicatriz() == 0
                    && res.getEp_hematoma() == 0 && res.getEp_petequia() == 0 && res.getEp_edema() == 0) {

                celda = new PdfPCell(new Paragraph("                   "
                        + "                         Sin Observaciones", ft1));
                celda.setColspan(6);

                tabla3.addCell(celda);
            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("                                                       "
                    + "                                                                         ", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(6);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Patrón Respiratorio", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getPatron_respiratorio_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Respirador", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getRespirador_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Dificultad Respiratoria", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            if (res.getDificultad_respiratoria()==1){
            celda = new PdfPCell(new Paragraph("" + res.getDificultad_respiratoria_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
                
            celda = new PdfPCell(new Paragraph("Uso Musculatura Accesoria", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);
            
            
            if (res.getMusculatura_accesoria()==1){
            celda = new PdfPCell(new Paragraph("" + res.getMusculatura_accesoria_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Detalle", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getMusculatura_accesoria_det(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);
            }else{
            celda = new PdfPCell(new Paragraph("" + res.getMusculatura_accesoria_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);
            }
                     
            }else{
            celda = new PdfPCell(new Paragraph("" + res.getDificultad_respiratoria_desc(), ft1));
            celda.setColspan(5);
            tabla3.addCell(celda);
            }
            
            
  

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Aleteo Nasal", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getAleteo_nasal_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Aleteo Costal", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getAleteo_costal_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Temp.", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getTemperatura_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Elasticidad de la piel", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getElasticidad_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Detalle", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getElasticidad_det(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Restricción Miofascial", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getRestriccion_miofascial(), ft1));
            celda.setColspan(5);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Llene Capilar", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getLlene_capilar_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Edema", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getEdema_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Dolor", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getDolor_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("EVA", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getEVA_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Desalineación Articular", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getDesalineacion(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Frecuencia Cardíaca", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getFrecuencia_cardiaca(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Frecuencia Respiratoria", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getFrecuencia_respiratoria(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Saturación", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getSaturacion(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Tos", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getTos_presencia_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Tipo Tos", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getTos_produccion_desc(), ft1));
            celda.setColspan(3);
            tabla3.addCell(celda);

            
            if (res.getTos_produccion()==1){
                //OTRA FILA
            celda = new PdfPCell(new Paragraph("Secr. Cantidad", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getSecrecion_cantidad_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Secr. Coloración", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getSecrecion_coloracion_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("Secr. Tipo", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getSecrecion_tipo_desc(), ft1));
            celda.setColspan(1);
            tabla3.addCell(celda);

            }
        
            document.add(tabla3);
            /*FIN TABLA 3*/

            /* TABLA 5*/
            float[] colsWidth5 = {1.2f, 0.8f, 1.2f, 0.8f, 1.2f, 0.8f, 1.2f, 0.8f, 1.2f, 0.8f};
            PdfPTable tabla5 = new PdfPTable(colsWidth5);
            tabla5.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("                                             "
                    + "                                                     Auscultación", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(10);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulmón Izquierdo", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulmón Derecho", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Lóbulo Superior", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getAus_pi_ls_desc(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getAus_pd_ls_desc(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Lóbulo Medio", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("//", ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getAus_pd_lm_desc(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Lóbulo Inferior", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getAus_pi_li_desc(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getAus_pd_li_desc(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("                                              "
                    + "                                                 Ruidos Agregados", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(10);
            tabla5.addCell(celda);

            // PARA LOS CHECKBOX DE RUIDOS AGREGADOS
            celda = new PdfPCell(new Paragraph("Sibilancias", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getSibilancia_desc(), ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Roncus", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getRoncus_desc(), ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Estertor traqueal", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getEstertor_traqueal_desc(), ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Crep. finas", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getCrepitacion_fina_desc(), ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Crep. gruesas", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + res.getCrepitacion_gruesa_desc(), ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulmón Izquierdo", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulmón Derecho", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Lóbulo Superior", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getRa_pi_ls(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getRa_pd_ls(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Lóbulo Medio", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("//", ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getRa_pd_lm(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Lóbulo Inferior", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getRa_pi_li(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(res.getRa_pd_li(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            document.add(tabla5);
            /*FIN TABLA 5*/


            /* TABLA 10*/
            float[] colsWidth10 = {4f};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph(" \n \n \n \n                                        Responsable " + res.getNombre_usuario() + " " + res.getApellidop_usuario()
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

    public class FooterPiePaginaiText_respi extends PdfPageEventHelper {

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
