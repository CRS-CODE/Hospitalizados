/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import jxl.format.Alignment;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.format.Alignment;
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
public class nominaPacientes extends HttpServlet {

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

        Date dia = new Date();
        Locale currentLocale = new Locale("es", "CL");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);

        Vector<cDuo> sectorOne = neg.listadelDiasector1();

        Vector<cDuo> sectorTwo = neg.listadelDiasector2();

        // Vector<cDuo> vi3 = neg.listadelDiasector3();
        try {
            org.apache.poi.ss.usermodel.Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
            /**
             * ************FUENTES**********
             */
            HSSFFont font = (HSSFFont) wb.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Verdana");
            //font.setItalic(true);
            //font.setStrikeout(true);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
            style.setFont(font);
            /**
             * **********************************************
             */
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
           /*sector*/
             
            HSSFFont fontSector = (HSSFFont) wb.createFont();
            fontSector.setFontHeightInPoints((short) 12);
            fontSector.setFontName("Verdana");
            fontSector.setColor((short) HSSFColor.WHITE.index);
            fontSector.setItalic(false);
            
            fontSector.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Sector = (HSSFCellStyle) wb.createCellStyle();
            style_Sector.setFont(fontDestacado);
            
            style_Sector.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_Sector.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style_Sector.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_Sector.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_Sector.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            style_Sector.setAlignment(HSSFCellStyle.ALIGN_CENTER);
           
            style_Sector.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
           
           
            Sheet sheet0 = wb.createSheet("DATOS");
            //Esta es el Header y el Footer
            HSSFHeader cabecera = (HSSFHeader) sheet0.getHeader();
            cabecera.setLeft("Ministerio de Salud\nCentro de referencia de Salud Maipú\n");

            HSSFFooter pie = (HSSFFooter) sheet0.getFooter();
            pie.setCenter("Pagina " + HSSFFooter.page() + " de " + HSSFFooter.numPages());//N° de Pagina
            //Fin Esta es el Header y el Footer

            int control_fila = 1;
            String horas = "";
            String dias = "";
            String edad = "";

            HSSFRow row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            HSSFCell cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas
            // cell0.setCellValue("N°"); //vacia el nombre de las columnas en la hoja de excell

            cell = row.createCell((short) 1);
            row.createCell((short) 1); // crea las celdas de acuerdo al numero de columnas
            cell.setCellValue("NOMINA PACIENTES HOSPITALIZADOS "); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    4, //first row (0-based)
                    4, //last row  (0-based)
                    4, //first column (0-based)
                    7 //last column  (0-based)
            ));

            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Dia " + formateadorFecha.format(dia) + "  - Hora : " + dateFormat.format(date) + " "); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    4, //first column (0-based)
                    7 //last column  (0-based)
            ));
            control_fila++;

            control_fila = 5;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("Nª Cama");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Rut Paciente");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 2); //crea la celda tipo
            cell.setCellValue("Apellido Paterno");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 3); //crea la celda tipo
            cell.setCellValue("Apellido Materno");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 4); //crea la celda tipo
            cell.setCellValue("Nombre");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 5); //crea la celda tipo
            cell.setCellValue("Edad");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 6); //crea la celda tipo
            cell.setCellValue("Fecha Ingreso");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 7); //crea la celda tipo
            cell.setCellValue("Hora Ingreso");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 8); //crea la celda tipo

            cell.setCellValue("Dias Hospitalizados");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 9); //crea la celda tipo
            cell.setCellValue("Categorizacion");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 10); //crea la celda tipo

            control_fila++;

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Sector 1"); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Sector); //Asigna el estilo del nombre de las columnas
            

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    1, //first column (0-based)
                    9 //last column  (0-based)
            ));

            for (cDuo duo : neg.listadelDiasector1()) {

                control_fila++;
                horas = "";
                dias = "";
                edad = "";
                if (!duo.getRut_paciente().equalsIgnoreCase("")) {
                    horas = duo.getHora_duo();
                    dias = String.valueOf(duo.getDias_cama());
                    edad = duo.getEdad() + " Años";
                   
                }

                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(duo.getCama_descripcion().replace("CAMA", ""));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(duo.getRut_paciente());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getApellidop_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getApellidom_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getNombres_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(edad);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(duo.getFecha_creacion());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(horas);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(dias);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(duo.getCategorizacion_descripcion());
                cell.setCellStyle(style_Normal);

            }
            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Sector 2"); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Sector); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    1, //first column (0-based)
                    9 //last column  (0-based)
            ));

            for (cDuo duo : neg.listadelDiasector2()) {

                control_fila++;
                horas = "";
                dias = "";
                edad = "";
                if (!duo.getRut_paciente().equalsIgnoreCase("")) {
                    horas = duo.getHora_duo();
                    dias = String.valueOf(duo.getDias_cama());
                    edad = duo.getEdad() + " Años";
                   
                }


                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(duo.getCama_descripcion().replace("CAMA", ""));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(duo.getRut_paciente());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getApellidop_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getApellidom_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getNombres_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(edad);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(duo.getFecha_creacion());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(horas);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(dias);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(duo.getCategorizacion_descripcion());
                cell.setCellStyle(style_Normal);

            }
            control_fila++;

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("Sector 3"); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Sector); //Asigna el estilo del nombre de las columnas
            

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    1, //first column (0-based)
                    9 //last column  (0-based)
            ));

            for (cDuo duo : neg.listadelDiasector3()) {

                control_fila++;
                horas = "";
                dias = "";
                edad = "";
                if (!duo.getRut_paciente().equalsIgnoreCase("")) {
                    horas = duo.getHora_duo();
                    dias = String.valueOf(duo.getDias_cama());
                    edad = duo.getEdad() + " Años";
                   
                }

                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(duo.getCama_descripcion().replace("CAMA", ""));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(duo.getRut_paciente());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getApellidop_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getApellidom_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(neg.primeraMayuscula(duo.getNombres_paciente()));
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(edad);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(duo.getFecha_creacion());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(horas);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(dias);
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(duo.getCategorizacion_descripcion());
                cell.setCellStyle(style_Normal);

            }

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
