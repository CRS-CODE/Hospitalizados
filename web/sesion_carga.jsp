<%-- 
    Document   : visita_medica_carga
    Created on : 02-sep-2015, 12:39:11
    Author     : Informatica
--%>

<%@page import="CapaDato.cVisita"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int obtiene_visita_medica = Integer.parseInt(request.getParameter("visita_medica"));
    NegocioQ neg = new NegocioQ();
    cVisita vis = neg.obtiene_visita_sesion(obtiene_visita_medica);
    out.write( vis.getObs_visita());
%><input type="hidden" name="txt_fecha2" id="txt_fecha2" value="<%=vis.getFecha_visita()%>" />