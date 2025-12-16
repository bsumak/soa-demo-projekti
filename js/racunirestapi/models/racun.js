const mongoose = require('mongoose');
const racunSchema = new mongoose.Schema({
  id: {
    type: String,
    required: true
  },
  datum: {
    type: String,
    required: true
  },
  kraj: {
    type: String,
    required: true
  },
  znesek: {
    type: Number,
    required: true
  },
  ppId: {
    type: String,
    required: true
  },
  davcnaSt: {
    type: String,
    required: true
  }
});
const Racun = mongoose.model('Racun', racunSchema);
module.exports = Racun;
