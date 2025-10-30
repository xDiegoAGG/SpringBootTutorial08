INSERT INTO nutricionista (nombre, apellido, numero_licencia, especialidad, email, telefono, activo) 
VALUES ('Ana', 'García', 'NUT-2023-001', 'Nutrición Deportiva', 'ana.garcia@ejemplo.com', '555-1234', true);

INSERT INTO nutricionista (nombre, apellido, numero_licencia, especialidad, email, telefono, activo) 
VALUES ('Carlos', 'Rodríguez', 'NUT-2023-002', 'Nutrición Clínica', 'carlos.rodriguez@ejemplo.com', '555-2345', true);

INSERT INTO nutricionista (nombre, apellido, numero_licencia, especialidad, email, telefono, activo) 
VALUES ('María', 'López', 'NUT-2023-003', 'Nutrición Pediátrica', 'maria.lopez@ejemplo.com', '555-3456', true);

INSERT INTO paciente (nombre, apellido, fecha_nacimiento, email, telefono, activo, nutricionista_id)
VALUES ('Juan', 'Pérez', '1985-05-15', 'juan.perez@ejemplo.com', '555-1001', true, 1);

INSERT INTO paciente (nombre, apellido, fecha_nacimiento, email, telefono, activo, nutricionista_id)
VALUES ('Laura', 'Martínez', '1990-07-22', 'laura.martinez@ejemplo.com', '555-1002', true, 1);

INSERT INTO paciente (nombre, apellido, fecha_nacimiento, email, telefono, activo, nutricionista_id)
VALUES ('Pedro', 'González', '1988-03-10', 'pedro.gonzalez@ejemplo.com', '555-1003', true, 1);

INSERT INTO paciente (nombre, apellido, fecha_nacimiento, email, telefono, activo, nutricionista_id)
VALUES ('Sofia', 'Hernández', '1995-11-30', 'sofia.hernandez@ejemplo.com', '555-1004', true, 2);

INSERT INTO paciente (nombre, apellido, fecha_nacimiento, email, telefono, activo, nutricionista_id)
VALUES ('Miguel', 'Sánchez', '1982-09-18', 'miguel.sanchez@ejemplo.com', '555-1005', true, 2);

INSERT INTO nota (titulo, contenido, fecha_creacion, tipo_nota, paciente_id, nutricionista_id)
VALUES ('Evaluación inicial', 
        'Paciente con sobrepeso leve. IMC: 27.5. Objetivo: reducir 5kg en 3 meses. Buena disposición para seguir plan nutricional.', 
        '2023-01-15 10:30:00', 'Evaluación', 1, 1);

INSERT INTO nota (titulo, contenido, fecha_creacion, tipo_nota, paciente_id, nutricionista_id)
VALUES ('Plan alimenticio', 
        'Dieta hipocalórica con restricción moderada de carbohidratos. 1800 kcal/día. 5 comidas. Incremento de proteínas y fibra.', 
        '2023-01-15 11:00:00', 'Plan', 1, 1);

INSERT INTO nota (titulo, contenido, fecha_creacion, tipo_nota, paciente_id, nutricionista_id)
VALUES ('Seguimiento 1 mes', 
        'Paciente perdió 2kg. Buena adherencia al plan. Se mantiene motivado. Continuaremos con el mismo plan.', 
        '2023-02-15 10:00:00', 'Seguimiento', 1, 1);

INSERT INTO nota (titulo, contenido, fecha_creacion, tipo_nota, paciente_id, nutricionista_id)
VALUES ('Primera consulta', 
        'Paciente atleta de alto rendimiento. Objetivo: optimizar rendimiento deportivo. Necesita aumentar masa muscular.', 
        '2023-01-20 14:00:00', 'Evaluación', 2, 1);

INSERT INTO nota (titulo, contenido, fecha_creacion, tipo_nota, paciente_id, nutricionista_id)
VALUES ('Plan deportivo', 
        'Dieta hipercalórica: 2500 kcal/día. Alto contenido proteico (2g/kg peso). Suplementación con creatina y BCAA.', 
        '2023-01-20 14:30:00', 'Plan', 2, 1);

INSERT INTO nota (titulo, contenido, fecha_creacion, tipo_nota, paciente_id, nutricionista_id)
VALUES ('Evaluación inicial', 
        'Paciente con diabetes tipo 2 controlada. Necesita educación nutricional para control glucémico.', 
        '2023-01-25 09:00:00', 'Evaluación', 4, 2);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-01-15', 85.5, 176.0, 98.0, 102.0, 24.5, 1, 1);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-02-15', 83.0, 176.0, 96.5, 101.0, 23.8, 1, 1);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-03-15', 81.5, 176.0, 94.0, 100.0, 22.5, 1, 1);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-01-20', 62.0, 168.0, 68.0, 92.0, 18.5, 2, 1);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-02-20', 64.0, 168.0, 68.5, 93.0, 17.8, 2, 1);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-02-01', 78.0, 172.0, 88.0, 96.0, 21.0, 3, 1);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-01-25', 72.5, 162.0, 82.0, 98.0, 28.5, 4, 2);

INSERT INTO medicion (fecha, peso, altura, circunferencia_cintura, circunferencia_cadera, porcentaje_grasa_corporal, paciente_id, nutricionista_id)
VALUES ('2023-02-10', 91.0, 180.0, 102.0, 105.0, 26.8, 5, 2);

