package si.feri.soa.davcnablagajna.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import si.feri.soa.davcnablagajna.data.PoslovniProstor;
import si.feri.soa.davcnablagajna.data.Racun;

@WebService(targetNamespace = "http://si.feri.soa/davcnablagajna/service/1.0",
name = "DavcnaBlagajna", serviceName = "DavcnaBlagajnaService")
public interface DavcnaBlagajna {

    @WebMethod(operationName = "NovRacun")
    public Racun novRacun(@WebParam(name = "racun") Racun racun);


    public PoslovniProstor novPoslovniProstor(@WebParam(name="poslovniProstor") PoslovniProstor poslovniProstor);

}
