/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cCategorizacion;
import CapaDato.cVisita;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Kairin
 */
public class InfCatxls extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        GregorianCalendar f1 = new GregorianCalendar();
        f1.set(Integer.parseInt(ano), Integer.parseInt(mes), 1);

        int numero_dias_max = f1.getActualMaximum(f1.DAY_OF_MONTH);
        GregorianCalendar f2 = new GregorianCalendar();
        f2.set(Integer.parseInt(ano), Integer.parseInt(mes), numero_dias_max);

        Calendar gc = new GregorianCalendar();
        gc.set(Calendar.MONTH, Integer.parseInt(mes));
        gc.set(Calendar.YEAR, Integer.parseInt(ano));
        gc.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = gc.getTime();
        gc.add(Calendar.MONTH, 1);
        gc.add(Calendar.DAY_OF_MONTH, -1);
        Date monthEnd = gc.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        NegocioQ neg = new NegocioQ();

        Date hoy = new Date();
        Locale currentLocale = new Locale("es", "CL");
        java.text.DateFormat formateadorFecha = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, currentLocale);
        Locale currentLocale2 = new Locale("es", "Ch");
        java.text.DateFormat formateadorHora = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, currentLocale2);
        SimpleDateFormat formatMes = new SimpleDateFormat("MMMM/yyyy", new java.util.Locale("es", "CL"));
        SimpleDateFormat formatSearch = new SimpleDateFormat("MM/dd/yyyy", new java.util.Locale("es", "CL"));
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=InfCategorización.xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            HSSFSheet sheet1 = wb.createSheet("Informe");

            HSSFDataFormat tipo_dato = wb.createDataFormat();//creo un nuevo formato  
            HSSFCellStyle style_dato_fecha = wb.createCellStyle();
            style_dato_fecha.setDataFormat(tipo_dato.getFormat("dd-mm-yyyy"));
            style_dato_fecha.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_dato_fecha.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_dato_fecha.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_dato_fecha.setBorderTop(HSSFCellStyle.BORDER_THIN);
            /**
             * **********************************************
             */
            HSSFCellStyle style_datos = wb.createCellStyle();
            style_datos.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_datos.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_datos.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_datos.setBorderTop(HSSFCellStyle.BORDER_THIN);
            /**
             * **********************************************
             */
            HSSFFont fontTitulo = wb.createFont();
            fontTitulo.setFontHeightInPoints((short) 15);
            fontTitulo.setFontName("Verdana");
            fontTitulo.setColor((short) HSSFColor.BLUE_GREY.index);
            fontTitulo.setItalic(true);
            //fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Titulo = wb.createCellStyle();
            style_Titulo.setFont(fontTitulo);
            style_Titulo.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
            //style_Titulo.setWrapText(true);
            /**
             * **********************************************
             */
            HSSFFont fontSubtitulo = wb.createFont();
            fontSubtitulo.setFontHeightInPoints((short) 10);
            fontSubtitulo.setFontName("tahoma");
            fontSubtitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Subtitulo = wb.createCellStyle();
            style_Subtitulo.setFont(fontSubtitulo);
            style_Subtitulo.setAlignment((short) 50);
            style_Subtitulo.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style_Subtitulo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style_Subtitulo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style_Subtitulo.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

            HSSFCellStyle style_Subtitulo_porcentaje = wb.createCellStyle();
            style_Subtitulo_porcentaje.setFont(fontSubtitulo);
            style_Subtitulo_porcentaje.setAlignment((short) 50);
            style_Subtitulo_porcentaje.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style_Subtitulo_porcentaje.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style_Subtitulo_porcentaje.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style_Subtitulo_porcentaje.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

            HSSFDataFormat tipo_dato_porcentaje = wb.createDataFormat();//creo un nuevo formato
            style_Subtitulo_porcentaje.setDataFormat(tipo_dato_porcentaje.getFormat("0.00%"));
            /**
             * **********************************************
             */
            HSSFFont fontDestacado = wb.createFont();
            fontDestacado.setFontHeightInPoints((short) 10);
            fontDestacado.setFontName("tahoma");
            fontDestacado.setColor((short) HSSFColor.BLACK.index);
            fontDestacado.setItalic(false);
            fontDestacado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style_Destacado = wb.createCellStyle();
            style_Destacado.setFont(fontDestacado);
            style_Destacado.setAlignment((short) 50);
            style_Destacado.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            style_Destacado.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle style_Destacado_osc = wb.createCellStyle();
            style_Destacado_osc.setFont(fontDestacado);
            style_Destacado_osc.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
            style_Destacado_osc.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            /**
             * **********************************************
             */
            HSSFFont font = wb.createFont();//Este font y style son los de la cabecera
            font.setFontHeightInPoints((short) 10);
            font.setFontName("tahoma");
            font.setColor((short) HSSFColor.WHITE.index);
            font.setItalic(true);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);
            //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
            style.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            /**
             * ***************************************************************************************************************************
             */
            //Esta es el Header y el Footer
            HSSFHeader cabecera = sheet1.getHeader();
            cabecera.setLeft("Ministerio de Salud\nCentro de referencia de Salud Maipú");
            HSSFFooter pie = sheet1.getFooter();
            pie.setCenter("Pagina " + HSSFFooter.page() + " de " + HSSFFooter.numPages());//N° de Pagina
            //*********************************************************************************************       
            /**
             * ***********************[ESTE ES TODO EL ENCABEZADO]**************************************
             */
            HSSFRow rowTitulo = sheet1.createRow((short) 1); //Esta es la primera fila de la hoja
            HSSFRow rowHoy = sheet1.createRow((short) 2); //esta el la segunda

            HSSFCell cellTitulo = rowTitulo.createCell((short) 0); //TITULO
            cellTitulo.setCellValue("Formulario 2");
            cellTitulo.setCellStyle(style_Destacado_osc);//le doy el estilo

            cellTitulo = rowTitulo.createCell((short) 1); //TITULO
            cellTitulo.setCellValue("CATEGORIZACIÓN DE PACIENTES EN SALA DE HOSPITALIZACIÓN " + formatMes.format(f1.getTime()));
            cellTitulo.setCellStyle(style_Destacado);//le doy el estilo

            Region regionT = new Region((short) 1, (short) 1, (short) 1, (short) 13);
            sheet1.addMergedRegion(regionT); //es para combinar celdas
            /**
             * ***********************[INCIO LA CREACION DE LOS REGISTROS]******************
             */
            HSSFRow row3 = sheet1.createRow((short) 3); //crea el renglon o fila

            HSSFCell cell0 = row3.createCell((short) 0); //crea la celda tipo
            cell0.setCellValue("Establecimiento:");
            cell0.setCellStyle(style_datos);//le doy el estilo

            cell0 = row3.createCell((short) 1); //crea la celda tipo
            cell0.setCellValue("Centro de Referencia de Salud Maipú");

            Region regionT2 = new Region((short) 3, (short) 1, (short) 3, (short) 4);
            sheet1.addMergedRegion(regionT2); //es para combinar celdas

            HSSFRow row4 = sheet1.createRow((short) 4); //crea el renglon o fila

            HSSFCell cell1 = row4.createCell((short) 0); //crea la celda tipo
            cell1.setCellValue("Area de Trabajo");
            cell1.setCellStyle(style_datos);//le doy el estilo

            cell1 = row4.createCell((short) 1); //crea la celda tipo
            cell1.setCellValue("---");

            Region regionT3 = new Region((short) 4, (short) 1, (short) 4, (short) 4);
            sheet1.addMergedRegion(regionT3); //es para combinar celdas

            cell1 = row4.createCell((short) 7); //crea la celda tipo
            cell1.setCellValue("Emitido el:");
            cell1.setCellStyle(style_datos);//le doy el estilo

            cell1 = row4.createCell((short) 8); //crea la celda tipo
            cell1.setCellValue(formateadorFecha.format(hoy));

            /**
             * ********************************************************************************************************************
             */
            HSSFRow row5 = sheet1.createRow((short) 5); //crea el renglon o fila

            HSSFCell cell2 = row5.createCell((short) 0); //crea la celda tipo
            cell2.setCellValue("Subárea");
            cell2.setCellStyle(style_datos);//le doy el estilo

            cell2 = row5.createCell((short) 1); //crea la celda tipo
            cell2.setCellValue("---");

            Region regionT4 = new Region((short) 5, (short) 1, (short) 5, (short) 4);
            sheet1.addMergedRegion(regionT4); //es para combinar celdas
            /**
             * ********************************************************************************************************************
             */
            HSSFRow row7 = sheet1.createRow((short) 7); //crea el renglon o fila

            HSSFCell cell3 = row7.createCell((short) 0); //crea la celda tipo
            cell3.setCellValue("DESAGREGACIÓN DE LA ACTIVIDAD POR TIPO DE PACIENTE");
            cell3.setCellStyle(style_Subtitulo);//le doy el estilo
            for (int i = 1; i < 14; i++) {
                cell3 = row7.createCell((short) i);
                cell3.setCellStyle(style_Subtitulo);//le doy el estilo
            }

            Region regionT5 = new Region((short) 7, (short) 0, (short) 7, (short) 13);
            sheet1.addMergedRegion(regionT5); //es para combinar celdas

            /**
             * ********************************************************************************************************************
             */
            int col = 0;
            int fila = 8;
            String[] arrayDescripcion_categorizacion = new String[12];
            HSSFRow rowCabecera = sheet1.createRow((short) fila); //crea el renglon o fila
            HSSFCell cellCabecera = rowCabecera.createCell((short) col); //crea la celda tipo
            cellCabecera.setCellValue("Día");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            int pos = 0;
            Vector<cCategorizacion> list = neg.searchCategory();
            for (cCategorizacion category : list) {
                col++;
                cellCabecera = rowCabecera.createCell((short) col); //crea la celda tipo
                cellCabecera.setCellValue(category.getDescripcion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                arrayDescripcion_categorizacion[pos] = category.getDescripcion();

                pos++;

            }
            col++;
            cellCabecera = rowCabecera.createCell((short) col); //crea la celda tipo
            cellCabecera.setCellValue("TOTAL");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo

            try {

                fila++;
                HSSFRow rowRegistro = sheet1.createRow((short) fila); //crea el renglon o fila
                int columnaReg = 1;
                for (int i = 0; i < numero_dias_max; i++) {
                    GregorianCalendar fecha_Visita = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes), (i + 1));
                    rowRegistro = sheet1.createRow((short) (fila)); //crea el renglon o fila
                    HSSFCell cell4 = rowRegistro.createCell((short) 0); //crea la celda tipo
                    cell4.setCellValue(formateadorFecha.format(fecha_Visita.getTime()));
                    cell4.setCellStyle(style_Subtitulo);//le doy el estilo

                    for (int x = 0; x < arrayDescripcion_categorizacion.length; x++) {
                        int quantity = neg.countCategory(fecha_Visita.getTime(), arrayDescripcion_categorizacion[x]);

                        cell4 = rowRegistro.createCell((short) columnaReg);
                        cell4.setCellValue(quantity);
                        cell4.setCellStyle(style_datos);
                        columnaReg++;

                    }
                    cell4 = rowRegistro.createCell((short) columnaReg);
                    cell4.setCellFormula("sum(B" + (fila + 1) + ":M" + (fila + 1) + ")");
                    cell4.setCellStyle(style_Subtitulo);

                    columnaReg = 1;
                    fila++;

                }

                HSSFRow rowTotal = sheet1.createRow((short) fila); //crea el renglon o fila
                HSSFCell cell5 = rowTotal.createCell((short) 0); //crea la celda tipo
                cell5.setCellValue("TOTAL MENSUAL");
                cell5.setCellStyle(style_Subtitulo);//le doy el estilo
                String[] arrayABC = new String[13];
                for (int i = 0; i < arrayABC.length; i++) {
                    if (i == 0) {
                        arrayABC[i] = new String("B");
                    }
                    if (i == 1) {
                        arrayABC[i] = new String("C");
                    }
                    if (i == 2) {
                        arrayABC[i] = new String("D");
                    }
                    if (i == 3) {
                        arrayABC[i] = new String("E");
                    }
                    if (i == 4) {
                        arrayABC[i] = new String("F");
                    }
                    if (i == 5) {
                        arrayABC[i] = new String("G");
                    }
                    if (i == 6) {
                        arrayABC[i] = new String("H");
                    }
                    if (i == 7) {
                        arrayABC[i] = new String("I");
                    }
                    if (i == 8) {
                        arrayABC[i] = new String("J");
                    }
                    if (i == 9) {
                        arrayABC[i] = new String("K");
                    }
                    if (i == 10) {
                        arrayABC[i] = new String("L");
                    }
                    if (i == 11) {
                        arrayABC[i] = new String("M");
                    }
                    if (i == 12) {
                        arrayABC[i] = new String("N");
                    }

                }
                for (int i = 0; i < 13; i++) {
                    cell5 = rowTotal.createCell((short) (i + 1)); //crea la celda tipo
                    cell5.setCellFormula("sum(" + arrayABC[i] + "10:" + arrayABC[i] + "" + (numero_dias_max + 9) + ")");
                    cell5.setCellStyle(style_Subtitulo);//le doy el estilo
                }

                fila++;
                //*****************PORCENTAJE HORIZONTAL****************************
                HSSFRow rowPorcentaje = sheet1.createRow((short) fila); //crea el renglon o fila
                HSSFCell cell6 = rowPorcentaje.createCell((short) 0); //crea la celda tipo
                cell6.setCellValue("Porcentaje");
                cell6.setCellStyle(style_Subtitulo);//le doy el estilo

                for (int i = 0; i < 13; i++) {
                    cell6 = rowPorcentaje.createCell((short) (i + 1)); //crea la celda tipo
                    cell6.setCellFormula(arrayABC[i] + "" + (numero_dias_max + 10) + "/" + "N" + (numero_dias_max + 10));
                    cell6.setCellStyle(style_Subtitulo_porcentaje);//le doy el estilo
                }

            } catch (Exception e) {
            }

            sheet1.setColumnWidth((short) 0, (short) 4300);
            for (int i = 1; i < 50; i++) {
                sheet1.setColumnWidth((short) i, (short) 3000);
            }

            /*create new hoja*/
            HSSFSheet sheet = wb.createSheet("Descripcion Informe");
            //Esta es el Header y el Footer
            HSSFHeader cabeceraDescription = sheet.getHeader();
            cabeceraDescription.setLeft("Ministerio de Salud\nCentro de referencia de Salud Maipú");
            HSSFFooter pieDescription = sheet.getFooter();
            pie.setCenter("Pagina " + HSSFFooter.page() + " de " + HSSFFooter.numPages());//N° de Pagina
            //*********************************************************************************************       
            /**
             * ***********************[ESTE ES TODO EL ENCABEZADO]**************************************
             */
            HSSFRow rowTituloDescription = sheet.createRow((short) 1); //Esta es la primera fila de la hoja

            HSSFCell cellTituloA = rowTituloDescription.createCell((short) 0); //TITULO
            cellTituloA = rowTituloDescription.createCell((short) 1); //TITULO
            cellTituloA.setCellValue("DETALLE CATEGORIZACIÓN DE PACIENTES EN SALA DE HOSPITALIZACIÓN " + formatMes.format(f1.getTime()));
            cellTituloA.setCellStyle(style_Subtitulo);//le doy el estilo

            Region regionA = new Region((short) 7, (short) 0, (short) 7, (short) 13);
            sheet1.addMergedRegion(regionA); //es para combinar celdas

            int colC = 0;
            int filaC = 8;

            rowCabecera = sheet.createRow((short) filaC); //crea el renglon o fila
            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Fecha de categorización");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Hora de categorización");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Usuario que realiza el registro");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Usuario que realiza el registro");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Datos del Paciente Categorizado");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Datos del Paciente Categorizado");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Categorizacion Asignada ");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Cuidados en Confort y Bienestar”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Movilización y Transporte”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Cuidados de Alimentación”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Cuidados de Eliminación”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Apoyo psicosocial y emocional”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Vigilancia”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Medición de Signos Vitales”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Balance hídrico”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Cuidados Oxígeno Terapia”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Cuidados diarios de la Vía Aérea”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Intervenciones Profesionales”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Cuidados de la Piel y Curaciones”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Administración de Tratamiento Farmacológico”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
            cellCabecera.setCellValue("Puntaje asignado para “Presencia de Elementos Invasivos”");
            cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
            ++colC;

            for (cVisita visita : neg.searchInfoCategory(monthStart, monthEnd)) {
                filaC++;
                colC = 0;
                rowCabecera = sheet.createRow((short) filaC); //crea el renglon o fila
                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getFecha_visita());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getHora_visita());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getRut_usuario());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getNombre_usuario());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getRut_paciente());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getNombre_paciente());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getCat_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getD1_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getD2_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getD3_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getD4_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getD5_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getD6_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR1_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR2_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR3_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR4_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR5_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR6_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR7_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

                cellCabecera = rowCabecera.createCell((short) colC); //crea la celda tipo
                cellCabecera.setCellValue(visita.getR8_visita_categorizacion());
                cellCabecera.setCellStyle(style_Subtitulo);//le doy el estilo
                ++colC;

            }

            sheet.setColumnWidth((short) 0, (short) 8300);
            for (int i = 1; i < 50; i++) {
                sheet.setColumnWidth((short) i, (short) 8300);
            }

// Cerramos el archivo de excell    
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
