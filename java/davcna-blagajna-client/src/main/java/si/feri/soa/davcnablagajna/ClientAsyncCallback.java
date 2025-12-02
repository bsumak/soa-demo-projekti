package si.feri.soa.davcnablagajna;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Response;
import si.feri.soa.davcnablagajna.client.DavcnaBlagajna;
import si.feri.soa.davcnablagajna.client.DavcnaBlagajnaServiceService;
import si.feri.soa.davcnablagajna.client.NovRacunResponse;
import si.feri.soa.davcnablagajna.client.Racun;

public class ClientAsyncCallback {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        DavcnaBlagajnaServiceService service = new DavcnaBlagajnaServiceService();
        DavcnaBlagajna port = service.getDavcnaBlagajnaServicePort();

        Racun racun = new Racun();
        racun.setDavcnaSt("12345678");
        racun.setDatum("2025-12-01");
        racun.setZnesek(100.0);
        racun.setPpId("POS01");

        // handler se izvede, ko rezultat prispe
        AsyncHandler<NovRacunResponse> handler = new AsyncHandler<>() {
            @Override
            public void handleResponse(Response<NovRacunResponse> response) {
                try {
                    NovRacunResponse odgovor = response.get();
                    Racun rez = odgovor.getReturn();
                    System.out.println("Račun registriran, id= " + rez.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // asinhroni klic s callback
        Future<?> future = port.novRacunAsync(racun, handler);
        System.out.println("Prožil metodo novRacunAsync. Odjemalec lahko medtem dela kaj drugega...");

        // tukaj blokiramo, dokler callback ne konča
        future.get();  // počaka, dokler asinhroni klic ni zaključen

    }

}
