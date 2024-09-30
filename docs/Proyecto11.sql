
CREATE TABLE CIUDAD(
nombre VARCHAR2(2) NOT NULL,
codigo NUMBER(25) PRIMARY KEY NOT NULL );
    
CREATE TABLE SUCURSAL (
id_sucursal NUMBER(25) PRIMARY KEY NOT NULL, 
nombre VARCHAR2(20)NOT NULL,
intalacionM2 NUMBER DEFAULT 25 NOT NULL,
telefono NUMBER DEFAULT 25 NOT NULL);
    
CREATE TABLE BODEGA(
id_bodega  NUMBER(25) PRIMARY KEY NOT NULL,
nombre VARCHAR2(25)NOT NULL,
tamano NUMBER DEFAULT 25 NOT NULL,
cantidad_Prod NUMBER DEFAULT 25 NOT NULL);
    
CREATE TABLE PROVEEDORES(
id_proveedor  NUMBER(25) PRIMARY KEY NOT NULL,
nit NUMBER(25) NOT NULL,
nombre VARCHAR2(25) NOT NULL,
direccion VARCHAR2(25)NOT NULL,
nombre_contacto VARCHAR(25) NOT NULL,
telefono_contacto  VARCHAR (25)NOT NULL);
    
CREATE TABLE CATEGORIA(
id_categoria  NUMBER(25) PRIMARY KEY NOT NULL,
codigo VARCHAR(25)NOT NULL,
nombre VARCHAR(25)NOT NULL,
descripcion VARCHAR(25), 
caracteristicas_almacenamiento VARCHAR2(25)NOT NULL);
    
CREATE TABLE PRODUCTO (
id_producto NUMBER(25) PRIMARY KEY NOT NULL,
nombre VARCHAR(255) NOT NULL,
codigoBarras VARCHAR(255)NOT NULL,
precioVenta NUMBER DEFAULT 25 NOT NULL,
presentacion VARCHAR(255) NOT NULL,
cantidadPresentacion NUMBER DEFAULT 25 NOT NULL,
unidadMedida VARCHAR(255) NOT NULL,
expiracion DATE NOT NULL);	

CREATE TABLE ORDENCOMPRA (
id_compra NUMBER(25) PRIMARY KEY NOT NULL,
cantidad NUMBER DEFAULT 25 NOT NULL, 
prrcioAcordado VARCHAR2(25) NOT NULL, 
fechaEspera DATE NOT NULL, 
estado VARCHAR2(25)NOT NULL); 

CREATE TABLE RECEPCIONPRODUCTO (
id_recepcion NUMBER(25) PRIMARY KEY NOT NULL, 
fechaRecepcion DATE NOT NULL, 
cantidadRecibida INT NOT NULL, 
costoUnitario INT NOT NULL); 

CREATE TABLE INFOEXTRABODEGA (
costopromedio INT NOT NULL, 
mivelMinReorden INT NOT NULL, 
capacidadAlmacenamiento INT NOT NULL, 
totalExistencias INT NOT NULL); 

CREATE TABLE PRODUCTOPERECEDERO (
fechaVencimiento DATE NOT NULL); 

CREATE TABLE INFOEXTRAORDEN(
cantidad INT NOT NULL, 
costoUnitario INT NOT NULL); 

CREATE TABLE ORDENPRODUCTO (
id_producto NUMBER(25) NOT NULL,
id_compra NUMBER(25) NOT NULL,
CONSTRAINT fk_producto_ordenproducto FOREIGN KEY (id_producto) REFERENCES PRODUCTO(id_producto),
CONSTRAINT fk_compra_ordenproducto FOREIGN KEY (id_compra) REFERENCES ORDENCOMPRA(id_compra),
CONSTRAINT pk_ordenproducto PRIMARY KEY (id_producto, id_compra));

CREATE TABLE PRODUCTOPROVEEDOR( 
id_producto NUMBER(25),
id_proveedor NUMBER(25),
CONSTRAINT fk_producto_productoproveedor FOREIGN KEY (id_producto) REFERENCES PRODUCTO(id_producto),
CONSTRAINT fk_proveedor_productoproveedor FOREIGN KEY (id_proveedor) REFERENCES PROVEEDORES(id_proveedor),
CONSTRAINT pk_productoproveedor PRIMARY KEY (id_producto, id_proveedor));

ALTER TABLE SUCURSAL 
ADD codigo_ciudad NUMBER(25)  NOT NULL;

ALTER TABLE SUCURSAL 
ADD CONSTRAINT fk_ciudad_sucursal
FOREIGN KEY (codigo_ciudad) 
REFERENCES CIUDAD(codigo);


ALTER TABLE BODEGA
ADD id_sucursal NUMBER(25)  NOT NULL;

ALTER TABLE BODEGA
ADD CONSTRAINT fk_sucursal_bodega
FOREIGN KEY (id_sucursal)
REFERENCES SUCURSAL(id_sucursal);

ALTER TABLE PRODUCTO
ADD id_categoria NUMBER(25)  NOT NULL;

ALTER TABLE PRODUCTO
ADD CONSTRAINT fk_categoria_producto
FOREIGN KEY (id_categoria)
REFERENCES CATEGORIA(id_categoria);


ALTER TABLE ORDENCOMPRA
ADD id_sucursal NUMBER(25)  NOT NULL;

ALTER TABLE ORDENCOMPRA
ADD id_producto NUMBER(25)  NOT NULL;

ALTER TABLE ORDENCOMPRA
ADD id_proveedor NUMBER(25)  NOT NULL;

ALTER TABLE ORDENCOMPRA
ADD CONSTRAINT fk_sucursal_ordencompra
FOREIGN KEY (id_sucursal)
REFERENCES SUCURSAL(id_sucursal);

ALTER TABLE ORDENCOMPRA
ADD CONSTRAINT fk_producto_ordencompra
FOREIGN KEY (id_producto)
REFERENCES PRODUCTO(id_producto);

ALTER TABLE ORDENCOMPRA
ADD CONSTRAINT fk_proveedor_ordencompra
FOREIGN KEY (id_proveedor)
REFERENCES PROVEEDORES(id_proveedor);


ALTER TABLE RECEPCIONPRODUCTO
ADD id_bodega NUMBER(25)  NOT NULL;

ALTER TABLE RECEPCIONPRODUCTO
ADD id_producto NUMBER(25)  NOT NULL;

ALTER TABLE RECEPCIONPRODUCTO
ADD CONSTRAINT fk_bodega_recepcion
FOREIGN KEY (id_bodega)
REFERENCES BODEGA(id_bodega);

ALTER TABLE RECEPCIONPRODUCTO
ADD CONSTRAINT fk_producto_recepcion
FOREIGN KEY (id_producto)
REFERENCES PRODUCTO(id_producto);

ALTER TABLE INFOEXTRABODEGA
ADD id_bodega NUMBER(25) NOT NULL;

ALTER TABLE INFOEXTRABODEGA
ADD id_producto NUMBER(25) NOT NULL;

ALTER TABLE INFOEXTRABODEGA
ADD CONSTRAINT fk_bodega_infoextrabodega
FOREIGN KEY (id_bodega)
REFERENCES BODEGA(id_bodega);

ALTER TABLE INFOEXTRABODEGA
ADD CONSTRAINT fk_producto_infoextrabodega
FOREIGN KEY (id_producto)
REFERENCES PRODUCTO(id_producto);

ALTER TABLE INFOEXTRABODEGA
ADD CONSTRAINT pk_infoextrabodega
PRIMARY KEY (id_bodega, id_producto);


ALTER TABLE PRODUCTOPERECEDERO
ADD id_producto NUMBER(25) NOT NULL;

ALTER TABLE PRODUCTOPERECEDERO
ADD CONSTRAINT pk_fk_productoperecedero
PRIMARY KEY (id_producto);

ALTER TABLE PRODUCTOPERECEDERO
ADD CONSTRAINT fk_productoperecedero_producto
FOREIGN KEY (id_producto)
REFERENCES PRODUCTO(id_producto);


ALTER TABLE INFOEXTRAORDEN
ADD id_categoria NUMBER(25) NOT NULL;

ALTER TABLE INFOEXTRAORDEN
ADD id_producto NUMBER(25) NOT NULL;

ALTER TABLE INFOEXTRAORDEN
ADD CONSTRAINT fk_categoria_infoextraorden
FOREIGN KEY (id_categoria)
REFERENCES CATEGORIA(id_categoria);

ALTER TABLE INFOEXTRAORDEN
ADD CONSTRAINT fk_producto_infoextraorden
FOREIGN KEY (id_producto)
REFERENCES PRODUCTO(id_producto);

ALTER TABLE INFOEXTRAORDEN
ADD CONSTRAINT pk_infoextraorden
PRIMARY KEY (id_categoria, id_producto);

DROP TABLE PRODUCTOPROVEEDOR CASCADE CONSTRAINTS;

DROP TABLE ORDENPRODUCTO CASCADE CONSTRAINTS;

DROP TABLE INFOEXTRAORDEN CASCADE CONSTRAINTS;

DROP TABLE PRODUCTOPERECEDERO CASCADE CONSTRAINTS;

DROP TABLE INFOEXTRABODEGA CASCADE CONSTRAINTS;

DROP TABLE RECEPCIONPRODUCTO CASCADE CONSTRAINTS;

DROP TABLE ORDENCOMPRA CASCADE CONSTRAINTS;

DROP TABLE PRODUCTO CASCADE CONSTRAINTS;

DROP TABLE CATEGORIA CASCADE CONSTRAINTS;

DROP TABLE PROVEEDORES CASCADE CONSTRAINTS;

DROP TABLE BODEGA CASCADE CONSTRAINTS;

DROP TABLE SUCURSAL CASCADE CONSTRAINTS;

DROP TABLE CIUDAD CASCADE CONSTRAINTS;


INSERT INTO CIUDAD (codigo, nombre) VALUES (1, 'BO');
INSERT INTO CIUDAD (codigo, nombre) VALUES (2, 'ME');
INSERT INTO CIUDAD (codigo, nombre) VALUES (3, 'CA');
INSERT INTO CIUDAD (codigo, nombre) VALUES (4, 'BA');
INSERT INTO CIUDAD (codigo, nombre) VALUES (5, 'CA');

INSERT INTO SUCURSAL (id_sucursal, nombre, intalacionM2, telefono, codigo_ciudad) VALUES (1, 'Sucursal Norte', 500, 123456789, 1);
INSERT INTO SUCURSAL (id_sucursal, nombre, intalacionM2, telefono, codigo_ciudad) VALUES (2, 'Sucursal Sur', 300, 987654321, 2);
INSERT INTO SUCURSAL (id_sucursal, nombre, intalacionM2, telefono, codigo_ciudad) VALUES (3, 'Sucursal Centro', 400, 555555555, 3);
INSERT INTO SUCURSAL (id_sucursal, nombre, intalacionM2, telefono, codigo_ciudad) VALUES (4, 'Sucursal Este', 350, 444444444, 4);
INSERT INTO SUCURSAL (id_sucursal, nombre, intalacionM2, telefono, codigo_ciudad) VALUES (5, 'Sucursal Oeste', 600, 333333333, 5);

INSERT INTO BODEGA (id_bodega, nombre, tamano, cantiaProd, id_sucursal) VALUES (1, 'Bodega Principal', 1000, 5000, 1);
INSERT INTO BODEGA (id_bodega, nombre, tamano, cantiaProd, id_sucursal) VALUES (2, 'Bodega Secundaria', 800, 3000, 2);
INSERT INTO BODEGA (id_bodega, nombre, tamano, cantiaProd, id_sucursal) VALUES (3, 'Bodega Este', 600, 2000, 4);
INSERT INTO BODEGA (id_bodega, nombre, tamano, cantiaProd, id_sucursal) VALUES (4, 'Bodega Oeste', 700, 2500, 5);
INSERT INTO BODEGA (id_bodega, nombre, tamano, cantiaProd, id_sucursal) VALUES (5, 'Bodega Central', 900, 4000, 3); 

INSERT INTO PROVEEDORES (id_proveedor, NIT, nombre, direccion, nombreContacto, telefonoContacto) VALUES (1, '800123456-7', 'Proveedor A', 'Calle 123', 'Juan Pérez', '3101234567');
INSERT INTO PROVEEDORES (id_proveedor, NIT, nombre, direccion, nombreContacto, telefonoContacto) VALUES (2, '800987654-3', 'Proveedor B', 'Carrera 45', 'Ana Gómez', '3209876543');
INSERT INTO PROVEEDORES (id_proveedor, NIT, nombre, direccion, nombreContacto, telefonoContacto) VALUES (3, '800555555-5', 'Proveedor C', 'Avenida 10', 'Luis Rodríguez', '3005555555');
INSERT INTO PROVEEDORES (id_proveedor, NIT, nombre, direccion, nombreContacto, telefonoContacto) VALUES (4, '800444444-2', 'Proveedor D', 'Calle 50', 'María López', '3104444444');
INSERT INTO PROVEEDORES (id_proveedor, NIT, nombre, direccion, nombreContacto, telefonoContacto) VALUES (5, '800333333-8', 'Proveedor E', 'Carrera 80', 'Carlos Martínez', '3003333333');

INSERT INTO CATEGORIA (id_categoria, codigo, nombre, descripcion, caracteristicasAlmacenamiento) VALUES (1, 'CAT001', 'Electrónica', 'Dispositivos', 'Temperatura controlada');
INSERT INTO CATEGORIA (id_categoria, codigo, nombre, descripcion, caracteristicasAlmacenamiento) VALUES (2, 'CAT002', 'Alimentos', 'Productos frescos', 'Refrigerado');
INSERT INTO CATEGORIA (id_categoria, codigo, nombre, descripcion, caracteristicasAlmacenamiento) VALUES (3, 'CAT003', 'Textiles', 'Ropa y telas', 'Seco');
INSERT INTO CATEGORIA (id_categoria, codigo, nombre, descripcion, caracteristicasAlmacenamiento) VALUES (4, 'CAT004', 'Muebles', 'Mobiliario', 'Espacio amplio');
INSERT INTO CATEGORIA (id_categoria, codigo, nombre, descripcion, caracteristicasAlmacenamiento) VALUES (5, 'CAT005', 'Juguetes', 'Artículos para niños', 'Temperatura ambiente');

INSERT INTO PRODUCTO (id_producto, nombre, codigoBarras, precioVenta, presentacion, cantidadPresentacion, unidadMedida, expiracion, id_categoria) VALUES (1, 'Televisor', '1234567890123', 1200, 'Caja', 1, 'Unidad', TO_DATE('2025-12-31', 'YYYY-MM-DD'), 1);
INSERT INTO PRODUCTO (id_producto, nombre, codigoBarras, precioVenta, presentacion, cantidadPresentacion, unidadMedida, expiracion, id_categoria) VALUES (2, 'Laptop', '2345678901234', 1500, 'Caja', 1, 'Unidad', TO_DATE('2026-12-31', 'YYYY-MM-DD'), 1);
INSERT INTO PRODUCTO (id_producto, nombre, codigoBarras, precioVenta, presentacion, cantidadPresentacion, unidadMedida, expiracion, id_categoria) VALUES (3, 'Leche', '3456789012345', 1.5, 'Botella', 1, 'Litro', TO_DATE('2024-03-01', 'YYYY-MM-DD'), 2);
INSERT INTO PRODUCTO (id_producto, nombre, codigoBarras, precioVenta, presentacion, cantidadPresentacion, unidadMedida, expiracion, id_categoria) VALUES (4, 'Camisa', '4567890123456', 25, 'Bolsa', 1, 'Unidad', TO_DATE('2025-01-01', 'YYYY-MM-DD'), 3);
INSERT INTO PRODUCTO (id_producto, nombre, codigoBarras, precioVenta, presentacion, cantidadPresentacion, unidadMedida, expiracion, id_categoria) VALUES (5, 'Sofá', '5678901234567', 500, 'Caja', 1, 'Unidad', TO_DATE('2026-01-01', 'YYYY-MM-DD'), 4);

INSERT INTO ORDENCOMPRA (id_compra, cantidad, prrcioAcordado, fechaEspera, estado, id_sucursal, id_producto, id_proveedor) VALUES (1, 100, '100000', TO_DATE('2023-10-01', 'YYYY-MM-DD'), 'Pendiente', 1, 1, 1);
INSERT INTO ORDENCOMPRA (id_compra, cantidad, prrcioAcordado, fechaEspera, estado, id_sucursal, id_producto, id_proveedor) VALUES (2, 200, '150000', TO_DATE('2023-11-01', 'YYYY-MM-DD'), 'En camino', 2, 2, 2);
INSERT INTO ORDENCOMPRA (id_compra, cantidad, prrcioAcordado, fechaEspera, estado, id_sucursal, id_producto, id_proveedor) VALUES (3, 300, '300000', TO_DATE('2023-12-01', 'YYYY-MM-DD'), 'Recibido', 3, 3, 3);
INSERT INTO ORDENCOMPRA (id_compra, cantidad, prrcioAcordado, fechaEspera, estado, id_sucursal, id_producto, id_proveedor) VALUES (4, 150, '75000', TO_DATE('2023-09-01', 'YYYY-MM-DD'), 'Pendiente', 4, 4, 4);
INSERT INTO ORDENCOMPRA (id_compra, cantidad, prrcioAcordado, fechaEspera, estado, id_sucursal, id_producto, id_proveedor) VALUES (5, 50, '50000', TO_DATE('2023-08-01', 'YYYY-MM-DD'), 'Recibido', 5, 5, 5);

INSERT INTO RECEPCIONPRODUCTO (id_recepcion, fechaRecepcion, cantidadRecibida, costoUnitario, id_bodega, id_producto) VALUES (1, TO_DATE('2023-09-15', 'YYYY-MM-DD'), 100, 1200, 1, 1);
INSERT INTO RECEPCIONPRODUCTO (id_recepcion, fechaRecepcion, cantidadRecibida, costoUnitario, id_bodega, id_producto) VALUES (2, TO_DATE('2023-10-20', 'YYYY-MM-DD'), 200, 1500, 2, 2);
INSERT INTO RECEPCIONPRODUCTO (id_recepcion, fechaRecepcion, cantidadRecibida, costoUnitario, id_bodega, id_producto) VALUES (3, TO_DATE('2023-11-25', 'YYYY-MM-DD'), 300, 1.5, 3, 3);
INSERT INTO RECEPCIONPRODUCTO (id_recepcion, fechaRecepcion, cantidadRecibida, costoUnitario, id_bodega, id_producto) VALUES (4, TO_DATE('2023-12-30', 'YYYY-MM-DD'), 150, 25, 4, 4);
INSERT INTO RECEPCIONPRODUCTO (id_recepcion, fechaRecepcion, cantidadRecibida, costoUnitario, id_bodega, id_producto) VALUES (5, TO_DATE('2024-01-05', 'YYYY-MM-DD'), 50, 500, 5, 5);

INSERT INTO INFOEXTRABODEGA (id_bodega, id_producto, costopromedio, mivelMinReorden, capacidadAlmacenamiento, totalExistencias) VALUES (1, 1, 1100, 50, 10000, 4500);
INSERT INTO INFOEXTRABODEGA (id_bodega, id_producto, costopromedio, mivelMinReorden, capacidadAlmacenamiento, totalExistencias) VALUES (2, 2, 1400, 30, 8000, 2900);
INSERT INTO INFOEXTRABODEGA (id_bodega, id_producto, costopromedio, mivelMinReorden, capacidadAlmacenamiento, totalExistencias) VALUES (3, 3, 1.4, 20, 6000, 1800);
INSERT INTO INFOEXTRABODEGA (id_bodega, id_producto, costopromedio, mivelMinReorden, capacidadAlmacenamiento, totalExistencias) VALUES (4, 4, 23, 40, 7000, 2400);
INSERT INTO INFOEXTRABODEGA (id_bodega, id_producto, costopromedio, mivelMinReorden, capacidadAlmacenamiento, totalExistencias) VALUES (5, 5, 450, 60, 9000, 3900);

INSERT INTO PRODUCTOPERECEDERO (id_producto, fechaVencimiento) VALUES (3, TO_DATE('2024-03-01', 'YYYY-MM-DD'));
INSERT INTO PRODUCTOPERECEDERO (id_producto, fechaVencimiento) VALUES (1, TO_DATE('2025-12-31', 'YYYY-MM-DD'));
INSERT INTO PRODUCTOPERECEDERO (id_producto, fechaVencimiento) VALUES (2, TO_DATE('2026-12-31', 'YYYY-MM-DD'));
INSERT INTO PRODUCTOPERECEDERO (id_producto, fechaVencimiento) VALUES (4, TO_DATE('2025-01-01', 'YYYY-MM-DD'));
INSERT INTO PRODUCTOPERECEDERO (id_producto, fechaVencimiento) VALUES (5, TO_DATE('2026-01-01', 'YYYY-MM-DD'));

INSERT INTO INFOEXTRAORDEN (id_categoria, id_producto, cantidad, costoUnitario) VALUES (1, 1, 100, 1200);
INSERT INTO INFOEXTRAORDEN (id_categoria, id_producto, cantidad, costoUnitario) VALUES (2, 3, 200, 1.5);
INSERT INTO INFOEXTRAORDEN (id_categoria, id_producto, cantidad, costoUnitario) VALUES (3, 4, 150, 25);
INSERT INTO INFOEXTRAORDEN (id_categoria, id_producto, cantidad, costoUnitario) VALUES (4, 5, 50, 500);
INSERT INTO INFOEXTRAORDEN (id_categoria, id_producto, cantidad, costoUnitario) VALUES (1, 2, 100, 1500);

INSERT INTO ORDENPRODUCTO (id_producto, id_compra) VALUES (1, 1);
INSERT INTO ORDENPRODUCTO (id_producto, id_compra) VALUES (2, 2);
INSERT INTO ORDENPRODUCTO (id_producto, id_compra) VALUES (3, 3);
INSERT INTO ORDENPRODUCTO (id_producto, id_compra) VALUES (4, 4);
INSERT INTO ORDENPRODUCTO (id_producto, id_compra) VALUES (5, 5);

INSERT INTO PRODUCTOPROVEEDOR (id_producto, id_proveedor) VALUES (1, 1);
INSERT INTO PRODUCTOPROVEEDOR (id_producto, id_proveedor) VALUES (2, 2);
INSERT INTO PRODUCTOPROVEEDOR (id_producto, id_proveedor) VALUES (3, 3);
INSERT INTO PRODUCTOPROVEEDOR (id_producto, id_proveedor) VALUES (4, 4);
INSERT INTO PRODUCTOPROVEEDOR (id_producto, id_proveedor) VALUES (5, 5);