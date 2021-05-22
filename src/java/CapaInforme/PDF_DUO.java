/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDiagnostico;
import CapaDato.cDuo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.Font;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;


import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import CapaNegocio.NegocioQ;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
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
 * @author Silvio
 */
public class PDF_DUO extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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


        /********************DATOS***************************/
        NegocioQ neg = new NegocioQ();
        int id_duo = Integer.parseInt(request.getParameter("id_duo"));

        /*   duo  */
        cDuo duo = neg.obtiene_duo(id_duo);
        ArrayList enfermedades = neg.lista_enfermedad_cronica(id_duo);
        ArrayList diagnosticos = neg.lista_diagnostico(id_duo, " 1,2 ");
        ArrayList trazadores = neg.lista_diagnostico_trazador(id_duo);

        Iterator it_enf = enfermedades.iterator();
        Iterator it_dia = diagnosticos.iterator();
        Iterator it_tra = trazadores.iterator();

        /*****************DATOS******************************/
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


            String tipo_informe = "DATO UNIDAD DE OBSERVACIÓN\n  N° " + id_duo ;

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


            /****TABLA 2***********************************************************************/
            float[] colsWidth = {1.15f, 1f, 0.8f, 0.9f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(90);

            //fila 1
            BaseColor myColor = WebColors.getRGBColor("#6699FF");

            celda = new PdfPCell(new Paragraph("Paciente", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Rut", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Sexo", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            //fila 1


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
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Derivado", ft3));
            celda.setBackgroundColor(myColor);
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Fecha", ft3));
            celda.setBackgroundColor(myColor);
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Hora", ft3));
            celda.setBackgroundColor(myColor);
            //celda.setBorder(0);
            tabla2.addCell(celda);

            /************/
            try{
            celda = new PdfPCell(new Paragraph("" + duo.getEdad(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getDerivador_descripcion(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med().substring(0, 10), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getFecha_hora_ing_med().substring(duo.getFecha_hora_ing_med().length()-8,duo.getFecha_hora_ing_med().length() ), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);
               } catch (Exception ex) {
                celda = new PdfPCell(new Paragraph("", ft1));
                tabla2.addCell(celda);
                celda = new PdfPCell(new Paragraph("", ft1));
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

            /* otra fila*/
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("" + duo.getTelefono1() + " // " + duo.getTelefono2(), ft1));
            //celda.setBorder(0);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getDireccion()+", "+duo.getComuna_descri(), ft1));
            celda.setColspan(3);
            //celda.setBorder(0);
            tabla2.addCell(celda);

            // OTRA FILA

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Cama a la que ingreso", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("Consultorio de Pertenencia", ft3));
            celda.setColspan(3);
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("" + duo.getCama_descripcion(), ft1));
            //celda.setBorder(0);

            tabla2.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getConsultorio_descri(), ft1));
            celda.setColspan(3);
            //celda.setBorder(0);
            tabla2.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /******************tabla 3*****************/
            float[] colsWidth3 = {4f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            celda = new PdfPCell(new Paragraph("Anamnesis", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);

            celda = new PdfPCell(new Paragraph("" + duo.getAnamnesis_duo().trim(), ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);


            document.add(tabla3);


            /***************fin tabla 3***************/
            /***************** tabla 4*****************/
            float[] colsWidth4 = {2f, 2f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);


            celda = new PdfPCell(new Paragraph("Enf. Crónicas", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);


            /**/
            String enfermedad_cronica = "";
            while (it_enf.hasNext()) {
                cDiagnostico dia = (cDiagnostico) it_enf.next();
                enfermedad_cronica += dia.getDescripcion_diagnostico() + " -";
            }
            /**/
            if (enfermedad_cronica.length() > 0) {
                enfermedad_cronica = enfermedad_cronica.substring(0, enfermedad_cronica.length() - 1);
            }

            celda = new PdfPCell(new Paragraph("" + enfermedad_cronica, ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);


            // OTRA CELDA
            celda = new PdfPCell(new Paragraph("Diagnósticos Trazadores", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);

            /**/

            String diagnostico_trazador = "";
            while (it_tra.hasNext()) {
                cDiagnostico dia = (cDiagnostico) it_tra.next();

                diagnostico_trazador += dia.getDescripcion_diagnostico() + " -";
            }
            if (diagnostico_trazador.length() > 0) {
                diagnostico_trazador = diagnostico_trazador.substring(0, diagnostico_trazador.length() - 1);
            }

            /**/

            celda = new PdfPCell(new Paragraph("" + diagnostico_trazador, ft1));
            //celda.setBorder(0);
            tabla4.addCell(celda);


            // OTRA CELDA
            celda = new PdfPCell(new Paragraph("Diagnósticos", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla4.addCell(celda);

            String diagnostico = "";
            while (it_dia.hasNext()) {
                cDiagnostico dia = (cDiagnostico) it_dia.next();

                diagnostico += dia.getDescripcion_diagnostico() + " \n";

            }
            if (diagnostico.length() > 0) {
                diagnostico = diagnostico.substring(0, diagnostico.length() - 2);
            }

            celda = new PdfPCell(new Paragraph("" + diagnostico, ft1));
            //celda.setBorder(0);
            celda.setColspan(2);
            tabla4.addCell(celda);


            celda = new PdfPCell(new Paragraph("Duo "+duo.getId_duo()+" firmado en sistema por Rut "+duo.getRut_usuario_ing_med()+", el "+duo.getFecha_hora_ing_med().substring(0, 10)+" a las " +"" + duo.getFecha_hora_ing_med().substring(duo.getFecha_hora_ing_med().length()-8,duo.getFecha_hora_ing_med().length())+" "           
                    + " \n \n                                              Dr. "+duo.getNombre_usuario_ing_med(), ft2));
            //celda.setBorder(0);
            celda.setColspan(2);
            tabla4.addCell(celda);


            celda = new PdfPCell(new Paragraph("Fecha de Impresión :" + actual, ft1));
            //celda.setBorder(0);

            celda.setColspan(2);
            tabla4.addCell(celda);


            document.add(tabla4);


            /***************fin tabla 4***************/
            /**************************************************************************/
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
