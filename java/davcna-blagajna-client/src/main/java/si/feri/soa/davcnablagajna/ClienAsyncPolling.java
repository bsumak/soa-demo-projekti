package si.feri.soa.davcnablagajna;
import java.util.concurrent.TimeUnit;
import jakarta.xml.ws.Response;
import si.feri.soa.davcnablagajna.client.DavcnaBlagajna;
import si.feri.soa.davcnablagajna.client.DavcnaBlagajnaServiceService;
import si.feri.soa.davcnablagajna.client.NovRacunResponse;
import si.feri.soa.davcnablagajna.client.Racun;

public class ClienAsyncPolling {
    public static void main(String[] args) throws Exception {

        DavcnaBlagajnaServiceService service = new DavcnaBlagajnaServiceService();
        DavcnaBlagajna port = service.getDavcnaBlagajnaServicePort();

        Racun racun = new Racun();
        racun.setDavcnaSt("12345678");
        racun.setDatum("2025-12-01");
        racun.setZnesek(100.0);
        racun.setPpId("POS01");

        Response<NovRacunResponse> odg = port.novRacunAsync(racun);

        System.out.println("Čakam na rezultat...");
        while(!odg.isDone()){ //polling
            System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(100);
        }

        System.out.println();
        System.out.println("Račun je bil registriran, id = " + odg.get().getReturn().getId());
    }
}
