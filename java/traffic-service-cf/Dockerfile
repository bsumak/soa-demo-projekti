# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /src
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
# Compile classes and copy runtime deps to target/dependency
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests package \
 && mvn -q -B -DincludeScope=runtime -DskipTests dependency:copy-dependencies -DoutputDirectory=target/dependency

# Runtime stage
FROM eclipse-temurin:21-jre
ENV TZ=Europe/Ljubljana \
    LANG=C.UTF-8 \
    JAVA_OPTS=""
WORKDIR /app

# Copy compiled classes and dependencies
COPY --from=build /src/target/classes /app/classes
COPY --from=build /src/target/dependency /app/dependency

EXPOSE 9090
HEALTHCHECK --interval=30s --timeout=5s --start-period=20s --retries=3 \
  CMD wget -qO- http://localhost:9090/trafficservice?wsdl || exit 1

# Run the App main class directly on the classpath
ENTRYPOINT ["/bin/sh","-c","exec java $JAVA_OPTS -cp /app/classes:/app/dependency/* si.feri.soa.trafficservice.App"]
