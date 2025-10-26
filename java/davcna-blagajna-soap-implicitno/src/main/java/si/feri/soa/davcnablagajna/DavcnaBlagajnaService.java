package si.feri.soa.davcnablagajna;
import java.util.UUID;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "DavcnaBlagajnaService", 
            targetNamespace = "http://si.feri.soa/davcnablagajna/service/1.0")
public class DavcnaBlagajnaService {

    @WebMethod(operationName = "NovRacun")
    public String novRacun(@WebParam(name = "davcnaId") String davcnaId, 
                @WebParam(name = "datum") String datum, 
                @WebParam(name = "znesek") double znesek, 
                @WebParam(name = "ppId") String ppId){
        return UUID.randomUUID().toString();
    }

    @WebMethod(operationName = "NovPoslovniProstor")
    public String novPoslovniProstor(@WebParam(name = "davcnaSt") String davcnaSt, 
                                     @WebParam(name = "naslov") String naslov){
        return UUID.randomUUID().toString();
    }
}
