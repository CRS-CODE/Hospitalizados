/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

import com.lowagie.text.Document;

import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 *
 * @author a
 */
public class pdfconpieyfoto extends PdfPageEventHelper {

    public Image headerImage;
    public Image headerImage2;

    public PdfGState gstate;
    public PdfTemplate tpl;
    /**
     * The font that will be used.
     */
    public BaseFont helv;
    /**
     * A template that will hold the total number of pages.
     */
    

    public void onOpenDocument(PdfWriter writer, Document document) {
        try {

            /*Debo colocar si es tipo de doctor tipo de firma*/
            headerImage = Image.getInstance("/logo_gobierno.jpg");
            // headerImage2 = Image.getInstance("logo_gob_2.jpg");

            gstate = new PdfGState();
            gstate.setFillOpacity(0.89f);
            gstate.setStrokeOpacity(0.3f);
            helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
            tpl = writer.getDirectContent().createTemplate(100, 100);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContentUnder();
        String text = "";

        text = "pagina 1 de 1 ";

        float textSize = helv.getWidthPoint(text, 20);
        float textBase = document.bottom() - 10;
        float adjust = helv.getWidthPoint("0", 12);
        cb.beginText();
        cb.setFontAndSize(helv, 8);
        cb.setTextMatrix(document.right() - textSize - adjust, textBase);
        cb.showText(text);
        cb.endText();
        cb.addTemplate(tpl, document.right() - adjust, textBase);

        cb.setGState(gstate);

        try {
            //  cb.addImage(headerImage2, 240, 0, 0, 89, 70, 50);
            cb.addImage(headerImage, 60, 0, 0, 50, 60, 720);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        tpl.beginText();
        tpl.setFontAndSize(helv, 8);
        tpl.setTextMatrix(0, 0);
        //tpl.showText("" + (writer.getPageNumber() - 1));
        tpl.endText();
    }

}
