INSERT INTO socio(id_socio, nombre, apellido, direccion, telefono) VALUES(10, "Juan José", "Jiménez Gil", "Calle Lago 6", "654321987");
INSERT INTO socio(id_socio, nombre, apellido, direccion, telefono) VALUES(13, "Nerea", "Armario Redondo", "Félix Pons, 9, Bajo A", "654987321");

INSERT INTO barco(id_barco, matricula, nombre, numero_amarre, cuota_barco, fk_id_socio) VALUES(01, "6543AAA", "Live4EVER", 69, 500.95, 10);
INSERT INTO barco(id_barco, matricula, nombre, numero_amarre, cuota_barco, fk_id_socio) VALUES(02, "6543BBB", "Die4EVER", 71, 400.95, 10);
INSERT INTO barco(id_barco, matricula, nombre, numero_amarre, cuota_barco, fk_id_socio) VALUES(03, "6543CCC", "Vacaciones", 73, 300.95, 13);
INSERT INTO barco(id_barco, matricula, nombre, numero_amarre, cuota_barco, fk_id_socio) VALUES(04, "6543DDD", "ArmarioBoat", 75, 200.95, 13);
