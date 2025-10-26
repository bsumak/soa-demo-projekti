package si.feri.soa.davcnablagajna;

import java.util.UUID;

import jakarta.jws.WebService;
import si.feri.soa.davcnablagajna.entity.PoslovniProstor;
import si.feri.soa.davcnablagajna.entity.Racun;

@WebService(endpointInterface = "si.feri.soa.davcnablagajna.IDavcnaBlagajnaService")
public class DavcnaBlagajnaService implements IDavcnaBlagajnaService {

    @Override
    public Racun novRacun(Racun racun) {
        racun.setId(UUID.randomUUID().toString());
        return racun;
    }

    @Override
    public PoslovniProstor novPoslovniProstor(PoslovniProstor poslovniProstor) {
        poslovniProstor.setPpId(UUID.randomUUID().toString());
        return poslovniProstor;
    }


}
