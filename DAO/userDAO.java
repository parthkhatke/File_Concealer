package DAO;

import DataBase.DbConnection;
import Model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    public static boolean userexists(String email) throws SQLException {
        Connection con = DbConnection.createConnection();
        PreparedStatement statement = con.prepareStatement("select email from user");
        ResultSet set = statement.executeQuery();
        while(set.next())
        {
            String mail = set.getString("email");
            if (mail.equals(email))
            {
                System.out.println(mail);
                return true;
            }
        }

        return false;
    }
    public static void saveuser(user user) throws SQLException
    {
        Connection con = DbConnection.createConnection();
        PreparedStatement statement= con.prepareStatement("insert into user (name , email) values (?,?)");
        statement.setString(1,user.getName());
        statement.setString(2,user.getEmail());
        statement.executeUpdate();
    }
}
