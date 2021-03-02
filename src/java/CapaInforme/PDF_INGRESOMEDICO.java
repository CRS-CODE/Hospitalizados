/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDiagnostico;
import CapaDato.cDuo;
import CapaDato.cIngreso_Medico;
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
public class PDF_INGRESOMEDICO extends HttpServlet {

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

//        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();

        /**
         * ******************DATOS**************************
         */
        NegocioQ neg = new NegocioQ();
        int id_duo = Integer.parseInt(request.getParameter("id_duo"));

        /*   duo  */
        cDuo duo = neg.obtiene_duo(id_duo);
        cIngreso_Medico im = neg.Buscaringreso(id_duo);

        /**
         * ***************DATOS*****************************
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

            String tipo_informe = "INGRESO MEDICO";

//IMAGEN
            ServletContext context = this.getServletConfig().getServletContext();
            String path1 = context.getRealPath("/Imagenes");
            Image img = Image.getInstance(path1 + "/Logo_Gob.jpg");

//        img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth()) / 2,
//                (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
            img.scalePercent(25);
            img.setAbsolutePosition(60, PageSize.A4.getHeight() - img.getScaledHeight() - 40);
            document.add(img);
//FIN IMAGEN
            FooterPiePaginaiText_pdf_duo footer = new FooterPiePaginaiText_pdf_duo();
            //Asignamos el manejador de eventos al escritor.
            writer.setPageEvent(footer);

            document.add(new Paragraph("\n\n\n"));
            //creamos una tabla con 3 columnas

            //creamos una tabla con 3 columnas
            float[] colsWidth2 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90);  // aqui cambio el ancho de la tabla ,, obviamente ¬¬
            //
//CREO UN ARREGLO QUE CONTIENE LAS MEDIDAS DE CADA UNA DE LAS COLUMNAS
// EN MI CASO SON 4, (TB PUEDES PASAR EL ARREGLO DIRECTAMENTE)
            //float[] medidaCeldas = {0.55f, 0.55f, 0.55f, 0.55f};
//// ASIGNAS LAS MEDIDAS A LA TABLA (ANCHO)
//          tabla1.setWidths(medidaCeldas);

            //añadimos texto con formato a la primera celda
            Font ft1 = FontFactory.getFont("Verdana", 9, Font.NORMAL, BaseColor.BLACK);
      
            Font ft2 = FontFactory.getFont("Verdana", 10, Font.NORMAL, BaseColor.BLACK);
            Font ft3 = FontFactory.getFont("Verdana", 11, Font.NORMAL, BaseColor.WHITE);

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
            float[] colsWidth = {1.15f, 1f, 0.8f, 0.9f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(90);

            //fila 1
            BaseColor myColor = WebColors.getRGBColor("#1f75ef");

            /*empezar*/
            celda = new PdfPCell(new Paragraph("Nombre", ft3));
            celda.setColspan(3);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Fecha Ingreso", ft3));

            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            /* otra fila*/
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), ft1));
            celda.setColspan(3);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med().substring(0, 10), ft1));

            //celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Edad", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("F. de Nacimiento", ft3));
            celda.setColspan(2);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Hora Ingreso", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            //fila 1
            celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
            //celda.setBorder(0);

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(""+duo.getFecha_nac(), ft1));
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med().substring(duo.getFecha_hora_ing_med().length() - 8, duo.getFecha_hora_ing_med().length()), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("RUT", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Prevision", ft3));
            celda.setColspan(2);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Procedencia", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            //fila 1
            celda = new PdfPCell(new Paragraph("" + duo.getRut_paciente(), ft1));
            //celda.setBorder(0);

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph(""+duo.getCodigo_fonasa_descripcion() + "  " + duo.getTramo_prevision(), ft1));
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getConsultorio_descri(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("CESFAM", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(4);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getDerivador_descripcion(), ft1));
            celda.setColspan(4);
            tabla2.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph("\n", ft1));

            celda.setColspan(4);

            tabla2.addCell(celda);

            document.add(tabla2);

            /**
             * ****************tabla 3****************
             */
            float[] colsWidth3 = {4f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("MOTIVO DE INGRESO", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getMotivodeingreso() + "", ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("ENFERMEDAD ACTUAL", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getEnfermedadactual() + "", ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);

            document.add(tabla3);

            /**
             * *************fin tabla 3**************
             */
            /**
             * *************** tabla 4****************
             */
            float[] colsWidth4 = {2f, 2f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("ANTECEDENTES MÒRBIDOS", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("a)Médicos:", ft2));
            //celda.setBorder(0);

            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("Cardiovasculares:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);


            /**/
            celda = new PdfPCell(new Paragraph("" + im.getCardiovasculares() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("Metabólico:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);


            /**/
            celda = new PdfPCell(new Paragraph("" + im.getMetabolicas() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("Endocrinológicas:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);


            /**/
            celda = new PdfPCell(new Paragraph("" + im.getEndocrinologicas() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("Gastrointestinales:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);


            /**/
            celda = new PdfPCell(new Paragraph("" + im.getGastrointestinales() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("Salud Mental:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);


            /**/
            celda = new PdfPCell(new Paragraph("" + im.getSaludmetales() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("Otras(especificar):", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);


            /**/
            celda = new PdfPCell(new Paragraph("" + im.getOtras() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("b) Quirúrgicos:", ft2));
            //celda.setBorder(0);

            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getQuirurgicos() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("c) Gineco obstétricos:", ft2));
            //celda.setBorder(0);

            celda.setColspan(2);
            tabla4.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getGinecoobstetrico() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            // OTRA CELDA
            celda = new PdfPCell(new Paragraph("Medicamentos", ft3));
            celda.setColspan(2);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getMedicamentos() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            // OTRA CELDA
            celda = new PdfPCell(new Paragraph("Alergias y Hábitos", ft3));

            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Tabaco: " + im.getTabaco(), ft1));
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("IPA: " + im.getIpa(), ft1));
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Alergias:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getAlergias() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Alcohol:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getAlcohol() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Drogas:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getDrogas() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Alimentación:", ft2));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getAlimentacion() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("Antecedentes sociales , personales y familiares:", ft3));

            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getAntecedentesspf() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("REVISIÒN POR SISTEMAS:", ft3));

            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getRevisionporsistema() + "", ft1));
            celda.setColspan(2);
            tabla4.addCell(celda);

            celda = new PdfPCell(new Paragraph("\n", ft1));

            celda.setColspan(2);

            tabla4.addCell(celda);

            document.add(tabla4);

            float[] colsWidth5 = {0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f};
            PdfPTable tabla5 = new PdfPTable(colsWidth5);
            tabla5.setWidthPercentage(90);
            celda = new PdfPCell(new Paragraph("EXAMEN FÌSICO GENERAL:", ft3));

            celda.setBackgroundColor(myColor);
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Presión Arterial:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getPresionarterial() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("PAM:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getPam() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Pulso:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getPulso() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Peso:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getPeso() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Sat.O2:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getSat02() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("FiO2:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getFio2() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Respiraciòn:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getRespiracion() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Talla:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getTalla() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Tº axilar:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getTauxilar() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Tº Rectal:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getTrectal() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("IMC:", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getIcm() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Posición y decúbito:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getPosicion() + "", ft1));
            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Ex. Mental:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getExmental() + "", ft1));
            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Piel y anexos:", ft2));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Gangilos:", ft2));
            celda.setColspan(4);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getPielyanexos() + "", ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getGanglios() + "", ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulso arteriales", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(" " + im.getPulso() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Carotideo", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Braquial", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Radial", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Femoral", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Poplíteo", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Tibial posterior", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Pedio", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Derecha ", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getCarotideoderecho() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getBraquialderecho() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getRadialderecho() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getFemoralderecho() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getPopliteoderecho() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getTibiaderecho() + " ", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getPedioderecho() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Izuierda ", ft2));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getCarotideoizquierda() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getBraquializquierda() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getRadializquierda() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getFemoralizquierda() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getPopliteoizquierda() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getTibiaizquierda() + " ", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getPedioizquierda() + "", ft1));
            celda.setColspan(1);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("\n", ft1));

            celda.setColspan(8);

            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("EXAMEN FISICO SEGMENTARIO", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Cabeza:", ft2));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("a) Ojos:", ft2));

            celda.setColspan(4);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("b) Oídos:", ft2));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getOjos() + "", ft1));

            celda.setColspan(4);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + im.getOidos() + "", ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("c) Fosas nasales:", ft2));

            celda.setColspan(4);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("d) Cavidad oral:", ft2));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + im.getFosasnasales() + "", ft1));

            celda.setColspan(4);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph(im.getCavidadoral(), ft1));
            celda.setColspan(4);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("e) Faringe:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getFaringe(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Cuello:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getCuello(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Tórax:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getTorax(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Corazón:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getCorazon(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Pulmón:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getPulmon(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Mamas:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getMamas(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Abdomen:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getAbdomen(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Extremidades:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getExtremidades(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Neurológico:", ft2));
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getNeurologico(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("\n", ft1));

            celda.setColspan(8);

            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("DIAGNÒSTICOS DE INGRESO:", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("a) Patología aguda:", ft2));

            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph(im.getPatologiaaguda(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("b) Comorbilidad:", ft2));

            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph(im.getComorbilidad(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("c) Síndromes Geriátricos:", ft2));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph(im.getSindromesgeriátricos(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Planes:", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph(im.getPlanes(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Indicaciones:", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(8);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph(im.getIndicaciones(), ft1));

            celda.setColspan(8);
            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("\n", ft1));

            celda.setColspan(8);

            tabla5.addCell(celda);

            celda = new PdfPCell(new Paragraph("Medico Residente: "+duo.getNombre_usuario_ing_med(), ft2));

            celda.setColspan(4);
            tabla5.addCell(celda);
            celda = new PdfPCell(new Paragraph("Firma:", ft2));

            celda.setColspan(4);
            tabla5.addCell(celda);

            document.add(tabla5);

            /**
             * *************fin tabla 4**************
             */
            /**
             * ***********************************************************************
             */
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
