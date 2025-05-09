## 1. Clonando o Repositório

Para começar, você precisa clonar o repositório do projeto para sua máquina local. Abra o terminal e execute o seguinte comando:

### 1.1 Clonando o repositório com Git

Execute o comando abaixo para clonar o repositório:

```bash
  git clone https://github.com/isaelsilva1039/produto-fabrica-bff.git

  cd /diretorio

```


# Configuração do Ambiente para Docker e Build
Esse ponto voce só precisa fazer uma vez (serve para ambos os repo).
Este guia descreve os passos necessários para configurar o arquivo `/etc/hosts` no seu sistema e rodar o build da aplicação com Docker.

## 2. Modificando o arquivo `/etc/hosts`

Primeiro, precisamos editar o arquivo `/etc/hosts` para garantir que a configuração do Docker funcione corretamente. Siga os passos abaixo:

### 2.1 Abrir o arquivo `/etc/hosts`

Use o seguinte comando para editar o arquivo `hosts` com permissões de superusuário:

```bash
  sudo nano /etc/hosts
```

### 2.2 Adicione as entradas
Dentro do arquivo, adicione a seguinte linha para garantir que o Docker consiga resolver o nome host.docker.internal
```bash
  127.0.0.1 localhost host.docker.internal
```

### 3 Execute o Build com docker-compose
Com o arquivo de configuração do Docker (docker-compose.yml) já presente no projeto, use o comando abaixo para iniciar o processo de build e rodar os containers:

```bash
  docker-compose up --build -d
```

