#!/bin/bash

# Переменные
JAR_NAME="empoyees-0.0.1-SNAPSHOT.jar"
DOCKER_COMPOSE_PATH="docker-compose.yml"

# Шаг 1: Сборка проекта и генерация JAR файла
echo "Сборка проекта..."
./mvnw clean package -DskipTests

# Шаг 2: Проверка наличия JAR файла
if [ ! -f "target/$JAR_NAME" ]; then
    echo "Ошибка: JAR файл не найден!"
    exit 1
fi

echo "JAR файл успешно сгенерирован."

# Шаг 3: Запуск Docker контейнера
echo "Запуск Docker контейнера..."
docker-compose -f $DOCKER_COMPOSE_PATH up -d

# Шаг 4: Проверка запуска Docker контейнера
if [ $? -ne 0 ]; then
    echo "Ошибка: Не удалось запустить Docker контейнер!"
    exit 1
fi

echo "Docker контейнер успешно запущен."

# Шаг 5: Запуск JAR файла
echo "Запуск JAR файла..."
java -jar target/$JAR_NAME

if [ $? -ne 0 ]; then
    echo "Ошибка: Не удалось запустить JAR файл!"
    exit 1
fi

echo "Приложение успешно запущено."
