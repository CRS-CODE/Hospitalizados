<%-- 
    Document   : logo_agua
    Created on : 25-feb-2015, 9:29:34
    Author     : Informatica
--%>

<%! 
public class LogoWatermark extends PdfPageEventHelper {
	/** An Image that goes in the header. */
     public Image HeaderImage;
     public Image Logo_crs;
     public Image FooterImage2;
  
      
    public PdfGState gstate;
    /** A template that will hold the total number of pages. */
      
    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
          HeaderImage =Image.getInstance("D:/VICTOR/ProyectosWeb/modulo_uo/web/Imagenes/membrete.png");//(path +"logo_gobierno.JPG");
            Logo_crs =Image.getInstance("D://logo_crs.png");//(path +"logo_gobierno.JPG");
            FooterImage2 =Image.getInstance("D:/FooterSDGP.png");//(path +"logo_gobierno.JPG");
         //colocar esto...     /Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps/
            
            /*
            HeaderImage =Image.getInstance("D:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps/modulo_uo/web/Imagenes/membrete.png");//(path +"logo_gobierno.JPG");
            Logo_crs =Image.getInstance("D:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps/modulo_uo/web/Imagenes/logo_crs.png");//(path +"logo_gobierno.JPG");
            FooterImage2 =Image.getInstance("D:Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps/modulo_uo/web/Imagenes/FooterSDGP.png");//(path +"logo_gobierno.JPG");
         */
            //  
            
            /* HeaderImage =Image.getInstance("C:/Archivos de programa/Apache Software Foundation/Tomcat 6.0/webapps/modulo_uo/Imagenes/membrete.png");//(path +"logo_gobierno.JPG");
            Logo_crs =Image.getInstance("C:/Archivos de programa/Apache Software Foundation/Tomcat 6.0/webapps/modulo_uo/Imagenes/logo_crs.png");//(path +"logo_gobierno.JPG");
            FooterImage2 =Image.getInstance("C:/Archivos de programa/Apache Software Foundation/Tomcat 6.0/webapps/modulo_uo/Imagenes/FooterSDGP.png");//(path +"logo_gobierno.JPG");
            
            HeaderImage =Image.getInstance("/media/Documentos/VICTOR/Proyectos Web/modulo_uo/web/Imagenes/membrete.png");//(path +"logo_gobierno.JPG");
            Logo_crs =Image.getInstance("/media/Documentos/VICTOR/Proyectos Web/modulo_uo/web/Imagenes/logo_crs.png");//(path +"logo_gobierno.JPG");
            FooterImage2 =Image.getInstance("/media/Documentos/VICTOR/Proyectos Web/modulo_uo/web/Imagenes/FooterSDGP.png");//(path +"logo_gobierno.JPG");
            */
            gstate = new PdfGState();
            gstate.setFillOpacity(0.2f);
            gstate.setStrokeOpacity(0.2f);
             }
        catch(Exception e) {
            throw new ExceptionConverter(e);
        }
    }    
    
   
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContentUnder();
                  
        
         cb.setGState(gstate);
           
           try {
                cb.addImage(HeaderImage, HeaderImage.width(),0,0, HeaderImage.height(),53, 20);
                //cb.addImage(Logo_crs, Logo_crs.width(),0,0, Logo_crs.height(),145, 170);
                //cb.addImage(FooterImage2, FooterImage2.width(),0,0, FooterImage2.height(),53, 20); 
            }
            catch(Exception e) {
                throw new ExceptionConverter(e);
            }
                  
    }
    
    
}
%>