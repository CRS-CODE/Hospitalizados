/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//para elegir

function pone() {

    if (!isSelected("#fromSelectBox")) {
        return;
    }
    //If atleast one of the item is selected, initially the selected option would be 'removed' and then it is appended to 'toSelectBox' (select box)
    $('#fromSelectBox option:selected').remove().appendTo('#toSelectBox');
    return false;

}
function saca() {

    //If no items are present in 'toSelectBox' (or) if none of the items are selected inform the user using an alert
    if (!noOptions("#toSelectBox") || !isSelected("#toSelectBox")) {
        return;
    }
    //If atleast one of the item is selected, initially the selected option would be 'removed' and then it is appended to 'fromSelectBox' (select box)
    $('#toSelectBox option:selected').remove().appendTo('#fromSelectBox');

}

function todos() {
    selectAll('#fromSelectBox');
    pone();
}

function ninguno() {
    selectAll('#toSelectBox');
    saca();
}

function selecciona_todos() {

    selectAll('toSelectBox');
}

//Below function is to validate the select box, if none of the item(s) is selected then it alerts saying 'Please select atleast one option' if user selects an item then it returns true
function isSelected(thisObj) {
    if (!$(thisObj + " option:selected").length) {
        Alert.render("Debe Completar los Datos para Continuar");
        return 0;
    }
    return 1;
}

//Below function is to validate the select box, if none of the item(s) where present in the select box provided then it alerts saying 'There are no options to select/move' if select box has more than one item it returns true
function noOptions(thisObj) {
    if (!$(thisObj + " option").length) {
        // alert("There are no options to select/move");
        return 0;
    }
    return 1;
}

//Below function is to de-select all items if any of the item(s) are selected
function clearAll(thisObj) {
    $('#' + thisObj).each(function () {
        $(this).find('option:selected').removeAttr("selected");
    });
}//function close

//Below function is to select all items
function selectAll(thisObj) {
    obj = document.getElementById(thisObj);
    for (var i = 0; i < obj.options.length; i++) {
        obj.options[i].selected = true;
    }
}
