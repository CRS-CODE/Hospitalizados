/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cAlta_Das;
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

import CapaDato.cDas;
import CapaDato.cDiagnostico;
import CapaDato.cExamen;
import CapaDato.cObservacion;
import CapaNegocio.NegocioQ;
import com.itextpdf.text.html.WebColors;
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
public class PDF_DAS extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("application/pdf");
        response.setHeader("charset=UTF-8", " attachment; filename=\"Informe.pdf\"");

//        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();


        /********************DATOS***************************/
        NegocioQ neg = new NegocioQ();
        int id_das = Integer.parseInt(request.getParameter("txt_das"));
        ArrayList lista_diagnostico = neg.lista_diagnostico_suam(id_das, "1");
        ArrayList lista_observacion = neg.lista_observacion_suam(id_das);
        ArrayList lista_examen = neg.lista_examen_x_das(id_das);

        cDas das = neg.obtiene_das(id_das);
        cAlta_Das alt = neg.obtiene_alta_das(id_das);

        Iterator it1 = lista_diagnostico.iterator();
        Iterator it2 = lista_observacion.iterator();
        Iterator it3 = lista_observacion.iterator();
        Iterator it4 = lista_observacion.iterator();

        Iterator it5 = lista_examen.iterator();
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

            String tipo_informe = "DATO OBSERVACIÓN ADULTO SERVICIO DE URGENCIA";

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
            document.add(new Paragraph("\n\n\n\n"));
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


            //fila 3
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);


            document.add(tabla1);


            /****TABLA 2***********************************************************************/
            float[] colsWidth = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(90);

            //fila 1
            BaseColor myColor = WebColors.getRGBColor("#6699FF");

            celda = new PdfPCell(new Paragraph("Rut", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getRut_paciente(), ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla2.addCell(celda);


            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Nombre", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getNombres_paciente() + " " + das.getApellidop_paciente() + " " + das.getApellidom_paciente(), ft1v2));
            celda.setColspan(3);
            //celda.setBorder(0);
            tabla2.addCell(celda);


            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Edad", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getEdad() + "", ft1));
            celda.setColspan(3);
            //celda.setBorder(0);
            tabla2.addCell(celda);

//OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);

            document.add(tabla2);

            /****TABLA 3***********************************************************************/
            float[] colsWidth3 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(90);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("N° DAS", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getId_das(), ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);

            int numero_dau = 0;
            String modo_dau = "--";
            if (das.getDau_id() == 0) {
                modo_dau = "N° DAU NN";
                numero_dau = das.getDau_nn_id();
            } else {
                modo_dau = "N° DAU";
                numero_dau = das.getDau_id();
            }

            celda = new PdfPCell(new Paragraph("" + modo_dau, ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + numero_dau, ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);


            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Fecha Ingreso", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getFecha_ingreso(), ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("Camilla", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getCamilla_descri(), ft1));
            //celda.setBorder(0);
            tabla3.addCell(celda);



            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Medico Tratante", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getNombre_medico() + " " + das.getApellidop_medico() + " " + das.getApellidom_medico(), ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla3.addCell(celda);

            /*
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Derivador", ft3));
            //celda.setBorder(0);
            celda.setBackgroundColor(myColor);
            tabla3.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + das.getDerivador_descri(), ft1));
            //celda.setBorder(0);
            celda.setColspan(3);
            tabla3.addCell(celda);
             */


            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla3.addCell(celda);





            document.add(tabla3);


            /****TABLA 4 DIAGNOSTICOS*******************************************************************/
            float[] colsWidth4 = {0.80f, 1.00f, 0.8f, 1.4f};
            PdfPTable tabla4 = new PdfPTable(colsWidth4);
            tabla4.setWidthPercentage(90);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Diagnóstico(s) de Ingreso", ft3));
            //celda.setBorder(0);

            celda.setColspan(4);

            celda.setBackgroundColor(myColor);
            tabla4.addCell(celda);


            if (lista_diagnostico.isEmpty()) {
                //OTRA FILA
                celda = new PdfPCell(new Paragraph("Sin Diagnósticos", ft1));
                //celda.setBorder(0);
                celda.setColspan(4);
                tabla4.addCell(celda);
            }


            while (it1.hasNext()) {
                cDiagnostico diag = (cDiagnostico) it1.next();

                //OTRA FILA

                celda = new PdfPCell(new Paragraph("" + diag.getFecha_corta(), ft1));
                //celda.setBorder(0);
                celda.setColspan(1);
                tabla4.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + diag.getDescripcion_diagnostico(), ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla4.addCell(celda);

            }

            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla4.addCell(celda);

            document.add(tabla4);

            /****TABLA 5 OBSERVACION*******************************************************************/
            float[] colsWidth5 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla5 = new PdfPTable(colsWidth5);
            tabla5.setWidthPercentage(90);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Evolución e Indicaciones", ft3));
            //celda.setBorder(0);
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla5.addCell(celda);

            boolean sw_indicaciones = false;
            while (it2.hasNext()) {
                cObservacion obs = (cObservacion) it2.next();

                //OTRA FILA
                if (obs.getObservacion_detalle().trim().equals("null")) {
                    sw_indicaciones = true;
                    celda = new PdfPCell(new Paragraph("" + obs.getFecha_corta(), ft1));
                    //celda.setBorder(0);
                    celda.setColspan(1);
                    tabla5.addCell(celda);

                    celda = new PdfPCell(new Paragraph("" + obs.getObservacion_detalle(), ft1));
                    //celda.setBorder(0);
                    celda.setColspan(3);
                    tabla5.addCell(celda);
                }
            }

            if (sw_indicaciones == false) {
                //OTRA FILA
                celda = new PdfPCell(new Paragraph("-Sin indicaciones registradas-", ft1));
                //celda.setBorder(0);
                celda.setColspan(4);
                tabla5.addCell(celda);
            }


            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla5.addCell(celda);

            document.add(tabla5);


            /****TABLA 6 RADIOGRAFIA*******************************************************************/
           float[] colsWidth6 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla6 = new PdfPTable(colsWidth6);
            tabla6.setWidthPercentage(90);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Espera de Radiografía", ft3));
            //celda.setBorder(0);
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla6.addCell(celda);



            boolean sw_radio = false;
            while (it3.hasNext()) {
                cObservacion obs = (cObservacion) it3.next();

                //OTRA FILA
                if (obs.getEspera_radiografia() == 1) {
                    sw_radio = true;
                    celda = new PdfPCell(new Paragraph("" + obs.getFecha_corta(), ft1));
                    //celda.setBorder(0);
                    celda.setColspan(1);
                    tabla6.addCell(celda);


                    celda = new PdfPCell(new Paragraph("En espera radiografía", ft1));
                    //celda.setBorder(0);
                    celda.setColspan(3);
                    tabla6.addCell(celda);
                }


            }


            if (sw_radio == false) {
                //OTRA FILA
                celda = new PdfPCell(new Paragraph("-Sin esperas registradas-", ft1));
                //celda.setBorder(0);
                celda.setColspan(4);
                tabla6.addCell(celda);
            }


            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla6.addCell(celda);

            document.add(tabla6);


           //TABLA 7 LABORATORIO******************************************************************
            float[] colsWidth7 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla7 = new PdfPTable(colsWidth7);
            tabla7.setWidthPercentage(90);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Espera de Laboratorio", ft3));
            //celda.setBorder(0);
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla7.addCell(celda);

            boolean sw_lab = false;
            while (it4.hasNext()) {
                cObservacion obs = (cObservacion) it4.next();

                //OTRA FILA
                if (obs.getEspera_ex_laboratorio() == 1) {
                    sw_lab = true;
                    celda = new PdfPCell(new Paragraph("" + obs.getFecha_corta(), ft1));
                    //celda.setBorder(0);
                    celda.setColspan(1);
                    tabla7.addCell(celda);



                    celda = new PdfPCell(new Paragraph("En espera lab.", ft1));
                    //celda.setBorder(0);
                    celda.setColspan(3);
                    tabla7.addCell(celda);
                }
            }

            if (sw_lab == false) {
                //OTRA FILA
                celda = new PdfPCell(new Paragraph("-Sin esperas registradas-", ft1));
                //celda.setBorder(0);
                celda.setColspan(4);
                tabla7.addCell(celda);
            }
            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla7.addCell(celda);

            document.add(tabla7);
          
            /****EXAMENES****************************************************************/
            float[] colsWidth9 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla9 = new PdfPTable(colsWidth9);
            tabla9.setWidthPercentage(90);
            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Exámenes y Radiografías", ft3));
            //celda.setBorder(0);
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla9.addCell(celda);
            boolean sw_exa = false;

            while (it5.hasNext()) {
                cExamen exa = (cExamen) it5.next();
                sw_exa = true;
                //OTRA FILA

                celda = new PdfPCell(new Paragraph("" + exa.getFecha_corta(), ft1));
                //celda.setBorder(0);
                celda.setColspan(1);
                tabla9.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + exa.getDescripcion(), ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla9.addCell(celda);

            }

            if (sw_exa == false) {
                //OTRA FILA
                celda = new PdfPCell(new Paragraph("-Sin exámenes registrados-", ft1));
                //celda.setBorder(0);
                celda.setColspan(4);
                tabla9.addCell(celda);
            }
            //OTRA FILA
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla9.addCell(celda);

            document.add(tabla9);

            /****FIN EXAMENES****************************************************************/
            /****EGRESO****************************************************************/
            float[] colsWidth8 = {0.75f, 1.05f, 0.8f, 1.4f};
            PdfPTable tabla8 = new PdfPTable(colsWidth8);
            tabla8.setWidthPercentage(90);

            //OTRA FILA
            celda = new PdfPCell(new Paragraph("Egreso", ft3));
            //celda.setBorder(0);
            celda.setColspan(4);
            celda.setBackgroundColor(myColor);
            tabla8.addCell(celda);

            if (alt.getId_das() <= 0) {

                celda = new PdfPCell(new Paragraph("Sin egreso registrado", ft1));
                //celda.setBorder(0);
                celda.setColspan(4);
                tabla8.addCell(celda);

            } else {


                //OTRA FILA
                celda = new PdfPCell(new Paragraph("Médico", ft3));
                //celda.setBorder(0);
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla8.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + alt.getMedico_nombre() + " " + alt.getMedico_apellidop() + " " + alt.getMedico_apellidom(), ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla8.addCell(celda);

                //OTRA FILA
                celda = new PdfPCell(new Paragraph("Destino", ft3));
                //celda.setBorder(0);
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla8.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + alt.getDestino_descri(), ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla8.addCell(celda);


                //OTRA FILA
                celda = new PdfPCell(new Paragraph("Diagnóstico de Egreso", ft3));
                //celda.setBorder(0);
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla8.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + alt.getDetalle(), ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla8.addCell(celda);

                //OTRA FILA
                celda = new PdfPCell(new Paragraph("Fecha Egreso", ft3));
                //celda.setBorder(0);
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla8.addCell(celda);

                celda = new PdfPCell(new Paragraph("" + alt.getFecha_ingreso(), ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla8.addCell(celda);


                //OTRA FILA
                celda = new PdfPCell(new Paragraph("Tiempo en Obs.", ft3));
                //celda.setBorder(0);
                celda.setColspan(1);
                celda.setBackgroundColor(myColor);
                tabla8.addCell(celda);

                String cadena_tiempo = "";

                if (alt.getDif_dd() > 1) {
                    cadena_tiempo += "" + alt.getDif_dd() + " días ";
                } else if (alt.getDif_dd() == 1) {
                    cadena_tiempo += "" + alt.getDif_dd() + " día ";
                } else {
                    cadena_tiempo += "";
                }

                cadena_tiempo += "" + neg.dig_cero(alt.getDif_hh()) + ":" + neg.dig_cero(alt.getDif_mm()) + ":" + neg.dig_cero(alt.getDif_ss());


                celda = new PdfPCell(new Paragraph("" + cadena_tiempo, ft1));
                //celda.setBorder(0);
                celda.setColspan(3);
                tabla8.addCell(celda);


            }


            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            tabla8.addCell(celda);
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            tabla8.addCell(celda);
            celda = new PdfPCell(new Paragraph("      Fecha de Impresión: ", ft4));
            celda.setBorder(0);
            tabla8.addCell(celda);
            celda = new PdfPCell(new Paragraph(actual, ft4));
            celda.setBorder(0);
            tabla8.addCell(celda);

            document.add(tabla8);

            /**************************************************************************/
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PDF_DAS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(PDF_DAS.class.getName()).log(Level.SEVERE, null, "Ocurrio un Error" + e);
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
