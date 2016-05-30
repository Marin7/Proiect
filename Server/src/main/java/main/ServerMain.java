package main;

import dao.TruckDAO;
import models.Truck;

import java.util.List;

/**
 * Created by Marin on 20-May-16.
 */
public class ServerMain {

    private static final int PORT = 5555;
    public static final String INIT_LOCAL = "Strada Observatorului, Nr. 100, Cluj-Napoca RO";

    public static void main(String[] args) {
        List<Truck> trucks = TruckDAO.getInstance().getTrucks();
        for(Truck truck : trucks) {
            Server.getIdleTucks().put(truck, INIT_LOCAL);
        }

        Server server = Server.getInstance();
        server.setUpConnection(PORT);
    }
}
