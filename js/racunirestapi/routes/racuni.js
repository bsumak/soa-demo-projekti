const Racun = require('../models/racun');
const express = require('express');
const router = express.Router();

router.get('/', async (req, res) => {
  try {
    const racuni = await Racun.find({});
    return res.status(200).json(racuni);
  } catch (error) {
    return res.status(500).json({ msg: 'Napaka na strežniku' });
  }
});

router.get('/:id', async (req, res) => {
  const { id } = req.params;
  if (!id || !id.trim()) {
    return res.status(400).json({ msg: "Manjka ali je prazen parameter 'id'." });
  }
  try {
    const racun = await Racun.findById(id);
    if (!racun) {
      return res.status(404).json({ msg: `Račun z id=${id} ne obstaja` });
    }
    return res.status(200).json(racun);
  } catch (error) {
    return res.status(500).json({ msg: 'Napaka na strežniku' });
  }
});

router.post('/', async (req, res) => {
  if (!req.body || !req.body.davcnaSt) {
    return res.status(400).json({ msg: "Manjka obvezno polje 'davcnaSt'." });
  }
  try {
    const novRacun = new Racun(req.body);
    await novRacun.save();
    const location = `${req.protocol}://${req.get('host')}/api/racuni/${novRacun._id}`;
    return res.status(201)
      .location(location)
      .json(novRacun);

  } catch (error) {
    return res.status(500).json({ msg: 'Napaka na strežniku' });
  }
});

// PUT /api/racuni/:id
router.put('/:id', async (req, res) => {
  const { id } = req.params;
  if (!id || !id.trim()) {
    return res.status(400).json({ msg: "Manjka ali je prazen parameter 'id'." });
  }
  if (!req.body) {
    return res.status(400).json({ msg: "Manjka telo zahtevka." });
  }
  if (req.body._id && String(req.body._id) !== id) {
    return res.status(400).json({ msg: "ID v poti in v telesu se ne ujemata." });
  }
  try {
    const updated = await Racun.findByIdAndUpdate(id, req.body);
    if (!updated) {
      return res.status(404).json({ msg: `Račun z id=${id} ne obstaja` });
    }
    return res.status(200).json(updated);
  } catch (error) {
    return res.status(500).json({ msg: 'Napaka na strežniku' });
  }
});

// DELETE /api/racuni/:id
router.delete('/:id', async (req, res) => {
  const { id } = req.params;

  if (!id || !id.trim()) {
    return res.status(400).json({ msg: "Manjka ali je prazen parameter 'id'." });
  }
  try {
    const deleted = await Racun.findByIdAndDelete(id);
    if (!deleted) {
      return res.status(404).json({ msg: `Račun z id=${id} ne obstaja` });
    }
    return res.sendStatus(204);
  } catch (error) {
    return res.status(500).json({ msg: 'Napaka na strežniku' });
  }
});


module.exports = router;
