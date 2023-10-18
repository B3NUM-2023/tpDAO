package org.example;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM", Locale.FRANCE);

    /**
     * methode  qqui trouve l'utilisateur par son id
     * @param id
     * @param c
     * @return
     * @throws SQLException
     */
    @Override
    public User find(int id, Connection c) throws SQLException {

        PreparedStatement pst = c.prepareStatement("SELECT * FROM personne WHERE id=?");
        User user = null;

        try {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            user = new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastName"),
                    rs.getString("email"), this.formatter.parse(rs.getString("birthday")));
            rs.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return user;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /**
     * methode qui retoune une liste d'utilisateur plus agé que l'age en paramètre
     * @param age
     * @param c
     * @return
     */
    @Override
    public List<User> findOlderThan(int age, Connection c) {

        List<User> listUserOlderThan = new ArrayList<>();
        List<User> listAllUser = this.findAll(c);

        for (User user : listAllUser) {
            if (user.getAge() > age) {
                listUserOlderThan.add(user);
            }
        }

        return listUserOlderThan;
    }

    /**
     * retourne tous les utilisateurs de la table personnes
     * @param c
     * @return
     */
    @Override
    public List<User> findAll(Connection c) {

        String q = "SELECT * FROM personne";
        List<User> res = new ArrayList<>();

        try {
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(q);
            boucleGetUser(this.formatter, res, stm, rs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return res;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    /**
     * retoure les utilisateurs par nom 
     * @param name
     * @param c
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> findByName(String name, Connection c) throws SQLException {

        PreparedStatement pst = c.prepareStatement("SELECT * FROM personne WHERE lastname=?");
        List<User> res = new ArrayList<>();

        try {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            boucleGetUser(this.formatter, res, pst, rs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return res;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    private void boucleGetUser(SimpleDateFormat formatter, List<User> res, Statement stm, ResultSet rs) throws SQLException, ParseException {
        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastName"),
                    rs.getString("email"), formatter.parse(rs.getString("birthday")));
            res.add(user);
        }

        rs.close();
        stm.close();
    }
}