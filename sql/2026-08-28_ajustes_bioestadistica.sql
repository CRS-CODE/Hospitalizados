-- =====================================================================
-- Ajustes tras primera revision de Bioestadistica (2026-08-28)
-- Modulo: modulo_uhce - Ingreso de paciente (admision_ugu_carga.jsp)
--
-- Este script es un COMPLEMENTO del script anterior
-- (2026-08-27_ume_bioestadistica.sql). Ejecutar despues de ese, o de
-- forma independiente si ese ya se ejecuto: todo aqui es idempotente
-- (se puede correr mas de una vez sin error ni duplicar datos).
-- =====================================================================

BEGIN;

-- ---------------------------------------------------------------------
-- 1) Correccion: "Seguro Escolar" estaba mal referenciado como
--    "Ley 18.490". La Ley 18.490 corresponde en realidad al SOAP
--    (Seguro Obligatorio de Accidentes Personales, ley de transito).
--    El Seguro Escolar se rige por el Decreto Supremo N 313 de 1972
--    del Ministerio del Trabajo y Prevision Social, no por una ley.
--    Este UPDATE corrige el registro SOLO SI quedo con el texto viejo
--    (no hace nada si el script anterior no se habia ejecutado aun,
--    porque en ese caso el script 1 ya trae el texto correcto).
-- ---------------------------------------------------------------------
UPDATE schemaoirs.leyes_previsionales
   SET ley_prev_descripcion = 'Decreto Supremo N 313/1972 - Seguro Escolar'
 WHERE ley_prev_id = 3
   AND ley_prev_descripcion = 'Ley 18.490 - Seguro Escolar';

UPDATE schemaoirs.leyes_previsionales
   SET ley_prev_descripcion = 'Ley 18.490 - Seguro Obligatorio de Accidentes Personales (SOAP, Ley de Transito)'
 WHERE ley_prev_id = 5
   AND ley_prev_descripcion = 'Seguro Obligatorio de Accidentes Personales (Ley de Transito)';

-- NOTA: la lista completa y definitiva de "leyes previsionales" que se
-- usa en el Informe de Egreso Hospitalario todavia esta pendiente de
-- que Bioestadistica la confirme (ver conversacion). Cuando la tengan,
-- se reemplaza con UPDATE/INSERT/DELETE directo sobre
-- schemaoirs.leyes_previsionales, sin tocar codigo.

-- ---------------------------------------------------------------------
-- 2) Tipo de Via para la direccion (Calle, Avenida, Pasaje, etc.)
--    Catalogo generico (no depende de codificacion DEIS), mismo patron
--    que los catalogos anteriores.
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS schemaoirs.tipo_via (
    via_id            integer PRIMARY KEY,
    via_descripcion   varchar(60) NOT NULL,
    via_estado        smallint NOT NULL DEFAULT 1,
    via_fecha_ingreso timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO schemaoirs.tipo_via (via_id, via_descripcion, via_estado) VALUES
    (1, 'Calle', 1),
    (2, 'Avenida', 1),
    (3, 'Pasaje', 1),
    (4, 'Camino', 1),
    (5, 'Poblacion', 1),
    (6, 'Villa', 1),
    (7, 'Sector', 1),
    (8, 'Parcela', 1),
    (9, 'Otra', 1)
ON CONFLICT (via_id) DO NOTHING;

ALTER TABLE agenda.paciente
    ADD COLUMN IF NOT EXISTS id_tipo_via integer;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_paciente_tipo_via') THEN
        ALTER TABLE agenda.paciente ADD CONSTRAINT fk_paciente_tipo_via
            FOREIGN KEY (id_tipo_via) REFERENCES schemaoirs.tipo_via (via_id);
    END IF;
END $$;

COMMIT;

-- ---------------------------------------------------------------------
-- 3) SOLO LECTURA - para que Bioestadistica/TI revisen manualmente
--    (no se puede ejecutar desde esta sesion, no hay acceso a la BD):
--
-- 3a) Ver que previsiones existen hoy y cuales estan inactivas.
--     Si CAPREDENA/DIPRECA/SISA ya existen pero con estatus != 1,
--     basta con activarlas (ver UPDATE de ejemplo mas abajo).
--
-- SELECT id_prevision, nombre, estatus FROM agenda.prevision ORDER BY id_prevision;
--
-- Ejemplo para activar una previsión ya existente (ajustar id_prevision
-- real segun lo que devuelva el SELECT de arriba):
-- UPDATE agenda.prevision SET estatus = 1 WHERE id_prevision = <id_capredena>;
--
-- Si CAPREDENA, DIPRECA y/o SISA NO existen como filas en agenda.prevision,
-- hay que insertarlas. Ejemplo (ajustar id_prevision para que no choque
-- con ids ya usados):
-- INSERT INTO agenda.prevision (id_prevision, nombre, estatus) VALUES
--     (<id_libre_1>, 'CAPREDENA', 1),
--     (<id_libre_2>, 'DIPRECA', 1),
--     (<id_libre_3>, 'SISA', 1);
-- =====================================================================
