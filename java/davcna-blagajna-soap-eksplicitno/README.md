# Davčna Blagajna – JAX‑WS (eksplicitno)

Spletna storitev SOAP (Metro/JAX‑WS), kjer je končna točka definirana **eksplicitno** — najprej z vmesnikom `IDavcnaBlagajnaService`, nato z implementacijo `DavcnaBlagajnaService` in anotacijo `@WebService(endpointInterface=...)`.

- Imen­sko področje (targetNamespace): `http://si.feri.soa/davcnablagajna/service/1.0`
- Privzeti endpoint: `/davcnablagajna`
- WSDL: `http://localhost:8080/davcnablagajna?wsdl` (po potrebi zamenjaj port)

## Zahteve
- Java 21+ (priporočeno Temurin/Oracle JDK 25)
- Maven 3.9+
- (za Docker) Docker Desktop / Engine

## Hiter zagon (lokalno)
```bash
cd java/davcna-blagajna-soap-eksplicitno
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

> Če imaš že zagnano implicitno različico na 8080, lahko to storitev poženeš na drugem portu (npr. 8081). Port nastavi v `Server.java` in ponovno zgradi.  
> Začasno lahko tudi premapiraš porta pri Dockerju (glej spodaj).

## Docker
```bash
cd java/davcna-blagajna-soap-eksplicitno
docker build -t davcna-blagajna:eksplicitno .
# če je na hostu zaseden 8080, premapiraj na 8081
docker run --rm -p 8081:8080 davcna-blagajna:eksplicitno
# WSDL: http://localhost:8081/davcnablagajna?wsdl
```

## Struktura
```
src/
  main/java/si/feri/soa/davcnablagajna/
    IDavcnaBlagajnaService.java  # @WebService (vmesnik)
    DavcnaBlagajnaService.java   # @WebService(endpointInterface=...)
    Server.java                  # Endpoint.publish(.../davcnablagajna)
Dockerfile
pom.xml
```
