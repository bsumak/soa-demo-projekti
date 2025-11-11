package si.feri.soa.trafficservice.config;

import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;
import si.feri.soa.trafficservice.service.TrafficServiceImpl;

@Configuration
public class CxfConfig {

  // Objavi JAX-WS endpoint
  @Bean
  public Endpoint trafficServiceEndpoint(Bus bus, TrafficServiceImpl implementor) {
    EndpointImpl endpoint = new EndpointImpl(bus, implementor);
    endpoint.getFeatures().add(new LoggingFeature());
    endpoint.publish("/trafficservice");
    return endpoint;
  }

  // Implementacija kot Spring bean
  @Bean
  public TrafficServiceImpl trafficServiceImpl() {
    return new TrafficServiceImpl();
  }
}
