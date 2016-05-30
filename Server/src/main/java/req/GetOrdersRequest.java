package req;

import main.Server;
import models.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marin on 29-May-16.
 */
public class GetOrdersRequest implements Request, Serializable{

    private List<Order> orders;

    @Override
    public void process() {
        orders = Server.getOngoingOrders();
    }

    public List<Order> getOrders() {
        return this.orders;
    }
}
