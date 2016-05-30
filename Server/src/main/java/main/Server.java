package main;

import models.Order;
import models.Truck;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Marin on 20-May-16.
 */
public class Server extends Thread implements ServerInterface  {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private ExecutorService executor;
    private static List<Order> ongoingOrders = new ArrayList<>();
    private static Map<Truck, String> idleTucks = new HashMap<>();
    private static final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private static final Server SERVER = new Server();

    public Server() {
        executor = Executors.newCachedThreadPool();
    }

    public static Server getInstance() {
        return SERVER;
    }

    public void setUpConnection(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void finalize() {
        try {
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestPosition(Truck truck) {
        return "";
    }

    public void dispatchTruck(Order order) {
    }

    @Override
    public void run() {
        while(true) {
            try {
                clientSocket = serverSocket.accept();
                executor.submit(new Broker(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Order> getOngoingOrders() {
        return ongoingOrders;
    }

    public static Map<Truck, String> getIdleTucks() {
        return idleTucks;
    }

    public static ScheduledExecutorService getScheduledExecutor() {
        return scheduledExecutor;
    }

    public static void removeTruckFromIdleTrucks(Truck truck) {
        idleTucks.remove(truck);
    }
}
