package com.ilongross.concurrency.prof_library.company.work_place;

import com.ilongross.concurrency.prof_library.company.department.Company;
import com.ilongross.concurrency.prof_library.company.department.Department;
import com.ilongross.concurrency.prof_library.company.employes.Engineer;
import com.ilongross.concurrency.prof_library.company.employes.Address;
import com.ilongross.concurrency.prof_library.company.employes.BirthDate;
import com.ilongross.concurrency.prof_library.company.employes.Employee;
import com.ilongross.concurrency.prof_library.company.employes.Grade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompanyIT {

    public static void main(String[] args) {

        var company = new Company();
        var department = new Department("Payments&Transfers");
        company.addDepartment(department);

        var engineer1 = new Engineer(
                "Ilon",
                new BirthDate(27, 11, 1989),
                new Address("Russia", "Irkutskaya", "Angarsk", "Komminterna", "15", "89"),
                new Grade(0));

        var engineer2 = new Engineer(
                "John",
                new BirthDate(12, 8, 198),
                new Address("Russia", "Irkutskaya", "Angarsk", "Komminterna", "15", "89"),
                null);

        var engineer3 = new Engineer(
                "Valery",
                new BirthDate(3, 5, 1986),
                new Address("Russia", "Irkutskaya", "Angarsk", "Komminterna", "15", "89"),
                new Grade(3));


        boolean he2 = company.hireEmployee(engineer2, "Payments&Transfers");
        engineer2.upPositionGrade();
        boolean he3 = company.hireEmployee(engineer3, "Payments&Transfers");
        engineer3.upgradeSalary(30);
        boolean he1 = company.hireEmployee(engineer1, "Payments&Transfers");
        engineer1.upPositionGrade();

        company.hireEmployee(engineer1, "Payments&Transfers");
        company.hireEmployee(engineer2, "Payments&Transfers");
        company.hireEmployee(engineer3, "Payments&Transfers");

        company.paySalary();

        company.dismissEmployee(engineer3);
        company.showDismissedEmployees();

        company.showAllDepartments();
        company.showAllWorkers();


        Runnable task1 = ()-> {
            for (int i = 0; i < 50; i++) {
                Random rand = new Random();
                List<Employee> list = department.getEmployeeList();
                Employee e1 = list.get(rand.nextInt(list.size()));
                Employee e2 = list.get(rand.nextInt(list.size()));
                if(e1.getBankAccId() == e2.getBankAccId())
                    continue;
                if(e1.getBankAmount() == 0 || e2.getBankAmount() == 0)
                    break;
                BigDecimal transfer = new BigDecimal(rand.nextInt(200));
                boolean operation = department.transferMoneyBetweenEmployees(e1, e2 , transfer);
                if(operation == false)
                    continue;
                try {
                    Thread.sleep(100);
                    System.out.printf("%d -> $%.2f -> %d [%s %s]\n",
                            e1.getBankAccId(),
                            transfer.setScale(2).doubleValue(),
                            e2.getBankAccId(),
                            Thread.currentThread(),
                            Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            department.getEmployeeList().forEach(e-> System.out.println(e));
        };

        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<?> f = exec.submit(task1);
        System.out.printf("future=%s\n", f.isDone());
        exec.submit(task1);
        exec.shutdown();


//        new Thread(task1).start();
//        new Thread(task1).start();

//        department.getEmployeeList().forEach(e-> System.out.println(e));


    }



}




