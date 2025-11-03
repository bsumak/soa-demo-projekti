using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Threading.Tasks;

namespace CoreWcfDavcnaBlagajna.Models
{
    [DataContract(Namespace = "http://si.feri.soa/davcnablagajna/data/poslovniprostor/1.0")]
    public class PoslovniProstor
    {
        [DataMember(Name = "PpId", Order = 1)]

        public string PpId { get; set; }

        [DataMember(Name = "DavcnaSt", Order = 2)]
         public string DavcnaSt { get; set; }

        [DataMember(Name = "Naslov", Order = 3)]
         public string Naslov { get; set; }
    }
}
