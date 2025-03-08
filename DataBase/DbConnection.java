package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection con;
    public static Connection createConnection()
    {
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/filehider";
        String password = "parth";
        String user = "root";
        con = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e)
        {
            System.out.println("Connection unsucessful due to this error " + e);
        }

        return con;
    }
    public static void closeConnection()
    {
        if(con != null)
        {
        try {
            con.close();
        }
        catch (SQLException e) {
            System.out.println("error in closing the connection "+ e );
        }
        }
    }


}
