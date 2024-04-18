# График в режиме реального времени валютных пар

## Технологии
1. [JFreeChart](https://github.com/jfree/jfreechart)
2. [gson](https://github.com/google/gson)
3. [Spring Web](https://spring.io/projects/spring-framework/)
4. [OpenCSV](https://opencsv.sourceforge.net/)
5. [Log4J](https://logging.apache.org/log4j/2.x/)

## MVC
model
* api
* filters

view
* chart drawer
* console printer


controller

## Используемое API
```properties
path.csv=input.csv
api.url=https://min-api.cryptocompare.com/data/v2/histominute?&limit=1
```

Ознакомится с *[документацией](https://min-api.cryptocompare.com/documentation)* **API**

### Сборка приложения
```sh
gradle clean build
```

### Запуск приложения
```sh
java -jar task2-1.0.jar
```

* `logs/app.log` - Логирование
* `input.csv` - Файл с прошлым графиком. **Должен находится в текущей директории**

## Интерфейс пользователя
![image](https://github.com/StudentPNZGTU/java-industrial-dev/assets/143221017/f3b00d82-c291-4c5c-ad1d-c7ea44d02e64)

### Результат
![image](https://github.com/StudentPNZGTU/java-industrial-dev/assets/143221017/51890a0e-cf48-4ad8-bf82-07aabce1ca03)
