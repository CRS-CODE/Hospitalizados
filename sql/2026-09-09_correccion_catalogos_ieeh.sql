-- =====================================================================
-- Correccion de catalogos + campos nuevos segun formulario oficial
-- "Informe Estadistico de Egreso Hospitalario" (IEEH) 2025 - MINSAL/DEIS
-- Modulo: modulo_uhce - Ingreso de paciente (admision_ugu_carga.jsp)
-- Fecha  : 2026-09-09
--
-- Bioestadistica entrego el formulario oficial (PDF) el 2026-09-09. Este
-- script reemplaza los valores PROVISORIOS de los scripts anteriores
-- (2026-08-27 y 2026-08-28) por los codigos oficiales, y agrega las
-- estructuras nuevas que el formulario deja en evidencia que faltaban:
--
--   12/nuevo  CATEGORIA OCUPACIONAL (se corrige) + OCUPACION DECLARADA (nueva,
--             sub-clasificacion de 2do nivel, solo aplica si categoria = Activos)
--   13        NIVEL DE INSTRUCCION (se corrige)
--   21        LEYES PREVISIONALES (se corrige)
--   53        PUEBLO AFRODESCENDIENTE CHILENO (se corrige)
--   54        IDENTIDAD DE GENERO (se corrige)
--   16        TIPO DE VIA de la direccion (se corrige)
--   55/nuevo  GATE "se considera perteneciente a pueblo indigena u originario"
--             (nuevo, SI/NO; si es SI se despliega el select de PUEBLO INDIGENA
--             ya existente, campo 10 = schemaoirs.pueblo_originario)
--   11/nuevo  PAIS DE ORIGEN DEL PACIENTE (nuevo, texto libre, separado de
--             NACIONALIDAD segun decision explicita de Bioestadistica de
--             2026-09-09: se mantienen como DOS campos distintos)
--   22/nuevo  PROCEDENCIA DEL PACIENTE segun clasificacion oficial (nueva,
--             6 codigos: Unidad Emergencia / Atencion especialidades / Otro
--             Establecimiento / Otra Procedencia / Cirugia Mayor Ambulatoria /
--             Hospital comunitario o baja complejidad). Es DISTINTA y
--             ADICIONAL al campo "Procedencia del paciente" (id_derivado)
--             que ya existia, el cual apunta a schema_uo.derivador (listado
--             de establecimientos/servicios derivadores, no a esta
--             clasificacion oficial de 6 categorias).
--   23/nuevo  ESTABLECIMIENTO DE PROCEDENCIA (nuevo, solo se completa cuando
--             el campo 22 = 4 "Otro Establecimiento" o 7 "Hospital
--             comunitario o de baja complejidad").
--
-- Este script es IDEMPOTENTE (se puede correr mas de una vez sin error).
-- Requiere haber ejecutado antes los dos scripts anteriores (2026-08-27 y
-- 2026-08-28), porque reutiliza/corrige tablas creadas en ellos.
--
-- IMPORTANTE - LEER ANTES DE EJECUTAR EN PRODUCCION:
-- Las secciones 1 y 2 de abajo CORRIGEN datos de catalogos que ya podrian
-- estar en uso por pacientes ya registrados en modulo_uhce desde el
-- 2026-08-27 en adelante. Se incluyen consultas de verificacion (solo
-- lectura) ANTES de cada seccion riesgosa: ejecutenlas primero y, si
-- devuelven filas, avisenme antes de continuar con esa seccion, para
-- decidir juntos como migrar esos registros puntuales (para
-- leyes_previsionales, nivel_instruccion, tipo_via, identidad_genero y
-- pueblo_afrodescendiente el script YA incluye un remapeo automatico
-- razonable de los datos existentes hacia los codigos oficiales; para
-- categoria_ocupacional NO se incluye remapeo automatico porque el
-- esquema anterior no es equivalente al oficial, y forzar una
-- equivalencia seria inventar datos que el paciente nunca declaro -- si
-- existen registros, el DELETE de la seccion 2 fallara a proposito por la
-- llave foranea, como aviso de que hay que revisarlos manualmente).
-- =====================================================================


-- =====================================================================
-- SECCION 0: Verificacion de datos ya registrados (SOLO LECTURA)
-- Ejecutar y revisar el resultado ANTES de seguir con las secciones 1-6.
-- =====================================================================
-- SELECT id_ley_previsional, COUNT(*) FROM agenda.paciente WHERE id_ley_previsional IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_nivel_instruccion, COUNT(*) FROM agenda.paciente WHERE id_nivel_instruccion IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_tipo_via, COUNT(*) FROM agenda.paciente WHERE id_tipo_via IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_identidad_genero, COUNT(*) FROM agenda.paciente WHERE id_identidad_genero IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_pueblo_afrodescendiente, COUNT(*) FROM agenda.paciente WHERE id_pueblo_afrodescendiente IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_categoria_ocupacional, COUNT(*) FROM agenda.paciente WHERE id_categoria_ocupacional IS NOT NULL GROUP BY 1 ORDER BY 1;


BEGIN;

-- ---------------------------------------------------------------------
-- 1) Remapeo de datos ya guardados con la codificacion PROVISORIA
--    anterior hacia los codigos OFICIALES, antes de reemplazar cada
--    catalogo. Si no hay ningun paciente registrado todavia con estos
--    campos, estos UPDATE simplemente no afectan ninguna fila (no
--    generan error).
--
--    Se usa un offset temporal (+1000) para poder remapear sin choques
--    aunque el codigo viejo y el nuevo compartan el mismo numero de id
--    con distinto significado (por ejemplo en identidad_genero, donde
--    "1" y "2" cambian de significado).
-- ---------------------------------------------------------------------

-- 1a) Leyes previsionales
--     viejo 1 Ninguna                                  -> nuevo 96 Ninguna
--     viejo 2 Ley 16.744 trabajo                        -> nuevo 2  (sin cambio de significado)
--     viejo 3 "Decreto 313 Seguro Escolar" (mi correccion anterior, incorrecta) -> nuevo 3 Ley 16.744 Accidente escolar
--     viejo 4 PRAIS                                      -> nuevo 5  PRAIS
--     viejo 5 "Ley 18.490 SOAP"                          -> nuevo 1  Ley 18.490 Accidentes de transporte (mismo numero de ley)
--     viejo 99 Otra/Ignorado                             -> nuevo 97 No recuerda
UPDATE agenda.paciente SET id_ley_previsional = 1000 + id_ley_previsional
 WHERE id_ley_previsional IN (1,2,3,4,5,99);
UPDATE agenda.paciente SET id_ley_previsional = 96 WHERE id_ley_previsional = 1001;
UPDATE agenda.paciente SET id_ley_previsional = 2  WHERE id_ley_previsional = 1002;
UPDATE agenda.paciente SET id_ley_previsional = 3  WHERE id_ley_previsional = 1003;
UPDATE agenda.paciente SET id_ley_previsional = 5  WHERE id_ley_previsional = 1004;
UPDATE agenda.paciente SET id_ley_previsional = 1  WHERE id_ley_previsional = 1005;
UPDATE agenda.paciente SET id_ley_previsional = 97 WHERE id_ley_previsional = 1099;

-- 1b) Nivel de instruccion (esquema viejo mas granular -> esquema oficial mas agregado)
--     viejo 1 Sin escolaridad                 -> nuevo 6 Sin instruccion
--     viejo 2/3 Basica incompleta/completa    -> nuevo 3 Basica o primaria
--     viejo 4/5 Media incompleta/completa     -> nuevo 4 Media o secundaria
--     viejo 6/7/8/9/10 Tecnica.../Universitaria.../Postgrado -> nuevo 5 Educacion superior
--     viejo 99 Ignorado/No informa            -> nuevo 98 No responde
UPDATE agenda.paciente SET id_nivel_instruccion = 1000 + id_nivel_instruccion
 WHERE id_nivel_instruccion IN (1,2,3,4,5,6,7,8,9,10,99);
UPDATE agenda.paciente SET id_nivel_instruccion = 6  WHERE id_nivel_instruccion = 1001;
UPDATE agenda.paciente SET id_nivel_instruccion = 3  WHERE id_nivel_instruccion IN (1002,1003);
UPDATE agenda.paciente SET id_nivel_instruccion = 4  WHERE id_nivel_instruccion IN (1004,1005);
UPDATE agenda.paciente SET id_nivel_instruccion = 5  WHERE id_nivel_instruccion IN (1006,1007,1008,1009,1010);
UPDATE agenda.paciente SET id_nivel_instruccion = 98 WHERE id_nivel_instruccion = 1099;

-- 1c) Tipo de via (ids 1-4 no cambian de significado; 5/6/7/8 no tienen
--     equivalente oficial y se remapean a "Otro"; el 9 viejo "Otra" y el
--     9 nuevo "Otro" ya coinciden en id, no requiere remapeo)
UPDATE agenda.paciente SET id_tipo_via = 9 WHERE id_tipo_via IN (5,6,7,8);

-- 1d) Identidad de genero
--     viejo 1 Mujer        -> nuevo 2 Femenina
--     viejo 2 Hombre       -> nuevo 1 Masculino
--     viejo 3 Mujer trans  -> nuevo 5 Transgenero Femenina
--     viejo 4 Hombre trans -> nuevo 4 Transgenero Masculino (mismo id)
--     viejo 5 No binario   -> nuevo 6 No binarie
--     viejo 6 Otra         -> nuevo 7 Otra
--     viejo 9 Prefiere no decir/No informa -> nuevo 8 No Revelado
UPDATE agenda.paciente SET id_identidad_genero = 1000 + id_identidad_genero
 WHERE id_identidad_genero IN (1,2,3,5,6,9);
UPDATE agenda.paciente SET id_identidad_genero = 2 WHERE id_identidad_genero = 1001;
UPDATE agenda.paciente SET id_identidad_genero = 1 WHERE id_identidad_genero = 1002;
UPDATE agenda.paciente SET id_identidad_genero = 5 WHERE id_identidad_genero = 1003;
UPDATE agenda.paciente SET id_identidad_genero = 6 WHERE id_identidad_genero = 1005;
UPDATE agenda.paciente SET id_identidad_genero = 7 WHERE id_identidad_genero = 1006;
UPDATE agenda.paciente SET id_identidad_genero = 8 WHERE id_identidad_genero = 1009;

-- 1e) Pueblo afrodescendiente chileno: los ids 1 (Si) y 2 (No) no cambian
--     de significado. El viejo id 9 ("No sabe/No responde") no tiene
--     equivalente en el formulario oficial (solo permite SI/NO): se deja
--     en NULL para que Bioestadistica revise manualmente esos casos
--     puntuales si es que existen.
UPDATE agenda.paciente SET id_pueblo_afrodescendiente = NULL WHERE id_pueblo_afrodescendiente = 9;

COMMIT;


BEGIN;

-- ---------------------------------------------------------------------
-- 2) Reemplazo de los catalogos por los valores OFICIALES (una vez
--    remapeados los datos existentes en la seccion 1, estos DELETE ya
--    no deberian tener filas de agenda.paciente apuntandolos, salvo
--    categoria_ocupacional que se deja sin remapeo automatico a
--    proposito, ver nota mas arriba).
-- ---------------------------------------------------------------------

-- Campo 21: Leyes Previsionales (lista oficial completa, 10 items)
DELETE FROM schemaoirs.leyes_previsionales WHERE ley_prev_id NOT IN (1,2,3,4,5,6,7,8,96,97);
INSERT INTO schemaoirs.leyes_previsionales (ley_prev_id, ley_prev_descripcion, ley_prev_estado) VALUES
    (1, 'Ley 18.490: Accidentes de transporte', 1),
    (2, 'Ley 16.744: Accidentes de trabajo y enfermedades profesionales', 1),
    (3, 'Ley 16.744: Accidente escolar', 1),
    (4, 'Ley 19.659/99 de urgencia', 1),
    (5, 'Ley 19.992 PRAIS', 1),
    (6, 'Ley N 19.966: Regimen General de Garantias en Salud (GES)', 1),
    (7, 'Ley N 20.850: Ricarte Soto', 1),
    (8, 'N 21.030: Despenalizacion de la Interrupcion Voluntaria del Embarazo en tres Causales', 1),
    (96, 'Ninguna', 1),
    (97, 'No recuerda', 1)
ON CONFLICT (ley_prev_id) DO UPDATE SET
    ley_prev_descripcion = EXCLUDED.ley_prev_descripcion,
    ley_prev_estado = EXCLUDED.ley_prev_estado;

-- Campo 13: Nivel de Instruccion (lista oficial completa, 8 items)
DELETE FROM schemaoirs.nivel_instruccion WHERE niv_ins_id NOT IN (1,2,3,4,5,6,97,98);
INSERT INTO schemaoirs.nivel_instruccion (niv_ins_id, niv_ins_descripcion, niv_ins_estado) VALUES
    (1, 'Preescolar', 1),
    (2, 'Especial o diferencial', 1),
    (3, 'Basica o primaria', 1),
    (4, 'Media o secundaria', 1),
    (5, 'Educacion superior', 1),
    (6, 'Sin instruccion', 1),
    (97, 'No recuerda', 1),
    (98, 'No responde', 1)
ON CONFLICT (niv_ins_id) DO UPDATE SET
    niv_ins_descripcion = EXCLUDED.niv_ins_descripcion,
    niv_ins_estado = EXCLUDED.niv_ins_estado;

-- Campo 16: Tipo de Via (lista oficial completa, 10 items)
DELETE FROM schemaoirs.tipo_via WHERE via_id NOT IN (1,2,3,4,5,6,7,8,9,10);
INSERT INTO schemaoirs.tipo_via (via_id, via_descripcion, via_estado) VALUES
    (1, 'Calle', 1),
    (2, 'Avenida', 1),
    (3, 'Pasaje', 1),
    (4, 'Camino', 1),
    (5, 'Carretera', 1),
    (6, 'Callejon', 1),
    (7, 'Paseo', 1),
    (8, 'Escalera', 1),
    (9, 'Otro', 1),
    (10, 'Rotonda', 1)
ON CONFLICT (via_id) DO UPDATE SET
    via_descripcion = EXCLUDED.via_descripcion,
    via_estado = EXCLUDED.via_estado;

-- Campo 54: Identidad de Genero (lista oficial completa, 7 items, sin codigo 3)
DELETE FROM schemaoirs.identidad_genero WHERE gen_id NOT IN (1,2,4,5,6,7,8);
INSERT INTO schemaoirs.identidad_genero (gen_id, gen_descripcion, gen_estado) VALUES
    (1, 'Masculino', 1),
    (2, 'Femenina', 1),
    (4, 'Transgenero Masculino', 1),
    (5, 'Transgenero Femenina', 1),
    (6, 'No binarie', 1),
    (7, 'Otra', 1),
    (8, 'No Revelado', 1)
ON CONFLICT (gen_id) DO UPDATE SET
    gen_descripcion = EXCLUDED.gen_descripcion,
    gen_estado = EXCLUDED.gen_estado;

-- Campo 53: Pueblo Afrodescendiente Chileno (lista oficial, solo SI/NO)
DELETE FROM schemaoirs.pueblo_afrodescendiente WHERE afro_id NOT IN (1,2);
INSERT INTO schemaoirs.pueblo_afrodescendiente (afro_id, afro_descripcion, afro_estado) VALUES
    (1, 'SI', 1),
    (2, 'NO', 1)
ON CONFLICT (afro_id) DO UPDATE SET
    afro_descripcion = EXCLUDED.afro_descripcion,
    afro_estado = EXCLUDED.afro_estado;

-- Campo 12: Categoria Ocupacional (lista oficial, solo 4 items).
-- ADVERTENCIA: a diferencia de los catalogos anteriores, este DELETE NO
-- tiene un remapeo automatico previo (ver Seccion 0 y la nota al inicio
-- del script). Si ya existen pacientes con id_categoria_ocupacional
-- usando los codigos provisorios antiguos (5 al 10), este DELETE va a
-- fallar por la llave foranea fk_paciente_categoria_ocupacional. Eso es
-- intencional: si pasa, avisar antes de continuar para decidir la
-- migracion caso a caso (no se puede automatizar sin inventar datos).
DELETE FROM schemaoirs.categoria_ocupacional WHERE cat_ocu_id NOT IN (1,2,3,99);
INSERT INTO schemaoirs.categoria_ocupacional (cat_ocu_id, cat_ocu_descripcion, cat_ocu_estado) VALUES
    (1, 'Inactivos', 1),
    (2, 'Activos', 1),
    (3, 'Cesante o temporalmente sin trabajo', 1),
    (99, 'Ignorado', 1)
ON CONFLICT (cat_ocu_id) DO UPDATE SET
    cat_ocu_descripcion = EXCLUDED.cat_ocu_descripcion,
    cat_ocu_estado = EXCLUDED.cat_ocu_estado;

COMMIT;


BEGIN;

-- ---------------------------------------------------------------------
-- 3) Campo 12 (2do nivel): Ocupacion Declarada. Solo tiene sentido
--    completarla cuando Categoria Ocupacional = Activos (id 2); la
--    pantalla la mostrara/ocultara segun esa regla.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS schemaoirs.ocupacion_declarada (
    ocu_dec_id            integer PRIMARY KEY,
    ocu_dec_descripcion   varchar(250) NOT NULL,
    ocu_dec_estado        smallint NOT NULL DEFAULT 1,
    ocu_dec_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.ocupacion_declarada (ocu_dec_id, ocu_dec_descripcion, ocu_dec_estado) VALUES
    (1, 'Miembro del poder ejecutivo de los cuerpos legislativos, personal directivo de la administracion publica y de empresa', 1),
    (2, 'Profesionales cientificos o intelectuales', 1),
    (3, 'Tecnicos y profesionales de nivel medio', 1),
    (4, 'Empleados de oficina', 1),
    (5, 'Trabajadores de los servicios y vendedores de comercio y mercado', 1),
    (6, 'Agricultores y trabajadores calificados agropecuarios y pesqueros', 1),
    (7, 'Oficiales, operarios y artesanos de artes mecanicas y de otros oficios', 1),
    (8, 'Operadores de instalaciones y maquinas y montadores', 1),
    (9, 'Trabajadores no calificados', 1),
    (10, 'Fuerzas armadas', 1),
    (99, 'Desconocido', 1)
ON CONFLICT (ocu_dec_id) DO UPDATE SET
    ocu_dec_descripcion = EXCLUDED.ocu_dec_descripcion,
    ocu_dec_estado = EXCLUDED.ocu_dec_estado;

ALTER TABLE agenda.paciente
    ADD COLUMN IF NOT EXISTS id_ocupacion_declarada integer;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_ocupacion_declarada') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_ocupacion_declarada
            FOREIGN KEY (id_ocupacion_declarada) REFERENCES schemaoirs.ocupacion_declarada (ocu_dec_id);
    END IF;
END $$;

-- ---------------------------------------------------------------------
-- 4) Campo 55: gate "Se considera perteneciente a algun pueblo indigena
--    u originario" (SI/NO). Si es SI, se despliega el select de Pueblo
--    Indigena ya existente (campo 10 = schemaoirs.pueblo_originario /
--    columna agenda.paciente.id_puebloorigen, sin cambios).
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS schemaoirs.pueblo_originario_gate (
    gate_id            integer PRIMARY KEY,
    gate_descripcion   varchar(10) NOT NULL,
    gate_estado        smallint NOT NULL DEFAULT 1,
    gate_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.pueblo_originario_gate (gate_id, gate_descripcion, gate_estado) VALUES
    (1, 'SI', 1),
    (2, 'NO', 1)
ON CONFLICT (gate_id) DO NOTHING;

ALTER TABLE agenda.paciente
    ADD COLUMN IF NOT EXISTS id_pueblo_originario_gate integer;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_pueblo_originario_gate') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_pueblo_originario_gate
            FOREIGN KEY (id_pueblo_originario_gate) REFERENCES schemaoirs.pueblo_originario_gate (gate_id);
    END IF;
END $$;

-- ---------------------------------------------------------------------
-- 5) Campo 11: Pais de Origen del paciente (texto libre, NUEVO,
--    independiente de Nacionalidad segun decision de Bioestadistica del
--    2026-09-09 de mantener los dos campos por separado).
-- ---------------------------------------------------------------------
ALTER TABLE agenda.paciente
    ADD COLUMN IF NOT EXISTS pais_origen varchar(100);

-- ---------------------------------------------------------------------
-- 6) Campos 22/23: Procedencia del paciente segun clasificacion OFICIAL
--    (6 codigos) + Establecimiento de Procedencia (solo si opcion 4 o 7).
--    Es ADICIONAL al campo id_derivado ya existente (listado de
--    establecimientos/servicios derivadores en schema_uo.derivador), que
--    no se modifica.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS schemaoirs.procedencia_paciente (
    proc_id            integer PRIMARY KEY,
    proc_descripcion   varchar(150) NOT NULL,
    proc_estado        smallint NOT NULL DEFAULT 1,
    proc_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.procedencia_paciente (proc_id, proc_descripcion, proc_estado) VALUES
    (1, 'Unidad Emergencia (mismo establecimiento)', 1),
    (3, 'Atencion especialidades (mismo establecimiento)', 1),
    (4, 'Otro Establecimiento', 1),
    (5, 'Otra Procedencia', 1),
    (6, 'Area de Cirugia Mayor Ambulatoria (mismo establecimiento)', 1),
    (7, 'Hospital comunitario o de baja complejidad', 1)
ON CONFLICT (proc_id) DO UPDATE SET
    proc_descripcion = EXCLUDED.proc_descripcion,
    proc_estado = EXCLUDED.proc_estado;

ALTER TABLE agenda.paciente
    ADD COLUMN IF NOT EXISTS id_procedencia_servicio integer,
    ADD COLUMN IF NOT EXISTS establecimiento_procedencia varchar(150);

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_procedencia_servicio') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_procedencia_servicio
            FOREIGN KEY (id_procedencia_servicio) REFERENCES schemaoirs.procedencia_paciente (proc_id);
    END IF;
END $$;

COMMIT;

-- =====================================================================
-- 7) SOLO LECTURA - revisiones que requieren criterio de Bioestadistica
--    y que no puedo hacer yo porque no tengo acceso a la base de datos.
-- =====================================================================

-- 7a) Prevision (campo 18 del formulario oficial): confirmar que existan
--     y esten activas las siguientes previsiones. Los codigos oficiales
--     del formulario son: 1 Fonasa, 2 Isapre, 3 Capredena, 4 Dipreca,
--     5 SISA, 96 Ninguna, 99 Desconocido (no implica que agenda.prevision
--     deba usar exactamente estos ids; solo sirve para verificar que las
--     6 categorias existan como opciones, especialmente Capredena,
--     Dipreca y SISA que hoy no se reciben pero Bioestadistica pidio
--     tenerlas disponibles igual).
--
-- SELECT id_prevision, nombre, estatus FROM agenda.prevision ORDER BY id_prevision;
--
-- Ejemplo para activar una prevision ya existente pero inactiva (ajustar
-- el id real segun lo que devuelva el SELECT de arriba):
-- UPDATE agenda.prevision SET estatus = 1 WHERE id_prevision = <id_capredena>;
--
-- Si Capredena, Dipreca y/o SISA NO existen como filas, hay que
-- insertarlas (ajustar los ids libres segun corresponda):
-- INSERT INTO agenda.prevision (id_prevision, nombre, estatus) VALUES
--     (<id_libre_1>, 'Capredena', 1),
--     (<id_libre_2>, 'Dipreca', 1),
--     (<id_libre_3>, 'SISA', 1);

-- 7b) Pueblo Originario (campo 10, schemaoirs.pueblo_originario): NO se
--     modifico en este script porque ya existia y ya estaba en uso; solo
--     se deja la consulta para comparar contra la lista oficial de 12
--     pueblos (Mapuche, Aymara, Rapa Nui o pascuense, Lickanantay,
--     Quechua, Colla, Diaguita, Kawesqar, Yagan, Otro (especificar),
--     Chango, Selk'nam):
--
-- SELECT pue_id, pue_descripcion, pue_estado FROM schemaoirs.pueblo_originario ORDER BY pue_id;

-- =====================================================================
