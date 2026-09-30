-- =====================================================================
-- Campos nuevos para el Informe de Egreso Hospitalario (Bioestadistica)
-- Modulo: modulo_uhce - Ingreso de paciente (admision_ugu_carga.jsp)
-- Fecha  : 2026-08-27
--
-- Campos cubiertos por este script (numeracion segun el requerimiento
-- de Bioestadistica / DEIS):
--   12  CATEGORIA OCUPACIONAL
--   13  NIVEL DE INSTRUCCION
--   21  LEYES PREVISIONALES
--   53  PUEBLO AFRODESCENDIENTE CHILENO
--   54  IDENTIDAD DE GENERO
--
-- Campos que NO requieren cambio de esquema porque ya existen en
-- agenda.paciente y ya se usan en modulo_uhce (no se tocan aqui):
--   11/56  PAIS DE ORIGEN / NACIONALIDAD  -> columna agenda.paciente.id_nacionalidad
--   52     NOMBRE SOCIAL                  -> columna agenda.paciente.nombresocial
--                                            (existia en la BD pero modulo_uhce no la
--                                             leia ni la escribia; el codigo ya fue
--                                             corregido para hacerlo, sin tocar el esquema)
--   55     PUEBLO ORIGINARIO              -> columna agenda.paciente.id_puebloorigen
--                                            + catalogo schemaoirs.pueblo_originario
--                                            (ya existia y ya se usaba)
--
-- IMPORTANTE - LEER ANTES DE EJECUTAR EN PRODUCCION:
-- Los valores de los catalogos incluidos abajo (categoria ocupacional,
-- nivel de instruccion, leyes previsionales, pueblo afrodescendiente,
-- identidad de genero) son PROVISORIOS. No pude confirmar los codigos
-- oficiales DEIS/MINSAL para el Informe de Egreso Hospitalario a traves
-- de busqueda web (los documentos oficiales disponibles son PDF
-- escaneados sin texto extraible). Bioestadistica debe validar/reemplazar
-- estos codigos con los oficiales ANTES de que el sistema empiece a
-- alimentar reportes reales a DEIS/REM. Como el codigo de la aplicacion
-- lee estas listas dinamicamente desde estas tablas (mismo patron que
-- ya usan schemaoirs.pueblo_originario y schemaoirs.nacion), reemplazar
-- los valores es un simple UPDATE/DELETE+INSERT en estas tablas: no
-- requiere tocar Java ni JSP.
--
-- NOTA sobre version de PostgreSQL: el proyecto trae un driver JDBC muy
-- antiguo (postgresql-8.3-603.jdbc3.jar). Si el servidor de BD real
-- fuera anterior a PostgreSQL 9.6, "ADD COLUMN IF NOT EXISTS" no existe
-- todavia; en ese caso reemplazar esas lineas por "ADD COLUMN" a secas.
-- Confirmar la version real del servidor (SELECT version();) antes de
-- ejecutar si hay dudas.
-- =====================================================================

BEGIN;

-- ---------------------------------------------------------------------
-- 1) Catalogos nuevos (mismo patron que schemaoirs.pueblo_originario /
--    schemaoirs.nacion: id, descripcion, estado)
-- ---------------------------------------------------------------------

-- Campo 12: Categoria Ocupacional
CREATE TABLE IF NOT EXISTS schemaoirs.categoria_ocupacional (
    cat_ocu_id            integer PRIMARY KEY,
    cat_ocu_descripcion   varchar(150) NOT NULL,
    cat_ocu_estado        smallint NOT NULL DEFAULT 1,
    cat_ocu_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.categoria_ocupacional (cat_ocu_id, cat_ocu_descripcion, cat_ocu_estado) VALUES
    (1, 'Patron o empleador', 1),
    (2, 'Trabajador por cuenta propia', 1),
    (3, 'Empleado u obrero del sector publico', 1),
    (4, 'Empleado u obrero del sector privado', 1),
    (5, 'Servicio domestico', 1),
    (6, 'Familiar no remunerado', 1),
    (7, 'Fuerzas Armadas y de Orden', 1),
    (8, 'Sin actividad remunerada / Cesante', 1),
    (9, 'Estudiante', 1),
    (10, 'Jubilado o Pensionado', 1),
    (99, 'Ignorado / No informa', 1)
ON CONFLICT (cat_ocu_id) DO NOTHING;

-- Campo 13: Nivel de Instruccion
CREATE TABLE IF NOT EXISTS schemaoirs.nivel_instruccion (
    niv_ins_id            integer PRIMARY KEY,
    niv_ins_descripcion   varchar(150) NOT NULL,
    niv_ins_estado        smallint NOT NULL DEFAULT 1,
    niv_ins_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.nivel_instruccion (niv_ins_id, niv_ins_descripcion, niv_ins_estado) VALUES
    (1, 'Sin escolaridad', 1),
    (2, 'Basica incompleta', 1),
    (3, 'Basica completa', 1),
    (4, 'Media incompleta', 1),
    (5, 'Media completa', 1),
    (6, 'Tecnica superior incompleta', 1),
    (7, 'Tecnica superior completa', 1),
    (8, 'Universitaria incompleta', 1),
    (9, 'Universitaria completa', 1),
    (10, 'Postgrado', 1),
    (99, 'Ignorado / No informa', 1)
ON CONFLICT (niv_ins_id) DO NOTHING;

-- Campo 21: Leyes Previsionales
CREATE TABLE IF NOT EXISTS schemaoirs.leyes_previsionales (
    ley_prev_id            integer PRIMARY KEY,
    ley_prev_descripcion   varchar(200) NOT NULL,
    ley_prev_estado        smallint NOT NULL DEFAULT 1,
    ley_prev_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.leyes_previsionales (ley_prev_id, ley_prev_descripcion, ley_prev_estado) VALUES
    (1, 'Ninguna', 1),
    (2, 'Ley 16.744 - Accidentes del Trabajo y Enfermedades Profesionales', 1),
    (3, 'Decreto Supremo N 313/1972 - Seguro Escolar', 1),
    (4, 'PRAIS - Programa de Reparacion y Atencion Integral en Salud', 1),
    (5, 'Ley 18.490 - Seguro Obligatorio de Accidentes Personales (SOAP, Ley de Transito)', 1),
    (99, 'Otra / Ignorado', 1)
ON CONFLICT (ley_prev_id) DO NOTHING;

-- Campo 53: Pueblo Afrodescendiente Chileno
CREATE TABLE IF NOT EXISTS schemaoirs.pueblo_afrodescendiente (
    afro_id            integer PRIMARY KEY,
    afro_descripcion   varchar(150) NOT NULL,
    afro_estado        smallint NOT NULL DEFAULT 1,
    afro_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.pueblo_afrodescendiente (afro_id, afro_descripcion, afro_estado) VALUES
    (1, 'Si, se considera parte del Pueblo Tribal Afrodescendiente Chileno', 1),
    (2, 'No', 1),
    (9, 'No sabe / No responde', 1)
ON CONFLICT (afro_id) DO NOTHING;

-- Campo 54: Identidad de Genero
CREATE TABLE IF NOT EXISTS schemaoirs.identidad_genero (
    gen_id            integer PRIMARY KEY,
    gen_descripcion   varchar(150) NOT NULL,
    gen_estado        smallint NOT NULL DEFAULT 1,
    gen_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.identidad_genero (gen_id, gen_descripcion, gen_estado) VALUES
    (1, 'Mujer', 1),
    (2, 'Hombre', 1),
    (3, 'Mujer trans', 1),
    (4, 'Hombre trans', 1),
    (5, 'No binario', 1),
    (6, 'Otra', 1),
    (9, 'Prefiere no decir / No informa', 1)
ON CONFLICT (gen_id) DO NOTHING;

-- ---------------------------------------------------------------------
-- 2) Columnas nuevas en agenda.paciente
--    (se agregan al final de la tabla; el codigo de guardarPaciente()
--    fue reescrito para usar INSERT con lista de columnas explicita,
--    por lo que el orden fisico de estas columnas nuevas ya no es
--    critico para ese INSERT)
-- ---------------------------------------------------------------------

ALTER TABLE agenda.paciente
    ADD COLUMN IF NOT EXISTS id_categoria_ocupacional     integer,
    ADD COLUMN IF NOT EXISTS id_nivel_instruccion          integer,
    ADD COLUMN IF NOT EXISTS id_ley_previsional            integer,
    ADD COLUMN IF NOT EXISTS id_pueblo_afrodescendiente    integer,
    ADD COLUMN IF NOT EXISTS id_identidad_genero           integer;

-- Llaves foraneas opcionales hacia los catalogos (comentar si el
-- estandar de la BD no usa FKs explicitas en agenda.paciente).
-- Nota: PostgreSQL no soporta "ADD CONSTRAINT IF NOT EXISTS", por eso
-- se usa un bloque DO con chequeo contra pg_constraint para que el
-- script se pueda re-ejecutar sin error si ya se aplico antes.
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
END $$;

COMMIT;

-- ---------------------------------------------------------------------
-- Verificacion rapida post-despliegue
-- ---------------------------------------------------------------------
-- SELECT column_name, data_type FROM information_schema.columns
--   WHERE table_schema='agenda' AND table_name='paciente'
--   AND column_name IN ('id_categoria_ocupacional','id_nivel_instruccion',
--   'id_ley_previsional','id_pueblo_afrodescendiente','id_identidad_genero');
-- SELECT * FROM schemaoirs.categoria_ocupacional;
-- SELECT * FROM schemaoirs.nivel_instruccion;
-- SELECT * FROM schemaoirs.leyes_previsionales;
-- SELECT * FROM schemaoirs.pueblo_afrodescendiente;
-- SELECT * FROM schemaoirs.identidad_genero;
