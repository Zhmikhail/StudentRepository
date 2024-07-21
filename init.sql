-- Создание таблицы должностей
CREATE TABLE position (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(50) NOT NULL
);

-- Вставка данных в таблицу должностей
INSERT INTO position (name) VALUES ('Менеджер');
INSERT INTO position (name) VALUES ('Разработчик');
INSERT INTO position (name) VALUES ('Тестировщик');

-- Создание таблицы пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       birth_date DATE NOT NULL,
                       position_id INTEGER REFERENCES position(id),
                       salary NUMERIC(10, 2) NOT NULL,
                       hire_date DATE NOT NULL,
                       fire_date DATE
);
