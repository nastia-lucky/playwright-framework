FROM mcr.microsoft.com/playwright/java:v1.58.0

WORKDIR /app

# Кэшируем зависимости Maven
COPY pom.xml .

RUN mvn dependency:go-offline

# Копируем проект
COPY src ./src

# Копируем скрипт запуска
COPY run-tests.sh .

RUN chmod +x run-tests.sh

ENTRYPOINT ["./run-tests.sh"]