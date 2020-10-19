create table tb_aluno
(
    id bigint generated by default as identity,
    ativo boolean,
    data_atualizacao timestamp,
    data_criacao timestamp not null,
    numero_cartao varchar(255),
    nome varchar(255),
    primary key (id)
)