package dao;

import models.Truck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marin on 20-May-16.
 */
public class TruckDAO {

    private static final TruckDAO DAO = new TruckDAO();

    private TruckDAO(){

    }

    public static TruckDAO getInstance() {
        return DAO;
    }

    public void addTruck(Truck truck) {
        Connection con = UtilsDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO truck(number,model,capacity,max_qty) VALUES" +
                    "('" + truck.getNumber() + "','" + truck.getModel() + "'," + truck.getCapacity() + "," + truck.getMaxQuantity() + ")";
            stmt.execute(sql);
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void removeTruck(Truck truck) {
        int id = getTruckId(truck);
        Connection con = UtilsDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM truck WHERE id=" + id;
            stmt.execute(sql);
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Truck> getTrucks() {
        List<Truck> trucks = new ArrayList<>();
        Connection con = UtilsDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM truck";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Truck truck = new Truck(rs.getString("number"), rs.getString("model"),
                        rs.getDouble("capacity"), rs.getDouble("max_qty"));
                trucks.add(truck);
            }
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return trucks;
    }

    public int getTruckId(Truck truck) {
        int id = 0;
        Connection con = UtilsDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT id FROM truck WHERE number='" + truck.getNumber() + "' AND " +
                    "model='" + truck.getModel() + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                id = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return id;
    }

}
