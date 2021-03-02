<%-- 
    Document   : retroalimentacion_carga
    Created on : 03-feb-2014, 16:14:47
    Author     : Silvio
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%

String obtiene_rut=request.getParameter("user");

%>

  <fieldset>
        <legend>Ingreso del Paciente:<%=obtiene_rut%></legend>
        <table  style="FONT-FAMILY: Verdana; FONT-SIZE: 13px;" BORDER="0" width="720">
            <tr><td>Nombre:</td>
                <td><input style=" background-color:  white  "  type="text" size="25" name="nombres" id="nombres" value=""  ></td>
                <td>Apellido  P.</td><td><input  style=" background-color:  white  "  name="apellidop" id="apellidop" type="text" size="25" value=""  ></td>
                <td>Apellido M.</td><td><input style=" background-color:  white  "  name="apellidom" id="apellidom" type="text" size="25" value="" ></td>
            <tr>
                <td>Rut:</td>
                <td>
                    <input style=" background-color:  white  "  type="text" name="rut" id="rut" value=""  >
                </td>
                <td>Fecha Nac:</td>
                <td>
                    <input style=" background-color:  white  "  name="fecha_nac" id="fecha_nac" type="text" size="20" value=""  >
                    <%
                                    /*
                                    <img src="Imagenes/calender.png" id="f_trigger_a" style="cursor:pointer" onclick="return showCalendar('fecha_nac', '%Y-%m-%d %H:%M', '24', true);">
                                     */
                    %>
                </td>
                <td>Sexo:</td>
                <td>

                    M<input type="radio" name="rbt_sexo" id="rbt_sexo0"  value="0" checked="checked"    />
                    F<input type="radio" name="rbt_sexo" id="rbt_sexo1"  value="1"  />
                </td>
            </tr>
            <tr>
                <td>Dirección:</td>
                <td colspan="3">
                    <input style=" background-color:  white  "  type="text" size="60" onkeyup="for(i=0;i<100;i++){if(this.value[i]=='#'){alert('No use Caracteres como # u Otros!!!');this.value=this.value.replace('#','N° ');}}" id="direccion" name="direccion" value="">
                </td>
                <td>Comuna:</td>
                <td>
                    <select style=" background-color:  white  "  id="id_comuna" name="id_comuna" >
                        <option value="-2" >Seleccione...</option>

                    </select>
                </td>
            </tr>
            <tr>
                <td>Teléfono:</td>
                <td>
                    <input style=" background-color:  white  "  type="text" id="telefono1" name="telefono1"  value="">
                </td>
                <td>Celular: 09-</td>
                <td><input style=" background-color:  white  "  type="text" id="telefono2" name="telefono2"  value=""></td>
            </tr>

            <tr>
                <td>Mail</td>
                <td colspan="3" >
                    <input style=" background-color:  white  "   style=" width: 400px " type="text" id="txt_mail" name="txt_mail"  value="">
                </td>
            </tr>

            <tr>
                <td>Pertenecia:</td>
                <td>
                    <select   name="id_consultorio_pertenencia" id="id_consultorio_pertenencia">
                        <option value="-2" >Seleccione...</option>

                    </select>
                </td>
                <td>Tipo Cita:</td>
                <td>
                    <select  name="id_pueblo" id="id_pueblo">
                        <option value="-2" >Seleccione...</option>
                    </select>
                </td>
                <td>Programa:
                </td>
                <td>
                    <select name="paciente_nacion" id="paciente_nacion" style=" width:150px;  " >
 <option value="-2">Seleccione...</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Destino:</td>
                <td>
                    <select id='paciente_prevision' name='paciente_prevision'>
 <option value="-2">Seleccione...</option>
                    </select>
                </td>
                <td colspan="2"></td>

                <td colspan="1" >

                </td><td>
                    <input type="hidden" name="paciente_programa" id="paciente_programa" value="0" >
                <td>
            </tr>
            <tr>
                <td>GES:</td>
                <td>
                      <select name="id_derivado" id="id_derivado">
                        <option value="-2">Seleccione...</option>
                        <option value="-2">SI</option>
                        <option value="-2">NO</option>

                    </select>
                </td>
                <td>R.S.P.:</td>
                <td>
                    <select name="id_derivado" id="id_derivado">
                        <option value="-2">Seleccione...</option>

                    </select>
                </td>
                <td>A.T.E.P.:</td>
                <td>
                    <select  name="id_cama" id="id_cama">
                        <option value="-2">Seleccione...</option>

                    </select>
                </td>
            </tr>

        </table>


        <fieldset class="buttons">
            <br><br>
            <input class="btn btn-primary" type="submit" value="GUARDAR DATOS" name="btn_guarda_datos" />


            <br><br>
        </fieldset>
    </fieldset>