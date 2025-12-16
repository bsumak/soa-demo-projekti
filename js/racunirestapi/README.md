# restracuniapi — REST API za račune (Node.js + Express + Mongoose)

Projekt implementira preprost **REST API** za upravljanje z računi (CRUD) na osnovi **Express 5** in **Mongoose**. Podatki se shranjujejo v **in-memory MongoDB** (prek `mongodb-memory-server`), zato *ni potrebna zunanja baza* in se podatki po ponovnem zagonu aplikacije izgubijo.

- Privzeti port: **3000** (ali `PORT` iz okolja)
- Osnovna pot API: **`/api/racuni`**
- Osnovni URL (lokalno): `http://localhost:3000/api/racuni`

---

## Zahteve

- Node.js (priporočeno 18+ / 20+)
- npm

---

## Zagon

### 1) Namestitev odvisnosti
```bash
npm install
```

### 2) Zagon v razvojni obliki (nodemon)
```bash
npm run dev
```

### 3) Zagon brez nodemon
```bash
node index.js
```

Ob zagonu se samodejno ustvari in poveže **in-memory MongoDB**, nato aplikacija začne poslušati na `http://localhost:3000/api/racuni`.

---

## Struktura projekta

```
.
├─ index.js              # zagon Express + povezava na in-memory MongoDB
├─ routes/
│  └─ racuni.js          # REST poti (CRUD)
├─ models/
│  └─ racun.js           # Mongoose shema/model Racun
├─ racuni.http           # primeri klicev (VS Code REST Client)
├─ package.json
└─ package-lock.json
```

---

## Podatkovni model

Model **Racun** vsebuje naslednja polja:

- `id` *(string, required)* – poslovni identifikator (npr. številka računa)
- `datum` *(string, required)* – datum (npr. `"16.12.2025"`)
- `kraj` *(string, required)* – kraj izdaje
- `znesek` *(number, required)* – znesek
- `ppId` *(string, required)* – identifikator poslovnega prostora/PP
- `davcnaSt` *(string, required)* – davčna številka

MongoDB sam doda še:
- `_id` – Mongo identifikator dokumenta (uporablja se v URL-jih `/:id`)
- `__v` – verzija dokumenta (Mongoose)

> Opomba: V URL-jih za GET/PUT/DELETE se uporablja **Mongo `_id`**, ne polje `id`.

---

## REST API

### GET `/api/racuni`
Vrne seznam vseh računov.

- **200 OK** + JSON seznam
- **500 Internal Server Error** ob napaki na strežniku

### GET `/api/racuni/:id`
Vrne en račun po Mongo `_id`.

- **200 OK** + JSON
- **400 Bad Request** če manjka/prázno `id`
- **404 Not Found** če račun ne obstaja
- **500 Internal Server Error** ob napaki na strežniku

### POST `/api/racuni`
Ustvari nov račun.

- Obvezno polje: **`davcnaSt`** (validacija v kodi)
- Uspešno vrne:
  - **201 Created**
  - `Location` header z URL do ustvarjenega vira
  - JSON ustvarjenega računa

Možni odzivi:
- **400 Bad Request** če manjka `davcnaSt`
- **500 Internal Server Error** ob napaki na strežniku

### PUT `/api/racuni/:id`
Posodobi obstoječi račun.

- **400 Bad Request** če manjka/prázno `id`
- **400 Bad Request** če manjka telo zahtevka
- **400 Bad Request** če se `req.body._id` ne ujema z `:id`
- **404 Not Found** če račun ne obstaja
- **200 OK** + JSON (opomba spodaj)
- **500 Internal Server Error** ob napaki na strežniku

> Opomba: uporabljena je `findByIdAndUpdate(id, req.body)` brez opcije `{ new: true }`, zato lahko API v odgovoru vrne **staro verzijo** dokumenta (odvisno od privzetih nastavitev Mongoose).

### DELETE `/api/racuni/:id`
Izbriše račun po Mongo `_id`.

- **204 No Content** ob uspehu
- **400 Bad Request** če manjka/prázno `id`
- **404 Not Found** če račun ne obstaja
- **500 Internal Server Error** ob napaki na strežniku

---

## Primeri uporabe (curl)

### Ustvari račun
```bash
curl -X POST http://localhost:3000/api/racuni   -H "Content-Type: application/json"   -d '{
    "id": "6546645465465",
    "datum": "16.12.2025",
    "kraj": "Koper",
    "znesek": 134.34,
    "ppId": "20220202020202",
    "davcnaSt": "49394939939"
  }'
```

### Pridobi vse račune
```bash
curl http://localhost:3000/api/racuni
```

### Pridobi račun po `_id`
```bash
curl http://localhost:3000/api/racuni/<_id>
```

### Posodobi račun
```bash
curl -X PUT http://localhost:3000/api/racuni/<_id>   -H "Content-Type: application/json"   -d '{
    "_id": "<_id>",
    "id": "6546645465465",
    "datum": "16.12.2025",
    "kraj": "Koper",
    "znesek": 2334.34,
    "ppId": "20220202020202",
    "davcnaSt": "49394939939"
  }'
```

### Izbriši račun
```bash
curl -X DELETE http://localhost:3000/api/racuni/<_id>
```

---

## Testiranje z datoteko `racuni.http`

V projektu je priložena datoteka **`racuni.http`**, ki vsebuje pripravljene zahteve za:
- GET all
- GET by id
- POST
- PUT
- DELETE

Najbolj uporabno z razširitvijo **REST Client** v VS Code.

---

## Opombe

- **In-memory baza**: podatki se hranijo samo v pomnilniku in se ob ponovnem zagonu aplikacije izgubijo.
- **CORS**: v projektu je uporabljena knjižnica `cors`. Če želiš, da se CORS pravilno aplicira tudi na `/api/racuni`, naj bo `app.use(cors())` registriran **pred** `app.use('/api/racuni', ...)` (ker je vrstni red middleware-ov v Express pomemben).
