<%-- 
    Document   : uh_registro_carga
    Created on : 10-jun-2012, 12:06:42
    Author     : Dis
--%>

<%@page import="CapaDato.cUsuario"%>
<%@page import="CapaDato.cPerfil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%
    NegocioQ neg = new NegocioQ();
    ArrayList lista_perfil = neg.lista_perfil();
    Iterator it = lista_perfil.iterator();
    cPerfil per = new cPerfil();

    String a_nombres = "";
    String a_apellidop = "";
    String a_apellidom = "";
    String a_rut = request.getParameter("user").toUpperCase();

    cUsuario usu = neg.valida_Usuario_sinPass(a_rut);

    boolean sw_tiene_usuario = false;

    if (usu.getRut_usuario().trim().length() > 0) {

        sw_tiene_usuario = true;
        a_nombres = usu.getNombre_usuario();
        a_apellidop = usu.getApellidop_usuario();
        a_apellidom = usu.getApellidom_usuario();
    } else {
        sw_tiene_usuario = false;
        usu = neg.obtiene_funcionario_rrhh(a_rut);
        a_nombres = usu.getNombre_usuario();
        a_apellidop = usu.getApellidop_usuario();
        a_apellidom = usu.getApellidom_usuario();
    }
%>
<form action="<% out.print(neg.getLocal());%>ingreso_uh" method="post"  name="index" onsubmit="">
    <input type='hidden' name='modo' value='96'/>
    <input type='hidden' name='txt_rut' value='<% out.write(usu.getRut_usuario()); %>'/>
    <input class="btn btn-primary" type="submit" value="Resetear Clave" name="btn_modificar" />
</form>
<form action="<% out.print(neg.getLocal());%>ingreso_uh" method="post"  name="index" onsubmit="return valida_form()">
    <input type="hidden" name="txt_rut" value="<%=a_rut%>" />

    <table border="0">
        <%
            if (sw_tiene_usuario == true) {
                out.write("<tr><td colspan='2' > <h4>Este Funcionario ya tiene un usuario</h4> </td></tr>");
            }

            if (usu.getRut_usuario().trim().length() == 0) {
                out.write("<tr><td colspan='2' > <h4>Este Funcionario no esta registrado aun en rrhh, si desea continuar ingrese todos los campos requeridos</h4> </td></tr>");
            }

        %>
        <tr>
            <td>Nombre:</td><td><input  name="txt_nombres" id="txt_nombres" size="40" autocomplete="off" type="text" value="<%=a_nombres%>"></td>
            <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
            <td rowspan="6"><img  width="150"src="Imagenes/Usuarios.bmp"></td>
        </tr>
        <tr>
            <td>Apellido Paterno:</td><td><input name="txt_apellidop" id="txt_apellidop" size="40" autocomplete="off" type="text" value="<%=a_apellidop%>"></td>
        </tr>
        <tr>
            <td>Apellido Materno:</td><td><input name="txt_apellidom" id="txt_apellidom" size="40" autocomplete="off" type="text" value="<%=a_apellidom%>"></td>
        </tr>
        <tr>
            <td>Descripci&oacute;n Perfil:</td>
            <td>
                <select name="cbo_perfil" id="cbo_perfil" style=" width: 200px " >
                    <%
                        while (it.hasNext()) {
                            per = (cPerfil) it.next();
                            if (per.getId_perfil() == usu.getPerfil_usuario()) {
                                out.write("<option value=" + per.getId_perfil() + " selected='selected'  >" + per.getDescripcion_perfil() + "</option>");

                            } else {
                                out.write("<option value=" + per.getId_perfil() + " >" + per.getDescripcion_perfil() + "</option>");

                            }

                        }
                    %>
                </select>
            </td>
        </tr>

        <tr>
            <td>Estado </td>
            <td>
                <select name="cbo_estado" id="cbo_estado"  style=" width: 200px " >
                    <%
                        if (sw_tiene_usuario == false) {
                            out.write("<option value='1' selected='selected' >Habilitado</option>");
                        } else if (usu.getEstado_usuario() == 0) {
                            out.write("<option value='0' selected='selected' >Deshabilitado</option>");
                            out.write("<option value='1'  >Habilitado</option>");
                        } else {
                            out.write("<option value='0'  >Deshabilitado</option>");
                            out.write("<option value='1' selected='selected' >Habilitado</option>");
                        }


                    %>
                </select>
            </td>
        </tr>


        <tr><td>&nbsp;</td>
            <td>
                <%                    if (sw_tiene_usuario == true) {
                        // boton para resetar clave
                        out.write("<input type='hidden' name='modo' value='97'/>");
                        out.write("<input class=\"btn btn-primary\" type=\"submit\" value=\"Modificar\" name=\"btn_grabar\" />");
                    } else {
                        out.write("<input type='hidden' name='modo' value='99'/>");
                        out.write("<input class=\"btn btn-primary\" type=\"submit\" value=\"Registrar\" name=\"btn_Registrar\" />");
                    }


                %>


                <%  // <input id="modificarUsuario" value="REGISTRAR" type="button"   style="cursor:pointer" >%>

            </td>
        </tr>

    </table>
</form>