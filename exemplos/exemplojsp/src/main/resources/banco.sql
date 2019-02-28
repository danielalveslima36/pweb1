CREATE TABLE usuario (
    id serial primary key,
    login varchar(50),
    nome varchar(150),
    senha varchar(64),
    data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP,
    ativo boolean DEFAULT true
);

CREATE TABLE produto (
	id serial primary key,
	nome varchar(50),
	preco float,
    data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP,
    descricao varchar(255),
    estoque int
);