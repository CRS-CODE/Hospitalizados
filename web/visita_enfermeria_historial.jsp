<%-- 
    Document   : visita_enfermeria_historial
    Created on : 14-abr-2015, 12:21:59
    Author     : Informatica
--%>

<%@page import="CapaDato.cVisita"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%

    NegocioQ neg = new NegocioQ();
    //14-04-2015
    int obtiene_duo = Integer.parseInt(request.getParameter("txt_duo"));
    ArrayList lista_visita = neg.lista_historial_visita_enfermeria(obtiene_duo);
    Iterator it_visita = lista_visita.iterator();
    int contador = 0;
    if (lista_visita.isEmpty()) {
        out.write("No se encontraron registros");
    } else {
        String clas = "";

        //// 15-12-2015
        ArrayList lista_visita_enfermeria = neg.lista_historial_visita_enfermeria(obtiene_duo);
        Iterator it_visita_enfermeria = lista_visita_enfermeria.iterator();

        out.write("<table>");
        out.write("<tr>");
        out.write("<th>Dia/Hora</th>");
        out.write("<th>Usuario</th>");
        out.write("<th>Duo</th>");
        out.write("<th>Categorización</th>");
        out.write("<th>Detalle</th>");

        out.write("</tr>");

        while (it_visita_enfermeria.hasNext()) {
            cVisita vis = (cVisita) it_visita_enfermeria.next();
            out.write("<tr>");
            out.write("<td>" + vis.getFecha_visita() + "&nbsp;" + vis.getHora_visita() + "</td>");
            out.write("<td>" + vis.getNombre_usuario() + "&nbsp;" + vis.getApellidop_usuario() + "&nbsp;" + vis.getApellidom_usuario().substring(0, 1) + ".</td>");
            out.write("<td>" + vis.getId_duo() + "</td>");
            out.write("<td>" + vis.getCat_visita_categorizacion() + "</td>");
            out.write("<td>" + vis.getObs_visita() + "</td>");
            out.write("</tr>");
        }

        out.write("</table>");

        out.write("<table>");
        while (it_visita.hasNext()) {
            cVisita vis = (cVisita) it_visita.next();
            if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("D")) {
                clas = "CRD_D";
            }
            if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("C")) {
                clas = "CRD_C";
            }
            if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("B")) {
                clas = "CRD_B";
            }
            if (vis.getCat_visita_categorizacion().substring(0, 1).equalsIgnoreCase("A")) {
                clas = "CRD_A";
            }
            int resto = contador % 10;
            if (contador == 0 || resto == 0) {
                out.write("<tr>");
            }
            out.write("<td> <a href='/modulo_uhce/visitas/CategorizacionPDF.jsp?id_visita=" + vis.getId_visita_categorizacion() + "' target='a_blank'  > " + vis.getFecha_visita() + "</a></td>");
            out.write("<th " + clas + " >  " + vis.getCat_visita_categorizacion() + "</th>");
            contador++;
        }
        out.write("</tr>");
        out.write("</table>");
    }

%>

<style>
    table { /*background:#D3E4E5;*/
        border:1px solid gray;
        border-collapse:collapse;
        color:#fff;
        font:normal 12px verdana, arial, helvetica, sans-serif;
    }
    caption { border:1px solid #5C443A;
              color:#5C443A;
              font-weight:bold;
              letter-spacing:20px;
              padding:6px 4px 8px 0px;
              text-align:center;
              text-transform:uppercase;
    }
    td, th { color:#363636;
             padding:.4em;
    }
    tr { border:1px dotted gray;
    }
    thead th, tfoot th { background:#5C443A;
                         color:#FFFFFF;
                         padding:3px 10px 3px 10px;
                         text-align:left;
                         text-transform:uppercase;
    }
    tbody td a { color:#363636;
                 text-decoration:none;
    }
    tbody td a:visited { color:gray;
                         text-decoration:line-through;
    }
    tbody td a:hover { text-decoration:underline;
    }
    tbody th a { color:#363636;
                 font-weight:normal;
                 text-decoration:none;
    }
    tbody th a:hover { color:#363636;
    }
    tbody td+td+td+td a { background-image:url('bullet_blue.png');
                          background-position:left center;
                          background-repeat:no-repeat;
                          color:#03476F;
                          padding-left:15px;
    }
    tbody td+td+td+td a:visited { background-image:url('bullet_white.png');
                                  background-position:left center;
                                  background-repeat:no-repeat;
    }
    tbody th, tbody td { text-align:left;
                         vertical-align:top;
    }
    tfoot td { background:#5C443A;
               color:#FFFFFF;
               padding-top:3px;
    }
    .odd { background:#fff;
    }
    tbody tr:hover { background:#99BCBF;
                     border:1px solid #03476F;
                     color:#000000;
    }
</style>
