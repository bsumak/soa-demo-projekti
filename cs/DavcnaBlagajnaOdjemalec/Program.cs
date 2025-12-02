using System;
using System.Threading.Tasks;
using DavcnaBlagajnaOdjemalec.Proxy;

namespace DavcnaBlagajnaOdjemalec
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            try
            {
                var client = new DavcnaBlagajnaClient();

                var racun = new Racun
                {
                    davcnaSt = "12345678",
                    datum    = "2025-12-01",
                    znesek   = 100.0,
                    ppId     = "POS01"
                };

                Console.WriteLine("Pošiljam asinhrono zahtevo za NovRacun...");

                NovRacunResponse response = await client.NovRacunAsync(racun);

                Racun rezultat = response.@return;

                Console.WriteLine("ASYNC .NET – NovRacun");
                Console.WriteLine($"ID:      {rezultat.id}");
                Console.WriteLine($"Davčna:  {rezultat.davcnaSt}");
                Console.WriteLine($"Datum:   {rezultat.datum}");
                Console.WriteLine($"Znesek:  {rezultat.znesek}");
                Console.WriteLine($"PPID:    {rezultat.ppId}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Napaka pri klicu storitve: {ex.Message}");
            }
        }
    }
}
