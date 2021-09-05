package com.ilongross.concurrency.prof_library.company.department;

import com.ilongross.concurrency.prof_library.company.default_classes.Defaults;
import com.ilongross.concurrency.prof_library.company.employes.Employee;

import java.math.BigDecimal;
import java.util.*;

public class Department implements Comparable<Department>{

    private static int DEPARTMENT_TOTAL_ID = 0;
    private int departmentId;
    private String name;
    private Set<Employee> employeeSet;
    private Map<Integer, Long> bankIdList;

    public Department(String name, int departmentId) {
        this.name = name;
        this.departmentId = departmentId;
    }

    public Department(String name) {
        incTotalId();
        this.departmentId = DEPARTMENT_TOTAL_ID;
        this.name = name;
        this.employeeSet = new TreeSet<>();
        this.bankIdList = new TreeMap<>();
        initBankIdList();
    }

    public synchronized void paySalary() {
        this.employeeSet.forEach(e-> e.receiveMoney(e.getSalary()));
    }


    public synchronized boolean transferMoneyBetweenEmployees(Employee sender, Employee receiver, BigDecimal amount) {
        if(bankIdList.containsValue(sender.getBankAccId()) &&
                bankIdList.containsValue(receiver.getBankAccId()) &&
                sender != receiver) {
            boolean sendOperation = sender.sendMoney(amount);
            boolean receiveOperation = false;
            if(sendOperation)
                receiveOperation = receiver.receiveMoney(amount);
            if(receiveOperation)
                return true;
        }
        return false;
    }

    private void initBankIdList() {
        this.employeeSet.forEach(e -> bankIdList.put(e.getId(), e.getBankAccId()));
    }


    public synchronized boolean addEmployee(Employee employee) {
        if(this.employeeSet.contains(employee))
            return false;
        employee.setDepartment(this);
        bankIdList.put(employee.getId(), employee.getBankAccId());
        return this.employeeSet.add(employee);
    }
    public synchronized boolean removeEmployee(Employee employee) {
        if(!(this.employeeSet.contains(employee)) || this.employeeSet.isEmpty())
            return false;
        if(employee.getDepartment().getDepartmentId() != -1)
            employee.setDepartment(Defaults.department());
        bankIdList.remove(employee.getId());
        return this.employeeSet.remove(employee);
    }

    public synchronized int getSize() {
        return employeeSet.size();
    }

    private synchronized static void incTotalId() {
        ++DEPARTMENT_TOTAL_ID;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public synchronized List<Employee> getEmployeeList() {
        List<Employee> list = new ArrayList<>();
        for (Employee e : employeeSet) {
            list.add(e);
        }
        return Collections.unmodifiableList(list);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId);
    }

    @Override
    public String toString() {
        return "Department [" + getDepartmentId() + "]: \"" + name + "\"" + " (workers=" + getSize() + ")";
    }

    @Override
    public int compareTo(Department department) {
        if(this.getDepartmentId() == department.getDepartmentId())
            return 0;
        else
            if(this.getDepartmentId() < department.getDepartmentId())
                return -1;
            else
                return 1;
    }
}
