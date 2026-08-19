package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentService {
   private static String url  = "jdbc:mysql://localhost:3306/student2";
   private static String user = "root";
   private static String password = "Dipu@1234";
   private static Connection con  = null;

   static  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,password);
            System.out.println("Now Database initialized");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(int id,String name,String email,int redgno,String password){
        try {
           PreparedStatement prt = con.prepareStatement("insert into student_data values (?,?,?,?,?)");
           prt.setInt(1,id);
           prt.setString(2,name);
           prt.setString(3,email);
           prt.setInt(4,redgno);
           prt.setString(5,password);
           int rows=prt.executeUpdate();
           if (rows>0){
               return true;
           }else{
               return false;
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
