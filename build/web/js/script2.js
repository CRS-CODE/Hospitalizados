/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function soloNumeros(evt){
    //asignamos el valor de la tecla a keynum
    if(window.event){// IE
        keynum = evt.keyCode;
    }else{
        keynum = evt.which;
    }
    //
    if((keynum>45 && keynum<58) || keynum == 8 ||keynum == 13||keynum == 9 ){
        //numeros || delete || enter || tab
        return true;
    }else{
        return false;
    }
}
