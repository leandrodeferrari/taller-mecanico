USE taller_mecanico;

-- Inserts de empleados

INSERT INTO empleados(APELLIDO, CELULAR, CALLE, CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, NUMERO, PISO, NOMBRES, TIPO_EMPLEADO)
	VALUES('Suarez', '1124567897', 'Caaguazu', '1567', '2', 'La Matanza', '347', '1', 'Leonardo Ezequiel', 'Administrativo');
INSERT INTO empleados(APELLIDO, CELULAR, CALLE, CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, NUMERO, PISO, NOMBRES, TIPO_EMPLEADO)
	VALUES('Farias', '1178785656', 'Pedernera', '1677', '6', 'CABA', '788', '3', 'Clara Rocio', 'Recepcionista');

-- Inserts de vehiculos

INSERT INTO vehiculos(ANIO, COLOR, MARCA, MODELO, PATENTE)
	VALUES(2018, 'Rojo', 'Renault', 'Logan', 'AB123CD');
INSERT INTO vehiculos(ANIO, COLOR, MARCA, MODELO, PATENTE)
	VALUES(2020, 'Gris claro', 'Chevrolet', 'Onix', 'JK444LM');

-- Inserts de clientes

INSERT INTO clientes(APELLIDO, CELULAR, CALLE, CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, NUMERO, PISO, CORREO_ELECTRONICO, NOMBRES, TELEFONO_LINEA)
	VALUES('Gomez', '1123879645', 'Av. Montiel', '4545', '20', 'Montegrande', '89', '10', 'flor_belen_gomez@hotmail.com', 'Florencia Belen', '44549090');
INSERT INTO clientes(APELLIDO, CELULAR, CALLE, CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, NUMERO, PISO, CORREO_ELECTRONICO, NOMBRES, TELEFONO_LINEA)
	VALUES('Lopez', '1122222222', 'Buffano', '2384', '1', 'Lomas de Zamora', '4', 'PB', 'male_lopez@hotmail.com', 'Malena Esperanza', '33608888');

-- Inserts de mecanicos

INSERT INTO mecanicos(ACTIVO, APELLIDO, CELULAR, CALLE, CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, NUMERO, PISO, ESPECIALIDAD, NOMBRES)
	VALUES('V', 'Torres', '1190995656', 'Coronel Thorne', '1678', '6', 'La Matanza', '899', '1', 'Armado y reparación', 'Gonzalo Emanuel');
INSERT INTO mecanicos(ACTIVO, APELLIDO, CELULAR, CALLE, CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, NUMERO, PISO, ESPECIALIDAD, NOMBRES)
	VALUES('F', 'Correa', '1122334455', 'Av. Crovara', '1122', '4', 'La Matanza', '901', '2', 'Mantenimiento', 'Maximiliano Enrique');

-- Inserts de repuestos

INSERT INTO repuestos(MARCA, MODELO, REPUESTO, VALOR)
	VALUES('Renault', 'Logan', 'Amortiguador trasero', 7243.93);
INSERT INTO repuestos(MARCA, MODELO, REPUESTO, VALOR)
	VALUES('Chevrolet', 'Onix', 'Compresor', 127970.23);

-- Inserts de cliente_vehiculo

INSERT INTO cliente_vehiculo(CLIENTE_ID, VEHICULO_ID)
	VALUES(1, 2);
INSERT INTO cliente_vehiculo(CLIENTE_ID, VEHICULO_ID)
	VALUES(2, 1);

-- Inserts de ordenes_trabajo

INSERT INTO ordenes_trabajo(CANTIDAD_CUOTAS, DETALLE_FALLA, ESTADO, FECHA_FIN_REPARACION, FECHA_INGRESO,
 FECHA_PAGO, FORMA_PAGO, IMPORTE_TOTAL, KILOMETRAJE, NIVEL_COMBUSTIBLE, TIPO_TARJETA, ADMINISTRATIVO_ID, RECEPCIONISTA_ID, VEHICULO_ID)
	VALUES(46, 'Pérdida de dirección y aumento de distancia, en el frenado, notorio', 'Facturada', '2023-03-05', '2023-01-25', '2023-03-05', 'Efectivo', 7243.93, 120345, 'Lleno', 'Ninguna', 1, 2, 1);

-- Inserts de detalle_ordenes_trabajo

INSERT INTO detalle_ordenes_trabajo(CANTIDAD, VALOR_TOTAL, ORDEN_TRABAJO_ID, REPUESTO_ID)
	VALUES(2, 14487.86, 1, 1);

-- Inserts de mano_obra

INSERT INTO mano_obra(DETALLE, DURACION_HS, MECANICO_ID, ORDEN_TRABAJO_ID)
	VALUES('Arreglo de los 2 amortiguadores traseros', '06:00:00', 1, 1);