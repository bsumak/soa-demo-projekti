package si.feri.soa.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import trafficservice.TrafficFlowData;
import trafficservice.TrafficFlowResponse;
import trafficservice.TrafficSensorActivationResponse;
import trafficservice.TrafficSensorData;
import trafficservice.TrafficServiceGrpc;

public class TrafficSensorClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            TrafficServiceGrpc.TrafficServiceBlockingStub stub = TrafficServiceGrpc.newBlockingStub(channel);
            // 1) Aktivacija senzorja
            TrafficSensorData sensor = TrafficSensorData.newBuilder()
                    .setSensorName("Senzor-1")
                    .setSensorType("INDUKCIJSKA_ZANKA")
                    .setLattitude(46.5547)
                    .setLongitude(15.6459)
                    .build();

            TrafficSensorActivationResponse activation = stub.trafficSensorActivation(sensor);
            System.out.println("Activation response: " + activation);

            // 2) TrafficFlow
            TrafficFlowData flowData = TrafficFlowData.newBuilder()
                    .setSensorId(activation.getSensorId())
                    .setDatumOd("2026-01-10T00:00:00+01:00")
                    .setDatumDo("2026-01-10T23:59:59+01:00")
                    .setFlow(123)
                    .build();

            TrafficFlowResponse flowResp = stub.trafficFlow(flowData);
            System.out.println("TrafficFlow response: " + flowResp.getResponse());

        } finally {
            channel.shutdownNow();
        }
    }
}
