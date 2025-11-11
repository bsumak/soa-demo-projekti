# traffic-service-cf — Apache CXF (Docker & lokalni zagon)

Implementacija SOAP storitve z **Apache CXF**. Modul se lahko zažene kot Docker zabojnik ali neposredno iz JAR datoteke.

## Zahteve
- Docker Desktop / Engine
- Java 21 in Maven 3.9+ (za lokalni zagon brez Dockerja)

## Hiter zagon (Docker)
```bash
docker build -t traffic-service-cf:latest .
docker run --rm -p 9090:9090 traffic-service-cf:latest
# WSDL po zagonu: http://localhost:9090/trafficservice?wsdl
```

## Lokalni zagon (brez Docker)
```bash
mvn -DskipTests clean package
java -jar target/traffic-service-cf.jar
# WSDL po zagonu: http://localhost:9090/trafficservice?wsdl
```

## Konfiguracija
- Privzeti port storitve: **9090** (po želji premapiraj, npr. `-p 8080:9090`).
- Okoljska spremenljivka `JAVA_OPTS` je podprta tako lokalno kot v Dockerju.
  - Primer: `JAVA_OPTS="-Xms256m -Xmx512m"`

## Struktura
```
.
├─ pom.xml
├─ Dockerfile
├─ .dockerignore
└─ src/...
```

## Opombe
- Gradnja ustvarja **uber (shaded) JAR** z nastavljenim `Main-Class` (`traffic-service-cf.jar`), zato zagon deluje z `java -jar`.
- WSDL in XSD sta v `src/main/resources/wsdl/` in se servirata na `/trafficservice?wsdl`.
