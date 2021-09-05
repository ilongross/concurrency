package com.ilongross.concurrency.prof_library.company.department;

import com.ilongross.concurrency.prof_library.company.default_classes.Defaults;
import com.ilongross.concurrency.prof_library.company.employes.Employee;

import java.util.*;

public class Company {

    private Map<Integer, Department> departments;
    private Map<Integer, Employee> dismissed;

    public Company() {
        this.departments = new HashMap<>();
        this.dismissed = new HashMap<>();
    }

    public synchronized void paySalary() {
        departments.forEach((k, d)-> d.paySalary());
    }

    public synchronized boolean addDepartment(String name) {
        for (Department d : departments.values()) {
            if(d.getName().equals(name))
                return false;
        }
        var d = new Department(name);
        departments.put(d.getDepartmentId(), d);
        return true;
    }

    public synchronized boolean addDepartment(Department d) {
        if(departments.containsValue(d))
            return false;
        else
            departments.put(d.getDepartmentId(), d);
        return true;
    }

    public synchronized boolean hireEmployee(Employee newWorker, String departmentName) {
        Map<Integer, Employee> allWorkers = getAllWorkers();
        if(allWorkers.containsKey(newWorker.getId()))
            return false;
        return putEmployeeToDepartment(newWorker, departmentName);
    }

    public synchronized boolean dismissEmployee(Employee goOut) {
        int dId = goOut.getDepartment().getDepartmentId();
        for (Department d : departments.values()) {
            if(d.getDepartmentId() == dId) {
                goOut.setDepartment(Defaults.dismissed());
                dismissed.put(goOut.getId(), goOut);
                return d.removeEmployee(goOut);
            }
        }
        return false;
    }

    public synchronized Department getDepartment(String departmentName) {
        for (Department d : departments.values()) {
            if(d.getName().equals(departmentName))
                return d;
        }
        return null;
    }

    public synchronized void showAllDepartments() {
        departments.forEach((k, d)-> System.out.println(d));
    }

    private synchronized boolean putEmployeeToDepartment(Employee e, String departmentName) {
        Department d = getDepartment(departmentName);
        if(d == null)
            return false;
        else
            departments.get(d.getDepartmentId()).addEmployee(e);
        return true;
    }

    public synchronized Map<Integer, Employee> getAllWorkers() {
        Map<Integer, Employee> allWorkers = new HashMap<>();
        for (Department d : departments.values()) {
            for (Employee e : d.getEmployeeList()) {
                allWorkers.put(e.getId(), e);
            }
        }
        return allWorkers;
    }

    public synchronized void showAllWorkers() {
        getAllWorkers().forEach((k, v)-> System.out.println(v));
    }

    public synchronized void showDismissedEmployees() {
        dismissed.forEach((k, e)-> System.out.println(e));
    }


}
