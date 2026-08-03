-- ============================================================
-- Tablas para módulo de Prescripción de Medicamentos
-- Ejecutar en la base de datos: crsm
-- ============================================================

-- Prescripciones simples por hospitalización
CREATE TABLE schema_uo.prescripcion_medicamento (
    id_pm           SERIAL PRIMARY KEY,
    id_duo          INTEGER NOT NULL,
    id_insumo       INTEGER NOT NULL,
    medicamento_desc VARCHAR(255) NOT NULL,
    dosis           VARCHAR(50),
    unidad_desc     VARCHAR(100),
    id_via          INTEGER DEFAULT 0,
    via_desc        VARCHAR(100),
    frecuencia      VARCHAR(100),
    observacion     VARCHAR(255),
    estado          INTEGER DEFAULT 1,
    fecha_registro  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_registro VARCHAR(100)
);

COMMENT ON TABLE schema_uo.prescripcion_medicamento IS
    'Prescripciones de medicamentos simples por hospitalización (id_duo). estado=1 activo, estado=0 eliminado.';

-- Cabecera de infusiones IV
CREATE TABLE schema_uo.prescripcion_infusion (
    id_pi           SERIAL PRIMARY KEY,
    id_duo          INTEGER NOT NULL,
    suero_desc      VARCHAR(255),
    velocidad_inf   DECIMAL(10,2) DEFAULT 0,
    unidad_velocidad VARCHAR(20) DEFAULT 'mL/hr',
    observacion     VARCHAR(255),
    estado          INTEGER DEFAULT 1,
    fecha_registro  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_registro VARCHAR(100)
);

COMMENT ON TABLE schema_uo.prescripcion_infusion IS
    'Cabecera de infusiones IV: vehículo/suero y velocidad. estado=1 activo, estado=0 eliminado.';

-- Detalle de medicamentos por infusión
CREATE TABLE schema_uo.prescripcion_infusion_detalle (
    id_pid          SERIAL PRIMARY KEY,
    id_pi           INTEGER NOT NULL REFERENCES schema_uo.prescripcion_infusion(id_pi) ON DELETE CASCADE,
    orden           INTEGER DEFAULT 1,
    id_insumo       INTEGER NOT NULL,
    medicamento_desc VARCHAR(255),
    dosis           VARCHAR(50),
    unidad_desc     VARCHAR(100)
);

COMMENT ON TABLE schema_uo.prescripcion_infusion_detalle IS
    'Medicamentos que componen una infusión IV (uno o más por cabecera).';

-- Índices para búsquedas frecuentes por id_duo
CREATE INDEX idx_pm_duo    ON schema_uo.prescripcion_medicamento(id_duo, estado);
CREATE INDEX idx_pi_duo    ON schema_uo.prescripcion_infusion(id_duo, estado);
CREATE INDEX idx_pid_pi    ON schema_uo.prescripcion_infusion_detalle(id_pi);
