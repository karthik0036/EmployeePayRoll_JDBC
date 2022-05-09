package com.bridge;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class EmpPayRollTest {
    EmpPayRollService employeePayRollService = new EmpPayRollService();

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        String sql = "select * from employee_payroll";
        List<EmpInfo> employeePayrollDataList = employeePayRollService.queryExecute(sql);
        Assert.assertEquals(7, employeePayrollDataList.size());
    }

    @Test
    public void givenUpdatingCersiBasicPay_whenUpdate_ShouldReturnUpdatedPay() {
        double BasicPay = 40000;
        String Name = "Cersi";
        double salaryUpdated = employeePayRollService.updateBasicPay(Name, BasicPay);
        Assert.assertEquals(BasicPay, salaryUpdated,0.0);
    }
}
