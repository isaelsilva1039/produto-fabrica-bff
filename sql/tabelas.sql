CREATE SCHEMA IF NOT EXISTS seguranca;



CREATE TABLE seguranca.usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    role VARCHAR(50)
);



CREATE SCHEMA IF NOT EXISTS nota;

CREATE TABLE IF NOT EXISTS nota.nota_fiscal (
    id SERIAL PRIMARY KEY,
    chave_nf VARCHAR(44) NOT NULL,
    url_nf TEXT,
    data_hora_coleta TIMESTAMP,
    status INTEGER NOT NULL,
    usuario_id BIGINT NOT NULL
);



CREATE SCHEMA IF NOT EXISTS  cadastro;

-- Tabela GRUPO
CREATE TABLE cadastro.grupo (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255)
);

-- Tabela CATEGORIA
CREATE TABLE cadastro.categoria (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255)
);

-- Tabela BANDEIRA
CREATE TABLE cadastro.bandeira (
	id bigserial NOT NULL,
	descricao varchar(255) NULL,
	hash_imagem varchar(255) NULL,
	caminho_imagem varchar(255) NULL,
	CONSTRAINT bandeira_pkey PRIMARY KEY (id)
);

-- Tabela MARCA
CREATE TABLE cadastro.marca (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255)
);

-- Tabela PRODUTO
CREATE TABLE cadastro.produto (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    id_categoria INTEGER REFERENCES cadastro.categoria(id)
);

-- Tabela PRODUTO_ITEM
CREATE TABLE cadastro.produto_item (
    id SERIAL PRIMARY KEY,
    id_produto INTEGER REFERENCES cadastro.produto(id),
    descricao VARCHAR(255),
    tipo_codigo VARCHAR(100),
    id_marca INTEGER REFERENCES cadastro.marca(id),
    peso DECIMAL(10,2),
    hash_imagem TEXT,
    codigo_barras VARCHAR(50)
);

-- Tabela MERCADO
CREATE TABLE cadastro.mercado (
    id SERIAL PRIMARY KEY,
    fantasia VARCHAR(255),
    razao_social VARCHAR(255),
    tipo_pessoa VARCHAR(50),
    id_grupo INTEGER REFERENCES cadastro.grupo(id),
    id_bandeira INTEGER REFERENCES cadastro.bandeira(id),
    id_cidade INTEGER,
    endereco VARCHAR(255),
    bairro VARCHAR(100),
    longitude DECIMAL(9,6),
    latitude DECIMAL(9,6),
    ddd VARCHAR(3),
    telefone VARCHAR(20)
);

-- Tabela CUPOM
CREATE TABLE cadastro.cupom (
    id SERIAL PRIMARY KEY,
    id_mercado INTEGER REFERENCES cadastro.mercado(id),
    id_usuario INTEGER,
    data_coleta TIMESTAMP,
    data_compra TIMESTAMP,
    valor_compras DECIMAL(10,2)
);

-- Tabela CUPOM_ITEM
CREATE TABLE cadastro.cupom_item (
    id SERIAL PRIMARY KEY,
    id_cupom INTEGER REFERENCES cadastro.cupom(id),
    id_produto_item INTEGER REFERENCES cadastro.produto_item(id),
    qtde DECIMAL(10,2),
    preco DECIMAL(10,2)
);

-- Tabela DISPENSA
CREATE TABLE cadastro.dispensa (
    id SERIAL PRIMARY KEY,
    id_usuario INTEGER,
    data_criacao TIMESTAMP
);

-- Tabela DISPENSA_ITEM
CREATE TABLE cadastro.dispensa_item (
    id SERIAL PRIMARY KEY,
    id_dispensa INTEGER REFERENCES cadastro.dispensa(id),
    id_produto INTEGER REFERENCES cadastro.produto(id),
    peso_consumo DECIMAL(10,2),
    peso_estoque DECIMAL(10,2),
    dias_reposicao INTEGER,
    data_ultima_rep TIMESTAMP
);



ALTER TABLE cadastro.cupom DROP CONSTRAINT IF EXISTS fk_cupom_usuario;
ALTER TABLE cadastro.dispensa DROP CONSTRAINT IF EXISTS fk_dispensa_usuario;


ALTER TABLE cadastro.mercado
ADD COLUMN cnpj VARCHAR(20);

-- Garante que o CNPJ seja único, evitando mercados duplicados:
CREATE UNIQUE INDEX idx_mercado_cnpj ON cadastro.mercado (cnpj);


CREATE TABLE cadastro.preco_item_mercado (
    id SERIAL PRIMARY KEY,
    id_produto_item INTEGER REFERENCES cadastro.produto_item(id),
    id_mercado INTEGER REFERENCES cadastro.mercado(id),
    preco DECIMAL(10,2),
    data_coleta TIMESTAMP,
    UNIQUE (id_produto_item, id_mercado)
);



CREATE TABLE cadastro.produto_item_info_fiscal (
    id SERIAL PRIMARY KEY,
    id_produto_item INTEGER REFERENCES cadastro.produto_item(id),
    id_mercado INTEGER REFERENCES cadastro.mercado(id),
    cst_icms VARCHAR(100),
    valor_total_icms DECIMAL(10,2),
    cst_pis VARCHAR(100),
    valor_pis DECIMAL(10,2),
    cst_cofins VARCHAR(100),
    valor_cofins DECIMAL(10,2),
    data_atualizacao TIMESTAMP,
    UNIQUE (id_produto_item, id_mercado)
);


ALTER TABLE cadastro.produto_item_info_fiscal
    ALTER COLUMN cst_icms TYPE VARCHAR(255),
    ALTER COLUMN cst_pis TYPE VARCHAR(255),
    ALTER COLUMN cst_cofins TYPE VARCHAR(255);

