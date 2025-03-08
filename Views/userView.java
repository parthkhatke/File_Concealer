package Views;

import DAO.dataDAO;
import DataBase.DbConnection;
import Model.data;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class userView {
    private String email;

    public userView(String email) {
        this.email = email;
    }

    public void userview()
    {
        Connection con= DbConnection.createConnection();
        String name="";
        try {
            PreparedStatement st= con.prepareStatement("select name from user where email= ?");
            st.setString(1,this.email);
            ResultSet n =st.executeQuery();
            while(n.next())
            {
                name=n.getString("name");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        do {
            System.out.println("Welcome - "+name);
            System.out.println("Press 1 to show hidden files in database");
            System.out.println("Press 2 to hide files into database ");
            System.out.println("Press 3 to un-hide  files from database ");
            System.out.println("Press 4 to go back to main menu");
            System.out.println("Press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int choice= Integer.parseInt(sc.nextLine());
            switch (choice)
            {
                case 1->
                {
                    List<data> f =dataDAO.getAllFiles(this.email);
                    System.out.println("ID -- FileName ");
                    for (data file:f)
                    {
                        System.out.println(file.getId()+"    "+file.getFilename());
                    }
                }
                case 2->
                {
                    System.out.println("enter file path - ");
                    String path=sc.nextLine();
                    File f= new File(path);
                    data fobj= new data(f.getName(),path,this.email);
                    int ans=dataDAO.hideFile(fobj);
                    if(ans!=0)
                        System.out.println("file hidden successfully");
                    else
                        System.out.println("error");
                }
                case 3->
                {
                    List<data> f =dataDAO.getAllFiles(this.email);
                    System.out.println("ID -- FileName ");
                    for (data file:f)
                    {
                        System.out.println(file.getId()+"    "+file.getFilename());
                    }
                    System.out.println("enter ID of the file to unhidden - ");
                    int id=Integer.parseInt(sc.nextLine());
                    boolean idvalid=false;
                    for(data ob:f)
                    {
                        if(id==ob.getId())
                        {
                            idvalid=true;
                            break;
                        }
                    }
                    if(idvalid)
                    {
                        dataDAO.UnHideFile(id);
                    }
                    else
                    {
                        System.out.println("enter valid id");
                    }
                }
                case 4->
                {
                    Welcome_page wel= new Welcome_page();
                    wel.welcomepge();
                }
                case 0-> System.exit(0);
            }
        }while(true);


    }
}
