<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%
    // POSTGRES

  String usuarioBD = "uhm";
  String passwordBD = "crsdb2020";
  String drv = "org.postgresql.Driver";
  String dsn = "jdbc:postgresql://10.8.4.163:5432/crsm";

  Connection cn = null;
  Statement st = null;
  try {
    Class.forName(drv);
  } catch (Exception e) { }
  try {
    cn = DriverManager.getConnection(dsn,usuarioBD,passwordBD);
  } catch (Exception e) {
    out.println("<h1>Problemas en la base de datos POSTGRES, consulte al administrador de red.</h1>");
  }
  try {
    st = cn.createStatement();
  } catch (Exception e) {}

//**************************************************************

  // SYBASE
  //String usuarioBDsy = "hernan";
  //String passwordBDsy = "hernan07";
 /*
  String usuarioBDsy = "sa";
  String passwordBDsy = "gerente";  
  String drvsy = "com.sybase.jdbc2.jdbc.SybDriver";
  String dsnsy = "jdbc:sybase:Tds:10.8.4.9:7000/BD_ENTI_CORPORATIVA";
  Connection cnsy = null;
  Statement stsy = null;
  try {
    Class.forName(drvsy);
  } catch (Exception e) {out.print(e+"</br>"); }
  try {
    cnsy = DriverManager.getConnection(dsnsy,usuarioBDsy,passwordBDsy);
  } catch (Exception e) {
    out.println(e+"<h1>Problemas en la base de datos SYBASE, consulte al administrador de red.</h1>");
  }
  try {
    stsy = cnsy.createStatement();
  } catch (Exception e) {}  
*/
  //*******************************************************************

%>
