CREATE TABLE cadastro.bandeira (
	id bigserial NOT NULL,
	descricao varchar(255) NULL,
	hash_imagem varchar(255) NULL,
	caminho_imagem varchar(255) NULL,
	CONSTRAINT bandeira_pkey PRIMARY KEY (id)
);


CREATE TABLE seguranca.usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    role VARCHAR(50)
);