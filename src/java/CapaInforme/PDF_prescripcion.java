package CapaInforme;

import CapaDato.cDuo;
import CapaDato.cInfusion;
import CapaDato.cInfusionDetalle;
import CapaDato.cPrescripcion;
import CapaNegocio.NegocioQ;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PDF_prescripcion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session1 = request.getSession();
        if (session1.getAttribute("usuario_rut") == null) {
            response.sendRedirect("index.jsp?timeout=1");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"Prescripcion_Medicamentos.pdf\"");

        NegocioQ neg = new NegocioQ();
        int id_duo = 0;
        try { id_duo = Integer.parseInt(request.getParameter("id_duo")); } catch (Exception e) {}

        cDuo duo = neg.obtiene_duo(id_duo);
        ArrayList lista_pm  = neg.lista_prescripciones_por_duo(id_duo);
        ArrayList lista_inf = neg.lista_infusiones_por_duo(id_duo);

        Locale locale = new Locale("es", "CL");
        DateFormat fFull  = DateFormat.getDateInstance(DateFormat.FULL, locale);
        DateFormat fShort = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String fechaHoy = fFull.format(new Date());

        // ---- Fuentes ----
        Font fTitulo  = FontFactory.getFont("Helvetica", 12, Font.BOLD,   BaseColor.BLACK);
        Font fSeccion = FontFactory.getFont("Helvetica", 9,  Font.BOLD,   BaseColor.WHITE);
        Font fLabel   = FontFactory.getFont("Helvetica", 8,  Font.BOLD,   BaseColor.WHITE);
        Font fDato    = FontFactory.getFont("Helvetica", 9,  Font.NORMAL, BaseColor.BLACK);
        Font fItem    = FontFactory.getFont("Helvetica", 9,  Font.NORMAL, BaseColor.BLACK);
        Font fItemBold= FontFactory.getFont("Helvetica", 9,  Font.BOLD,   BaseColor.BLACK);
        Font fPie     = FontFactory.getFont("Helvetica", 7,  Font.ITALIC, BaseColor.DARK_GRAY);

        BaseColor azul      = WebColors.getRGBColor("#4169E1");
        BaseColor azulClaro = WebColors.getRGBColor("#6699FF");
        BaseColor grisClaro = WebColors.getRGBColor("#F5F5F5");

        Document document = new Document(PageSize.A4, 40, 40, 55, 40);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            FooterPrescripcion footer = new FooterPrescripcion();
            writer.setPageEvent(footer);
            document.open();

            // ---- Logo ----
            try {
                ServletContext ctx = this.getServletConfig().getServletContext();
                Image img = Image.getInstance(ctx.getRealPath("/Imagenes") + "/Logo_Gob.jpg");
                img.scalePercent(22);
                img.setAbsolutePosition(40, PageSize.A4.getHeight() - img.getScaledHeight() - 30);
                document.add(img);
            } catch (Exception ex) { /* logo opcional */ }

            document.add(new Paragraph("\n\n\n"));

            // ---- Título ----
            PdfPTable tTitulo = new PdfPTable(1);
            tTitulo.setWidthPercentage(100);
            PdfPCell cTitulo = new PdfPCell(new Phrase("MEDICAMENTOS", fTitulo));
            cTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cTitulo.setBackgroundColor(azul);
            cTitulo.setPadding(6);
            cTitulo.setBorder(0);
            // reemplazar font para que sea blanco
            cTitulo = new PdfPCell(new Phrase("MEDICAMENTOS",
                    FontFactory.getFont("Helvetica", 12, Font.BOLD, BaseColor.WHITE)));
            cTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cTitulo.setBackgroundColor(azul);
            cTitulo.setPadding(6);
            tTitulo.addCell(cTitulo);
            document.add(tTitulo);

            document.add(new Paragraph("\n"));

            // ---- Datos del paciente ----
            float[] wPac = {1f, 2f, 1f, 2f};
            PdfPTable tPac = new PdfPTable(wPac);
            tPac.setWidthPercentage(100);

            agregarCeldaLabel(tPac, "Paciente", fLabel, azul);
            String nomPac = duo.getNombres_paciente() + " " + duo.getApellidop_paciente()
                          + " " + duo.getApellidom_paciente();
            agregarCeldaDato(tPac, nomPac, fDato, 3);

            agregarCeldaLabel(tPac, "RUT", fLabel, azul);
            agregarCeldaDato(tPac, duo.getRut_paciente(), fDato, 1);
            agregarCeldaLabel(tPac, "Fecha", fLabel, azul);
            agregarCeldaDato(tPac, fechaHoy, fDato, 1);

            agregarCeldaLabel(tPac, "Cama", fLabel, azul);
            agregarCeldaDato(tPac, String.valueOf(duo.getCama()), fDato, 3);

            document.add(tPac);
            document.add(new Paragraph("\n"));

            // ---- Tabla de medicamentos ----
            PdfPTable tMeds = new PdfPTable(1);
            tMeds.setWidthPercentage(100);
            tMeds.setKeepTogether(false);

            int numero = 0;

            // ---- Prescripciones simples ----
            for (Object obj : lista_pm) {
                cPrescripcion pm = (cPrescripcion) obj;
                numero++;

                StringBuilder texto = new StringBuilder();
                texto.append(numero).append(". ");
                texto.append(pm.getMedicamento_desc()).append(": ");
                texto.append("Administrar ").append(pm.getDosis()).append(" ").append(pm.getUnidad_desc());
                texto.append(" vía ").append(pm.getVia_desc());
                texto.append(" cada ").append(pm.getFrecuencia());
                if (pm.getObservacion() != null && !pm.getObservacion().isEmpty()) {
                    texto.append(". ").append(pm.getObservacion());
                }

                PdfPCell cTexto = new PdfPCell();
                cTexto.addElement(new Paragraph(texto.toString(), fItem));
                cTexto.setPadding(4);
                cTexto.setPaddingLeft(6);
                cTexto.setBackgroundColor(numero % 2 == 0 ? grisClaro : BaseColor.WHITE);
                tMeds.addCell(cTexto);

            }

            // ---- Infusiones (al final) ----
            for (Object obj2 : lista_inf) {
                cInfusion inf = (cInfusion) obj2;
                numero++;

                StringBuilder texto = new StringBuilder();
                texto.append(numero).append(". ");

                boolean primero = true;
                for (Object detObj : inf.getDetalles()) {
                    cInfusionDetalle det = (cInfusionDetalle) detObj;
                    if (!primero) texto.append(" + ");
                    texto.append(det.getMedicamento_desc()).append(": ")
                         .append(det.getDosis()).append(" ").append(det.getUnidad_desc());
                    primero = false;
                }

                texto.append(" diluido en ").append(inf.getSuero_desc()).append(".");
                texto.append(" Administrar a ").append(
                        inf.getVelocidad_inf() == (long) inf.getVelocidad_inf()
                        ? String.valueOf((long) inf.getVelocidad_inf())
                        : String.valueOf(inf.getVelocidad_inf())
                ).append(" ").append(inf.getUnidad_velocidad()).append(".");
                if (inf.getObservacion() != null && !inf.getObservacion().isEmpty()) {
                    texto.append(" ").append(inf.getObservacion());
                }

                PdfPCell cTexto = new PdfPCell();
                cTexto.addElement(new Paragraph(texto.toString(), fItem));
                cTexto.setPadding(4);
                cTexto.setPaddingLeft(6);
                cTexto.setBackgroundColor(numero % 2 == 0 ? grisClaro : BaseColor.WHITE);
                tMeds.addCell(cTexto);

            }

            if (numero == 0) {
                PdfPCell cVacio = new PdfPCell(new Phrase("Sin prescripciones registradas.", fDato));
                cVacio.setColspan(1);
                cVacio.setPadding(8);
                tMeds.addCell(cVacio);
            }

            document.add(tMeds);

            // ---- Firma médico ----
            document.add(new Paragraph("\n\n"));
            float[] wFirma = {3f, 1f};
            PdfPTable tFirma = new PdfPTable(wFirma);
            tFirma.setWidthPercentage(80);
            tFirma.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cFirmaLabel = new PdfPCell(new Phrase(
                    "Médico responsable: ___________________________________", fItem));
            cFirmaLabel.setBorder(0);
            cFirmaLabel.setPaddingTop(10);
            tFirma.addCell(cFirmaLabel);

            PdfPCell cFechaLabel = new PdfPCell(new Phrase("Fecha: " + fShort.format(new Date()), fItem));
            cFechaLabel.setBorder(0);
            cFechaLabel.setPaddingTop(10);
            tFirma.addCell(cFechaLabel);

            document.add(tFirma);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PDF_prescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PDF_prescripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCeldaLabel(PdfPTable tabla, String texto, Font font, BaseColor color) {
        PdfPCell c = new PdfPCell(new Phrase(texto, font));
        c.setBackgroundColor(color);
        c.setPadding(3);
        tabla.addCell(c);
    }

    private void agregarCeldaDato(PdfPTable tabla, String texto, Font font, int colspan) {
        PdfPCell c = new PdfPCell(new Phrase(texto != null ? texto : "", font));
        c.setColspan(colspan);
        c.setPadding(3);
        tabla.addCell(c);
    }

    public class FooterPrescripcion extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(
                    writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("Centro de Referencia de Salud de Maipú",
                            FontFactory.getFont("Helvetica", 7, Font.ITALIC, BaseColor.DARK_GRAY)),
                    297, 20, 0);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "PDF prescripcion de medicamentos e infusiones";
    }
}
