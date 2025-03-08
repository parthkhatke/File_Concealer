
import Views.Welcome_page;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Welcome_page wel= new Welcome_page();

        do{
            wel.welcomepge();
        }while (true);

    }
}
