package CapaInforme;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import CapaDato.cDuo;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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
public class nominaPorFecha extends HttpServlet {

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
            throws ServletException, IOException, ParseException, WriteException {
        {

            NegocioQ neg = new NegocioQ();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=NominaPacientePorFecha.xls");
            WritableWorkbook workbook = jxl.Workbook.createWorkbook(response.getOutputStream());
            WritableSheet sheet = null;

            Locale currentLocale = new Locale("es", "CL");

            java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);

            String fecha1_dma = request.getParameter("fecha1");
            String fecha2_dma = request.getParameter("fecha2");
            Date fechaInicial = new Date(Integer.parseInt(fecha1_dma.substring(6, 10)) - 1900, Integer.parseInt(fecha1_dma.substring(3, 5)) - 1, Integer.parseInt(fecha1_dma.substring(0, 2)), 0, 0, 0);
            Date fechaFinal = new Date(Integer.parseInt(fecha2_dma.substring(6, 10)) - 1900, Integer.parseInt(fecha2_dma.substring(3, 5)) - 1, Integer.parseInt(fecha2_dma.substring(0, 2)), 0, 0, 0);
            int diasConsulta = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
            System.out.println("Hay " + diasConsulta + " dias de diferencia");
            // Vector<cDuo> vi3 = neg.listadelDiasector3();

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

            WritableFont Datos = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD, false);
            WritableCellFormat FormatoDatos = new WritableCellFormat(Datos);
            FormatoDatos.setBorder(Border.ALL, BorderLineStyle.THIN);
            FormatoDatos.setAlignment(Alignment.LEFT);
            FormatoDatos.setBackground(Colour.WHITE);

            HSSFFont fontSector = (HSSFFont) wb.createFont();
            fontSector.setFontHeightInPoints((short) 12);
            fontSector.setFontName("Verdana");
            fontSector.setColor((short) HSSFColor.WHITE.index);
            fontSector.setItalic(false);

            WritableFont Item = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.WHITE);
            WritableCellFormat FormatoItem = new WritableCellFormat(Item);
            FormatoItem.setBorder(Border.ALL, BorderLineStyle.NONE);
            FormatoItem.setAlignment(Alignment.CENTRE);
            FormatoItem.setBackground(Colour.LIGHT_BLUE);
            FormatoItem.setWrap(true);

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
            WritableFont DatosFecha = new WritableFont(WritableFont.TAHOMA, 8, WritableFont.NO_BOLD, false, UnderlineStyle.SINGLE, jxl.format.Colour.LIGHT_BLUE);

            WritableCellFormat FormatoDatosFechaGeneracion = new WritableCellFormat(DatosFecha);
            FormatoDatosFechaGeneracion.setAlignment(Alignment.LEFT);
            FormatoDatosFechaGeneracion.setBackground(Colour.WHITE);
            try {
                for (int k = 0; k < diasConsulta+1; ++k) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(fechaInicial);
                    c.add(Calendar.DATE, k);
                    Date FechaConsultando = c.getTime();
                    DateFormat dateFormatNew = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = dateFormatNew.format(FechaConsultando);

                    sheet = workbook.createSheet("" + strDate + "", 0);
                   

                    Label titulo = new Label(2, 0, "NOMINA PACIENTES HOSPITALIZADOS");
                    sheet.addCell(titulo);
                    sheet.mergeCells(2, 0, 14, 2);

                    Label etiqueta_num = new Label(0, 3, "Nª Cama", FormatoItem);
                    sheet.addCell(etiqueta_num);
                    sheet.setColumnView(0, 20);

                    Label num_dau = new Label(1, 3, "Rut Paciente", FormatoItem);
                    sheet.addCell(num_dau);
                    sheet.setColumnView(1, 18);

                    Label num_pagare = new Label(2, 3, "Apellido Paterno", FormatoItem);
                    sheet.addCell(num_pagare);
                    sheet.setColumnView(2, 35);

                    Label rut = new Label(3, 3, "Apellido Materno", FormatoItem);
                    sheet.addCell(rut);
                    sheet.setColumnView(3, 13);

                    Label comuna = new Label(4, 3, "Nombre", FormatoItem);
                    sheet.addCell(comuna);
                    sheet.setColumnView(4, 13);

                    Label fecha_transaccion = new Label(5, 3, "EDAD ", FormatoItem);
                    sheet.addCell(fecha_transaccion);
                    sheet.setColumnView(5, 8);

                    Label txt_cod_prestacion = new Label(6, 3, "Fecha Ingreso", FormatoItem);
                    sheet.addCell(txt_cod_prestacion);
                    sheet.setColumnView(6, 60);

                    Label txt_cod_prestacionrealizada = new Label(7, 3, "Hora Ingreso", FormatoItem);
                    sheet.addCell(txt_cod_prestacionrealizada);
                    sheet.setColumnView(7, 60);

                    Label nombre_paciente = new Label(8, 3, "Dias Hospitalizados", FormatoItem);
                    sheet.addCell(nombre_paciente);
                    sheet.setColumnView(8, 25);

                    Label apellido_p_paciente = new Label(9, 3, "Categorizacion", FormatoItem);
                    sheet.addCell(apellido_p_paciente);
                    sheet.setColumnView(9, 25);

                    Label num_protocolo = new Label(10, 3, "Riesgo Caida", FormatoItem);
                    sheet.addCell(num_protocolo);
                    sheet.setColumnView(10, 25);

                    /*hora de citacion y hora de recepcion*/
                    Label hra_cita = new Label(11, 3, "Riesgo UPP", FormatoItem);
                    sheet.addCell(hra_cita);
                    sheet.setColumnView(11, 25);

                    jxl.write.Number dato_numero;
                    Label dato_texto;

                    try {
                        int i = 0;
                        for (cDuo duo : neg.lisNominaporFecha(strDate)) {
                            String horas = "";
                            String dias = "";
                            String edad = "";
                            if (!duo.getRut_paciente().equalsIgnoreCase("")) {
                                horas = duo.getHora_duo();
                                dias = String.valueOf(duo.getDias_cama());
                                edad = duo.getEdad() + " Años";

                            }

                            dato_texto = new Label(0, i + 4, duo.getCama_descripcion().replace("CAMA", ""), FormatoDatos);
                            sheet.addCell(dato_texto);
                            dato_texto = new Label(1, i + 4, duo.getRut_paciente(), FormatoDatos);
                            sheet.addCell(dato_texto);
                            dato_texto = new Label(2, i + 4, neg.primeraMayuscula(duo.getApellidop_paciente()), FormatoDatos);
                            sheet.addCell(dato_texto);
                            dato_texto = new Label(3, i + 4, neg.primeraMayuscula(duo.getApellidom_paciente()), FormatoDatos);
                            sheet.addCell(dato_texto);

                            dato_texto = new Label(4, i + 4, neg.primeraMayuscula(duo.getNombres_paciente()), FormatoDatos);
                            sheet.addCell(dato_texto);

                            dato_texto = new Label(5, i + 4, edad, FormatoDatos);
                            sheet.addCell(dato_texto);

                            dato_texto = new Label(6, i + 4, duo.getFecha_creacion(), FormatoDatos);
                            sheet.addCell(dato_texto);

                            dato_texto = new Label(7, i + 4, horas, FormatoDatos);
                            sheet.addCell(dato_texto);

                            dato_texto = new Label(8, i + 4, dias, FormatoDatos);
                            sheet.addCell(dato_texto);
                            dato_texto = new Label(9, i + 4, duo.getCategorizacion_descripcion(), FormatoDatos);
                            sheet.addCell(dato_texto);
                            dato_texto = new Label(10, i + 4, duo.getRiesgo_caida(), FormatoDatos);
                            sheet.addCell(dato_texto);
                            /*hora cita y hora recepcion*/
                            dato_texto = new Label(11, i + 4, duo.getRiesgo_up(), FormatoDatos);
                            sheet.addCell(dato_texto);

                            /**/
                            i++;

                        }
                    } catch (Exception ex) {
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            workbook.write();
            workbook.close();

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
            try {
                processRequest(request, response);
            } catch (WriteException ex) {
                Logger.getLogger(nominaPorFecha.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(nominaPorFecha.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                processRequest(request, response);
            } catch (WriteException ex) {
                Logger.getLogger(nominaPorFecha.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(nominaPorFecha.class.getName()).log(Level.SEVERE, null, ex);
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
