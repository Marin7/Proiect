package req;

import models.Truck;

/**
 * Created by Marin on 21-May-16.
 */
public class RequestFactory {

    public static Request getRequest(String type, Object... obj) {
        switch (type) {
            case "AddTruck" : {
                Truck truck = (Truck)obj[0];
                return new AddTruckRequest(truck);
            }
            case "GetTrucks" : {
                return new GetTrucksRequest();
            }
            case "RemoveTruck" : {
                Truck truck = (Truck)obj[0];
                return new RemoveTruckRequest(truck);
            }
            case "PlaceOrder" : {
                Truck truck = (Truck)obj[0];
                String startPoint = (String)obj[1];
                String endPoint = (String)obj[2];
                return new PlaceOrderRequest(truck, startPoint, endPoint);
            }
            case "GetAvailableTrucks" : {
                return new GetAvailableTrucksRequest();
            }
            case "GetOrders" : {
                return new GetOrdersRequest();
            }
            default : return null;
        }
    }

}
