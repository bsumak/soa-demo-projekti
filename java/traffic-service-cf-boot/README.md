# traffic-cxf-boot — Spring Boot (Docker & lokalni zagon)

SOAP storitev na osnovi **Spring Boot**. Modul se lahko zažene kot Docker zabojnik ali neposredno iz JAR datoteke.

## Zahteve
- Docker Desktop / Engine
- Java 21 in Maven 3.9+ (za lokalni zagon brez Dockerja)

## Hiter zagon (Docker)
```bash
docker build -t traffic-cxf-boot:latest .
docker run --rm -p 9090:9090 traffic-cxf-boot:latest

```
# WSDL: http://localhost:9090/services/trafficservice?wsdl

## Lokalni zagon (brez Docker)
```bash
mvn -DskipTests clean package
java -jar target/traffic-cxf-boot-*.jar
# Če je repackage, se lahko imenuje tudi target/spring-boot-starter-parent-3.3.5.jar ali *-exec.jar
```

## Konfiguracija
- Privzeti port: **8080** (spremeni z `server.port=8080` v `application.properties` ali `-Dserver.port=...`)
- Podprta spremenljivka okolja: `JAVA_OPTS` (npr. `-Xms256m -Xmx512m`)

## Struktura
```
.
├─ pom.xml
├─ Dockerfile
├─ .dockerignore
└─ src/...
```


### Opomba
- Privzeti port v Dockerju je 9090. Po potrebi ga spremeniš z `-e SERVER_PORT=xxxx` in preslikaš z `-p xxxx:xxxx`.
