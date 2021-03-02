<%@page import="CapaNegocio.NegocioQ"%>



<%
int riesgo=Integer.parseInt(String.valueOf(request.getParameter("riesgo")));
int dependencia=Integer.parseInt(String.valueOf(request.getParameter("dependencia")));
    

NegocioQ neg=new NegocioQ();

String letra=neg. getCatRiesgo(riesgo);
String numero=neg.getCatDependencia(dependencia);

out.print(letra+numero);
%>