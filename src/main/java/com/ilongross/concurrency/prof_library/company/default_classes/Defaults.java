package com.ilongross.concurrency.prof_library.company.default_classes;

import com.ilongross.concurrency.prof_library.company.bank_accs.BankAccount;
import com.ilongross.concurrency.prof_library.company.employes.Grade;
import com.ilongross.concurrency.prof_library.company.department.Department;

import java.math.BigDecimal;

public class Defaults {

    public static BigDecimal salary() {
        return new BigDecimal("2000.00");
    }

    public static Grade grade() {
        return new Grade(0);
    }

    public static Department department() {
        return new Department("Trainees", 0);
    }

    public static Department dismissed() {
        return new Department("Dismissed", -1);
    }

    public static BankAccount bankAccount() {
        return new BankAccount();
    }

}
