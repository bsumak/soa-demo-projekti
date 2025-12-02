import { pd } from 'pretty-data';

const url = "http://localhost:8081/davcnablagajna";

const soapEnvelope = `
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:ns="http://si.feri.soa/davcnablagajna/service/1.0">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:NovRacun>
         <racun>
            <datum>2.12.2025</datum>
            <davcnaSt>435534534</davcnaSt>
            <ppId>345534534</ppId>
            <znesek>100</znesek>
         </racun>
      </ns:NovRacun>
   </soapenv:Body>
</soapenv:Envelope>
`;

const response = await fetch(url, {
  method: "POST",
  headers: {
       "Content-Type": "text/xml;charset=utf-8"
      },
  body: soapEnvelope
});

const text = await response.text();
console.log("SOAP odgovor (XML): "  + pd.xml(text));
