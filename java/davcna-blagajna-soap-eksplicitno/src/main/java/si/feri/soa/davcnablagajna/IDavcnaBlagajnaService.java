package si.feri.soa.davcnablagajna;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import si.feri.soa.davcnablagajna.entity.PoslovniProstor;
import si.feri.soa.davcnablagajna.entity.Racun;

@WebService(serviceName = "DavcnaBlagajnaService",
targetNamespace = "http://si.feri.soa/davcnablagajna/service/1.0")
public interface IDavcnaBlagajnaService {

    @WebMethod(operationName = "NovRacun")
    public Racun novRacun(@WebParam(name = "racun") Racun racun);

    @WebMethod(operationName = "NovPoslovniProstor")
    public PoslovniProstor novPoslovniProstor(@WebParam(name = "poslovniProstor") PoslovniProstor poslovniProstor);

}
