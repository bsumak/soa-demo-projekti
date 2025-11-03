package si.feri.soa.davcnablagajna.services;

import java.util.UUID;

import jakarta.jws.WebService;
import si.feri.soa.davcnablagajna.data.PoslovniProstor;
import si.feri.soa.davcnablagajna.data.Racun;

@WebService(endpointInterface = "si.feri.soa.davcnablagajna.services.DavcnaBlagajna")
public class DavcnaBlagajnaService implements DavcnaBlagajna {

    @Override
    public Racun novRacun(Racun racun) {
       racun.setId(UUID.randomUUID().toString());
       return racun;
    }

    @Override
    public PoslovniProstor novPoslovniProstor(PoslovniProstor poslovniProstor) {
        poslovniProstor.setPpId( UUID.randomUUID().toString());
        return poslovniProstor;
    }

}
