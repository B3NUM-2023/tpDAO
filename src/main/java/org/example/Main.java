package org.example;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserDAOImpl userDAO = new UserDAOImpl();
        String etoiles ="********************************************";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:users.db", "jdbc", "pwd");

            if (conn != null) {
                System.out.println("Connected to the database !");

                //find
                System.out.println("Find :");
                System.out.println(userDAO.find(2, conn).toString());
                System.out.println(etoiles);

                //findOlderThan
                List<User> listUserOldenThan = userDAO.findOlderThan(65, conn);
                System.out.println("FindOlderThan:");
                for(User user : listUserOldenThan) {
                    System.out.println(user.toString());
                }
                System.out.println(etoiles);

                //findAll
                List<User> listUser = userDAO.findAll(conn);
                System.out.println("FindAll :");
                for(User user : listUser){
                    System.out.println(user.toString());
                }
                System.out.println(etoiles);

                //findByName
                System.out.println("FindByName :");
                System.out.println(userDAO.findByName("Chirac", conn));
                System.out.println(etoiles);

                conn.close();

            } else {
                System.out.println("Failed to make connection !");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}