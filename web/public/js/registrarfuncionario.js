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
        document.forms["paciente"].txtRutSinDV.value = sRut;
        document.forms["paciente"].txtDV.value = sDV;
        while (sRut.length > 3)
        {
            sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;
            sRut = sRut.substring(0, sRut.length - 3);
        }
        sRutFormateado = sRut + sRutFormateado;
        if (sRutFormateado != "")
            sRutFormateado += "-";
        sRutFormateado += sDV;
        if (document.forms["paciente"].rutpaciento.value != sRutFormateado)
            document.forms["paciente"].rutpaciento.value = sRutFormateado;
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
        var sRut = new String(document.getElementById('rutpaciento').value);
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
    
