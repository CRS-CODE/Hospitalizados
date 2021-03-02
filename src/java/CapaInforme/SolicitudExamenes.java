/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cReceta;
import CapaDato.pdfconpieyfoto;
import CapaNegocio.NegocioQ;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a
 */
public class SolicitudExamenes extends HttpServlet {

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
            throws ServletException, IOException, DocumentException {
            
             response.setHeader("charset=UTF-8", " attachment; filename=\"Informe.pdf\"");
            NegocioQ neg = new NegocioQ();

        int idindicacion = Integer.parseInt(request.getParameter("id_ind"));
        //   ArrayList lista_diagnostico = neg.lista_diagnostico_suam(id_das, "1");

        cReceta rec = neg.bucarindicacion(idindicacion);

        cDuo duo = neg.obtiene_duo(rec.getId_receta_detalle());
        Document document = new Document(PageSize.LETTER, 50, 50, 50, 100);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        Locale currentLocale = new Locale("es", "CL");

        DateFormat formateadorFecha = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
        PdfWriter writer = PdfWriter.getInstance(document, buffer);

        int SPACE_TITULO = 10;
        int SPACE_NORMAL = 50;

        int SPACE_NORMAL2 = 17;
        int SPACE_ESPACIO = 4;

        Font TEXT_TITULO = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(68, 117, 196));
        Font TEXT_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL, new Color(0, 0, 0));

        Font TEXT_SUPERTITULO = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0));
        Font TEXT_SUPERTITULONORMAL = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL, new Color(0, 0, 0));

        Font Linea = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new Color(68, 114, 196));

        writer.setPageEvent(new pdfconpieyfoto());
        document.open();

        Table tabla_titulo;
        Cell celda;

        tabla_titulo = new Table(3);
        tabla_titulo.setBorderWidth(0);
        tabla_titulo.setPadding(1);
        tabla_titulo.setSpacing(0);
        tabla_titulo.setWidth(100);

        celda = new Cell(new Phrase(SPACE_ESPACIO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla_titulo.addCell(celda);

        String serviciot = "";
        String establecimientot = "";
        String especialidadt = "";
        String unidadt = "";
        
        String correlativo = "F-" + rec.getId_duo();

        serviciot = " Metropolitano Central(SSMC)";
        establecimientot = " Unidad de Media Estadia";
        especialidadt = " ";
        unidadt = " ";

        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla_titulo.addCell(celda);
        Paragraph rutCita = new Paragraph();
        rutCita.add(new Phrase(SPACE_NORMAL2, "", TEXT_SUPERTITULONORMAL));
        rutCita.add(new Phrase(SPACE_TITULO, "N° :", TEXT_NORMAL));
        rutCita.add(new Phrase(SPACE_NORMAL2, "", TEXT_SUPERTITULONORMAL));
        rutCita.add(new Phrase(SPACE_TITULO, correlativo, TEXT_SUPERTITULONORMAL));
        celda = new Cell(rutCita);
        celda.setBorderWidth(0);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "SOLICITUD DE EXAMENES", TEXT_SUPERTITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_ESPACIO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla_titulo.addCell(celda);
        celda = new Cell(new Phrase(SPACE_ESPACIO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);

        celda = new Cell(new Phrase(SPACE_ESPACIO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla_titulo.addCell(celda);
        celda = new Cell(new Phrase(SPACE_ESPACIO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);

        Paragraph variable1 = new Paragraph();
        variable1.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable1.add(new Phrase(SPACE_TITULO, "Nombre paciente: ", TEXT_NORMAL));
        variable1.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable1.add(new Phrase(SPACE_TITULO, duo.getNombres_paciente(), TEXT_SUPERTITULONORMAL));
        celda = new Cell(variable1);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        Paragraph variable2 = new Paragraph();
        variable2.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable2.add(new Phrase(SPACE_TITULO, "RUT: ", TEXT_NORMAL));
        variable2.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable2.add(new Phrase(SPACE_TITULO, duo.getRut_paciente() + "                     Edad:   " + duo.getEdad() + " ", TEXT_SUPERTITULONORMAL));
        celda = new Cell(variable2);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        Paragraph variable3 = new Paragraph();
        variable3.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable3.add(new Phrase(SPACE_TITULO, "N° Historia Clínica: ", TEXT_NORMAL));
        variable3.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable3.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        celda = new Cell(variable3);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);
        String d = "";
        


        /*Datos de la atencion*/
        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));

        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);
         Vector<String> recetas = neg.buscarSolicitudExamenesdeIndicaciones(idindicacion);
            String mens = "";
            for (int i = 0; i < recetas.size(); i++) {
                mens = mens + neg.upperCaseFirst(recetas.get(i)) + "  \n";
            }


        Paragraph variable5 = new Paragraph();
        variable5.add(new Phrase(SPACE_TITULO, "Examenes: \n", TEXT_SUPERTITULONORMAL));
        variable5.add(new Phrase(SPACE_TITULO, "", TEXT_NORMAL));
        variable5.add(new Phrase(SPACE_TITULO, "", TEXT_SUPERTITULONORMAL));
        variable5.add(new Phrase(SPACE_NORMAL, mens, TEXT_SUPERTITULONORMAL));
        celda = new Cell(variable5);
        // celda.setBackgroundColor(new Color(217, 225, 242));

        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla_titulo.addCell(celda);

        /*Datos de la atencion*/
        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);
        /*Datos de la atencion*/
        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        Paragraph nombredoctor = new Paragraph();
        nombredoctor.add(new Phrase(SPACE_NORMAL2, "", TEXT_SUPERTITULONORMAL));
        nombredoctor.add(new Phrase(SPACE_TITULO, "Nombre Médico: ", TEXT_NORMAL));
        nombredoctor.add(new Phrase(SPACE_NORMAL2, "", TEXT_SUPERTITULONORMAL));
        nombredoctor.add(new Phrase(SPACE_TITULO, rec.getNombre_usuario(), TEXT_SUPERTITULONORMAL));
        celda = new Cell(nombredoctor);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

       

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);
        /*Datos de la atencion*/
        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        celda = new Cell(new Phrase(SPACE_TITULO, "\n", TEXT_TITULO));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla_titulo.addCell(celda);

        Paragraph raya22 = new Paragraph();
        raya22.add(new Phrase(SPACE_NORMAL2, "", TEXT_SUPERTITULONORMAL));
        // nombredoctor.add(new Phrase(SPACE_TITULO, "Nombre: ", TEXT_NORMAL));

        raya22.add(new Phrase(SPACE_TITULO, formateadorFecha.format(new Date()) + "                                          _______________________________", TEXT_SUPERTITULONORMAL));
        celda = new Cell(raya22);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla_titulo.addCell(celda);

        /*pie de documento*/
        Paragraph pie2 = new Paragraph();
        pie2.add(new Phrase(SPACE_NORMAL2, "", TEXT_SUPERTITULONORMAL));
        pie2.add(new Phrase(SPACE_TITULO, "Fecha                                                              Firma", TEXT_SUPERTITULO));
        // pie.add(new Phrase(SPACE_TITULO, "Firma de la persona que notifica                                    Firma o huella digital del paciente o representante", TEXT_NORMAL2));

        celda = new Cell(pie2);
        // celda.setBackgroundColor(new Color(217, 225, 242));
        celda.setBorderWidth(0);
        celda.setColspan(3);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla_titulo.addCell(celda);

        document.add(tabla_titulo);

        document.close();
        try {

            DataOutput output = new DataOutputStream(response.getOutputStream());
            byte[] bytes = buffer.toByteArray();
            response.setContentLength(bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                output.writeByte(bytes[i]);
            }

        } catch (Exception exstream) {
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(SolicitudExamenes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(SolicitudExamenes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
