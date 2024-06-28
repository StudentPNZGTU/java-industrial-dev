# SHOP-CART

## Описание проекта

Проект представляет собой систему управления корзиной покупок, разработанную с использованием Java и Spring Boot. Основные функции включают в себя добавление, удаление и просмотр товаров в корзине, а также обработку данных с использованием Postgres.

## Взаимодействует с API
![image](https://github.com/StudentPNZGTU/java-industrial-dev/assets/143221017/b7f42ed2-3708-4f30-a9fc-f2f17e644149)

*https://fakestoreapi.com/docs*

## Используемые технологии

- **Java**: основной язык программирования проекта.
- **Spring Boot**: фреймворк для создания веб-приложений.
- **Postgres**: популярная реляционная база данных.
- **Gradle**: инструмент автоматизации сборки проектов.
- **JUnit**: библиотека для модульного тестирования.
## Фреймворки
- **[Java-Console-View](https://github.com/thousandlemons/Java-Console-View)**
- **[Swagger](https://github.com/swagger-api/swagger-core)**
- **[Jakarta](https://github.com/datanucleus/jakarta.persistence)**

### application.properties
## Конфигурация `application.properties`

```properties
# Настройки базы данных
spring.datasource.url=jdbc:postgresql://localhost:5432/shopcart
spring.datasource.username=name
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

# Настройки Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# Параметры генератора данных
data.generator.user.count=10
data.generator.product.count=10
data.generator.cart.count=10
data.generator.cart.product.count=5

# Порт сервера
server.port=8081
```

## Используемые паттерны
- Singleton
- Abstract factory
- Builder
- Adapter
- Iterator
