using System.Runtime.Serialization;

namespace CoreWcfDavcnaBlagajna.Models
{
    [DataContract(Namespace = "http://si.feri.soa/davcnablagajna/entity/racun/1.0")]
    public class Racun
    {
        [DataMember(Name = "Id", Order = 1)]
        public string Id { get; set; } = default!;

        [DataMember(Name = "DavcnaStevilka", Order = 2)]
        public string DavcnaStevilka { get; set; } = default!;

        [DataMember(Name = "Datum", Order = 3)]
        public string Datum { get; set; } = default!;

        [DataMember(Name = "Znesek", Order = 4)]
        public double Znesek { get; set; } = default!;

        [DataMember(Name = "PpId", Order = 5)]
        public string PpId { get; set; } = default!;
    }
}
