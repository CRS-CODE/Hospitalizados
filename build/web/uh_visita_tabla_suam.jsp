<%-- 
    Document   : uh_visita_tabla_suam
    Created on : 29-ago-2012, 12:58:48
    Author     : EseGamboa
--%>
<%@page import="CapaDato.cDau"%>
<%@page import="CapaDato.cDiagnostico"%>
<%@page import="CapaDato.cDas"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
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
                // String titulo = " style=' background-color: #f7903b ; color: white '  ";
                // String datos = " style=' background-color: #fcd5b6 ; color: yellow '  ";

                String titulo = " style=' background-color: #f7903b ; color: white ;font-size: 12px  ";
                String datos = " style=' background-color: yellow ; color: blue   "; // camilla ocupada
                String datos_vacia = "  style=' background-color:#FAF0E6; color: black   "; // camilla vacia

                String datos_verde = " style=' background-color:#FAF0E6; color: black  ";
                String datos_naranjo = " style=' background-color:#FF7F00; color: black  ";
                String datos_rojo = " style=' background-color:#FF0000; color: black ";


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
                        <input class="btn btn-primary" type="submit" value="Ver Grilla" name="btn_listado" onclick="location.href='uh_visita_suam.jsp'" />
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
 
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                    Iterator it_mar = lista_camas.iterator();
                                    String nombre_completo_paciente = "";
                                    String nombre_completo_doctor = "";
                                    int numero_dau = 0;
                                    String descri_dau = "";
                                    String diagnostico = "";
                                    String fecha_ingreso = "";
                                    String indicacion_egreso = "&nbsp;";

                                    String color = "";

                                    while (it_mar.hasNext()) {
                                        cDas mar = (cDas) it_mar.next();

                                        /*si el paciente es NN */
                                        if (mar.getDau_id() == 0) {
                                            cDau busca_nn = neg.obtiene_paciente_segun_dauNN(mar.getDau_nn_id());
                                            mar.setRut_paciente("NN");
                                            mar.setNombres_paciente(busca_nn.getNombres_paciente());
                                            mar.setApellidop_paciente(busca_nn.getApellidop_paciente());
                                            mar.setApellidom_paciente(busca_nn.getApellidom_paciente());
                                            mar.setSexo(busca_nn.getSexo());
                                            mar.setSexo_descri(busca_nn.getSexo_descri());
                                            mar.setTelefono1(busca_nn.getTelefono1());

                                            mar.setTelefono2(busca_nn.getTelefono2());
                                            mar.setDireccion(busca_nn.getDireccion());
                                            mar.setComuna_descri(busca_nn.getComuna_descri());
                                            mar.setFecha_nac(busca_nn.getFecha_nac());
                                            mar.setEdad(busca_nn.getEdad());
                                        }
                                        /*si el paciente es NN */
                                        if (mar.getDif_dd() == 0 && mar.getDif_hh() < 4) {
                                            datos = datos_verde;
                                        } else if (mar.getDif_dd() == 0 && (mar.getDif_hh() == 4 || mar.getDif_hh() == 5)) {
                                            datos = datos_naranjo;
                                        } else if (mar.getDif_dd() == 0 && (mar.getDif_hh() > 5)) {
                                            datos = datos_rojo;
                                        } else if (mar.getDif_dd() > 0) {
                                            datos = datos_rojo;
                                        }

                                        color = datos;
                                        diagnostico = "";
                                        nombre_completo_paciente = neg.corta_cadena(mar.getNombres_paciente()) + " " + mar.getApellidop_paciente() + " " + mar.getApellidom_paciente();
                                        nombre_completo_doctor = "Dr. " + neg.corta_cadena(mar.getNombre_medico()) + " " + mar.getApellidop_medico();
                                        fecha_ingreso = mar.getFecha_ingreso();
                                        indicacion_egreso = mar.getIndicacion_destino_descri();
                                        
                                        if (indicacion_egreso.length()==0)
                                        {indicacion_egreso="&nbsp;";}

                                        if (nombre_completo_paciente.length() > 30) {
                                            nombre_completo_paciente = nombre_completo_paciente.substring(0, 30);
                                        }


                                        if (nombre_completo_doctor.length() > 20) {
                                            nombre_completo_doctor = nombre_completo_doctor.substring(0, 20);
                                        }


                                        if (mar.getId_das() == 0) {
                                            numero_dau = 0;
                                            descri_dau = " ";
                                            nombre_completo_paciente = "&nbsp;";
                                            nombre_completo_doctor = "&nbsp;";
                                            color = datos_vacia; // cama desocupada
                                            fecha_ingreso = "&nbsp;";
                                            indicacion_egreso = "&nbsp;";
                                        } else if (mar.getDau_id() == 0) {
                                            // DAU NN
                                            numero_dau = mar.getDau_nn_id();
                                            descri_dau = mar.getDau_nn_id() + "(DAU NN)";
                                            nombre_completo_paciente = "(NN)" + nombre_completo_paciente.toUpperCase();
                                        } else {
                                            numero_dau = mar.getDau_id();
                                            descri_dau = mar.getDau_id() + "";
                                        }



                                        Iterator it = lista_diagnosticos.iterator();
                                        while (it.hasNext()) {
                                            cDiagnostico diag = (cDiagnostico) it.next();
                                            if (mar.getId_das() == diag.getId_duo()) {

                                                String pre_diagnostico = diag.getDescripcion_diagnostico().toUpperCase();

                                                //pre_diagnostico = pre_diagnostico.substring(0, 12);

                                                diagnostico += "" + pre_diagnostico + " // ";
                                            }
                                        }

                                        if (diagnostico.length() > 35) {
                                            diagnostico = diagnostico.substring(0, 35) + "...";
                                        } else {
                                            if (diagnostico.length() > 3) {
                                                diagnostico = diagnostico.substring(0, diagnostico.length() - 3);
                                            }
                                        }


                                        out.write("<tr style='  height: 24px '  >");
                                        String camilla = mar.getCamilla_descri();
                                        camilla = camilla.replace("CAMILLA", "");
                                        out.write("<td " + color + "' align='center' ><b>" + camilla + "</b></td>");
                                        out.write("<td " + color + "' > <font style='font-size: 12px' >" + fecha_ingreso + "</font></td>");
                                        out.write("<td " + color + "' > <font style='font-size: 12px' >" + nombre_completo_paciente + "</td>");
                                        out.write("<td " + color + "' > <font style='font-size: 12px' >" + diagnostico + "</font></td>");
                                        out.write("<td " + color + "' > <font style='font-size: 12px' >" + nombre_completo_doctor + "</font> </td>");
                                        out.write("<td " + color + "' > <font style='font-size: 12px' >" + indicacion_egreso + "</font></td>");
                                   
                                        out.write("</tr>");
                                    }


                        %>
                        <tr>


                        </tr>

                    </tbody>
                </table>
      </font>
        </DIV>
                   


