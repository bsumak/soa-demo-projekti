# Računi – ASP.NET Core Minimal API (.NET 9)

REST storitev za upravljanje **računov** (CRUD) implementirana z **ASP.NET Core Minimal API**. Projekt je namenjen demonstraciji osnovnih REST vzorcev (GET/POST/PUT/DELETE) brez kontrolerjev, z uporabo preprostega **in-memory repozitorija**.

## Funkcionalnosti

- Seznam vseh računov
- Pridobivanje računa po `id`
- Dodajanje novega računa
- Posodabljanje obstoječega računa
- Brisanje računa
- Vgrajen primer klicev v datoteki `racuni.http`

## Podatkovni model

`Racun`:

```json
{
  "id": "string (GUID)",
  "datum": "dd.MM.yyyy",
  "kraj": "string",
  "znesek": 0.0,
  "ppId": "string",
  "davcnaSt": "string"
}
```

> Opomba: Implementacija uporablja **in-memory** shrambo (`ConcurrentDictionary`) prek `IRacunRepository`/`RacunRepository`. Ob zagonu je dodanih nekaj začetnih primerov računov.

## REST API

Osnovna pot: `/api/racuni`

### GET /api/racuni
Vrne seznam vseh računov.

- **200 OK** + seznam računov

### GET /api/racuni/{id}
Vrne račun z izbranim `id`.

- **200 OK** + račun
- **400 Bad Request** (prazen/manjkajoč `id`)
- **404 Not Found** (račun ne obstaja)

### POST /api/racuni
Ustvari nov račun.

- **201 Created** + ustvarjen račun (in `Location` header)
- **400 Bad Request** (neveljaven payload)

### PUT /api/racuni/{id}
Posodobi obstoječi račun.

- **204 No Content**
- **400 Bad Request** (prazen/manjkajoč `id` ali neveljaven payload)
- **404 Not Found** (račun ne obstaja)

### DELETE /api/racuni/{id}
Izbriše račun.

- **204 No Content**
- **400 Bad Request** (prazen/manjkajoč `id`)
- **404 Not Found** (račun ne obstaja)

## Zagon projekta

### Predpogoji
- Nameščen **.NET SDK 9** (TargetFramework: `net9.0`)

### Zagon (CLI)

V mapi projekta:

```bash
dotnet restore
dotnet run
```

Privzeti URL-ji (po `launchSettings.json`):
- HTTP: `http://localhost:5247`
- HTTPS: `https://localhost:7271`

Če želiš nastaviti drug port:

```bash
dotnet run --urls "http://localhost:5000"
```

## Testiranje

### `.http` datoteka
Projekt vsebuje `racuni.http` z vzorčnimi klici (primerno za:
- Visual Studio / JetBrains Rider HTTP Client
- VS Code + REST Client razširitev)

Primer:

```http
GET http://localhost:5247/api/racuni
```

## Struktura projekta

- `Program.cs` – definicija Minimal API endpointov
- `Models/Racun.cs` – model
- `Models/IRacunRepository.cs` – vmesnik repozitorija
- `Models/RacunRepository.cs` – in-memory implementacija

## Opombe

- Projekt ne uporablja baze podatkov (podatki se ob ponovnem zagonu izgubijo).
- V primeru CORS, avtentikacije ali Swagger/OpenAPI podpore je potrebno dodati ustrezne middleware komponente (po potrebi pri nadgradnji projekta).
