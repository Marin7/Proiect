package main;

import models.Order;
import models.Truck;

/**
 * Created by Marin on 20-May-16.
 */
public interface ServerInterface {

    String requestPosition(Truck truck);
    void dispatchTruck(Order order);
}
