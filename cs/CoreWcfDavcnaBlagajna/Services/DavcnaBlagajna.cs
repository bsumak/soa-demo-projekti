using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CoreWcfDavcnaBlagajna.Models;

namespace CoreWcfDavcnaBlagajna.Services
{
    public class DavcnaBlagajna : IDavcnaBlagajna
    {
        public PoslovniProstor NovPoslovniProstor(PoslovniProstor poslovniProstor)
        {
            poslovniProstor.PpId = Guid.NewGuid().ToString();
            return poslovniProstor;
        }

        public Racun NovRacun(Racun racun)
        {
            racun.Id = Guid.NewGuid().ToString();
            return racun;
        }
    }
}
