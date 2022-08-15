package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = getConnection();
             Statement stat = conn.createStatement();) {
            try {
                stat.executeUpdate("CREATE TABLE IF NOT EXISTS `db_pp1134hib`.`users` (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `lastName` VARCHAR(45) NOT NULL,\n" +
                        "  `age` INT(3) NOT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = getConnection();
             Statement stat = conn.createStatement();) {
            try {
                stat.executeUpdate("DROP TABLE IF EXISTS db_pp1134hib.users");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO `db_pp1134hib`.`users` (name, lastName, age) VALUES (?, ?, ?);";
        try (Connection conn = getConnection();
             PreparedStatement prepStat = conn.prepareStatement(sql);) {
            try {
                prepStat.setString(1, name);
                prepStat.setString(2, lastName);
                prepStat.setInt(3, age);
                prepStat.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM db_pp1134hib.users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement prepStat = conn.prepareStatement(sql);) {
            try {
                prepStat.setLong(1, id);
                prepStat.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stat = conn.createStatement();) {
            ResultSet res = stat.executeQuery("SELECT * FROM db_pp1134hib.users");
            while (res.next()) {
                User user = new User(res.getString(2),
                        res.getString(3), res.getByte(4));
                user.setId(res.getLong(1));
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try (Connection conn = getConnection();
             Statement stat = conn.createStatement();) {
            try {
                stat.executeUpdate("DELETE FROM db_pp1134hib.users");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
