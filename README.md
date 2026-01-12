# SOA demo projekti (Java, .NET, JavaScript)

Ta repozitorij vsebuje demo projekte za predavanja/vaje s področja **storitveno usmerjenih arhitektur (SOA)**:
- **SOAP** spletne storitve (Contract‑First in Code‑First) + odjemalci
- **REST** API (primeri za domeno *računi*) v različnih tehnologijah
- **GraphQL** API (primeri za domeno *študenti/predmeti/ocene*)
- **gRPC** primer (strežnik + odjemalec) za domeno *TrafficService*

---

## SOAP storitve (strežniki)

- **Java / JAX‑WS (implicitno)** → [`java/davcna-blagajna-soap-implicitno`](java/davcna-blagajna-soap-implicitno)
- **Java / JAX‑WS (eksplicitno, z vmesnikom)** → [`java/davcna-blagajna-soap-eksplicitno`](java/davcna-blagajna-soap-eksplicitno)
- **Java / Contract‑First na osnovi Apache CXF (JAX‑WS)** → [`java/traffic-service-cf`](java/traffic-service-cf) — samostojna JAX‑WS implementacija
- **Java / Contract‑First na osnovi Spring Boot + Apache CXF** → [`java/traffic-service-cf-boot`](java/traffic-service-cf-boot) — Boot aplikacija z vključenim CXF
- **.NET / CoreWCF** → [`cs/CoreWcfDavcnaBlagajna`](cs/CoreWcfDavcnaBlagajna)
- **JavaScript / Node.js (SOAP + Express)** → [`js/davcnablagajna`](js/davcnablagajna)
- **WSDL & XSD (tehnični vmesnik / pogodba)** → [`traffic-service`](traffic-service) — pogodba za *TrafficService* (Contract‑First)

---

## Odjemalci SOAP storitev

- **Java / JAX‑WS odjemalec za storitev Davčna blagajna** → [`java/davcna-blagajna-client`](java/davcna-blagajna-client)
- **.NET odjemalec za storitev Davčna blagajna** → [`cs/DavcnaBlagajnaOdjemalec`](cs/DavcnaBlagajnaOdjemalec)
- **JavaScript / Node.js odjemalec za Davčno blagajno** → [`js/davcnablagajnaclient`](js/davcnablagajnaclient)
- **JavaScript / primer za *TrafficService*** → [`js/trafficservice`](js/trafficservice)

---

## REST API projekti (računi)

Da ne mešamo SOAP in REST primerov, so projekti za **REST API (računi)** zbrani v tej sekciji.  
Vsak projekt vsebuje svoj README z natančnimi navodili za zagon in uporabo API‑ja.

### Java

- **JAX‑RS / REST (računi)** → [`java/racuni-rest`](java/racuni-rest)
- **Spring Boot / REST (računi)** → [`java/spring-racuni`](java/spring-racuni)

### .NET (C#)

- **ASP.NET Core Web API (računi)** → [`cs/AspNetCoreRacuni`](cs/AspNetCoreRacuni)
- **ASP.NET Core Minimal API (računi)** → [`cs/MinimalApiRacuni`](cs/MinimalApiRacuni)

### JavaScript (Node.js)

- **REST API (računi)** → [`js/racunirestapi`](js/racunirestapi)

---

## GraphQL projekti (študenti)

- **Spring Boot / GraphQL + JPA + H2** → [`java/student-gql-jpa`](java/student-gql-jpa)

Hiter zagon:
```bash
cd java/student-gql-jpa
./mvnw spring-boot:run
# GraphiQL: http://localhost:8080/graphiql
```

---

## gRPC projekti

- **Java / gRPC (TrafficService)** → [`java/traffic-grpc`](java/traffic-grpc)

Hiter zagon (dve konzoli):
```bash
cd java/traffic-grpc
mvn -q exec:java -Dexec.mainClass="si.feri.soa.server.TrafficSensorServer"
```

```bash
cd java/traffic-grpc
mvn -q exec:java -Dexec.mainClass="si.feri.soa.client.TrafficSensorClient"
```

---

## Zahteve

- **Docker** (Desktop ali Engine) — če zaganjate preko Dockerja
- **Java 17+** (priporočeno 25) in **Maven 3.9+** — za Java projekte
- **.NET SDK 8.0** — za C# projekte
- **Node.js (LTS) in npm** — za projekte v mapi `js/`

---

## Hitri zagon (lokalno)

> Za REST projekte (računi) priporočam, da sledite navodilom v README posameznega projekta (mape se razlikujejo glede na tehnologijo).

### Java – JAX‑WS (implicitno)
```bash
cd java/davcna-blagajna-soap-implicitno
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

### Java – JAX‑WS (eksplicitno)
```bash
cd java/davcna-blagajna-soap-eksplicitno
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8080/davcnablagajna?wsdl
```

### Java – TrafficService (Apache CXF, JAX‑WS)
```bash
cd java/traffic-service-cf
mvn -DskipTests clean package
java -jar target/traffic-service-cf.jar
# WSDL: http://localhost:9090/trafficservice?wsdl
```

### Java – TrafficService (Spring Boot + CXF)
```bash
cd java/traffic-service-cf-boot
mvn -DskipTests clean package
java -jar target/traffic-service-cf-boot-*.jar
# WSDL: http://localhost:9090/services/trafficservice?wsdl
```

> **Opomba (Java + Metro/JAX‑WS na JDK 17+)**: zaradi internega reflektiranja je pogosto potreben
> `--add-opens java.base/java.lang=ALL-UNNAMED`.

### .NET – CoreWCF
```bash
cd cs/CoreWcfDavcnaBlagajna
dotnet restore
dotnet run
# WSDL: http://localhost:8082/DavcnaBlagajna?wsdl
```

### JavaScript – Davčna blagajna (Node.js)
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

### Java – TrafficService (Apache CXF, JAX‑WS)
```bash
cd java/traffic-service-cf
docker build -t traffic-service-cf:latest .
docker run --rm -p 9090:9090 traffic-service-cf:latest
# WSDL: http://localhost:9090/trafficservice?wsdl
```

### Java – TrafficService (Spring Boot + CXF)
```bash
cd java/traffic-service-cf-boot
docker build -t traffic-service-cf-boot:latest .
docker run --rm -e SERVER_PORT=9090 -p 9090:9090 traffic-service-cf-boot:latest
# WSDL: http://localhost:9090/services/trafficservice?wsdl
```

### .NET – CoreWCF
```bash
cd cs/CoreWcfDavcnaBlagajna
docker build -t corewcf-davcnablagajna:latest .
docker run --rm -p 8082:8082 corewcf-davcnablagajna:latest
# WSDL: http://localhost:8082/DavcnaBlagajna?wsdl
```

### JavaScript – Davčna blagajna (Node.js)
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
├── cs/
│   ├── AspNetCoreRacuni/                  # ASP.NET Core Web API – REST API za račune
│   ├── CoreWcfDavcnaBlagajna/             # .NET CoreWCF implementacija SOAP storitve Davčna blagajna
│   ├── DavcnaBlagajnaOdjemalec/           # .NET odjemalec za SOAP storitev Davčna blagajna
│   └── MinimalApiRacuni/                  # ASP.NET Core Minimal API – REST API za račune
├── java/
│   ├── davcna-blagajna-client/            # Java odjemalec za SOAP storitev Davčna blagajna (JAX-WS)
│   ├── davcna-blagajna-soap-eksplicitno/  # Java JAX-WS – eksplicitna definicija storitve (z vmesnikom)
│   ├── davcna-blagajna-soap-implicitno/   # Java JAX-WS – implicitna definicija storitve
│   ├── racuni-rest/                       # Java REST API za račune (JAX-RS)
│   ├── spring-racuni/                     # Spring Boot REST API za račune
│   ├── student-gql-jpa/                  # Spring Boot GraphQL + JPA + H2 – primer za študente
│   ├── traffic-grpc/                      # gRPC strežnik + odjemalec (TrafficService) + .proto
│   ├── traffic-service-cf/                # Apache CXF JAX-WS – Contract-First implementacija (TrafficService)
│   └── traffic-service-cf-boot/           # Spring Boot + CXF – Contract-First implementacija (TrafficService)
├── js/
│   ├── davcnablagajna/                    # Node.js implementacija SOAP storitve Davčna blagajna
│   ├── davcnablagajnaclient/              # Node.js odjemalec za SOAP storitev Davčna blagajna
│   ├── racunirestapi/                     # Node.js REST API za račune
│   └── trafficservice/                    # Node.js primer za storitev TrafficService (Contract-First)
└── traffic-service/                       # WSDL + XSD (tehnični vmesnik) za TrafficService (Contract-First)
```
