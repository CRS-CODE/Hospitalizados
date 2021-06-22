/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaInforme;


import CapaDato.cDas;
import CapaNegocio.NegocioQ;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 *
 * @author Silvio
 */
public class XLS_InformeDas extends HttpServlet {
   
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

        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        String fecha1_dma = request.getParameter("fecha1");
        String fecha2_dma = request.getParameter("fecha2");
  
      


        String dia1 = fecha1_dma.substring(0, 2);
        String mes1 = fecha1_dma.substring(3, 5);
        String año1 = fecha1_dma.substring(6, 10);
        String fecha1_mda = mes1 + "-" + dia1 + "-" + año1;
        String fecha1_dma_sh = dia1 + "-" + mes1 + "-" + año1;


        String dia2 = fecha2_dma.substring(0, 2);
        String mes2 = fecha2_dma.substring(3, 5);
        String año2 = fecha2_dma.substring(6, 10);
        String fecha2_mda = mes2 + "-" + dia2 + "-" + año2;
        String fecha2_dma_sh = dia2 + "-" + mes2 + "-" + año2;

  
        int modo= Integer.parseInt(request.getParameter("modo"));
        ArrayList lista = neg.lista_das(fecha1_dma_sh,fecha2_dma_sh,modo);

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
          //  99;184;255
            style_Destacado.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);






            style_Destacado.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            /*DOS*/
            HSSFFont fontNormal = (HSSFFont) wb.createFont();
            fontNormal.setFontHeightInPoints((short) 10);
            fontNormal.setFontName("Verdana");
            fontNormal.setColor((short) HSSFColor.BLACK.index);
            fontNormal.setItalic(false);
            //fontNormal.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Normal = (HSSFCellStyle) wb.createCellStyle();
            style_Normal.setFont(fontNormal);
            //  style_Destacado.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            style_Normal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_Normal.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style_Normal.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_Normal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_Normal.setFillForegroundColor(HSSFColor.WHITE.index);
            style_Normal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            
            HSSFCellStyle style_Normal_sin_margen = (HSSFCellStyle) wb.createCellStyle();
            style_Normal_sin_margen.setFont(fontNormal);

            style_Normal_sin_margen.setFillForegroundColor(HSSFColor.WHITE.index);
            style_Normal_sin_margen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);



            /**************FUENTES***********/
            /****************************************HOJA DATOS *******************************************/
            Sheet sheet0 = wb.createSheet("DATOS");
            //Esta es el Header y el Footer
            HSSFHeader cabecera = (HSSFHeader) sheet0.getHeader();
            cabecera.setLeft("Ministerio de Salud\nCentro de referencia de Salud Maipú\nSistemas Zauron");

            HSSFFooter pie = (HSSFFooter) sheet0.getFooter();
            pie.setCenter("Pagina " + HSSFFooter.page() + " de " + HSSFFooter.numPages());//N° de Pagina
            //Fin Esta es el Header y el Footer

            int control_fila = 1;

            HSSFRow row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            HSSFCell cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas
            // cell0.setCellValue("N°"); //vacia el nombre de las columnas en la hoja de excell

            cell = row.createCell((short) 0);
            row.createCell((short) 1); // crea las celdas de acuerdo al numero de columnas
            cell.setCellValue("NÓMINA DE PACIENTES OBSERVACIÓN ADULTO"); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    1, //first row (0-based)
                    1, //last row  (0-based)
                    0, //first column (0-based)
                    12 //last column  (0-based)
                    ));

            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("Fecha seleccionada    :  Entre " + fecha1_dma + " y "+fecha2_dma); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Normal_sin_margen); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    0, //first column (0-based)
                    3 //last column  (0-based)
                    ));
            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("Informe generado      :" + formateadorFecha.format(hoy) + " " + formateadorHora.format(hoy)); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Normal_sin_margen); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    0, //first column (0-based)
                    3 //last column  (0-based)
                    ));

            /*otra celda*/

            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("Registros Encontrados: "+lista.size()); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Normal_sin_margen); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
                    0, //first column (0-based)
                    3 //last column  (0-based)
                    ));

            /*otra celda*/


            /*********************CUADRO 1**********************/
            control_fila = 6;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("ID DAS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("N° DAU");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 2); //crea la celda tipo
            cell.setCellValue("RUT PACIENTE");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 3); //crea la celda tipo
            cell.setCellValue("NOMBRES PACIENTE");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 4); //crea la celda tipo
            cell.setCellValue("APELLIDO P.");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 5); //crea la celda tipo
            cell.setCellValue("APELLIDO M.");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 6); //crea la celda tipo
            cell.setCellValue("ESTADO");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 7); //crea la celda tipo
            cell.setCellValue("FECHA INGRESO");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 8); //crea la celda tipo
            cell.setCellValue("CAMILLA");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 9); //crea la celda tipo
            cell.setCellValue("DERIVADOR");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 10); //crea la celda tipo
            cell.setCellValue("MEDICO");
            cell.setCellStyle(style_Destacado);


            cell = row.createCell((short) 11); //crea la celda tipo
            cell.setCellValue("FECHA EGRESO DESTINO");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 12); //crea la celda tipo
             cell.setCellValue("EGRESO DESTINO");
            cell.setCellStyle(style_Destacado);

            Iterator it = lista.iterator();
            while (it.hasNext()) {
                cDas das = (cDas) it.next();
                control_fila++;

                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(das.getId_das());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(das.getDau_id());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(das.getRut_paciente());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(das.getNombres_paciente());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(das.getApellidop_paciente());
                cell.setCellStyle(style_Normal);

               cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(das.getApellidom_paciente());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(""+das.getEstado_descri());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(""+das.getFecha_ingreso());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(""+das.getCamilla_descri());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(""+das.getDerivador_descri());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 10); //crea la celda tipo
                cell.setCellValue(""+das.getMedico());
                cell.setCellStyle(style_Normal);

    
                cell = row.createCell((short) 11); //crea la celda tipo
                cell.setCellValue(""+das.getAlta_fecha_ingreso());
                cell.setCellStyle(style_Normal);

                 cell = row.createCell((short) 12); //crea la celda tipo
                cell.setCellValue(""+das.getAlta_destino_descri());
                cell.setCellStyle(style_Normal);

     
            
            }

            /****************************************FIN HOJA DATOS ****************************************/
            for (int j = 0; j < 21; j++) {
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
