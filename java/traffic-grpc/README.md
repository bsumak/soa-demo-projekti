# TrafficService – gRPC (Java + Protobuf)

Ta projekt je primer **gRPC** storitve za:
1) **registracijo/aktivacijo senzorja** in  
2) **poročanje o prometu**.

Pogodba (protokol) je definirana v `src/main/proto/trafficservice.proto`, iz katere Maven med gradnjo generira Java razrede (Protobuf + gRPC stubs).

## Zahteve
- Java **17**
- Maven **3.9+**

## Hiter zagon

### 1) Prevedi in generiraj razrede iz `.proto`
```bash
mvn clean compile
```

> Generirane datoteke se pojavijo v `target/generated-sources/protobuf/`.

### 2) Zaženi strežnik (1. konzola)
```bash
mvn -q exec:java -Dexec.mainClass="si.feri.soa.server.TrafficSensorServer"
# strežnik posluša na portu 50051
```

### 3) Zaženi odjemalca (2. konzola)
```bash
mvn -q exec:java -Dexec.mainClass="si.feri.soa.client.TrafficSensorClient"
```

## gRPC API (povzetek)

### Storitev `TrafficService`
- `TrafficSensorActivation(TrafficSensorData) -> TrafficSensorActivationResponse`
- `TrafficFlow(TrafficFlowData) -> TrafficFlowResponse`

### Sporočila
- `TrafficSensorData`: `sensor_name`, `sensor_type`, `lattitude`, `longitude`
- `TrafficSensorActivationResponse`: `sensor_id`, `activation_status`, `activation_date`
- `TrafficFlowData`: `sensor_id`, `datum_od`, `datum_do`, `flow`
- `TrafficFlowResponse`: `response`

## Kaj naredi demo implementacija?
- **Aktivacija senzorja** vrne naključno generiran `sensor_id` (UUID), status `OK` in trenutni čas.
- **Poročilo prometa** vrne besedilni odgovor s povzetkom prejetih podatkov.

## Struktura projekta (ključni deli)
```
.
├─ pom.xml
└─ src/
   ├─ main/proto/trafficservice.proto
   └─ main/java/si/feri/soa/
      ├─ server/TrafficSensorServer.java
      ├─ service/TrafficSensorServiceImpl.java
      └─ client/TrafficSensorClient.java
```

## Opombe
- Strežnik je nastavljen na **port 50051**. Če ga spremeniš, posodobi tudi odjemalca (`TrafficSensorClient`).
- V tem demu se uporablja “plain text” (brez TLS), kar je primerno za lokalno okolje.
