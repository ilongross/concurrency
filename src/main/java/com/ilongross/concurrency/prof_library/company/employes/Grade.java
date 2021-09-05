package com.ilongross.concurrency.prof_library.company.employes;

public class Grade {
    private int grade;

    public Grade(int grade) {
        this.grade = grade;
    }
    public int getGrade() {
        return grade;
    }
    public void upGrade() {
        ++this.grade;
    }
    public void downGrade() {
        if(grade > 1)
            --this.grade;
    }

    @Override
    public String toString() {
        return "" + grade;
    }
}
