-- =====================================================================
-- Migracion completa - Campos IEEH en modulo_uhce
-- Compatible con PostgreSQL 9.3
-- Fecha: 2026-09-10
--
-- Este script REEMPLAZA y deja sin efecto los tres scripts anteriores:
--   2026-08-27_ume_bioestadistica.sql
--   2026-08-28_ajustes_bioestadistica.sql
--   2026-09-09_correccion_catalogos_ieeh.sql
-- porque esos tres usan dos sintaxis que PostgreSQL 9.3 NO soporta:
--   - "ON CONFLICT ... DO UPDATE / DO NOTHING" (recien existe desde
--     PostgreSQL 9.5)
--   - "ALTER TABLE ... ADD COLUMN IF NOT EXISTS" (recien existe desde
--     PostgreSQL 9.6)
-- Este script hace exactamente lo mismo que los tres anteriores, pero
-- usando bloques DO/plpgsql (disponibles desde PostgreSQL 9.0) en vez de
-- esas dos sintaxis. Es seguro ejecutarlo aunque los scripts anteriores
-- se hayan alcanzado a ejecutar parcialmente (o no), porque cada paso
-- revisa primero si ya existe antes de crear/agregar/modificar.
--
-- NO hace falta ejecutar los tres scripts anteriores: basta con este.
--
-- Cubre los mismos campos que ya se explicaron antes:
--   12  CATEGORIA OCUPACIONAL (+ OCUPACION DECLARADA, 2do nivel)
--   13  NIVEL DE INSTRUCCION
--   16  TIPO DE VIA (direccion)
--   21  LEYES PREVISIONALES
--   53  PUEBLO AFRODESCENDIENTE CHILENO
--   54  IDENTIDAD DE GENERO
--   55  Gate "pertenece a pueblo indigena u originario" (SI/NO)
--   11  PAIS DE ORIGEN (texto libre, separado de Nacionalidad)
--   22  PROCEDENCIA DEL PACIENTE (clasificacion oficial, 6 codigos)
--   23  ESTABLECIMIENTO DE PROCEDENCIA (condicional a 22 = 4 o 7)
--
-- IMPORTANTE: antes de ejecutar en produccion, correr primero en un
-- ambiente de prueba. Revisar tambien la Seccion 0 (solo lectura) para
-- saber si ya existen pacientes registrados con los catalogos
-- provisorios anteriores.
-- =====================================================================


-- =====================================================================
-- SECCION 0: Verificacion de datos ya registrados (SOLO LECTURA)
-- =====================================================================
-- SELECT id_ley_previsional, COUNT(*) FROM agenda.paciente WHERE id_ley_previsional IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_nivel_instruccion, COUNT(*) FROM agenda.paciente WHERE id_nivel_instruccion IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_tipo_via, COUNT(*) FROM agenda.paciente WHERE id_tipo_via IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_identidad_genero, COUNT(*) FROM agenda.paciente WHERE id_identidad_genero IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_pueblo_afrodescendiente, COUNT(*) FROM agenda.paciente WHERE id_pueblo_afrodescendiente IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT id_categoria_ocupacional, COUNT(*) FROM agenda.paciente WHERE id_categoria_ocupacional IS NOT NULL GROUP BY 1 ORDER BY 1;
-- SELECT version(); -- para reconfirmar la version real del servidor


BEGIN;

-- ---------------------------------------------------------------------
-- 1) Catalogos (estructura). CREATE TABLE IF NOT EXISTS ya es valido
--    desde PostgreSQL 9.1, no necesita cambios.
-- ---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS schemaoirs.categoria_ocupacional (
    cat_ocu_id            integer PRIMARY KEY,
    cat_ocu_descripcion   varchar(150) NOT NULL,
    cat_ocu_estado        smallint NOT NULL DEFAULT 1,
    cat_ocu_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.nivel_instruccion (
    niv_ins_id            integer PRIMARY KEY,
    niv_ins_descripcion   varchar(150) NOT NULL,
    niv_ins_estado        smallint NOT NULL DEFAULT 1,
    niv_ins_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.leyes_previsionales (
    ley_prev_id            integer PRIMARY KEY,
    ley_prev_descripcion   varchar(200) NOT NULL,
    ley_prev_estado        smallint NOT NULL DEFAULT 1,
    ley_prev_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.pueblo_afrodescendiente (
    afro_id            integer PRIMARY KEY,
    afro_descripcion   varchar(150) NOT NULL,
    afro_estado        smallint NOT NULL DEFAULT 1,
    afro_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.identidad_genero (
    gen_id            integer PRIMARY KEY,
    gen_descripcion   varchar(150) NOT NULL,
    gen_estado        smallint NOT NULL DEFAULT 1,
    gen_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.tipo_via (
    via_id            integer PRIMARY KEY,
    via_descripcion   varchar(60) NOT NULL,
    via_estado        smallint NOT NULL DEFAULT 1,
    via_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.ocupacion_declarada (
    ocu_dec_id            integer PRIMARY KEY,
    ocu_dec_descripcion   varchar(250) NOT NULL,
    ocu_dec_estado        smallint NOT NULL DEFAULT 1,
    ocu_dec_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.pueblo_originario_gate (
    gate_id            integer PRIMARY KEY,
    gate_descripcion   varchar(10) NOT NULL,
    gate_estado        smallint NOT NULL DEFAULT 1,
    gate_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS schemaoirs.procedencia_paciente (
    proc_id            integer PRIMARY KEY,
    proc_descripcion   varchar(150) NOT NULL,
    proc_estado        smallint NOT NULL DEFAULT 1,
    proc_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------------
-- 2) Columnas nuevas en agenda.paciente. En PostgreSQL 9.3 no existe
--    "ADD COLUMN IF NOT EXISTS", asi que se reemplaza por un bloque DO
--    que revisa information_schema.columns antes de cada ALTER TABLE.
-- ---------------------------------------------------------------------
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_categoria_ocupacional') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_categoria_ocupacional integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_nivel_instruccion') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_nivel_instruccion integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_ley_previsional') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_ley_previsional integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_pueblo_afrodescendiente') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_pueblo_afrodescendiente integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_identidad_genero') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_identidad_genero integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_tipo_via') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_tipo_via integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_ocupacion_declarada') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_ocupacion_declarada integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_pueblo_originario_gate') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_pueblo_originario_gate integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='pais_origen') THEN
        ALTER TABLE agenda.paciente ADD COLUMN pais_origen varchar(100);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='id_procedencia_servicio') THEN
        ALTER TABLE agenda.paciente ADD COLUMN id_procedencia_servicio integer;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema='agenda' AND table_name='paciente' AND column_name='establecimiento_procedencia') THEN
        ALTER TABLE agenda.paciente ADD COLUMN establecimiento_procedencia varchar(150);
    END IF;
END $$;

-- ---------------------------------------------------------------------
-- 3) Llaves foraneas (mismo patron de siempre: bloque DO + pg_constraint,
--    ya era compatible con PostgreSQL 9.3, no requiere cambios).
-- ---------------------------------------------------------------------
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_categoria_ocupacional') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_categoria_ocupacional
            FOREIGN KEY (id_categoria_ocupacional) REFERENCES schemaoirs.categoria_ocupacional (cat_ocu_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_nivel_instruccion') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_nivel_instruccion
            FOREIGN KEY (id_nivel_instruccion) REFERENCES schemaoirs.nivel_instruccion (niv_ins_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_ley_previsional') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_ley_previsional
            FOREIGN KEY (id_ley_previsional) REFERENCES schemaoirs.leyes_previsionales (ley_prev_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_pueblo_afrodescendiente') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_pueblo_afrodescendiente
            FOREIGN KEY (id_pueblo_afrodescendiente) REFERENCES schemaoirs.pueblo_afrodescendiente (afro_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_identidad_genero') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_identidad_genero
            FOREIGN KEY (id_identidad_genero) REFERENCES schemaoirs.identidad_genero (gen_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_tipo_via') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_tipo_via
            FOREIGN KEY (id_tipo_via) REFERENCES schemaoirs.tipo_via (via_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_ocupacion_declarada') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_ocupacion_declarada
            FOREIGN KEY (id_ocupacion_declarada) REFERENCES schemaoirs.ocupacion_declarada (ocu_dec_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_pueblo_originario_gate') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_pueblo_originario_gate
            FOREIGN KEY (id_pueblo_originario_gate) REFERENCES schemaoirs.pueblo_originario_gate (gate_id);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_procedencia_servicio') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_procedencia_servicio
            FOREIGN KEY (id_procedencia_servicio) REFERENCES schemaoirs.procedencia_paciente (proc_id);
    END IF;
END $$;

COMMIT;


BEGIN;

-- ---------------------------------------------------------------------
-- 4) Remapeo de datos ya guardados con codificacion PROVISORIA anterior
--    (si es que alguno de los scripts viejos alcanzo a insertar datos de
--    prueba). Son UPDATE simples, sin ON CONFLICT ni IF NOT EXISTS: ya
--    eran compatibles con PostgreSQL 9.3. No afectan nada si no hay
--    filas que remapear.
-- ---------------------------------------------------------------------
UPDATE agenda.paciente SET id_ley_previsional = 1000 + id_ley_previsional
 WHERE id_ley_previsional IN (1,2,3,4,5,99);
UPDATE agenda.paciente SET id_ley_previsional = 96 WHERE id_ley_previsional = 1001;
UPDATE agenda.paciente SET id_ley_previsional = 2  WHERE id_ley_previsional = 1002;
UPDATE agenda.paciente SET id_ley_previsional = 3  WHERE id_ley_previsional = 1003;
UPDATE agenda.paciente SET id_ley_previsional = 5  WHERE id_ley_previsional = 1004;
UPDATE agenda.paciente SET id_ley_previsional = 1  WHERE id_ley_previsional = 1005;
UPDATE agenda.paciente SET id_ley_previsional = 97 WHERE id_ley_previsional = 1099;

UPDATE agenda.paciente SET id_nivel_instruccion = 1000 + id_nivel_instruccion
 WHERE id_nivel_instruccion IN (1,2,3,4,5,6,7,8,9,10,99);
UPDATE agenda.paciente SET id_nivel_instruccion = 6  WHERE id_nivel_instruccion = 1001;
UPDATE agenda.paciente SET id_nivel_instruccion = 3  WHERE id_nivel_instruccion IN (1002,1003);
UPDATE agenda.paciente SET id_nivel_instruccion = 4  WHERE id_nivel_instruccion IN (1004,1005);
UPDATE agenda.paciente SET id_nivel_instruccion = 5  WHERE id_nivel_instruccion IN (1006,1007,1008,1009,1010);
UPDATE agenda.paciente SET id_nivel_instruccion = 98 WHERE id_nivel_instruccion = 1099;

UPDATE agenda.paciente SET id_tipo_via = 9 WHERE id_tipo_via IN (5,6,7,8);

UPDATE agenda.paciente SET id_identidad_genero = 1000 + id_identidad_genero
 WHERE id_identidad_genero IN (1,2,3,5,6,9);
UPDATE agenda.paciente SET id_identidad_genero = 2 WHERE id_identidad_genero = 1001;
UPDATE agenda.paciente SET id_identidad_genero = 1 WHERE id_identidad_genero = 1002;
UPDATE agenda.paciente SET id_identidad_genero = 5 WHERE id_identidad_genero = 1003;
UPDATE agenda.paciente SET id_identidad_genero = 6 WHERE id_identidad_genero = 1005;
UPDATE agenda.paciente SET id_identidad_genero = 7 WHERE id_identidad_genero = 1006;
UPDATE agenda.paciente SET id_identidad_genero = 8 WHERE id_identidad_genero = 1009;

UPDATE agenda.paciente SET id_pueblo_afrodescendiente = NULL WHERE id_pueblo_afrodescendiente = 9;

COMMIT;


BEGIN;

-- ---------------------------------------------------------------------
-- 5) Limpieza de codigos provisorios sin equivalente oficial (mismos
--    DELETE que antes, sin cambios: no usan ON CONFLICT ni IF NOT
--    EXISTS). Si ya existe algun paciente apuntando a estos ids, el
--    DELETE fallara por la llave foranea -- es la señal de que hay que
--    revisar esos registros a mano antes de continuar (ver Seccion 0).
-- ---------------------------------------------------------------------
DELETE FROM schemaoirs.leyes_previsionales WHERE ley_prev_id NOT IN (1,2,3,4,5,6,7,8,96,97);
DELETE FROM schemaoirs.nivel_instruccion WHERE niv_ins_id NOT IN (1,2,3,4,5,6,97,98);
DELETE FROM schemaoirs.tipo_via WHERE via_id NOT IN (1,2,3,4,5,6,7,8,9,10);
DELETE FROM schemaoirs.identidad_genero WHERE gen_id NOT IN (1,2,4,5,6,7,8);
DELETE FROM schemaoirs.pueblo_afrodescendiente WHERE afro_id NOT IN (1,2);
-- categoria_ocupacional: ADVERTENCIA -- si ya hay pacientes con los
-- codigos provisorios antiguos (5 al 10), este DELETE fallara a
-- proposito por fk_paciente_categoria_ocupacional. Si eso pasa, avisar
-- antes de continuar: no se puede migrar automaticamente sin inventar
-- datos que el paciente nunca declaro.
DELETE FROM schemaoirs.categoria_ocupacional WHERE cat_ocu_id NOT IN (1,2,3,99);

-- ---------------------------------------------------------------------
-- 6) Carga/correccion de los valores OFICIALES. Reemplaza el
--    "INSERT ... ON CONFLICT DO UPDATE" (no soportado en 9.3) por un
--    bloque DO/plpgsql: por cada fila, intenta UPDATE primero; si no
--    actualizo ninguna fila (FOUND = false), hace INSERT. Es el patron
--    estandar de "upsert" para versiones de PostgreSQL anteriores a la
--    9.5.
-- ---------------------------------------------------------------------

-- Campo 21: Leyes Previsionales
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Ley 18.490: Accidentes de transporte'),
        (2, 'Ley 16.744: Accidentes de trabajo y enfermedades profesionales'),
        (3, 'Ley 16.744: Accidente escolar'),
        (4, 'Ley 19.659/99 de urgencia'),
        (5, 'Ley 19.992 PRAIS'),
        (6, 'Ley N 19.966: Regimen General de Garantias en Salud (GES)'),
        (7, 'Ley N 20.850: Ricarte Soto'),
        (8, 'N 21.030: Despenalizacion de la Interrupcion Voluntaria del Embarazo en tres Causales'),
        (96, 'Ninguna'),
        (97, 'No recuerda')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.leyes_previsionales SET ley_prev_descripcion = rec.descripcion, ley_prev_estado = 1 WHERE ley_prev_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.leyes_previsionales (ley_prev_id, ley_prev_descripcion, ley_prev_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 13: Nivel de Instruccion
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Preescolar'),
        (2, 'Especial o diferencial'),
        (3, 'Basica o primaria'),
        (4, 'Media o secundaria'),
        (5, 'Educacion superior'),
        (6, 'Sin instruccion'),
        (97, 'No recuerda'),
        (98, 'No responde')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.nivel_instruccion SET niv_ins_descripcion = rec.descripcion, niv_ins_estado = 1 WHERE niv_ins_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.nivel_instruccion (niv_ins_id, niv_ins_descripcion, niv_ins_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 16: Tipo de Via
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Calle'),
        (2, 'Avenida'),
        (3, 'Pasaje'),
        (4, 'Camino'),
        (5, 'Carretera'),
        (6, 'Callejon'),
        (7, 'Paseo'),
        (8, 'Escalera'),
        (9, 'Otro'),
        (10, 'Rotonda')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.tipo_via SET via_descripcion = rec.descripcion, via_estado = 1 WHERE via_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.tipo_via (via_id, via_descripcion, via_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 54: Identidad de Genero
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Masculino'),
        (2, 'Femenina'),
        (4, 'Transgenero Masculino'),
        (5, 'Transgenero Femenina'),
        (6, 'No binarie'),
        (7, 'Otra'),
        (8, 'No Revelado')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.identidad_genero SET gen_descripcion = rec.descripcion, gen_estado = 1 WHERE gen_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.identidad_genero (gen_id, gen_descripcion, gen_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 53: Pueblo Afrodescendiente Chileno
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'SI'),
        (2, 'NO')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.pueblo_afrodescendiente SET afro_descripcion = rec.descripcion, afro_estado = 1 WHERE afro_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.pueblo_afrodescendiente (afro_id, afro_descripcion, afro_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 12: Categoria Ocupacional
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Inactivos'),
        (2, 'Activos'),
        (3, 'Cesante o temporalmente sin trabajo'),
        (99, 'Ignorado')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.categoria_ocupacional SET cat_ocu_descripcion = rec.descripcion, cat_ocu_estado = 1 WHERE cat_ocu_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.categoria_ocupacional (cat_ocu_id, cat_ocu_descripcion, cat_ocu_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 12 (2do nivel): Ocupacion Declarada
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Miembro del poder ejecutivo de los cuerpos legislativos, personal directivo de la administracion publica y de empresa'),
        (2, 'Profesionales cientificos o intelectuales'),
        (3, 'Tecnicos y profesionales de nivel medio'),
        (4, 'Empleados de oficina'),
        (5, 'Trabajadores de los servicios y vendedores de comercio y mercado'),
        (6, 'Agricultores y trabajadores calificados agropecuarios y pesqueros'),
        (7, 'Oficiales, operarios y artesanos de artes mecanicas y de otros oficios'),
        (8, 'Operadores de instalaciones y maquinas y montadores'),
        (9, 'Trabajadores no calificados'),
        (10, 'Fuerzas armadas'),
        (99, 'Desconocido')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.ocupacion_declarada SET ocu_dec_descripcion = rec.descripcion, ocu_dec_estado = 1 WHERE ocu_dec_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.ocupacion_declarada (ocu_dec_id, ocu_dec_descripcion, ocu_dec_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campo 55: gate Pueblo Originario (SI/NO)
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'SI'),
        (2, 'NO')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.pueblo_originario_gate SET gate_descripcion = rec.descripcion, gate_estado = 1 WHERE gate_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.pueblo_originario_gate (gate_id, gate_descripcion, gate_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

-- Campos 22/23: Procedencia del paciente (clasificacion oficial)
DO $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN SELECT * FROM (VALUES
        (1, 'Unidad Emergencia (mismo establecimiento)'),
        (3, 'Atencion especialidades (mismo establecimiento)'),
        (4, 'Otro Establecimiento'),
        (5, 'Otra Procedencia'),
        (6, 'Area de Cirugia Mayor Ambulatoria (mismo establecimiento)'),
        (7, 'Hospital comunitario o de baja complejidad')
    ) AS t(id, descripcion)
    LOOP
        UPDATE schemaoirs.procedencia_paciente SET proc_descripcion = rec.descripcion, proc_estado = 1 WHERE proc_id = rec.id;
        IF NOT FOUND THEN
            INSERT INTO schemaoirs.procedencia_paciente (proc_id, proc_descripcion, proc_estado) VALUES (rec.id, rec.descripcion, 1);
        END IF;
    END LOOP;
END $$;

COMMIT;

-- =====================================================================
-- 7) SOLO LECTURA - revisiones que requieren criterio de Bioestadistica
-- =====================================================================
-- SELECT id_prevision, nombre, estatus FROM agenda.prevision ORDER BY id_prevision;
-- SELECT pue_id, pue_descripcion, pue_estado FROM schemaoirs.pueblo_originario ORDER BY pue_id;
-- =====================================================================
