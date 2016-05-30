package req;

import dao.TruckDAO;
import main.Server;
import models.Truck;

import java.io.Serializable;

/**
 * Created by Marin on 24-May-16.
 */
public class RemoveTruckRequest implements Request, Serializable {

    private final Truck truck;

    public RemoveTruckRequest(Truck truck) {
        this.truck = truck;
    }

    @Override
    public void process() {
        TruckDAO dao = TruckDAO.getInstance();
        dao.removeTruck(truck);
        Server.getIdleTucks().remove(truck);
    }

}
