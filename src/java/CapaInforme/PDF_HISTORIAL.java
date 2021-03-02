//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cVisita;
import CapaNegocio.NegocioQ;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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

public class PDF_HISTORIAL extends HttpServlet {
    public PDF_HISTORIAL() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("charset=UTF-8", " attachment; filename=\"Informe.pdf\"");
        Document document = new Document();
        NegocioQ neg = new NegocioQ();
        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
        ArrayList lista_sesion = neg.lista_historial_paciente(id_duo);
        cDuo duo = neg.obtiene_duo(id_duo);
        Locale currentLocale2 = new Locale("es", "Ch");
        DateFormat formateadorHora = DateFormat.getTimeInstance(2, currentLocale2);
        NumberFormat formateadorNumero = NumberFormat.getNumberInstance(currentLocale2);
        Locale currentLocale = new Locale("es", "CL");
        DateFormat formateadorFecha = DateFormat.getDateInstance(2, currentLocale);
        DateFormat formateadorFechaLarga = DateFormat.getDateInstance(0, currentLocale);
        Date hoy = new Date();
        String actual = formateadorFechaLarga.format(hoy) + ", " + formateadorHora.format(hoy.getTime());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hoy);
        calendar.add(5, 1);
        Date fecha_final = calendar.getTime();
        formateadorFecha.format(fecha_final);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            String tipo_informe = "Unidad de Hospitalización de Media Estadia";
            ServletContext context = this.getServletConfig().getServletContext();
            String path1 = context.getRealPath("/Imagenes");
            Image img = Image.getInstance(path1 + "/Logo_Gob.jpg");
            img.scalePercent(25.0F);
            img.setAbsolutePosition(60.0F, PageSize.A4.getHeight() - img.getScaledHeight() - 40.0F);
            document.add(img);
            PDF_HISTORIAL.FooterPiePaginaiText_visita_medica footer = new PDF_HISTORIAL.FooterPiePaginaiText_visita_medica();
            writer.setPageEvent(footer);
            document.add(new Paragraph("\n\n\n\n"));
            float[] colsWidth2 = new float[]{0.75F, 1.05F, 0.8F, 1.4F};
            PdfPTable tabla1 = new PdfPTable(colsWidth2);
            tabla1.setWidthPercentage(90.0F);
            Font ft1 = FontFactory.getFont("Verdana", 9.0F, 2, BaseColor.BLACK);
            Font ft1v2 = FontFactory.getFont("Verdana", 11.0F, 2, BaseColor.BLACK);
            Font ft11 = FontFactory.getFont("Verdana", 11.0F, 3, BaseColor.BLUE);
            Font ft4 = FontFactory.getFont("Verdana", 10.0F, 2, BaseColor.BLACK);
            Font ft2 = FontFactory.getFont("Verdana", 13.0F, 2, BaseColor.BLACK);
            Font ft3 = FontFactory.getFont("Verdana", 9.0F, 2, BaseColor.WHITE);
            Font ft5 = FontFactory.getFont("Verdana", 8.0F, 2, BaseColor.BLACK);
            PdfPCell celda = new PdfPCell(new Paragraph(tipo_informe, ft2));
            celda.setBorder(0);
            celda.setColspan(4);
            celda.setHorizontalAlignment(1);
            celda.setPadding(12.0F);
            celda.setBackgroundColor(BaseColor.WHITE);
            tabla1.addCell(celda);
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla1.addCell(celda);
            document.add(tabla1);
            BaseColor myColor = WebColors.getRGBColor("#6699FF");
            float[] colsWidth = new float[]{1.15F, 1.0F, 0.8F, 0.9F};
            PdfPTable tabla2 = new PdfPTable(colsWidth);
            tabla2.setWidthPercentage(95.0F);
            celda = new PdfPCell(new Paragraph("Paciente", ft3));
            celda.setBackgroundColor(myColor);
            celda.setColspan(2);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Rut", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Sexo", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente(), ft1));
            celda.setColspan(2);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getRut_paciente(), ft1));
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getSexo_descri(), ft1));
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Edad", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Derivado", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Fecha Ingreso", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("Hora Ingreso", ft3));
            celda.setBackgroundColor(myColor);
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph(duo.getEdad(), ft1));
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph(duo.getDerivador_descripcion(), ft1));
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getFecha_duo(), ft1));
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph("" + duo.getHora_duo(), ft1));
            tabla2.addCell(celda);
            celda = new PdfPCell(new Paragraph(""));
            celda.setBorder(0);
            celda.setColspan(4);
            tabla2.addCell(celda);
            document.add(tabla2);
            float[] colsWidth3 = new float[]{0.7F, 3.5F, 0.7F};
            PdfPTable tabla3 = new PdfPTable(colsWidth3);
            tabla3.setWidthPercentage(95.0F);
            String guarda_fecha = "";
            Iterator it_ses = lista_sesion.iterator();
            if (lista_sesion.isEmpty()) {
                celda = new PdfPCell(new Paragraph("No se encontraron registros", ft1));
                celda.setColspan(3);
                tabla3.addCell(celda);
            } else {
                while(it_ses.hasNext()) {
                    cVisita vis = (cVisita)it_ses.next();
                    if (!vis.getFecha_visita().equals(guarda_fecha)) {
                        celda = new PdfPCell(new Paragraph("Día " + vis.getFecha_visita(), ft1));
                        celda.setColspan(3);
                        celda.setBackgroundColor(myColor);
                        tabla3.addCell(celda);
                    }

                    celda = new PdfPCell(new Paragraph("" + vis.getHora_visita() + "\n " + vis.getDia_visita(), ft5));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + vis.getDescripcion_cama(), ft4));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                    celda = new PdfPCell(new Paragraph("" + vis.getApellidop_usuario(), ft5));
                    celda.setColspan(1);
                    tabla3.addCell(celda);
                    guarda_fecha = vis.getFecha_visita();
                }
            }

            document.add(tabla3);
            float[] colsWidth10 = new float[]{4.0F};
            PdfPTable tabla10 = new PdfPTable(colsWidth10);
            tabla10.setWidthPercentage(95.0F);
            celda = new PdfPCell(new Paragraph(" \n \n \n \n                                         \n                                       UNIDAD DE MEDIA ESTADIA", ft2));
            tabla10.addCell(celda);
            celda = new PdfPCell(new Paragraph("Fecha de Impresión :" + actual, ft1));
            tabla10.addCell(celda);
            document.add(tabla10);
            document.close();
        } catch (DocumentException var44) {
            Logger.getLogger(PDF_visita_medica.class.getName()).log(Level.SEVERE, (String)null, var44);
        } catch (Exception var45) {
            Logger.getLogger(PDF_visita_medica.class.getName()).log(Level.SEVERE, (String)null, "Ocurrio un Error" + var45);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

    public class FooterPiePaginaiText_visita_medica extends PdfPageEventHelper {
        public FooterPiePaginaiText_visita_medica() {
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
        }

        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(), 1, new Phrase("Centro de Referencia de Salud de Maipú"), 300.0F, 20.0F, 0.0F);
        }
    }
}
