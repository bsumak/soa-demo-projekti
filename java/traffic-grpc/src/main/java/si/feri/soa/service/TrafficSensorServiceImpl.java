package si.feri.soa.service;

import java.time.OffsetDateTime;
import java.util.UUID;
import io.grpc.stub.StreamObserver;
import trafficservice.TrafficFlowData;
import trafficservice.TrafficFlowResponse;
import trafficservice.TrafficSensorActivationResponse;
import trafficservice.TrafficSensorData;
import trafficservice.TrafficServiceGrpc.TrafficServiceImplBase;

public class TrafficSensorServiceImpl extends TrafficServiceImplBase {

    @Override
    public void trafficSensorActivation(TrafficSensorData request,
            StreamObserver<TrafficSensorActivationResponse> responseObserver) {
        String sensorId = UUID.randomUUID().toString();

        TrafficSensorActivationResponse response = TrafficSensorActivationResponse.newBuilder()
                .setSensorId(sensorId)
                .setActivationStatus("OK")
                .setActivationDate(OffsetDateTime.now().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

   @Override
   public void trafficFlow(TrafficFlowData request, StreamObserver<TrafficFlowResponse> responseObserver) {

        String odgovor = "Prejel informacije o prometu" + request.getFlow()
        + " za senzor=" + request.getSensorId()
        + " [" + request.getDatumOd() + " - " + request.getDatumDo() + "]";

        TrafficFlowResponse response = TrafficFlowResponse.newBuilder()
        .setResponse(odgovor)
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

   }

}
