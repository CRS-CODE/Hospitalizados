<%-- 
    Document   : registers_index_barthel
    Created on : 27-sep-2020, 10:54:11
    Author     : Kairin
--%>

<%@page import="java.util.Iterator"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.List"%>
<%@page import="CapaDato.DetailIndexBarthel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.Negocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../Header.jsp" />
<style>
    /* The container */
    .container {
        display: block;
        position: relative;
        padding-left: 35px;
        margin-bottom: 12px;
        cursor: pointer;
        font-size: 20px;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    /* Hide the browser's default checkbox */
    .container input {
        position: absolute;
        opacity: 0;
        cursor: pointer;
        height: 0;
        width: 0;
    }

    /* Create a custom checkbox */
    .checkmark {
        position: absolute;
        top: 0;
        left: 0;
        height: 25px;
        width: 25px;
        background-color: #eee;
    }

    /* On mouse-over, add a grey background color */
    .container:hover input ~ .checkmark {
        background-color: #ccc;
    }

    /* When the checkbox is checked, add a blue background */
    .container input:checked ~ .checkmark {
        background-color: #00a3cc;
    }

    /* Create the checkmark/indicator (hidden when not checked) */
    .checkmark:after {
        content: "";
        position: absolute;
        display: none;
    }

    /* Show the checkmark when checked */
    .container input:checked ~ .checkmark:after {
        display: block;
    }

    /* Style the checkmark/indicator */
    .container .checkmark:after {
        left: 9px;
        top: 5px;
        width: 5px;
        height: 10px;
        border: solid white;
        border-width: 0 3px 3px 0;
        -webkit-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
    }
    tr:nth-child(even) {
        background-color: #e6f2ff;
    }
    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }
    table.left {
        margin-left:auto; 

    }
    table.center {
        margin-left:auto; 
        margin-right: auto;

    }
    .button {
        background-color: #00a3cc;
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
    }
    #column-left {
        background-color: #ffffff;
        border: 1px solid #D2D2D2;
        border-radius: 8px 8px 8px 8px;
        float: left;
        position: fixed;
        min-height: 225px;
        margin-bottom: 10px;
        margin-right: 10px;
        overflow: hidden;
        text-align: center;
        max-width: 800px;
        width: 25%;
        height: 70%;
        padding-top: -2px 
    }

    .paciente {
        padding: 20px;
        float: left;
        width: 10%; 
        padding-top: -2px
    }
</style>

<%
    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("index.jsp?timeout=1");
    } else {

        Negocio controller = new Negocio();
        List<DetailIndexBarthel> listDetailIndexBarthel = controller.searchDetailIndexBarthel();

        String obtiene_usuario = session1.getAttribute("usuario_rut").toString();
        int obtiene_duo = 0;
        if (request.getParameter("txt_duo") != null) {
            obtiene_duo = Integer.parseInt(request.getParameter("txt_duo"));
        }
        if (request.getParameter("id") != null) {%>
<script language="javascript">
    window.open('<%=controller.getLocal()%>PDF_indexBarthel?id=' +<%=request.getParameter("id")%>, '', 'titlebar=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1400,height=1050');
</script>
<%}

%>
<script src="<%=neg.getLocal()%>js/jquery/jquery.js"></script>
<body>
    <div>
        <form  style=" padding-top: 2px" name="form_index_barthel" id="form_index_barthel" action="registers_index_barthel.jsp" method="POST"  >
            <div class="paciente">
                <h1 style="color:#00a3cc">Lista Pacientes</h1>
                <select name="txt_duo" id="txt_duo" onchange="selecciona_cama()">
                    <option value="0" >Seleccione...</option>
                    <%    //
                        ArrayList lista_cama = null;
                        Iterator it_sala = null;

                        lista_cama = neg.lista_grilla_camas();
                        it_sala = lista_cama.iterator();

                        while (it_sala.hasNext()) {
                            cDuo aux = (cDuo) it_sala.next();
                            if (aux.getId_duo() != 0) {
                                out.write("<option value='" + aux.getId_duo() + "' >" + aux.getCama_descripcion() + "::" + aux.getNombres_paciente() + " " + aux.getApellidop_paciente() + "</option>");
                            }
                        }
                    %>
                </select>
            </div>
        </form>
    </div>
    <%    if (obtiene_duo == 0) {

        } else {
            cDuo duo = neg.obtiene_duo(obtiene_duo);
    %>
    <form style=" padding-top: 67px" name="form_detail_index_barthel" action="<%=controller.getLocal()%>insertIndexBarthel" id="form_detail_index_barthel" method="POST"  >
        <input type="hidden" name="txt_usuario" id="txt_usuario" value="<%=session1.getAttribute("usuario_rut")%>" >

        <input type="hidden" name="txt_duo" id="txt_duo" value="<%=obtiene_duo%>" > 
        <input type="hidden" name="txt_score" id="txt_score" >
        <input type="hidden" name="txt_outcome" id="txt_outcome" > 
        <div id="column-left">
            <h1 align='center' style="color:#00a3cc">Clasificacion Resultante</h1>
            <table>
                <tr>
                    <th>Tipo Registro</th>
                    <td>
                        <select name="type_attention" id="type_attention">
                            <option value="0" >Seleccione...</option>
                            <option value="1">Ingreso</option>
                            <option value="2">Egreso</option>
                        </select>
                    </td>

                </tr>
                <tr>
                    <th>Puntaje:</th>
                    <td><div id="score" name="score"></div></td>
                </tr>
                <tr>
                    <th>Grado de Dependencia:</th>
                    <td><div id="outcome" name="outcome"></div></td>
                </tr>
            </table>
            <h1 align='center' style="color:#00a3cc">Clasificación según IB</h1>
            <table class="center">
                <tr style=" background-color:#00a3cc; color: #ffffff">
                    <th>Resultado</th>
                    <th>Grado de dependencia</th>
                </tr>
                <tr>
                    <td>
                        < 20
                    </td>
                    <td>
                        Total
                    </td>
                </tr>
                <tr>
                    <td>
                        20 a 35
                    </td>
                    <td>
                        Grave
                    </td>
                </tr>
                <tr>
                    <td>
                        40 a 55
                    </td>
                    <td>
                        Moderado
                    </td>
                </tr>
                <tr>
                    <td>
                        > 60
                    </td>
                    <td>
                        Leve
                    </td>
                </tr>
                <tr>
                    <td>
                        100
                    </td>
                    <td>
                        Independiente
                    </td>
                </tr>
            </table>
            <div>
                <button class="button" type="submit" >Guardar </button>
            </div>

        </div>
        <table align='center'>
            <tr>
                <td><h1 align='center' style="color:#00a3cc">Datos Paciente</h1>
                    <table>
                        <tbody>
                            <tr>
                                <th>Rut Paciente</th>
                                <td colspan="3"  ><% out.write("" + duo.getRut_paciente());%></td>

                                <th>Fecha Nacimiento</th>
                                <td colspan="2"  >   <% out.write("" + duo.getFecha_nac());%></td>
                            </tr>
                            <tr>
                                <th>Nombre Completo</th>
                                <td colspan="3"><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>

                                <th>Edad</th>
                                <td><% out.write("" + duo.getEdad());%></td>
                            </tr>
                            <tr>
                                <th>Cama</th>
                                <td colspan="3" ><% out.write("" + duo.getCama_descripcion());%></td>

                                <th>Registro Nª</th>
                                <td><% out.write("" + duo.getId_duo());%></td>
                            </tr>
                            <tr>

                                <th>Días Hospitalizado</th>
                                <td>  <%out.write("" + duo.getDias_cama());%>
                                </td>

                            </tr>
                        </tbody>
                    </table> 
                </td>


            </tr>
        </table> 
        <table class="left">
            <h1 align='center' style="color:#00a3cc">Índice de Barthel (IB) (Mahoney, Barthel, 1965)</h1>
            <tr style=" background-color:#00a3cc; color: #ffffff ">
                <th> <img src="../Imagenes/aceptar.png" alt=""/> </th>
                <th>Actividad</th>
                <th>Descripcion</th>
                <th>Puntaje</th>
            </tr>

            <%
                int idIndex = 0, idIndexNext = 0;
                for (DetailIndexBarthel detailIndexBarthel : listDetailIndexBarthel) {
            %>
            <tr>
                <td><label class="container">
                        <input id='detailBarthel<%=detailIndexBarthel.getIdDetail()%>' name='detailBarthel<%=detailIndexBarthel.getIdDetail()%>'  type="checkbox" value="checkbox" onchange="myFunction()">
                        <span class="checkmark"></span>
                    </label></td>
                    <% idIndexNext = detailIndexBarthel.getIdIndexBarthel();
                        if ((idIndexNext != idIndex) & (idIndex != '0')) {%>
                <td><%=detailIndexBarthel.getDescriptionBarthel()%> </td>
                <%  } else { %>
                <td></td>
                <%}%>

                <td><%=detailIndexBarthel.getDescriptionDetail()%></td>
                <td style="text-align: center"><%=detailIndexBarthel.getPunctuction()%></td>

            </tr>

            <% idIndex = idIndexNext;
                }%>
        </table> 

    </form>

</body>
<script>
    function myFunction() {
        var sum = 0;
    <%for (DetailIndexBarthel detailIndexBarthel : listDetailIndexBarthel) {%>
        var idIndexBarthel = <%=detailIndexBarthel.getIdDetail()%>
        var checkIndex = document.forms["form_detail_index_barthel"]["detailBarthel" + idIndexBarthel].checked;
        if (checkIndex) {
            sum +=<%=detailIndexBarthel.getPunctuction()%>
        }
    <% }%>

        if (sum < 20) {
            document.getElementById("outcome").innerHTML = "Total";
            document.getElementById("txt_outcome").value = "Total";
        } else if (sum >= 20 && sum <= 35) {
            document.getElementById("outcome").innerHTML = "Grave";
            document.getElementById("txt_outcome").value = "Grave";
        } else if (sum >= 40 && sum <= 50) {
            document.getElementById("outcome").innerHTML = "Moderado";
            document.getElementById("txt_outcome").value = "Moderado";
        } else if (sum > 60 && sum < 100) {
            document.getElementById("outcome").innerHTML = "Leve";
            document.getElementById("txt_outcome").value = "Leve";
        } else if (sum === 100) {
            document.getElementById("outcome").innerHTML = "Independiente";
            document.getElementById("txt_outcome").value = "Independiente";
        }

        document.getElementById("score").innerHTML = sum;
        document.getElementById("txt_score").value = sum;


    }

</script>
<%     }
    }%>

<script>
    function selecciona_cama() {

        if ($("#txt_duo").val() != 0) {
            document.getElementById('form_index_barthel').submit();
        }

    }
</script>


<jsp:include page="../Footer.jsp" />

