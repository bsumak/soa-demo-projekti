import soap from 'soap';
const wsdl = "http://localhost:8081/davcnablagajna?wsdl";
const args = {
  racun: {
    datum: '2.12.2025',
    davcnaSt: '7188181919',
    ppId: '54454545',
    znesek: 100
  }
};

soap.createClient(wsdl, function (err, client) {
  client.NovRacun(args, function (err, odg) {
    console.log("Račun je bil registriran. Id= " + odg.return.id);
  });
});
