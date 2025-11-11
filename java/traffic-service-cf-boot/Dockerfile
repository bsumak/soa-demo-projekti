# ===== Build stage =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests package

# ===== Runtime stage =====
FROM eclipse-temurin:21-jre
ENV TZ=Europe/Ljubljana \
    LANG=C.UTF-8 \
    JAVA_OPTS="" \
    SERVER_PORT=9090
WORKDIR /app
# copy repackaged (spring-boot) jar if present, else any jar
COPY --from=build /build/target/*-exec.jar /app/app.jar
COPY --from=build /build/target/*-SNAPSHOT.jar /app/app.jar
COPY --from=build /build/target/*spring-boot-starter-parent*.jar /app/app.jar

EXPOSE 9090
HEALTHCHECK --interval=30s --timeout=5s --start-period=20s --retries=3 \
  CMD wget -qO- "http://localhost:$SERVER_PORT/services/trafficservice?wsdl" || exit 1

ENTRYPOINT ["/bin/sh","-c","exec java $JAVA_OPTS -Dserver.port=$SERVER_PORT -jar /app/app.jar"]
