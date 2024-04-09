CREATE DATABASE empresaFIltro;

USE empresaFIltro;

CREATE TABLE empresa(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    sector varchar (255) not null,
    ubicacion varchar (255) not null,
    contacto varchar (255) not null
);

CREATE TABLE coder(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    apellido varchar (255) not null,
    documento varchar (255) unique  not null,
    cohorte int not null,
    cv text  not null
);

CREATE TABLE vacante(
	id int primary key auto_increment,
    titulo varchar (255) not null,
    description text not null,
    duracion varchar (255) not null,
    estado varchar(255) not null ,
    CHECK(estado IN("activa","desactivo")),
    
    id_empresa int not null,
    
    CONSTRAINT FK_idEmpresa FOREIGN KEY (id_empresa) REFERENCES empresa(id) ON DELETE CASCADE
);

CREATE table contratacion(
	id int primary key auto_increment not null not null,
    fecha_aplication TIMESTAMP DEFAULT current_timestamp not null,
    estado VARCHAR(255) not null,
    salario decimal(10,2) not null,
    
    id_vacante int not null,
    id_coder int not null,
    
    CONSTRAINT FK_idvacante FOREIGN KEY (id_vacante) REFERENCES vacante(id)ON DELETE CASCADE,
    CONSTRAINT FK_idcoder FOREIGN KEY (id_coder) REFERENCES coder(id)ON DELETE CASCADE
);

ALTER TABLE vacante ADD (tecnoligia text);

Alter table coder ADD (clan text);





