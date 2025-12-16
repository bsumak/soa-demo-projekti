using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AspNetCoreRacuni.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.VisualBasic;

namespace AspNetCoreRacuni.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class RacuniController : ControllerBase
    {
        private static readonly ConcurrentDictionary<string, Racun> _data = new ConcurrentDictionary<string, Racun>();

        [HttpGet]
        public ActionResult<IEnumerable<Racun>> GetVsi()
        {
            return Ok(_data.Values.ToList()); // snapshot
        }

        [HttpGet("{id}")]
        public ActionResult<Racun> getById(string id)
        {
            if (string.IsNullOrWhiteSpace(id))
                return BadRequest("Manjka ali je prazen id.");

            return _data.TryGetValue(id, out var racun)
                ? Ok(racun)
                : NotFound($"Račun z id={id} ne obstaja.");
        }

        [HttpPost]
        public ActionResult<Racun> create([FromBody] Racun racun)
        {
            if (racun == null)
                return BadRequest("Manjka telo zahtevka.");

            if (!string.IsNullOrWhiteSpace(racun.Id))
                return BadRequest("Id ne sme biti podan pri ustvarjanju.");

            racun.Id = Guid.NewGuid().ToString();

            if (!_data.TryAdd(racun.Id, racun))
                return StatusCode(StatusCodes.Status500InternalServerError);

            return CreatedAtAction(nameof(getById), new { id = racun.Id }, racun);
        }

        [HttpPut("{id}")]
        public IActionResult update(string id, [FromBody] Racun racun)
        {
            if (string.IsNullOrWhiteSpace(id) || racun == null)
                return BadRequest("Neveljaven id ali manjkajoče telo.");

            if (!string.IsNullOrWhiteSpace(racun.Id) && id != racun.Id)
                return BadRequest("Id v poti in v telesu se ne ujemata.");

            if (!_data.TryGetValue(id, out var obstojeci))
                return NotFound($"Račun z id={id} ne obstaja.");

            racun.Id = id;

            return _data.TryUpdate(id, racun, obstojeci)
                ? NoContent()
                : Conflict("Posodobitev ni uspela zaradi sočasne spremembe. Poskusi znova.");
        }

        [HttpDelete("{id}")]
        public IActionResult delete(string id)
        {
            if (string.IsNullOrWhiteSpace(id))
                return BadRequest("Manjka ali je prazen id.");

            return _data.TryRemove(id, out _)
                ? NoContent()
                : NotFound($"Račun z id={id} ne obstaja.");
        }
    }
}
