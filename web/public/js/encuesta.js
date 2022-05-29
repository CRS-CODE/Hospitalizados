/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarcombos() {


    var asma = document.getElementById('asma').value;

    var tuberculosis = document.getElementById('tuberculosis').value;

    var ira = document.getElementById('ira').value;

    var renitis = document.getElementById('renitis').value;

    var diabetes = document.getElementById('diabetes').value;

    var obesidad = document.getElementById('obesidad').value;

    var rge = document.getElementById('rge').value;

    var hipo = document.getElementById('hipo').value;

    var ulcera = document.getElementById('ulcera').value;

    var alergias = document.getElementById('alergias').value;

    var coronario = document.getElementById('coronario').value;

    var arritmias = document.getElementById('arritmias').value;

    var coagulapatia = document.getElementById('coagulapatia').value;

    var valvular = document.getElementById('valvular').value;

    var marcapasos = document.getElementById('marcapasos').value;

    var dislipidemia = document.getElementById('dislipidemia').value;

    var hta = document.getElementById('hta').value;

    var ecv = document.getElementById('ecv').value;

    var depresion = document.getElementById('depresion').value;

    var esquizofrenia = document.getElementById('esquizofrenia').value;

    var epilepsia = document.getElementById('epilepsia').value;

    var alcohol = document.getElementById('alcohol').value;

    var nefropatia = document.getElementById('nefropatia').value;

    var uropatia = document.getElementById('uropatia').value;

    var infeccionurinaria = document.getElementById('infeccionurinaria').value;

    var intervencion = document.getElementById('intervencion').value;

    var anastesico = document.getElementById('anastesico').value;

    var pase = document.getElementById('pase').value;

    if (asma == -1 || pase == -1 || tuberculosis == -1 || ira == -1 || renitis == -1 || diabetes == -1 || obesidad == -1 || rge == -1 || hipo == -1 || ulcera == -1 || alergias == -1 || coronario == -1 || arritmias == -1 || coagulapatia == -1 || valvular == -1 || marcapasos == -1 || dislipidemia == -1 || hta == -1 || ecv == -1 || depresion == -1 || esquizofrenia == -1 || epilepsia == -1 || alcohol == -1 || nefropatia == -1 || uropatia == -1 || infeccionurinaria == -1 || intervencion == -1 || anastesico == -1) {
        return false;
    }

}

function validarretro() {
    var ret = document.getElementById('retrocontrol').checked;
    var paretro = document.getElementById('paretro').value;
    var cardiacaretro = document.getElementById('cardiacaretro').value;
    var satretro = document.getElementById('satretro').value;
    if (ret) {
        if (paretro == "" || cardiacaretro == "" || satretro == "") {

            return false;

        }

    }
}

function validartabaco() {
    var tabaco = document.getElementById('tabaco').value;
    var desde = document.getElementById('desde').value;
    var anno = document.getElementById('annos').value;

    if (tabaco != 0) {
        if (desde == "" || anno == "") {

            return false;

        }

    }
}

function validaralergias() {
    var aler = document.getElementById('alergias').value;
    var cualalergia = document.getElementById('cualesalergias').value;

    if (aler == 1) {
        if (cualalergia == "") {
            return false;
        }

    }

}

function validaranastesico() {
    var ret = document.getElementById('anastesico').value;
    var paretro = document.getElementById('otraanastesico').value;

    if (ret == 1) {
        if (paretro == "") {
            return false;
        }

    }

}

function validarpertinencia() {
    var atencion = document.getElementById('idatencioncita').value;
    var pertinencia = document.getElementById('pertinencia').value;

    if (atencion == 1) {
        if (pertinencia == -1) {
            return false;
        }
    }


}
function validarcontrol() {

    var fechacontrol = document.getElementById('fechacontrol').value;
    var motivocontrol = document.getElementById('motivocontrol').value;

    if (fechacontrol != "") {
        if (motivocontrol == "") {
            return false;
        }
    }
}
$(function () {
    $("#fechacontrol").datepicker();
});


function numeros(e)  // 1

{
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum >= 48) || (keynum <= 57))
        return true;

    return /\d/.test(String.fromCharCode(keynum));
}

function validar() {

    var atencion = document.getElementById('idatencioncita').value;
    var pertinencia = document.getElementById('pertinencia').value;
    var diagnosticoenfermera = document.getElementById('diagnosticoenfermera').value;
    var cirugiapropuesta = document.getElementById('cirugiapropuesta').value;
    var descripcion_ventilatorios = document.getElementById('descripcion_ventilatorios').value;
    var descripcion_metabolicos = document.getElementById('descripcion_metabolicos').value;
    var descripcion_cardiovasculares = document.getElementById('descripcion_cardiovasculares').value;
    var descripcionneuro = document.getElementById('descripcionneuro').value;
    var descripcion_urinarios = document.getElementById('descripcion_urinarios').value;
    var descripcion_antecedentes = document.getElementById('descripcion_antecedentes').value;
    var descripcion_examenes = document.getElementById('descripcion_examenes').value;
    var observaciones = document.getElementById('observaciones').value;
    if (atencion == 1) {
        if (pertinencia == -1) {
            Alert.render("Como su atencion es Nueva debe indicar si ¿Es pertinente la derivación a su atención? !! Debe Completar los Datos para Continuar");
            return false;
        }
    }

    if (diagnosticoenfermera == "") {
        Alert.render("Debe inidicar el Diagnostico!! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.diagnosticoenfermera.focus();
        document.entrevistaproperatorio.diagnosticoenfermera.select();
        return false;

    }

    if (cirugiapropuesta == "") {
        Alert.render("Debe inidicar la cirugia propuesta!! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.cirugiapropuesta.focus();
        document.entrevistaproperatorio.cirugiapropuesta.select();
        return false;
    }

    if (validarretro() == false) {
        Alert.render("Si Confirma RetroControl!! Debe Completar los Datos para Continuar");
        return false;
    }

    if (validartabaco() == false) {
        Alert.render("Si Tabaco es mas de 0 !! Debe Completar los Datos para Continuar");
        return false;

    }

    if (descripcion_ventilatorios == "") {
        Alert.render("Debe indicar descripcion de Ventilatorios !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcion_ventilatorios.focus();
        document.entrevistaproperatorio.descripcion_ventilatorios.select();
        return false;

    }



    if (validaralergias() == false) {
        Alert.render("Si Tiene alergias debe decir Cuales !! Debe Completar los Datos para Continuar");
        return false;

    }
    if (descripcion_metabolicos == "") {
        Alert.render("Debe indicar descripcion de Metabolicos/Generales !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcion_metabolicos.focus();
        document.entrevistaproperatorio.descripcion_metabolicos.select();
        return false;


    }


    if (descripcion_cardiovasculares == "") {
        Alert.render("Debe indicar descripcion de Cardiovasculares !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcion_cardiovasculares.focus();
        document.entrevistaproperatorio.descripcion_cardiovasculares.select();
        return false;


    }

    if (descripcionneuro == "") {
        Alert.render("Debe indicar descripcion de Neuro-Psiquiatricos !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcionneuro.focus();
        document.entrevistaproperatorio.descripcionneuro.select();
        return false;


    }

    if (descripcion_urinarios == "") {
        Alert.render("Debe indicar descripcion de Nefro-Urinarios !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcion_urinarios.focus();
        document.entrevistaproperatorio.descripcion_urinarios.select();
        return false;


    }

    if (validaranastesico() == false) {
        Alert.render("Si es Anastesico debe especificar !! Debe Completar los Datos para Continuar");
        return false;

    }

    if (descripcion_antecedentes == "") {
        Alert.render("Debe indicar descripcion de Quirurgicos !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcion_antecedentes.focus();
        document.entrevistaproperatorio.descripcion_antecedentes.select();
        return false;


    }
    if (descripcion_examenes == "") {
        Alert.render("Debe indicar descripcion de Examenes !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.descripcion_examenes.focus();
        document.entrevistaproperatorio.descripcion_examenes.select();
        return false;


    }
    if (observaciones == "") {
        Alert.render("Debe indicar Observacion , si el paciente requiere evaluacion con Medico y si puede operarse !! Debe Completar los Datos para Continuar");
        document.entrevistaproperatorio.observaciones.focus();
        document.entrevistaproperatorio.observaciones.select();
        return false;


    }
    if (validarcombos() == false) {
        Alert.render("Revise los combos debe indicar toda la informaciòn !! Debe Completar los Datos para Continuar");
        return false;
    }


    if (validarcontrol() == false) {
        Alert.render("Debe Indicar el Motivo del Control!! Complete los datos para continuar");
        return false;
    }


    document.entrevistaproperatorio.submit();


}