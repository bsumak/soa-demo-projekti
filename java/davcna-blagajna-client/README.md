# davcna-blagajna-client (Java)

Projekt **davcna-blagajna-client** je **Java odjemalec** za SOAP storitev *Davčna blagajna*. Namenjen je demonstraciji uporabe JAX‑WS (ali sorodnih knjižnic) za klicanje obstoječe SOAP storitve na osnovi WSDL pogodbe.

## Predpogoji

- **Java 21+** (priporočeno 25)
- **Maven 3.9+**
- Ena izmed strežniških implementacij Davčne blagajne mora biti zagnana, npr.:
  - `java/davcna-blagajna-soap-implicitno`
  - `java/davcna-blagajna-soap-eksplicitno`
  - `cs/CoreWcfDavcnaBlagajna` (če je odjemalec konfiguriran na .NET varianto)

## Struktura projekta (tipična Maven struktura)

- `pom.xml` – Maven konfiguracija, odvisnosti (JAX‑WS, logiranje, testni frameworki ipd.)
- `src/main/java/...` – odjemalska logika, morebitni JAX‑WS generirani razredi (stub-i)
- `src/main/resources/...` – konfiguracija, lastnosti, morda WSDL (če je vključen lokalno)
- `src/test/java/...` – enotni testi (če obstajajo)

## Gradnja in zagon

```bash
cd java/davcna-blagajna-client
mvn -DskipTests package
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/davcna-blagajna-client-*.jar
```

Če projekt nima konfiguriranega *executable jar*, lahko uporabiš enega izmed naslednjih pristopov:

- zaženeš neposredno `main` razred, npr.:

  ```bash
  java --add-opens java.base/java.lang=ALL-UNNAMED -cp target/davcna-blagajna-client-*.jar com.example.Main
  ```

- ali uporabiš Maven plugin:

  ```bash
  mvn exec:java -Dexec.mainClass="com.example.Main"
  ```

(ime paketa/razreda prilagodi dejanski strukturi projekta).

## Konfiguracija odjemalca

Naslov SOAP storitve (endpoint / WSDL) je običajno nastavljen v:

- konfiguracijskem razredu,
- datoteki lastnosti (npr. v `src/main/resources`),
- ali neposredno v kodi pri ustvarjanju `Service` / `Port` objektov.

Prilagodi URL, da kaže na zagnano storitev, npr.:

- `http://localhost:8080/davcnablagajna?wsdl`
- `http://localhost:8081/davcnablagajna?wsdl`
- `http://localhost:8082/DavcnaBlagajna?wsdl` (CoreWCF varianta)

## Povezava z drugimi projekti

- Strežniški Java projekti Davčna blagajna:
  - `java/davcna-blagajna-soap-implicitno`
  - `java/davcna-blagajna-soap-eksplicitno`
- Strežniški .NET CoreWCF projekt: `cs/CoreWcfDavcnaBlagajna`
- JavaScript implementacija storitve Davčna blagajna: `js/davcnablagajna`
