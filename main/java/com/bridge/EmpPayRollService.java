package com.bridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}