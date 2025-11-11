package si.feri.soa.trafficservice;

import jakarta.xml.ws.Endpoint;
import si.feri.soa.trafficservice.service.TrafficServiceImpl;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String address = "http://0.0.0.0:9090/trafficservice";
        Endpoint.publish(address, new TrafficServiceImpl());
        System.out.println("WSDL: " + address + "?wsdl");
        try {
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
