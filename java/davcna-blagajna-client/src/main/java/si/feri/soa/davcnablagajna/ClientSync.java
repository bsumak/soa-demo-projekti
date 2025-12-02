package si.feri.soa.davcnablagajna;

import si.feri.soa.davcnablagajna.client.DavcnaBlagajna;
import si.feri.soa.davcnablagajna.client.DavcnaBlagajnaServiceService;
import si.feri.soa.davcnablagajna.client.Racun;

public class ClientSync {
    public static void main(String[] args) {
        // 1. Service (proxy do SOAP storitve)
        DavcnaBlagajnaServiceService service = new DavcnaBlagajnaServiceService();
        // 2. Port (vmesnik z operacijami)
        DavcnaBlagajna port = service.getDavcnaBlagajnaServicePort();

        // 3. Pripravimo primer računa
        Racun racun = new Racun();
        racun.setDavcnaSt("12345678");
        racun.setDatum("2025-12-01");
        racun.setZnesek(100.0);
        racun.setPpId("POS01");

        System.out.println("Prožim operacijo novRacun");
        // 4. Sinhron klic – blokira do odgovora
        Racun rezultat = port.novRacun(racun);
        System.out.println("Ustvarjen račun ID: " + rezultat.getId());
    }
}
