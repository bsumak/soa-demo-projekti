package si.feri.soa.davcnablagajna;

import jakarta.xml.ws.Endpoint;

public class Server {

    public static void main(String[] args) {
        String address = "http://0.0.0.0:8080/davcnablagajna";
        Endpoint.publish(address, new DavcnaBlagajnaService());
        System.out.println("Endpoint: " + address);
        System.out.println("WSDL: " + address + "?wsdl");

        
        try {
            Thread.currentThread().join();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
