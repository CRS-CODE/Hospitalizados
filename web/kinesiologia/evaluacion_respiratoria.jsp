<%-- 
Document   : evaluacion_respiratoria
Created on : 14-ago-2014, 15:07:01
Author     : Informatica
--%>

<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.text.DateFormat"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("../index.jsp?timeout=1");
    } else {
        String style_0 = " style=' width: 150px ' ";
        String style_1 = " style=' width: 200px ' ";
        String style_2 = " style=' width: 250px ' ";
        String style_3 = " style=' width: 575px ' ";

        String titulo = " style=' background-color: #4169E1 ; color: white '  ";
        String datos = " style=' background-color: #87CEFA ; color: black '  ";

        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
        cDuo duo = neg.obtiene_duo(id_duo);
%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="../Header.jsp" />


<body onload="inicio()" >

<legend>INGRESO EVALUACION RESPIRATORIA</legend>


<table BORDER="0" style=" width: 75%" >
    <tr>
        <td colspan="6" <%=titulo%> >I.- IDENTIFICACION PERSONAL</td>
    </tr>
    <tr>
        <td <%=datos%>>Nombre</td>
        <td colspan="3" > <% out.write(duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%> </td>
        <td <%=datos%>>Cama</td>
        <td><% out.write("" + duo.getCama_descripcion());%></td>
    </tr>
    <tr>
        <td <%=datos%>>Rut</td>
        <td><% out.write("" + duo.getRut_paciente());%>
            <input type="hidden" name="txt_paciente_rut" id="txt_paciente_rut" value="<% out.write("" + duo.getRut_paciente());%>" >
        </td>
        <td <%=datos%>>Edad</td>
        <td><% out.write("" + duo.getEdad());%></td>
        <td <%=datos%>>Fecha Nacimiento</td>
        <td><% out.write("" + duo.getFecha_nac());%></td>
    </tr>
    <tr>
        <td <%=datos%>> Domicilio</td>
        <td colspan="5" ><% out.write("" + duo.getDireccion());%></td>

    </tr>
    <tr>
        <td <%=datos%>>Comuna</td>
        <td colspan="3" > <% out.write("" + duo.getComuna_descri());%> </td>
        <td <%=datos%>>DUO</td>
        <td> <% out.write("" + duo.getId_duo());%> </td>
    </tr>
</table>
<form name="form_1" method="GET" action="../ingreso_caso" onsubmit="return valida_respiratoria()"  >

    <table border="0" cellspacing="5px" cellpadding="3px"   >
        <tr>
        <input type="hidden" name="txt_modo" value="1" >
        <input type="hidden" name="txt_duo" value="<%=duo.getId_duo()%>" >
        <input type="hidden" name="txt_usuario"  id="txt_usuario"  value="<%=session1.getAttribute("usuario_rut")%>" >
        <td colspan="6" <%=titulo%> >Datos de la evaluación</td>
        </tr>

        <tr>
            <td>Oxígeno</td>
            <td>
                <select name="cbo_oxigeno" id="cbo_oxigeno" onchange=" cambia_oxigeno()"  <%=style_0%>  >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>
            </td>

            <td  id="td_oxigeno" colspan="0"  >
                Oxígeno 
            </td>
            <td id="td_oxigeno1"  ><input type="text" name="txt_oxigeno" id="txt_oxigeno" style=""  > lt/min</td>
        </tr>
        <tr>
            <td>Vías Venosas</td>
            <td colspan="0" >
                <select name="cbo_via_venosa" id="cbo_via_venosa"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>

            </td>
        </tr>
        <tr>
            <td>Vía Urinaria o Sonda</td>
            <td colspan="0" >
                <select name="cbo_via_urinaria" id="cbo_via_urinaria" onchange="cambia_via_urinaria()"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>



            </td>
            <td id="td_via_urinaria_sonda" >
                Especifique
            <td id="td_via_urinaria_sonda1" colspan="3"  >
                <input type="text" name="txt_via_urinaria_especifique" id="txt_via_urinaria_especifique" <%=style_2%>  > 
            </td>

        </tr>
        <tr>
            <td>Estado</td>
            <td colspan="0" >
                <select name="cbo_vigil" id="cbo_vigil" onchange="cambia_estado()"  <%=style_0%> >
                    <option value="-1" > Seleccione...</option>
                    <option value="1" >Vigil</option>
                    <option value="2" >No Vigil</option>
                </select>
            </td>
        </tr>
        <tr id="tr_vigil" >
            <td id="tr_alteracion_lenguaje">Alteración Lenguaje</td>
            <td colspan="0" >
                <select name="cbo_alteracion_lenguaje" id="cbo_alteracion_lenguaje"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>
            </td>
            <td>Responde</td>
            <td colspan="2" >
                <input type="checkbox" name="chk_estimulo_verbal" value="ON" />estímulo verbal
                <br><input type="checkbox" name="chk_estimulo_visual" value="ON" />estímulo visual
            </td>

        </tr>

        <tr id="tr_no_vigil" >
            <td>Estado no vigil</td>
            <td colspan="0" >
                <select name="cbo_estado_no_vigil" id="cbo_estado_no_vigil"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Obnibulado</option>
                    <option value="2" >Soporoso</option>
                    <option value="3" >Comatoso</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Ubicación</td>
            <td colspan="5" >

                <input type="checkbox" name="chk_temporal" id="chk_temporal" value="ON" />Temporal
                <input type="checkbox" name="chk_espacial" id="chk_espacial" value="ON" />Espacial


            </td>
        </tr>
        <tr valign="top" >
            <td  >Postura</td>
            <td colspan="0" >
                <select name="cbo_postura" id="cbo_postura"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Simétrico</option>
                    <option value="2" >Asimétrico</option>
                </select>
            </td>
            <td colspan="2" style="  text-align:  start " >

                <textarea name="txa_eess" id="txt_eess" cols="33" rows="2" placeholder="Escribe aquí el EESS..." ></textarea>
            </td>
            <td colspan="2"  >
                <textarea name="txa_eeii" id="txt_eeii" cols="33" rows="2" placeholder="Escribe aquí el EEII..." ></textarea>
            </td>

        </tr>
        <tr>
            <td>Deformidades</td>
            <td colspan="0" >
                <input type="text" name="txt_deformidad" id="txt_deformidad" <%=style_3%>  >
            </td>
        </tr>   
        <tr>
            <td>Estado Nutricional</td>
            <td colspan="0" >
                <select name="cbo_estado_nutricional" id="cbo_estado_nutricional"  <%=style_0%>  >
                    <option value="-1"  >Seleccione...</option>
                    <option value="1" >Caquético</option>
                    <option value="2" >Normal</option>
                    <option value="3" >Sobrepeso</option>
                    <option value="4" >Obeso</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Coloración de la Piel</td>
            <td colspan="0" >
                <select name="cbo_coloracion" id="cbo_coloracion" onchange="cambia_coloracion_piel()"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1"  >Normal</option>
                    <option value="2"  >Cianotica</option>
                </select>
            </td>

            <td id="td_cianosis" colspan="0"  >
                Cianosis
            </td>
            <td id="td_cianosis1" colspan="0"  >
                <select name="cbo_cianosis" id="cbo_cianosis"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Periférica</option>
                    <option value="2" >Central</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>Estado Piel</td>
            <td colspan="5" >
                <table>
                    <tr>
                        <td>  <input type="checkbox" name="chk_normal" id="chk_normal" value="ON" /></td>
                        <td>Normal</td>
                        <td> <input type="text" name="txt_normal" id="txt_normal" <%=style_1%>  ></td>
                    </tr>
                    <tr>
                        <td> <input type="checkbox" name="chk_escaras" id="chk_escaras" value="ON" /></td>
                        <td>Escaras</td>
                        <td>   <input type="text" name="txt_escaras" id="txt_escaras" <%=style_1%>  ></td>
                    </tr>
                    <tr>
                        <td>  <input type="checkbox" name="chk_cicatrices" id="chk_cicatrices" value="ON" /></td>
                        <td>Cicatrices</td>
                        <td> <input type="text" name="txt_cicatrices" id="txt_cicatrices" <%=style_1%>    ></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="chk_hematomas" id="chk_hematomas" value="ON" /></td>
                        <td>Hematomas</td>
                        <td> <input type="text" name="txt_hematomas" id="txt_hematomas" <%=style_1%>  ></td>
                    </tr>
                    <tr>
                        <td> <input type="checkbox" name="chk_petequias" id="chk_petequias" value="ON" /></td>
                        <td>Petequias</td>
                        <td>   <input type="text" name="txt_petequias" id="txt_petequias" <%=style_1%>  >  </td>
                    </tr>
                    <tr>
                        <td> <input type="checkbox" name="chk_edemas" id="chk_edemas" value="ON" /></td>
                        <td>Edemas</td>
                        <td><input type="text" name="txt_edemas" id="txt_edemas" <%=style_1%> ></td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr>
            <td>Patrón Respiratorio</td>
            <td>
                <select name="cbo_patron_respiratorio" id="cbo_patron_respiratorio"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Diafragmático</option>
                    <option value="2" >Costal Inferior</option>
                    <option value="3" >Costal Superior</option>
                </select>
            </td>
            <td>Respirador</td>
            <td>
                <select name="cbo_respirador" id="cbo_respirador"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Nasal</option>
                    <option value="2" >Bucal</option>
                    <option value="3" >Ninguno</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>Dificultad Respiratoria</td>
            <td colspan="0" >
                <select name="cbo_dificultad_respiratoria" id="cbo_dificultad_respiratoria" onchange="cambia_dificultad_respiratoria()"  <%=style_0%>  >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>
            </td>
            <td colspan="1" id="td_uso_musculatura"  >Uso Musculatura<br> Accesoria</td>
            <td colspan="0" id="td_uso_musculatura1"  >
                <select name="cbo_musculatura_accesoria" id="cbo_musculatura_accesoria" onchange="cambia_uso_musculatura()"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>
            </td>  

            <td  id="td_uso_musculatura2" colspan="2"  >
                Detalle <input type="text" name="txt_musculatura_accesoria_det" id="txt_musculatura_accesoria_det" >
            </td>
        </tr>

        <tr>
            <td>Aleteo Nasal</td>
            <td colspan="0">
                <select name="cbo_aleteo_nasal" id="cbo_aleteo_nasal"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>
            </td>
            <td>Aleteo Costal</td>
            <td colspan="0" >
                <select name="cbo_aleteo_costal" id="cbo_aleteo_costal"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Si</option>
                    <option value="2" >No</option>
                </select>
            </td>
            <td>Temperatura</td>
            <td colspan="0" >
                <select name="cbo_temperatura" id="cbo_temperatura"   <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Febril</option>
                    <option value="2" >Afebril</option>
                </select>
            </td>
        </tr>


        <tr>
            <td>Elasticidad de la piel</td>
            <td colspan="0" >
                <select name="cbo_elasticidad" id="cbo_elasticidad" onchange="cambia_elasticidad()"  <%=style_0%>  >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Normal</option>
                    <option value="2" >Restringida</option>
                </select>
            </td>
            <td id="td_elasticidad" colspan="0" >Detalle</td>
            <td id="td_elasticidad1" >
                <input type="text" name="txt_elasticidad" id="txt_elasticidad" >
            </td>
        </tr> 
        <tr>
            <td>Restricción Miofascial</td>
            <td colspan="5" >
                <input type="text" name="txt_restriccion_miofascial" id="txt_restriccion_miofascial" <%=style_3%>  >
            </td>
        </tr>
        <tr>
            <td>Llene Capilar</td>
            <td>
                <select name="cbo_llene_capilar" id="cbo_llene_capilar"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Menor 2 segundos</option>
                    <option value="2" >Mayor 2 segundos</option>
                </select>
            </td>
            <td>Edema</td>
            <td>
                <select name="cbo_edema" id="cbo_edema" onchange=""  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Blando</option>
                    <option value="2" >Duro</option>
                    <option value="3" >Ninguno</option>
                </select>
            </td>
            <td>Dolor</td>
            <td>
                <select name="cbo_dolor" id="cbo_dolor" onchange=""  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="1" >Presente</option> 
                    <option value="2" >No presente</option>   
                </select>
            </td>

        <tr>
            <td>EVA</td>
            <td colspan="0"  >
                <select name="cbo_eva" id="cbo_eva"  <%=style_0%> >
                    <option value="-1" >Seleccione...</option>
                    <option value="0" >0/10</option>
                    <option value="1" >1/10</option>
                    <option value="2" >2/10</option>
                    <option value="3" >3/10</option>
                    <option value="4" >4/10</option>
                    <option value="5" >5/10</option>
                    <option value="6"  >6/10</option>
                    <option value="7" >7/10</option>
                    <option value="8" >8/10</option>
                    <option value="9" >9/10</option>
                    <option value="10" >10/10</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Desalineación Articular</td>
            <td colspan="0" >
                <input type="text" name="txt_desalineacion_articular" id="txt_desalineacion_articular" <%=style_3%> >
            </td>
        </tr>
        <tr>
            <td>Frecuencia <br>Cardíaca</td>
            <td colspan="0" >
                <input type="text" name="txt_frecuencia_cardiaca" id="txt_frecuencia_cardiaca" <%=style_0%> >
            </td>
            <td>Frecuencia <br>Respiratoria</td>
            <td >
                <input type="text" name="txt_frecuencia_respiratoria" id="txt_frecuencia_respiratoria" <%=style_0%> >
            </td>
            <td>Saturación</td>
            <td>
                <input type="text" name="txt_saturacion" id="txt_saturacion" <%=style_0%> >
            </td>
        </tr>





        <tr id="tr_pulmon" <%=datos%> >
            <td style="border-top: 1px solid #000;"  >Auscultación</td>
            <td  colspan="2" style="border-top: 1px solid #000;" >
                Pulmón izquierdo
            </td>
            <td colspan="3" style="border-top: 1px solid #000;" >
                Pulmón derecho
            </td>
        </tr>
        <tr id="tr_pulmon1" >
            <td>Lóbulo Superior</td>
            <td colspan="2" >
                <select name="cbo_aus_pi_ls" id="cbo_aus_pi_ls"  <%=style_0%>  >
                    <option value="1"  >MP Presente</option>
                    <option value="2"  >MP Disminuido</option>
                    <option value="3"  >Mp No Presente</option>
                </select>
            </td>
            <td colspan="2" >
                <select name="cbo_aus_pd_ls" id="cbo_aus_pd_ls"  <%=style_0%>  >
                    <option value="1"  >MP Presente</option>
                    <option value="2"  >MP Disminuido</option>
                    <option value="3"  >MP No Presente</option>
                </select>
            </td>
        </tr>
        <tr id="tr_pulmon2" >
            <td>Lóbulo Medio</td>
            <td colspan="2" >

            </td>
            <td colspan="2" >
                <select name="cbo_aus_pd_lm" id="cbo_aus_pd_lm"  <%=style_0%>  >
                    <option value="1"  >MP Presente</option>
                    <option value="2"  >MP Disminuido</option>
                    <option value="3"  >MP No Presente</option>
                </select>
            </td>
        </tr>
        <tr id="tr_pulmon3" >
            <td>Lóbulo Inferior</td>
            <td colspan="2" > 
                <select name="cbo_aus_pi_li" id="cbo_aus_pi_li"  <%=style_0%>  >
                    <option value="1"  >MP Presente</option>
                    <option value="2"  >MP Disminuido</option>
                    <option value="3"  >MP No Presente</option>
                </select>
            </td>
            <td colspan="2" >
                <select name="cbo_aus_pd_li" id="cbo_aus_pd_li"  <%=style_0%>  >
                    <option value="1"  >MP Presente</option>
                    <option value="2"  >MP Disminuido</option>
                    <option value="3"  >MP No Presente</option>
                </select>
            </td>
        </tr>






        <tr <%=datos%>>
            <td rowspan="2" style="border-top: 1px solid #000;" >Ruidos Agregados</td>
            <td colspan="5" style="border-top: 1px solid #000;" >

                <table>
                    <tr>
                        <td><input type="checkbox" name="chk_sibilancia" id="chk_sibilancia" value="ON" onchange="cambia_ruidos_agregados()"  /></td>
                        <td>Sibilancias</td>
                        <td><input type="checkbox" name="chk_roncus" id="chk_roncus" value="ON"  onchange="cambia_ruidos_agregados()"/></td>
                        <td>Roncus</td>
                        <td><input type="checkbox" name="chk_estertor_traqueal" id="chk_estertor_traqueal" value="ON" onchange="cambia_ruidos_agregados()" /></td>
                        <td>Estertor traqueal</td>
                        <td><input type="checkbox" name="chk_crepitacion_finas" id="chk_crepitacion_finas" value="ON" onchange="cambia_ruidos_agregados()" /></td>
                        <td>Crepitaciones finas</td>
                        <td><input type="checkbox" name="chk_crepitacion_gruesa" id="chk_crepitacion_gruesa" value="ON" onchange="cambia_ruidos_agregados()" /></td>
                        <td>Crepitaciones gruesas</td> 
                    </tr>

                </table> 


            </td>
        </tr>
        <tr id="tr_lobulo" <%=datos%> >

            <td colspan="2" >
                Pulmón izquierdo
            </td>
            <td colspan="3" >
                Pulmón derecho
            </td>
        </tr>
        <tr id="tr_lobulo_superior" >
            <td>Lóbulo Superior</td>
            <td colspan="2" >
                <input type="text" name="txt_derecho_lobulo_superior" id="txt_derecho_lobulo_superior" <%=style_2%> >
            </td>
            <td colspan="2" >
                <input type="text" name="txt_izquierdo_lobulo_superior" id="txt_izquierdo_lobulo_superior"  <%=style_2%> >
            </td>
        </tr>
        <tr id="tr_lobulo_medio" >
            <td>Lóbulo Medio</td>
            <td colspan="2" >

            </td>
            <td colspan="2" >
                <input type="text" name="txt_derecho_lobulo_medio" id="txt_derecho_lobulo_medio"  <%=style_2%> >
            </td>
        </tr>
        <tr id="tr_lobulo_inferior" >
            <td style="border-bottom: 1px solid #000;">Lóbulo Inferior</td>
            <td colspan="2" style="border-bottom: 1px solid #000;" > 
                <input type="text" name="txt_izquierdo_lobulo_inferior" id="txt_izquierdo_lobulo_inferior"  <%=style_2%> >
            </td>
            <td colspan="3" style="border-bottom: 1px solid #000;" >
                <input type="text" name="txt_derecho_lobulo_inferior" id="txt_derecho_lobulo_inferior"  <%=style_2%>  >
            </td>
        </tr>

        <tr>
            <td>Tos</td>
            <td colspan="0" >

                <table>
                    <tr>
                        <td>Presencia</td>
                        <td>
                            <select name="cbo_tos_presencia" id="cbo_tos_presencia"  <%=style_0%> >
                                <option value="-1" >Seleccione...</option>
                                <option value="1" >Presente</option>
                                <option value="2" >No presente</option>
                            </select>  
                        </td>
                        <td>Producción</td>
                        <td>
                            <select name="cbo_tos_produccion" id="cbo_tos_produccion"  <%=style_0%> onchange="cambia_tos()"  >
                                <option value="-1" >Seleccione...</option>
                                <option value="1" >Productiva</option>
                                <option value="2" >No productiva</option>
                            </select>  

                        </td>
                    </tr>

                </table>

            </td>
        </tr>
        <tr id="tr_secrecion" >
            <td>Secreciones</td>

            <td colspan="5" >

                <table>
                    <tr>
                        <td>Cantidad&nbsp;</td>
                        <td colspan="" >
                            <select name="cbo_secrecion_cantidad" id="cbo_secrecion_cantidad"  <%=style_0%> >
                                <option value="-1" >Selecione...</option>
                                <option value="1" >Abundantes</option>
                                <option value="2" >Moderadas</option>
                                <option value="3" >Bajas</option>
                            </select> 


                        </td>
                        <td>Coloración&nbsp;</td>
                        <td>
                            <select name="cbo_secrecion_color" id="cbo_secrecion_color"  <%=style_0%> >
                                <option value="-1" >Selecione...</option>
                                <option value="1" >Blanca</option>
                                <option value="2" >Verde</option>
                                <option value="3" >Amarilla</option>
                                <option value="4" >Café</option>
                            </select> 


                        </td>
                        <td>Tipo</td>
                        <td>
                            <select name="cbo_secrecion_tipo" id="cbo_secrecion_tipo" <%=style_0%> >
                                <option value="-1" >Selecione...</option>
                                <option value="1" >Serosas</option>
                                <option value="2" >Mucosas</option>
                                <option value="3" >Mucopurulentas</option>
                                <option value="4" >Infección</option>
                            </select> 

                        </td>
                    </tr>
                </table>

            </td>


        </tr>
        <tr>
            <td colspan="6" >   <input class="btn btn-primary" type="submit" value="GRABAR" name="btn2_grabar"   /></td>
        </tr>


    </table>


</body>

<script>

    function valida_respiratoria() {

        if (confirm("CONFIRMACION ! Desea ingresar este registro ? \n \n ")) {
        } else {
            return false;
        }
    }


    function inicio() {


        $('#td_oxigeno').hide();
        $('#td_oxigeno1').hide();
        $('#td_via_urinaria_sonda').hide();
        $('#td_via_urinaria_sonda1').hide();

        $('#tr_no_vigil').hide();
        $('#tr_vigil').hide();

        $('#td_alteracion_lenguaje').hide();
        $('#td_alteracion_lenguaje1').hide();
        $('#td_cianosis').hide();
        $('#td_cianosis1').hide();
        $('#td_elasticidad').hide();
        $('#td_elasticidad1').hide();
        $('#td_uso_musculatura').hide();
        $('#td_uso_musculatura1').hide();
        $('#td_uso_musculatura2').hide();

        $('#tr_secrecion').hide();

        //  $('#tr_pulmon').hide();
        //   $('#tr_pulmon1').hide();

        /*$('#tr_lobulo').hide();
         $('#tr_lobulo_superior').hide();
         $('#tr_lobulo_medio').hide();
         $('#tr_lobulo_inferior').hide();
         */

    }

    function cambia_oxigeno() {

        if ($('#cbo_oxigeno').val() == 1) {
            $('#td_oxigeno').show();
            $('#td_oxigeno1').show();

        } else {
            $('#td_oxigeno').hide();
            $('#td_oxigeno1').hide();
        }

    }


    function cambia_via_urinaria() {

        if ($('#cbo_via_urinaria').val() == 1) {
            $('#td_via_urinaria_sonda').show();
            $('#td_via_urinaria_sonda1').show();
        } else {
            $('#td_via_urinaria_sonda').hide();
            $('#td_via_urinaria_sonda1').hide();
        }

    }

    function cambia_estado() {

        if ($('#cbo_vigil').val() == 1) {
            $('#tr_vigil').show();
            $('#tr_alteracion_lenguaje').show();
            $('#tr_no_vigil').hide();

        } else if ($('#cbo_vigil').val() == 2) {
            $('#tr_vigil').hide();
            $('#tr_alteracion_lenguaje').hide();
            $('#tr_no_vigil').show();
        } else {
            $('#tr_vigil').hide();
            $('#tr_alteracion_lenguaje').hide();
            $('#tr_no_vigil').hide();
        }

    }


    function cambia_coloracion_piel() {

        if ($('#cbo_coloracion').val() == 2) {
            $('#td_cianosis').show();
            $('#td_cianosis1').show();
        } else {
            $('#td_cianosis').hide();
            $('#td_cianosis1').hide();
        }
    }

    function cambia_dificultad_respiratoria() {

        if ($('#cbo_dificultad_respiratoria').val() == 1) {
            $('#td_uso_musculatura').show();
            $('#td_uso_musculatura1').show();

        } else {

            $('#td_uso_musculatura').hide();
            $('#td_uso_musculatura1').hide();
            $('#td_uso_musculatura2').hide();
        }
    }

    function cambia_uso_musculatura() {

        if ($('#cbo_musculatura_accesoria').val() == 1) {
            $('#td_uso_musculatura2').show();
        } else {
            $('#td_uso_musculatura2').hide();
        }

    }

    function cambia_elasticidad() {

        if ($('#cbo_elasticidad').val() == 2) {
            $('#td_elasticidad').show();
            $('#td_elasticidad1').show();
        } else {
            $('#td_elasticidad').hide();
            $('#td_elasticidad1').hide();
        }

    }

    function cambia_auscultacion() {
        /*  if ($('#cbo_auscultacion').val() == 2 || $('#cbo_auscultacion').val() == 3) {
         $('#tr_pulmon').show();
         $('#tr_pulmon1').show();
         } else {
         $('#tr_pulmon').hide();
         $('#tr_pulmon1').hide();
         }*/
    }

    function cambia_tos() {
        if ($('#cbo_tos_produccion').val() == 1) {
            $('#tr_secrecion').show();
        } else {
            $('#tr_secrecion').hide();

        }


    }



</script>

<jsp:include page="../Footer.jsp" />

<%
    }
%>
