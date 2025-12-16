const express = require('express');
const mongoose = require('mongoose');
const { MongoMemoryServer } = require("mongodb-memory-server");
const racuniRoutes = require('./routes/racuni');
const cors = require('cors');

const PORT = process.env.PORT || 3000;

async function connectInMemoryMongo() {
  const mongod = await MongoMemoryServer.create();
  const uri = mongod.getUri();
  await mongoose.connect(uri);
  console.log("In-memory MongoDB connected:", uri);
  return mongod;
}

connectInMemoryMongo().catch(console.error);

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use('/api/racuni', racuniRoutes);
app.use(cors());
app.listen(PORT, () => {
   console.log(`API za račune je na posluša na http://localhost:${PORT}/api/racuni`);
});
