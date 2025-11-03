# Davčna Blagajna – Node.js (Express + soap)

Primer SOAP storitve v Node.js, ki posluša na **9090**. WSDL datoteka je prebrana iz lokalne datoteke `DavcnaBlagajna.wsdl`, endpoint pa se izpostavi preko Express + `soap` knjižnice.

- Endpoint: `/davcnablagajna`
- WSDL: `http://localhost:9090/davcnablagajna?wsdl`

## Zahteve
- Node.js 20+
- (za Docker) Docker Desktop / Engine

## Struktura (primer)
```
js/
  davcnablagajna/
    index.js                 # glavna datoteka (Express + soap)
    DavcnaBlagajna.wsdl      # WSDL, ki ga bere storitev
    package.json
    Dockerfile
    README.md
```

## Hiter zagon (lokalno)
```bash
cd javascript/<ime-projekta>
npm install
node index.js
# WSDL: http://localhost:9090/davcnablagajna?wsdl
```

## Docker
```bash
cd javascript/<ime-projekta>
docker build -t node-davcnablagajna:latest .
docker run --rm -p 9090:9090 node-davcnablagajna:latest
# WSDL: http://localhost:9090/davcnablagajna?wsdl
```

## Opombe
- Koda v `index.js` posluša na portu **9090** in izpostavi SOAP storitev na poti **/davcnablagajna**:
  ```js
  const PORT = 9090;
  let service_path = "/davcnablagajna";
  // WSDL bo na http://localhost:9090/davcnablagajna?wsdl
  ```
- Ker Docker izvede `node index.js` iz mape `/app`, naj bosta `index.js` in `DavcnaBlagajna.wsdl` v isti mapi.
- Če uporabljaš drugačno ime vstopne datoteke, spremeni `CMD` v `Dockerfile` ali dodaj `scripts.start` v `package.json` in uporabi `CMD ["npm", "start"]`.
