using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Threading.Tasks;

namespace CoreWcfDavcnaBlagajna.Models
{
    [DataContract(Namespace = "http://si.feri.soa/davcnablagajna/data/racun/1.0")]
    public class Racun
    {
        [DataMember(Name = "Id",Order = 1)]
        public string Id { get; set; }
        [DataMember(Name = "DavcnaSt", Order = 2)]
        public string DavcnaSt { get; set; }

        [DataMember(Name ="Datum", Order = 3)]
        public string Datum { get; set; }

        [DataMember(Name = "Znesek", Order = 4)]
        public double Znesek { get; set; }

        [DataMember(Name = "PpId", Order = 5)]
        public string PpId { get; set; }
    }
}
