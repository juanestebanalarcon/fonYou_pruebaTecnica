create database fonYouApp;
use fonYouApp;
create table preguntas(
Id int not null auto_increment primary key,
enunciado varchar(40) not null default 'descripción_pregunta',
opc_a boolean not null default false,
opc_b boolean null default false,
opc_c boolean null default false,
opc_d boolean null default false,
valor_pregunta int not null default 0
);
create table examen (
id bigint not null auto_increment primary key,
puntaje int not null default 0,
cantidadPreguntas int not null default 0,
id_pregunta int not null,
constraint fk_preguntas foreign key(id_pregunta) references preguntas(Id)
);

create table estudiante(
id bigint not null auto_increment primary key,
nombre varchar(30) not null default ' ',
edad int not null default 0,
ciudad varchar(50) not null default ' ',
zona_horaria timestamp null,
id_examen bigint not null,
constraint fkexamen foreign key(id_examen) references examen(id),
constraint chkedad check(edad >0)
);
