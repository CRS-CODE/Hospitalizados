/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function solicitudpabellon() {
    
    var solicitudes = document.forms["hojadiaria"]["numerodesolicitudes"].value;
    var especialidad = document.forms["hojadiaria"]["especialidad"].value;


    if (solicitudes == "") {

        Alert.render("Debe Completar los Datos para Continuar");
        return false;
    } else
    {
        try
        {
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e)
        {
            try
            {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (E) {
                xmlhttp = false;
            }
        }
        if (!xmlhttp && typeof XMLHttpRequest != "undefined") {
            xmlhttp = new XMLHttpRequest();
        }

        if (xmlhttp) {
            var objeto_recibidor = document.getElementById("respuesta");
            xmlhttp.open("post", "solicitud.jsp");
            xmlhttp.send("");
            if (xmlhttp.readyState == 1) {
                objeto_recibidor.innerHTML = '</br></br><b> </b>';

            }
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    objeto_recibidor.innerHTML = xmlhttp.responseText;



                }
                if (xmlhttp.status != 200) {
                    objeto_recibidor.innerHTML = 'ERROR EN EL SISTEMA... FAVOR LLAMAR A INFORMATICA';

                }
            }
        }
    }
}

