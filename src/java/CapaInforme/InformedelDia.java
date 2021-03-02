/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cVistaUoce;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author a
 */
public class InformedelDia extends HttpServlet {

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
        response.setHeader("Content-Disposition", "attachment; filename=pacientes_deldia.xls");
       // ServletContext context = this.getServletConfig().getServletContext();
       // String path2 = context.getRealPath("\\imagen\\").replace("build", "");

        
        Date hoy = new Date();
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
        Locale currentLocale2 = new Locale("es", "Ch");
        java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);

       
      
      
        ArrayList lista_duo = neg.listadelDia();
        Iterator it_lista = lista_duo.iterator();

        try {
            Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
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
            cabecera.setLeft("Ministerio de Salud\nCentro de referencia de Salud Maipú");

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
            cell.setCellValue("ESTADO DE LAS CAMAS EN EL DIA "+ formateadorFecha.format(hoy) + " " + formateadorHora.format(hoy)); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    1, //first row (0-based)
                    1, //last row  (0-based)
                    1, //first column (0-based)
                    3 //last column  (0-based)
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
            cell.setCellValue("Apellido Moderno");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 4); //crea la celda tipo
            cell.setCellValue("Nombre");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 5); //crea la celda tipo
            cell.setCellValue("Fecha Ingreso");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 6); //crea la celda tipo
            cell.setCellValue("Hora Ingreso");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 7); //crea la celda tipo
            cell.setCellValue("Dias Hospitalizados");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 8); //crea la celda tipo
            


            while (it_lista.hasNext()) {
                cDuo vis = (cDuo) it_lista.next();
                control_fila++;
                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(vis.getCama_descripcion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(vis.getRut_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(vis.getApellidop_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(vis.getApellidom_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 4); //crea la celda tipo
                 cell.setCellValue(vis.getNombres_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 5); //crea la celda tipo
                
                cell.setCellValue(vis.getFecha_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(vis.getHora_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(vis.getDias_cama()   );
                cell.setCellStyle(style_Normal);
                
                
            }


            /****************************************FIN HOJA DATOS ****************************************/
            for (int j = 0; j < 25; j++) {
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
