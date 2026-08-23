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
            System.out.print("Enter Name :");
            String name=sc.next();
            System.out.print("Enter Ref No :");
            int ref_no=sc.nextInt();
            System.out.print("Enter Branch :");
            String branch=sc.next();

            PreparedStatement prt=con.prepareStatement("insert into employe values(?,?,?,?)");
            prt.setInt(1,id);
            prt.setString(2,name);
            prt.setInt(3,ref_no);
            prt.setString(4,branch);
            int result=prt.executeUpdate();
            if(result>0){
                System.out.println("inserted successfully");
            }else{
                System.out.println("Data not inserted");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
