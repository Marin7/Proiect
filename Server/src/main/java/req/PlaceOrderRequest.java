package req;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import dao.OrderDAO;
import main.Server;
import models.Order;
import models.Truck;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marin on 28-May-16.
 */
public class PlaceOrderRequest implements Request, Serializable {

    private String startPoint, endPoint;
    private Truck truck;

    public PlaceOrderRequest(Truck truck, String startPoint, String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.truck = truck;
    }

    @Override
    public void process() {
        if(!Server.getIdleTucks().containsKey(truck)) return;
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDRvCJhxrA6V3-j_mP-BG0LdykyylG_YVw");
        GeocodingResult[] result1 = new GeocodingResult[0];
        GeocodingResult[] result2 = new GeocodingResult[0];

        DirectionsResult results = new DirectionsResult();
        try {
            results = DirectionsApi.getDirections(context, startPoint, endPoint).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            result1 = GeocodingApi.geocode(context, startPoint).await();
            result2 = GeocodingApi.geocode(context, endPoint).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date startDate = new Date();
        Order order = new Order(truck, result1[0].formattedAddress, result2[0].formattedAddress, startDate, null);
        OrderDAO dao = OrderDAO.getInstance();
        dao.addOrder(order);

        Server.getOngoingOrders().add(order);
        long period = results.routes[0].legs[0].duration.inSeconds;
        System.out.println("Add new order: " + order + " duration: " + period);

        Server.removeTruckFromIdleTrucks(order.getTruck());

        Server.getScheduledExecutor().schedule(() -> {
            Server.getOngoingOrders().remove(order);
            dao.updateOrder(order, new Date());
            System.out.println("Order was executed: " + order);
        }, period, TimeUnit.SECONDS);

    }

}
