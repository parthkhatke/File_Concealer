package DAO;

import DataBase.DbConnection;
import Model.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dataDAO {
    public static List<data> getAllFiles(String email) {
        List<data> listdata= new ArrayList<>();
       try
       {
        Connection con= DbConnection.createConnection();
        PreparedStatement st=con.prepareStatement("select * from data where email=?");
        st.setString(1,email);
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            int fid= rs.getInt("fid");
            String fname= rs.getString("fname");
            String fpath= rs.getString("fpath");
            data da= new data(fid,fname,fpath);
            listdata.add(da);

        }

       }catch (SQLException sq)
       {
           sq.printStackTrace();
       }
       return listdata;
    }
    public static int hideFile(data file)
    {
        Connection con= DbConnection.createConnection();
        try {
            PreparedStatement st = con.prepareStatement("insert into data(fname,fpath,email,binaryData) values(?,?,?,?)");
            st.setString(1,file.getFilename());
            st.setString(2,file.getPath());
            st.setString(3,file.getEmail());
            File f = new File(file.getPath());
            FileReader fr= new FileReader(f);
            st.setCharacterStream(4,fr,f.length());
            int rs = st.executeUpdate();
            fr.close();
            f.delete();
            con.close();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void UnHideFile(int id)
    {
        Connection con = DbConnection.createConnection();
        try {
        PreparedStatement st = con.prepareStatement("select fpath , binaryData from data where fid=?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        rs.next();
        String path=rs.getString("fpath");

        Clob cl = rs.getClob("binaryData");// new data type clob= charater large object
        Reader r= cl.getCharacterStream();

        FileWriter fw= new FileWriter(path);
        int i;
        while((i=r.read())!=-1)
        {
            fw.write((char)i);
        }
        fw.close();
        //to remove file from database
        st=con.prepareStatement("delete from data where fid=?");
        st.setInt(1,id);
        st.executeUpdate();
        System.out.println("un-hidden successfully");
        con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
