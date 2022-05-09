package com.bridge;


import java.time.LocalDate;
import java.util.Scanner;

public class EmpPayRoll {
    public static void main(String[] args) {
        EmpPayRollService employeePayRollService = new EmpPayRollService();
        Scanner scanner = new Scanner(System.in);
        final int EXIT = 4;
        int option = 0;
        while (option != EXIT) {
            System.out.println("enter your choice\n1. Execute query\n2. update basic pay\n3. display employee roll\n4. select range of employee\n5. EXIT\n");
            option = scanner.nextInt();
            switch (option) {
                case 1 : {
                    String query = "select * from employee_payroll";
                    employeePayRollService.queryExecute(query);
                    employeePayRollService.display();
                }
                case 2 : {
                    System.out.println("enter employee name");
                    String empName = scanner.next();
                    System.out.println("enter basic pay you want to update");
                    double basicPay = scanner.nextDouble();
                    employeePayRollService.updateBasicPay(empName, basicPay);
                }
                case 3 : EmpPayRollService.display();
                case 4:  {
                    System.out.println("enter initial date");
                    LocalDate startDate = LocalDate.parse(scanner.next());
                    System.out.println("enter final date");
                    LocalDate endDate = LocalDate.parse(scanner.next());
                    employeePayRollService.selectEmployee(startDate,endDate);
                };
                case 5 :
                    System.out.println("exiting...");
            }
        }
    }


}
