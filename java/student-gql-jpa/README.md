# Študenti – GraphQL API (Spring Boot + GraphQL + JPA + H2)

Ta projekt je preprost primer **GraphQL** strežnika za domeno *študenti/predmeti/ocene*.  
Uporablja **Spring Boot**, **Spring for GraphQL**, **JPA/Hibernate** in **H2** bazo (v pomnilniku).

- GraphQL endpoint: `POST /graphql`
- GraphiQL (UI): `GET /graphiql`
- H2 konzola: `GET /h2-console`
- Baza: `jdbc:h2:mem:studb` (v pomnilniku; podatki se ob ponovnem zagonu izbrišejo)

## Zahteve
- Java **17**
- Maven (ali Maven Wrapper `./mvnw`)

## Hiter zagon (lokalno)
```bash
./mvnw spring-boot:run
# aplikacija:  http://localhost:8080
# GraphiQL:    http://localhost:8080/graphiql
# H2 konzola:  http://localhost:8080/h2-console
```

### H2 konzola (nastavitve povezave)
- JDBC URL: `jdbc:h2:mem:studb`
- Uporabnik: `sa`
- Geslo: *(prazno)*

## GraphQL shema
Shema je definirana v datoteki: `src/main/resources/graphql/schema.graphqls`.

### Query
- `getStudent(id: ID!): Student`
- `vsiPredmeti(id: ID!): [Predmet!]!` *(parameter `id` je v tem demu uporabljen zgolj kot “varovalka”, da mora študent obstajati)*
- `vseOcene(id: ID!): [Ocena!]!`

### Mutation
- `createStudent(ime, priimek, smer): Student!` *(samodejno generira `vpisnaStevilka` in `email`)*
- `updateStudent(id, ime, priimek, smer, email): Student!`
- `deleteStudent(id): Student!`
- `createPredmet / updatePredmet / deletePredmet`
- `createOcena / updateOcena / deleteOcena` *(ocena je vezana na študenta prek `studentId`)*

## Primeri poizvedb (GraphiQL)

### 1) Ustvari študenta
```graphql
mutation {
  createStudent(ime: "Ana", priimek: "Novak", smer: "ITK") {
    id
    vpisnaStevilka
    ime
    priimek
    email
    smer
  }
}
```

### 2) Ustvari predmet
```graphql
mutation {
  createPredmet(naziv: "Storitveno usmerjene arhitekture", semester: "Zimski", ects: 6) {
    id
    naziv
    semester
    ects
  }
}
```

### 3) Dodaj oceno študentu
> `studentId` naj bo ID, ki ga vrne `createStudent`.
```graphql
mutation {
  createOcena(naziv: "Izpit", ocena: 8.5, datum: "2026-01-10", studentId: "1") {
    id
    naziv
    ocena
    datum
    student { id ime priimek }
  }
}
```

### 4) Pridobi študenta z ocenami
```graphql
query {
  getStudent(id: "1") {
    id
    vpisnaStevilka
    ime
    priimek
    email
    smer
    ocene { id naziv ocena datum }
  }
}
```

## Struktura projekta (ključni deli)
```
.
├─ pom.xml
└─ src/
   ├─ main/java/si/um/studentgql/
   │  ├─ graphql/StudentGraphqlController.java
   │  ├─ entity/*Entity.java
   │  └─ repo/*Repository.java
   └─ main/resources/
      ├─ application.properties
      └─ graphql/schema.graphqls
```

## Opombe
- Privzeti port je **8080** (spremeniš ga lahko z `server.port` v `application.properties`).
- H2 baza je v pomnilniku; če želiš trajno shranjevanje, zamenjaj `spring.datasource.url` za “file” ali zunanjo bazo.
