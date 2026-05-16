CREATE DATABASE AgroPlan;
GO
USE AgroPlan;
GO

---
--- 1. TABLA: CAMPOS
---
CREATE TABLE campos (
    id_campo INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(200),
    hectareas INT,
    estado VARCHAR(30),
    fecha_creacion DATETIME2 DEFAULT GETDATE()
);
GO

---
--- 2. TABLA: CULTIVOS
---
CREATE TABLE cultivos (
    id_cultivo INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    temporada_siembra VARCHAR(50),
    dias_cosecha INT,
    estado VARCHAR(30),
    fecha_creacion DATETIME2 DEFAULT GETDATE()
);
GO

---
--- 3. TABLA: PLANIFICACIÓN DE SIEMBRA
---
CREATE TABLE planificacion_siembra (
    id_siembra INT IDENTITY(1,1) PRIMARY KEY,
    id_campo INT FOREIGN KEY REFERENCES campos(id_campo),
    id_cultivo INT FOREIGN KEY REFERENCES cultivos(id_cultivo),
    fecha_siembra DATE NOT NULL,
    hectareas INT NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT 1, 
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_at DATETIME2,
    deleted_at DATETIME2,
    restored_at DATETIME2
);
GO

---
--- 4. TABLA: LOTES / SECTORES DE CULTIVO (El estado actual en el Dashboard)
---
CREATE TABLE estado_cultivo (
    id_estado_cultivo INT IDENTITY(1,1) PRIMARY KEY,
    id_campo INT FOREIGN KEY REFERENCES campos(id_campo),
    id_cultivo INT FOREIGN KEY REFERENCES cultivos(id_cultivo),
    hectareas DECIMAL(10,2) NOT NULL,      -- Ej: 33ha, 12ha
    fase_actual VARCHAR(50),               -- Ej: 'Floración', 'Maduración', 'En letargo'
    estado_salud VARCHAR(30),              -- Ej: 'Óptimo', 'Atención', 'Letargo'
    dias_proxima_accion INT                -- Ej: 45, 22, 62 días
);
GO

---
--- 5. TABLA: PLANIFICACIÓN DE COSECHA
---
CREATE TABLE planificacion_cosecha (
    id_cosecha INT IDENTITY(1,1) PRIMARY KEY,
    id_campo INT FOREIGN KEY REFERENCES campos(id_campo),
    id_cultivo INT FOREIGN KEY REFERENCES cultivos(id_cultivo),
    fecha_recomendada DATE NOT NULL,
    hectareas DECIMAL(10,2) NOT NULL,
    estado VARCHAR(30) NOT NULL,           -- Ej: 'En Planificación', 'Pendiente', 'En letargo', 'Proyectando'
    fecha_creacion DATETIME2 DEFAULT GETDATE()
);
GO

---
--- 6. TABLA: CALENDARIO INTELIGENTE (Eventos de la finca)
---
CREATE TABLE eventos_calendario (
    id_evento INT IDENTITY(1,1) PRIMARY KEY,
    id_campo INT FOREIGN KEY REFERENCES campos(id_campo),
    id_cultivo INT FOREIGN KEY REFERENCES cultivos(id_cultivo),
    fecha DATE NOT NULL,
    tipo_evento VARCHAR(30) NOT NULL,      -- Ej: 'Siembra', 'Cosecha', 'Riego', 'Fertilización'
    hectareas DECIMAL(10,2),
    estado VARCHAR(30),
    descripcion NVARCHAR(250)
);
GO

---
--- 7. TABLA: ALERTAS (Historial y alertas activas en pantalla)
---
CREATE TABLE alertas (
    id_alerta INT IDENTITY(1,1) PRIMARY KEY,
    id_campo INT FOREIGN KEY REFERENCES campos(id_campo),
    id_cultivo INT FOREIGN KEY REFERENCES cultivos(id_cultivo), -- Puede ser NULL si es alerta general
    tipo_alerta VARCHAR(30) NOT NULL,      -- Ej: 'Peligro', 'Información', 'Éxito', 'Advertencia'
    titulo VARCHAR(100) NOT NULL,          -- Ej: 'Riesgo de helada leve'
    mensaje NVARCHAR(500) NOT NULL,        -- Ej: 'Temperatura mínima proyectada 8°C...'
    fecha_alerta DATE NOT NULL,
    activa BIT DEFAULT 1                   -- 1 = Activa, 0 = Archivada
);
GO

---
--- 8. TABLA: REPORTES FINANCIEROS Y DE PRODUCCIÓN
---
CREATE TABLE reportes_historicos (
    id_reporte INT IDENTITY(1,1) PRIMARY KEY,
    id_campo INT FOREIGN KEY REFERENCES campos(id_campo),
    id_cultivo INT FOREIGN KEY REFERENCES cultivos(id_cultivo),
    mes INT NOT NULL,                      -- 1 al 12
    anio INT NOT NULL,                     -- Ej: 2026
    produccion_ton DECIMAL(10,2) NOT NULL, -- Toneladas producidas
    ingresos DECIMAL(12,2) NOT NULL,       -- S/. Ingresos
    gastos DECIMAL(12,2) NOT NULL          -- S/. Gastos
);
GO

