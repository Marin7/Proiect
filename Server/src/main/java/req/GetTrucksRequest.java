package req;

import dao.TruckDAO;
import models.Truck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marin on 24-May-16.
 */
public class GetTrucksRequest implements Request, Serializable {

    private List<Truck> trucks;

    public GetTrucksRequest() {
        trucks = new ArrayList<>();
    }

    @Override
    public void process() {
        TruckDAO dao = TruckDAO.getInstance();
        trucks = dao.getTrucks();
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

}
