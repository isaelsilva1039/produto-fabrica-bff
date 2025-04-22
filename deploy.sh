#!/bin/bash

echo "🧹 Limpando e gerando novo .jar..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Erro ao gerar o .jar"
    exit 1
fi

echo "📦 Copiando app.jar para a pasta do Docker..."
cp target/app.jar /opt/api-pega-preco/

cd /opt/api-pega-preco || exit

echo "🐳 Rebuild e up dos containers..."
docker-compose up -d --build

echo "📝 Aguardando logs da API..."
sleep 5
docker logs -f pega-preco-api
