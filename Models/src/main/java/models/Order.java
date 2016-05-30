package models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marin on 20-May-16.
 */
public class Order implements Serializable {

    private Truck truck;
    private String startingPoint, endPoint;
    private Date startDate, endDate;

    public Order(Truck truck, String startingPoint, String endPoint, Date startDate, Date endDate) {
        this.truck = truck;
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "truck=" + truck +
                ", startingPoint='" + startingPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
