# Description
Программа предназначена для инициализации Aerospike

# Prerequisite
## Maven
На Jetson убунту содержит старую неработающую с jdk-17 версию

## Aerospike
Текущая версия aerospike:ce-6.3.0.6_1
Запуск в первый раз:
```
docker run -d --name aerospike -e "NAMESPACE=frm" -p 3000-3003:3000-3003 aerospike:ce-6.3.0.6_1
```
Последующие запуски:
```
docker container start aerospike
```
## JDK-17
Версия зависит от Aerospike REST Gateway
```
sudo apt-get install openjdk-17-jdk
```
# Build
```
mvn clean install
```

# Run
```
mvn spring-boot:run
```

# REST API
https://aerospike.github.io/aerospike-rest-gateway/
Server: http://127.0.0.1:8080
namespace: frm

# Пример запроса
```
curl -X 'GET' 'http://127.0.0.1:8080/v1/kvs/frm/A123BC199/visits' -H 'accept: application/json'
```

## 1. Бренды машин
set "brands"
key "all"   
бин: список брендов

## 2. Модели бренда
set "brands"
key имя бренда из списка 1, например"BMW"
бин: список моделей бренда

## 3. События
set номер автомобиля, например "A123BC199" 
key "visits"
бин: дата_время без пробела, in-въезд/out-выезд

## 4. Платежи
set номер автомобиля, например "A123BC199" 
key "payments"
бин: дата_время платежа без пробела, сумма платежа 1000.00

## 5. Баланс
set номер автомобиля, например "A123BC199" 
key "balance"
бин: "rub", сумма баланса 1000.00

## 6. Имя владельца авто
set номер автомобиля, например "A123BC199" 
key "name"
бин: "fio","например "Илья Александрович Ю."

## 7. Список ролей
set "roles"
key "all"
бин: список ролей

## 8. Роль владельца авто
set номер автомобиля, например "A123BC199" 
key "role"
бин: "position", имя роли например "сотрудник"

## 9. Типы
set "types" 
key "all"
бин: список типов

## 10. Тип владельца авто
set номер автомобиля, например "A123BC199" 
key "type"
бин: "type",тип например "Постоянный"

## 11. Логотип в ЛК
set номер автомобиля, например "A123BC199" 
key "logo"
бин: "url", ссылка на картинку в minio "https://img.png"

## 12. Описание автомобиля
set номер автомобиля, например "A123BC199"
key "car"
бин: "model", модель например "Audi A8"
бин: "color",цвет например "Черный металлик"

## 13. Шлагбаумы
set "barriers" 
key "all"
бин: список номеров шлагбаумов

## 14. Шлагбаум
set "barriers" 
key номер шлагбаума например 1
бин: "name", наименование шлагбаума например "Въезд 1"

## 15. Цвета
set "colors" 
key "all"
бин: список цветов
