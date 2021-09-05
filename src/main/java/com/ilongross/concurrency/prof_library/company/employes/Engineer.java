package com.ilongross.concurrency.prof_library.company.employes;


import com.ilongross.concurrency.prof_library.company.employes.Address;
import com.ilongross.concurrency.prof_library.company.employes.BirthDate;
import com.ilongross.concurrency.prof_library.company.employes.Employee;
import com.ilongross.concurrency.prof_library.company.employes.Grade;

public class Engineer extends Employee {

    public Engineer(String name, BirthDate birthDate, Address address, Grade grade) {
        super(name, birthDate, address, grade);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
