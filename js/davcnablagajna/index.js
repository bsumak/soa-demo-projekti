const express = require('express');
const soap = require('soap');
const fs = require('fs');
const uuid = require('uuid');
const wsdl = "DavcnaBlagajna.wsdl";

const NovRacunImpl = function(racun){
  console.log('Registracija novega računa');
  racun.id = uuid.v4();
  return racun;
}

const PoslovniProstorImpl = function(poslovniProstor){
  console.log('Registracija poslovnega prostora');
  poslovniProstor.ppId = uuid.v4();
  return poslovniProstor;
}

const serviceObject = {
  DavcnaBlagajnaService: {
    DavcnaBlagajnaPort:{
      NovRacun: NovRacunImpl,
      PoslovniProstor: PoslovniProstorImpl
    }
  }
}

let wsdlFile = fs.readFileSync(wsdl, "utf-8");
let app = express();
const PORT = 9090;

app.listen(PORT, ()=>{
    let service_path = "/davcnablagajna";
    soap.listen(app, service_path, serviceObject, wsdlFile);
    console.log("Storitev je zagnana. Poglej WSDL na http://localhost:9090/davcnablagajna?wsdl");
});
