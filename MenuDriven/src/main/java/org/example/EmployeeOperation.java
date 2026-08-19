package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;

public class EmployeeOperation {

    Scanner sc = new Scanner(System.in);

    public void addEmployee() {


        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            String sql = "INSERT INTO employee VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setDouble(4, salary);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Employee Added Successfully...");
            } else {
                System.out.println("Failed!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void viewEmployee() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM employee";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nID\tName\tDepartment\tSalary");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t"
                                + rs.getString("name") + "\t"
                                + rs.getString("department") + "\t"
                                + rs.getDouble("salary"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateEmployee() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("\n===== UPDATE MENU =====");
            System.out.println("1. Update Name");
            System.out.println("2. Update Department");
            System.out.println("3. Update Salary");
            System.out.println("4. Update All");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = null;

            switch (choice) {

                case 1:
                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();

                    ps = con.prepareStatement("UPDATE employee SET name=? WHERE id=?");
                    ps.setString(1, name);
                    ps.setInt(2, id);
                    break;

                case 2:
                    System.out.print("Enter New Department: ");
                    String department = sc.nextLine();

                    ps = con.prepareStatement("UPDATE employee SET department=? WHERE id=?");
                    ps.setString(1, department);
                    ps.setInt(2, id);
                    break;

                case 3:
                    System.out.print("Enter New Salary: ");
                    double salary = sc.nextDouble();

                    ps = con.prepareStatement("UPDATE employee SET salary=? WHERE id=?");
                    ps.setDouble(1, salary);
                    ps.setInt(2, id);
                    break;

                case 4:
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Department: ");
                    String newDepartment = sc.nextLine();

                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();

                    ps = con.prepareStatement(
                            "UPDATE employee SET name=?, department=?, salary=? WHERE id=?");

                    ps.setString(1, newName);
                    ps.setString(2, newDepartment);
                    ps.setDouble(3, newSalary);
                    ps.setInt(4, id);
                    break;

                default:
                    System.out.println("Invalid Choice!");
                    con.close();
                    return;
            }

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Employee Updated Successfully...");
            } else {
                System.out.println("Employee Not Found...");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteEmployee() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM employee WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Employee Deleted Successfully...");
            } else {
                System.out.println("Employee ID Not Found...");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}