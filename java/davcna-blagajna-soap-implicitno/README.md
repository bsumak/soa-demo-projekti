# Davčna Blagajna – JAX‑WS (implicitno)

Spletna storitev SOAP (Metro/JAX‑WS), kjer je končna točka definirana **implicitno** — direktno na razredu z anotacijo `@WebService`.

- Imen­sko področje (targetNamespace): `http://si.feri.soa/davcnablagajna/service/1.0`
- Privzeti endpoint: `/davcnablagajna`
- WSDL: `http://localhost:8080/davcnablagajna?wsdl`

## Zahteve
- Java 21+ (priporočeno Temurin/Oracle JDK 25)
- Maven 3.9+
- (za Docker) Docker Desktop / Engine

## Hiter zagon (lokalno)
```bash
cd java/davcna-blagajna-soap-implicitno
mvn -DskipTests package
# (JDK 17+ z Metro potrebuje add-opens)
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

Če je port 8080 zaseden, prilagodi port v `Server.java` (URI v `Endpoint.publish`) in ponovno zgradi projekt.

## Docker
```bash
cd java/davcna-blagajna-soap-implicitno
docker build -t davcna-blagajna:implicitno .
docker run --rm -p 8080:8080 davcna-blagajna:implicitno
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

## Struktura
```
src/
  main/java/si/feri/soa/davcnablagajna/
    DavcnaBlagajnaService.java   # @WebService (implicitno)
    Server.java                  # Endpoint.publish(.../davcnablagajna)
Dockerfile
pom.xml
```
