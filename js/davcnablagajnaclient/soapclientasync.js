import soap from 'soap';

const wsdl = 'http://localhost:8081/davcnablagajna?wsdl';
const client = await soap.createClientAsync(wsdl);
const args = {
  racun: {
    datum: '2025-12-02',
    davcnaSt: '7188181919',
    ppId: '54454545',
    znesek: 100
  }
};

console.log('Pošiljam SOAP zahtevo NovRacun (sinhroni flow z await)...');

const [result, rawResponse, soapHeader, rawRequest] =
  await client.NovRacunAsync(args);

console.log('Raw result object:');
console.log(JSON.stringify(result, null, 2));
