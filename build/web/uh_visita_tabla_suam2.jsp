<%-- 
    Document   : uh_visita_tabla_suam
    Created on : 29-ago-2012, 12:58:48
    Author     : EseGamboa
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.*,java.net.URL,java.util.Date,java.util.GregorianCalendar,java.util.Vector,java.text.DateFormat,java.util.Locale,java.util.Calendar" %>
<%@ include file="conexion.jsp"%>

<%
Statement st2 = cn.createStatement();
%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" >
<html>
    <head>
        <title>SISTEMA UHCE</title>
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css">
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style_diseno.css">
        <script type="text/javascript" src="js/valida_rut.js"></script>
        <LINK REL="SHORTCUT ICON" HREF="Iconos/flag_chile.ico">
        <LINK REL="stylesheet" TYPE="text/css" HREF="js/menu/menu.css">
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script src="ajax.js"></script>
        <jsp:include page="css/boton_html.jsp" />

        <script type="text/javascript">
            // Cambia estos parametros

            var seconds = 60; // el tiempo en que se refresca
            var divid = "timediv"; // el div que quieres actualizar!
            var url = "mensajes.jsp"; // el archivo que ira en el div

            ////////////////////////////////
            //
            // Refreshing the DIV
            //
            ////////////////////////////////

            function refreshdiv(){

                // The XMLHttpRequest object

                var xmlHttp;
                try{
                    xmlHttp=new XMLHttpRequest(); // Firefox, Opera 8.0+, Safari
                }
                catch (e){
                    try{
                        xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); // Internet Explorer
                    }
                    catch (e){
                        try{
                            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        catch (e){
                            alert("Tu explorador no soporta AJAX.");
                            return false;
                        }
                    }
                }

                // Timestamp for preventing IE caching the GET request
                var fetch_unix_timestamp ="";// lets declare the variable

                fetch_unix_timestamp = function()
                {
                    return parseInt(new Date().getTime().toString().substring(0, 10))
                }

                var timestamp = fetch_unix_timestamp();
                var nocacheurl = url+"?t="+timestamp;

                // The code...

                xmlHttp.onreadystatechange=function(){
                    if(xmlHttp.readyState==4){
                        document.getElementById(divid).innerHTML=xmlHttp.responseText;
                        setTimeout('refreshdiv()',seconds*1000);
                    }
                }
                xmlHttp.open("GET",nocacheurl,true);
                xmlHttp.send(null);
            }

            // Empieza la función de refrescar

            window.onload = function startrefresh(){
                setTimeout('refreshdiv()',seconds*1000);
            }

        </script>
    </head>



    <%
                // String titulo = " style=' background-color: #4169E1 ; color: white '  ";
                // String datos = " style=' background-color: #87CEFA ; color: yellow '  ";

                String titulo = " style=' background-color: #4169E1 ; color: white ;font-size: 12px  ";
              /*  String datos = " style=' background-color: yellow ; color: blue   "; // camilla ocupada
                String datos_vacia = "  style=' background-color:#FAF0E6; color: black   "; // camilla vacia

                String datos_verde = " style=' background-color:#FAF0E6; color: black  ";
                String datos_naranjo = " style=' background-color:#FF7F00; color: black  ";
                String datos_rojo = " style=' background-color:#FF0000; color: black ";

/*
                NegocioQ neg = new NegocioQ();
                ArrayList lista_camas = neg.lista_camillas_actuales();

                ArrayList lista_diagnosticos = neg.lista_diagnosticos_das_en_camilla();
                /*
                String fecha_hora = neg.obtiene_fecha_hora();
                String dia = fecha_hora.substring(0, 2);
                String mes = fecha_hora.substring(3, 5);
                String año = fecha_hora.substring(6, 10);
                String fecha_mda = mes + "-" + dia + "-" + año;
                String hora_duo = fecha_hora.substring(fecha_hora.length() - 8, fecha_hora.length());
                 */
    %>

    <body onload=""  >


 

        <DIV id="formArea" >

            <table border="0"  style=" width:980px">
                <tr>
                    <td>
                        <img src="Imagenes/Encabezado_1.png" width="500" alt="Utilidad Observacion SUAM"/>
                    </td>
                    <td <% out.write(" style='width:520px' ");%> >
                        <table border="0" width="99%">
                            <tr><td align="center"><b>TIEMPO DE OBSERVACIÓN</b></td> </tr>
                            <tr><td align="center">
                                    <table border="0" width="100%">
                                        <tr><td style="background-color:#FAF0E6; border-style: inset; border-width: 2px;" align="center">Menos de 4 Horas. &nbsp;&nbsp;</td><td></td> </tr>
                                        <tr><td style="background-color:#FF7F00; border-style: inset; border-width: 2px;" align="center">Entre 4 y 6 Horas. &nbsp;&nbsp;</td><td></td> </tr>
                                        <tr><td style="background-color:#FF0000; border-style: inset; border-width: 2px;" align="center">Más de 6 Horas. &nbsp;&nbsp;</td><td></td> </tr>
                                    </table>
                                </td> </tr>
                        </table>
                    </td>
                    <td <% out.write(" style='width:250px' align='center' ");%> valign="middle" >
                        <div id="loaddiv">
                            <script type="text/javascript">
                                refreshdiv();
                            </script>
                            <b>
                            <div name="timediv" id="timediv">
                            </div>
                            </b>
                        </div>
                    </td>
                    <td>
                        <input class="btn btn-primary" type="submit" value="Ver Grilla" name="btn_listado" onclick="location.href='uh_visita_suam2.jsp'" />
                    </td>
                </tr>
            </table>


            <font style=" font-family: arial   " >
                <table border="1" style=" width:980px  " >
                    <thead>
                        <tr style="  height: 25px  " >
                            <th <% out.write(titulo + " ;width:60px   '");%> >Camilla</th>
                            <th <% out.write(titulo + " ;width:90px   '");%>>Fecha Ingreso</th>
                            <th <% out.write(titulo + " ;width:250px '");%> >Nombre</th>
                            <th <% out.write(titulo + "  '");%> >Diagnóstico</th>
                            <th <% out.write(titulo + " ;width:150px '");%> >Médico Tratante</th>
                            <th <% out.write(titulo + " ;width:140px '");%> >Indicación/Egreso</th>
                            <!--<th <% out.write(titulo + " ;width:140px '");%> >Espera Radiografia</th>
                            <th <% out.write(titulo + " ;width:140px '");%> >Espera Exac. Laboratorio</th>
                            <th <% out.write(titulo + " ;width:140px '");%> >Observacion</th>-->
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        try
                        {
                            String query="select A.cam_id, A.cam_descripcion" +
                                " from schema_suam.camilla A" +
                                " where A.cam_estado=1" +
                                " order by A.cam_id";
                            ResultSet rs=st.executeQuery(query);
                            while(rs.next())
                            {%>
                                <tr style="  height: 25px  " >
                                    <%
                                        int das_id=0;
                                        String das_fecha="";
                                        int das_estado=0;
                                        String nombrePaciente="";
                                        String nombreMedico="";
                                        String diagnostico="";
                                        String indicacionEgreso="";
                                        int dif_hh=0;
                                        int dif_dd=0;
                                        try
                                        {
                                            String query2="select A.das_id,to_char(A.das_fecha_ingreso,'DD/MM HH24:MI' ) as das_fecha_ingreso,A.das_estado,\n" +
                                              " (B.nombre ||' '||B.apellido_paterno ||' '||B.apellido_moderno ) as nombre,\n" +
                                              " ('Dr. '||C.usuario_urgencia_nombres||' '||C.usuario_urgencia_apellidop||' '||C.usuario_urgencia_apellidom) as medico,\n" +
                                              " EXTRACT(HOUR FROM CURRENT_TIMESTAMP-A.das_fecha_ingreso) as dif_hh,\n" +
                                              " EXTRACT(DAY FROM CURRENT_TIMESTAMP-A.das_fecha_ingreso) as dif_dd\n" +
                                              " from schema_suam.das A, agenda.paciente B, \n" +
                                              " schema_urgencia.usuario_urgencia C \n" +
                                              " where A.das_camilla="+rs.getString("cam_id")+" and \n" +
                                              " A.das_estado in (1,2,3) and \n" +
                                              " B.rut=A.das_paciente and \n" +
                                              " C.usuario_urgencia_rut=A.das_medico";
                                            ResultSet rs2=st2.executeQuery(query2);
                                            int control=0;
                                            if(rs2.next())
                                            {
                                                das_id=rs2.getInt("das_id");
                                                das_fecha=rs2.getString("das_fecha_ingreso");
                                                das_estado=rs2.getInt("das_estado");
                                                nombrePaciente=rs2.getString("nombre");
                                                nombreMedico=rs2.getString("medico");
                                                dif_hh=rs2.getInt("dif_hh");
                                                dif_dd=rs2.getInt("dif_dd");
                                            }


                                        }
                                        catch(SQLException ex){out.print(ex);}

                                        try
                                        {
                                            String query3="select A.dia_id,A.dia_detalle" +
                                                " from schema_suam.diagnostico A" +
                                                " where A.das_id="+das_id+" and" +
                                                " A.dia_estado=1 ";
                                            ResultSet rs3=st2.executeQuery(query3);
                                            int controldiag=0;
                                            while(rs3.next())
                                            {
                                                if(controldiag==0)
                                                {diagnostico=rs3.getString("dia_detalle");}
                                                else
                                                {diagnostico=diagnostico+"<br>"+rs3.getString("dia_detalle");}
                                                controldiag++;
                                            }
                                        }        
                                        catch(SQLException ex){}

                                        try
                                        {
                                            String query3="select B.des_descripcion" +
                                                " from schema_suam.das A, schema_suam.destino B" +
                                                " where A.das_id="+das_id+" and" +
                                                " B.des_id=A.das_indicacion_destino ";
                                            ResultSet rs3=st2.executeQuery(query3);
                                            int controlIE=0;
                                            while(rs3.next())
                                            {
                                                if(controlIE==0)
                                                {indicacionEgreso=rs3.getString("dia_detalle");}
                                                else
                                                {indicacionEgreso=indicacionEgreso+"<br>"+rs3.getString("dia_detalle");}
                                                controlIE++;
                                            }
                                        }
                                        catch(SQLException ex){}

                                        String color="";
                                        if (dif_dd == 0 && dif_hh < 4)
                                        {color = "bgcolor='#FAF0E6'";}//verde
                                        if (dif_dd == 0 && (dif_hh == 4 || dif_hh == 5))
                                        {color = "bgcolor='#FF7F00'";}//naranjo
                                        if (dif_dd == 0 && (dif_hh > 5))
                                        {color = "bgcolor='#FF0000'";}//rojo
                                        if (dif_dd > 0)
                                        {color = "bgcolor='#FF0000'";}//rojo

                                        %>
                                        <th <%=color%>><font style='font-size: 12px'><%=rs.getString("cam_descripcion").replaceAll("CAMILLA","")%></font></th>
                                        <th <%=color%>><font style='font-size: 12px'><%=das_fecha%></font></th>
                                        <th <%=color%>><font style='font-size: 12px'><%=nombrePaciente%></font></th>
                                        <th <%=color%>><font style='font-size: 12px'><%=diagnostico%></font></th>
                                        <th <%=color%>><font style='font-size: 12px'><%=nombreMedico%></font></th>
                                        <th <%=color%>><font style='font-size: 12px'><%=indicacionEgreso%></font></th>
                                </tr>
                            <%}

                        }
                        catch(SQLException ex){out.print(ex);}
                        %>
                    </tbody>
                </table>
            </font>
        </DIV>
                   
<%
st.close();
st2.close();
cn.close();
%>

