/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function valida_ing1(){
   
    if( document.getElementById('cbo_procedencia').value==-1){
        alert('Debe seleccionar Procedencia');
        return false;
    }else if (document.getElementById('cbo_destino').value==-1){
        alert('Debe seleccionar Destino');
        return false;
    
    }else if (document.getElementById('txt_destinatario').value.length==0){
        alert('Debe seleccionar Destinatario');
        return false;

    }else if (document.getElementById('cbo_tiempo').value==-1){
        alert('Debe seleccionar Tiempo');
        return false;
    }else if (document.getElementById('txa_motivo').value==-1){
        alert('Debe ingresar motivo (min. 20 caracteres)');
        return false;
    }

    return true;

}

function valida_form (fecha1,fecha2,cbo_id){


    if (document.getElementById(cbo_id).value==0){
        alert('Seleccione informe');
        return false;
    }
    else  if ( document.getElementById(fecha1).value.length==0){
        alert('No se ha ingresado fecha inicial');
        return false;
    }else if ( document.getElementById(fecha2).value.length==0){
        alert('No se ha ingresado fecha final');
        return false;
    }

    return true;
    
}


