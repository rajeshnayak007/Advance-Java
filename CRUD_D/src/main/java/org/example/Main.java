package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main (String[]args){
        String url="jdbc:mysql://localhost:3306/company";
        String user="root";
        String password="Dipu@1234";

        try {
            Connection con =DriverManager.getConnection(url,user,password);
            Scanner sc=new Scanner(System.in);
            System.out.print("Enter ID :");
            int id=sc.nextInt();
            PreparedStatement prt=con.prepareStatement("delete from employe where id=?");
            prt.setInt(1,id);
            int result=prt.executeUpdate();
            if(result>0){
                System.out.println("deleted successfully");
            }else{
                System.out.println("Id not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
