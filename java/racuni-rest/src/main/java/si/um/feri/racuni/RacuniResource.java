package si.um.feri.racuni;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("/api/racuni")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RacuniResource {

    private static final Map<String, Racun> DATA = new ConcurrentHashMap<>();

    @GET
    public Response getAll() {
        try {
            List<Racun> racuni = new ArrayList<>(DATA.values());
            if (racuni.isEmpty())
                return Response.noContent().build();
            return Response.ok(racuni).build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity("Napaka pri branju računov.")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getRacun(@PathParam("id") String id) {
        if (id == null || id.isBlank())
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Manjka ali je prazen 'id'.").build();
        try {
            Racun r = DATA.get(id);
            if (r == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Račun z id=" + id + " ni bil najden.")
                        .build();
            }
            return Response.ok(r).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    public Response createRacun(Racun racun, @Context UriInfo uriInfo) {
        if (racun == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Manjka telo zahtevka (Racun).")
                    .build();
        }
        try {
            racun.setId(UUID.randomUUID().toString());
            DATA.put(racun.getId(), racun);
            URI createdAt = uriInfo.getAbsolutePathBuilder()
                    .path(racun.getId())
                    .build();
            return Response.created(createdAt).entity(racun).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateRacun(@PathParam("id") String id, Racun racun) {

        if (id == null || id.isBlank() || racun == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("Neveljaven id ali manjkajoče telo.").build();

        if (racun.getId() == null || !id.equals(racun.getId()))
            return Response.status(Response.Status.BAD_REQUEST).entity("ID v poti in v telesu se ne ujemata.").build();

        if (!DATA.containsKey(id))
            return Response.status(Response.Status.NOT_FOUND).entity("Račun z id=" + id + " ne obstaja.").build();

        DATA.put(id, racun);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRacun(@PathParam("id") String id) {

        if (id == null || id.isBlank())
            return Response.status(Response.Status.BAD_REQUEST).entity("Manjka ali je prazen 'id'.").build();

        if (DATA.remove(id) == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Račun z id=" + id + " ne obstaja.").build();

        return Response.noContent().build();
    }

}
