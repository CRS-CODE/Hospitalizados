/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaInforme;

import CapaDato.cEnfermedad;
import CapaDato.cVisita;
import CapaDato.cVistaUoce;
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
 * @author EseGamboa
 */
public class XLS_DuosDadoAltaDatosEpicrisis extends HttpServlet {

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

        String fecha1_dma_sh = dia1 + "-" + mes1 + "-" + año1;
        String fecha2_dma_sh = dia2 + "-" + mes2 + "-" + año2;

        String resto_sql = "";
        ArrayList lista_duo = neg.lista_vista_duo("fecha_hora_alta_adm", fecha1_dma_sh, fecha2_dma_sh, resto_sql);
        Iterator it_lista = lista_duo.iterator();

        resto_sql = " and  fecha_hora_alta_adm>'" + fecha2_dma_sh + " 23:59:59' ";
        ArrayList lista_duo2 = neg.lista_vista_duo("fecha_epicrisis", fecha1_dma_sh, fecha2_dma_sh, resto_sql);
        Iterator it_lista2 = lista_duo2.iterator();
        resto_sql = "";// porsiacaso



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

            cell = row.createCell((short) 1);
            row.createCell((short) 1); // crea las celdas de acuerdo al numero de columnas
            cell.setCellValue("INFORME DE PACIENTES YA DADOS DE ALTA"); //vacia el nombre de las columnas en la hoja de excell
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
            cell.setCellValue("Id Ingreso");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("N°EPICRISIS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 2); //crea la celda tipo
            cell.setCellValue("RUT PACIENTE");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 3); //crea la celda tipo
            cell.setCellValue("NOMBRE COMPLETO PACIENTE");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 4); //crea la celda tipo
            cell.setCellValue("SEXO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 5); //crea la celda tipo
            cell.setCellValue("FECHA NACIMIENTO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 6); //crea la celda tipo
            cell.setCellValue("EDAD");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 7); //crea la celda tipo
            cell.setCellValue("PREVISION");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 8); //crea la celda tipo
            cell.setCellValue("TRAMO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 9); //crea la celda tipo
            cell.setCellValue("ORIGEN");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 10); //crea la celda tipo
            cell.setCellValue("DESTINO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 11); //crea la celda tipo
            cell.setCellValue("CONSULTORIO PERTENENCIA");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 12); //crea la celda tipo
            cell.setCellValue("FECHA/HORA INGRESO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 13); //crea la celda tipo
            cell.setCellValue("FECHA/HORA EPICRISIS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 14); //crea la celda tipo
            cell.setCellValue("FECHA/HORA ALTA ADM.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 15); //crea la celda tipo
            cell.setCellValue("Q DIAS  / EPI");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 16); //crea la celda tipo
            cell.setCellValue("Q DIAS  / ALTA ADM");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 17); //crea la celda tipo
            cell.setCellValue("TOTAL DIAS");
            cell.setCellStyle(style_Destacado);


            cell = row.createCell((short) 18); //crea la celda tipo
            cell.setCellValue("FECHA/HORA ING. ENF.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 19); //crea la celda tipo
            cell.setCellValue("RESUMEN");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 20); //crea la celda tipo
            cell.setCellValue("DIAGNOSTICOS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 21); //crea la celda tipo
            cell.setCellValue("EXAMENES");
            cell.setCellStyle(style_Destacado);


            cell = row.createCell((short) 22); //crea la celda tipo
            cell.setCellValue("INDICACIONES");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 23); //crea la celda tipo
            cell.setCellValue("ENF. CRONICAS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 24); //crea la celda tipo
            cell.setCellValue("CATEGORIZACIONES");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 25); //crea la celda tipo
            cell.setCellValue("MED. EPICRISIS");
            cell.setCellStyle(style_Destacado);

            cell = row.createCell((short) 26); //crea la celda tipo
            cell.setCellValue("NOMBRE MED.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 27); //crea la celda tipo
            cell.setCellValue("APELL. PATERNO MED.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 28); //crea la celda tipo
            cell.setCellValue("APELL. MATERNO MED.");
            cell.setCellStyle(style_Destacado);

            while (it_lista.hasNext()) {
                cVistaUoce vis = (cVistaUoce) it_lista.next();
                control_fila++;

                String enfCronica = "";
                String catRD = "";
                ArrayList lista_cronicas = neg.obtiene_duo_enfermedad(vis.getId_duo());
                ArrayList lista_categorizaciones = neg.lista_historial_visita_enfermeria(vis.getId_duo());

                 Iterator it_cro = lista_cronicas.iterator();
                Iterator it_cat = lista_categorizaciones.iterator();

                while (it_cro.hasNext()) {
                    cEnfermedad cro = (cEnfermedad) it_cro.next();
                    enfCronica += cro.getDescripcion() + " || ";
                }

                while (it_cat.hasNext()) {
                    cVisita cat = (cVisita) it_cat.next();
                    catRD += cat.getCat_visita_categorizacion() + " || ";
                }


                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(vis.getId_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(vis.getId_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_rut());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_nombres() + " " + vis.getPaciente_apellidop() + " " + vis.getPaciente_apellidom());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_sexo());
                cell.setCellStyle(style_Normal);

                cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_fecha_nac());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_edad());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(vis.getCodigo_fonasa_descripcion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(vis.getTramo_prevision_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(vis.getDescripcion_derivador());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 10); //crea la celda tipo
                cell.setCellValue(vis.getDescripcion_destino());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 11); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_consultorio_pertenencia());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 12); //crea la celda tipo
                cell.setCellValue(vis.getFecha_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 13); //crea la celda tipo
                cell.setCellValue(vis.getFecha_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 14); //crea la celda tipo
                cell.setCellValue(vis.getFecha_hora_alta_adm());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 15); //crea la celda tipo
                cell.setCellValue(vis.getQdias_epi_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 16); //crea la celda tipo
                cell.setCellValue(vis.getQdias_altaadm_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 17); //crea la celda tipo
                cell.setCellValue(vis.getFecha_dias());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 18); //crea la celda tipo
                cell.setCellValue(vis.getFecha_ingreso_enfermeria());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 19); //crea la celda tipo
                cell.setCellValue(vis.getResumen_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 20); //crea la celda tipo
                cell.setCellValue(vis.getDiagnostico_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 21); //crea la celda tipo
                cell.setCellValue(vis.getExamen_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 22); //crea la celda tipo
                cell.setCellValue(vis.getIndicacion_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 23); //crea la celda tipo
                cell.setCellValue(enfCronica);
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 24); //crea la celda tipo
                cell.setCellValue(catRD);
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 25); //crea la celda tipo
                cell.setCellValue(vis.getRut_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 26); //crea la celda tipo
                cell.setCellValue(vis.getNombre_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 27); //crea la celda tipo
                cell.setCellValue(vis.getApellidop_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 28); //crea la celda tipo
                cell.setCellValue(vis.getApellidom_usuario());
                cell.setCellStyle(style_Normal);
            }

            /****************************************FIN HOJA DATOS ****************************************/
            /**************************************** TABLA 2***************************************/
            control_fila++;
            control_fila++;
            control_fila++;
            control_fila++;

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas
            // cell0.setCellValue("N°"); //vacia el nombre de las columnas en la hoja de excell

            cell = row.createCell((short) 1);
            row.createCell((short) 1); // crea las celdas de acuerdo al numero de columnas
            cell.setCellValue("INFORME DE DUO's YA DADOS DE ALTA [ALT. MEDICA POSTERIOR A LA EPICRISIS SEGUN RANGO BUSQUEDA]"); //vacia el nombre de las columnas en la hoja de excell
            cell.setCellStyle(style_Destacado); //Asigna el estilo del nombre de las columnas

            sheet0.addMergedRegion(new CellRangeAddress(
                    control_fila, //first row (0-based)
                    control_fila, //last row  (0-based)
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
            control_fila++;
            control_fila++;
            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 5); //crea la celda tipo
            cell = row.createCell((short) 0);
            row.createCell((short) 0); // crea las celdas de acuerdo al numero de columnas

            row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
            cell = row.createCell((short) 0); //crea la celda tipo
            cell.setCellValue("N°DUO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 1); //crea la celda tipo
            cell.setCellValue("N°EPICRISIS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 2); //crea la celda tipo
            cell.setCellValue("RUT PACIENTE");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 3); //crea la celda tipo
            cell.setCellValue("NOMBRE COMPLETO PACIENTE");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 4); //crea la celda tipo
            cell.setCellValue("SEXO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 5); //crea la celda tipo
            cell.setCellValue("FECHA NACIMIENTO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 6); //crea la celda tipo
            cell.setCellValue("EDAD");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 7); //crea la celda tipo
            cell.setCellValue("PREVISION");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 8); //crea la celda tipo
            cell.setCellValue("TRAMO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 9); //crea la celda tipo
            cell.setCellValue("ORIGEN");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 10); //crea la celda tipo
            cell.setCellValue("DESTINO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 11); //crea la celda tipo
            cell.setCellValue("CONSULTORIO PERTENENCIA");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 12); //crea la celda tipo
            cell.setCellValue("FECHA/HORA DUO");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 13); //crea la celda tipo
            cell.setCellValue("FECHA/HORA EPICRISIS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 14); //crea la celda tipo
            cell.setCellValue("FECHA/HORA ALTA ADM.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 15); //crea la celda tipo
            cell.setCellValue("Q DIAS DUO / EPI");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 16); //crea la celda tipo
            cell.setCellValue("Q DIAS DUO / ALTA ADM");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 17); //crea la celda tipo
            cell.setCellValue("TOTAL DIAS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 18); //crea la celda tipo
            cell.setCellValue("FECHA/HORA ING. ENF.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 19); //crea la celda tipo
            cell.setCellValue("RESUMEN");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 20); //crea la celda tipo
            cell.setCellValue("DIAGNOSTICOS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 21); //crea la celda tipo
            cell.setCellValue("EXAMENES");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 22); //crea la celda tipo
            cell.setCellValue("INDICACIONES");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 23); //crea la celda tipo
            cell.setCellValue("ENF. CRONICAS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 24); //crea la celda tipo
            cell.setCellValue("CATEGORIZACIONES");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 25); //crea la celda tipo
            cell.setCellValue("MED. EPICRISIS");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 26); //crea la celda tipo
            cell.setCellValue("NOMBRE MED.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 27); //crea la celda tipo
            cell.setCellValue("APELL. PATERNO MED.");
            cell.setCellStyle(style_Destacado);
            cell = row.createCell((short) 28); //crea la celda tipo
            cell.setCellValue("APELL. MATERNO MED.");
            cell.setCellStyle(style_Destacado);

            /**/
            while (it_lista2.hasNext()) {
                cVistaUoce vis = (cVistaUoce) it_lista2.next();
                control_fila++;

                String enfCronica = "";
                String catRD = "";
                ArrayList lista_cronicas = neg.obtiene_duo_enfermedad(vis.getId_duo());
                ArrayList lista_categorizaciones = neg.lista_historial_visita_enfermeria(vis.getId_duo());

                Iterator it_cro = lista_cronicas.iterator();
                Iterator it_cat = lista_categorizaciones.iterator();

                while (it_cro.hasNext()) {
                    cEnfermedad cro = (cEnfermedad) it_cro.next();
                    enfCronica += cro.getDescripcion() + " || ";
                }
                
                while (it_cat.hasNext()) {
                    cVisita cat = (cVisita) it_cat.next();
                    catRD += cat.getCat_visita_categorizacion() + " || ";
                }



                row = (HSSFRow) sheet0.createRow((short) control_fila); //crea el renglon
                cell = row.createCell((short) 0); //crea la celda tipo
                cell.setCellValue(vis.getId_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 1); //crea la celda tipo
                cell.setCellValue(vis.getId_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 2); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_rut());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 3); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_nombres() + " " + vis.getPaciente_apellidop() + " " + vis.getPaciente_apellidom());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 4); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_sexo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 5); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_fecha_nac());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 6); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_edad());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 7); //crea la celda tipo
                cell.setCellValue(vis.getCodigo_fonasa_descripcion());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 8); //crea la celda tipo
                cell.setCellValue(vis.getTramo_prevision_paciente());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 9); //crea la celda tipo
                cell.setCellValue(vis.getDescripcion_derivador());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 10); //crea la celda tipo
                cell.setCellValue(vis.getDescripcion_destino());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 11); //crea la celda tipo
                cell.setCellValue(vis.getPaciente_consultorio_pertenencia());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 12); //crea la celda tipo
                cell.setCellValue(vis.getFecha_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 13); //crea la celda tipo
                cell.setCellValue(vis.getFecha_hora_alta_med()); // cambio de fecha epicrisis a alta
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 14); //crea la celda tipo
                cell.setCellValue(vis.getFecha_hora_alta_adm());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 15); //crea la celda tipo
                cell.setCellValue(vis.getQdias_epi_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 16); //crea la celda tipo
                cell.setCellValue(vis.getQdias_altaadm_duo());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 17); //crea la celda tipo
                cell.setCellValue(vis.getFecha_dias());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 18); //crea la celda tipo
                cell.setCellValue(vis.getFecha_ingreso_enfermeria());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 19); //crea la celda tipo
                cell.setCellValue(vis.getResumen_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 20); //crea la celda tipo
                cell.setCellValue(vis.getDiagnostico_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 21); //crea la celda tipo
                cell.setCellValue(vis.getExamen_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 22); //crea la celda tipo
                cell.setCellValue(vis.getIndicacion_epicrisis());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 23); //crea la celda tipo
                cell.setCellValue(enfCronica);
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 24); //crea la celda tipo
                cell.setCellValue(catRD);
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 25); //crea la celda tipo
                cell.setCellValue(vis.getRut_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 26); //crea la celda tipo
                cell.setCellValue(vis.getNombre_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 27); //crea la celda tipo
                cell.setCellValue(vis.getApellidop_usuario());
                cell.setCellStyle(style_Normal);
                cell = row.createCell((short) 28); //crea la celda tipo
                cell.setCellValue(vis.getApellidom_usuario());
                cell.setCellStyle(style_Normal);
            }
            /**/

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
