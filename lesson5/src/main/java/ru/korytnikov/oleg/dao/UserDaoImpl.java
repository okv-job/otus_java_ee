package ru.korytnikov.oleg.dao;

import ru.korytnikov.oleg.models.Marker;
import ru.korytnikov.oleg.models.User;
import ru.korytnikov.oleg.models.Users;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/lesson2");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                    "(first_name,second_name,address) VALUES (?,?,?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getAddress());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<User>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getString("first_name"), rs.getString("second_name"), rs.getString("address"), rs.getInt("id"));
                result.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void markPath(Marker marker) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO markers VALUES (NULL , ?, ?, ?, ?, ?)");
            statement.setString(1, marker.getMarkerName());
            statement.setString(2, marker.getPageName());
            statement.setString(3, marker.getBrowserName());
            statement.setString(4, marker.getIpAddress());
            statement.setString(5, marker.getTime());
            statement.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void init(Users users) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT , first_name VARCHAR (40) NOT NULL ," +
                    "second_name VARCHAR (40) NOT NULL , address VARCHAR (100))");
            statement.execute("CREATE TABLE IF NOT EXISTS auth (id INT NOT NULL, login VARCHAR (40) NOT NULL ," +
                    "password VARCHAR (40) NOT NULL, FOREIGN KEY (id) REFERENCES users(id))");
            statement.execute("CREATE TABLE IF NOT EXISTS  markers (\n" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    marker_name VARCHAR(50) not null,\n" +
                    "    page_name VARCHAR(50),\n" +
                    "    browser_name VARCHAR(100),\n" +
                    "    ip_addr VARCHAR(50),\n" +
                    "    time VARCHAR(100)) ENGINE=INNODB;");
            users.getUsers().forEach(user -> {
                addUser(user);
            });
            statement.execute("INSERT INTO auth VALUES (1,'admin','admin')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE auth");
            statement.execute("DROP TABLE users");
            statement.execute("DROP TABLE markers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                    "SET first_name=?,second_name=?,address=? WHERE id = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getAddress());
            statement.setInt(4, user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(String username, String password) {
        User user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM auth WHERE login=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = getUserById(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getUserById(int id) {
        User user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("address"),
                        rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
