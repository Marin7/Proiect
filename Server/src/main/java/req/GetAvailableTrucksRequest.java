package req;

import main.Server;
import models.Truck;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Marin on 28-May-16.
 */
public class GetAvailableTrucksRequest implements Request, Serializable {

    @Override
    public void process() {
    }

    public Map<Truck, String> getTruckPosition() {
        return Server.getIdleTucks();
    }

}
