CREATE DATABASE todolist;

USE todolist;

CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    descriptionn TEXT,
    statsus VARCHAR(20) NOT NULL DEFAULT 'ToDo',
    priority VARCHAR(20) NOT NULL DEFAULT 'Undefined',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    delated_at TIMESTAMP NULL 
);