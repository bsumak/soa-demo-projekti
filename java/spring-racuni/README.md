# Spring Računi – REST API (Spring Boot + JPA + H2)

Preprost primer **REST** storitve za upravljanje *računov* (CRUD). Aplikacija je zgrajena s **Spring Boot**, podatki se shranjujejo prek **Spring Data JPA** v **H2** bazo (v pomnilniku), storitev pa teče na portu **8085**.

- Osnovna pot API: `/api/racuni`
- Shranjevanje: JPA entiteta `Racun` + `JpaRepository`
- ID: generiran na strežniku (UUID), klient ga pri kreiranju ne pošilja

## Zahteve
- Java **25** (glej `pom.xml`: `<java.version>25</java.version>`)
- Maven (ali Maven Wrapper `./mvnw`)

## Hiter zagon (lokalno)
```bash
cd spring-racuni
./mvnw spring-boot:run
```

Aplikacija bo privzeto dostopna na:
- `http://localhost:8085`

## API – pregled endpointov

### GET /api/racuni
Vrne seznam vseh računov.

**200 OK** + JSON array

### GET /api/racuni/{id}
Vrne posamezen račun po ID.

- **400 Bad Request** (prazen/neveljaven `id`)
- **200 OK** (če obstaja)
- **404 Not Found** (če ne obstaja)
- **500 Internal Server Error** (v primeru napake pri dostopu)

### POST /api/racuni
Ustvari nov račun.

Validacija:
- `davcnaSt` mora biti podana in ne sme biti prazna
- `id` ne sme biti podan (če je podan in ni prazen → **400**)
- `id` se nastavi na strežniku (UUID)

Odziv:
- **201 Created** + `Location` header + JSON ustvarjenega objekta

### PUT /api/racuni/{id}
Posodobi obstoječi račun.

Validacija:
- `id` v poti mora biti podan in ne sme biti prazen
- body ne sme biti `null`
- če body vsebuje `id`, se mora ujemati z `id` v poti (drugače **400**)

Odziv:
- **204 No Content** (če posodobitev uspe)
- **404 Not Found** (če ne obstaja)

### DELETE /api/racuni/{id}
Izbriše račun po ID.

Odziv:
- **204 No Content** (če brisanje uspe)
- **404 Not Found** (če ne obstaja)
- **400 Bad Request** (prazen/neveljaven `id`)

## Primeri klicev (curl)

### Ustvari račun
```bash
curl -i -X POST "http://localhost:8085/api/racuni"   -H "Content-Type: application/json"   -d '{
    "datum": "16.12.2025",
    "kraj": "Maribor",
    "znesek": 100,
    "ppId": "299292020202",
    "davcnaSt": "838484848"
  }'
```

### Pridobi vse
```bash
curl -s "http://localhost:8085/api/racuni" | jq
```

### Posodobi (PUT)
```bash
curl -i -X PUT "http://localhost:8085/api/racuni/<ID>"   -H "Content-Type: application/json"   -d '{
    "id": "<ID>",
    "datum": "16.12.2025",
    "kraj": "Maribor",
    "znesek": 400.0,
    "ppId": "299292020202",
    "davcnaSt": "838484848"
  }'
```

### Izbriši
```bash
curl -i -X DELETE "http://localhost:8085/api/racuni/<ID>"
```

> Namig: v projektu je priložena datoteka `racuni.http` (primeri klicev za IntelliJ / VS Code REST Client).

## Podatkovni model

Entiteta `Racun` vsebuje naslednja polja:
- `id` (String, **@Id**, UUID)
- `datum` (String)
- `kraj` (String)
- `znesek` (double)
- `ppId` (String)
- `davcnaSt` (String)

## H2 konzola
V projektu je uporabljen H2 (runtime). Če je H2 konzola omogočena (starter `spring-boot-h2console`), je običajno dostopna na:
- `http://localhost:8085/h2-console`

Tipične privzete vrednosti (odvisno od konfiguracije Spring Boot):
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: *(prazno)*

## Struktura projekta (ključni deli)
```
spring-racuni/
  pom.xml
  src/main/resources/
    application.properties
  src/main/java/si/um/feri/rest/racuni/
    SpringRacuniApplication.java
    Racun.java
    RacunRepository.java
    RacunController.java
  racuni.http
```

## Opombe
- Če želiš projekt poganjati na drugem JDK, prilagodi `<java.version>` v `pom.xml`.
