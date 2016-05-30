package dao;

import models.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Marin on 20-May-16.
 */
public class OrderDAO {

    private static final OrderDAO DAO = new OrderDAO();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private OrderDAO() {
    }

    public static OrderDAO getInstance() {
        return DAO;
    }

    public void addOrder(Order order) {
        TruckDAO dao = TruckDAO.getInstance();
        int id = dao.getTruckId(order.getTruck());
        Connection con = UtilsDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO orders(start_date,start_point,end_point,truck_id) VALUES ('" +
                    format.format(order.getStartDate()) + "','" + order.getStartingPoint() + "','" + order.getEndPoint() + "'," + id + ")";
            System.out.println(sql);
            stmt.execute(sql);
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void updateOrder(Order order, Date endDate) {
        TruckDAO dao = TruckDAO.getInstance();
        int id = dao.getTruckId(order.getTruck());
        Connection con = UtilsDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE orders SET end_date='" + format.format(endDate) + "' WHERE start_date='" +
                    format.format(order.getStartDate()) + "' AND truck_id=" + id;
            System.out.println(sql);
            stmt.execute(sql);
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Order> getOrders() {
        return null;
    }
}
