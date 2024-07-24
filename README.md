# Проект "База данных работников в docker с подключением по API"

## Как собрать проект?

Скачать, зайти в папку проекта в терминале и запустить команды:
    
    docker-compose up -d
и 

    ./build_and_run.sh 

## Что умеет?

#### В проекте предусмотрено несколько запросов к базе данных:

Получение информации обо всех пользователях - getAllUsers

    curl -X GET http://localhost:8080/users/getAllUsers

Получение информации об одном пользователе - getUser

    curl -X GET http://localhost:8080/users/getUser/<id>
Сохранение пользователя - addUser

    curl -X POST http://localhost:8080/users/addUser -H "Content-Type: application/json" -d '{
    "firstName": <firstName>,
    "lastName": <lastName>,
    "birthDate": <birthDate>,
    "positionId": <positionId>,
    "salary": <salary>,
    "hireDate": <hireDate>,
    "fireDate": <fireDate>
    }'

Обновление пользователя - updateUser

    curl -X PUT http://localhost:8080/users/updateUser/5 -H "Content-Type: application/json" -d '{
    "firstName": <firstName>,
    "lastName": <lastName>,
    "birthDate": <birthDate>,
    "positionId": <positionId>,
    "salary": <salary>,
    "hireDate": <hireDate>,
    "fireDate": <fireDate>
    }'

Удаление пользователя - deleteUser

    curl -X DELETE http://localhost:8080/users/deleteUser/2
