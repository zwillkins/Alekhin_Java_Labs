Это для запуска в терминале

javac ru/xxx/Main.java

java ru.xxx.Main

+++++++++++++++++++++++++++++++++++++++++++++=

./mvnw spring-boot:run

curl http://localhost:8080/api/products
curl http://localhost:8080/api/users
curl -X POST -H "Content-Type: application/json" -d '{"name":"Клавиатура", "price":75.99}' http://localhost:8080/api/products
curl -X PUT -H "Content-Type: application/json" -d '{"name":"Игровой Ноутбук", "price":1550.0}' http://localhost:8080/api/products/1
curl -i -X DELETE http://localhost:8080/api/products/2

http://localhost:8080/swagger-ui/index.html


+++++++++++++++++++++++++++++++++++++++++++++=

java -jar target/notification-service-0.0.1-SNAPSHOT.jar

java -jar target/printer-farm-api-0.0.1-SNAPSHOT.jar