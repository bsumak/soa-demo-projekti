using CoreWCF;
using CoreWcfDavcnaBlagajna.Models;


namespace CoreWcfDavcnaBlagajna.Services
{

    [ServiceContract(Name ="DavcnaBlagajnaService", Namespace = "http://si.feri.soa/davcnablagajna/service/1.0")]
    public interface IDavcnaBlagajna
    {
        [OperationContract(Name = "NovRacun")]
        Racun NovRacun(Racun racun);

        [OperationContract(Name = "PoslovniProstor")]
        PoslovniProstor PoslovniProstor(PoslovniProstor poslovniProstor);

    }
}
