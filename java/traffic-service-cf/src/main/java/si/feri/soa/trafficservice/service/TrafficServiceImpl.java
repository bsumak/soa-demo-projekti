package si.feri.soa.trafficservice.service;

import java.util.UUID;

import jakarta.jws.WebService;
import si.feri.soa.trafficservice.data.TrafficFlowRequestType;
import si.feri.soa.trafficservice.data.TrafficFlowResponseType;
import si.feri.soa.trafficservice.data.TrafficSensorActivationRequestType;
import si.feri.soa.trafficservice.data.TrafficSensorActivationResponseType;

@WebService(endpointInterface = "si.feri.soa.trafficservice.service.TrafficService")
public class TrafficServiceImpl implements TrafficService {

    @Override
    public TrafficSensorActivationResponseType trafficSensorActivation(TrafficSensorActivationRequestType parameters) {
         TrafficSensorActivationResponseType odg = new TrafficSensorActivationResponseType();
        odg.setSensorId(UUID.randomUUID().toString());
        odg.setActivationDate("11.11.2025");
        odg.setActivationStatus("activated");
        return odg;
    }

    @Override
    public TrafficFlowResponseType trafficFlow(TrafficFlowRequestType parameters) {
        TrafficFlowResponseType odg = new TrafficFlowResponseType();
        odg.setResponse("OK - " + parameters.getSensorId());
        return odg;
    }

}
