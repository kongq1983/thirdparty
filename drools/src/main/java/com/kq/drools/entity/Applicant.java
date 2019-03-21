package com.kq.drools.entity;

/**
 * Applicant
 *
 * @author kq
 * @date 2019-03-20
 */
public class Applicant {

    private String name;
    private int age;
    private boolean valid = true;

    public Applicant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", valid=" + valid +
                '}';
    }
}
