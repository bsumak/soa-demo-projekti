# ===== 1) Build stage: Maven + JDK =====
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -Dmaven.test.skip=true package

# ===== 2) Run stage: JRE =====
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/app.jar /app/app.jar

# (opcijsko) healthcheck WSDL
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*
HEALTHCHECK --interval=20s --timeout=3s --retries=5 \
CMD curl -fsS http://localhost:8080/davcnablagajna?wsdl >/dev/null || exit 1

EXPOSE 8080
# JDK 25 + Metro: potreben add-opens
ENTRYPOINT ["java","--add-opens","java.base/java.lang=ALL-UNNAMED","-jar","/app/app.jar"]
