/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cEnfermedad;
import CapaDato.cVisita;
import CapaDato.cVistaUoce;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Kairin
 */
public class XLS_AdmissionHospitalization extends HttpServlet {

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

        NegocioQ neg = new NegocioQ();
        response.setContentType("application/vnd.ms-excel");
        ServletContext context = this.getServletConfig().getServletContext();
        String path2 = context.getRealPath("\\imagen\\").replace("build", "");

        Date hoy = new Date();
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
        Locale currentLocale2 = new Locale("es", "Ch");
        java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);

       
        String fecha1_dma = request.getParameter("fecha_inicio");
        String fecha2_dma = request.getParameter("fecha_fin");

        String dia1 = fecha1_dma.substring(0, 2);
        String mes1 = fecha1_dma.substring(3, 5);
        String año1 = fecha1_dma.substring(6, 10);
        String dia2 = fecha2_dma.substring(0, 2);
        String mes2 = fecha2_dma.substring(3, 5);
        String año2 = fecha2_dma.substring(6, 10);
        String fecha1_mda = mes1 + "-" + dia1 + "-" + año1;
        String fecha2_mda = mes2 + "-" + dia2 + "-" + año2;

        ArrayList lista_duo = neg.getAdmissionHospitalization( fecha1_mda, fecha2_mda);
        Iterator it_lista = lista_duo.iterator();

       

        try {
            org.apache.poi.ss.usermodel.Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
            /**************FUENTES***********/
            HSSFFont font = (HSSFFont) wb.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Verdana");
            //font.setItalic(true);
            //font.setStrikeout(true);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
            style.setFont(font);
            /*************************************************/
            /*UNO*/
            HSSFFont fontDestacado = (HSSFFont) wb.createFont();
            fontDestacado.setFontHeightInPoints((short) 10);
            fontDestacado.setFontName("Verdana");
            fontDestacado.setColor((short) HSSFColor.WHITE.index);
            fontDestacado.setItalic(false);
            fontDestacado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Destacado = (HSSFCellStyle) wb.createCellStyle();
            style_Destacado.setFont(fontDestacado);
            style_Destacado.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_Destacado.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style_Destacado.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_Destacado.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_Destacado.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);

            style_Destacado.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            /*DOS*/
            HSSFFont fontNormal = (HSSFFont) wb.createFont();
            fontNormal.setFontHeightInPoints((short) 10);
            fontNormal.setFontName("Verdana");
            fontNormal.setColor((short) HSSFColor.BLACK.index);
            fontNormal.setItalic(false);
            fontNormal.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Normal = (HSSFCellStyle) wb.createCellStyle();
            style_Normal.setFont(fontNormal);
            //  style_Destacado.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            style_Normal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_Normal.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style_Normal.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_Normal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_Normal.setFillForegroundColor(HSSFColor.WHITE.index);
            style_Normal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            /**************FUENTES***********/
            /****************************************HOJA DATOS *******************************************/
            Sheet sheet0 = wb.createSheet("DATOS");
            //Esta es el Header y el Footer
            HSSFHeader cabecera = (HSSFHeader) sheet0.getHeader();
            cabecera.setLeft("Ministerio de Salud\nCentro de referencia de Salud Maipú\n");

            HSSFFooter pie = (HSSFFooter) sheet0.getFooter();
            pie.setCenter("Pagina " + HSSFFooter.page() + " de " + HSSFFooter.numPages());//N° de Pagina
            //Fin Esta es el Header y el Footer

            int control_fila = 1;

            HSSFRow row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            HSSFCell cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas
            // cell0.setCellValue("N°"); //vacia el nombre de las columnas en la hoja de excell

            cell = row.createCell((short) 1);
            row.createCell((short) 1); // crea las celdas de acuerdo al numero de columnas
            cell.setCellValue("Ingresos a Hospitalización"); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    1, //first row (0-based)
                    1, //last row  (0-based)
                    1, //first column (0-based)
                    3 //last column  (0-based)
                    ));

            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Dia " + fecha1_dma + " al " + fecha2_dma + " "); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    1, //first column (0-based)
                    2 //last column  (0-based)
                    ));
            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Generado el " + formateadorFecha.format(hoy) + " " + formateadorHora.format(hoy)); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    1, //first column (0-based)
                    2 //last column  (0-based)
                    ));

            /*otra celda*/
            /*********************CUADRO 1**********************/
            control_fila = 5;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("RUT Paciente");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Nombre y Apellidos Paciente");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 2); //crea la celda tipo
            cell.setCellValue("Edad (años)");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 3); //crea la celda tipo
            cell.setCellValue("Sexo");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 4); //crea la celda tipo
            cell.setCellValue("CESFAM de Pertenencia");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 5); //crea la celda tipo
            cell.setCellValue("Establecimiento de Origen");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 6); //crea la celda tipo
            cell.setCellValue("Ingreso Administrativo – RUT de usuario");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 7); //crea la celda tipo
            cell.setCellValue("Ingreso Administrativo – Fecha");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 8); //crea la celda tipo
            cell.setCellValue("Ingreso Administrativo – Hora");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 9); //crea la celda tipo
            cell.setCellValue("Ingreso Médico – RUT de usuario médico");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 10); //crea la celda tipo
            cell.setCellValue("Ingreso Médico – Fecha");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 11); //crea la celda tipo
            cell.setCellValue("Ingreso Médico – Hora");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 12); //crea la celda tipo
            cell.setCellValue("Ingreso Enfermería – RUT de usuario enfermera");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 13); //crea la celda tipo
            cell.setCellValue("Ingreso Enfermería – Fecha");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 14); //crea la celda tipo
            cell.setCellValue("Ingreso Enfermería – Hora");
            cell.setCellStyle(style_Destacado);


            cell = row.createCell((short) 15); //crea la celda tipo
            cell.setCellValue("Primera Categorización Enfermería – Resultado");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 16); //crea la celda tipo
            cell.setCellValue("Primera Categorización Enfermería – Fecha");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 17); //crea la celda tipo
            cell.setCellValue("Primera Categorización Enfermería – Hora");
            cell.setCellStyle(style_Destacado);
            

            while (it_lista.hasNext()) {
                cDuo admission = (cDuo) it_lista.next();
                control_fila++;

               
                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(admission.getRut_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(admission.getNombres_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(admission.getEdad());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(admission.getSexo_descri());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(admission.getConsultorio_descri());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(admission.getDerivador_descripcion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(admission.getRut_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(admission.getFecha_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(admission.getHora_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(admission.getRut_usuario_ing_med());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 10); //crea la celda tipo
                cell.setCellValue(admission.getFecha_hora_ing_med());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 11); //crea la celda tipo
                cell.setCellValue(admission.getDireccion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 12); //crea la celda tipo
                cell.setCellValue(admission.getRut_sdv());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 13); //crea la celda tipo
                cell.setCellValue(admission.getFecha_hora_ing_enf());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 14); //crea la celda tipo
                cell.setCellValue(admission.getComuna_descri());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 15); //crea la celda tipo
                cell.setCellValue(admission.getCategorizacion_descripcion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 16); //crea la celda tipo
                cell.setCellValue(admission.getFecha_creacion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 17); //crea la celda tipo
                cell.setCellValue(admission.getFecha_hora_alta_adm_duo());
                cell.setCellStyle(style_Normal);
               
                
            }

            /****************************************FIN HOJA DATOS ****************************************/
            /**************************************** TABLA 2***************************************/
            control_fila++;
            control_fila++;
            control_fila++;
            control_fila++;

           


            /****************************************FIN TABLA 2***************************************/
            for (int j = 0; j < 17; j++) {
                sheet0.autoSizeColumn((short) j);
            }


            wb.write(response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
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
