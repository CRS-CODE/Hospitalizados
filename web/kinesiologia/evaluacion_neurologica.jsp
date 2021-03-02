<%-- 
Document   : evaluacion_neurologica
Created on : 14-ago-2014, 15:06:43
Author     : Informatica
--%>
<%@page import="CapaDato.cEvaNeurologia"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cDiagnostico"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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

        String titulo = " style=' background-color: #4169E1 ; color: white '  ";
        String datos = " style=' background-color: #87CEFA ; color: black '  ";

        String wii_txt = " style='width: 400px ' ";
        String wii_txt2 = " style='width: 370px ' ";
        String wii_cbo = " style='width: 405px ' ";

        //int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
        // int id_duo = 11507;
        int id_duo = Integer.parseInt(request.getParameter("txt_duo"));

        cDuo duo = neg.obtiene_duo(id_duo);

        ArrayList lista_lesion = neg.lista_lesion();

%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="../Header.jsp" />

<script>

    function oculta_lesion() {
        if ($('#cbo2_lesion_evaluada').val() == 1) {
            $('#div_medular').show();
            $('#div_otro').hide();
            $('#tr_accidente_cerebro_vascular').hide();
        } else if ($('#cbo2_lesion_evaluada').val() == -1) {
            $('#div_medular').hide();
            $('#div_otro').hide();
        } else {
            $('#div_medular').hide();
            $('#div_otro').show();
            if ($('#cbo2_lesion_evaluada').val() == 2) {
                $('#tr_accidente_cerebro_vascular').show();
            } else {
                $('#tr_accidente_cerebro_vascular').hide();
            }

        }
    }

    function oculta_txt(id) {

        if ($('#cbo2_' + id).val() == 1) {
            $('#txt2_' + id + '_adicional').hide();
        } else if ($('#cbo2_' + id).val() == -1) {
            $('#txt2_' + id + '_adicional').hide();
        } else {
            $('#txt2_' + id + '_adicional').show();
        }
        //$('#txt2_' + id + '_adicional').val('modified')
    }

    function inicio_neuro() {
        $('#div_medular').hide();
        $('#div_otro').hide();

        oculta_txt('propiocepcion');
        oculta_txt('trofismo');
    }

    function valida_neuro() {

        if ($('#cbo2_lesion_evaluada').val() == 1) {
//evaluo si es lesion medular
            if ($('#cbo2_lesion_medular').val() == -1) {
                alert('Debe seleccionar Lesióm ');
                return false;
            } else if ($('#cbo2_asia').val() == -1) {
                alert('Debe seleccionar ASIA');
                return false;
            } else if ($('#cbo2_tipo_lesion').val() == -1) {
                alert('Debe seleccionar Tipo Lesión');
                return false;
            } else if ($('#cbo2_ashworth_medular').val() == -1) {
                alert('Debe seleccionar Ashworth Medular');
                return false;
            }



        } else {
//evaluo si es otro tipo de lesion
            /*  if ($('#cbo2_lesion_otro').val() == -1) {
             alert('Debe seleccionar Lesión ');
             return false;
             } else*/
            if ($('#cbo2_ashworth_otro').val() == -1) {
                alert('Debe seleccionar Ashworth');
                return false;
            } else if ($('#cbo2_trofismo').val() == -1) {
                alert('Debe seleccionar Trofismo ');
                return false;
            } else if ($('#cbo2_reflejo_osteotendineo').val() == -1) {
                alert('Debe seleccionar Reflejo Osteotendineo');
                return false;
            } else if ($('#cbo2_propiocepcion').val() == -1) {
                alert('Debe seleccionar Propiocepción ');
                return false;
            } else if ($('#cbo2_reaccion_equilibrio').val() == -1) {
                alert('Debe seleccionar Reacción Equilibrio ');
                return false;
            } else if ($('#cbo2_reaccion_enderezamiento').val() == -1) {
                alert('Debe seleccionar Reacción Enderezamiento ');
                return false;
            } else if ($('#cbo2_reaccion_apoyo').val() == -1) {
                alert('Debe seleccionar  Reacción Apoyo');
                return false;
            }

        }

        if (confirm("CONFIRMACION ! Desea ingresar este registro ? \n \n ")) {
        } else {
            return false;
        }

    }


</script>

<legend>LISTADO DE CAMILLAS</legend>


<body onload="inicio_neuro()" >

    <form name="form_1" method="POST" action="../ingreso_caso" onsubmit="return valida_neuro()"  >

        <input type="hidden" name="txt_modo" value="2" >
        <input type="hidden" name="txt_duo" value="<%=duo.getId_duo()%>" >
        <input type="hidden" name="txt_usuario"  id="txt_usuario"  value="<%=session1.getAttribute("usuario_rut")%>" >


        <table>
            <tr>
                <td><h3>Seleccione tipo de lesión</h3></td>
                <td>
                    <select name="cbo2_lesion_evaluada" id="cbo2_lesion_evaluada" onchange="oculta_lesion()"  >
                        <option value="-1" >Seleccione...</option>
                        <%
                            Iterator it1 = lista_lesion.iterator();
                            while (it1.hasNext()) {
                                cDiagnostico dia1 = (cDiagnostico) it1.next();
                                if (dia1.getTipo_diagnostico() == 1) {
                                    out.write("<option value='" + dia1.getId_diagnostico() + "' >" + dia1.getDescripcion_diagnostico() + "</option>");
                                }
                            }

                        %>
                    </select>
                </td>
            </tr>
        </table>
        <div id="div_medular" >
            <table id="tbl_lesion_medular" border="0"   >
                <tr>
                    <td <%=titulo%> >Lesión</td>
                    <td>
                        <select name="cbo2_lesion_medular" id="cbo2_lesion_medular"  <%=wii_cbo%> >
                            <option value="-1" >Seleccione...</option>
                            <%
                                Iterator it2 = lista_lesion.iterator();
                                while (it2.hasNext()) {
                                    cDiagnostico dia2 = (cDiagnostico) it2.next();
                                    if (dia2.getTipo_diagnostico() == 2) {
                                        out.write("<option value='" + dia2.getId_diagnostico() + "' >" + dia2.getDescripcion_diagnostico() + "</option>");
                                    }
                                }

                            %>
                        </select> 
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> >ASIA</td>
                    <td>
                        <SELECT name="cbo2_asia" id="cbo2_asia"  <%=wii_cbo%> >
                            <option value="-1" >Seleccione...</option>
                            <option value="A" >A</option>
                            <option value="B" >B</option>
                            <option value="C" >C</option>
                            <option value="D" >D</option>
                            <option value="E" >E</option>
                        </SELECT>
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> >Tipo de Lesión</td>
                    <td>
                        <SELECT name="cbo2_tipo_lesion"   id="cbo2_tipo_lesion"   <%=wii_cbo%>>
                            <option value="-1" >Seleccione...</option>
                            <%
                                Iterator it3 = lista_lesion.iterator();
                                while (it3.hasNext()) {
                                    cDiagnostico dia3 = (cDiagnostico) it3.next();
                                    if (dia3.getTipo_diagnostico() == 3) {
                                        out.write("<option value='" + dia3.getId_diagnostico() + "' >" + dia3.getDescripcion_diagnostico() + "</option>");
                                    }
                                }

                            %>
                        </SELECT>
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> >Ashworth Modificado</td>
                    <td>
                        <select name="cbo2_ashworth_medular" id="cbo2_ashworth_medular" <%=wii_cbo%>>
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >1</option>
                            <option value="1+" >1+</option>
                            <option value="2" >2</option>
                            <option value="3" >3</option>
                            <option value="4" >4</option>
                        </select> 
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> >Reflejos Osteotendíneos</td>
                    <td>
                        <input type="text" name="txt2_reflejo_osteorendineo"  id="txt2_reflejo_osteorendineo" value="" <%=wii_txt%> />
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Evaluación Sensitiva</td>
                    <td>
                        <input type="text" name="txt2_evaluacion_sensitiva" id="txt2_evaluacion_sensitiva" value="" <%=wii_txt%> />
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Sensibilidad Anal Profunda</td>
                    <td>
                        <input type="text" name="txt2_sensibilidad" id="txt2_sensibilidad" value="" <%=wii_txt%> />
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Motor Index Score</td>
                    <td>
                        <input type="text" name="txt2_motor_index" id="txt2_motor_index" value="" <%=wii_txt%> />
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Contracción Esfinteránea y Anal</td>
                    <td>
                        <input type="text" name="txt2_contraccion" id="txt2_contraccion" value="" <%=wii_txt%> />
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Transferencias</td>
                    <td>
                        <table>
                            <tr>
                                <td <%=datos%>>Giros</td>  
                                <td> SI<input type="radio" name="rbt2_giro" id="rbt2_giro_1"  value="1" /></td> 
                                <td> NO<input type="radio" name="rbt2_giro" id="rbt2_giro_0" value="0" /></td>
                                <td> <input type="txt" name="txt2_giro" id="txt2_giro" ></td>
                            </tr>
                            <tr>
                                <td <%=datos%>>Supino Decúbito Lateral</td>  
                                <td> SI<input type="radio" name="rbt2_supino_decubito_lateral" id="rbt2_supino_decubito_lateral_0" value="1" /></td> 
                                <td> NO<input type="radio" name="rbt2_supino_decubito_lateral" id="rbt2_supino_decubito_lateral_1" value="0" /></td>
                                <td><input type="txt" name="txt2_supino_decubito_lateral" id="txt2_supino_decubito_lateral" ></td>
                            </tr>
                            <tr>
                                <td <%=datos%>>Supino Prono</td>  
                                <td> SI<input type="radio" name="rbt2_supino_prono" id="rbt2_supino_prono_0" value="1" /></td> 
                                <td> NO<input type="radio" name="rbt2_supino_prono" id="rbt2_supino_prono_1" value="0" /></td>
                                <td><input type="txt" name="txt2_supino_prono" id="txt2_supino_prono" ></td>
                            </tr>
                            <tr>
                                <td <%=datos%>>Decúbito Lateral Sedente</td>  
                                <td> SI<input type="radio" name="rbt2_decubito_lateral_sedente" id="rbt2_decubito_lateral_sedente_0" value="1" /></td> 
                                <td> NO<input type="radio" name="rbt2_decubito_lateral_sedente" id="rbt2_decubito_lateral_sedente_1" value="0" /></td>
                                <td><input type="txt" name="txt2_decubito_lateral_sedente" id="txt2_decubito_lateral_sedente" ></td>
                            </tr>
                            <tr>
                                <td <%=datos%>>Supino Sedente</td>  
                                <td> SI<input type="radio" name="rbt2_supino_sedente" id="rbt2_supino_sedente_1" value="1" /></td> 
                                <td> NO<input type="radio" name="rbt2_supino_sedente" id="rbt2_supino_sedente_0" value="0" /></td>
                                <td><input type="txt" name="txt2_supino_sedente" id="txt2_supino_sedente" ></td>
                            </tr>
                            <tr>
                                <td <%=datos%>>Sedente Bípedo</td>  
                                <td> SI<input type="radio" name="rbt2_sedente_bipedo"  id="rbt2_sedente_bipedo_1" value="1" /></td> 
                                <td> NO<input type="radio" name="rbt2_sedente_bipedo"  id="rbt2_sedente_bipedo_0" value="0" /></td>
                                <td><input type="txt" name="txt2_sedente_bipedo" id="txt2_sedente_bipedo" ></td>
                            </tr>
                        </table>          
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Silla de Ruedas</td>
                    <td> <input type="text" name="txt2_silla_rueda" id="txt2_silla_rueda" value="" <%=wii_txt%> />   </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Marcha</td>
                    <td> <input type="text" name="txt2_marcha_medular" id="txt2_marcha_medular" value="" <%=wii_txt%> />   </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Nivel Sentivo Lesión</td>
                    <td> <input type="text" name="txt2_nivel_sentivo" id="txt2_nivel_sentivo" value="" <%=wii_txt%> />   </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Nivel Motor Lesión</td>
                    <td> <input type="text" name="txt2_nivel_motor" id="txt2_nivel_motor" value="" <%=wii_txt%> />   </td>
                </tr>
                <tr>
                    <td <%=titulo%>>Nivel Neurológico Lesión</td>
                    <td><input type="text" name="txt2_nivel_neurologico" id="txt2_nivel_neurologico" value="" <%=wii_txt%> />   </td>
                </tr>
                <td colspan="2" >   <input class="btn btn-primary" type="submit" value="GRABAR" name="btn2_grabar"   /></td>
            </table>
        </div>

        <div id="div_otro" >

            <table id="tbl_otro" border="0"  >
                <tr id="tr_accidente_cerebro_vascular" >
                    <td <%=titulo%> colspan="2">Lesión</td>
                    <td>
                        <select name="cbo2_lesion_otro" id="cbo2_lesion_otro" <%=wii_cbo%>  >
                            <option value="-1" >Seleccione...</option>
                            <%
                                Iterator it12 = lista_lesion.iterator();
                                while (it12.hasNext()) {
                                    cDiagnostico dia12 = (cDiagnostico) it12.next();
                                    if (dia12.getTipo_diagnostico() == 12) {
                                        out.write("<option value='" + dia12.getId_diagnostico() + "' >" + dia12.getDescripcion_diagnostico() + "</option>");
                                    }
                                }

                            %>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> colspan="2">2° Motoneurona</td>
                    <td>
                        <input type="text" name="txt2_motoneurona" id="txt2_motoneurona" value="" <%=wii_txt%> />   
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> colspan="2" >Extrapiramidal</td>
                    <td>

                        <input type="text" name="txt2_extrapiramidal" id="txt2_extrapiramidal" value="" <%=wii_txt%> />   
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%>  rowspan="4" >Antecedentes <BR>Mórbidos</td>
                    <td <%=datos%>>    
                        HTA 
                    </td>
                    <td>    
                        <input type="checkbox" name="chk2_hta" id="chk2_hta" value="1" />  <input type="text" name="txt2_hta" id="txt2_hta"  value="" <%=wii_txt2%> />   
                    </td>
                </tr>
                <tr>
                    <td <%=datos%> >         DM 
                    </td>
                    <td>      
                        <input type="checkbox" name="chk2_dm" id="chk2_dm" value="1" />     <input type="text" name="txt2_dm" id="txt2_dm" value="" <%=wii_txt2%> />       
                    </td>
                </tr>   
                <tr> 
                    <td <%=datos%>>  Dislipidemia  
                    </td>
                    <td>  
                        <input type="checkbox" name="chk2_dislipidemia" id="chk2_dislipidemia" value="1" />  <input type="text" name="txt2_dislipidemia" id="txt2_dislipidemia" value="" <%=wii_txt2%> />   
                    </td>
                </tr>
                <tr>
                    <td <%=datos%>>    
                        Tabaquismo 
                    </td>
                    <td>    
                        <input type="checkbox" name="chk2_tabaquismo" id="chk2_tabaquismo" value="1" />  <input type="text" name="txt2_tabaquismo" id="txt2_tabaquismo" value="" <%=wii_txt2%> />   
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> colspan="2">Postura (todos los planos posibles)</td>
                    <td>
                        <input type="text" name="txt2_postura" id="txt2_postura" value="" <%=wii_txt%> />    
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Ashworth Modificado</td>
                    <td>
                        <select name="cbo2_ashworth_otro" id="cbo2_ashworth_otro"  <%=wii_cbo%> >
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >1</option>
                            <option value="1+" >1+</option>
                            <option value="2" >2</option>
                            <option value="3" >3</option>
                            <option value="4" >4</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Fuerza</td>
                    <td>
                        <input type="text" name="txt2_fuerza" id="txt2_fuerza" value="" <%=wii_txt%> />    
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2"> Tono Muscular</td>
                    <td>

                        <input type="text" name="txt2_tono_muscular" id="txt2_tono_muscular"  value="" <%=wii_txt%> />    
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> colspan="2">Trofismo</td>
                    <td>
                        <select name="cbo2_trofismo" id="cbo2_trofismo" <%=wii_cbo%> onchange="oculta_txt('trofismo')" >
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Normal</option>
                            <option value="0" >Atrofia</option>
                        </select> 
                        <input type="text" name="txt2_trofismo_adicional" id="txt2_trofismo_adicional" value="" <%=wii_txt%> />    
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> rowspan="2" >Movilidad Activa</td>
                    <td <%=datos%>> EESS</td>
                    <td>
                        <input type="text" name="txt2_eess" id="txt2_eess" value="" <%=wii_txt%> />
                    </td>
                </tr>
                <tr>
                    <td <%=datos%>>EEII</td>
                    <td>
                        <input type="text" name="txt2_eeii" id="txt2_eeii" value="" <%=wii_txt%> />  
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> colspan="2">Reflejos Osteotendíneos</td>
                    <td>
                        <select name="cbo2_reflejo_osteotendineo" id="cbo2_reflejo_osteotendineo"  <%=wii_cbo%>>
                            <option value="-1" >Seleccione...</option>
                            <%
                                Iterator it14 = lista_lesion.iterator();
                                while (it14.hasNext()) {
                                    cDiagnostico dia14 = (cDiagnostico) it14.next();
                                    if (dia14.getTipo_diagnostico() == 14) {
                                        out.write("<option value='" + dia14.getId_diagnostico() + "' >" + dia14.getDescripcion_diagnostico() + "</option>");
                                    }
                                }

                            %>
                        </select> 
                    </td>
                </tr>

                <tr>
                    <td <%=titulo%> colspan="2">Propiocepción</td>
                    <td>
                        <select name="cbo2_propiocepcion" id="cbo2_propiocepcion"  <%=wii_cbo%>  onchange="oculta_txt('propiocepcion')" >
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Normal</option>
                            <option value="0" >Alterada</option>
                        </select>
                        <input type="text" name="txt2_propiocepcion_adicional" id="txt2_propiocepcion_adicional"  value="" <%=wii_txt%> />  
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Transiciones</td>
                    <td>
                        <input type="text" name="txt2_transicion" id="txt2_transicion"  value="" <%=wii_txt%> />  
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Reacciones de Equilibrio</td>
                    <td>
                        <select name="cbo2_reaccion_equilibrio" id="cbo2_reaccion_equilibrio"  <%=wii_cbo%>>
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Presente</option>
                            <option value="0" >No presente</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Reacciones de Enderezamiento</td>
                    <td>
                        <select name="cbo2_reaccion_enderezamiento" id="cbo2_reaccion_enderezamiento" <%=wii_cbo%>>
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Presente</option>
                            <option value="0" >No presente</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Reacciones de Apoyo</td>
                    <td>
                        <select name="cbo2_reaccion_apoyo" id="cbo2_reaccion_apoyo" <%=wii_cbo%> >
                            <option value="-1" >Seleccione...</option>
                            <option value="1" >Presente</option>
                            <option value="0" >No presente</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%> colspan="2">Marcha</td>
                    <td>
                        <input type="text" name="txt2_marcha_otro" id="txt2_marcha_otro"  value="" <%=wii_txt%> /> 
                    </td>
                </tr>
                <tr>
                    <td <%=titulo%>  colspan="2">Test Especiales</td>
                    <td>
                        <input type="text" name="txt2_test_especial" id="txt2_test_especial"  value="" <%=wii_txt%> />  
                    </td>
                </tr>
                <tr>
                    <td colspan="2" >   <input class="btn btn-primary" type="submit" value="GRABAR" name="btn2_grabar"   /></td>
                </tr>
            </table>
        </div>


    </form>





    <jsp:include page="../Footer.jsp" />

    <%        }
    %>
