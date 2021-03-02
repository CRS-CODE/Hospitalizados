function formateaRut(Rut)

{

    var sRut = new String(Rut.toUpperCase());

    var sRutFormateado = '';

    sRut = quitaFormato(sRut);

    var sDV = sRut.charAt(sRut.length-1);

    sRut = sRut.substring(0, sRut.length-1);

    document.forms[0].txtRutSinDV.value = sRut;

    document.forms[0].txtDV.value = sDV;



    while( sRut.length > 3 )

    {

        sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;

        sRut = sRut.substring(0, sRut.length - 3);

    }

    sRutFormateado = sRut + sRutFormateado;

    if(sRutFormateado != "") sRutFormateado += "-";

    sRutFormateado += sDV;

    if(document.forms[0].user.value!=sRutFormateado)

        document.forms[0].user.value = sRutFormateado;

}

function quitaFormato(Nro)

{
    var strNro = new String(Nro);

    while( strNro.indexOf(".") != -1 ) strNro = strNro.replace(".","");

    strNro = strNro.replace("-","");

    return strNro;

}

function validaRut(sRut){

    sDV= document.forms[0].user.value.substr(document.forms[0].user.value.length-1,1);
    RUTCORTADO = quitaFormato(sRut);
    RUTCORTADO =  RUTCORTADO.substr(0,RUTCORTADO.length-1);
    /*
alert (RUTCORTADO);
alert (dv (16623070));

    \u00e1 -> á
\u00e9 -> é
\u00ed -> í
\u00f3 -> ó
\u00fa -> ú
\u00c1 -> Á
\u00c9 -> É
\u00cd -> Í
\u00d3 -> Ó
\u00da -> Ú
\u00f1 -> ñ
\u00d1 -> Ñ
    ü -> \u00FC

Ü -> \u00DC

ç -> \u00E7

Ç -> \u00C7

¿ -> \u00BF

¡ -> \u00A1
*/

    if(document.forms[0].user.value=='')

    {
        alert("             DEBE INGRESAR RUT!\n             .");
        document.forms[0].user.select();
        return false;
    }

    if(sDV.toUpperCase() != dv(RUTCORTADO))

    {
        alert("             RUT INVALIDO!\n             Vuelva a Intentar.");
        document.forms[0].user.select();
        return false;
    }
    
    if( document.getElementById('pass').value.length=="")
    {
        alert('Debe ingresar su contrase\u00f1a');
        document.getElementById('pass').focus();
        return false;
    }

    var sRut = new String(document.forms[0].user.value);

    sRut = quitaFormato(sRut);

    var sDV = new String(sRut.charAt(sRut.length-1));

    sRut = sRut.substring(0, sRut.length-1);

    if(sDV.toUpperCase() == dv(RUTCORTADO))

    {
        return true;
    }

    return false;


}




function dv(T){
    var M=0,S=1;
    for(;T;T=Math.floor(T/10))
        S=(S+T%10*(9-M++%6))%11;
    return S?S-1:'K';
}



function validasoloRut(sRut){

    sDV= document.forms[0].user.value.substr(document.forms[0].user.value.length-1,1);
    RUTCORTADO = quitaFormato(sRut);
    RUTCORTADO =  RUTCORTADO.substr(0,RUTCORTADO.length-1);
    /*
alert (RUTCORTADO);
alert (dv (16623070));
*/
    if(document.forms[0].user.value=='')

    {
        alert("             DEBE INGRESAR RUT!\n             .");
        document.forms[0].user.select();
        return false;
    }



    if(sDV.toUpperCase() != dv(RUTCORTADO))

    {
        alert("             RUT INVALIDO!\n             Vuelva a Intentar.");
        document.forms[0].user.select();
        return false;
    }
    return true
}


function mensaj(){
    alert ("toma valida rut");

}










