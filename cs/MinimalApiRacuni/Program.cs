using MinimalApiRacuni.Models;

var builder = WebApplication.CreateBuilder(args);
var app = builder.Build();

IRacunRepository racuniRepository = new RacunRepository();

app.UseHttpsRedirection();

//implementacija metod GET, POST, PUT in DELETE

app.MapGet("/api/racuni", () =>
{
    return Results.Ok(racuniRepository.GetAll());
});

app.MapGet("/api/racuni/{id}", (string id) =>
{
    if (string.IsNullOrWhiteSpace(id))
        return Results.BadRequest("Manjka ali je prazen id.");

    Racun najdi = racuniRepository.Get(id);
    if (najdi == null)
    {
        return Results.NotFound("Računa z id " + id + " ni v bazi");
    }
    return Results.Ok(najdi);
});

app.MapPost("/api/racuni", (Racun racun, HttpContext context) =>
{
    if (racun is null)
        return Results.BadRequest("Manjka telo zahtevka.");

    if (!string.IsNullOrWhiteSpace(racun.Id))
        return Results.BadRequest("Id ne sme biti podan pri ustvarjanju.");

    var created = racuniRepository.Add(racun);

    var location = $"{context.Request.Scheme}://{context.Request.Host}/api/racuni/{created.Id}";
    return Results.Created(location, created);
});


app.MapPut("/api/racuni/{id}", (string id, Racun racun) =>
{
    if (string.IsNullOrWhiteSpace(id) || racun is null)
        return Results.BadRequest("Neveljaven id ali manjkajoče telo.");

    if (!string.IsNullOrWhiteSpace(racun.Id) && id != racun.Id)
        return Results.BadRequest("Id v poti in v telesu se ne ujemata.");

    var obstojeci = racuniRepository.Get(id);
    if (obstojeci is null)
        return Results.NotFound($"Račun z id={id} ne obstaja.");

    racun.Id = id;
    racuniRepository.Update(id, racun);

    return Results.NoContent();
});

app.MapDelete("/api/racuni/{id}", (string id) =>
{
    if (string.IsNullOrWhiteSpace(id))
        return Results.BadRequest("Manjka ali je prazen id.");

    var obstojeci = racuniRepository.Get(id);
    if (obstojeci is null)
        return Results.NotFound($"Račun z id={id} ne obstaja.");

    racuniRepository.Delete(id);
    return Results.NoContent();
});

app.Run();
