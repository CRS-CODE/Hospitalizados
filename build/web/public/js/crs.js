/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function CustomAlert() {
    this.render = function (dialog) {
        var winW = window.innerWidth;
        var winH = window.innerHeight;
        var dialogoverlay = document.getElementById('dialogoverlay');
        var dialogbox = document.getElementById('dialogbox');
        dialogoverlay.style.display = "block";
        dialogoverlay.style.height = winH + "px";
        dialogbox.style.left = (winW / 2) - (550 * .5) + "px";
        dialogbox.style.top = "100px";
        dialogbox.style.display = "block";
        document.getElementById('dialogboxhead').innerHTML = "Mensaje de Error";
        document.getElementById('dialogboxbody').innerHTML = '<p><span style="float:left; margin:0 7px 50px 0;"> <img src="../../public/imagenes/icon/close.png" alt=""/></span></p>' + dialog;
        document.getElementById('dialogboxfoot').innerHTML = '<button aria-disabled="false" onclick="Alert.ok()">Ok</button>';
    }
    this.ok = function () {
        document.getElementById('dialogbox').style.display = "none";
        document.getElementById('dialogoverlay').style.display = "none";
    }
}


var Alert = new CustomAlert();

$(function () {
    $("#dialog-message").dialog({
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });
});

function reset() {
    $("#toggleCSS").attr("href", "../../public/themes/alertify.default.css");
    alertify.set({
        labels: {
            ok: "OK",
            cancel: "Cancel"
        },
        delay: 5000,
        buttonReverse: false,
        buttonFocus: "ok"
    });
}


function nobackbutton() {

    window.location.hash = "no-back-button";

    window.location.hash = "Again-No-back-button" //chrome

    window.onhashchange = function () {
        window.location.hash = "no-back-button";
    }

}

function numeros(e)  // 1

{
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum >= 48) || (keynum <= 57))
        return true;

    return /\d/.test(String.fromCharCode(keynum));
}
function validarsololetra(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for (var i in especiales) {
        if (key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) == -1 && !tecla_especial) {
        return false;
    }
}
    