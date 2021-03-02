<%-- 
    Document   : mensajes.jsp
    Created on : 31-ago-2012, 13:13:09
    Author     : EseGamboa
--%>

<%@page import="CapaNegocio.NegocioQ"%>

<%

try{
      String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
      "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};


            NegocioQ neg = new NegocioQ();

            String fecha_hora = neg.obtiene_fecha_hora();
            String dia = fecha_hora.substring(0, 2);
            String mes = fecha_hora.substring(3, 5);
            String año = fecha_hora.substring(6, 10);
            String fecha_mda = mes + "-" + dia + "-" + año;
            String hora_duo = fecha_hora.substring(fecha_hora.length() - 8, fecha_hora.length()-3);

            out.write("" +dia+" "+meses[Integer.parseInt(mes)-1]+" "+hora_duo+"");
            }catch (Exception ex){
            out.write("--:--:--");
            }
%>



