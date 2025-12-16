using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MinimalApiRacuni.Models
{
    public class Racun
    {
        public string? Id { get; set; }
        public string? Datum { get; set; }
        public string? Kraj { get; set; }
        public double Znesek { get; set; }
        public string? PpId { get; set; }
        public string? DavcnaSt { get; set; }

    }
}
