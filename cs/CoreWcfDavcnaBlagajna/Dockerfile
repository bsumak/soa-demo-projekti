# ===== 1) Build =====
FROM mcr.microsoft.com/dotnet/sdk:9.0 AS build
WORKDIR /src

# csproj za cache
COPY *.csproj ./
RUN dotnet restore ./CoreWcfDavcnaBlagajna.csproj

# preostala koda
COPY . ./
RUN dotnet publish -c Release -o /app/publish

# ===== 2) Run =====
FROM mcr.microsoft.com/dotnet/aspnet:9.0
WORKDIR /app
COPY --from=build /app/publish ./

# če v Program.cs uporabljaš UseUrls("http://0.0.0.0:8082"):
EXPOSE 8082
ENTRYPOINT ["dotnet", "CoreWcfDavcnaBlagajna.dll"]
