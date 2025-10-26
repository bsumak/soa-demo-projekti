using System.Runtime.Serialization;

namespace CoreWcfDavcnaBlagajna.Models
{
    [DataContract(Namespace = "http://si.feri.soa/davcnablagajna/entity/poslovniprostor/1.0")]
    public class PoslovniProstor
    {
        [DataMember(Name = "PpId", Order = 1)]
        public string PpId { get; set; } = default!;

        [DataMember(Name = "DavcnaStevilka", Order = 2)]
        public string DavcnaStevilka { get; set; } = default!;

        [DataMember(Name = "Naslov", Order = 3)]
        public string Naslov { get; set; } = default!;
    }
}
