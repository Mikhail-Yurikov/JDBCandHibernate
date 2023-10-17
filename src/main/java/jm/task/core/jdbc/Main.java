package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

          UserServiceImpl userService = new UserServiceImpl();
          userService.createUsersTable();
          userService.saveUser("Mark", "Tven", (byte) 44);
          userService.saveUser("Alexandr", "Block", (byte) 22);
          userService.saveUser("Jeck", "London", (byte) 33);
          userService.cleanUsersTable();
          userService.dropUsersTable();
    }
}
