package si.feri.soa.trafficservice.service;

import java.util.UUID;

import jakarta.jws.WebService;
import si.feri.soa.trafficservice.data.TrafficFlowRequestType;
import si.feri.soa.trafficservice.data.TrafficFlowResponseType;
import si.feri.soa.trafficservice.data.TrafficSensorActivationRequestType;
import si.feri.soa.trafficservice.data.TrafficSensorActivationResponseType;

@WebService(endpointInterface = "si.feri.soa.trafficservice.service.TrafficService")
public class TrafficServiceImpl implements TrafficService{

    @Override
    public TrafficSensorActivationResponseType trafficSensorActivation(
            TrafficSensorActivationRequestType trafficSensorActivationRequest) {
       TrafficSensorActivationResponseType odg = new TrafficSensorActivationResponseType();
            odg.setActivationDate("11.11.2025");
            odg.setActivatonStatus("activated");
            odg.setSensorId(UUID.randomUUID().toString());

       return odg;
    }

    @Override
    public TrafficFlowResponseType trafficFlow(TrafficFlowRequestType trafficFlowRequest) {
        TrafficFlowResponseType odg = new TrafficFlowResponseType();
        odg.setResponse("OK");
        return odg;
    }

}
