# SOA demo projekti (Java & .NET & JavaScript)

Primeri SOAP spletnih storitev za predavanja in vaje:
- **Java / JAX-WS (implicitno)** → [`java/davcna-blagajna-soap-implicitno`](java/davcna-blagajna-soap-implicitno)
- **Java / JAX-WS (eksplicitno, z vmesnikom)** → [`java/davcna-blagajna-soap-eksplicitno`](java/davcna-blagajna-soap-eksplicitno)
- **Java / Apache CXF (JAX-WS)** → [`java/traffic-service-cf`](java/traffic-service-cf) — samostojna JAX‑WS implementacija
- **Java / Spring Boot + Apache CXF** → [`java/traffic-cxf-boot`](java/traffic-cxf-boot) — Boot aplikacija z vključenim CXF
- **.NET / CoreWCF** → [`cs/CoreWcfDavcnaBlagajna`](cs/CoreWcfDavcnaBlagajna)
- **JavaScript / soap in express** → [`js/davcnablagajna`](js/davcnablagajna)
- **WSDL & XSD vmesnik** → [`/traffic-service`](/traffic-service) — samo tehnična pogodba, nastala v okviru Contract-First


## Zahteve
- **Docker** (Desktop ali Engine)
- **Java 21+** (priporočeno 25) in **Maven 3.9+** za lokalni zagon Java projektov
- **.NET SDK 8.0** za lokalni zagon CoreWCF projekta

---

## Hitri zagon (lokalno)

### Java – JAX-WS (implicitno)
```bash
cd java/davcna-blagajna-soap-implicitno
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

### Java – JAX-WS (eksplicitno)
```bash
cd java/davcna-blagajna-soap-eksplicitno
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

### Java – TrafficService (Apache CXF, JAX-WS)
```bash
cd java/traffic-service-cf
mvn -DskipTests clean package
java -jar target/traffic-service-cf.jar
# WSDL: http://localhost:9090/trafficservice?wsdl
```

### Java – TrafficService (Spring Boot + CXF)
```bash
cd java/traffic-cxf-boot
mvn -DskipTests clean package
java -jar target/traffic-cxf-boot-*.jar
# WSDL: http://localhost:9090/services/trafficservice?wsdl
```

> **Opomba (Java + Metro)**: zaradi internega reflektiranja je na JDK 17+ potreben
> `--add-opens java.base/java.lang=ALL-UNNAMED`.

### .NET – CoreWCF
```bash
cd cs/CoreWcfDavcnaBlagajna
dotnet restore
dotnet run
# WSDL: http://localhost:8082/DavcnaBlagajna?wsdl
```

### JavaScript
```bash
cd js/davcnablagajna
npm install
node index.js
# WSDL: http://localhost:9090/davcnablagajna?wsdl
```

---

## Hitri zagon (Docker)

### Java – implicitno
```bash
cd java/davcna-blagajna-soap-implicitno
docker build -t davcna-blagajna:implicitno .
docker run --rm -p 8080:8080 davcna-blagajna:implicitno
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

### Java – eksplicitno
```bash
cd java/davcna-blagajna-soap-eksplicitno
docker build -t davcna-blagajna:eksplicitno .
docker run --rm -p 8081:8080 davcna-blagajna:eksplicitno
# WSDL: http://localhost:8081/davcnablagajna?wsdl
```

### Java – TrafficService (Apache CXF, JAX-WS)
```bash
cd java/traffic-service-cf
docker build -t traffic-service-cf:latest .
docker run --rm -p 9090:9090 traffic-service-cf:latest
# WSDL: http://localhost:9090/trafficservice?wsdl
```

### Java – TrafficServices (Spring Boot + CXF)
```bash
cd java/traffic-cxf-boot
docker build -t traffic-cxf-boot:latest .
docker run --rm -e SERVER_PORT=9090 -p 9090:9090 traffic-cxf-boot:latest
# WSDL: http://localhost:9090/services/trafficservice?wsdl
```

### .NET – CoreWCF
```bash
cd cs/CoreWcfDavcnaBlagajna
docker build -t corewcf-davcnablagajna:latest .
docker run --rm -p 8082:8082 corewcf-davcnablagajna:latest
# WSDL: http://localhost:8082/DavcnaBlagajna?wsdl
```

### JavaScript
```bash
cd js/davcnablagajna
docker build -t node-davcnablagajna:latest .
docker run --rm -p 9090:9090 node-davcnablagajna:latest
# WSDL: http://localhost:9090/davcnablagajna?wsdl
```

---

## Struktura repozitorija
```
.
├── java/
│   ├── davcna-blagajna-soap-implicitno/
│   ├── davcna-blagajna-soap-eksplicitno/
│   ├── traffic-service-cf/       # Apache CXF JAX-WS implementacija
│   └── traffic-cxf-boot/         # Spring Boot + CXF implementacija
├── cs/
│   └── CoreWcfDavcnaBlagajna/
└── js/        
    └── davcnablagajna/
└── traffic-service/              # WSDL + XSD (tehnični vmesnik spletne storitve TrafficService)
```
