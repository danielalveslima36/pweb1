CREATE TABLE usuario (
    id serial primary key,
    login varchar(50),
    nome varchar(150),
    senha varchar(64),
    data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP,
    ativo boolean DEFAULT true
)