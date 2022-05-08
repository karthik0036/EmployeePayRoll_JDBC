package com.bridge;

import java.util.ArrayList;

public class EmpInfo {
    int id;
    String name, phoneNo, empAddress, dept, startDate, gender;
    double BasicPay, Deductions, TaxablePay, IncomeTax, NetPay;

    ArrayList<EmpInfo> empList;

    public ArrayList<EmpInfo> getEmpList() {
        return empList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getBasicPay() {
        return BasicPay;
    }

    public void setBasicPay(double basicPay) {
        BasicPay = basicPay;
    }

    public double getDeductions() {
        return Deductions;
    }

    public void setDeductions(double deductions) {
        Deductions = deductions;
    }

    public double getTaxablePay() {
        return TaxablePay;
    }

    public void setTaxablePay(double taxablePay) {
        TaxablePay = taxablePay;
    }

    public double getIncomeTax() {
        return IncomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        IncomeTax = incomeTax;
    }

    public double getNetPay() {
        return NetPay;
    }

    public void setNetPay(double netPay) {
        NetPay = netPay;
    }

    @Override
    public String toString() {
        return "EmpInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", dept='" + dept + '\'' +
                ", startDate='" + startDate + '\'' +
                ", gender='" + gender + '\'' +
                ", BasicPay=" + BasicPay +
                ", Deductions=" + Deductions +
                ", TaxablePay=" + TaxablePay +
                ", IncomeTax=" + IncomeTax +
                ", NetPay=" + NetPay +
                '}';
    }
}
