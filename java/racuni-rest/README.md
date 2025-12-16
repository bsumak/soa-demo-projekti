# racuni-rest — REST API (Jersey + Grizzly)

Projekt **racuni-rest** je preprost primer **RESTful** spletne storitve za upravljanje računov (*CRUD*), implementiran z **Jersey (JAX‑RS)** in vgrajenim strežnikom **Grizzly**. Storitev vrača in sprejema podatke v obliki **JSON** (Jackson).

- Privzeti base URL: `http://localhost:8080/`
- API pot: `http://localhost:8080/api/racuni`

---

## Zahteve

- **Java 21+**
- **Maven 3.9+**

---

## Zagon (lokalno)

### 1) Zgradi projekt
```bash
mvn -DskipTests clean package
```

### 2) Zaženi strežnik
Ker je v `pom.xml` nastavljen `exec-maven-plugin`, je najlažje:
```bash
mvn exec:java
```

Po zagonu bo storitev dostopna na:
- `GET http://localhost:8080/api/racuni`

Za ustavitev strežnika v konzoli pritisni **Enter**.

---

## Model: Racun (JSON)

Primer objekta, ki ga pošlješ pri ustvarjanju (POST):
```json
{
  "datum": "16.12.2025",
  "kraj": "Maribor",
  "znesek": 100.0,
  "ppId": "299292020202",
  "davcnaSt": "838484848"
}
```

Polja:
- `id` *(string)* – ID računa (UUID); pri `POST` se generira na strežniku
- `datum` *(string)* – datum računa
- `kraj` *(string)* – kraj izdaje
- `znesek` *(number)* – znesek
- `ppId` *(string)* – ID poslovnega prostora
- `davcnaSt` *(string)* – davčna številka

---

## API (endpoints)

### GET `/api/racuni`
Vrne seznam računov.

- `200 OK` + JSON array, če obstajajo računi
- `204 No Content`, če ni podatkov

### GET `/api/racuni/{id}`
Vrne račun po ID.

- `200 OK` + JSON objekt, če obstaja
- `400 Bad Request`, če je `id` prazen/neveljaven
- `404 Not Found`, če račun ne obstaja

### POST `/api/racuni`
Ustvari nov račun.

- `201 Created` + JSON ustvarjenega računa
- `Location` header kaže na novo ustvarjen vir (`/api/racuni/{id}`)
- `400 Bad Request`, če manjka telo zahtevka

> Opomba: `id` se vedno nastavi na strežniku (UUID).

### PUT `/api/racuni/{id}`
Posodobi obstoječ račun (celoten objekt).

- `204 No Content`, če je posodobitev uspešna
- `400 Bad Request`, če manjka `id` ali telo, ali če se `id` v poti in v telesu ne ujemata
- `404 Not Found`, če račun ne obstaja

### DELETE `/api/racuni/{id}`
Izbriše račun.

- `204 No Content`, če je brisanje uspešno
- `400 Bad Request`, če je `id` prazen/neveljaven
- `404 Not Found`, če račun ne obstaja

---

## Hitri testi

V projektu je priložena datoteka **`racuni.http`** z vzorčnimi zahtevki (primerno za IntelliJ HTTP Client ali VS Code REST Client).

Primeri s `curl`:

**Ustvari račun**
```bash
curl -i -X POST http://localhost:8080/api/racuni \
  -H "Content-Type: application/json" \
  -d '{ "datum":"16.12.2025","kraj":"Maribor","znesek":100,"ppId":"299292020202","davcnaSt":"838484848" }'
```

**Preberi vse**
```bash
curl -i http://localhost:8080/api/racuni
```

---

## Pomembne opombe

- Podatki se hranijo **v pomnilniku** (in-memory mapa). Ob ponovnem zagonu aplikacije se podatki izgubijo.
- Projekt je namenjen demonstraciji **JAX‑RS** konceptov (resource, HTTP metode, statusne kode, JSON serializacija).
- Avtentikacija, validacije na nivoju pravil (npr. format davčne številke) in trajna baza podatkov niso vključeni.

---

## Struktura projekta

```
racuni-rest/
├─ pom.xml
├─ racuni.http
└─ src/
   └─ main/java/si/um/feri/racuni/
      ├─ Main.java            # zagon Grizzly + Jersey
      ├─ RacuniResource.java  # REST resource (/api/racuni)
      └─ Racun.java           # model (POJO)
```
