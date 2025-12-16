using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MinimalApiRacuni.Models
{
    public interface IRacunRepository
    {
        Racun Get(string id);
        List<Racun> GetAll();
        Racun Add(Racun racun);
        void Update(string id, Racun racun);
        void Delete(string id);

    }
}
