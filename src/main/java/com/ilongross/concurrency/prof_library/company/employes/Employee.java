package com.ilongross.concurrency.prof_library.company.employes;

import com.ilongross.concurrency.prof_library.company.bank_accs.BankAccount;
import com.ilongross.concurrency.prof_library.company.default_classes.Defaults;
import com.ilongross.concurrency.prof_library.company.department.Department;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee implements Comparable{

    private static int EMPLOYEES_TOTAL_ID = 0;
    private int id;
    private String name;
//    private int age;
    private BirthDate birthDate;
    private Address address;
    private BigDecimal salary;
    private BankAccount bankAccount;
    private Grade grade;
    private Department department;

    public Employee(String name, BirthDate birthDate, Address address, Grade grade) {
        incTotalId();
        this.id = EMPLOYEES_TOTAL_ID;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        if(grade == null)
            this.grade = Defaults.grade();
        else
            this.grade = grade;
        this.salary = Defaults.salary();
        this.department = Defaults.department();
        this.bankAccount = Defaults.bankAccount();
    }

    public synchronized boolean sendMoney(BigDecimal out) {
        return bankAccount.withdrawFromAccount(out);

    }
    public synchronized boolean receiveMoney(BigDecimal in) {
        return bankAccount.addToAmount(in);
    }


    public synchronized void upgradeSalary(double percents) {
        String valueForBigDecimal = String.valueOf(1 + (percents/100));
        BigDecimal newSalary = this.salary.multiply(new BigDecimal(valueForBigDecimal));
        this.salary = newSalary;
    }
    public synchronized void upgradeSalary(BigDecimal amountMoney) {
        BigDecimal newSalary = this.salary.add(amountMoney);
        this.salary = newSalary;
    }

    public synchronized void downgradeSalary(double percents) {
        String valueForBigDecimal = String.valueOf(1 - (percents/100));
        BigDecimal newSalary = this.salary.multiply(new BigDecimal(valueForBigDecimal));
        this.salary = newSalary;
    }

    public synchronized void downgradeSalary(BigDecimal amountMoney) {
        BigDecimal newSalary = this.salary.add(amountMoney.negate());
        this.salary = newSalary;
    }

    public synchronized void upPositionGrade() {
        this.grade.upGrade();
        upgradeSalary(10);
    }

    public synchronized void downPositionGrade() {
        this.grade.downGrade();
        downgradeSalary(5);
    }

    private synchronized static void incTotalId() {
        ++EMPLOYEES_TOTAL_ID;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public synchronized BigDecimal getSalary() {
        return salary.setScale(2);
    }

    public synchronized int getGrade() {
        return this.grade.getGrade();
    }

    public long getBankAccId() {
        return bankAccount.getId();
    }


    public synchronized double getBankAmount(){
        return bankAccount.getAmount().doubleValue();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +": " +
                "dID=" + department.getDepartmentId() +
                ", eID=" + id +
                ", bID=" + bankAccount.getId() +
                ", bank=" + bankAccount.getAmount().setScale(2) +
                ", name=" + name +
                ", grade=" + grade +
                ", salary=" + salary.setScale(2);
    }


    @Override
    public int compareTo(Object o) {
        Employee e = (Employee) o;
        return Integer.compare(id, e.id);
    }
    @Override
    public final boolean equals(Object o) {
        if(this == o)
            return true;
        if (o == null || !(o instanceof Employee) || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
