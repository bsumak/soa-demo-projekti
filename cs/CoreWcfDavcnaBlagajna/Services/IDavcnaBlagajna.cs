using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CoreWCF;
using CoreWcfDavcnaBlagajna.Models;

namespace CoreWcfDavcnaBlagajna.Services
{
    [ServiceContract(Name ="DavcnaBlagajnaService",
        Namespace = "http://si.feri.soa/davcnablagajna/service/1.0")]
    public interface IDavcnaBlagajna
    {
        [OperationContract(Name = "NovRacun")]
        Racun NovRacun(Racun racun);

        [OperationContract(Name = "NovPoslovniProstor")]
        PoslovniProstor NovPoslovniProstor(PoslovniProstor poslovniProstor);
    }
}
