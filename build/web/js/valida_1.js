/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function validaRut12(sRut,num){

    sDV= sRut.substr(sRut.length-1,1);
    RUTCORTADO = quitaFormato(sRut);
    RUTCORTADO =  RUTCORTADO.substr(0,RUTCORTADO.length-1);

    var sRut = sRut;

    sRut = quitaFormato(sRut);

    var sDV = new String(sRut.charAt(sRut.length-1));

    sRut = sRut.substring(0, sRut.length-1);

    if(sDV.toUpperCase() == dv(RUTCORTADO))

    {
        return true;
    }
    if (num==1){
        alert('Rut invalido');
    }

    return false;


}
