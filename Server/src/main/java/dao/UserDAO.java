package dao;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Marin on 21-May-16.
 */
public class UserDAO {

    private static final UserDAO DAO = new UserDAO();

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return DAO;
    }

    public boolean login(String username, String password) {
        Connection con = UtilsDAO.getConnection();
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM user WHERE name='" + username + "' AND password='" + password + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void addUser(User User) {
    }

    public void removeUser(User User) {
    }

    public List<User> getUsers() {
        return null;
    }
}
