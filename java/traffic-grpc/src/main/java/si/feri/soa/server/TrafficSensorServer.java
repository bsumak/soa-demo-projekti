package si.feri.soa.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import si.feri.soa.service.TrafficSensorServiceImpl;

public class TrafficSensorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;

    Server server = ServerBuilder.forPort(port)
        .addService(new TrafficSensorServiceImpl())
        .build()
        .start();

    System.out.println("gRPC strežnik teče na portu " + port);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("Zaustavljam gRPC strežnik...");
      server.shutdown();
    }));

    server.awaitTermination();
    }
}
