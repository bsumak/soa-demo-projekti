using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MinimalApiRacuni.Models
{
    public class RacunRepository : IRacunRepository
    {
        private readonly ConcurrentDictionary<string, Racun> racuni = new ConcurrentDictionary<string, Racun>();
        public RacunRepository()
        {
            if (this.racuni.Count == 0)
            {
                Add(new Racun(){ DavcnaSt="22929222", Datum="16.12.2025", Kraj="Maribor", PpId="2199292910", Znesek=100});
                Add(new Racun(){ DavcnaSt="54454545", Datum="16.12.2025", Kraj="Celje", PpId="657567655", Znesek=200});
            }
        }
        public Racun Get(string id)
        {
            racuni.TryGetValue(id, out var racun);
            return racun;
        }
        public List<Racun> GetAll()
        {
            return racuni.Values.ToList();
        }
        public Racun Add(Racun racun)
        {
            racun.Id = Guid.NewGuid().ToString();
            if (!racuni.TryAdd(racun.Id, racun))
            {
                throw new InvalidOperationException("Dodajanje računa ni uspelo.");
            }
            return racun;
        }
        public void Update(string id, Racun racun)
        {
            if (!racuni.ContainsKey(id))
            {
                throw new KeyNotFoundException($"Računa z ID {id} nisem našel.");
            }
            racun.Id = id;
            racuni[id] = racun;
        }
        public void Delete(string id)
        {
            if (!racuni.TryRemove(id, out _))
            {
                throw new KeyNotFoundException($"Računa z ID {id} nisem našel.");
            }
        }
    }
}
