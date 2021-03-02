<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaDato.cCatRiesgoDependencia"%>
<%@ page contentType="text/html; charset=iso-8859-1" 
         language="java"
         import="java.sql.*"
         import="java.util.Iterator"
         import="java.util.Properties"
         import="java.util.Date"
         import="java.util.Locale"
         %>



<%
    NegocioQ neg = new NegocioQ();
    cCatRiesgoDependencia cat = new cCatRiesgoDependencia();
    ArrayList lista_cat_riesgo = neg.lista_riesgo_dependendencia();
    Iterator icat = lista_cat_riesgo.iterator();
%>


<LINK href="css/style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen" href="themes/supreme/grid.css" />
<script type="text/javascript" src="ObjetoAjax.js"></script>


<table border="1" cellpadding="0" cellspacing="0">
    <input value="0" name="control" id="control" type="hidden">
    <thead>
        <tr>
            <td colspan="7" class="DEPENDENCIA" style="text-transform: uppercase;">Dependencia</td><td class="RIESGO" style="text-transform: uppercase;" colspan="9">Riesgo</td>
            <td class="CRD_TOTAL" rowspan="2">Ptje. Total &#8721;</td>
            <td class="CRD_TOTAL" rowspan="2">&nbsp;&nbsp;C&nbsp;&nbsp;</td>
        </tr>
        <tr> <%
            int cont_dep = 0;
            int cont_rie = 0;
            while (icat.hasNext()) {
                cat = (cCatRiesgoDependencia) icat.next();%>
            <%
                if (cat.getTipo_ambito_crd() == 1) {
                    cont_dep++;%>
            <td class="DEPENDENCIA" title="<%out.print(cat.getAbre_ambito_crd());%>">

                <label title="<%out.print(cat.getAbre_ambito_crd());%>">Dep. <%=cont_dep%></label></td>
                <%} else {
                    cont_rie++;%>
            <td class="RIESGO" title="<%out.print(cat.getAbre_ambito_crd());%>">

                <label title="<%out.print(cat.getAbre_ambito_crd());%>">Riesgo <%=cont_rie%></label></td>
                <%}
                %>

            <%if (cont_dep == 6) {
                    cont_dep = 0;%>
            <td class="DEPENDENCIA">Ptje. Depend. &#8721;</td>
            <%}
                }%>
            <td class="RIESGO">Ptje. Riesgo &#8721;</td>
        </tr>
        <tr>
            <%for (int i = 0; i < 18; i++) {
                    if (i == 6) {%>
            <td align="center"><input id="<%=i%>" name="<%=i%>" type="hidden" size="4" value="0"  readonly style="FONT-SIZE: 12px;color:blue;FONT-WEIGHT: bold;"><label id="LBLDep" style="FONT-SIZE: 12px;color:blue;FONT-WEIGHT: bold;">0</label></td>

            <%} else {
                if (i == 15) {%>
            <td align="center"><input id="<%=i%>" name="<%=i%>" type="hidden" size="4" value="0"  readonly style="FONT-SIZE: 12px;color:#660000;FONT-WEIGHT: bold;"><label id="LBLRie" style="FONT-SIZE: 12px;color:#660000;FONT-WEIGHT: bold;">0</label></td>

            <%} else {
                if (i == 16) {%>
            <td align="center"><input id="<%=i%>" name="<%=i%>" type="hidden" size="4" value="0"  readonly style="FONT-SIZE: 12px;color:#25145f;FONT-WEIGHT: bold;"><label id="LBLTOTAL" style="FONT-SIZE: 12px;color:#25145f;FONT-WEIGHT: bold;">0</label></td>

            <%} else {
                if (i == 17) {%>
            <td align="center"><input id="<%=i%>" name="<%=i%>" type="hidden" size="4" value="D3"  readonly style="FONT-SIZE: 12px;color:#000000;FONT-WEIGHT: bold;"><label id="LBLCAT" style="FONT-SIZE: 12px;color:#000000;FONT-WEIGHT: bold;">-</label></td>

            <%} else {
            %>
            <td><input id="<%=i%>" name="<%=i%>" onfocus="document.getElementById('<%=i%>').select();" type="text" size="3" value="0" onkeypress="soloNumeros(event)" onkeyup="tab(this.id)" onkeydown="if ((event.code < 48 || event.code > 57) && (event.code < 96 || event.code > 105) && event.code != 109 && event.code != 189 && event.code != 75 && event.code != 190 && event.code != 110 && event.code != 8 && event.code != 9 && event.code != 13)
                    {
                        return false;
                    }
                    //valido(this.id)"></td>

            <%}
                            }
                        }
                    }
                }
            %>
        </tr>
    </thead>

</table>
<script>
    function cat()
    {
        var riesgo = document.getElementById('15').value;
        var dependencia = document.getElementById('6').value;
        //alert('Riesgo '+riesgo);alert('Dependencia '+dependencia);
        var ajax3 = nuevoAjax();
        ajax3.open('POST', 'Categorizo.jsp?riesgo=' + riesgo + '&dependencia=' + dependencia, true);
        ajax3.send(null);
        if (ajax3.readyState == 1)
        {

        }
        ajax3.onreadystatechange = function()
        {
            if (ajax3.readyState == 4)
            {
                document.getElementById('17').value = ajax3.responseText;
                document.getElementById('LBLCAT').innerHTML = ajax3.responseText;
            }
        }

    }
    function tab(id)
    {
        var algo = parseInt(id) + 1;
        if (document.getElementById(id).value > 3) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
        if (document.getElementById(id).value.length == 0) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
        else {
            if (document.getElementById(id).value.length == 1)
            {
                if (algo == 6) {
                    algo = algo + 1;
                }
                document.getElementById(algo).focus();

                //alert(algo);
            }
        }
        suma_dep();
        suma_rie();
        suma_total();
        cat();
    }
    function valido(id)
    {
        if (document.getElementById(id).value > 3) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
        if (document.getElementById(id).value.length == 0) {
            document.getElementById(id).value = '0';
            document.getElementById(id).select();
        }
    }
    function suma_dep()
    {
        var suma_dep = 0;
        for (i = 0; i < 6; i++) {
            suma_dep = suma_dep + parseInt(document.getElementById(i).value);
            //alert(document.getElementById(i).value);
        }
        //alert(suma_dep);
        document.getElementById('6').value = suma_dep;
        document.getElementById('LBLDep').innerHTML = suma_dep;
    }

    function suma_rie()
    {
        var suma_rie = 0;
        for (i = 7; i < 15; i++) {
            suma_rie = suma_rie + parseInt(document.getElementById(i).value);
        }
        document.getElementById('15').value = suma_rie;
        document.getElementById('LBLRie').innerHTML = suma_rie;
    }

    function suma_total()
    {
        var total = parseInt(document.getElementById('6').value) + parseInt(document.getElementById('15').value)
        document.getElementById('16').value = total;
        document.getElementById('LBLTOTAL').innerHTML = total;
    }

    function soloNumeros(evt) {
        //asignamos el valor de la tecla a keynum
        if (window.event) {// IE
            keynum = evt.keyCode;
        } else {
            keynum = evt.which;
        }
        //
        if ((keynum > 45 && keynum < 58) || keynum == 8 || keynum == 13 || keynum == 9) {
            //numeros || delete || enter || tab
            return true;
        } else {
            alert('Solo se aceptan Números');
            return false;
        }
    }



</script>
</html>
