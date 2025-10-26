# Davčna Blagajna – CoreWCF (.NET 8)

CoreWCF primer za SOAP storitev, ki posluša na **8082**. WSDL je omogočen prek `ServiceMetadataBehavior`.

- Endpoint: `/DavcnaBlagajna`
- WSDL: `http://localhost:8082/DavcnaBlagajna?wsdl`

## Zahteve
- .NET SDK 8.0
- (za Docker) Docker Desktop / Engine

## Hiter zagon (lokalno)
```bash
cd cs/CoreWcfDavcnaBlagajna
dotnet restore
dotnet run
# WSDL: http://localhost:8082/DavcnaBlagajna?wsdl
```

## Docker
```bash
cd cs/CoreWcfDavcnaBlagajna
docker build -t corewcf-davcnablagajna:latest .
docker run --rm -p 8082:8082 corewcf-davcnablagajna:latest
# WSDL: http://localhost:8082/DavcnaBlagajna?wsdl
```

## Struktura
```
Services/
  IDavcnaBlagajna.cs    # [ServiceContract] vmesnik
  DavcnaBlagajna.cs     # [ServiceBehavior] implementacija
Models/
  Racun.cs, PoslovniProstor.cs
Program.cs              # konfiguracija CoreWCF + metadata
Dockerfile
CoreWcfDavcnaBlagajna.csproj
```
