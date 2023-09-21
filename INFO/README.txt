TEST REST API
---------------

Отримання всіх даних
GET
http://localhost:8082/api/v1.0/contacts

Створення даних
POST
http://localhost:8082/api/v1.0/contacts

Налаштування в Postman: Body, raw, JSON.

{
    "id": 5,
    "name": "John",
    "phone": "333 444-5555"
}
Отримання всіх даних
GET
http://localhost:8082/api/v1.0/contacts


Створення даних
POST
http://localhost:8082/api/v1.0/contacts

Налаштування в Postman: Body, raw, JSON.

{
    "id": 6,
    "name": "Mark",
    "phone": "333 555-7777"
}

Отримання всіх даних
GET
http://localhost:8082/api/v1.0/contacts


Видалення даних за id
DELETE
http://localhost:8082/api/v1.0/contacts/2

Отримання всіх даних
GET
http://localhost:8082/api/v1.0/contacts