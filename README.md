Написать простого бота используя rest API Беларусбанка

Возможности бота:

1. При старте бота регистрация пользователя. 
	1.1 Ввод фио
	1.2 Поделится контактом, взять getContact(), получить номер телефона и сохранить в бд.
	1.3 Сообщение об успешной регистрации

Все данные сохранять в базу данных в сущность user

2. После регистрации вовести главное меню (или инлайн кнопки):
	2.1. Получить актуальные новости с сайта беларусбанка и вывести в бота (последние 3-5 новостей).
	2.2. Получить курс валют (USD, EURO) 3 городов(на выбор). С каждого города по 2-3 подразделения.

Реализация кнопок в боте на свое усмотрение.

API банка: https://belarusbank.by/ru/33139/forDevelopers/api