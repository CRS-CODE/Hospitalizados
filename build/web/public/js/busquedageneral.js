/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


   function formateaRut(Rut)
    {
        var sRut = new String(Rut);
        var sRutFormateado = '';
        sRut = quitaFormato(sRut);
        var sDV = sRut.charAt(sRut.length - 1);
        sRut = sRut.substring(0, sRut.length - 1);
        document.getElementById('txtRutSinDV').value = sRut;
        document.getElementById('txtDV').value = sDV;
        //document.forms[0].txtRutSinDV.value = sRut;
        //document.forms[0].txtDV.value = sDV;

        while (sRut.length > 3)
        {
            sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;
            sRut = sRut.substring(0, sRut.length - 3);
        }
        sRutFormateado = sRut + sRutFormateado;
        if (sRutFormateado != "")
            sRutFormateado += "-";
        sRutFormateado += sDV;
        if (document.getElementById('rutpaciente').value != sRutFormateado)
            document.getElementById('rutpaciente').value = sRutFormateado;
    }
    function quitaFormato(Nro)
    {
        var strNro = new String(Nro);
        while (strNro.indexOf(".") != - 1)
            strNro = strNro.replace(".", "");
        strNro = strNro.replace("-", "");
        return strNro;
    }
    function validaRut() {
        var sRut = new String(document.getElementById('rutpaciente').value);
        sRut = quitaFormato(sRut);
        var sDV = new String(sRut.charAt(sRut.length - 1));
        sRut = sRut.substring(0, sRut.length - 1);
        if (sDV.toUpperCase() == DigitoVerificadorRut(sRut))
        {
            return true;
        }
        if (sDV.toUpperCase() != DigitoVerificadorRut(sRut))
        {
            return false;
        }
    }
    function DigitoVerificadorRut(strRut) {
        var rut = 0;
        var s = 0;
        var l_dv = "";

        rut = strRut;
        for (i = 2; i < 8; i++) {
            s = s + (rut % 10) * i;
            rut = (rut - (rut % 10)) / 10;
        }
        s = s + (rut % 10) * 2;
        rut = (rut - (rut % 10)) / 10;
        s = s + (rut % 10) * 3;
        rut = (rut - (rut % 10)) / 10;
        s = 11 - (s % 11);
        if (s == 10)
            l_dv = "K";
        else
        if (s == 11)
            l_dv = "0"
        else
            l_dv = s + "";
        return(l_dv);
    }
    function buscaPaciente() {
        if (document.getElementById('rutpaciente').value == 0)
        {
            Alert.render("Debe Completar los Datos para Continuar");
            document.getElementById('rutpaciente').focus();
        } else
        {
            if (validaRut(document.getElementById('rutpaciente').value))
            {
                var paciente = document.getElementById('rutpaciente').value;

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
                    var objeto_recibidor = document.getElementById("busqueda");
                    xmlhttp.open("post", "busquedageneralpaciente.jsp?paciente=" + paciente);
                    xmlhttp.send("");
                    if (xmlhttp.readyState == 1) {
                        objeto_recibidor.innerHTML = '</br></br><b>Favor espere... </b>';

                    }
                    xmlhttp.onreadystatechange = function () {
                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                            objeto_recibidor.innerHTML = xmlhttp.responseText;

                            document.getElementById("busqueda").value = null;
                        }
                        if (xmlhttp.status != 200) {
                            objeto_recibidor.innerHTML = 'ERROR EN EL SISTEMA... FAVOR LLAMAR A INFORMATICA';

                        }
                    }
                }
            } else {
                Alert.render("Rut Invalida!!Debe Completar los Datos para Continuar");
                return false;
            }
        }

    }