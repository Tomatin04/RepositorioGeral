create table usuarios(

    id bigint not null auto_increment,
    nomeUsuario varchar(100) not null,
    senha varchar(255) not null unique,
    email varchar(255) not null unique,
    deleteAccount BOOLEAN not null default true

    primary key(id)

);

