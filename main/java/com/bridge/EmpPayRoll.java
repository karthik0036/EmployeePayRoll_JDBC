package com.bridge;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpPayRoll {
    public static void main(String[] args) {
        String FETCH = "SELECT * FROM employee_payroll";
        ArrayList<EmpInfo> empList = new ArrayList<>();
        EmpConnection service = new EmpConnection();
        service.getConnection();
        PreparedStatement preparedStatement;
        try{
            preparedStatement = service.getConnection().prepareStatement(FETCH);
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
            for (EmpInfo i : empList) {
                System.out.println(i.toString());
            }

        } catch (SQLException e) {
            throw new EmpException("invalid column label");
        }
    }


}
