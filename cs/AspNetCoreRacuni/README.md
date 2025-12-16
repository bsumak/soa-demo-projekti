# Računi – ASP.NET Core Web API (Controllers)

Enostaven primer **REST API** storitve za upravljanje *računov* (CRUD). Projekt je namenjen demonstraciji osnov ASP.NET Core kontrolerjev, pravilnih HTTP odzivov ter testiranja z `.http` datoteko.

## Kaj je implementirano

- REST API endpointi za **račune** (`/api/racuni`)
- Operacije: **GET (vsi)**, **GET (po id)**, **POST (ustvari)**, **PUT (posodobi)**, **DELETE (izbriši)**
- Validacije vhodnih podatkov (npr. prazen `id`, manjkajoče telo, neusklajen `id` v poti/telesu)
- Shranjevanje podatkov **v pomnilniku** (thread‑safe `ConcurrentDictionary`) – brez baze (podatki se ob ponovnem zagonu izgubijo)
- Generiranje **OpenAPI** opisa v razvojnem okolju (Development)

## Tehnologije

- .NET **9.0**
- ASP.NET Core Web API (controllers)
- `Microsoft.AspNetCore.OpenApi`

## Zagon projekta

### Predpogoji

- Nameščen **.NET SDK 9.0** (ali novejši, ki podpira `net9.0`)
- (Neobvezno) Visual Studio / Rider / VS Code

### Zagon iz ukazne vrstice

V korenu projekta (mapa z datoteko `AspNetCoreRacuni.csproj`) zaženi:

```bash
dotnet restore
dotnet run
```

Privzete povezave (glej `Properties/launchSettings.json`):

- HTTP: `http://localhost:5047`
- HTTPS: `https://localhost:7212`

## OpenAPI

V **Development** okolju je mapiran OpenAPI opis. Privzeta pot je običajno:

- `GET /openapi/v1.json`

Primer:

```bash
curl http://localhost:5047/openapi/v1.json
```

> Opomba: če uporabljaš HTTPS, zamenjaj shemo in port.

## API

Osnovna pot:

- `/api/racuni`

### Model: `Racun`

```json
{
  "id": "string (GUID, generira strežnik pri POST)",
  "datum": "string",
  "kraj": "string",
  "znesek": 0.0,
  "ppId": "string",
  "davcnaSt": "string"
}
```

### Endpointi

| Metoda | Pot | Opis | Odzivi |
|---|---|---|---|
| GET | `/api/racuni` | Vrne seznam vseh računov | `200 OK` |
| GET | `/api/racuni/{id}` | Vrne račun po ID | `200 OK`, `400 BadRequest`, `404 NotFound` |
| POST | `/api/racuni` | Ustvari nov račun (ID se generira) | `201 Created`, `400 BadRequest`, `500` |
| PUT | `/api/racuni/{id}` | Posodobi obstoječ račun | `204 NoContent`, `400 BadRequest`, `404 NotFound`, `409 Conflict` |
| DELETE | `/api/racuni/{id}` | Izbriše račun | `204 NoContent`, `400 BadRequest`, `404 NotFound` |

## Primeri klicev

V projektu je pripravljena datoteka **`racuni.http`** s primeri vseh klicev.

### POST (ustvari)

```http
POST http://localhost:5047/api/racuni
Content-Type: application/json

{
  "Datum":"16.12.2025",
  "Kraj": "Maribor",
  "Znesek": 502.33,
  "PpId": "453534543534543",
  "DavcnaSt": "2929292929"
}
```

### GET (vsi)

```bash
curl http://localhost:5047/api/racuni
```

### PUT (posodobi)

```bash
curl -X PUT "http://localhost:5047/api/racuni/<id>" \
  -H "Content-Type: application/json" \
  -d '{
    "datum":"16.12.2025",
    "kraj":"Maribor",
    "znesek":102.33,
    "ppId":"453534543534543",
    "davcnaSt":"2929292929"
  }'
```

### DELETE (izbriši)

```bash
curl -X DELETE http://localhost:5047/api/racuni/<id>
```

## Pomembne opombe

- Podatki so shranjeni **samo v RAM-u** (ni baze). Po ponovnem zagonu aplikacije se zbirka izprazni.
- `POST` ne dovoljuje, da klient nastavi `Id` – `Id` se generira na strežniku.
- `PUT` uporablja `ConcurrentDictionary.TryUpdate(...)` (optimistična posodobitev). Če je objekt vmes spremenjen, vrne `409 Conflict`.

## Struktura projekta (hitro)

- `Program.cs` – konfiguracija aplikacije, mapiranje kontrolerjev in OpenAPI
- `Controllers/RacuniController.cs` – REST kontroler za račune
- `Models/Racun.cs` – podatkovni model
- `Properties/launchSettings.json` – porti in profili zagona
- `racuni.http` – primeri testnih klicev

