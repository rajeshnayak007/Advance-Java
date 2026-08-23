package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Jdbc {
    public static void main(String[] args) {

                String url = "jdbc:mysql://localhost:3306/mydata";
                String username = "root";
                String password = "Dipu@1234";
                try {
                    Connection con = DriverManager.getConnection(url, username, password);
                    System.out.println("Database Connected Successfully!");
                    String sql = "INSERT INTO data (id, name, age) VALUES (?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, 51);
                    ps.setString(2, "Rajesh");
                    ps.setInt(3, 22);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Record inserted successfully.");
                    } else {
                        System.out.println("Record not inserted.");
                    }
                    ps.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

