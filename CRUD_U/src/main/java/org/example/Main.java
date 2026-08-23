package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String password = "Dipu@1234";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter new name: ");
            String name = sc.nextLine();

            System.out.println("Enter new Ref_No: ");
            int ref_no = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new domain: ");
            String branch = sc.nextLine();
            PreparedStatement prt = con.prepareStatement(
                    "update employe set name=?, ref_no=?, branch=? where id=?"
            );

            prt.setString(1, name);
            prt.setInt(2, ref_no);
            prt.setString(3, branch);
            prt.setInt(4, id);

            int result = prt.executeUpdate();

            if (result > 0) {
                System.out.println("data updated");
            } else {
                System.out.println("Id not found");
            }

            prt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
