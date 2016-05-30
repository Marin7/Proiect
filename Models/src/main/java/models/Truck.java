package models;

import java.io.Serializable;

/**
 * Created by Marin on 20-May-16.
 */
public class Truck implements Serializable {

    private String number, model;
    private double capacity, maxQuantity;

    public Truck(String number, String model, double capacity, double maxQuantity) {
        this.number = number;
        this.model = model;
        this.capacity = capacity;
        this.maxQuantity = maxQuantity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Model: ").append(model).append(' ')
                .append("Number: ").append(number).append(' ')
                .append("Capacity: ").append(capacity).append(' ')
                .append("Max Quantity: ").append(maxQuantity);
        return builder.toString();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (Double.compare(truck.capacity, capacity) != 0) return false;
        if (Double.compare(truck.maxQuantity, maxQuantity) != 0) return false;
        if (number != null ? !number.equals(truck.number) : truck.number != null) return false;
        return model != null ? model.equals(truck.model) : truck.model == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = number != null ? number.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        temp = Double.doubleToLongBits(capacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxQuantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
