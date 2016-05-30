package main;

import models.Order;
import req.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marin on 22-May-16.
 */
public class Broker extends Thread {

    private static Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Broker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private void processInput(Request request) {
        request.process();
        try {
            if(request instanceof AddTruckRequest) {
                out.writeObject("Added new truck");
            }
            if(request instanceof RemoveTruckRequest) {
                out.writeObject("Truck Removed");
            }
            if(request instanceof GetTrucksRequest) {
                GetTrucksRequest getTrucksRequest = (GetTrucksRequest)request;
                out.writeObject(getTrucksRequest.getTrucks());
            }
            if(request instanceof GetAvailableTrucksRequest) {
                GetAvailableTrucksRequest getTrucksRequest = (GetAvailableTrucksRequest)request;
                out.writeObject(new HashMap<>(getTrucksRequest.getTruckPosition()));
            }
            if(request instanceof PlaceOrderRequest) {
                out.writeObject("Order placed");
            }
            if(request instanceof GetOrdersRequest) {
                GetOrdersRequest getOrdersRequest = (GetOrdersRequest)request;
                ArrayList<Order> orders = new ArrayList<>(getOrdersRequest.getOrders());
                out.writeObject(orders);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Request request;
        try {
            while ((request = (Request) in.readObject()) != null) {
                processInput(request);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
