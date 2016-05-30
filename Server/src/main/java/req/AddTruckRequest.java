package req;

import dao.TruckDAO;
import main.Server;
import main.ServerMain;
import models.Truck;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Marin on 21-May-16.
 */
public class AddTruckRequest implements Request, Serializable {

    private final Truck truck;

    public AddTruckRequest(Truck truck) {
        this.truck = truck;
    }

    @Override
    public void process() {
        TruckDAO dao = TruckDAO.getInstance();
        dao.addTruck(truck);
        Server.getIdleTucks().put(truck, ServerMain.INIT_LOCAL);
    }
}
