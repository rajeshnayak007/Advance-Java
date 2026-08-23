package org.example;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String password = "Dipu@1234";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from employe");
            while (rs.next()) {
                System.out.println("ID :" + rs.getInt("id"));
                System.out.println("Name :" + rs.getString("name"));
                System.out.println("Ref_No :" + rs.getInt("ref_no"));
                System.out.println("Branch :" + rs.getString("branch"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}