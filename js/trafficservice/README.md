# TrafficService (Node.js, contract-first SOAP storitev)

Ta projekt prikazuje **Node.js** implementacijo SOAP spletne storitve **TrafficService** po pristopu *contract‑first*. Storitev je implementirana na osnovi WSDL pogodbe iz repozitorija (mapa `traffic-service`).

Tipičen scenarij uporabe:
- v mapi `traffic-service/` definiramo WSDL + XSD (tehnični vmesnik),
- v tem projektu (`js/trafficservice`) z Node.js + SOAP knjižnico implementiramo dejansko storitev, ki spoštuje definicijo v WSDL.

## Predpogoji

- Nameščen **Node.js (LTS)** in **npm**
- Dostopna WSDL definicija storitve (npr. datoteka v mapi `traffic-service/`)
- (Opcijsko) Docker za containerized zagon

## Namestitev in zagon (lokalno)

V korenu repozitorija:

```bash
cd js/trafficservice
npm install
npm run start   # ali: node index.js / node app.js (glede na projekt)
```

Po zagonu:
- v konzoli se izpiše naslov HTTP vmesnika (host + port),
- WSDL je praviloma na istem naslovu, npr.:
  - `http://localhost:9090/trafficservice?wsdl`

Natančen naslov preveri v izpisu ob zagonu ali v konfiguraciji projekta.

## Contract‑first pristop

1. **Definicija pogodbe**  
   WSDL + XSD sta pripravljena vnaprej (mapa `traffic-service/`). Ta definicija je *vir resnice* za operacije, tipe in strukture sporočil.

2. **Implementacija v Node.js**  
   V tem projektu:
   - naložimo WSDL (lokalna datoteka ali URL),
   - uporabimo SOAP knjižnico (npr. `soap`, `strong-soap` ali podobno) za izpostavitev SOAP storitve,
   - implementiramo handlerje za operacije, definirane v WSDL (npr. `GetTrafficEvents`, `GetTrafficInfo`, ...).

3. **Testiranje**  
   - S programom **SoapUI** ali podobnim uvozimo WSDL iz zagnanega Node.js strežnika,
   - pošiljamo SOAP zahteve in preverimo, da struktura sporočil in odgovori ustrezajo pogodbi.

## Konfiguracija

- Pot do WSDL datoteke je običajno nastavljena v konfiguracijski datoteki ali neposredno v kodi (npr. `index.js`, `app.js` ali `config/*.js`).
- Po potrebi:
  - posodobi pot do WSDL (če je premaknjena datoteka v `traffic-service/`),
  - spremeni port, na katerem posluša Node.js aplikacija.

## Docker (opcijsko)

Če ima projekt pripravljen `Dockerfile`, lahko storitev zaženeš v Docker vsebniku:

```bash
cd js/trafficservice
docker build -t trafficservice-node:latest .
docker run --rm -p 9090:9090 trafficservice-node:latest
# WSDL (primer): http://localhost:9090/trafficservice?wsdl
```

Port 9090 po potrebi prilagodi (npr. 9091, 8080 …), da se ne tepe z drugimi primeri v istem repozitoriju.

## Povezava z drugimi projekti

- WSDL + XSD pogodba: `traffic-service/`
- Java implementacije TrafficService:
  - `java/traffic-service-cf`
  - `java/traffic-service-cf-boot`
