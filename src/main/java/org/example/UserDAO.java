package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO{

    User find (int id, Connection c) throws SQLException;
    List<User> findOlderThan(int age, Connection c);
    List<User> findAll(Connection c);
    List<User> findByName(String name, Connection c) throws SQLException;

}
