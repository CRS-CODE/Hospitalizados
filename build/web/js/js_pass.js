/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function valida_clave (){
var clave_actual=document.getElementById('txt_pass_actual').value;
var clave_antigua=document.getElementById('txt_pass_vieja').value;
var clave_nueva=document.getElementById('txt_pass_nueva').value;
var clave_nueva_confirma=document.getElementById('txt_pass_confirma').value;

var clave_cuatro=document.getElementById('txt_cuatro').value;

if (clave_antigua.length==0){
        alert('Debe ingresar su clave actual');
        document.getElementById('txt_pass_vieja').focus();
        return false;
 }else if (clave_antigua!=clave_actual){
        alert('La clave actual no corresponde a la ingresada');
          document.getElementById('txt_pass_vieja').focus();
        return false;
 }else if (clave_nueva.length<4){
        alert('La nueva clave debe tener al menos 4 digitos');
          document.getElementById('txt_pass_nueva').focus();
        return false;
 }else if (clave_nueva==clave_cuatro){
        alert('La clave no puede corresponder a los primeros cuatro numeros de su rut');
        document.getElementById('txt_pass_nueva').focus();
        return false;
 }else if (clave_nueva!=clave_nueva_confirma){
        alert('La nueva clave y la confirmacion son distintas');
        document.getElementById('txt_pass_nueva').focus();
        return false;
 }
    return true;
}

function carga_pass (){
     document.getElementById('txt_pass_vieja').focus();  
}