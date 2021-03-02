<%-- 
    Document   : PautaCRD
    Created on : 20-10-2010, 11:55:01 AM
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="../ObjetoAjax.js"></script>
        <style>
            table { background:#D3E4E5;
 border:1px solid gray;
 border-collapse:collapse;
 color:#fff;
 font:normal 12px verdana, arial, helvetica, sans-serif;
}
caption { border:1px solid #5C443A;
 color:#5C443A;
 font-weight:bold;
 letter-spacing:20px;
 padding:6px 4px 8px 0px;
 text-align:center;
 text-transform:uppercase;
}
td, th { color:#363636;
 padding:.4em;
}
tr { border:1px dotted gray;
}
thead th, tfoot th { background:#5C443A;
 color:#FFFFFF;
 padding:3px 10px 3px 10px;
 text-align:left;
 text-transform:uppercase;
}
tbody td a { color:#363636;
 text-decoration:none;
}
tbody td a:visited { color:gray;
 text-decoration:line-through;
}
tbody td a:hover { text-decoration:underline;
}
tbody th a { color:#363636;
 font-weight:normal;
 text-decoration:none;
}
tbody th a:hover { color:#363636;
}
tbody td+td+td+td a { background-image:url('bullet_blue.png');
 background-position:left center;
 background-repeat:no-repeat;
 color:#03476F;
 padding-left:15px;
}
tbody td+td+td+td a:visited { background-image:url('bullet_white.png');
 background-position:left center;
 background-repeat:no-repeat;
}
tbody th, tbody td { text-align:left;
 vertical-align:top;
}
tfoot td { background:#5C443A;
 color:#FFFFFF;
 padding-top:3px;
}
.odd { background:#fff;
}
tbody tr:hover { background:#99BCBF;
 border:1px solid #03476F;
 color:#000000;
}
        </style>
    </head>
    <body>
        <DIV id=wrapper> <DIV id=header>
        <div id="formArea">
            <h1>Pauta de Categorización de pacientes Riesgo/Dependencia</h1>
            <br>
            <table>
                <thead>
                    <tr>
                        <th colspan="3" style="background-color: #3366CC">CATEGORIA DE CUIDADOS UNIVERSALES</th>
                    </tr>
                    <tr>
                        <th title="Dependencia">1.</th>
                        <th colspan="1">Cuidados en Confort y Bienestar&nbsp;<input id="d1" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Cuidados Básicos requeridos 3 veces al día o mas C/S Familia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d1').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Cuidados Básicos requeridos 2 veces al día o mas C/S Familia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d1').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Usuario y Flia, realizan cuidados con ayuda y supervision, cualquier frecuencia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d1').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Autovalente</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d1').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                <thead>
                    <tr>
                    <th title="Dependencia">2.</th>
                    <th colspan="1">Movilización y Transporte&nbsp;<input id="d2" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Usuario no se levanta y requiere cambios de pocisión 10 o mas veces c/s flia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d2').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Usuario es levantado a silla y cambios de posición 4 a 9 veces c/s flia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d2').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Usuario se levanta y deambula con ayuda, se cambia de pocisión solo o c/s flia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d2').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Usuario deambula sin ayuda y se moviliza solo en la cama</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d2').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                <thead>
                    <tr>
                        <th>3</th>
                    <th colspan="1">Cuidados de alimentación&nbsp;<input id="d3" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Alimentación y/o hidratación parental total/parcial o ayuno prolongado</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d3').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Alimentación por via enteral permanente o discontinua c/s familia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d3').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr>
                    <td colspan="2">Alimentación por via oral, la que es administrada c/s la familia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d3').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Alimentación oral o enteral, con ayuda y/o supervisión</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d3').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr>
                    <td colspan="2">Autovalente</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d3').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>4</th>
                    <th colspan="1">Cuidados de eliminación&nbsp;<input id="d4" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Egresos por sonda, protesis, procedimientos dialiticos, colectores, pañales</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d4').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Egresos x via natural y se le entregan o colocan los colectores (chata, pato)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d4').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Usuario y familia realizan recolección de egresos con ayuda y supervision</td>
                    <td  style="cursor: pointer" onclick="document.getElementById('d4').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Usuario usa colectores (chata, pato) sin ayuda y/o usa WC</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d4').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>5</th>
                    <th colspan="1">Apoyo Psicosocial y emocional&nbsp;<input id="d5" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Recibe mas de 30 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d5').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Recibe entre 15 y 30 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d5').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Recibe entre 5 y 14 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d5').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Recibe menos de 5 min. de apoyo x turno (Conversar, acompañar, escuchar, tomar en brazos)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d5').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>6</th>
                    <th colspan="1">Vigilancia&nbsp;<input id="d6" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Alteración de conciencia y/o conducta insegura (desorientado, confuso, exitado, agresivo)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d6').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Con riesgo de caida o de incidentes (limitacion fisica o cognoscituva y/o > de 70 años y < de 2 años) </td>
                    <td style="cursor: pointer" onclick="document.getElementById('d6').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr>
                    <td colspan="2">Conciente pero intranquilo y c/riesgo de caida o incidente (bajo efectos de farmacos, con 1 o + elementos)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d6').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Conciente pero c/inestabilidad de la marcha o no camina por reposo, edad o alteración fisica</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d6').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr>
                    <td colspan="2">Conciente, orientado, autonomo</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d6').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="3" style="background-color: #3366CC">CATEGORIA DE CUIDADOS TERAPEUTICOS</th>
                    </tr>
                    <tr>
                        <th>7</th>
                    <th colspan="1">Medición de signos vitales&nbsp;<input id="d7" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Control por 8 veces y mas (cada 3 horas o mas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d7').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Control por 4 a 7 veces (cada 4,5,6,7 horas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d7').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Control por 2 a 3 veces (cada 8,9,10,11,12 horas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d7').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Control por 1 vez (cada 13 a cada 24 horas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('d7').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>8</th>
                    <th colspan="1">Balance hidrico&nbsp;<input id="r8" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Balance hidrico por 6 veces o mas  (cada 4 horas o mas frecuente)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r8').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Balance hidrico por 2 a 5 veces (cada 12,8,6 o 5 horas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r8').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Balance hidrico por 1 vez (cada 24 horas o menor de cada 12 horas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r8').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">No requiere</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r8').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>9</th>
                    <th colspan="1">Cuidados oxigenoterapia&nbsp;<input id="r9" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Administración de oxigeno por tubo y canula endotraqueak y/o VMI y VMNI permanente</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r9').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Administración de oxigeno por halo, mascara, incubadora y/o VMNI intermitente</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r9').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Administración de oxigeno por bigotera</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r9').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Sin oxigenoterapia</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r9').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>10</th>
                    <th colspan="1">Cuidados diarios de la via aerea&nbsp;<input id="r10" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Con via aerea artificial (tubo o canula endotraqueal)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r10').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Via aerea artificial y/o natural con 4 o mas aspiraciones secresiones tranqueales y/o apoyo kinesico</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r10').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr>
                    <td colspan="2">Respira x via natural y requiere de 1 a 3 aspiraciones de secresiones y/o apoyo kinesico 2 a 3 veces/día</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r10').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Respira x via natural, sin aspiracion de secresiones y/o apoyo kinesico 1 vez al día</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r10').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr>
                    <td colspan="2">No requiere apoyo ventilatorio adicional</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r10').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>11</th>
                    <th colspan="1">Intervenciones profesionales&nbsp;<input id="r11" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">1 o mas procedimientos invasivos realizados por medicos en la últimas 24 hrs.</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r11').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">3 o mas procedimientos invasivos realizados por enfermera, matrona en la últimas 24 hrs.</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r11').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr>
                    <td colspan="2">1 o 2 procedimientos invasivos realizados por enfermera, matrona en la últimas 24 hrs.</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r11').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">1 o mas procedimientos invasivos realizados por otros prefesionales en las últimas 24 hrs.</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r11').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr>
                    <td colspan="2">No se realizan procedimientos invasivos en 24 hrs.</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r11').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>12</th>
                    <th colspan="1">Cuidados de la piel y curaciones&nbsp;<input id="r12" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Curación o refuerzo 3 o mas veces al día, independiente de la complejidad de la tecnica</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r12').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Curación o refuerzo 1 a 2 veces al día, independiente de la complejidad de la tecnica</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r12').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Prevención compleja de lesiones de piel: uso de colchon antiescara, piel de cordero otros</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r12').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Prevención corriente de lesiones: aseo, lubricación y protección de zonas propensas</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r12').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr>
                    <td colspan="2">No requiere</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r12').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>13</th>
                    <th colspan="1">Administración de TTO farmacologico&nbsp;<input id="r13" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Tratamiento intratecal e inyectable endovenoso, directo o por fleboclisis</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r13').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Tratamiento diario con 5 o más fármacos distintos, administrados por diferentes vías no inyectable</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r13').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr>
                    <td colspan="2">Tratamiento inyectable no endovenoso (IM,SC,ID)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r13').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Tratamiento diario con 2 a 4 fármacos, administrados por diferentes vías no inyectable</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r13').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Tratamiento con 1 fármaco, administrado por diferentes vías no inyectable</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r13').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                   <tr>
                    <td colspan="2">Sin tratamiento farmacológico.</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r13').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
                <thead>
                    <tr>
                        <th>14</th>
                    <th colspan="1">Presencia de elementos invasivos&nbsp;<input id="r14" readonly type="text" size="1" maxlength="1" style="font-size: 16px" value="0"></th>
                    <th colspan="1">Ptje.</th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td colspan="2">Con 3 o mas elementos invasivos (sondas, drenajes, cateteres o vias vasculares)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r14').value='3'">&nbsp;&nbsp;3</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Con 1 a 2 elementos invasivos (sondas, drenajes, via arterial,catetere o via venosa central)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r14').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr>
                    <td colspan="2">Con 2 o mas vias venosas perifericas (mariposas, teflones, agujas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r14').value='2'">&nbsp;&nbsp;2</td>
                </tr>
                <tr class="odd">
                    <td colspan="2">Con 1 via venosa periferica (mariposas, teflones, agujas)</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r14').value='1'">&nbsp;&nbsp;1</td>
                </tr>
                <tr>
                    <td colspan="2">Sin elementos invasivos</td>
                    <td style="cursor: pointer" onclick="document.getElementById('r14').value='0'">&nbsp;&nbsp;0</td>
                </tr>
                </tbody>
            </table>
            <br>
            
            <fieldset>
                <table border="1">
                    <thead>
                    <tr>
                        <td colspan="2" rowspan="2">&nbsp;</td>
                        <th colspan="3">CUIDADOS UNIVERSALES</th>
                    </tr>
                   
                    
                    <tr>
                        <td style="text-align: center">1</td>
                        <td style="text-align: center">2</td>
                        <td style="text-align: center">3</td>
                    </tr>
                    
                    <tr>
                        <th colspan="2">RIESGO TERAPEUTICO</th>
                        <td  class="odd">Dependencia Total (13 a 18 ptos.)</td>
                        <td  class="odd">Dependencia Parcial (7 a 12 ptos.)</td>
                        <td  class="odd">Autovalencia Parcial </td>
                    </tr>
                    </thead>
                    <tr>
                        <td style="background-color: red;">A</td>
                        <td class="odd">Máximo&nbsp;Riesgo&nbsp;Terapeutico&nbsp;(19&nbsp;a&nbsp;24&nbsp;ptos)</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('A1: Máximo Riesgo y Dependencia Total');">A1</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('A2: Máximo Riesgo y Dependencia Parcial');">A2</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('A3: Máximo Riesgo y Autovalencia Parcial');">A3</td>
                    </tr>
                    <tr >
                        <td style="background-color: blue;color: #ffffff">B</td>
                        <td class="odd">Alto Riesgo Terapeutico (12 a 18 ptos)</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('B1: Alto Riesgo y Dependencia Total');">B1</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('B2: Alto Riesgo y Dependencia Parcial');">B2</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('B3: Alto Riesgo y Autovalencia Parcial');">B3</td>
                    </tr>
                    <tr>
                        <td style="background-color: pink">C</td>
                        <td class="odd">Mediano Riesgo Terapeutico (6 a 11 ptos)</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('C1: Mediano Riesgo y Dependencia Total');">C1</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('C2: Mediano Riesgo y Dependencia Parcial');">C2</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('C3: Mediano Riesgo y Autovalencia Parcial');">C3</td>
                    </tr>
                    <tr>
                        <td style="background-color: yellow">D</td>
                        <td class="odd">Bajo Riesgo Terapeutico (0 a 5 ptos)</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('D1: Bajo Riesgo y Dependencia Total');">D1</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('D2: Bajo Riesgo y Dependencia Parcial');">D2</td>
                        <td style="cursor: pointer;text-align: center" onclick="alert('D3: Bajo Riesgo y Autovalencia Parcial');">D3</td>
                    </tr>
                </table>
                <br>
                <center>
                <input type="button" style="width: 90px; height: 40px;background-color: #000000;color: white" onclick="cat()" value="CALCULAR">
                </center>
                </fieldset>
            <div id="LBLCAT">Resultado</div>
            <script>
                function cat()
        {

        //alert('Alto: '+document.getElementById('r8').value);
        var riesgo=parseInt(document.getElementById('r8').value)+parseInt(document.getElementById('r9').value)+parseInt(document.getElementById('r10').value)+parseInt(document.getElementById('r11').value)+parseInt(document.getElementById('r12').value)+parseInt(document.getElementById('r13').value)+parseInt(document.getElementById('r14').value);
        var dependencia=parseInt(document.getElementById('d1').value)+parseInt(document.getElementById('d2').value)+parseInt(document.getElementById('d3').value)+parseInt(document.getElementById('d4').value)+parseInt(document.getElementById('d5').value)+parseInt(document.getElementById('d6').value)+parseInt(document.getElementById('d7').value);
       var letra='';var num=0;
       if(riesgo>=0 & riesgo<6)
           {letra='D';}
       if(riesgo>5 & riesgo<12)
           {letra='C';}
       if(riesgo>11 & riesgo<19)
           {letra='B';}
       if(riesgo>18 & riesgo<25)
           {letra='A';}
       if(dependencia>=0 & dependencia<7)    
           {num=3;}
       if(dependencia>6 & dependencia<13)    
           {num=2;}
       if(dependencia>12 & dependencia<19)    
           {num=1;}    
      alert(letra+num);
      //alert('Riesgo '+riesgo+' Letra='+letra);alert('Dependencia '+dependencia+' Numero='+num);
        var ajax3=nuevoAjax();
        ajax3.open('POST', 'Categorizo.jsp?riesgo='+riesgo+'&dependencia='+dependencia, true);
        ajax3.send(null);
        if (ajax3.readyState==1)
            {
            document.getElementById('LBLCAT').innerHTML='Calculando...';
            }
        ajax3.onreadystatechange=function()
            {
                if (ajax3.readyState==4)
                {
                  //document.getElementById('17').value=ajax3.responseText;
                  document.getElementById('LBLCAT').innerHTML='<h1>De acuerdo a los datos ingresados este pacientes es Categorizado como: '+ajax3.responseText+'</h1>';
                }
            }

        }
            </script>
<%@ include file="../Footer.jsp"%>
