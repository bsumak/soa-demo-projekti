# DavcnaBlagajnaOdjemalec (.NET)

Projekt **DavcnaBlagajnaOdjemalec** je zgleden **.NET odjemalec** za SOAP spletno storitev **Davčna blagajna**. Namenjen je prikazu generiranja proxy razredov iz WSDL in izvajanja klicev na obstoječo SOAP storitev.

## Predpogoji

- **.NET SDK 8.0** (ali novejši)
- Za poln preizkus je priporočljivo, da je zagnana strežniška implementacija Davčne blagajne, npr.:
  - `cs/CoreWcfDavcnaBlagajna` (privzeto: `http://localhost:8082/DavcnaBlagajna?wsdl`)
  - ali ena izmed Java različic Davčne blagajne (`java/davcna-blagajna-soap-implicitno`, `java/davcna-blagajna-soap-eksplicitno`).

## Struktura projekta (tipična)

Se lahko malenkost razlikuje glede na dejansko rešitev, običajno pa vsebuje:

- `Program.cs` – vstopna točka odjemalca (klici na storitev, izpis rezultatov)
- generirane proxy razrede / tipe iz WSDL (lahko v ločenem projektu ali mapi)
- konfiguracijo endpointa storitve (appsettings.json, `.config` datoteka ali ročno nastavljanje URL v kodi)

## Namestitev in zagon

V korenu repozitorija:

```bash
cd cs/DavcnaBlagajnaOdjemalec
dotnet restore
dotnet run
```

Odjemalec bo:

- prebral konfiguracijo (naslov storitve),
- izvedel enega ali več SOAP klicev na storitev Davčna blagajna (npr. registracija računa, testni klici ipd.),
- rezultate izpisal v konzolo.

## Konfiguracija naslova storitve

Naslov SOAP storitve je običajno nastavljen v:

- `appsettings.json` ali
- `.config` datoteki (npr. `App.config`) ali
- neposredno v kodi (ustvarjanje `ChannelFactory` / WCF klienta, nastavitev `EndpointAddress`).

Prilagodi URL, da kaže na trenutno zagnano instanco storitve, na primer:
- `http://localhost:8082/DavcnaBlagajna?wsdl` za CoreWCF projekt,
- `http://localhost:8080/davcnablagajna?wsdl` za Java implementacijo.

## Povezava z drugimi projekti

- Strežniška .NET implementacija: `cs/CoreWcfDavcnaBlagajna`
- Java strežniške implementacije:
  - `java/davcna-blagajna-soap-implicitno`
  - `java/davcna-blagajna-soap-eksplicitno`
- JavaScript implementacija storitve Davčna blagajna: `js/davcnablagajna`
