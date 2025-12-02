# davcnablagajnaclient (Node.js SOAP odjemalec)

Projekt **davcnablagajnaclient** je **Node.js odjemalec** za SOAP storitev *Davčna blagajna*. Prikazuje več načinov klicanja SOAP storitve:

- ročno sestavljen SOAP envelope in klic preko `fetch`,
- klic prek knjižnice `soap` z uporabo `async/await`,
- klic prek knjižnice `soap` z uporabo callback vzorca.

Cilj je demonstrirati, kako lahko iz JavaScript/Node.js okolja kličeš obstoječo SOAP storitev na osnovi WSDL pogodbe.

## Predpogoji

- **Node.js 18+** (zaradi uporabe ES modulov in `fetch`)
- **npm**
- zagnana strežniška implementacija storitve *Davčna blagajna*, npr.:
  - Java eksplicitna različica na `http://localhost:8081/davcnablagajna?wsdl`, ali
  - druga ustrezna implementacija z enakovrednim WSDL/end-point naslovom.

## Namestitev odvisnosti

V mapi projekta (npr. `js/davcnablagajnaclient`):

```bash
npm install
```

Datoteka `package.json` vsebuje odvisnosti:

- `soap` – Node.js SOAP knjižnica,
- `pretty-data` – za lepši izpis XML,
- `xml-formatter` – dodatno formatiranje XML (če ga uporabiš v demonstracijah).

## Datoteke v projektu

- `jsclient.js`  
  Primer ročnega pošiljanja SOAP zahtevka z uporabo `fetch`:
  - sestavi celoten SOAP envelope kot niz,
  - pošlje `POST` na `http://localhost:8081/davcnablagajna`,
  - prejme XML odgovor in ga lepo izpiše z `pretty-data`.

- `soapclientasync.js`  
  Primer uporabe knjižnice `soap` z `async/await`:
  - ustvari SOAP klienta na osnovi WSDL (`http://localhost:8081/davcnablagajna?wsdl`),
  - pripravi objekt `args` (podatki o računu),
  - pokliče operacijo `NovRacunAsync` in izpiše rezultat.

- `soapclientasynccallback.js`  
  Primer uporabe knjižnice `soap` s klasičnim callback vzorcem:
  - ustvari SOAP klienta,
  - pokliče `NovRacun` z `args`,
  - v callbacku izpiše rezultat (npr. ID registriranega računa).

## Zagon primerov

Pred zagonom odjemalcev poskrbi, da je strežniška storitev *Davčna blagajna* zagnana, npr. Java projekt:

```bash
# primer – Java eksplicitna storitev
cd java/davcna-blagajna-soap-eksplicitno
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-soap-1.0-SNAPSHOT.jar
# WSDL: http://localhost:8081/davcnablagajna?wsdl
```

Nato v mapi Node.js odjemalca (npr. `js/davcnablagajnaclient`):

### 1. Ročni SOAP klic z `fetch`

```bash
node jsclient.js
```

- pošlje SOAP envelope s podatki o računu,
- sprejme XML odgovor,
- izpiše formatiran XML v konzolo.

### 2. SOAP odjemalec z `async/await`

```bash
node soapclientasync.js
```

- ustvari SOAP klienta na osnovi WSDL,
- pokliče metodo `NovRacunAsync(args)`,
- izpiše objekt `result` (JSON reprezentacija odgovora).

### 3. SOAP odjemalec s callback vzorcem

```bash
node soapclientasynccallback.js
```

- ustvari SOAP klienta,
- pokliče `NovRacun(args)`,
- v callbacku izpiše npr. ID registriranega računa.

## Prilagoditve

- **Naslov storitve (URL/WSDL)**  
  Če strežnik ne teče na `http://localhost:8081/davcnablagajna?wsdl`, posodobi URL v datotekah:
  - `jsclient.js` (`const url = "http://localhost:8081/davcnablagajna";`),
  - `soapclientasync.js` (`const wsdl = 'http://localhost:8081/davcnablagajna?wsdl';`),
  - `soapclientasynccallback.js` (`const wsdl = "http://localhost:8081/davcnablagajna?wsdl";`).

- **Podatki o računu (`args`)**  
  Po potrebi prilagodi vsebino elementov (datum, davčna številka, znesek …), da ustrezajo validaciji na strežniški strani.

## Povezava z drugimi projekti

Ta projekt je namenjen uporabi skupaj z ostalimi primeri v repozitoriju **SOA demo projekti**:

- Java strežniške implementacije storitve Davčna blagajna:
  - `java/davcna-blagajna-soap-implicitno`
  - `java/davcna-blagajna-soap-eksplicitno`
- .NET CoreWCF različica storitve: `cs/CoreWcfDavcnaBlagajna`
- Node.js implementacija same storitve: `js/davcnablagajna`

Skupaj omogočajo demonstracijo SOAP storitev in odjemalcev v različnih tehnologijah.
