<%-- 
    Document   : constancia_carga
    Created on : 05-feb-2014, 17:35:31
    Author     : Silvio
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%

            String obtiene_rut = request.getParameter("user");

%>
<fieldset style=" border-color:  black " >
    <legend>DATOS DEL PRESTADOR</legend>

    <table  style="FONT-FAMILY: Verdana; FONT-SIZE: 13px;" BORDER="0" width="720">
        <tr><td>Institución:</td>
            <td><input style=" background-color:  white  "  type="text" size="25" name="nombres" id="nombres" value=""  ></td>
        </tr>
        <tr>
            <td>Dirección:</td>
            <td>
                <input style=" background-color:  white  "  type="text" name="rut" id="rut" value=""  >
            </td>
            <td>Ciudad:</td>
            <td>
                <input style=" background-color:  white  "  name="fecha_nac" id="fecha_nac" type="text" size="20" value=""  >

            </td>
        </tr>
        <tr>
            <td>Nombre persona que notifica:</td>
            <td><input style=" background-color:  white  "  name="fecha_nac" id="fecha_nac" type="text" size="20" value=""  >
            </td>
        </tr>
         <tr>
            <td>Rut:</td>
            <td><input style=" background-color:  white  "  name="fecha_nac" id="fecha_nac" type="text" size="20" value=""  >
            </td>
        </tr>
    </table>

</fieldset>
<fieldset style=" border-color:  black " >
    <legend>DATOS DEL PACIENTE:<%=obtiene_rut%></legend>
    <table  style="FONT-FAMILY: Verdana; FONT-SIZE: 13px;" BORDER="0" width="720">
        <tr><td>Nombre:</td>
            <td><input style=" background-color:  white  "  type="text" size="25" name="nombres" id="nombres" value=""  ></td>
            <td>Apellido  P.</td><td><input  style=" background-color:  white  "  name="apellidop" id="apellidop" type="text" size="25" value=""  ></td>
            <td>Apellido M.</td><td><input style=" background-color:  white  "  name="apellidom" id="apellidom" type="text" size="25" value="" ></td>
        </tr>
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




    </table>
</fieldset>


<fieldset style=" border-color:  black " >
    <legend>INFORMACION MEDICA:</legend>
    <TABLE>
        <tr>
            <td>DIAGNOSTICO GES:</td>
            <td colspan="4" >
                <select   name="id_consultorio_pertenencia" id="id_consultorio_pertenencia" style=" width: 300px " >
                    <option value="-2" >Seleccione...</option>
                    <option value="-2" >COLE</option>
                    <option value="-2" >OTRO</option>

                </select>
                <br>
            </td>

        </tr>
        <tr>
            <td>Confirmacion diagnóstica:</td>
            <td>
                <input type="checkbox" name="" value="" />
            </td>
            <td colspan="">
                En tratamiento:
            </td>

            <td colspan="1" >
                <input type="checkbox" name="" value="" />
            </td>


        </tr>
    </TABLE>
</fieldset>


<br><br>
<input class="btn btn-primary" type="submit" value="GUARDAR DATOS" name="btn_guarda_datos" />



