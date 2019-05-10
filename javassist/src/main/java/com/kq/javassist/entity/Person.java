package com.kq.javassist.entity;

/**
 * Person
 *
 * @author kq
 * @date 2019-05-10
 */
public class Person extends BaseEntity{

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("age=").append(age);
        sb.append(", id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
