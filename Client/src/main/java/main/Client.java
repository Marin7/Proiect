package main;

import models.Order;
import models.Truck;
import req.Request;
import req.RequestFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marin on 20-May-16.
 */
public class Client {

    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public Client(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object sendRequest(Request request) throws IOException {
        Object result = null;
        if (socket.isConnected()) {
            out.writeObject(request);
            try {
                result = in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void addTruck(Truck truck) {
        Request request = RequestFactory.getRequest("AddTruck", truck);
        try {
            sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTruck(Truck truck) {
        Request request = RequestFactory.getRequest("RemoveTruck", truck);
        try {
            sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Truck> getTrucks() {
        List<Truck> trucks = Collections.emptyList();
        Request request = RequestFactory.getRequest("GetTrucks", null);
        try {
            trucks = (List<Truck>)sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trucks;
    }

    public Map<Truck, String> getAvailableTrucks() {
        Map<Truck, String> trucks = new HashMap<>();
        Request request = RequestFactory.getRequest("GetAvailableTrucks", null);
        try {
            trucks = (Map<Truck, String>)sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trucks;
    }

    public void placeOrder(Truck truck, String startPoint, String endPoint) {
        Request req = RequestFactory.getRequest("PlaceOrder", truck, startPoint, endPoint);
        try {
            sendRequest(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrders() {
        List<Order> orders = Collections.emptyList();
        Request req = RequestFactory.getRequest("GetOrders");
        try {
            orders = (List<Order>)sendRequest(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void closeConnection() throws IOException {
        socket.close();
        out.close();
        in.close();
    }

    @Override
    public void finalize() {
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
