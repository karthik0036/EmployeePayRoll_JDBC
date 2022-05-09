package com.bridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpPayRollService {

    static ArrayList<EmpInfo> empList;
    PreparedStatement preparedStatement;
    Connection connection = EmpConnection.getConnection();

    public List<EmpInfo> queryExecute(String query) {
        empList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmpInfo employee = new EmpInfo();

                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhoneNo(resultSet.getString("phoneNo"));
                employee.setEmpAddress(resultSet.getString("empAddress"));
                employee.setDept(resultSet.getString("dept"));
                employee.setStartDate(resultSet.getString("startDate"));
                employee.setBasicPay(resultSet.getDouble("BasicPay"));
                employee.setDeductions(resultSet.getDouble("Deductions"));
                employee.setTaxablePay(resultSet.getDouble("TaxablePay"));
                employee.setIncomeTax(resultSet.getDouble("IncomeTax"));
                employee.setNetPay(resultSet.getDouble("NetPay"));

                empList.add(employee);
            }

        } catch (SQLException e) {
            throw new EmpException("invalid column label");
        }
        return empList;

    }

    public static void display() {
        for (EmpInfo i : empList) {
            System.out.println(i.toString());
        }
    }

    public double updateBasicPay(String empName, double basicPay) {
        String UPDATE = "UPDATE employee_payroll SET BasicPay = ? WHERE name = ?";
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDouble(1, basicPay);
            preparedStatement.setString(2, empName);
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM employee_payroll";
        queryExecute(sql);
        for (EmpInfo employee : empList) {
            if (employee.getName().equals(empName)) {
                return employee.getBasicPay();
            }
        }
        return 0.0;
    }
    public void selectEmployee(LocalDate start, LocalDate end){
        ArrayList<EmpInfo> empSelected = new ArrayList<>();
        String select = "SELECT * FROM employee_payroll WHERE EmpStart BETWEEN ? AND ?";
        String sDate = String.valueOf(start);
        String eDate = String.valueOf(end);
        try {
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1,sDate);
            preparedStatement.setString(2, eDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmpInfo employee = new EmpInfo();

                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhoneNo(resultSet.getString("phoneNo"));
                employee.setEmpAddress(resultSet.getString("empAddress"));
                employee.setDept(resultSet.getString("dept"));
                employee.setStartDate(resultSet.getString("startDate"));
                employee.setBasicPay(resultSet.getDouble("BasicPay"));
                employee.setDeductions(resultSet.getDouble("Deductions"));
                employee.setTaxablePay(resultSet.getDouble("TaxablePay"));
                employee.setIncomeTax(resultSet.getDouble("IncomeTax"));
                employee.setNetPay(resultSet.getDouble("NetPay"));

                empSelected.add(employee);
            }
            for (EmpInfo employee:empSelected) {
                System.out.println(employee);
            }

        } catch (SQLException e) {
            throw new EmpException("Invalid date");
        }
    }
    public void calculate() {
        Scanner scanner = new Scanner(System.in);

        final int EXIT = 6;
        int option = 0;
        while (option != EXIT) {
            System.out.println("enter your choice\n1. SUM\n2. AVG\n3. MIN\n4. MAX  \n5.COUNT\n6.EXIT\n");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    calculateQuery("SELECT Gender, SUM(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;

                case 2:
                    calculateQuery("SELECT Gender, AVG(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;

                case 3:
                    calculateQuery("SELECT Gender, MIN(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;
                case 4:
                    calculateQuery("SELECT Gender, MAX(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;
                case 5:
                    calculateQuery("SELECT Gender, COUNT(BasicPay) FROM employee_payroll GROUP BY Gender");
                    break;
            }
        }
    }
    /*
     * this method is used to print the basic pay by using the gender
     * @param calculate
     */

    public void calculateQuery(String calculate) {
        List<EmpInfo> result = new ArrayList<EmpInfo>();

        try {
            preparedStatement = connection.prepareStatement(calculate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmpInfo employee = new EmpInfo();
                employee.setGender(resultSet.getString(1));
                employee.setBasicPay(resultSet.getDouble(2));

                result.add(employee);
            }
            if (calculate.contains("COUNT")) {
                for (EmpInfo i : result) {
                    System.out.println("Gender: " + i.getGender() + " COUNT: " + i.getBasicPay());
                }
            } else {
                for (EmpInfo i : result) {
                    System.out.println("Gender: " + i.getGender() + " Basic pay: " + i.getBasicPay());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}